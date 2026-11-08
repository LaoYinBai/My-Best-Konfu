package com.ss.android.downloadlib.addownload.model;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadEventInfo {
    private long mAdId;
    private long mExtValue;
    private boolean mIsEnableBackDialog;
    private String mLogExtra;

    public long getAdId() {
        return this.mAdId;
    }

    public long getExtValue() {
        return this.mExtValue;
    }

    public String getLogExtra() {
        return this.mLogExtra;
    }

    public boolean isEnableBackDialog() {
        return this.mIsEnableBackDialog;
    }

    public DownloadEventInfo(Builder builder) {
        this.mAdId = builder.mAdId;
        this.mExtValue = builder.mExtValue;
        this.mLogExtra = builder.mLogExtra;
        this.mIsEnableBackDialog = builder.mIsEnableBackDialog;
    }

    public static class Builder {
        public long mAdId;
        public long mExtValue;
        public boolean mIsEnableBackDialog;
        public String mLogExtra;

        public Builder setAdId(long j) {
            this.mAdId = j;
            return this;
        }

        public Builder setExtValue(long j) {
            this.mExtValue = j;
            return this;
        }

        public Builder setLogExtra(String str) {
            this.mLogExtra = str;
            return this;
        }

        public Builder setIsEnableBackDialog(boolean z) {
            this.mIsEnableBackDialog = z;
            return this;
        }

        public DownloadEventInfo build() {
            return new DownloadEventInfo(this);
        }
    }
}
