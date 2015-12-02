package com.bisikennadi.androidNews.model;

import android.app.Application;

import org.acra.ACRA;

/**
 * Created by BNnadi on 12/2/2015.
 * com.bisikennadi.androidNews.model
 */
public class SessionData extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ACRA.init(this);
    }
}
