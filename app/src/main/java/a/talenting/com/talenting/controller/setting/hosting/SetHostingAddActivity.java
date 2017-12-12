package a.talenting.com.talenting.controller.setting.hosting;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.ActivityResultManager;
import a.talenting.com.talenting.common.DialogManager;
import a.talenting.com.talenting.common.GooglePlaceApi;
import a.talenting.com.talenting.common.GoogleStaticMap;
import a.talenting.com.talenting.custom.adapter.DetailRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.DetailItemType;
import a.talenting.com.talenting.custom.domain.detailItem.IItemClickListener;
import a.talenting.com.talenting.custom.domain.detailItem.MapPreviewItem;
import a.talenting.com.talenting.custom.domain.detailItem.ProfileItem;
import a.talenting.com.talenting.custom.domain.detailItem.TextContentItem;
import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailItem;
import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailsItem;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndCheckItem;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndValueItem;
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.util.ResourceUtil;

public class SetHostingAddActivity extends AppCompatActivity {
    private ActivityResultManager activityResultManager;

    private RecyclerView recyclerView;
    private DetailRecyclerViewAdapter adapter;

    private String sampleImage = "https://firebasestorage.googleapis.com/v0/b/locationsharechat.appspot.com/o/profile%2FAvXoH1Ar9PQXDBXYBk6yrUFpfA22.jpg?alt=media&token=c1d5fa82-b535-4d97-af88-75043642f019";

    private ThumbnailsItem thumbnailsItem;
    private ProfileItem profile;
    private TextContentItem summary, rules, description, todo;
    private TitleAndCheckItem smoking, pet;
    private TitleAndValueItem title, category, houseType, roomType, mealType, internet,
                                language, capacity, min_stay, max_stay,
                                exchange, neighborhood, transportation, locationTitle;
    private MapPreviewItem location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_hosting_add);

        activityResultManager = new ActivityResultManager();

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
        //region thumbnails
        thumbnailsItem = new ThumbnailsItem(new ArrayList<>());
        thumbnailsItem.isEditMode = true;
        thumbnailsItem.setOnAddClickListener(item -> {
            DialogManager.showCameraDialog(this, activityResultManager, value -> {
                thumbnailsItem.addThumbnail(new ThumbnailItem("", value));
                //adapter.refresh(thumbnailsItem);
            });
        });
        thumbnailsItem.setOnDeleteClickListener(item -> {

        });
        adapter.addData(thumbnailsItem);
        //endregion
        //region profile
        profile = new ProfileItem("Host name", sampleImage);
        profile.useBottomLine = true;
        adapter.addData(profile);
        //endregion
        //region title
        title = new TitleAndValueItem(getResStrng(R.string.hosting_title)
                                    , getResStrng(R.string.hosting_input)
                                    , titleAndValueItemClickEvent);
        title.useBottomLine = true;
        adapter.addData(title);
        //endregion
        //region summary
        summary = new TextContentItem(getResStrng(R.string.hosting_summary)
                                    , getResStrng(R.string.hosting_input)
                                    , contentItemClickEvent);
        summary.useBottomLine = true;
        adapter.addData(summary);
        //endregion
        //region category type
        category = new TitleAndValueItem(getResStrng(R.string.hosting_category)
                                        , getResStrng(R.string.hosting_input)
                                        , typeItemClickEvent);
        category.useBottomLine = true;
        adapter.addData(category);
        //endregion
        //region house type
        houseType = new TitleAndValueItem(getResStrng(R.string.hosting_house_type)
                                        , getResStrng(R.string.hosting_input)
                                        , typeItemClickEvent);
        houseType.useBottomLine = true;
        adapter.addData(houseType);
        //endregion
        //region room type
        roomType = new TitleAndValueItem(getResStrng(R.string.hosting_room_type)
                                        , getResStrng(R.string.hosting_input)
                                        , typeItemClickEvent);
        roomType.useBottomLine = true;
        adapter.addData(roomType);
        //endregion
        //region meal type
        mealType = new TitleAndValueItem(getResStrng(R.string.hosting_meal_type)
                                        , getResStrng(R.string.hosting_input)
                                        , typeItemClickEvent);
        mealType.useBottomLine = true;
        adapter.addData(mealType);
        //endregion
        //region internet type
        internet = new TitleAndValueItem(getResStrng(R.string.hosting_internet)
                                        , getResStrng(R.string.hosting_input)
                                        , typeItemClickEvent);
        internet.useBottomLine = true;
        adapter.addData(internet);
        //endregion
        //region smoking
        smoking = new TitleAndCheckItem(getResStrng(R.string.hosting_smoking), false);
        smoking.useBottomLine = true;
        adapter.addData(smoking);
        //endregion
        //region pet
        pet = new TitleAndCheckItem(getResStrng(R.string.hosting_pet), false);
        pet.useBottomLine = true;
        adapter.addData(pet);
        //endregion
        //region rules
        rules = new TextContentItem(getResStrng(R.string.hosting_rules)
                                    , getResStrng(R.string.hosting_input)
                                    , contentItemClickEvent);
        rules.useBottomLine = true;
        adapter.addData(rules);
        //endregion
        //region language type
        language = new TitleAndValueItem(getResStrng(R.string.hosting_language)
                                        , getResStrng(R.string.hosting_input)
                                        , typeItemClickEvent);
        language.useBottomLine = true;
        adapter.addData(language);
        //endregion
        //region capacity
        capacity = new TitleAndValueItem(getResStrng(R.string.hosting_capacity)
                                        , getResStrng(R.string.hosting_input)
                                        , titleAndValueItemClickEvent);
        capacity.useBottomLine = true;
        adapter.addData(capacity);
        //endregion
        //region min stay
        min_stay = new TitleAndValueItem(
                getResStrng(R.string.hosting_min_stay)
                , getResStrng(R.string.hosting_input)
                , item -> {

        });
        min_stay.useBottomLine = true;
        adapter.addData(min_stay);
        //endregion
        //region max stay
        max_stay = new TitleAndValueItem(
                getResStrng(R.string.hosting_max_stay)
                , getResStrng(R.string.hosting_input)
                , item -> {

        });
        max_stay.useBottomLine = true;
        adapter.addData(max_stay);
        //endregion
        //region description
        description = new TextContentItem(getResStrng(R.string.hosting_description)
                                        , getResStrng(R.string.hosting_input)
                                        , contentItemClickEvent);
        description.useBottomLine = true;
        adapter.addData(description);
        //endregion
        //region todo
        todo = new TextContentItem(getResStrng(R.string.hosting_to_do)
                                    , getResStrng(R.string.hosting_input)
                                    , contentItemClickEvent);
        todo.useBottomLine = true;
        adapter.addData(todo);
        //endregion
        //region exchange
        exchange = new TitleAndValueItem(getResStrng(R.string.hosting_exchange)
                                        , getResStrng(R.string.hosting_input)
                                        , titleAndValueItemClickEvent);
        exchange.useBottomLine = true;
        adapter.addData(exchange);
        //endregion
        //region neighborhood
        neighborhood = new TitleAndValueItem(getResStrng(R.string.hosting_neighborhood)
                                            , getResStrng(R.string.hosting_input)
                                            , titleAndValueItemClickEvent);
        neighborhood.useBottomLine = true;
        adapter.addData(neighborhood);
        //endregion
        //region transportation
        transportation = new TitleAndValueItem(getResStrng(R.string.hosting_transportation)
                                            , getResStrng(R.string.hosting_input)
                                            , titleAndValueItemClickEvent);
        transportation.useBottomLine = true;
        adapter.addData(transportation);
        //endregion
        //region location
        locationTitle = new TitleAndValueItem(getResStrng(R.string.hosting_location), "");
        adapter.addData(locationTitle);

        GoogleStaticMap googleStaticMap = new GoogleStaticMap();
        location = new MapPreviewItem(googleStaticMap, item -> {
            GooglePlaceApi.startPlaceSelectMap(activityResultManager
                    , p -> {
                        location.googleStaticMap.setLatlng(p.getLatLng(), Color.RED);
                        adapter.refresh(location);
                    }
                    , this
                    , location.googleStaticMap.getLatLng());
        });
        adapter.addData(location);
        //endregion

        adapter.refresh();
    }

    private String getResStrng(int id){
        return ResourceUtil.getString(this, id);
    }

    private IItemClickListener titleAndValueItemClickEvent = i -> {
        if(i.getDetailItemType() == DetailItemType.TITLE_AND_VALUE) {
            TitleAndValueItem item = (TitleAndValueItem) i;

            if(item.value.equals(getResStrng(R.string.hosting_input))) item.value = "";
            DialogManager.showTextDialog(this, item, value -> {
                item.value = value;
                adapter.refresh(item);
            });
        }
    };
    private IItemClickListener contentItemClickEvent = i -> {
        if(i.getDetailItemType() == DetailItemType.TEXT_CONTENT) {
            TextContentItem item = (TextContentItem) i;

            if(item.content.equals(getResStrng(R.string.hosting_input))) item.content = "";
            DialogManager.showMultiLineTextDialog(this, item.title, summary, value -> {
                summary.content = value;
                adapter.refresh(summary);
            });
        }
    };
    private IItemClickListener typeItemClickEvent = i -> {
        if(i.getDetailItemType() == DetailItemType.TITLE_AND_VALUE){
            TitleAndValueItem item = (TitleAndValueItem) i;
            Map<Integer, String> data = new LinkedHashMap<>();
            if(item == category) data = DomainManager.getHostingCategory();
            else if(item == houseType) data = DomainManager.getHostingHouseType();
            else if(item == roomType) data = DomainManager.getHostingRoomType();
            else if(item == mealType) data = DomainManager.getHostingMealType();
            else if(item == internet) data = DomainManager.getHostingInternetType();
            else if(item == language){

            }

            DialogManager.showTypeListDialog(this, item.title, data, (String code, String text) ->
                {
                    item.value = text;
                    item.valueCode = code;
                    adapter.refresh(item);
                });
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        activityResultManager.onActivityResult(requestCode, resultCode, data);
    }
}
