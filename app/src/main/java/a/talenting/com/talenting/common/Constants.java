package a.talenting.com.talenting.common;

/**
 * Created by daeho on 2017. 11. 28..
 */

public class Constants {
    private static final String URL_API = "http://talenting-env.ap-northeast-2.elasticbeanstalk.com/api/";
    private static final String URL_MEMBER = URL_API + "member/";

    public static final String URL_SIGN_UP = URL_MEMBER + "sign-up/";
    public static final String URL_SIGN_IN = URL_MEMBER + "log-in/";
    public static final String URL_EMAIL_CHECK = URL_MEMBER + "email-check/";
    public static final int CAMERA_PERMISSION_REQ = 902;
    public static final int CAMERA_REQ = 800;
    public static final int GALLERY_REQ = 801;
    public static final int LOGIN_REQ = 900;
    public static final int PERMISSION_REQ = 901;
}
