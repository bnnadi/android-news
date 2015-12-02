package com.bisikennadi.androidNews.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import com.bisikennadi.androidNews.R;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by BNnadi on 12/2/2015.
 * com.bisikennadi.androidNews.utils
 */
public class CompatUtils {

    public static  boolean isJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean isTablet(Context context) {
        boolean xlarge = (context.getResources().getConfiguration().screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK) == 4;
        boolean large = (context.getResources().getConfiguration().screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE;
        return (xlarge || large);
    }

    public static boolean isJellyBeanTablet(Context context) {
        return isJellyBean() && isTablet(context);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        Log.v(CompatUtils.class.getName(), "connected!");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static  int checkPlayServices(Context context) {
        return GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
    }

    public static boolean isGoogleMapsInstalled(Context context)
    {
        try
        {
            context.getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
            return true;
        }
        catch(PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }

    private CompatUtils() {}
}
