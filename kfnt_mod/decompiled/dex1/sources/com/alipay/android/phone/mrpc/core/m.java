package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class m extends FutureTask<u> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ q f2464a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ l f2465b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    m(l lVar, Callable callable, q qVar) {
        super(callable);
        this.f2465b = lVar;
        this.f2464a = qVar;
    }

    @Override // java.util.concurrent.FutureTask
    protected final void done() {
        o oVarA = this.f2464a.a();
        if (oVarA.f() == null) {
            super.done();
            return;
        }
        try {
            get();
            if (isCancelled() || oVarA.h()) {
                oVarA.g();
                if (isCancelled() && isDone()) {
                    return;
                }
                cancel(false);
            }
        } catch (InterruptedException e) {
            new StringBuilder().append(e);
        } catch (CancellationException unused) {
            oVarA.g();
        } catch (ExecutionException e2) {
            if (e2.getCause() == null || !(e2.getCause() instanceof HttpException)) {
                new StringBuilder().append(e2);
                return;
            }
            HttpException httpException = (HttpException) e2.getCause();
            httpException.getCode();
            httpException.getMsg();
        } catch (Throwable th) {
            throw new RuntimeException("An error occured while executing http request", th);
        }
    }
}
