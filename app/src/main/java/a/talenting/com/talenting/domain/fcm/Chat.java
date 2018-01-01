package a.talenting.com.talenting.domain.fcm;

import com.google.gson.annotations.Expose;

/**
 * Created by daeho on 2017. 12. 29..
 */

public class Chat {
    @Expose private String body;
    @Expose private TargetUser target_user;
    @Expose private String created_at;
    @Expose private String sent_by;
    @Expose private String pk;

    public String getBody ()
    {
        return body;
    }

    public void setBody (String body)
    {
        this.body = body;
    }

    public TargetUser getTarget_user ()
    {
        return target_user;
    }

    public void setTarget_user (TargetUser target_user)
    {
        this.target_user = target_user;
    }

    public String getSent_by() {
        return sent_by;
    }

    public void setSent_by(String sent_by) {
        this.sent_by = sent_by;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getPk ()
    {
        return pk;
    }

    public void setPk (String pk)
    {
        this.pk = pk;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [body = "+body+", target_user = "+target_user+", created_at = "+created_at+", pk = "+pk+"]";
    }
}
