package com.ss.union.game.sdk.ad.ad_mediation.c;

import android.app.Activity;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.reward.RewardItem;
import com.bytedance.msdk.api.v2.ad.reward.GMRewardAd;
import com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdListener;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c implements LGMediationAdRewardVideoAd {
    private GMRewardAd a;
    private LGMediationAdRewardVideoAd.InteractionCallback b;

    c(GMRewardAd gMRewardAd) {
        this.a = gMRewardAd;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd
    public void setInteractionCallback(LGMediationAdRewardVideoAd.InteractionCallback interactionCallback) {
        this.b = interactionCallback;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd
    public boolean isReady() {
        return this.a.isReady();
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd
    public void destroy() {
        this.a.destroy();
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd
    @MainThread
    public void showRewardVideoAd(final Activity activity) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.c.1
                @Override // java.lang.Runnable
                public void run() {
                    c.this.showRewardVideoAd(activity);
                }
            });
            return;
        }
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showRewardVideoAd() start");
        a(this.a, this.b);
        this.a.showRewardAd(activity);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd
    public void showRewardVideoAd(final Activity activity, final TTAdConstant.GroMoreRitScenes groMoreRitScenes, final String str) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.c.2
                @Override // java.lang.Runnable
                public void run() {
                    c.this.showRewardVideoAd(activity, groMoreRitScenes, str);
                }
            });
            return;
        }
        if (groMoreRitScenes == null) {
            showRewardVideoAd(activity);
        }
        HashMap map = new HashMap();
        map.put(TTAdConstant.GroMoreExtraKey.RIT_SCENES, groMoreRitScenes);
        map.put(TTAdConstant.GroMoreExtraKey.CUSTOMIZE_RIT_SCENES, str);
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showRewardVideoAd() with scenes start");
        a(this.a, this.b);
        this.a.showRewardAd(activity);
    }

    private void a(GMRewardAd gMRewardAd, final LGMediationAdRewardVideoAd.InteractionCallback interactionCallback) {
        gMRewardAd.setRewardAdListener(new GMRewardedAdListener() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.c.3
            public void onRewardedAdShow() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showRewardVideoAd() show");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.c.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (interactionCallback != null) {
                            interactionCallback.onRewardedAdShow();
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("RewardVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onRewardedAdShowFail(@NonNull final AdError adError) {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showRewardVideoAd() onRewardedAdShowFail code = " + adError.code + "--message = " + adError.message);
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.c.3.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (interactionCallback != null) {
                            interactionCallback.onRewardedAdShowFail(adError.code, adError.message);
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("RewardVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onRewardClick() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showRewardVideoAd() click");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.c.3.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (interactionCallback != null) {
                            interactionCallback.onRewardClick();
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("RewardVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onRewardedAdClosed() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showRewardVideoAd() close");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.c.3.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (interactionCallback != null) {
                            interactionCallback.onRewardedAdClosed();
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("RewardVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onVideoComplete() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showRewardVideoAd() complete");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.c.3.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (interactionCallback != null) {
                            interactionCallback.onVideoComplete();
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("RewardVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onVideoError() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showRewardVideoAd() error");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.c.3.6
                    @Override // java.lang.Runnable
                    public void run() {
                        if (interactionCallback != null) {
                            interactionCallback.onVideoError();
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("RewardVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onRewardVerify(@NonNull RewardItem rewardItem) {
                final boolean zRewardVerify = rewardItem.rewardVerify();
                final float amount = rewardItem.getAmount();
                final String rewardName = TextUtils.isEmpty(rewardItem.getRewardName()) ? "" : rewardItem.getRewardName();
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showRewardVideoAd() reward verify rewardVerify= " + zRewardVerify + " amount= " + amount + " rewardName= " + rewardName);
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.c.3.7
                    @Override // java.lang.Runnable
                    public void run() {
                        if (interactionCallback != null) {
                            interactionCallback.onRewardVerify(zRewardVerify, amount, rewardName);
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("RewardVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onSkippedVideo() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showRewardVideoAd() skip");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.c.3.8
                    @Override // java.lang.Runnable
                    public void run() {
                        if (interactionCallback != null) {
                            interactionCallback.onSkippedVideo();
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("RewardVideoAd InteractionCallback is null");
                        }
                    }
                });
            }
        });
    }
}
