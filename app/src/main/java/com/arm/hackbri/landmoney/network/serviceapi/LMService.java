package com.arm.hackbri.landmoney.network.serviceapi;

import com.arm.hackbri.landmoney.network.LMEndpointURL;
import com.arm.hackbri.landmoney.network.converter.LMResponseConverter;
import com.arm.hackbri.landmoney.network.converter.StringResponseConverter;
import com.arm.hackbri.landmoney.network.interceptor.LMInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * LMService
 * Created by anggaprasetiyo on 9/29/16.
 */

public class LMService {
    private static LMService ourInstance = new LMService();
    private LMApi api;

    public static LMService getInstance() {
        return ourInstance;
    }

    private LMService() {
        OkHttpClient client = new OkHttpClient();
        Interceptor interceptor = new LMInterceptor();
        client.interceptors().add(interceptor);
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        client.interceptors().add(logInterceptor);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();

        Retrofit.Builder retrofit = new Retrofit.Builder();
        String baseUrl = LMEndpointURL.BASE_URL;
        retrofit.baseUrl(baseUrl);
        retrofit.addConverterFactory(new LMResponseConverter());
        retrofit.addConverterFactory(new StringResponseConverter());
        retrofit.addConverterFactory(GsonConverterFactory.create(gson));
        retrofit.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        retrofit.client(client);
        this.api = retrofit.build().create(LMApi.class);
    }

    public LMApi getApi() {
        return api;
    }
}
