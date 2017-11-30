package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.databinding.CustomDetailItemTextAndValueBinding;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class TitleAndValueItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.TITLE_AND_VALUE;
    private View.OnClickListener onClickListener;

    private String title = "";
    private String value = "";
    private int topPadding = 50;
    private int leftPadding = 50;
    private int rightPadding = 50;
    private int bottomPadding = 50;
    private int titleColor = Color.BLACK;
    private int valueColor = Color.DKGRAY;
    private float titleSize = 50;
    private float valueSize = 50;
    private boolean useBottomLine = false;

    public boolean isUseBottomLine() {
        return useBottomLine;
    }

    public void setUseBottomLine(boolean useBottomLine) {
        this.useBottomLine = useBottomLine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public int getValueColor() {
        return valueColor;
    }

    public void setValueColor(int valueColor) {
        this.valueColor = valueColor;
    }

    public float getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(float titleSize) {
        this.titleSize = titleSize;
    }

    public float getValueSize() {
        return valueSize;
    }

    public void setValueSize(float valueSize) {
        this.valueSize = valueSize;
    }

    @Override
    public DetailItemType getDetailItemType() {
        return null;
    }


    @Override
    public View getLayoutView(LayoutInflater layoutInflater, ViewGroup parent) {
        CustomDetailItemTextAndValueBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_text_and_value, parent, false);
        binding.setItem(this);

        return binding.getRoot();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}