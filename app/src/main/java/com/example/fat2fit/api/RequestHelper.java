package com.example.fat2fit.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequestHelper {
    private static final String SHARED_PREFS = "fat2fit_api_prefs";

    @SuppressLint("StaticFieldLeak")
    private static RequestHelper instance;

    private final Gson gson;
    private final Context context;
    private final RequestQueue requestQueue;

    private RequestHelper(final Context ctx) {
        instance = this;
        context = ctx;
        gson = new Gson();
        requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        requestQueue.start();
    }

    static void init(final Context ctx) {
        instance = new RequestHelper(ctx);
    }

    public static Gson getGson() {
        return instance.gson;
    }

    public static RequestQueue getRequestQueue() {
        return instance.requestQueue;
    }

    static Context getContext() {
        return instance.context.getApplicationContext();
    }

    static void addToRequestQueue(Request<?> req) {
        getRequestQueue().add(req);
    }

    static SharedPreferences getPrefs() {
        return getContext().getSharedPreferences(SHARED_PREFS, 0);
    }

    static <T> T fromJson(JSONObject json, Class<T> class0) {
        return fromJson(json.toString(), class0);
    }

    static <T> T fromJson(JSONArray json, Class<T> class0) {
        return fromJson(json.toString(), class0);
    }

    static <T> T fromJson(String json, Class<T> class0) {
        try {
            return getGson().fromJson(json, class0);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    static <T> String toJson(T object) {
        return getGson().toJson(object);
    }

    public static JsonObjectRequest get(
            String url, Response.Listener<JSONObject> res, Response.ErrorListener error) {
        return new JsonObjectRequest(Request.Method.GET, url, null, res, error);
    }

    public static JsonObjectRequest post(
            String url, JSONObject data,
            Response.Listener<JSONObject> res,
            Response.ErrorListener error) {

        return new JsonObjectRequest(Request.Method.POST, url, data, res, error);
    }
}
