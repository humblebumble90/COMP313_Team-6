package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.GroupActivity;
import com.example.fat2fit.models.Workout;

public class RecommendedWorkoutActivity extends AppCompatActivity {
    Workout[] recommendedWorkouts;
    RecyclerView workoutRecyclerView;
    Fat2FitApi api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_workout);

        api = Fat2FitApi.getInstance(this);
        api.getRecommendedWorkouts(res -> {
            recommendedWorkouts = res.getData();
        },err -> {
            Toast.makeText(this, err.getMessage(),Toast.LENGTH_LONG);
        });
        workoutRecyclerView = findViewById(R.id.workoutsRecyclerView);
        WorkoutAdaptor workoutAdaptor = new WorkoutAdaptor(this, recommendedWorkouts);
        //workoutRecyclerView.setAdapter(workoutAdaptor);
        //workoutRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}