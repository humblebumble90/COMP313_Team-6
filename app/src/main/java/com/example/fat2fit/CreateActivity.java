package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fat2fit.api.Fat2FitApi;

public class CreateActivity extends AppCompatActivity {

    private String title, description, hyperlink, groupIdentify;
    private EditText groupTitleText, groupDescText, groupHyperLinkText, groupId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        groupTitleText = (EditText) findViewById(R.id.groupTitleText);
        groupDescText = (EditText) findViewById(R.id.groupDescText);
        groupHyperLinkText = (EditText) findViewById(R.id.groupHyperLinkText);
        groupId = (EditText) findViewById(R.id.groupIdentify);

    }

    public void createActivity(View view) {
        //TODO
        setContentView(R.layout.activity_home);

    }
}