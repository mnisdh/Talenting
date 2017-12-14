package a.talenting.com.talenting.domain.profile.photo;

import com.google.gson.annotations.Expose;

/**
 * Created by user on 2017-12-14.
 */

public class ProfileImageResponse {
    @Expose
    private String code;
    @Expose
    private String msg;
    @Expose
    private ProfileImage profileimage;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getMsg ()
    {
        return msg;
    }

    public void setMsg (String msg)
    {
        this.msg = msg;
    }

    public ProfileImage getProfileimage ()
    {
        return profileimage;
    }

    public void setProfileimage (ProfileImage profileimage)
    {
        this.profileimage = profileimage;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+", msg = "+msg+", profileimage = "+profileimage+"]";
    }
}
