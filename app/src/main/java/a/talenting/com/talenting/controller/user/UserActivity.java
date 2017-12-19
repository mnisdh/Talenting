package a.talenting.com.talenting.controller.user;

/**
 * Created by user on 2017-12-13.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.ActivityResultManager;
import a.talenting.com.talenting.common.Constants;
import a.talenting.com.talenting.custom.adapter.DetailRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.IDetailItem;
import a.talenting.com.talenting.custom.domain.detailItem.RecyclerItem;
import a.talenting.com.talenting.custom.domain.detailItem.TextContentItem;
import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailItem;
import a.talenting.com.talenting.custom.domain.detailItem.ThumbnailsItem;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndValueItem;
import a.talenting.com.talenting.domain.BaseData;
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.domain.profile.Profile;
import a.talenting.com.talenting.domain.profile.photo.ProfileImage;
import a.talenting.com.talenting.util.ResourceUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserActivity extends AppCompatActivity {
    private ActivityResultManager activityResultManager;
    private String pk;
    private Profile baseProfile = null;

    private RecyclerView recyclerView;
    private DetailRecyclerViewAdapter adapter;

    private ThumbnailsItem thumbnailsItem;
    private TextContentItem self_intro, talent_intro;
    private TitleAndValueItem first_name, last_name, city, occupation, gender, country, birth;
    private RecyclerItem available_languages, talent_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        pk = getIntent().getStringExtra(Constants.EXT_USER_PK);
        if(pk == null || "".equals(pk)) finish();

        activityResultManager = new ActivityResultManager();

        init();
        loadData();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new DetailRecyclerViewAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadData() {
        DomainManager.getProfileApiService().retrieve(DomainManager.getTokenHeader(), pk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) loadData(result.getProfile());
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
    private void loadData(Profile profile) {
        baseProfile = profile;

        //region thumbnails
        thumbnailsItem = new ThumbnailsItem(new ArrayList<>());
        for (ProfileImage image : profile.getImages()) {
            thumbnailsItem.addThumbnail(new ThumbnailItem(image));
        }
        adapter.refresh(thumbnailsItem);

        adapter.addData(thumbnailsItem);

        //endregion
        //region first_name
        first_name = new TitleAndValueItem(getResStrng(R.string.profile_firstname)
                , profile.getFirst_name());
        first_name.useBottomLine = true;
        adapter.addData(first_name);
        //endregion
        //region last_name
        last_name = new TitleAndValueItem(getResStrng(R.string.profile_lastname)
                , profile.getLast_name());
        last_name.useBottomLine = true;
        adapter.addData(last_name);
        //endregion
        //region birth
        birth = new TitleAndValueItem(getResStrng(R.string.profile_birth)
                , profile.getBirth()
                , null);
        birth.useBottomLine = true;
        adapter.addData(birth);
        //endregion
        //region gender
        gender = new TitleAndValueItem(getResStrng(R.string.profile_gender)
                , BaseData.getProfileGenderText(profile.getGender())
                , profile.getGender()
                , null);
        gender.useBottomLine = true;
        adapter.addData(gender);
        //endregion
        //region self_intro
        self_intro = new TextContentItem(getResStrng(R.string.profile_selfintro)
                , profile.getSelf_intro()
                , null);
        self_intro.useBottomLine = true;
        adapter.addData(self_intro);
        //endregion
        //region talent type
        List<IDetailItem> talentItems = new ArrayList<>();
        for (String talent : profile.getTalent_category()) {
            TitleAndValueItem talentItem = new TitleAndValueItem();
            talentItem.padding.setRight(10);
            talentItem.value = BaseData.getCategoryText(talent);
            talentItem.valueCode = talent;
            talentItems.add(talentItem);
        }
        talent_category = new RecyclerItem(getResStrng(R.string.profile_talent_category), talentItems);
        talent_category.useBottomLine = true;
        adapter.addData(talent_category);
        //endregion
        //region talent_intro
        talent_intro = new TextContentItem(getResStrng(R.string.profile_talentintro)
                , profile.getSelf_intro()
                , null);
        talent_intro.useBottomLine = true;
        adapter.addData(talent_intro);
        //endregion
        //region available_languages
        List<IDetailItem> langItems = new ArrayList<>();
        for (String lang : profile.getAvailable_languages()) {
            TitleAndValueItem langItem = new TitleAndValueItem();
            langItem.padding.setRight(10);
            langItem.value = BaseData.getLanguageText(lang);
            langItem.valueCode = lang;
            langItems.add(langItem);
        }
        available_languages = new RecyclerItem(getResStrng(R.string.profile_available_language), langItems);
        available_languages.useBottomLine = true;
        adapter.addData(available_languages);
        //endregion
        //region country
        country = new TitleAndValueItem(getResStrng(R.string.profile_country)
                , BaseData.getLanguageText(profile.getCountry())
                , profile.getCountry()
                , null);
        country.useBottomLine = true;
        adapter.addData(country);
        //endregion
        //region city
        city = new TitleAndValueItem(getResStrng(R.string.profile_city)
                , profile.getCity()
                , null);
        city.useBottomLine = true;
        adapter.addData(city);
        //endregion
        //region occupation
        occupation = new TitleAndValueItem(getResStrng(R.string.profile_occupation)
                , profile.getFirst_name()
                , null);
        occupation.useBottomLine = true;
        adapter.addData(occupation);
        //endregion

        adapter.refresh();
    }

    private String getResStrng(int id) {
        return ResourceUtil.getString(this, id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        activityResultManager.onActivityResult(requestCode, resultCode, data);
    }
}

