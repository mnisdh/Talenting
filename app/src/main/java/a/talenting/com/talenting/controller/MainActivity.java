package a.talenting.com.talenting.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import a.talenting.com.talenting.ApplicationInitializer;
import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.ActivityResultManager;
import a.talenting.com.talenting.common.Constants;
import a.talenting.com.talenting.common.SharedPreferenceManager;
import a.talenting.com.talenting.controller.event.EventListView;
import a.talenting.com.talenting.controller.hosting.HostingListView;
import a.talenting.com.talenting.controller.message.MessageListView;
import a.talenting.com.talenting.controller.setting.event.SetEventListActivity;
import a.talenting.com.talenting.controller.setting.event.SetJoinedEventListActivity;
import a.talenting.com.talenting.controller.setting.hosting.SetHostingAddActivity;
import a.talenting.com.talenting.controller.setting.profile.SetProfileEditActivity;
import a.talenting.com.talenting.controller.setting.signup.SignupActivity;
import a.talenting.com.talenting.controller.user.UserListView;
import a.talenting.com.talenting.custom.ImageTextButton;
import a.talenting.com.talenting.domain.BaseData;

public class MainActivity extends AppCompatActivity {
    private ActivityResultManager activityResultManager;

    private GoogleApiClient mGoogleApiClient;

    private FrameLayout stage;
    private FrameLayout settingMenu;
    private ImageTextButton btnHosting, btnUsers, btnEvent, btnMessage, btnSetting;
    private CardView card_profile, card_hosting, card_event, card_review, card_logout,card_myEvent,card_joinEvent;

    private HostingListView hostingListView;
    private UserListView usersListView;
    private EventListView eventListView;
    private MessageListView messageListView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityResultManager = new ActivityResultManager();
        BaseData.init(isSuccess -> {});

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.d("MainActivity", connectionResult.getErrorMessage());
                    }
                })
                .build();

        Intent intent =  new Intent(this, LoginMainActivity.class);
        startActivityForResult(intent, Constants.REQ_LOG_IN_ACTIVITY);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init(){
        initView();

        initHosting();
        initUsers();
        initEvent();
        initMessage();
        initSetting();

        onBtnClick(btnHosting);
    }

    @Override
    public void onBackPressed() {
        if(settingMenu.getVisibility()==View.VISIBLE){
            closeSettingMenu();
        }else{
            Intent intent = new Intent(Intent.ACTION_MAIN);

            intent.addCategory(Intent.CATEGORY_HOME);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }
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
        hostingListView = new HostingListView(this, activityResultManager);
    }
    private void initUsers(){
        usersListView = new UserListView(this, activityResultManager);
    }
    private void initEvent(){
        eventListView = new EventListView(this, activityResultManager);
    }
    private void initMessage(){
        messageListView = new MessageListView(this, activityResultManager);
        messageListView.setSampleData();
    }
    private void initSetting(){
        settingMenu = findViewById(R.id.settingMenu);

        btnHosting = findViewById(R.id.btnHosting);
        btnUsers = findViewById(R.id.btnUsers);
        btnEvent = findViewById(R.id.btnEvent);
        btnMessage = findViewById(R.id.btnMessage);
        btnSetting = findViewById(R.id.btnSetting);
        card_profile = findViewById(R.id.card_profile);
        card_hosting = findViewById(R.id.card_hosting);
        card_event = findViewById(R.id.card_event);
        card_review = findViewById(R.id.card_review);
        card_logout = findViewById(R.id.card_logout);
        card_myEvent = findViewById(R.id.card_myEvent);
        card_joinEvent = findViewById(R.id.card_joinEvent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQ_LOG_IN_ACTIVITY){
            init();
            return;
        }

        activityResultManager.onActivityResult(requestCode, resultCode, data);
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
        anim();
    }

    private void anim(){
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.profile_anim);
        card_profile.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.hosting_anim);
        card_hosting.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.event_anim);
        card_event.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.review_anim);
        card_review.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.logout_anim);
        card_logout.startAnimation(anim);
    }

    private void subAnim(){
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.profile_anim);
        card_joinEvent.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.hosting_anim);
        card_myEvent.startAnimation(anim);
    }
    public void closeSettingMenu(View v){
        settingMenu.setVisibility(View.GONE);
        card_myEvent.setVisibility(View.GONE);
        card_joinEvent.setVisibility(View.GONE);
    }

    public void closeSettingMenu(){
        settingMenu.setVisibility(View.GONE);
        card_myEvent.setVisibility(View.GONE);
        card_joinEvent.setVisibility(View.GONE);
    }
    public void goProfile(View v){
        Intent intent = new Intent(this, SetProfileEditActivity.class);
        startActivity(intent);

        closeSettingMenu();
    }
    public void goRecommend(View v){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);

        closeSettingMenu();
    }
    public void goHostingSetting(View v){
        Intent intent = new Intent(this, SetHostingAddActivity.class);
        startActivity(intent);

        settingMenu.setVisibility(View.GONE);
    }
    public void goEventSetting(View v){
        card_myEvent.setVisibility(View.VISIBLE);
        card_joinEvent.setVisibility(View.VISIBLE);
        subAnim();
    }
    public void goMyEventSetting(View v){
        Intent intent = new Intent(this, SetEventListActivity.class);
        startActivity(intent);

        closeSettingMenu();
    }
    public void goJoinEventSetting(View v){
        Intent intent = new Intent(this, SetJoinedEventListActivity.class);
        startActivity(intent);

        closeSettingMenu();
    }
    public void goLogout(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Really you want Logout?");
        builder.setPositiveButton("Yes", (DialogInterface dialog, int which) -> {
            SharedPreferenceManager.getInstance().logout();
            Toast.makeText(ApplicationInitializer.getAppContext(),"Logout!",Toast.LENGTH_SHORT).show();

            recreate();
        });
        builder.setNegativeButton("No", (DialogInterface dialog, int which) -> {

        });
        builder.show();
    }
}
