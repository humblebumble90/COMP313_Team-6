package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.fat2fit.api.ApiResponse;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.PasswordSecurity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText usernameText;
    private Button resetButton;
    private Fat2FitApi api;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_one);
        setTitle("Forgot Password");
        usernameText = findViewById(R.id.usernameText);
        resetButton = findViewById(R.id.resetButton);
        api = Fat2FitApi.getInstance(this);
    }

    public void resetNext(View view){
        resetButton.setEnabled(false);
        email = usernameText.getText().toString();

        if (email.isEmpty()) {
            resetButton.setEnabled(true);
            Toast.makeText(
                    getApplicationContext(),
                    "Email is not entered",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        api.getQuestions(email, this::handleResponse, this::handleError);
    }

    private void handleResponse(ApiResponse<PasswordSecurity> res) {
        PasswordSecurity sec = res.getData();

        Intent intent = new Intent(
                getApplicationContext(),
                ForgotPasswordTwoActivity.class);

        intent.putExtra("email", email);
        intent.putExtra("question1", sec.question1);
        intent.putExtra("question2", sec.question2);
        startActivity(intent);
        resetButton.setEnabled(true);
        finish();
    }

    private void handleError(VolleyError err) {
        Toast.makeText(
                getApplicationContext(),
                err.getMessage(),
                Toast.LENGTH_SHORT).show();
        resetButton.setEnabled(true);
    }
}