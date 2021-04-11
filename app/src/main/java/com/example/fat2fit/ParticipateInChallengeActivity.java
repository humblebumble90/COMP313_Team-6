package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.fat2fit.api.ApiResponse;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.helpers.StringHelper;
import com.example.fat2fit.models.Challenge;
import com.example.fat2fit.models.Participant;

import java.util.ArrayList;

public class ParticipateInChallengeActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private Fat2FitApi api;

    private Spinner participateSpinner;
    private Button  participateButton;

    private boolean gotChallenges = false;

    private Challenge[] challenges;
    ArrayAdapter<String> adapter;
    private String selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participate_in_challenge);

        api = Fat2FitApi.getInstance(this);

        participateSpinner = findViewById(R.id.participateSpinner);
        participateButton = findViewById(R.id.participateButton);
        participateButton.setEnabled(false);

        api.getAvailableChallenges(this::handleGetChallenges, this::handleError);
    }


    private void handleGetChallenges(ApiResponse<Challenge[]> res) {
        challenges = res.getData();
        String[] titles = new String[challenges.length];
        for (int i = 0; i < challenges.length; ++i) {
            titles[i] = challenges[i].getTitle();
        }

        adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                titles);

        participateSpinner.setAdapter(adapter);
        participateSpinner.setOnItemSelectedListener(this);
        participateButton.setOnClickListener(this::onParticipateClick);
        gotChallenges = true;
        participateButton.setEnabled(true);
    }

    public void onParticipateClick(View v) {
        if (StringHelper.isNullOrEmpty(selectedId)) {
            showToast("Must select an item");
        } else {
            participateButton.setEnabled(false);
            api.participateInChallenge(selectedId, this::handleParticipate, this::handleError);
        }
    }

    private void handleParticipate(ApiResponse<Participant> res) {
        // Participant participant = res.getData();
        showToast(res.getMeta().getMsg());
        finish();
    }

    private void handleError(VolleyError err) {
        showToast(err.getMessage());
        participateButton.setEnabled(gotChallenges);
    }

    private void showToast(String msg) {
        Toast.makeText(
            getApplicationContext(), msg,
            Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedId = challenges[i].get_id();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selectedId = null;
    }
}