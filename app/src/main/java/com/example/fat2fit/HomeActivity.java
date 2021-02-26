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
        setContentView(R.layout.activity_main);
        /*when pressed will move the screen to a new activity */
        Button launchBtn = (Button) findViewById(R.id.loginBtn);
        launchBtn.setOnClickListener (new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, RecommendedWorkoutActivity.class));
            }
        });
    }
}