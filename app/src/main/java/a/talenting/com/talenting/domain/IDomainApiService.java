package a.talenting.com.talenting.domain;

import a.talenting.com.talenting.domain.user.SignupResponse;
import a.talenting.com.talenting.domain.user.UserSignup;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by user on 2017-12-07.
 */

public interface IDomainApiService {
    final String Base_URL = "http://talenting-env.ap-northeast-2.elasticbeanstalk.com";

    @POST("/member/sign-up/")
    Observable<SignupResponse> signUp(@Body UserSignup user_signup);
}
