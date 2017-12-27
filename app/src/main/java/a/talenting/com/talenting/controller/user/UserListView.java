package a.talenting.com.talenting.controller.user;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.ActivityResultManager;
import a.talenting.com.talenting.common.Constants;
import a.talenting.com.talenting.common.GooglePlaceApi;
import a.talenting.com.talenting.custom.AddressSearchTextView;
import a.talenting.com.talenting.custom.adapter.ListRecyclerViewAdapter;
import a.talenting.com.talenting.custom.adapter.MultiListRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.IDetailItem;
import a.talenting.com.talenting.custom.domain.detailItem.ImageContentItem;
import a.talenting.com.talenting.custom.domain.detailItem.RecyclerItem;
import a.talenting.com.talenting.domain.BaseData;
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.domain.profile.Profile;
import a.talenting.com.talenting.domain.profile.trip.MyTrip;
import a.talenting.com.talenting.domain.profile.trip.MyTripResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user on 2017-12-06.
 */

public class UserListView extends FrameLayout {
    private Activity activity;
    private ActivityResultManager manager;
    private Place place;

    private RecyclerView recyclerView;
    private ListRecyclerViewAdapter adapter;
    private MultiListRecyclerViewAdapter adapterTemp;
    private AddressSearchTextView tvAddressSearch;

    public UserListView(Activity activity, ActivityResultManager manager) {
        super(activity);
        this.activity = activity;
        this.manager = manager;

        this.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        init();

        setSampleDataTemp();
    }

    private void init(){
        View v = LayoutInflater.from(this.getContext()).inflate(R.layout.view_user_list, null, false);
        this.addView(v);

        recyclerView = v.findViewById(R.id.recyclerView);
        adapter = new ListRecyclerViewAdapter(true);
        adapterTemp = new MultiListRecyclerViewAdapter();

        recyclerView.setAdapter(adapterTemp);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        tvAddressSearch = v.findViewById(R.id.tvAddressSearch);
        tvAddressSearch.setParentActivity(activity, manager);
        tvAddressSearch.setOnPlaceChangedListener(place -> {
            this.place = place;
            getData();
        });
    }

    public void getData(){
        if(recyclerView.getAdapter() == adapterTemp) recyclerView.setAdapter(adapter);
        if(place == null){
            Observable<MyTripResponse> myTripResponseObservable = null;
            myTripResponseObservable = DomainManager.getMyTripApiService().getEveryList(DomainManager.getTokenHeader());
            myTripResponseObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                if (result.isSuccess()) setData(result.getMytrip());
                                else Toast.makeText(activity, result.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                            , error -> Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show());
        }
        else{
            DomainManager.getPlaceApiService().select(place.getId(), "en", GooglePlaceApi.DETAIL_KEY)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(r -> {
                                if (r.isSuccess()) {
                                    Observable<MyTripResponse> myTripResponseObservable = null;
                                    myTripResponseObservable = DomainManager.getMyTripApiService().search(DomainManager.getTokenHeader(), r.getResult().getFormatted_address());
                                    myTripResponseObservable.subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(result -> {
                                                        if (result.isSuccess()) setData(result.getMytrip());
                                                        else Toast.makeText(activity, result.getMsg(), Toast.LENGTH_SHORT).show();
                                                    }
                                                    , error -> Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show());
                                }
                            }
                            , error -> {
                            });
        }
    }

    public void setData(List<MyTrip> myTrips){
        adapter.clearData();

        for(MyTrip myTrip : myTrips){
            adapter.addDataAndRefresh(createItem(myTrip, true));
        }
    }

    private ImageContentItem createItem(MyTrip myTrip, boolean useMatchParentWidth){
        ImageContentItem item = new ImageContentItem(false, useMatchParentWidth);
        item.useFavorite = false;

        DomainManager.getProfileApiService().retrieve(DomainManager.getTokenHeader(), myTrip.getUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) {
                                Profile profile = result.getProfile();
                                item.imageUrl = profile.getImages().get(0).getImageUrl();
                                item.title = profile.getFirst_name() + profile.getLast_name();
                                item.isFavorite = profile.isWish();

                                String content="";
                                for(String talent : profile.getTalent_category()){
                                    content+=BaseData.getCategoryText(talent) + " ";
                                }
                                content+="\n";
                                for(String language : profile.getAvailable_languages()){
                                    content+=BaseData.getLanguageText(language)+ " ";
                                }
                                item.content = content;
                            }
                            else Toast.makeText(activity, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        , e -> Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show());

        item.setOnClickListener(j -> {
            Intent intent = new Intent(this.getContext(), UserActivity.class);
            intent.putExtra(Constants.EXT_USER_PK, myTrip.getUser());
            intent.putExtra(Constants.EXT_USER_TRIP_ADDR, myTrip.getDestination());
            this.getContext().startActivity(intent);
        });
//        item.setOnFavoriteClickListener(j ->{
//            DomainManager.getProfileApiService().wishListToggle(DomainManager.getTokenHeader(), myTrip.getUser())
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(result -> { },
//                            e -> item.isFavorite = !item.isFavorite
//                    );
//        });

        return item;
    }

    public void setSampleDataTemp() {
        String[] tempAddress = {"USA", "UK", "Australia", "Canada", "France", "Korea"};

        for (String tempAddr : tempAddress) {
            DomainManager.getMyTripApiService().search(DomainManager.getTokenHeader(), tempAddr)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                if (result.isSuccess()) {
                                    List<IDetailItem> items = new ArrayList<>();

                                    for (MyTrip myTrip : result.getMytrip()) {
                                        items.add(createItem(myTrip, false));
                                    }

                                    if(items.size() > 0) adapterTemp.addDataAndRefresh(new RecyclerItem(tempAddr, items));
                                }
                            }
                            , error -> Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }


}
