package com.apm.insight.runtime;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class u {

    /* renamed from: a, reason: collision with root package name */
    static final b<d, Runnable> f3077a = new b<d, Runnable>() { // from class: com.apm.insight.runtime.u.1
    };

    /* renamed from: b, reason: collision with root package name */
    static final b<Message, Runnable> f3078b = new b<Message, Runnable>() { // from class: com.apm.insight.runtime.u.2
    };

    /* renamed from: c, reason: collision with root package name */
    private final HandlerThread f3079c;
    private volatile Handler f;
    private final Queue<d> d = new ConcurrentLinkedQueue();
    private final Queue<Message> e = new ConcurrentLinkedQueue();
    private final Object g = new Object();

    class a implements Runnable {
        a() {
        }

        void a() {
            while (!u.this.d.isEmpty()) {
                d dVar = (d) u.this.d.poll();
                if (u.this.f != null) {
                    try {
                        u.this.f.sendMessageAtTime(dVar.f3084a, dVar.f3085b);
                    } catch (Throwable unused) {
                    }
                }
            }
        }

        void b() {
            while (!u.this.e.isEmpty()) {
                if (u.this.f != null) {
                    try {
                        u.this.f.sendMessageAtFrontOfQueue((Message) u.this.e.poll());
                    } catch (Throwable unused) {
                    }
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            b();
            a();
        }
    }

    public interface b<A, B> {
    }

    class c extends HandlerThread {

        /* renamed from: a, reason: collision with root package name */
        volatile int f3081a;

        /* renamed from: b, reason: collision with root package name */
        volatile boolean f3082b;

        c(String str) {
            super(str);
            this.f3081a = 0;
            this.f3082b = false;
        }

        @Override // android.os.HandlerThread
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            synchronized (u.this.g) {
                u.this.f = new Handler();
            }
            u.this.f.post(u.this.new a());
            while (true) {
                try {
                    Looper.loop();
                } catch (Throwable th) {
                    try {
                        com.apm.insight.b.g.a(com.apm.insight.h.g()).a().c();
                        if (this.f3081a < 5) {
                            com.apm.insight.b.a().a("NPTH_CATCH", th);
                        } else if (!this.f3082b) {
                            this.f3082b = true;
                            com.apm.insight.b.a().a("NPTH_ERR_MAX", new RuntimeException());
                        }
                        this.f3081a++;
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        Message f3084a;

        /* renamed from: b, reason: collision with root package name */
        long f3085b;

        d(Message message, long j) {
            this.f3084a = message;
            this.f3085b = j;
        }
    }

    public u(String str) {
        this.f3079c = new c(str);
    }

    private Message b(Runnable runnable) {
        return Message.obtain(this.f, runnable);
    }

    @Nullable
    public Handler a() {
        return this.f;
    }

    public final boolean a(Message message, long j) {
        if (j < 0) {
            j = 0;
        }
        return b(message, SystemClock.uptimeMillis() + j);
    }

    public final boolean a(Runnable runnable) {
        return a(b(runnable), 0L);
    }

    public final boolean a(Runnable runnable, long j) {
        return a(b(runnable), j);
    }

    public void b() {
        this.f3079c.start();
    }

    public final boolean b(Message message, long j) {
        if (this.f == null) {
            synchronized (this.g) {
                if (this.f == null) {
                    this.d.add(new d(message, j));
                    return true;
                }
            }
        }
        try {
            return this.f.sendMessageAtTime(message, j);
        } catch (Throwable unused) {
            return true;
        }
    }

    public HandlerThread c() {
        return this.f3079c;
    }
}
