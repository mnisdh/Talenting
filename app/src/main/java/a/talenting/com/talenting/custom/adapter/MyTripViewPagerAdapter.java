package a.talenting.com.talenting.custom.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.custom.domain.detailItem.MyTripItem;

/**
 * Created by user on 2017-12-21.
 */

public class MyTripViewPagerAdapter extends PagerAdapter {
    private List<MyTripItem> data = new ArrayList<>();

    public void addItem(MyTripItem item){
        data.add(item);
        notifyDataSetChanged();
    }

    public void addItem(List<MyTripItem> items){
        data.addAll(items);
        notifyDataSetChanged();
    }

    public void deleteItem(MyTripItem item){
        data.remove(item);
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
