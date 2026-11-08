package com.huawei.hmf.tasks.a;

import java.util.concurrent.Executor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c<TResult> implements com.huawei.hmf.tasks.b<TResult> {
    private com.huawei.hmf.tasks.d a;
    private Executor b;
    private final Object c = new Object();

    c(Executor executor, com.huawei.hmf.tasks.d dVar) {
        this.a = dVar;
        this.b = executor;
    }

    @Override // com.huawei.hmf.tasks.b
    public final void a() {
        synchronized (this.c) {
            this.a = null;
        }
    }

    @Override // com.huawei.hmf.tasks.b
    public final void a(final com.huawei.hmf.tasks.f<TResult> fVar) {
        if (fVar.c() || fVar.b()) {
            return;
        }
        this.b.execute(new Runnable() { // from class: com.huawei.hmf.tasks.a.c.1
            @Override // java.lang.Runnable
            public final void run() {
                synchronized (c.this.c) {
                    if (c.this.a != null) {
                        c.this.a.a(fVar.f());
                    }
                }
            }
        });
    }
}
