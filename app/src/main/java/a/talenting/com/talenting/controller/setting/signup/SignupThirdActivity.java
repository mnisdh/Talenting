package a.talenting.com.talenting.controller.setting.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import a.talenting.com.talenting.R;

public class SignupThirdActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_third);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this,SignupSecondActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,SignupSecondActivity.class);
        startActivity(intent);
    }

    public void thirdPrev(View view){
        Intent intent = new Intent(this,SignupSecondActivity.class);
        startActivity(intent);
    }

    public void thirdComplete(View view){
        NavUtils.navigateUpFromSameTask(this);
        finish();
    }
}
