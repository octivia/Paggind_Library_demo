package com.technohack.associate_android_developer_modules;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.technohack.associate_android_developer_modules.paging_adapter.ItemAdapter;
import com.technohack.associate_android_developer_modules.paging_adapter.ItemViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagingLibrary extends AppCompatActivity {

    ItemAdapter itemAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_library);

        recyclerView=findViewById(R.id.recycler_viewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ItemViewModel itemViewModel=ViewModelProviders.of(this).get(ItemViewModel.class);
        itemAdapter=new ItemAdapter(this);

        itemViewModel.listLiveData.observe(this, new Observer<PagedList<StackOverFlowResponse.ItemsBean>>() {
            @Override
            public void onChanged(@Nullable PagedList<StackOverFlowResponse.ItemsBean> itemsBeans) {

                itemAdapter.submitList(itemsBeans);
            }
        });

        recyclerView.setAdapter(itemAdapter);

    }
}
