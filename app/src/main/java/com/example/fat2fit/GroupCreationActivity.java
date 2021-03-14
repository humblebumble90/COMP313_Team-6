package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.Group;

public class GroupCreationActivity extends AppCompatActivity {

    private Fat2FitApi api;

    private EditText grpName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creation);
        api = Fat2FitApi.getInstance(this);
    }
// todo: doesnt actually create a group yet, just has the views and toast. Create Functions to create group [DONE]
    //TODO: Add create group notification
    public void createGroup(View view)
    {
        grpName = findViewById(R.id.groupName);
        String groupName = grpName.getText().toString();
        api.createGroup(groupName, res -> {
            Group group = res.getData();
            String text = String.format("ID: %s\nName:%s\nCoach:%s",
                    group.get_id(),
                    group.getName(),
                    group.getCoach().getEmail());
            //setSuccessText(text);
        }, err -> {
            // Unsuccessful
        });

        startActivity(new Intent(GroupCreationActivity.this, HomeActivity.class));

        //OLD Stuff CLEANUP LATER
        //EditText groupName = findViewById(R.id.groupName);
        //Context context = getApplicationContext();
        //String text = "Your Group Has Been Created \n Group Name:" + groupName.getText().toString();

        //int duration = Toast.LENGTH_SHORT;

        //Toast toast = Toast.makeText(context, text, duration);
        //toast.show();
    }
}
