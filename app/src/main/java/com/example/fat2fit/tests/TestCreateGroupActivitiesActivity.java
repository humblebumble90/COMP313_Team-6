package com.example.fat2fit.tests;

import androidx.appcompat.app.AppCompatActivity;
import com.example.fat2fit.R;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.GroupActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestCreateGroupActivitiesActivity extends AppCompatActivity {

    private TextView testTextView;
    private Button testButton;
    private Fat2FitApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        api = Fat2FitApi.getInstance(this);

        testTextView = findViewById(R.id.testTextView);
        testButton = findViewById(R.id.testButton);
        init();
    }

    public void onTestButtonPressed(View v) {
    }

    /**
     * Create an activity with proper data
     */
    private void test1() {
        final String groupId = "I8N1VN";
        final String title = "Title (test1)!";
        final String description = "Description (test1)!";
        final String hyperlink = "https://youtu.be/gC_L9qAHVJ8";
        api.createGroupActivity(groupId, title, description, hyperlink, res -> {
            GroupActivity act = res.getData();
            String text = "ID: " + act.get_id() +
                    "\nTitle: " + act.getTitle() +
                    "\nDescription: " + act.getDescription() +
                    "\nHyperlink: " + act.getHyperlink() +
                    "\nCreated: " + act.getCreated();
            setSuccessText("PASSED\n" + text);
        }, err -> {
            setErrorText("FAILED\n" + err.getMessage());
        });
    }

    /**
     * Create an activity with no hyperlink
     */
    private void test2() {
        final String groupId = "I8N1VN";
        final String title = "title (test2)!";
        final String description = "Description (test2)!";
        final String hyperlink = "https://youtu.be/gC_L9qAHVJ8";
        api.createGroupActivity(groupId, title, description, hyperlink, res -> {
            GroupActivity act = res.getData();
            String text = "ID: " + act.get_id() +
                    "\nTitle: " + act.getTitle() +
                    "\nDescription: " + act.getDescription() +
                    "\nHyperlink: " + act.getHyperlink() +
                    "\nCreated: " + act.getCreated();
            setSuccessText("SUCCESS\n" + text);
        }, err -> {
            setErrorText("FAILED\n" + err.getMessage());
        });
    }


    /**
     * Create an activity with no title
     */
    private void test3() {
        final String groupId = "I8N1VN";
        final String title = "";
        final String description = "Description (test3)!";
        final String hyperlink = null;
        api.createGroupActivity(groupId, title, description, hyperlink, res -> {
            GroupActivity act = res.getData();
            String text = "ID: " + act.get_id() +
                    "\nTitle: " + act.getTitle() +
                    "\nDescription: " + act.getDescription() +
                    "\nHyperlink: " + act.getHyperlink() +
                    "\nCreated: " + act.getCreated();
            setErrorText("FAILED\n" + text);
        }, err -> {
            setSuccessText("SUCCESS\n" + err.getMessage());
        });
    }

    /**
     * Create an activity when user is not the coach
     */
    private void test4() {
        final String groupId = "82D9QK";
        final String title = "title (test4)!";
        final String description = "Description (test4)!";
        final String hyperlink = "https://youtu.be/gC_L9qAHVJ8";
        api.createGroupActivity(groupId, title, description, hyperlink, res -> {
            GroupActivity act = res.getData();
            String text = "ID: " + act.get_id() +
                    "\nTitle: " + act.getTitle() +
                    "\nDescription: " + act.getDescription() +
                    "\nHyperlink: " + act.getHyperlink() +
                    "\nCreated: " + act.getCreated();
            setErrorText("FAILED\n" + text);
        }, err -> {
            setSuccessText("SUCCESS\n" + err.getMessage());
        });
    }

    private void init() {
        testButton.setEnabled(false);
        String email = "test3@email.com";
        String password = "Password#123$";
        api.login(email, password, res -> {
            Log.d("TEST_CREATE_ACTIVITY", "Logged in successfully");
            testButton.setEnabled(true);
        }, err -> {
            testButton.setText(err.getMessage());
            Log.e("TEST_CREATE_ACTIVITY", err.getMessage());
        });
    }

    private void setSuccessText(String text) {
        testTextView.setTextColor(Color.rgb(0, 128, 0));
        testTextView.setText(text);
    }

    private void setErrorText(String text) {
        testTextView.setTextColor(Color.RED);
        testTextView.setText(text);
    }
}