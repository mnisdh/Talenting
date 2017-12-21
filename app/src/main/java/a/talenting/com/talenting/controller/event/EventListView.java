package a.talenting.com.talenting.controller.event;

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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.ActivityResultManager;
import a.talenting.com.talenting.common.Constants;
import a.talenting.com.talenting.common.DialogManager;
import a.talenting.com.talenting.common.GooglePlaceApi;
import a.talenting.com.talenting.custom.AddressSearchTextView;
import a.talenting.com.talenting.custom.DetailItemView;
import a.talenting.com.talenting.custom.adapter.ListRecyclerViewAdapter;
import a.talenting.com.talenting.custom.adapter.MultiListRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.DetailItemType;
import a.talenting.com.talenting.custom.domain.detailItem.IDetailItem;
import a.talenting.com.talenting.custom.domain.detailItem.IItemClickListener;
import a.talenting.com.talenting.custom.domain.detailItem.ImageContentItem;
import a.talenting.com.talenting.custom.domain.detailItem.RecyclerItem;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndValueItem;
import a.talenting.com.talenting.domain.BaseData;
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.domain.event.Event;
import a.talenting.com.talenting.domain.event.GetEventList;
import a.talenting.com.talenting.domain.event.photo.EventPhoto;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by daeho on 2017. 12. 5..
 */

public class EventListView extends FrameLayout {
    private Activity activity;
    private ActivityResultManager manager;

    private Place place;
    private String categoryCode = "";

    private RecyclerView recyclerView;
    private ListRecyclerViewAdapter adapter;
    private MultiListRecyclerViewAdapter adapterTemp;
    private AddressSearchTextView tvAddressSearch;
    private TitleAndValueItem category;
    private DetailItemView categoryView;

    public EventListView(Activity activity, ActivityResultManager manager) {
        super(activity);
        this.activity = activity;
        this.manager = manager;

        this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        init();

        setSampleDataTemp();
    }

    private void init(){
        View v = LayoutInflater.from(this.getContext()).inflate(R.layout.view_hosting_list, null, false);
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

        category = new TitleAndValueItem("Category", "", categoryClickEvent);
        category.value = "All";
        category.valueCode = "";

        categoryView = v.findViewById(R.id.categoryView);
        categoryView.setDetailItem(category);
    }

    private IItemClickListener categoryClickEvent = i -> {
        if(i.getDetailItemType() == DetailItemType.TITLE_AND_VALUE){
            TitleAndValueItem item = (TitleAndValueItem) i;
            Map<String, String> data = new LinkedHashMap<>();
            data.put("", "All");
            if(item == category) data.putAll(BaseData.getCategory());

            DialogManager.showTypeListDialog(activity, item.title, data, (String code, String text) ->
            {
                item.value = text;
                item.valueCode = code;

                categoryView.setDetailItem(category);

                categoryCode = code;
                getData();
            });
        }
    };

    public void getData(){
        if(recyclerView.getAdapter() == adapterTemp) recyclerView.setAdapter(adapter);

        if(place == null){
            Observable<GetEventList> getEventListObservable = null;

            if(categoryCode.equals("")){
                getEventListObservable = DomainManager.getEventApiService().selects(DomainManager.getTokenHeader());
            }
            else{
                getEventListObservable = DomainManager.getEventApiService().selectsCategories(DomainManager.getTokenHeader(), categoryCode);
            }

            getEventListObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                if (result.isSuccess()) setData(result.getEvent());
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
                                    Observable<GetEventList> getEventListObservable = null;

                                    if(!categoryCode.equals("")){
                                        getEventListObservable = DomainManager.getEventApiService().selects(DomainManager.getTokenHeader(), r.getResult().getFormatted_address(), categoryCode);
                                    }
                                    else{
                                        getEventListObservable = DomainManager.getEventApiService().selectsAddress(DomainManager.getTokenHeader(), r.getResult().getFormatted_address());
                                    }

                                    getEventListObservable.subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(result -> {
                                                        if (result.isSuccess()) setData(result.getEvent());
                                                        else Toast.makeText(activity, result.getMsg(), Toast.LENGTH_SHORT).show();
                                                    }
                                                    , error -> Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show());
                                }
                            }
                            , error -> {
                            });
        }
    }

    public void setData(List<Event> events){
        adapter.clearData();

        for(Event event : events){
            adapter.addDataAndRefresh(createItem(event));
        }
    }

    private ImageContentItem createItem(Event event){
        ImageContentItem item = new ImageContentItem(false);
        if(event.getPrimary_photo() == null || event.getPrimary_photo().equals("")) setPhoto(item, event.getId());
        else item.imageUrl = event.getPrimary_photo();
        item.title = event.getTitle();
        item.isFavorite = event.isWish();
        item.content = BaseData.getCountryText(event.getCountry()) + " " + event.getCity() + "\n"
                + event.getPrice() + "\n"
                + event.getEvent_date();

        item.setOnClickListener(j -> {
            Intent intent = new Intent(this.getContext(), EventActivity.class);
            intent.putExtra(Constants.EXT_EVENT_PK, event.getId());
            this.getContext().startActivity(intent);
        });
        item.setOnFavoriteClickListener(j -> {
            DomainManager.getEventApiService().wishListToggle(DomainManager.getTokenHeader(), event.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> { },
                            e -> item.isFavorite = !item.isFavorite
                    );
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

                            if(recyclerView.getAdapter() == adapterTemp) adapterTemp.notifyDataSetChanged();
                            else adapter.notifyDataSetChanged();
                        }
                );
    }

    public void setSampleDataTemp() {
        String[] tempAddress = {"USA", "UK", "Australia", "Canada", "France", "Korea"};

        for (String tempAddr : tempAddress) {
            DomainManager.getEventApiService().selectsAddress(DomainManager.getTokenHeader(), tempAddr)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                if (result.isSuccess()) {
                                    List<IDetailItem> items = new ArrayList<>();
                                    for (Event event : result.getEvent()) {
                                        items.add(createItem(event));
                                    }

                                    if(items.size() > 0) adapterTemp.addDataAndRefresh(new RecyclerItem(tempAddr, items));
                                }
                            }
                            , error -> Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }

    public void addData(ImageContentItem item){
        adapter.addDataAndRefresh(item);
    }

    public void addData(List<IDetailItem> items){
        adapter.addDataAndRefresh(items);
    }
}
