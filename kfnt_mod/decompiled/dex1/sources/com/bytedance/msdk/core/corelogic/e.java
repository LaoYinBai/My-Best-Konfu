package com.bytedance.msdk.core.corelogic;

import android.app.Activity;
import android.content.Context;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.q;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.v2.ad.custom.GMCustomTTBaseAd;
import com.bytedance.msdk.base.TTBaseAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class e extends bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.d {

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ TTBaseAd f4490a;

        a(TTBaseAd tTBaseAd) {
            this.f4490a = tTBaseAd;
        }

        @Override // java.lang.Runnable
        public void run() {
            TTBaseAd tTBaseAd = this.f4490a;
            if (tTBaseAd == null || !tTBaseAd.adnHasAdVideoCachedApi() || !this.f4490a.isCacheSuccess() || (e.this.I instanceof GMCustomTTBaseAd)) {
                return;
            }
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(e.this.f, this.f4490a.getAdNetworkSlotId()) + "在show时触发了预加载【" + this.f4490a.getAdNetWorkName() + "】, loadSort: " + this.f4490a.getLoadSort() + ", showSort: " + this.f4490a.getShowSort());
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b bVarA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a();
            e eVar = e.this;
            bVarA.b(eVar.E, eVar.f, eVar.e());
        }
    }

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ TTBaseAd f4492a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f4493b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Map f4494c;

        b(TTBaseAd tTBaseAd, Activity activity, Map map) {
            this.f4492a = tTBaseAd;
            this.f4493b = activity;
            this.f4494c = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            TTBaseAd tTBaseAd = this.f4492a;
            if (tTBaseAd != null) {
                e eVar = e.this;
                eVar.I = tTBaseAd;
                TTBaseAd tTBaseAd2 = eVar.I;
                boolean z2 = true;
                tTBaseAd2.setHasShown(true);
                e eVar2 = e.this;
                eVar2.I.setTTAdatperCallback(eVar2.F);
                if (e.this.I.canAdReuse()) {
                    bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a aVarA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a();
                    e eVar3 = e.this;
                    if (aVarA.c(eVar3.f, eVar3.I.getAdNetworkSlotId(), e.this.e())) {
                        e eVar4 = e.this;
                        TTBaseAd tTBaseAd3 = eVar4.I;
                        eVar4.a(tTBaseAd3, tTBaseAd3.getAdNetworkSlotId());
                    }
                }
                Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(e.this.f, TTLogUtil.TAG_EVENT_SHOW) + "展示的广告类型：" + com.bytedance.msdk.base.a.a(e.this.I.getAdNetworkPlatformId()) + ",slotId：" + e.this.I.getAdNetworkSlotId() + ",slotType:" + e.this.I.getAdNetworkSlotType() + ",isReady()：" + e.this.I.isReady() + "，是否为缓存广告:" + e.this.I.isCacheSuccess());
                e.this.I.showAd(this.f4493b, this.f4494c);
                i iVarB = i.b();
                StringBuilder sb = new StringBuilder();
                sb.append(e.this.f);
                sb.append("");
                iVarB.a(sb.toString());
                i iVarB2 = i.b();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(e.this.f);
                sb2.append("");
                iVarB2.j(sb2.toString());
                e eVar5 = e.this;
                eVar5.H = true;
                if (eVar5.I != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(e.this.I);
                    e.this.a(arrayList);
                }
                e eVar6 = e.this;
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.d(eVar6.I, eVar6.h);
                if (this.f4492a.getAdType() == 8 || this.f4492a.getAdType() == 7) {
                    if (this.f4492a.adnHasAdVideoCachedApi() && this.f4492a.isCacheSuccess()) {
                        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b bVarA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a();
                        e eVar7 = e.this;
                        if (bVarA.a(eVar7.f, eVar7.e()) != 0) {
                            z2 = false;
                        }
                    }
                    z = this.f4492a.getAdNetworkPlatformId() == 4 ? false : z2;
                } else {
                    z = true;
                }
                if (this.f4492a.canAdReuse() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().c(e.this.f, this.f4492a.getAdNetworkSlotId(), e.this.e())) {
                    bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a aVarA2 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a();
                    String adNetworkSlotId = e.this.I.getAdNetworkSlotId();
                    e eVar8 = e.this;
                    aVarA2.a(adNetworkSlotId, eVar8.h, eVar8.t, z, eVar8.N, eVar8.x, eVar8.E);
                }
            }
        }
    }

    public interface c {
        void a();
    }

    protected e(Context context, String str) {
        super(context, str);
    }

    private boolean L() {
        boolean zE = q.e(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d());
        boolean zF = q.f(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d());
        Logger.i("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "setting 下发是否为弱网执行：" + bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().F() + "，当前网络环境：2G-type=" + zE + ",3G-type=" + zF);
        return bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().F() && (zE || zF);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0117  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean K() throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 285
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.core.corelogic.e.K():boolean");
    }

    protected void a(Activity activity, c cVar) throws JSONException {
        List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> listC;
        List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f> listA;
        TTBaseAd tTBaseAd;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.h);
        if (this.H || this.f4486b.get()) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a((List<TTBaseAd>) null, this.h);
            if (cVar != null) {
                cVar.a();
                return;
            }
            return;
        }
        List<TTBaseAd> listP = p();
        try {
            listC = C();
        } catch (Throwable th) {
            th.printStackTrace();
            listC = null;
        }
        if (listC != null && listC.size() > 0) {
            HashMap<String, TTBaseAd> mapE = e(listP);
            for (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar : listC) {
                if (iVar != null) {
                    String strD = iVar.d();
                    TTBaseAd tTBaseAd2 = mapE.get(strD);
                    if (tTBaseAd2 != null && tTBaseAd2.isReady() && !tTBaseAd2.isHasShown()) {
                        Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.f, TTLogUtil.TAG_EVENT_SHOW) + "adSlotId：" + tTBaseAd2.getAdNetworkSlotId() + "，广告类型：" + com.bytedance.msdk.base.a.a(tTBaseAd2.getAdNetworkPlatformId()) + ",isReady()：" + tTBaseAd2.isReady());
                        a(tTBaseAd2, activity, (Map<TTAdConstant.GroMoreExtraKey, Object>) null);
                        return;
                    }
                    if (iVar.a() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().c(this.f, strD, e()) && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().b(strD, this.h) == 3 && (listA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().a(strD, this.h)) != null && listA.size() > 0 && (tTBaseAd = listA.get(0).f1866a) != null && tTBaseAd.isReady() && !tTBaseAd.isHasShown()) {
                        Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.f, TTLogUtil.TAG_EVENT_SHOW) + "adSlotId：" + tTBaseAd.getAdNetworkSlotId() + "，广告类型：" + com.bytedance.msdk.base.a.a(tTBaseAd.getAdNetworkPlatformId()) + ",isReady()：" + tTBaseAd.isReady());
                        a(tTBaseAd);
                        a(tTBaseAd, activity, (Map<TTAdConstant.GroMoreExtraKey, Object>) null);
                        return;
                    }
                }
            }
        }
        if (listP != null && listP.size() > 0) {
            for (TTBaseAd tTBaseAd3 : listP) {
                if (tTBaseAd3 != null && tTBaseAd3.isReady() && !tTBaseAd3.isHasShown()) {
                    Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.f, TTLogUtil.TAG_EVENT_SHOW) + "adSlotId：" + tTBaseAd3.getAdNetworkSlotId() + "，广告类型：" + com.bytedance.msdk.base.a.a(tTBaseAd3.getAdNetworkPlatformId()) + ",isReady()：" + tTBaseAd3.isReady());
                    a(tTBaseAd3, activity, (Map<TTAdConstant.GroMoreExtraKey, Object>) null);
                    return;
                }
            }
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(listP, this.h);
        if (cVar != null) {
            cVar.a();
        }
    }

    protected void b(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map, c cVar) throws JSONException {
        a(activity, map, cVar);
    }

    private void b(TTBaseAd tTBaseAd) {
        ThreadHelper.runOnUiThread(new a(tTBaseAd));
    }

    private boolean a(List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> list, List<TTBaseAd> list2, boolean z, Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
        List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f> listA;
        TTBaseAd tTBaseAd;
        List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f> listA2;
        TTBaseAd tTBaseAd2;
        HashMap<String, TTBaseAd> mapE = e(list2);
        for (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar : list) {
            if (iVar != null) {
                String strD = iVar.d();
                TTBaseAd tTBaseAd3 = mapE.get(strD);
                if (tTBaseAd3 != null) {
                    if (z) {
                        if (tTBaseAd3.isCacheSuccess()) {
                            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(this.f, tTBaseAd3.getAdNetworkSlotId()) + "弱网情况下已缓存好的广告，广告类型：" + com.bytedance.msdk.base.a.a(tTBaseAd3.getAdNetworkPlatformId()) + ",isReady()：" + tTBaseAd3.isReady());
                            if (tTBaseAd3.isReady() && !tTBaseAd3.isHasShown()) {
                                a(tTBaseAd3, activity, map);
                                b(tTBaseAd3);
                                return true;
                            }
                        }
                    } else if (tTBaseAd3.isReady() && !tTBaseAd3.isHasShown()) {
                        a(tTBaseAd3, activity, map);
                        b(tTBaseAd3);
                        return true;
                    }
                }
                if (iVar.a() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().c(this.f, strD, e()) && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().b(strD, this.h) == 3 && (listA2 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().a(strD, this.h)) != null && listA2.size() > 0 && (tTBaseAd2 = listA2.get(0).f1866a) != null) {
                    if (z) {
                        if (tTBaseAd2.isCacheSuccess()) {
                            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(this.f, tTBaseAd2.getAdNetworkSlotId()) + "弱网情况下已缓存好的广告，广告类型：" + com.bytedance.msdk.base.a.a(tTBaseAd2.getAdNetworkPlatformId()) + ",isReady()：" + tTBaseAd2.isReady());
                            if (tTBaseAd2.isReady() && !tTBaseAd2.isHasShown()) {
                                a(tTBaseAd2);
                                a(tTBaseAd2, activity, map);
                                b(tTBaseAd2);
                                return true;
                            }
                        } else {
                            continue;
                        }
                    } else if (tTBaseAd2.isReady() && !tTBaseAd2.isHasShown()) {
                        a(tTBaseAd2);
                        a(tTBaseAd2, activity, map);
                        b(tTBaseAd2);
                        return true;
                    }
                }
            }
        }
        if (z && this.I == null) {
            for (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar2 : list) {
                if (iVar2 != null) {
                    String strD2 = iVar2.d();
                    TTBaseAd tTBaseAd4 = mapE.get(strD2);
                    if (tTBaseAd4 != null && tTBaseAd4.isReady() && !tTBaseAd4.isHasShown()) {
                        Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(this.f, tTBaseAd4.getAdNetworkSlotId()) + "弱网情况下没有缓存好的广告,那么直接根据优先级展示，广告类型：" + com.bytedance.msdk.base.a.a(tTBaseAd4.getAdNetworkPlatformId()) + ",isReady()：" + tTBaseAd4.isReady());
                        a(tTBaseAd4, activity, map);
                        b(tTBaseAd4);
                        return true;
                    }
                    if (iVar2.a() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().c(this.f, strD2, e()) && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().b(strD2, this.h) == 3 && (listA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().a(strD2, this.h)) != null && listA.size() > 0 && (tTBaseAd = listA.get(0).f1866a) != null && tTBaseAd.isReady() && !tTBaseAd.isHasShown()) {
                        Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(this.f, tTBaseAd.getAdNetworkSlotId()) + "弱网情况下没有缓存好的广告,那么直接根据优先级展示，广告类型：" + com.bytedance.msdk.base.a.a(tTBaseAd.getAdNetworkPlatformId()) + ",isReady()：" + tTBaseAd.isReady());
                        a(tTBaseAd);
                        a(tTBaseAd, activity, map);
                        b(tTBaseAd);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected void a(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map, c cVar) throws JSONException {
        List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> listC;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.h);
        if (!this.H && !this.f4486b.get()) {
            boolean zL = L();
            List<TTBaseAd> listP = p();
            try {
                listC = C();
            } catch (Throwable th) {
                th.printStackTrace();
                listC = null;
            }
            if (listC == null || listC.size() <= 0 || !a(listC, listP, zL, activity, map)) {
                if (listP != null && listP.size() > 0) {
                    for (TTBaseAd tTBaseAd : listP) {
                        if (tTBaseAd != null) {
                            if (zL) {
                                if (tTBaseAd.isCacheSuccess()) {
                                    Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(this.f, tTBaseAd.getAdNetworkSlotId()) + "弱网情况下已缓存好的广告，广告类型：" + com.bytedance.msdk.base.a.a(tTBaseAd.getAdNetworkPlatformId()) + ",isReady()：" + tTBaseAd.isReady());
                                } else {
                                    continue;
                                }
                            }
                            if (tTBaseAd.isReady() && !tTBaseAd.isHasShown()) {
                                a(tTBaseAd, activity, map);
                                b(tTBaseAd);
                                return;
                            }
                        }
                    }
                    if (zL && this.I == null) {
                        for (TTBaseAd tTBaseAd2 : listP) {
                            if (tTBaseAd2 != null && tTBaseAd2.isReady() && !tTBaseAd2.isHasShown()) {
                                Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(this.f, tTBaseAd2.getAdNetworkSlotId()) + "弱网情况下没有缓存好的广告,那么直接根据优先级展示，广告类型：" + com.bytedance.msdk.base.a.a(tTBaseAd2.getAdNetworkPlatformId()) + ",isReady()：" + tTBaseAd2.isReady());
                                a(tTBaseAd2, activity, map);
                                b(tTBaseAd2);
                                return;
                            }
                        }
                    }
                }
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(listP, this.h);
                if (cVar != null) {
                    cVar.a();
                    return;
                }
                return;
            }
            return;
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a((List<TTBaseAd>) null, this.h);
        if (cVar != null) {
            cVar.a();
        }
    }

    protected void a(TTBaseAd tTBaseAd, Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
        ThreadHelper.runOnUiThread(new b(tTBaseAd, activity, map));
    }
}
