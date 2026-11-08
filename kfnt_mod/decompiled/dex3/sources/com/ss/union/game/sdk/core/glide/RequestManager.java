package com.ss.union.game.sdk.core.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.annotation.GuardedBy;
import com.ss.union.game.sdk.core.glide.load.engine.DiskCacheStrategy;
import com.ss.union.game.sdk.core.glide.load.resource.gif.GifDrawable;
import com.ss.union.game.sdk.core.glide.manager.ConnectivityMonitor;
import com.ss.union.game.sdk.core.glide.manager.ConnectivityMonitorFactory;
import com.ss.union.game.sdk.core.glide.manager.Lifecycle;
import com.ss.union.game.sdk.core.glide.manager.LifecycleListener;
import com.ss.union.game.sdk.core.glide.manager.RequestManagerTreeNode;
import com.ss.union.game.sdk.core.glide.manager.RequestTracker;
import com.ss.union.game.sdk.core.glide.manager.TargetTracker;
import com.ss.union.game.sdk.core.glide.request.BaseRequestOptions;
import com.ss.union.game.sdk.core.glide.request.Request;
import com.ss.union.game.sdk.core.glide.request.RequestListener;
import com.ss.union.game.sdk.core.glide.request.RequestOptions;
import com.ss.union.game.sdk.core.glide.request.target.Target;
import com.ss.union.game.sdk.core.glide.request.target.ViewTarget;
import com.ss.union.game.sdk.core.glide.request.transition.Transition;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RequestManager implements com.ss.union.game.sdk.core.glide.b<RequestBuilder<Drawable>>, LifecycleListener {
    private static final RequestOptions b = RequestOptions.decodeTypeOf(Bitmap.class).lock();
    private static final RequestOptions c = RequestOptions.decodeTypeOf(GifDrawable.class).lock();
    private static final RequestOptions d = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA).priority(Priority.LOW).skipMemoryCache(true);
    final Lifecycle a;
    protected final Context context;

    @GuardedBy("this")
    private final RequestTracker e;

    @GuardedBy("this")
    private final RequestManagerTreeNode f;

    @GuardedBy("this")
    private final TargetTracker g;
    protected final Glide glide;
    private final Runnable h;
    private final Handler i;
    private final ConnectivityMonitor j;
    private final CopyOnWriteArrayList<RequestListener<Object>> k;

    @GuardedBy("this")
    private RequestOptions l;

    public RequestManager(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context) {
        this(glide, lifecycle, requestManagerTreeNode, new RequestTracker(), glide.a(), context);
    }

    RequestManager(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, RequestTracker requestTracker, ConnectivityMonitorFactory connectivityMonitorFactory, Context context) {
        this.g = new TargetTracker();
        this.h = new Runnable() { // from class: com.ss.union.game.sdk.core.glide.RequestManager.1
            @Override // java.lang.Runnable
            public void run() {
                RequestManager.this.a.addListener(RequestManager.this);
            }
        };
        this.i = new Handler(Looper.getMainLooper());
        this.glide = glide;
        this.a = lifecycle;
        this.f = requestManagerTreeNode;
        this.e = requestTracker;
        this.context = context;
        this.j = connectivityMonitorFactory.build(context.getApplicationContext(), new b(requestTracker));
        if (Util.isOnBackgroundThread()) {
            this.i.post(this.h);
        } else {
            lifecycle.addListener(this);
        }
        lifecycle.addListener(this.j);
        this.k = new CopyOnWriteArrayList<>(glide.b().getDefaultRequestListeners());
        setRequestOptions(glide.b().getDefaultRequestOptions());
        glide.a(this);
    }

    protected synchronized void setRequestOptions(RequestOptions requestOptions) {
        this.l = requestOptions.mo3clone().autoClone();
    }

    private synchronized void a(RequestOptions requestOptions) {
        this.l = this.l.apply(requestOptions);
    }

    public synchronized RequestManager applyDefaultRequestOptions(RequestOptions requestOptions) {
        a(requestOptions);
        return this;
    }

    public synchronized RequestManager setDefaultRequestOptions(RequestOptions requestOptions) {
        setRequestOptions(requestOptions);
        return this;
    }

    public RequestManager addDefaultRequestListener(RequestListener<Object> requestListener) {
        this.k.add(requestListener);
        return this;
    }

    public synchronized boolean isPaused() {
        return this.e.isPaused();
    }

    public synchronized void pauseRequests() {
        this.e.pauseRequests();
    }

    public synchronized void pauseAllRequests() {
        this.e.pauseAllRequests();
    }

    public synchronized void pauseRequestsRecursive() {
        pauseRequests();
        Iterator<RequestManager> it = this.f.getDescendants().iterator();
        while (it.hasNext()) {
            it.next().pauseRequests();
        }
    }

    public synchronized void resumeRequests() {
        this.e.resumeRequests();
    }

    public synchronized void resumeRequestsRecursive() {
        Util.assertMainThread();
        resumeRequests();
        Iterator<RequestManager> it = this.f.getDescendants().iterator();
        while (it.hasNext()) {
            it.next().resumeRequests();
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public synchronized void onStart() {
        resumeRequests();
        this.g.onStart();
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public synchronized void onStop() {
        pauseRequests();
        this.g.onStop();
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public synchronized void onDestroy() {
        this.g.onDestroy();
        Iterator<Target<?>> it = this.g.getAll().iterator();
        while (it.hasNext()) {
            clear(it.next());
        }
        this.g.clear();
        this.e.clearRequests();
        this.a.removeListener(this);
        this.a.removeListener(this.j);
        this.i.removeCallbacks(this.h);
        this.glide.b(this);
    }

    public RequestBuilder<Bitmap> asBitmap() {
        return as(Bitmap.class).apply((BaseRequestOptions<?>) b);
    }

    public RequestBuilder<GifDrawable> asGif() {
        return as(GifDrawable.class).apply((BaseRequestOptions<?>) c);
    }

    public RequestBuilder<Drawable> asDrawable() {
        return as(Drawable.class);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<Drawable> load(Bitmap bitmap) {
        return asDrawable().load(bitmap);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<Drawable> load(Drawable drawable) {
        return asDrawable().load(drawable);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<Drawable> load(String str) {
        return asDrawable().load(str);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<Drawable> load(Uri uri) {
        return asDrawable().load(uri);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<Drawable> load(File file) {
        return asDrawable().load(file);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<Drawable> load(Integer num) {
        return asDrawable().load(num);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ss.union.game.sdk.core.glide.b
    @Deprecated
    public RequestBuilder<Drawable> load(URL url) {
        return asDrawable().load(url);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<Drawable> load(byte[] bArr) {
        return asDrawable().load(bArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<Drawable> load(Object obj) {
        return asDrawable().load(obj);
    }

    public RequestBuilder<File> downloadOnly() {
        return as(File.class).apply((BaseRequestOptions<?>) d);
    }

    public RequestBuilder<File> download(Object obj) {
        return downloadOnly().load(obj);
    }

    public RequestBuilder<File> asFile() {
        return as(File.class).apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true));
    }

    public <ResourceType> RequestBuilder<ResourceType> as(Class<ResourceType> cls) {
        return new RequestBuilder<>(this.glide, this, cls, this.context);
    }

    public void clear(View view) {
        clear(new a(view));
    }

    public synchronized void clear(Target<?> target) {
        if (target == null) {
            return;
        }
        b(target);
    }

    private void b(Target<?> target) {
        if (a(target) || this.glide.a(target) || target.getRequest() == null) {
            return;
        }
        Request request = target.getRequest();
        target.setRequest(null);
        request.clear();
    }

    synchronized boolean a(Target<?> target) {
        Request request = target.getRequest();
        if (request == null) {
            return true;
        }
        if (!this.e.clearRemoveAndRecycle(request)) {
            return false;
        }
        this.g.untrack(target);
        target.setRequest(null);
        return true;
    }

    synchronized void a(Target<?> target, Request request) {
        this.g.track(target);
        this.e.runRequest(request);
    }

    List<RequestListener<Object>> a() {
        return this.k;
    }

    synchronized RequestOptions b() {
        return this.l;
    }

    <T> TransitionOptions<?, T> a(Class<T> cls) {
        return this.glide.b().getDefaultTransitionOptions(cls);
    }

    public synchronized String toString() {
        return super.toString() + "{tracker=" + this.e + ", treeNode=" + this.f + "}";
    }

    private class b implements ConnectivityMonitor.ConnectivityListener {

        @GuardedBy("RequestManager.this")
        private final RequestTracker b;

        b(RequestTracker requestTracker) {
            this.b = requestTracker;
        }

        @Override // com.ss.union.game.sdk.core.glide.manager.ConnectivityMonitor.ConnectivityListener
        public void onConnectivityChanged(boolean z) {
            if (z) {
                synchronized (RequestManager.this) {
                    this.b.restartRequests();
                }
            }
        }
    }

    private static class a extends ViewTarget<View, Object> {
        @Override // com.ss.union.game.sdk.core.glide.request.target.Target
        public void onResourceReady(Object obj, Transition<? super Object> transition) {
        }

        a(View view) {
            super(view);
        }
    }
}
