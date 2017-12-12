package a.talenting.com.talenting.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import a.talenting.com.talenting.domain.hosting.IHostingApiService;
import a.talenting.com.talenting.domain.user.IUserApiService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2017-12-07.
 */

public class DomainManager {
    public static final String BASE_URL = "http://talenting-env.ap-northeast-2.elasticbeanstalk.com";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit retrofit;
    private static IUserApiService iUserApiService;
    private static IHostingApiService iHostingApiService;

    private static void initRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();

            httpClient.addInterceptor(chain -> {
                Request ori = chain.request();

                Request.Builder requestBuilder = ori.newBuilder().header("Authorization", "");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            });
        }
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

    public static Map<Integer, String> getHostingCategory(){
        Map<Integer, String> data = new LinkedHashMap<>();
        data.put(1, "Culture");
        data.put(2, "Work hand");
        data.put(3, "Language exchange");
        data.put(4, "Art");
        data.put(5, "Other");

        return data;
    }

    public static Map<Integer, String> getHostingHouseType(){
        Map<Integer, String> data = new HashMap<>();
        data.put(1, "Apartment");
        data.put(2, "House");
        data.put(3, "Guesthouse");
        data.put(4, "Office");
        data.put(5, "Dormitory");

        return data;
    }

    public static Map<Integer, String> getHostingRoomType(){
        Map<Integer, String> data = new HashMap<>();
        data.put(1, "Private room");
        data.put(2, "Shared room");

        return data;
    }

    public static Map<Integer, String> getHostingMealType(){
        Map<Integer, String> data = new HashMap<>();
        data.put(1, "It's a deal! We share a meal!");
        data.put(2, "Make your dishes using host's ingredient");

        return data;
    }

    public static Map<Integer, String> getHostingInternetType(){
        Map<Integer, String> data = new HashMap<>();
        data.put(1, "Wifi");
        data.put(2, "Only LAN");
        data.put(3, "No Internet");

        return data;
    }

    public static Map<Integer, String> getHostingPhotoType(){
        Map<Integer, String> data = new HashMap<>();
        data.put(1, "Inside of the house");
        data.put(2, "View of the house");
        data.put(3, "External look of the house");
        data.put(4, "Around the house");
        data.put(5, "Other");

        return data;
    }
}
