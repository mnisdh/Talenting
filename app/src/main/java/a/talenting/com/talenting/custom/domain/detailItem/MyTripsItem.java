package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.adapter.MyTripViewPagerAdapter;
import a.talenting.com.talenting.databinding.CustomDetailItemMytripsBinding;

/**
 * Created by user on 2017-12-21.
 */

public class MyTripsItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.MyTRIPS;
    private List<MyTripItem> data = new ArrayList<>();
    private IItemClickListener onAddClickListener;
    private IItemClickListener onDeleteClickListener;

    public boolean isEditMode = false;

    public MyTripsItem(List<MyTripItem> items){
        data.addAll(items);
    }

    public void addMyTrip(MyTripItem item){
        data.add(item);
        if(adapter != null) adapter.addItem(item);
    }

    public void deleteMyTrip(MyTripItem item){
        data.remove(item);
        if(adapter != null) adapter.deleteItem(item);
    }

    public List<MyTripItem> getMyTripItems(){
        return data;
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

    public MyTripItem selectedMyTrip(){
        if(currentPosition < 0) return null;
        if(data.size() == 0) return null;

        return data.get(currentPosition);
    }

    @Override
    public DetailItemType getDetailItemType() {
        return detailItemType;
    }

    private int currentPosition = 0;
    private View view;
    private ViewPager viewPager;
    private MyTripViewPagerAdapter adapter;
    @Override
    public View getLayoutView(LayoutInflater layoutInflater, ViewGroup parent) {
        //if(view == null) {
        CustomDetailItemMytripsBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_mytrips, parent, false);
        binding.setItem(this);

        view = binding.getRoot();
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        adapter = new MyTripViewPagerAdapter();
        viewPager.setAdapter(adapter);
        adapter.addItem(data);
        //}

        return view;
    }
}