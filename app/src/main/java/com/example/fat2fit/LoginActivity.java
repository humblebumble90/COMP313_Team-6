package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {
    //User type
    private enum userType {NORMAL, ADMIN, BUSINESS};
    private userType type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    //this is just temporary, should be replaced with proper login fields and stuff
    public void login(View view)
    {
        System.out.println("login button pushed");
        //TODO: Implement assigning type enum value when user type is defined by ID and Password
        switch (type)
        {
            case ADMIN:
                startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                break;
            case NORMAL:
                break;
            case BUSINESS:
                break;
             default:
                break;
        }
    }

    public void signUp(View view)
    {
        System.out.println("signup button pushed");
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