package a.talenting.com.talenting.controller.setting.hosting;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.ActivityResultManager;
import a.talenting.com.talenting.common.Constants;
import a.talenting.com.talenting.common.DialogManager;
import a.talenting.com.talenting.common.GooglePlaceApi;
import a.talenting.com.talenting.common.GoogleStaticMap;
import a.talenting.com.talenting.controller.common.LocationActivity;
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
import a.talenting.com.talenting.util.ResourceUtil;
import a.talenting.com.talenting.util.TempUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SetHostingAddActivity extends AppCompatActivity {
    private ActivityResultManager activityResultManager;
    private String pk;
    private Hosting baseHosting = null;
    private boolean isAddMode = true;
    private boolean isEditMode = false;

    private RecyclerView recyclerView;
    private DetailRecyclerViewAdapter adapter;

    private String sampleImage = "https://firebasestorage.googleapis.com/v0/b/locationsharechat.appspot.com/o/profile%2FAvXoH1Ar9PQXDBXYBk6yrUFpfA22.jpg?alt=media&token=c1d5fa82-b535-4d97-af88-75043642f019";

    private ThumbnailsItem thumbnailsItem;
    private ProfileItem profile;
    private TextContentItem summary, rules, description, todo;
    private TitleAndCheckItem smoking, pet;
    private TitleAndValueItem title, category, houseType, roomType, mealType, internet,
                                capacity, min_stay, max_stay, exchange, neighborhood,
                                transportation, locationTitle;
    private MapPreviewItem location;
    private RecyclerItem language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_hosting_add);

        activityResultManager = new ActivityResultManager();

        pk = getIntent().getStringExtra(Constants.EXT_HOSTING_PK);
        isAddMode = ("".equals(pk) || pk == null);

        initActionBar();
        init();

        if(isAddMode) {
            loadData(new Hosting());
            setEditModeViews();
        }
        else loadData();
    }

    private void initActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            if(isAddMode) actionBar.setTitle(R.string.hosting_add_title);
            else actionBar.setTitle(R.string.hosting_none_title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(isAddMode) getMenuInflater().inflate(R.menu.hosting_add, menu);
        else getMenuInflater().inflate(R.menu.hosting_edit, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnSave:
                addHosting();
                return true;
            case R.id.btnEdit:
                if(isEditMode) updateHosting(item);
                else{
                    setEditMode(true);

                    item.setIcon(R.drawable.save);
                    item.setTitle(getResStrng(R.string.save));
                }
                return true;
            case R.id.btnDelete:
                deleteHosting();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new DetailRecyclerViewAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setEditMode(boolean use){
        isEditMode = use;

        initActionBar();
        setEditModeViews();
    }

    private void setEditModeViews(){
        boolean isEdit = isAddMode || isEditMode;

        thumbnailsItem.isEditMode = isEdit;
        language.setUseAddMode(isEdit);
        language.setUseRemoveMode(isEdit);
        smoking.isEditMode = isEdit;
        pet.isEditMode = isEdit;

        adapter.refresh();
    }

    private void loadData(){
        DomainManager.getHostingApiService().select(DomainManager.getTokenHeader(), pk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) loadData(result.getHosting());
                            else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        , error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void loadData(Hosting hosting){
        baseHosting = hosting;

        //region thumbnails
        thumbnailsItem = new ThumbnailsItem(new ArrayList<>());
        thumbnailsItem.setOnAddClickListener(item -> {
            if(!isAddMode && !isEditMode) return;
            DialogManager.showCameraDialog(this, activityResultManager, value -> {
                thumbnailsItem.addThumbnail(new ThumbnailItem("", value));
                adapter.refresh(thumbnailsItem);
            });
        });
        thumbnailsItem.setOnDeleteClickListener(item -> {
            if(!isAddMode && !isEditMode) return;
        });
        adapter.addData(thumbnailsItem);

        if(!isAddMode) loadPhotoData();
        //endregion
        //region profile
        profile = new ProfileItem("Host name", sampleImage);
        profile.useBottomLine = true;
        adapter.addData(profile);
        //endregion
        //region title
        title = new TitleAndValueItem(getResStrng(R.string.hosting_title)
                , hosting.getTitle()
                , titleAndValueItemClickEvent);
        title.useBottomLine = true;
        adapter.addData(title);
        //endregion
        //region summary
        summary = new TextContentItem(getResStrng(R.string.hosting_summary)
                , hosting.getSummary()
                , contentItemClickEvent);
        summary.useBottomLine = true;
        adapter.addData(summary);
        //endregion
        //region category type
        category = new TitleAndValueItem(getResStrng(R.string.hosting_category)
                , BaseData.getHostigCategoryText(hosting.getCategory())
                , hosting.getCategory()
                , typeItemClickEvent);
        category.useBottomLine = true;
        adapter.addData(category);
        //endregion
        //region house type
        houseType = new TitleAndValueItem(getResStrng(R.string.hosting_house_type)
                , BaseData.getHostigHouseText(hosting.getHouse_type())
                , hosting.getHouse_type()
                , typeItemClickEvent);
        houseType.useBottomLine = true;
        adapter.addData(houseType);
        //endregion
        //region room type
        roomType = new TitleAndValueItem(getResStrng(R.string.hosting_room_type)
                , BaseData.getHostigRoomText(hosting.getRoom_type())
                , hosting.getRoom_type()
                , typeItemClickEvent);
        roomType.useBottomLine = true;
        adapter.addData(roomType);
        //endregion
        //region meal type
        mealType = new TitleAndValueItem(getResStrng(R.string.hosting_meal_type)
                , BaseData.getHostigMealText(hosting.getMeal_type())
                , hosting.getMeal_type()
                , typeItemClickEvent);
        mealType.useBottomLine = true;
        adapter.addData(mealType);
        //endregion
        //region internet type
        internet = new TitleAndValueItem(getResStrng(R.string.hosting_internet)
                , BaseData.getHostigInternetText(hosting.getInternet())
                , hosting.getInternet()
                , typeItemClickEvent);
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
                , contentItemClickEvent);
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
        language.setOnAddClickListener(recyclerAddClickEvent);
        language.useBottomLine = true;
        adapter.addData(language);
        //endregion
        //region capacity
        capacity = new TitleAndValueItem(getResStrng(R.string.hosting_capacity)
                , hosting.getCapacity()
                , numTitleAndValueItemClickEvent);
        capacity.useBottomLine = true;
        adapter.addData(capacity);
        //endregion
        //region min stay
        min_stay = new TitleAndValueItem(getResStrng(R.string.hosting_min_stay)
                                        , hosting.getMin_stay()
                                        , numTitleAndValueItemClickEvent);
        min_stay.useBottomLine = true;
        adapter.addData(min_stay);
        //endregion
        //region max stay
        max_stay = new TitleAndValueItem(getResStrng(R.string.hosting_max_stay)
                                        , hosting.getMax_stay()
                                        , numTitleAndValueItemClickEvent);
        max_stay.useBottomLine = true;
        adapter.addData(max_stay);
        //endregion
        //region description
        description = new TextContentItem(getResStrng(R.string.hosting_description)
                , hosting.getDescription()
                , contentItemClickEvent);
        description.useBottomLine = true;
        adapter.addData(description);
        //endregion
        //region todo
        todo = new TextContentItem(getResStrng(R.string.hosting_to_do)
                , hosting.getTo_do()
                , contentItemClickEvent);
        todo.useBottomLine = true;
        adapter.addData(todo);
        //endregion
        //region exchange
        exchange = new TitleAndValueItem(getResStrng(R.string.hosting_exchange)
                , hosting.getExchange()
                , titleAndValueItemClickEvent);
        exchange.useBottomLine = true;
        adapter.addData(exchange);
        //endregion
        //region neighborhood
        neighborhood = new TitleAndValueItem(getResStrng(R.string.hosting_neighborhood)
                , hosting.getNeighborhood()
                , titleAndValueItemClickEvent);
        neighborhood.useBottomLine = true;
        adapter.addData(neighborhood);
        //endregion
        //region transportation
        transportation = new TitleAndValueItem(getResStrng(R.string.hosting_transportation)
                , hosting.getTransportation()
                , titleAndValueItemClickEvent);
        transportation.useBottomLine = true;
        adapter.addData(transportation);
        //endregion
        //region location
        locationTitle = new TitleAndValueItem(getResStrng(R.string.hosting_location), "");
        adapter.addData(locationTitle);

        GoogleStaticMap googleStaticMap = new GoogleStaticMap();
        googleStaticMap.setLatlng(Double.parseDouble(hosting.getMax_lat()), Double.parseDouble(hosting.getMin_lon()), Color.RED);
        location = new MapPreviewItem(googleStaticMap, mapPreviewClickEvent);
        adapter.addData(location);
        //endregion

        adapter.refresh();
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
            thumbnailsItem.addThumbnail(new ThumbnailItem(hostingPhoto));
        }
    }

    private String getResStrng(int id){
        return ResourceUtil.getString(this, id);
    }

    private IItemClickListener titleAndValueItemClickEvent = i -> {
        if(!isAddMode && !isEditMode) return;

        if(i.getDetailItemType() == DetailItemType.TITLE_AND_VALUE) {
            TitleAndValueItem item = (TitleAndValueItem) i;

            DialogManager.showTextDialog(this, item, value -> {
                item.value = value;
                adapter.refresh(item);
            });
        }
    };
    private IItemClickListener contentItemClickEvent = i -> {
        if(!isAddMode && !isEditMode) return;

        if(i.getDetailItemType() == DetailItemType.TEXT_CONTENT) {
            TextContentItem item = (TextContentItem) i;

            DialogManager.showMultiLineTextDialog(this, item.title, item, value -> {
                item.content = value;
                adapter.refresh(item);
            });
        }
    };
    private IItemClickListener typeItemClickEvent = i -> {
        if(!isAddMode && !isEditMode) return;

        if(i.getDetailItemType() == DetailItemType.TITLE_AND_VALUE){
            TitleAndValueItem item = (TitleAndValueItem) i;
            Map<String, String> data = new LinkedHashMap<>();
            if(item == category) data = BaseData.getHostingCategory();
            else if(item == houseType) data = BaseData.getHostingHouseType();
            else if(item == roomType) data = BaseData.getHostingRoomType();
            else if(item == mealType) data = BaseData.getHostingMealType();
            else if(item == internet) data = BaseData.getHostingInternetType();

            DialogManager.showTypeListDialog(this, item.title, data, (String code, String text) ->
                {
                    item.value = text;
                    item.valueCode = code;
                    adapter.refresh(item);
                });
        }
    };
    private IItemClickListener mapPreviewClickEvent = i ->{
        if(i.getDetailItemType() == DetailItemType.MAP_PREVIEW) {
            MapPreviewItem item = (MapPreviewItem) i;
            LatLng latLng = item.googleStaticMap.getLatLng();

            if (!isAddMode && !isEditMode) {
                Intent intent = new Intent(this, LocationActivity.class);
                intent.putExtra(Constants.EXT_LAT, latLng.latitude);
                intent.putExtra(Constants.EXT_LNG, latLng.longitude);
                intent.putExtra(Constants.EXT_IS_DETAIL, false);
                startActivity(intent);
            } else
                {
                GooglePlaceApi.startPlaceSelectMap(activityResultManager
                        , p -> {
                            item.googleStaticMap.setLatlng(p.getLatLng(), Color.RED);
                            adapter.refresh(item);
                        }
                        , this
                        , latLng);
            }
        }
    };
    private IItemClickListener recyclerAddClickEvent = i -> {
        if(!isAddMode && !isEditMode) return;

        if(i.getDetailItemType() == DetailItemType.RECYCLER){
            RecyclerItem item = (RecyclerItem) i;
            Map<String, String> data = new LinkedHashMap<>();
            if(item == language) data = BaseData.getLanguage();

            DialogManager.showTypeListDialog(this, item.title, data, (String code, String text) ->
            {
                TitleAndValueItem langItem = new TitleAndValueItem();
                langItem.value = text;
                langItem.valueCode = code;
                item.addItem(langItem);

                adapter.refresh(item);
            });
        }
    };
    private IItemClickListener numTitleAndValueItemClickEvent = i -> {
        if(!isAddMode && !isEditMode) return;

        if(i.getDetailItemType() == DetailItemType.TITLE_AND_VALUE) {
            TitleAndValueItem item = (TitleAndValueItem) i;

            DialogManager.showNumTextDialog(this, item, value -> {
                item.value = value;
                adapter.refresh(item);
            });
        }
    };

    private void updateHostingData(){
        if(baseHosting == null) baseHosting = new Hosting();

        baseHosting.setTitle(title.value);
        baseHosting.setSummary(summary.content);
        baseHosting.setCategory(category.valueCode);
        baseHosting.setHouse_type(houseType.valueCode);
        baseHosting.setRoom_type(roomType.valueCode);
        baseHosting.setMeal_type(mealType.valueCode);
        baseHosting.setInternet(internet.valueCode);
        baseHosting.setSmoking(smoking.isCheck + "");
        baseHosting.setPet(pet.isCheck + "");
        baseHosting.setRules(rules.content);
        List<String> lang = new ArrayList<>();
        for(IDetailItem item : language.getItems()){
            if(item.getDetailItemType() == DetailItemType.TITLE_AND_VALUE){
                lang.add(((TitleAndValueItem)item).valueCode);
            }
        }
        baseHosting.setLanguage(lang);
        baseHosting.setCapacity(capacity.value);
        baseHosting.setMin_stay(min_stay.value);
        baseHosting.setMax_stay(max_stay.value);
        baseHosting.setDescription(description.content);
        baseHosting.setTo_do(todo.content);
        baseHosting.setExchange(exchange.value);
        baseHosting.setNeighborhood(neighborhood.value);
        baseHosting.setTransportation(transportation.value);
        LatLng latLng = location.googleStaticMap.getLatLng();
        baseHosting.setMin_lat(latLng.latitude + "");
        baseHosting.setMax_lat(latLng.latitude + "");
        baseHosting.setMin_lon(latLng.longitude + "");
        baseHosting.setMax_lon(latLng.longitude + "");
    }

    private void addHosting(){
        updateHostingData();

        if(!checkValidation()) return;

        DomainManager.getHostingApiService().insert(DomainManager.getTokenHeader(), baseHosting)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.isSuccess()) addPhoto(result.getHosting().getPk());
                    else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                    , error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void updateHosting(MenuItem updateItem){
        updateHostingData();

        if(!checkValidation()) return;

        DomainManager.getHostingApiService().update(DomainManager.getTokenHeader(), pk, baseHosting)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) updatePhoto(updateItem);
                            else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        , e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void deleteHosting(){
        DomainManager.getHostingApiService().delete(DomainManager.getTokenHeader(), pk)
                .subscribeOn(Schedulers.io())
                .subscribe(result -> finish()
                        , e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void addPhoto(String pk){
        int count = 1;
        for(ThumbnailItem item : thumbnailsItem.getThumbnail()){
            HostingPhoto hostingPhoto = new HostingPhoto();
            hostingPhoto.setHosting_image(item.imageUrl);
            hostingPhoto.setCaption("");
            hostingPhoto.setType(count + "");

            Uri uri = Uri.parse(item.imageUrl);
//            String path = getRealPathFromURI(uri);
            File file = TempUtil.createTempImage(this.getContentResolver(), uri);
            //File file = new File(uri.getPath());

            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("hosting_image", file.getName(), requestFile);
            RequestBody caption = RequestBody.create(MediaType.parse("multipart/form-data"), "");
            RequestBody type = RequestBody.create(MediaType.parse("multipart/form-data"), "1");

            DomainManager.getHostingPhotoApiService().insert(DomainManager.getTokenHeader(), pk, body, caption, type)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                if (result.isSuccess()) ;
                                else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                            , e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());
            count++;
        }

        finish();
    }

    private void updatePhoto(MenuItem updateItem){

        setEditMode(false);

        updateItem.setIcon(R.drawable.edit);
        updateItem.setTitle(getResStrng(R.string.edit));

        Toast.makeText(this, "SUCCESS!", Toast.LENGTH_SHORT).show();
    }

    private boolean checkValidation(){
        String msg = "";
        if(!checkValidation(baseHosting.getTitle())) msg = getResStrng(R.string.hosting_title);
        else if(!checkValidation(baseHosting.getCategory())) msg = getResStrng(R.string.hosting_category);
        else if(!checkValidation(baseHosting.getSummary())) msg = getResStrng(R.string.hosting_summary);
        else if(!checkValidation(baseHosting.getHouse_type())) msg = getResStrng(R.string.hosting_house_type);
        else if(!checkValidation(baseHosting.getRoom_type())) msg = getResStrng(R.string.hosting_room_type);
        else if(!checkValidation(baseHosting.getMeal_type())) msg = getResStrng(R.string.hosting_meal_type);
        else if(baseHosting.getLanguage().size() == 0) msg = getResStrng(R.string.hosting_language);
        else if("".equals(baseHosting.getMax_lat()) && "".equals(baseHosting.getMax_lon())) msg = getResStrng(R.string.hosting_location);

        if(msg.equals("")) return true;
        else{
            Toast.makeText(this, msg + " check please", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    private boolean checkValidation(String str){
        return str != null && !str.equals("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        activityResultManager.onActivityResult(requestCode, resultCode, data);
    }
}
