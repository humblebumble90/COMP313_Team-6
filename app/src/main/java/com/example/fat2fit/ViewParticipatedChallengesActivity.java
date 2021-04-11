package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.Challenge;
import com.example.fat2fit.models.Participant;

import java.util.List;

public class ViewParticipatedChallengesActivity extends AppCompatActivity {
    private Fat2FitApi api;
    private RecyclerView recyclerView;
    ChallengeAdaptor challengeAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_participated_challenges);
        api =  Fat2FitApi.getInstance(this);
        recyclerView = findViewById(R.id.listChallenges);
    }
    void listParticipatedView(View v)
    {
        api.getMyActiveChallenges(res -> {
                    Participant[] participants = res.getData();
                    List<Challenge> challengeList = new ArrayList<>();
                    for (Participant p : participants) {
                        challengeList.add(p.getChallenge());
                    }
                    // List<Participant> participantsList = Arrays.asList(participants);
                    challengeAdaptor = new ChallengeAdaptor(this, challengeList);
                    recyclerView.setAdapter(challengeAdaptor);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));

                },
                error -> {});
    }
    public void returnToHome(View v)
    {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }
}