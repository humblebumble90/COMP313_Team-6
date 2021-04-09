package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CustomerRepRewardActivity extends AppCompatActivity {
    // todo: Add api functionality
    RecyclerView rewardsRecyclerView;
    String name[], detail[], challenge[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rep_reward);

        rewardsRecyclerView = findViewById(R.id.rewardsRecyclerView);
        name = getResources().getStringArray(R.array.reward);
        detail = getResources().getStringArray(R.array.reward_details);
        challenge = getResources().getStringArray(R.array.reward_challenge);

        RewardAdaptor rewardAdaptor = new RewardAdaptor(this, name, detail, challenge);
        rewardsRecyclerView.setAdapter(rewardAdaptor);
        rewardsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void addReward(View view)
    {
        startActivity(new Intent(CustomerRepRewardActivity.this, CustomerRepAddRewardActivity.class));
    }
    public void editReward(View view)
    {
        startActivity(new Intent(CustomerRepRewardActivity.this, CustomerRepEditReward.class));
    }
    public void goBack(View view)
    {
        finish();
    }
}