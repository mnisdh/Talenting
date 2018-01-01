package a.talenting.com.talenting.domain.fcm;

import com.google.gson.annotations.Expose;

/**
 * Created by daeho on 2017. 12. 29..
 */

public class SentMessage {
    @Expose private String body;
    @Expose private FromUser from_user;
    @Expose private String created_at;
    @Expose private String sent_by;

    public String getBody ()
    {
        return body;
    }

    public void setBody (String body)
    {
        this.body = body;
    }

    public FromUser getFrom_user ()
    {
        return from_user;
    }

    public void setFrom_user (FromUser from_user)
    {
        this.from_user = from_user;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getSent_by ()
    {
        return sent_by;
    }

    public void setSent_by (String sent_by)
    {
        this.sent_by = sent_by;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [body = "+body+", from_user = "+from_user+", created_at = "+created_at+", sent_by = "+sent_by+"]";
    }
}
