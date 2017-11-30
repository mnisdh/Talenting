package a.talenting.com.talenting.controller.setting.hosting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.adapter.ListRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.ImageContentItem;

public class HostingListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListRecyclerViewAdapter adapter;

    private String sampleImage = "https://firebasestorage.googleapis.com/v0/b/locationsharechat.appspot.com/o/profile%2FAvXoH1Ar9PQXDBXYBk6yrUFpfA22.jpg?alt=media&token=c1d5fa82-b535-4d97-af88-75043642f019";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosting_list);

        init();
        setData();
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ListRecyclerViewAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setData(){
        List<ImageContentItem> items = new ArrayList<>();

        ImageContentItem item;
        for(int i = 0; i < 10; i++){
            item = new ImageContentItem(true);
            item.setImageUrl(sampleImage);
            item.setTitle("title" + i);
            item.setContent("content" + i);

            item.setOnClickListener(j -> {
                Intent intent = new Intent(this, HostingActivity.class);
                startActivity(intent);
            });

            items.add(item);
        }

        adapter.addDataAndRefresh(items);
    }

    public void goAdd(View v){

    }
}
