package a.talenting.com.talenting.domain.user;

import com.google.gson.annotations.Expose;

/**
 * Created by user on 2017-12-07.
 */

public class SignupResponse {
    @Expose private String code;
    @Expose private String msg;
    @Expose private User user;

    public boolean isSuccess(){
        return "201".equals(code);
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

    public User getUser ()
    {
        return user;
    }
    public void setUser (User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+", msg = "+msg+", user = "+user+"]";
    }
}
