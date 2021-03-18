package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomerRepChallengesActivity extends AppCompatActivity {

    //todo: add way to display challenges to list view
    // I was thinking of making it so when you click on one of the challenges inside the listview it would take you to the edit/view details page.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rep_challenges);
    }
    public void addChallenge(View view)
    {
        startActivity(new Intent(CustomerRepChallengesActivity.this, CustomerRepAddChallengeActivity.class));
    }
    public void goBack(View view)
    {
        finish();
    }
}