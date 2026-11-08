package com.ss.union.game.sdk.core.glide.request;

import android.graphics.drawable.Drawable;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.engine.GlideException;
import com.ss.union.game.sdk.core.glide.request.target.SizeReadyCallback;
import com.ss.union.game.sdk.core.glide.request.target.Target;
import com.ss.union.game.sdk.core.glide.request.transition.Transition;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RequestFutureTarget<R> implements FutureTarget<R>, RequestListener<R> {
    private static final a a = new a();
    private final int b;
    private final int c;
    private final boolean d;
    private final a e;
    private R f;
    private Request g;
    private boolean h;
    private boolean i;
    private boolean j;
    private GlideException k;

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public void onLoadCleared(Drawable drawable) {
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public void onLoadStarted(Drawable drawable) {
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onStart() {
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onStop() {
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public void removeCallback(SizeReadyCallback sizeReadyCallback) {
    }

    public RequestFutureTarget(int i, int i2) {
        this(i, i2, true, a);
    }

    RequestFutureTarget(int i, int i2, boolean z, a aVar) {
        this.b = i;
        this.c = i2;
        this.d = z;
        this.e = aVar;
    }

    @Override // java.util.concurrent.Future
    public synchronized boolean cancel(boolean z) {
        if (isDone()) {
            return false;
        }
        this.h = true;
        this.e.a(this);
        if (z && this.g != null) {
            this.g.clear();
            this.g = null;
        }
        return true;
    }

    @Override // java.util.concurrent.Future
    public synchronized boolean isCancelled() {
        return this.h;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0010  */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean isDone() {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.h     // Catch: java.lang.Throwable -> L13
            if (r0 != 0) goto L10
            boolean r0 = r1.i     // Catch: java.lang.Throwable -> L13
            if (r0 != 0) goto L10
            boolean r0 = r1.j     // Catch: java.lang.Throwable -> L13
            if (r0 == 0) goto Le
            goto L10
        Le:
            r0 = 0
            goto L11
        L10:
            r0 = 1
        L11:
            monitor-exit(r1)
            return r0
        L13:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.core.glide.request.RequestFutureTarget.isDone():boolean");
    }

    @Override // java.util.concurrent.Future
    public R get() throws ExecutionException, InterruptedException {
        try {
            return a(null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    @Override // java.util.concurrent.Future
    public R get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return a(Long.valueOf(timeUnit.toMillis(j)));
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public void getSize(SizeReadyCallback sizeReadyCallback) {
        sizeReadyCallback.onSizeReady(this.b, this.c);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public synchronized void setRequest(Request request) {
        this.g = request;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public synchronized Request getRequest() {
        return this.g;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public synchronized void onLoadFailed(Drawable drawable) {
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public synchronized void onResourceReady(R r, Transition<? super R> transition) {
    }

    private synchronized R a(Long l) throws ExecutionException, InterruptedException, TimeoutException {
        if (this.d && !isDone()) {
            Util.assertBackgroundThread();
        }
        if (this.h) {
            throw new CancellationException();
        }
        if (this.j) {
            throw new ExecutionException(this.k);
        }
        if (this.i) {
            return this.f;
        }
        if (l == null) {
            this.e.a(this, 0L);
        } else if (l.longValue() > 0) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jLongValue = l.longValue() + jCurrentTimeMillis;
            while (!isDone() && jCurrentTimeMillis < jLongValue) {
                this.e.a(this, jLongValue - jCurrentTimeMillis);
                jCurrentTimeMillis = System.currentTimeMillis();
            }
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (this.j) {
            throw new ExecutionException(this.k);
        }
        if (this.h) {
            throw new CancellationException();
        }
        if (!this.i) {
            throw new TimeoutException();
        }
        return this.f;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.RequestListener
    public synchronized boolean onLoadFailed(GlideException glideException, Object obj, Target<R> target, boolean z) {
        this.j = true;
        this.k = glideException;
        this.e.a(this);
        return false;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.RequestListener
    public synchronized boolean onResourceReady(R r, Object obj, Target<R> target, DataSource dataSource, boolean z) {
        this.i = true;
        this.f = r;
        this.e.a(this);
        return false;
    }

    static class a {
        a() {
        }

        void a(Object obj, long j) throws InterruptedException {
            obj.wait(j);
        }

        void a(Object obj) {
            obj.notifyAll();
        }
    }
}
