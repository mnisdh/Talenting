package a.talenting.com.talenting.domain.event.photo;

import com.google.gson.annotations.Expose;

import a.talenting.com.talenting.custom.domain.detailItem.IThumbnailPhoto;

/**
 * Created by daeho on 2017. 12. 20..
 */

public class EventPhoto implements IThumbnailPhoto {
    @Expose private String id;
    @Expose private String events;
    @Expose private String created_at;
    @Expose private String image;
    @Expose private String image_thumbnail;

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

    public String getImage_thumbnail() {
        return image_thumbnail;
    }

    public void setImage_thumbnail(String image_thumbnail) {
        this.image_thumbnail = image_thumbnail;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", events = "+events+", created_at = "+created_at+", image = "+image+"]";
    }

    @Override
    public String getContent() {
        return "";
    }

    @Override
    public String getImageUrl() {
        return image_thumbnail;
    }
}
