package a.talenting.com.talenting.domain.hosting;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by daeho on 2017. 12. 11..
 */

public class GetHostingList {
    @Expose private List<Hosting> hosting;
    @Expose private String code;
    @Expose private String msg;

    public boolean isSuccess(){
        return code.substring(0, 1).equals("2");
    }

    public List<Hosting> getHosting ()
    {
        return hosting;
    }

    public void setHosting (List<Hosting> hosting)
    {
        this.hosting = hosting;
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
        return "ClassPojo [hosting = "+hosting+", code = "+code+", msg = "+msg+"]";
    }
}
