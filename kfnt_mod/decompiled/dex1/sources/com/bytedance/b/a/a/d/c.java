package com.bytedance.b.a.a.d;

import android.os.Message;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3624a = "LogSendManager-Thread";
    private static long e = 5000;

    /* renamed from: b, reason: collision with root package name */
    CopyOnWriteArraySet<b> f3625b;

    /* renamed from: c, reason: collision with root package name */
    private d f3626c;
    private volatile boolean d;
    private final Runnable f;

    private c() {
        this.d = true;
        this.f = new Runnable() { // from class: com.bytedance.b.a.a.d.c.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Iterator<b> it = c.this.f3625b.iterator();
                    while (it.hasNext()) {
                        it.next().a(System.currentTimeMillis());
                    }
                    if (c.this.d) {
                        c.this.f3626c.b(this, c.e);
                    }
                } catch (OutOfMemoryError unused) {
                }
            }
        };
        this.f3625b = new CopyOnWriteArraySet<>();
        this.f3626c = new d(f3624a);
        this.f3626c.a();
    }

    private static final class a {

        /* renamed from: a, reason: collision with root package name */
        static final c f3628a = new c();

        private a() {
        }
    }

    public static c a() {
        return a.f3628a;
    }

    public void a(Message message) {
        this.f3626c.a(message);
    }

    public void a(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        this.f3626c.a(runnable);
    }

    public void a(Runnable runnable, long j) {
        if (runnable == null) {
            return;
        }
        this.f3626c.b(runnable, j);
    }

    public void b(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        this.f3626c.c(runnable);
    }

    public void a(b bVar) {
        if (bVar != null) {
            try {
                this.f3625b.add(bVar);
                if (this.d) {
                    this.f3626c.c(this.f);
                    this.f3626c.b(this.f, e);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void b(b bVar) {
        if (bVar != null) {
            try {
                this.f3625b.remove(bVar);
            } catch (Throwable unused) {
            }
        }
    }

    public void b() {
        this.d = false;
        if (this.f3626c != null) {
            this.f3626c.c(this.f);
        }
    }

    public void c() {
        this.d = true;
        if (this.f3626c == null || this.f3625b.isEmpty()) {
            return;
        }
        this.f3626c.c(this.f);
        this.f3626c.b(this.f, e);
    }
}
