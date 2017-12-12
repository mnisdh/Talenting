package a.talenting.com.talenting.domain.hosting;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by user on 2017-12-07.
 */

public interface IHostingApiService {
    @GET("/hosting/")
    Observable<Hosting> select();

    @GET("/hosting/{pk}/")
    Observable<Hosting> select(@Path("pk") String pk);

    @POST("/hosting/")
    Observable<GetHostingList> insert(@Body Hosting hosting);

    @PUT("/hosting/")
    Observable<Hosting> update(@Body Hosting hosting);

    @DELETE("/hosting/{pk}/")
    Observable<Boolean> delete(@Path("pk") String pk);
}
