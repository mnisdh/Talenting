package a.talenting.com.talenting.domain.hosting.options;

import com.google.gson.annotations.Expose;

/**
 * Created by daeho on 2017. 12. 17..
 */

public class RoomTypes {
    @Expose private String value;
    @Expose private String code;

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [value = "+value+", code = "+code+"]";
    }
}
