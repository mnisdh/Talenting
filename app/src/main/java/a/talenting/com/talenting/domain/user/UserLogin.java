package a.talenting.com.talenting.domain.user;

/**
 * Created by user on 2017-12-08.
 */

public class UserLogin
{
    private String email;

    private String password;

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [email = "+email+", password = "+password+"]";
    }
}
