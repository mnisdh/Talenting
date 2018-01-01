package a.talenting.com.talenting.domain.fcm;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by daeho on 2017. 12. 29..
 */

public class GetChatDetail {
    @Expose private String code;
    @Expose private String msg;
    @Expose private List<SentMessage> messages;

    public boolean isSuccess(){
        return code.substring(0, 1).equals("2");
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

    public List<SentMessage> getMessages ()
    {
        return messages;
    }

    public void setMessages (List<SentMessage> messages)
    {
        this.messages = messages;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+", msg = "+msg+", messages = "+messages+"]";
    }
}
