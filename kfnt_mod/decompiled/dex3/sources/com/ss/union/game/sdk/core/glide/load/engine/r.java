package com.ss.union.game.sdk.core.glide.load.engine;

import android.util.Log;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.Encoder;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.data.DataFetcher;
import com.ss.union.game.sdk.core.glide.load.engine.DataFetcherGenerator;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import com.ss.union.game.sdk.core.glide.util.LogTime;
import java.util.Collections;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class r implements DataFetcher.DataCallback<Object>, DataFetcherGenerator, DataFetcherGenerator.FetcherReadyCallback {
    private static final String a = "SourceGenerator";
    private final f<?> b;
    private final DataFetcherGenerator.FetcherReadyCallback c;
    private int d;
    private c e;
    private Object f;
    private volatile ModelLoader.LoadData<?> g;
    private d h;

    r(f<?> fVar, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.b = fVar;
        this.c = fetcherReadyCallback;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.DataFetcherGenerator
    public boolean startNext() {
        if (this.f != null) {
            Object obj = this.f;
            this.f = null;
            a(obj);
        }
        if (this.e != null && this.e.startNext()) {
            return true;
        }
        this.e = null;
        this.g = null;
        boolean z = false;
        while (!z && a()) {
            List<ModelLoader.LoadData<?>> listN = this.b.n();
            int i = this.d;
            this.d = i + 1;
            this.g = listN.get(i);
            if (this.g != null && (this.b.c().isDataCacheable(this.g.fetcher.getDataSource()) || this.b.a(this.g.fetcher.getDataClass()))) {
                this.g.fetcher.loadData(this.b.d(), this);
                z = true;
            }
        }
        return z;
    }

    private boolean a() {
        return this.d < this.b.n().size();
    }

    private void a(Object obj) {
        long logTime = LogTime.getLogTime();
        try {
            Encoder<X> encoderA = this.b.a((f<?>) obj);
            e eVar = new e(encoderA, obj, this.b.e());
            this.h = new d(this.g.sourceKey, this.b.f());
            this.b.b().put(this.h, eVar);
            if (Log.isLoggable(a, 2)) {
                Log.v(a, "Finished encoding source to cache, key: " + this.h + ", data: " + obj + ", encoder: " + encoderA + ", duration: " + LogTime.getElapsedMillis(logTime));
            }
            this.g.fetcher.cleanup();
            this.e = new c(Collections.singletonList(this.g.sourceKey), this.b, this);
        } catch (Throwable th) {
            this.g.fetcher.cleanup();
            throw th;
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.DataFetcherGenerator
    public void cancel() {
        ModelLoader.LoadData<?> loadData = this.g;
        if (loadData != null) {
            loadData.fetcher.cancel();
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher.DataCallback
    public void onDataReady(Object obj) {
        DiskCacheStrategy diskCacheStrategyC = this.b.c();
        if (obj != null && diskCacheStrategyC.isDataCacheable(this.g.fetcher.getDataSource())) {
            this.f = obj;
            this.c.reschedule();
        } else {
            this.c.onDataFetcherReady(this.g.sourceKey, obj, this.g.fetcher, this.g.fetcher.getDataSource(), this.h);
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher.DataCallback
    public void onLoadFailed(Exception exc) {
        this.c.onDataFetcherFailed(this.h, exc, this.g.fetcher, this.g.fetcher.getDataSource());
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void reschedule() {
        throw new UnsupportedOperationException();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void onDataFetcherReady(Key key, Object obj, DataFetcher<?> dataFetcher, DataSource dataSource, Key key2) {
        this.c.onDataFetcherReady(key, obj, dataFetcher, this.g.fetcher.getDataSource(), key);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void onDataFetcherFailed(Key key, Exception exc, DataFetcher<?> dataFetcher, DataSource dataSource) {
        this.c.onDataFetcherFailed(key, exc, dataFetcher, this.g.fetcher.getDataSource());
    }
}
