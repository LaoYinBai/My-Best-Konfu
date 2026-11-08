package com.ss.union.game.sdk.core.glide.load.model;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.data.DataFetcher;
import com.ss.union.game.sdk.core.glide.load.data.mediastore.MediaStoreUtil;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import com.ss.union.game.sdk.core.glide.signature.ObjectKey;
import java.io.File;
import java.io.FileNotFoundException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class MediaStoreFileLoader implements ModelLoader<Uri, File> {
    private final Context a;

    public MediaStoreFileLoader(Context context) {
        this.a = context;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public ModelLoader.LoadData<File> buildLoadData(Uri uri, int i, int i2, Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(uri), new a(this.a, uri));
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public boolean handles(Uri uri) {
        return MediaStoreUtil.isMediaStoreUri(uri);
    }

    private static class a implements DataFetcher<File> {
        private static final String[] a = {"_data"};
        private final Context b;
        private final Uri c;

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void cancel() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void cleanup() {
        }

        a(Context context, Uri uri) {
            this.b = context;
            this.c = uri;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void loadData(Priority priority, DataFetcher.DataCallback<? super File> dataCallback) {
            Cursor cursorQuery = this.b.getContentResolver().query(this.c, a, null, null, null);
            if (cursorQuery != null) {
                try {
                    string = cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data")) : null;
                } finally {
                    cursorQuery.close();
                }
            }
            if (TextUtils.isEmpty(string)) {
                dataCallback.onLoadFailed(new FileNotFoundException("Failed to find file path for: " + this.c));
                return;
            }
            dataCallback.onDataReady(new File(string));
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public Class<File> getDataClass() {
            return File.class;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }

    public static final class Factory implements ModelLoaderFactory<Uri, File> {
        private final Context a;

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        public Factory(Context context) {
            this.a = context;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public ModelLoader<Uri, File> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new MediaStoreFileLoader(this.a);
        }
    }
}
