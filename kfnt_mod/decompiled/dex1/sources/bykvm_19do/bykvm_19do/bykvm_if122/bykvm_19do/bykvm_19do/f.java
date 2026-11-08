package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_byte12b.a;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.TTVideoOptionUtil;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdLoadCallback;
import com.bytedance.msdk.api.v2.slot.GMAdSlotNative;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f extends d {
    private GMNativeAdLoadCallback W;

    class a implements a.c {
        a() {
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_byte12b.a.c
        public void a() {
            ((TTAdHeaderBidingRequestCore) f.this).H = true;
        }
    }

    public f(Context context, String str) {
        super(context, str);
    }

    private void K() {
        List<TTBaseAd> list;
        try {
            List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> listC = C();
            if (listC == null || listC.size() <= 0) {
                return;
            }
            for (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar : listC) {
                if (iVar != null) {
                    String strD = iVar.d();
                    if (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().c(this.f, strD, e())) {
                        if (iVar.f() == 100) {
                            list = this.q;
                        } else if (iVar.f() == 0) {
                            list = this.o;
                        }
                        a(strD, list);
                    }
                }
            }
            com.bytedance.msdk.core.corelogic.f.a(this.q, (Comparator<TTBaseAd>) null);
            com.bytedance.msdk.core.corelogic.f.a(this.o, (Comparator<TTBaseAd>) null);
            com.bytedance.msdk.core.corelogic.f.a(this.p, com.bytedance.msdk.core.corelogic.f.a());
            Logger.d("TMe", "--==-- sorted ok");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private List<GMNativeAd> L() {
        AdSlot adSlot = this.h;
        if (adSlot == null) {
            return null;
        }
        int adCount = adSlot.getAdCount();
        f(this.q);
        f(this.p);
        f(this.o);
        K();
        List<TTBaseAd> listP = p();
        return listP.size() >= adCount ? a(listP, adCount) : a(listP, adCount);
    }

    private List<GMNativeAd> a(List<TTBaseAd> list, int i) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<TTBaseAd> it = list.iterator();
        while (it.hasNext() && arrayList2.size() < i) {
            TTBaseAd next = it.next();
            arrayList.add(next);
            if (Logger.isDebug()) {
                Logger.e("TTMediationSDK", "");
                Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.f, TTLogUtil.TAG_EVENT_SHOW) + "返回给开发者最终广告：slotId=" + next.getAdNetworkSlotId() + ",slotType:" + next.getAdNetworkSlotType() + ",cpm=" + next.getCpm() + ",广告类型：" + com.bytedance.msdk.base.a.a(next.getAdNetworkPlatformId()) + ",ImageMode=" + next.getImageMode() + ",showSort=" + next.getShowSort() + ",isExpressAd=" + next.isExpressAd());
            }
            arrayList2.add(new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_byte12b.a(next, this.h, new a()));
            it.remove();
        }
        a(arrayList);
        return arrayList2;
    }

    private void a(String str, List<TTBaseAd> list) {
        List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f> listA;
        TTBaseAd tTBaseAd;
        if (b(str, list) || bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().b(str, this.h) != 3 || (listA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().a(str, this.h)) == null || listA.size() <= 0) {
            return;
        }
        for (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f fVar : listA) {
            if (fVar != null && (tTBaseAd = fVar.f1866a) != null && !tTBaseAd.isHasShown()) {
                list.add(fVar.f1866a);
                a(fVar.f1866a);
            }
        }
    }

    private boolean b(String str, List<TTBaseAd> list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        for (TTBaseAd tTBaseAd : list) {
            if (tTBaseAd != null && TextUtils.equals(str, tTBaseAd.getAdNetworkSlotId())) {
                return true;
            }
        }
        return false;
    }

    private void f(List<TTBaseAd> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        ArrayList<TTBaseAd> arrayList = new ArrayList(list.size());
        arrayList.addAll(list);
        list.clear();
        for (TTBaseAd tTBaseAd : arrayList) {
            if (tTBaseAd != null && !tTBaseAd.isHasShown()) {
                list.add(tTBaseAd);
            }
        }
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void D() {
        List<GMNativeAd> listL;
        if (this.W == null || (listL = L()) == null || listL.size() <= 0) {
            return;
        }
        if (Logger.isDebug()) {
            Logger.e("TTMediationSDK", "返回给外部开发者的广告数量：sumList.size=" + listL.size());
        }
        for (GMNativeAd gMNativeAd : listL) {
            if (gMNativeAd != null) {
                if (gMNativeAd instanceof bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_byte12b.a) {
                    ((bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_byte12b.a) gMNativeAd).a(true);
                }
                if (gMNativeAd.canAdReuse() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().c(this.f, gMNativeAd.getAdNetworkRitId(), e())) {
                    a(gMNativeAd.getTTBaseAd(), gMNativeAd.getAdNetworkRitId());
                }
            }
        }
        this.W.onAdLoaded(listL);
        int i = 0;
        while (i < listL.size()) {
            GMNativeAd gMNativeAd2 = listL.get(i);
            if (gMNativeAd2 != null && gMNativeAd2.canAdReuse() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().c(this.f, gMNativeAd2.getAdNetworkRitId(), e())) {
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().a(gMNativeAd2.getAdNetworkRitId(), this.h, this.t, i == 0, this.N, this.x, this.E);
            }
            i++;
        }
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void E() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    public void a(AdError adError) {
        GMNativeAdLoadCallback gMNativeAdLoadCallback = this.W;
        if (gMNativeAdLoadCallback != null) {
            gMNativeAdLoadCallback.onAdLoadedFail(adError);
        }
    }

    public void a(AdSlot adSlot, GMAdSlotNative gMAdSlotNative, GMNativeAdLoadCallback gMNativeAdLoadCallback) {
        int i;
        AdSlot adSlot2;
        if (adSlot != null && adSlot.isV2Request()) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a("mediation_v2_feed");
        }
        if (J()) {
            this.h = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.b.getShallowCopy(adSlot);
            AdSlot adSlot3 = this.h;
            if (adSlot3 != null) {
                adSlot3.setAdType(5);
                if (this.h.getAdCount() <= 0) {
                    adSlot2 = this.h;
                    i = 1;
                } else {
                    i = 3;
                    if (this.h.getAdCount() > 3) {
                        adSlot2 = this.h;
                    }
                    TTVideoOptionUtil.setFeedTTVideoOptionIfNeed(this.h);
                }
                adSlot2.setAdCount(i);
                TTVideoOptionUtil.setFeedTTVideoOptionIfNeed(this.h);
            }
            this.W = gMNativeAdLoadCallback;
            this.y = gMAdSlotNative;
            G();
        }
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    public void x() {
        super.x();
        this.W = null;
    }
}
