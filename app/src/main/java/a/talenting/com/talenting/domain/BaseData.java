package a.talenting.com.talenting.domain;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by daeho on 2017. 12. 12..
 */

public class BaseData {
    private static Map<Integer, String> hosting_category = new LinkedHashMap<>();
    private static Map<Integer, String> hosting_house = new LinkedHashMap<>();
    private static Map<Integer, String> hosting_room = new LinkedHashMap<>();
    private static Map<Integer, String> hosting_meal = new LinkedHashMap<>();
    private static Map<Integer, String> hosting_internet = new LinkedHashMap<>();
    private static Map<Integer, String> hosting_photo = new LinkedHashMap<>();


    public static Map<Integer, String> getHostingCategory(){
        if(hosting_category.size() == 0) {
            hosting_category.put(1, "Culture");
            hosting_category.put(2, "Work hand");
            hosting_category.put(3, "Language exchange");
            hosting_category.put(4, "Art");
            hosting_category.put(5, "Other");
        }

        return hosting_category;
    }

    public static Map<Integer, String> getHostingHouseType(){
        if(hosting_house.size() == 0) {
            hosting_house.put(1, "Apartment");
            hosting_house.put(2, "House");
            hosting_house.put(3, "Guesthouse");
            hosting_house.put(4, "Office");
            hosting_house.put(5, "Dormitory");
        }

        return hosting_house;
    }

    public static Map<Integer, String> getHostingRoomType(){
        if(hosting_room.size() == 0) {
            hosting_room.put(1, "Private room");
            hosting_room.put(2, "Shared room");
        }

        return hosting_room;
    }

    public static Map<Integer, String> getHostingMealType(){
        if(hosting_meal.size() == 0) {
            hosting_meal.put(1, "It's a deal! We share a meal!");
            hosting_meal.put(2, "Make your dishes using host's ingredient");
        }

        return hosting_meal;
    }

    public static Map<Integer, String> getHostingInternetType(){
        if(hosting_internet.size() == 0) {
            hosting_internet.put(1, "Wifi");
            hosting_internet.put(2, "Only LAN");
            hosting_internet.put(3, "No Internet");
        }

        return hosting_internet;
    }

    public static Map<Integer, String> getHostingPhotoType(){
        if(hosting_photo.size() == 0) {
            hosting_photo.put(1, "Inside of the house");
            hosting_photo.put(2, "View of the house");
            hosting_photo.put(3, "External look of the house");
            hosting_photo.put(4, "Around the house");
            hosting_photo.put(5, "Other");
        }

        return hosting_photo;
    }
}
