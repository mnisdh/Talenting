package a.talenting.com.talenting.controller.setting.signup;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.ActivityResultManager;
import a.talenting.com.talenting.common.Constants;
import a.talenting.com.talenting.common.DialogManager;
import a.talenting.com.talenting.custom.DetailItemView;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndValueItem;
import a.talenting.com.talenting.domain.BaseData;
import a.talenting.com.talenting.domain.profile.Profile;
import a.talenting.com.talenting.domain.profile.photo.ProfileImage;


public class SignupFirstActivity extends AppCompatActivity {
    private ActivityResultManager activityResultManager;

    private ImageView ivProfile;
    private EditText edit_birth;
    private EditText edit_city;
    private RadioGroup radioGroup;
    private Profile profile;
    private TitleAndValueItem country;
    private DetailItemView countryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_first);

        activityResultManager = new ActivityResultManager();

        Intent intent = getIntent();
        if(intent.getSerializableExtra(Constants.EXT_PROFILE)!=null){
            profile = (Profile)intent.getSerializableExtra(Constants.EXT_PROFILE);
        }else{
            profile = new Profile();

        }
        initView();
        initListener();
    }

    private void initView(){
        ivProfile = findViewById(R.id.ivProfile);
        radioGroup = findViewById(R.id.radioGroup);
        edit_birth = findViewById(R.id.edit_birth);
        edit_city = findViewById(R.id.edit_city);
        countryView = findViewById(R.id.countryView);
        country = new TitleAndValueItem("Country", "Select Country");
        country.padding.setBottom(0);
        country.padding.setTop(0);
        country.padding.setLeft(0);
        country.padding.setRight(0);
        country.titleStyle.setColor(Color.WHITE);
        country.valueStyle.setColor(Color.WHITE);
        countryView.setDetailItem(country);
    }

    public void birth(View view){
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this,R.style.MySpinnerDatePickerStyle,dateSetListener,2017,11,30);
                datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String msg = String.format("%d-%d-%d", year, month+1, dayOfMonth);
            edit_birth.setText(msg);
            profile.setBirth(msg);
        }
    };

    public void later(View view){
        NavUtils.navigateUpFromSameTask(this);
        finish();
    }

    public void firstNext(View view){
        profile.setCity(edit_city.getText().toString());
        Intent intent = new Intent(this, SignupSecondActivity.class);
        intent.putExtra(Constants.EXT_PROFILE, profile);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
        finish();
    }

    public void addProfile(View view){
        DialogManager.showCameraDialog(this, activityResultManager, value -> {
            List<ProfileImage> images = new ArrayList<>();
            ProfileImage profileImage = new ProfileImage();
            profileImage.setImage(value);
            images.add(profileImage);
            profile.setImages(images);

            Glide.with(this).load(value).apply(new RequestOptions().circleCrop()).into(ivProfile);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        activityResultManager.onActivityResult(requestCode, resultCode, data);
    }

    private void initListener(){
        radioGroup.setOnCheckedChangeListener((RadioGroup group, int checkedId) -> {
            if(group == radioGroup){
                if(checkedId==R.id.radio_male){
                    profile.setGender("1");
                }else{
                    profile.setGender("2");
                }
            }
        });
        country.setOnClickListener(item -> {
            Map<String, String> data = BaseData.getLanguage();
            DialogManager.showTypeListDialog(SignupFirstActivity.this, "Country", data, (String code, String text) ->
                {
                    country.value = text;
                    country.valueCode = code;
                    countryView.setDetailItem(country);
                    profile.setCountry(country.valueCode);
                });
        });

    }
}
