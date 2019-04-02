package com.technohack.associate_android_developer_modules;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //https://api.stackexchange.com/2.2/answers?page=1&pagesize=50&site=stackoverflow

    private static final String BASE_URL="https://api.stackexchange.com/2.2/";

    private static RetrofitClient retrofitClient;

    private Retrofit retrofit;

    private RetrofitClient(){

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized RetrofitClient getRetrofitClient(){

        if(retrofitClient==null){
            retrofitClient=new RetrofitClient();
        }
        return retrofitClient;
    }

    public StackOverflowApi getApi(){
        return  retrofit.create(StackOverflowApi.class);
    }

}
