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
        /*when pressed will move the screen to a new activity */
        Button recommendedWorkoutBtn = (Button) findViewById(R.id.recommendedWorkoutBtn);
        Button groupCreationBtn = (Button) findViewById(R.id.groupCreationBtn);
        recommendedWorkoutBtn.setOnClickListener (new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, RecommendedWorkoutActivity.class));
            }
        });
        groupCreationBtn.setOnClickListener (new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, GroupCreationActivity.class));
            }
        });
    }
}