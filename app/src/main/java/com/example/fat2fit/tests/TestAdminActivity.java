package com.example.fat2fit.tests;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.fat2fit.R;
import com.example.fat2fit.api.Fat2FitApi;

public class TestAdminActivity extends AppCompatActivity {
    Fat2FitApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_admin2);
        api = Fat2FitApi.getInstance(this);
    }

    public void Test1()
    {
        //Test1: modify existing user
        //Condition:
        //Id:604e614f466f440015e9f3e7
        //firstName: John
        //lastName: Doe
        //Expected outcome:
        //“Successfully updated

        String id = "604e614f466f440015e9f3e7";
        String fName = "John";
        String lName = "Doe";
        api.login("amin@email.com","Password#123$", res ->
                {

                },
                err->
                {

                });
    }

    public void Test2()
    {

    }

    public void Test3()
    {

    }

    public void Test4()
    {

    }
}