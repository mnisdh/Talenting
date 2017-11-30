package a.talenting.com.talenting.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.SigninActivity;
import a.talenting.com.talenting.controller.setting.hosting.HostingListActivity;
import a.talenting.com.talenting.controller.setting.profile.ProfileActivity;

public class MainActivity extends AppCompatActivity {
    private FrameLayout settingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // signin 확인

        initView();
    }

    private void initView(){
        settingMenu = findViewById(R.id.settingMenu);

    }

    public void onHosting(View v){

    }

    public void onSearching(View v){

    }

    public void onEvent(View v){

    }

    public void onMessage(View v){

    }

    public void onSetting(View v){
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
