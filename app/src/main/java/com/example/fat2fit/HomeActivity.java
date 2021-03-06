package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fat2fit.tests.TestNavActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void recommendedWorkout(View view)
    {
        Toast.makeText(this, "Recommended workout selected!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(HomeActivity.this, RecommendedWorkoutActivity.class));
    }

    public void groupCreation(View view)
    {
        Toast.makeText(this, "Group creation selected!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(HomeActivity.this, GroupCreationActivity.class));
    }

    public void userProfile(View view)
    {
        Toast.makeText(this, "User profile selected!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(HomeActivity.this, UserProfileActivity.class));
    }
    public void customerRep(View view) {
        Toast.makeText(this, "Customer rep selected!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(HomeActivity.this, CustomerRepChallengesActivity.class));

    }
    public void joinGroup(View view)
    {
        Toast.makeText(this, "Join activity selected!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), Join_LeaveGroupActivity.class));
    }

    public void ActivityCreation(View View)
    {
        Toast.makeText(this, "Create activity selected!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), CreateActivity.class));
    }

    public void TestAdminView(View view)
    {
        startActivity(new Intent(getApplicationContext(), AdminActivity.class));
    }
    public void UnitTest(View view)
    {
        startActivity(new Intent(getApplicationContext(), TestNavActivity.class));
    }

    public void RemoveGrpMbr(View view)
    {
        startActivity(new Intent(getApplicationContext(), group_member_remove.class));
    }

    public void CustomerRepReward(View view)
    {
        startActivity(new Intent(getApplicationContext(), CustomerRepRewardActivity.class));
    }

    public void ParticipateInChallenge(View v) {
        startActivity(new Intent(getApplicationContext(), ParticipateInChallengeActivity.class));
    }

    public void testViewParticipatedChallenges(View v)
    {
        startActivity(new Intent(getApplicationContext(), ViewParticipatedChallengesActivity.class));
    }

}