package a.talenting.com.talenting.custom.domain.style;

/**
 * Created by daeho on 2017. 12. 1..
 */

public class PaddingStyle {
    private int top = 0;
    private int left = 0;
    private int right = 0;
    private int bottom = 0;

    public PaddingStyle(){};
    public PaddingStyle(int top, int left, int right, int bottom){
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }

    public int getTop() {
        return top;
    }
    public void setTop(int top) {
        this.top = top;
    }
    public int getLeft() {
        return left;
    }
    public void setLeft(int left) {
        this.left = left;
    }
    public int getRight() {
        return right;
    }
    public void setRight(int right) {
        this.right = right;
    }
    public int getBottom() {
        return bottom;
    }
    public void setBottom(int bottom) {
        this.bottom = bottom;
    }
}
