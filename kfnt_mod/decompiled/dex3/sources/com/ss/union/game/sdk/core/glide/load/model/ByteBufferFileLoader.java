package com.ss.union.game.sdk.core.glide.load.model;

import android.util.Log;
import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.data.DataFetcher;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import com.ss.union.game.sdk.core.glide.signature.ObjectKey;
import com.ss.union.game.sdk.core.glide.util.ByteBufferUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ByteBufferFileLoader implements ModelLoader<File, ByteBuffer> {
    private static final String a = "ByteBufferFileLoader";

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public boolean handles(File file) {
        return true;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public ModelLoader.LoadData<ByteBuffer> buildLoadData(File file, int i, int i2, Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(file), new a(file));
    }

    public static class Factory implements ModelLoaderFactory<File, ByteBuffer> {
        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public ModelLoader<File, ByteBuffer> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ByteBufferFileLoader();
        }
    }

    private static final class a implements DataFetcher<ByteBuffer> {
        private final File a;

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void cancel() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void cleanup() {
        }

        a(File file) {
            this.a = file;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void loadData(Priority priority, DataFetcher.DataCallback<? super ByteBuffer> dataCallback) {
            try {
                dataCallback.onDataReady(ByteBufferUtil.fromFile(this.a));
            } catch (IOException e) {
                if (Log.isLoggable(ByteBufferFileLoader.a, 3)) {
                    Log.d(ByteBufferFileLoader.a, "Failed to obtain ByteBuffer for file", e);
                }
                dataCallback.onLoadFailed(e);
            }
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public Class<ByteBuffer> getDataClass() {
            return ByteBuffer.class;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }
}
