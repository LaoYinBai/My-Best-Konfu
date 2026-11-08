package com.ss.union.game.sdk.core.glide.load.engine;

import com.ss.union.game.sdk.core.glide.GlideContext;
import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.Registry;
import com.ss.union.game.sdk.core.glide.load.Encoder;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceEncoder;
import com.ss.union.game.sdk.core.glide.load.Transformation;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache;
import com.ss.union.game.sdk.core.glide.load.engine.g;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import com.ss.union.game.sdk.core.glide.load.resource.UnitTransformation;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class f<Transcode> {
    private final List<ModelLoader.LoadData<?>> a = new ArrayList();
    private final List<Key> b = new ArrayList();
    private GlideContext c;
    private Object d;
    private int e;
    private int f;
    private Class<?> g;
    private g.d h;
    private Options i;
    private Map<Class<?>, Transformation<?>> j;
    private Class<Transcode> k;
    private boolean l;
    private boolean m;
    private Key n;
    private Priority o;
    private DiskCacheStrategy p;
    private boolean q;
    private boolean r;

    f() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    <R> void a(GlideContext glideContext, Object obj, Key key, int i, int i2, DiskCacheStrategy diskCacheStrategy, Class<?> cls, Class<R> cls2, Priority priority, Options options, Map<Class<?>, Transformation<?>> map, boolean z, boolean z2, g.d dVar) {
        this.c = glideContext;
        this.d = obj;
        this.n = key;
        this.e = i;
        this.f = i2;
        this.p = diskCacheStrategy;
        this.g = cls;
        this.h = dVar;
        this.k = cls2;
        this.o = priority;
        this.i = options;
        this.j = map;
        this.q = z;
        this.r = z2;
    }

    void a() {
        this.c = null;
        this.d = null;
        this.n = null;
        this.g = null;
        this.k = null;
        this.i = null;
        this.o = null;
        this.j = null;
        this.p = null;
        this.a.clear();
        this.l = false;
        this.b.clear();
        this.m = false;
    }

    DiskCache b() {
        return this.h.a();
    }

    DiskCacheStrategy c() {
        return this.p;
    }

    Priority d() {
        return this.o;
    }

    Options e() {
        return this.i;
    }

    Key f() {
        return this.n;
    }

    int g() {
        return this.e;
    }

    int h() {
        return this.f;
    }

    ArrayPool i() {
        return this.c.getArrayPool();
    }

    Class<?> j() {
        return this.k;
    }

    Class<?> k() {
        return this.d.getClass();
    }

    List<Class<?>> l() {
        return this.c.getRegistry().getRegisteredResourceClasses(this.d.getClass(), this.g, this.k);
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean a(Class<?> cls) {
        return b(cls) != null;
    }

    <Data> LoadPath<Data, ?, Transcode> b(Class<Data> cls) {
        return this.c.getRegistry().getLoadPath(cls, this.g, this.k);
    }

    boolean m() {
        return this.r;
    }

    <Z> Transformation<Z> c(Class<Z> cls) {
        Transformation<Z> transformation = (Transformation) this.j.get(cls);
        if (transformation == null) {
            Iterator<Map.Entry<Class<?>, Transformation<?>>> it = this.j.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<Class<?>, Transformation<?>> next = it.next();
                if (next.getKey().isAssignableFrom(cls)) {
                    transformation = (Transformation) next.getValue();
                    break;
                }
            }
        }
        if (transformation != null) {
            return transformation;
        }
        if (this.j.isEmpty() && this.q) {
            throw new IllegalArgumentException("Missing transformation for " + cls + ". If you wish to ignore unknown resource types, use the optional transformation methods.");
        }
        return UnitTransformation.get();
    }

    boolean a(Resource<?> resource) {
        return this.c.getRegistry().isResourceEncoderAvailable(resource);
    }

    <Z> ResourceEncoder<Z> b(Resource<Z> resource) {
        return this.c.getRegistry().getResultEncoder(resource);
    }

    List<ModelLoader<File, ?>> a(File file) throws Registry.NoModelLoaderAvailableException {
        return this.c.getRegistry().getModelLoaders(file);
    }

    boolean a(Key key) {
        List<ModelLoader.LoadData<?>> listN = n();
        int size = listN.size();
        for (int i = 0; i < size; i++) {
            if (listN.get(i).sourceKey.equals(key)) {
                return true;
            }
        }
        return false;
    }

    List<ModelLoader.LoadData<?>> n() {
        if (!this.l) {
            this.l = true;
            this.a.clear();
            List modelLoaders = this.c.getRegistry().getModelLoaders(this.d);
            int size = modelLoaders.size();
            for (int i = 0; i < size; i++) {
                ModelLoader.LoadData<?> loadDataBuildLoadData = ((ModelLoader) modelLoaders.get(i)).buildLoadData(this.d, this.e, this.f, this.i);
                if (loadDataBuildLoadData != null) {
                    this.a.add(loadDataBuildLoadData);
                }
            }
        }
        return this.a;
    }

    List<Key> o() {
        if (!this.m) {
            this.m = true;
            this.b.clear();
            List<ModelLoader.LoadData<?>> listN = n();
            int size = listN.size();
            for (int i = 0; i < size; i++) {
                ModelLoader.LoadData<?> loadData = listN.get(i);
                if (!this.b.contains(loadData.sourceKey)) {
                    this.b.add(loadData.sourceKey);
                }
                for (int i2 = 0; i2 < loadData.alternateKeys.size(); i2++) {
                    if (!this.b.contains(loadData.alternateKeys.get(i2))) {
                        this.b.add(loadData.alternateKeys.get(i2));
                    }
                }
            }
        }
        return this.b;
    }

    <X> Encoder<X> a(X x) throws Registry.NoSourceEncoderAvailableException {
        return this.c.getRegistry().getSourceEncoder(x);
    }
}
