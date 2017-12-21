package a.talenting.com.talenting.domain.hosting.options;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by daeho on 2017. 12. 17..
 */

public class GetInternetTypes {
    @Expose private String code;
    @Expose private List<InternetTypes> internet_types;
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

    public List<InternetTypes> getInternet_types ()
    {
        return internet_types;
    }

    public void setInternet_types (List<InternetTypes> internet_types)
    {
        this.internet_types = internet_types;
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
        return "ClassPojo [code = "+code+", internet_types = "+internet_types+", msg = "+msg+"]";
    }
}
