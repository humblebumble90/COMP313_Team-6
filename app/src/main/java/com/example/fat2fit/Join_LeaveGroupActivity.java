package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.fat2fit.api.ApiResponse;
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
        api.leaveGroup(groupId, this::handleLeaveResponse, this::handleError);
    }

    public void joinGroup(View view) {
        Fat2FitApi api = Fat2FitApi.getInstance(this);
        String groupId = groupCodeEdx.getText().toString();
        api.joinGroup(groupId, this::handleJoinResponse, this::handleError);
    }

    private void handleLeaveResponse(ApiResponse<String> res) {
        Toast.makeText(
                getApplicationContext(),
                res.getMeta().getMsg(),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    private void handleJoinResponse(ApiResponse<Group> res) {
        Toast.makeText(
                getApplicationContext(),
                res.getMeta().getMsg(),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    private void handleError(VolleyError err) {
        Toast.makeText(
                getApplicationContext(),
                err.getMessage(),
                Toast.LENGTH_SHORT).show();
    }
}