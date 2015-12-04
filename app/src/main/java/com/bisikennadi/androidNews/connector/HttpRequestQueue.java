package com.bisikennadi.androidNews.connector;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by BNnadi on 12/4/2015.
 * com.bisikennadi.androidNews.connector
 */
public class HttpRequestQueue {
    private RequestQueue requestQueue = null;
    private static HttpRequestQueue instance = null;

    private HttpRequestQueue(Context context) {
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
    }
    public static synchronized HttpRequestQueue getInstance(Context context) {
        if(instance == null) {
            instance = new HttpRequestQueue(context);
        }
        return instance;
    }

    public <T>void addToQueue(Request<T> request) {
        requestQueue.add(request);
    }
}
