package Implimentation;

import javafx.scene.image.Image;

/**
 * Created by Gratia Gamage on 11/7/2017.
 */
public class Symbol implements ISymbol {
    private Image image;
    private int value;

    public Symbol(Image image,int value){
        this.image=image;
        this.value=value;
    }

    @Override
    public void setImage(Image image) {

    }

    @Override
    public void setValue(int v) {

    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public int getValue() {
        return value;
    }
}
