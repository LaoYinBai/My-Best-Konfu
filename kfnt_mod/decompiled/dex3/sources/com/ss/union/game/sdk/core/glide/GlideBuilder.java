package com.ss.union.game.sdk.core.glide;

import android.content.Context;
import androidx.collection.ArrayMap;
import com.ss.union.game.sdk.core.glide.load.engine.Engine;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.LruArrayPool;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache;
import com.ss.union.game.sdk.core.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.ss.union.game.sdk.core.glide.load.engine.cache.LruResourceCache;
import com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache;
import com.ss.union.game.sdk.core.glide.load.engine.cache.MemorySizeCalculator;
import com.ss.union.game.sdk.core.glide.load.engine.executor.GlideExecutor;
import com.ss.union.game.sdk.core.glide.manager.ConnectivityMonitorFactory;
import com.ss.union.game.sdk.core.glide.manager.DefaultConnectivityMonitorFactory;
import com.ss.union.game.sdk.core.glide.manager.RequestManagerRetriever;
import com.ss.union.game.sdk.core.glide.request.RequestListener;
import com.ss.union.game.sdk.core.glide.request.RequestOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class GlideBuilder {
    private Engine b;
    private BitmapPool c;
    private ArrayPool d;
    private MemoryCache e;
    private GlideExecutor f;
    private GlideExecutor g;
    private DiskCache.Factory h;
    private MemorySizeCalculator i;
    private ConnectivityMonitorFactory j;
    private RequestManagerRetriever.RequestManagerFactory m;
    private GlideExecutor n;
    private boolean o;
    private List<RequestListener<Object>> p;
    private boolean q;
    private final Map<Class<?>, TransitionOptions<?, ?>> a = new ArrayMap();
    private int k = 4;
    private RequestOptions l = new RequestOptions();

    public GlideBuilder setBitmapPool(BitmapPool bitmapPool) {
        this.c = bitmapPool;
        return this;
    }

    public GlideBuilder setArrayPool(ArrayPool arrayPool) {
        this.d = arrayPool;
        return this;
    }

    public GlideBuilder setMemoryCache(MemoryCache memoryCache) {
        this.e = memoryCache;
        return this;
    }

    public GlideBuilder setDiskCache(DiskCache.Factory factory) {
        this.h = factory;
        return this;
    }

    @Deprecated
    public GlideBuilder setResizeExecutor(GlideExecutor glideExecutor) {
        return setSourceExecutor(glideExecutor);
    }

    public GlideBuilder setSourceExecutor(GlideExecutor glideExecutor) {
        this.f = glideExecutor;
        return this;
    }

    public GlideBuilder setDiskCacheExecutor(GlideExecutor glideExecutor) {
        this.g = glideExecutor;
        return this;
    }

    public GlideBuilder setAnimationExecutor(GlideExecutor glideExecutor) {
        this.n = glideExecutor;
        return this;
    }

    public GlideBuilder setDefaultRequestOptions(RequestOptions requestOptions) {
        this.l = requestOptions;
        return this;
    }

    public <T> GlideBuilder setDefaultTransitionOptions(Class<T> cls, TransitionOptions<?, T> transitionOptions) {
        this.a.put(cls, transitionOptions);
        return this;
    }

    public GlideBuilder setMemorySizeCalculator(MemorySizeCalculator.Builder builder) {
        return setMemorySizeCalculator(builder.build());
    }

    public GlideBuilder setMemorySizeCalculator(MemorySizeCalculator memorySizeCalculator) {
        this.i = memorySizeCalculator;
        return this;
    }

    public GlideBuilder setConnectivityMonitorFactory(ConnectivityMonitorFactory connectivityMonitorFactory) {
        this.j = connectivityMonitorFactory;
        return this;
    }

    public GlideBuilder setLogLevel(int i) {
        if (i < 2 || i > 6) {
            throw new IllegalArgumentException("Log level must be one of Log.VERBOSE, Log.DEBUG, Log.INFO, Log.WARN, or Log.ERROR");
        }
        this.k = i;
        return this;
    }

    public GlideBuilder setIsActiveResourceRetentionAllowed(boolean z) {
        this.o = z;
        return this;
    }

    public GlideBuilder addGlobalRequestListener(RequestListener<Object> requestListener) {
        if (this.p == null) {
            this.p = new ArrayList();
        }
        this.p.add(requestListener);
        return this;
    }

    public GlideBuilder setLogRequestOrigins(boolean z) {
        this.q = z;
        return this;
    }

    void a(RequestManagerRetriever.RequestManagerFactory requestManagerFactory) {
        this.m = requestManagerFactory;
    }

    GlideBuilder a(Engine engine) {
        this.b = engine;
        return this;
    }

    Glide a(Context context) {
        if (this.f == null) {
            this.f = GlideExecutor.newSourceExecutor();
        }
        if (this.g == null) {
            this.g = GlideExecutor.newDiskCacheExecutor();
        }
        if (this.n == null) {
            this.n = GlideExecutor.newAnimationExecutor();
        }
        if (this.i == null) {
            this.i = new MemorySizeCalculator.Builder(context).build();
        }
        if (this.j == null) {
            this.j = new DefaultConnectivityMonitorFactory();
        }
        if (this.c == null) {
            int bitmapPoolSize = this.i.getBitmapPoolSize();
            if (bitmapPoolSize > 0) {
                this.c = new LruBitmapPool(bitmapPoolSize);
            } else {
                this.c = new BitmapPoolAdapter();
            }
        }
        if (this.d == null) {
            this.d = new LruArrayPool(this.i.getArrayPoolSizeInBytes());
        }
        if (this.e == null) {
            this.e = new LruResourceCache(this.i.getMemoryCacheSize());
        }
        if (this.h == null) {
            this.h = new InternalCacheDiskCacheFactory(context);
        }
        if (this.b == null) {
            this.b = new Engine(this.e, this.h, this.g, this.f, GlideExecutor.newUnlimitedSourceExecutor(), GlideExecutor.newAnimationExecutor(), this.o);
        }
        if (this.p == null) {
            this.p = Collections.emptyList();
        } else {
            this.p = Collections.unmodifiableList(this.p);
        }
        return new Glide(context, this.b, this.e, this.c, this.d, new RequestManagerRetriever(this.m), this.j, this.k, this.l.lock(), this.a, this.p, this.q);
    }
}
