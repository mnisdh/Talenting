package a.talenting.com.talenting.domain.fcm;

import com.google.gson.annotations.Expose;

/**
 * Created by daeho on 2017. 12. 11..
 */

public class FCMToken {
    @Expose(serialize = false)
    private String id;
    @Expose(serialize = false)
    private String active;
    @Expose(serialize = false)
    private String date_created;
    @Expose(serialize = false)
    private String type;

    @Expose private String registration_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(String registration_id) {
        this.registration_id = registration_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", active = "+active+", date_created = "+date_created+", type = "+type+", registration_id = "+registration_id+"]";
    }
}
