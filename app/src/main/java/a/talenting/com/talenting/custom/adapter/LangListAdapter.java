package a.talenting.com.talenting.custom.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Map;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.domain.BaseData;

/**
 * Created by user on 2017-12-01.
 */

public class LangListAdapter extends RecyclerView.Adapter<LangListAdapter.Holder> {
    Map<String,String> data = new LinkedHashMap<>();
    Context context;
    public LangListAdapter(Context context){
        this.context = context;
    }

    public Map<String,String> getData(){
        return data;
    }

    public void langAdd(int position){
        String key = BaseData.getLanguageKey(position);
        if(!data.containsKey(key)) data.put(key, BaseData.getLanguageText(key));
        notifyDataSetChanged();
    }

    @Override
    public LangListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_talent,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(LangListAdapter.Holder holder, int position) {
        String text="";
        int i = 0;
        for(String key : data.keySet()){
            if(i == position){
                text=data.get(key);
            }
            i++;
        }
        holder.txt_talent.setText(text);
        holder.image_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=0;
                for(String value : data.values()){
                    if(holder.txt_talent.getText().toString().equals(value)){
                       break;
                    }
                    i++;
                }
                int j = 0;
                for(String key : data.keySet()){
                    if(i == j){
                        data.remove(key);
                        break;
                    }
                    j++;
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txt_talent;
        ImageView image_minus;
        public Holder(View itemView) {
            super(itemView);
            txt_talent = itemView.findViewById(R.id.text_talent);
            image_minus = itemView.findViewById(R.id.image_minus);
        }
    }
}
