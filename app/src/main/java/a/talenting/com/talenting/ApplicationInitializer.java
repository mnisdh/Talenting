package a.talenting.com.talenting;

import android.app.Application;
import android.content.Context;

import a.talenting.com.talenting.common.SharedPreferenceManager;
import a.talenting.com.talenting.domain.BaseData;

/**
 * Created by user on 2017-12-08.
 */

public class ApplicationInitializer extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();

        ApplicationInitializer.context = getApplicationContext();
        SharedPreferenceManager.getInstance();


    }

    public static Context getAppContext() {
        return ApplicationInitializer.context;
    }

}
