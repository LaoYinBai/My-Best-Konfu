package com.ss.union.game.sdk.core.glide.load.engine;

import android.os.Build;
import android.util.Log;
import androidx.core.util.Pools;
import com.ss.union.game.sdk.core.glide.GlideContext;
import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.Registry;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.EncodeStrategy;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceEncoder;
import com.ss.union.game.sdk.core.glide.load.Transformation;
import com.ss.union.game.sdk.core.glide.load.data.DataFetcher;
import com.ss.union.game.sdk.core.glide.load.data.DataRewinder;
import com.ss.union.game.sdk.core.glide.load.engine.DataFetcherGenerator;
import com.ss.union.game.sdk.core.glide.load.engine.DecodePath;
import com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.Downsampler;
import com.ss.union.game.sdk.core.glide.util.LogTime;
import com.ss.union.game.sdk.core.glide.util.pool.FactoryPools;
import com.ss.union.game.sdk.core.glide.util.pool.GlideTrace;
import com.ss.union.game.sdk.core.glide.util.pool.StateVerifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class g<R> implements DataFetcherGenerator.FetcherReadyCallback, FactoryPools.Poolable, Comparable<g<?>>, Runnable {
    private static final String a = "DecodeJob";
    private Object A;
    private DataSource B;
    private DataFetcher<?> C;
    private volatile DataFetcherGenerator D;
    private volatile boolean E;
    private volatile boolean F;
    private final d e;
    private final Pools.Pool<g<?>> f;
    private GlideContext i;
    private Key j;
    private Priority k;
    private j l;
    private int m;
    private int n;
    private DiskCacheStrategy o;
    private Options p;
    private a<R> q;
    private int r;
    private EnumC0081g s;
    private f t;
    private long u;
    private boolean v;
    private Object w;
    private Thread x;
    private Key y;
    private Key z;
    private final com.ss.union.game.sdk.core.glide.load.engine.f<R> b = new com.ss.union.game.sdk.core.glide.load.engine.f<>();
    private final List<Throwable> c = new ArrayList();
    private final StateVerifier d = StateVerifier.newInstance();
    private final c<?> g = new c<>();
    private final e h = new e();

    interface a<R> {
        void a(GlideException glideException);

        void a(Resource<R> resource, DataSource dataSource);

        void a(g<?> gVar);
    }

    interface d {
        DiskCache a();
    }

    private enum f {
        INITIALIZE,
        SWITCH_TO_SOURCE_SERVICE,
        DECODE_DATA
    }

    /* renamed from: com.ss.union.game.sdk.core.glide.load.engine.g$g, reason: collision with other inner class name */
    private enum EnumC0081g {
        INITIALIZE,
        RESOURCE_CACHE,
        DATA_CACHE,
        SOURCE,
        ENCODE,
        FINISHED
    }

    g(d dVar, Pools.Pool<g<?>> pool) {
        this.e = dVar;
        this.f = pool;
    }

    g<R> a(GlideContext glideContext, Object obj, j jVar, Key key, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> map, boolean z, boolean z2, boolean z3, Options options, a<R> aVar, int i3) {
        this.b.a(glideContext, obj, key, i, i2, diskCacheStrategy, cls, cls2, priority, options, map, z, z2, this.e);
        this.i = glideContext;
        this.j = key;
        this.k = priority;
        this.l = jVar;
        this.m = i;
        this.n = i2;
        this.o = diskCacheStrategy;
        this.v = z3;
        this.p = options;
        this.q = aVar;
        this.r = i3;
        this.t = f.INITIALIZE;
        this.w = obj;
        return this;
    }

    boolean a() {
        EnumC0081g enumC0081gA = a(EnumC0081g.INITIALIZE);
        return enumC0081gA == EnumC0081g.RESOURCE_CACHE || enumC0081gA == EnumC0081g.DATA_CACHE;
    }

    void a(boolean z) {
        if (this.h.a(z)) {
            e();
        }
    }

    private void c() {
        if (this.h.a()) {
            e();
        }
    }

    private void d() {
        if (this.h.b()) {
            e();
        }
    }

    private void e() {
        this.h.c();
        this.g.b();
        this.b.a();
        this.E = false;
        this.i = null;
        this.j = null;
        this.p = null;
        this.k = null;
        this.l = null;
        this.q = null;
        this.s = null;
        this.D = null;
        this.x = null;
        this.y = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.u = 0L;
        this.F = false;
        this.w = null;
        this.c.clear();
        this.f.release(this);
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(g<?> gVar) {
        int iF = f() - gVar.f();
        return iF == 0 ? this.r - gVar.r : iF;
    }

    private int f() {
        return this.k.ordinal();
    }

    public void b() {
        this.F = true;
        DataFetcherGenerator dataFetcherGenerator = this.D;
        if (dataFetcherGenerator != null) {
            dataFetcherGenerator.cancel();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        GlideTrace.beginSectionFormat("DecodeJob#run(model=%s)", this.w);
        DataFetcher<?> dataFetcher = this.C;
        try {
            try {
                if (this.F) {
                    j();
                    return;
                }
                g();
                if (dataFetcher != null) {
                    dataFetcher.cleanup();
                }
                GlideTrace.endSection();
            } catch (com.ss.union.game.sdk.core.glide.load.engine.b e2) {
                throw e2;
            } catch (Throwable th) {
                if (Log.isLoggable(a, 3)) {
                    Log.d(a, "DecodeJob threw unexpectedly, isCancelled: " + this.F + ", stage: " + this.s, th);
                }
                if (this.s != EnumC0081g.ENCODE) {
                    this.c.add(th);
                    j();
                }
                if (!this.F) {
                    throw th;
                }
                throw th;
            }
        } finally {
            if (dataFetcher != null) {
                dataFetcher.cleanup();
            }
            GlideTrace.endSection();
        }
    }

    private void g() throws GlideException {
        switch (this.t) {
            case INITIALIZE:
                this.s = a(EnumC0081g.INITIALIZE);
                this.D = h();
                i();
                return;
            case SWITCH_TO_SOURCE_SERVICE:
                i();
                return;
            case DECODE_DATA:
                l();
                return;
            default:
                throw new IllegalStateException("Unrecognized run reason: " + this.t);
        }
    }

    private DataFetcherGenerator h() {
        switch (this.s) {
            case RESOURCE_CACHE:
                return new o(this.b, this);
            case DATA_CACHE:
                return new com.ss.union.game.sdk.core.glide.load.engine.c(this.b, this);
            case SOURCE:
                return new r(this.b, this);
            case FINISHED:
                return null;
            default:
                throw new IllegalStateException("Unrecognized stage: " + this.s);
        }
    }

    private void i() {
        this.x = Thread.currentThread();
        this.u = LogTime.getLogTime();
        boolean zStartNext = false;
        while (!this.F && this.D != null && !(zStartNext = this.D.startNext())) {
            this.s = a(this.s);
            this.D = h();
            if (this.s == EnumC0081g.SOURCE) {
                reschedule();
                return;
            }
        }
        if ((this.s == EnumC0081g.FINISHED || this.F) && !zStartNext) {
            j();
        }
    }

    private void j() {
        k();
        this.q.a(new GlideException("Failed to load resource", new ArrayList(this.c)));
        d();
    }

    private void a(Resource<R> resource, DataSource dataSource) {
        k();
        this.q.a(resource, dataSource);
    }

    private void k() {
        this.d.throwIfRecycled();
        if (this.E) {
            throw new IllegalStateException("Already notified", this.c.isEmpty() ? null : this.c.get(this.c.size() - 1));
        }
        this.E = true;
    }

    private EnumC0081g a(EnumC0081g enumC0081g) {
        switch (enumC0081g) {
            case RESOURCE_CACHE:
                return this.o.decodeCachedData() ? EnumC0081g.DATA_CACHE : a(EnumC0081g.DATA_CACHE);
            case DATA_CACHE:
                return this.v ? EnumC0081g.FINISHED : EnumC0081g.SOURCE;
            case SOURCE:
            case FINISHED:
                return EnumC0081g.FINISHED;
            case INITIALIZE:
                return this.o.decodeCachedResource() ? EnumC0081g.RESOURCE_CACHE : a(EnumC0081g.RESOURCE_CACHE);
            default:
                throw new IllegalArgumentException("Unrecognized stage: " + enumC0081g);
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void reschedule() {
        this.t = f.SWITCH_TO_SOURCE_SERVICE;
        this.q.a((g<?>) this);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void onDataFetcherReady(Key key, Object obj, DataFetcher<?> dataFetcher, DataSource dataSource, Key key2) {
        this.y = key;
        this.A = obj;
        this.C = dataFetcher;
        this.B = dataSource;
        this.z = key2;
        if (Thread.currentThread() != this.x) {
            this.t = f.DECODE_DATA;
            this.q.a((g<?>) this);
        } else {
            GlideTrace.beginSection("DecodeJob.decodeFromRetrievedData");
            try {
                l();
            } finally {
                GlideTrace.endSection();
            }
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void onDataFetcherFailed(Key key, Exception exc, DataFetcher<?> dataFetcher, DataSource dataSource) {
        dataFetcher.cleanup();
        GlideException glideException = new GlideException("Fetching data failed", exc);
        glideException.a(key, dataSource, dataFetcher.getDataClass());
        this.c.add(glideException);
        if (Thread.currentThread() != this.x) {
            this.t = f.SWITCH_TO_SOURCE_SERVICE;
            this.q.a((g<?>) this);
        } else {
            i();
        }
    }

    private void l() throws GlideException {
        if (Log.isLoggable(a, 2)) {
            a("Retrieved data", this.u, "data: " + this.A + ", cache key: " + this.y + ", fetcher: " + this.C);
        }
        Resource<R> resourceA = null;
        try {
            resourceA = a(this.C, (DataFetcher<?>) this.A, this.B);
        } catch (GlideException e2) {
            e2.a(this.z, this.B);
            this.c.add(e2);
        }
        if (resourceA != null) {
            b(resourceA, this.B);
        } else {
            i();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b(Resource<R> resource, DataSource dataSource) {
        if (resource instanceof Initializable) {
            ((Initializable) resource).initialize();
        }
        n nVar = 0;
        if (this.g.a()) {
            resource = n.a(resource);
            nVar = resource;
        }
        a((Resource) resource, dataSource);
        this.s = EnumC0081g.ENCODE;
        try {
            if (this.g.a()) {
                this.g.a(this.e, this.p);
            }
            c();
        } finally {
            if (nVar != 0) {
                nVar.a();
            }
        }
    }

    private <Data> Resource<R> a(DataFetcher<?> dataFetcher, Data data, DataSource dataSource) throws GlideException {
        if (data != null) {
            try {
                long logTime = LogTime.getLogTime();
                Resource<R> resourceA = a((g<R>) data, dataSource);
                if (Log.isLoggable(a, 2)) {
                    a("Decoded result " + resourceA, logTime);
                }
                return resourceA;
            } finally {
                dataFetcher.cleanup();
            }
        }
        return null;
    }

    private <Data> Resource<R> a(Data data, DataSource dataSource) throws GlideException {
        return a((g<R>) data, dataSource, (LoadPath<g<R>, ResourceType, R>) this.b.b(data.getClass()));
    }

    private Options a(DataSource dataSource) {
        Options options = this.p;
        if (Build.VERSION.SDK_INT < 26) {
            return options;
        }
        boolean z = dataSource == DataSource.RESOURCE_DISK_CACHE || this.b.m();
        Boolean bool = (Boolean) options.get(Downsampler.ALLOW_HARDWARE_CONFIG);
        if (bool != null && (!bool.booleanValue() || z)) {
            return options;
        }
        Options options2 = new Options();
        options2.putAll(this.p);
        options2.set(Downsampler.ALLOW_HARDWARE_CONFIG, Boolean.valueOf(z));
        return options2;
    }

    private <Data, ResourceType> Resource<R> a(Data data, DataSource dataSource, LoadPath<Data, ResourceType, R> loadPath) throws GlideException {
        Options optionsA = a(dataSource);
        DataRewinder<Data> rewinder = this.i.getRegistry().getRewinder(data);
        try {
            return loadPath.load(rewinder, optionsA, this.m, this.n, new b(dataSource));
        } finally {
            rewinder.cleanup();
        }
    }

    private void a(String str, long j) {
        a(str, j, (String) null);
    }

    private void a(String str, long j, String str2) {
        String str3;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" in ");
        sb.append(LogTime.getElapsedMillis(j));
        sb.append(", load key: ");
        sb.append(this.l);
        if (str2 != null) {
            str3 = ", " + str2;
        } else {
            str3 = "";
        }
        sb.append(str3);
        sb.append(", thread: ");
        sb.append(Thread.currentThread().getName());
        Log.v(a, sb.toString());
    }

    @Override // com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.Poolable
    public StateVerifier getVerifier() {
        return this.d;
    }

    <Z> Resource<Z> a(DataSource dataSource, Resource<Z> resource) {
        Resource<Z> resourceTransform;
        Transformation<Z> transformation;
        EncodeStrategy encodeStrategy;
        Key dVar;
        Class<?> cls = resource.get().getClass();
        ResourceEncoder<Z> resourceEncoderB = null;
        if (dataSource != DataSource.RESOURCE_DISK_CACHE) {
            Transformation<Z> transformationC = this.b.c(cls);
            transformation = transformationC;
            resourceTransform = transformationC.transform(this.i, resource, this.m, this.n);
        } else {
            resourceTransform = resource;
            transformation = null;
        }
        if (!resource.equals(resourceTransform)) {
            resource.recycle();
        }
        if (this.b.a((Resource<?>) resourceTransform)) {
            resourceEncoderB = this.b.b(resourceTransform);
            encodeStrategy = resourceEncoderB.getEncodeStrategy(this.p);
        } else {
            encodeStrategy = EncodeStrategy.NONE;
        }
        ResourceEncoder resourceEncoder = resourceEncoderB;
        if (!this.o.isResourceCacheable(!this.b.a(this.y), dataSource, encodeStrategy)) {
            return resourceTransform;
        }
        if (resourceEncoder == null) {
            throw new Registry.NoResultEncoderAvailableException(resourceTransform.get().getClass());
        }
        switch (encodeStrategy) {
            case SOURCE:
                dVar = new com.ss.union.game.sdk.core.glide.load.engine.d(this.y, this.j);
                break;
            case TRANSFORMED:
                dVar = new p(this.b.i(), this.y, this.j, this.m, this.n, transformation, cls, this.p);
                break;
            default:
                throw new IllegalArgumentException("Unknown strategy: " + encodeStrategy);
        }
        n nVarA = n.a(resourceTransform);
        this.g.a(dVar, resourceEncoder, nVarA);
        return nVarA;
    }

    private final class b<Z> implements DecodePath.a<Z> {
        private final DataSource b;

        b(DataSource dataSource) {
            this.b = dataSource;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.engine.DecodePath.a
        public Resource<Z> a(Resource<Z> resource) {
            return g.this.a(this.b, resource);
        }
    }

    private static class e {
        private boolean a;
        private boolean b;
        private boolean c;

        e() {
        }

        synchronized boolean a(boolean z) {
            this.a = true;
            return b(z);
        }

        synchronized boolean a() {
            this.b = true;
            return b(false);
        }

        synchronized boolean b() {
            this.c = true;
            return b(false);
        }

        synchronized void c() {
            this.b = false;
            this.a = false;
            this.c = false;
        }

        private boolean b(boolean z) {
            return (this.c || z || this.b) && this.a;
        }
    }

    private static class c<Z> {
        private Key a;
        private ResourceEncoder<Z> b;
        private n<Z> c;

        c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        <X> void a(Key key, ResourceEncoder<X> resourceEncoder, n<X> nVar) {
            this.a = key;
            this.b = resourceEncoder;
            this.c = nVar;
        }

        void a(d dVar, Options options) {
            GlideTrace.beginSection("DecodeJob.encode");
            try {
                dVar.a().put(this.a, new com.ss.union.game.sdk.core.glide.load.engine.e(this.b, this.c, options));
            } finally {
                this.c.a();
                GlideTrace.endSection();
            }
        }

        boolean a() {
            return this.c != null;
        }

        void b() {
            this.a = null;
            this.b = null;
            this.c = null;
        }
    }
}
