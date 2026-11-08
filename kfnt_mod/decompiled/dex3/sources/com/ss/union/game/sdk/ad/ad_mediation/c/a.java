package com.ss.union.game.sdk.ad.ad_mediation.c;

import android.R;
import android.app.Activity;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.v2.ad.banner.GMBannerAd;
import com.bytedance.msdk.api.v2.ad.banner.GMBannerAdListener;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdNativeBannerAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdBannerAd;
import com.ss.union.game.sdk.ad.ad_mediation.views.BannerContainerView;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements LGMediationAdBannerAd {
    private GMBannerAd a;
    private LGMediationAdNativeBannerAdDTO b;
    private BannerContainerView c;
    private ViewGroup d;

    public a(GMBannerAd gMBannerAd, LGMediationAdNativeBannerAdDTO lGMediationAdNativeBannerAdDTO) {
        this.a = gMBannerAd;
        this.b = lGMediationAdNativeBannerAdDTO;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdBannerAd
    public void show(final Activity activity, final int i, final int i2) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.a.1
                @Override // java.lang.Runnable
                public void run() {
                    a.this.show(activity, i, i2);
                }
            });
            return;
        }
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd activity" + i + " " + i2);
        if (activity == null) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd activity = null");
            return;
        }
        if (activity.getWindow() == null || activity.getWindow().getDecorView() == null) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd window = null");
        } else if (activity.getWindow().getDecorView() == null) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd decorView = null");
        } else {
            a((ViewGroup) activity.getWindow().getDecorView().findViewById(R.id.content), i, i2);
        }
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdBannerAd
    public void show(final ViewGroup viewGroup) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.a.2
                @Override // java.lang.Runnable
                public void run() {
                    a.this.show(viewGroup);
                }
            });
        } else {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd parent");
            a(viewGroup, 0, 0);
        }
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdBannerAd
    public void remove() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.a.3
                @Override // java.lang.Runnable
                public void run() {
                    a.this.remove();
                }
            });
            return;
        }
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("remove BannerAd");
        if (this.d == null) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("remove BannerAd mParent is null");
            return;
        }
        try {
            if (this.c != null) {
                this.c.removeAllViews();
            }
            try {
                this.a.destroy();
            } catch (Throwable unused) {
            }
            this.d.removeView(this.c);
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("remove BannerAd success");
        } catch (Throwable th) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("remove BannerAd error " + th.getMessage());
        }
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdBannerAd
    public void setInteractionCallback(final LGMediationAdBannerAd.InteractionCallback interactionCallback) {
        this.a.setAdBannerListener(new GMBannerAdListener() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.a.4
            public void onAdOpened() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd onAdOpened");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.a.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        interactionCallback.onAdOpened();
                    }
                });
            }

            public void onAdLeftApplication() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd onAdLeftApplication");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.a.4.2
                    @Override // java.lang.Runnable
                    public void run() {
                        interactionCallback.onAdLeftApplication();
                    }
                });
            }

            public void onAdClosed() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd onAdClosed");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.a.4.3
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.remove();
                        interactionCallback.onAdClosed();
                    }
                });
            }

            public void onAdClicked() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd onAdClicked");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.a.4.4
                    @Override // java.lang.Runnable
                    public void run() {
                        interactionCallback.onAdClicked();
                    }
                });
            }

            public void onAdShow() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd onAdShow");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.a.4.5
                    @Override // java.lang.Runnable
                    public void run() {
                        interactionCallback.onAdShow();
                    }
                });
            }

            public void onAdShowFail(final AdError adError) {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd onAdShowFail code = " + adError.code + "---message = " + adError.message);
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.a.4.6
                    @Override // java.lang.Runnable
                    public void run() {
                        interactionCallback.onAdShowFail(adError.code, adError.message);
                    }
                });
            }
        });
    }

    private void a(ViewGroup viewGroup, int i, int i2) {
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd inner");
        if (viewGroup == null) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd parent = null");
            return;
        }
        if (viewGroup.getContext() == null) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd parent.getContext() = null");
            return;
        }
        this.d = viewGroup;
        View bannerView = this.a.getBannerView();
        if (bannerView == null) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd banner = null");
            return;
        }
        if (bannerView.getParent() != null) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd had parent");
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        bannerView.setLayoutParams(layoutParams);
        this.c = new BannerContainerView(viewGroup.getContext()).a(i, i2);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(this.b.expectedImageSize.width, this.b.expectedImageSize.height);
        marginLayoutParams.topMargin = i2;
        marginLayoutParams.leftMargin = i;
        this.c.setLayoutParams(marginLayoutParams);
        this.c.addView(bannerView);
        viewGroup.addView(this.c);
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("show BannerAd addView success");
    }
}
