package a.talenting.com.talenting.controller.setting.event;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import a.talenting.com.talenting.common.SharedPreferenceManager;
import a.talenting.com.talenting.controller.common.LocationActivity;
import a.talenting.com.talenting.controller.user.UserActivity;
import a.talenting.com.talenting.custom.adapter.DetailRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.DetailItemType;
import a.talenting.com.talenting.custom.domain.detailItem.IItemClickListener;
import a.talenting.com.talenting.custom.domain.detailItem.IThumbnailPhoto;
import a.talenting.com.talenting.custom.domain.detailItem.MapPreviewItem;
import a.talenting.com.talenting.custom.domain.detailItem.ProfileItem;
import a.talenting.com.talenting.custom.domain.detailItem.TextContentItem;
import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailItem;
import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailsItem;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndValueItem;
import a.talenting.com.talenting.domain.BaseData;
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.domain.event.Author;
import a.talenting.com.talenting.domain.event.Event;
import a.talenting.com.talenting.domain.event.photo.EventPhoto;
import a.talenting.com.talenting.domain.hosting.photo.HostingPhoto;
import a.talenting.com.talenting.domain.profile.Profile;
import a.talenting.com.talenting.util.ResourceUtil;
import a.talenting.com.talenting.util.TempUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SetEventAddActivity extends AppCompatActivity {
    private ActivityResultManager activityResultManager;
    private String pk;
    private Event baseEvent = null;
    private boolean isAddMode = true;
    private boolean isEditMode = false;

    private ConstraintLayout progress;
    private RecyclerView recyclerView;
    private DetailRecyclerViewAdapter adapter;

    private ThumbnailItem primaryPhoto;
    private ThumbnailsItem thumbnailsItem;
    private ProfileItem profile;
    private TextContentItem program;
    private TitleAndValueItem title, maximumParticipant, price, category, notedItem,
                                openingDate, closingDate, eventDate, locationTitle;
    private MapPreviewItem location;

    private List<EventPhoto> deletePhotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_event_add);

        pk = getIntent().getStringExtra(Constants.EXT_EVENT_PK);

        activityResultManager = new ActivityResultManager();

        init();

        if(pk == null || "".equals(pk)) setNewData();
        else loadData();

        initActionBar();
    }

    private void initActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            if(isAddMode) actionBar.setTitle(R.string.event_add_title);
            else actionBar.setTitle(R.string.event_none_title);
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
                addEvent();
                return true;
            case R.id.btnEdit:
                if(isEditMode) updateEvent(item);
                else{
                    setEditMode(true);

                    item.setIcon(R.drawable.save);
                    item.setTitle(getResStrng(R.string.save));
                }
                return true;
            case R.id.btnDelete:
                deleteEvent();
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

        progress = findViewById(R.id.progress);
    }

    private void setEditMode(boolean use){
        isEditMode = use;

        initActionBar();
        setEditModeViews();
    }

    private void setEditModeViews(){
        boolean isEdit = isAddMode || isEditMode;

        thumbnailsItem.isEditMode = isEdit;

        adapter.refresh();
    }

    private void loadData(){
        isAddMode = false;
        DomainManager.getEventApiService().select(DomainManager.getTokenHeader(), pk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) loadData(result.getEvent());
                        }
                        , error -> {}
                );
    }
    private void setNewData(){
        isAddMode = true;

        Event newEvent = new Event();
        Author newAuthor = new Author();
        newAuthor.setPk(SharedPreferenceManager.getInstance().getPk());
        newEvent.setAuthor(newAuthor);

        loadData(newEvent);
        setEditModeViews();
    }
    private void loadData(Event event){
        baseEvent = event;

        //region thumbnails
        thumbnailsItem = new ThumbnailsItem(new ArrayList<>());
        thumbnailsItem.setOnAddClickListener(item -> {
            if(!isAddMode && !isEditMode) return;
            DialogManager.showCameraDialog(this, activityResultManager, value -> {
                ThumbnailItem thumbnailItem = new ThumbnailItem("", value);
                thumbnailsItem.addThumbnail(thumbnailItem);

                adapter.refresh(thumbnailsItem);
            });
        });
        thumbnailsItem.setOnDeleteClickListener(item -> {
            if(!isAddMode && !isEditMode) return;

            ThumbnailItem thumbnailItem = thumbnailsItem.selectedThubnail();
            if(thumbnailItem == null) return;

            IThumbnailPhoto thumbnailPhoto = thumbnailItem.getThumbnailPhoto();
            if(thumbnailPhoto != null && thumbnailPhoto instanceof EventPhoto) deletePhotos.add((EventPhoto) thumbnailPhoto);

            thumbnailsItem.deleteThubnail(thumbnailItem);

            adapter.refresh(thumbnailsItem);
        });
        adapter.addData(thumbnailsItem);

        if(!isAddMode) loadPhotoData();
        //endregion
        //region primary photo
//        primaryPhoto = new ThumbnailItem("", event.getPrimary_photo());
//        primaryPhoto.setOnClickListener(item -> {
//            if(!isAddMode && !isEditMode) return;
//            DialogManager.showCameraDialog(this, activityResultManager, value -> {
//                primaryPhoto.imageUrl = value;
//                adapter.refresh(primaryPhoto);
//            });
//        });
//        adapter.addData(primaryPhoto);
        //endregion
        //region profile
        profile = new ProfileItem("", "");
        profile.useBottomLine = true;
        adapter.addData(profile);

        loadProfileData(event.getAuthor().getPk());
        //endregion
        //region title
        title = new TitleAndValueItem(getResStrng(R.string.event_title)
                , event.getTitle()
                , titleAndValueItemClickEvent);
        title.useBottomLine = true;
        adapter.addData(title);
        //endregion
        //region category type
        category = new TitleAndValueItem(getResStrng(R.string.event_category)
                , BaseData.getCategoryText(event.getEvent_categories())
                , event.getEvent_categories()
                , typeItemClickEvent);
        category.useBottomLine = true;
        adapter.addData(category);
        //endregion
        //region program
        program = new TextContentItem(getResStrng(R.string.event_program)
                , event.getProgram()
                , contentItemClickEvent);
        program.useBottomLine = true;
        adapter.addData(program);
        //endregion
        //region notedItem type
        notedItem = new TitleAndValueItem(getResStrng(R.string.event_noted_item)
                , BaseData.getCategoryText(event.getNoted_item())
                , event.getNoted_item()
                , typeItemClickEvent);
        notedItem.useBottomLine = true;
        adapter.addData(notedItem);
        //endregion
        //region maximumParticipant
        maximumParticipant = new TitleAndValueItem(getResStrng(R.string.event_maximum_participant)
                , event.getMaximum_participant()
                , numTitleAndValueItemClickEvent);
        maximumParticipant.useBottomLine = true;
        adapter.addData(maximumParticipant);
        //endregion
        //region price
        price = new TitleAndValueItem(getResStrng(R.string.event_price)
                , event.getPrice()
                , numTitleAndValueItemClickEvent);
        price.useBottomLine = true;
        adapter.addData(price);
        //endregion
        //region opening date
        openingDate = new TitleAndValueItem(getResStrng(R.string.event_opening_date)
                , event.getOpening_date()
                , calenderClickEvent);
        openingDate.useBottomLine = true;
        adapter.addData(openingDate);
        //endregion
        //region closing date
        closingDate = new TitleAndValueItem(getResStrng(R.string.event_closing_date)
                , event.getClosing_date()
                , calenderClickEvent);
        closingDate.useBottomLine = true;
        adapter.addData(closingDate);
        //endregion
        //region event date
        eventDate = new TitleAndValueItem(getResStrng(R.string.event_event_date)
                , event.getEvent_date()
                , calenderClickEvent);
        eventDate.useBottomLine = true;
        adapter.addData(eventDate);
        //endregion
        //region location
        locationTitle = new TitleAndValueItem(getResStrng(R.string.hosting_location), "");
        adapter.addData(locationTitle);

        GoogleStaticMap googleStaticMap = new GoogleStaticMap();
        googleStaticMap.setAddress(event.getAddress());
        googleStaticMap.setLatlng(Double.parseDouble(event.getLat()), Double.parseDouble(event.getLon()), Color.RED);
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
        DomainManager.getEventPhotoApiService().selects(DomainManager.getTokenHeader(), pk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) loadPhotoData(result.getEvent_photo());
                            else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        , error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show());
    }
    private void loadPhotoData(List<EventPhoto> eventPhotos){
        for(EventPhoto eventPhoto : eventPhotos){
            ThumbnailItem thumbnailItem = new ThumbnailItem(eventPhoto);
            thumbnailsItem.addThumbnail(thumbnailItem);
        }
    }

    private String getResStrng(int id){
        return ResourceUtil.getString(this, id);
    }

    private IItemClickListener calenderClickEvent = i -> {
        if(!isAddMode && !isEditMode) return;

        if (i.getDetailItemType() == DetailItemType.TITLE_AND_VALUE) {
            TitleAndValueItem item = (TitleAndValueItem) i;

            DialogManager.showDateTimePickerDialog(this, item, value -> {
                item.value = value;
                adapter.refresh(item);
            });
        }
    };
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
            if(item == category) data = BaseData.getCategory();
            else if(item == notedItem) data = BaseData.getLanguage();

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
                            item.googleStaticMap.setPlaceId(p.getId());
                            adapter.refresh(item);
                        }
                        , this
                        , latLng);
            }
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

    private void updateEventData(){
        if(baseEvent == null) baseEvent = new Event();

        baseEvent.setTitle(title.value);
        baseEvent.setProgram(program.content);
        baseEvent.setEvent_categories(category.valueCode);
        baseEvent.setPrice(price.value);
        baseEvent.setMaximum_participant(maximumParticipant.value);
        baseEvent.setOpening_date(openingDate.value);
        baseEvent.setClosing_date(closingDate.value);
        baseEvent.setEvent_date(eventDate.value);
        LatLng latLng = location.googleStaticMap.getLatLng();
        baseEvent.setLat(latLng.latitude + "");
        baseEvent.setLon(latLng.longitude + "");
        baseEvent.setAddress(location.googleStaticMap.getAddress());
    }

    private void addEvent(){
        updateEventData();

        if(!checkValidation()) return;

        DomainManager.getEventApiService().insert(DomainManager.getTokenHeader(), baseEvent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) addPhoto(result.getEvent().getId());
                            else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        , error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show());
    }
    private void updateEvent(MenuItem updateItem){
        updateEventData();

        if(!checkValidation()) return;

        DomainManager.getEventApiService().update(DomainManager.getTokenHeader(), pk, baseEvent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) updatePhoto(updateItem);
                            else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        , e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());
    }
    private void deleteEvent(){
        DomainManager.getEventApiService().delete(DomainManager.getTokenHeader(), pk)
                .subscribeOn(Schedulers.io())
                .subscribe(result -> finish()
                        , e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void addPhoto(String pk){
        for(ThumbnailItem item : thumbnailsItem.getThumbnail()) addPhoto(pk, item);

        finish();
    }
    private void addPhoto(String pk, ThumbnailItem thumbnailItem){
        Uri uri = Uri.parse(thumbnailItem.imageUrl);
        File file = TempUtil.createTempImage(this.getContentResolver(), uri);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        RequestBody bEvent = RequestBody.create(MediaType.parse("multipart/form-data"), pk);

        DomainManager.getEventPhotoApiService().insert(DomainManager.getTokenHeader(), pk, body, bEvent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {}
                        , e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show()
                        , () -> editPhotoFinishCheck()
                );
    }
    private void updatePhoto(MenuItem updateItem){
        editPhotoStart(updateItem, thumbnailsItem.getThumbnail().size() + deletePhotos.size());

        //region delete
        deletePhoto();
        //endregion
        //region update/create
        for(ThumbnailItem item : thumbnailsItem.getThumbnail()){
            if(item.getThumbnailPhoto() instanceof HostingPhoto){
                HostingPhoto hostingPhoto = (HostingPhoto) item.getThumbnailPhoto();

                if(!hostingPhoto.getImageUrl().equals(item.imageUrl)){
                    File file = TempUtil.createTempImage(this.getContentResolver(), Uri.parse(item.imageUrl));

                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
                    RequestBody bEvent = RequestBody.create(MediaType.parse("multipart/form-data"), pk);

                    DomainManager.getEventPhotoApiService().update(DomainManager.getTokenHeader(), pk, hostingPhoto.getPk(), body, bEvent)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(result -> {}
                                    , e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show()
                                    , () -> editPhotoFinishCheck()
                            );
                }
                else editPhotoFinishCheck();
            }
            else addPhoto(pk, item);
        }
        //endregion
    }
    private void deletePhoto(){
        for(EventPhoto eventPhoto : deletePhotos) {
            DomainManager.getEventPhotoApiService().delete(DomainManager.getTokenHeader(), pk, eventPhoto.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {}
                            , e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show()
                            , () -> editPhotoFinishCheck());
        }

        deletePhotos.clear();
    }

    private MenuItem updateItem;
    private int uploadCount = -1;
    private void editPhotoStart(MenuItem updateItem, int uploadCount){
        this.updateItem = updateItem;
        this.uploadCount = uploadCount;

        progress.setVisibility(View.VISIBLE);
    }
    private void editPhotoFinishCheck(){
        uploadCount--;

        if(uploadCount == 0){
            setEditMode(false);

            updateItem.setIcon(R.drawable.edit);
            updateItem.setTitle(getResStrng(R.string.edit));

            progress.setVisibility(View.GONE);

            Toast.makeText(this, "SUCCESS!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkValidation(){
        String msg = "";
        if(!checkValidation(baseEvent.getTitle())) msg = getResStrng(R.string.event_title);
        else if(!checkValidation(baseEvent.getEvent_categories())) msg = getResStrng(R.string.event_category);
        else if(!checkValidation(baseEvent.getMaximum_participant())) msg = getResStrng(R.string.event_maximum_participant);
        else if(!checkValidation(baseEvent.getPrice())) msg = getResStrng(R.string.event_price);
        else if(!checkValidation(baseEvent.getOpening_date())) msg = getResStrng(R.string.event_opening_date);
        else if(!checkValidation(baseEvent.getClosing_date())) msg = getResStrng(R.string.event_closing_date);
        else if(!checkValidation(baseEvent.getEvent_date())) msg = getResStrng(R.string.event_event_date);
        else if("0".equals(baseEvent.getLat()) && "0".equals(baseEvent.getLon())) msg = getResStrng(R.string.hosting_location);

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
