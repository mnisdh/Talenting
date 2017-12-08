package a.talenting.com.talenting.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.controller.setting.login.LoginActivity;
import a.talenting.com.talenting.controller.setting.signup.SignupActivity;
import a.talenting.com.talenting.controller.setting.signup.SignupFirstActivity;

public class LoginMainActivity extends AppCompatActivity {

    long pressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
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
