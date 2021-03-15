package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.Group;
import com.example.fat2fit.models.User;

public class Join_LeaveGroupActivity extends AppCompatActivity {

    private Button joinButton;
    private Button leaveButton;
    private EditText groupCodeEdx;
    private String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join__leave_group);
        groupCodeEdx = (EditText)findViewById(R.id.edxGroupCode);
        joinButton = (Button)findViewById(R.id.btnJoinGroup);

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinGroup(view);
            }
        });
    }

    public void leaveGroup(View view) {
        Fat2FitApi api = Fat2FitApi.getInstance(this);
        String groupId = groupCodeEdx.getText().toString();
        api.leaveGroup(groupId, res -> {
            Toast.makeText(
                    getApplicationContext(),
                    res.getMeta().getMsg(),
                    Toast.LENGTH_SHORT).show();
            finish();
        }, err -> {
            // Unsuccessful
            Toast.makeText(
                    getApplicationContext(),
                    err.getMessage(),
                    Toast.LENGTH_SHORT).show();
        });
    }

    public void joinGroup(View view)
    {
        Fat2FitApi api = Fat2FitApi.getInstance(this);
        String groupId = groupCodeEdx.getText().toString();
        api.joinGroup(groupId, res -> {
            Toast.makeText(
                    getApplicationContext(),
                    res.getMeta().getMsg(),
                    Toast.LENGTH_SHORT).show();
            finish();
        }, err -> {
            // Unsuccessful
            Toast.makeText(
                    getApplicationContext(),
                    err.getMessage(),
                    Toast.LENGTH_SHORT).show();
        });



        //int duration = Toast.LENGTH_SHORT;

        //Toast toast = Toast.makeText(context, text, duration);
        //toast.show();
        //startActivity(new Intent(Join_LeaveGroupActivity.this, HomeActivity.class));
    }
}