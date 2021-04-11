package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.Participant;
import com.example.fat2fit.models.Challenge;
import com.example.fat2fit.ChallengeAdaptor;

import java.lang.reflect.Array;
import java.util.List;

public class ViewParticipatedChallengesActivity extends AppCompatActivity {
    private Fat2FitApi api;
    private ListView listView;
    ChallengeAdaptor challengeAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_participated_challenges);
        api =  Fat2FitApi.getInstance(this);

    }
    void listParticipatedView(View v)
    {
        api.getMyActiveChallenges(res -> {
                    listView = findViewById(R.id.listChallenges);
                    Participant[] participants = res.getData();
                    List<Challenge> challengeList = new ArrayList<Challenge>();
                    for(Participant p : participants)
                    {
                        challengeList.add(p.getChallenge());
                    }

                    ArrayAdapter<Challenge> adapter = new ArrayAdapter<Challenge>(this,R.layout.challenge_list, challengeList);
                    listView.setAdapter(adapter);





                },
                error -> {});
    }
}