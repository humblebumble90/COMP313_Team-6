package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fat2fit.api.Fat2FitApi;

public class CreateActivity extends AppCompatActivity {

    private String title, description, hyperlink, groupIdentify;
    private EditText groupTitleText, groupDescText, groupHyperLinkText, groupId;

    private Fat2FitApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        api = Fat2FitApi.getInstance(this);

        groupTitleText = (EditText) findViewById(R.id.groupTitleText);
        groupDescText = (EditText) findViewById(R.id.groupDescText);
        groupHyperLinkText = (EditText) findViewById(R.id.groupHyperLinkText);
        groupId = (EditText) findViewById(R.id.groupIdentify);

    }

    public void createActivity(View view) {

        String id = groupId.getText().toString();
        String title = groupTitleText.getText().toString();
        String desc = groupDescText.getText().toString();
        String hyperlink = groupHyperLinkText.getText().toString();

        api.createGroupActivity(id, title, desc, hyperlink, res -> {
            Toast.makeText(
                    getApplicationContext(),
                    "Successfully created group activity",
                    Toast.LENGTH_LONG).show();
            finish();
            //startActivity(new Intent(CreateActivity.this, HomeActivity.class));
        }, err -> {
            Toast.makeText(
                    getApplicationContext(),
                    err.getMessage(),
                    Toast.LENGTH_LONG).show();
        });
    }
}