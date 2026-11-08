package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122;

import android.content.Context;
import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b extends bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.a {
    private static volatile b l;

    /* renamed from: c, reason: collision with root package name */
    private final Map<String, Integer> f1859c = new HashMap();
    private final Map<String, Integer> d = new HashMap();
    private final Map<String, Integer> e = new HashMap();
    private final Map<String, Long> f = new HashMap();
    private final Map<String, AdSlot> g = new HashMap();
    private final Map<String, Map<String, f>> h = new HashMap();
    private final Map<String, Map<String, AdError>> i = new HashMap();
    private final Map<String, Boolean> j = new HashMap();
    private final Map<String, c> k = new HashMap();

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f1860a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f1861b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f1862c;

        a(Context context, String str, int i) {
            this.f1860a = context;
            this.f1861b = str;
            this.f1862c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.a(this.f1860a, this.f1861b, 2, this.f1862c);
        }
    }

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b$b, reason: collision with other inner class name */
    class RunnableC0065b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f1863a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f1864b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f1865c;

        RunnableC0065b(Context context, String str, int i) {
            this.f1863a = context;
            this.f1864b = str;
            this.f1865c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.a(this.f1863a, this.f1864b, 4, this.f1865c);
        }
    }

    private b() {
    }

    public void b(String str, int i) {
        this.f1859c.put(str, Integer.valueOf(i));
    }

    public void c(String str, int i) {
        this.d.put(str, Integer.valueOf(i));
    }

    public void d(String str, int i) {
        this.e.put(str, Integer.valueOf(i));
    }

    public int e(String str) {
        Integer num = this.d.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 3;
    }

    public Long f(String str) {
        return this.f.get(str);
    }

    public List<f> g(String str) {
        ArrayList arrayList = new ArrayList();
        Map<String, f> map = this.h.get(str);
        if (map != null) {
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                f fVar = map.get(it.next());
                if (fVar != null) {
                    arrayList.add(fVar);
                }
            }
        }
        return arrayList;
    }

    public static b a() {
        if (l == null) {
            synchronized (b.class) {
                if (l == null) {
                    l = new b();
                }
            }
        }
        return l;
    }

    public void b(String str, AdSlot adSlot) {
        if (adSlot == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.g.put(str, adSlot);
    }

    public c c(String str) {
        return this.k.get(str);
    }

    public int d(String str) {
        Integer num = this.f1859c.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 1;
    }

    private void c(String str, AdSlot adSlot) {
        Map<String, f> map = this.h.get(str);
        if (map != null) {
            String adUnitId = adSlot != null ? adSlot.getAdUnitId() : "";
            for (String str2 : map.keySet()) {
                f fVar = map.get(str2);
                if (fVar != null && fVar.f1866a != null) {
                    if (a(adUnitId, fVar)) {
                        map.put(str2, null);
                        Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(str) + "--==-- 预缓存：广告过期了 ---: " + fVar.f1866a.getAdNetWorkName() + ", loadSort: " + fVar.f1866a.getLoadSort() + ", showSort: " + fVar.f1866a.getShowSort());
                    } else if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.a.a(this.g.get(str), adSlot, fVar.f1866a)) {
                        map.put(str2, null);
                        Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(str) + "--==-- 预缓存：广告AdSlot不符合 ---: " + fVar.f1866a.getAdNetWorkName() + ", loadSort: " + fVar.f1866a.getLoadSort() + ", showSort: " + fVar.f1866a.getShowSort());
                    } else if (b(adUnitId, str2) != 0 && (!fVar.f1866a.isAdCacheReady() || fVar.f1866a.isHasShown())) {
                        map.put(str2, null);
                        Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(str) + "--==-- 预缓存：isReady为false ---: " + fVar.f1866a.getAdNetWorkName() + ", loadSort: " + fVar.f1866a.getLoadSort() + ", showSort: " + fVar.f1866a.getShowSort());
                    }
                }
            }
        }
    }

    public void b(String str, f fVar) {
        if (f(str) == null || f(str).longValue() == fVar.c()) {
            Map<String, f> map = this.h.get(str);
            if (map == null) {
                map = new HashMap<>();
                this.h.put(str, map);
            }
            map.put(fVar.f1866a.getAdNetworkSlotId(), fVar);
        }
    }

    public int a(String str, int i) {
        Integer num;
        if (i == 2 || (num = this.e.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    public void a(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f.put(str, Long.valueOf(j));
    }

    public AdSlot a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.g.get(str);
    }

    public Map<String, f> b(String str) {
        return this.h.get(str);
    }

    public void b(Context context, String str, int i) {
        ThreadHelper.runOnMSDKThread(new RunnableC0065b(context, str, i));
    }

    public void a(String str, String str2, long j, AdError adError) {
        if (f(str) == null || f(str).longValue() == j) {
            Map<String, AdError> map = this.i.get(str);
            if (map == null) {
                map = new HashMap<>();
                this.i.put(str, map);
            }
            map.put(str2, adError);
        }
    }

    public boolean a(String str, AdSlot adSlot) {
        this.j.put(str, false);
        c cVar = this.k.get(str);
        if (cVar != null) {
            List<i> listC = cVar.C();
            if (Logger.isDebug()) {
                for (i iVar : listC) {
                    Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(str) + "waterfall: " + iVar.e() + ", loadSort: " + iVar.m() + ", showSort: " + iVar.w() + ", eCpm: " + iVar.h());
                }
            }
            a(listC, str, adSlot, cVar);
            c(str, adSlot);
            Map<String, f> map = this.h.get(str);
            if (map != null) {
                if (cVar.g() == 1) {
                    for (int i = 0; i < e(str) && i < listC.size(); i++) {
                        if (map.get(listC.get(i).d()) != null) {
                            return true;
                        }
                    }
                } else {
                    for (int i2 = 0; i2 < listC.size(); i2++) {
                        if (map.get(listC.get(i2).d()) != null) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public f c(String str, String str2) {
        Map<String, f> map = this.h.get(str);
        if (map != null) {
            return map.get(str2);
        }
        return null;
    }

    private void a(List<i> list, String str, AdSlot adSlot, c cVar) {
        if (list.size() <= 0) {
            return;
        }
        Map<String, f> map = this.h.get(str);
        if (map != null) {
            f fVar = map.get(list.get(0).d());
            String adUnitId = adSlot != null ? adSlot.getAdUnitId() : "";
            if (fVar != null && !a(adUnitId, fVar) && fVar.f1866a.isAdCacheReady() && !fVar.f1866a.isHasShown()) {
                Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(str) + "--==-- 命中最优广告： " + fVar.f1866a.getAdNetWorkName() + ", loadSort: " + fVar.f1866a.getLoadSort() + ", showSort: " + fVar.f1866a.getShowSort());
                return;
            }
        }
        List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.a> listA = a(list, str, map, adSlot, cVar);
        if (listA.size() > 0) {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < listA.size(); i++) {
                sb.append(listA.get(i).a());
                if (i == listA.size() - 1) {
                    sb.append("]");
                } else {
                    sb.append(IWeiboService.Scope.EMPTY_SCOPE);
                }
            }
            try {
                JSONArray jSONArray = new JSONArray(sb.toString());
                Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(str) + "adCannotUseInfo: " + jSONArray.toString());
                g.a(this.g.get(str), jSONArray.toString());
            } catch (JSONException e) {
                Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(str) + "adCannotUseInfo json err: " + e.getMessage());
            }
        }
    }

    private List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.a> a(List<i> list, String str, Map<String, f> map, AdSlot adSlot, c cVar) {
        int iE;
        TTBaseAd tTBaseAd;
        ArrayList arrayList = new ArrayList();
        if (cVar.g() == 2 && (cVar instanceof e)) {
            iE = ((e) cVar).J();
        } else {
            iE = e(str);
        }
        for (int i = 0; i < list.size() && i < iE; i++) {
            String strD = list.get(i).d();
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.a aVar = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.a();
            aVar.b(strD);
            aVar.e(list.get(i).w());
            aVar.c(list.get(i).m());
            f fVar = map != null ? map.get(strD) : null;
            if (fVar != null && (tTBaseAd = fVar.f1866a) != null) {
                aVar.b(tTBaseAd.isHasShown() ? 1 : 0);
                int iA = a(fVar, this.g.get(str), adSlot, "预缓存");
                if (iA != -1) {
                    aVar.d(iA);
                    arrayList.add(aVar);
                }
            } else {
                Map<String, AdError> map2 = this.i.get(str);
                AdError adError = map2 != null ? map2.get(strD) : null;
                if (adError != null) {
                    aVar.d(3);
                    aVar.a(adError.thirdSdkErrorCode);
                    aVar.a(adError.thirdSdkErrorMessage);
                } else {
                    aVar.d(4);
                }
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    public void a(Context context, String str, int i) {
        ThreadHelper.runOnMSDKThread(new a(context, str, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str, int i, int i2) {
        c eVar;
        int iA = a(str, i2);
        if (iA == 0) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(str) + "--==-- 配置未开启预加载缓存 ----");
            return;
        }
        if (this.j.get(str) == null || !this.j.get(str).booleanValue()) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(str) + "--==-- 预缓存开始请求, req_type: " + iA);
            Map<String, f> map = this.h.get(str);
            if (map != null) {
                map.clear();
            }
            Map<String, AdError> map2 = this.i.get(str);
            if (map2 != null) {
                map2.clear();
            }
            if (iA == 1) {
                eVar = new d(context, str);
            } else if (iA == 2) {
                eVar = new e(context, str);
            } else {
                Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(str) + "--==-- 预加载配置错误，req_type：" + iA);
                return;
            }
            this.k.put(str, eVar);
            this.j.put(str, true);
            AdSlot shallowCopy = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.b.getShallowCopy(this.g.get(str));
            if (shallowCopy == null) {
                Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(str) + "--==-- preload fail，adslot为null，rit：" + str);
                return;
            }
            shallowCopy.setPrimeRitReqType(i);
            eVar.a(shallowCopy);
        }
    }
}
