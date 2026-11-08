package com.ss.union.game.sdk.core.glide.load.model;

import android.util.Base64;
import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.data.DataFetcher;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import com.ss.union.game.sdk.core.glide.signature.ObjectKey;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class DataUrlLoader<Model, Data> implements ModelLoader<Model, Data> {
    private static final String a = "data:image";
    private static final String b = ";base64";
    private final DataDecoder<Data> c;

    public interface DataDecoder<Data> {
        void close(Data data) throws IOException;

        Data decode(String str) throws IllegalArgumentException;

        Class<Data> getDataClass();
    }

    public DataUrlLoader(DataDecoder<Data> dataDecoder) {
        this.c = dataDecoder;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public ModelLoader.LoadData<Data> buildLoadData(Model model, int i, int i2, Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(model), new a(model.toString(), this.c));
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public boolean handles(Model model) {
        return model.toString().startsWith(a);
    }

    private static final class a<Data> implements DataFetcher<Data> {
        private final String a;
        private final DataDecoder<Data> b;
        private Data c;

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void cancel() {
        }

        a(String str, DataDecoder<Data> dataDecoder) {
            this.a = str;
            this.b = dataDecoder;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void loadData(Priority priority, DataFetcher.DataCallback<? super Data> dataCallback) {
            try {
                this.c = this.b.decode(this.a);
                dataCallback.onDataReady(this.c);
            } catch (IllegalArgumentException e) {
                dataCallback.onLoadFailed(e);
            }
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void cleanup() {
            try {
                this.b.close(this.c);
            } catch (IOException unused) {
            }
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public Class<Data> getDataClass() {
            return this.b.getDataClass();
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }

    public static final class StreamFactory<Model> implements ModelLoaderFactory<Model, InputStream> {
        private final DataDecoder<InputStream> a = new DataDecoder<InputStream>() { // from class: com.ss.union.game.sdk.core.glide.load.model.DataUrlLoader.StreamFactory.1
            @Override // com.ss.union.game.sdk.core.glide.load.model.DataUrlLoader.DataDecoder
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public InputStream decode(String str) {
                if (!str.startsWith(DataUrlLoader.a)) {
                    throw new IllegalArgumentException("Not a valid image data URL.");
                }
                int iIndexOf = str.indexOf(44);
                if (iIndexOf == -1) {
                    throw new IllegalArgumentException("Missing comma in data URL.");
                }
                if (!str.substring(0, iIndexOf).endsWith(DataUrlLoader.b)) {
                    throw new IllegalArgumentException("Not a base64 image data URL.");
                }
                return new ByteArrayInputStream(Base64.decode(str.substring(iIndexOf + 1), 0));
            }

            @Override // com.ss.union.game.sdk.core.glide.load.model.DataUrlLoader.DataDecoder
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void close(InputStream inputStream) throws IOException {
                inputStream.close();
            }

            @Override // com.ss.union.game.sdk.core.glide.load.model.DataUrlLoader.DataDecoder
            public Class<InputStream> getDataClass() {
                return InputStream.class;
            }
        };

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public ModelLoader<Model, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new DataUrlLoader(this.a);
        }
    }
}
