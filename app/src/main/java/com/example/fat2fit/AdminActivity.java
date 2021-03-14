package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.User;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    ListView workoutUserList;
    Fat2FitApi api;
    EditText editTextUsername;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        workoutUserList = findViewById(R.id.workout_user_list);
        api = Fat2FitApi.getInstance(this);


    }

    public void search(View view) {
        editTextUsername = findViewById(R.id.EditTextUsername);
        username = editTextUsername.getText().toString();


    }

    public void reset(View view) {
    }
}