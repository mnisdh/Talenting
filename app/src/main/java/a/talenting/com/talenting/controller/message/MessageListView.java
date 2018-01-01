package a.talenting.com.talenting.controller.message;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.ActivityResultManager;
import a.talenting.com.talenting.common.Constants;
import a.talenting.com.talenting.common.SharedPreferenceManager;
import a.talenting.com.talenting.custom.adapter.ListRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.MsgPreviewItem;
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.domain.fcm.Chat;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user on 2017-12-18.
 */

public class MessageListView extends FrameLayout {

    private Activity activity;
    private ActivityResultManager manager;
    private RecyclerView recyclerView;
    private ListRecyclerViewAdapter adapter;

    public MessageListView(Activity activity, ActivityResultManager manager) {
        super(activity);
        this.activity = activity;
        this.manager = manager;

        this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        init();

        getData();
    }

    private void init(){
        View v = LayoutInflater.from(this.getContext()).inflate(R.layout.view_message_list, null, false);
        this.addView(v);

        recyclerView = v.findViewById(R.id.recyclerView);
        adapter = new ListRecyclerViewAdapter(true);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    public void getData(){
        DomainManager.getFCMApiService().chatList(DomainManager.getTokenHeader())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) setData(result.getChat());
                            else Toast.makeText(activity, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        , error -> Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show());
    }

    public void setData(List<Chat> chats){
        adapter.clearData();

        for(Chat chat : chats){
            adapter.addDataAndRefresh(createItem(chat));
        }
    }

    private MsgPreviewItem createItem(Chat chat){
        MsgPreviewItem item = new MsgPreviewItem();
        item.name = "";
        item.content = chat.getBody();
        item.imageUrl = chat.getTarget_user().getImage();
        item.lastTime = chat.getCreated_at();
        item.useBottomLine = true;
        item.setOnClickListener(j -> {
            Intent intent = new Intent(this.getContext(), MessageActivity.class);
            intent.putExtra(Constants.EXT_CHAT_PK, chat.getPk());
            intent.putExtra(Constants.EXT_FROM_USER_PK, SharedPreferenceManager.getInstance().getPk());
            intent.putExtra(Constants.EXT_TO_USER_PK, chat.getTarget_user().getPk());
            this.getContext().startActivity(intent);
        });

        return item;
    }
}
