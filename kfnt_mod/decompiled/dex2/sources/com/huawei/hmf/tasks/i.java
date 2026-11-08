package com.huawei.hmf.tasks;

import com.huawei.hmf.tasks.a.f;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class i {
    private static com.huawei.hmf.tasks.a.f a = new com.huawei.hmf.tasks.a.f();

    public static <TResult> f<TResult> a(Callable<TResult> callable) {
        return a.a(h.b(), callable);
    }

    public static <TResult> TResult a(f<TResult> fVar) throws ExecutionException, InterruptedException {
        com.huawei.hmf.tasks.a.f.a("await must not be called on the UI thread");
        if (fVar.a()) {
            return (TResult) com.huawei.hmf.tasks.a.f.a(fVar);
        }
        f.a aVar = new f.a();
        fVar.a((e) aVar).a((d) aVar);
        aVar.a.await();
        return (TResult) com.huawei.hmf.tasks.a.f.a(fVar);
    }
}
