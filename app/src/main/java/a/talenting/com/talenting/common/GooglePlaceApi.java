package a.talenting.com.talenting.common;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import static android.app.Activity.RESULT_OK;

/**
 * Created by daeho on 2017. 12. 7..
 */

public class GooglePlaceApi {
    private static LatLngBounds getBounds(double lat, double lng){
        LatLng latLng1 = new LatLng(lat - 0.01, lng - 0.01);
        LatLng latLng2 = new LatLng(lat + 0.01, lng + 0.01);

        return new LatLngBounds(latLng1, latLng2);
    }
    private static LatLngBounds getBounds(LatLng latLng){
        return getBounds(latLng.latitude, latLng.longitude);
    }

    public static void startPlaceSelectMap(ActivityResultManager manager, IGooglePlaceApiEvent event, Activity activity, LatLngBounds latLngBounds){
        try {
            int REQ = Constants.REQ_SELECT_PLACE;

            ActivityResultItem item = new ActivityResultItem(REQ, (int resultCode, Intent data) -> {
                if (resultCode == RESULT_OK) {
                    Place place = PlacePicker.getPlace(data, activity);
                    event.callback(place);
                }
            });
            manager.addItem(item);

            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            activity.startActivityForResult(builder.setLatLngBounds(latLngBounds).build(activity), REQ);
        }
        catch (Exception e){
            Log.d("GooglePlaceApi", "startPlaceSelectMap : " + e.getMessage());
        }
    }
    public static void startPlaceSelectMap(ActivityResultManager manager, IGooglePlaceApiEvent event, Activity activity, double lat, double lng){
        startPlaceSelectMap(manager, event, activity, getBounds(lat, lng));
    }
    public static void startPlaceSelectMap(ActivityResultManager manager, IGooglePlaceApiEvent event, Activity activity, LatLng latLng){
        startPlaceSelectMap(manager, event, activity, getBounds(latLng));
    }

    public static void startAutoComplateAddress(ActivityResultManager manager, IGooglePlaceApiEvent event, Activity activity){
        try{
            int REQ = Constants.REQ_EVENT_PLACE;

            ActivityResultItem item = new ActivityResultItem(REQ, (int resultCode, Intent data) -> {
                if (resultCode == RESULT_OK) {
                    Place place = PlaceAutocomplete.getPlace(activity, data);
                    event.callback(place);
                }
            });
            manager.addItem(item);

            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(activity);
            activity.startActivityForResult(intent, REQ);
        }
        catch (Exception e){
            Log.d("GooglePlaceApi", "startAutoComplateAddress : " + e.getMessage());
        }
    }

}
