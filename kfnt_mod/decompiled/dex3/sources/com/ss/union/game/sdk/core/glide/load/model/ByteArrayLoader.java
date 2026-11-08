package com.ss.union.game.sdk.core.glide.load.model;

import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.data.DataFetcher;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import com.ss.union.game.sdk.core.glide.signature.ObjectKey;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ByteArrayLoader<Data> implements ModelLoader<byte[], Data> {
    private final Converter<Data> a;

    public interface Converter<Data> {
        Data convert(byte[] bArr);

        Class<Data> getDataClass();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public boolean handles(byte[] bArr) {
        return true;
    }

    public ByteArrayLoader(Converter<Data> converter) {
        this.a = converter;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public ModelLoader.LoadData<Data> buildLoadData(byte[] bArr, int i, int i2, Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(bArr), new a(bArr, this.a));
    }

    private static class a<Data> implements DataFetcher<Data> {
        private final byte[] a;
        private final Converter<Data> b;

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void cancel() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void cleanup() {
        }

        a(byte[] bArr, Converter<Data> converter) {
            this.a = bArr;
            this.b = converter;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void loadData(Priority priority, DataFetcher.DataCallback<? super Data> dataCallback) {
            dataCallback.onDataReady(this.b.convert(this.a));
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

    public static class ByteBufferFactory implements ModelLoaderFactory<byte[], ByteBuffer> {
        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public ModelLoader<byte[], ByteBuffer> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ByteArrayLoader(new Converter<ByteBuffer>() { // from class: com.ss.union.game.sdk.core.glide.load.model.ByteArrayLoader.ByteBufferFactory.1
                @Override // com.ss.union.game.sdk.core.glide.load.model.ByteArrayLoader.Converter
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public ByteBuffer convert(byte[] bArr) {
                    return ByteBuffer.wrap(bArr);
                }

                @Override // com.ss.union.game.sdk.core.glide.load.model.ByteArrayLoader.Converter
                public Class<ByteBuffer> getDataClass() {
                    return ByteBuffer.class;
                }
            });
        }
    }

    public static class StreamFactory implements ModelLoaderFactory<byte[], InputStream> {
        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public ModelLoader<byte[], InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ByteArrayLoader(new Converter<InputStream>() { // from class: com.ss.union.game.sdk.core.glide.load.model.ByteArrayLoader.StreamFactory.1
                @Override // com.ss.union.game.sdk.core.glide.load.model.ByteArrayLoader.Converter
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public InputStream convert(byte[] bArr) {
                    return new ByteArrayInputStream(bArr);
                }

                @Override // com.ss.union.game.sdk.core.glide.load.model.ByteArrayLoader.Converter
                public Class<InputStream> getDataClass() {
                    return InputStream.class;
                }
            });
        }
    }
}
