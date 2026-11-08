package com.baidu.mobads.sdk.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.KeyEvent;
import com.baidu.mobads.sdk.internal.a;
import com.baidu.mobads.sdk.internal.bz;
import com.baidu.mobads.sdk.internal.cm;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ExpressInterstitialAd {
    private boolean adReady;
    private String mAdPlaceId;
    private Context mContext;
    private ExpressInterstitialListener mExpressInterstitialListener;
    private int mHeight;
    private InterAdDownloadWindowListener mInterAdDownloadWindowListener;
    private cm mNativeInterstitialAdProd;
    private boolean mUseDialogFrame;
    private int mWidth;
    private boolean onlyFetchAd;

    public interface InterAdDownloadWindowListener {
        void adDownloadWindowClose();

        void adDownloadWindowShow();

        void onADPermissionClose();

        void onADPermissionShow();

        void onADPrivacyClick();

        void onADPrivacyClose();
    }

    public ExpressInterstitialAd(Context context, String str) {
        this(context, str, 500, 600);
    }

    public ExpressInterstitialAd(Context context, String str, int i, int i2) {
        this.mUseDialogFrame = false;
        this.mContext = context;
        this.mAdPlaceId = str;
        this.mWidth = i;
        this.mHeight = i2;
    }

    public void setDialogFrame(boolean z) {
        this.mUseDialogFrame = z;
    }

    public void setDownloadListener(InterAdDownloadWindowListener interAdDownloadWindowListener) {
        this.mInterAdDownloadWindowListener = interAdDownloadWindowListener;
    }

    public void setLoadListener(ExpressInterstitialListener expressInterstitialListener) {
        this.mExpressInterstitialListener = expressInterstitialListener;
    }

    public void load() {
        this.onlyFetchAd = true;
        this.adReady = true;
        reallyLoad();
    }

    public void show() {
        if (this.mNativeInterstitialAdProd == null) {
            return;
        }
        if (this.adReady) {
            this.mNativeInterstitialAdProd.g();
        } else {
            this.mNativeInterstitialAdProd.a("上一次已经show过,请重新load", 0);
        }
        this.adReady = false;
    }

    private void reallyLoad() {
        bz bzVar = new bz(this.mContext);
        bzVar.a(new bz.a() { // from class: com.baidu.mobads.sdk.api.ExpressInterstitialAd.1
            @Override // com.baidu.mobads.sdk.internal.bz.a
            public void onAttachedToWindow() {
            }

            @Override // com.baidu.mobads.sdk.internal.bz.a
            public boolean onKeyDown(int i, KeyEvent keyEvent) {
                return i == 4;
            }

            @Override // com.baidu.mobads.sdk.internal.bz.a
            public void onLayoutComplete(int i, int i2) {
            }

            @Override // com.baidu.mobads.sdk.internal.bz.a
            public void onWindowFocusChanged(boolean z) {
            }

            @Override // com.baidu.mobads.sdk.internal.bz.a
            public void onWindowVisibilityChanged(int i) {
            }

            @Override // com.baidu.mobads.sdk.internal.bz.a
            @SuppressLint({"MissingSuperCall"})
            public void onDetachedFromWindow() {
                if (ExpressInterstitialAd.this.mNativeInterstitialAdProd != null) {
                    ExpressInterstitialAd.this.mNativeInterstitialAdProd.p();
                }
            }
        });
        this.mNativeInterstitialAdProd = new cm(this.mContext, bzVar, this.mAdPlaceId);
        this.mNativeInterstitialAdProd.p = this.onlyFetchAd;
        this.mNativeInterstitialAdProd.a(this.mExpressInterstitialListener);
        this.mNativeInterstitialAdProd.a(this.mInterAdDownloadWindowListener);
        this.mNativeInterstitialAdProd.c(this.mUseDialogFrame);
        this.mNativeInterstitialAdProd.b_();
    }

    public String getECPMLevel() {
        a aVarH;
        return (this.mNativeInterstitialAdProd == null || (aVarH = this.mNativeInterstitialAdProd.h()) == null) ? "" : aVarH.y();
    }

    public boolean isReady() {
        if (this.mNativeInterstitialAdProd == null || this.mNativeInterstitialAdProd.j == null || !this.adReady) {
            return false;
        }
        return this.mNativeInterstitialAdProd.j.isAdReady();
    }

    public void biddingSuccess(String str) {
        if (this.mNativeInterstitialAdProd != null) {
            this.mNativeInterstitialAdProd.a(true, str);
        }
    }

    public void biddingFail(String str) {
        if (this.mNativeInterstitialAdProd != null) {
            this.mNativeInterstitialAdProd.a(false, str);
        }
    }
}
