package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do;

import android.app.Activity;
import android.content.Context;
import com.bytedance.msdk.adapter.listener.ITTAdapterInterstitialListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.v2.ad.interstitial.GMInterstitialAdListener;
import com.bytedance.msdk.api.v2.ad.interstitial.GMInterstitialAdLoadCallback;
import com.bytedance.msdk.api.v2.slot.GMAdSlotInterstitial;
import com.bytedance.msdk.core.corelogic.e;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b extends com.bytedance.msdk.core.corelogic.e implements ITTAdapterInterstitialListener {
    private GMInterstitialAdLoadCallback W;
    private GMInterstitialAdListener X;

    class a implements e.c {
        a() {
        }

        @Override // com.bytedance.msdk.core.corelogic.e.c
        public void a() {
            b.this.b(new AdError(AdError.ERROR_CODE_SHOW_FAIL_NO_AD, AdError.getMessage(AdError.ERROR_CODE_SHOW_FAIL_NO_AD)));
        }
    }

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.b$b, reason: collision with other inner class name */
    class RunnableC0058b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AdError f1778a;

        RunnableC0058b(AdError adError) {
            this.f1778a = adError;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (b.this.X != null) {
                b.this.X.onInterstitialShowFail(this.f1778a);
            }
        }
    }

    public b(Context context, String str) {
        super(context, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(AdError adError) {
        ThreadHelper.runOnUiThread(new RunnableC0058b(adError));
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void D() {
        GMInterstitialAdLoadCallback gMInterstitialAdLoadCallback = this.W;
        if (gMInterstitialAdLoadCallback != null) {
            gMInterstitialAdLoadCallback.onInterstitialLoad();
        }
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void E() {
    }

    @Override // com.bytedance.msdk.api.v2.ad.interstitial.GMInterstitialAdListener
    public void onAdLeftApplication() {
        GMInterstitialAdListener gMInterstitialAdListener = this.X;
        if (gMInterstitialAdListener != null) {
            gMInterstitialAdListener.onAdLeftApplication();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.interstitial.GMInterstitialAdListener
    public void onAdOpened() {
        GMInterstitialAdListener gMInterstitialAdListener = this.X;
        if (gMInterstitialAdListener != null) {
            gMInterstitialAdListener.onAdOpened();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.interstitial.GMInterstitialAdListener
    public void onInterstitialAdClick() {
        GMInterstitialAdListener gMInterstitialAdListener = this.X;
        if (gMInterstitialAdListener != null) {
            gMInterstitialAdListener.onInterstitialAdClick();
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.c(this.I, this.h);
    }

    @Override // com.bytedance.msdk.api.v2.ad.interstitial.GMInterstitialAdListener
    public void onInterstitialClosed() {
        GMInterstitialAdListener gMInterstitialAdListener = this.X;
        if (gMInterstitialAdListener != null) {
            gMInterstitialAdListener.onInterstitialClosed();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.interstitial.GMInterstitialAdListener
    public void onInterstitialShow() {
        GMInterstitialAdListener gMInterstitialAdListener = this.X;
        if (gMInterstitialAdListener != null) {
            gMInterstitialAdListener.onInterstitialShow();
        }
        if (this.I != null) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.f, TTLogUtil.TAG_EVENT_SHOW_LISTEN) + "adSlotId：" + this.I.getAdNetworkSlotId() + "，广告类型：" + com.bytedance.msdk.base.a.a(this.I.getAdNetworkPlatformId()));
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.e(this.I, this.h);
        com.bytedance.msdk.core.corelogic.f.a(this.f, this.I);
    }

    @Override // com.bytedance.msdk.api.v2.ad.interstitial.GMInterstitialAdListener
    public void onInterstitialShowFail(AdError adError) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.I, this.h, adError, 1);
        b(adError);
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    public void x() {
        super.x();
        this.X = null;
        this.W = null;
    }

    public void a(AdSlot adSlot, GMAdSlotInterstitial gMAdSlotInterstitial, GMInterstitialAdLoadCallback gMInterstitialAdLoadCallback) {
        if (adSlot != null && adSlot.isV2Request()) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a("mediation_v2_interstitial");
        }
        if (J()) {
            this.h = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.b.getShallowCopy(adSlot);
            AdSlot adSlot2 = this.h;
            if (adSlot2 != null) {
                adSlot2.setAdType(2);
                this.h.setAdCount(1);
            }
            this.W = gMInterstitialAdLoadCallback;
            this.F = this;
            this.y = gMAdSlotInterstitial;
            G();
        }
    }

    public void a(Activity activity) throws JSONException {
        super.a(activity, new a());
    }

    public void a(GMInterstitialAdListener gMInterstitialAdListener) {
        this.X = gMInterstitialAdListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    public void a(AdError adError) {
        GMInterstitialAdLoadCallback gMInterstitialAdLoadCallback = this.W;
        if (gMInterstitialAdLoadCallback != null) {
            gMInterstitialAdLoadCallback.onInterstitialLoadFail(adError);
        }
    }
}
