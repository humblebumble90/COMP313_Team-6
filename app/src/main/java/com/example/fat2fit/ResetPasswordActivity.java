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
import com.example.fat2fit.helpers.StringHelper;
import com.example.fat2fit.models.User;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText passwordText;
    private Button resetPasswordButton;

    private Fat2FitApi api;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        passwordText = findViewById(R.id.passwordText);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);
        api = Fat2FitApi.getInstance(this);

        unpackIntent();
    }

    private void unpackIntent() {
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
    }

    public void resetPassword(View v) {
        resetPasswordButton.setEnabled(false);
        String newPassword = passwordText.getText().toString();

        if (StringHelper.isBlank(newPassword)) {
            resetPasswordButton.setEnabled(true);
            Toast.makeText(
                    getApplicationContext(),
                    "Please enter a password",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        api.resetPassword(token, newPassword, this::handleResponse, this::handleError);
    }

    private void handleResponse(ApiResponse<User> res) {
        finish();
        resetPasswordButton.setEnabled(true);
    }

    private void handleError(VolleyError err) {
        Toast.makeText(
                getApplicationContext(),
                err.getMessage(),
                Toast.LENGTH_SHORT).show();
        resetPasswordButton.setEnabled(true);
    }
}