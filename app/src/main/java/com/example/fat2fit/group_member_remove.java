package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.fat2fit.api.ApiResponse;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.helpers.StringHelper;
import com.example.fat2fit.models.Group;

public class group_member_remove extends AppCompatActivity {

    private Fat2FitApi api;


    private EditText groupCode;
    private EditText memberID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_member_remove);
        api = Fat2FitApi.getInstance(this);
    }

    //TODO unsure how to approach API aspect.
    public void removeMember(View view)
    {
        //Get group code
        groupCode = findViewById(R.id.grpCode);
        String grpCode = groupCode.getText().toString();

        //Get member ID
        memberID = findViewById(R.id.mbrID);
        String mbrID = memberID.getText().toString();

        if (StringHelper.isBlank(grpCode)) {
            showToast("Group code cannot be blank");
            return;
        } else if (StringHelper.isBlank(mbrID)) {
            showToast("Member ID cannot be blank");
            return;
        }

        api.removeGroupMember(grpCode, mbrID, this::handleResponse, this::handleError);
    }

    private void handleResponse(ApiResponse<Group> res) {
        showToast(res.getMeta().getMsg());
        finish();
    }

    private void handleError(VolleyError err) {
        showToast(err.getMessage());
    }

    private void showToast(String msg) {
        Toast.makeText(
            getApplicationContext(),
            msg, Toast.LENGTH_SHORT
        ).show();
    }
}