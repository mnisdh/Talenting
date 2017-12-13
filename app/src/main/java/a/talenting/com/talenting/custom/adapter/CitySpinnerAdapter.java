package a.talenting.com.talenting.custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Map;

import a.talenting.com.talenting.R;

/**
 * Created by user on 2017-11-29.
 */

public class CitySpinnerAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflator;
    Map<String, String> language = new LinkedHashMap<>();

    public CitySpinnerAdapter(Context context, Map<String, String> language){
        this.context = context;
        inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.language = language;

    }

    @Override
    public int getCount() {
        return language.size();
    }

    @Override
    public Object getItem(int position) {
        String text="";
        int i = 0;
        for(String key : language.keySet()){
            if(i == position){
                text=language.get(key);
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
        for(String key : language.keySet()){
            if(i == position){
                text=language.get(key);
            }
            i++;
        }
        ((TextView)convertView.findViewById(R.id.spinnerText)).setText(text);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflator.inflate(R.layout.item_list_spinner_dropdown, parent, false);
        }
        if (language != null){
            String text="";
            int i = 0;
            for(String key : language.keySet()){
                if(i == position){
                    text=language.get(key);
                }
                i++;
            }
            ((TextView)convertView.findViewById(R.id.spinnerText)).setText(text);
        }

        return convertView;
    }
}
