package a.talenting.com.talenting.controller.event;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.adapter.ListRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.ImageContentItem;

/**
 * Created by daeho on 2017. 12. 5..
 */

public class EventListView extends FrameLayout {
    private RecyclerView recyclerView;
    private ListRecyclerViewAdapter adapter;

    private String sampleImage = "https://firebasestorage.googleapis.com/v0/b/locationsharechat.appspot.com/o/profile%2FAvXoH1Ar9PQXDBXYBk6yrUFpfA22.jpg?alt=media&token=c1d5fa82-b535-4d97-af88-75043642f019";

    public EventListView(Context context) {
        super(context);

        this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        init();
    }

    private void init(){
        View v = LayoutInflater.from(this.getContext()).inflate(R.layout.view_event_list, null, false);
        this.addView(v);

        recyclerView = v.findViewById(R.id.recyclerView);
        adapter = new ListRecyclerViewAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    public void setSampleData(){
        List<ImageContentItem> items = new ArrayList<>();

        ImageContentItem item;
        for(int i = 0; i < 10; i++){
            item = new ImageContentItem(false);
            item.imageUrl = sampleImage;
            item.title = "title" + i;
            item.content = "content" + i;

            item.setOnClickListener(j -> {
                Intent intent = new Intent(this.getContext(), EventActivity.class);
                this.getContext().startActivity(intent);
            });

            items.add(item);
        }

        adapter.addDataAndRefresh(items);
    }

    public void addData(ImageContentItem item){
        adapter.addDataAndRefresh(item);
    }

    public void addData(List<ImageContentItem> items){
        adapter.addDataAndRefresh(items);
    }
}
