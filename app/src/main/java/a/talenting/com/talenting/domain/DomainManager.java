package a.talenting.com.talenting.domain;

import a.talenting.com.talenting.domain.user.LoginResponse;
import a.talenting.com.talenting.domain.user.SignupResponse;
import a.talenting.com.talenting.domain.user.UserLogin;
import a.talenting.com.talenting.domain.user.UserSignup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2017-12-07.
 */

public class DomainManager {
    private static DomainManager domainManager = null;

    private IDomainApiService apiService;
    private Retrofit retrofit;

    public static DomainManager getInstance() {
        if (domainManager == null) domainManager = new DomainManager(IDomainApiService.Base_URL);

        return domainManager;
    }

    private DomainManager(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();

        apiService = retrofit.create(IDomainApiService.class);
    }

    public void signUp(UserSignup user_signup, final IDomainCallback callback) {
        apiService.signUp(user_signup).enqueue(new Callback<SignupResponse>() {
            @Override public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.code(), response.body());
                }else {
                    callback.onFailure(response.code(), response.body());
                }
            }

            @Override public void onFailure(Call<SignupResponse> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void login(UserLogin user_login, final IDomainCallback callback) {
        apiService.login(user_login).enqueue(new Callback<LoginResponse>() {
            @Override public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.code(), response.body());
                }else {
                    callback.onFailure(response.code(), response.body());
                }
            }

            @Override public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
