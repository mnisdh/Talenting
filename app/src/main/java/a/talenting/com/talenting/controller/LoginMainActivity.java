package a.talenting.com.talenting.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.SharedPreferenceManager;
import a.talenting.com.talenting.controller.setting.login.LoginActivity;
import a.talenting.com.talenting.controller.setting.signup.SignupActivity;
import a.talenting.com.talenting.controller.setting.signup.SignupFirstActivity;
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.domain.user.LoginResponse;
import a.talenting.com.talenting.domain.user.UserLogin;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginMainActivity extends AppCompatActivity {

    long pressedTime = 0;
    long time = 0;
    private ConstraintLayout autoLoginLayout;
    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        autoLoginLayout = findViewById(R.id.autoLoginLayout);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);
        autoLoginLayout.setVisibility(View.VISIBLE);

        autoLoginLayout.postDelayed(new Runnable() {
            public void run() {
                autoLoginLayout.setVisibility(View.GONE);
                if (SharedPreferenceManager.getInstance().getEmail() != "" && SharedPreferenceManager.getInstance().getPw() != "") {
                    autologin();
                }
            }
        }, 2000);

    }


    private void autologin() {
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail(SharedPreferenceManager.getInstance().getEmail());
        userLogin.setPassword(SharedPreferenceManager.getInstance().getPw());
        Observable<LoginResponse> observable = DomainManager.getUserApiService().login(userLogin);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.isSuccess()) {
                        Toast.makeText(this, "SUCCESS!", Toast.LENGTH_SHORT).show();
                        success();
                    } else {
                        Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void success(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (pressedTime == 0) {
            Toast.makeText(LoginMainActivity.this, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
            pressedTime = System.currentTimeMillis();
        } else {
            int seconds = (int)(System.currentTimeMillis() - pressedTime);
            if (seconds > 2000){
                Toast.makeText(LoginMainActivity.this, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
                pressedTime = 0;
            } else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                System.exit(0);
            }
        }
    }

    public void login(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent,200);
    }

    public void signin(View view){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivityForResult(intent,300);
    }

    public void option(View view){
        Intent intent = new Intent(this, SignupFirstActivity.class);
        startActivityForResult(intent, 400);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200){
            if(requestCode==RESULT_OK){

            }else{

            }
        }else if(requestCode==300){
            if(requestCode==RESULT_OK){

            }else{

            }
        }else if(requestCode==400){
            if(requestCode==RESULT_OK){

            }else{

            }
        }
    }

}
