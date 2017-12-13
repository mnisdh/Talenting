package a.talenting.com.talenting.domain.user;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by user on 2017-12-07.
 */

public interface IUserApiService {
    @POST("/member/sign-up/")
    Observable<SignupResponse> signUp(@Body UserSignup user_signup);

    @POST("/member/log-in/")
    Observable<LoginResponse> login(@Body UserLogin user_login);

    @PUT("/member/password-missing/")
    Observable<PwMissingResponse> pwMissing(@Body PwMissing pw_missing);
}
