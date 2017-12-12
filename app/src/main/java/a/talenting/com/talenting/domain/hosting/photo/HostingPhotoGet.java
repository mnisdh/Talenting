package a.talenting.com.talenting.domain.hosting.photo;

import java.util.List;

/**
 * Created by daeho on 2017. 12. 11..
 */

public class HostingPhotoGet {
    private List<Hosting> hosting;
    private String code;
    private String msg;

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
