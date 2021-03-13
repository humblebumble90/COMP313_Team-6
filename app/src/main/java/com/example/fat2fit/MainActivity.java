package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fat2fit.api.ApiDebugActivity;
import com.example.fat2fit.api.ApiResponse;
import com.example.fat2fit.api.Fat2FitApi;

public class MainActivity extends AppCompatActivity {
    // private Fat2FitApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*when pressed will move the screen to a new activity */
        Button launchBtn = (Button) findViewById(R.id.loginBtn);
        launchBtn.setOnClickListener (new View.OnClickListener(){
            public void onClick(View v){
                // startActivity(new Intent(getApplicationContext(), ApiDebugActivity.class));
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        // api = Fat2FitApi.getInstance(this);
        // api.login("email", "password", res -> {}, err -> {});
    }

}