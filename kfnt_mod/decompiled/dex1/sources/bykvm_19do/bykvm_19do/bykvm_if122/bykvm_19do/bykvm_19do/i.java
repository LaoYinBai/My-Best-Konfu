package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.VisibleForTesting;
import com.bytedance.msdk.adapter.listener.ITTAdapterSplashAdListener;
import com.bytedance.msdk.adapter.listener.ITTAdatperCallback;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.TTRequestExtraParams;
import com.bytedance.msdk.api.v2.GMNetworkRequestInfo;
import com.bytedance.msdk.api.v2.ad.splash.GMSplashAdListener;
import com.bytedance.msdk.api.v2.ad.splash.GMSplashAdLoadCallback;
import com.bytedance.msdk.api.v2.ad.splash.GMSplashMinWindowListener;
import com.bytedance.msdk.api.v2.slot.GMAdSlotSplash;
import com.bytedance.msdk.base.TTBaseAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i extends d implements ITTAdapterSplashAdListener {
    private GMSplashAdLoadCallback W;
    private GMSplashAdListener X;
    private GMSplashMinWindowListener Y;

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AdError f1801a;

        a(AdError adError) {
            this.f1801a = adError;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (i.this.X != null) {
                i.this.X.onAdShowFail(this.f1801a);
            }
        }
    }

    public i(Activity activity, String str) {
        super(activity, str);
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void D() {
        GMSplashAdLoadCallback gMSplashAdLoadCallback = this.W;
        if (gMSplashAdLoadCallback != null) {
            gMSplashAdLoadCallback.onSplashAdLoadSuccess();
        }
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void E() {
    }

    public int[] K() {
        TTBaseAd tTBaseAd = this.I;
        if (tTBaseAd == null) {
            return null;
        }
        tTBaseAd.getMinWindowSize();
        return null;
    }

    public Bitmap L() {
        TTBaseAd tTBaseAd = this.I;
        if (tTBaseAd == null) {
            return null;
        }
        tTBaseAd.getSplashBitMap();
        return null;
    }

    @VisibleForTesting
    public int M() {
        return ((Integer) this.t.get(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT)).intValue();
    }

    public void N() {
        TTBaseAd tTBaseAd = this.I;
        if (tTBaseAd != null) {
            tTBaseAd.splashMinWindowAnimationFinish();
        }
    }

    public void b(int i) {
        this.t.put(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT, Integer.valueOf(i));
    }

    @Override // com.bytedance.msdk.api.v2.ad.splash.GMSplashAdListener
    public void onAdClicked() {
        GMSplashAdListener gMSplashAdListener = this.X;
        if (gMSplashAdListener != null) {
            gMSplashAdListener.onAdClicked();
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.c(this.I, this.h);
    }

    @Override // com.bytedance.msdk.api.v2.ad.splash.GMSplashAdListener
    public void onAdDismiss() {
        GMSplashAdListener gMSplashAdListener = this.X;
        if (gMSplashAdListener != null) {
            gMSplashAdListener.onAdDismiss();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.splash.GMSplashAdListener
    public void onAdShow() {
        GMSplashAdListener gMSplashAdListener = this.X;
        if (gMSplashAdListener != null) {
            gMSplashAdListener.onAdShow();
        }
        if (this.I != null) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.f, TTLogUtil.TAG_EVENT_SHOW_LISTEN) + "adSlotId：" + this.I.getAdNetworkSlotId() + "，广告类型：" + com.bytedance.msdk.base.a.a(this.I.getAdNetworkPlatformId()));
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.e(this.I, this.h);
        com.bytedance.msdk.core.corelogic.f.a(this.f, this.I);
    }

    @Override // com.bytedance.msdk.api.v2.ad.splash.GMSplashAdListener
    public void onAdShowFail(AdError adError) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.I, this.h, adError, 1);
        b(adError);
    }

    @Override // com.bytedance.msdk.api.v2.ad.splash.GMSplashAdListener
    public void onAdSkip() {
        GMSplashAdListener gMSplashAdListener = this.X;
        if (gMSplashAdListener != null) {
            gMSplashAdListener.onAdSkip();
        }
    }

    @Override // com.bytedance.msdk.adapter.listener.ITTAdapterSplashAdListener
    public void onMinWindowPlayFinish() {
        GMSplashMinWindowListener gMSplashMinWindowListener = this.Y;
        if (gMSplashMinWindowListener != null) {
            gMSplashMinWindowListener.onMinWindowPlayFinish();
        }
    }

    @Override // com.bytedance.msdk.adapter.listener.ITTAdapterSplashAdListener
    public void onMinWindowStart() {
        GMSplashMinWindowListener gMSplashMinWindowListener = this.Y;
        if (gMSplashMinWindowListener != null) {
            gMSplashMinWindowListener.onMinWindowStart();
        }
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    public void x() {
        super.x();
        this.X = null;
        this.W = null;
    }

    public i(Activity activity, View view, String str) {
        super(activity, str);
    }

    private void b(AdError adError) {
        ThreadHelper.runOnUiThread(new a(adError));
    }

    public void a(AdSlot adSlot, GMAdSlotSplash gMAdSlotSplash, GMNetworkRequestInfo gMNetworkRequestInfo, GMSplashAdLoadCallback gMSplashAdLoadCallback, int i) {
        if (adSlot != null && adSlot.isV2Request()) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a("mediation_v2_splash");
        }
        if (J()) {
            this.h = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.b.getShallowCopy(adSlot);
            AdSlot adSlot2 = this.h;
            if (adSlot2 != null) {
                adSlot2.setAdType(3);
                this.h.setAdCount(1);
            }
            this.N = gMNetworkRequestInfo;
            this.W = gMSplashAdLoadCallback;
            if (i != -1) {
                this.t.put(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT, Integer.valueOf(i));
            }
            this.F = this;
            this.y = gMAdSlotSplash;
            G();
        }
    }

    public void a(AdSlot adSlot, GMAdSlotSplash gMAdSlotSplash, GMNetworkRequestInfo gMNetworkRequestInfo, GMSplashAdLoadCallback gMSplashAdLoadCallback) {
        a(adSlot, gMAdSlotSplash, gMNetworkRequestInfo, gMSplashAdLoadCallback, -1);
    }

    public void a(GMSplashAdListener gMSplashAdListener) {
        this.X = gMSplashAdListener;
    }

    public void a(GMSplashMinWindowListener gMSplashMinWindowListener) {
        this.Y = gMSplashMinWindowListener;
    }

    public void a(ViewGroup viewGroup) throws JSONException {
        boolean z;
        List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f> listA;
        TTBaseAd tTBaseAd;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.h);
        List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> listC = null;
        if (this.H) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a((List<TTBaseAd>) null, this.h);
            b(new AdError(AdError.ERROR_CODE_SHOW_FAIL_NO_AD, AdError.getMessage(AdError.ERROR_CODE_SHOW_FAIL_NO_AD)));
            return;
        }
        List<TTBaseAd> listP = p();
        if (viewGroup != null && !this.f4486b.get()) {
            try {
                listC = C();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            boolean z2 = false;
            if (listC == null || listC.size() <= 0) {
                z = false;
            } else {
                HashMap<String, TTBaseAd> mapE = e(listP);
                for (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar : listC) {
                    if (iVar != null) {
                        String strD = iVar.d();
                        TTBaseAd tTBaseAd2 = mapE.get(strD);
                        if (tTBaseAd2 != null && tTBaseAd2.isReady() && !tTBaseAd2.isHasShown()) {
                            a(tTBaseAd2, viewGroup);
                        } else if (iVar.a() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().c(this.f, strD, e()) && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().b(strD, this.h) == 3 && (listA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().a(strD, this.h)) != null && listA.size() > 0 && (tTBaseAd = listA.get(0).f1866a) != null && tTBaseAd.isReady() && !tTBaseAd.isHasShown()) {
                            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.f, TTLogUtil.TAG_EVENT_SHOW) + "adSlotId：" + tTBaseAd.getAdNetworkSlotId() + "，广告类型：" + com.bytedance.msdk.base.a.a(tTBaseAd.getAdNetworkPlatformId()) + ",isReady()：" + tTBaseAd.isReady());
                            a(tTBaseAd);
                            a(tTBaseAd, viewGroup);
                        }
                        z = true;
                        z2 = true;
                        break;
                    }
                }
                z = false;
            }
            if (!z2 && listP != null && listP.size() > 0) {
                Iterator<TTBaseAd> it = listP.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    TTBaseAd next = it.next();
                    if (next != null && next.isReady() && !next.isHasShown()) {
                        a(next, viewGroup);
                        z = true;
                        break;
                    }
                }
            }
            if (z) {
                this.H = true;
                if (this.I != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(this.I);
                    a(arrayList);
                }
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.d(this.I, this.h);
                if (this.I.canAdReuse() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().c(this.f, this.I.getAdNetworkSlotId(), e())) {
                    bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().a(this.I.getAdNetworkSlotId(), this.h, this.t, true, this.N, this.x, this.E);
                    return;
                }
                return;
            }
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(listP, this.h);
            b(new AdError(AdError.ERROR_CODE_SHOW_FAIL_NO_AD, AdError.getMessage(AdError.ERROR_CODE_SHOW_FAIL_NO_AD)));
            return;
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(listP, this.h);
        b(new AdError(AdError.ERROR_CODE_SHOW_FAIL_NO_AD, AdError.getMessage(AdError.ERROR_CODE_SHOW_FAIL_NO_AD)));
    }

    private void a(TTBaseAd tTBaseAd, ViewGroup viewGroup) {
        this.I = tTBaseAd;
        this.I.setHasShown(true);
        this.I.setTTAdatperCallback(this.F);
        if (this.I.canAdReuse() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().c(this.f, this.I.getAdNetworkSlotId(), e())) {
            TTBaseAd tTBaseAd2 = this.I;
            a(tTBaseAd2, tTBaseAd2.getAdNetworkSlotId());
        }
        Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.f, TTLogUtil.TAG_EVENT_SHOW) + "展示的广告类型：" + com.bytedance.msdk.base.a.a(this.I.getAdNetworkPlatformId()) + ",slotId：" + this.I.getAdNetworkSlotId() + ",slotType:" + this.I.getAdNetworkSlotType());
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i iVarB = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i.b();
        StringBuilder sb = new StringBuilder();
        sb.append(this.f);
        sb.append("");
        iVarB.a(sb.toString());
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i.b().j(this.f + "");
        this.I.showSplashAd(viewGroup);
    }

    public boolean a(Rect rect, ITTAdatperCallback iTTAdatperCallback) {
        TTBaseAd tTBaseAd = this.I;
        if (tTBaseAd == null || tTBaseAd.getAdNetworkPlatformId() != 7) {
            return false;
        }
        this.I.showMinWindow(rect, iTTAdatperCallback);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    public void a(AdError adError) {
        GMSplashAdLoadCallback gMSplashAdLoadCallback = this.W;
        if (gMSplashAdLoadCallback != null) {
            gMSplashAdLoadCallback.onSplashAdLoadFail(adError);
        }
    }
}
