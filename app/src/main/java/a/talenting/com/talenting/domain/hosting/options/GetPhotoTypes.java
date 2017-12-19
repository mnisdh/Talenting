package a.talenting.com.talenting.domain.hosting.options;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by daeho on 2017. 12. 17..
 */

public class GetPhotoTypes {
    @Expose private List<PhotoTypes> photo_types;
    @Expose private String code;
    @Expose private String msg;

    public boolean isSuccess(){
        return code.substring(0, 1).equals("2");
    }

    public List<PhotoTypes> getPhoto_types ()
    {
        return photo_types;
    }

    public void setPhoto_types (List<PhotoTypes> photo_types)
    {
        this.photo_types = photo_types;
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
        return "ClassPojo [photo_types = "+photo_types+", code = "+code+", msg = "+msg+"]";
    }
}
