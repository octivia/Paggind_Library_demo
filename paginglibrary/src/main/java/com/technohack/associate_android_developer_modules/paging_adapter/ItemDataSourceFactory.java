package com.technohack.associate_android_developer_modules.paging_adapter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.technohack.associate_android_developer_modules.StackOverFlowResponse;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer,StackOverFlowResponse.ItemsBean>> mutableLiveData=new MutableLiveData<>();

    public MutableLiveData<PageKeyedDataSource<Integer, StackOverFlowResponse.ItemsBean>> getMutableLiveData() {
        return mutableLiveData;
    }

    @Override
    public DataSource create() {
       ItemSources itemSources=new ItemSources();
       mutableLiveData.postValue(itemSources);
       return  itemSources;

    }


}
