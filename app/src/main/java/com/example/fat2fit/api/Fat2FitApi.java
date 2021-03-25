package com.example.fat2fit.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.example.fat2fit.helpers.StringHelper;
import com.example.fat2fit.models.Challenge;
import com.example.fat2fit.models.Group;
import com.example.fat2fit.models.GroupActivity;
import com.example.fat2fit.models.Participant;
import com.example.fat2fit.models.PasswordSecurity;
import com.example.fat2fit.models.User;
import com.example.fat2fit.models.UserToken;
import com.example.fat2fit.models.Workout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Fat2FitApi {
    private static final String
            AUTH_HEADER = "Authorization",
            BEARER_PREFIX = "Bearer ",
            SAVED_EMAIL = "email",
            SAVED_PASS = "pass",
            API_URL = "https://fat2fit-api.herokuapp.com";
    private static final JSONObject EMPTY_JSON = new JSONObject();

    private static Fat2FitApi instance;
    private final Map<String, String> headers;

    private Fat2FitApi() {
        headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        loadAuthorization();
    }

    private void loadAuthorization() {
        SharedPreferences prefs = RequestHelper.getPrefs();
        String token = prefs.getString(AUTH_HEADER, "");
        if (!token.isEmpty()) {
            headers.put(AUTH_HEADER, BEARER_PREFIX + token);
        }
    }

    public static synchronized Fat2FitApi getInstance(Context context) {
        if (instance == null) {
            RequestHelper.init(context);
            instance = new Fat2FitApi();
        }
        return instance;
    }

    /**
     * @param token pass null to clear
     */
    public void setAuthorization(String token) {
        SharedPreferences.Editor edit = RequestHelper.getPrefs().edit();
        if (token != null) {
            headers.put(AUTH_HEADER, BEARER_PREFIX + token);
            edit.putString(AUTH_HEADER, token);
        } else {
            headers.remove(AUTH_HEADER);
            edit.remove(AUTH_HEADER);
        }
        edit.apply();
    }

    public String getSavedEmail() {
        return RequestHelper.getPrefs().getString(SAVED_EMAIL, "");
    }
    public String getSavedPassword() {
        return RequestHelper.getPrefs().getString(SAVED_PASS, "");
    }

    private void saveEmailAndPassword(String email, String password) {
        SharedPreferences.Editor edit = RequestHelper.getPrefs().edit();
        edit.putString(SAVED_EMAIL, email);
        edit.putString(SAVED_PASS, password);
        edit.apply();
    }

    public String getAuthorization() {
        try {
            return headers.get(AUTH_HEADER);
        } catch (Exception e) {
            return null;
        }
    }

    //--------------------------------------------------

    public ApiRequest<UserToken> login(
            String email,
            String password,
            ApiResponse.Listener<UserToken> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/account/login";
        JSONObject body = new JSONObject();

        try {
            body.put("email", email);
            body.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        ApiResponse.Listener<UserToken> wrapper = data -> {
            setAuthorization(data.getData().getToken());
            saveEmailAndPassword(email, password);
            resListener.onResponse(data);
        };
        return ApiRequest.post(
                UserToken.class, endpoint, headers,
                body, wrapper, errorListener);
    }

    public ApiRequest<User> signUp(
            User userData,
            PasswordSecurity questions,
            ApiResponse.Listener<User> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/account/signup";
        JSONObject body = new JSONObject();

        try {
            body.put("email", userData.getEmail());
            body.put("password", userData.getPassword());
            body.put("firstName", userData.getFirstName());
            body.put("lastName", userData.getLastName());
            body.put("question1", questions.question1);
            body.put("question2", questions.question2);
            body.put("answer1", questions.answer1);
            body.put("answer2", questions.answer2);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return ApiRequest.post(
                User.class, endpoint, headers,
                body, resListener, errorListener);
    }

    public ApiRequest<User> getUserInfo(
            ApiResponse.Listener<User> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/account/info";
        return ApiRequest.get(
                User.class, endpoint, headers,
                resListener, errorListener);
    }

    public ApiRequest<User> sendFitData(
            User data,
            ApiResponse.Listener<User> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/account/fitdata";
        JSONObject body = new JSONObject();

        try {
            body.put("waist", data.getWaist());
            body.put("height", data.getHeight());
            body.put("situpScore", data.getSitupScore());
            body.put("pushupScore", data.getPushupScore());
            body.put("freq", data.getFreq());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return ApiRequest.post(
                User.class, endpoint, headers,
                body, resListener, errorListener);
    }

    public ApiRequest<PasswordSecurity> getQuestions(
            String email,
            ApiResponse.Listener<PasswordSecurity> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/account/questions" + ("?email=" + email);
        return ApiRequest.get(
                PasswordSecurity.class, endpoint,
                headers, resListener, errorListener);
    }

    public ApiRequest<String> answerQuestions(
            String email, PasswordSecurity answers,
            ApiResponse.Listener<String> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/account/questions";
        JSONObject body = new JSONObject();
        try {
            body.put("email", email);
            body.put("answer1", answers.answer1);
            body.put("answer2", answers.answer2);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return ApiRequest.post(
                String.class, endpoint, headers,
                body, resListener, errorListener);
    }

    public ApiRequest<User> resetPassword(
            String resetToken, String newPassword,
            ApiResponse.Listener<User> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/account/passreset";
        JSONObject body = new JSONObject();

        try {
            body.put("password", newPassword);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        headers.put(AUTH_HEADER, BEARER_PREFIX + resetToken);
        ApiResponse.Listener<User> resWrapper = res -> {
            headers.remove(AUTH_HEADER);
            resListener.onResponse(res);
        };
        Response.ErrorListener errWrapper = err -> {
            headers.remove(AUTH_HEADER);
            errorListener.onErrorResponse(err);
        };
        return ApiRequest.post(
                User.class, endpoint, headers,
                body, resWrapper, errWrapper);
    }

    //--------------------------------------------------

    public ApiRequest<Group> createGroup(
            String name,
            ApiResponse.Listener<Group> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/group/create";
        JSONObject body = new JSONObject();

        try {
            body.put("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return ApiRequest.post(
                Group.class, endpoint, headers,
                body, resListener, errorListener);
    }

    public ApiRequest<Group> joinGroup(
            String groupId,
            ApiResponse.Listener<Group> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/group/join/" + groupId;

        return ApiRequest.put(
                Group.class, endpoint, headers,
                EMPTY_JSON, resListener, errorListener);
    }

    public ApiRequest<String> leaveGroup(
            String groupId,
            ApiResponse.Listener<String> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/group/leave/" + groupId;

        return ApiRequest.put(
                String.class, endpoint, headers,
                EMPTY_JSON, resListener, errorListener);
    }

    public ApiRequest<Group> getGroup(
            String groupId,
            ApiResponse.Listener<Group> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/group/" + groupId;

        return ApiRequest.get(
                Group.class, endpoint, headers,
                resListener, errorListener);
    }

    public ApiRequest<GroupActivity> createGroupActivity(
            String groupId,
            String title, String description,
            @Nullable String hyperlink,
            ApiResponse.Listener<GroupActivity> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/group/" + groupId + "/activity/create";
        JSONObject body = new JSONObject();
        try {
            body.put("title", title);
            body.put("description", description);
            if (!StringHelper.isBlank(hyperlink)) {
                body.put("hyperlink", hyperlink);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return ApiRequest.post(
                GroupActivity.class, endpoint, headers,
                body, resListener, errorListener);
    }

    //--------------------------------------------------

    public ApiRequest<Workout[]> getRecommendedWorkouts(
            ApiResponse.Listener<Workout[]> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/workout/recommended";
        return ApiRequest.get(
                Workout[].class, endpoint, headers,
                resListener, errorListener);
    }

    //--------------------------------------------------

    public ApiRequest<User> adminUpdateUser(
            User original, User changes,
            ApiResponse.Listener<User> resListener,
            Response.ErrorListener errorListener
    ) {
        final String _id = original.get_id();
        if (StringHelper.isBlank(_id)) {
            Log.e("AdminUpdateUser","Id is null or empty");
            return null;
        }
        final String endpoint = API_URL + "/admin/user/" + _id;
        JSONObject body;

        try {
            body = User.differences(original, changes);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return ApiRequest.post(
                User.class, endpoint, headers,
                body, resListener, errorListener);
    }

    public ApiRequest<User[]> adminSearchUser(
            String term,
            ApiResponse.Listener<User[]> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/admin/search";
        JSONObject body = new JSONObject();

        try {
            body.put("term", term);
            body.put("email", term);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return ApiRequest.post(
                User[].class, endpoint, headers,
                body, resListener, errorListener);
    }

    //--------------------------------------------------

    /**
     * Customer rep only
     */
    public ApiRequest<Challenge[]> getAllChallenges(
            ApiResponse.Listener<Challenge[]> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/cusrep/challenges";
        return ApiRequest.get(
                Challenge[].class, endpoint, headers,
                resListener, errorListener);
    }

    /**
     * Customer rep only
     */
    public ApiRequest<Challenge> updateChallenge(
            Challenge challenge,
            ApiResponse.Listener<Challenge> resListener,
            Response.ErrorListener errorListener
    ) {
        String id = challenge.get_id();
        if (StringHelper.isBlank(id)) return null;
        final String endpoint = API_URL + "/cusrep/challenges/" + id;
        JSONObject body = new JSONObject();

        try {
            body.put("title", challenge.getTitle());
            body.put("description", challenge.getDescription());
            body.put("distance", challenge.getDistance());
            // TODO: Add field to update reward
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return ApiRequest.post(
                Challenge.class, endpoint, headers,
                body, resListener, errorListener);
    }

    /**
     * Customer rep only
     */
    public ApiRequest<Challenge> createChallenge(
            String title, String description, double distance,
            ApiResponse.Listener<Challenge> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/challenge/create";
        JSONObject body = new JSONObject();

        try {
            body.put("title", title);
            body.put("description", description);
            body.put("distance", distance);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return ApiRequest.post(
                Challenge.class, endpoint, headers,
                body, resListener, errorListener);
    }

    //--------------------------------------------------

    /**
     * Note this is for the user
     */
    public ApiRequest<Challenge[]> getAvailableChallenges(
            ApiResponse.Listener<Challenge[]> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/challenge/available";
        return ApiRequest.get(
                Challenge[].class, endpoint, headers,
                resListener, errorListener);
    }

    public ApiRequest<Participant[]> getMyActiveChallenges(
            ApiResponse.Listener<Participant[]> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/challenge/active";
        return ApiRequest.get(
                Participant[].class, endpoint, headers,
                resListener, errorListener);
    }

    public ApiRequest<Participant> participateInChallenge(
            String challengeId,
            ApiResponse.Listener<Participant> resListener,
            Response.ErrorListener errorListener
    ) {
        if (StringHelper.isBlank(challengeId)) {
            Log.e("participateInChallenge","ChallengeId is blank");
            return null;
        }
        final String endpoint = API_URL + "/challenge/participate/" + challengeId;
        return ApiRequest.get(
                Participant.class, endpoint, headers,
                resListener, errorListener);
    }

    public ApiRequest<Participant> updateChallengeProgress(
            String challengeId,
            double newDistance,
            ApiResponse.Listener<Participant> resListener,
            Response.ErrorListener errorListener
    ) {
        if (StringHelper.isBlank(challengeId)) {
            Log.e("challengeProgress","ChallengeId is blank");
            return null;
        }
        final String endpoint = API_URL + "/challenge/progress/" + challengeId;
        JSONObject body = new JSONObject();
        try {
            body.put("distance", newDistance);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return ApiRequest.post(
                Participant.class, endpoint, headers,
                body, resListener, errorListener);
    }
}

