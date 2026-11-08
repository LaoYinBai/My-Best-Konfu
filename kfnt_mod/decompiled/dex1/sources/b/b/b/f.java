package b.b.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public static final String f1162a = f.class.getSimpleName() + "#";

    /* renamed from: b, reason: collision with root package name */
    public static b<o> f1163b = new a();

    public static class a extends b<o> {
        @Override // b.b.b.b
        public o a(Object[] objArr) {
            return new o((Context) objArr[0]);
        }
    }

    @WorkerThread
    public static String a(SharedPreferences sharedPreferences) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        String strB = d.f1161a.b(sharedPreferences);
        g.b("TrackerDr", f1162a + "getCdid takes " + (SystemClock.elapsedRealtime() - jElapsedRealtime) + " ms");
        return strB;
    }

    @AnyThread
    public static void a(@Nullable b.b.a.e eVar) {
        o.a(eVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0088  */
    @androidx.annotation.Nullable
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Map a(@androidx.annotation.NonNull android.content.Context r10) throws java.lang.Throwable {
        /*
            long r0 = android.os.SystemClock.elapsedRealtime()
            b.b.b.b<b.b.b.o> r2 = b.b.b.f.f1163b
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = 0
            r3[r4] = r10
            java.lang.Object r10 = r2.b(r3)
            b.b.b.o r10 = (b.b.b.o) r10
            boolean r2 = r10.g
            if (r2 != 0) goto L19
            r10 = 0
            goto La4
        L19:
            r10.b()
            java.lang.String r2 = b.b.b.o.f1173b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "Oaid#getOaid timeoutMills="
            r3.append(r5)
            r5 = 100
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            b.b.b.g.a(r2, r3)
            java.util.Map<java.lang.String, java.lang.String> r2 = r10.k
            if (r2 != 0) goto L8e
            long r2 = android.os.SystemClock.elapsedRealtime()
            java.util.concurrent.locks.ReentrantLock r7 = r10.e     // Catch: java.lang.Throwable -> L75 java.lang.InterruptedException -> L78
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L75 java.lang.InterruptedException -> L78
            boolean r5 = r7.tryLock(r5, r8)     // Catch: java.lang.Throwable -> L75 java.lang.InterruptedException -> L78
            java.lang.String r4 = b.b.b.o.f1173b     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            r6.<init>()     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.String r7 = "Oaid#getOaid locked="
            r6.append(r7)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            r6.append(r5)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.String r7 = ", took "
            r6.append(r7)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            long r7 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            r9 = 0
            long r7 = r7 - r2
            r6.append(r7)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.String r2 = " ms"
            r6.append(r2)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.String r2 = r6.toString()     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            b.b.b.g.b(r4, r2)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            if (r5 == 0) goto L8e
            goto L7e
        L70:
            r0 = move-exception
            goto L86
        L72:
            r2 = move-exception
            r4 = r5
            goto L79
        L75:
            r0 = move-exception
            r5 = 0
            goto L86
        L78:
            r2 = move-exception
        L79:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L84
            if (r4 == 0) goto L8e
        L7e:
            java.util.concurrent.locks.ReentrantLock r2 = r10.e
            r2.unlock()
            goto L8e
        L84:
            r0 = move-exception
            r5 = r4
        L86:
            if (r5 == 0) goto L8d
            java.util.concurrent.locks.ReentrantLock r10 = r10.e
            r10.unlock()
        L8d:
            throw r0
        L8e:
            java.lang.String r2 = b.b.b.o.f1173b
            java.lang.String r3 = "Oaid#getOaid return apiMap="
            java.lang.StringBuilder r3 = b.a.a.a.a.a(r3)
            java.util.Map<java.lang.String, java.lang.String> r4 = r10.k
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            b.b.b.g.a(r2, r3)
            java.util.Map<java.lang.String, java.lang.String> r10 = r10.k
        La4:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = b.b.b.f.f1162a
            r2.append(r3)
            java.lang.String r3 = "getOaid takes "
            r2.append(r3)
            long r3 = android.os.SystemClock.elapsedRealtime()
            long r3 = r3 - r0
            r2.append(r3)
            java.lang.String r0 = " ms"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.String r1 = "TrackerDr"
            b.b.b.g.b(r1, r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.b.f.a(android.content.Context):java.util.Map");
    }

    @Nullable
    @AnyThread
    public static String a(@Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optString("id", null);
        }
        return null;
    }

    public static void b(b.b.a.e eVar) {
        o.b(eVar);
    }
}
