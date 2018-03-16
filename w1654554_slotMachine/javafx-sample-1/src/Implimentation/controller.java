package Implimentation;

import Implimentation.Statistics;
import Implimentation.Symbol;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gratia Gamage on 11/14/2017.
 */
public class controller {

    static int creditRemaining = 10;//default value when starting the game
    static int betAmount;//this is getting multiplied while the game is going on according to the matched
                         // image values and after the spinning this amount is again set to "0"
    static int totalEarnedCredits;//this value is getting incremented when all the reels or two of them are matched with each
    static int totalLostCredits;//this value is getting incremented when any of the reels don't match with each
    static int numOfWins;//this value is getting incremented when all the reels or two of them are matched with each
    static int numOfLoses;//this value is getting incremented when any of the reels don't match with each
    static double avg_totEarned;//considered when all the reels or two of them are matched with each
    static double avg_totlost;//considered when any of the reels don't match with each

    public Thread thread01 = new Thread();
    public Thread thread02 = new Thread();
    public Thread thread03 = new Thread();


    public void runReels() {
        spinReels(thread01, SlotMachine.img_Label_reel1, SlotMachine.reel1imgV);
        spinReels(thread02, SlotMachine.img_Label_reel2, SlotMachine.reel2imgV);
        spinReels(thread03, SlotMachine.img_Label_reel3, SlotMachine.reel3imgV);
    }

    //to spin the reels and passing tha parameters
    public static void spinReels(Thread thread, Label label, ImageView imageView) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (SlotMachine.isPressed) {
                    Reel reel = new Reel();
                    reel.spin();
                    int index = new Random().nextInt(6);
                    ArrayList <Symbol> temp_arr = reel.symbol_array;

                    imageView.setFitHeight(140);
                    imageView.setFitWidth(140);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImage(temp_arr.get(index).getImage());
                            label.setGraphic(imageView);
                        }
                    });
                    //Thread.sleep(millis) does not guarantee that it will sleep for the provided duration.
                    // No assumption can be made that if you specify it to sleep for 1 seconds, its gonna sleep for 2 seconds.
                    // If there are any interrupts, then sleep can be interrupted, hence it throws an InterruptedException.
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }


    public static void process() {
        Image image1 = SlotMachine.reel1imgV.getImage();
        Image image2 = SlotMachine.reel2imgV.getImage();
        Image image3 = SlotMachine.reel3imgV.getImage();

        if ((image1 == image2) && (image1 == image3)) {
            SlotMachine.lInformationAboutWinningAndLosing.setText("you won");
            SlotMachine.lInformationAboutWinningAndLosing.setMinWidth(125.0);
            Reel reel = new Reel();
            //enhanced for loop to check whether the all 3 images match in 3 reels
            for (Symbol k : reel.symbol_array) {
                if (k.getImage().equals(image1)) {
                    //calculating the value considering the value of the image
                    int earnedCredits = (k.getValue() * betAmount);
                    //static variable gets updated according to the won credit
                    totalEarnedCredits = totalEarnedCredits + earnedCredits;
                    //adding the won credits to the credit area of remaining
                    creditRemaining += earnedCredits;
                    //displaying the remaining credit value in the label
                    SlotMachine.lCreditRemainingData.setText(String.valueOf(creditRemaining));
                    //incrementing the winnings as all the reels are matched
                    numOfWins++;
                    //displaying number of wins in the label
                    Statistics.lAllTimeWinsData.setText(String.valueOf(numOfWins));
                    //showing the times of winning as an average
                    double average = totalEarnedCredits / (numOfWins + numOfLoses);
                    //updating a static variable continuously
                    avg_totEarned = average;
                    Statistics.lWinningAverageData.setText(String.valueOf(average));
                    //setting the bet amount to 0 and store it in the slot machine
                    betAmount = 0;

                }
            }
            System.out.println("All matches");
        } else if (image1 == image2 && (image1 != image3)) {
            SlotMachine.lInformationAboutWinningAndLosing.setText("Try again");
            SlotMachine.lInformationAboutWinningAndLosing.setMinWidth(125.0);
            Reel reel = new Reel();
            //enhanced for loop to check whether the all 2 images match in 3 reels
            for (Symbol k : reel.symbol_array) {
                if (k.getImage().equals(image1)) {
                    int earnedCredits = (k.getValue() * betAmount);
                    totalEarnedCredits = totalEarnedCredits + earnedCredits;
                    creditRemaining += earnedCredits;
                    SlotMachine.lCreditRemainingData.setText(String.valueOf(creditRemaining));
                    numOfWins++;
                    Statistics.lAllTimeWinsData.setText(String.valueOf(numOfWins));
                    double average = totalEarnedCredits / (numOfWins + numOfLoses);
                    Statistics.lWinningAverageData.setText(String.valueOf(average));
                    avg_totEarned = average;
                    betAmount = 0;


                }
            }
            System.out.println("only 1 and 2");
        } else if (image1 == image3 && (image1 != image2)) {
            SlotMachine.lInformationAboutWinningAndLosing.setText("Try again");
            SlotMachine.lInformationAboutWinningAndLosing.setMinWidth(125.0);
            Reel reel = new Reel();
            //enhanced for loop to check whether the all 2 images match in 3 reels
            for (Symbol k : reel.symbol_array) {
                if (k.getImage().equals(image1)) {
                    int earnedCredits = (k.getValue() * betAmount);
                    totalEarnedCredits = totalEarnedCredits + earnedCredits;
                    creditRemaining += earnedCredits;
                    SlotMachine.lCreditRemainingData.setText(String.valueOf(creditRemaining));
                    numOfWins++;
                    Statistics.lAllTimeWinsData.setText(String.valueOf(numOfWins));
                    double average = totalEarnedCredits / (numOfWins + numOfLoses);
                    Statistics.lWinningAverageData.setText(String.valueOf(average));
                    avg_totEarned = average;
                    betAmount = 0;
                }
            }
            System.out.println("only 1 and 3");
        } else if (image2 == image3) {
            SlotMachine.lInformationAboutWinningAndLosing.setText("Try again");
            SlotMachine.lInformationAboutWinningAndLosing.setMinWidth(125.0);
            Reel reel = new Reel();
            //enhanced for loop to check whether the all 2 images match in 3 reels
            for (Symbol k : reel.symbol_array) {
                if (k.getImage().equals(image2)) {
                    int earnedCredits = (k.getValue() * betAmount);
                    totalEarnedCredits = totalEarnedCredits + earnedCredits;
                    creditRemaining += earnedCredits;
                    SlotMachine.lCreditRemainingData.setText(String.valueOf(creditRemaining));
                    numOfWins++;
                    Statistics.lAllTimeWinsData.setText(String.valueOf(numOfWins));
                    double average = totalEarnedCredits / (numOfWins + numOfLoses);
                    Statistics.lWinningAverageData.setText(String.valueOf(average));
                    avg_totEarned = average;
                    betAmount = 0;
                }
            }
            System.out.println("only 2 and 3");
        } else {
            SlotMachine.lInformationAboutWinningAndLosing.setText("you lose");
            SlotMachine.lInformationAboutWinningAndLosing.setMinWidth(125.0);
            totalLostCredits = totalLostCredits + betAmount;
            numOfLoses++;
            Statistics.lAllTimeLossesData.setText(String.valueOf(numOfLoses));
            double average = totalLostCredits / (numOfWins + numOfLoses);
            avg_totlost = average;
            Statistics.lLosingAverageData.setText(String.valueOf(average));
            betAmount = 0;
        }
    }


}




