package a.talenting.com.talenting.domain.profile.photo;

import com.google.gson.annotations.Expose;

import a.talenting.com.talenting.custom.domain.detailItem.IThumbnailPhoto;

/**
 * Created by user on 2017-12-14.
 */

public class ProfileImage implements IThumbnailPhoto{
    @Expose
    private String profile_thumbnail;
    @Expose
    private String created_at;
    @Expose
    private String image;
    @Expose
    private String pk;

    public String getProfile_thumbnail ()
    {
        return profile_thumbnail;
    }

    public void setProfile_thumbnail (String profile_thumbnail)
    {
        this.profile_thumbnail = profile_thumbnail;
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
        return "ClassPojo [profile_thumbnail = "+profile_thumbnail+", created_at = "+created_at+", image = "+image+", pk = "+pk+"]";
    }

    @Override
    public String getContent() {
        return "";
    }

    @Override
    public String getImageUrl() {
        return profile_thumbnail;
    }
}
