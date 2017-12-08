package a.talenting.com.talenting.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.SharedPreferenceManager;
import a.talenting.com.talenting.controller.setting.login.LoginActivity;
import a.talenting.com.talenting.controller.setting.signup.SignupActivity;
import a.talenting.com.talenting.controller.setting.signup.SignupFirstActivity;
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.domain.IDomainCallback;
import a.talenting.com.talenting.domain.user.LoginResponse;
import a.talenting.com.talenting.domain.user.UserLogin;

import static android.view.View.VISIBLE;

public class LoginMainActivity extends AppCompatActivity {

    long pressedTime = 0;
    private DomainManager domainManager;
    private ConstraintLayout AutologinLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        AutologinLayout = findViewById(R.id.AutoLoginLayout);
        initRetro();
        if(SharedPreferenceManager.getInstance().getEmail()!=""&&SharedPreferenceManager.getInstance().getPw()!=""){
            autologin();
        }
    }

    private void initRetro(){
        domainManager = DomainManager.getInstance();
    }

    private void autologin(){
        AutologinLayout.setVisibility(VISIBLE);
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail(SharedPreferenceManager.getInstance().getEmail());
        userLogin.setPassword(SharedPreferenceManager.getInstance().getPw());
        domainManager.login(userLogin, new IDomainCallback() {
            @Override
            public void onError(Throwable t) {
                Toast.makeText(getApplicationContext(),"LOGIN ERROR!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int code, Object receivedData) {
                LoginResponse data = (LoginResponse)receivedData;
                if(code==200){
                    Toast.makeText(getApplicationContext(),"LOGIN SUCCESS!",Toast.LENGTH_SHORT).show();
                    success();
                }else if(code==401){
                    Toast.makeText(getApplicationContext(),data.getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int code, Object receivedData) {
                Toast.makeText(getApplicationContext(),"LOGIN FAIL!",Toast.LENGTH_SHORT).show();
            }
        });
        AutologinLayout.setVisibility(View.GONE);

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
