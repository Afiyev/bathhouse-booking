/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myFunction(id) {
    document.getElementById(id).classList.toggle("show");
}

function setDataType(cabin, time) {
    document.getElementById('timeIndex').value = time;
    document.getElementById('cabinIndex').value = cabin;
    let form = document.getElementById("booking");
    form.submit();
}