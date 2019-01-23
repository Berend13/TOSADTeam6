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
  var inputTable = $('#selectTable :selected').val();
  var inputColumn = $('#selectColumn :selected').val();
  var inputValue1 = $('#value1Input').val();
  var inputRule = $('#selectRule :selected').val();
  var inputValue2 = $('#value2Input').val();
  var inputError = $('#errorInput').val();
  var BRT = sessionStorage.getItem('BRT');

  $.ajax({
   url: 'api/businessrule/new',
   type: 'POST',
   data: {
    BusinessFunction : BRT,
    BusinessName: inputName,
    BusinessTable: inputTable,
    BusinessColumn: inputColumn,
    BusinessValue1: inputValue1,
    BusinessRule: inputRule,
    BusinessValue2: inputValue2,
    BusinessError: inputError
   }
  })
      .done(function () {
       console.log("success");
      })
      .fail(function () {
       console.log("error");
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
  $('#businessRuleTable').DataTable({searching: false, info: false});
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