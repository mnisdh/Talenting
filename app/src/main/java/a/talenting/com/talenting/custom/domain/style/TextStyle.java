package a.talenting.com.talenting.custom.domain.style;

import android.graphics.Color;

/**
 * Created by daeho on 2017. 12. 1..
 */

public class TextStyle {
    private float size = 50;
    private int color = Color.BLACK;
    private PaddingStyle padding = new PaddingStyle();

    public TextStyle(){

    }
    public TextStyle(int color){
        this.color = color;
    }
    public TextStyle(float size, int color){
        this.size = size;
        this.color = color;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public PaddingStyle getPadding() {
        return padding;
    }

    public void setPadding(PaddingStyle padding) {
        this.padding = padding;
    }
}
