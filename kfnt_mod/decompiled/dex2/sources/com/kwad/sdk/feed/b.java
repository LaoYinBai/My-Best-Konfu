package com.kwad.sdk.feed;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.api.KsAdVideoPlayConfig;
import com.kwad.sdk.api.KsFeedAd;
import com.kwad.sdk.api.core.AbstractKsFeedAd;
import com.kwad.sdk.api.model.AdExposureFailedReason;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.feed.widget.base.a;
import com.kwad.sdk.feed.widget.l;
import com.kwad.sdk.utils.as;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends AbstractKsFeedAd {
    private KsFeedAd.AdInteractionListener a;
    private com.kwad.sdk.feed.widget.base.a b;
    private com.kwad.sdk.feed.widget.base.a c;
    private boolean d;
    private int e;

    @NonNull
    private AdTemplate f;
    private AdInfo g;
    private KsAdVideoPlayConfig h;

    public static class a {
        int a;
        int b;

        public a(int i) {
            this.a = i;
        }

        public boolean a() {
            this.b++;
            return this.b == this.a;
        }
    }

    /* renamed from: com.kwad.sdk.feed.b$b, reason: collision with other inner class name */
    public interface InterfaceC0061b {
        void a();
    }

    public b(@NonNull AdTemplate adTemplate) {
        this(adTemplate, 0, false);
    }

    public b(@NonNull AdTemplate adTemplate, int i, boolean z) {
        this.h = new KsAdVideoPlayConfig.Builder().videoSoundEnable(false).dataFlowAutoStart(com.kwad.sdk.core.config.b.o()).build();
        this.f = adTemplate;
        this.f.mInitVoiceStatus = 1;
        this.g = d.j(adTemplate);
        this.d = z;
        this.e = i;
    }

    @Nullable
    private com.kwad.sdk.feed.widget.base.a a(Context context) {
        if (!this.d || !com.kwad.sdk.core.response.a.b.m(this.f)) {
            return com.kwad.sdk.feed.a.a(context, FeedType.fromInt(this.f.type), com.kwad.sdk.core.response.a.a.R(this.g));
        }
        l lVar = new l(context);
        lVar.setVideoPlayConfig(this.h);
        return lVar;
    }

    private void a() {
        if (this.f != null) {
            com.kwad.sdk.core.diskcache.a.a.a().d("feed_ad_cache_" + this.f.posId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final com.kwad.sdk.feed.widget.base.a aVar) {
        if (aVar == null) {
            return;
        }
        aVar.setInnerAdInteractionListener(new a.InterfaceC0062a() { // from class: com.kwad.sdk.feed.b.2
            @Override // com.kwad.sdk.feed.widget.base.a.InterfaceC0062a
            public void a() {
                if (b.this.a != null) {
                    b.this.a.onAdClicked();
                }
            }

            @Override // com.kwad.sdk.feed.widget.base.a.InterfaceC0062a
            public void b() {
                if (b.this.a != null) {
                    b.this.a.onAdShow();
                }
            }

            @Override // com.kwad.sdk.feed.widget.base.a.InterfaceC0062a
            public void c() {
                if (b.this.a != null) {
                    b.this.a.onDislikeClicked();
                    try {
                        if (aVar.getParent() instanceof ViewGroup) {
                            ((ViewGroup) aVar.getParent()).removeView(aVar);
                        }
                    } catch (Exception e) {
                        com.kwad.sdk.core.d.a.a(e);
                    }
                }
            }

            @Override // com.kwad.sdk.feed.widget.base.a.InterfaceC0062a
            public void d() {
                if (b.this.a != null) {
                    try {
                        b.this.a.onDownloadTipsDialogShow();
                    } catch (Throwable unused) {
                    }
                }
            }

            @Override // com.kwad.sdk.feed.widget.base.a.InterfaceC0062a
            public void e() {
                if (b.this.a != null) {
                    try {
                        b.this.a.onDownloadTipsDialogDismiss();
                    } catch (Throwable unused) {
                    }
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.kwad.sdk.feed.widget.base.a a(final com.kwad.sdk.feed.b.InterfaceC0061b r4) {
        /*
            r3 = this;
            com.kwad.sdk.KsAdSDKImpl r0 = com.kwad.sdk.KsAdSDKImpl.get()
            android.content.Context r0 = r0.getContext()
            if (r0 != 0) goto Lf
            r4.a()
            r4 = 0
            return r4
        Lf:
            com.kwad.sdk.feed.widget.base.a r1 = r3.a(r0)
            r3.c = r1
            com.kwad.sdk.feed.widget.base.a r1 = r3.c
            if (r1 == 0) goto L60
            r1 = 1098907648(0x41800000, float:16.0)
            int r0 = com.kwad.sdk.a.kwai.a.a(r0, r1)
            com.kwad.sdk.feed.widget.base.a r1 = r3.c
            r1.setMargin(r0)
            com.kwad.sdk.feed.widget.base.a r0 = r3.c
            boolean r0 = r0 instanceof com.kwad.sdk.feed.widget.l
            if (r0 == 0) goto L42
            com.kwad.sdk.feed.widget.base.a r0 = r3.c
            com.kwad.sdk.feed.widget.l r0 = (com.kwad.sdk.feed.widget.l) r0
            int r1 = r3.e
            if (r1 <= 0) goto L37
            int r1 = r3.e
            r0.setWidth(r1)
        L37:
            com.kwad.sdk.core.response.model.AdTemplate r1 = r3.f
            com.kwad.sdk.feed.b$1 r2 = new com.kwad.sdk.feed.b$1
            r2.<init>()
            r0.a(r1, r2)
            goto L65
        L42:
            com.kwad.sdk.feed.widget.base.a r0 = r3.c
            com.kwad.sdk.core.response.model.AdTemplate r1 = r3.f
            r0.a(r1)
            com.kwad.sdk.feed.widget.base.a r0 = r3.c
            boolean r0 = r0 instanceof com.kwad.sdk.feed.widget.a
            if (r0 == 0) goto L58
            com.kwad.sdk.feed.widget.base.a r0 = r3.c
            com.kwad.sdk.feed.widget.a r0 = (com.kwad.sdk.feed.widget.a) r0
            com.kwad.sdk.api.KsAdVideoPlayConfig r1 = r3.h
            r0.a(r1)
        L58:
            com.kwad.sdk.feed.widget.base.a r0 = r3.c
            r3.a(r0)
            if (r4 == 0) goto L65
            goto L62
        L60:
            if (r4 == 0) goto L65
        L62:
            r4.a()
        L65:
            com.kwad.sdk.feed.widget.base.a r4 = r3.c
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.feed.b.a(com.kwad.sdk.feed.b$b):com.kwad.sdk.feed.widget.base.a");
    }

    @Override // com.kwad.sdk.api.KsFeedAd
    public int getECPM() {
        return com.kwad.sdk.core.response.a.a.F(this.g);
    }

    @Override // com.kwad.sdk.api.core.AbstractKsFeedAd
    public View getFeedView2(Context context) {
        if (this.b == null) {
            a();
            if (this.c != null) {
                this.b = this.c;
                return this.b;
            }
            if (context == null) {
                return null;
            }
            this.b = a(context);
        } else if (this.b.getParent() instanceof ViewGroup) {
            ((ViewGroup) this.b.getParent()).removeView(this.b);
        }
        if (this.b != null) {
            this.b.a(this.f);
            if (this.b instanceof com.kwad.sdk.feed.widget.a) {
                ((com.kwad.sdk.feed.widget.a) this.b).a(this.h);
            }
            a(this.b);
        }
        return this.b;
    }

    @Override // com.kwad.sdk.api.KsFeedAd
    public int getInteractionType() {
        return com.kwad.sdk.core.response.a.a.E(this.g);
    }

    @Override // com.kwad.sdk.api.KsFeedAd
    public int getMaterialType() {
        return com.kwad.sdk.core.response.a.a.R(this.g);
    }

    @Override // com.kwad.sdk.api.KsFeedAd
    public void reportAdExposureFailed(int i, AdExposureFailedReason adExposureFailedReason) {
        com.kwad.sdk.core.report.a.a(this.f, i, adExposureFailedReason);
    }

    @Override // com.kwad.sdk.api.KsFeedAd
    public void setAdInteractionListener(KsFeedAd.AdInteractionListener adInteractionListener) {
        this.a = adInteractionListener;
    }

    @Override // com.kwad.sdk.api.KsFeedAd
    public void setBidEcpm(int i) {
        this.f.mBidEcpm = i;
        com.kwad.sdk.core.report.a.m(this.f);
    }

    @Override // com.kwad.sdk.api.KsFeedAd
    public void setVideoPlayConfig(@Nullable KsAdVideoPlayConfig ksAdVideoPlayConfig) {
        AdTemplate adTemplate;
        int i;
        if (ksAdVideoPlayConfig != null) {
            this.h.setDataFlowAutoStart(ksAdVideoPlayConfig.isDataFlowAutoStart());
            this.h.setVideoSoundEnable(ksAdVideoPlayConfig.isVideoSoundEnable());
            if (this.c instanceof l) {
                ((l) this.c).setVideoPlayConfig(ksAdVideoPlayConfig);
            }
            if (this.h.isVideoSoundEnable()) {
                adTemplate = this.f;
                i = 2;
            } else {
                adTemplate = this.f;
                i = 1;
            }
            adTemplate.mInitVoiceStatus = i;
            if (this.f.mAdScene == null || KsAdSDKImpl.get().getContext() == null) {
                return;
            }
            as.a(KsAdSDKImpl.get().getContext(), String.valueOf(this.f.mAdScene.posId), ksAdVideoPlayConfig.isDataFlowAutoStart() ? 1 : 0);
        }
    }

    @Override // com.kwad.sdk.api.KsFeedAd
    public void setVideoSoundEnable(boolean z) {
        AdTemplate adTemplate;
        int i;
        this.h.setVideoSoundEnable(z);
        if (this.h.isVideoSoundEnable()) {
            adTemplate = this.f;
            i = 2;
        } else {
            adTemplate = this.f;
            i = 1;
        }
        adTemplate.mInitVoiceStatus = i;
    }
}
