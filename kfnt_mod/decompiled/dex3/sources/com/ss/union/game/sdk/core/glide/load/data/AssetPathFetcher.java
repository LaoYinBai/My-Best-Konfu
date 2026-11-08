package com.ss.union.game.sdk.core.glide.load.data;

import android.content.res.AssetManager;
import android.util.Log;
import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.data.DataFetcher;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class AssetPathFetcher<T> implements DataFetcher<T> {
    private static final String a = "AssetPathFetcher";
    private final String b;
    private final AssetManager c;
    private T d;

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public void cancel() {
    }

    protected abstract void close(T t) throws IOException;

    protected abstract T loadResource(AssetManager assetManager, String str) throws IOException;

    public AssetPathFetcher(AssetManager assetManager, String str) {
        this.c = assetManager;
        this.b = str;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public void loadData(Priority priority, DataFetcher.DataCallback<? super T> dataCallback) {
        try {
            this.d = loadResource(this.c, this.b);
            dataCallback.onDataReady(this.d);
        } catch (IOException e) {
            if (Log.isLoggable(a, 3)) {
                Log.d(a, "Failed to load data from asset manager", e);
            }
            dataCallback.onLoadFailed(e);
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public void cleanup() {
        if (this.d == null) {
            return;
        }
        try {
            close(this.d);
        } catch (IOException unused) {
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }
}
