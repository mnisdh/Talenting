package a.talenting.com.talenting.controller.message;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.controller.hosting.HostingActivity;
import a.talenting.com.talenting.controller.setting.profile.ProfileEditActivity;
import a.talenting.com.talenting.custom.adapter.DetailRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.IDetailItem;
import a.talenting.com.talenting.custom.domain.detailItem.IItemClickListener;
import a.talenting.com.talenting.custom.domain.detailItem.MsgMyItem;
import a.talenting.com.talenting.custom.domain.detailItem.MsgOthersItem;

import static android.view.View.VISIBLE;

/**
 * Created by user on 2017-12-18.
 */

public class MessageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DetailRecyclerViewAdapter adapter;
    private EditText edit_msg;
    private Button btn_send;

    private String sampleImage = "https://firebasestorage.googleapis.com/v0/b/locationsharechat.appspot.com/o/profile%2FAvXoH1Ar9PQXDBXYBk6yrUFpfA22.jpg?alt=media&token=c1d5fa82-b535-4d97-af88-75043642f019";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        init();
        initListener();
        setData();
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new DetailRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setBackgroundColor(Color.WHITE);

        edit_msg = findViewById(R.id.edit_msg);
        btn_send = findViewById(R.id.btn_send);


    }

    private void initListener(){
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        edit_msg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("")){
                    btn_send.setVisibility(VISIBLE);
                }else{
                    btn_send.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setData(){
        List<IDetailItem> items = new ArrayList<>();

        MsgOthersItem msgOthersItem = new MsgOthersItem();
        msgOthersItem.content = ("other item");
        msgOthersItem.imageUrl = (sampleImage);
        msgOthersItem.lastTime = ("1 minutes ago");
        msgOthersItem.name = ("other");
        msgOthersItem.setOnClickListener(hostMsgClickEvent);
        items.add(msgOthersItem);

        MsgMyItem msgMyItem = new MsgMyItem();
        msgMyItem.content = ("other item");
        msgMyItem.imageUrl = (sampleImage);
        msgMyItem.lastTime = ("2 minutes ago");
        msgMyItem.name = ("me");
        msgMyItem.setOnClickListener(guestMsgClickEvent);
        items.add(msgMyItem);

        adapter.addDataAndRefresh(items);
    }

    private IItemClickListener hostMsgClickEvent = i -> {
        Intent intent = new Intent(this, HostingActivity.class);
        startActivity(intent);
    };

    private IItemClickListener guestMsgClickEvent = i -> {
        Intent intent = new Intent(this, ProfileEditActivity.class);
        startActivity(intent);
    };

}
