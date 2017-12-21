package a.talenting.com.talenting.domain.hosting.options;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by daeho on 2017. 12. 17..
 */

public class GetHouseTypes {
    @Expose private String code;
    @Expose private String msg;
    @Expose private List<HouseTypes> house_types;

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

    public List<HouseTypes> getHouse_types ()
    {
        return house_types;
    }

    public void setHouse_types (List<HouseTypes> house_types)
    {
        this.house_types = house_types;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+", msg = "+msg+", house_types = "+house_types+"]";
    }
}
