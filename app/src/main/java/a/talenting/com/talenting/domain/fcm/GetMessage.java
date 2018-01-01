package a.talenting.com.talenting.domain.fcm;

import com.google.gson.annotations.Expose;

/**
 * Created by daeho on 2017. 12. 29..
 */

public class GetMessage {
    @Expose private SentMessage sent_message;
    @Expose private String code;
    @Expose private String msg;

    public boolean isSuccess(){
        return code.substring(0, 1).equals("2");
    }

    public SentMessage getSent_message ()
    {
        return sent_message;
    }

    public void setSent_message (SentMessage sent_message)
    {
        this.sent_message = sent_message;
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
        return "ClassPojo [sent_message = "+sent_message+", code = "+code+", msg = "+msg+"]";
    }
}
