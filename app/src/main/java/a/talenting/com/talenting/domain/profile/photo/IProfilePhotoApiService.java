package a.talenting.com.talenting.domain.profile.photo;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by user on 2017-12-14.
 */

public interface IProfilePhotoApiService {
    @Multipart
    @POST("/member/profile/{profile_pk}/image/")
    Observable<ProfileImageResponse> create(
            @Header("Authorization") String token,
            @Path("profile_pk") String profilePk,
            @Part MultipartBody.Part image);

    @GET("/member/profile/{profile_pk}/image/{image_pk}/")
    Observable<ProfileImageResponse> retrieve(
            @Header("Authorization") String token,
            @Path("profile_pk")String profilePk, @Path("image_pk")String pk);

    @PUT("/member/profile/{profile_pk}/image/{image_pk}/")
    Observable<ProfileImageResponse> update(
            @Header("Authorization") String token,
            @Path("profile_pk")String profilePk, @Path("image_pk")String pk);

    @DELETE("/member/profile/{profile_pk}/image/{image_pk}/")
    Observable<ProfileImageResponse> delete(
            @Header("Authorization") String token,
            @Path("profile_pk")String profilePk, @Path("image_pk")String pk);

}
