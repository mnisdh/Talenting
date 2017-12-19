package a.talenting.com.talenting.domain.event.photo;

import com.google.gson.annotations.Expose;

/**
 * Created by daeho on 2017. 12. 20..
 */

public class EventPhoto {
    @Expose private String id;
    @Expose private String events;
    @Expose private String created_at;
    @Expose private String image;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEvents ()
    {
        return events;
    }

    public void setEvents (String events)
    {
        this.events = events;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", events = "+events+", created_at = "+created_at+", image = "+image+"]";
    }
}