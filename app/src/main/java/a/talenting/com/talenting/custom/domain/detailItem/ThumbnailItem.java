package a.talenting.com.talenting.custom.domain.detailItem;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a.talenting.com.talenting.custom.domain.style.PaddingStyle;
import a.talenting.com.talenting.custom.domain.style.TextStyle;

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

    public ThumbnailItem(){
        contentStyle.getPadding().setLeft(10);
        contentStyle.getPadding().setBottom(10);
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

    public void onClick(View v){
        if(onClickListener != null) onClickListener.run(this);
    }
    public void setOnClickListener(IItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public DetailItemType getDetailItemType() {
        return null;
    }

    @Override
    public View getLayoutView(LayoutInflater layoutInflater, ViewGroup parent) {
        return null;
    }
}