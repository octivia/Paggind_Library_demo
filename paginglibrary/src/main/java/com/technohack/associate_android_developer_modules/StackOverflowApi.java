package com.technohack.associate_android_developer_modules;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StackOverflowApi {

    //https://api.stackexchange.com/2.2/answers?page=1&pagesize=50&site=stackoverflow

    @GET("answers")
    Call<StackOverFlowResponse> getAnswers(

            @Query("page") int page ,
            @Query("pagesize") int  pagesize ,
            @Query("size") String site

    );
}
