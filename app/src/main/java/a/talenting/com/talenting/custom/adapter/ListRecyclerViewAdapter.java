package a.talenting.com.talenting.custom.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.domain.detailItem.IDetailItem;

/**
 * Created by daeho on 2017. 11. 30..
 */

public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.Holder> {
    private List<IDetailItem> data = new ArrayList<>();
    private boolean useWidthMatchParent = false;
    private boolean useRemove = false;

    public ListRecyclerViewAdapter(boolean useWidthMatchParent){
        this.useWidthMatchParent = useWidthMatchParent;
    }

    public void addDataAndRefresh(IDetailItem item){
        data.add(item);
        notifyDataSetChanged();
    }

    public void addDataAndRefresh(List<IDetailItem> items){
        data.addAll(items);
        notifyDataSetChanged();
    }

    public void setUseRemove(boolean use){
        useRemove = use;

        notifyDataSetChanged();
    }

    public List<IDetailItem> getData(){
        return data;
    }

    public void removeData(IDetailItem item){
        data.remove(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View v;
        if(useWidthMatchParent) v = layoutInflater.inflate(R.layout.custom_detail_recycler_item, parent, false);
        else v = layoutInflater.inflate(R.layout.custom_detail_multi_recycler_item, parent, false);

        Holder holder = new Holder(v);
        holder.init(layoutInflater, parent);

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setDetailItem(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        private LayoutInflater layoutInflater;
        private ViewGroup parent;

        private IDetailItem item;

        private LinearLayout stage;
        private ImageButton btnDelete;

        public Holder(View itemView) {
            super(itemView);

            stage = itemView.findViewById(R.id.stage);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(v -> {
                if(item != null) removeData(item);
            });
        }

        public void init(LayoutInflater layoutInflater, ViewGroup parent){
            this.layoutInflater = layoutInflater;
            this.parent = parent;
        }

        public void setDetailItem(IDetailItem item){
            this.item = item;

            if(useRemove) btnDelete.setVisibility(View.VISIBLE);
            else btnDelete.setVisibility(View.GONE);

            stage.removeAllViews();
            stage.addView(item.getLayoutView(layoutInflater, parent));
        }
    }
}