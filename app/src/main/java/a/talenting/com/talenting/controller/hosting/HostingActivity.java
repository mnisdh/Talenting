package a.talenting.com.talenting.controller.hosting;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.ActivityResultManager;
import a.talenting.com.talenting.common.Constants;
import a.talenting.com.talenting.common.GoogleStaticMap;
import a.talenting.com.talenting.controller.common.LocationActivity;
import a.talenting.com.talenting.controller.user.UserActivity;
import a.talenting.com.talenting.custom.adapter.DetailRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.DetailItemType;
import a.talenting.com.talenting.custom.domain.detailItem.IDetailItem;
import a.talenting.com.talenting.custom.domain.detailItem.IItemClickListener;
import a.talenting.com.talenting.custom.domain.detailItem.MapPreviewItem;
import a.talenting.com.talenting.custom.domain.detailItem.ProfileItem;
import a.talenting.com.talenting.custom.domain.detailItem.RecyclerItem;
import a.talenting.com.talenting.custom.domain.detailItem.TextContentItem;
import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailItem;
import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailsItem;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndCheckItem;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndValueItem;
import a.talenting.com.talenting.domain.BaseData;
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.domain.hosting.Hosting;
import a.talenting.com.talenting.domain.hosting.photo.HostingPhoto;
import a.talenting.com.talenting.domain.profile.Profile;
import a.talenting.com.talenting.util.ResourceUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HostingActivity extends AppCompatActivity {
    private ActivityResultManager activityResultManager;
    private String pk;
    private Hosting baseHosting = null;

    private ConstraintLayout progress;
    private RecyclerView recyclerView;
    private DetailRecyclerViewAdapter adapter;

    private ThumbnailsItem thumbnailsItem;
    private ProfileItem profile;
    private TextContentItem summary, rules, description, todo;
    private TitleAndCheckItem smoking, pet;
    private TitleAndValueItem title, category, houseType, roomType, mealType, internet,
            message, capacity, min_stay, max_stay, exchange, neighborhood,
            transportation, locationTitle;
    private MapPreviewItem location;
    private RecyclerItem language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosting);

        pk = getIntent().getStringExtra(Constants.EXT_HOSTING_PK);
        if(pk == null || "".equals(pk)) finish();

        activityResultManager = new ActivityResultManager();

        init();
        loadData();
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new DetailRecyclerViewAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progress = findViewById(R.id.progress);
    }

    private void loadData(){
        DomainManager.getHostingApiService().select(DomainManager.getTokenHeader(), pk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) loadData(result.getHosting());
                            else{
                                Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        , error -> {
                            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                );
    }
    private void loadData(Hosting hosting){
        baseHosting = hosting;

        //region thumbnails
        thumbnailsItem = new ThumbnailsItem(new ArrayList<>());
        thumbnailsItem.useFavorite = true;
        thumbnailsItem.isFavorite = hosting.isWish();
        thumbnailsItem.setOnFavoriteClickListener(i -> {
            DomainManager.getHostingApiService().wishListToggle(DomainManager.getTokenHeader(), hosting.getOwner())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> { },
                            e -> thumbnailsItem.isFavorite = !thumbnailsItem.isFavorite
                    );
        });
        adapter.addData(thumbnailsItem);

        loadPhotoData();
        //endregion
        //region profile
        profile = new ProfileItem("", "");
        profile.useBottomLine = false;
        adapter.addData(profile);

        loadProfileData(hosting.getOwner());
        //endregion
        //region message
        message = new TitleAndValueItem(""
                , getResStrng(R.string.hosting_send_message)
                , i -> {
            //todo:메세지 보내는 인텐트 추가
        });
        message.valueStyle.setColor(Color.GREEN);
        message.useBottomLine = true;
        adapter.addData(message);
        //endregion
        //region title
        title = new TitleAndValueItem(getResStrng(R.string.hosting_title)
                , hosting.getTitle()
                , null);
        title.useBottomLine = true;
        adapter.addData(title);
        //endregion
        //region summary
        summary = new TextContentItem(getResStrng(R.string.hosting_summary)
                , hosting.getSummary()
                , null);
        summary.useBottomLine = true;
        adapter.addData(summary);
        //endregion
        //region category type
        category = new TitleAndValueItem(getResStrng(R.string.hosting_category)
                , BaseData.getCategoryText(hosting.getCategory())
                , hosting.getCategory()
                , null);
        category.useBottomLine = true;
        adapter.addData(category);
        //endregion
        //region house type
        houseType = new TitleAndValueItem(getResStrng(R.string.hosting_house_type)
                , BaseData.getHostigHouseText(hosting.getHouse_type())
                , hosting.getHouse_type()
                , null);
        houseType.useBottomLine = true;
        adapter.addData(houseType);
        //endregion
        //region room type
        roomType = new TitleAndValueItem(getResStrng(R.string.hosting_room_type)
                , BaseData.getHostigRoomText(hosting.getRoom_type())
                , hosting.getRoom_type()
                , null);
        roomType.useBottomLine = true;
        adapter.addData(roomType);
        //endregion
        //region meal type
        mealType = new TitleAndValueItem(getResStrng(R.string.hosting_meal_type)
                , BaseData.getHostigMealText(hosting.getMeal_type())
                , hosting.getMeal_type()
                , null);
        mealType.useBottomLine = true;
        adapter.addData(mealType);
        //endregion
        //region internet type
        internet = new TitleAndValueItem(getResStrng(R.string.hosting_internet)
                , BaseData.getHostigInternetText(hosting.getInternet())
                , hosting.getInternet()
                , null);
        internet.useBottomLine = true;
        adapter.addData(internet);
        //endregion
        //region smoking
        smoking = new TitleAndCheckItem(getResStrng(R.string.hosting_smoking), hosting.isSmoking());
        smoking.useBottomLine = true;
        adapter.addData(smoking);
        //endregion
        //region pet
        pet = new TitleAndCheckItem(getResStrng(R.string.hosting_pet), hosting.isPet());
        pet.useBottomLine = true;
        adapter.addData(pet);
        //endregion
        //region rules
        rules = new TextContentItem(getResStrng(R.string.hosting_rules)
                , hosting.getRules()
                , null);
        rules.useBottomLine = true;
        adapter.addData(rules);
        //endregion
        //region language type
        List<IDetailItem> langItems = new ArrayList<>();
        for(String lang : hosting.getLanguage()){
            TitleAndValueItem langItem = new TitleAndValueItem();
            langItem.padding.setRight(10);
            langItem.value = BaseData.getLanguageText(lang);
            langItem.valueCode = lang;
            langItems.add(langItem);
        }
        language = new RecyclerItem(getResStrng(R.string.hosting_language), langItems);
        language.setOnAddClickListener(null);
        language.useBottomLine = true;
        adapter.addData(language);
        //endregion
        //region capacity
        capacity = new TitleAndValueItem(getResStrng(R.string.hosting_capacity)
                , hosting.getCapacity()
                , null);
        capacity.useBottomLine = true;
        adapter.addData(capacity);
        //endregion
        //region min stay
        min_stay = new TitleAndValueItem(getResStrng(R.string.hosting_min_stay)
                , hosting.getMin_stay()
                , null);
        min_stay.useBottomLine = true;
        adapter.addData(min_stay);
        //endregion
        //region max stay
        max_stay = new TitleAndValueItem(getResStrng(R.string.hosting_max_stay)
                , hosting.getMax_stay()
                , null);
        max_stay.useBottomLine = true;
        adapter.addData(max_stay);
        //endregion
        //region description
        description = new TextContentItem(getResStrng(R.string.hosting_description)
                , hosting.getDescription()
                , null);
        description.useBottomLine = true;
        adapter.addData(description);
        //endregion
        //region to_do
        todo = new TextContentItem(getResStrng(R.string.hosting_to_do)
                , hosting.getTo_do()
                , null);
        todo.useBottomLine = true;
        adapter.addData(todo);
        //endregion
        //region exchange
        exchange = new TitleAndValueItem(getResStrng(R.string.hosting_exchange)
                , hosting.getExchange()
                , null);
        exchange.useBottomLine = true;
        adapter.addData(exchange);
        //endregion
        //region neighborhood
        neighborhood = new TitleAndValueItem(getResStrng(R.string.hosting_neighborhood)
                , hosting.getNeighborhood()
                , null);
        neighborhood.useBottomLine = true;
        adapter.addData(neighborhood);
        //endregion
        //region transportation
        transportation = new TitleAndValueItem(getResStrng(R.string.hosting_transportation)
                , hosting.getTransportation()
                , null);
        transportation.useBottomLine = true;
        adapter.addData(transportation);
        //endregion
        //region location
        locationTitle = new TitleAndValueItem(getResStrng(R.string.hosting_location), "");
        adapter.addData(locationTitle);

        GoogleStaticMap googleStaticMap = new GoogleStaticMap();
        googleStaticMap.setLatlng(Double.parseDouble(hosting.getLat()), Double.parseDouble(hosting.getLon()), Color.RED);
        location = new MapPreviewItem(googleStaticMap, mapPreviewClickEvent);
        adapter.addData(location);
        //endregion

        adapter.refresh();
    }
    private void loadProfileData(String owner){
        if("".equals(owner)) return;

        DomainManager.getProfileApiService().retrieve(DomainManager.getTokenHeader(), owner)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) {
                                Profile ownerProfile = result.getProfile();

                                profile.content = ownerProfile.getFirst_name() + " " + ownerProfile.getLast_name();
                                profile.imageUrl = ownerProfile.getFirstImageUrl();
                                profile.setOnClickListener(item -> {
                                    Intent intent = new Intent(this, UserActivity.class);
                                    intent.putExtra(Constants.EXT_USER_PK, owner);
                                    startActivity(intent);
                                });

                                adapter.refresh(profile);
                            }
                            else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        , error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void loadPhotoData(){
        DomainManager.getHostingPhotoApiService().selects(DomainManager.getTokenHeader(), pk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) loadPhotoData(result.getHostingPhoto());
                            else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        , error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show());
    }
    private void loadPhotoData(List<HostingPhoto> hostingPhotos){
        for(HostingPhoto hostingPhoto : hostingPhotos){
            ThumbnailItem thumbnailItem = new ThumbnailItem(hostingPhoto);

            thumbnailItem.useContent = true;
            thumbnailItem.content = hostingPhoto.getCaption();
            thumbnailItem.useSubContent = true;
            thumbnailItem.subContent = BaseData.getHostigPhotoText(hostingPhoto.getType());
            thumbnailItem.subContentCode = hostingPhoto.getType();

            thumbnailsItem.addThumbnail(thumbnailItem);
        }
    }

    private String getResStrng(int id){
        return ResourceUtil.getString(this, id);
    }

    private IItemClickListener mapPreviewClickEvent = i ->{
        if(i.getDetailItemType() == DetailItemType.MAP_PREVIEW) {
            MapPreviewItem item = (MapPreviewItem) i;
            LatLng latLng = item.googleStaticMap.getLatLng();

            Intent intent = new Intent(this, LocationActivity.class);
            intent.putExtra(Constants.EXT_LAT, latLng.latitude);
            intent.putExtra(Constants.EXT_LNG, latLng.longitude);
            intent.putExtra(Constants.EXT_IS_DETAIL, false);
            startActivity(intent);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        activityResultManager.onActivityResult(requestCode, resultCode, data);
    }
}
