package com.example.fat2fit.api;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.example.fat2fit.R;
import com.example.fat2fit.models.Group;
import com.example.fat2fit.models.GroupActivity;
import com.example.fat2fit.models.User;
import com.example.fat2fit.models.UserToken;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ApiDebugActivity extends AppCompatActivity {

    private TextView textView;
    private Fat2FitApi api;
    private Response.ErrorListener stdErrListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_debug);

        stdErrListener = err -> setErrorText(err.getMessage());

        textView = findViewById(R.id.apiDebugTextView);
        Button button = findViewById(R.id.apiDebugButton);

        api = Fat2FitApi.getInstance(this);

        button.setText("Send");
    }

    public void onDebugButtonPress(View v) {
        testGetRecommendedWorkouts();
    }

    private void testGetUserInfo() {
        api.getUserInfo(res -> {
            User user = res.getData();
            if (user.getRole() == User.Roles.END_USER) {
                Toast.makeText(this, "End user!", Toast.LENGTH_LONG).show();
            }
            setSuccessText(RequestHelper.toJson(user));
        }, stdErrListener);
    }

    private void testCreateActivity() {
        final String groupId = "W4FBRH";
        final String title = "Title!";
        final String description = "Description!";
        final String hyperlink = null;
        api.createGroupActivity(groupId, title, description, hyperlink, res -> {
            GroupActivity act = res.getData();
            String text = "ID: " + act.get_id() +
                    "\nTitle: " + act.getTitle() +
                    "\nDescription: " + act.getDescription() +
                    "\nHyperlink: " + act.getHyperlink() +
                    "\nCreated: " + act.getCreated();
            setSuccessText(text);
        }, stdErrListener);
    }

    private void getGroup() {
        String groupId = "SR7HZU";
        api.getGroup(groupId, res -> {
            Group group = res.getData();
            StringBuilder sb = new StringBuilder();
            //sb.append();

        }, stdErrListener);
    }

    private void testGetRecommendedWorkouts() {
        api.getRecommendedWorkouts(res -> {
            GroupActivity[] workouts = res.getData();
            StringBuilder sb = new StringBuilder();
            for (GroupActivity w : workouts) {
                sb.append(w.getTitle()).append(" (");
                sb.append(w.getHyperlink()).append(")\n");
            }
            setSuccessText(sb.toString());
        }, stdErrListener);
    }

    private void testJoinGroup() {
        final String groupId = "RLY51W"; // make sure it's not your group
        api.joinGroup(groupId, res -> {
            Group group = res.getData();
            StringBuilder sb = new StringBuilder();
            User[] members = group.getMembers();
            sb.append(String.format("%s (Count: %s)\n", res.getMeta().getMsg(), members.length));
            for (User m : members) {
                String text = String.format("\n[%s] %s %s (%s)\n",
                        m.get_id(), m.getFirstName(), m.getLastName(), m.getEmail());
                sb.append(text);
            }
            setSuccessText(sb.toString());
        }, stdErrListener);
    }

    private void testLeaveGroup() {
        final String groupId = "RLY51W";
        api.leaveGroup(groupId, res -> {
            setSuccessText(res.getMeta().getMsg() + "\n\t" + res.getData());
        }, stdErrListener);
    }

    private void testCreateGroup() {
        String groupName = "My Group 01";
        api.createGroup(groupName, res -> {
            Group group = res.getData();
            String text = String.format("ID: %s\nName:%s\nCoach:%s",
                    group.get_id(),
                    group.getName(),
                    group.getCoach().getEmail());
            setSuccessText(text);
        }, stdErrListener);
    }

    private void testSendFitData() {
        User data = new User();

        data.setHeight(20);
        data.setWaist(21);
        data.setPushupScore(22);
        data.setSitupScore(22);
        data.setFreq(23);

        api.sendFitData(data, res -> {
            User u = res.getData();
            String text = "" +
                    "Pushup Score: " + u.getPushupScore() +
                    "\nSitup Score: " + u.getSitupScore() +
                    "\nWaist: " + u.getWaist() +
                    "\nHeight: " + u.getHeight() +
                    "\nFreq: " + u.getFreq();
            setSuccessText(text);
        }, stdErrListener);
    }

    private void testLogin() {
        final String email = "email@example.com", password = "Password#123$";
        api.login(email, password, res -> {
            UserToken ut = res.getData();
            String text = ut.getUser().getEmail() + "\n" + ut.getToken();
            setSuccessText(text);
        }, stdErrListener);
    }

    private void setSuccessText(String text) {
        textView.setTextColor(Color.rgb(0, 128, 0));
        textView.setText(text);
    }

    private void setErrorText(String text) {
        textView.setTextColor(Color.RED);
        textView.setText(text);
    }
}