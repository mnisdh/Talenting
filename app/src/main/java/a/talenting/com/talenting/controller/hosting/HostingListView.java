package a.talenting.com.talenting.controller.hosting;

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
import a.talenting.com.talenting.domain.hosting.GetHostingList;
import a.talenting.com.talenting.domain.hosting.Hosting;
import a.talenting.com.talenting.domain.hosting.photo.HostingPhoto;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by daeho on 2017. 12. 5..
 */

public class HostingListView extends FrameLayout {
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

    public HostingListView(Activity activity, ActivityResultManager manager) {
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
            Observable<GetHostingList> getHostingListObservable = null;

            if(categoryCode.equals("")){
                getHostingListObservable = DomainManager.getHostingApiService().selects(DomainManager.getTokenHeader());
            }
            else{
                getHostingListObservable = DomainManager.getHostingApiService().selectsCategories(DomainManager.getTokenHeader(), categoryCode);
            }

            getHostingListObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                if (result.isSuccess()) setData(result.getHosting());
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
                                    Observable<GetHostingList> getHostingListObservable = null;

                                    if(!categoryCode.equals("")){
                                        getHostingListObservable = DomainManager.getHostingApiService().selects(DomainManager.getTokenHeader(), r.getResult().getFormatted_address(), categoryCode);
                                    }
                                    else{
                                        getHostingListObservable = DomainManager.getHostingApiService().selectsAddress(DomainManager.getTokenHeader(), r.getResult().getFormatted_address());
                                    }

                                    getHostingListObservable.subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(result -> {
                                                        if (result.isSuccess()) setData(result.getHosting());
                                                        else Toast.makeText(activity, result.getMsg(), Toast.LENGTH_SHORT).show();
                                                    }
                                                    , error -> Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show());
                                }
                            }
                            , error -> {
                            });
        }
    }

    public void setData(List<Hosting> hostings){
        adapter.clearData();

        for(Hosting hosting : hostings){
            adapter.addDataAndRefresh(createItem(hosting));
        }
    }

    private ImageContentItem createItem(Hosting hosting){
        ImageContentItem item = new ImageContentItem(false);
        if(hosting.getPrimary_photo() == null || hosting.getPrimary_photo().equals("")) setPhoto(item, hosting.getOwner());
        else item.imageUrl = hosting.getPrimary_photo();
        item.title = hosting.getTitle();
        item.isFavorite = hosting.isWish();
        item.content = BaseData.getCategoryText(hosting.getCategory()) + "\n"
                + hosting.getSummary();

        item.setOnClickListener(j -> {
            Intent intent = new Intent(this.getContext(), HostingActivity.class);
            intent.putExtra(Constants.EXT_HOSTING_PK, hosting.getOwner());
            this.getContext().startActivity(intent);
        });
        item.setOnFavoriteClickListener(j ->{
            DomainManager.getHostingApiService().wishListToggle(DomainManager.getTokenHeader(), hosting.getOwner())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> { },
                            e -> item.isFavorite = !item.isFavorite
                    );
        });

        return item;
    }

    private void setPhoto(ImageContentItem item, String pk){
        DomainManager.getHostingPhotoApiService().selects(DomainManager.getTokenHeader(), pk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            int first = 999;
                            String image = "";
                            for (HostingPhoto photo : result.getHostingPhoto()) {
                                int photoId = Integer.parseInt(photo.getPk());
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
            DomainManager.getHostingApiService().selectsAddress(DomainManager.getTokenHeader(), tempAddr)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                if (result.isSuccess()) {
                                    List<IDetailItem> items = new ArrayList<>();

                                    for (Hosting hosting : result.getHosting()) {
                                        items.add(createItem(hosting));
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
