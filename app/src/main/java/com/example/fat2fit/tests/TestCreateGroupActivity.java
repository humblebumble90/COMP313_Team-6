package com.example.fat2fit.tests;
import androidx.appcompat.app.AppCompatActivity;
import com.example.fat2fit.R;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.Group;
import com.example.fat2fit.models.User;
import com.example.fat2fit.models.UserToken;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestCreateGroupActivity extends AppCompatActivity {

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
        TextView testNum = findViewById(R.id.testNumber);
        int num = Integer.parseInt(testNum.getText().toString());
        switch (num){
            case 1: test1(); return;
            case 2: test2(); return;
            case 3: test3(); return;
            default: setErrorText("Incorrect Test Number"); return;
        }
    }

    /**
     * Create a group with a valid name
     */
    private void test1() {
        String groupName = "My Group 01";
        api.createGroup(groupName, res -> {
            Group group = res.getData();
            String text = String.format("ID: %s\nName:%s\nCoach:%s",
                    group.get_id(),
                    group.getName(),
                    group.getCoach().getEmail());
            setSuccessText("PASSED\n" + text);
        }, err -> {
            setErrorText(err.getMessage());
        });
    }

    /**
     * try to create a group with no name
     */
    private void test2() {
        String groupName = "";
        api.createGroup(groupName, res -> {
            Group group = res.getData();
            String text = String.format("ID: %s\nName:%s\nCoach:%s",
                    group.get_id(),
                    group.getName(),
                    group.getCoach().getEmail());
            setErrorText(text);
        }, err -> {
            setSuccessText("PASSED\n" + err.getMessage());
        });
    }

    /**
     * Try to create a group with only whitespaces as the name
     */
    private void test3() {
        String groupName = "         ";
        api.createGroup(groupName, res -> {
            Group group = res.getData();
            String text = String.format("ID: %s\nName:%s\nCoach:%s",
                    group.get_id(),
                    group.getName(),
                    group.getCoach().getEmail());
            setErrorText(text);
        }, err -> {
            setSuccessText("PASSED\n" + err.getMessage());
        });
    }

    private void init() {
        testButton.setEnabled(false);
        String email = "test1@email.com";
        String password = "Password#123$";
        api.login(email, password, res -> {
            Log.d("TEST_CREATE_GROUP", "Logged in successfully");
            testButton.setEnabled(true);
        }, err -> {
            testButton.setText(err.getMessage());
            Log.e("TEST_FITNESS_DATA", err.getMessage());
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