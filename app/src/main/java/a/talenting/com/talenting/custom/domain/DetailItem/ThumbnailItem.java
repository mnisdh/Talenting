package a.talenting.com.talenting.custom.domain.detailItem;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class ThumbnailItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.THUMBNAIL;
    private View.OnClickListener onClickListener;

    private String content = "";
    private int topPadding = 50;
    private int leftPadding = 50;
    private int rightPadding = 50;
    private int bottomPadding = 50;
    private int contentColor = Color.BLACK;
    private float contentSize = 50;
    private String imageUrl = "";

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTopPadding() {
        return topPadding;
    }

    public void setTopPadding(int topPadding) {
        this.topPadding = topPadding;
    }

    public int getLeftPadding() {
        return leftPadding;
    }

    public void setLeftPadding(int leftPadding) {
        this.leftPadding = leftPadding;
    }

    public int getRightPadding() {
        return rightPadding;
    }

    public void setRightPadding(int rightPadding) {
        this.rightPadding = rightPadding;
    }

    public int getBottomPadding() {
        return bottomPadding;
    }

    public void setBottomPadding(int bottomPadding) {
        this.bottomPadding = bottomPadding;
    }

    public int getContentColor() {
        return contentColor;
    }

    public void setContentColor(int contentColor) {
        this.contentColor = contentColor;
    }

    public float getContentSize() {
        return contentSize;
    }

    public void setContentSize(float contentSize) {
        this.contentSize = contentSize;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public DetailItemType getDetailItemType() {
        return null;
    }

    @Override
    public View getLayoutView(LayoutInflater layoutInflater, ViewGroup parent) {
        return null;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}