package com.bytedance.common.push.utility.b;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements Runnable {
    private Runnable d;
    private final boolean e;

    /* renamed from: b, reason: collision with root package name */
    private static ExecutorService f3835b = a.a();

    /* renamed from: c, reason: collision with root package name */
    private static ExecutorService f3836c = a.a();

    /* renamed from: a, reason: collision with root package name */
    protected static final AtomicInteger f3834a = new AtomicInteger();

    public b() {
        this(false);
    }

    public b(boolean z) {
        this.e = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.d != null) {
            this.d.run();
        }
    }

    public void a() {
        Runnable runnable = com.bytedance.common.push.utility.a.b() ? new Runnable() { // from class: com.bytedance.common.push.utility.b.b.1
            @Override // java.lang.Runnable
            public void run() {
                com.bytedance.common.push.utility.a.b("ThreadPlus", "thread count: " + b.f3834a.incrementAndGet());
                try {
                    b.this.run();
                } catch (Exception e) {
                    com.bytedance.common.push.utility.a.d("ThreadPlus", "Thread crashed!", e);
                }
                com.bytedance.common.push.utility.a.b("ThreadPlus", "thread count: " + b.f3834a.decrementAndGet());
            }
        } : this;
        if (this.e) {
            f3836c.submit(runnable);
        } else {
            f3835b.submit(runnable);
        }
    }

    public static void a(Runnable runnable) {
        if (runnable != null) {
            f3835b.submit(runnable);
        }
    }
}
