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
 $('#selectTable').click(function () {
  $("#selectColumn").prop("disabled", false);
 });
 $('#selectColumn').click(function () {
  $("#selectRule").prop("disabled", false);
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
 if (whatBRT == 'ACR') {
  console.log('YEET');
  //hide div
  $("#divName").addClass('d-none');
 // show div
 //  $("#divName").removeClass('d-none');
 }
}