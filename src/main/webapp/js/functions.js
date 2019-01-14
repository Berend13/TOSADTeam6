$('#singlebutton').on('click', function() {

 var inputTable = $('#selectTable :selected').val();
 var inputColumn = $('#selectColumn :selected').val();
 var inputValue1 = $('#textInput1').val();
 var inputValue2 = $('#textInput2').val();
 var inputCategory = $('#selectCategory :selected').val();
 var inputBRT = $('#selectBRT :selected').val();
 var inputRule = $('#selectRule :selected').val();


 localStorage.setItem("table", inputTable);
 localStorage.setItem("column", inputColumn);
 localStorage.setItem("category", inputCategory);
 localStorage.setItem("BRT", inputBRT);
 localStorage.setItem("value 1", inputValue1);
 localStorage.setItem("Rule", inputRule);
 localStorage.setItem("value 2", inputValue2);
});