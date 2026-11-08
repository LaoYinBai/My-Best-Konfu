package com.ss.union.game.sdk.ad.ad_mediation.c;

import android.app.Activity;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.v2.ad.splash.GMSplashAd;
import com.bytedance.msdk.api.v2.ad.splash.GMSplashAdListener;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdSplashAd;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e implements LGMediationAdSplashAd {
    public static final String a = "LGMediationAdSplashAdImpl";
    private GMSplashAd b;
    private LGMediationAdSplashAd.InteractionCallback c;
    private FrameLayout d;
    private Activity e;
    private boolean f = false;

    public e(GMSplashAd gMSplashAd) {
        this.b = gMSplashAd;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdSplashAd
    public void showAd(final Activity activity) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.e.1
                @Override // java.lang.Runnable
                public void run() {
                    e.this.showAd(activity);
                }
            });
            return;
        }
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showSplashVideoAd() start");
        if (activity == null) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showSplashVideoAd() activity = null");
            return;
        }
        if (this.d != null) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showSplashVideoAd() mContainerView != null");
            return;
        }
        this.e = activity;
        this.b.setAdSplashListener(new GMSplashAdListener() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.e.2
            public void onAdClicked() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showSplashVideoAd() onAdClicked");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.e.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (e.this.c != null) {
                            e.this.c.onAdClicked();
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("RewardVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onAdShow() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showSplashVideoAd() onAdShow");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.e.2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (e.this.c != null) {
                            e.this.c.onAdShow();
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("RewardVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onAdShowFail(@NonNull final AdError adError) {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showSplashVideoAd() onAdShowFail code = " + adError.code + "---message = " + adError.message);
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.e.2.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (e.this.c != null) {
                            e.this.c.onAdShowFail(adError.code, adError.message);
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("RewardVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onAdSkip() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showSplashVideoAd() onAdSkip");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.e.2.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (e.this.c != null) {
                            e.this.c.onAdSkip();
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("RewardVideoAd InteractionCallback is null");
                        }
                        e.this.destroy();
                    }
                });
            }

            public void onAdDismiss() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showSplashVideoAd() onAdDismiss");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.e.2.5
                    @Override // java.lang.Runnable
                    public void run() {
                        e.this.a();
                    }
                });
            }
        });
        this.d = new FrameLayout(activity);
        this.d.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.format = -3;
        layoutParams.type = 2;
        layoutParams.flags = com.ss.union.game.sdk.feedback.picture.select.d.d;
        layoutParams.width = -1;
        layoutParams.height = -1;
        try {
            activity.getWindowManager().addView(this.d, layoutParams);
            this.d.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.e.3
                @Override // java.lang.Runnable
                public void run() {
                    e.this.b.showAd(e.this.d);
                }
            });
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showSplashVideoAd() addView success");
        } catch (Throwable th) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showSplashVideoAd() addView error " + th.getMessage());
            a();
        }
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdSplashAd
    public void setInteractionCallback(LGMediationAdSplashAd.InteractionCallback interactionCallback) {
        this.c = interactionCallback;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdSplashAd
    public void destroy() {
        try {
            if (this.b != null) {
                this.b.destroy();
            }
        } catch (Throwable unused) {
        }
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.f) {
            return;
        }
        this.f = true;
        try {
            if (this.d != null) {
                this.d.removeAllViews();
                this.e.getWindowManager().removeView(this.d);
            }
        } catch (Throwable unused) {
        }
        if (this.c != null) {
            this.c.onAdDismiss();
        }
    }
}
