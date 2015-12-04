package com.bisikennadi.androidNews.connector;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

/**
 * Created by BNnadi on 12/4/2015.
 * com.bisikennadi.androidNews.connector
 */
public class GsonRequest<T> extends JsonRequest<T> {
    public interface InternalServerErrorListener {
        void onErrorResponse(String json);
    }

    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private Response.Listener<T> sessionTimeOutListener = null;
    private InternalServerErrorListener fiveHundredListener = null;

    public GsonRequest(int method,String url,Class<T> clazz,String requestBody,Response.Listener<T> listener,Response.ErrorListener errorListener) {
        super(method, url,requestBody,listener, errorListener);
        this.clazz = clazz;
    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
        try {
            String json = new String(
                    error.networkResponse.data,
                    HttpHeaderParser.parseCharset(error.networkResponse.headers));

            if(error.networkResponse.statusCode==403){
                Log.e("Testing", "The status code is 403");

                // Call the session timeout listener
                if(sessionTimeOutListener!=null)sessionTimeOutListener.onResponse(null);

            }else if(error.networkResponse.statusCode==500){
                Log.e("Testing", "The status code is 500");

                // Call the session timeout listener
                if(fiveHundredListener!=null)fiveHundredListener.onErrorResponse(json);

            }
        } catch (Exception ignored) {
        }
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        try {
            String json = new String(
                    volleyError.networkResponse.data,
                    HttpHeaderParser.parseCharset(volleyError.networkResponse.headers));
            if(volleyError.networkResponse.statusCode==403){
                Log.e("Volley Testing", "The status code is 403");

                // Call the session timeout listener
                if(sessionTimeOutListener!=null)sessionTimeOutListener.onResponse(null);

            }else if(volleyError.networkResponse.statusCode==500){
                Log.e("Volley Testing", "The status code is 500");

                // Call the session timeout listener
                if(fiveHundredListener!=null)fiveHundredListener.onErrorResponse(json);

            }
        } catch (Exception e) {
            return super.parseNetworkError(volleyError);
        }

        return volleyError;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {

            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            if(response.statusCode==403){
                Log.e("Testing", "The status code is 403");

                // Call the session timeout listener
                if(sessionTimeOutListener!=null)sessionTimeOutListener.onResponse(null);

            }else if(response.statusCode==500){
                Log.e("Testing", "The status code is 500");

                // Call the session timeout listener
                if(fiveHundredListener!=null)fiveHundredListener.onErrorResponse(json);

            }

            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

}
