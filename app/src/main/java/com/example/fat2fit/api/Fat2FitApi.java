package com.example.fat2fit.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.example.fat2fit.models.Group;
import com.example.fat2fit.models.GroupActivity;
import com.example.fat2fit.models.PasswordSecurity;
import com.example.fat2fit.models.User;
import com.example.fat2fit.models.UserToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Fat2FitApi {
    private static final String
            AUTH_HEADER = "Authorization",
            BEARER_PREFIX = "Bearer ",
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

         // Up to you if you want to auto load the token
        ApiResponse.Listener<UserToken> wrapper = data -> {
            setAuthorization(data.getData().getToken());
            resListener.onResponse(data);
        };

        ApiRequest<UserToken> request = ApiRequest.post(
                UserToken.class, endpoint, headers,
                body, wrapper, errorListener);

        RequestHelper.addToRequestQueue(request);
        return request;
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

        ApiRequest<User> request = ApiRequest.post(
                User.class, endpoint, headers,
                body, resListener, errorListener);

        RequestHelper.addToRequestQueue(request);
        return request;
    }

    public ApiRequest<User> getUserInfo(
            ApiResponse.Listener<User> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/account/info";
        ApiRequest<User> request = ApiRequest.get(
                User.class, endpoint, headers,
                resListener, errorListener);

        RequestHelper.addToRequestQueue(request);
        return request;
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

        ApiRequest<User> request = ApiRequest.post(
                User.class, endpoint, headers,
                body, resListener, errorListener);

        RequestHelper.addToRequestQueue(request);
        return request;
    }

    public ApiRequest<PasswordSecurity> getQuestions(
            String email,
            ApiResponse.Listener<PasswordSecurity> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/account/questions" + ("?email=" + email);
        ApiRequest<PasswordSecurity> request = ApiRequest.get(
                PasswordSecurity.class, endpoint,
                headers, resListener, errorListener);

        RequestHelper.addToRequestQueue(request);
        return request;
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

        ApiRequest<String> request = ApiRequest.post(
                String.class, endpoint, headers,
                body, resListener, errorListener);

        RequestHelper.addToRequestQueue(request);
        return request;
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
        ApiRequest<User> request = ApiRequest.post(
                User.class, endpoint, headers,
                body, resWrapper, errWrapper);

        RequestHelper.addToRequestQueue(request);
        return request;
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

        ApiRequest<Group> request = ApiRequest.post(
                Group.class, endpoint, headers,
                body, resListener, errorListener);

        RequestHelper.addToRequestQueue(request);
        return request;
    }

    public ApiRequest<Group> joinGroup(
            String groupId,
            ApiResponse.Listener<Group> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/group/join/" + groupId;

        ApiRequest<Group> request = ApiRequest.put(
                Group.class, endpoint, headers,
                EMPTY_JSON, resListener, errorListener);

        RequestHelper.addToRequestQueue(request);
        return request;
    }

    public ApiRequest<String> leaveGroup(
            String groupId,
            ApiResponse.Listener<String> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/group/leave/" + groupId;

        ApiRequest<String> request = ApiRequest.put(
                String.class, endpoint, headers,
                EMPTY_JSON, resListener, errorListener);

        RequestHelper.addToRequestQueue(request);
        return request;
    }

    public ApiRequest<Group> getGroup(
            String groupId,
            ApiResponse.Listener<Group> resListener,
            Response.ErrorListener errorListener
    ) {
        final String endpoint = API_URL + "/group/" + groupId;

        ApiRequest<Group> request = ApiRequest.get(
                Group.class, endpoint, headers,
                resListener, errorListener);

        RequestHelper.addToRequestQueue(request);
        return request;
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
            if (hyperlink != null && !hyperlink.isEmpty()) {
                body.put("hyperlink", hyperlink);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        ApiRequest<GroupActivity> request = ApiRequest.post(
                GroupActivity.class, endpoint, headers,
                body, resListener, errorListener);

        RequestHelper.addToRequestQueue(request);
        return request;
    }

    //--------------------------------------------------

    public ApiRequest<User> adminUpdateUser(
            User data,
            ApiResponse.Listener<User> resListener,
            Response.ErrorListener errorListener
    ) {
        final String _id = data.get_id();
        if (_id == null || _id.isEmpty()) {
            Log.e("AdminUpdateUser","Id is null or empty");
            return null;
        }
        final String endpoint = API_URL + "/admin/user/" + _id;
        JSONObject body = new JSONObject();

        // TODO: Finish Admin update user
        Log.w("AdminUpdateUser", "Currently not finished");

        ApiRequest<User> request = ApiRequest.post(
                User.class, endpoint, headers,
                body, resListener, errorListener);
        RequestHelper.addToRequestQueue(request);
        return request;
    }
}

