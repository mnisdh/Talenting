package a.talenting.com.talenting.controller.setting.signup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.Constants;
import a.talenting.com.talenting.common.SharedPreferenceManager;
import a.talenting.com.talenting.domain.DomainManager;
import a.talenting.com.talenting.domain.profile.Profile;
import a.talenting.com.talenting.util.TempUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SignupThirdActivity extends AppCompatActivity {

    private EditText edit_occupation;
    private EditText edit_talentIntro;
    private EditText edit_selfIntro;
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_third);
        Intent intent = getIntent();
        profile = (Profile)intent.getSerializableExtra(Constants.EXT_PROFILE);
        initView();
    }

    private void initView(){
        edit_occupation = findViewById(R.id.edit_occupation);
        edit_selfIntro = findViewById(R.id.edit_selfIntro);
        edit_talentIntro = findViewById(R.id.edit_talentIntro);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SignupSecondActivity.class);
        intent.putExtra(Constants.EXT_PROFILE, profile);
        startActivity(intent);
    }

    public void thirdPrev(View view){
        Intent intent = new Intent(this, SignupSecondActivity.class);
        intent.putExtra(Constants.EXT_PROFILE, profile);
        startActivity(intent);
    }

    public void thirdComplete(View view){
        profile.setOccupation(edit_occupation.getText().toString());
        profile.setSelf_intro(edit_selfIntro.getText().toString());
        profile.setTalent_intro(edit_talentIntro.getText().toString());
        DomainManager.getProfileApiService().update(DomainManager.getTokenHeader(), SharedPreferenceManager.getInstance().getPk(), profile)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(result -> {
                    if(result.isSuccess()) {
                        if(profile.getImages() != null && profile.getImages().size() == 1) createPhoto();
                    }
                    else Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();}
                , e -> {
                //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });

        NavUtils.navigateUpFromSameTask(this);

        finish();
    }

    private void createPhoto(){
        Uri uri = Uri.parse(profile.getImages().get(0).getImage());
        File file = TempUtil.createTempImage(this.getContentResolver(), uri);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        DomainManager.getProfilePhotoApiService().create(DomainManager.getTokenHeader(), SharedPreferenceManager.getInstance().getPk(), body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(result -> {}
                , e -> {
                //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
    }
}
