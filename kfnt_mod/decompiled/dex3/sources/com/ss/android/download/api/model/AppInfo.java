package com.ss.android.download.api.model;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppInfo {
    public String appId;
    public String appName;
    public String appVersion;
    public String channel;
    public String versionCode;

    public AppInfo(Builder builder) {
        this.appId = "";
        this.appName = builder.mAppName;
        this.appId = builder.mAppId;
        this.channel = builder.mChannel;
        this.appVersion = builder.mAppVersion;
        this.versionCode = builder.mVersionCode;
    }

    public static class Builder {
        private String mAppId;
        private String mAppName;
        private String mAppVersion;
        private String mChannel;
        private String mVersionCode;

        public Builder appName(String str) {
            this.mAppName = str;
            return this;
        }

        public Builder appId(String str) {
            this.mAppId = str;
            return this;
        }

        public Builder channel(String str) {
            this.mChannel = str;
            return this;
        }

        public Builder appVersion(String str) {
            this.mAppVersion = str;
            return this;
        }

        public Builder versionCode(String str) {
            this.mVersionCode = str;
            return this;
        }

        public AppInfo build() {
            return new AppInfo(this);
        }
    }
}
