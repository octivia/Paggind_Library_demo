package com.technohack.associate_android_developer_modules.paging_adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.technohack.associate_android_developer_modules.R;
import com.technohack.associate_android_developer_modules.StackOverFlowResponse;

public class ItemAdapter extends PagedListAdapter<StackOverFlowResponse.ItemsBean,ItemAdapter.MyViewHolder> {

    // Todo
    // Whenever we have the api in which we need to mention in page number then in that context we have to use the PageListAdapter as Adapter

    Context context;

    public ItemAdapter(Context context) {
        super(diffCallback);

        this.context=context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.single_list_item,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        StackOverFlowResponse.ItemsBean item=getItem(position);

        if(item!=null){

            Glide.with(context).load(item.getOwner().getProfile_image()).into(myViewHolder.imageView);

            myViewHolder.textView.setText(item.getOwner().getDisplay_name());


        }

    }

    //This will use to check the values of the answer whether the page id is same or not
    //to get the difference between the objects
    private static DiffUtil.ItemCallback<StackOverFlowResponse.ItemsBean> diffCallback=new DiffUtil.ItemCallback<StackOverFlowResponse.ItemsBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull StackOverFlowResponse.ItemsBean oldItem, @NonNull StackOverFlowResponse.ItemsBean newItem) {
            return  oldItem.getAnswer_id()==newItem.getAnswer_id();
        }

        @Override
        public boolean areContentsTheSame(@NonNull StackOverFlowResponse.ItemsBean oldItem, @NonNull StackOverFlowResponse.ItemsBean newItem) {
            return oldItem.equals(newItem);
        }
    };


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageViewId);
            textView=itemView.findViewById(R.id.textViewId);

        }
    }
}
