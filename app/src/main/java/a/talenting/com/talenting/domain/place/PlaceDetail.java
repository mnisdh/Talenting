package a.talenting.com.talenting.domain.place;

import com.google.gson.annotations.Expose;

/**
 * Created by daeho on 2017. 12. 21..
 */

public class PlaceDetail {
    @Expose private PlaceDetailResult result;
    @Expose private String status;

    public boolean isSuccess(){
        return "OK".equals(status);
    }

    public PlaceDetailResult getResult() {
        return result;
    }

    public void setResult(PlaceDetailResult result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
