package a.talenting.com.talenting.domain.event;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by user on 2017-12-07.
 */

public interface IEventApiService {
    @GET("/event/")
    Observable<GetEventList> selects(@Header("Authorization") String token);

    @GET("/event/")
    Observable<GetEventList> selects(
            @Header("Authorization") String token,
            @Query("search_query") String address,
            @Query("search_categories") String category);

    @GET("/event/")
    Observable<GetEventList> selectsCategories(
            @Header("Authorization") String token,
            @Query("search_categories") String category);

    @GET("/event/")
    Observable<GetEventList> selectsAddress(
            @Header("Authorization") String token,
            @Query("search_query") String address);

    @GET("/event/created/")
    Observable<GetEventList> selectsCreated(@Header("Authorization") String token);

    @GET("member/profile/{profile_pk}/my-event/")
    Observable<GetEventList> selectsJoined(@Header("Authorization") String token, @Path("profile_pk") String profilePk);

    @GET("/event/{pk}/")
    Observable<GetEvent> select(@Header("Authorization") String token, @Path("pk") String pk);

    @POST("/event/")
    Observable<GetEvent> insert(@Header("Authorization") String token, @Body Event event);

    @POST("/event/{pk}/")
    Observable<GetEvent> update(@Header("Authorization") String token, @Path("pk") String pk, @Body Event event);

    @DELETE("/event/{pk}/")
    Observable<Response<Void>> delete(@Header("Authorization") String token, @Path("pk") String pk);

    @POST("/event/{pk}/participate/")
    Observable<Response<Void>> participate(@Header("Authorization") String token, @Path("pk") String pk);

    @POST("/event/{pk}/wish-list-toggle/")
    Observable<Response<Void>> wishListToggle(@Header("Authorization") String token, @Path("pk") String pk);
}
