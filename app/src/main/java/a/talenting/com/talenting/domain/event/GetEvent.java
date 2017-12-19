package a.talenting.com.talenting.domain.event;

import com.google.gson.annotations.Expose;

/**
 * Created by daeho on 2017. 12. 17..
 */

public class GetEvent {
    @Expose private Event event;
    @Expose private String code;
    @Expose private String msg;

    public Event getEvent ()
    {
        return event;
    }

    public void setEvent (Event event)
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
