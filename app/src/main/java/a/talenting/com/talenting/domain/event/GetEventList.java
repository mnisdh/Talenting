package a.talenting.com.talenting.domain.event;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by daeho on 2017. 12. 17..
 */

public class GetEventList {
    @Expose private List<Event> event;
    @Expose private String code;
    @Expose private String msg;

    public boolean isSuccess(){
        return code.substring(0, 1).equals("2");
    }

    public List<Event> getEvent ()
    {
        return event;
    }

    public void setEvent (List<Event> event)
    {
        this.event = event;
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
        return "ClassPojo [event = "+event+", code = "+code+", msg = "+msg+"]";
    }
}
