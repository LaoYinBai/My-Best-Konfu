package com.baidu.mobads.sdk.api;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.internal.bb;
import com.baidu.mobads.sdk.internal.bz;
import com.baidu.mobads.sdk.internal.cv;
import com.baidu.mobads.sdk.internal.t;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class InterstitialAd {
    public static final String TAG = "InterstitialAd";
    private boolean initialized;
    private InterstitialAdListener listener;
    private final bb mAdLogger;
    private t mXInterstitial;

    public InterstitialAd(Context context, String str) {
        this(context, AdSize.InterstitialGame, str);
    }

    public InterstitialAd(Context context, AdSize adSize, String str) {
        this.initialized = false;
        this.mAdLogger = bb.a();
        this.listener = new InterstitialAdListener() { // from class: com.baidu.mobads.sdk.api.InterstitialAd.1
            @Override // com.baidu.mobads.sdk.api.InterstitialAdListener
            public void onAdClick(InterstitialAd interstitialAd) {
            }

            @Override // com.baidu.mobads.sdk.api.InterstitialAdListener
            public void onAdDismissed() {
            }

            @Override // com.baidu.mobads.sdk.api.InterstitialAdListener
            public void onAdFailed(String str2) {
            }

            @Override // com.baidu.mobads.sdk.api.InterstitialAdListener
            public void onAdPresent() {
            }

            @Override // com.baidu.mobads.sdk.api.InterstitialAdListener
            public void onAdReady() {
            }
        };
        bz bzVar = new bz(context);
        bzVar.a(new bz.a() { // from class: com.baidu.mobads.sdk.api.InterstitialAd.2
            @Override // com.baidu.mobads.sdk.internal.bz.a
            public void onAttachedToWindow() {
            }

            @Override // com.baidu.mobads.sdk.internal.bz.a
            @SuppressLint({"MissingSuperCall"})
            public void onDetachedFromWindow() {
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
            public boolean onKeyDown(int i, KeyEvent keyEvent) {
                return InterstitialAd.this.mXInterstitial.a(i, keyEvent);
            }
        });
        if (isInterstitial(adSize)) {
            this.mXInterstitial = new cv(context, bzVar, this, str);
        } else if (isInterstitialPause(adSize)) {
            this.mXInterstitial = new cv(context, bzVar, this, adSize, str);
        }
        this.mXInterstitial.a(this.listener);
    }

    private boolean isInterstitial(AdSize adSize) {
        return adSize.getValue() <= AdSize.InterstitialOther.getValue() && adSize.getValue() >= AdSize.InterstitialGame.getValue();
    }

    private boolean isInterstitialPause(AdSize adSize) {
        return adSize.getValue() >= AdSize.InterstitialForVideoBeforePlay.getValue() && adSize.getValue() <= AdSize.InterstitialForVideoPausePlay.getValue();
    }

    public boolean isAdReady() {
        return this.mXInterstitial.b();
    }

    public void loadAd() {
        if (!this.initialized) {
            this.mXInterstitial.a_();
            this.initialized = true;
        }
        this.mXInterstitial.a();
    }

    public void loadAdForVideoApp(int i, int i2) {
        if (!this.initialized) {
            this.mXInterstitial.a_();
            this.initialized = true;
        }
        this.mXInterstitial.a(i, i2);
    }

    public void setListener(InterstitialAdListener interstitialAdListener) {
        if (interstitialAdListener == null) {
            throw new IllegalArgumentException();
        }
        this.listener = interstitialAdListener;
        this.mXInterstitial.a(interstitialAdListener);
    }

    public void showAd() {
        this.mXInterstitial.c_();
    }

    @Deprecated
    public void showAd(Activity activity) {
        this.mXInterstitial.c_();
    }

    public void setAppSid(String str) {
        this.mXInterstitial.a(str);
    }

    public void destroy() {
        this.mXInterstitial.d();
    }

    public void showAdInParentForVideoApp(RelativeLayout relativeLayout) {
        this.mXInterstitial.a(relativeLayout);
    }

    @Deprecated
    public void showAdInParentForVideoApp(Activity activity, RelativeLayout relativeLayout) {
        if (activity == null || relativeLayout == null) {
            throw new IllegalArgumentException();
        }
        this.mXInterstitial.a(relativeLayout);
    }
}
