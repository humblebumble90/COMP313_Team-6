package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.Challenge;
import com.example.fat2fit.models.Reward;

import java.util.ArrayList;

public class CustomerRedEditChallengeActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {
//todo add a way to edit challenges inside the list view

    private TextView challengeRewardTextView;
    private Spinner rewardsSpinner;

    private Fat2FitApi api;

    private Reward[] rewards;

    private Reward selectedReward, currentReward;

    private Challenge challenge = new Challenge();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_red_edit_challenge);
        challengeRewardTextView = findViewById(R.id.challengeRewardTextView);
        rewardsSpinner = findViewById(R.id.rewardsSpinner);
        api = Fat2FitApi.getInstance(this);
        unpackIntent();
        grabRewards();
    }

    private void unpackIntent() {
        Intent in = getIntent();
        challenge.set_id(in.getStringExtra("_id"));
        challenge.setCloses(in.getStringExtra("closes"));
        challenge.setDescription(in.getStringExtra("description"));
        challenge.setTitle(in.getStringExtra("title"));
        challenge.setState(in.getCharExtra("state", Challenge.State.AVAILABLE));
        challenge.setDistance(in.getDoubleExtra("distance", 20));
    }

    private void grabRewards() {
        api.getChallengeReward(challenge.get_id(), res -> {
            currentReward = res.getData();
            if (currentReward == null) {
                challengeRewardTextView.setText("No Reward for this challenge");
            } else {
                challengeRewardTextView.setText(
                        "Current reward: " + currentReward.getTitle()
                );
            }
        }, err -> showToast(err.getMessage()));

        api.getRewards(res -> {
            rewards = res.getData();
            String[] titles =  new String[rewards.length];
            for (int i = 0; i < rewards.length; ++i) {
               titles[i] = rewards[i].getTitle();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                    android.R.layout.simple_spinner_dropdown_item, titles);

            rewardsSpinner.setAdapter(adapter);
            rewardsSpinner.setOnItemSelectedListener(this);

        }, err -> {
            showToast(err.getMessage());
        });
    }

    public void goBack(View view)
    {
        finish();
    }
    public void edit(View view)
    {

        String rewardId = null;

        if (selectedReward != null) {
            rewardId = selectedReward.get_id();
        }

        api.updateChallenge(challenge, rewardId, res -> {
            showToast(res.getMeta().getMsg());
            finish();
        }, err -> showToast(err.getMessage()));

        String text = "Your Challenge Has Been Edited";
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
//        finish();
    }


    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedReward = rewards[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selectedReward = currentReward;
    }
}