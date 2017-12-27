package a.talenting.com.talenting.domain.profile.trip;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by user on 2017-12-21.
 */

public class MyTripResponse {
    @Expose
    private List<MyTrip> my_trip;
    @Expose
    private String code;
    @Expose
    private String msg;

    public boolean isSuccess(){
        return code.substring(0, 1).equals("2");
    }

    public List<MyTrip> getMytrip ()
    {
        return my_trip;
    }


    public void setMytrip (List<MyTrip> my_trip)
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
