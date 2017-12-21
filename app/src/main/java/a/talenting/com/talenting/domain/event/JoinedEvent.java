package a.talenting.com.talenting.domain.event;

import com.google.gson.annotations.Expose;

/**
 * Created by daeho on 2017. 12. 20..
 */

public class JoinedEvent {
    @Expose(serialize = false) private String pk;
    @Expose(serialize = false) private String title;
    @Expose(serialize = false) private String primary_photo;
    @Expose(serialize = false) private Author author;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrimary_photo() {
        return primary_photo;
    }

    public void setPrimary_photo(String primary_photo) {
        this.primary_photo = primary_photo;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pk = "+pk+", title = "+title+", primary_photo = "+primary_photo+", author = "+author+"]";
    }
}
