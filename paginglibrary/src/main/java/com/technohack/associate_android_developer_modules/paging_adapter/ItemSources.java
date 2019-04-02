package com.technohack.associate_android_developer_modules.paging_adapter;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.technohack.associate_android_developer_modules.RetrofitClient;
import com.technohack.associate_android_developer_modules.StackOverFlowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemSources extends PageKeyedDataSource<Integer,StackOverFlowResponse.ItemsBean> {

    public static int PAGE_SIZE=50;
    private static int FIRST_PAGE=1;
    private static String SITE="stackoverflow";


    //This method will call initially
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, StackOverFlowResponse.ItemsBean> callback) {

        RetrofitClient.getRetrofitClient()
                .getApi()
                .getAnswers(FIRST_PAGE,PAGE_SIZE,SITE)
                .enqueue(new Callback<StackOverFlowResponse>() {
                    @Override
                    public void onResponse(Call<StackOverFlowResponse> call, Response<StackOverFlowResponse> response) {

                        if(response.body()!=null){

                            //previous key is null because we don't have any page ye
                            callback.onResult(response.body().getItems(),null,FIRST_PAGE+1);

                        }
                    }

                    @Override
                    public void onFailure(Call<StackOverFlowResponse> call, Throwable t) {

                    }
                });
    }

    //this method will call when we want to fetch the previous page
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, StackOverFlowResponse.ItemsBean> callback) {

        RetrofitClient.getRetrofitClient()
                .getApi()
                .getAnswers(params.key,PAGE_SIZE,SITE)
                .enqueue(new Callback<StackOverFlowResponse>() {
                    @Override
                    public void onResponse(Call<StackOverFlowResponse> call, Response<StackOverFlowResponse> response) {

                        if(response.body()!=null){

                            //so in this case we need to decrease the value of the key
                            Integer key=(params.key>1)? params.key -1:null;
                            callback.onResult(response.body().getItems(),key);

                        }
                    }
                    @Override
                    public void onFailure(Call<StackOverFlowResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, StackOverFlowResponse.ItemsBean> callback) {

        RetrofitClient.getRetrofitClient()
                .getApi()
                .getAnswers(params.key,PAGE_SIZE,SITE)
                .enqueue(new Callback<StackOverFlowResponse>() {
                    @Override
                    public void onResponse(Call<StackOverFlowResponse> call, Response<StackOverFlowResponse> response) {

                        if(response.body()!=null){

                            //If it has more page then it will fetch the another page
                            //so in this case we need to increment the key of the page
                            Integer key=response.body().isHas_more()? params.key +1:null;

                            callback.onResult(response.body().getItems(),key);


                        }
                    }

                    @Override
                    public void onFailure(Call<StackOverFlowResponse> call, Throwable t) {

                    }
                });
    }
}
