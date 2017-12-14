package a.talenting.com.talenting.domain.hosting.photo;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by user on 2017-12-07.
 */

public interface IHostingPhotoApiService {
    @GET("/hosting/{hosting_pk}/photo/")
    Observable<GetHostingPhoto> selects(@Path("hosting_pk") String hostingPk);

    @GET("/hosting/{hosting_pk}/photo/")
    Observable<GetHostingPhoto> selects(@Header("Authorization") String token, @Path("hosting_pk") String hostingPk);

    @GET("/hosting/{hosting_pk}/photo/{photo_pk}/")
    Observable<GetHostingPhoto> select(@Path("hosting_pk") String hostingPk, @Path("photo_pk") String photoPk);

    @GET("/hosting/{hosting_pk}/photo/{photo_pk}/")
    Observable<GetHostingPhoto> select(@Header("Authorization") String token, @Path("hosting_pk") String hostingPk, @Path("photo_pk") String photoPk);

    @POST("/hosting/{hosting_pk}/photo/")
    Observable<GetHostingPhoto> insert(@Header("Authorization") String token, @Path("hosting_pk") String hostingPk, @Body HostingPhoto hosting);

    @Multipart
    @POST("/hosting/{hosting_pk}/photo/")
    Observable<GetHostingPhoto> insert(
            @Header("Authorization") String token,
            @Path("hosting_pk") String hostingPk,
            @Part MultipartBody.Part image,
            @Part("caption") RequestBody caption,
            @Part("type") RequestBody type);



    @PUT("/hosting/{hosting_pk}/photo/{photo_pk}/")
    Observable<HostingPhoto> update(@Header("Authorization") String token, @Path("hosting_pk") String hostingPk, @Path("photo_pk") String photoPk, @Body HostingPhoto hosting);

    @DELETE("/hosting/{hosting_pk}/photo/{photo_pk}/")
    Observable<Boolean> delete(@Header("Authorization") String token, @Path("hosting_pk") String hostingPk, @Path("photo_pk") String photoPk);
}
