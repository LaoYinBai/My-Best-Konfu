package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1;

import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, g> f1898a;

    /* renamed from: b, reason: collision with root package name */
    private Map<String, h> f1899b;

    /* renamed from: c, reason: collision with root package name */
    private bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.e<h> f1900c;
    private bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.c<g> d;
    private Map<String, Long> e;
    private Map<String, Pair<String, String>> f;

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f1901a;

        a(String str) {
            this.f1901a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Logger.d("TTMediationSDK_IntervalShowControl", "addShowFreqctlCount ritId = " + this.f1901a);
            h hVarI = i.this.i(this.f1901a);
            if (hVarI == null || !hVarI.a()) {
                return;
            }
            Date date = new Date();
            Logger.d("TTMediationSDK_IntervalShowControl", "addShowFreqctlCount save time = " + f.b(date.getTime()));
            i.this.a(this.f1901a, date.getTime());
        }
    }

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f1903a;

        b(String str) {
            this.f1903a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Logger.d("TTMediationSDK_IntervalShowControl", "addShowFreqctlCount ritId = " + this.f1903a);
            g gVarH = i.this.h(this.f1903a);
            if (gVarH == null || !gVarH.a()) {
                return;
            }
            for (e eVar : gVarH.e()) {
                if (eVar.a() < eVar.c()) {
                    i.this.a(gVarH, eVar.d(), eVar.a() + 1);
                }
            }
        }
    }

    private static class c {

        /* renamed from: a, reason: collision with root package name */
        private static i f1905a = new i(null);
    }

    /* synthetic */ i(a aVar) {
        this();
    }

    public static i b() {
        return c.f1905a;
    }

    public void a() {
        this.f1900c = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.f(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d());
        this.d = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.d(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d());
        this.f1898a = new ConcurrentHashMap();
        this.f1899b = new ConcurrentHashMap();
        this.f = new ConcurrentHashMap();
        this.e = new ConcurrentHashMap();
    }

    public boolean c(String str) {
        Logger.d("TTMediationSDK_IntervalShowControl", "checkPacing ritId = " + str);
        h hVarI = i(str);
        if (hVarI != null && hVarI.a() && hVarI.h()) {
            long time = new Date().getTime();
            long jG = hVarI.g();
            long jG2 = hVarI.g() + hVarI.e();
            Logger.d("TTMediationSDK_IntervalShowControl", "checkPacing currentTime = " + f.b(time) + " showTime = " + f.b(jG) + " pacingBean.getPacing() = " + hVarI.e() + " pacingTime = " + f.b(jG2));
            if (time <= jG2) {
                this.e.put(str, Long.valueOf(time - jG));
                return false;
            }
        }
        return true;
    }

    public void d(String str) {
        Logger.d("TTMediationSDK_IntervalShowControl", "deleteFreqctlByRitId = " + str);
        if (this.d != null) {
            Map<String, g> map = this.f1898a;
            if (map != null) {
                map.remove(str);
            }
            this.d.a(str);
        }
    }

    public void e(String str) {
        Logger.d("TTMediationSDK_IntervalShowControl", "deletePacingByRitId = " + str);
        if (this.f1900c != null) {
            Map<String, h> map = this.f1899b;
            if (map != null) {
                map.remove(str);
            }
            this.f1900c.a(str);
        }
    }

    public Pair<String, String> f(String str) {
        return this.f.get(str);
    }

    public long g(String str) {
        Long l = this.e.get(str);
        if (l == null) {
            return -2L;
        }
        return l.longValue();
    }

    public g h(String str) {
        if (this.d != null) {
            Map<String, g> map = this.f1898a;
            g gVar = map != null ? map.get(str) : null;
            if (gVar != null) {
                return gVar;
            }
            g gVarB = this.d.b(str);
            if (gVarB != null) {
                Map<String, g> map2 = this.f1898a;
                if (map2 != null) {
                    map2.put(gVarB.b(), gVarB);
                }
                return gVarB;
            }
        }
        return null;
    }

    public h i(String str) {
        if (this.f1900c == null) {
            return null;
        }
        Map<String, h> map = this.f1899b;
        h hVar = map != null ? map.get(str) : null;
        if (hVar != null) {
            return hVar;
        }
        h hVarB = this.f1900c.b(str);
        if (hVarB != null) {
            this.f1899b.put(hVarB.b(), hVarB);
        }
        return hVarB;
    }

    public void j(String str) {
        ThreadHelper.runOnMSDKThread(new a(str));
    }

    private i() {
    }

    public void b(h hVar) {
        Logger.d("TTMediationSDK_IntervalShowControl", "saveIntervalPacingBean = " + hVar.toString());
        if (this.f1900c == null || TextUtils.isEmpty(hVar.f())) {
            return;
        }
        Map<String, h> map = this.f1899b;
        if (map != null) {
            map.put(hVar.b(), hVar);
        }
        this.f1900c.a((bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.e<h>) hVar);
    }

    public void a(String str, long j) {
        if (this.f1900c != null) {
            Map<String, h> map = this.f1899b;
            h hVar = map != null ? map.get(str) : null;
            if (hVar != null) {
                hVar.a(j);
            }
            this.f1900c.a(str, j);
        }
    }

    public boolean b(String str) {
        Logger.d("TTMediationSDK_IntervalShowControl", "checkFreqctl ritId = " + str);
        g gVarH = h(str);
        if (gVarH == null || !gVarH.a()) {
            return true;
        }
        for (e eVar : gVarH.e()) {
            Date date = new Date();
            Logger.d("TTMediationSDK_IntervalShowControl", "checkFreqctl date = " + date.getTime() + " item.getEffectiveTime() = " + eVar.b());
            if (date.getTime() <= eVar.b()) {
                if (eVar.a() >= eVar.c()) {
                    Logger.d("TTMediationSDK_IntervalShowControl", "checkFreqctl 到了上线不可以展示 ruleId = " + eVar.d() + " count = " + eVar.a());
                    this.f.put(str, new Pair<>(eVar.d(), eVar.a() + ""));
                    return false;
                }
                Logger.d("TTMediationSDK_IntervalShowControl", "checkFreqctl 未到上线可以展示 ruleId = " + eVar.d() + " count = " + eVar.a());
            } else {
                a(gVarH, eVar.d(), 0);
                a(gVarH, eVar.d(), f.a(eVar.e()));
                Logger.d("TTMediationSDK_IntervalShowControl", "checkFreqctl 有效期外计数需要清0，过期时间需要更新 = " + eVar.d());
            }
        }
        return true;
    }

    public boolean a(h hVar) {
        Logger.d("TTMediationSDK_IntervalShowControl", "pacing checkPacingUpdate ritId = " + hVar.b());
        h hVarI = i(hVar.b());
        if (hVarI == null) {
            Logger.d("TTMediationSDK_IntervalShowControl", "pacing 规则为空直接存储");
            return true;
        }
        if (TextUtils.isEmpty(hVar.f())) {
            Logger.d("TTMediationSDK_IntervalShowControl", "pacing ruleId 为空 清空本地数据 ");
            e(hVar.b());
            return false;
        }
        if (TextUtils.equals(hVarI.f(), hVar.f())) {
            Logger.d("TTMediationSDK_IntervalShowControl", "pacing ruleId 一致 ruleId = " + hVarI.f() + " 无需处理");
            return false;
        }
        Logger.d("TTMediationSDK_IntervalShowControl", "pacing ruleId不一致，缓存新规则 old ruleId = " + hVarI.f() + "  new ruleId" + hVar.f());
        StringBuilder sb = new StringBuilder();
        sb.append("pacing 删除 ritId = ");
        sb.append(hVar.b());
        Logger.d("TTMediationSDK_IntervalShowControl", sb.toString());
        e(hVar.b());
        return true;
    }

    public void c(g gVar) {
        Logger.d("TTMediationSDK_IntervalShowControl", "updateFreqctlItem bean = " + gVar.toString());
        if (this.d != null) {
            Map<String, g> map = this.f1898a;
            if (map != null) {
                map.put(gVar.b(), gVar);
            }
            this.d.b((bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.c<g>) gVar);
        }
    }

    public void a(String str) {
        ThreadHelper.runOnMSDKThread(new b(str));
    }

    public boolean a(g gVar) {
        Logger.d("TTMediationSDK_IntervalShowControl", "pacing checkFreqctlUpdate ritId = " + gVar.b());
        g gVarH = h(gVar.b());
        if (gVarH == null) {
            Logger.d("TTMediationSDK_IntervalShowControl", "freqctl 规则为空直接存储");
            return true;
        }
        if (TextUtils.isEmpty(gVar.c())) {
            Logger.d("TTMediationSDK_IntervalShowControl", "freqctl version 为空 清空本地数据 ");
            d(gVar.b());
            return false;
        }
        if (TextUtils.equals(gVarH.c(), gVar.c())) {
            Logger.d("TTMediationSDK_IntervalShowControl", "freqctl version 一致 version = " + gVarH.c() + "无需处理");
            return false;
        }
        Logger.d("TTMediationSDK_IntervalShowControl", "freqctl version不一致，缓存新规则 old version = " + gVarH.c() + "  new version " + gVar.c());
        Logger.d("TTMediationSDK_IntervalShowControl", "freqctl 需要取出json重新赋值");
        return true;
    }

    public void b(g gVar) {
        e eVar;
        Logger.d("TTMediationSDK_IntervalShowControl", "saveIntervalFreqctlBean = " + gVar.toString());
        g gVarH = h(gVar.b());
        if (gVarH == null) {
            Logger.d("TTMediationSDK_IntervalShowControl", "saveIntervalFreqctlBean 数据库中无内容直接存储");
            if (this.d != null) {
                Map<String, g> map = this.f1898a;
                if (map != null) {
                    map.put(gVar.b(), gVar);
                }
                this.d.a((bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.c<g>) gVar);
                return;
            }
            return;
        }
        HashMap map2 = new HashMap();
        for (e eVar2 : gVarH.e()) {
            map2.put(eVar2.d(), eVar2);
        }
        for (e eVar3 : gVar.e()) {
            if (map2.containsKey(eVar3.d()) && (eVar = (e) map2.get(eVar3.d())) != null) {
                eVar3.a(eVar);
            }
        }
        Map<String, g> map3 = this.f1898a;
        if (map3 != null) {
            map3.put(gVar.b(), gVar);
        }
        c(gVar);
    }

    public void a(g gVar, String str, int i) {
        Logger.d("TTMediationSDK_IntervalShowControl", "updateFreqctlCount bean = " + gVar.toString() + " ruleId = " + str + " count = " + i);
        if (this.d != null) {
            gVar.a(str, i);
            Map<String, g> map = this.f1898a;
            if (map != null) {
                map.put(gVar.b(), gVar);
            }
            this.d.a((bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.c<g>) gVar, str, i);
        }
    }

    public void a(g gVar, String str, long j) {
        Logger.d("TTMediationSDK_IntervalShowControl", "updateFreqctlTime bean = " + gVar.toString() + " ruleId = " + str + " time = " + j);
        if (this.d != null) {
            gVar.a(str, j);
            Map<String, g> map = this.f1898a;
            if (map != null) {
                map.put(gVar.b(), gVar);
            }
            this.d.a((bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.c<g>) gVar, str, j);
        }
    }
}
