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
import a.talenting.com.talenting.common.Constants;
import a.talenting.com.talenting.custom.adapter.ListRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.IDetailItem;
import a.talenting.com.talenting.custom.domain.detailItem.ImageContentItem;

public class SetHostingListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListRecyclerViewAdapter adapter;

    private String sampleImage = "https://firebasestorage.googleapis.com/v0/b/locationsharechat.appspot.com/o/profile%2FAvXoH1Ar9PQXDBXYBk6yrUFpfA22.jpg?alt=media&token=c1d5fa82-b535-4d97-af88-75043642f019";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_hosting_list);

        init();
        setData();
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ListRecyclerViewAdapter(true);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setData(){
        List<IDetailItem> items = new ArrayList<>();

        ImageContentItem item;
        for(int i = 0; i < 10; i++){
            item = new ImageContentItem(true);
            item.imageUrl = sampleImage;
            item.title = "title" + i;
            item.content = "content" + i;

            item.setOnClickListener(j -> {
                Intent intent = new Intent(this, SetHostingActivity.class);
                startActivityForResult(intent, Constants.REQ_EDIT_HOSTING);
            });

            items.add(item);
        }

        adapter.addDataAndRefresh(items);
    }

    public void goAdd(View v){
        Intent intent = new Intent(this, SetHostingAddActivity.class);
        startActivityForResult(intent, Constants.REQ_ADD_HOSTING);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case Constants.REQ_ADD_HOSTING:
            case Constants.REQ_EDIT_HOSTING:
                if(resultCode == RESULT_OK){
                    // TODO: 2017. 12. 1. 목록갱신코드 
                }
                break;

        }
    }
}
