package a.talenting.com.talenting.domain.fcm;

import com.google.gson.annotations.Expose;

/**
 * Created by daeho on 2017. 12. 29..
 */

public class FCMMessage {
    @Expose private String to_user;
    @Expose private String body;

    public String getTo_user() {
        return to_user;
    }

    public void setTo_user(String to_user) {
        this.to_user = to_user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
