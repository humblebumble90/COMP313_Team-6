package com.example.fat2fit.tests;

import android.content.Intent;
import android.os.Bundle;

import com.example.fat2fit.R;
import com.example.fat2fit.tests.TestAdminActivity;
import com.example.fat2fit.tests.TestAdminActivity2_Searching;
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
        launchTest(TestLoginActivity.class);
    }
    public void Story2(View view){
        launchTest(TestPassResetActivity.class);
    }
    public void Story3(View view){
        launchTest(TestFitnessDataActivity.class);
    }
    public void Story4(View view){
        launchTest(TestCreateGroupActivity.class);
    }
    public void Story5(View view){
        launchTest(TestJoin_LeaveGroupActivity.class);
    }
    public void Story6(View view){
        launchTest(TestCreateGroupActivitiesActivity.class);
    }
    public void Story7(View view){
        launchTest(TestAdminActivity.class);
    }
    public void Story8(View view){
        launchTest(TestAdminActivity2_Searching.class);
    }
    public void Home(View view){
        finish();
    }

    private void launchTest(Class<?> class0) {
        Intent intent = new Intent(getApplicationContext(), class0);
        startActivity(intent);
    }
}