package a.talenting.com.talenting.domain.user;

/**
 * Created by user on 2017-12-08.
 */

public class UserSignup {
    private String first_name;

    private String password1;

    private String password2;

    private String email;

    private String last_name;

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getPassword1 ()
    {
        return password1;
    }

    public void setPassword1 (String password1)
    {
        this.password1 = password1;
    }

    public String getPassword2 ()
    {
        return password2;
    }

    public void setPassword2 (String password2)
    {
        this.password2 = password2;
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

    @Override
    public String toString()
    {
        return "ClassPojo [first_name = "+first_name+", password1 = "+password1+", password2 = "+password2+", email = "+email+", last_name = "+last_name+"]";
    }
}
