package com.example.fat2fit.api;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.fat2fit.helpers.StringHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @param <T> The data type that the response wraps
 */
public class ApiResponse<T> {
    private Meta meta;
    private T data;

    public ApiResponse() {
        this(null, null);
    }

    public ApiResponse(Meta meta, T data) {
        this.meta = meta;
        this.data = data;
    }

    public boolean isOK() {
        return meta != null && meta.isOK();
    }

    public void setMetaData(int code) {
        if (meta != null) meta.setCode(code);
        else meta = new Meta(code);
    }
    public void setMetaData(int code, String msg) {
        if (meta != null) {
            meta.setCode(code);
            meta.setMsg(msg);
        } else {
            meta = new Meta(code, msg);
        }
    }

    public Meta getMeta() { return meta; }
    public void setMeta(Meta meta) { this.meta = meta; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    @SuppressWarnings("unchecked")
    public static <F> ApiResponse<F> from(Class<F> class0, JSONObject json) {
        ApiResponse<F> res = new ApiResponse<>();
        try {
            JSONObject meta = json.getJSONObject("meta");
            res.setMeta(RequestHelper.fromJson(meta, Meta.class));

            if (res.isOK()) {
                if (class0 == String.class) {
                    res.setData((F) json.getString("data"));
                } else if (class0.isArray()) {
                    JSONArray data = json.getJSONArray("data");
                    res.setData(RequestHelper.fromJson(data, class0));
                } else {
                    Log.d("JSON_OBJECT", "" + json);
                    JSONObject data = json.getJSONObject("data");
                    res.setData(RequestHelper.fromJson(data, class0));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            res.setMetaData(567, e.getMessage());
        }

        return res;
    }

    public static class Meta {
        private int code;
        private String msg;

        public Meta() {
            this(200);
        }
        public Meta(int code) {
            this(code, "");
        }
        public Meta(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public boolean isOK() {
            return code == 200;
        }

        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return String.format("{ code: %s, msg: \"%s\" }", code, msg);
        }
    }

    public interface Listener<T> extends Response.Listener<ApiResponse<T>> {}

    public static class MetaVolleyError extends VolleyError {
        private final Meta meta;

        public MetaVolleyError(Meta meta) {
            super(meta.getMsg());
            this.meta = meta;
        }

        public MetaVolleyError(int code, String msg) {
            this(new Meta(code, msg));
        }

        @Override
        public String getMessage() {
            return meta.getMsg();
        }

        @Override
        public String toString() {
            return !StringHelper.isNullOrEmpty(meta.getMsg()) ? meta.getMsg() : "Error";
        }
    }
}
