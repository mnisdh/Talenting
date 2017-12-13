package a.talenting.com.talenting.domain.profile;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by user on 2017-12-13.
 */

public interface IProfileApiService {
    @PUT("/member/profile/{pk}/")
    Observable<ProfileResponse> update(@Header("Authorization") String token,@Path("pk")String pk, @Body Profile profile);
}
