package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.fat2fit.api.ApiResponse;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.PasswordSecurity;

public class ForgotPasswordTwoActivity extends AppCompatActivity {

    private TextView securityQuestion1Text, securityQuestion2Text;
    private EditText answer1Text, answer2Text;
    private Button answerQuestionsButton;

    private Fat2FitApi api;
    private PasswordSecurity security;
    private String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_two);
        setTitle("Forgot Password");

        api = Fat2FitApi.getInstance(this);

        security = new PasswordSecurity();

        answerQuestionsButton = findViewById(R.id.answerQuestionsButton);

        securityQuestion1Text = findViewById(R.id.securityQuestion1Text);
        answer1Text = findViewById(R.id.securityAnswer1Text);

        securityQuestion2Text = findViewById(R.id.securityQuestion2Text);
        answer2Text = findViewById(R.id.securityAnswer2Text);

        unpackIntent();
    }

    private void unpackIntent() {
        Intent intent = getIntent();

        email = intent.getStringExtra("email");

        security.question1 = intent.getStringExtra("question1");
        securityQuestion1Text.setText(security.question1);

        security.question2 = intent.getStringExtra("question2");
        securityQuestion2Text.setText(security.question2);

        Log.d("QUESTIONS", security.question1 + " : " + security.question2);
    }

    public void answerQuestions(View view) {
        answerQuestionsButton.setEnabled(false);
        security.answer1 = answer1Text.getText().toString();
        security.answer2 = answer2Text.getText().toString();

        api.answerQuestions(email, security, this::handleResponse, this::handleError);
    }

    private void handleResponse(ApiResponse<String> res) {
        String token = res.getData();
        Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);

        intent.putExtra("token", token);
        intent.putExtra("email", email);

        startActivity(intent);
        answerQuestionsButton.setEnabled(true);
        finish();
    }

    private void handleError(VolleyError err) {
        Toast.makeText(
                getApplicationContext(),
                err.getMessage(),
                Toast.LENGTH_SHORT).show();
        answerQuestionsButton.setEnabled(true);
    }
}