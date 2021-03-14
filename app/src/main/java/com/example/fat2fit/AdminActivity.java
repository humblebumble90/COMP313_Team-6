package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.fat2fit.api.ApiResponse;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.User;

public class AdminActivity extends AppCompatActivity {

    Fat2FitApi api;
    EditText editTextUsername;
    String username;
    TextView editTextId;
    TextView editTextEmail;
    EditText editTextFirstname;
    EditText editTextLastname;
    EditText editTextRole;
    EditText editTextHeight;
    EditText editTextWaist;
    EditText editTextPushupScore;
    EditText editTextSitupScore;
    EditText editTextFreq;

    User targetUser;
    String id;
    String email;
    String fname;
    String lname;
    char role;
    float height;
    float waist;
    int pScore;
    int sScore;
    int freq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        api = Fat2FitApi.getInstance(this);
        //TextViews
        editTextId = findViewById(R.id.userIDTextView);
        editTextEmail = findViewById(R.id.emailTextView);
        editTextFirstname = findViewById(R.id.firstnameTextView);
        editTextLastname = findViewById(R.id.lastnameTextView);
        editTextRole = findViewById(R.id.roleTextView);
        editTextHeight = findViewById(R.id.heightTextView);
        editTextWaist = findViewById(R.id.waistTextView);
        editTextPushupScore = findViewById(R.id.pushupScoreTextView);
        editTextSitupScore = findViewById(R.id.situpScoreTextView);
        editTextFreq = findViewById(R.id.freqTextView);



    }

    public void search(View view) {
        editTextUsername = findViewById(R.id.EditTextUsername);
        username = editTextUsername.getText().toString();

        api.adminSearchUser(username, res -> {
                    User[] users = res.getData(); // found users that match
                    for(User u : users)
                    {
                        targetUser = u;
                        id = u.get_id();
                        email = u.getEmail();
                        fname = u.getFirstName();
                        lname = u.getLastName();
                        role = u.getRole();
                        height = u.getHeight();
                        waist = u.getWaist();
                        pScore = u.getPushupScore();
                        sScore = u.getSitupScore();
                        freq = u.getFreq();
                        editTextId.setText("" + id);
                        editTextEmail.setText("" + email);
                        editTextFirstname.setText("" + fname );
                        editTextLastname.setText("" + lname);
                        editTextRole.setText("" + role);
                        editTextHeight.setText("" + height);
                        editTextWaist.setText("" + waist);
                        editTextPushupScore.setText("" + pScore);
                        editTextSitupScore.setText("" + sScore);
                        editTextFreq.setText("" + freq);
                    }
                },
                err -> {
                    Toast.makeText(getApplicationContext(), err.getMessage(), Toast.LENGTH_SHORT).show();
                    // Unsuccessful
                });

    }

    public void update(View view)
    {
//        editTextId = findViewById(R.id.userIDTextView);
//        editTextEmail = findViewById(R.id.emailTextView);
//        editTextFirstname = findViewById(R.id.firstnameTextView);
//        editTextLastname = findViewById(R.id.lastnameTextView);
//        editTextRole = findViewById(R.id.roleTextView);
//        editTextHeight = findViewById(R.id.heightTextView);
//        editTextWaist = findViewById(R.id.waistTextView);
//        editTextPushupScore = findViewById(R.id.pushupScoreTextView);
//        editTextSitupScore = findViewById(R.id.situpScoreTextView);
//        editTextFreq = findViewById(R.id.freqTextView);
//
        User user = targetUser;
        fname = editTextFirstname.getText().toString();
        user.setFirstName(fname);
        lname = editTextLastname.getText().toString();
        user.setLastName(lname);
        role = editTextRole.getText().charAt(0);
        user.setRole(role);
        height = Float.parseFloat(editTextHeight.getText().toString());
        user.setHeight(height);
        waist = Float.parseFloat(editTextWaist.getText().toString());
        user.setWaist(waist);
        freq = Integer.parseInt(editTextFreq.getText().toString());
        user.setFreq(freq);

        if(targetUser != null)
        {
            api.adminUpdateUser(targetUser, user,
                    res->
                    {
                        targetUser = res.getData();
                    },
                    err ->
                    {
                        //error
                        Toast.makeText(getApplicationContext(), err.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    );
        }
        else
        {
            Toast.makeText(this, "No user found yet for updating", Toast.LENGTH_SHORT).show();
        }
    }


    public void reset(View view) {
        editTextUsername.setText("");
        editTextFreq.setText("");
        editTextSitupScore.setText("");
        editTextFirstname.setText("");
        editTextLastname.setText("");
        editTextRole.setText("");
        editTextId.setText("");
        editTextEmail.setText("");
        editTextHeight.setText("");
        editTextWaist.setText("");
        editTextPushupScore.setText("");
        editTextSitupScore.setText("");
        targetUser =null;
    }
}