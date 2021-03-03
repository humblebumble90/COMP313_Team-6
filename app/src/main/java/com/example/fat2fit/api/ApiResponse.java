package com.example.fat2fit.api;

import com.android.volley.Response;

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

    public static <F> ApiResponse<F> from(Class<F> class0, JSONObject json) {
        ApiResponse<F> res = new ApiResponse<>();
        try {
            JSONObject meta = json.getJSONObject("meta"),
                    data = json.getJSONObject("data");

            res.setMeta(RequestHelper.fromJson(meta, Meta.class));
            res.setData(RequestHelper.fromJson(data, class0));
        } catch (JSONException e) {
            e.printStackTrace();
            res.setMetaData(567, e.getMessage());
        }

        return res;
    }

    public interface Listener<T> extends Response.Listener<ApiResponse<T>> {}

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
}
