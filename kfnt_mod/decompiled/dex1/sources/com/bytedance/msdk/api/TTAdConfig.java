package com.bytedance.msdk.api;

import android.text.TextUtils;
import com.bytedance.msdk.api.v2.GMPrivacyConfig;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class TTAdConfig {

    /* renamed from: a, reason: collision with root package name */
    private String f4289a;

    /* renamed from: b, reason: collision with root package name */
    private String f4290b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f4291c;
    private boolean d;
    private String e;
    private boolean f;
    private int g;
    private String[] h;
    private boolean i;
    private boolean j;
    private int[] k;
    private boolean l;
    private String m;
    private Map<String, String> n;
    private boolean o;
    private String p;
    private Set<String> q;
    private Map<String, Map<String, String>> r;
    private Map<String, Map<String, String>> s;
    private UserInfoForSegment t;
    private int u;
    private GMPrivacyConfig v;

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        @Deprecated
        private String f4292a;

        /* renamed from: b, reason: collision with root package name */
        @Deprecated
        private String f4293b;

        @Deprecated
        private int[] h;

        @Deprecated
        private String[] j;

        @Deprecated
        private String k;

        @Deprecated
        private boolean m;

        @Deprecated
        private String n;

        @Deprecated
        private String p;
        private Set<String> q;
        private Map<String, Map<String, String>> r;
        private Map<String, Map<String, String>> s;

        @Deprecated
        private UserInfoForSegment t;
        private GMPrivacyConfig v;

        /* renamed from: c, reason: collision with root package name */
        @Deprecated
        private boolean f4294c = false;

        @Deprecated
        private boolean d = false;

        @Deprecated
        private int e = 0;

        @Deprecated
        private boolean f = true;

        @Deprecated
        private boolean g = false;

        @Deprecated
        private boolean i = false;

        @Deprecated
        private boolean l = true;

        @Deprecated
        private Map<String, String> o = new HashMap();

        @Deprecated
        private int u = 2;

        @Deprecated
        public Builder allowPangleShowNotify(boolean z) {
            this.f = z;
            return this;
        }

        @Deprecated
        public Builder allowPangleShowPageWhenScreenLock(boolean z) {
            this.g = z;
            return this;
        }

        @Deprecated
        public Builder appId(String str) {
            this.f4292a = str;
            return this;
        }

        @Deprecated
        public Builder appName(String str) {
            this.f4293b = str;
            return this;
        }

        @Deprecated
        public TTAdConfig build() {
            return new TTAdConfig(this);
        }

        @Deprecated
        public Builder data(String str) {
            this.n = str;
            return this;
        }

        @Deprecated
        public Builder dataMap(Map<String, String> map) {
            if (map != null && map.size() > 0) {
                this.o.putAll(map);
            }
            return this;
        }

        @Deprecated
        public Builder isPanglePaid(boolean z) {
            this.d = z;
            return this;
        }

        @Deprecated
        public Builder needPangleClearTaskReset(String... strArr) {
            this.j = strArr;
            return this;
        }

        @Deprecated
        public Builder openAdnTest(boolean z) {
            this.m = z;
            return this;
        }

        @Deprecated
        public Builder openDebugLog(boolean z) {
            this.f4294c = z;
            return this;
        }

        public Builder setBaiduSdkReadDeviceId(boolean z) {
            this.l = z;
            return this;
        }

        @Deprecated
        public Builder setKeywords(String str) {
            this.p = str;
            return this;
        }

        @Deprecated
        public Builder setPangleDirectDownloadNetworkType(int... iArr) {
            this.h = iArr;
            return this;
        }

        @Deprecated
        public Builder setPangleTitleBarTheme(int i) {
            this.e = i;
            return this;
        }

        public Builder setPrivacyConfig(GMPrivacyConfig gMPrivacyConfig) {
            this.v = gMPrivacyConfig;
            return this;
        }

        public Builder setPublisherDid(String str) {
            this.k = str;
            return this;
        }

        @Deprecated
        public Builder setUserInfoForSegment(UserInfoForSegment userInfoForSegment) {
            this.t = userInfoForSegment;
            return this;
        }

        @Deprecated
        public Builder usePangleTextureView(boolean z) {
            this.i = z;
            return this;
        }

        @Deprecated
        public Builder data(String str, String str2) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                this.o.put(str, str2);
            }
            return this;
        }
    }

    public boolean allowBaiduSdkReadDeviceId() {
        return this.o;
    }

    public Set<String> getAdapterConfigurationClasses() {
        Set<String> set = this.q;
        if (set != null) {
            return Collections.unmodifiableSet(set);
        }
        return null;
    }

    public String getAppId() {
        return this.f4289a;
    }

    public String getAppName() {
        return this.f4290b;
    }

    public Map<String, String> getExtraData() {
        return this.n;
    }

    public Map<String, Map<String, String>> getMediatedNetworkConfigurations() {
        Map<String, Map<String, String>> map = this.r;
        if (map != null) {
            return Collections.unmodifiableMap(map);
        }
        return null;
    }

    @Deprecated
    public String getPangleData() {
        return this.m;
    }

    public int[] getPangleDirectDownloadNetworkType() {
        return this.k;
    }

    public String getPangleKeywords() {
        return this.p;
    }

    public String[] getPangleNeedClearTaskReset() {
        return this.h;
    }

    public int getPanglePluginUpdateConfig() {
        return this.u;
    }

    public int getPangleTitleBarTheme() {
        return this.g;
    }

    public GMPrivacyConfig getPrivacyConfig() {
        return this.v;
    }

    public String getPublisherDid() {
        return this.e;
    }

    public Map<String, Map<String, String>> getRequestOptions() {
        Map<String, Map<String, String>> map = this.s;
        if (map != null) {
            return Collections.unmodifiableMap(map);
        }
        return null;
    }

    public UserInfoForSegment getUserInfoForSegment() {
        return this.t;
    }

    public boolean isDebug() {
        return this.f4291c;
    }

    public boolean isOpenAdnTest() {
        return this.f;
    }

    public boolean isPangleAllowShowNotify() {
        return this.i;
    }

    public boolean isPangleAllowShowPageWhenScreenLock() {
        return this.j;
    }

    public boolean isPanglePaid() {
        return this.d;
    }

    public boolean isPangleUseTextureView() {
        return this.l;
    }

    private TTAdConfig(Builder builder) {
        this.f4291c = false;
        this.d = false;
        this.e = null;
        this.g = 0;
        this.i = true;
        this.j = false;
        this.l = false;
        this.o = true;
        this.u = 2;
        this.f4289a = builder.f4292a;
        this.f4290b = builder.f4293b;
        this.f4291c = builder.f4294c;
        this.d = builder.d;
        this.e = builder.k;
        this.f = builder.m;
        this.g = builder.e;
        this.h = builder.j;
        this.i = builder.f;
        this.j = builder.g;
        this.k = builder.h;
        this.l = builder.i;
        this.m = builder.n;
        this.n = builder.o;
        this.p = builder.p;
        this.q = builder.q;
        this.r = builder.r;
        this.s = builder.s;
        this.o = builder.l;
        this.t = builder.t;
        this.u = builder.u;
        this.v = builder.v;
    }
}
