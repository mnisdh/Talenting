package a.talenting.com.talenting.domain;


import com.google.gson.GsonBuilder;

import a.talenting.com.talenting.common.SharedPreferenceManager;
import a.talenting.com.talenting.domain.hosting.IHostingApiService;
import a.talenting.com.talenting.domain.hosting.options.IHostingOptionsApiService;
import a.talenting.com.talenting.domain.hosting.photo.IHostingPhotoApiService;
import a.talenting.com.talenting.domain.profile.IProfileApiService;
import a.talenting.com.talenting.domain.profile.photo.IProfilePhotoApiService;
import a.talenting.com.talenting.domain.user.IUserApiService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2017-12-07.
 */

public class DomainManager {
    public static final String BASE_URL = "http://talenting-dev.ap-northeast-2.elasticbeanstalk.com";

    private static Retrofit retrofit;
    private static IUserApiService iUserApiService;
    private static IHostingApiService iHostingApiService;
    private static IHostingPhotoApiService iHostingPhotoApiService;
    private static IHostingOptionsApiService iHostingOptionsApiService;
    private static IProfileApiService iProfileApiService;
    private static IProfilePhotoApiService iProfilePhotoApiService;

    private static void initRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }
    }

    public static String getTokenHeader(){
        return "token " + SharedPreferenceManager.getInstance().getToken();
    }

    public static IUserApiService getUserApiService(){
        initRetrofit();

        if(iUserApiService == null) iUserApiService = retrofit.create(IUserApiService.class);
        return iUserApiService;
    }

    public static IHostingApiService getHostingApiService(){
        initRetrofit();

        if(iHostingApiService == null) iHostingApiService = retrofit.create(IHostingApiService.class);
        return iHostingApiService;
    }

    public static IHostingPhotoApiService getHostingPhotoApiService(){
        initRetrofit();

        if(iHostingPhotoApiService == null) iHostingPhotoApiService = retrofit.create(IHostingPhotoApiService.class);
        return iHostingPhotoApiService;
    }

    public static IHostingOptionsApiService getHostingOptionsApiService(){
        initRetrofit();

        if(iHostingOptionsApiService == null) iHostingOptionsApiService = retrofit.create(IHostingOptionsApiService.class);
        return iHostingOptionsApiService;
    }

    public static IProfileApiService getProfileApiService(){
        initRetrofit();

        if(iProfileApiService == null) iProfileApiService = retrofit.create(IProfileApiService.class);
        return iProfileApiService;
    }

    public static IProfilePhotoApiService getProfilePhotoApiService(){
        initRetrofit();

        if(iProfilePhotoApiService == null) iProfilePhotoApiService = retrofit.create(IProfilePhotoApiService.class);
        return iProfilePhotoApiService;
    }






}
