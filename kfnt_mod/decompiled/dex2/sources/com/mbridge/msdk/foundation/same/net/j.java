package com.mbridge.msdk.foundation.same.net;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.mbridge.msdk.foundation.tools.s;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: RequestQueue.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class j {
    private static final String a = "j";
    private Context b;
    private Set<i> d = new HashSet();
    private ExecutorService e = new ThreadPoolExecutor(5, 30, 60, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadPoolExecutor.DiscardOldestPolicy());
    private AtomicInteger f = new AtomicInteger();
    private c c = new d(new Handler(Looper.getMainLooper()));

    public j(Context context) {
        this.b = context.getApplicationContext();
    }

    public final void a(final i iVar) {
        iVar.a(this);
        synchronized (this) {
            this.d.add(iVar);
        }
        iVar.a(this.f.incrementAndGet());
        s.b(a, "add-to-queue request=" + iVar.b());
        this.e.execute(new Runnable() { // from class: com.mbridge.msdk.foundation.same.net.j.1
            @Override // java.lang.Runnable
            public final void run() {
                new h(j.this.b, null, j.this.c).a(iVar);
            }
        });
    }

    final void b(i iVar) {
        synchronized (this) {
            this.d.remove(iVar);
        }
    }
}
