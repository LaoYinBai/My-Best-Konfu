package com.kwad.sdk.core.network;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.kwad.sdk.core.network.g;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a<R extends g> {
    private static final ExecutorService a = com.kwad.sdk.core.i.b.h();
    private Future<?> b;

    protected abstract void a(R r, c cVar);

    @NonNull
    protected abstract R b();

    protected void d() {
        try {
            this.b = a.submit(new Runnable() { // from class: com.kwad.sdk.core.network.a.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        a.this.f();
                    } catch (Exception e) {
                        com.kwad.sdk.core.d.a.b(e);
                    }
                }
            });
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.a(th);
        }
    }

    @CallSuper
    protected void e() {
        if (this.b != null) {
            this.b.cancel(true);
        }
    }

    @WorkerThread
    protected abstract void f();
}
