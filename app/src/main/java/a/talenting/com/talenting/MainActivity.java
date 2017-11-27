package a.talenting.com.talenting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){

    }

    public void goSignin(View v){
        Intent intent = new Intent(this, SigninActivity.class);
        startActivity(intent);
    }
}
