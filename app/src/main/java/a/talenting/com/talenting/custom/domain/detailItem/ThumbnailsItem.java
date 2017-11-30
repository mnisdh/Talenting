package a.talenting.com.talenting.custom.domain.detailItem;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.adapter.ThumbnailViewPagerAdapter;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class ThumbnailsItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.THUMBNAIL;
    private View.OnClickListener onClickListener;
    private List<ThumbnailItem> data = new ArrayList<>();

    public ThumbnailsItem(List<ThumbnailItem> items){
        data.addAll(items);
    }

    @Override
    public DetailItemType getDetailItemType() {
        return null;
    }

    @Override
    public View getLayoutView(LayoutInflater layoutInflater, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.custom_detail_item_thumbnails, parent, false);
        ViewPager viewPager = v.findViewById(R.id.viewPager);
        ThumbnailViewPagerAdapter adapter = new ThumbnailViewPagerAdapter();
        viewPager.setAdapter(adapter);
        adapter.addItem(data);

        return v;
    }
}