package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.push.ai;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class dx extends ai.a {
    protected int a;

    /* renamed from: a, reason: collision with other field name */
    protected Context f249a;

    public dx(Context context, int i) {
        this.a = i;
        this.f249a = context;
    }

    public static void a(Context context, hr hrVar) {
        dk dkVarM201a = dl.a().m201a();
        String strA = dkVarM201a == null ? "" : dkVarM201a.a();
        if (TextUtils.isEmpty(strA) || TextUtils.isEmpty(hrVar.a())) {
            return;
        }
        a(context, hrVar, strA);
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x0096 A[Catch: all -> 0x00a0, TRY_LEAVE, TryCatch #0 {, blocks: (B:15:0x0056, B:17:0x005c, B:18:0x005f, B:19:0x0062, B:44:0x008f, B:50:0x0096, B:52:0x009c, B:56:0x00a2, B:57:0x00a8, B:40:0x0082, B:42:0x0088, B:43:0x008b), top: B:64:0x0013 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(android.content.Context r6, com.xiaomi.push.hr r7, java.lang.String r8) {
        /*
            byte[] r7 = com.xiaomi.push.it.a(r7)
            byte[] r7 = com.xiaomi.push.dq.b(r8, r7)
            if (r7 == 0) goto Lab
            int r8 = r7.length
            if (r8 != 0) goto Lf
            goto Lab
        Lf:
            java.lang.Object r8 = com.xiaomi.push.dr.a
            monitor-enter(r8)
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L7a
            java.io.File r2 = r6.getExternalFilesDir(r0)     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L7a
            java.lang.String r3 = "push_cdata.lock"
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L7a
            com.xiaomi.push.y.m650a(r1)     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L7a
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L7a
            java.lang.String r3 = "rw"
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L7a
            java.nio.channels.FileChannel r1 = r2.getChannel()     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L73
            java.nio.channels.FileLock r1 = r1.lock()     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L73
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6c
            java.io.File r6 = r6.getExternalFilesDir(r0)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6c
            java.lang.String r4 = "push_cdata.data"
            r3.<init>(r6, r4)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6c
            java.io.BufferedOutputStream r6 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6c
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6c
            r5 = 1
            r4.<init>(r3, r5)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6c
            r6.<init>(r4)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6c
            int r0 = r7.length     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            byte[] r0 = com.xiaomi.push.ac.a(r0)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r6.write(r0)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r6.write(r7)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r6.flush()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            if (r1 == 0) goto L5f
            boolean r7 = r1.isValid()     // Catch: java.lang.Throwable -> La0
            if (r7 == 0) goto L5f
            r1.release()     // Catch: java.io.IOException -> L5f java.lang.Throwable -> La0
        L5f:
            com.xiaomi.push.y.a(r6)     // Catch: java.lang.Throwable -> La0
        L62:
            com.xiaomi.push.y.a(r2)     // Catch: java.lang.Throwable -> La0
            goto L8f
        L66:
            r7 = move-exception
            goto L93
        L68:
            r7 = move-exception
            goto L6e
        L6a:
            r7 = move-exception
            goto L94
        L6c:
            r7 = move-exception
            r6 = r0
        L6e:
            r0 = r1
            goto L7d
        L70:
            r7 = move-exception
            r1 = r0
            goto L94
        L73:
            r7 = move-exception
            r6 = r0
            goto L7d
        L76:
            r7 = move-exception
            r1 = r0
            r2 = r1
            goto L94
        L7a:
            r7 = move-exception
            r6 = r0
            r2 = r6
        L7d:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L91
            if (r0 == 0) goto L8b
            boolean r7 = r0.isValid()     // Catch: java.lang.Throwable -> La0
            if (r7 == 0) goto L8b
            r0.release()     // Catch: java.io.IOException -> L8b java.lang.Throwable -> La0
        L8b:
            com.xiaomi.push.y.a(r6)     // Catch: java.lang.Throwable -> La0
            goto L62
        L8f:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> La0
            return
        L91:
            r7 = move-exception
            r1 = r0
        L93:
            r0 = r6
        L94:
            if (r1 == 0) goto La2
            boolean r6 = r1.isValid()     // Catch: java.lang.Throwable -> La0
            if (r6 == 0) goto La2
            r1.release()     // Catch: java.lang.Throwable -> La0 java.io.IOException -> La2
            goto La2
        La0:
            r6 = move-exception
            goto La9
        La2:
            com.xiaomi.push.y.a(r0)     // Catch: java.lang.Throwable -> La0
            com.xiaomi.push.y.a(r2)     // Catch: java.lang.Throwable -> La0
            throw r7     // Catch: java.lang.Throwable -> La0
        La9:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> La0
            throw r6
        Lab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.dx.a(android.content.Context, com.xiaomi.push.hr, java.lang.String):void");
    }

    private String c() {
        return "dc_job_result_time_" + mo205a();
    }

    private String d() {
        return "dc_job_result_" + mo205a();
    }

    /* renamed from: a */
    public abstract hl mo205a();

    protected boolean a() {
        return dq.a(this.f249a, String.valueOf(mo205a()), this.a);
    }

    public abstract String b();

    /* renamed from: b, reason: collision with other method in class */
    protected boolean m206b() {
        return true;
    }

    /* renamed from: c, reason: collision with other method in class */
    protected boolean m207c() {
        return false;
    }

    @Override // java.lang.Runnable
    public void run() {
        String strB = b();
        if (TextUtils.isEmpty(strB)) {
            return;
        }
        if (a()) {
            com.xiaomi.channel.commonutils.logger.b.m22a("DC run job mutual: " + mo205a());
            return;
        }
        dk dkVarM201a = dl.a().m201a();
        String strA = dkVarM201a == null ? "" : dkVarM201a.a();
        if (!TextUtils.isEmpty(strA) && m206b()) {
            if (m207c()) {
                SharedPreferences sharedPreferences = this.f249a.getSharedPreferences("mipush_extra", 0);
                if (bm.a(strB).equals(sharedPreferences.getString(d(), null))) {
                    long j = sharedPreferences.getLong(c(), 0L);
                    int iA = com.xiaomi.push.service.aq.a(this.f249a).a(ho.DCJobUploadRepeatedInterval.a(), 604800);
                    if ((System.currentTimeMillis() - j) / 1000 < this.a) {
                        return;
                    }
                    if ((System.currentTimeMillis() - j) / 1000 < iA) {
                        strB = "same_" + j;
                    }
                }
            }
            hr hrVar = new hr();
            hrVar.a(strB);
            hrVar.a(System.currentTimeMillis());
            hrVar.a(mo205a());
            a(this.f249a, hrVar, strA);
        }
    }
}
