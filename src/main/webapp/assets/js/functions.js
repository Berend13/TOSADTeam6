//  initiate Variables
var whatBRT = sessionStorage.getItem("BRT");
var inputCBI;
var inputCBU;
var inputCBD;
var inputCBFER;
var amountOfFields;


// This function sends the businessruletype from the specify page to the create or createfromsession page
function sendBRT() {
  $("#toCreate").on("click", function () {
    $("#selectBRT").find("option:selected").text();   
    selectedText = $("#selectBRT").find("option:selected").val();   
    sessionStorage.setItem("BRT", selectedText); 
  }); 
}

// This functions only shows static rules or dynamic rules and enabled the dropdown for rules
function dropdownRule() {
 $("#selectCategory").change(function () {
  if (typeof $(this).data("options") === "undefined") {
   $(this).data("options", $("#selectBRT option").clone());
 }
 var id = $(this).val();
 var options = $(this).data("options").filter("[data-option=" + id + "]");
 $("#selectBRT").html(options);
});

 $("#selectCategory").click(function () {
  $("#selectBRT").prop("disabled", false);
});
}

//This function enables and disabled the dropdowns in create
function disableDropdown(){
 $("#selectTable1").click(function () {
  $("#selectColumn1").prop("disabled", false);
});
 $("#selectColumn1").click(function () {
  $("#selectRuleBetween").prop("disabled", false);
  $("#selectRuleCompare").prop("disabled", false);
});
 $("#selectRuleCompare").click(function () {
  $("#selectTable2").prop("disabled", false);
  $("#selectColumn2").prop("disabled", false);
});
}

//This function removes the ability to use spaces in trigger name
function noSpace(event){
 var k = event ? event.which : window.event.keyCode;
 if (k == 32) return false;
}

//This function retreives all the tables 
function getTables(){
  $.ajax({
    url: "api/businessrule/tables",
    type: "GET",
  })
  .done(function(tableName) {
    $.each(tableName, function(index, name) {
      var tableOption = "<option value=" +name.tableName+ ">"+name.tableName+"</option>";
      $(tableOption).appendTo("#selectTable1");
      $(tableOption).appendTo("#selectTable2");
    });
  });
}

//This function retreives all the columns based on the table
function getColumns(tableName, select,){
  $.ajax({
    url: "api/businessrule/columns/"+ tableName +"/" + whatBRT,
    type: "GET"
  })
  .done(function(columnName) {
    if ($("#selectColumn1 option").length > 1 && select == "select1") {
      $("#selectColumn1").empty();
    }
    if ($("#selectColumn2 option").length > 1 && select == "select2") {
      $("#selectColumn2").empty();
    }
    if ($("#selectColumn1 option").length < 2 && select == "select1") {
      $.each(columnName, function(index, name) {
        var columnOption = "<option value=" +name.column+ ">"+name.column+"</option>";
        $(columnOption).appendTo("#selectColumn1");
      });
    }
    if ($("#selectColumn2 option").length < 2 && select == "select2"){
      $.each(columnName, function(index, name) {
        var columnOption = "<option value=" +name.column+ ">"+name.column+"</option>";
        $(columnOption).appendTo("#selectColumn2");
      });
    }
  });
}

//This function posts the trigger to the database with parameters and puts everything in session storage for createfromsession
function postTrigger() {
 $("#sendbutton").click(function(event) {
  event.preventDefault();
  if ($("#CBInsert").is(":checked"))
  {
    sessionStorage.setItem("selectCBI" , "checked");
  }else{
   sessionStorage.setItem("selectCBI" , ""); 
 }if ($("#CBUpdate").is(":checked"))
 {
  sessionStorage.setItem("selectCBU" , "checked");
}else{
 sessionStorage.setItem("selectCBU" , ""); 
}if ($("#CBDelete").is(":checked"))
{
  sessionStorage.setItem("selectCBD" , "checked");
}else{
 sessionStorage.setItem("selectCBD" , ""); 
}if ($("#CBFER").is(":checked"))
{
  sessionStorage.setItem("selectCBFER" , "checked");
}else{
 sessionStorage.setItem("selectCBFER" , ""); 
}
var inputName = $("#selectName").val();
var inputTable1 = $("#selectTable1 :selected").val();
var inputTrigger = $("#selectTrigger :selected").val();
var inputColumn1 = $("#selectColumn1 :selected").val();
var inputValue1 = $("#value1Input").val();
var inputRuleBetween = $("#selectRuleBetween :selected").val();
var inputRuleCompare = $("#selectRuleCompare :selected").val();
var inputValue2 = $("#value2Input").val();
var inputTable2 = $("#selectTable2 :selected").val();
var inputColumn2 = $("#selectColumn2 :selected").val();
var inputSQL = $("#userSQLInput").val();
var inputError = $("#errorInput").val();
var BRT = sessionStorage.getItem("BRT");
var fields = $(".field_value");
var fieldList = [];
$.each(fields, function(index, field) {
  fieldList.push($(field).val());
});

sessionStorage.setItem("fieldList", fieldList);
sessionStorage.setItem("inputName" , inputName);
sessionStorage.setItem("inputTable1" , inputTable1);
sessionStorage.setItem("inputTrigger", inputTrigger);
sessionStorage.setItem("inputColumn1" , inputColumn1);
sessionStorage.setItem("inputValue1" , inputValue1);
sessionStorage.setItem("inputRuleBetween" , inputRuleBetween);
sessionStorage.setItem("inputRuleCompare" , inputRuleCompare);
sessionStorage.setItem("inputValue2" , inputValue2);
sessionStorage.setItem("inputTable2" , inputTable2);
sessionStorage.setItem("inputColumn2" , inputColumn2);
sessionStorage.setItem("inputSQL" , inputSQL);
sessionStorage.setItem("inputError" , inputError);

$.ajax({
 url: "api/businessrule/new",
 type: "POST",
 data: {
  BusinessFunction    : BRT,
  BusinessName        : inputName,
  BusinessList        : fieldList,
  BusinessTable1      : inputTable1,
  BusinessTrigger     : inputTrigger, 
  BusinessColumn1     : inputColumn1,
  BusinessValue1      : inputValue1,
  BusinessRuleBetween : inputRuleBetween,
  BusinessRuleCompare : inputRuleCompare,
  BusinessValue2      : inputValue2,
  BusinessTable2      : inputTable2,
  BusinessSQL         : inputSQL,
  BusinessColumn2     : inputColumn2,
  BusinessError       : inputError
}
})

.done(function () {
 $.notify({title: "<b>Success!</b>", message: "The business rule has been generated. "},{type: "success"});
 sessionStorage.setItem("type" , "success");
 successDangerNotification();
})
.fail(function () {
 $.notify({title: "<b>Unfortunately...</b>", message: "An error has occured."},{type: "danger"});
 sessionStorage.setItem("type" , "danger");
 successDangerNotification();

});

});
}


//This functions shows the specific inputs for each businessrule
function showHide() {
 if (whatBRT == "ARR") {
  $("#title").append("Attribute range rule");
  $("#divTable1").removeClass("d-none");
  $("#divColumn1").removeClass("d-none");
  $("#divValue1").removeClass("d-none");
  $("#divRuleBetween").removeClass("d-none");
  $("#divValue2").removeClass("d-none");
}else if (whatBRT =="ACR"){
  $("#title").append("Attribute compare rule");
  $("#divTable1").removeClass("d-none");
  $("#divColumn1").removeClass("d-none");
  $("#divRuleCompare").removeClass("d-none");
  $("#divValue2").removeClass("d-none");

}else if (whatBRT =="TCR"){
  $("#title").append("Tuple compare rule");
  $("#divTable1").removeClass("d-none");
  $("#divColumn1").removeClass("d-none");
  $("#divRuleCompare").removeClass("d-none");
  $("#divColumn2").removeClass("d-none");

}else if (whatBRT =="IECR"){
  $("#title").append("Inter entity compare rule");
  $("#divTable1").removeClass("d-none");
  $("#divColumn1").removeClass("d-none");
  $("#divRuleCompare").removeClass("d-none");
  $("#divTable2").removeClass("d-none");
  $("#divColumn2").removeClass("d-none");
}else if (whatBRT == "ALR"){
  $("#title").append("attribute list rule");
  $("#divTable1").removeClass("d-none");
  $("#divColumn1").removeClass("d-none");
  $("#divList").removeClass("d-none");
}else if (whatBRT == "AOR"){
  $("#title").append("Attribute other rule");
  $("#divTriggerTiming").removeClass("d-none");
  $("#divDMLStatements").removeClass("d-none");
  $("#divTable1").removeClass("d-none");
  $("#divCheckboxU").removeClass("d-none");
  $("#divCheckboxD").removeClass("d-none");
  $("#divCheckboxI").removeClass("d-none");
  $("#divCheckboxFER").removeClass("d-none");
  $("#divUserSQL").removeClass("d-none");
  $("#userSQLInput").append("Declare\n\nBegin\n\nEND (Triggername);");
}else if (whatBRT == "EOR"){
  $("#title").append("Entity other rule");
  $("#divTriggerTiming").removeClass("d-none");
  $("#divDMLStatements").removeClass("d-none");
  $("#divTable1").removeClass("d-none");
  $("#divCheckboxU").removeClass("d-none");
  $("#divCheckboxD").removeClass("d-none");
  $("#divCheckboxI").removeClass("d-none");
  $("#divCheckboxFER").removeClass("d-none");
  $("#divUserSQL").removeClass("d-none");
  $("#userSQLInput").append("Declare\n\nBegin\n\nEND (Triggername);");
}else if (whatBRT == "TOR"){
  $("#title").append("Tuple other rule");
  $("#divTriggerTiming").removeClass("d-none");
  $("#divDMLStatements").removeClass("d-none");
  $("#divTable1").removeClass("d-none");
  $("#divCheckboxU").removeClass("d-none");
  $("#divCheckboxD").removeClass("d-none");
  $("#divCheckboxI").removeClass("d-none");
  $("#divCheckboxFER").removeClass("d-none");
  $("#divUserSQL").removeClass("d-none");
  $("#userSQLInput").append("Declare\n\nBegin\n\nEND (Triggername);");
}else{
 alert("Oops! Something went wrong. Maybe you did not select a business rule type.");
 location.replace("home.html");
}
};


//This function Retreives all the rules and appends them into the table
function getAllRules() {
 $(document).ready(function () {
  $.ajax({
   url: "api/businessrule/all",
   type: "GET",
   success: function(rules){
    $.each(rules, function(index, rule) {
     var table_tr = "<tr><td>"  +rule.ID+   "</td><td>"  +rule.name+ "</td><td>"  +rule.code+ "</td></tr>";
     $(table_tr).appendTo("#tbodyBusinessRuleTable");
   });
  }
});
});
}

//This function is used when you want to search in the businessrule table
function searchMachine() {
 $(document).ready(function () {
  $("#search").on("keyup", function () {
   var value = $(this).val().toLowerCase();
   $("#tbodyBusinessRuleTable tr").filter(function () {
    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
  });
 });
});
}


//This function gives notifications and puts them in a list so that you can edit them later
function successDangerNotification(){
  var sesType = sessionStorage.getItem("type");
  var amount = $("#notificationsList li").length + 1;
  if (sesType == "success") {
    $("#notificationsList").empty();
    $("#notificationsList").append("<li><a href='createFromSession.html'>success: " + sessionStorage.getItem('inputName') + "</a></li>");
    $("#notificationAmount").text(amount);
  } else if (sesType == "danger") {
    $("#notificationsList").empty();
    $("#notificationsList").append("<li><a href='createFromSession.html'>Error: " + sessionStorage.getItem('inputName') + "</a></li>");
    $("#notificationAmount").text(amount);
  }
}

$(".add_field_button").click(function() {
  var field2 = " <div class='form-group'> <label class='col-md-4 control-label' for='valueInputDynamic'></label> <div class='col-md-4'> <div class='input-group'> <input type='text' class='form-control field_value' placeholder='...''> <span class='input-group-btn'> <button class='btn btn-default remove_field_button' type='button'>-</button> </span> </div></div></div>";

  $(field2).appendTo("#field_holder");
  var amountOfFields = $("#field_holder > div").length;
  sessionStorage.setItem("amountOfFields", amountOfFields);
  removeField();
});

//This function makes sure that you can edit a businessrule that you previously made
function createFromSession(){
  var checkCBI = sessionStorage.getItem("selectCBI");
  var checkCBU = sessionStorage.getItem("selectCBU");
  var checkCBD = sessionStorage.getItem("selectCBD");
  var checkCBFER = sessionStorage.getItem("selectCBFER");
  var textField = sessionStorage.getItem("fieldList");
  textField = textField.split(",");
  var amountOfFields = sessionStorage.getItem("amountOfFields");

  $("#selectName").val(sessionStorage.getItem("inputName"));
  $("#selectTable1").val(sessionStorage.getItem("inputTable1"));
  $("#selectTrigger").val(sessionStorage.getItem("inputTrigger"));
  $("#selectColumn1").val(sessionStorage.getItem("inputColumn1"));
  $("#value1Input").val(sessionStorage.getItem("inputValue1"));
  $("#selectRuleBetween").val(sessionStorage.getItem("inputRuleBetween"));
  $("#value2Input").val(sessionStorage.getItem("inputValue2"));
  $("#userSQLInput").val(sessionStorage.getItem("inputSQL"));
  $("#errorInput").val(sessionStorage.getItem("inputError"));

  $.each(textField, function(index, field) {
    if (index == 0) {
      $("#listInput").val(field);
    }else{
     $("<div class='form-group'> <label class='col-md-4 control-label' for='valueInputDynamic'></label> <div class='col-md-4'> <div class='input-group'> <input type='text' class='form-control field_value' placeholder='...'  value=" +field+ "> <span class='input-group-btn'> <button class='btn btn-default remove_field_button' type='button'>-</button> </span> </div></div></div>").appendTo("#field_holder");
   }
 });

  if (checkCBI == "checked"){
    $("#CBInsert").attr("checked","");
  }
  if (checkCBU == "checked"){
    $("#CBUpdate").attr("checked","");
  }
  if (checkCBD == "checked"){
    $("#CBDelete").attr("checked","");
  }
  if (checkCBFER == "checked"){
    $("#CBFER").attr("checked","");
  }
}

//This function is used when you do not what a field for the list rule
function removeField(){
 $(".remove_field_button").click(function() {
  $(this).closest(".form-group").remove();
});
}












