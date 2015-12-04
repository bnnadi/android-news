package com.bisikennadi.androidNews.model.request;

import java.io.Serializable;

/**
 * Created by BNnadi on 12/4/2015.
 * com.bisikennadi.androidNews.model.request
 */
public class LoginRequest implements Serializable {
    private String username;
    private String password;
    private String deviceId;
    private String version;
    private String os;

    private LoginRequest(String username, String password, String deviceId,String version,String os) {
        this.username = username;
        this.password = password;
        this.deviceId = deviceId;
        this.version = version;
        this.os = os;
    }

    public static class LoginRequestBuilder {
        private String username;
        private String password;
        private String deviceId;
        private String version;
        private String os;

        public LoginRequestBuilder username(String username) {
            this.username = username;
            return this;
        }

        public LoginRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public LoginRequestBuilder deviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public LoginRequestBuilder version(String version) {
            this.version = version;
            return this;
        }

        public LoginRequestBuilder os(String os) {
            this.os = os;
            return this;
        }

        public LoginRequest build() {
            return new LoginRequest(username,password,deviceId,version,os);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
