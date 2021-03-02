package com.example.fat2fit.api;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 *
 * @param <T> the type returned from the request (wrapped in an ApiResponse)
 */
public class ApiRequest<T> extends Request<ApiResponse<T>> {
    private final Class<T> class0;
    private final Map<String, String> headers;
    private final ApiResponse.Listener<T> resListener;
    private final JSONObject body;

    public ApiRequest(
            int method,
            String url,
            Class<T> class0,
            Map<String, String> headers,
            JSONObject body,
            ApiResponse.Listener<T> resListener,
            Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.class0 = class0;
        this.headers = headers;
        this.resListener = resListener;
        this.body = body;
    }

    public static <F> ApiRequest<F> get(
            Class<F> class0,
            String url,
            Map<String, String> headers,
            ApiResponse.Listener<F> listener,
            Response.ErrorListener errorListener
    ) {
        return new ApiRequest<>(
                Method.GET, url, class0,
                headers, null,
                listener, errorListener);
    }

    public static <F> ApiRequest<F> post(
            Class<F> class0,
            String url,
            Map<String, String> headers,
            JSONObject body,
            ApiResponse.Listener<F> resListener,
            Response.ErrorListener errorListener
    ) {
        return new ApiRequest<>(
                Method.POST, url, class0,
                headers, body,
                resListener, errorListener);
    }

    public static <F> ApiRequest<F> put(
            Class<F> class0,
            String url,
            Map<String, String> headers,
            JSONObject body,
            ApiResponse.Listener<F> resListener,
            Response.ErrorListener errorListener
    ) {
        return new ApiRequest<>(
                Method.PUT, url, class0,
                headers, body,
                resListener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    public byte[] getBody() {
        try {
            String charset = HttpHeaderParser.parseCharset(getHeaders());
            return body.toString().getBytes(charset);
        } catch (AuthFailureError | UnsupportedEncodingException e) {
            return body == null ? null : body.toString().getBytes();
        }
    }

    @Override
    protected void deliverResponse(ApiResponse<T> response) {
        if (response.isOK()) {
            // Continue if Response is ok.
            resListener.onResponse(response);
            return;
        }

        Response.ErrorListener listener = getErrorListener();

        if (listener != null) {
            VolleyError err = new VolleyError(response.getMeta().getMsg());
            listener.onErrorResponse(err);
        }
    }

    @Override
    protected Response<ApiResponse<T>> parseNetworkResponse(NetworkResponse response) {
        try {
            String str = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            JSONObject json = new JSONObject(str);
            ApiResponse<T> data = ApiResponse.from(class0, json);
            return data.isOK()
                ? Response.success(data, HttpHeaderParser.parseCacheHeaders(response))
                : Response.error(new VolleyError(response));
        } catch (UnsupportedEncodingException | JsonSyntaxException | JSONException e) {
            return Response.error(new ParseError(e));
        }
    }
}

