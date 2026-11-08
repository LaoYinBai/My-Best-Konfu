package com.huawei.hmf.tasks.a;

import java.util.concurrent.Executor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b<TResult> implements com.huawei.hmf.tasks.b<TResult> {
    Executor a;
    private com.huawei.hmf.tasks.c<TResult> b;
    private final Object c = new Object();

    b(Executor executor, com.huawei.hmf.tasks.c<TResult> cVar) {
        this.b = cVar;
        this.a = executor;
    }

    @Override // com.huawei.hmf.tasks.b
    public final void a() {
        synchronized (this.c) {
            this.b = null;
        }
    }

    @Override // com.huawei.hmf.tasks.b
    public final void a(final com.huawei.hmf.tasks.f<TResult> fVar) {
        this.a.execute(new Runnable() { // from class: com.huawei.hmf.tasks.a.b.1
            @Override // java.lang.Runnable
            public final void run() {
                synchronized (b.this.c) {
                    if (b.this.b != null) {
                        b.this.b.onComplete(fVar);
                    }
                }
            }
        });
    }
}
