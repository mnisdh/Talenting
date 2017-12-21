package a.talenting.com.talenting.domain.profile.mytrip;

import com.google.gson.annotations.Expose;

/**
 * Created by user on 2017-12-21.
 */

public class MyTripResponse {
    @Expose
    private Mytrip[] mytrip;
    @Expose
    private String code;
    @Expose
    private String msg;

    public boolean isSuccess(){
        return code.substring(0, 1).equals("2");
    }

    public Mytrip[] getMytrip ()
    {
        return mytrip;
    }


    public void setMytrip (Mytrip[] mytrip)
    {
        this.mytrip = mytrip;
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

    @Override
    public String toString()
    {
        return "ClassPojo [mytrip = "+mytrip+", code = "+code+", msg = "+msg+"]";
    }
}
