package a.talenting.com.talenting.domain.fcm;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by user on 2017-12-07.
 */

public interface IFCMApiService {
    @POST("/fcm/device/")
    Observable<FCMToken> insert(@Header("Authorization") String token, @Body FCMToken fcmToken);

    @POST("/fcm/chat/send-message/")
    Observable<GetMessage> sendMessage(@Header("Authorization") String token, @Body FCMMessage fcmMessage);

    @GET("/fcm/chat-list/")
    Observable<GetChatList> chatList(@Header("Authorization") String token);

    @GET("/fcm/chat/{pk}/")
    Observable<GetChatDetail> chat(@Header("Authorization") String token, @Path("pk") String pk);
}
