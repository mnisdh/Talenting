package a.talenting.com.talenting.controller.setting.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.adapter.CategorySpinnerAdapter;
import a.talenting.com.talenting.custom.adapter.LangListAdapter;
import a.talenting.com.talenting.custom.adapter.TalentListAdapter;
import a.talenting.com.talenting.domain.BaseData;
import a.talenting.com.talenting.domain.profile.Profile;

public class SignupSecondActivity extends AppCompatActivity {

    private Map<String,String> talents = new HashMap<>();
    private Map<String,String> langs = new HashMap<>();

    private CategorySpinnerAdapter talentAdapter;
    private CategorySpinnerAdapter langAdapter;
    private TalentListAdapter listAdapter;
    private LangListAdapter langListAdapter;
    private Spinner spinner_talent;
    private Spinner spinner_lang;
    private RecyclerView talentList;
    private RecyclerView langList;
    private Profile profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_second);
        Intent intent = getIntent();
        profile = (Profile)intent.getSerializableExtra("PROFILE");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initList();
        initListener();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,SignupFirstActivity.class);
        intent.putExtra("PROFILE",profile);
        startActivity(intent);
        }

    public void addTalent(View view){
        listAdapter.talentAdd(spinner_talent.getSelectedItemPosition());
    }

    public void addLanguage(View view){
        langListAdapter.langAdd(spinner_lang.getSelectedItemPosition());
    }

    public void secondPrev(View view){
        Intent intent = new Intent(this,SignupFirstActivity.class);
        intent.putExtra("PROFILE",profile);
        startActivity(intent);
    }

    public void secondNext(View view){
        profile.setTalent_category(new ArrayList(listAdapter.getData().keySet()));
        profile.setAvailable_languages(new ArrayList(langListAdapter.getData().keySet()));
        Intent intent = new Intent(this,SignupThirdActivity.class);
        intent.putExtra("PROFILE",profile);
        startActivity(intent);
    }


    public void initView(){
        talentList = findViewById(R.id.talentList);
        langList = findViewById(R.id.langList);
        listAdapter = new TalentListAdapter(this);
        langListAdapter = new LangListAdapter(this);
        talentList.setAdapter(listAdapter);
        talentList.setLayoutManager(new GridLayoutManager(this,2));
        langList.setAdapter(langListAdapter);
        langList.setLayoutManager(new GridLayoutManager(this,2));
        spinner_talent = findViewById(R.id.spinner_category);
        spinner_lang = findViewById(R.id.spinner_Ncateogry);
    }

    public void initList() {
        talents = BaseData.getProfileTalent();
        langs = BaseData.getLanguage();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this,SignupFirstActivity.class);
                intent.putExtra("PROFILE",profile);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void initListener(){
        talentAdapter = new CategorySpinnerAdapter(this, talents);
        spinner_talent.setAdapter(talentAdapter);
        spinner_talent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        langAdapter = new CategorySpinnerAdapter(this,langs);
        spinner_lang.setAdapter(langAdapter);
        spinner_lang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent2, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
