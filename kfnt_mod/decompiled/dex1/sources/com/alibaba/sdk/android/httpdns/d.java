package com.alibaba.sdk.android.httpdns;

import com.alibaba.sdk.android.httpdns.probe.IPProbeItem;
import com.alibaba.sdk.android.httpdns.probe.IPProbeService;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class d {

    /* renamed from: a, reason: collision with root package name */
    private static d f2332a = new d();

    /* renamed from: a, reason: collision with other field name */
    private static IPProbeService f14a = com.alibaba.sdk.android.httpdns.probe.d.a(new com.alibaba.sdk.android.httpdns.probe.b() { // from class: com.alibaba.sdk.android.httpdns.d.1
        @Override // com.alibaba.sdk.android.httpdns.probe.b
        public void a(String str, String[] strArr) {
            e eVar;
            if (str == null || strArr == null || strArr.length == 0 || (eVar = (e) d.f15a.get(str)) == null) {
                return;
            }
            e eVar2 = new e(str, strArr, eVar.a(), eVar.b());
            d.f15a.put(str, eVar2);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < eVar2.m24a().length; i++) {
                sb.append(eVar2.m24a()[i] + IWeiboService.Scope.EMPTY_SCOPE);
            }
            i.f("optimized host:" + str + ", ip:" + sb.toString());
        }
    });

    /* renamed from: a, reason: collision with other field name */
    private static ConcurrentMap<String, e> f15a;

    /* renamed from: a, reason: collision with other field name */
    private static ConcurrentSkipListSet<String> f16a;

    private d() {
        f15a = new ConcurrentHashMap();
        f16a = new ConcurrentSkipListSet<>();
    }

    static d a() {
        return f2332a;
    }

    private IPProbeItem a(String str) {
        List<IPProbeItem> list = f.f21a;
        if (list == null) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(list.get(i).getHostName())) {
                return list.get(i);
            }
        }
        return null;
    }

    private boolean a(com.alibaba.sdk.android.httpdns.b.e eVar) {
        return (System.currentTimeMillis() / 1000) - com.alibaba.sdk.android.httpdns.b.c.a(eVar.j) > 604800;
    }

    private boolean a(String str, e eVar) {
        IPProbeItem iPProbeItemA;
        if (eVar == null || eVar.m24a() == null || eVar.m24a().length <= 1 || f14a == null || (iPProbeItemA = a(str)) == null) {
            return false;
        }
        if (f14a.getProbeStatus(str) == IPProbeService.a.PROBING) {
            f14a.stopIPProbeTask(str);
        }
        i.f("START PROBE");
        f14a.launchIPProbeTask(str, iPProbeItemA.getPort(), eVar.m24a());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        List<com.alibaba.sdk.android.httpdns.b.e> listA = com.alibaba.sdk.android.httpdns.b.b.a();
        String strG = com.alibaba.sdk.android.httpdns.b.b.g();
        for (com.alibaba.sdk.android.httpdns.b.e eVar : listA) {
            if (a(eVar)) {
                com.alibaba.sdk.android.httpdns.b.b.b(eVar);
            } else if (strG.equals(eVar.i)) {
                eVar.j = String.valueOf(System.currentTimeMillis() / 1000);
                e eVar2 = new e(eVar);
                f15a.put(eVar.h, eVar2);
                com.alibaba.sdk.android.httpdns.b.b.b(eVar);
                a(eVar.h, eVar2);
            }
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    int m15a() {
        return f15a.size();
    }

    /* renamed from: a, reason: collision with other method in class */
    e m16a(String str) {
        return f15a.get(str);
    }

    /* renamed from: a, reason: collision with other method in class */
    ArrayList<String> m17a() {
        return new ArrayList<>(f15a.keySet());
    }

    /* renamed from: a, reason: collision with other method in class */
    void m18a() {
        if (com.alibaba.sdk.android.httpdns.b.b.m7a()) {
            c.a().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        d.this.b();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    void m19a(String str) {
        f16a.add(str);
    }

    /* renamed from: a, reason: collision with other method in class */
    void m20a(String str, e eVar) {
        f15a.put(str, eVar);
        if (com.alibaba.sdk.android.httpdns.b.b.m7a()) {
            com.alibaba.sdk.android.httpdns.b.e eVarM23a = eVar.m23a();
            if ((eVarM23a.f2328a == null || eVarM23a.f2328a.size() <= 0) && (eVarM23a.f2329b == null || eVarM23a.f2329b.size() <= 0)) {
                com.alibaba.sdk.android.httpdns.b.b.b(eVarM23a);
            } else {
                com.alibaba.sdk.android.httpdns.b.b.a(eVarM23a);
            }
        }
        a(str, eVar);
    }

    /* renamed from: a, reason: collision with other method in class */
    boolean m21a(String str) {
        return f16a.contains(str);
    }

    void b(String str) {
        f16a.remove(str);
    }

    void clear() {
        f15a.clear();
        f16a.clear();
    }
}
