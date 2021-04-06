package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CustomerRepEditReward extends AppCompatActivity {
// todo: Add api functionality
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rep_edit_reward);
    }
    public void submit(View view)
    {
        String text = "Your Challenge Has Been Edited";
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        startActivity(new Intent(CustomerRepEditReward.this, CustomerRepRewardActivity.class));
    }
    public void goBack(View view)
    {
        startActivity(new Intent(CustomerRepEditReward.this, CustomerRepRewardActivity.class));
    }
}