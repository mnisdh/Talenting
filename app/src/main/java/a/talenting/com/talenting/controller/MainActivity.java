package a.talenting.com.talenting.controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.SigninActivity;
import a.talenting.com.talenting.common.Constants;

import a.talenting.com.talenting.controller.event.EventListView;
import a.talenting.com.talenting.controller.setting.event.SetEventAddActivity;
import a.talenting.com.talenting.controller.setting.event.SetEventListActivity;
import a.talenting.com.talenting.controller.setting.hosting.SetHostingActivity;
import a.talenting.com.talenting.controller.setting.hosting.SetHostingAddActivity;
import a.talenting.com.talenting.controller.setting.profile.ProfileActivity;
import a.talenting.com.talenting.controller.setting.signin.SigninActivity;
import a.talenting.com.talenting.custom.ImageTextButton;

public class MainActivity extends AppCompatActivity {
    private GoogleApiClient mGoogleApiClient;

    private FrameLayout stage;
    private FrameLayout settingMenu;
    private ImageTextButton btnHosting, btnUsers, btnEvent, btnMessage, btnSetting;

    private EventListView hostingListView;
    private EventListView usersListView;
    private EventListView eventListView;
    private EventListView messageListView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // signin 확인

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.d("MainActivity",connectionResult.getErrorMessage());
                    }
                })
                .build();

        initView();

        initHosting();
        initUsers();
        initEvent();
        initMessage();
        initSetting();

        onBtnClick(btnHosting);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView(){
        stage = findViewById(R.id.stage);
    }
    private void initHosting(){
        hostingListView = new EventListView(this);
    }
    private void initUsers(){
        usersListView = new EventListView(this);
    }
    private void initEvent(){
        eventListView = new EventListView(this);
        eventListView.setSampleData();
    }
    private void initMessage(){
        messageListView = new EventListView(this);
    }
    private void initSetting(){
        settingMenu = findViewById(R.id.settingMenu);

        btnHosting = findViewById(R.id.btnHosting);
        btnUsers = findViewById(R.id.btnUsers);
        btnEvent = findViewById(R.id.btnEvent);
        btnMessage = findViewById(R.id.btnMessage);
        btnSetting = findViewById(R.id.btnSetting);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case Constants.REQ_EVENT_PLACE: eventListView.onActivityResult(resultCode, data); break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onBtnClick(View v){
        if(v.getId() == R.id.btnSetting){
            onSetting();
            return;
        }

        btnHosting.changeColor(Color.DKGRAY);
        btnUsers.changeColor(Color.DKGRAY);
        btnEvent.changeColor(Color.DKGRAY);
        btnMessage.changeColor(Color.DKGRAY);
        btnSetting.changeColor(Color.DKGRAY);

        switch (v.getId()){
            case R.id.btnHosting:
                btnHosting.changeColor(Color.MAGENTA);
                onHosting();
                break;
            case R.id.btnUsers:
                btnUsers.changeColor(Color.MAGENTA);
                onSearching();
                break;
            case R.id.btnEvent:
                btnEvent.changeColor(Color.MAGENTA);
                onEvent();
                break;
            case R.id.btnMessage:
                btnMessage.changeColor(Color.MAGENTA);
                onMessage();
                break;
        }
    }
    private void onHosting(){
        stage.removeAllViews();
        stage.addView(hostingListView);
    }
    private void onSearching(){
        stage.removeAllViews();
        stage.addView(usersListView);
    }
    private void onEvent(){
        stage.removeAllViews();
        stage.addView(eventListView);
    }
    private void onMessage(){
        stage.removeAllViews();
        stage.addView(messageListView);
    }

    private void onSetting(){
        settingMenu.setVisibility(View.VISIBLE);
    }
    public void closeSettingMenu(View v){
        settingMenu.setVisibility(View.GONE);
    }
    public void goProfile(View v){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);

        settingMenu.setVisibility(View.GONE);
    }
    public void goRecommend(View v){
        Intent intent = new Intent(this, SigninActivity.class);
        startActivity(intent);

        settingMenu.setVisibility(View.GONE);
    }
    public void goHostingSetting(View v){
        boolean exist = true;

        Intent intent = null;
        if(exist) intent = new Intent(this, SetHostingActivity.class);
        else intent = new Intent(this, SetHostingAddActivity.class);

        startActivity(intent);

        settingMenu.setVisibility(View.GONE);
    }
    public void goEventSetting(View v){
        boolean exist = true;

        Intent intent = null;
        if(exist) intent = new Intent(this, SetEventListActivity.class);
        else intent = new Intent(this, SetEventAddActivity.class);

        startActivity(intent);

        settingMenu.setVisibility(View.GONE);
    }

    public void goLoginTest(View v){
        Intent intent =  new Intent(this, LoginMainActivity.class);

        startActivity(intent);

        settingMenu.setVisibility(View.GONE);
    }
}
