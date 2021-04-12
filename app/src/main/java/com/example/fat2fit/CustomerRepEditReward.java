package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.Reward;

public class CustomerRepEditReward extends AppCompatActivity {
// todo: Add api functionality and a way to submit edited reward

    private Fat2FitApi api;

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText companyEditText;
    Reward reward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rep_edit_reward);
        api = Fat2FitApi.getInstance(this);
    }
    public void submit(View view)
    {
        titleEditText = findViewById(R.id.rewardName);
        descriptionEditText = findViewById(R.id.rewardDetail);
        companyEditText = findViewById(R.id.rewardCompany);

        String title = titleEditText.getText().toString();;
        String description = descriptionEditText.getText().toString();;
        String company = companyEditText.getText().toString();;

        reward.setTitle(title);
        reward.setDescription(description);
        reward.setCompany(company);

        api.updateReward(reward, res -> {
            Reward reward = res.getData(); // the reward you just updated
            String text = "Your Reward Has Been Edited";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }, err -> {
            Toast.makeText(
                    getApplicationContext(),
                    err.getMessage(),
                    Toast.LENGTH_SHORT).show();
            // handle error
        });
        finish();
    }
    public void goBack(View view)
    {
        finish();
    }
}