package a.talenting.com.talenting.domain.place;

import com.google.gson.annotations.Expose;

/**
 * Created by daeho on 2017. 12. 21..
 */

public class PlaceDetailResult {
    @Expose String formatted_address;

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }
}
