package com.bytedance.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.bytedance.applog.IOaidObserver;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class o {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3534a = o.class.getSimpleName() + "#";

    /* renamed from: b, reason: collision with root package name */
    public static dt<bh> f3535b = new a();

    public static class a extends dt<bh> {
        @Override // com.bytedance.a.dt
        public bh a(Object[] objArr) {
            return new bh((Context) objArr[0]);
        }
    }

    @WorkerThread
    public static String a(SharedPreferences sharedPreferences) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        String strB = e.f3522a.b(sharedPreferences);
        t.a("TrackerDr", f3534a + "getCdid takes " + (SystemClock.elapsedRealtime() - jElapsedRealtime) + " ms", null);
        return strB;
    }

    @Nullable
    @AnyThread
    public static String a(@Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optString("id", null);
        }
        return null;
    }

    @AnyThread
    public static void a(@Nullable IOaidObserver iOaidObserver) {
        bh.a(iOaidObserver);
    }

    public static void b(IOaidObserver iOaidObserver) {
        bh.b(iOaidObserver);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0089  */
    @androidx.annotation.Nullable
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Map a(@androidx.annotation.NonNull android.content.Context r11) throws java.lang.Throwable {
        /*
            long r0 = android.os.SystemClock.elapsedRealtime()
            com.bytedance.a.dt<com.bytedance.a.bh> r2 = com.bytedance.a.o.f3535b
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = 0
            r3[r4] = r11
            java.lang.Object r11 = r2.b(r3)
            com.bytedance.a.bh r11 = (com.bytedance.a.bh) r11
            boolean r2 = r11.g
            r3 = 0
            if (r2 != 0) goto L1a
            r11 = r3
            goto La5
        L1a:
            r11.b()
            java.lang.String r2 = com.bytedance.a.bh.f3405b
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Oaid#getOaid timeoutMills="
            r5.append(r6)
            r6 = 100
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.bytedance.a.t.a(r2, r5)
            java.util.Map<java.lang.String, java.lang.String> r2 = r11.k
            if (r2 != 0) goto L8f
            long r8 = android.os.SystemClock.elapsedRealtime()
            java.util.concurrent.locks.ReentrantLock r2 = r11.e     // Catch: java.lang.Throwable -> L73 java.lang.InterruptedException -> L76
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L73 java.lang.InterruptedException -> L76
            boolean r2 = r2.tryLock(r6, r5)     // Catch: java.lang.Throwable -> L73 java.lang.InterruptedException -> L76
            java.lang.String r4 = com.bytedance.a.bh.f3405b     // Catch: java.lang.InterruptedException -> L71 java.lang.Throwable -> L86
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.InterruptedException -> L71 java.lang.Throwable -> L86
            r5.<init>()     // Catch: java.lang.InterruptedException -> L71 java.lang.Throwable -> L86
            java.lang.String r6 = "Oaid#getOaid locked="
            r5.append(r6)     // Catch: java.lang.InterruptedException -> L71 java.lang.Throwable -> L86
            r5.append(r2)     // Catch: java.lang.InterruptedException -> L71 java.lang.Throwable -> L86
            java.lang.String r6 = ", took "
            r5.append(r6)     // Catch: java.lang.InterruptedException -> L71 java.lang.Throwable -> L86
            long r6 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.InterruptedException -> L71 java.lang.Throwable -> L86
            r10 = 0
            long r6 = r6 - r8
            r5.append(r6)     // Catch: java.lang.InterruptedException -> L71 java.lang.Throwable -> L86
            java.lang.String r6 = " ms"
            r5.append(r6)     // Catch: java.lang.InterruptedException -> L71 java.lang.Throwable -> L86
            java.lang.String r5 = r5.toString()     // Catch: java.lang.InterruptedException -> L71 java.lang.Throwable -> L86
            com.bytedance.a.t.a(r4, r5, r3)     // Catch: java.lang.InterruptedException -> L71 java.lang.Throwable -> L86
            if (r2 == 0) goto L8f
            goto L80
        L71:
            r4 = move-exception
            goto L79
        L73:
            r0 = move-exception
            r2 = 0
            goto L87
        L76:
            r2 = move-exception
            r4 = r2
            r2 = 0
        L79:
            java.lang.String r5 = ""
            com.bytedance.a.de.a(r5, r4)     // Catch: java.lang.Throwable -> L86
            if (r2 == 0) goto L8f
        L80:
            java.util.concurrent.locks.ReentrantLock r2 = r11.e
            r2.unlock()
            goto L8f
        L86:
            r0 = move-exception
        L87:
            if (r2 == 0) goto L8e
            java.util.concurrent.locks.ReentrantLock r11 = r11.e
            r11.unlock()
        L8e:
            throw r0
        L8f:
            java.lang.String r2 = com.bytedance.a.bh.f3405b
            java.lang.String r4 = "Oaid#getOaid return apiMap="
            java.lang.StringBuilder r4 = com.bytedance.a.a.a(r4)
            java.util.Map<java.lang.String, java.lang.String> r5 = r11.k
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.bytedance.a.t.a(r2, r4)
            java.util.Map<java.lang.String, java.lang.String> r11 = r11.k
        La5:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = com.bytedance.a.o.f3534a
            r2.append(r4)
            java.lang.String r4 = "getOaid takes "
            r2.append(r4)
            long r4 = android.os.SystemClock.elapsedRealtime()
            long r4 = r4 - r0
            r2.append(r4)
            java.lang.String r0 = " ms"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.String r1 = "TrackerDr"
            com.bytedance.a.t.a(r1, r0, r3)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.o.a(android.content.Context):java.util.Map");
    }
}
