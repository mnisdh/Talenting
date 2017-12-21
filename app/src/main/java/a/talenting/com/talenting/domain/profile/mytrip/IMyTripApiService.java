package a.talenting.com.talenting.domain.profile.mytrip;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by user on 2017-12-21.
 */

public interface IMyTripApiService {
    @GET("/member/mytrip/")
    Observable<MyTripResponse> getEveryList(@Header("Authorization") String token);

    @GET("/member/mytrip/created/")
    Observable<MyTripResponse> getMyList(@Header("Authorization") String token);

    @POST("/member/mytrip/")
    Observable<MyTripResponse> create(@Header("Authorization") String token,@Body Mytrip mytrip);

    @GET("/member/mytrip/{mytrip_pk}/")
    Observable<MyTripResponse> retrieve(@Header("Authorization") String token,@Path("mytrip_pk")String pk);

    @PUT("/member/mytrip/{mytrip_pk}/")
    Observable<MyTripResponse> update(@Header("Authorization") String token,@Path("mytrip_pk")String pk,@Body Mytrip mytrip);

    @DELETE("/member/mytrip/{mytrip_pk}/")
    Observable<MyTripResponse> delete(@Header("Authorization") String token,@Path("mytrip_pk")String pk);

    @GET("/member/mytrip/?search_query={address}")
    Observable<MyTripResponse> search(@Header("Authorization") String token,@Path("address")String address);


}
