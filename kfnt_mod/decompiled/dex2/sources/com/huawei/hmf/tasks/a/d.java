package com.huawei.hmf.tasks.a;

import java.util.concurrent.Executor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d<TResult> implements com.huawei.hmf.tasks.b<TResult> {
    private com.huawei.hmf.tasks.e<TResult> a;
    private Executor b;
    private final Object c = new Object();

    d(Executor executor, com.huawei.hmf.tasks.e<TResult> eVar) {
        this.a = eVar;
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
        if (!fVar.c() || fVar.b()) {
            return;
        }
        this.b.execute(new Runnable() { // from class: com.huawei.hmf.tasks.a.d.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public final void run() {
                synchronized (d.this.c) {
                    if (d.this.a != null) {
                        d.this.a.a(fVar.d());
                    }
                }
            }
        });
    }
}
