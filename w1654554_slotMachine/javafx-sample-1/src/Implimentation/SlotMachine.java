package Implimentation;

import Implimentation.Statistics;
import Implimentation.controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by Gratia Gamage on 11/5/2017.
 */
public class SlotMachine extends Application {

    static boolean isPressed = false;

    static Label img_Label_reel1 = new Label("");
    static Label img_Label_reel2 = new Label("");
    static Label img_Label_reel3 = new Label("");

    //creating the 3 image views
    static ImageView reel1imgV = new ImageView();
    static ImageView reel2imgV = new ImageView();
    static ImageView reel3imgV = new ImageView();

    static Label lInformationAboutWinningAndLosing = new Label("");
    static Label lCreditRemainingData = new Label(String.valueOf(controller.creditRemaining));

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Slot Machine");

        //creating buttons and setting names to them
        Button bSpin = new Button("SPIN");
        Button bBetMin = new Button("BET ONE (1$)");
        Button bBetMax = new Button("BET MAX (3$)");
        Button bReset = new Button("RESET");
        Button bStatistics = new Button("Statistics");
        Button bAddCredit = new Button("Add Credit (1$)");

        //creating labels and setting names for thme
        Label lBetAmount = new Label("Bet Amount : ");
        lBetAmount.setMinWidth(125.0);
        Label lCreditRemaining = new Label("Credits Remaining : ");
        lCreditRemaining.setMinWidth(200.0);
        Label lBetAmountData = new Label(String.valueOf(controller.betAmount));


        //Three reels
        img_Label_reel1.setId("reelLabels");
        img_Label_reel1.setPadding(new Insets(10));

        img_Label_reel2.setId("reelLabels");
        img_Label_reel2.setPadding(new Insets(10));

        img_Label_reel3.setId("reelLabels");
        img_Label_reel3.setPadding(new Insets(10));

        //setting the image reels in the grid pane
        GridPane.setConstraints(img_Label_reel1, 1, 2, 2, 3);
        GridPane.setConstraints(img_Label_reel2, 4, 2, 2, 3);
        GridPane.setConstraints(img_Label_reel3, 7, 2, 2, 3);

        //setting action to view the statistics report
        bStatistics.setOnAction(e -> Statistics.display("Statistics"));

        //creating the grid pane
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        //setting the labels to the respective areas
        GridPane.setConstraints(lBetAmount, 5, 7, 1, 1);
        GridPane.setConstraints(lBetAmountData, 8, 7, 1, 1);
        GridPane.setConstraints(lCreditRemaining, 5, 8, 1, 1);
        GridPane.setConstraints(lCreditRemainingData, 8, 8, 1, 1);
        GridPane.setConstraints(lInformationAboutWinningAndLosing, 5, 5, 1, 1);

        //setting the buttons to the respective areas
        GridPane.setConstraints(bSpin, 4, 5, 2, 3);
        GridPane.setConstraints(bBetMin, 3, 6, 2, 3);
        GridPane.setConstraints(bBetMax, 3, 7, 2, 3);
        GridPane.setConstraints(bReset, 3, 9, 2, 3);
        GridPane.setConstraints(bStatistics, 5, 9, 2, 3);
        GridPane.setConstraints(bAddCredit, 4, 8, 2, 3);

        //default image for reel 1
        reel1imgV.setImage(new Image("Images/bell.png"));
        reel1imgV.setId("imageViews");
        reel1imgV.setFitWidth(140);
        reel1imgV.setFitHeight(140);
        reel1imgV.setPreserveRatio(true);
        img_Label_reel1.setGraphic(reel1imgV);

        //default image for reel 2
        reel2imgV.setImage(new Image("Images/cherry.png"));
        reel2imgV.setId("imageViews");
        reel2imgV.setFitWidth(140);
        reel2imgV.setFitHeight(140);
        reel2imgV.setPreserveRatio(true);
        img_Label_reel2.setGraphic(reel2imgV);

        //default image for reel 3
        reel3imgV.setImage(new Image("Images/lemon.png"));
        reel3imgV.setId("imageViews");
        reel3imgV.setFitWidth(140);
        reel3imgV.setFitHeight(140);
        reel3imgV.setPreserveRatio(true);
        img_Label_reel3.setGraphic(reel3imgV);

        //to set the alignments made the grid lines visible
        //gridPane.setGridLinesVisible(true);

        for (int i = 0; i < 11; i++) {
            ColumnConstraints gridpaneCol = new ColumnConstraints();
            gridpaneCol.setHgrow(Priority.SOMETIMES);
            gridpaneCol.setMinWidth(10.00);
            gridpaneCol.setPrefWidth(20.00);
            gridPane.getColumnConstraints().add(gridpaneCol);
        }

        for (int i = 0; i < 12; i++) {
            RowConstraints gridpaneCol = new RowConstraints();
            gridpaneCol.setVgrow(Priority.SOMETIMES);
            gridpaneCol.setMinHeight(10.00);
            gridpaneCol.setPrefHeight(20.00);
            gridPane.getRowConstraints().add(gridpaneCol);
        }

        //set on action  to spin button
        bSpin.setOnAction(e -> {
            if (controller.betAmount > 0) {
                isPressed = true;
                controller controller = new controller();
                controller.runReels();
                img_Label_reel1.setDisable(false);
                img_Label_reel2.setDisable(false);
                img_Label_reel3.setDisable(false);
                //setting the bet amount to 0 after the spin
                if (isPressed) {
                    hidebuttons(bSpin, bBetMin, bBetMax, bReset, bStatistics, bAddCredit);
                }


            } else {
                isPressed = false;
                //setting the warning to bet before spin
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert!!");
                alert.setContentText("You have to bet before spin!");
                alert.showAndWait();

            }
        });

        //set on action to label
        img_Label_reel1.setOnMouseClicked(event -> {
            isPressed = false;
            controller.process();
//            Implimentation.controller.betAmount = 0;
            lBetAmountData.setText(String.valueOf(controller.betAmount));
            img_Label_reel1.setDisable(true);
            img_Label_reel2.setDisable(true);
            img_Label_reel3.setDisable(true);
            //calling the method off_hidebuttons
            off_hidebuttons(bSpin,bBetMin,bBetMax,bReset,bStatistics,bAddCredit);
        });

        img_Label_reel2.setOnMouseClicked(event -> {
            isPressed = false;
            controller.process();
//            Implimentation.controller.betAmount = 0;
            lBetAmountData.setText(String.valueOf(controller.betAmount));
            img_Label_reel1.setDisable(true);
            img_Label_reel2.setDisable(true);
            img_Label_reel3.setDisable(true);
            off_hidebuttons(bSpin,bBetMin,bBetMax,bReset,bStatistics,bAddCredit);
            System.out.println();
        });

        img_Label_reel3.setOnMouseClicked(event -> {
            isPressed = false;
            controller.process();
            System.out.println();
            lBetAmountData.setText(String.valueOf(controller.betAmount));
            img_Label_reel1.setDisable(true);
            img_Label_reel2.setDisable(true);
            img_Label_reel3.setDisable(true);
            off_hidebuttons(bSpin,bBetMin,bBetMax,bReset,bStatistics,bAddCredit);
        });

        //set BetMin button on action
        //have to call inside the actions in Implementation.controller cz while the credits are added we need to increment
        bAddCredit.setOnAction(event -> {
            controller.creditRemaining++;
            lCreditRemainingData.setText(String.valueOf(controller.creditRemaining));
        });

        //maximizing 1 credits in credit remaining
        bBetMin.setOnAction(event -> {
            if (controller.creditRemaining > 1) {
                controller.betAmount++;
                controller.creditRemaining--;
                lBetAmountData.setText(String.valueOf(controller.betAmount));
                lCreditRemainingData.setText(String.valueOf(controller.creditRemaining));

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert!!");
                alert.setContentText("You have to keep at least 1$ remaining to bet min!");
                alert.showAndWait();
            }
        });

        //maximizing 3 credits in credit remaining
        bBetMax.setOnAction(event -> {
            if (controller.betAmount <= 0) {
                if (controller.creditRemaining > 3) {
                    controller.betAmount = controller.betAmount + 3;
                    controller.creditRemaining = controller.creditRemaining - 3;
                    lBetAmountData.setText(String.valueOf(controller.betAmount));
                    lCreditRemainingData.setText(String.valueOf(controller.creditRemaining));
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alert!!");
                    alert.setContentText("You have to keep at least 3$ remaining to bet max!");
                    alert.showAndWait();
                }
            }
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alert!!");
                    alert.setContentText("You have already bet the maximum amount!");
                    alert.showAndWait();
                }

            });

            //set action to the reset button
            bReset.setOnAction(event -> {
                controller.creditRemaining = controller.creditRemaining + controller.betAmount;
                lCreditRemainingData.setText(String.valueOf(controller.creditRemaining));
                controller.betAmount = 0;
                lBetAmountData.setText(String.valueOf(controller.betAmount));
            });


            gridPane.getChildren().addAll(lBetAmount, lBetAmountData, lCreditRemaining, lCreditRemainingData,
                    bBetMin, bBetMax, bReset, bStatistics, img_Label_reel1, img_Label_reel2, img_Label_reel3, bSpin, lInformationAboutWinningAndLosing, bAddCredit);
            Scene scene = new Scene(gridPane, 700, 700);
            scene.getStylesheets().add("css/layout.css");

            //gridPane.setHgap(15);
            //gridPane.setVgap(15);
            primaryStage.setScene(scene);
            primaryStage.show();

        }
        //hide buttons when the reels are spinning
        public void hidebuttons(Button bSpin,Button bBetMin,Button bBetMax , Button bReset,Button bStatistics,Button bAddCredit  ){
                bSpin.setDisable(true);
                bBetMin.setDisable(true);
                bBetMax.setDisable(true);
                bReset.setDisable(true);
                bStatistics.setDisable(true);
                bAddCredit.setDisable(true);
        }

    //un-hide buttons when the reels are stopped spinning
    public void off_hidebuttons(Button bSpin,Button bBetMin,Button bBetMax , Button bReset,Button bStatistics,Button bAddCredit  ){
        bSpin.setDisable(false);
        bBetMin.setDisable(false);
        bBetMax.setDisable(false);
        bReset.setDisable(false);
        bStatistics.setDisable(false);
        bAddCredit.setDisable(false);
    }
    }
