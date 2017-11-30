package a.talenting.com.talenting.custom.adapter;

import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailItem;
import a.talenting.com.talenting.databinding.CustomDetailItemThumbnailBinding;

/**
 * Created by daeho on 2017. 11. 30..
 */

public class ThumbnailViewPagerAdapter extends PagerAdapter {
    private List<ThumbnailItem> data = new ArrayList<>();

    public void addItem(ThumbnailItem item){
        data.add(item);
        notifyDataSetChanged();
    }

    public void addItem(List<ThumbnailItem> items){
        data.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(container.getContext());
        CustomDetailItemThumbnailBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_thumbnail, container, false);
        binding.setItem(data.get(position));

        container.addView(binding.getRoot());

        return binding.getRoot();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
