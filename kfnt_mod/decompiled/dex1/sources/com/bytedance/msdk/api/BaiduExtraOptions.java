package com.bytedance.msdk.api;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BaiduExtraOptions {
    public static final int DOWNLOAD_APP_CONFIRM_ALWAYS = 2;
    public static final int DOWNLOAD_APP_CONFIRM_CUSTOM_BY_APP = 4;
    public static final int DOWNLOAD_APP_CONFIRM_NEVER = 3;
    public static final int DOWNLOAD_APP_CONFIRM_ONLY_MOBILE = 1;

    /* renamed from: a, reason: collision with root package name */
    private boolean f4267a;

    /* renamed from: b, reason: collision with root package name */
    private int f4268b;

    /* renamed from: c, reason: collision with root package name */
    private BaiduNativeSmartOptStyleParams f4269c;
    private BaiduRequestParameters d;
    private BaiduSplashParams e;
    private boolean f;
    private boolean g;
    private String h;

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        @Deprecated
        private boolean f4270a;

        /* renamed from: b, reason: collision with root package name */
        @Deprecated
        private int f4271b;

        /* renamed from: c, reason: collision with root package name */
        @Deprecated
        private BaiduNativeSmartOptStyleParams f4272c;

        @Deprecated
        private BaiduRequestParameters d;

        @Deprecated
        private BaiduSplashParams e;
        private boolean f;
        private boolean g;
        private String h;

        public final BaiduExtraOptions build() {
            return new BaiduExtraOptions(this);
        }

        public Builder setAppSid(String str) {
            this.h = str;
            return this;
        }

        public Builder setBaiduNativeSmartOptStyleParams(BaiduNativeSmartOptStyleParams baiduNativeSmartOptStyleParams) {
            this.f4272c = baiduNativeSmartOptStyleParams;
            return this;
        }

        public Builder setBaiduRequestParameters(BaiduRequestParameters baiduRequestParameters) {
            this.d = baiduRequestParameters;
            return this;
        }

        public Builder setBaiduSplashParams(BaiduSplashParams baiduSplashParams) {
            this.e = baiduSplashParams;
            return this;
        }

        public Builder setCacheVideoOnlyWifi(boolean z) {
            this.f4270a = z;
            return this;
        }

        public Builder setGDTExtraOption(int i) {
            this.f4271b = i;
            return this;
        }

        public Builder setShowDialogOnSkip(boolean z) {
            this.f = z;
            return this;
        }

        public Builder setUseRewardCountdown(boolean z) {
            this.g = z;
            return this;
        }
    }

    public String getAppSid() {
        return this.h;
    }

    public BaiduNativeSmartOptStyleParams getBaiduNativeSmartOptStyleParams() {
        return this.f4269c;
    }

    public BaiduRequestParameters getBaiduRequestParameters() {
        return this.d;
    }

    public BaiduSplashParams getBaiduSplashParams() {
        return this.e;
    }

    public int getDownloadAppConfirmPolicy() {
        return this.f4268b;
    }

    public boolean getShowDialogOnSkip() {
        return this.f;
    }

    public boolean getUseRewardCountdown() {
        return this.g;
    }

    public boolean isCacheVideoOnlyWifi() {
        return this.f4267a;
    }

    private BaiduExtraOptions(Builder builder) {
        this.f4267a = builder.f4270a;
        this.f4268b = builder.f4271b;
        this.f4269c = builder.f4272c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
    }
}
