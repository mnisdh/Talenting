package a.talenting.com.talenting.domain.event;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by daeho on 2017. 12. 17..
 */

public class GetJoinedEventList {
    @Expose private List<JoinedEvent> event;
    @Expose private String code;
    @Expose private String msg;

    public boolean isSuccess(){
        return code.substring(0, 1).equals("2");
    }

    public List<JoinedEvent> getEvent ()
    {
        return event;
    }

    public void setEvent (List<JoinedEvent> event)
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
