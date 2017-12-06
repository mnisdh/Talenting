package a.talenting.com.talenting.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a.talenting.com.talenting.R;

/**
 * Created by user on 2017-12-06.
 */

public class ItemListView extends LinearLayout{

    private Map<String,View> items = new HashMap<>();

    public ItemListView(Context context) {
        super(context);
    }

    public ItemListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public View createItemView(String text){
        View v = LayoutInflater.from(this.getContext()).inflate(R.layout.custom_item_view, this, false);
        ((TextView)v.findViewById(R.id.textView)).setText(text);

        return v;
    }

    public void addItem(String text){
        View v = createItemView(text);
        addView(v);
        items.put(text,v);
    }

    public void deleteItem(String text){
        removeView(items.get(text));
    }

    public void addItems(List<String> data){
        for(String text : data){
            View v = createItemView(text);
            addView(v);
            items.put(text,v);
        }
    }

    public void deleteItems(List<String> data){
        for(String text : data){
            removeView(items.get(text));
        }
    }



}
