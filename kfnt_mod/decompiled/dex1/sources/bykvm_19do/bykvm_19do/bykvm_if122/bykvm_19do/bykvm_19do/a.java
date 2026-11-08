package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.bytedance.msdk.adapter.listener.ITTAdapterFullVideoAdListener;
import com.bytedance.msdk.adapter.listener.ITTAdapterInterstitialListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.v2.ad.interstitialFull.GMInterstitialFullAdListener;
import com.bytedance.msdk.api.v2.ad.interstitialFull.GMInterstitialFullAdLoadCallback;
import com.bytedance.msdk.api.v2.slot.GMAdSlotInterstitialFull;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore;
import com.bytedance.msdk.core.corelogic.e;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends com.bytedance.msdk.core.corelogic.e {
    private GMInterstitialFullAdLoadCallback W;
    private GMInterstitialFullAdListener X;
    ITTAdapterFullVideoAdListener Y;
    ITTAdapterInterstitialListener Z;

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.a$a, reason: collision with other inner class name */
    class C0057a implements e.c {
        C0057a() {
        }

        @Override // com.bytedance.msdk.core.corelogic.e.c
        public void a() {
            a.this.b(new AdError(AdError.ERROR_CODE_SHOW_FAIL_NO_AD, AdError.getMessage(AdError.ERROR_CODE_SHOW_FAIL_NO_AD)));
        }
    }

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AdError f1773a;

        b(AdError adError) {
            this.f1773a = adError;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.X != null) {
                a.this.X.onInterstitialFullShowFail(this.f1773a);
            }
        }
    }

    class c implements ITTAdapterFullVideoAdListener {
        c() {
        }

        @Override // com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener
        public void onFullVideoAdClick() {
            if (a.this.X != null) {
                a.this.X.onInterstitialFullClick();
            }
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.c(((TTAdHeaderBidingRequestCore) a.this).I, ((com.bytedance.msdk.core.corelogic.d) a.this).h);
        }

        @Override // com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener
        public void onFullVideoAdClosed() {
            a.this.L();
            if (a.this.X != null) {
                a.this.X.onInterstitialFullClosed();
            }
        }

        @Override // com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener
        public void onFullVideoAdShow() {
            if (a.this.X != null) {
                a.this.X.onInterstitialFullShow();
            }
            if (((TTAdHeaderBidingRequestCore) a.this).I != null) {
                Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(((com.bytedance.msdk.core.corelogic.d) a.this).f, TTLogUtil.TAG_EVENT_SHOW_LISTEN) + "adSlotId：" + ((TTAdHeaderBidingRequestCore) a.this).I.getAdNetworkSlotId() + "，广告类型：" + com.bytedance.msdk.base.a.a(((TTAdHeaderBidingRequestCore) a.this).I.getAdNetworkPlatformId()));
            }
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.e(((TTAdHeaderBidingRequestCore) a.this).I, ((com.bytedance.msdk.core.corelogic.d) a.this).h);
            com.bytedance.msdk.core.corelogic.f.a(((com.bytedance.msdk.core.corelogic.d) a.this).f, ((TTAdHeaderBidingRequestCore) a.this).I);
        }

        @Override // com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener
        public void onFullVideoAdShowFail(@NonNull AdError adError) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(((TTAdHeaderBidingRequestCore) a.this).I, ((com.bytedance.msdk.core.corelogic.d) a.this).h, adError, 1);
            a.this.b(adError);
        }

        @Override // com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener
        public void onSkippedVideo() {
            a.this.L();
            if (a.this.X != null) {
                a.this.X.onSkippedVideo();
            }
        }

        @Override // com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener
        public void onVideoComplete() {
            a.this.L();
            if (a.this.X != null) {
                a.this.X.onVideoComplete();
            }
        }

        @Override // com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener
        public void onVideoError() {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(((TTAdHeaderBidingRequestCore) a.this).I, ((com.bytedance.msdk.core.corelogic.d) a.this).h, (AdError) null, 2);
            if (a.this.X != null) {
                a.this.X.onVideoError();
            }
        }
    }

    class d implements ITTAdapterInterstitialListener {
        d() {
        }

        @Override // com.bytedance.msdk.api.v2.ad.interstitial.GMInterstitialAdListener
        public void onAdLeftApplication() {
            if (a.this.X != null) {
                a.this.X.onAdLeftApplication();
            }
        }

        @Override // com.bytedance.msdk.api.v2.ad.interstitial.GMInterstitialAdListener
        public void onAdOpened() {
            if (a.this.X != null) {
                a.this.X.onAdOpened();
            }
        }

        @Override // com.bytedance.msdk.api.v2.ad.interstitial.GMInterstitialAdListener
        public void onInterstitialAdClick() {
            if (a.this.X != null) {
                a.this.X.onInterstitialFullClick();
            }
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.c(((TTAdHeaderBidingRequestCore) a.this).I, ((com.bytedance.msdk.core.corelogic.d) a.this).h);
        }

        @Override // com.bytedance.msdk.api.v2.ad.interstitial.GMInterstitialAdListener
        public void onInterstitialClosed() {
            a.this.L();
            if (a.this.X != null) {
                a.this.X.onInterstitialFullClosed();
            }
        }

        @Override // com.bytedance.msdk.api.v2.ad.interstitial.GMInterstitialAdListener
        public void onInterstitialShow() {
            if (a.this.X != null) {
                a.this.X.onInterstitialFullShow();
            }
            if (((TTAdHeaderBidingRequestCore) a.this).I != null) {
                Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(((com.bytedance.msdk.core.corelogic.d) a.this).f, TTLogUtil.TAG_EVENT_SHOW_LISTEN) + "adSlotId：" + ((TTAdHeaderBidingRequestCore) a.this).I.getAdNetworkSlotId() + "，广告类型：" + com.bytedance.msdk.base.a.a(((TTAdHeaderBidingRequestCore) a.this).I.getAdNetworkPlatformId()));
            }
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.e(((TTAdHeaderBidingRequestCore) a.this).I, ((com.bytedance.msdk.core.corelogic.d) a.this).h);
            com.bytedance.msdk.core.corelogic.f.a(((com.bytedance.msdk.core.corelogic.d) a.this).f, ((TTAdHeaderBidingRequestCore) a.this).I);
        }

        @Override // com.bytedance.msdk.api.v2.ad.interstitial.GMInterstitialAdListener
        public void onInterstitialShowFail(@NonNull AdError adError) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(((TTAdHeaderBidingRequestCore) a.this).I, ((com.bytedance.msdk.core.corelogic.d) a.this).h, adError, 1);
            a.this.b(adError);
        }
    }

    public a(Context context, String str) {
        super(context, str);
        this.Y = new c();
        this.Z = new d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L() {
        TTBaseAd tTBaseAd = this.I;
        if (tTBaseAd == null || tTBaseAd.getAdNetworkPlatformId() == 4) {
            return;
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a().a(this.E, this.f, e());
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void D() {
        GMInterstitialFullAdLoadCallback gMInterstitialFullAdLoadCallback = this.W;
        if (gMInterstitialFullAdLoadCallback != null) {
            gMInterstitialFullAdLoadCallback.onInterstitialFullAdLoad();
        }
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void E() {
        GMInterstitialFullAdLoadCallback gMInterstitialFullAdLoadCallback = this.W;
        if (gMInterstitialFullAdLoadCallback != null) {
            gMInterstitialFullAdLoadCallback.onInterstitialFullCached();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(AdError adError) {
        ThreadHelper.runOnUiThread(new b(adError));
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    public void x() {
        super.x();
        this.X = null;
        this.W = null;
    }

    public void a(AdSlot adSlot, GMAdSlotInterstitialFull gMAdSlotInterstitialFull, GMInterstitialFullAdLoadCallback gMInterstitialFullAdLoadCallback) {
        if (J()) {
            this.h = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.b.getShallowCopy(adSlot);
            AdSlot adSlot2 = this.h;
            if (adSlot2 != null) {
                adSlot2.setAdType(10);
                this.h.setAdCount(1);
            }
            this.W = gMInterstitialFullAdLoadCallback;
            this.F = this.Y;
            this.y = gMAdSlotInterstitialFull;
            G();
        }
    }

    public void a(Activity activity) throws JSONException {
        super.b(activity, null, new C0057a());
    }

    public void a(GMInterstitialFullAdListener gMInterstitialFullAdListener) {
        this.X = gMInterstitialFullAdListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    public void a(AdError adError) {
        GMInterstitialFullAdLoadCallback gMInterstitialFullAdLoadCallback = this.W;
        if (gMInterstitialFullAdLoadCallback != null) {
            gMInterstitialFullAdLoadCallback.onInterstitialFullLoadFail(adError);
        }
    }

    @Override // com.bytedance.msdk.core.corelogic.e
    protected void a(TTBaseAd tTBaseAd, Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
        if (tTBaseAd != null) {
            if (tTBaseAd.getSubAdType() == 1) {
                this.F = this.Z;
            } else if (tTBaseAd.getSubAdType() == 2) {
                this.F = this.Y;
            }
        }
        super.a(tTBaseAd, activity, map);
    }
}
