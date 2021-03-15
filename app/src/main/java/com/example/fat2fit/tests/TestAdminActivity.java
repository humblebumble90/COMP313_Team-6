package com.example.fat2fit.tests;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fat2fit.R;
import com.android.volley.Response;
import com.example.fat2fit.api.ApiResponse;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.api.RequestHelper;
import com.example.fat2fit.models.User;
import com.example.fat2fit.models.UserToken;

public class TestAdminActivity extends AppCompatActivity {
    Fat2FitApi api;
    User originUser, updatedUser;
    private TextView testTextView;
    private Button testButton;

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
            case 1: Test1(); return;
            case 2: Test2(); return;
            case 3: Test3(); return;
            case 4: Test4(); return;
            default: setErrorText("Incorrect Test Number");
                return;
        }
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
        originUser.setEmail("");
        originUser.setFirstName("");
        originUser.setLastName("");
        originUser.setPassword("");

        updatedUser = new User();
        updatedUser.set_id(originUser.get_id());
        updatedUser.setFirstName("UpdatedJohn");
        updatedUser.setLastName("UpdatedDoe");
        updatedUser.setEmail(originUser.getEmail());
        updatedUser.setPassword(originUser.getPassword());

        api.login("admin@email.com","Password#123$", res ->
                {
                    api.adminUpdateUser(originUser, updatedUser,
                            response->
                            {
                                originUser = response.getData();
                                if(originUser.getFirstName().equals(updatedUser.getFirstName())){
                                    setSuccessText("Passed test 1");
                                }

                            },
                            error ->
                            {
                                //test failed
                                setErrorText("Failed test 1");
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
        originUser.setFirstName("Mia");
        originUser.setFirstName("Kim");
        originUser.setEmail("");
        originUser.setPassword("");

        updatedUser = new User();
        updatedUser.set_id(originUser.get_id());
        updatedUser.setFirstName("Updated Mia");
        updatedUser.setLastName("Updated Kim");
        updatedUser.setPassword(originUser.getPassword());
        updatedUser.setEmail(originUser.getEmail());

        api.login("admin@email.com","Password#123$", res ->
                {
                    api.adminUpdateUser(originUser, updatedUser,
                            response->
                            {
                                originUser = response.getData();

                            },
                            error ->
                            {
                                //test failed
                                setSuccessText("Passed test 2");
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
        originUser.setEmail("");
        originUser.setPassword("");
        originUser.setFirstName("");
        originUser.setLastName("");

        updatedUser = new User();
        String newEmail = "updated@email.com";
        updatedUser.set_id(originUser.get_id());
        updatedUser.setEmail(newEmail);
        updatedUser.setLastName(originUser.getLastName());
        updatedUser.setFirstName(originUser.getFirstName());
        updatedUser.setPassword(originUser.getPassword());

        api.login("admin@email.com","Password#123$", res ->
                {
                    api.adminUpdateUser(originUser, updatedUser,
                            response->
                            {
                                originUser = response.getData();
                                if (response.getData().getEmail().equals(newEmail)) {
                                    setErrorText("Failed test 3");
                                } else {
                                    setSuccessText("Passed test 3");
                                }

                            },
                            error ->
                            {
                                //test failed
                                setErrorText("Failed test 3");
                            }
                    );
                },
                err->
                {
                    setErrorText("Failed test\n" + err.getMessage());
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
        originUser.setFirstName("Brandon");
        originUser.setLastName("Hoyle");
        originUser.setEmail("");
        originUser.setPassword("");

        updatedUser.set_id(originUser.get_id());
        updatedUser.setFirstName("Updated Brandon");
        updatedUser.setLastName("updated Hoyle");
        updatedUser.setPassword(originUser.getPassword());

        api.adminUpdateUser(originUser, updatedUser, res ->
        {
            originUser = res.getData();
            setSuccessText(String.format(
                    "SUCCESS\nfirstName: %s\nlastName: %s",
                    originUser.getFirstName(), originUser.getLastName()));
        }, err ->
        {
            setErrorText(err.getMessage());
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