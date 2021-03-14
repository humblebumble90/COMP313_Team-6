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
                setErrorText("Failed test 2");
            }
        }, err -> {
            setSuccessText("Passed test 2\n" + err.getMessage());
        });
    }


    /**
     * No Credentials
     */
    private void test3() {
        String email = "";
        String password = "";

        api.login(email, password, res -> {
            UserToken ut = res.getData();
            User user = ut.getUser();
            if (user.getEmail().equals(email)) {
                setErrorText("Failed test 3");
            }
        }, err -> {
            setSuccessText("Passed test 3\n" + err.getMessage());
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