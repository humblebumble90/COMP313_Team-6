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

public class GroupCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creation);
    }
// todo: doesnt actually create a group yet, just has the views and toast. Create Functions to create group
    public void createGroup(View view)
    {
        EditText groupName = findViewById(R.id.groupName);
        Context context = getApplicationContext();
        String text = "Your Group Has Been Created \n Group Name:" + groupName.getText().toString();

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        startActivity(new Intent(GroupCreationActivity.this, HomeActivity.class));
    }
}
