package a.talenting.com.talenting.domain.fcm;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by daeho on 2017. 12. 29..
 */

public class GetChatList {
    @Expose private String code;
    @Expose private List<Chat> chat;
    @Expose private String msg;

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

    public List<Chat> getChat ()
    {
        return chat;
    }

    public void setChat (List<Chat> chat)
    {
        this.chat = chat;
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
        return "ClassPojo [code = "+code+", chat = "+chat+", msg = "+msg+"]";
    }
}
