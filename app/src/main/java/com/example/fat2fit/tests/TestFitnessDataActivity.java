package com.example.fat2fit.tests;

import androidx.appcompat.app.AppCompatActivity;
import com.example.fat2fit.R;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.User;
import com.example.fat2fit.models.UserToken;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestFitnessDataActivity extends AppCompatActivity {

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
     * Proper fitness data
     */
    private void test1() {
        User user = new User();

        user.setHeight(20);
        user.setWaist(25);
        user.setFreq(30);
        user.setSitupScore(35);
        user.setPushupScore(40);

        api.sendFitData(user, res -> {
            User updated = res.getData();
            String text = "" +
                    "Pushup Score: " + updated.getPushupScore() +
                    "\nSitup Score: " + updated.getSitupScore() +
                    "\nWaist: " + updated.getWaist() +
                    "\nHeight: " + updated.getHeight() +
                    "\nFreq: " + updated.getFreq();
            if (
                    updated.getHeight() == user.getHeight() &&
                    updated.getWaist() == user.getWaist() &&
                    updated.getFreq() == user.getFreq() &&
                    updated.getSitupScore() == user.getSitupScore() &&
                    updated.getPushupScore() == user.getPushupScore()
            ) {
                setSuccessText(text);
            } else {
                setErrorText("Did not update\n" + text);
            }
        }, err -> {
            setErrorText(err.getMessage());
        });
    }

    /**
     * Improper fitness data
     */
    private void test2() {
        User user = new User();

        user.setHeight(-1);
        user.setWaist(-1);
        user.setFreq(-1);
        user.setSitupScore(-1);
        user.setPushupScore(-1);

        api.sendFitData(user, res -> {
            User updated = res.getData();
            String text = "" +
                    "Pushup Score: " + updated.getPushupScore() +
                    "\nSitup Score: " + updated.getSitupScore() +
                    "\nWaist: " + updated.getWaist() +
                    "\nHeight: " + updated.getHeight() +
                    "\nFreq: " + updated.getFreq();
            if (
                    updated.getHeight() == user.getHeight() &&
                            updated.getWaist() == user.getWaist() &&
                            updated.getFreq() == user.getFreq() &&
                            updated.getSitupScore() == user.getSitupScore() &&
                            updated.getPushupScore() == user.getPushupScore()
            ) {
                setErrorText(text);
            } else {
                setSuccessText("Did not update\n" + text);
            }
        }, err -> {
            setSuccessText(err.getMessage());
        });
    }


    private void init() {
        testButton.setEnabled(false);
        String email = "test1@email.com";
        String password = "Password#123$";
        api.login(email, password, res -> {
            Log.d("TEST_FITNESS_DATA", "Logged in successfully");
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