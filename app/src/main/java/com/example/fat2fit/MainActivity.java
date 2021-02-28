package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fat2fit.api.Fat2FitApi;

public class MainActivity extends AppCompatActivity {
    // private Fat2FitApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // api = Fat2FitApi.getInstance(this);
        // api.login("email", "password", res -> {}, err -> {});
    }
}