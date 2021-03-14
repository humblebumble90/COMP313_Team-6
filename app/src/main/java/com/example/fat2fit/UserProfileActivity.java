package com.example.fat2fit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.fat2fit.api.Fat2FitApi;
import com.example.fat2fit.models.User;

public class UserProfileActivity extends AppCompatActivity {
    private EditText heightEditText;
    private EditText waistEditText;
    private EditText situpEditText;
    private EditText pushupEditText;
    private EditText freqEditText;

    private int height;
    private int waist;
    private int situp;
    private int pushup;
    private int freq;
    private User user;

    Fat2FitApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        api = Fat2FitApi.getInstance(this);

    }
    //Todo: Functions need to be implemented for values in the UI and on click actions.
    public void submit(View view)
    {
        System.out.println("Submit Button was pressed.");
       heightEditText = findViewById(R.id.EditTextUsername);
       waistEditText = findViewById(R.id.editWaistText);
       situpEditText = findViewById(R.id.situpEditText);
       pushupEditText = findViewById(R.id.editPushupText);
       freqEditText = findViewById(R.id.frequencyEditText);

       height = Integer.parseInt(heightEditText.getText().toString());
       waist = Integer.parseInt(waistEditText.getText().toString());
       situp = Integer.parseInt(situpEditText.getText().toString());
       pushup = Integer.parseInt(freqEditText.getText().toString());
       freq = Integer.parseInt(freqEditText.getText().toString());

        user = new User();
        user.setHeight(height);
        user.setWaist(waist);
        user.setSitupScore(situp);
        user.setPushupScore(pushup);
        user.setFreq(freq);

        api.sendFitData(user, res -> {
            User u = res.getData();
            String text = "" +
                    "Pushup Score: " + u.getPushupScore() +
                    "\nSitup Score: " + u.getSitupScore() +
                    "\nWaist: " + u.getWaist() +
                    "\nHeight: " + u.getHeight() +
                    "\nFreq: " + u.getFreq();
        }, err -> {
            // Unsuccessful
        });

    }
}