package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CustomerRepAddChallengeActivity extends AppCompatActivity {
    //todo: add way to add challenges to list view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rep_add_challenge);
    }
    public void submit(View view)
    {
        String text = "Your Challenge Has Been Created";
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        startActivity(new Intent(CustomerRepAddChallengeActivity.this, CustomerRepChallengesActivity.class));
    }
    public void goBack(View view)
    {
        finish();
    }
}