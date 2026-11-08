package com.bytedance.b.a.a.d;

import android.os.Message;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3619a = "AsyncEventManager-Thread";
    private static long e = 30000;

    /* renamed from: b, reason: collision with root package name */
    CopyOnWriteArraySet<b> f3620b;

    /* renamed from: c, reason: collision with root package name */
    private d f3621c;
    private volatile boolean d;
    private final Runnable f;

    private a() {
        this.d = true;
        this.f = new Runnable() { // from class: com.bytedance.b.a.a.d.a.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Iterator<b> it = a.this.f3620b.iterator();
                    while (it.hasNext()) {
                        it.next().a(System.currentTimeMillis());
                    }
                    if (a.this.d) {
                        a.this.f3621c.b(this, a.e);
                    }
                } catch (OutOfMemoryError unused) {
                }
            }
        };
        this.f3620b = new CopyOnWriteArraySet<>();
        this.f3621c = new d(f3619a);
        this.f3621c.a();
    }

    /* renamed from: com.bytedance.b.a.a.d.a$a, reason: collision with other inner class name */
    private static final class C0103a {

        /* renamed from: a, reason: collision with root package name */
        static final a f3623a = new a();

        private C0103a() {
        }
    }

    public static a a() {
        return C0103a.f3623a;
    }

    public void a(Message message) {
        this.f3621c.a(message);
    }

    public void a(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        this.f3621c.a(runnable);
    }

    public void a(Runnable runnable, long j) {
        if (runnable == null) {
            return;
        }
        this.f3621c.b(runnable, j);
    }

    public void b(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        this.f3621c.c(runnable);
    }

    public void a(b bVar) {
        if (bVar != null) {
            try {
                this.f3620b.add(bVar);
                if (this.d) {
                    this.f3621c.c(this.f);
                    this.f3621c.b(this.f, e);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void b(b bVar) {
        if (bVar != null) {
            try {
                this.f3620b.remove(bVar);
            } catch (Throwable unused) {
            }
        }
    }

    public void b() {
        this.d = false;
        if (this.f3621c != null) {
            this.f3621c.c(this.f);
        }
    }

    public void c() {
        this.d = true;
        if (this.f3621c == null || this.f3620b.isEmpty()) {
            return;
        }
        this.f3621c.c(this.f);
        this.f3621c.b(this.f, e);
    }
}
