package a.talenting.com.talenting.domain.hosting;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by user on 2017-12-07.
 */

public interface IHostingApiService {
    @GET("/hosting/")
    Observable<GetHostingList> selects(@Header("Authorization") String token);

    @GET("/hosting/{pk}/")
    Observable<GetHosting> select(@Header("Authorization") String token, @Path("pk") String pk);

    @POST("/hosting/")
    Observable<GetHosting> insert(@Header("Authorization") String token, @Body Hosting hosting);

    @PUT("/hosting/{pk}/")
    Observable<GetHosting> update(@Header("Authorization") String token, @Path("pk") String pk, @Body Hosting hosting);

    @DELETE("/hosting/{pk}/")
    Observable<Boolean> delete(@Header("Authorization") String token, @Path("pk") String pk);
}
