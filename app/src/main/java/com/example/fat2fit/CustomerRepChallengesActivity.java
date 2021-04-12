package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.Challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerRepChallengesActivity extends AppCompatActivity {

    private RecyclerView challengeList;

    private Fat2FitApi api;

    //todo: add way to display challenges to list view
    // I was thinking of making it so when you click on one of the challenges inside the listview it would take you to the edit/view details page.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rep_challenges);
        challengeList = findViewById(R.id.challengeList);
        api = Fat2FitApi.getInstance(this);
        getChallenges();
    }

    private void getChallenges() {
        api.getAllChallenges(res -> {
            List<Challenge> challenges = new ArrayList<>();
            Collections.addAll(challenges, res.getData());
            ChallengeAdaptor adaptor = new ChallengeAdaptor(this, challenges);
            adaptor.setOnClick(challenge -> {
                Intent intent = new Intent(
                        this, CustomerRedEditChallengeActivity.class);
                intent.putExtra("_id", challenge.get_id());
                intent.putExtra("title", challenge.getTitle());
                intent.putExtra("description", challenge.getDescription());
                intent.putExtra("closes", challenge.getCloses());
                intent.putExtra("state", challenge.getState());
                intent.putExtra("distance", challenge.getDistance());
                startActivity(intent);
            });
            challengeList.setAdapter(adaptor);
            challengeList.setLayoutManager(new LinearLayoutManager(this));
        }, err -> {
            Toast.makeText(
                    getApplicationContext(),
                    err.getMessage(),
                    Toast.LENGTH_SHORT).show();
        });
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