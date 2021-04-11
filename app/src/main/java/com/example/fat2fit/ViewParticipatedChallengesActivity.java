package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.Participant;

public class ViewParticipatedChallengesActivity extends AppCompatActivity {
    private Fat2FitApi api;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_participated_challenges);
        api =  Fat2FitApi.getInstance(this);
    }
    void listParticipatedView(View v)
    {
        api.getMyActiveChallenges(res -> {
                    listview = findViewById(R.id.listChallenges);
                    Participant[] participants = res.getData();
                    listview.set


                },
                error -> {});
    }
}