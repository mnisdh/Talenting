package a.talenting.com.talenting.domain.event;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
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
    Observable<GetEventList> selects(@Header("Authorization") String token);

    @GET("/event/created/")
    Observable<GetEventList> selectsCreated(@Header("Authorization") String token);

    @GET("/event/{pk}/")
    Observable<GetEvent> select(@Header("Authorization") String token, @Path("pk") String pk);

    @Multipart
    @POST("/event/")
    Observable<GetEvent> insert(
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
            @Part("event_date") RequestBody event_date,
            @Part("lat") RequestBody lat,
            @Part("lon") RequestBody lon);

    @Multipart
    @POST("/event/{pk}/")
    Observable<GetEvent> update(
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
            @Part("event_date") RequestBody event_date,
            @Part("lat") RequestBody lat,
            @Part("lon") RequestBody lon);

    @Multipart
    @POST("/event/{pk}/")
    Observable<GetEvent> update(
            @Header("Authorization") String token,
            @Path("pk") String pk,
            @Part("title") RequestBody title,
            @Part("program") RequestBody program,
            @Part("event_categories") RequestBody eventCategries,
            @Part("country") RequestBody cuntry,
            @Part("city") RequestBody city,
            @Part("price") RequestBody price,
            @Part("maximum_participant") RequestBody maximumParticipant,
            @Part("opening_date") RequestBody openingDate,
            @Part("closing_date") RequestBody closingDate,
            @Part("event_date") RequestBody event_date,
            @Part("lat") RequestBody lat,
            @Part("lon") RequestBody lon);

    @DELETE("/event/{pk}/")
    Observable<Response<Void>> delete(@Header("Authorization") String token, @Path("pk") String pk);

    @POST("/event/{pk}/participate/")
    Observable<GetEvent> participate(@Header("Authorization") String token, @Path("pk") String pk);

}
