package com.bisikennadi.androidNews.model;

import android.app.Application;

import com.bisikennadi.androidNews.R;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

/**
 * Created by BNnadi on 12/2/2015.
 * com.bisikennadi.androidNews.model
 */
@ReportsCrashes(
        mode = ReportingInteractionMode.DIALOG,
        resDialogText = R.string.crash_dialog_text,
        resDialogIcon = android.R.drawable.ic_dialog_info,
        resDialogTitle = R.string.crash_dialog_title,
        resDialogCommentPrompt = R.string.crash_dialog_comment_prompt,
        resDialogEmailPrompt = R.string.crash_user_email_label,
        resDialogOkToast = R.string.crash_dialog_ok_toast
)
public class SessionData extends Application {

    private String username;
    private String password;
    private String sessionId;
    private String lastLocationUpdate;

    @Override
    public void onCreate() {
        super.onCreate();
        ACRA.init(this);
    }

    public void killSession() {
        this.username = null;
        this.password = null;
        this.sessionId = null;
        this.lastLocationUpdate = null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setLastLocationUpdate(String lastLocationUpdate) {
        this.lastLocationUpdate = lastLocationUpdate;
    }

    public String getLastLocationUpdate() {
        return lastLocationUpdate;
    }
}
