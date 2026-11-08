package com.baidu.mobads.sdk.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.Future;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class h<T> implements Runnable {

    /* renamed from: b, reason: collision with root package name */
    private static final String f3231b = "BaseTask";

    /* renamed from: c, reason: collision with root package name */
    private static final int f3232c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static b j;

    /* renamed from: a, reason: collision with root package name */
    protected Future<T> f3233a;
    private String f;
    private long g;
    private long h;
    private long i;

    protected void a(T t) {
    }

    protected void a(Throwable th) {
    }

    protected abstract T i();

    protected void j() {
    }

    public h() {
        this.f = com.bytedance.b.a.a.d.m;
    }

    public h(String str) {
        this.f = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        h();
    }

    public String a() {
        return this.f;
    }

    public void a(Future future) {
        this.f3233a = future;
    }

    public void a(long j2) {
        this.g = j2;
    }

    public void a(boolean z) {
        if (this.f3233a != null) {
            this.f3233a.cancel(z);
            k().obtainMessage(3, new a(this, null)).sendToTarget();
        }
    }

    public void b() {
        a(false);
    }

    public boolean c() {
        if (this.f3233a != null) {
            return this.f3233a.isCancelled();
        }
        return false;
    }

    public boolean d() {
        if (this.f3233a != null) {
            return this.f3233a.isDone();
        }
        return false;
    }

    public long e() {
        return this.h - this.g;
    }

    public long f() {
        return this.i - this.g;
    }

    public long g() {
        return this.i - this.h;
    }

    public h h() {
        try {
            try {
                this.h = System.currentTimeMillis();
                k().obtainMessage(1, new a(this, i())).sendToTarget();
            } catch (Throwable th) {
                k().obtainMessage(2, new a(this, th)).sendToTarget();
            }
            return this;
        } finally {
            this.i = System.currentTimeMillis();
        }
    }

    private static Handler k() {
        b bVar;
        synchronized (h.class) {
            if (j == null) {
                j = new b(Looper.getMainLooper());
            }
            bVar = j;
        }
        return bVar;
    }

    private static class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            a aVar = (a) message.obj;
            switch (message.what) {
                case 1:
                    aVar.f3234a.a((h) aVar.f3235b);
                    break;
                case 2:
                    aVar.f3234a.a((Throwable) aVar.f3235b);
                    break;
                case 3:
                    aVar.f3234a.j();
                    break;
            }
        }
    }

    private static class a<T> {

        /* renamed from: a, reason: collision with root package name */
        final h f3234a;

        /* renamed from: b, reason: collision with root package name */
        final T f3235b;

        a(h hVar, T t) {
            this.f3234a = hVar;
            this.f3235b = t;
        }
    }
}
