package a.talenting.com.talenting.controller.setting.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.SharedPreferenceManager;
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.domain.user.LoginResponse;
import a.talenting.com.talenting.domain.user.UserLogin;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private boolean email=false;
    private boolean pw=false;
    private EditText edit_email;
    private EditText edit_pw;
    private TextView txt_emailAlert;
    private TextView txt_pwAlert;
    private Button btn_login;
    private Button btn_findPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initListener();
    }

    private void initView(){
        edit_email = findViewById(R.id.edit_email);
        edit_pw = findViewById(R.id.edit_pw);
        txt_emailAlert = findViewById(R.id.txt_emailAlert);
        txt_pwAlert = findViewById(R.id.txt_pwAlert);
        btn_login = findViewById(R.id.btn_login);
        btn_findPw = findViewById(R.id.btn_findPw);

    }

    public void login(View view){
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail(edit_email.getText().toString());
        userLogin.setPassword(edit_pw.getText().toString());
        Observable<LoginResponse> observable = DomainManager.getUserApiService().login(userLogin);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.isSuccess()) {
                        SharedPreferenceManager.getInstance().setEmail(edit_email.getText().toString());
                        SharedPreferenceManager.getInstance().setPw(edit_pw.getText().toString());
                        SharedPreferenceManager.getInstance().setToken(result.getToken());
                        NavUtils.navigateUpFromSameTask(this);
                        finish();
                    }
                    else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                },
                e -> {
                    //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    public void findpassword(View view){
        Intent intent = new Intent(this,FindPwActivity.class);
        startActivityForResult(intent,400);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==400){
            if(requestCode==RESULT_OK){

            }else{

            }
        }
    }

    private void initListener(){
        edit_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checkEmail(s.toString())!=true){
                    txt_emailAlert.setVisibility(View.VISIBLE);
                    email=false;
                    enableLogin(email,pw);
                }
                else{
                    txt_emailAlert.setVisibility(View.INVISIBLE);
                    email=true;
                    enableLogin(email,pw);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    txt_emailAlert.setVisibility(View.INVISIBLE);
                    email=false;
                    enableLogin(email,pw);
                }
            }
        });
        edit_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checkPw(s.toString())!=true){
                    txt_pwAlert.setVisibility(View.VISIBLE);
                    pw=false;
                    enableLogin(email,pw);
                }else{
                    txt_pwAlert.setVisibility(View.INVISIBLE);
                    pw=true;
                    enableLogin(email,pw);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")) {
                    txt_pwAlert.setVisibility(View.INVISIBLE);
                    pw = false;
                    enableLogin(email,pw);
                }
            }
        });
    }

    private void enableLogin(boolean email,boolean pw){
        if(email&&pw==true){
            btn_login.setEnabled(true);
        }else{
            btn_login.setEnabled(false);
        }
    }

    private boolean checkEmail(String email){

        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        boolean isNormal = m.matches();
        return isNormal;
    }

    private boolean checkPw(String str) {
        String Passwrod_PATTERN = "^(?=.*[a-zA-Z]+)(?=.*[0-9]+).{8,16}$";
        Pattern pattern = Pattern.compile(Passwrod_PATTERN);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }



}
