package Implimentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Gratia Gamage on 11/6/2017.
 */
public class Statistics {

    static Label lAllTimeWinsData = new Label("");
    static Label lAllTimeLossesData = new Label("");
    static Label lWinningAverageData = new Label("");
    static Label lLosingAverageData = new Label("");

    public static void display(String title) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Statistics");
        stage.setMinWidth(500);
        stage.setMinHeight(500);

        Button bSave = new Button("Save");

        Label lDetail = new Label("STATISTICS");
        lDetail.setPadding(new Insets(10));

        lAllTimeWinsData.setMinWidth(150);
        lAllTimeLossesData.setMinWidth(150);
        lWinningAverageData.setMinWidth(150);
        lLosingAverageData.setMinWidth(150);
        // lAverageCreditEarnedData.setMinWidth(150);

        Label lAllTimeWins = new Label("All time wins : ");
        lAllTimeWins.setMinWidth(150);
        lAllTimeWins.setPadding(new Insets(10));


        Label lAllTimeLosses = new Label("All time losses : ");
        lAllTimeLosses.setMinWidth(150);
        lAllTimeLosses.setPadding(new Insets(10));


        Label lWinningAverage = new Label("Winning Credits Average : ");
        lWinningAverage.setMinWidth(200);
        lWinningAverage.setPadding(new Insets(10));

        Label lLosingAverage = new Label("Losing Credits Average : ");
        lLosingAverage.setMinWidth(200);
        lLosingAverage.setPadding(new Insets(10));

        Label lAverageCreditEarned = new Label("Average credit earned : ");
        //lAverageCreditEarnedData.setMinWidth(200);
        lAverageCreditEarned.setPadding(new Insets(10));


        //creating the grid pane
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        //Preparing ObservableList object
        ObservableList <PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Won", controller.numOfWins),
                new PieChart.Data("Lost", controller.numOfLoses)
        );
        //Creating a Pie chart
        final PieChart pieChart = new PieChart(pieChartData);
        pieChart.setMinSize(300, 300);

        //Setting the title of the Pie chart
        pieChart.setTitle("Pie Chart");
        //setting the direction to arrange the data
        pieChart.setClockwise(true);
        //Setting the length of the label line
        pieChart.setLabelLineLength(30);
        //Setting the labels of the pie chart visible
        pieChart.setLabelsVisible(true);
        //Setting the start angle of the pie chart
        pieChart.setStartAngle(180);
        //Creating a Group object
        Group root = new Group(pieChart);
        //setting the pie chart in the grid pane
        //  GridPane.setConstraints(root,3,2);
        GridPane.setConstraints(pieChart, 3, 2);

        GridPane.setConstraints(lDetail, 1, 0);
        GridPane.setConstraints(lAllTimeWins, 1, 1);
        GridPane.setConstraints(lAllTimeWinsData, 2, 1);
        GridPane.setConstraints(lAllTimeLosses, 1, 2);
        GridPane.setConstraints(lAllTimeLossesData, 2, 2);
        GridPane.setConstraints(lWinningAverage, 1, 3);
        GridPane.setConstraints(lWinningAverageData, 2, 3);
        GridPane.setConstraints(lLosingAverage, 1, 4);
        GridPane.setConstraints(lLosingAverageData, 2, 4);
        GridPane.setConstraints(lAverageCreditEarned, 1, 5);
        //  GridPane.setConstraints(lAverageCreditEarnedData,2,5);

        GridPane.setConstraints(bSave, 1, 6);

        //gridPane.setGridLinesVisible(true);

        //setting the number of the columns in the grid pane
        for (int i = 0; i < 5; i++) {
            ColumnConstraints gridpaneCol = new ColumnConstraints();
            gridpaneCol.setHgrow(Priority.SOMETIMES);
            gridpaneCol.setMinWidth(10.00);
            gridpaneCol.setPrefWidth(20.00);
            gridPane.getColumnConstraints().add(gridpaneCol);
        }

        //setting the number of the rows in the grid pane
        for (int i = 0; i < 7; i++) {
            RowConstraints gridpaneCol = new RowConstraints();
            gridpaneCol.setVgrow(Priority.SOMETIMES);
            gridpaneCol.setMinHeight(10.00);
            gridpaneCol.setPrefHeight(20.00);
            gridPane.getRowConstraints().add(gridpaneCol);
        }

        bSave.setOnAction(event -> {
            //Taking the date
            String getdatetime = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(Calendar.getInstance().getTime());
            String datetime = getdatetime + ".text";
            System.out.println(datetime);


            //setting the action on the save button
            try {
                FileWriter fw = new FileWriter(datetime); //create a file with current date and time

                fw.write("Number of Wins :" + controller.numOfWins + "\n");
                fw.write("");
                fw.write("Number of Loses :" + controller.numOfLoses + "\n");
                fw.write("");
                fw.write("Average Won Credits : " + controller.avg_totEarned + "\n");
                fw.write("");
                fw.write("Average Lose Credits : " + controller.avg_totlost + "\n");
                fw.write("");
                fw.write("Date and Time :" + getdatetime + "\n");
                fw.write("");
                fw.close();
            } catch (IOException i) {
                System.out.println("Invalid Marks");
            }
        });


        gridPane.getChildren().addAll(pieChart, lDetail, lAllTimeWins, lAllTimeWinsData, lAllTimeLosses,
                lAllTimeLossesData, lWinningAverage, lWinningAverageData, lLosingAverage, lLosingAverageData, bSave);
        Scene scene = new Scene(gridPane, 800, 500);//hieght and width of grid pane
        scene.getStylesheets().add("css/secondLayout.css");//setting the styles to the grid pane
        stage.setScene(scene);
        stage.showAndWait();//can't deal with another window until this window is closed
    }
}
