package a.talenting.com.talenting.controller.setting.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.SharedPreferenceManager;
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.domain.profile.Profile;
import a.talenting.com.talenting.domain.profile.ProfileResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SignupThirdActivity extends AppCompatActivity {

    private EditText edit_occupation;
    private EditText edit_talentIntro;
    private EditText edit_selfIntro;
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_third);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        profile = (Profile)intent.getSerializableExtra("PROFILE");
        initView();
    }

    private void initView(){
        edit_occupation = findViewById(R.id.edit_occupation);
        edit_selfIntro = findViewById(R.id.edit_selfIntro);
        edit_talentIntro = findViewById(R.id.edit_talentIntro);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this,SignupSecondActivity.class);
                intent.putExtra("PROFILE",profile);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,SignupSecondActivity.class);
        intent.putExtra("PROFILE",profile);
        startActivity(intent);
    }

    public void thirdPrev(View view){
        Intent intent = new Intent(this,SignupSecondActivity.class);
        intent.putExtra("PROFILE",profile);
        startActivity(intent);
    }

    public void thirdComplete(View view){
        profile.setOccupation(edit_occupation.getText().toString());
        profile.setSelf_intro(edit_selfIntro.getText().toString());
        profile.setTalent_intro(edit_talentIntro.getText().toString());
        Observable<ProfileResponse> observable = DomainManager.getProfileApiService().update(DomainManager.getTokenHeader(), SharedPreferenceManager.getInstance().getPk(),profile);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if(result.isSuccess()) {
                        Toast.makeText(this,"Success Profile Enroll!",Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                        },
                        e -> {
                            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
        NavUtils.navigateUpFromSameTask(this);
        finish();
    }
}
