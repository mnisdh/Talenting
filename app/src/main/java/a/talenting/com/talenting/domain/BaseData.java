package a.talenting.com.talenting.domain;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by daeho on 2017. 12. 12..
 */

public class BaseData {
    //region common
    private static Map<String, String> language = new LinkedHashMap<>();

    public static Map<String, String> getLanguage(){
        if(language.size() == 0){
            language.put("af", "Afrikaans");
            language.put("ar", "Arabic");
            language.put("ast", "Asturian");
            language.put("az", "Azerbaijani");
            language.put("bg", "Bulgarian");
            language.put("be", "Belarusian");
            language.put("bn", "Bengali");
            language.put("br", "Breton");
            language.put("bs", "Bosnian");
            language.put("ca", "Catalan");
            language.put("cs", "Czech");
            language.put("cy", "Welsh");
            language.put("da", "Danish");
            language.put("de", "German");
            language.put("dsb", "Lower Sorbian");
            language.put("el", "Greek");
            language.put("en", "English");
            language.put("en-au", "Australian English");
            language.put("en-gb", "British English");
            language.put("eo", "Esperanto");
            language.put("es", "Spanish");
            language.put("es-ar", "Argentinian Spanish");
            language.put("es-co", "Colombian Spanish");
            language.put("es-mx", "Mexican Spanish");
            language.put("es-ni", "Nicaraguan Spanish");
            language.put("es-ve", "Venezuelan Spanish");
            language.put("et", "Estonian");
            language.put("eu", "Basque");
            language.put("fa", "Persian");
            language.put("fi", "Finnish");
            language.put("fr", "French");
            language.put("fy", "Frisian");
            language.put("ga", "Irish");
            language.put("gd", "Scottish Gaelic");
            language.put("gl", "Galician");
            language.put("he", "Hebrew");
            language.put("hi", "Hindi");
            language.put("hr", "Croatian");
            language.put("hsb", "Upper Sorbian");
            language.put("hu", "Hungarian");
            language.put("ia", "Interlingua");
            language.put("id", "Indonesian");
            language.put("io", "Ido");
            language.put("is", "Icelandic");
            language.put("it", "Italian");
            language.put("ja", "Japanese");
            language.put("ka", "Georgian");
            language.put("kk", "Kazakh");
            language.put("km", "Khmer");
            language.put("kn", "Kannada");
            language.put("ko", "Korean");
            language.put("lb", "Luxembourgish");
            language.put("lt", "Lithuanian");
            language.put("lv", "Latvian");
            language.put("mk", "Macedonian");
            language.put("ml", "Malayalam");
            language.put("mn", "Mongolian");
            language.put("mr", "Marathi");
            language.put("my", "Burmese");
            language.put("nb", "Norwegian Bokmål");
            language.put("ne", "Nepali");
            language.put("nl", "Dutch");
            language.put("nn", "Norwegian Nynorsk");
            language.put("os", "Ossetic");
            language.put("pa", "Punjabi");
            language.put("pl", "Polish");
            language.put("pt", "Portuguese");
            language.put("pt-br", "Brazilian Portuguese");
            language.put("ro", "Romanian");
            language.put("ru", "Russian");
            language.put("sk", "Slovak");
            language.put("sl", "Slovenian");
            language.put("sq", "Albanian");
            language.put("sr", "Serbian");
            language.put("sr-latn", "Serbian Latin");
            language.put("sv", "Swedish");
            language.put("sw", "Swahili");
            language.put("ta", "Tamil");
            language.put("te", "Telugu");
            language.put("th", "Thai");
            language.put("tr", "Turkish");
            language.put("tt", "Tatar");
            language.put("udm", "Udmurt");
            language.put("uk", "Ukrainian");
            language.put("ur", "Urdu");
            language.put("vi", "Vietnamese");
            language.put("zh-hans", "Simplified Chinese");
            language.put("zh-hant", "Traditional Chinese");
        }

        return language;
    }
    public static String getLanguageText(String key){
        if(!getLanguage().containsKey(key)) return "";
        return getLanguage().get(key);
    }
    //endregion

    //region hosting
    private static Map<String, String> hosting_category = new LinkedHashMap<>();
    private static Map<String, String> hosting_house = new LinkedHashMap<>();
    private static Map<String, String> hosting_room = new LinkedHashMap<>();
    private static Map<String, String> hosting_meal = new LinkedHashMap<>();
    private static Map<String, String> hosting_internet = new LinkedHashMap<>();
    private static Map<String, String> hosting_photo = new LinkedHashMap<>();

    public static Map<String, String> getHostingCategory(){
        if(hosting_category.size() == 0) {
            hosting_category.put("1", "Culture");
            hosting_category.put("2", "Work hand");
            hosting_category.put("3", "Language exchange");
            hosting_category.put("4", "Art");
            hosting_category.put("5", "Other");
        }

        return hosting_category;
    }
    public static String getHostigCategoryText(String key){
        if(!getHostingCategory().containsKey(key)) return "";
        return getHostingCategory().get(key);
    }

    public static Map<String, String> getHostingHouseType(){
        if(hosting_house.size() == 0) {
            hosting_house.put("1", "Apartment");
            hosting_house.put("2", "House");
            hosting_house.put("3", "Guesthouse");
            hosting_house.put("4", "Office");
            hosting_house.put("5", "Dormitory");
        }

        return hosting_house;
    }
    public static String getHostigHouseText(String key){
        if(!getHostingHouseType().containsKey(key)) return "";
        return getHostingHouseType().get(key);
    }

    public static Map<String, String> getHostingRoomType(){
        if(hosting_room.size() == 0) {
            hosting_room.put("1", "Private room");
            hosting_room.put("2", "Shared room");
        }

        return hosting_room;
    }
    public static String getHostigRoomText(String key){
        if(!getHostingRoomType().containsKey(key)) return "";
        return getHostingRoomType().get(key);
    }

    public static Map<String, String> getHostingMealType(){
        if(hosting_meal.size() == 0) {
            hosting_meal.put("1", "It's a deal! We share a meal!");
            hosting_meal.put("2", "Make your dishes using host's ingredient");
        }

        return hosting_meal;
    }
    public static String getHostigMealText(String key){
        if(!getHostingMealType().containsKey(key)) return "";
        return getHostingMealType().get(key);
    }

    public static Map<String, String> getHostingInternetType(){
        if(hosting_internet.size() == 0) {
            hosting_internet.put("1", "Wifi");
            hosting_internet.put("2", "Only LAN");
            hosting_internet.put("3", "No Internet");
        }

        return hosting_internet;
    }
    public static String getHostigInternetText(String key){
        if(!getHostingInternetType().containsKey(key)) return "";
        return getHostingInternetType().get(key);
    }

    public static Map<String, String> getHostingPhotoType(){
        if(hosting_photo.size() == 0) {
            hosting_photo.put("1", "Inside of the house");
            hosting_photo.put("2", "View of the house");
            hosting_photo.put("3", "External look of the house");
            hosting_photo.put("4", "Around the house");
            hosting_photo.put("5", "Other");
        }

        return hosting_photo;
    }
    public static String getHostigPhotoText(String key){
        if(!getHostingPhotoType().containsKey(key)) return "";
        return getHostingPhotoType().get(key);
    }
    //endregion


}
