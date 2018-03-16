package Implimentation;

import Implimentation.Symbol;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Gratia Gamage on 11/7/2017.
 */
public class Reel {
    //creating the symbol_array ArrayList
 public ArrayList<Symbol> symbol_array = new ArrayList <>();

 //passing the values to the constructor
  static Symbol seven = new Symbol(new Image("Images/redseven.png"),7);
   static Symbol bell = new Symbol(new Image("Images/bell.png"),6);
   static Symbol watermelon = new Symbol(new Image("Images/watermelon.png"),5);
   static Symbol plum = new Symbol(new Image("Images/plum.png"),4);
   static Symbol lemon = new Symbol(new Image("Images/lemon.png"),3);
    static Symbol cherry = new Symbol(new Image("Images/cherry.png"),2);

    //adding objects to the symbol array
    public Reel() {
        symbol_array.add(seven);
        symbol_array.add(bell);
        symbol_array.add(watermelon);
        symbol_array.add(plum);
        symbol_array.add(lemon);
        symbol_array.add(cherry);
    }

    public  ArrayList<Symbol> spin() {
        //to shuffle the elements of a collection with the help of Collections.shuffle() method of Collections class
        Collections.shuffle(symbol_array);
        //returning the ArrayList
        return symbol_array;
    }

}
