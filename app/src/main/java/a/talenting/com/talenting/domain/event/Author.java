package a.talenting.com.talenting.domain.event;

/**
 * Created by daeho on 2017. 12. 17..
 */

public class Author {
    private String email;
    private String pk;

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
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
        return "ClassPojo [email = "+email+", pk = "+pk+"]";
    }
}
