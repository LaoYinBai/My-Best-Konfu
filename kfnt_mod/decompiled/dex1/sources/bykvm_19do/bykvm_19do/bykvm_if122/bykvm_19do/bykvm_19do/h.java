package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.bytedance.msdk.adapter.listener.ITTAdapterRewardedAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.TTVideoOptionUtil;
import com.bytedance.msdk.api.reward.RewardItem;
import com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdListener;
import com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdLoadCallback;
import com.bytedance.msdk.api.v2.slot.GMAdSlotRewardVideo;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.msdk.core.corelogic.e;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h extends com.bytedance.msdk.core.corelogic.e implements ITTAdapterRewardedAdListener {
    private GMRewardedAdLoadCallback W;
    private GMRewardedAdListener X;

    class a implements e.c {
        a() {
        }

        @Override // com.bytedance.msdk.core.corelogic.e.c
        public void a() {
            h.this.b(new AdError(AdError.ERROR_CODE_SHOW_FAIL_NO_AD, AdError.getMessage(AdError.ERROR_CODE_SHOW_FAIL_NO_AD)));
        }
    }

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AdError f1799a;

        b(AdError adError) {
            this.f1799a = adError;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (h.this.X != null) {
                h.this.X.onRewardedAdShowFail(this.f1799a);
            }
        }
    }

    public h(Context context, String str) {
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
        GMRewardedAdLoadCallback gMRewardedAdLoadCallback = this.W;
        if (gMRewardedAdLoadCallback != null) {
            gMRewardedAdLoadCallback.onRewardVideoAdLoad();
        }
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void E() {
        GMRewardedAdLoadCallback gMRewardedAdLoadCallback = this.W;
        if (gMRewardedAdLoadCallback != null) {
            gMRewardedAdLoadCallback.onRewardVideoCached();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdListener
    public void onRewardClick() {
        GMRewardedAdListener gMRewardedAdListener = this.X;
        if (gMRewardedAdListener != null) {
            gMRewardedAdListener.onRewardClick();
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.c(this.I, this.h);
    }

    @Override // com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdListener
    public void onRewardVerify(RewardItem rewardItem) {
        GMRewardedAdListener gMRewardedAdListener = this.X;
        if (gMRewardedAdListener != null) {
            gMRewardedAdListener.onRewardVerify(rewardItem);
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdListener
    public void onRewardedAdClosed() {
        L();
        GMRewardedAdListener gMRewardedAdListener = this.X;
        if (gMRewardedAdListener != null) {
            gMRewardedAdListener.onRewardedAdClosed();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdListener
    public void onRewardedAdShow() {
        GMRewardedAdListener gMRewardedAdListener = this.X;
        if (gMRewardedAdListener != null) {
            gMRewardedAdListener.onRewardedAdShow();
        }
        if (this.I != null) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.f, TTLogUtil.TAG_EVENT_SHOW_LISTEN) + "adSlotId：" + this.I.getAdNetworkSlotId() + "，广告类型：" + com.bytedance.msdk.base.a.a(this.I.getAdNetworkPlatformId()));
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.e(this.I, this.h);
        com.bytedance.msdk.core.corelogic.f.a(this.f, this.I);
    }

    @Override // com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdListener
    public void onRewardedAdShowFail(AdError adError) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.I, this.h, adError, 1);
        b(adError);
    }

    @Override // com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdListener
    public void onSkippedVideo() {
        L();
        GMRewardedAdListener gMRewardedAdListener = this.X;
        if (gMRewardedAdListener != null) {
            gMRewardedAdListener.onSkippedVideo();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdListener
    public void onVideoComplete() {
        L();
        GMRewardedAdListener gMRewardedAdListener = this.X;
        if (gMRewardedAdListener != null) {
            gMRewardedAdListener.onVideoComplete();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdListener
    public void onVideoError() {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.I, this.h, (AdError) null, 2);
        GMRewardedAdListener gMRewardedAdListener = this.X;
        if (gMRewardedAdListener != null) {
            gMRewardedAdListener.onVideoError();
        }
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    public void x() {
        super.x();
        this.W = null;
        this.X = null;
    }

    public void a(AdSlot adSlot, GMAdSlotRewardVideo gMAdSlotRewardVideo, @NonNull GMRewardedAdLoadCallback gMRewardedAdLoadCallback) {
        if (adSlot != null && adSlot.isV2Request()) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a("mediation_v2_reward");
        }
        if (J()) {
            this.W = gMRewardedAdLoadCallback;
            this.h = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.b.getShallowCopy(adSlot);
            AdSlot adSlot2 = this.h;
            if (adSlot2 != null) {
                adSlot2.setAdType(7);
                this.h.setAdCount(1);
                TTVideoOptionUtil.setRewardTTVideoOptionIfNeed(this.h);
            }
            this.F = this;
            this.y = gMAdSlotRewardVideo;
            G();
        }
    }

    @MainThread
    public void a(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) throws JSONException {
        super.a(activity, map, new a());
        if (this.X == null) {
            Logger.d("TTMediationSDK", "注意：未设置GMRewardedAdListener，将收不到广告播放/点击/关闭等回调信息");
        }
    }

    public void a(GMRewardedAdListener gMRewardedAdListener) {
        this.X = gMRewardedAdListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    public void a(AdError adError) {
        GMRewardedAdLoadCallback gMRewardedAdLoadCallback = this.W;
        if (gMRewardedAdLoadCallback != null) {
            gMRewardedAdLoadCallback.onRewardVideoLoadFail(adError);
        }
    }
}
