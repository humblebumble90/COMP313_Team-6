package com.example.fat2fit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fat2fit.models.Reward;

public class RewardAdaptor extends RecyclerView.Adapter<RewardAdaptor.RewardViewHolder> {

    String data1[], data2[], data3[];
    private Reward[] rewards;
    Context context;

    public RewardAdaptor(Context ct, Reward[] rewards){
        context = ct;
        this.rewards = rewards;
    }
    @NonNull
    @Override
    public RewardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.reward_list, parent, false);
        return new RewardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardViewHolder holder, int position) {
        holder.setReward(rewards[position]);
    }

    @Override
    public int getItemCount() {
        return rewards != null ? rewards.length : 0;
    }

    public static class RewardViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Reward reward;

        private final TextView rewardName, rewardDetail, rewardCompany;


        public RewardViewHolder(@NonNull View itemView) {
            super(itemView);
            rewardName = itemView.findViewById(R.id.rewardName);
            rewardDetail = itemView.findViewById(R.id.rewardDetail);
            rewardCompany = itemView.findViewById(R.id.rewardCompany);
            Button editRewardButton = itemView.findViewById(R.id.editRewardButton);
            editRewardButton.setOnClickListener(this);
        }

        public void setReward(Reward reward) {
            this.reward = reward;
            rewardName.setText(reward.getTitle());
            rewardDetail.setText(reward.getDescription());
            rewardCompany.setText(reward.getCompany());
        }

        @Override
        public void onClick(View view) {
            Context ctx = itemView.getContext();
            Intent intent = new Intent(ctx, CustomerRepEditReward.class);
            intent.putExtra("_id", reward.get_id());
            intent.putExtra("title", reward.getTitle());
            intent.putExtra("description", reward.getDescription());
            intent.putExtra("company", reward.getCompany());
            ctx.startActivity(intent);
        }
    }
}
