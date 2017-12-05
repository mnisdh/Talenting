package a.talenting.com.talenting.controller.setting.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.talenting.com.talenting.R;

public class FindPwActivity extends AppCompatActivity {

    private boolean email = false;
    private EditText edit_findEmail;
    private TextView txt_emailalert;
    private Button btn_sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pw);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initListener();
    }

    private void initView(){
        edit_findEmail = findViewById(R.id.edit_findEmail);
        txt_emailalert = findViewById(R.id.txt_emailalert);
        btn_sendEmail = findViewById(R.id.btn_sendEmail);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this, LoginActivity.class);
                NavUtils.navigateUpTo(this, intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void initListener(){
        edit_findEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checkEmail(s.toString())!=true){
                    txt_emailalert.setVisibility(View.VISIBLE);
                    email=false;
                    enableLogin(email);
                }
                else{
                    txt_emailalert.setVisibility(View.INVISIBLE);
                    email=true;
                    enableLogin(email);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    txt_emailalert.setVisibility(View.INVISIBLE);
                    email=false;
                    enableLogin(email);
                }
            }
        });
    }

    public void enableLogin(boolean email){
        if(email==true){
            btn_sendEmail.setEnabled(true);
        }else{
            btn_sendEmail.setEnabled(false);
        }
    }

    public boolean checkEmail(String email){

        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        boolean isNormal = m.matches();
        return isNormal;
    }
}
