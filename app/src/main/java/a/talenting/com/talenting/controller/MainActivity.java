package a.talenting.com.talenting.controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.SigninActivity;
import a.talenting.com.talenting.controller.setting.hosting.HostingListActivity;
import a.talenting.com.talenting.controller.setting.profile.ProfileActivity;
import a.talenting.com.talenting.custom.ImageTextButton;

public class MainActivity extends AppCompatActivity {
    private FrameLayout settingMenu;
    private ImageTextButton btnHosting, btnUsers, btnEvent, btnMessage, btnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // signin 확인

        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView(){
        settingMenu = findViewById(R.id.settingMenu);

        btnHosting = findViewById(R.id.btnHosting);
        btnUsers = findViewById(R.id.btnUsers);
        btnEvent = findViewById(R.id.btnEvent);
        btnMessage = findViewById(R.id.btnMessage);
        btnSetting = findViewById(R.id.btnSetting);

        onBtnClick(btnHosting);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onBtnClick(View v){
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
            case R.id.btnSetting:
                btnSetting.changeColor(Color.MAGENTA);
                onSetting();
                break;
        }
    }

    private void onHosting(){

    }

    private void onSearching(){

    }

    private void onEvent(){

    }

    private void onMessage(){

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
        Intent intent = new Intent(this, HostingListActivity.class);
        startActivity(intent);

        settingMenu.setVisibility(View.GONE);
    }

    public void goEventSetting(View v){
        Intent intent = new Intent(this, SigninActivity.class);
        startActivity(intent);

        settingMenu.setVisibility(View.GONE);
    }

    public void goSignin(View v){
        Intent intent = new Intent(this, SigninActivity.class);
        startActivity(intent);
    }
}
