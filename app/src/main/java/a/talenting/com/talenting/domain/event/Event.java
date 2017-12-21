package a.talenting.com.talenting.domain.event;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by daeho on 2017. 12. 20..
 */

public class Event {
    @Expose(serialize = false) private String id;
    @Expose(serialize = false) private String updated_at;
    @Expose(serialize = false) private String primary_photo;
    @Expose(serialize = false) private String wish_status;
    @Expose(serialize = false) private String state;
    @Expose(serialize = false) private Author author;
    @Expose(serialize = false) private List<String> participants;

    @Expose private String event_categories;
    @Expose private String city;
    @Expose private String country;
    @Expose private String title;
    @Expose private String noted_item;
    @Expose private String price;
    @Expose private String maximum_participant;
    @Expose private String program;
    @Expose private String participants_count;
    @Expose private String participants_counter;
    @Expose private String created_at;
    @Expose private String opening_date;
    @Expose private String closing_date;
    @Expose private String event_date;
    @Expose private String lat;
    @Expose private String lon;
    @Expose private String address;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isWish(){
        return !(wish_status == null || wish_status.equals("") || wish_status.toUpperCase().equals("FALSE"));
    }

    public String getWish_status ()
    {
        return wish_status;
    }

    public void setWish_status (String wish_status)
    {
        this.wish_status = wish_status;
    }

    public String getLon ()
    {
        if(lon == null || "".equals(lon)) lon = "0";
        return lon;
    }

    public void setLon (String lon)
    {
        this.lon = lon;
    }

    public String getNoted_item ()
    {
        return noted_item;
    }

    public void setNoted_item (String noted_item)
    {
        this.noted_item = noted_item;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getPrimary_photo ()
    {
        return primary_photo;
    }

    public void setPrimary_photo (String primary_photo)
    {
        this.primary_photo = primary_photo;
    }

    public String getClosing_date ()
    {
        return closing_date;
    }

    public void setClosing_date (String closing_date)
    {
        this.closing_date = closing_date;
    }

    public String getEvent_categories ()
    {
        return event_categories;
    }

    public void setEvent_categories (String event_categories)
    {
        this.event_categories = event_categories;
    }

    public List<String> getParticipants ()
    {
        return participants;
    }

    public void setParticipants (List<String> participants)
    {
        this.participants = participants;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Author getAuthor ()
    {
        return author;
    }

    public void setAuthor (Author author)
    {
        this.author = author;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getMaximum_participant ()
    {
        return maximum_participant;
    }

    public void setMaximum_participant (String maximum_participant)
    {
        this.maximum_participant = maximum_participant;
    }

    public String getProgram ()
    {
        return program;
    }

    public void setProgram (String program)
    {
        this.program = program;
    }

    public String getEvent_date ()
    {
        return event_date;
    }

    public void setEvent_date (String event_date)
    {
        this.event_date = event_date;
    }

    public String getParticipants_counter ()
    {
        return participants_counter;
    }

    public void setParticipants_counter (String participants_counter)
    {
        this.participants_counter = participants_counter;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getOpening_date ()
    {
        return opening_date;
    }

    public void setOpening_date (String opening_date)
    {
        this.opening_date = opening_date;
    }

    public String getLat ()
    {
        if(lat == null || "".equals(lat)) lat = "0";
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    public String getParticipants_count ()
    {
        return participants_count;
    }

    public void setParticipants_count (String participants_count)
    {
        this.participants_count = participants_count;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [wish_status = "+wish_status+", lon = "+lon+", noted_item = "+noted_item+", state = "+state+", primary_photo = "+primary_photo+", closing_date = "+closing_date+", event_categories = "+event_categories+", participants = "+participants+", city = "+city+", country = "+country+", id = "+id+", author = "+author+", title = "+title+", updated_at = "+updated_at+", price = "+price+", maximum_participant = "+maximum_participant+", program = "+program+", event_date = "+event_date+", participants_counter = "+participants_counter+", created_at = "+created_at+", opening_date = "+opening_date+", lat = "+lat+", participants_count = "+participants_count+"]";
    }
}
