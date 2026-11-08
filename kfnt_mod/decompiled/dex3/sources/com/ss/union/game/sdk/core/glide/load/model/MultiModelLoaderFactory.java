package com.ss.union.game.sdk.core.glide.load.model;

import androidx.core.util.Pools;
import com.ss.union.game.sdk.core.glide.Registry;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MultiModelLoaderFactory {
    private static final c a = new c();
    private static final ModelLoader<Object, Object> b = new a();
    private final List<b<?, ?>> c;
    private final c d;
    private final Set<b<?, ?>> e;
    private final Pools.Pool<List<Throwable>> f;

    public MultiModelLoaderFactory(Pools.Pool<List<Throwable>> pool) {
        this(pool, a);
    }

    MultiModelLoaderFactory(Pools.Pool<List<Throwable>> pool, c cVar) {
        this.c = new ArrayList();
        this.e = new HashSet();
        this.f = pool;
        this.d = cVar;
    }

    synchronized <Model, Data> void a(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        a(cls, cls2, modelLoaderFactory, true);
    }

    synchronized <Model, Data> void b(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        a(cls, cls2, modelLoaderFactory, false);
    }

    private <Model, Data> void a(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory, boolean z) {
        this.c.add(z ? this.c.size() : 0, new b<>(cls, cls2, modelLoaderFactory));
    }

    synchronized <Model, Data> List<ModelLoaderFactory<? extends Model, ? extends Data>> c(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        List<ModelLoaderFactory<? extends Model, ? extends Data>> listA;
        listA = a(cls, cls2);
        a(cls, cls2, modelLoaderFactory);
        return listA;
    }

    synchronized <Model, Data> List<ModelLoaderFactory<? extends Model, ? extends Data>> a(Class<Model> cls, Class<Data> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<b<?, ?>> it = this.c.iterator();
        while (it.hasNext()) {
            b<?, ?> next = it.next();
            if (next.a(cls, cls2)) {
                it.remove();
                arrayList.add(a(next));
            }
        }
        return arrayList;
    }

    synchronized <Model> List<ModelLoader<Model, ?>> a(Class<Model> cls) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            for (b<?, ?> bVar : this.c) {
                if (!this.e.contains(bVar) && bVar.a(cls)) {
                    this.e.add(bVar);
                    arrayList.add(b(bVar));
                    this.e.remove(bVar);
                }
            }
        } finally {
        }
        return arrayList;
    }

    synchronized List<Class<?>> b(Class<?> cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (b<?, ?> bVar : this.c) {
            if (!arrayList.contains(bVar.a) && bVar.a(cls)) {
                arrayList.add(bVar.a);
            }
        }
        return arrayList;
    }

    public synchronized <Model, Data> ModelLoader<Model, Data> build(Class<Model> cls, Class<Data> cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (b<?, ?> bVar : this.c) {
                if (this.e.contains(bVar)) {
                    z = true;
                } else if (bVar.a(cls, cls2)) {
                    this.e.add(bVar);
                    arrayList.add(b(bVar));
                    this.e.remove(bVar);
                }
            }
            if (arrayList.size() > 1) {
                return this.d.a(arrayList, this.f);
            }
            if (arrayList.size() == 1) {
                return (ModelLoader) arrayList.get(0);
            }
            if (z) {
                return a();
            }
            throw new Registry.NoModelLoaderAvailableException(cls, cls2);
        } catch (Throwable th) {
            this.e.clear();
            throw th;
        }
    }

    private <Model, Data> ModelLoaderFactory<Model, Data> a(b<?, ?> bVar) {
        return (ModelLoaderFactory<Model, Data>) bVar.b;
    }

    private <Model, Data> ModelLoader<Model, Data> b(b<?, ?> bVar) {
        return (ModelLoader) Preconditions.checkNotNull(bVar.b.build(this));
    }

    private static <Model, Data> ModelLoader<Model, Data> a() {
        return (ModelLoader<Model, Data>) b;
    }

    private static class b<Model, Data> {
        final Class<Data> a;
        final ModelLoaderFactory<? extends Model, ? extends Data> b;
        private final Class<Model> c;

        public b(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
            this.c = cls;
            this.a = cls2;
            this.b = modelLoaderFactory;
        }

        public boolean a(Class<?> cls, Class<?> cls2) {
            return a(cls) && this.a.isAssignableFrom(cls2);
        }

        public boolean a(Class<?> cls) {
            return this.c.isAssignableFrom(cls);
        }
    }

    static class c {
        c() {
        }

        public <Model, Data> com.ss.union.game.sdk.core.glide.load.model.a<Model, Data> a(List<ModelLoader<Model, Data>> list, Pools.Pool<List<Throwable>> pool) {
            return new com.ss.union.game.sdk.core.glide.load.model.a<>(list, pool);
        }
    }

    private static class a implements ModelLoader<Object, Object> {
        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
        public ModelLoader.LoadData<Object> buildLoadData(Object obj, int i, int i2, Options options) {
            return null;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
        public boolean handles(Object obj) {
            return false;
        }

        a() {
        }
    }
}
