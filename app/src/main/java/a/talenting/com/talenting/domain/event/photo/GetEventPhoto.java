package a.talenting.com.talenting.domain.event.photo;

import com.google.gson.annotations.Expose;

/**
 * Created by daeho on 2017. 12. 20..
 */

public class GetEventPhoto {
    @Expose private String code;
    @Expose private String msg;
    @Expose private EventPhoto event_photo;

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

    public EventPhoto getEvent_photo ()
    {
        return event_photo;
    }

    public void setEvent_photo (EventPhoto event_photo)
    {
        this.event_photo = event_photo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+", msg = "+msg+", event_photo = "+event_photo+"]";
    }
}
