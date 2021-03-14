package com.example.fat2fit.tests;

import androidx.appcompat.app.AppCompatActivity;
import com.example.fat2fit.R;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.User;
import com.example.fat2fit.models.UserToken;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestLoginActivity extends AppCompatActivity {

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
    }

    public void onTestButtonPressed(View v) {
    }

    /**
     * Correct Credentials
     */
    private void test1() {
        String email = "test1@email.com";
        String password = "Password#123$";
        api.login(email, password, res -> {
            UserToken ut = res.getData();
            User user = ut.getUser();
            if (user.getEmail().equals(email)) {
                setSuccessText("Passed test 1");
            }
        }, err -> {
            setErrorText("Failed test 1");
        });
    }

    /**
     * Incorrect Credentials
     */
    private void test2() {
        String email = "test1@email.com";
        String password = "Incorrect#123$";

        api.login(email, password, res -> {
            UserToken ut = res.getData();
            User user = ut.getUser();
            if (user.getEmail().equals(email)) {
                setErrorText("Failed test 1");
            }
        }, err -> {
            setSuccessText("Passed test 1");
        });
    }


    /**
     * No Credentials
     */
    private void test3() {
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