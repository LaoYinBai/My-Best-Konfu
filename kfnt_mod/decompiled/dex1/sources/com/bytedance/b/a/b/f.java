package com.bytedance.b.a.b;

import android.content.Context;
import android.database.sqlite.SQLiteFullException;
import android.text.TextUtils;
import com.bytedance.b.a.b.a;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class f extends Thread {

    /* renamed from: a, reason: collision with root package name */
    static final String f3664a = "magic_tag";

    /* renamed from: b, reason: collision with root package name */
    static final String f3665b = "log_queue";

    /* renamed from: c, reason: collision with root package name */
    static final String f3666c = "message";
    static final String d = "success";
    private static final String e = "LogSender";
    private static final long f = 120000;
    private static final long g = 600000;
    private static final long h = 864000000;
    private final Context i;
    private final Object j;
    private final AtomicBoolean k;
    private final e l;
    private long m;
    private long n;
    private long o;
    private d p;
    private final LinkedList<b> q;
    private int r;

    f(Context context, d dVar, LinkedList<b> linkedList, AtomicBoolean atomicBoolean) {
        super(e);
        this.j = new Object();
        this.m = -1L;
        this.n = 0L;
        this.o = 120000L;
        this.p = dVar;
        this.i = context;
        this.q = linkedList;
        this.k = atomicBoolean;
        this.l = e.a(this.i);
    }

    private void c() {
        a.b bVarD;
        if (f()) {
            return;
        }
        Map<String, a> mapB = this.p.b();
        if (mapB != null && !mapB.isEmpty()) {
            for (String str : mapB.keySet()) {
                if (f()) {
                    break;
                }
                a aVar = mapB.get(str);
                if (aVar != null && (bVarD = aVar.d()) != null) {
                    this.l.a(str, bVarD.c(), bVarD.f());
                }
            }
        }
        this.l.a(null, -1, h);
    }

    private boolean d() {
        if (f()) {
            return false;
        }
        synchronized (this.q) {
            if (f()) {
                return false;
            }
            b bVarPoll = this.q.isEmpty() ? null : this.q.poll();
            boolean z = !this.q.isEmpty();
            if (bVarPoll != null) {
                try {
                    if (this.l.a(bVarPoll.f, bVarPoll.f3653b) >= Long.MAX_VALUE) {
                        this.l.c();
                    }
                } catch (SQLiteFullException unused) {
                    this.l.c();
                }
            }
            return z;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:106:0x00e5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00e4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0166 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0167  */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean e() {
        /*
            Method dump skipped, instructions count: 432
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.b.a.b.f.e():boolean");
    }

    private boolean a(a aVar, String str, byte[] bArr) throws Throwable {
        if (bArr == null || bArr.length <= 0 || aVar == null || TextUtils.isEmpty(str)) {
            return false;
        }
        return aVar.a(str, bArr);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        d.a(e, "LogSender start");
        while (!f()) {
            boolean zD = d();
            if (f()) {
                break;
            }
            boolean z = e() || zD;
            if (f()) {
                break;
            }
            if (!z) {
                synchronized (this.j) {
                    try {
                        if (this.o == 0) {
                            this.j.wait();
                        } else {
                            this.j.wait(this.o);
                        }
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
        d.a(e, "LogSender quit");
    }

    private boolean f() {
        return this.k.get();
    }

    void a() {
        synchronized (this.j) {
            this.j.notify();
        }
    }

    void b() {
        a();
        this.l.b();
    }
}
