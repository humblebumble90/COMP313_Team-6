package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.User;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    String email;
    String password;
    Fat2FitApi api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         api = Fat2FitApi.getInstance(this);

         emailEditText = findViewById(R.id.usernameText);
         passwordEditText = findViewById(R.id.passwordText);

         email = api.getSavedEmail();
         password = api.getSavedPassword();

         emailEditText.setText(email);
         passwordEditText.setText(password);
    }

        public void login(View view) {

        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();

        api.login(email, password, res -> {
            User user = res.getData().getUser();
            char role = user.getRole();

            if (role == User.Roles.ADMIN) {
                // logged in as admin.
                startActivity(new Intent(LoginActivity.this, AdminActivity.class));
            } else if (role == User.Roles.CUSTOMER_REP) {
                // logged in as Customer rep.
                startActivity(new Intent(LoginActivity.this, CustomerRepHomeActivity.class));
            } else if (role == User.Roles.END_USER) {
                // logged in as end user
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        }, err -> {
            // Handles unsuccessful login
            Toast.makeText(this, "Login is not properly proceeded for missed username, password, or other factors.", Toast.LENGTH_SHORT).show();
        });
    }

    public void signUp(View view)
    {
        System.out.println("signup button pushed");
        //startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }

    public void forgotPassword(View view)
    {
        System.out.println("forgotPassword button pushed");
        startActivity(new Intent(getApplicationContext(),ForgotPasswordActivity.class));
    }

    public void skip(View view)
    {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }


}