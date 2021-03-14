package com.example.fat2fit.tests;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fat2fit.R;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.User;

public class TestAdminActivity2_Searching extends AppCompatActivity {
    Fat2FitApi api;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_admin_activity2__searching);
        api = Fat2FitApi.getInstance(this);
    }

    public void Test1()
    {
        //Test1:
        //Condition: To search a specific user having an email address “example@email.com”
        //Expected outcome:
        //The user is searched

        String userName = "example@gmail.com";
        api.login("admin@email.com", "Password#123$", res ->
        {
            api.adminSearchUser(userName, response->
            {
                User[] users = response.getData();
                for(User u: users)
                {
                    if(u.getEmail() == userName)
                    {
                        user = u;
                    }
                }
            }, error ->
            {
                //test failed
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            });
        }, err ->
                {

                });
    }

    public void Test2()
    {
        //Test2:
        //Condition: To search a specific fuser having an email address “helloworld@email.com” that actually doesn’t exist.
        //Expected outcome: The user is not searched.

        String userName = "helloworld@gmail.com";
        api.login("admin@email.com", "Password#123$", res ->
        {
            api.adminSearchUser(userName, response->
            {
                User[] users = response.getData();
                for(User u: users)
                {
                    if(u.getEmail() == userName)
                    {
                        user = u;
                    }
                }
            }, error ->
            {
                //test failed
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            });
        }, err ->
        {

        });
    }
}