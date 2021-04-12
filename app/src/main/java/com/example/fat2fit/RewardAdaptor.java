package com.example.fat2fit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RewardAdaptor extends RecyclerView.Adapter<RewardAdaptor.MyViewHolder> {

    String data1[], data2[], data3[];
    Context context;

    public RewardAdaptor(Context ct, String rewardName[], String rewardDetail[], String rewardCompany[]){
        context = ct;
        data1 = rewardName;
        data2 = rewardDetail;
        data3 = rewardCompany;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.reward_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.rewardName.setText(data1[position]);
        holder.rewardDetail.setText(data2[position]);
        holder.rewardCompany.setText(data3[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView rewardName, rewardDetail, rewardCompany;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rewardName = itemView.findViewById(R.id.rewardName);
            rewardDetail = itemView.findViewById(R.id.rewardDetail);
            rewardCompany = itemView.findViewById(R.id.rewardCompany);
        }
    }
}
