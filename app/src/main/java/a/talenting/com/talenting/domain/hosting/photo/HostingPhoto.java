package a.talenting.com.talenting.domain.hosting.photo;

import com.google.gson.annotations.Expose;

import a.talenting.com.talenting.custom.domain.detailItem.IThumbnailPhoto;

/**
 * Created by daeho on 2017. 12. 13..
 */

public class HostingPhoto implements IThumbnailPhoto {
    @Expose(serialize = false) private String pk;
    @Expose(serialize = false) private String place;
    @Expose(serialize = false) private String hosting_thumbnail;
    @Expose(serialize = false) private String created_at;
    @Expose(serialize = false) private String hosting_image;
    @Expose private String caption;
    @Expose private String type;

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getHosting_image ()
    {
        return hosting_image;
    }

    public void setHosting_image (String hosting_image)
    {
        this.hosting_image = hosting_image;
    }

    public String getCaption ()
    {
        return caption;
    }

    public void setCaption (String caption)
    {
        this.caption = caption;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getPlace ()
    {
        return place;
    }

    public void setPlace (String place)
    {
        this.place = place;
    }

    public String getHosting_thumbnail ()
    {
        return hosting_thumbnail;
    }

    public void setHosting_thumbnail (String hosting_thumbnail)
    {
        this.hosting_thumbnail = hosting_thumbnail;
    }

    public String getPk ()
    {
        return pk;
    }

    public void setPk (String pk)
    {
        this.pk = pk;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [created_at = "+created_at+", hosting_image = "+hosting_image+", caption = "+caption+", type = "+type+", place = "+place+", hosting_thumbnail = "+hosting_thumbnail+", pk = "+pk+"]";
    }

    @Override
    public String getContent() {
        return caption;
    }

    @Override
    public String getImageUrl() {
        return hosting_thumbnail;
    }
}
