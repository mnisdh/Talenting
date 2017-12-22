package a.talenting.com.talenting.domain.profile.mytrip;

import com.google.gson.annotations.Expose;

/**
 * Created by user on 2017-12-21.
 */

public class MyTripResponse {
    @Expose
    private My_trip[] my_trip;
    @Expose
    private String code;
    @Expose
    private String msg;

    public boolean isSuccess(){
        return code.substring(0, 1).equals("2");
    }

    public My_trip[] getMytrip ()
    {
        return my_trip;
    }


    public void setMytrip (My_trip[] mytrip)
    {
        this.my_trip = my_trip;
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
        return "ClassPojo [mytrip = "+my_trip+", code = "+code+", msg = "+msg+"]";
    }
}
