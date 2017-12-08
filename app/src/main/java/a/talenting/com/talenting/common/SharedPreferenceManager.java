package a.talenting.com.talenting.common;

import android.content.Context;
import android.content.SharedPreferences;

import a.talenting.com.talenting.ApplicationInitializer;

/**
 * Created by user on 2017-12-08.
 */

public class SharedPreferenceManager {
    private static String AUTO = "auto";
    private static String AUTO_EMAIL = "auto_email";
    private static String AUTO_PW = "auto_pw";
    private static String SETTING = "setting";
    private SharedPreferences.Editor autoEditor;
    private SharedPreferences.Editor settingEditor;

    private static SharedPreferenceManager sharedPreferenceManager = null;


    public static SharedPreferenceManager getInstance() {
        if (sharedPreferenceManager == null)
            sharedPreferenceManager = new SharedPreferenceManager();

        return sharedPreferenceManager;
    }

    private Context context;
    private SharedPreferences autoSpf;
    private SharedPreferences settingSpf;

    private SharedPreferenceManager() {
        context = ApplicationInitializer.getAppContext();
        autoSpf = context.getSharedPreferences(AUTO, Context.MODE_PRIVATE);
        settingSpf = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        autoEditor = autoSpf.edit();
        settingEditor = settingSpf.edit();
    }


    public void setEmail(String email) {
        autoEditor.putString(AUTO_EMAIL, email);
        autoEditor.commit();
    }

    public String getEmail() {
        return autoSpf.getString(AUTO_EMAIL, "");
    }

    public void setPw(String pw) {
        autoEditor.putString(AUTO_PW, pw);
        autoEditor.commit();
    }

    public String getPw() {
        return autoSpf.getString(AUTO_PW, "");
    }

    public void logout() {
        autoEditor.clear();
        autoEditor.commit();
    }
}
