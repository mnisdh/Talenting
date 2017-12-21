package a.talenting.com.talenting.controller.setting.event;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.Constants;
import a.talenting.com.talenting.common.SharedPreferenceManager;
import a.talenting.com.talenting.controller.event.EventActivity;
import a.talenting.com.talenting.custom.adapter.ListRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.ImageContentItem;
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.domain.event.JoinedEvent;
import a.talenting.com.talenting.domain.event.photo.EventPhoto;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SetJoinedEventListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_joined_event_list);

        init();
        loadData();
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ListRecyclerViewAdapter(true);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadData(){
        DomainManager.getEventApiService().selectsJoined(DomainManager.getTokenHeader(), SharedPreferenceManager.getInstance().getPk())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) loadData(result.getEvent());
                        }
                        , error -> {}
                );
    }

    private void loadData(List<JoinedEvent> events){
        adapter.clearData();

        for(JoinedEvent event : events){
            adapter.addDataAndRefresh(createItem(event));
        }
    }

    private ImageContentItem createItem(JoinedEvent event){
        ImageContentItem item = new ImageContentItem(false);
        if(event.getPrimary_photo() == null || event.getPrimary_photo().equals("")) setPhoto(item, event.getPk());
        else item.imageUrl = event.getPrimary_photo();
        item.title = event.getTitle();
        item.useFavorite = false;

        item.setOnClickListener(j -> {
            Intent intent = new Intent(this, EventActivity.class);
            intent.putExtra(Constants.EXT_EVENT_PK, event.getPk());
            startActivityForResult(intent, Constants.REQ_EDIT_EVENT);
        });

        return item;
    }

    private void setPhoto(ImageContentItem item, String pk){
        DomainManager.getEventPhotoApiService().selects(DomainManager.getTokenHeader(), pk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            int first = 999;
                            String image = "";
                            for (EventPhoto photo : result.getEvent_photo()) {
                                int photoId = Integer.parseInt(photo.getId());
                                if(first > photoId){
                                    first = photoId;
                                    image = photo.getImageUrl();
                                }
                            }

                            item.imageUrl = image;
                            adapter.refresh(item);
                        }
                );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case Constants.REQ_EDIT_EVENT:
                loadData();
                break;

        }
    }
}
