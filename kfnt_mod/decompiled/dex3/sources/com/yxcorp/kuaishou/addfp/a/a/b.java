package com.yxcorp.kuaishou.addfp.a.a;

import android.content.Context;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class b implements a {
    public static String a = "KWE_NS";
    private static AtomicBoolean c = new AtomicBoolean(false);
    private a b;
    private CountDownLatch d;
    private com.yxcorp.kuaishou.addfp.a.b.a e;
    private Context f;
    private com.yxcorp.kuaishou.addfp.android.a.e g;

    private b() {
        this.d = null;
        this.e = null;
    }

    /* synthetic */ b(byte b) {
        this();
    }

    public static final b a() {
        return e.a;
    }

    public final void a(Context context) {
        d dVar = new d(this);
        try {
            if (c.compareAndSet(false, true)) {
                if (this.f == null) {
                    this.f = context;
                }
                this.g = new com.yxcorp.kuaishou.addfp.android.a.e(this.f);
                Thread thread = new Thread(new c(this, context, dVar));
                thread.setName("adsence-dfp");
                thread.start();
            }
        } catch (Throwable unused) {
            a(false, (com.yxcorp.kuaishou.addfp.a.b.a) null);
        }
    }

    @Override // com.yxcorp.kuaishou.addfp.a.a.a
    public final void a(boolean z, com.yxcorp.kuaishou.addfp.a.b.a aVar) {
        try {
            if (this.b != null) {
                this.b.a(z, aVar);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void b() {
        if (this.d != null) {
            this.d.countDown();
        }
    }

    public final void c() throws InterruptedException {
        try {
            if (this.d == null || this.d.getCount() <= 0) {
                return;
            }
            this.d.await(2000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
