package a.talenting.com.talenting.domain.user;

import com.google.gson.annotations.Expose;

/**
 * Created by user on 2017-12-08.
 */

public class LoginResponse {
    @Expose private String token;
    @Expose private String code;
    @Expose private String msg;
    @Expose private User user;

    public boolean isSuccess(){
        return "2".equals(code.substring(0, 1));
    }

    public String getToken ()
    {
        return token;
    }

    public void setToken (String token)
    {
        this.token = token;
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
        return "ClassPojo [token = "+token+", code = "+code+", msg = "+msg+", user = "+user+"]";
    }
}
