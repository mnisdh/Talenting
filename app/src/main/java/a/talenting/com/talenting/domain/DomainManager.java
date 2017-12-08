package a.talenting.com.talenting.domain;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2017-12-07.
 */

public class DomainManager {
    private static Retrofit retrofit;
    private static IDomainApiService iDomainApiService;
    public static IDomainApiService getDomainApiService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(IDomainApiService.Base_URL)
                    .build();
        }

        if(iDomainApiService == null) iDomainApiService = retrofit.create(IDomainApiService.class);

        return iDomainApiService;
    }
}
