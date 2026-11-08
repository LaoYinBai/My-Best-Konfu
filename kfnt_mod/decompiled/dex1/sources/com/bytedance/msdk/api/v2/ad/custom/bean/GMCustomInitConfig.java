package com.bytedance.msdk.api.v2.ad.custom.bean;

import android.text.TextUtils;
import com.bytedance.msdk.api.v2.ad.custom.banner.GMCustomBannerAdapter;
import com.bytedance.msdk.api.v2.ad.custom.fullvideo.GMCustomFullVideoAdapter;
import com.bytedance.msdk.api.v2.ad.custom.interstitial.GMCustomInterstitialAdapter;
import com.bytedance.msdk.api.v2.ad.custom.nativeAd.GMCustomNativeAdapter;
import com.bytedance.msdk.api.v2.ad.custom.reward.GMCustomRewardAdapter;
import com.bytedance.msdk.api.v2.ad.custom.splash.GMCustomSplashAdapter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMCustomInitConfig {
    public static final String CUSTOM_TYPE = "1";

    /* renamed from: a, reason: collision with root package name */
    private final String f4381a;

    /* renamed from: b, reason: collision with root package name */
    private final String f4382b;

    /* renamed from: c, reason: collision with root package name */
    private final String f4383c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;

    public GMCustomInitConfig() {
        this.f4383c = "";
        this.f4381a = "";
        this.f4382b = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.k = "";
    }

    public String getADNName() {
        return this.f4383c;
    }

    public String getAdnInitClassName() {
        return this.d;
    }

    public String getAppId() {
        return this.f4381a;
    }

    public String getAppKey() {
        return this.f4382b;
    }

    public GMCustomAdConfig getClassName(int i, int i2) {
        switch (i) {
            case 1:
                return new GMCustomAdConfig(this.e, GMCustomBannerAdapter.class);
            case 2:
                return new GMCustomAdConfig(this.f, GMCustomInterstitialAdapter.class);
            case 3:
                return new GMCustomAdConfig(this.i, GMCustomSplashAdapter.class);
            case 4:
            case 6:
            case 9:
            default:
                return null;
            case 5:
                return new GMCustomAdConfig(this.j, GMCustomNativeAdapter.class);
            case 7:
                return new GMCustomAdConfig(this.g, GMCustomRewardAdapter.class);
            case 8:
                return new GMCustomAdConfig(this.h, GMCustomFullVideoAdapter.class);
            case 10:
                if (i2 == 1) {
                    return new GMCustomAdConfig(this.f, GMCustomInterstitialAdapter.class);
                }
                if (i2 == 2) {
                    return new GMCustomAdConfig(this.h, GMCustomFullVideoAdapter.class);
                }
                return null;
        }
    }

    public boolean isCustom() {
        return TextUtils.equals(this.k, "1");
    }

    public String toString() {
        return "GMCustomInitConfig{mAppId='" + this.f4381a + "', mAppKey='" + this.f4382b + "', mADNName='" + this.f4383c + "', mAdnInitClassName='" + this.d + "', mBannerClassName='" + this.e + "', mInterstitialClassName='" + this.f + "', mRewardClassName='" + this.g + "', mFullVideoClassName='" + this.h + "', mSplashClassName='" + this.i + "', mFeedClassName='" + this.j + "'}";
    }

    public GMCustomInitConfig(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.f4383c = str;
        this.f4381a = str2;
        this.f4382b = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
        this.h = str8;
        this.i = str9;
        this.j = str10;
        this.k = str11;
    }
}
