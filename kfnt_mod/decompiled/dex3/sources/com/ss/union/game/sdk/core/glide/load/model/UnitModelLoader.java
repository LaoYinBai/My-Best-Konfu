package com.ss.union.game.sdk.core.glide.load.model;

import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.data.DataFetcher;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import com.ss.union.game.sdk.core.glide.signature.ObjectKey;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UnitModelLoader<Model> implements ModelLoader<Model, Model> {
    private static final UnitModelLoader<?> a = new UnitModelLoader<>();

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public boolean handles(Model model) {
        return true;
    }

    public static <T> UnitModelLoader<T> getInstance() {
        return (UnitModelLoader<T>) a;
    }

    @Deprecated
    public UnitModelLoader() {
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public ModelLoader.LoadData<Model> buildLoadData(Model model, int i, int i2, Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(model), new a(model));
    }

    private static class a<Model> implements DataFetcher<Model> {
        private final Model a;

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void cancel() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void cleanup() {
        }

        a(Model model) {
            this.a = model;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void loadData(Priority priority, DataFetcher.DataCallback<? super Model> dataCallback) {
            dataCallback.onDataReady(this.a);
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public Class<Model> getDataClass() {
            return (Class<Model>) this.a.getClass();
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }

    public static class Factory<Model> implements ModelLoaderFactory<Model, Model> {
        private static final Factory<?> a = new Factory<>();

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        public static <T> Factory<T> getInstance() {
            return (Factory<T>) a;
        }

        @Deprecated
        public Factory() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public ModelLoader<Model, Model> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return UnitModelLoader.getInstance();
        }
    }
}
