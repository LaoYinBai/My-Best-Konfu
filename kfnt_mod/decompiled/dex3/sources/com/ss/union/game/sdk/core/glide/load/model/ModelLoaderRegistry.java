package com.ss.union.game.sdk.core.glide.load.model;

import androidx.core.util.Pools;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ModelLoaderRegistry {
    private final MultiModelLoaderFactory a;
    private final a b;

    public ModelLoaderRegistry(Pools.Pool<List<Throwable>> pool) {
        this(new MultiModelLoaderFactory(pool));
    }

    private ModelLoaderRegistry(MultiModelLoaderFactory multiModelLoaderFactory) {
        this.b = new a();
        this.a = multiModelLoaderFactory;
    }

    public synchronized <Model, Data> void append(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        this.a.a(cls, cls2, modelLoaderFactory);
        this.b.a();
    }

    public synchronized <Model, Data> void prepend(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        this.a.b(cls, cls2, modelLoaderFactory);
        this.b.a();
    }

    public synchronized <Model, Data> void remove(Class<Model> cls, Class<Data> cls2) {
        a((List) this.a.a(cls, cls2));
        this.b.a();
    }

    public synchronized <Model, Data> void replace(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        a((List) this.a.c(cls, cls2, modelLoaderFactory));
        this.b.a();
    }

    private <Model, Data> void a(List<ModelLoaderFactory<? extends Model, ? extends Data>> list) {
        Iterator<ModelLoaderFactory<? extends Model, ? extends Data>> it = list.iterator();
        while (it.hasNext()) {
            it.next().teardown();
        }
    }

    public <A> List<ModelLoader<A, ?>> getModelLoaders(A a2) {
        List<ModelLoader<A, ?>> listA = a((Class) a(a2));
        int size = listA.size();
        List<ModelLoader<A, ?>> listEmptyList = Collections.emptyList();
        boolean z = true;
        for (int i = 0; i < size; i++) {
            ModelLoader<A, ?> modelLoader = listA.get(i);
            if (modelLoader.handles(a2)) {
                if (z) {
                    listEmptyList = new ArrayList<>(size - i);
                    z = false;
                }
                listEmptyList.add(modelLoader);
            }
        }
        return listEmptyList;
    }

    public synchronized <Model, Data> ModelLoader<Model, Data> build(Class<Model> cls, Class<Data> cls2) {
        return this.a.build(cls, cls2);
    }

    public synchronized List<Class<?>> getDataClasses(Class<?> cls) {
        return this.a.b(cls);
    }

    private synchronized <A> List<ModelLoader<A, ?>> a(Class<A> cls) {
        List<ModelLoader<A, ?>> listA;
        listA = this.b.a(cls);
        if (listA == null) {
            listA = Collections.unmodifiableList(this.a.a(cls));
            this.b.a(cls, listA);
        }
        return listA;
    }

    private static <A> Class<A> a(A a2) {
        return (Class<A>) a2.getClass();
    }

    private static class a {
        private final Map<Class<?>, C0083a<?>> a = new HashMap();

        a() {
        }

        public void a() {
            this.a.clear();
        }

        public <Model> void a(Class<Model> cls, List<ModelLoader<Model, ?>> list) {
            if (this.a.put(cls, new C0083a<>(list)) == null) {
                return;
            }
            throw new IllegalStateException("Already cached loaders for model: " + cls);
        }

        public <Model> List<ModelLoader<Model, ?>> a(Class<Model> cls) {
            C0083a<?> c0083a = this.a.get(cls);
            if (c0083a == null) {
                return null;
            }
            return (List<ModelLoader<Model, ?>>) c0083a.a;
        }

        /* renamed from: com.ss.union.game.sdk.core.glide.load.model.ModelLoaderRegistry$a$a, reason: collision with other inner class name */
        private static class C0083a<Model> {
            final List<ModelLoader<Model, ?>> a;

            public C0083a(List<ModelLoader<Model, ?>> list) {
                this.a = list;
            }
        }
    }
}
