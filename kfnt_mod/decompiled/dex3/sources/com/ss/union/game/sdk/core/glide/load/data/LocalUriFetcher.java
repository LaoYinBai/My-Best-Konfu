package com.ss.union.game.sdk.core.glide.load.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.data.DataFetcher;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class LocalUriFetcher<T> implements DataFetcher<T> {
    private static final String a = "LocalUriFetcher";
    private final Uri b;
    private final ContentResolver c;
    private T d;

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public void cancel() {
    }

    protected abstract void close(T t) throws IOException;

    protected abstract T loadResource(Uri uri, ContentResolver contentResolver) throws FileNotFoundException;

    public LocalUriFetcher(ContentResolver contentResolver, Uri uri) {
        this.c = contentResolver;
        this.b = uri;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public final void loadData(Priority priority, DataFetcher.DataCallback<? super T> dataCallback) {
        try {
            this.d = loadResource(this.b, this.c);
            dataCallback.onDataReady(this.d);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable(a, 3)) {
                Log.d(a, "Failed to open Uri", e);
            }
            dataCallback.onLoadFailed(e);
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public void cleanup() {
        if (this.d != null) {
            try {
                close(this.d);
            } catch (IOException unused) {
            }
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }
}
