package a.talenting.com.talenting.domain.hosting.photo;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
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
    Observable<GetHostingPhotoList> selects(@Header("Authorization") String token, @Path("hosting_pk") String hostingPk);

    @GET("/hosting/{hosting_pk}/photo/{photo_pk}/")
    Observable<GetHostingPhoto> select(@Header("Authorization") String token, @Path("hosting_pk") String hostingPk, @Path("photo_pk") String photoPk);

    @DELETE("/hosting/{hosting_pk}/photo/{photo_pk}/")
    Observable<Response<Void>> delete(
            @Header("Authorization") String token
            , @Path("hosting_pk") String hostingPk
            , @Path("photo_pk") String photoPk);

    @Multipart
    @POST("/hosting/{hosting_pk}/photo/")
    Observable<Response<Void>> insert(
            @Header("Authorization") String token,
            @Path("hosting_pk") String hostingPk,
            @Part MultipartBody.Part image,
            @Part("caption") RequestBody caption,
            @Part("type") RequestBody type);


    @Multipart
    @PUT("/hosting/{hosting_pk}/photo/{photo_pk}/")
    Observable<GetHostingPhoto> update(
            @Header("Authorization") String token,
            @Path("hosting_pk") String hostingPk,
            @Path("photo_pk") String photoPk,
            @Part MultipartBody.Part image,
            @Part("caption") RequestBody caption,
            @Part("type") RequestBody type);

    @Multipart
    @PUT("/hosting/{hosting_pk}/photo/{photo_pk}/")
    Observable<GetHostingPhoto> update(
            @Header("Authorization") String token,
            @Path("hosting_pk") String hostingPk,
            @Path("photo_pk") String photoPk,
            @Part("caption") RequestBody caption,
            @Part("type") RequestBody type);
}
