package a.talenting.com.talenting.controller.setting.hosting;

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
import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailItem;
import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailsItem;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndCheckItem;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndEditTextItem;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndValueItem;

public class HostingAddActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DetailRecyclerViewAdapter adapter;

    private String sampleImage = "https://firebasestorage.googleapis.com/v0/b/locationsharechat.appspot.com/o/profile%2FAvXoH1Ar9PQXDBXYBk6yrUFpfA22.jpg?alt=media&token=c1d5fa82-b535-4d97-af88-75043642f019";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosting_add);

        init();
        initData();
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new DetailRecyclerViewAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData(){
        List<IDetailItem> items = new ArrayList<>();

        List<ThumbnailItem> thumbnails = new ArrayList<>();
        thumbnails.add(new ThumbnailItem("image 1", sampleImage));
        thumbnails.add(new ThumbnailItem("image 2", sampleImage));
        ThumbnailsItem thumbnailsItem = new ThumbnailsItem(thumbnails);
        thumbnailsItem.isEditMode = true;
        items.add(thumbnailsItem);

        ProfileItem profileItem = new ProfileItem("daeho shin", sampleImage);
        profileItem.useBottomLine = true;
        items.add(profileItem);

        items.add(new TitleAndValueItem("Representation", ""));
        items.add(new TitleAndEditTextItem("Title", "", "Input title"));
        items.add(new TitleAndEditTextItem("Category", "", "Input category"));
        TitleAndValueItem titleAndValueItem = new TitleAndValueItem("Summary","write");
        titleAndValueItem.useBottomLine = true;
        items.add(titleAndValueItem);

        items.add(new TitleAndValueItem("House", ""));
        items.add(new TitleAndValueItem("House type", "choice"));
        items.add(new TitleAndValueItem("Room type", "choice"));
        items.add(new TitleAndValueItem("Meal type", "choice"));
        items.add(new TitleAndValueItem("Capacity", "choice"));
        items.add(new TitleAndValueItem("Internet", "choice"));
        items.add(new TitleAndCheckItem("Smoking", false));
        items.add(new TitleAndCheckItem("Pet", true));
        items.add(new TitleAndValueItem("Language", "choice"));
        items.add(new TitleAndValueItem("Stay min", "choice"));
        titleAndValueItem = new TitleAndValueItem("Stay max","choice");
        titleAndValueItem.useBottomLine = true;
        items.add(titleAndValueItem);

        items.add(new TitleAndValueItem("Address", ""));
        items.add(new TitleAndValueItem("Country", "choice"));
        items.add(new TitleAndValueItem("City", "choice"));
        items.add(new TitleAndValueItem("Distinct", "choice"));
        items.add(new TitleAndValueItem("Street", "choice"));
        items.add(new TitleAndValueItem("Address", "choice"));
        items.add(new TitleAndValueItem("Post code", "choice"));

        adapter.addDataAndRefresh(items);
    }
}
