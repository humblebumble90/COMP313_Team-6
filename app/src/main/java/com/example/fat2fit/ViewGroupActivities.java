package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.fat2fit.api.ApiResponse;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.Group;
import com.example.fat2fit.models.GroupActivity;

import java.util.ArrayList;
import java.util.Collections;

public class ViewGroupActivities extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group_activities);
        recyclerView = findViewById(R.id.viewGroupActivitiesRecycler);

        Fat2FitApi api = Fat2FitApi.getInstance(this);
        api.getMyGroups(this::handleGetGroups, this::handleError);
    }

    private void handleGetGroups(ApiResponse<Group[]> res) {
        Group[] groups = res.getData();
        ArrayList<GroupActivity> activities = new ArrayList<>();

        for (Group group : groups) {
            Collections.addAll(activities, group.getActivities());
        }

        GroupActivityAdaptor adaptor = new GroupActivityAdaptor(this, activities);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void handleError(VolleyError err) {
        Toast.makeText(
            getApplicationContext(),
            err.getMessage(),
            Toast.LENGTH_SHORT
        ).show();
    }
}