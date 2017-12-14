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
import a.talenting.com.talenting.domain.hosting.photo.HostingPhoto;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class ThumbnailItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.THUMBNAIL;
    private IItemClickListener onClickListener;

    public PaddingStyle padding = new PaddingStyle();

    public String content = "";
    public TextStyle contentStyle = new TextStyle(Color.WHITE);

    public String imageUrl = "";

    private HostingPhoto hostingPhoto;

    public ThumbnailItem(){
        contentStyle.getPadding().setLeft(10);
        contentStyle.getPadding().setBottom(10);
    }
    public ThumbnailItem(HostingPhoto hostingPhoto){
        content = hostingPhoto.getCaption();
        imageUrl = hostingPhoto.getHosting_thumbnail();

        this.hostingPhoto = hostingPhoto;
    }
    public ThumbnailItem(String content, String imageUrl){
        contentStyle.getPadding().setLeft(10);
        contentStyle.getPadding().setBottom(10);

        this.content = content;
        this.imageUrl = imageUrl;
    }
    public ThumbnailItem(String content, String imageUrl, IItemClickListener onClickListener){
        contentStyle.getPadding().setLeft(10);
        contentStyle.getPadding().setBottom(10);

        this.content = content;
        this.imageUrl = imageUrl;
        this.onClickListener = onClickListener;
    }

    public HostingPhoto getHostingPhoto(){
        return hostingPhoto;
    }

    public void onClick(View v){
        if(onClickListener != null) onClickListener.run(this);
    }
    public void setOnClickListener(IItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
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