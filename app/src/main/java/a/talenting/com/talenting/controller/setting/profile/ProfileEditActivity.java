package a.talenting.com.talenting.controller.setting.profile;

/**
 * Created by user on 2017-12-13.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.ActivityResultManager;
import a.talenting.com.talenting.common.DialogManager;
import a.talenting.com.talenting.common.SharedPreferenceManager;
import a.talenting.com.talenting.custom.adapter.DetailRecyclerViewAdapter;
import a.talenting.com.talenting.custom.domain.detailItem.DetailItemType;
import a.talenting.com.talenting.custom.domain.detailItem.IDetailItem;
import a.talenting.com.talenting.custom.domain.detailItem.IItemClickListener;
import a.talenting.com.talenting.custom.domain.detailItem.ProfileItem;
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

public class ProfileEditActivity extends AppCompatActivity {
    private ActivityResultManager activityResultManager;
    private Profile baseProfile = null;
    private boolean isEditMode = false;

    private RecyclerView recyclerView;
    private DetailRecyclerViewAdapter adapter;

    private String sampleImage = "https://firebasestorage.googleapis.com/v0/b/locationsharechat.appspot.com/o/profile%2FAvXoH1Ar9PQXDBXYBk6yrUFpfA22.jpg?alt=media&token=c1d5fa82-b535-4d97-af88-75043642f019";

    private ThumbnailsItem thumbnailsItem;
    private ProfileItem profileItem;
    private TextContentItem self_intro, talent_intro;
    private TitleAndValueItem first_name, last_name, city, occupation, gender,country;
    private RecyclerItem available_languages,talent_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile_edit);

        activityResultManager = new ActivityResultManager();

        initActionBar();
        init();
        loadData();
    }

    private void initActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(R.string.profile);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(isEditMode) getMenuInflater().inflate(R.menu.hosting_add, menu);
        else getMenuInflater().inflate(R.menu.hosting_edit, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnSave:
                addProfile();
                return true;
            case R.id.btnEdit:
                if(isEditMode) updateProfile(item);
                else{
                    setEditMode(true);

                    item.setIcon(R.drawable.save);
                    item.setTitle(getResStrng(R.string.save));
                }
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
        boolean isEdit = isEditMode;

        thumbnailsItem.isEditMode = isEdit;
        available_languages.setUseAddMode(isEdit);
        available_languages.setUseRemoveMode(isEdit);

        adapter.refresh();
    }

    private void loadData(){
        DomainManager.getProfileApiService().retrieve(DomainManager.getTokenHeader(), SharedPreferenceManager.getInstance().getPk())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) loadData(result.getProfile());
                            else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        , error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void loadData(Profile profile){
        baseProfile = profile;

        //region thumbnails
        thumbnailsItem = new ThumbnailsItem(new ArrayList<>());
        thumbnailsItem.setOnAddClickListener(item -> {
            if(!isEditMode) return;
            DialogManager.showCameraDialog(this, activityResultManager, value -> {
                thumbnailsItem.addThumbnail(new ThumbnailItem("", value));
                adapter.refresh(thumbnailsItem);
            });
        });
        thumbnailsItem.setOnDeleteClickListener(item -> {
            if(!isEditMode) return;
        });
        adapter.addData(thumbnailsItem);
        //endregion
        //region first_name
        profileItem = new ProfileItem("My name", sampleImage);
        profileItem.useBottomLine = true;
        adapter.addData(profileItem);
        //endregion
        //region title
        first_name = new TitleAndValueItem(getResStrng(R.string.profile_firstname)
                , profile.getFirst_name()
                , titleAndValueItemClickEvent);
        first_name.useBottomLine = true;
        adapter.addData(first_name);
        //endregion
        //region last_name
        last_name = new TitleAndValueItem(getResStrng(R.string.profile_lastname)
                , profile.getFirst_name()
                , titleAndValueItemClickEvent);
        last_name.useBottomLine = true;
        adapter.addData(last_name);
        //endregion
        //region gender
        gender = new TitleAndValueItem(getResStrng(R.string.profile_gender)
                , BaseData.getProfileGenderText(profile.getGender())
                , profile.getGender()
                , typeItemClickEvent);
        gender.useBottomLine = true;
        adapter.addData(gender);
        //endregion
        //region self_intro
        self_intro = new TextContentItem(getResStrng(R.string.profile_selfintro)
                , profile.getSelf_intro()
                , contentItemClickEvent);
        self_intro.useBottomLine = true;
        adapter.addData(self_intro);
        //endregion
        //region talent type
        List<IDetailItem> talentItems = new ArrayList<>();
        for(String talent : profile.getTalent_category()){
            TitleAndValueItem talentItem = new TitleAndValueItem();
            talentItem.padding.setRight(10);
            talentItem.value = BaseData.getLanguageText(talent);
            talentItem.valueCode = talent;
            talentItems.add(talentItem);
        }
        talent_category = new RecyclerItem(getResStrng(R.string.profile_talent_category), talentItems);
        talent_category.setOnAddClickListener(recyclerAddClickEvent);
        talent_category.useBottomLine = true;
        adapter.addData(talent_category);
        //endregion
        //region talent_intro
        talent_intro = new TextContentItem(getResStrng(R.string.profile_talentintro)
                , profile.getSelf_intro()
                , contentItemClickEvent);
        talent_intro.useBottomLine = true;
        adapter.addData(talent_intro);
        //endregion
        //region country
        country = new TitleAndValueItem(getResStrng(R.string.profile_country)
                , BaseData.getLanguageText(profile.getCountry())
                , profile.getCountry()
                , typeItemClickEvent);
        country.useBottomLine = true;
        adapter.addData(country);
        //endregion
        //region city
        city = new TitleAndValueItem(getResStrng(R.string.profile_city)
                , profile.getFirst_name()
                , titleAndValueItemClickEvent);
        city.useBottomLine = true;
        adapter.addData(city);
        //endregion
        //region occupation
        occupation = new TitleAndValueItem(getResStrng(R.string.profile_occupation)
                , profile.getFirst_name()
                , titleAndValueItemClickEvent);
        occupation.useBottomLine = true;
        adapter.addData(occupation);
        //endregion
        //region available_languages
        List<IDetailItem> langItems = new ArrayList<>();
        for(String lang : profile.getAvailable_languages()){
            TitleAndValueItem langItem = new TitleAndValueItem();
            langItem.padding.setRight(10);
            langItem.value = BaseData.getLanguageText(lang);
            langItem.valueCode = lang;
            langItems.add(langItem);
        }
        available_languages = new RecyclerItem(getResStrng(R.string.profile_available_language), talentItems);
        available_languages.setOnAddClickListener(recyclerAddClickEvent);
        available_languages.useBottomLine = true;
        adapter.addData(available_languages);
        //endregion

        adapter.refresh();
    }

    private String getResStrng(int id){
        return ResourceUtil.getString(this, id);
    }

    private IItemClickListener titleAndValueItemClickEvent = i -> {
        if(!isEditMode) return;

        if(i.getDetailItemType() == DetailItemType.TITLE_AND_VALUE) {
            TitleAndValueItem item = (TitleAndValueItem) i;

            DialogManager.showTextDialog(this, item, value -> {
                item.value = value;
                adapter.refresh(item);
            });
        }
    };
    private IItemClickListener contentItemClickEvent = i -> {
        if(!isEditMode) return;

        if(i.getDetailItemType() == DetailItemType.TEXT_CONTENT) {
            TextContentItem item = (TextContentItem) i;

            DialogManager.showMultiLineTextDialog(this, item.title, item, value -> {
                item.content = value;
                adapter.refresh(item);
            });
        }
    };
    private IItemClickListener typeItemClickEvent = i -> {
        if(!isEditMode) return;

        if(i.getDetailItemType() == DetailItemType.TITLE_AND_VALUE){
            TitleAndValueItem item = (TitleAndValueItem) i;
            Map<String, String> data = new LinkedHashMap<>();
            if(item == gender) data = BaseData.getProfileGender();

            DialogManager.showTypeListDialog(this, item.title, data, (String code, String text) ->
            {
                item.value = text;
                item.valueCode = code;
                adapter.refresh(item);
            });
        }
    };

    private IItemClickListener recyclerAddClickEvent = i -> {
        if(!isEditMode) return;

        if(i.getDetailItemType() == DetailItemType.RECYCLER){
            RecyclerItem item = (RecyclerItem) i;
            Map<String, String> data = new LinkedHashMap<>();
            if(item == available_languages) data = BaseData.getLanguage();
            else if(item == talent_category) data = BaseData.getProfileTalent();

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
        if(!isEditMode) return;

        if(i.getDetailItemType() == DetailItemType.TITLE_AND_VALUE) {
            TitleAndValueItem item = (TitleAndValueItem) i;

            DialogManager.showNumTextDialog(this, item, value -> {
                item.value = value;
                adapter.refresh(item);
            });
        }
    };

    private void updateProfileData(){
        if(baseProfile == null) baseProfile = new Profile();

        baseProfile.setFirst_name(first_name.value);
        baseProfile.setLast_name(last_name.value);
        baseProfile.setCity(city.value);
        baseProfile.setOccupation(occupation.value);
        baseProfile.setSelf_intro(self_intro.content);
        baseProfile.setTalent_intro(talent_intro.content);
        baseProfile.setGender(gender.valueCode);
        baseProfile.setCountry(country.valueCode);

        List<String> lang = new ArrayList<>();
        for(IDetailItem item : available_languages.getItems()){
            if(item.getDetailItemType() == DetailItemType.TITLE_AND_VALUE){
                lang.add(((TitleAndValueItem)item).valueCode);
            }
        }
        baseProfile.setAvailable_languages(lang);
        List<String> talent = new ArrayList<>();
        for(IDetailItem item : talent_category.getItems()){
            if(item.getDetailItemType() == DetailItemType.TITLE_AND_VALUE){
                talent.add(((TitleAndValueItem)item).valueCode);
            }
        }
        baseProfile.setTalent_category(talent);
    }

    private void addProfile(){
        updateProfileData();

        if(!checkValidation()) return;

        DomainManager.getProfileApiService().update(DomainManager.getTokenHeader(), SharedPreferenceManager.getInstance().getPk(), baseProfile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) addPhoto();
                            else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        , error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void updateProfile(MenuItem updateItem){
        updateProfileData();

        if(!checkValidation()) return;

        DomainManager.getProfileApiService().update(DomainManager.getTokenHeader(), SharedPreferenceManager.getInstance().getPk(), baseProfile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.isSuccess()) updatePhoto(updateItem);
                            else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        , e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void addPhoto(){
        ProfileImage profileImage = new ProfileImage();

        Toast.makeText(this, "SUCCESS!", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void updatePhoto(MenuItem updateItem){
        ProfileImage profileImage = new ProfileImage();





        setEditMode(false);

        updateItem.setIcon(R.drawable.edit);
        updateItem.setTitle(getResStrng(R.string.edit));

        Toast.makeText(this, "SUCCESS!", Toast.LENGTH_SHORT).show();
    }

    private boolean checkValidation(){
        String msg = "";
        if(!checkValidation(baseProfile.getFirst_name())) msg = getResStrng(R.string.profile_firstname);
        else if(!checkValidation(baseProfile.getLast_name())) msg = getResStrng(R.string.profile_lastname);
        else if(!checkValidation(baseProfile.getGender())) msg = getResStrng(R.string.profile_gender);
        //else if(!checkValidation(baseProfile.getSelf_intro())) msg = getResStrng(R.string.profile_selfintro);
        //else if(!checkValidation(baseProfile.getTalent_intro())) msg = getResStrng(R.string.profile_talentintro);
        //else if(!checkValidation(baseProfile.getCountry())) msg = getResStrng(R.string.profile_country);
        //else if(!checkValidation(baseProfile.getCity())) msg = getResStrng(R.string.profile_city);
        //else if(!checkValidation(baseProfile.getOccupation())) msg = getResStrng(R.string.profile_occupation);
        else if(baseProfile.getTalent_category().size() == 0) msg = getResStrng(R.string.profile_talent_category);
        else if(baseProfile.getAvailable_languages().size() == 0) msg = getResStrng(R.string.profile_available_language);

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


