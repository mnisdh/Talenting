package a.talenting.com.talenting.domain.profile;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.domain.profile.photo.ProfileImage;

/**
 * Created by user on 2017-12-13.
 */

public class Profile implements Serializable{
    @Expose(serialize = false)
    private String first_name;
    @Expose(serialize = false)
    private String last_name;
    @Expose(serialize = false)
    private String age;
    @Expose(serialize = false)
    private String pk;
    @Expose
    private String occupation;
    @Expose
    private String birth;
    @Expose
    private String talent_intro;
    @Expose
    private List<String> talent_category;
    @Expose
    private String gender;
    @Expose
    private String self_intro;
    @Expose
    private List<String> available_languages;
    @Expose
    private String city;
    @Expose
    private String country;
    @Expose(serialize=false)
    private List<ProfileImage> images;
    @Expose(serialize=false)
    private boolean wishList;

    public String getProfilePk(){
        return pk;
    }

    public void setProfilePk(String pk) {
        this.pk = pk;
    }

    public boolean getWishList(){
        return wishList;
    }

    public void setWishList(boolean wishList){
        this.wishList = wishList;
    }

    public List<ProfileImage> getImages ()
    {
        return images;
    }

    public void setImages (List<ProfileImage> images)
    {
        this.images = images;
    }
    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getOccupation ()
    {
        return occupation;
    }

    public void setOccupation (String occupation)
    {
        this.occupation = occupation;
    }

    public String getBirth ()
    {
        return birth;
    }

    public void setBirth (String birth)
    {
        this.birth = birth;
    }

    public String getTalent_intro ()
    {
        return talent_intro;
    }

    public void setTalent_intro (String talent_intro)
    {
        this.talent_intro = talent_intro;
    }

    public List<String> getTalent_category ()
    {
        if(talent_category==null) talent_category=new ArrayList<>();
        return talent_category;
    }

    public void setTalent_category (List<String> talent_category)
    {
        this.talent_category = talent_category;
    }

    public String getAge ()
    {
        return age;
    }

    public void setAge (String age)
    {
        this.age = age;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    public String getSelf_intro ()
    {
        return self_intro;
    }

    public void setSelf_intro (String self_intro)
    {
        this.self_intro = self_intro;
    }

    public List<String> getAvailable_languages ()
    {
        if(available_languages==null) available_languages=new ArrayList<>();
        return available_languages;
    }

    public void setAvailable_languages (List<String> available_languages)
    {
        this.available_languages = available_languages;
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

    @Override
    public String toString()
    {
        return "ClassPojo [first_name = "+first_name+", occupation = "+occupation+", birth = "+birth+", talent_intro = "+talent_intro+", talent_category = "+talent_category+", age = "+age+", last_name = "+last_name+", gender = "+gender+", self_intro = "+self_intro+", available_languages = "+available_languages+", city = "+city+", country = "+country+"]";
    }
}
