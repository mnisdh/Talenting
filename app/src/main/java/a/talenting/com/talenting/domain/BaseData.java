package a.talenting.com.talenting.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import a.talenting.com.talenting.domain.hosting.options.Categories;
import a.talenting.com.talenting.domain.hosting.options.Countries;
import a.talenting.com.talenting.domain.hosting.options.HouseTypes;
import a.talenting.com.talenting.domain.hosting.options.InternetTypes;
import a.talenting.com.talenting.domain.hosting.options.Languages;
import a.talenting.com.talenting.domain.hosting.options.MealTypes;
import a.talenting.com.talenting.domain.hosting.options.PhotoTypes;
import a.talenting.com.talenting.domain.hosting.options.RoomTypes;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by daeho on 2017. 12. 12..
 */

public class BaseData {
    private static int initCount = 8;
    private static IBaseDataEvent iBaseDataEvent;

    private static CompositeDisposable disposable;

    private static void checkInitSuccess(){
        initCount--;
        if(initCount == 0) iBaseDataEvent.finished(true);
    }

    public void destroy() {
        disposable.clear();
    }

    public static void init(IBaseDataEvent event){
        iBaseDataEvent = event;

        if(initCount == 0) {
            iBaseDataEvent.finished(true);
            return;
        }

        //region languages
        disposable.add(DomainManager.getHostingOptionsApiService().selectLanguages()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
                    if(result.isSuccess()){
                        for(Languages item : result.getLanguages())
                            language.put(item.getCode(), item.getValue());
                    }
                }
                , error -> {}
                , () -> checkInitSuccess()));
        //endregion
        //region category
        DomainManager.getHostingOptionsApiService().selectCategories()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
                    if (result.isSuccess()) {
                        for (Categories item : result.getCategories())
                            category.put(item.getCode(), item.getValue());
                    }
                }
                , error -> {
                }
                , () -> checkInitSuccess());
        //endregion
        //region country
        DomainManager.getHostingOptionsApiService().selectCountries()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
                    if(result.isSuccess()){
                        for(Countries item : result.getCountries()) country.put(item.getCode(), item.getValue());
                    }
                }
                , error -> {}
                , () -> checkInitSuccess());
        //endregion
        //region house_types
        DomainManager.getHostingOptionsApiService().selectHouseTypes()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
                    if(result.isSuccess()){
                        for(HouseTypes item : result.getHouse_types()) hosting_house.put(item.getCode(), item.getValue());
                    }
                }
                , error -> {}
                , () -> checkInitSuccess());
        //endregion
        //region room_types
        DomainManager.getHostingOptionsApiService().selectRoomTypes()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
                    if(result.isSuccess()){
                        for(RoomTypes item : result.getRoom_types()) hosting_room.put(item.getCode(), item.getValue());
                    }
                }
                , error -> {}
                , () -> checkInitSuccess());
        //endregion
        //region meal_types
        DomainManager.getHostingOptionsApiService().selectMealTypes()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
                    if(result.isSuccess()){
                        for(MealTypes item : result.getMeal_types()) hosting_meal.put(item.getCode(), item.getValue());
                    }
                }
                , error -> {}
                , () -> checkInitSuccess());
        //endregion
        //region internet_types
        DomainManager.getHostingOptionsApiService().selectInternetTypes()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
                    if(result.isSuccess()){
                        for(InternetTypes item : result.getInternet_types()) hosting_internet.put(item.getCode(), item.getValue());
                    }
                }
                , error -> {}
                , () -> checkInitSuccess());
        //endregion
        //region photo_types
        DomainManager.getHostingOptionsApiService().selectPhotoTypes()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
                    if(result.isSuccess()){
                        for(PhotoTypes item : result.getPhoto_types()) hosting_internet.put(item.getCode(), item.getValue());
                    }
                }
                , error -> {}
                , () -> checkInitSuccess());
        //endregion
    }

    //region common
    private static Map<String, String> language = new LinkedHashMap<>();
    private static Map<String, String> category = new LinkedHashMap<>();
    private static Map<String, String> country = new LinkedHashMap<>();

    public static Map<String, String> getLanguage(){
        return language;
    }
    public static Map<String, String> getLanguage(List<String> exceptKeys){
        if(exceptKeys.size() == 0) return getLanguage();

        Map<String, String> result = new LinkedHashMap<>();

        for(String key : getLanguage().keySet()){
            if(!exceptKeys.contains(key)) result.put(key, getLanguage().get(key));
        }

        return result;
    }
    public static String getLanguageText(String key){
        if(!getLanguage().containsKey(key)) return "";
        return getLanguage().get(key);
    }
    public static String getLanguageKey(int index){
        String result = "";

        if(index < 0 || index >= getLanguage().size()) return result;

        int idx = 0;
        for(String key : getLanguage().keySet()){
            if(idx == index) return key;
            idx++;
        }

        return result;
    }

    public static Map<String, String> getCategory(){
        return category;
    }
    public static String getCategoryText(String key){
        if(!getCategory().containsKey(key)) return "";
        return getCategory().get(key);
    }
    public static String getCategoryKey(int index){
        String result = "";

        if(index < 0 || index >= getCategory().size()) return result;

        int idx = 0;
        for(String key : getCategory().keySet()){
            if(idx == index) return key;
            idx++;
        }

        return result;
    }

    public static Map<String, String> getCountry(){
        return country;
    }
    public static String getCountryText(String key){
        if(!getCountry().containsKey(key)) return "";
        return getCountry().get(key);
    }
    public static String getCountryKey(int index){
        String result = "";

        if(index < 0 || index >= getCountry().size()) return result;

        int idx = 0;
        for(String key : getCountry().keySet()){
            if(idx == index) return key;
            idx++;
        }

        return result;
    }
    //endregion

    //region hosting
    private static Map<String, String> hosting_house = new LinkedHashMap<>();
    private static Map<String, String> hosting_room = new LinkedHashMap<>();
    private static Map<String, String> hosting_meal = new LinkedHashMap<>();
    private static Map<String, String> hosting_internet = new LinkedHashMap<>();
    private static Map<String, String> hosting_photo = new LinkedHashMap<>();

    public static Map<String, String> getHostingHouseType(){
        return hosting_house;
    }
    public static String getHostigHouseText(String key){
        if(!getHostingHouseType().containsKey(key)) return "";
        return getHostingHouseType().get(key);
    }

    public static Map<String, String> getHostingRoomType(){
        return hosting_room;
    }
    public static String getHostigRoomText(String key){
        if(!getHostingRoomType().containsKey(key)) return "";
        return getHostingRoomType().get(key);
    }

    public static Map<String, String> getHostingMealType(){
        return hosting_meal;
    }
    public static String getHostigMealText(String key){
        if(!getHostingMealType().containsKey(key)) return "";
        return getHostingMealType().get(key);
    }

    public static Map<String, String> getHostingInternetType(){
        return hosting_internet;
    }
    public static String getHostigInternetText(String key){
        if(!getHostingInternetType().containsKey(key)) return "";
        return getHostingInternetType().get(key);
    }

    public static Map<String, String> getHostingPhotoType(){
        return hosting_photo;
    }
    public static String getHostigPhotoText(String key){
        if(!getHostingPhotoType().containsKey(key)) return "";
        return getHostingPhotoType().get(key);
    }
    //endregion

    //region profile
    private static Map<String, String> profile_gender = new LinkedHashMap<>();
    private static Map<String, String> profile_talent = new LinkedHashMap<>();

    public static Map<String, String> getProfileGender(){
        if(profile_gender.size() == 0) {
            profile_gender.put("1", "Male");
            profile_gender.put("2", "Female");
        }

        return profile_gender;
    }
    public static String getProfileGenderText(String key){
        if(!getProfileGender().containsKey(key)) return "";
        return getProfileGender().get(key);
    }

    public static Map<String, String> getProfileTalent(){
        if(profile_talent.size() == 0) {
            profile_talent.put("1", "Culture");
            profile_talent.put("2", "Work hand");
            profile_talent.put("3", "Language exchange");
            profile_talent.put("4", "Art");
            profile_talent.put("5", "Other");
        }

        return profile_talent;
    }
    public static String getProfileTalentText(String key){
        if(!getProfileTalent().containsKey(key)) return "";
        return getProfileTalent().get(key);
    }


    public interface IBaseDataEvent{
        void finished(boolean isSuccess);
    }
}


