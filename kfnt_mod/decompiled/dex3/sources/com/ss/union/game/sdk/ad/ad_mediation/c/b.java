package com.ss.union.game.sdk.ad.ad_mediation.c;

import android.app.Activity;
import android.os.Looper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAd;
import com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b implements LGMediationAdFullScreenVideoAd {
    private GMFullVideoAd a;
    private LGMediationAdFullScreenVideoAd.InteractionCallback b;

    b(GMFullVideoAd gMFullVideoAd) {
        this.a = gMFullVideoAd;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd
    public void setInteractionCallback(LGMediationAdFullScreenVideoAd.InteractionCallback interactionCallback) {
        this.b = interactionCallback;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd
    public boolean isReady() {
        return this.a.isReady();
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd
    public void destroy() {
        this.a.destroy();
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd
    @MainThread
    public void showFullScreenVideoAd(final Activity activity) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.b.1
                @Override // java.lang.Runnable
                public void run() {
                    b.this.showFullScreenVideoAd(activity);
                }
            });
            return;
        }
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showFullScreenVideoAd() Start");
        a(this.a, this.b);
        this.a.showFullAd(activity);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd
    public void showFullScreenVideoAd(final Activity activity, final TTAdConstant.GroMoreRitScenes groMoreRitScenes, final String str) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.b.2
                @Override // java.lang.Runnable
                public void run() {
                    b.this.showFullScreenVideoAd(activity, groMoreRitScenes, str);
                }
            });
            return;
        }
        if (groMoreRitScenes == null) {
            showFullScreenVideoAd(activity);
        }
        HashMap map = new HashMap();
        map.put(TTAdConstant.GroMoreExtraKey.RIT_SCENES, groMoreRitScenes);
        map.put(TTAdConstant.GroMoreExtraKey.CUSTOMIZE_RIT_SCENES, str);
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showFullScreenVideoAd() with scenes  Start");
        a(this.a, this.b);
        this.a.showFullAd(activity, map);
    }

    private void a(GMFullVideoAd gMFullVideoAd, final LGMediationAdFullScreenVideoAd.InteractionCallback interactionCallback) {
        gMFullVideoAd.setFullVideoAdListener(new GMFullVideoAdListener() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.b.3
            public void onFullVideoAdShow() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showFullScreenVideoAd() show");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.b.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (interactionCallback != null) {
                            interactionCallback.onFullVideoAdShow();
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("FullScreenVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onFullVideoAdShowFail(@NonNull final AdError adError) {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showFullScreenVideoAd() onFullVideoAdShowFail code = " + adError.code + "--message = " + adError.message);
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.b.3.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (interactionCallback != null) {
                            interactionCallback.onFullVideoAdShowFail(adError.code, adError.message);
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("FullScreenVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onFullVideoAdClick() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showFullScreenVideoAd() click");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.b.3.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (interactionCallback != null) {
                            interactionCallback.onFullVideoAdClick();
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("FullScreenVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onFullVideoAdClosed() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showFullScreenVideoAd() close");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.b.3.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (interactionCallback != null) {
                            interactionCallback.onFullVideoAdClosed();
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("FullScreenVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onVideoComplete() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showFullScreenVideoAd() complete");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.b.3.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (interactionCallback != null) {
                            interactionCallback.onVideoComplete();
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("FullScreenVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onVideoError() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showFullScreenVideoAd() error");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.b.3.6
                    @Override // java.lang.Runnable
                    public void run() {
                        if (interactionCallback != null) {
                            interactionCallback.onVideoError();
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("FullScreenVideoAd InteractionCallback is null");
                        }
                    }
                });
            }

            public void onSkippedVideo() {
                com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showFullScreenVideoAd() skip");
                MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.b.3.7
                    @Override // java.lang.Runnable
                    public void run() {
                        if (interactionCallback != null) {
                            interactionCallback.onSkippedVideo();
                        } else {
                            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("FullScreenVideoAd InteractionCallback is null");
                        }
                    }
                });
            }
        });
    }
}
