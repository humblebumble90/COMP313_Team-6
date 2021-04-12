package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.Reward;

public class CustomerRepRewardActivity extends AppCompatActivity {
    // todo: Add api functionality
    RecyclerView rewardsRecyclerView;
//    String name[], detail[], company[];
    private Reward[] rewards;
    private Fat2FitApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rep_reward);

        api = Fat2FitApi.getInstance(this);
        rewardsRecyclerView = findViewById(R.id.rewardsRecyclerView);
//        name = getResources().getStringArray(R.array.reward);
//        detail = getResources().getStringArray(R.array.reward_details);
//        company = getResources().getStringArray(R.array.reward_company);

        fetchRewards();
    }

    private void fetchRewards() {
        api.getRewards(res -> {
            rewards = res.getData();
            updateRecycler();
        }, err -> {
            Toast.makeText(
                getApplicationContext(),
                err.getMessage(),
                Toast.LENGTH_SHORT).show();
        });
    }

    private void updateRecycler() {
        RewardAdaptor rewardAdaptor = new RewardAdaptor(this, rewards);
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