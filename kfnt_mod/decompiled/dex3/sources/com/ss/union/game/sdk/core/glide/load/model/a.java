package com.ss.union.game.sdk.core.glide.load.model;

import androidx.core.util.Pools;
import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.data.DataFetcher;
import com.ss.union.game.sdk.core.glide.load.engine.GlideException;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a<Model, Data> implements ModelLoader<Model, Data> {
    private final List<ModelLoader<Model, Data>> a;
    private final Pools.Pool<List<Throwable>> b;

    a(List<ModelLoader<Model, Data>> list, Pools.Pool<List<Throwable>> pool) {
        this.a = list;
        this.b = pool;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public ModelLoader.LoadData<Data> buildLoadData(Model model, int i, int i2, Options options) {
        ModelLoader.LoadData<Data> loadDataBuildLoadData;
        int size = this.a.size();
        ArrayList arrayList = new ArrayList(size);
        Key key = null;
        for (int i3 = 0; i3 < size; i3++) {
            ModelLoader<Model, Data> modelLoader = this.a.get(i3);
            if (modelLoader.handles(model) && (loadDataBuildLoadData = modelLoader.buildLoadData(model, i, i2, options)) != null) {
                key = loadDataBuildLoadData.sourceKey;
                arrayList.add(loadDataBuildLoadData.fetcher);
            }
        }
        if (arrayList.isEmpty() || key == null) {
            return null;
        }
        return new ModelLoader.LoadData<>(key, new C0084a(arrayList, this.b));
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public boolean handles(Model model) {
        Iterator<ModelLoader<Model, Data>> it = this.a.iterator();
        while (it.hasNext()) {
            if (it.next().handles(model)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "MultiModelLoader{modelLoaders=" + Arrays.toString(this.a.toArray()) + '}';
    }

    /* renamed from: com.ss.union.game.sdk.core.glide.load.model.a$a, reason: collision with other inner class name */
    static class C0084a<Data> implements DataFetcher<Data>, DataFetcher.DataCallback<Data> {
        private final List<DataFetcher<Data>> a;
        private final Pools.Pool<List<Throwable>> b;
        private int c;
        private Priority d;
        private DataFetcher.DataCallback<? super Data> e;
        private List<Throwable> f;
        private boolean g;

        C0084a(List<DataFetcher<Data>> list, Pools.Pool<List<Throwable>> pool) {
            this.b = pool;
            Preconditions.checkNotEmpty(list);
            this.a = list;
            this.c = 0;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void loadData(Priority priority, DataFetcher.DataCallback<? super Data> dataCallback) {
            this.d = priority;
            this.e = dataCallback;
            this.f = (List) this.b.acquire();
            this.a.get(this.c).loadData(priority, this);
            if (this.g) {
                cancel();
            }
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void cleanup() {
            if (this.f != null) {
                this.b.release(this.f);
            }
            this.f = null;
            Iterator<DataFetcher<Data>> it = this.a.iterator();
            while (it.hasNext()) {
                it.next().cleanup();
            }
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void cancel() {
            this.g = true;
            Iterator<DataFetcher<Data>> it = this.a.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public Class<Data> getDataClass() {
            return this.a.get(0).getDataClass();
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public DataSource getDataSource() {
            return this.a.get(0).getDataSource();
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher.DataCallback
        public void onDataReady(Data data) {
            if (data != null) {
                this.e.onDataReady(data);
            } else {
                a();
            }
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher.DataCallback
        public void onLoadFailed(Exception exc) {
            ((List) Preconditions.checkNotNull(this.f)).add(exc);
            a();
        }

        private void a() {
            if (this.g) {
                return;
            }
            if (this.c < this.a.size() - 1) {
                this.c++;
                loadData(this.d, this.e);
            } else {
                Preconditions.checkNotNull(this.f);
                this.e.onLoadFailed(new GlideException("Fetch failed", new ArrayList(this.f)));
            }
        }
    }
}
