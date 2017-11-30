package a.talenting.com.talenting.custom.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.domain.detailItem.ImageContentItem;

/**
 * Created by daeho on 2017. 11. 30..
 */

public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.Holder> {
    private List<ImageContentItem> data = new ArrayList<>();

    public void addDataAndRefresh(ImageContentItem item){
        data.add(item);
        notifyDataSetChanged();
    }

    public void addDataAndRefresh(List<ImageContentItem> items){
        data.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.custom_detail_recycler_item, parent, false);
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

        private LinearLayout stage;

        public Holder(View itemView) {
            super(itemView);

            stage = itemView.findViewById(R.id.stage);
        }

        public void init(LayoutInflater layoutInflater, ViewGroup parent){
            this.layoutInflater = layoutInflater;
            this.parent = parent;
        }

        public void setDetailItem(ImageContentItem item){
            stage.removeAllViews();
            stage.addView(item.getLayoutView(layoutInflater, parent));
        }
    }
}