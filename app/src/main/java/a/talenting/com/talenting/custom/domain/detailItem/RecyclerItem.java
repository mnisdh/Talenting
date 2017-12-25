package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
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
import a.talenting.com.talenting.custom.domain.style.TextStyle;
import a.talenting.com.talenting.databinding.CustomDetailItemRecyclerBinding;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class RecyclerItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.RECYCLER;
    private IItemClickListener onAddClickListener;
    private IItemClickListener onRemoveClickListener;
    private List<IDetailItem> data = new ArrayList<>();

    public String title;
    public TextStyle titleStyle = new TextStyle(Color.BLACK);
    public PaddingStyle titlePadding = new PaddingStyle(50, 50, 50, 0);

    private boolean useAddMode = false;
    private boolean useRemoveMode = false;

    public boolean useBottomLine = false;

    public RecyclerItem(String title, List<IDetailItem> items){
        this.title = title;
        data.addAll(items);
    }

    public boolean isUseAddMode() {
        return useAddMode;
    }

    public void setUseAddMode(boolean useAddMode) {
        this.useAddMode = useAddMode;
    }

    public boolean isUseRemoveMode() {
        return useRemoveMode;
    }

    public void setUseRemoveMode(boolean useRemoveMode) {
        this.useRemoveMode = useRemoveMode;

        if(adapter != null) adapter.setUseRemove(useRemoveMode, item -> {
            if(data.contains(item)) data.remove(item);
        });
    }

    public void addItem(IDetailItem item){
        if(adapter == null) data.add(item);
        else {
            adapter.addDataAndRefresh(item);
            data = adapter.getData();
        }
    }

    public List<IDetailItem> getItems(){
        if(adapter != null && adapter.getData().size() > 0) data = adapter.getData();
        return data;
    }

    public void refresh(IDetailItem item){
        if(adapter != null) adapter.refresh(item);
    }

    public void onAddClick(View v){
        if(onAddClickListener != null) onAddClickListener.run(this);
    }
    public void setOnAddClickListener(IItemClickListener onClickListener) {
        this.onAddClickListener = onClickListener;
    }

    public void onRemoveClick(View v){
        if(onRemoveClickListener != null) onRemoveClickListener.run(this);
    }
    public void setOnRemoveClickListener(IItemClickListener onClickListener) {
        this.onRemoveClickListener = onClickListener;
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
            adapter.setUseRemove(useRemoveMode, item -> {
                if(data.contains(item)) data.remove(item);
            });
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(layoutInflater.getContext(), LinearLayoutManager.HORIZONTAL, false));

            adapter.addDataAndRefresh(data);
        //}

        return view;
    }
}