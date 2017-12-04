package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.domain.style.PaddingStyle;
import a.talenting.com.talenting.custom.domain.style.TextStyle;
import a.talenting.com.talenting.databinding.CustomDetailItemProfileBinding;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class ProfileItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.PROFILE;
    private IItemClickListener onClickListener;

    public PaddingStyle padding = new PaddingStyle(50, 50, 50, 50);

    public String content = "";
    public TextStyle contentStyle = new TextStyle(Color.BLACK);

    public String imageUrl = "";

    public boolean useBottomLine = false;

    public ProfileItem(){

    }
    public ProfileItem(String content, String imageUrl){
        this.content = content;
        this.imageUrl = imageUrl;
    }
    public ProfileItem(String content, String imageUrl, IItemClickListener onClickListener){
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
        CustomDetailItemProfileBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_profile, parent, false);
        binding.setItem(this);

        return binding.getRoot();
    }
}