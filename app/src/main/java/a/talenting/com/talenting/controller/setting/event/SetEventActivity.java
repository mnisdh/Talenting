package a.talenting.com.talenting.controller.setting.event;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.adapter.DetailRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.IDetailItem;
import a.talenting.com.talenting.custom.domain.detailItem.ProfileItem;
import a.talenting.com.talenting.custom.domain.detailItem.TextContentItem;
import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailItem;
import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailsItem;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndValueItem;

public class SetEventActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DetailRecyclerViewAdapter adapter;

    private String sampleImage = "https://firebasestorage.googleapis.com/v0/b/locationsharechat.appspot.com/o/profile%2FAvXoH1Ar9PQXDBXYBk6yrUFpfA22.jpg?alt=media&token=c1d5fa82-b535-4d97-af88-75043642f019";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();
        setData();
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new DetailRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setData() {
        List<IDetailItem> items = new ArrayList<>();

        List<ThumbnailItem> thumbnails = new ArrayList<>();
        ThumbnailItem thumbnailItem = new ThumbnailItem();
        thumbnailItem.content=("image 1");
        thumbnailItem.imageUrl=(sampleImage);
        thumbnails.add(thumbnailItem);

        ThumbnailItem thumbnailItem2 = new ThumbnailItem();
        thumbnailItem2.content=("image 2");
        thumbnailItem2.imageUrl=(sampleImage);
        thumbnails.add(thumbnailItem2);

        items.add(new ThumbnailsItem(thumbnails));

        ProfileItem profileItem = new ProfileItem();
        profileItem.content=("profile");
        profileItem.imageUrl=(sampleImage);
        items.add(profileItem);

        TitleAndValueItem item = new TitleAndValueItem();
        item.title=("title");
        item.value=("value");
        items.add(item);

        item = new TitleAndValueItem();
        item.title=("title");
        item.value=("value");
        item.useBottomLine=(true);
        items.add(item);

        item = new TitleAndValueItem();
        item.title=("title");
        item.value=("value");
        items.add(item);

        TextContentItem textContentItem = new TextContentItem();
        textContentItem.content=("asdkj\naljsadklj\ndsjf");
        textContentItem.useBottomLine=(true);
        items.add(textContentItem);

        profileItem = new ProfileItem();
        profileItem.content=("profile");
        profileItem.imageUrl=(sampleImage);
        items.add(profileItem);

        profileItem = new ProfileItem();
        profileItem.content=("profile");
        profileItem.imageUrl=(sampleImage);
        profileItem.useBottomLine=(true);
        items.add(profileItem);

        profileItem = new ProfileItem();
        profileItem.content=("profile");
        profileItem.imageUrl=(sampleImage);
        items.add(profileItem);

        profileItem = new ProfileItem();
        profileItem.content=("profile");
        profileItem.imageUrl=(sampleImage);
        items.add(profileItem);

        adapter.addDataAndRefresh(items);
    }
}
