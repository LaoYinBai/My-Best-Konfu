package com.baidu.mobads.sdk.api;

import android.content.Context;
import com.baidu.mobads.sdk.internal.cp;
import com.baidu.mobads.sdk.internal.cx;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class FullScreenVideoAd {
    private static final String TAG = "FullScreenVideoAd";
    private cx mAdProd;
    private final Context mContext;

    public interface FullScreenVideoAdListener extends ScreenVideoAdListener {
        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void onAdClick();

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void onAdClose(float f);

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void onAdFailed(String str);

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void onAdShow();

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void onAdSkip(float f);

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void onVideoDownloadFailed();

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void onVideoDownloadSuccess();

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void playCompletion();
    }

    public FullScreenVideoAd(Context context, String str, FullScreenVideoAdListener fullScreenVideoAdListener) {
        this(context, str, fullScreenVideoAdListener, false);
    }

    public FullScreenVideoAd(Context context, String str, FullScreenVideoAdListener fullScreenVideoAdListener, boolean z) {
        this.mContext = context;
        this.mAdProd = new cp(this.mContext, str, z);
        this.mAdProd.a(fullScreenVideoAdListener);
    }

    public synchronized void load() {
        if (this.mAdProd != null) {
            this.mAdProd.b_();
        }
    }

    public String getBiddingToken() {
        if (this.mAdProd != null) {
            return this.mAdProd.m();
        }
        return null;
    }

    public void setBiddingData(String str) {
        if (this.mAdProd != null) {
            this.mAdProd.b(str);
        }
    }

    public synchronized void show() {
        if (this.mAdProd != null) {
            this.mAdProd.f();
        }
    }

    public boolean isReady() {
        if (this.mAdProd != null) {
            return this.mAdProd.g();
        }
        return false;
    }

    public void setAppSid(String str) {
        if (this.mAdProd != null) {
            this.mAdProd.f(str);
        }
    }

    public String getECPMLevel() {
        return this.mAdProd != null ? this.mAdProd.h() : "";
    }

    public void biddingSuccess(String str) {
        if (this.mAdProd != null) {
            this.mAdProd.a(true, str);
        }
    }

    public void biddingFail(String str) {
        if (this.mAdProd != null) {
            this.mAdProd.a(false, str);
        }
    }
}
