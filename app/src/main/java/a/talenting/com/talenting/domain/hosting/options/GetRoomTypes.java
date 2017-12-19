package a.talenting.com.talenting.domain.hosting.options;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by daeho on 2017. 12. 17..
 */

public class GetRoomTypes {
    @Expose private List<RoomTypes> room_types;
    @Expose private String code;
    @Expose private String msg;

    public boolean isSuccess(){
        return code.substring(0, 1).equals("2");
    }

    public List<RoomTypes> getRoom_types ()
    {
        return room_types;
    }

    public void setRoom_types (List<RoomTypes> room_types)
    {
        this.room_types = room_types;
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
        return "ClassPojo [room_types = "+room_types+", code = "+code+", msg = "+msg+"]";
    }
}
