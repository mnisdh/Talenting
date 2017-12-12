package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.adapter.ListRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.style.PaddingStyle;
import a.talenting.com.talenting.databinding.CustomDetailItemRecyclerBinding;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class RecyclerItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.RECYCLER;
    private List<ImageContentItem> data = new ArrayList<>();

    public String title;
    public PaddingStyle titlePadding = new PaddingStyle(50, 50, 50, 0);

    public RecyclerItem(String title, List<ImageContentItem> items){
        this.title = title;
        data.addAll(items);
    }

    @Override
    public DetailItemType getDetailItemType() {
        return detailItemType;
    }

    private View view;
    private RecyclerView recyclerView;
    private ListRecyclerViewAdapter adapter;
    @Override
    public View getLayoutView(LayoutInflater layoutInflater, ViewGroup parent) {
        //if(view == null) {
            CustomDetailItemRecyclerBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_recycler, parent, false);
            binding.setItem(this);

            view = binding.getRoot();

            recyclerView = view.findViewById(R.id.recyclerView);
            adapter = new ListRecyclerViewAdapter(false);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(layoutInflater.getContext(), LinearLayoutManager.HORIZONTAL, false));

            adapter.addDataAndRefresh(data);
        //}

        return view;
    }
}