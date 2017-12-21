package a.talenting.com.talenting.controller.event;

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
import a.talenting.com.talenting.common.SharedPreferenceManager;
import a.talenting.com.talenting.controller.common.LocationActivity;
import a.talenting.com.talenting.controller.user.UserActivity;
import a.talenting.com.talenting.custom.adapter.DetailRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.DetailItemType;
import a.talenting.com.talenting.custom.domain.detailItem.IItemClickListener;
import a.talenting.com.talenting.custom.domain.detailItem.MapPreviewItem;
import a.talenting.com.talenting.custom.domain.detailItem.ProfileItem;
import a.talenting.com.talenting.custom.domain.detailItem.TextContentItem;
import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailItem;
import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailsItem;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndToogleButtonItem;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndValueItem;
import a.talenting.com.talenting.domain.BaseData;
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.domain.event.Event;
import a.talenting.com.talenting.domain.event.photo.EventPhoto;
import a.talenting.com.talenting.domain.profile.Profile;
import a.talenting.com.talenting.util.ResourceUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EventActivity extends AppCompatActivity {
    private ActivityResultManager activityResultManager;
    private String pk;
    private Event baseEvent = null;

    private ConstraintLayout progress;
    private RecyclerView recyclerView;
    private DetailRecyclerViewAdapter adapter;

    private ThumbnailsItem thumbnailsItem;
    private TitleAndToogleButtonItem participate;
    private ProfileItem profile;
    private TextContentItem program;
    private TitleAndValueItem title, maximumParticipant, price, category, notedItem,
            openingDate, closingDate, eventDate, locationTitle, participant;
    private MapPreviewItem location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        pk = getIntent().getStringExtra(Constants.EXT_EVENT_PK);

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
        DomainManager.getEventApiService().select(DomainManager.getTokenHeader(), pk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) loadData(result.getEvent());
                        }
                        , error -> {}
                );
    }
    private void loadData(Event event){
        baseEvent = event;

        //region thumbnails
        thumbnailsItem = new ThumbnailsItem(new ArrayList<>());
        thumbnailsItem.useFavorite = true;
        thumbnailsItem.isFavorite = event.isWish();
        thumbnailsItem.setOnFavoriteClickListener(i -> {
            DomainManager.getEventApiService().wishListToggle(DomainManager.getTokenHeader(), event.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> { },
                            e -> thumbnailsItem.isFavorite = !thumbnailsItem.isFavorite
                    );
        });
        adapter.addData(thumbnailsItem);

        loadPhotoData();
        //endregion
        //region participate
        participate = new TitleAndToogleButtonItem(
                getResStrng(R.string.event_participant),
                getResStrng(R.string.event_participation),
                getResStrng(R.string.event_nonparticipation),
                i -> {
                    DomainManager.getEventApiService().participate(DomainManager.getTokenHeader(), event.getId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(result -> { },
                                    e -> participate.isButtonChecked = !participate.isButtonChecked
                            );
                }
        );
        participate.isButtonChecked = true;
        participate.isButtonChecked = event.isParticipation(SharedPreferenceManager.getInstance().getPk());
        participate.useBottomLine = true;
        adapter.addData(participate);
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
                , null);
        title.useBottomLine = true;
        adapter.addData(title);
        //endregion
        //region category type
        category = new TitleAndValueItem(getResStrng(R.string.event_category)
                , BaseData.getCategoryText(event.getEvent_categories())
                , event.getEvent_categories()
                , null);
        category.useBottomLine = true;
        adapter.addData(category);
        //endregion
        //region program
        program = new TextContentItem(getResStrng(R.string.event_program)
                , event.getProgram()
                , null);
        program.useBottomLine = true;
        adapter.addData(program);
        //endregion
        //region notedItem type
        notedItem = new TitleAndValueItem(getResStrng(R.string.event_noted_item)
                , BaseData.getCategoryText(event.getNoted_item())
                , event.getNoted_item()
                , null);
        notedItem.useBottomLine = true;
        adapter.addData(notedItem);
        //endregion
        //region maximumParticipant
        maximumParticipant = new TitleAndValueItem(getResStrng(R.string.event_maximum_participant)
                , event.getMaximum_participant()
                , null);
        maximumParticipant.useBottomLine = true;
        adapter.addData(maximumParticipant);
        //endregion
        //region participant
        participant = new TitleAndValueItem(getResStrng(R.string.event_participant_counter)
                , event.getParticipants_counter()
                , null);
        participant.useBottomLine = true;
        adapter.addData(participant);
        //endregion
        //region price
        price = new TitleAndValueItem(getResStrng(R.string.event_price)
                , event.getPrice()
                , null);
        price.useBottomLine = true;
        adapter.addData(price);
        //endregion
        //region opening date
        openingDate = new TitleAndValueItem(getResStrng(R.string.event_opening_date)
                , event.getOpening_date()
                , null);
        openingDate.useBottomLine = true;
        adapter.addData(openingDate);
        //endregion
        //region closing date
        closingDate = new TitleAndValueItem(getResStrng(R.string.event_closing_date)
                , event.getClosing_date()
                , null);
        closingDate.useBottomLine = true;
        adapter.addData(closingDate);
        //endregion
        //region event date
        eventDate = new TitleAndValueItem(getResStrng(R.string.event_event_date)
                , event.getEvent_date()
                , null);
        eventDate.useBottomLine = true;
        adapter.addData(eventDate);
        //endregion
        //region location
        locationTitle = new TitleAndValueItem(getResStrng(R.string.hosting_location), "");
        adapter.addData(locationTitle);

        GoogleStaticMap googleStaticMap = new GoogleStaticMap();
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
