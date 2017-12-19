package a.talenting.com.talenting.domain.hosting.options;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by user on 2017-12-07.
 */

public interface IHostingOptionsApiService {
    @GET("/hosting/options?param=categories")
    Observable<GetCategories> selectCategories();

    @GET("/hosting/options?param=house_types")
    Observable<GetHouseTypes> selectHouseTypes();

    @GET("/hosting/options?param=room_types")
    Observable<GetRoomTypes> selectRoomTypes();

    @GET("/hosting/options?param=meal_types")
    Observable<GetMealTypes> selectMealTypes();

    @GET("/hosting/options?param=internet_types")
    Observable<GetInternetTypes> selectInternetTypes();

    @GET("/hosting/options?param=photo_types")
    Observable<GetPhotoTypes> selectPhotoTypes();

    @GET("/hosting/options?param=languages")
    Observable<GetLanguages> selectLanguages();

    @GET("/hosting/options?param=countries")
    Observable<GetCountries> selectCountries();
}
