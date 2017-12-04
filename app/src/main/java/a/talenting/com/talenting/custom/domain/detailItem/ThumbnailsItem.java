package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.adapter.ThumbnailViewPagerAdapter;
import a.talenting.com.talenting.databinding.CustomDetailItemThumbnailsBinding;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class ThumbnailsItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.THUMBNAILS;
    private List<ThumbnailItem> data = new ArrayList<>();
    private IItemClickListener onAddClickListener;
    private IItemClickListener onDeleteClickListener;

    public boolean isEditMode = false;

    public ThumbnailsItem(List<ThumbnailItem> items){
        data.addAll(items);
    }

    public void onAddClick(View v){
        if(onAddClickListener != null) onAddClickListener.run(this);
    }
    public void setOnAddClickListener(IItemClickListener onAddClickListener) {
        this.onAddClickListener = onAddClickListener;
    }

    public void onDeleteClick(View v){
        if(onDeleteClickListener != null) onDeleteClickListener.run(this);
    }
    public void setOnDeleteClickListener(IItemClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @Override
    public DetailItemType getDetailItemType() {
        return null;
    }

    @Override
    public View getLayoutView(LayoutInflater layoutInflater, ViewGroup parent) {
        CustomDetailItemThumbnailsBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_thumbnails, parent, false);
        binding.setItem(this);

        View v = binding.getRoot();
        ViewPager viewPager = v.findViewById(R.id.viewPager);
        ThumbnailViewPagerAdapter adapter = new ThumbnailViewPagerAdapter();
        viewPager.setAdapter(adapter);
        adapter.addItem(data);

        return v;
    }
}