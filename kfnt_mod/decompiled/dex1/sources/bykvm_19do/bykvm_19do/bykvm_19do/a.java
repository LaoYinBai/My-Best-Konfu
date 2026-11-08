package bykvm_19do.bykvm_19do.bykvm_19do;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bytedance.mapplog.collector.Collector;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    private static volatile i2 f1309a = null;

    /* renamed from: b, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    public static j2 f1310b = null;

    /* renamed from: c, reason: collision with root package name */
    private static boolean f1311c = true;
    private static volatile d e;
    public static b g;
    private static e0 d = new e0();
    public static ConcurrentHashMap<String, String> f = new ConcurrentHashMap<>(4);

    public static void a(Context context, h hVar) {
        if (f1309a != null) {
            l0.a(new RuntimeException("Init Twice!"));
            return;
        }
        if (hVar.q() == null) {
            l0.a(new RuntimeException("need to involve setSensitiveInfoProvider!"));
            return;
        }
        Application application = (Application) context.getApplicationContext();
        u1 u1VarI = u1.i();
        i2 i2Var = new i2(application, hVar);
        j2 j2Var = new j2(application, i2Var);
        u1VarI.a(application, i2Var, j2Var, new j0(hVar.m()));
        f1309a = i2Var;
        f1310b = j2Var;
        if (f1309a.G()) {
            application.registerReceiver(new Collector(), new IntentFilter());
        }
        l0.d("Inited", null);
    }

    public static JSONObject b() {
        if (f1309a != null) {
            return f1309a.J();
        }
        return null;
    }

    public static String c() {
        if (f1309a != null) {
            return f1309a.I();
        }
        return null;
    }

    public static String d() {
        j2 j2Var = f1310b;
        if (j2Var != null) {
            return j2Var.l();
        }
        return null;
    }

    public static boolean e() {
        return f1311c;
    }

    @NonNull
    public static c f() {
        return d;
    }

    public static String g() {
        j2 j2Var = f1310b;
        return j2Var != null ? j2Var.g() : "";
    }

    public static boolean h() {
        return true;
    }

    public static d i() {
        return e;
    }

    public static b j() {
        return g;
    }

    public static h k() {
        if (f1309a != null) {
            return f1309a.o();
        }
        return null;
    }

    public static int l() {
        if (f1309a != null) {
            return f1309a.x();
        }
        return 0;
    }

    public static void a() {
        u1.h();
    }

    public static <T> T a(String str, T t) {
        j2 j2Var = f1310b;
        if (j2Var != null) {
            return (T) j2Var.a(str, t);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(java.lang.String r3) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r1 = 8192(0x2000, float:1.148E-41)
            r0.<init>(r1)
            r1 = 0
            boolean r2 = h()     // Catch: java.lang.Throwable -> L30
            if (r2 == 0) goto L21
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L30
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L30
            java.lang.String r1 = "UTF-8"
            byte[] r3 = r3.getBytes(r1)     // Catch: java.lang.Throwable -> L1e
            r2.write(r3)     // Catch: java.lang.Throwable -> L1e
            r1 = r2
            goto L2a
        L1e:
            r3 = move-exception
            r1 = r2
            goto L31
        L21:
            java.lang.String r2 = "UTF-8"
            byte[] r3 = r3.getBytes(r2)     // Catch: java.lang.Throwable -> L30
            r0.write(r3)     // Catch: java.lang.Throwable -> L30
        L2a:
            if (r1 == 0) goto L3e
            r1.close()     // Catch: java.io.IOException -> L3a
            goto L3e
        L30:
            r3 = move-exception
        L31:
            bykvm_19do.bykvm_19do.bykvm_19do.l0.a(r3)     // Catch: java.lang.Throwable -> L4e
            if (r1 == 0) goto L3e
            r1.close()     // Catch: java.io.IOException -> L3a
            goto L3e
        L3a:
            r3 = move-exception
            bykvm_19do.bykvm_19do.bykvm_19do.l0.a(r3)
        L3e:
            byte[] r3 = r0.toByteArray()
            boolean r0 = h()
            if (r0 == 0) goto L4d
            int r0 = r3.length
            byte[] r3 = com.bytedance.mapplog.util.TTEncryptUtils.a(r3, r0)
        L4d:
            return r3
        L4e:
            r3 = move-exception
            if (r1 == 0) goto L59
            r1.close()     // Catch: java.io.IOException -> L55
            goto L59
        L55:
            r0 = move-exception
            bykvm_19do.bykvm_19do.bykvm_19do.l0.a(r0)
        L59:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_19do.a.a(java.lang.String):byte[]");
    }

    public static void a(String str, JSONObject jSONObject) {
        if (TextUtils.isEmpty(str)) {
            l0.a("eventName is empty", null);
            u1.a(new c0("", OnekeyLoginConstants.UNICOM_TYPE, 1));
        }
        u1.a(new y(str, false, jSONObject != null ? jSONObject.toString() : null));
    }

    public static void a(String str, Bundle bundle) {
        JSONObject jSONObject = null;
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        for (String str2 : bundle.keySet()) {
                            jSONObject2.put(str2, bundle.get(str2));
                        }
                        jSONObject = jSONObject2;
                    } catch (Throwable th) {
                        th = th;
                        jSONObject = jSONObject2;
                        l0.a(th);
                        a(str, jSONObject);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        a(str, jSONObject);
    }

    public static void a(c cVar) {
        d.a(cVar);
    }

    public static void a(e eVar) {
        s0.a(eVar);
    }
}
