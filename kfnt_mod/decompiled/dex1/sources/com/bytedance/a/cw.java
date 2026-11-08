package com.bytedance.a;

import com.bytedance.common.utility.NetworkUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class cw {

    /* renamed from: a, reason: collision with root package name */
    public final dg f3480a;

    /* renamed from: b, reason: collision with root package name */
    public int f3481b;

    /* renamed from: c, reason: collision with root package name */
    public volatile boolean f3482c;
    public long d;
    public boolean e;

    public cw(dg dgVar) {
        this.f3480a = dgVar;
    }

    public cw(dg dgVar, long j) {
        this.f3480a = dgVar;
        this.d = j;
    }

    public abstract boolean a();

    public abstract long b();

    public abstract long[] c();

    public abstract boolean d();

    public abstract String e();

    public final long f() {
        String str;
        long jH = h();
        if (jH > System.currentTimeMillis()) {
            return jH;
        }
        try {
            try {
                boolean zD = d();
                this.d = System.currentTimeMillis();
                this.f3481b = zD ? 0 : this.f3481b + 1;
                str = e() + " worked:" + zD;
            } catch (Exception e) {
                de.a("U SHALL NOT PASS!", e);
                this.d = System.currentTimeMillis();
                this.f3481b++;
                str = e() + " worked:false";
            }
            de.a(str, null);
            return h();
        } catch (Throwable th) {
            this.d = System.currentTimeMillis();
            this.f3481b++;
            de.a(e() + " worked:false", null);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T extends cw> T g() {
        StringBuilder sbA = a.a("setImmediately, ");
        sbA.append(e());
        de.a(sbA.toString(), null);
        this.f3482c = true;
        return this;
    }

    public final long h() {
        if (a() && !NetworkUtils.isNetworkAvailableFast(this.f3480a.d)) {
            de.a("checkWorkTime, 0", null);
            return System.currentTimeMillis() + 5000;
        }
        long jB = 0;
        if (this.f3482c) {
            this.d = 0L;
            this.f3482c = false;
        } else {
            int i = this.f3481b;
            if (i > 0) {
                long[] jArrC = c();
                jB = jArrC[(i - 1) % jArrC.length];
            } else {
                jB = b();
            }
        }
        return this.d + jB;
    }
}
