package com.example.fat2fit;

import android.content.Intent;
import android.os.Bundle;

import com.example.fat2fit.tests.TestAdminActivity;
import com.example.fat2fit.tests.TestCreateGroupActivitiesActivity;
import com.example.fat2fit.tests.TestCreateGroupActivity;
import com.example.fat2fit.tests.TestFitnessDataActivity;
import com.example.fat2fit.tests.TestJoin_LeaveGroupActivity;
import com.example.fat2fit.tests.TestLoginActivity;
import com.example.fat2fit.tests.TestPassResetActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class TestNavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_nav);
    }
    public void Story1(View view){
        startActivity(new Intent(getApplicationContext(), TestLoginActivity.class));
    }
    public void Story2(View view){
        startActivity(new Intent(getApplicationContext(), TestPassResetActivity.class));
    }
    public void Story3(View view){
        startActivity(new Intent(getApplicationContext(), TestFitnessDataActivity.class));
    }
    public void Story4(View view){
        startActivity(new Intent(getApplicationContext(), TestCreateGroupActivity.class));
    }
    public void Story5(View view){
        startActivity(new Intent(getApplicationContext(), TestJoin_LeaveGroupActivity.class));
    }
    public void Story6(View view){
        startActivity(new Intent(getApplicationContext(), TestCreateGroupActivitiesActivity.class));
    }
    public void Story7(View view){
        startActivity(new Intent(getApplicationContext(), TestAdminActivity.class));
    }
//    public void Story8(View view){
//        startActivity(new Intent(getApplicationContext(), .class));
//    }
    public void Home(View view){
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }
}