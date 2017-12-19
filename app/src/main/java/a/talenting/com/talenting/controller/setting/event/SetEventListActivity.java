package a.talenting.com.talenting.controller.setting.event;

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
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.domain.event.Event;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SetEventListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_event_list);

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
        DomainManager.getEventApiService().selectsCreated(DomainManager.getTokenHeader())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) loadData(result.getEvent());
                        }
                        , error -> {}
                );
    }

    private void loadData(List<Event> events){
        List<IDetailItem> items = new ArrayList<>();

        ImageContentItem item;
        for(Event event : events){
            item = new ImageContentItem(true);
            item.imageUrl = event.getPrimary_photo();
            item.title = event.getTitle();
            item.content = event.getClosing_date();

            item.setOnClickListener(j -> {
                Intent intent = new Intent(this, SetEventActivity.class);
                startActivityForResult(intent, Constants.REQ_EDIT_EVENT);
            });

            items.add(item);
        }

        adapter.addDataAndRefresh(items);
    }

    public void goAdd(View v){
        Intent intent = new Intent(this, SetEventAddActivity.class);
        startActivityForResult(intent, Constants.REQ_ADD_EVENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case Constants.REQ_ADD_EVENT:
            case Constants.REQ_EDIT_EVENT:
                if(resultCode == RESULT_OK){
                    recreate();
                }
                break;

        }
    }
}
