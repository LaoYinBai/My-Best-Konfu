package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.bytedance.msdk.adapter.listener.ITTAdapterFullVideoAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.TTVideoOptionUtil;
import com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener;
import com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdLoadCallback;
import com.bytedance.msdk.api.v2.slot.GMAdSlotFullVideo;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.msdk.core.corelogic.e;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e extends com.bytedance.msdk.core.corelogic.e implements ITTAdapterFullVideoAdListener {
    private GMFullVideoAdLoadCallback W;
    private GMFullVideoAdListener X;

    class a implements e.c {
        a() {
        }

        @Override // com.bytedance.msdk.core.corelogic.e.c
        public void a() {
            e.this.b(new AdError(AdError.ERROR_CODE_SHOW_FAIL_NO_AD, AdError.getMessage(AdError.ERROR_CODE_SHOW_FAIL_NO_AD)));
        }
    }

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AdError f1786a;

        b(AdError adError) {
            this.f1786a = adError;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (e.this.X != null) {
                e.this.X.onFullVideoAdShowFail(this.f1786a);
            }
        }
    }

    public e(Context context, String str) {
        super(context, str);
    }

    private void L() {
        TTBaseAd tTBaseAd = this.I;
        if (tTBaseAd == null || tTBaseAd.getAdNetworkPlatformId() == 4) {
            return;
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a().a(this.E, this.f, e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(AdError adError) {
        ThreadHelper.runOnUiThread(new b(adError));
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void D() {
        GMFullVideoAdLoadCallback gMFullVideoAdLoadCallback = this.W;
        if (gMFullVideoAdLoadCallback != null) {
            gMFullVideoAdLoadCallback.onFullVideoAdLoad();
        }
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void E() {
        GMFullVideoAdLoadCallback gMFullVideoAdLoadCallback = this.W;
        if (gMFullVideoAdLoadCallback != null) {
            gMFullVideoAdLoadCallback.onFullVideoCached();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener
    public void onFullVideoAdClick() {
        GMFullVideoAdListener gMFullVideoAdListener = this.X;
        if (gMFullVideoAdListener != null) {
            gMFullVideoAdListener.onFullVideoAdClick();
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.c(this.I, this.h);
    }

    @Override // com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener
    public void onFullVideoAdClosed() {
        L();
        GMFullVideoAdListener gMFullVideoAdListener = this.X;
        if (gMFullVideoAdListener != null) {
            gMFullVideoAdListener.onFullVideoAdClosed();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener
    public void onFullVideoAdShow() {
        GMFullVideoAdListener gMFullVideoAdListener = this.X;
        if (gMFullVideoAdListener != null) {
            gMFullVideoAdListener.onFullVideoAdShow();
        }
        if (this.I != null) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.f, TTLogUtil.TAG_EVENT_SHOW_LISTEN) + "adSlotId：" + this.I.getAdNetworkSlotId() + "，广告类型：" + com.bytedance.msdk.base.a.a(this.I.getAdNetworkPlatformId()));
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.e(this.I, this.h);
        com.bytedance.msdk.core.corelogic.f.a(this.f, this.I);
    }

    @Override // com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener
    public void onFullVideoAdShowFail(AdError adError) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.I, this.h, adError, 1);
        b(adError);
    }

    @Override // com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener
    public void onSkippedVideo() {
        L();
        GMFullVideoAdListener gMFullVideoAdListener = this.X;
        if (gMFullVideoAdListener != null) {
            gMFullVideoAdListener.onSkippedVideo();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener
    public void onVideoComplete() {
        L();
        GMFullVideoAdListener gMFullVideoAdListener = this.X;
        if (gMFullVideoAdListener != null) {
            gMFullVideoAdListener.onVideoComplete();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener
    public void onVideoError() {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.I, this.h, (AdError) null, 2);
        GMFullVideoAdListener gMFullVideoAdListener = this.X;
        if (gMFullVideoAdListener != null) {
            gMFullVideoAdListener.onVideoError();
        }
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    public void x() {
        super.x();
        this.X = null;
        this.W = null;
    }

    public void a(AdSlot adSlot, GMAdSlotFullVideo gMAdSlotFullVideo, @NonNull GMFullVideoAdLoadCallback gMFullVideoAdLoadCallback) {
        if (adSlot != null && adSlot.isV2Request()) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a("mediation_v2_full_video");
        }
        if (J()) {
            this.W = gMFullVideoAdLoadCallback;
            this.h = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.b.getShallowCopy(adSlot);
            AdSlot adSlot2 = this.h;
            if (adSlot2 != null) {
                adSlot2.setAdType(8);
                this.h.setAdCount(1);
                TTVideoOptionUtil.setFullTTVideoOptionIfNeed(this.h);
            }
            this.F = this;
            this.y = gMAdSlotFullVideo;
            G();
        }
    }

    @MainThread
    public void a(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) throws JSONException {
        super.a(activity, map, new a());
        if (this.X == null) {
            Logger.d("TTMediationSDK", "注意：未设置GMFullVideoAdListener，将收不到广告播放/点击/关闭等回调信息");
        }
    }

    public void a(GMFullVideoAdListener gMFullVideoAdListener) {
        this.X = gMFullVideoAdListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    public void a(AdError adError) {
        GMFullVideoAdLoadCallback gMFullVideoAdLoadCallback = this.W;
        if (gMFullVideoAdLoadCallback != null) {
            gMFullVideoAdLoadCallback.onFullVideoLoadFail(adError);
        }
    }
}
