package com.example.fat2fit.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Response;
import com.example.fat2fit.models.User;
import com.example.fat2fit.models.UserToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Fat2FitApi {
    private static final String
            AUTH_HEADER = "Authorization",
            API_URL = "https://fat2fit-api.herokuapp.com";

    private static Fat2FitApi instance;
    private final Map<String, String> headers;

    private Fat2FitApi() {
        headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
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
            headers.put(AUTH_HEADER, "Bearer " + token);
            edit.putString(AUTH_HEADER, token);
        } else {
            headers.remove(AUTH_HEADER);
            edit.remove(AUTH_HEADER);
        }
        edit.apply();
    }

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

        ApiRequest<UserToken> request = ApiRequest.post(
                UserToken.class, endpoint, headers,
                body, resListener, errorListener);

        RequestHelper.addToRequestQueue(request);

        return request;
    }

    public ApiRequest<User> signUp(
            User userData,
            ApiResponse.Listener<User> resListener,
            Response.ErrorListener errorListener
        ) {
        final String endpoint = API_URL + "/account/signup";

        JSONObject body = new JSONObject();

        try {
            // TODO: Some validation before sending data
            // TODO: Improve RequestHelper.toJson method
            body.put("email", userData.getEmail());
            body.put("password", userData.getPassword());
            body.put("firstName", userData.getFirstName());
            body.put("lastName", userData.getLastName());
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
}

