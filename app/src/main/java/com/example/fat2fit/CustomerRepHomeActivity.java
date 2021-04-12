package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CustomerRepHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rep_home);
    }

    public void viewChallenges(View v) {
        Intent intent = new Intent(getApplicationContext(), CustomerRepChallengesActivity.class);
        startActivity(intent);
    }

    public void viewRewards(View v) {
        Intent intent = new Intent(getApplicationContext(), CustomerRepRewardActivity.class);
        startActivity(intent);
    }

}