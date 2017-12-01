package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.databinding.CustomDetailItemTextAndEdittextBinding;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class TitleAndEditTextItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.TITLE_AND_EDITTEXT;
    private View.OnClickListener onClickListener;

    private String title = "";
    private String editText = "";
    private int topPadding = 50;
    private int leftPadding = 50;
    private int rightPadding = 50;
    private int bottomPadding = 50;
    private int titleColor = Color.BLACK;
    private int editTextColor = Color.DKGRAY;
    private float titleSize = 50;
    private float editTextSize = 50;
    private int editTextWidth = 0;
    private boolean useBottomLine = false;

    public boolean isUseBottomLine() {
        return useBottomLine;
    }

    public void setUseBottomLine(boolean useBottomLine) {
        this.useBottomLine = useBottomLine;
    }

    public String getEditText() {
        return editText;
    }

    public void setEditText(String editText) {
        this.editText = editText;
    }

    public int getEditTextWidth() {
        return editTextWidth;
    }

    public void setEditTextWidth(int editTextWidth) {
        this.editTextWidth = editTextWidth;
    }

    public int getEditTextColor() {
        return editTextColor;
    }

    public void setEditTextColor(int editTextColor) {
        this.editTextColor = editTextColor;
    }

    public float getEditTextSize() {
        return editTextSize;
    }

    public void setEditTextSize(float editTextSize) {
        this.editTextSize = editTextSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public float getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(float titleSize) {
        this.titleSize = titleSize;
    }

    @Override
    public DetailItemType getDetailItemType() {
        return null;
    }


    @Override
    public View getLayoutView(LayoutInflater layoutInflater, ViewGroup parent) {
        CustomDetailItemTextAndEdittextBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_text_and_edittext, parent, false);
        binding.setItem(this);

        return binding.getRoot();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}