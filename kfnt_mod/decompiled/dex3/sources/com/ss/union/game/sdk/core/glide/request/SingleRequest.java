package com.ss.union.game.sdk.core.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.core.util.Pools;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.union.game.sdk.core.glide.GlideContext;
import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.engine.Engine;
import com.ss.union.game.sdk.core.glide.load.engine.GlideException;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.resource.drawable.DrawableDecoderCompat;
import com.ss.union.game.sdk.core.glide.request.target.SizeReadyCallback;
import com.ss.union.game.sdk.core.glide.request.target.Target;
import com.ss.union.game.sdk.core.glide.request.transition.TransitionFactory;
import com.ss.union.game.sdk.core.glide.util.LogTime;
import com.ss.union.game.sdk.core.glide.util.Util;
import com.ss.union.game.sdk.core.glide.util.pool.FactoryPools;
import com.ss.union.game.sdk.core.glide.util.pool.StateVerifier;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class SingleRequest<R> implements Request, ResourceCallback, SizeReadyCallback, FactoryPools.Poolable {
    private static final String b = "Glide";
    private Drawable A;
    private Drawable B;
    private Drawable C;
    private int D;
    private int E;
    private RuntimeException F;
    private boolean d;
    private final String f;
    private final StateVerifier g;
    private RequestListener<R> h;
    private RequestCoordinator i;
    private Context j;
    private GlideContext k;
    private Object l;
    private Class<R> m;
    private BaseRequestOptions<?> n;
    private int o;
    private int p;
    private Priority q;
    private Target<R> r;
    private List<RequestListener<R>> s;
    private Engine t;
    private TransitionFactory<? super R> u;
    private Executor v;
    private Resource<R> w;
    private Engine.LoadStatus x;
    private long y;

    @GuardedBy("this")
    private a z;
    private static final Pools.Pool<SingleRequest<?>> c = FactoryPools.threadSafe(Constants.MIN_PROGRESS_TIME, new FactoryPools.Factory<SingleRequest<?>>() { // from class: com.ss.union.game.sdk.core.glide.request.SingleRequest.1
        @Override // com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.Factory
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SingleRequest<?> create() {
            return new SingleRequest<>();
        }
    });
    private static final String a = "Request";
    private static final boolean e = Log.isLoggable(a, 2);

    private enum a {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    public static <R> SingleRequest<R> obtain(Context context, GlideContext glideContext, Object obj, Class<R> cls, BaseRequestOptions<?> baseRequestOptions, int i, int i2, Priority priority, Target<R> target, RequestListener<R> requestListener, List<RequestListener<R>> list, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> transitionFactory, Executor executor) {
        SingleRequest<R> singleRequest = (SingleRequest) c.acquire();
        if (singleRequest == null) {
            singleRequest = new SingleRequest<>();
        }
        singleRequest.a(context, glideContext, obj, cls, baseRequestOptions, i, i2, priority, target, requestListener, list, requestCoordinator, engine, transitionFactory, executor);
        return singleRequest;
    }

    SingleRequest() {
        this.f = e ? String.valueOf(super.hashCode()) : null;
        this.g = StateVerifier.newInstance();
    }

    private synchronized void a(Context context, GlideContext glideContext, Object obj, Class<R> cls, BaseRequestOptions<?> baseRequestOptions, int i, int i2, Priority priority, Target<R> target, RequestListener<R> requestListener, List<RequestListener<R>> list, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> transitionFactory, Executor executor) {
        this.j = context;
        this.k = glideContext;
        this.l = obj;
        this.m = cls;
        this.n = baseRequestOptions;
        this.o = i;
        this.p = i2;
        this.q = priority;
        this.r = target;
        this.h = requestListener;
        this.s = list;
        this.i = requestCoordinator;
        this.t = engine;
        this.u = transitionFactory;
        this.v = executor;
        this.z = a.PENDING;
        if (this.F == null && glideContext.isLoggingRequestOriginsEnabled()) {
            this.F = new RuntimeException("Glide request origin trace");
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.Poolable
    public StateVerifier getVerifier() {
        return this.g;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public synchronized void recycle() {
        b();
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = -1;
        this.p = -1;
        this.r = null;
        this.s = null;
        this.h = null;
        this.i = null;
        this.u = null;
        this.x = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = -1;
        this.E = -1;
        this.F = null;
        c.release(this);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public synchronized void begin() {
        b();
        this.g.throwIfRecycled();
        this.y = LogTime.getLogTime();
        if (this.l == null) {
            if (Util.isValidDimensions(this.o, this.p)) {
                this.D = this.o;
                this.E = this.p;
            }
            a(new GlideException("Received null model"), e() == null ? 5 : 3);
            return;
        }
        if (this.z == a.RUNNING) {
            throw new IllegalArgumentException("Cannot restart a running request");
        }
        if (this.z == a.COMPLETE) {
            onResourceReady(this.w, DataSource.MEMORY_CACHE);
            return;
        }
        this.z = a.WAITING_FOR_SIZE;
        if (Util.isValidDimensions(this.o, this.p)) {
            onSizeReady(this.o, this.p);
        } else {
            this.r.getSize(this);
        }
        if ((this.z == a.RUNNING || this.z == a.WAITING_FOR_SIZE) && i()) {
            this.r.onLoadStarted(d());
        }
        if (e) {
            a("finished run method in " + LogTime.getElapsedMillis(this.y));
        }
    }

    private void a() {
        b();
        this.g.throwIfRecycled();
        this.r.removeCallback(this);
        if (this.x != null) {
            this.x.cancel();
            this.x = null;
        }
    }

    private void b() {
        if (this.d) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public synchronized void clear() {
        b();
        this.g.throwIfRecycled();
        if (this.z == a.CLEARED) {
            return;
        }
        a();
        if (this.w != null) {
            a((Resource<?>) this.w);
        }
        if (h()) {
            this.r.onLoadCleared(d());
        }
        this.z = a.CLEARED;
    }

    private void a(Resource<?> resource) {
        this.t.release(resource);
        this.w = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0010  */
    @Override // com.ss.union.game.sdk.core.glide.request.Request
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean isRunning() {
        /*
            r2 = this;
            monitor-enter(r2)
            com.ss.union.game.sdk.core.glide.request.SingleRequest$a r0 = r2.z     // Catch: java.lang.Throwable -> L13
            com.ss.union.game.sdk.core.glide.request.SingleRequest$a r1 = com.ss.union.game.sdk.core.glide.request.SingleRequest.a.RUNNING     // Catch: java.lang.Throwable -> L13
            if (r0 == r1) goto L10
            com.ss.union.game.sdk.core.glide.request.SingleRequest$a r0 = r2.z     // Catch: java.lang.Throwable -> L13
            com.ss.union.game.sdk.core.glide.request.SingleRequest$a r1 = com.ss.union.game.sdk.core.glide.request.SingleRequest.a.WAITING_FOR_SIZE     // Catch: java.lang.Throwable -> L13
            if (r0 != r1) goto Le
            goto L10
        Le:
            r0 = 0
            goto L11
        L10:
            r0 = 1
        L11:
            monitor-exit(r2)
            return r0
        L13:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.core.glide.request.SingleRequest.isRunning():boolean");
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public synchronized boolean isComplete() {
        return this.z == a.COMPLETE;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public synchronized boolean isResourceSet() {
        return isComplete();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public synchronized boolean isCleared() {
        return this.z == a.CLEARED;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public synchronized boolean isFailed() {
        return this.z == a.FAILED;
    }

    private Drawable c() {
        if (this.A == null) {
            this.A = this.n.getErrorPlaceholder();
            if (this.A == null && this.n.getErrorId() > 0) {
                this.A = a(this.n.getErrorId());
            }
        }
        return this.A;
    }

    private Drawable d() {
        if (this.B == null) {
            this.B = this.n.getPlaceholderDrawable();
            if (this.B == null && this.n.getPlaceholderId() > 0) {
                this.B = a(this.n.getPlaceholderId());
            }
        }
        return this.B;
    }

    private Drawable e() {
        if (this.C == null) {
            this.C = this.n.getFallbackDrawable();
            if (this.C == null && this.n.getFallbackId() > 0) {
                this.C = a(this.n.getFallbackId());
            }
        }
        return this.C;
    }

    private Drawable a(int i) {
        return DrawableDecoderCompat.getDrawable(this.k, i, this.n.getTheme() != null ? this.n.getTheme() : this.j.getTheme());
    }

    private synchronized void f() {
        if (i()) {
            Drawable drawableE = this.l == null ? e() : null;
            if (drawableE == null) {
                drawableE = c();
            }
            if (drawableE == null) {
                drawableE = d();
            }
            this.r.onLoadFailed(drawableE);
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.SizeReadyCallback
    public synchronized void onSizeReady(int i, int i2) throws Throwable {
        try {
            this.g.throwIfRecycled();
            if (e) {
                a("Got onSizeReady in " + LogTime.getElapsedMillis(this.y));
            }
            if (this.z != a.WAITING_FOR_SIZE) {
                return;
            }
            this.z = a.RUNNING;
            float sizeMultiplier = this.n.getSizeMultiplier();
            this.D = a(i, sizeMultiplier);
            this.E = a(i2, sizeMultiplier);
            if (e) {
                a("finished setup for calling load in " + LogTime.getElapsedMillis(this.y));
            }
            try {
                try {
                    this.x = this.t.load(this.k, this.l, this.n.getSignature(), this.D, this.E, this.n.getResourceClass(), this.m, this.q, this.n.getDiskCacheStrategy(), this.n.getTransformations(), this.n.isTransformationRequired(), this.n.a(), this.n.getOptions(), this.n.isMemoryCacheable(), this.n.getUseUnlimitedSourceGeneratorsPool(), this.n.getUseAnimationPool(), this.n.getOnlyRetrieveFromCache(), this, this.v);
                    if (this.z != a.RUNNING) {
                        this.x = null;
                    }
                    if (e) {
                        a("finished onSizeReady in " + LogTime.getElapsedMillis(this.y));
                    }
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private static int a(int i, float f) {
        return i == Integer.MIN_VALUE ? i : Math.round(f * i);
    }

    private boolean g() {
        return this.i == null || this.i.canSetImage(this);
    }

    private boolean h() {
        return this.i == null || this.i.canNotifyCleared(this);
    }

    private boolean i() {
        return this.i == null || this.i.canNotifyStatusChanged(this);
    }

    private boolean j() {
        return this.i == null || !this.i.isAnyResourceSet();
    }

    private void k() {
        if (this.i != null) {
            this.i.onRequestSuccess(this);
        }
    }

    private void l() {
        if (this.i != null) {
            this.i.onRequestFailed(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ss.union.game.sdk.core.glide.request.ResourceCallback
    public synchronized void onResourceReady(Resource<?> resource, DataSource dataSource) {
        this.g.throwIfRecycled();
        this.x = null;
        if (resource == null) {
            onLoadFailed(new GlideException("Expected to receive a Resource<R> with an object of " + this.m + " inside, but instead got null."));
            return;
        }
        Object obj = resource.get();
        if (obj != null && this.m.isAssignableFrom(obj.getClass())) {
            if (!g()) {
                a(resource);
                this.z = a.COMPLETE;
                return;
            } else {
                a(resource, obj, dataSource);
                return;
            }
        }
        a(resource);
        StringBuilder sb = new StringBuilder();
        sb.append("Expected to receive an object of ");
        sb.append(this.m);
        sb.append(" but instead got ");
        sb.append(obj != null ? obj.getClass() : "");
        sb.append("{");
        sb.append(obj);
        sb.append("} inside Resource{");
        sb.append(resource);
        sb.append("}.");
        sb.append(obj != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.");
        onLoadFailed(new GlideException(sb.toString()));
    }

    private synchronized void a(Resource<R> resource, R r, DataSource dataSource) {
        boolean zOnResourceReady;
        boolean zJ = j();
        this.z = a.COMPLETE;
        this.w = resource;
        if (this.k.getLogLevel() <= 3) {
            Log.d(b, "Finished loading " + r.getClass().getSimpleName() + " from " + dataSource + " for " + this.l + " with size [" + this.D + "x" + this.E + "] in " + LogTime.getElapsedMillis(this.y) + " ms");
        }
        boolean z = true;
        this.d = true;
        try {
            if (this.s != null) {
                Iterator<RequestListener<R>> it = this.s.iterator();
                zOnResourceReady = false;
                while (it.hasNext()) {
                    zOnResourceReady |= it.next().onResourceReady(r, this.l, this.r, dataSource, zJ);
                }
            } else {
                zOnResourceReady = false;
            }
            if (this.h == null || !this.h.onResourceReady(r, this.l, this.r, dataSource, zJ)) {
                z = false;
            }
            if (!(z | zOnResourceReady)) {
                this.r.onResourceReady(r, this.u.build(dataSource, zJ));
            }
            this.d = false;
            k();
        } catch (Throwable th) {
            this.d = false;
            throw th;
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.request.ResourceCallback
    public synchronized void onLoadFailed(GlideException glideException) {
        a(glideException, 5);
    }

    private synchronized void a(GlideException glideException, int i) {
        boolean zOnLoadFailed;
        this.g.throwIfRecycled();
        glideException.setOrigin(this.F);
        int logLevel = this.k.getLogLevel();
        if (logLevel <= i) {
            Log.w(b, "Load failed for " + this.l + " with size [" + this.D + "x" + this.E + "]", glideException);
            if (logLevel <= 4) {
                glideException.logRootCauses(b);
            }
        }
        this.x = null;
        this.z = a.FAILED;
        boolean z = true;
        this.d = true;
        try {
            if (this.s != null) {
                Iterator<RequestListener<R>> it = this.s.iterator();
                zOnLoadFailed = false;
                while (it.hasNext()) {
                    zOnLoadFailed |= it.next().onLoadFailed(glideException, this.l, this.r, j());
                }
            } else {
                zOnLoadFailed = false;
            }
            if (this.h == null || !this.h.onLoadFailed(glideException, this.l, this.r, j())) {
                z = false;
            }
            if (!(zOnLoadFailed | z)) {
                f();
            }
            this.d = false;
            l();
        } catch (Throwable th) {
            this.d = false;
            throw th;
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public synchronized boolean isEquivalentTo(Request request) {
        boolean z = false;
        if (!(request instanceof SingleRequest)) {
            return false;
        }
        SingleRequest<?> singleRequest = (SingleRequest) request;
        synchronized (singleRequest) {
            if (this.o == singleRequest.o && this.p == singleRequest.p && Util.bothModelsNullEquivalentOrEquals(this.l, singleRequest.l) && this.m.equals(singleRequest.m) && this.n.equals(singleRequest.n) && this.q == singleRequest.q && a(singleRequest)) {
                z = true;
            }
        }
        return z;
    }

    private synchronized boolean a(SingleRequest<?> singleRequest) {
        boolean z;
        synchronized (singleRequest) {
            z = (this.s == null ? 0 : this.s.size()) == (singleRequest.s == null ? 0 : singleRequest.s.size());
        }
        return z;
    }

    private void a(String str) {
        Log.v(a, str + " this: " + this.f);
    }
}
