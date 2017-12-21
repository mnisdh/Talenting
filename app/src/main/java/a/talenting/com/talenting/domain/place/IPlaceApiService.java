package a.talenting.com.talenting.domain.place;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 2017-12-07.
 */

public interface IPlaceApiService {
    @GET("/maps/api/place/details/json")
    Observable<PlaceDetail> select(
            @Query("placeid") String placeId,
            @Query("language") String language,
            @Query("key") String apiKey);
}
