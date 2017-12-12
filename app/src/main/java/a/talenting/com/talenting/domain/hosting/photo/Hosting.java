package a.talenting.com.talenting.domain.hosting.photo;

/**
 * Created by daeho on 2017. 12. 11..
 */

public class Hosting {
    private String hosting_image;
    private String caption;
    private String type;
    private String place;
    private String pk;

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
        return "ClassPojo [hosting_image = "+hosting_image+", caption = "+caption+", type = "+type+", place = "+place+", pk = "+pk+"]";
    }
}
