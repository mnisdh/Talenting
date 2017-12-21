package a.talenting.com.talenting.domain.hosting;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by user on 2017-12-07.
 */

public interface IHostingApiService {
    @GET("/hosting/")
    Observable<GetHostingList> selects(@Header("Authorization") String token);

    @GET("/hosting/")
    Observable<GetHostingList> selects(
            @Header("Authorization") String token,
            @Query("search_query") String address,
            @Query("search_categories") String category);

    @GET("/hosting/")
    Observable<GetHostingList> selectsCategories(
            @Header("Authorization") String token,
            @Query("search_categories") String category);

    @GET("/hosting/")
    Observable<GetHostingList> selectsAddress(
            @Header("Authorization") String token,
            @Query("search_query") String address);

    @GET("/hosting/{pk}/")
    Observable<GetHosting> select(@Header("Authorization") String token, @Path("pk") String pk);

    @POST("/hosting/")
    Observable<GetHosting> insert(@Header("Authorization") String token, @Body Hosting hosting);

    @PUT("/hosting/{pk}/")
    Observable<GetHosting> update(@Header("Authorization") String token, @Path("pk") String pk, @Body Hosting hosting);

    @DELETE("/hosting/{pk}/")
    Observable<Response<Void>> delete(@Header("Authorization") String token, @Path("pk") String pk);
}
