package a.talenting.com.talenting.domain.hosting.options;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by daeho on 2017. 12. 17..
 */

public class GetCountries {
    @Expose private String code;
    @Expose private List<Countries> countries;
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

    public List<Countries> getCountries ()
    {
        return countries;
    }

    public void setCountries (List<Countries> countries)
    {
        this.countries = countries;
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
        return "ClassPojo [code = "+code+", countries = "+countries+", msg = "+msg+"]";
    }
}
