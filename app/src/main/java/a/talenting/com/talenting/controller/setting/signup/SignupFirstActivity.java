package a.talenting.com.talenting.controller.setting.signup;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import a.talenting.com.talenting.BuildConfig;
import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.DialogManager;
import a.talenting.com.talenting.custom.DetailItemView;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndValueItem;
import a.talenting.com.talenting.domain.BaseData;
import a.talenting.com.talenting.domain.profile.Profile;
import a.talenting.com.talenting.util.PermissionUtil;

import static a.talenting.com.talenting.common.Constants.CAMERA_PERMISSION_REQ;
import static a.talenting.com.talenting.common.Constants.CAMERA_REQ;
import static a.talenting.com.talenting.common.Constants.GALLERY_REQ;


public class SignupFirstActivity extends AppCompatActivity {

    private Uri fileUri = null;
    private Uri profileUri = null;
    private ImageView ivProfile;
    private ImageButton btnAddProfile;
    private ImageButton btnCamera;
    private ImageButton btnGallery;
    private Button btn_firstNext;
    private Button btn_later;
    private RadioButton radio_male;
    private RadioButton radio_female;
    private EditText edit_birth;
    private EditText edit_city;
    private ConstraintLayout popupchoice;
    private RadioGroup radioGroup;
    private Profile profile;
    private TitleAndValueItem country;
    private DetailItemView countryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_first);

        Intent intent = getIntent();
        if(intent.getSerializableExtra("PROFILE")!=null){
            profile = (Profile)intent.getSerializableExtra("PROFILE");
        }else{
            profile = new Profile();

        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initListener();
    }

    private void initView(){
        ivProfile = findViewById(R.id.ivProfile);
        btnAddProfile = findViewById(R.id.btnAddProfile);
        btnCamera = findViewById(R.id.btnCamera);
        btnGallery = findViewById(R.id.btnGallery);
        btn_firstNext = findViewById(R.id.btn_firstNext);
        btn_later = findViewById(R.id.btn_later);
        radioGroup = findViewById(R.id.radioGroup);
        radio_male = findViewById(R.id.radio_male);
        radio_female = findViewById(R.id.radio_female);
        edit_birth = findViewById(R.id.edit_birth);
        edit_city = findViewById(R.id.edit_city);
        popupchoice = findViewById(R.id.popupChoice);
        countryView = findViewById(R.id.countryView);
        country = new TitleAndValueItem("Country", "Select Country");
        country.padding.setBottom(0);
        country.padding.setTop(0);
        country.padding.setLeft(0);
        country.padding.setRight(0);
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
        intent.putExtra("PROFILE",profile);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(popupchoice.getVisibility() == View.VISIBLE) popupchoice.setVisibility(View.GONE);
        else{
            NavUtils.navigateUpFromSameTask(this);
            finish();
        }
    }

    public void addProfile(View view){
        popupchoice.setVisibility(View.VISIBLE);
    }

    public void onCamera(View v){
        String[] Permission = new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE };

        PermissionUtil pUtil = new PermissionUtil(CAMERA_PERMISSION_REQ, Permission);
        pUtil.check(this, new PermissionUtil.IPermissionGrant() {
            @Override
            public void run() {
                camera();
            }

            @Override
            public void fail() {

            }
        });
    }

    /**
     * 카메라 앱 띄워서 결과 이미지 저장하기
     */
    private void camera(){
        // 1. Intent 만들기
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 2. 호환성 처리 버전체크 - 롤리팝 이상
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            // 3. 실제 파일이 저장되는 파일 객체 < 빈 파일을 생성해 둔다
            File photoFile = null;

            // 3.1 실제 파일이 저장되는 곳에 권한이 부여되어 있어야 한다
            //     롤리팝 부터는 File Provider를 선언해 줘야만한다 > Manifest에
            try {
                photoFile = createFile();

                // 갤러리에서 나오지 않을때
                refreshMedia(photoFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", photoFile);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent, CAMERA_REQ);
        }
        else startActivityForResult(intent, CAMERA_REQ);
    }

    /**
     * 미디어 파일 갱신
     * @param file
     */
    private void refreshMedia(File file){
        MediaScannerConnection.scanFile(this, new String[]{file.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
            @Override
            public void onScanCompleted(String path, Uri uri) {

            }
        });
    }

    private File createFile() throws IOException {
        // 임시파일명 생성
        String tempFileName = "Temp_" + System.currentTimeMillis();

        // 임시파일 저장용 디렉토리 생성
        File tempDir = new File(Environment.getExternalStorageDirectory() + File.separator + "tempPicture" + File.separator);

        // 생성체크
        if(!tempDir.exists()) tempDir.mkdirs();

        //실제 임시파일을 생성
        File tempFile = File.createTempFile(tempFileName, ".jpg", tempDir);

        return tempFile;
    }

    public void onGallery(View v){
        String[] Permission = new String[] { Manifest.permission.READ_EXTERNAL_STORAGE };

        PermissionUtil pUtil = new PermissionUtil(CAMERA_PERMISSION_REQ, Permission);
        pUtil.check(this, new PermissionUtil.IPermissionGrant() {
            @Override
            public void run() {
//                btnAddProfile.setVisibility(View.GONE);
            }

            @Override
            public void fail() {
//                btnAddProfile.setVisibility(View.GONE);
            }
        });

        gallery();
    }

    private void gallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        profileUri = null;

        switch (requestCode){
            case CAMERA_REQ:
                if(resultCode == RESULT_OK){
                    // 버전체크
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) profileUri = fileUri;
                    else profileUri = data.getData();
                }
                break;
            case GALLERY_REQ:
                // 갤러리 액티비티 종료시 호출 - 정상종료 된 경우만 이미지설정
                if(resultCode == RESULT_OK) profileUri = data.getData();
                break;
        }

        if(profileUri == null) ivProfile.setImageResource(R.drawable.ic_action_name);
        else Glide.with(SignupFirstActivity.this).load(profileUri).apply(RequestOptions.circleCropTransform()).into(ivProfile);

        if(resultCode == RESULT_OK) popupchoice.setVisibility(View.GONE);
    }

    private void initListener(){
        popupchoice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                popupchoice.setVisibility(View.GONE);
                return false;
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(group == radioGroup){
                    if(checkedId==R.id.radio_male){
                        profile.setGender("1");
                    }else{
                        profile.setGender("2");
                    }
                }
            }
        });
        country.setOnClickListener(item -> {
            Map<String, String> data = BaseData.getLanguage();
            DialogManager.showTypeListDialog(SignupFirstActivity.this,"Country",data,(String code, String text) ->
            {
                country.value = text;
                country.valueCode = code;
                countryView.setDetailItem(country);
                profile.setCountry(country.valueCode);
            });
        });

    }
}
