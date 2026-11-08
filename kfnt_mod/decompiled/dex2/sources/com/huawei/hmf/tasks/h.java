package com.huawei.hmf.tasks;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class h {
    private static final h a = new h();
    private final ExecutorService c = com.huawei.hmf.tasks.a.a.a();
    private final Executor b = new a();
    private final Executor d = com.huawei.hmf.tasks.a.a.b();

    static final class a implements Executor {
        a() {
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            runnable.run();
        }
    }

    private h() {
    }

    public static Executor a() {
        return a.d;
    }

    static ExecutorService b() {
        return a.c;
    }
}
