package a.talenting.com.talenting.domain.profile;

import com.google.gson.annotations.Expose;

/**
 * Created by user on 2017-12-13.
 */

public class ProfileResponse
{
    @Expose private String code;
    @Expose private String msg;
    @Expose private Profile profile;

    public boolean isSuccess(){
        return code.substring(0, 1).equals("2");
    }

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

    public Profile getProfile ()
    {
        return profile;
    }

    public void setProfile (Profile profile)
    {
        this.profile = profile;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+", msg = "+msg+", profile = "+profile+"]";
    }
}
