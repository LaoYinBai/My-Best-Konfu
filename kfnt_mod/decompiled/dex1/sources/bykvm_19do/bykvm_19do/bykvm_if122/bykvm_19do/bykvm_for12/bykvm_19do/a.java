package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.h;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.v2.GMNetworkRequestInfo;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.a {
    private static volatile a j;

    /* renamed from: c, reason: collision with root package name */
    private final String[] f1850c = {"unity", "ks"};
    private final Map<String, Integer> d = new HashMap();
    private final Map<String, Integer> e = new HashMap();
    private final Map<String, CopyOnWriteArrayList<f>> f = new ConcurrentHashMap();
    private final Map<String, i> g = new HashMap();
    private final Map<String, String> h = new HashMap();
    private final Map<String, Integer> i = new HashMap();

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a$a, reason: collision with other inner class name */
    class RunnableC0064a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f1851a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ AdSlot f1852b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1853c;
        final /* synthetic */ Map d;
        final /* synthetic */ Context e;
        final /* synthetic */ GMNetworkRequestInfo f;
        final /* synthetic */ h g;

        RunnableC0064a(String str, AdSlot adSlot, boolean z, Map map, Context context, GMNetworkRequestInfo gMNetworkRequestInfo, h hVar) {
            this.f1851a = str;
            this.f1852b = adSlot;
            this.f1853c = z;
            this.d = map;
            this.e = context;
            this.f = gMNetworkRequestInfo;
            this.g = hVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            f fVar;
            List<f> list = (List) a.this.f.get(this.f1851a);
            ArrayList arrayList = new ArrayList();
            if (list != null && list.size() > 0) {
                for (f fVar2 : list) {
                    if (fVar2 != null && fVar2.f1866a.isHasShown()) {
                        list.remove(fVar2);
                        arrayList.add(fVar2);
                    }
                }
            }
            if (Logger.isDebug()) {
                String strA = "";
                String adNetWorkName = "";
                if (arrayList.size() > 0 && (fVar = (f) arrayList.get(0)) != null) {
                    strA = com.bytedance.msdk.base.a.a(fVar.f1866a.getAdType(), fVar.f1866a.getSubAdType());
                    adNetWorkName = fVar.f1866a.getAdNetWorkName();
                }
                Logger.d("TTMediationSDK", "--==-- 广告复用:show时缓存移除 -----：" + adNetWorkName + ", " + strA + ", " + this.f1851a);
            }
            AdSlot adSlot = this.f1852b;
            String adUnitId = adSlot != null ? adSlot.getAdUnitId() : "";
            AdSlot adSlot2 = this.f1852b;
            boolean z = adSlot2 == null || adSlot2.getAdType() == 1;
            if (this.f1853c && a.this.d(adUnitId, this.f1851a) && !z) {
                a.this.a(this.f1851a, this.f1852b, this.d, this.e, this.f, this.g);
                return;
            }
            Logger.d("TTMediationSDK", "--==-- 广告复用:show时预请求取消，因为：已发起waterfall预加载，或feed多广告，或未开启adn预加载 --: " + this.f1851a);
        }
    }

    private a() {
    }

    public synchronized int b(String str, AdSlot adSlot) {
        String adUnitId;
        if (adSlot != null) {
            try {
                adUnitId = adSlot.getAdUnitId();
            } catch (Throwable th) {
                throw th;
            }
        } else {
            adUnitId = "";
        }
        if (b(adUnitId, str) == 0) {
            return c(str, adSlot);
        }
        CopyOnWriteArrayList<f> copyOnWriteArrayList = this.f.get(str);
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            Logger.d("TTMediationSDK", "--==-- 广告复用:当前没有广告缓存 -------adnSlotId: " + str);
            return 1;
        }
        ArrayList arrayList = new ArrayList();
        f fVar = null;
        boolean z = false;
        for (f fVar2 : copyOnWriteArrayList) {
            int iA = a(fVar2, fVar2.a(), adSlot, "广告复用");
            if (iA == -1) {
                z = true;
            } else {
                if (iA != 6) {
                    copyOnWriteArrayList.remove(fVar2);
                }
                if (fVar == null) {
                    fVar = fVar2;
                }
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.a aVar = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.a();
                aVar.c(fVar2.f1866a.getLoadSort());
                aVar.e(fVar2.f1866a.getShowSort());
                aVar.b(fVar2.f1866a.getAdNetworkSlotId());
                aVar.d(iA);
                aVar.b(fVar2.f1866a.isHasShown() ? 1 : 0);
                arrayList.add(aVar);
            }
        }
        if (z) {
            return 3;
        }
        if (fVar != null) {
            g.a(adSlot, fVar.f1866a, a(arrayList));
        }
        return 2;
    }

    public boolean c(String str, String str2, int i) {
        if (i == 2) {
            return false;
        }
        Integer num = this.d.get(str + "_" + str2);
        return num != null && num.intValue() == 1;
    }

    public void d(String str, String str2, int i) {
        this.e.put(str + "_" + str2, Integer.valueOf(i));
    }

    public void e(String str, String str2, int i) {
        this.d.put(str + "_" + str2, Integer.valueOf(i));
    }

    public boolean d(String str, String str2) {
        Integer num = this.e.get(str + "_" + str2);
        return num != null && num.intValue() == 1;
    }

    public void e(String str, String str2) {
        this.h.put(str, str2);
    }

    private class b implements TTAbsAdLoaderAdapter.AdapterLoaderListener {

        /* renamed from: a, reason: collision with root package name */
        private AdSlot f1854a;

        b(AdSlot adSlot) {
            this.f1854a = adSlot;
        }

        @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter.AdapterLoaderListener
        public void onAdFailed(AdError adError, d dVar) {
            if (adError == null || dVar == null) {
                return;
            }
            Logger.d("TTMediationSDK", "--==-- 广告复用:show时预请求广告缓存失败 --- " + dVar.c() + ", " + dVar.d() + ", " + com.bytedance.msdk.base.a.a(dVar.b(), dVar.g()) + ", errCode: " + adError.thirdSdkErrorCode + ",msg=" + adError.thirdSdkErrorMessage);
        }

        @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter.AdapterLoaderListener
        public void onAdLoaded(TTBaseAd tTBaseAd, d dVar) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(tTBaseAd);
            onAdLoaded(arrayList, dVar);
        }

        @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter.AdapterLoaderListener
        public void onAdVideoCache() {
        }

        @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter.AdapterLoaderListener
        public void onAdLoaded(List<TTBaseAd> list, d dVar) {
            if (list == null || list.size() <= 0) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (TTBaseAd tTBaseAd : list) {
                if (tTBaseAd != null) {
                    arrayList.add(new f(tTBaseAd, dVar, 0L, SystemClock.elapsedRealtime(), this.f1854a));
                }
            }
            if (arrayList.size() > 0) {
                TTBaseAd tTBaseAd2 = ((f) arrayList.get(0)).f1866a;
                Logger.d("TTMediationSDK", "--==-- 广告复用:show时预请求广告缓存成功 --- " + tTBaseAd2.getAdNetWorkName() + ", adType: " + com.bytedance.msdk.base.a.a(tTBaseAd2.getAdType(), tTBaseAd2.getSubAdType()) + ", adnSlotId: " + tTBaseAd2.getAdNetworkSlotId() + ", ad个数: " + arrayList.size());
                a.this.a(tTBaseAd2.getAdNetworkSlotId(), arrayList);
            }
        }
    }

    public static a a() {
        if (j == null) {
            synchronized (a.class) {
                if (j == null) {
                    j = new a();
                }
            }
        }
        return j;
    }

    public i c(String str, String str2) {
        return this.g.get(str + "_" + str2);
    }

    private int c(String str, AdSlot adSlot) {
        int i;
        CopyOnWriteArrayList<f> copyOnWriteArrayList = this.f.get(str);
        if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
            String adUnitId = adSlot != null ? adSlot.getAdUnitId() : "";
            ArrayList arrayList = new ArrayList();
            f fVar = null;
            boolean z = false;
            Iterator<f> it = copyOnWriteArrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                f next = it.next();
                if (!a(adUnitId, next)) {
                    if (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.a.a(next.a(), adSlot, next.f1866a)) {
                        Logger.d("TTMediationSDK", "--==-- 广告复用:复用成功：--------" + next.f1866a.getAdNetWorkName() + ", adSlotId: " + str);
                        z = true;
                    } else {
                        i = 6;
                        Logger.d("TTMediationSDK", "--==-- 广告复用:AdSlot不符合 -------: " + next.f1866a.getAdNetWorkName() + ", adSlotId: " + str);
                    }
                } else {
                    copyOnWriteArrayList.remove(next);
                    i = next.f1866a.adnHasAdExpiredApi() ? 1 : 2;
                    Logger.d("TTMediationSDK", "--==-- 广告复用:广告过期了 -------: " + next.f1866a.getAdNetWorkName() + ", adSlotId: " + str);
                }
                if (fVar == null) {
                    fVar = next;
                }
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.a aVar = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.a();
                aVar.c(next.f1866a.getLoadSort());
                aVar.e(next.f1866a.getShowSort());
                aVar.b(next.f1866a.getAdNetworkSlotId());
                aVar.d(i);
                aVar.b(next.f1866a.isHasShown() ? 1 : 0);
                arrayList.add(aVar);
            }
            if (z) {
                return 3;
            }
            if (fVar != null) {
                g.a(adSlot, fVar.f1866a, a(arrayList));
            }
            return 2;
        }
        Logger.d("TTMediationSDK", "--==-- 广告复用:当前没有广告缓存 -------adnSlotId: " + str);
        return 1;
    }

    public void a(String str, String str2, i iVar, int i) {
        if (iVar != null) {
            this.g.put(str + "_" + str2, iVar);
        }
        this.i.put(str2, Integer.valueOf(i));
    }

    public String a(String str) {
        return this.h.get(str);
    }

    public void a(String str, AdSlot adSlot, Map<String, Object> map, boolean z, GMNetworkRequestInfo gMNetworkRequestInfo, h hVar, Context context) {
        ThreadHelper.runOnMSDKThread(new RunnableC0064a(str, adSlot, z, map, context, gMNetworkRequestInfo, hVar));
    }

    public void a(String str, List<f> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        CopyOnWriteArrayList<f> copyOnWriteArrayList = this.f.get(str);
        if (copyOnWriteArrayList != null) {
            for (f fVar : list) {
                if (!a(copyOnWriteArrayList, fVar)) {
                    copyOnWriteArrayList.add(fVar);
                }
            }
            return;
        }
        this.f.put(str, new CopyOnWriteArrayList<>(list));
    }

    private boolean a(List<f> list, f fVar) {
        for (f fVar2 : list) {
            if (fVar2 != null && fVar != null && fVar2.f1866a == fVar.f1866a) {
                return true;
            }
        }
        return false;
    }

    public synchronized List<f> a(String str, AdSlot adSlot) {
        CopyOnWriteArrayList<f> copyOnWriteArrayList = this.f.get(str);
        if (copyOnWriteArrayList == null) {
            return null;
        }
        int adCount = adSlot != null ? adSlot.getAdCount() : 1;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; arrayList.size() < adCount && i < copyOnWriteArrayList.size(); i++) {
            if (!copyOnWriteArrayList.get(i).d() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.a.a(copyOnWriteArrayList.get(i).a(), adSlot, copyOnWriteArrayList.get(i).f1866a)) {
                arrayList.add(copyOnWriteArrayList.get(i));
            }
        }
        for (int i2 = 0; arrayList.size() < adCount && i2 < copyOnWriteArrayList.size(); i2++) {
            if (copyOnWriteArrayList.get(i2).d() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.a.a(copyOnWriteArrayList.get(i2).a(), adSlot, copyOnWriteArrayList.get(i2).f1866a)) {
                arrayList.add(copyOnWriteArrayList.get(i2));
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((f) it.next()).a(true);
        }
        return arrayList;
    }

    private String a(List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.a> list) {
        if (list.size() > 0) {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i).a());
                if (i == list.size() - 1) {
                    sb.append("]");
                } else {
                    sb.append(IWeiboService.Scope.EMPTY_SCOPE);
                }
            }
            try {
                return new JSONArray(sb.toString()).toString();
            } catch (JSONException e) {
                Logger.d("TTMediationSDK", "--==-- 广告复用：adCannotUseInfo json err: " + e.getMessage());
            }
        }
        return list.size() > 0 ? "json error" : "[]";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, AdSlot adSlot, Map<String, Object> map, Context context, GMNetworkRequestInfo gMNetworkRequestInfo, h hVar) {
        String strA;
        if (adSlot == null) {
            Logger.d("TTMediationSDK", "--==-- 广告复用:show时预请求取消，adSlot为空 -------: " + str);
            return;
        }
        i iVarC = c(adSlot.getAdUnitId(), str);
        if (iVarC != null) {
            try {
                strA = com.bytedance.msdk.core.corelogic.f.a(iVarC.n(), com.bytedance.msdk.core.corelogic.f.a(iVarC.e()), com.bytedance.msdk.base.a.a(iVarC.r(), iVarC.x()));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            strA = null;
        }
        if (!TextUtils.isEmpty(strA) && com.bytedance.msdk.core.corelogic.f.b(strA) && iVarC != null) {
            for (String str2 : this.f1850c) {
                if (TextUtils.equals(str2, iVarC.e())) {
                    Logger.d("TTMediationSDK", "--==-- 广告复用:show时预请求取消，" + str2 + "为单例模式 -------: " + str);
                    return;
                }
            }
            int iIntValue = this.i.get(str) != null ? this.i.get(str).intValue() : -1;
            Logger.d("TTMediationSDK", "--==-- 广告复用:show时预请求开始 -------: " + str);
            TTAbsAdLoaderAdapter tTAbsAdLoaderAdapterA = com.bytedance.msdk.core.corelogic.f.a((Map<String, TTAbsAdLoaderAdapter>) null, iVarC, strA);
            if (tTAbsAdLoaderAdapterA != null) {
                tTAbsAdLoaderAdapterA.setAdapterListener(new b(adSlot));
                tTAbsAdLoaderAdapterA.loadAdInter(context, iVarC, com.bytedance.msdk.core.corelogic.f.a(iVarC, adSlot, map, null, gMNetworkRequestInfo, 4, 1, true), adSlot, hVar, iIntValue);
                Logger.d("TTMediationSDK", "--==-- 广告复用:show时预请求已发完 -------: " + str);
                return;
            }
            g.a(iVarC, adSlot, 4, 1);
            return;
        }
        Logger.d("TTMediationSDK", "--==-- 广告复用:show时预请求取消，className或wfcBean为空, className: " + strA + ", wfcBean: " + iVarC + ", adnSlotId: " + str);
    }
}
