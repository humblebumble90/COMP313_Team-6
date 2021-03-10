package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);
    }
// todo: create function to allow values to be submitted to the view and be displayed
    public void decline(View view)
    {
        startActivity(new Intent(WorkoutDetailsActivity.this, RecommendedWorkoutActivity.class));
    }
    public void accept(View view)
    {
        TextView challengeName = findViewById(R.id.challengeName);

        Context context = getApplicationContext();
        String text = "You Have Accepted The Challenge:" + challengeName.getText().toString();

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        startActivity(new Intent(WorkoutDetailsActivity.this, HomeActivity.class));
    }
}