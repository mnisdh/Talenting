package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.domain.style.PaddingStyle;
import a.talenting.com.talenting.custom.domain.style.TextStyle;
import a.talenting.com.talenting.databinding.CustomDetailItemThumbnailBinding;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class ThumbnailItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.THUMBNAIL;
    private IItemClickListener onClickListener;
    private IItemClickListener onContentClickListener;
    private IItemClickListener onSubContentClickListener;

    public PaddingStyle padding = new PaddingStyle();

    public boolean useContent = false;
    public boolean useSubContent = false;

    public String content = "";
    public String contentCode = "";
    public String contentHint = "";
    public TextStyle contentStyle = new TextStyle(Color.DKGRAY);

    public String subContent = "";
    public String subContentCode = "";
    public String subContentHint = "";
    public TextStyle subContentStyle = new TextStyle(Color.DKGRAY);

    public String imageUrl = "";

    private IThumbnailPhoto thumbnailPhoto;

    public ThumbnailItem(){
        init();
    }
    public ThumbnailItem(IThumbnailPhoto thumbnailPhoto){
        init();

        this.thumbnailPhoto = thumbnailPhoto;
        this.content = thumbnailPhoto.getContent();
        this.imageUrl = thumbnailPhoto.getImageUrl();
    }
    public ThumbnailItem(String content, String imageUrl){
        init();

        this.content = content;
        this.imageUrl = imageUrl;
    }
    public ThumbnailItem(String content, String imageUrl, IItemClickListener onClickListener){
        init();

        this.content = content;
        this.imageUrl = imageUrl;
        this.onClickListener = onClickListener;
    }

    private void init(){
        contentStyle.getPadding().setLeft(10);
        contentStyle.getPadding().setBottom(10);
        subContentStyle.getPadding().setLeft(10);
        subContentStyle.getPadding().setBottom(10);
    }

    public IThumbnailPhoto getThumbnailPhoto(){
        return thumbnailPhoto;
    }

    public void onClick(View v){
        if(onClickListener != null) onClickListener.run(this);
    }
    public void setOnClickListener(IItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void onContentClick(View v){
        if(onContentClickListener != null) onContentClickListener.run(this);
    }
    public void setOnContentClickListener(IItemClickListener onContentClickListener) {
        this.onContentClickListener = onContentClickListener;
    }

    public void onSubContentClick(View v){
        if(onSubContentClickListener != null) onSubContentClickListener.run(this);
    }
    public void setOnSubContentClickListener(IItemClickListener onSubContentClickListener) {
        this.onSubContentClickListener = onSubContentClickListener;
    }

    @Override
    public DetailItemType getDetailItemType() {
        return detailItemType;
    }

    private View view;
    @Override
    public View getLayoutView(LayoutInflater layoutInflater, ViewGroup parent) {
        //if(view == null) {
            CustomDetailItemThumbnailBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_thumbnail, parent, false);
            binding.setItem(this);

            view = binding.getRoot();
        //}

        return view;
    }
}