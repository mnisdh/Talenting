package a.talenting.com.talenting.custom.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailItem;

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
        View view = data.get(position).getLayoutView(layoutInflater, container);

        container.addView(view);

        return view;
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
