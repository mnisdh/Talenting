package a.talenting.com.talenting.common;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;

import a.talenting.com.talenting.util.FormatUtil;

/**
 * Created by daeho on 2017. 12. 7..
 */

public class GoogleStaticMap {
    private static final String STATIC_MAP_KEY = "AIzaSyD-lqaD_2Vq9KfZkVU7CRP_htm7oL4sifA";
    private static final String URL_STATIC = "https://maps.googleapis.com/maps/api/staticmap?";

    private int zoom = 14;
    private int width = 1000;
    private int height = 600;
    private String mapType = "roadmap";
    private String url = "";
    private Map<LatLng, String> latlngs = new HashMap<>();

    public GoogleStaticMap(){

    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
        updateUrl();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        updateUrl();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        updateUrl();
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
        updateUrl();
    }

    public String getUrl(){
        return url;
    }

    private void updateUrl(){
        if(latlngs.size() == 0) {
            this.url = "";
            return;
        }

        String url = URL_STATIC + "zoom=" + zoom;
        url += "&center=" + getLat() + "," + getLng();
        url += "&size=" + width + "x" + height;
        url += "&maptype=" + mapType;

        for(LatLng latlng : latlngs.keySet()){
            String color = latlngs.get(latlng);
            url += "&markers=color:" + color + " label:" + latlng.latitude + "," + latlng.longitude;
        }

        this.url = url + "&key=" + STATIC_MAP_KEY;
    }

    public void clearMarker(){
        latlngs.clear();
        updateUrl();
    }

    private double minLat, maxLat, minLng, maxLng;
    public void setLatlng(double lat, double lng, int color){
        latlngs.clear();
        latlngs.put(new LatLng(lat, lng), FormatUtil.intColorToHexString(color));
        updateCoordinate(lat, lng);
        updateUrl();
    }

    public void setLatlng(LatLng latlng, int color){
        latlngs.clear();
        latlngs.put(latlng, FormatUtil.intColorToHexString(color));
        updateCoordinate(latlng.latitude, latlng.longitude);
        updateUrl();
    }

    public LatLng getLatLng(){
        for(LatLng latlng : latlngs.keySet()){
            return latlng;
        }
        return new LatLng(0, 0);
    }

    private void updateCoordinate(double lat, double lng){
        if(latlngs.size() == 0) return;

        if(latlngs.size() == 1){
            minLat = lat;
            maxLat = lat;
            minLng = lng;
            maxLng = lng;
        }
        else{
            if(lat < minLat) minLat = lat;
            if(lat > maxLat) maxLat = lat;
            if(lng < minLng) minLng = lng;
            if(lng > maxLng) maxLng = lng;
        }
    }

    private double getLng(){
        return minLng + (maxLng - minLng);
    }
    private double getLat(){
        return minLat + (maxLat - minLat);
    }

}
