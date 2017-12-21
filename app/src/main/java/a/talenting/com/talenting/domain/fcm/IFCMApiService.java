package a.talenting.com.talenting.domain.fcm;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by user on 2017-12-07.
 */

public interface IFCMApiService {
    @POST("/fcm/device/")
    Observable<FCMToken> insert(@Header("Authorization") String token, @Body FCMToken fcmToken);
}
