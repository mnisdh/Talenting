package a.talenting.com.talenting.domain.profile;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by user on 2017-12-13.
 */

public interface IProfileApiService {
    @PUT("/member/profile/{pk}/")
    Observable<ProfileResponse> update(@Header("Authorization") String token,@Path("pk")String pk, @Body Profile profile);

    @GET("/member/profile/{pk}/")
    Observable<ProfileResponse> retrieve(@Header("Authorization") String token,@Path("pk")String pk);

    @POST("/member/profile/{pk}/wish-list-toggle/")
    Observable<Response<Void>> wishListToggle(@Header("Authorization") String token, @Path("pk") String pk);
}
