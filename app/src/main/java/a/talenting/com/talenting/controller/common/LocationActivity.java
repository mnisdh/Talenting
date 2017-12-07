package a.talenting.com.talenting.controller.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.Constants;

public class LocationActivity extends AppCompatActivity {
    private MapView mapView;
    private GoogleMap googleMap;

    private LatLng latlng;
    private boolean isDetail = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        double lat = getIntent().getDoubleExtra(Constants.EXT_LAT, 0);
        double lng = getIntent().getDoubleExtra(Constants.EXT_LNG, 0);
        latlng = new LatLng(lat, lng);
        isDetail = getIntent().getBooleanExtra(Constants.EXT_IS_DETAIL, false);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(googleMap -> init(googleMap));
    }

    private void init(GoogleMap googleMap){
        this.googleMap = googleMap;
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        //googleMap.setMyLocationEnabled(true);
        MapsInitializer.initialize(this);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latlng, 14);
        this.googleMap.animateCamera(cameraUpdate);

        if(isDetail) addMarker();
        else addCircle();
    }

    private void addMarker(){
        googleMap.addMarker(new MarkerOptions().position(latlng));
    }

    private void addCircle(){
        googleMap.addCircle(new CircleOptions()
                .center(latlng)
                .radius(500)
                .strokeWidth(1)
                .strokeColor(Color.RED)
                .fillColor(R.color.circleLocation)
        );
    }

    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }



}
