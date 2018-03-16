// Initialize Firebase
var config = {
    apiKey: "AIzaSyAWUFBv7ZkhOkJ5g_sVkDuKxE_cKf9ExrQ",
    authDomain: "slot-machine-web-app-4115b.firebaseapp.com",
    databaseURL: "https://slot-machine-web-app-4115b.firebaseio.com",
    projectId: "slot-machine-web-app-4115b",
    storageBucket: "slot-machine-web-app-4115b.appspot.com",
    messagingSenderId: "1081990757590"
};
firebase.initializeApp(config);

function saveData() {

    console.log("sayyyyy")
    var firebaseRef= firebase.database().ref();
    var noofwins=2//numOfWins;
    var noofloses=3//numofLoses;
    //var avgcreditwon=/(noofLoses+noofWins);
    firebaseRef.child("Wins").set(noofwins);
    firebaseRef.child("Loses").set(noofloses);
    //firebaseRef.push().set(avgcreditwon);



}
function showData() {
    alert("wins");

    var firewons=firebase.database().ref().child("Wins");
    var fireloses=firebase.database().ref().child("Loses");

    firewons.on("value",function(datasnapshot1){
        document.getElementById("nwins").textContent = datasnapshot1.val();
        w=datasnapshot1.val();
        console.log("won"+datasnapshot1.val());

    });

    fireloses.on("value",function(datasnapshot2){
        document.getElementById("nloses").textContent = datasnapshot2.val();
        l=datasnapshot2.val();;
        console.log("loses"+datasnapshot2.val());

    });

    //chart
    var google;
    google.charts.load('current', { 'packages': ['corechart'] });
    google.charts.setOnLoadCallback(drawChart);
// Draw the chart and set the chart values
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day'],
            ['No of Wins', firewons.on("value",function(datasnapshot1){
                datasnapshot1.val();

            })],
            ['No of Loses',fireloses.on("value",function(datasnapshot2){
                document.getElementById("nloses").textContent = datasnapshot2.val();
                datasnapshot2.val();

            })]
        ]);
        // Optional; add a title and set the width and height of the chart
        var options = { 'title': 'Player Statistics', 'width': 550, 'height': 400 };
        // Display the chart inside the <div> element with id="piechart"
        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
    }


}
//to add player credit
var showData1 = document.querySelector("#show");
showData1.addEventListener("click", showData, false);

//to add player credit
var savedata = document.querySelector("#save");
savedata.addEventListener("click", saveData, false);