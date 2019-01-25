function sendBRT() {  $('#toCreate').on('click', function () {
  $('#selectBRT').find("option:selected").text();   selectedText =
  $('#selectBRT').find("option:selected").val();   sessionStorage.setItem('BRT'
    , selectedText); }); }

whatBRT = sessionStorage.getItem('BRT');

function specifyFunction() {
 $("#selectCategory").change(function () {
  if (typeof $(this).data('options') === "undefined") {
   $(this).data('options', $('#selectBRT option').clone());
 }
 var id = $(this).val();
 var options = $(this).data('options').filter('[data-option=' + id + ']');
 $('#selectBRT').html(options);
 $('#selectBRT').selectpicker('refresh');
});

 $('#selectCategory').click(function () {
  $("#selectBRT").prop("disabled", false);
});
}

function disableDropdown(){
 $('#selectTable1').click(function () {
  $("#selectColumn1").prop("disabled", false);
});
 $('#selectColumn1').click(function () {
  $("#selectRuleBetween").prop("disabled", false);
  $("#selectRuleCompare").prop("disabled", false);
});
 $('#selectRuleCompare').click(function () {
  $("#selectTable2").prop("disabled", false);
  $("#selectColumn2").prop("disabled", false);
});
}

function noSpace(event){
 var k = event ? event.which : window.event.keyCode;
 if (k == 32) return false;
}

function getTables(){
  $.ajax({
    url: 'api/businessrule/tables',
    type: 'GET',
  })
  .done(function(tableName) {
    $.each(tableName, function(index, name) {
      var tableOption = "<option value=" +name.tableName+ ">"+name.tableName+"</option>";
      $(tableOption).appendTo("#selectTable1");
      $(tableOption).appendTo("#selectTable2");
    });
  });
}

function getColumns(tableName, select,){


  $.ajax({
    url: 'api/businessrule/columns/'+ tableName +"/" + whatBRT,
    type: 'GET'
  })
  .done(function(columnName) {
    if ($('#selectColumn1 option').length > 1 && select == 'select1') {
      $('#selectColumn1').empty();
    }
    if ($('#selectColumn2 option').length > 1 && select == 'select2') {
      $('#selectColumn2').empty();
    }
    if ($('#selectColumn1 option').length < 2 && select == 'select1') {
      $.each(columnName, function(index, name) {
        var columnOption = "<option value=" +name.column+ ">"+name.column+"</option>";
        $(columnOption).appendTo("#selectColumn1");
      });
    }
    if ($('#selectColumn2 option').length < 2 && select == 'select2'){
      $.each(columnName, function(index, name) {
        var columnOption = "<option value=" +name.column+ ">"+name.column+"</option>";
        $(columnOption).appendTo("#selectColumn2");
      });
    }
  });
}

function postTrigger() {
 $('#sendbutton').on('click', function () {
  event.preventDefault();

  var inputName = $('#selectName').val();
  var inputTable1 = $('#selectTable1 :selected').val();
  var inputColumn1 = $('#selectColumn1 :selected').val();
  var inputValue1 = $('#value1Input').val();
  var inputRuleBetween = $('#selectRuleBetween :selected').val();
  var inputRuleCompare = $('#selectRuleCompare :selected').val();
  var inputValue2 = $('#value2Input').val();
  var inputTable2 = $('#selectTable2 :selected').val();
  var inputColumn2 = $('#selectColumn2 :selected').val();
  var inputError = $('#errorInput').val();
  var BRT = sessionStorage.getItem('BRT');
  var fields = $(".field_value");
  var fieldList = [];
  $.each(fields, function(index, field) {
    fieldList.push($(field).val());
  });

  console.log(fieldList);

  sessionStorage.setItem('inputName' , inputName);
  sessionStorage.setItem('inputTable1' , inputTable1);
  sessionStorage.setItem('inputColumn1' , inputColumn1);
  sessionStorage.setItem('inputValue1' , inputValue1);
  sessionStorage.setItem('inputRuleBetween' , inputRuleBetween);
  sessionStorage.setItem('inputRuleCompare' , inputRuleCompare);
  sessionStorage.setItem('inputValue2' , inputValue2);
  sessionStorage.setItem('inputTable2' , inputTable2);
  sessionStorage.setItem('inputColumn2' , inputColumn2);
  sessionStorage.setItem('inputError' , inputError);

  $.ajax({
   url: 'api/businessrule/new',
   type: 'POST',
   data: {
    BusinessFunction : BRT,
    BusinessName: inputName,
    BusinessTable1: inputTable1,
    BusinessColumn1: inputColumn1,
    BusinessValue1: inputValue1,
    BusinessRuleBetween: inputRuleBetween,
    BusinessRuleCompare: inputRuleCompare,
    BusinessValue2: inputValue2,
    BusinessTable2: inputTable2,
    BusinessColumn2: inputColumn2,
    BusinessError: inputError
  }
})

  .done(function () {
   $.notify({title: "<b>Success!</b>", message: "The business rule has been generated. "},{type: "success"});
   sessionStorage.setItem('type' , 'success');
   successDangerNotification();
 })
  .fail(function () {
   $.notify({title: "<b>Unfortunately...</b>", message: "An error has occured."},{type: "danger"});
   sessionStorage.setItem('type' , 'danger');
   successDangerNotification();

 });

});
}



function showHide() {
 if (whatBRT == 'ARR') {
  $("#divTable1").removeClass('d-none');
  $("#divColumn1").removeClass('d-none');
  $("#divValue1").removeClass('d-none');
  $("#divRuleBetween").removeClass('d-none');
  $("#divValue2").removeClass('d-none');
}else if (whatBRT =='ACR'){
  $("#divTable1").removeClass('d-none');
  $("#divColumn1").removeClass('d-none');
  $("#divRuleCompare").removeClass('d-none');
  $("#divValue2").removeClass('d-none');

}else if (whatBRT =='TCR'){
  $("#divTable1").removeClass('d-none');
  $("#divColumn1").removeClass('d-none');
  $("#divRuleCompare").removeClass('d-none');
  $("#divColumn2").removeClass('d-none');

}else if (whatBRT =='IECR'){
  $("#divTable1").removeClass('d-none');
  $("#divColumn1").removeClass('d-none');
  $("#divRuleCompare").removeClass('d-none');
  $("#divTable2").removeClass('d-none');
  $("#divColumn2").removeClass('d-none');
}else if (whatBRT == 'ALR'){
 $("#divValueDynamic").removeClass('d-none');
}else if (whatBRT == 'EOR'){

}
else{
 alert('Oops! Something went wrong. Maybe you did not select a business rule type.');
 location.replace(index.html);
}}


function getAllRules() {
 $(document).ready(function () {
  $.ajax({
   url: 'api/businessrule/all',
   type: 'GET',
   success: function(rules){
    console.log(rules);
    $.each(rules, function(index, rule) {
     var table_tr = "<tr><td>"  +rule.ID+   "</td><td>"  +rule.name+ "</td><td>"  +rule.code+ "</td></tr>";
     $(table_tr).appendTo("#tbodyBusinessRuleTable");
   });
  }
});
});
}

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

function successDangerNotification(){
  var sesType = sessionStorage.getItem('type');
  var amount = $('#notificationsList li').length + 1;
  if (sesType == 'success') {
    $("#notificationsList").empty();
    $("#notificationsList").append("<li><a href='createFromSession.html'>success: " + sessionStorage.getItem('inputName') + "</a></li>");
    $('#notificationAmount').text(amount);
  } else if (sesType == 'danger') {
    $("#notificationsList").empty();
    $("#notificationsList").append("<li><a href='createFromSession.html'>Error: " + sessionStorage.getItem('inputName') + "</a></li>");
    $('#notificationAmount').text(amount);
  }
}



function createFromSession(){
  $('#selectName').val(sessionStorage.getItem('inputName'));
  $('#selectTable1').val(sessionStorage.getItem('inputTable1'));
  $('#selectColumn1').val(sessionStorage.getItem('inputColumn1'));
  $('#value1Input').val(sessionStorage.getItem('inputValue1'));
  $('#selectRuleBetween').val(sessionStorage.getItem('inputRuleBetween'));
  $('#value2Input').val(sessionStorage.getItem('inputValue2'));
  $('#errorInput').val(sessionStorage.getItem('inputError'));

}

function removeField(){
 $(".remove_field_button").click(function() {
  $(this).closest('.form-group').remove();
});
}

$(".add_field_button").click(function() {
  var field2 = " <div class='form-group'> <label class='col-md-4 control-label' for='valueInputDynamic'></label> <div class='col-md-4'> <div class='input-group'> <input type='text' class='form-control field_value' placeholder='...'> <span class='input-group-btn'> <button class='btn btn-default remove_field_button' type='button'>-</button> </span> </div></div></div>";


  $(field2).appendTo('#field_holder');

  removeField();
});











