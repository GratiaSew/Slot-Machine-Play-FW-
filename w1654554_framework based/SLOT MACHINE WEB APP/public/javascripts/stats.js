/*
console.log("hi");
function say(){
  alert("fiiiiiiii");
}
const submit = document.querySelector("#submit");
submit.addEventListener("click", say);*/
// Load google charts
var google;
google.charts.load('current', { 'packages': ['corechart'] });
google.charts.setOnLoadCallback(drawChart);
// Draw the chart and set the chart values
function drawChart() {
    var data = google.visualization.arrayToDataTable([
        ['Task', 'Hours per Day'],
        ['Wins', 3],
        ['Loses', 2],
        ['winStatus', 4],
        ['loseStatus', 2],
    ]);
    // Optional; add a title and set the width and height of the chart
    var options = { 'title': 'My Average Day', 'width': 550, 'height': 400 };
    // Display the chart inside the <div> element with id="piechart"
    var chart = new google.visualization.PieChart(document.getElementById('piechart'));
    chart.draw(data, options);
}
