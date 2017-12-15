package a.talenting.com.talenting.custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import a.talenting.com.talenting.R;

/**
 * Created by user on 2017-11-29.
 */

public class CategorySpinnerAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflator;
    Map<String,String> data = new HashMap<>();

    public CategorySpinnerAdapter(Context context, Map<String,String> data){
        this.context = context;
        inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        String text="";
        int i = 0;
        for(String key : data.keySet()){
            if(i == position){
                text=data.get(key);
            }
            i++;
        }
        return text;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflator.inflate(R.layout.item_list_spinner_normal, parent, false);
        }
        String text="";
        int i = 0;
        for(String key : data.keySet()){
            if(i == position){
                text=data.get(key);
            }
            i++;
        }
        ((TextView)convertView.findViewById(R.id.spinnerText)).setText(text);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflator.inflate(R.layout.item_list_spinner_normal, parent, false);
        }
        if (data != null){
            String text="";
            int i = 0;
            for(String key : data.keySet()){
                if(i == position){
                    text=data.get(key);
                }
                i++;
            }
            ((TextView)convertView.findViewById(R.id.spinnerText)).setText(text);
        }

        return convertView;
    }
}
