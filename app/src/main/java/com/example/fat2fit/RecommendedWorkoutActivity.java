package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RecommendedWorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_workout);
    }

    public void viewDetails(View view)
    {
        startActivity(new Intent(RecommendedWorkoutActivity.this, WorkoutDetailsActivity.class));
    }
}