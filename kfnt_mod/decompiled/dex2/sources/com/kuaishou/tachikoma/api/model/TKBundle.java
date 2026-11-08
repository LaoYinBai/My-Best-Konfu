package com.kuaishou.tachikoma.api.model;

import com.kuaishou.tachikoma.api.BuildConfig;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TKBundle {
    private String mBundleId;
    private String mBundleVersion;
    private String mBundleVersionCode;
    private String mBusinessName;
    private String mEngineVersion;
    private long mTaskId;

    public TKBundle(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, -1L);
    }

    public TKBundle(String str, String str2, String str3, String str4, long j) {
        this.mBundleId = str;
        this.mBundleVersion = str2;
        this.mBundleVersionCode = str3;
        this.mEngineVersion = BuildConfig.VERSION_NAME;
        this.mBusinessName = str4;
        this.mTaskId = j;
    }

    public String getBundleId() {
        return this.mBundleId;
    }

    public String getBundleVersion() {
        return this.mBundleVersion;
    }

    public String getBundleVersionCode() {
        return this.mBundleVersionCode;
    }

    public String getBusinessName() {
        return this.mBusinessName;
    }

    public String getEngineVersion() {
        return this.mEngineVersion;
    }

    public long getTaskId() {
        return this.mTaskId;
    }

    public void setBundleId(String str) {
        this.mBundleId = str;
    }

    public void setBundleVersion(String str) {
        this.mBundleVersion = str;
    }

    public void setBundleVersionCode(String str) {
        this.mBundleVersionCode = str;
    }

    public void setBusinessName(String str) {
        this.mBusinessName = str;
    }

    public void setEngineVersion(String str) {
        this.mEngineVersion = str;
    }

    public void setTaskId(long j) {
        this.mTaskId = j;
    }

    public String toString() {
        return "TKBundle{mBundleId='" + this.mBundleId + "', mBundleVersion='" + this.mBundleVersion + "', mBundleVersionCode='" + this.mBundleVersionCode + "', mEngineVersion='" + this.mEngineVersion + "'}";
    }
}
