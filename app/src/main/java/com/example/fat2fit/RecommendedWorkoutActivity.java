package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RecommendedWorkoutActivity extends AppCompatActivity {
    String workoutName[], workoutsDetails[];
    RecyclerView workoutRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_workout);

        workoutRecyclerView = findViewById(R.id.workoutsRecyclerView);
        workoutName = getResources().getStringArray(R.array.workouts);
        workoutsDetails = getResources().getStringArray(R.array.workouts_details);

        WorkoutAdaptor workoutAdaptor = new WorkoutAdaptor(this, workoutName, workoutsDetails);
        workoutRecyclerView.setAdapter(workoutAdaptor);
        workoutRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}