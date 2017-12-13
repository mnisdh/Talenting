package a.talenting.com.talenting.domain.profile;

/**
 * Created by user on 2017-12-13.
 */

public class ProfileResponse
{
    private String code;

    private String msg;

    private Profile profile;

    public boolean isSuccess(){
        return "200".equals(code);
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
