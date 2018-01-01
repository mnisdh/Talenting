package a.talenting.com.talenting.domain.fcm;

import com.google.gson.annotations.Expose;

/**
 * Created by daeho on 2017. 12. 29..
 */

public class TargetUser {
    @Expose private String first_name;
    @Expose private String email;
    @Expose private String last_name;
    @Expose private String image;
    @Expose private String pk;

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
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
        return "ClassPojo [first_name = "+first_name+", email = "+email+", last_name = "+last_name+", image = "+image+", pk = "+pk+"]";
    }
}
