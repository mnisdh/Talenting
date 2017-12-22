package a.talenting.com.talenting.domain.profile.mytrip;

import com.google.gson.annotations.Expose;

/**
 * Created by user on 2017-12-21.
 */

public class My_trip {
    @Expose
    private String arrival_date;
    @Expose
    private String description;
    @Expose
    private String number_travelers;
    @Expose
    private String departure_date;
    @Expose(serialize = false)
    private String user;
    @Expose
    private String destination;
    @Expose(serialize = false)
    private String pk;

    public String getArrival_date ()
    {
        return arrival_date;
    }

    public void setArrival_date (String arrival_date)
    {
        this.arrival_date = arrival_date;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getNumber_travelers ()
    {
        return number_travelers;
    }

    public void setNumber_travelers (String number_travelers)
    {
        this.number_travelers = number_travelers;
    }

    public String getDeparture_date ()
    {
        return departure_date;
    }

    public void setDeparture_date (String departure_date)
    {
        this.departure_date = departure_date;
    }

    public String getUser ()
    {
        return user;
    }

    public void setUser (String user)
    {
        this.user = user;
    }

    public String getDestination ()
    {
        return destination;
    }

    public void setDestination (String destination)
    {
        this.destination = destination;
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
        return "ClassPojo [arrival_date = "+arrival_date+", description = "+description+", number_travelers = "+number_travelers+", departure_date = "+departure_date+", user = "+user+", destination = "+destination+", pk = "+pk+"]";
    }

}
