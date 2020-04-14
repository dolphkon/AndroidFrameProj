package com.example.frameproj.net;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofitclient {
    private static final int DEFAULT_TIMEOUT = 60;
    private ApiService apiService;
    private static OkHttpClient okHttpClient;
    public static String baseUrl = Config.BASE_URL;

    private static Retrofit retrofit;

    private static class SingletonHolder {
        private static Retrofitclient INSTANCE = new Retrofitclient();
    }

    private Retrofitclient() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new DataEncryptInterceptor())
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build();
    }

    public static Retrofitclient get() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 网络请求
     */
    public ApiService getService() {
        if (apiService == null) {
            apiService = retrofit.create(ApiService.class);
        }

        return apiService;
    }

}
