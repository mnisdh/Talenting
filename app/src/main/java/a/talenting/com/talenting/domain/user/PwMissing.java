package a.talenting.com.talenting.domain.user;

import com.google.gson.annotations.Expose;

/**
 * Created by user on 2017-12-12.
 */

public class PwMissing {
    @Expose
    private String first_name;
    @Expose
    private String email;
    @Expose
    private String last_name;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "ClassPojo [first_name = " + first_name + ", email = " + email + ", last_name = " + last_name + "]";
    }
}
