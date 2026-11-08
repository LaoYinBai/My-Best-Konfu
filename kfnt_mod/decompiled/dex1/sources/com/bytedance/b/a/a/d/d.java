package com.bytedance.b.a.a.d;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.bytedance.b.a.a.e.d;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    static final d.a<c, Runnable> f3629a = new d.a<c, Runnable>() { // from class: com.bytedance.b.a.a.d.d.1
        @Override // com.bytedance.b.a.a.e.d.a
        public boolean a(c cVar, Runnable runnable) {
            return runnable == null ? cVar == null || cVar.f3634a == null || cVar.f3634a.getCallback() == null : (cVar == null || cVar.f3634a == null || !runnable.equals(cVar.f3634a.getCallback())) ? false : true;
        }
    };

    /* renamed from: b, reason: collision with root package name */
    static final d.a<Message, Runnable> f3630b = new d.a<Message, Runnable>() { // from class: com.bytedance.b.a.a.d.d.2
        @Override // com.bytedance.b.a.a.e.d.a
        public boolean a(Message message, Runnable runnable) {
            return runnable == null ? message == null || message.getCallback() == null : message != null && runnable.equals(message.getCallback());
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private final HandlerThread f3631c;
    private volatile Handler f;
    private final Queue<c> d = new ConcurrentLinkedQueue();
    private final Queue<Message> e = new ConcurrentLinkedQueue();
    private final Object g = new Object();

    public d(String str, int i) {
        this.f3631c = new b(str, i);
    }

    public d(String str) {
        this.f3631c = new b(str);
    }

    public void a() {
        this.f3631c.start();
    }

    private Message d(Runnable runnable) {
        return Message.obtain(this.f, runnable);
    }

    private Message a(Runnable runnable, Object obj) {
        Message messageObtain = Message.obtain(this.f, runnable);
        messageObtain.obj = obj;
        return messageObtain;
    }

    public final boolean a(Runnable runnable) {
        return a(d(runnable), 0L);
    }

    public final boolean a(Runnable runnable, long j) {
        return b(d(runnable), j);
    }

    public final boolean a(Runnable runnable, Object obj, long j) {
        return b(a(runnable, obj), j);
    }

    public final boolean b(Runnable runnable, long j) {
        return a(d(runnable), j);
    }

    public final boolean b(Runnable runnable, Object obj, long j) {
        return a(a(runnable, obj), j);
    }

    public final boolean b(Runnable runnable) {
        return b(d(runnable));
    }

    public final void c(Runnable runnable) {
        if (!this.d.isEmpty() || !this.e.isEmpty()) {
            com.bytedance.b.a.a.e.d.a(this.d, runnable, f3629a);
            com.bytedance.b.a.a.e.d.a(this.e, runnable, f3630b);
        }
        if (this.f != null) {
            this.f.removeCallbacks(runnable);
        }
    }

    public final boolean a(Message message) {
        return a(message, 0L);
    }

    public final boolean a(int i) {
        return a(i, 0L);
    }

    public final boolean a(int i, long j) {
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        return a(messageObtain, j);
    }

    public final boolean b(int i, long j) {
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        return b(messageObtain, j);
    }

    public final boolean a(Message message, long j) {
        if (j < 0) {
            j = 0;
        }
        return b(message, SystemClock.uptimeMillis() + j);
    }

    public final boolean b(Message message, long j) {
        if (this.f == null) {
            synchronized (this.g) {
                if (this.f == null) {
                    this.d.add(new c(message, j));
                    return true;
                }
            }
        }
        return this.f.sendMessageAtTime(message, j);
    }

    public final boolean b(Message message) {
        if (this.f == null) {
            synchronized (this.g) {
                if (this.f == null) {
                    this.e.add(message);
                    return true;
                }
            }
        }
        return this.f.sendMessageAtFrontOfQueue(message);
    }

    class b extends HandlerThread {
        b(String str) {
            super(str);
        }

        b(String str, int i) {
            super(str, i);
        }

        @Override // android.os.HandlerThread
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            synchronized (d.this.g) {
                d.this.f = new Handler();
            }
            d.this.f.post(d.this.new a());
            while (true) {
                try {
                    Looper.loop();
                } catch (Throwable unused) {
                }
            }
        }
    }

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b();
            a();
        }

        void a() {
            while (!d.this.d.isEmpty()) {
                c cVar = (c) d.this.d.poll();
                if (d.this.f != null) {
                    d.this.f.sendMessageAtTime(cVar.f3634a, cVar.f3635b);
                }
            }
        }

        void b() {
            while (!d.this.e.isEmpty()) {
                if (d.this.f != null) {
                    d.this.f.sendMessageAtFrontOfQueue((Message) d.this.e.poll());
                }
            }
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        Message f3634a;

        /* renamed from: b, reason: collision with root package name */
        long f3635b;

        c(Message message, long j) {
            this.f3634a = message;
            this.f3635b = j;
        }
    }
}
