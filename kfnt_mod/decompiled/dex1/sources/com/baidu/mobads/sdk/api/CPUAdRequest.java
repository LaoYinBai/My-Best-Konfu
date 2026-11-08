package com.baidu.mobads.sdk.api;

import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CPUAdRequest {
    private static final String TAG = "NativeCPUAd";
    private HashMap<String, Object> mParameters;

    private CPUAdRequest(Builder builder) {
        this.mParameters = new HashMap<>();
        if (builder == null || builder.mExtras == null) {
            return;
        }
        this.mParameters = builder.mExtras;
    }

    public HashMap<String, Object> getExtras() {
        return this.mParameters;
    }

    public static class Builder {
        private HashMap<String, Object> mExtras = new HashMap<>();

        public Builder setListScene(int i) {
            this.mExtras.put("listScene", Integer.valueOf(i));
            return this;
        }

        public Builder setAccessType(int i) {
            this.mExtras.put("accessType", Integer.valueOf(i));
            return this;
        }

        public Builder setDownloadAppConfirmPolicy(int i) {
            this.mExtras.put("downloadAppConfirmPolicy", Integer.valueOf(i));
            return this;
        }

        public Builder setLpFontSize(CpuLpFontSize cpuLpFontSize) {
            this.mExtras.put("prefersfontsize", cpuLpFontSize.getValue());
            return this;
        }

        public Builder setLpDarkMode(boolean z) {
            if (z) {
                this.mExtras.put("preferscolortheme", "dark");
            } else {
                this.mExtras.put("preferscolortheme", "light");
            }
            return this;
        }

        public Builder setCustomUserId(String str) {
            this.mExtras.put("outerUid", str);
            return this;
        }

        public Builder setSubChannelId(String str) {
            this.mExtras.put("subChannelId", str);
            return this;
        }

        public Builder setCityIfLocalChannel(String str) {
            this.mExtras.put("listScene", 6);
            this.mExtras.put("city", str);
            return this;
        }

        public Builder setKeyWords(String str) {
            this.mExtras.put("keywords", str);
            return this;
        }

        public Builder addExtra(String str, String str2) {
            if (!TextUtils.isEmpty(str)) {
                this.mExtras.put(str, str2);
            }
            return this;
        }

        public CPUAdRequest build() {
            return new CPUAdRequest(this);
        }
    }
}
