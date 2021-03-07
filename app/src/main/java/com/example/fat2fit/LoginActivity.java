package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    //this is just temporary, should be replaced with proper login fields and stuff
    public void login(View view)
    {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }
}