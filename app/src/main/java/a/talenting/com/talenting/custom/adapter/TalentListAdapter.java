package a.talenting.com.talenting.custom.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import a.talenting.com.talenting.R;

/**
 * Created by user on 2017-12-01.
 */

public class TalentListAdapter extends RecyclerView.Adapter<TalentListAdapter.Holder> {
    Set<String> category = new HashSet<>();
    Map<String,String> talent = new LinkedHashMap<>();
    Context context;
    public TalentListAdapter(Context context){
        this.context = context;
    }

    public Map<String,String> getTalent(){
        return talent;
    }

    public Set<String> getCategory(){
        return category;
    }

    public void refresh(String categoryClicked, String talentClicked){
        if(!talent.containsKey(talentClicked)){
            talent.put(talentClicked,categoryClicked);
        }
        if(!category.contains(categoryClicked)){
            category.add(categoryClicked);
        }
        notifyDataSetChanged();

    }

    @Override
    public TalentListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_talent,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(TalentListAdapter.Holder holder, int position) {
        String text="";
        int i = 0;
        for(String key : talent.keySet()){
            if(i == position){
                text=key;
            }
            i++;
        }
        holder.txt_talent.setText(text);
        holder.image_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                talent.remove(holder.txt_talent.getText().toString());
                if(!talent.containsValue(holder.txt_talent.getText().toString())){
                    category.remove(holder.txt_talent.getText().toString());
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return talent.size();
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
