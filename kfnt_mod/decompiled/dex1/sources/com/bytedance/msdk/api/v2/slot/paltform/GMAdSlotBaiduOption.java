package com.bytedance.msdk.api.v2.slot.paltform;

import com.bytedance.msdk.api.BaiduExtraOptions;
import com.bytedance.msdk.api.BaiduNativeSmartOptStyleParams;
import com.bytedance.msdk.api.BaiduRequestParameters;
import com.bytedance.msdk.api.BaiduSplashParams;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMAdSlotBaiduOption {
    public static final int DOWNLOAD_APP_CONFIRM_ALWAYS = 2;
    public static final int DOWNLOAD_APP_CONFIRM_CUSTOM_BY_APP = 4;
    public static final int DOWNLOAD_APP_CONFIRM_NEVER = 3;
    public static final int DOWNLOAD_APP_CONFIRM_ONLY_MOBILE = 1;

    /* renamed from: a, reason: collision with root package name */
    private boolean f4450a;

    /* renamed from: b, reason: collision with root package name */
    private int f4451b;

    /* renamed from: c, reason: collision with root package name */
    private BaiduNativeSmartOptStyleParams f4452c;
    private BaiduRequestParameters d;
    private BaiduSplashParams e;
    private boolean f;
    private boolean g;
    private String h;

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private boolean f4453a;

        /* renamed from: b, reason: collision with root package name */
        private int f4454b;

        /* renamed from: c, reason: collision with root package name */
        private BaiduNativeSmartOptStyleParams f4455c;
        private BaiduRequestParameters d;
        private BaiduSplashParams e;
        private boolean f;
        private boolean g;
        private String h;

        public final GMAdSlotBaiduOption build() {
            return new GMAdSlotBaiduOption(this);
        }

        public Builder setAppSid(String str) {
            this.h = str;
            return this;
        }

        public Builder setBaiduNativeSmartOptStyleParams(BaiduNativeSmartOptStyleParams baiduNativeSmartOptStyleParams) {
            this.f4455c = baiduNativeSmartOptStyleParams;
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
            this.f4453a = z;
            return this;
        }

        public Builder setDownloadAppConfirmPolicy(int i) {
            this.f4454b = i;
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

    public BaiduExtraOptions getBaiduExtra() {
        BaiduExtraOptions.Builder builder = new BaiduExtraOptions.Builder();
        builder.setCacheVideoOnlyWifi(isCacheVideoOnlyWifi());
        builder.setAppSid(getAppSid());
        builder.setBaiduNativeSmartOptStyleParams(getBaiduNativeSmartOptStyleParams());
        builder.setBaiduRequestParameters(getBaiduRequestParameters());
        builder.setBaiduSplashParams(getBaiduSplashParams());
        builder.setGDTExtraOption(getDownloadAppConfirmPolicy());
        builder.setShowDialogOnSkip(getShowDialogOnSkip());
        builder.setUseRewardCountdown(getUseRewardCountdown());
        return builder.build();
    }

    public BaiduNativeSmartOptStyleParams getBaiduNativeSmartOptStyleParams() {
        return this.f4452c;
    }

    public BaiduRequestParameters getBaiduRequestParameters() {
        return this.d;
    }

    public BaiduSplashParams getBaiduSplashParams() {
        return this.e;
    }

    public int getDownloadAppConfirmPolicy() {
        return this.f4451b;
    }

    public boolean getShowDialogOnSkip() {
        return this.f;
    }

    public boolean getUseRewardCountdown() {
        return this.g;
    }

    public boolean isCacheVideoOnlyWifi() {
        return this.f4450a;
    }

    private GMAdSlotBaiduOption(Builder builder) {
        this.f4450a = builder.f4453a;
        this.f4451b = builder.f4454b;
        this.f4452c = builder.f4455c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
    }
}
