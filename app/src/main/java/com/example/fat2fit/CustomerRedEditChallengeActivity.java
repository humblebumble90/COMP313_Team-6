package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerRedEditChallengeActivity extends AppCompatActivity {
//todo add a way to edit challenges inside the list view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_red_edit_challenge);
    }
    public void goBack(View view)
    {
        finish();
    }
    public void edit(View view)
    {
        String text = "Your Challenge Has Been Edited";
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        finish();
    }
}