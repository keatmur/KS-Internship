package com.example.ks_internship.app.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {


    private static RestClient sInstance = new RestClient();

    private ApiService service;
    private Retrofit retrofit;

    private final static String API_URL = "https://api.deezer.com";

    private RestClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .create();

        retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(API_URL)
                .build();

        service = retrofit.create(ApiService.class);

    }

    public static RestClient getsInstance() {
        return sInstance;
    }

    public ApiService getService() {
        return service;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
