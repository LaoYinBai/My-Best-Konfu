package com.apm.insight.k;

import androidx.annotation.Nullable;
import com.apm.insight.l.p;
import com.apm.insight.l.q;
import com.apm.insight.runtime.u;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static Runnable f2891a = new Runnable() { // from class: com.apm.insight.k.a.1
        @Override // java.lang.Runnable
        public void run() {
            u uVarB;
            Runnable runnable;
            long j;
            if (p.b(com.apm.insight.h.g())) {
                a.i();
            }
            if (a.f2892b > 0) {
                if (com.apm.insight.l.a.b(com.apm.insight.h.g())) {
                    uVarB = com.apm.insight.runtime.p.b();
                    runnable = a.f2891a;
                    j = 15000;
                } else {
                    uVarB = com.apm.insight.runtime.p.b();
                    runnable = a.f2891a;
                    j = 60000;
                }
                uVarB.a(runnable, j);
            }
        }
    };

    /* renamed from: b, reason: collision with root package name */
    private static int f2892b = 0;

    public static void a() {
        f2892b = 40;
        com.apm.insight.runtime.p.b().a(f2891a);
    }

    public static void b() {
        if (!k.b()) {
            k.c();
        }
        if (p.b(com.apm.insight.h.g()) && k.a(false)) {
            i();
        }
    }

    public static boolean c() {
        return false;
    }

    public static void d() {
    }

    @Nullable
    private static byte[] h() {
        try {
            return e.a(com.apm.insight.h.i().getConfigUrl(), (Map<String, String>) null, com.apm.insight.entity.b.a().toString().getBytes());
        } catch (Throwable th) {
            q.a(th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void i() {
        if (f2892b > 0) {
            f2892b--;
        }
        q.a((Object) "try fetchApmConfig");
        if (com.apm.insight.l.a.b(com.apm.insight.h.g())) {
            JSONArray jSONArrayOptJSONArray = null;
            try {
                byte[] bArrH = h();
                if (bArrH != null) {
                    jSONArrayOptJSONArray = new JSONObject(new String(bArrH)).optJSONArray("data");
                }
            } catch (Throwable th) {
                q.a("npth", th);
            }
            q.a((Object) ("after fetchApmConfig net " + jSONArrayOptJSONArray));
            if (jSONArrayOptJSONArray != null) {
                com.apm.insight.runtime.a.a(jSONArrayOptJSONArray, true);
                f2892b = 0;
            } else {
                f2892b -= 10;
            }
        } else {
            k.c();
            if (k.a()) {
                f2892b = 0;
            }
        }
    }
}
