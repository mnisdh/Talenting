package a.talenting.com.talenting.common;

/**
 * Created by daeho on 2017. 11. 28..
 */

public class Constants {
    public static final int REQ_ADD_HOSTING = 900;
    public static final int REQ_EDIT_HOSTING = 901;
    public static final int REQ_ADD_EVENT = 800;
    public static final int REQ_EDIT_EVENT = 801;

    public static final int REQ_EVENT_PLACE = 500;

    private static final String URL_API = "http://talenting-env.ap-northeast-2.elasticbeanstalk.com/api/";
    private static final String URL_MEMBER = URL_API + "member/";

    public static final String URL_SIGN_UP = URL_MEMBER + "sign-up/";
    public static final String URL_SIGN_IN = URL_MEMBER + "log-in/";
    public static final String URL_EMAIL_CHECK = URL_MEMBER + "email-check/";
}
