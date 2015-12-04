package com.bisikennadi.androidNews.connector;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bisikennadi.androidNews.model.SessionData;
import com.bisikennadi.androidNews.model.reponse.ErrorResponse;
import com.bisikennadi.androidNews.model.reponse.LoginResponse;
import com.bisikennadi.androidNews.model.request.LoginRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by BNnadi on 12/4/2015.
 * com.bisikennadi.androidNews.connector
 */
public class BNNConnector {

    private static String TAG = BNNConnector.class.getSimpleName();

    /**
     *
     * Performs HTTP POST
     *
     * @param url               URL
     * @param data              request data
     * @param sessionData       {@link SessionData} the user's session
     * @param responseType      {@link Class} the expected response type
     * @param responseListener  {@link com.android.volley.Response.Listener} success response listener
     * @param errorListener     {@link com.android.volley.Response.ErrorListener} error response listener
     */
    private static void post(final String url, final Object data, final SessionData sessionData, final Class responseType,
                             final Response.Listener responseListener, final Response.ErrorListener errorListener) {
        GsonRequest request = new GsonRequest(Request.Method.POST,url, responseType,new Gson().toJson(data),responseListener,errorListener);
        request.setRetryPolicy(new DefaultRetryPolicy(5000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        HttpRequestQueue.getInstance(sessionData).addToQueue(request);
    }


    /**
     *
     * Performs HTTP POST
     *
     * @param url               URL
     * @param data              request data
     * @param responseListener  {@link Response.Listener} success response listener
     * @param errorListener     {@link Response.ErrorListener} error response listener
     */
    private static void post(final String url, final Object data, final SessionData sessionData, final Response.Listener responseListener, final Response.ErrorListener errorListener) {
        GsonRequest request = new GsonRequest(Request.Method.POST,url, JSONObject.class,new Gson().toJson(data),responseListener,errorListener);
        request.setRetryPolicy(new DefaultRetryPolicy(5000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        HttpRequestQueue.getInstance(sessionData).addToQueue(request);
    }

    public static void login(final LoginRequest request, final SessionData sessionData) {
        final String url = null;
        BNNConnector.post(url, request, sessionData, LoginResponse.class, new Response.Listener() {
            @Override
            public void onResponse(Object response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    public static void authenticate(final SessionData sessionData) {
        final String url = null;
        BNNConnector.post(url, null, sessionData, LoginResponse.class, new Response.Listener() {
            @Override
            public void onResponse(Object response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                
            }
        });
    }

    private static boolean isSessionTimeout(final VolleyError volleyError) {
        if(volleyError == null || volleyError.networkResponse == null || volleyError.networkResponse.data == null) return false;
        try {
            ErrorResponse errorResponse = new Gson().fromJson(new String(volleyError.networkResponse.data, "UTF-8"), ErrorResponse.class);
            return errorResponse.isSessionTimedOut();
        } catch (Exception e){
            Log.e(TAG, "Error Marshalling Error Response", e);
            return false;
        }
    }

    public static void handleSessionTimeout(final SessionData sessionData, final boolean notifyUser) {
        if(notifyUser) {
            // TODO notify the user the their session has timed out
        }
        // TODO use the authenticate method to log them back in
        authenticate(sessionData);
    }

    public static void logout(final SessionData sessionData) {
        final String url = null;
        BNNConnector.post(url,null,sessionData, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                sessionData.killSession();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                sessionData.killSession();
            }
        });
    }

}
