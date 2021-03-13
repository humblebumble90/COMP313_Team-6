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
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MDRiZGYwMGQxNGQ5ZjAwMTUzMmUwYjIiLCJlbWFpbCI6ImZpYWNyZUBlbWFpbC5jb20iLCJyb2xlIjoiVSIsInRzIjoxNjE1NTg1MDY1NDc4LCJpYXQiOjE2MTU1ODUwNjUsImV4cCI6MTYxNjE4OTg2NX0.E_Pea-RnJ9Z0tQy1tuPbq-MMzB4Td_sgB3SYOws4Ydw";

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

    public void joinGroup(View view)
    {
        Fat2FitApi api = Fat2FitApi.getInstance(this);
        Context context = getApplicationContext();
        api.setAuthorization(token);
        String groupId = "SR7HZU"; // not real, use "SR7HZU" 0DMTAK
        api.joinGroup(groupId, res -> {
            Group group = res.getData();
           group.set_id(groupId);
            text = "You have successfully joined the Group";
            //group.setMembers();
        }, err -> {
            // Unsuccessful
            text = "An error occurred. Check Group Code.";
        });



        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        startActivity(new Intent(Join_LeaveGroupActivity.this, HomeActivity.class));
    }
}