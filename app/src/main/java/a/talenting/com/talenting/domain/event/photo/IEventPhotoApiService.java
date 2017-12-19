package a.talenting.com.talenting.domain.event.photo;

import a.talenting.com.talenting.domain.hosting.photo.GetHostingPhoto;
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

public interface IEventPhotoApiService {
    @GET("/event/{event_pk}/photo/")
    Observable<GetEventPhotoList> selects(@Header("Authorization") String token, @Path("event_pk") String hostingPk);

    @GET("/event/{event_pk}/photo/{photo_pk}/")
    Observable<GetEventPhoto> select(@Header("Authorization") String token, @Path("event_pk") String hostingPk, @Path("photo_pk") String photoPk);

    @Multipart
    @POST("/event/{event_pk}/photo/")
    Observable<GetEventPhoto> insert(
            @Header("Authorization") String token,
            @Path("event_pk") String hostingPk,
            @Part MultipartBody.Part image,
            @Part("events") RequestBody events);


    @Multipart
    @PUT("/event/{event_pk}/photo/{photo_pk}/")
    Observable<GetEventPhoto> update(
            @Header("Authorization") String token,
            @Path("event_pk") String hostingPk,
            @Path("photo_pk") String photoPk,
            @Part MultipartBody.Part image,
            @Part("events") RequestBody events);

    @Multipart
    @PUT("/event/{event_pk}/photo/{photo_pk}/")
    Observable<GetHostingPhoto> update(
            @Header("Authorization") String token,
            @Path("event_pk") String hostingPk,
            @Path("photo_pk") String photoPk,
            @Part("events") RequestBody events);

    @DELETE("/event/{event_pk}/photo/{photo_pk}/")
    Observable<Response<Void>> delete(@Header("Authorization") String token, @Path("event_pk") String hostingPk, @Path("photo_pk") String photoPk);
}
