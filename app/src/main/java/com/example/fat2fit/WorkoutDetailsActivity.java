package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutDetailsActivity extends AppCompatActivity {

    TextView name, detail;

    String data1, data2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);

        name = findViewById(R.id.workoutName);
        detail = findViewById(R.id.workoutDetails);
        getData();
        setData();
    }

    private void getData(){
        if(getIntent().hasExtra("name") && getIntent().hasExtra("detail")){

            data1 = getIntent().getStringExtra("name");
            data2 = getIntent().getStringExtra("detail");
        }
        else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        name.setText(data1);
        detail.setText(data2);
    }
}