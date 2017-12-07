package a.talenting.com.talenting.common;

import com.google.android.gms.location.places.Place;

/**
 * Created by daeho on 2017. 12. 7..
 */

public interface IGooglePlaceApiEvent {
    void callback(Place place);
}
