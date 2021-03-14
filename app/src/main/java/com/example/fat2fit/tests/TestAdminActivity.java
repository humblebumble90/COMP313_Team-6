package com.example.fat2fit.tests;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fat2fit.R;
import com.android.volley.Response;
import com.example.fat2fit.api.ApiResponse;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.User;
import com.example.fat2fit.models.UserToken;

public class TestAdminActivity extends AppCompatActivity {
    Fat2FitApi api;
    User originUser, updatedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_admin2);
        api = Fat2FitApi.getInstance(this);
    }

    //Story: As an administrator, I can modify a user's personal data as the user may request
    public void Test1()
    {
        //Test1: modify existing user
        //Condition:
        //Id:604e614f466f440015e9f3e7
        //firstName: John
        //lastName: Doe
        //Expected outcome:
        //“Successfully updated

        String id = "604e614f466f440015e9f3e7";
        originUser = new User();
        originUser.set_id(id);

        updatedUser = new User();
        updatedUser.set_id(originUser.get_id());
        updatedUser.setFirstName("UpdatedJohn");
        updatedUser.setLastName("Updated Doe");

        api.login("amin@email.com","Password#123$", res ->
                {
                    api.adminUpdateUser(originUser, updatedUser,
                            response->
                            {
                                originUser = response.getData();

                            },
                            error ->
                            {
                                //test failed
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                    );
                },
                err->
                {

                });
    }

    public void Test2()
    {
        //Test2: modify non existing user
        //Condition:
        //id:645sdfsdf46s4dfsds65
        //Expected outcome:
        //“Cannot find user”
        //Result:

        String id = "645sdfsdf46s4dfsds65";
        originUser = new User();
        originUser.set_id(id);

        updatedUser = new User();
        updatedUser.set_id(originUser.get_id());
        updatedUser.setFirstName("Updated Mia");
        updatedUser.setLastName("Updated Kim");

        api.login("amin@email.com","Password#123$", res ->
                {
                    api.adminUpdateUser(originUser, updatedUser,
                            response->
                            {
                                originUser = response.getData();

                            },
                            error ->
                            {
                                //test failed
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                    );
                },
                err->
                {

                });
    }

    public void Test3()
    {
        //Test3: Attempt to modify email
        //Condition:
        //Id:604e614f466f440015e9f3e7
        //email: test2@email.com
        //Expected outcome:
        //User email is not updated
        //Result:

        String id = "604e614f466f440015e9f3e7";
        originUser = new User();
        originUser.set_id(id);

        updatedUser = new User();
        updatedUser.set_id(originUser.get_id());
        updatedUser.setEmail("updated@email.com");

        api.login("amin@email.com","Password#123$", res ->
                {
                    api.adminUpdateUser(originUser, updatedUser,
                            response->
                            {
                                originUser = response.getData();

                            },
                            error ->
                            {
                                //test failed
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                    );
                },
                err->
                {

                });
    }

    public void Test4()
    {
        //Test4: Attempt to modify user without admin account
        //Condition:
        //Id:604e614f466f440015e9f3e7
        //firstName: John
        //lastName: Doe
        //Expected outcome:
        //Cannot reach path

        originUser = new User();
        originUser.set_id("604e614f466f440015e9f3e7");

        updatedUser.set_id(originUser.get_id());
        updatedUser.setFirstName("Updated Brandon");
        updatedUser.setLastName("updated Hoyle");

        api.adminUpdateUser(originUser, updatedUser, res ->
        {
            originUser = res.getData();
        }, err ->
        {
            Toast.makeText(getApplicationContext(), err.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}