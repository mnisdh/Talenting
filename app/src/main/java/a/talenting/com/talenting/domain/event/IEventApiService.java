package a.talenting.com.talenting.domain.event;

import a.talenting.com.talenting.domain.hosting.GetHosting;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by user on 2017-12-07.
 */

public interface IEventApiService {
    @GET("/event/")
    Observable<GetEventList> selects();

    @GET("/event/")
    Observable<GetEventList> selects(@Header("Authorization") String token);

    @GET("/event/{pk}/")
    Observable<GetHosting> select(@Path("pk") String pk);

    @GET("/event/{pk}/")
    Observable<GetHosting> select(@Header("Authorization") String token, @Path("pk") String pk);

    @Multipart
    @POST("/event/")
    Observable<GetHosting> insert(
            @Header("Authorization") String token,
            @Part MultipartBody.Part primaryPhoto,
            @Part("title") RequestBody title,
            @Part("program") RequestBody program,
            @Part("event_categories") RequestBody eventCategries,
            @Part("country") RequestBody cuntry,
            @Part("city") RequestBody city,
            @Part("price") RequestBody price,
            @Part("maximum_participant") RequestBody maximumParticipant,
            @Part("opening_date") RequestBody openingDate,
            @Part("closing_date") RequestBody closingDate,
            @Part("event_date") RequestBody event_date);

    @Multipart
    @POST("/event/{pk}/")
    Observable<GetHosting> update(
            @Header("Authorization") String token,
            @Path("pk") String pk,
            @Part MultipartBody.Part primaryPhoto,
            @Part("title") RequestBody title,
            @Part("program") RequestBody program,
            @Part("event_categories") RequestBody eventCategries,
            @Part("country") RequestBody cuntry,
            @Part("city") RequestBody city,
            @Part("price") RequestBody price,
            @Part("maximum_participant") RequestBody maximumParticipant,
            @Part("opening_date") RequestBody openingDate,
            @Part("closing_date") RequestBody closingDate,
            @Part("event_date") RequestBody event_date);

    @DELETE("/event/{pk}/")
    Observable<Boolean> delete(@Header("Authorization") String token, @Path("pk") String pk);
}
