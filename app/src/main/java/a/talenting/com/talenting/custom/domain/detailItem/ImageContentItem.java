package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.databinding.CustomDetailItemImageContentHorizontalBinding;
import a.talenting.com.talenting.databinding.CustomDetailItemImageContentVerticalBinding;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class ImageContentItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.IMAGE_CONTENT;
    private boolean isHorizontal = false;

    private String title = "";
    private String content = "";
    private int topPadding = 50;
    private int leftPadding = 50;
    private int rightPadding = 50;
    private int bottomPadding = 50;
    private int titleColor = Color.BLACK;
    private float titleSize = 50;
    private int contentColor = Color.DKGRAY;
    private float contentSize = 40;
    private String imageUrl = "";
    private boolean useBottomLine = false;
    private boolean isFavorite = false;
    private IItemClickListener onClickListener;
    private IItemClickListener onFavoriteClickListener;

    public ImageContentItem(boolean useHorizontal){
        isHorizontal = useHorizontal;
    }

    public void setOnClickListener(IItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnFavoriteClickListener(IItemClickListener onFavoriteClickListener) {
        this.onFavoriteClickListener = onFavoriteClickListener;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean isUseBottomLine() {
        return useBottomLine;
    }

    public void setUseBottomLine(boolean useBottomLine) {
        this.useBottomLine = useBottomLine;
    }

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
        View v = null;

        if(isHorizontal){
            CustomDetailItemImageContentHorizontalBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_image_content_horizontal, parent, false);
            binding.setItem(this);

            v = binding.getRoot();
        }
        else{
            CustomDetailItemImageContentVerticalBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_image_content_vertical, parent, false);
            binding.setItem(this);

            v = binding.getRoot();
        }

        return v;
    }

    public void onClick(View v){
        if(onClickListener != null) onClickListener.run(this);
    }

    public void onFavoriteClick(View v){
        if(onFavoriteClickListener != null) onFavoriteClickListener.run(this);
    }
}