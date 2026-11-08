package com.alibaba.sdk.android.httpdns;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class s {

    /* renamed from: a, reason: collision with root package name */
    private static volatile s f2400a;
    private long g = 0;

    /* renamed from: g, reason: collision with other field name */
    private boolean f39g = true;
    private String hostName = null;

    private s() {
    }

    public static s a() {
        if (f2400a == null) {
            synchronized (s.class) {
                if (f2400a == null) {
                    f2400a = new s();
                }
            }
        }
        return f2400a;
    }

    private void a(String str, String str2) {
        try {
            com.alibaba.sdk.android.httpdns.d.b bVarA = com.alibaba.sdk.android.httpdns.d.b.a();
            if (bVarA != null) {
                bVarA.a(str, t.a(o.SNIFF_HOST), str2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean d() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.g != 0 && jCurrentTimeMillis - this.g < com.bytedance.b.a.a.c.g.e) {
            return false;
        }
        this.g = jCurrentTimeMillis;
        return true;
    }

    public synchronized void c(boolean z) {
        this.f39g = z;
    }

    public synchronized void e() {
        this.g = 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0011  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0016 A[Catch: all -> 0x0006, Exception -> 0x0008, TryCatch #1 {Exception -> 0x0008, blocks: (B:4:0x0003, B:9:0x000a, B:21:0x002d, B:22:0x0051, B:13:0x0016, B:16:0x001f), top: B:30:0x0003, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x002d A[Catch: all -> 0x0006, Exception -> 0x0008, TryCatch #1 {Exception -> 0x0008, blocks: (B:4:0x0003, B:9:0x000a, B:21:0x002d, B:22:0x0051, B:13:0x0016, B:16:0x001f), top: B:30:0x0003, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0051 A[Catch: all -> 0x0006, Exception -> 0x0008, TRY_LEAVE, TryCatch #1 {Exception -> 0x0008, blocks: (B:4:0x0003, B:9:0x000a, B:21:0x002d, B:22:0x0051, B:13:0x0016, B:16:0x001f), top: B:30:0x0003, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void g(java.lang.String r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            if (r6 == 0) goto La
            r5.hostName = r6     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            goto La
        L6:
            r6 = move-exception
            goto L6b
        L8:
            r6 = move-exception
            goto L66
        La:
            r0 = 1
            boolean r1 = r5.f39g     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            r2 = 0
            r3 = 0
            if (r1 != 0) goto L16
            java.lang.String r0 = "sniffer is turned off"
        L13:
            r1 = r0
            r0 = 0
            goto L2b
        L16:
            boolean r1 = r5.d()     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            if (r1 != 0) goto L1f
            java.lang.String r0 = "sniff too often"
            goto L13
        L1f:
            java.lang.String r1 = r5.hostName     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            if (r1 == 0) goto L2a
            java.lang.String r0 = "hostname is null"
            goto L13
        L2a:
            r1 = r2
        L2b:
            if (r0 == 0) goto L51
            java.lang.String r0 = "launch a sniff task"
            com.alibaba.sdk.android.httpdns.i.d(r0)     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            com.alibaba.sdk.android.httpdns.m r0 = new com.alibaba.sdk.android.httpdns.m     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            java.lang.String r1 = r5.hostName     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            com.alibaba.sdk.android.httpdns.o r4 = com.alibaba.sdk.android.httpdns.o.SNIFF_HOST     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            r0.<init>(r1, r4)     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            r0.a(r3)     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            java.util.concurrent.ExecutorService r1 = com.alibaba.sdk.android.httpdns.c.a()     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            r1.submit(r0)     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            com.alibaba.sdk.android.httpdns.o r0 = com.alibaba.sdk.android.httpdns.o.SNIFF_HOST     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            java.lang.String r0 = com.alibaba.sdk.android.httpdns.t.a(r0)     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            r5.a(r6, r0)     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            r5.hostName = r2     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            goto L69
        L51:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            r6.<init>()     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            java.lang.String r0 = "launch sniffer failed due to "
            r6.append(r0)     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            r6.append(r1)     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            com.alibaba.sdk.android.httpdns.i.d(r6)     // Catch: java.lang.Throwable -> L6 java.lang.Exception -> L8
            goto L69
        L66:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L6
        L69:
            monitor-exit(r5)
            return
        L6b:
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.s.g(java.lang.String):void");
    }
}
