package com.example.fat2fit.tests;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fat2fit.R;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.Group;
import com.example.fat2fit.models.User;
import com.example.fat2fit.models.UserToken;

public class TestJoin_LeaveGroupActivity extends AppCompatActivity {

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
            case 4: test4(); return;
            default: setErrorText("Incorrect Test Number"); return;
        }
    }

    /**
     * Correct Credentials
     */
    private void test1() {
        final String groupId = "SR7HZU";
        testButton.setEnabled(false);
        String email = "test1@email.com";
        String password = "Password#123$";
        api.login(email, password, res -> {
            Log.d("TEST1_JOIN_GROUP", "Logged in successfully");
            UserToken ut = res.getData();
            String token = ut.toString();
            api.setAuthorization(token);
            testButton.setEnabled(true);
            api.joinGroup(groupId, res1 -> {
                Group group = res1.getData();
                if(group.get_id().equals(groupId)){
                    setSuccessText("Passed test 1");
                }
            },err ->{
                setErrorText("Failed test 1");
            });
        }, err -> {
            testButton.setText(err.getMessage());
            Log.e("TEST1_JOIN_GROUP", err.getMessage());
        });
    }

    /**
     * Incorrect Credentials
     */
    private void test2() {
        final String groupId = "SR7HZU";
        testButton.setEnabled(false);
        String email = "test1@email.com";
        String password = "Password#123$";
        api.login(email, password, res -> {
            Log.d("TEST2_JOIN_GROUP", "Logged in successfully");
            UserToken ut = res.getData();
            String token = ut.toString();
            api.setAuthorization(token);
            testButton.setEnabled(true);
            api.joinGroup(groupId, res1 -> {
                Group group = res1.getData();
                if(group.get_id().equals(groupId)){
                    setErrorText("Failed test 2");
                }
            },err ->{
                setSuccessText("Passed test 2");
            });
        }, err -> {
            testButton.setText(err.getMessage());
            Log.e("TEST2_JOIN_GROUP", err.getMessage());
        });
    }


    /**
     * No Credentials
     */
    private void test3() {
        final String groupId = "SR7HZU";
        testButton.setEnabled(false);
        String email = "test1@email.com";
        String password = "Password#123$";
        api.login(email, password, res -> {
            Log.d("TEST3_LEAVE_GROUP", "Logged in successfully");
            UserToken ut = res.getData();
            String token = ut.toString();
            api.setAuthorization(token);
            testButton.setEnabled(true);
            api.leaveGroup(groupId, res1 -> {
                if(res1.getMeta().getMsg().equals("Successfully left")){
                    setSuccessText("Passed test 3");
                }
            },err ->{
                setErrorText("Failed test 3");
            });
        }, err -> {
            testButton.setText(err.getMessage());
            Log.e("TEST3_LEAVE_GROUP", err.getMessage());
        });
    }

    private void test4() {
        final String groupId = "WZAQT8";
        testButton.setEnabled(false);
        String email = "test1@email.com";
        String password = "Password#123$";
        api.login(email, password, res -> {
            Log.d("TEST4_LEAVE_GROUP", "Logged in successfully");
            UserToken ut = res.getData();
            String token = ut.toString();
            api.setAuthorization(token);
            testButton.setEnabled(true);
            api.leaveGroup(groupId, res1 -> {
                if(res1.getMeta().getMsg().equals("Successfully left")){
                    setErrorText("Failed test 4");
                }
            },err ->{
                setSuccessText("Passed test 4");
            });
        }, err -> {
            testButton.setText(err.getMessage());
            Log.e("TEST4_LEAVE_GROUP", err.getMessage());
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