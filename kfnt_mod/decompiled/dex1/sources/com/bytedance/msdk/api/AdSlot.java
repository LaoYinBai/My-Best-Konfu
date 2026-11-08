package com.bytedance.msdk.api;

import android.widget.FrameLayout;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.b;
import java.util.Map;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AdSlot extends b {
    public static final int AUTO_HEIGHT = -2;

    @Deprecated
    public static final String CUSTOM_DATA_KEY_ADMOB = "admob";

    @Deprecated
    public static final String CUSTOM_DATA_KEY_BAIDU = "baidu";

    @Deprecated
    public static final String CUSTOM_DATA_KEY_GDT = "gdt";

    @Deprecated
    public static final String CUSTOM_DATA_KEY_KLEVIN = "klevin";

    @Deprecated
    public static final String CUSTOM_DATA_KEY_KS = "ks";

    @Deprecated
    public static final String CUSTOM_DATA_KEY_MINTEGRAL = "mintegral";

    @Deprecated
    public static final String CUSTOM_DATA_KEY_PANGLE = "pangle";

    @Deprecated
    public static final String CUSTOM_DATA_KEY_SIGMOB = "sigmob";

    @Deprecated
    public static final String CUSTOM_DATA_KEY_UNITY = "unity";
    public static final int FULL_WIDTH = -1;
    public static final int POSITION_BOTTOM = 2;
    public static final int POSITION_FEED = 3;
    public static final int POSITION_FULL_SCREEN = 5;
    public static final int POSITION_MIDDLE = 4;
    public static final int POSITION_TOP = 1;
    public static final int TYPE_BANNER = 1;

    @Deprecated
    public static final int TYPE_CACHED_SPLASH = 4;

    @Deprecated
    public static final int TYPE_DRAW_FEED = 9;
    public static final int TYPE_EXPRESS_AD = 1;
    public static final int TYPE_FEED = 5;
    public static final int TYPE_FULL_SCREEN_VIDEO = 8;
    public static final int TYPE_INTERACTION_AD = 2;
    public static final int TYPE_INTERACTION_FULL = 10;
    public static final int TYPE_NATIVE_AD = 2;
    public static final int TYPE_REWARD_VIDEO = 7;
    public static final int TYPE_SPLASH = 3;

    /* renamed from: a, reason: collision with root package name */
    private String f4258a;

    /* renamed from: b, reason: collision with root package name */
    private long f4259b;

    /* renamed from: c, reason: collision with root package name */
    private String f4260c;
    private int d;
    private int e;
    private int f;
    private boolean g;
    private int h;
    private int i;
    private int j;
    private String k;
    private int l;
    private String m;
    private String n;
    private Map<String, String> o;
    private int p;
    private TTVideoOption q;
    private TTRequestExtraParams r;
    private AdmobNativeAdOptions s;
    private FrameLayout.LayoutParams t;
    private String u;
    private boolean v;
    private int w;
    private int x;
    private boolean y;

    public static class Builder {

        @Deprecated
        private String e;

        @Deprecated
        private int f;

        @Deprecated
        private String g;

        @Deprecated
        private Map<String, String> h;

        @Deprecated
        private String i;

        @Deprecated
        private int j;

        @Deprecated
        private int k;

        @Deprecated
        private TTVideoOption l;

        @Deprecated
        private TTRequestExtraParams m;

        @Deprecated
        private AdmobNativeAdOptions p;

        @Deprecated
        private FrameLayout.LayoutParams q;

        @Deprecated
        private String r;
        private boolean s;

        @Deprecated
        private boolean v;

        /* renamed from: a, reason: collision with root package name */
        @Deprecated
        private int f4261a = 640;

        /* renamed from: b, reason: collision with root package name */
        @Deprecated
        private int f4262b = 320;

        /* renamed from: c, reason: collision with root package name */
        @Deprecated
        private boolean f4263c = true;

        @Deprecated
        private int d = 1;

        @Deprecated
        private int n = 2;

        @Deprecated
        private int o = 3;

        @Deprecated
        private int t = 1;

        @Deprecated
        private int u = 0;

        public AdSlot build() {
            AdSlot adSlot = new AdSlot();
            adSlot.f = this.d;
            adSlot.g = this.f4263c;
            adSlot.d = this.f4261a;
            adSlot.e = this.f4262b;
            adSlot.k = this.e;
            adSlot.l = this.f;
            adSlot.m = this.g;
            adSlot.o = this.h;
            adSlot.n = this.i;
            adSlot.p = this.j;
            adSlot.h = this.k;
            adSlot.i = this.n;
            adSlot.q = this.l;
            adSlot.r = this.m;
            adSlot.s = this.p;
            adSlot.j = this.o;
            adSlot.t = this.q;
            adSlot.u = this.r;
            adSlot.v = this.s;
            adSlot.w = this.t;
            adSlot.x = this.u;
            adSlot.y = this.v;
            return adSlot;
        }

        @Deprecated
        public Builder setAdCount(int i) {
            this.d = i;
            return this;
        }

        @Deprecated
        public Builder setAdStyleType(int i) {
            this.n = i;
            return this;
        }

        @Deprecated
        public Builder setAdType(int i) {
            this.k = i;
            return this;
        }

        @Deprecated
        public Builder setAdmobNativeAdOptions(AdmobNativeAdOptions admobNativeAdOptions) {
            this.p = admobNativeAdOptions;
            return this;
        }

        @Deprecated
        public Builder setBannerSize(int i) {
            this.o = i;
            return this;
        }

        @Deprecated
        public Builder setBidNotify(boolean z) {
            this.v = z;
            return this;
        }

        @Deprecated
        public Builder setCustomData(Map<String, String> map) {
            this.h = map;
            return this;
        }

        @Deprecated
        public Builder setDownloadType(int i) {
            this.u = i;
            return this;
        }

        public Builder setGdtNativeAdLogoParams(FrameLayout.LayoutParams layoutParams) {
            this.q = layoutParams;
            return this;
        }

        @Deprecated
        public Builder setImageAdSize(int i, int i2) {
            this.f4261a = i;
            this.f4262b = i2;
            return this;
        }

        @Deprecated
        public Builder setMediaExtra(String str) {
            this.g = str;
            return this;
        }

        @Deprecated
        public Builder setOrientation(int i) {
            this.j = i;
            return this;
        }

        @Deprecated
        public Builder setRewardAmount(int i) {
            this.f = i;
            return this;
        }

        @Deprecated
        public Builder setRewardName(String str) {
            this.e = str;
            return this;
        }

        @Deprecated
        public Builder setSplashButtonType(int i) {
            this.t = i;
            return this;
        }

        @Deprecated
        public Builder setSupportDeepLink(boolean z) {
            this.f4263c = z;
            return this;
        }

        @Deprecated
        public Builder setTTRequestExtraParams(TTRequestExtraParams tTRequestExtraParams) {
            this.m = tTRequestExtraParams;
            return this;
        }

        @Deprecated
        public Builder setTTVideoOption(TTVideoOption tTVideoOption) {
            this.l = tTVideoOption;
            return this;
        }

        @Deprecated
        public Builder setTestSlotId(String str) {
            this.r = str;
            return this;
        }

        @Deprecated
        public Builder setUserID(String str) {
            this.i = str;
            return this;
        }

        public Builder setV2Request(boolean z) {
            this.s = z;
            return this;
        }
    }

    public int getAdCount() {
        return this.f;
    }

    public int getAdStyleType() {
        return this.i;
    }

    public int getAdType() {
        return this.h;
    }

    public String getAdUnitId() {
        return this.f4258a;
    }

    public AdmobNativeAdOptions getAdmobNativeAdOptions() {
        return this.s;
    }

    public int getBannerSize() {
        return this.j;
    }

    public Map<String, String> getCustomData() {
        return this.o;
    }

    public int getDownloadType() {
        return this.x;
    }

    public FrameLayout.LayoutParams getGdtNativeAdLogoParams() {
        return this.t;
    }

    public int getImgAcceptedHeight() {
        return this.e;
    }

    public int getImgAcceptedWidth() {
        return this.d;
    }

    @Deprecated
    public String getMediaExtra() {
        return this.m;
    }

    public int getOrientation() {
        return this.p;
    }

    public TTRequestExtraParams getReuestParam() {
        if (this.r == null) {
            this.r = new TTRequestExtraParams();
        }
        return this.r;
    }

    public int getRewardAmount() {
        return this.l;
    }

    public String getRewardName() {
        return this.k;
    }

    public int getSplashButtonType() {
        return this.w;
    }

    public TTVideoOption getTTVideoOption() {
        return this.q;
    }

    @Deprecated
    public String getTestSlotId() {
        return this.u;
    }

    public String getUserID() {
        return this.n;
    }

    @Deprecated
    public String getVersion() {
        return this.f4260c;
    }

    @Deprecated
    public long getWaterfallId() {
        return this.f4259b;
    }

    public boolean isBidNotify() {
        return this.y;
    }

    public boolean isSupportDeepLink() {
        return this.g;
    }

    public boolean isV2Request() {
        return this.v;
    }

    public void setAdCount(int i) {
        this.f = i;
    }

    public void setAdType(int i) {
        this.h = i;
    }

    public void setAdUnitId(String str) {
        this.f4258a = str;
    }

    public void setTTVideoOption(TTVideoOption tTVideoOption) {
        this.q = tTVideoOption;
    }

    @Deprecated
    public void setTestSlotId(String str) {
        this.u = str;
    }

    @Deprecated
    public void setVersion(String str) {
        this.f4260c = str;
    }

    @Deprecated
    public void setWaterfallId(long j) {
        this.f4259b = j;
    }

    private AdSlot() {
        this.i = 2;
        this.j = 3;
    }
}
