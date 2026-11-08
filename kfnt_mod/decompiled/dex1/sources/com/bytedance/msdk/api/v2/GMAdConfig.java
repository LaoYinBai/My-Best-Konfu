package com.bytedance.msdk.api.v2;

import androidx.annotation.NonNull;
import com.bytedance.msdk.api.v2.GMPangleOption;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMAdConfig {

    /* renamed from: a, reason: collision with root package name */
    private String f4333a;

    /* renamed from: b, reason: collision with root package name */
    private String f4334b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f4335c;
    private String d;
    private boolean e;
    private GMPangleOption f;
    private GMConfigUserInfoForSegment g;
    private GMPrivacyConfig h;
    private Map<String, Object> i;
    private boolean j;

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private String f4336a;

        /* renamed from: b, reason: collision with root package name */
        private String f4337b;
        private GMPangleOption f;
        private GMConfigUserInfoForSegment g;
        private GMPrivacyConfig h;
        private Map<String, Object> i;

        /* renamed from: c, reason: collision with root package name */
        private boolean f4338c = false;
        private String d = "";
        private boolean e = false;
        private boolean j = false;

        public GMAdConfig build() {
            return new GMAdConfig(this);
        }

        public Builder setAppId(String str) {
            this.f4336a = str;
            return this;
        }

        public Builder setAppName(String str) {
            this.f4337b = str;
            return this;
        }

        public Builder setConfigUserInfoForSegment(@NonNull GMConfigUserInfoForSegment gMConfigUserInfoForSegment) {
            this.g = gMConfigUserInfoForSegment;
            return this;
        }

        public Builder setDebug(boolean z) {
            this.f4338c = z;
            return this;
        }

        public Builder setHttps(boolean z) {
            this.j = z;
            return this;
        }

        public Builder setLocalExtra(Map<String, Object> map) {
            if (map != null && !map.isEmpty()) {
                this.i = new HashMap();
                this.i.putAll(map);
            }
            return this;
        }

        public Builder setOpenAdnTest(boolean z) {
            this.e = z;
            return this;
        }

        public Builder setPangleOption(@NonNull GMPangleOption gMPangleOption) {
            this.f = gMPangleOption;
            return this;
        }

        public Builder setPrivacyConfig(GMPrivacyConfig gMPrivacyConfig) {
            this.h = gMPrivacyConfig;
            return this;
        }

        public Builder setPublisherDid(@NonNull String str) {
            this.d = str;
            return this;
        }
    }

    public String getAppId() {
        return this.f4333a;
    }

    public String getAppName() {
        return this.f4334b;
    }

    @NonNull
    public GMConfigUserInfoForSegment getGMConfigUserInfoForSegment() {
        return this.g;
    }

    @NonNull
    public GMPangleOption getGMPangleOption() {
        return this.f;
    }

    public Map<String, Object> getLocalExtra() {
        return this.i;
    }

    public GMPrivacyConfig getPrivacyConfig() {
        return this.h;
    }

    public String getPublisherDid() {
        return this.d;
    }

    public boolean isDebug() {
        return this.f4335c;
    }

    public boolean isHttps() {
        return this.j;
    }

    public boolean isOpenAdnTest() {
        return this.e;
    }

    private GMAdConfig(Builder builder) {
        this.f4333a = builder.f4336a;
        this.f4334b = builder.f4337b;
        this.f4335c = builder.f4338c;
        this.d = builder.d;
        this.e = builder.e;
        if (builder.f != null) {
            this.f = builder.f;
        } else {
            this.f = new GMPangleOption.Builder().build();
        }
        if (builder.g != null) {
            this.g = builder.g;
        } else {
            this.g = new GMConfigUserInfoForSegment();
        }
        this.h = builder.h;
        this.i = builder.i;
        this.j = builder.j;
    }
}
