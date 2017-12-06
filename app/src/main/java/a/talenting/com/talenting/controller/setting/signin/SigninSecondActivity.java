package a.talenting.com.talenting.controller.setting.signin;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.adapter.CategorySpinnerAdapter;
import a.talenting.com.talenting.custom.adapter.TalentListAdapter;

public class SigninSecondActivity extends AppCompatActivity {

    private List<String> category = new ArrayList<>();
    private List<String> Ncategory = new ArrayList<>();
    private CategorySpinnerAdapter adapter;
    private CategorySpinnerAdapter nAdapter;
    private TalentListAdapter listAdapter;
    private Spinner spinner_category;
    private Spinner spinner_Ncategory;
    private RecyclerView talentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_second);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initList();
        initListener();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,SigninFirstActivity.class);
        startActivity(intent);
        }

    public void addTalent(View view){
        listAdapter.refresh(spinner_Ncategory.getSelectedItem().toString());
    }

    public void secondPrev(View view){
        Intent intent = new Intent(this,SigninFirstActivity.class);
        startActivity(intent);
    }

    public void secondNext(View view){
        Intent intent = new Intent(this,SigninThirdActivity.class);
        startActivity(intent);
    }


    public void initView(){
        talentList = findViewById(R.id.talentList);
        listAdapter = new TalentListAdapter(this);
        talentList.setAdapter(listAdapter);
        talentList.setLayoutManager(new GridLayoutManager(this,4));
        spinner_category = findViewById(R.id.spinner_category);
        spinner_Ncategory = findViewById(R.id.spinner_Ncateogry);
    }

    public void initList() {
        Resources res = getResources();
        String[] categoryArray = res.getStringArray(R.array.Category);

        for (String s : categoryArray) {
            category.add(s);
        }
        String[] languageArray = res.getStringArray(R.array.Language);

        for (String s : languageArray) {
            Ncategory.add(s);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this,SigninFirstActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void initListener(){
        adapter = new CategorySpinnerAdapter(this, category);
        spinner_category.setAdapter(adapter);
        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category = parent.getItemAtPosition(position).toString();
                nAdapter = new CategorySpinnerAdapter(parent.getContext(),Ncategory);
                spinner_Ncategory.setAdapter(nAdapter);
                spinner_Ncategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent2, View view, int position, long id) {
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
