package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void recommendedWorkout(View view)
    {
        startActivity(new Intent(HomeActivity.this, RecommendedWorkoutActivity.class));
    }

    public void groupCreation(View view)
    {
        startActivity(new Intent(HomeActivity.this, GroupCreationActivity.class));
    }

    public void userProfile(View view)
    {
        //for somereason moving to the userprofile activity crashes the app
        System.out.println("user profile button pushed");
        startActivity(new Intent(HomeActivity.this, UserProfileActivity.class));
    }
    public void customerRep(View view) {
        startActivity(new Intent(HomeActivity.this, CustomerRepChallengesActivity.class));

    }
    public void joinGroup(View view)
    {
        startActivity(new Intent(getApplicationContext(), Join_LeaveGroupActivity.class));
    }
}