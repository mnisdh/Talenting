package a.talenting.com.talenting.domain.hosting.photo;

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

public interface IHostingPhotoApiService {
    @GET("/hosting/{hosting_pk}/photo/")
    Observable<Hosting> select(@Path("hosting_pk") String hostingPk);

    @GET("/hosting/{hosting_pk}/photo/{photo_pk}/")
    Observable<Hosting> select(@Path("hosting_pk") String hostingPk, @Path("photo_pk") String photoPk);

    @POST("/hosting/")
    Observable<HostingPhotoGet> insert(@Body Hosting hosting);

    @PUT("/hosting/")
    Observable<Hosting> update(@Body Hosting hosting);

    @DELETE("/hosting/{pk}/")
    Observable<Boolean> delete(@Path("pk") String pk);
}
