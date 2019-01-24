function sendBRT() {
 $('#toCreate').on('click', function () {
  $('#selectBRT').find("option:selected").text();
  selectedText = $('#selectBRT').find("option:selected").val();
  sessionStorage.setItem('BRT' , selectedText);
});
}

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

function postARR() {
 $('#sendbutton').on('click', function () {
  event.preventDefault();

  var inputName = $('#selectName').val();
  var inputTable1 = $('#selectTable1 :selected').val();
  var inputColumn1 = $('#selectColumn1 :selected').val();
  var inputValue1 = $('#value1Input').val();
  var inputRuleBetween = $('#selectRuleBetween :selected').val();
  var inputValue2 = $('#value2Input').val();
  var inputError = $('#errorInput').val();
  var BRT = sessionStorage.getItem('BRT');

  sessionStorage.setItem('inputName' , inputName);
  sessionStorage.setItem('inputTable1' , inputTable1);
  sessionStorage.setItem('inputColumn1' , inputColumn1);
  sessionStorage.setItem('inputValue1' , inputValue1);
  sessionStorage.setItem('inputRuleBetween' , inputRuleBetween);
  sessionStorage.setItem('inputValue2' , inputValue2);
  sessionStorage.setItem('inputError' , inputError);

  $.ajax({
   url: 'api/businessrule/new',
   type: 'POST',
   data: {
    BusinessFunction : BRT,
    BusinessName: inputName,
    BusinessTable: inputTable1,
    BusinessColumn: inputColumn1,
    BusinessValue1: inputValue1,
    BusinessRule: inputRuleBetween,
    BusinessValue2: inputValue2,
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

 })
  .always(function () {
   console.log("complete");
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
}
}


function getAllRules() {
 $(document).ready(function () {
  $.ajax({
   url: 'api/businessrule/all',
   type: 'GET',
   success: function(rules){
    console.log(rules);
    $.each(rules, function(index, rule) {
     var table_tr = "<tr><td>"  +rule.ID+   "</td><td>"  +rule.name+ "</td><td>"  +rule.code+ "</td></tr>";


     // append to table
     // $("#businessRuleTable > tbody > tr:first-child").remove();
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





