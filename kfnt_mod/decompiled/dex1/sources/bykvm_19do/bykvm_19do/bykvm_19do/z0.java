package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import bykvm_19do.bykvm_19do.bykvm_19do.a1;
import bykvm_19do.bykvm_19do.bykvm_19do.e;
import bykvm_19do.bykvm_19do.bykvm_19do.v0;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class z0 {
    private static final String i = z0.class.getSimpleName() + "#";
    public static final String j = i;
    private static e k;
    private static String l;

    /* renamed from: b, reason: collision with root package name */
    private a1 f1401b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1402c;
    private d1 d;
    private final Context e;
    private Map<String, String> g;
    private Long h;

    /* renamed from: a, reason: collision with root package name */
    private final ReentrantLock f1400a = new ReentrantLock();
    private AtomicBoolean f = new AtomicBoolean(false);

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            z0.this.b();
        }
    }

    z0(Context context) {
        this.e = context;
        this.f1401b = b1.a(context);
        a1 a1Var = this.f1401b;
        if (a1Var != null) {
            this.f1402c = a1Var.a(context);
        } else {
            this.f1402c = false;
        }
        this.d = new d1(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        t0.b(j, "Oaid#initOaid");
        try {
            this.f1400a.lock();
            t0.b(j, "Oaid#initOaid exec");
            c1 c1VarA = this.d.a();
            t0.b(j, "Oaid#initOaid fetch=" + c1VarA);
            if (c1VarA != null) {
                l = c1VarA.f1317a;
                this.g = c1VarA.a();
            }
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            Pair<String, Boolean> pairA = a(this.e);
            long jElapsedRealtime2 = SystemClock.elapsedRealtime() - jElapsedRealtime;
            c1 c1Var = null;
            String string = null;
            if (pairA.first != null) {
                int iIntValue = -1;
                if (c1VarA != null) {
                    string = c1VarA.f1318b;
                    iIntValue = c1VarA.f.intValue() + 1;
                }
                if (TextUtils.isEmpty(string)) {
                    string = UUID.randomUUID().toString();
                }
                String str = string;
                if (iIntValue <= 0) {
                    iIntValue = 1;
                }
                c1 c1Var2 = new c1((String) pairA.first, str, (Boolean) pairA.second, Long.valueOf(jElapsedRealtime2), Long.valueOf(System.currentTimeMillis()), Integer.valueOf(iIntValue), this.h);
                this.d.a(c1Var2);
                c1Var = c1Var2;
            }
            if (c1Var != null) {
                l = c1Var.f1317a;
                this.g = c1Var.a();
            }
            t0.b(j, "Oaid#initOaid oaidModel=" + c1Var);
        } finally {
            this.f1400a.unlock();
            a(new e.a(l));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0074  */
    @androidx.annotation.Nullable
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.Map<java.lang.String, java.lang.String> a(long r7) throws java.lang.Throwable {
        /*
            r6 = this;
            boolean r0 = r6.f1402c
            if (r0 != 0) goto L6
            r7 = 0
            return r7
        L6:
            r6.a()
            java.lang.String r0 = bykvm_19do.bykvm_19do.bykvm_19do.z0.j
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Oaid#getOaid timeoutMills="
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            bykvm_19do.bykvm_19do.bykvm_19do.t0.a(r0, r1)
            java.util.Map<java.lang.String, java.lang.String> r0 = r6.g
            if (r0 != 0) goto L7a
            long r0 = android.os.SystemClock.elapsedRealtime()
            r2 = 0
            java.util.concurrent.locks.ReentrantLock r3 = r6.f1400a     // Catch: java.lang.Throwable -> L61 java.lang.InterruptedException -> L64
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L61 java.lang.InterruptedException -> L64
            boolean r7 = r3.tryLock(r7, r4)     // Catch: java.lang.Throwable -> L61 java.lang.InterruptedException -> L64
            java.lang.String r8 = bykvm_19do.bykvm_19do.bykvm_19do.z0.j     // Catch: java.lang.Throwable -> L5c java.lang.InterruptedException -> L5e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5c java.lang.InterruptedException -> L5e
            r2.<init>()     // Catch: java.lang.Throwable -> L5c java.lang.InterruptedException -> L5e
            java.lang.String r3 = "Oaid#getOaid locked="
            r2.append(r3)     // Catch: java.lang.Throwable -> L5c java.lang.InterruptedException -> L5e
            r2.append(r7)     // Catch: java.lang.Throwable -> L5c java.lang.InterruptedException -> L5e
            java.lang.String r3 = ", took "
            r2.append(r3)     // Catch: java.lang.Throwable -> L5c java.lang.InterruptedException -> L5e
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L5c java.lang.InterruptedException -> L5e
            r5 = 0
            long r3 = r3 - r0
            r2.append(r3)     // Catch: java.lang.Throwable -> L5c java.lang.InterruptedException -> L5e
            java.lang.String r0 = " ms"
            r2.append(r0)     // Catch: java.lang.Throwable -> L5c java.lang.InterruptedException -> L5e
            java.lang.String r0 = r2.toString()     // Catch: java.lang.Throwable -> L5c java.lang.InterruptedException -> L5e
            bykvm_19do.bykvm_19do.bykvm_19do.t0.b(r8, r0)     // Catch: java.lang.Throwable -> L5c java.lang.InterruptedException -> L5e
            if (r7 == 0) goto L7a
            goto L6a
        L5c:
            r8 = move-exception
            goto L72
        L5e:
            r8 = move-exception
            r2 = r7
            goto L65
        L61:
            r8 = move-exception
            r7 = 0
            goto L72
        L64:
            r8 = move-exception
        L65:
            r8.printStackTrace()     // Catch: java.lang.Throwable -> L70
            if (r2 == 0) goto L7a
        L6a:
            java.util.concurrent.locks.ReentrantLock r7 = r6.f1400a
            r7.unlock()
            goto L7a
        L70:
            r8 = move-exception
            r7 = r2
        L72:
            if (r7 == 0) goto L79
            java.util.concurrent.locks.ReentrantLock r7 = r6.f1400a
            r7.unlock()
        L79:
            throw r8
        L7a:
            java.lang.String r7 = bykvm_19do.bykvm_19do.bykvm_19do.z0.j
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r0 = "Oaid#getOaid return apiMap="
            r8.append(r0)
            java.util.Map<java.lang.String, java.lang.String> r0 = r6.g
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            bykvm_19do.bykvm_19do.bykvm_19do.t0.a(r7, r8)
            java.util.Map<java.lang.String, java.lang.String> r7 = r6.g
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_19do.z0.a(long):java.util.Map");
    }

    static void a(e eVar) {
        k = eVar;
        String str = l;
        if (str != null) {
            a(new e.a(str));
        }
    }

    private static void a(e.a aVar) {
        e eVar;
        if (aVar == null || (eVar = k) == null) {
            return;
        }
        eVar.a(aVar);
    }

    static void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
        if (TextUtils.isEmpty(str) || obj == null) {
            return;
        }
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static <K, V> void a(Map<K, V> map, K k2, V v) {
        if (k2 == null || v == null) {
            return;
        }
        map.put(k2, v);
    }

    public void a() {
        if (this.f.compareAndSet(false, true)) {
            a(new a());
        }
    }

    @NonNull
    @WorkerThread
    private Pair<String, Boolean> a(Context context) {
        Boolean boolValueOf;
        a1.a aVarB;
        a1 a1Var = this.f1401b;
        String str = null;
        if (a1Var == null || (aVarB = a1Var.b(context)) == null) {
            boolValueOf = null;
        } else {
            str = aVarB.f1312a;
            boolValueOf = Boolean.valueOf(aVarB.f1313b);
            if (aVarB instanceof v0.c) {
                this.h = Long.valueOf(((v0.c) aVarB).f1398c);
            }
        }
        return new Pair<>(str, boolValueOf);
    }

    private static void a(Runnable runnable) {
        u0.a(j + "-query", runnable);
    }
}
