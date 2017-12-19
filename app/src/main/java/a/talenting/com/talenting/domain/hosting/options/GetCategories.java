package a.talenting.com.talenting.domain.hosting.options;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by daeho on 2017. 12. 17..
 */

public class GetCategories {
    @Expose private List<Categories> categories;
    @Expose private String code;
    @Expose private String msg;

    public boolean isSuccess(){
        return code.substring(0, 1).equals("2");
    }

    public List<Categories> getCategories ()
    {
        return categories;
    }

    public void setCategories (List<Categories> categories)
    {
        this.categories = categories;
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
        return "ClassPojo [categories = "+categories+", code = "+code+", msg = "+msg+"]";
    }
}
