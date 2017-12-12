package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.ItemListView;
import a.talenting.com.talenting.custom.domain.style.PaddingStyle;
import a.talenting.com.talenting.custom.domain.style.TextStyle;
import a.talenting.com.talenting.databinding.CustomDetailItemImageContentHorizontalBinding;
import a.talenting.com.talenting.databinding.CustomDetailItemImageContentVerticalBinding;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class ImageContentItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.IMAGE_CONTENT;
    private IItemClickListener onClickListener;
    private IItemClickListener onFavoriteClickListener;
    private boolean isHorizontal = false;

    public PaddingStyle padding = new PaddingStyle(50, 50, 50, 50);

    public String title = "";
    public TextStyle titleStyle = new TextStyle(Color.BLACK);

    public String content = "";
    public TextStyle contentStyle = new TextStyle(Color.DKGRAY);

    public String imageUrl = "";

    public List<String> itemList = new ArrayList<>();

    public boolean useBottomLine = false;
    public boolean useFavorite = true;
    public boolean isFavorite = false;

    public ImageContentItem(){

    }

    public ImageContentItem(String title, String content, String imageUrl){
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public ImageContentItem(String title, String content, String imageUrl, List<String> itemList){
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.itemList = itemList;
    }

    public ImageContentItem(String title, String content, String imageUrl, List<String> itemList, IItemClickListener onClickListener, IItemClickListener onFavoriteClickListener){
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.itemList = itemList;
        this.onClickListener = onClickListener;
        this.onFavoriteClickListener = onFavoriteClickListener;
    }

    public ImageContentItem(String title, String content, String imageUrl, IItemClickListener onClickListener, IItemClickListener onFavoriteClickListener){
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.onClickListener = onClickListener;
        this.onFavoriteClickListener = onFavoriteClickListener;
    }

    public ImageContentItem(boolean useHorizontal){
        isHorizontal = useHorizontal;
    }

    public void onClick(View v){
        if(onClickListener != null) onClickListener.run(this);
    }
    public void setOnClickListener(IItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void onFavoriteClick(View v){
        if(onFavoriteClickListener != null) onFavoriteClickListener.run(this);
    }
    public void setOnFavoriteClickListener(IItemClickListener onFavoriteClickListener) {
        this.onFavoriteClickListener = onFavoriteClickListener;
    }

    @Override
    public DetailItemType getDetailItemType() {
        return detailItemType;
    }

    private View view;
    @Override
    public View getLayoutView(LayoutInflater layoutInflater, ViewGroup parent) {
        //if(view == null) {
            if (isHorizontal) {
                CustomDetailItemImageContentHorizontalBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_image_content_horizontal, parent, false);
                binding.setItem(this);

                if (!itemList.isEmpty()) {
                    ItemListView itemListView = binding.getRoot().findViewById(R.id.itemList);
                    itemListView.addItems(itemList);
                }

                view = binding.getRoot();
            } else {
                CustomDetailItemImageContentVerticalBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_image_content_vertical, parent, false);
                binding.setItem(this);
                binding.imageView.getLayoutParams().width = RelativeLayout.LayoutParams.MATCH_PARENT;
                if (!itemList.isEmpty()) {
                    ItemListView itemListView = binding.getRoot().findViewById(R.id.itemList);
                    itemListView.addItems(itemList);
                }
                view = binding.getRoot();
                view.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            }
        //}

        return view;
    }
}