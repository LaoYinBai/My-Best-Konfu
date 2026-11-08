package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.VisibleForTesting;
import com.bytedance.msdk.adapter.listener.ITTAdapterBannerAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.TTRequestExtraParams;
import com.bytedance.msdk.api.v2.GMDislikeCallback;
import com.bytedance.msdk.api.v2.ad.banner.GMBannerAdListener;
import com.bytedance.msdk.api.v2.ad.banner.GMBannerAdLoadCallback;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBase;
import com.bytedance.msdk.base.TTBaseAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c extends d implements ITTAdapterBannerAdListener {
    private GMBannerAdListener W;
    private GMBannerAdLoadCallback X;

    class a implements GMDislikeCallback {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f1780a;

        a(c cVar, View view) {
            this.f1780a = view;
        }

        @Override // com.bytedance.msdk.api.v2.GMDislikeCallback
        public void onCancel() {
        }

        @Override // com.bytedance.msdk.api.v2.GMDislikeCallback
        public void onRefuse() {
        }

        @Override // com.bytedance.msdk.api.v2.GMDislikeCallback
        public void onSelected(int i, String str) {
            View view = this.f1780a;
            if (view != null) {
                View view2 = (View) view.getParent();
                if (view2 instanceof ViewGroup) {
                    ((ViewGroup) view2).removeViewInLayout(this.f1780a);
                }
            }
        }

        @Override // com.bytedance.msdk.api.v2.GMDislikeCallback
        public void onShow() {
        }
    }

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AdError f1781a;

        b(AdError adError) {
            this.f1781a = adError;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (c.this.W != null) {
                c.this.W.onAdShowFail(this.f1781a);
            }
        }
    }

    public c(Context context, String str) {
        super(context, str);
    }

    private View b(TTBaseAd tTBaseAd) {
        this.I = tTBaseAd;
        this.I.setHasShown(true);
        this.I.setTTAdatperCallback(this.F);
        if (this.I.canAdReuse() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().c(this.f, this.I.getAdNetworkSlotId(), e())) {
            TTBaseAd tTBaseAd2 = this.I;
            a(tTBaseAd2, tTBaseAd2.getAdNetworkSlotId());
        }
        Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.f, TTLogUtil.TAG_EVENT_SHOW) + "展示的广告类型：" + com.bytedance.msdk.base.a.a(this.I.getAdNetworkPlatformId()) + ",slotId：" + this.I.getAdNetworkSlotId() + ",slotType:" + this.I.getAdNetworkSlotType());
        View adView = this.I.getAdView();
        if (this.I.hasDislike()) {
            this.I.setDislikeCallback((Activity) this.E, new a(this, adView));
        }
        return adView;
    }

    private void b(AdError adError) {
        ThreadHelper.runOnUiThread(new b(adError));
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void D() {
        GMBannerAdLoadCallback gMBannerAdLoadCallback = this.X;
        if (gMBannerAdLoadCallback != null) {
            gMBannerAdLoadCallback.onAdLoaded();
        }
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void E() {
    }

    public synchronized View K() {
        List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> listC;
        View viewB;
        TTBaseAd tTBaseAd;
        TTBaseAd next;
        List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f> listA;
        TTBaseAd tTBaseAd2;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.h);
        if (!this.H && !this.f4486b.get()) {
            List<TTBaseAd> listP = p();
            try {
                listC = C();
            } catch (Throwable th) {
                th.printStackTrace();
                listC = null;
            }
            if (listC != null && listC.size() > 0) {
                HashMap<String, TTBaseAd> mapE = e(listP);
                viewB = null;
                for (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar : listC) {
                    if (iVar != null) {
                        String strD = iVar.d();
                        TTBaseAd tTBaseAd3 = mapE.get(strD);
                        if (tTBaseAd3 != null && !tTBaseAd3.isHasShown() && (viewB = b(tTBaseAd3)) != null) {
                            break;
                        }
                        if (iVar.a() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().c(this.f, strD, e()) && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().b(strD, this.h) == 3 && (listA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().a(strD, this.h)) != null && listA.size() > 0 && (tTBaseAd2 = listA.get(0).f1866a) != null && !tTBaseAd2.isHasShown()) {
                            a(tTBaseAd2);
                            viewB = b(tTBaseAd2);
                            if (viewB != null) {
                                break;
                            }
                        }
                    }
                }
            } else {
                viewB = null;
            }
            if (viewB == null && listP != null && listP.size() > 0) {
                Iterator<TTBaseAd> it = listP.iterator();
                while (it.hasNext() && ((next = it.next()) == null || next.isHasShown() || (viewB = b(next)) == null)) {
                }
            }
            if (viewB == null) {
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(listP, this.h);
                b(new AdError(AdError.ERROR_CODE_SHOW_FAIL_NO_AD, AdError.getMessage(AdError.ERROR_CODE_SHOW_FAIL_NO_AD)));
                return null;
            }
            this.H = true;
            if (this.I != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(this.I);
                a(arrayList);
            }
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.d(this.I, this.h);
            if (this.W != null && (tTBaseAd = this.I) != null && tTBaseAd.getAdNetworkPlatformId() == 2) {
                Logger.d("ADMOB_EVENT", TTLogUtil.getTagThirdLevelById(this.f, this.I.getAdNetworkSlotId()) + "mTTAdBannerListener-->Admob--->onAdShow......");
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i.b().a(this.f + "");
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i.b().j(this.f + "");
                this.W.onAdShow();
                if (this.I != null) {
                    Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.f, TTLogUtil.TAG_EVENT_SHOW_LISTEN) + "adSlotId：" + this.I.getAdNetworkSlotId() + "，广告类型：" + com.bytedance.msdk.base.a.a(this.I.getAdNetworkPlatformId()));
                }
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.e(this.I, this.h);
                com.bytedance.msdk.core.corelogic.f.a(this.f, this.I);
            }
            TTBaseAd tTBaseAd4 = this.I;
            if (tTBaseAd4 != null && tTBaseAd4.canAdReuse() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().c(this.f, this.I.getAdNetworkSlotId(), e())) {
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().a(this.I.getAdNetworkSlotId(), this.h, this.t, true, this.N, this.x, this.E);
            }
            return viewB;
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a((List<TTBaseAd>) null, this.h);
        b(new AdError(AdError.ERROR_CODE_SHOW_FAIL_NO_AD, AdError.getMessage(AdError.ERROR_CODE_SHOW_FAIL_NO_AD)));
        while (true) {
            switch (19) {
                case 18:
                    if ((75 * 75 * 75) + (18 * 18 * 18) + (27 * 27 * 27) < 75 * 18 * 27 * 3) {
                        break;
                    } else {
                        continue;
                    }
                case 19:
                    break;
                case 20:
                    if ((((86 - 1) * 86) * ((86 * 2) - 1)) % 6 != 0) {
                        break;
                    } else {
                        break;
                    }
                default:
                    continue;
            }
            while (true) {
                switch (96) {
                    case 94:
                        if (((83 + 1) * 83) % 2 != 0) {
                            break;
                        } else {
                            break;
                        }
                }
            }
        }
        return null;
    }

    @VisibleForTesting
    public boolean L() {
        return ((Boolean) this.t.get(TTRequestExtraParams.PARAM_BANNER_ALLOW_SHOW_CLOSE_BTN)).booleanValue();
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void a(AdError adError) {
        GMBannerAdLoadCallback gMBannerAdLoadCallback = this.X;
        if (gMBannerAdLoadCallback != null) {
            gMBannerAdLoadCallback.onAdFailedToLoad(adError);
        }
    }

    public void a(AdSlot adSlot, GMAdSlotBase gMAdSlotBase, GMBannerAdLoadCallback gMBannerAdLoadCallback) {
        if (adSlot != null && adSlot.isV2Request()) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a("mediation_v2_banner");
        }
        if (J()) {
            this.h = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.b.getShallowCopy(adSlot);
            AdSlot adSlot2 = this.h;
            if (adSlot2 != null) {
                adSlot2.setAdType(1);
                this.h.setAdCount(1);
            }
            this.X = gMBannerAdLoadCallback;
            this.F = this;
            this.y = gMAdSlotBase;
            G();
        }
    }

    public void a(GMBannerAdListener gMBannerAdListener) {
        this.W = gMBannerAdListener;
    }

    public void a(boolean z) {
        this.t.put(TTRequestExtraParams.PARAM_BANNER_ALLOW_SHOW_CLOSE_BTN, Boolean.valueOf(z));
    }

    @Override // com.bytedance.msdk.api.v2.ad.banner.GMBannerAdListener
    public void onAdClicked() {
        GMBannerAdListener gMBannerAdListener = this.W;
        if (gMBannerAdListener != null) {
            gMBannerAdListener.onAdClicked();
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.c(this.I, this.h);
    }

    @Override // com.bytedance.msdk.api.v2.ad.banner.GMBannerAdListener
    public void onAdClosed() {
        GMBannerAdListener gMBannerAdListener = this.W;
        if (gMBannerAdListener != null) {
            gMBannerAdListener.onAdClosed();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.banner.GMBannerAdListener
    public void onAdLeftApplication() {
        GMBannerAdListener gMBannerAdListener = this.W;
        if (gMBannerAdListener != null) {
            gMBannerAdListener.onAdLeftApplication();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.banner.GMBannerAdListener
    public void onAdOpened() {
        GMBannerAdListener gMBannerAdListener = this.W;
        if (gMBannerAdListener != null) {
            gMBannerAdListener.onAdOpened();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.banner.GMBannerAdListener
    public void onAdShow() {
        if (this.W != null) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i.b().a(this.f + "");
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i.b().j(this.f + "");
            this.W.onAdShow();
        }
        if (this.I != null) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.f, TTLogUtil.TAG_EVENT_SHOW_LISTEN) + "adSlotId：" + this.I.getAdNetworkSlotId() + "，广告类型：" + com.bytedance.msdk.base.a.a(this.I.getAdNetworkPlatformId()));
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.e(this.I, this.h);
        com.bytedance.msdk.core.corelogic.f.a(this.f, this.I);
    }

    @Override // com.bytedance.msdk.api.v2.ad.banner.GMBannerAdListener
    public void onAdShowFail(AdError adError) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.I, this.h, adError, 1);
        b(adError);
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    public void x() {
        super.x();
        this.W = null;
        this.X = null;
    }
}
