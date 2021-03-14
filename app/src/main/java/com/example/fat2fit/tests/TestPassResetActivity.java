package com.example.fat2fit.tests;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fat2fit.R;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.PasswordSecurity;
import com.example.fat2fit.models.User;
import com.example.fat2fit.models.UserToken;

public class TestPassResetActivity extends AppCompatActivity {

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
     * Send incorrect answers
     */
    private void test1() {
        String email = "test1@email.com";
        PasswordSecurity answers = new PasswordSecurity();
        answers.answer1= "Correct1";
        answers.answer2 = "Correct2";
        String newPassword = "Password123$";
        api.answerQuestions(email,answers, res -> {
            String ut = res.getData();
            if(ut.equals(null)){
                api.resetPassword(ut,newPassword,res1 ->{
                    api.login(email, newPassword, res2 -> {
                        UserToken token = res2.getData();
                        User user = token.getUser();
                        if (user.getEmail().equals(email)) {
                            setErrorText("Failed test 1");
                        }
                    }, err -> {
                        setErrorText("Failed test 1");
                    });
                },err ->{
                    setErrorText("Failed test 1");
                });
            }
        },err ->{
            setSuccessText("Passed test 1");
        });
    }

    /**
     * Send Correct answers
     */
    private void test2() {
        String email = "test1@email.com";
        PasswordSecurity answers = new PasswordSecurity();
        answers.answer1= "Correct1";
        answers.answer2 = "Correct2";
        String newPassword = "Password123$";
        api.answerQuestions(email,answers, res -> {
            String ut = res.getData();
            api.resetPassword(ut,newPassword,res1 ->{
                api.login(email, newPassword, res2 -> {
                    UserToken token = res2.getData();
                    User user = token.getUser();
                    if (user.getEmail().equals(email)) {
                        setSuccessText("Passed test 2");
                    }
                }, err -> {
                    setErrorText("Failed test 2");
                    });
            },err ->{
                setErrorText("Failed test 2");
                });
        },err ->{
            setErrorText("Failed test 2");
        });
    }


    /**
     * No Credentials
     */
    private void test3() {
        String email = "test1@email.com";
        PasswordSecurity answers = new PasswordSecurity();
        answers.answer1= "Correct1";
        answers.answer2 = "Correct2";
        String newPassword = "p";
        api.answerQuestions(email,answers, res -> {
            String ut = res.getData();
            api.resetPassword(ut,newPassword,res1 ->{
                api.login(email, newPassword, res2 -> {
                    UserToken token = res2.getData();
                    User user = token.getUser();
                    if (user.getEmail().equals(email)) {
                        setErrorText("Failed test 3");
                    }
                }, err -> {
                    setErrorText("Failed test 3");
                });
            },err ->{
                setSuccessText("Passed test 3");
            });
        },err ->{
            setErrorText("Failed test 3");
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
