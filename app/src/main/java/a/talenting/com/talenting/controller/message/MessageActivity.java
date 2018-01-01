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
import android.widget.Toast;

import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.Constants;
import a.talenting.com.talenting.controller.hosting.HostingActivity;
import a.talenting.com.talenting.controller.setting.profile.SetProfileEditActivity;
import a.talenting.com.talenting.custom.adapter.DetailRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.IDetailItem;
import a.talenting.com.talenting.custom.domain.detailItem.IItemClickListener;
import a.talenting.com.talenting.custom.domain.detailItem.MsgMyItem;
import a.talenting.com.talenting.custom.domain.detailItem.MsgOthersItem;
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.domain.fcm.Chat;
import a.talenting.com.talenting.domain.fcm.FCMMessage;
import a.talenting.com.talenting.domain.fcm.SentMessage;
import a.talenting.com.talenting.domain.profile.Profile;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.view.View.VISIBLE;

/**
 * Created by user on 2017-12-18.
 */

public class MessageActivity extends AppCompatActivity {
    private Profile toUser, fromUser;
    private Boolean isFirst;
    private String chatPK;

    private RecyclerView recyclerView;
    private DetailRecyclerViewAdapter adapter;
    private EditText edit_msg;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        chatPK = getIntent().getStringExtra(Constants.EXT_CHAT_PK);
        initFromUser(getIntent().getStringExtra(Constants.EXT_FROM_USER_PK));
        initToUser(getIntent().getStringExtra(Constants.EXT_TO_USER_PK));
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new DetailRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setBackgroundColor(Color.WHITE);

        edit_msg = findViewById(R.id.edit_msg);
        btn_send = findViewById(R.id.btn_send);

        initListener();

        isFirst = chatPK == null || chatPK.equals("");

        if(!isFirst) getData(chatPK);
    }

    private void initFromUser(String pk){
        DomainManager.getProfileApiService().retrieve(DomainManager.getTokenHeader(), pk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) fromUser = result.getProfile();
                            else {
                                Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        , error -> {
                            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        });
    }

    private void initToUser(String pk){
        DomainManager.getProfileApiService().retrieve(DomainManager.getTokenHeader(), pk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) {
                                toUser = result.getProfile();
                                init();
                            }
                            else {
                                Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        , error -> {
                            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        });
    }

    private void initListener(){
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

    private void getData(String pk){
        DomainManager.getFCMApiService().chat(DomainManager.getTokenHeader(), pk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) setData(result.getMessages());
                            else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        , error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void setData(List<SentMessage> messages){
        //adapter.clearData();

        for(int i = messages.size(); i >= 1; i--){
            adapter.addData(createItem(messages.get(i - 1)));
        }

        adapter.refresh();
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }

    private IDetailItem createItem(SentMessage message){
        if(message.getFrom_user().getPk().equals(fromUser.getProfilePk())){
            MsgOthersItem msgOthersItem = new MsgOthersItem();
            msgOthersItem.content = message.getBody();
            msgOthersItem.imageUrl = fromUser.getFirstImageUrl();
            msgOthersItem.lastTime = message.getCreated_at();
            msgOthersItem.name = message.getFrom_user().getLast_name() + " " + message.getFrom_user().getFirst_name();
            msgOthersItem.setOnClickListener(hostMsgClickEvent);

            return msgOthersItem;
        }
        else{
            MsgMyItem msgMyItem = new MsgMyItem();
            msgMyItem.content = message.getBody();
            msgMyItem.imageUrl = toUser.getFirstImageUrl();
            msgMyItem.lastTime = message.getCreated_at();
            msgMyItem.name = message.getFrom_user().getLast_name() + " " + message.getFrom_user().getFirst_name();
            msgMyItem.setOnClickListener(guestMsgClickEvent);

            return msgMyItem;
        }
    }

    private IItemClickListener hostMsgClickEvent = i -> {
        Intent intent = new Intent(this, HostingActivity.class);
        startActivity(intent);
    };

    private IItemClickListener guestMsgClickEvent = i -> {
        Intent intent = new Intent(this, SetProfileEditActivity.class);
        startActivity(intent);
    };

    public void onSend(View v){
        FCMMessage fcmMessage = new FCMMessage();
        fcmMessage.setBody(edit_msg.getText().toString());
        fcmMessage.setTo_user(toUser.getProfilePk());

        DomainManager.getFCMApiService().sendMessage(DomainManager.getTokenHeader(), fcmMessage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) {
                                if (isFirst) {
                                    isFirst = false;
                                    updatePK();
                                }

                                MsgOthersItem msgOthersItem = new MsgOthersItem();
                                msgOthersItem.content = result.getSent_message().getBody();
                                msgOthersItem.imageUrl = fromUser.getFirstImageUrl();
                                msgOthersItem.lastTime = result.getSent_message().getCreated_at();
                                msgOthersItem.name = result.getSent_message().getFrom_user().getLast_name() + " " + result.getSent_message().getFrom_user().getFirst_name();
                                msgOthersItem.setOnClickListener(hostMsgClickEvent);

                                adapter.addDataAndRefresh(msgOthersItem);

                                edit_msg.setText("");
                            }
                        }
                        , error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void updatePK(){
        DomainManager.getFCMApiService().chatList(DomainManager.getTokenHeader())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) {
                                for(Chat chat : result.getChat()){
                                    if(chat.getTarget_user().getPk().equals(toUser.getProfilePk())){
                                        chatPK = chat.getPk();
                                        return;
                                    }
                                }
                            }
                        }
                        , error -> {
                            isFirst = true;
                            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        });
    }
}
