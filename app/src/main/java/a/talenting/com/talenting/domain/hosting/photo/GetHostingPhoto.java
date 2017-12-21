package a.talenting.com.talenting.domain.hosting.photo;

import com.google.gson.annotations.Expose;

/**
 * Created by daeho on 2017. 12. 11..
 */

public class GetHostingPhoto {
    @Expose private HostingPhoto hosting_photo;
    @Expose private String code;
    @Expose private String msg;

    public boolean isSuccess(){
        return code.substring(0, 1).equals("2");
    }

    public HostingPhoto getHostingPhoto ()
    {
        return hosting_photo;
    }

    public void setHostingPhoto (HostingPhoto hosting)
    {
        this.hosting_photo = hosting;
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
        return "ClassPojo [hostingPhoto = "+hosting_photo+", code = "+code+", msg = "+msg+"]";
    }
}
