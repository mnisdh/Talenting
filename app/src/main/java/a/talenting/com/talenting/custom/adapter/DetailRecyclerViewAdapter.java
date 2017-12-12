package a.talenting.com.talenting.custom.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.domain.detailItem.IDetailItem;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class DetailRecyclerViewAdapter extends RecyclerView.Adapter<DetailRecyclerViewAdapter.Holder> {
    private List<IDetailItem> data = new ArrayList<>();

    public void addData(IDetailItem item){
        data.add(item);
    }

    public void addData(List<IDetailItem> items){
        data.addAll(items);
    }

    public void refresh(){
        notifyDataSetChanged();
    }

    public void refresh(IDetailItem item){
        notifyItemChanged(data.indexOf(item));
    }

    public void addDataAndRefresh(IDetailItem item){
        data.add(item);
        refresh();
    }

    public void addDataAndRefresh(List<IDetailItem> items){
        data.addAll(items);
        refresh();
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

        public void setDetailItem(IDetailItem item){
            for(int i = stage.getChildCount() - 1; i >= 0; i--){
                View childView = stage.getChildAt(i);
                ViewGroup parentGroup = (ViewGroup)childView.getParent();

                if(parentGroup != null) parentGroup.removeView(childView);

                stage.removeView(childView);
            }

            stage.addView(item.getLayoutView(layoutInflater, parent));
        }
    }
}
