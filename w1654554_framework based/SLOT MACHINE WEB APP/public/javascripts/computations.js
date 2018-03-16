var Symbol = /** @class */ (function () {
    function Symbol(url, value) {
        this.setImageURL(url); //ulr of the image
        this.setSymbolValue(value); //value of the image
    }
    Symbol.prototype.setImageURL = function (imageURL) {
        this.imageURL = imageURL;
    };
    Symbol.prototype.getImageURL = function () {
        return this.imageURL;
    };
    Symbol.prototype.setSymbolValue = function (symbolValue) {
        this.symbolValue = symbolValue;
    };
    Symbol.prototype.getSymbolValue = function () {
        return this.symbolValue;
    };
    return Symbol;
}());
var Reel = /** @class */ (function () {
    function Reel() {
    }
    Reel.getSymbolArray = function () {
        Reel.symbolArray.push(new Symbol("assets/images/cherry.png", 2)); //cherry
        Reel.symbolArray.push(new Symbol("assets/images/lemon.png", 3)); //lemon
        Reel.symbolArray.push(new Symbol("assets/images/plum.png", 4)); //plum
        Reel.symbolArray.push(new Symbol("assets/images/watermelon.png", 5)); //watermelon
        Reel.symbolArray.push(new Symbol("assets/images/bell.png", 6)); //bell
        Reel.symbolArray.push(new Symbol("assets/images/redseven.png", 7)); //redseven
        return Reel.symbolArray;
    };
    Reel.symbolArray = [];
    return Reel;
}());
var computations = /** @class */ (function () {
    function computations() {
    }
    //maximizing 1 credits in credit remaining
    computations.incrementBetOne = function () {
        if (computations.creditsRemaining >= 1) {
            console.log("in increment");
            computations.betAmount++;
            computations.creditsRemaining--;
            document.getElementById("betAmount").setAttribute("value", computations.betAmount.toString());
            document.getElementById("creditsRemaining").setAttribute("value", computations.creditsRemaining.toString());
        }
        else {
            alert("You don’t have credit in the machine");
        }
    };
    //maximizing 3 credits in credit remaining
    computations.incrementBetMax = function () {
        if (computations.betAmount <= 0) {
            if (computations.creditsRemaining > 3) {
                computations.betAmount = computations.betAmount + 3;
                computations.creditsRemaining = computations.creditsRemaining - 3;
                document.getElementById("betAmount").setAttribute("value", computations.betAmount.toString());
                document.getElementById("creditsRemaining").setAttribute("value", computations.creditsRemaining.toString());
            }
            else {
                alert("You have to keep at least 3$ remaining to bet max!");
            }
        }
        else {
            alert("you can type Bet Max only once per game");
        }
    };
    //set the bet amount to zero
    computations.setReset = function () {
        computations.creditsRemaining = computations.creditsRemaining + computations.betAmount;
        document.getElementById("creditsRemaining").setAttribute("value", computations.creditsRemaining.toString());
        computations.betAmount = 0;
        document.getElementById("betAmount").setAttribute("value", computations.betAmount.toString());
    };
    //adding credits by 1$ to the remaining credits
    computations.addCredits = function () {
        computations.creditsRemaining++;
        document.getElementById("creditsRemaining").setAttribute("value", computations.creditsRemaining.toString());
    };
    //method to spin the reels
    computations.spin = function () {
        var self = this; //this is to avoid final
        computations.reelIntervalState = intervalSetter(); //this is to get the state so the the reels can be stopped
        function intervalSetter() {
            document.getElementById("status").setAttribute("value", "Spinning...");
            if (computations.betAmount > 0) {
                //disabling the buttons when the reels are spinning
                document.getElementById("spin").disabled = true;
                document.getElementById("betMax").disabled = true;
                document.getElementById("betOne").disabled = true;
                document.getElementById("addCredit").disabled = true;
                document.getElementById("reset").disabled = true;
                return setInterval(function () {
                    //generating a random number
                    var randomNumber1 = Math.floor(Math.random() * 6);
                    var randomNumber2 = Math.floor(Math.random() * 6);
                    var randomNumber3 = Math.floor(Math.random() * 6);
                    //setting the html obj with a random img src
                    var imageURL1 = Reel.symbolArray[randomNumber1].getImageURL();
                    var imageURL2 = Reel.symbolArray[randomNumber2].getImageURL();
                    var imageURL3 = Reel.symbolArray[randomNumber3].getImageURL();
                    document.getElementById("reel1").setAttribute("src", imageURL1.toString());
                    document.getElementById("reel2").setAttribute("src", imageURL2.toString());
                    document.getElementById("reel3").setAttribute("src", imageURL3.toString());
                    //getting the value of the finally left out symbols;
                    computations.finalValue1 = Reel.symbolArray[randomNumber1].getSymbolValue();
                    computations.finalValue2 = Reel.symbolArray[randomNumber2].getSymbolValue();
                    computations.finalValue3 = Reel.symbolArray[randomNumber3].getSymbolValue();
                    computations.finalImage1 = imageURL1;
                    computations.finalImage2 = imageURL2;
                    computations.finalImage3 = imageURL3;
                    console.log(imageURL1);
                    console.log(computations.finalValue1);
                    console.log(computations.finalValue2);
                    console.log(computations.finalValue3);
                }, 80); //delay for 80 milli seconds
            }
            else {
                alert("Bet before Spin!");
            }
        }
    };
    //method to stop the reels spinning
    computations.stopAReel = function () {
        document.getElementById("spin").disabled = false;
        document.getElementById("betMax").disabled = false;
        document.getElementById("betOne").disabled = false;
        document.getElementById("addCredit").disabled = false;
        document.getElementById("reset").disabled = false;
        //This method will make the current reel stop spinning
        clearInterval(computations.reelIntervalState);
    };
    //method to check the images of the reels and for the computations
    computations.checkMatched = function () {
        var image1 = computations.finalImage1;
        var image2 = computations.finalImage2;
        var image3 = computations.finalImage3;
        var imageValue1 = computations.finalValue1;
        var imageValue2 = computations.finalValue2;
        var imageValue3 = computations.finalValue3;
        var payout;
        //when the all 3 reel images are same
        if ((image1 == image2) && (image1 == image3)) {
            payout = (imageValue1 * (3 / 3)) * 10; //calculating payout as a percentage
            document.getElementById("payoutValue").setAttribute("value", payout + " %"); //setting the payout value to the label
            document.getElementById("status").setAttribute("value", "You Win!!!!");
            //calculating the value considering the value of the image
            var earnedCredits = imageValue1 * computations.betAmount;
            alert("you've won " + earnedCredits.toString() + " credits");
            //static variable gets updated according to the won credit
            computations.totalEarnedCredits = computations.totalEarnedCredits + earnedCredits;
            //adding the won credits to the credit area of remaining
            computations.creditsRemaining += earnedCredits;
            //incrementing the winnings as all the reels are matched
            computations.numOfWins++;
            //showing the times of winning as an average
            var average = computations.totalEarnedCredits / (computations.numOfWins + computations.numOfLoses);
            //updating a static variable continuously
            computations.avg_totEarned = average;
            //setting the bet amount to 0 and store it in the slot machine
            computations.betAmount = 0;
        }
        else if ((image1 == image2) && (image1 != image3)) {
            payout = (imageValue1 * (2 / 3) + imageValue3 * (1 / 3)) * 10; //calculating payout as a percentage
            document.getElementById("payoutValue").setAttribute("value", payout + " %"); //setting the payout value to the label
            document.getElementById("status").setAttribute("value", "You Win!!!!");
            //calculating the value considering the value of the image
            var earnedCredits = imageValue1 * computations.betAmount;
            alert("you've won " + earnedCredits.toString() + " credits");
            //static variable gets updated according to the won credit
            computations.totalEarnedCredits = computations.totalEarnedCredits + earnedCredits;
            //adding the won credits to the credit area of remaining
            computations.creditsRemaining += earnedCredits;
            //incrementing the winnings as all the reels are matched
            computations.numOfWins++;
            //showing the times of winning as an average
            var average = computations.totalEarnedCredits / (computations.numOfWins + computations.numOfLoses);
            //updating a static variable continuously
            computations.avg_totEarned = average;
            //setting the bet amount to 0 and store it in the slot machine
            computations.betAmount = 0;
        }
        else if ((image1 == image3) && (image1 != image2)) {
            payout = (imageValue1 * (2 / 3) + imageValue2 * (1 / 3)) * 10; //calculating payout as a percentage
            document.getElementById("payoutValue").setAttribute("value", payout + " %"); //setting the payout value to the label
            document.getElementById("status").setAttribute("value", "You Win!!!!");
            //calculating the value considering the value of the image
            var earnedCredits = imageValue1 * computations.betAmount;
            alert("you've won " + earnedCredits.toString() + " credits");
            //static variable gets updated according to the won credit
            computations.totalEarnedCredits = computations.totalEarnedCredits + earnedCredits;
            //adding the won credits to the credit area of remaining
            computations.creditsRemaining += earnedCredits;
            //incrementing the winnings as all the reels are matched
            computations.numOfWins++;
            //showing the times of winning as an average
            var average = computations.totalEarnedCredits / (computations.numOfWins + computations.numOfLoses);
            //updating a static variable continuously
            computations.avg_totEarned = average;
            //setting the bet amount to 0 and store it in the slot machine
            computations.betAmount = 0;
        }
        else if ((image2 == image3) && (image1 != image2) && (image1 != image3)) {
            payout = (imageValue2 * (2 / 3) + imageValue1 * (1 / 3)) * 10; //calculating payout as a percentage
            document.getElementById("payoutValue").setAttribute("value", payout + " %"); //setting the payout value to the label
            document.getElementById("status").setAttribute("value", "You Win!!!!");
            //calculating the value considering the value of the image
            var earnedCredits = imageValue2 * computations.betAmount;
            alert("you've won " + earnedCredits.toString() + " credits");
            //static variable gets updated according to the won credit
            computations.totalEarnedCredits = computations.totalEarnedCredits + earnedCredits;
            //adding the won credits to the credit area of remaining
            computations.creditsRemaining += earnedCredits;
            //incrementing the winnings as all the reels are matched
            computations.numOfWins++;
            //showing the times of winning as an average
            var average = computations.totalEarnedCredits / (computations.numOfWins + computations.numOfLoses);
            //updating a static variable continuously
            computations.avg_totEarned = average;
            //setting the bet amount to 0 and store it in the slot machine
            computations.betAmount = 0;
        }
        else {
            //when none of the images in reel are same
            payout = (imageValue1 * (1 / 3) + imageValue2 * (1 / 3) + imageValue3 * (1 / 3)) * 10; //calculating payout as a percentage
            document.getElementById("payoutValue").setAttribute("value", payout + " %"); //setting the payout value to the label
            document.getElementById("status").setAttribute("value", "You Lose!!!!");
            computations.totalLostCredits = computations.totalLostCredits + computations.betAmount;
            alert("you've lost " + computations.betAmount.toString() + " credits");
            computations.numOfLoses++;
            var average = computations.totalLostCredits / (computations.numOfWins + computations.numOfLoses);
            computations.avg_totlost = average;
            computations.betAmount = 0;
            document.getElementById("status").setAttribute("value", "      ");
        }
    };
    computations.creditsRemaining = 15; //default value when starting the game
    computations.betAmount = 0; //this is getting multiplied while the game is going on according to the matched
    return computations;
}());
//event listeners for button "bet one ($1)"
var betOne = document.querySelector("#betOne");
betOne.addEventListener("click", computations.incrementBetOne, false);
//betOne.addEventListener("click",computations.checkMatched,false);
//event listeners for button "bet max ($3)"
var betMax = document.querySelector("#betMax");
betMax.addEventListener("click", computations.incrementBetMax, false);
//betMax.addEventListener("click",computations.checkMatched,false);
//event listeners for button "reset"
var reset = document.querySelector("#reset");
reset.addEventListener("click", computations.setReset, false);
//reset.addEventListener("click",computations.checkMatched,false);
//event listeners for button "add credits(1$)"
var addCredit = document.querySelector("#addCredit");
addCredit.addEventListener("click", computations.addCredits, false);
//addCredit.addEventListener("click",computations.checkMatched,false);
//to spin the image reels
var spin = document.querySelector("#spin");
spin.addEventListener("click", Reel.getSymbolArray, false);
spin.addEventListener("click", computations.spin, false);
//to open statistics window
var statistics = document.querySelector("#statistics");
statistics.addEventListener("click", function () {
    window.open("stats.scala.html", "_blank", "resizable=yes", true);
}, false);
//to stop the reels spinning
var stopSpin1 = document.querySelector("#reel1");
var stopSpin2 = document.querySelector("#reel2");
var stopSpin3 = document.querySelector("#reel3");
stopSpin1.addEventListener("click", computations.stopAReel, false);
stopSpin2.addEventListener("click", computations.stopAReel, false);
stopSpin3.addEventListener("click", computations.stopAReel, false);
//to set all the values and computations done above when the reels are done spinning
stopSpin1.addEventListener("click", computations.checkMatched, false);
stopSpin2.addEventListener("click", computations.checkMatched, false);
stopSpin3.addEventListener("click", computations.checkMatched, false);
