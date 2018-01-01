package a.talenting.com.talenting.controller.setting.signup;

import android.content.Intent;
import android.os.Bundle;
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
import a.talenting.com.talenting.domain.user.SignupResponse;
import a.talenting.com.talenting.domain.user.UserLogin;
import a.talenting.com.talenting.domain.user.UserSignup;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SignupActivity extends AppCompatActivity {

    private boolean email = false;
    private boolean pw = false;
    private boolean pwcheck = false;
    private boolean fname = false;
    private boolean lname = false;
    private EditText edit_signEmail;
    private EditText edit_signPw;
    private EditText edit_checkPw;
    private EditText edit_fname;
    private EditText edit_lname;
    private TextView txt_signemail;
    private TextView txt_signpw;
    private TextView txt_checkpw;
    private Button btn_signinFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();
        initListener();
    }

    private void initView(){
        edit_signEmail = findViewById(R.id.edit_signEmail);
        edit_signPw = findViewById(R.id.edit_signPw);
        edit_checkPw = findViewById(R.id.edit_checkPw);
        edit_fname = findViewById(R.id.edit_fname);
        edit_lname = findViewById(R.id.edit_lname);
        txt_signemail = findViewById(R.id.txt_signemail);
        txt_signpw = findViewById(R.id.txt_signpw);
        txt_checkpw = findViewById(R.id.txt_checkpw);
        btn_signinFinal = findViewById(R.id.btn_signinFinal);
    }

    public void signin(View view){
        UserSignup user_signup = new UserSignup();
        user_signup.setEmail(edit_signEmail.getText().toString());
        user_signup.setPassword1(edit_signPw.getText().toString());
        user_signup.setPassword2(edit_checkPw.getText().toString());
        user_signup.setFirst_name(edit_fname.getText().toString());
        user_signup.setLast_name(edit_lname.getText().toString());

        Observable<SignupResponse> observable = DomainManager.getUserApiService().signUp(user_signup);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if(result.isSuccess()){
                        SharedPreferenceManager.getInstance().setEmail(edit_signEmail.getText().toString());
                        SharedPreferenceManager.getInstance().setPw(edit_signPw.getText().toString());
                        SharedPreferenceManager.getInstance().setPk(result.getUser().getPk());
                        login();
                    }
                    else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                },
                        e -> {
                            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
    }

    private void login(){
        UserLogin user_login = new UserLogin();
        user_login.setEmail(SharedPreferenceManager.getInstance().getEmail());
        user_login.setPassword(SharedPreferenceManager.getInstance().getPw());
        Observable<LoginResponse> observables = DomainManager.getUserApiService().login(user_login);
        observables.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> {
                            if(results.isSuccess()) {
                                SharedPreferenceManager.getInstance().setToken(results.getToken());
                                success();
                            }
                            else {
                                Toast.makeText(this, results.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        },
                        e -> {
                            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
    }

    private void success(){
        Intent intent = new Intent(this, SignupFirstActivity.class);
        startActivity(intent);
    }

    private void initListener(){
        edit_signEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checkEmail(s.toString())!=true){
                    txt_signemail.setVisibility(View.VISIBLE);
                    email=false;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
                else{
                    txt_signemail.setVisibility(View.INVISIBLE);
                    email=true;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    txt_signemail.setVisibility(View.INVISIBLE);
                    email=false;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
            }
        });
        edit_signPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checkPw(s.toString())!=true){
                    txt_signpw.setVisibility(View.VISIBLE);
                    pw=false;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }else{
                    txt_signpw.setVisibility(View.INVISIBLE);
                    pw=true;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")) {
                    txt_signpw.setVisibility(View.INVISIBLE);
                    pw = false;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
            }
        });
        edit_checkPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals(edit_signPw.getText().toString())){
                    txt_checkpw .setVisibility(View.INVISIBLE);
                    pwcheck=true;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }else{
                    txt_checkpw.setVisibility(View.VISIBLE);
                    pwcheck=false;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
            }
        });
        edit_lname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("")){
                    lname=true;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }else{
                    lname=false;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
            }
        });
        edit_fname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("")){
                    fname=true;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }else{
                    fname=false;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
            }
        });
    }

    private void enableLogin(boolean email,boolean pw, boolean pwcheck, boolean lname, boolean fname){
        if(email&&pw&&pwcheck&&fname&&lname==true){
            btn_signinFinal.setEnabled(true);
        }else{
            btn_signinFinal.setEnabled(false);
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
