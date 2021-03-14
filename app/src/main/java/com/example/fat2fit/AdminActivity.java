package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.User;

public class AdminActivity extends AppCompatActivity {

    Fat2FitApi api;
    EditText editTextUsername;
    String username;
    TextView editTextId;
    TextView editTextEmail;
    TextView editTextFirstname;
    TextView editTextLastname;
    TextView editTextRole;
    TextView editTextHeight;
    TextView editTextWaist;
    TextView editTextPushupScore;
    TextView editTextSitupScore;
    TextView editTextFreq;
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
                        editTextId.setText("ID: " + u.get_id());
                        editTextEmail.setText("Email: " + u.getEmail());
                        editTextFirstname.setText("Firstname: " + u.getFirstName() );
                        editTextLastname.setText("Lastname: " + u.getLastName());
                        editTextRole.setText("Role:" + u.getRole());
                        editTextHeight.setText("Height: " + u.getHeight());
                        editTextWaist.setText("Waist: " + u.getWaist());
                        editTextPushupScore.setText("Pushup Score: " + u.getPushupScore());
                        editTextSitupScore.setText("Situp Score: " + u.getSitupScore());
                        editTextFreq.setText("Freq: " + u.getFreq());
                    }
                },
                err -> {
                    Toast.makeText(getApplicationContext(), err.getMessage(), Toast.LENGTH_SHORT).show();
                    // Unsuccessful
                });

    }

    public void reset(View view) {
    }
}