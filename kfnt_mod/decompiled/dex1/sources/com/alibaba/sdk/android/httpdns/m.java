package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import java.util.concurrent.Callable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class m implements Callable<String[]> {

    /* renamed from: a, reason: collision with root package name */
    private static Context f2379a;

    /* renamed from: a, reason: collision with other field name */
    private o f26a;
    private String hostName;
    private static d hostManager = d.a();

    /* renamed from: a, reason: collision with other field name */
    private static final Object f25a = new Object();
    private int d = 1;
    private String e = null;

    /* renamed from: e, reason: collision with other field name */
    private String[] f29e = f.d;

    /* renamed from: e, reason: collision with other field name */
    private boolean f28e = false;

    /* renamed from: d, reason: collision with other field name */
    private long f27d = 0;

    m(String str, o oVar) {
        this.hostName = str;
        this.f26a = oVar;
    }

    static void setContext(Context context) {
        f2379a = context;
    }

    public void a(int i) {
        if (i >= 0) {
            this.d = i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x03fb A[Catch: IOException -> 0x03f7, TRY_LEAVE, TryCatch #1 {IOException -> 0x03f7, blocks: (B:74:0x03ac, B:76:0x03b1, B:96:0x03f3, B:100:0x03fb), top: B:117:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x041c A[Catch: IOException -> 0x0418, TRY_LEAVE, TryCatch #3 {IOException -> 0x0418, blocks: (B:109:0x0414, B:113:0x041c), top: B:119:0x0414 }] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0414 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x03dd A[Catch: all -> 0x040c, TryCatch #9 {all -> 0x040c, blocks: (B:89:0x03cf, B:91:0x03dd, B:92:0x03e6), top: B:121:0x03cf }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x03e6 A[Catch: all -> 0x040c, TRY_LEAVE, TryCatch #9 {all -> 0x040c, blocks: (B:89:0x03cf, B:91:0x03dd, B:92:0x03e6), top: B:121:0x03cf }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x03f3 A[Catch: IOException -> 0x03f7, TRY_ENTER, TryCatch #1 {IOException -> 0x03f7, blocks: (B:74:0x03ac, B:76:0x03b1, B:96:0x03f3, B:100:0x03fb), top: B:117:0x0046 }] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v47 */
    /* JADX WARN: Type inference failed for: r3v49 */
    /* JADX WARN: Type inference failed for: r3v50 */
    /* JADX WARN: Type inference failed for: r3v54, types: [int] */
    /* JADX WARN: Type inference failed for: r3v71 */
    /* JADX WARN: Type inference failed for: r3v72 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v45 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    @Override // java.util.concurrent.Callable
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String[] call() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1060
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.m.call():java.lang.String[]");
    }
}
