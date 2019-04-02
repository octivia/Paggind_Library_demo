package com.technohack.associate_android_developer_modules.paging_adapter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.technohack.associate_android_developer_modules.StackOverFlowResponse;

public class ItemViewModel extends ViewModel {

    public LiveData<PagedList<StackOverFlowResponse.ItemsBean>> listLiveData;
    public LiveData<PageKeyedDataSource<Integer,StackOverFlowResponse.ItemsBean>>  pageKeyedDataSourceLiveData;

    public ItemViewModel(){

        ItemDataSourceFactory itemDataSourceFactory=new ItemDataSourceFactory();
        pageKeyedDataSourceLiveData=itemDataSourceFactory.getMutableLiveData();

        PagedList.Config  config=new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(ItemSources.PAGE_SIZE)
                .build();

        listLiveData=new LivePagedListBuilder(itemDataSourceFactory,config).build();


    }
}
