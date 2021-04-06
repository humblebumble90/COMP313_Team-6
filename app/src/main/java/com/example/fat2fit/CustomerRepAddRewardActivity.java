package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CustomerRepAddRewardActivity extends AppCompatActivity {
    // todo: Add api functionality
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rep_add_reward);
    }
    public void submit(View view)
    {
        String text = "Your Reward Has Been Created";
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        startActivity(new Intent(CustomerRepAddRewardActivity.this, CustomerRepRewardActivity.class));
    }
    public void goBack(View view)
    {
        startActivity(new Intent(CustomerRepAddRewardActivity.this, CustomerRepRewardActivity.class));
    }
}