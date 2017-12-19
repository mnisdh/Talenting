package a.talenting.com.talenting.controller.message;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.adapter.DetailRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.IDetailItem;
import a.talenting.com.talenting.custom.domain.detailItem.MsgMyItem;
import a.talenting.com.talenting.custom.domain.detailItem.MsgOthersItem;

/**
 * Created by user on 2017-12-18.
 */

public class MessageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DetailRecyclerViewAdapter adapter;

    private String sampleImage = "https://firebasestorage.googleapis.com/v0/b/locationsharechat.appspot.com/o/profile%2FAvXoH1Ar9PQXDBXYBk6yrUFpfA22.jpg?alt=media&token=c1d5fa82-b535-4d97-af88-75043642f019";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        init();
        setData();
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new DetailRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setData(){
        List<IDetailItem> items = new ArrayList<>();

        List<MsgOthersItem> msgOthersItems = new ArrayList<>();
        MsgOthersItem msgOthersItem = new MsgOthersItem();
        msgOthersItem.content = ("other item");
        msgOthersItem.imageUrl = (sampleImage);
        msgOthersItem.lastTime = ("1 minutes ago");
        msgOthersItem.name = ("other");
        msgOthersItems.add(msgOthersItem);

        items.add(new MsgOthersItem(msgOthersItems));

        List<MsgMyItem> msgMyItems = new ArrayList<>();
        MsgMyItem msgMyItem = new MsgMyItem();
        msgMyItem.content = ("other item");
        msgMyItem.imageUrl = (sampleImage);
        msgMyItem.lastTime = ("2 minutes ago");
        msgMyItem.name = ("me");
        msgMyItems.add(msgMyItem);

        items.add(new MsgMyItem(msgMyItems));

        adapter.addDataAndRefresh(items);
    }
}
