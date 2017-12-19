package a.talenting.com.talenting.domain.hosting.photo;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daeho on 2017. 12. 11..
 */

public class GetHostingPhotoList {
    @Expose private List<HostingPhoto> hostingPhoto;
    @Expose private String code;
    @Expose private String msg;

    public boolean isSuccess(){
        return code.substring(0, 1).equals("2");
    }

    public List<HostingPhoto> getHostingPhoto ()
    {
        if(hostingPhoto == null) hostingPhoto = new ArrayList<>();
        return hostingPhoto;
    }

    public void setHostingPhoto (List<HostingPhoto> hosting)
    {
        this.hostingPhoto = hosting;
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
        return "ClassPojo [hostingPhoto = "+hostingPhoto+", code = "+code+", msg = "+msg+"]";
    }
}
