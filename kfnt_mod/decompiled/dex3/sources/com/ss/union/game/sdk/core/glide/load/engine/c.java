package com.ss.union.game.sdk.core.glide.load.engine;

import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.data.DataFetcher;
import com.ss.union.game.sdk.core.glide.load.engine.DataFetcherGenerator;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import java.io.File;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class c implements DataFetcher.DataCallback<Object>, DataFetcherGenerator {
    private final List<Key> a;
    private final f<?> b;
    private final DataFetcherGenerator.FetcherReadyCallback c;
    private int d;
    private Key e;
    private List<ModelLoader<File, ?>> f;
    private int g;
    private volatile ModelLoader.LoadData<?> h;
    private File i;

    c(f<?> fVar, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this(fVar.o(), fVar, fetcherReadyCallback);
    }

    c(List<Key> list, f<?> fVar, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.d = -1;
        this.a = list;
        this.b = fVar;
        this.c = fetcherReadyCallback;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.DataFetcherGenerator
    public boolean startNext() {
        while (true) {
            boolean z = false;
            if (this.f == null || !a()) {
                this.d++;
                if (this.d >= this.a.size()) {
                    return false;
                }
                Key key = this.a.get(this.d);
                this.i = this.b.b().get(new d(key, this.b.f()));
                if (this.i != null) {
                    this.e = key;
                    this.f = this.b.a(this.i);
                    this.g = 0;
                }
            } else {
                this.h = null;
                while (!z && a()) {
                    List<ModelLoader<File, ?>> list = this.f;
                    int i = this.g;
                    this.g = i + 1;
                    this.h = list.get(i).buildLoadData(this.i, this.b.g(), this.b.h(), this.b.e());
                    if (this.h != null && this.b.a(this.h.fetcher.getDataClass())) {
                        this.h.fetcher.loadData(this.b.d(), this);
                        z = true;
                    }
                }
                return z;
            }
        }
    }

    private boolean a() {
        return this.g < this.f.size();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.DataFetcherGenerator
    public void cancel() {
        ModelLoader.LoadData<?> loadData = this.h;
        if (loadData != null) {
            loadData.fetcher.cancel();
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher.DataCallback
    public void onDataReady(Object obj) {
        this.c.onDataFetcherReady(this.e, obj, this.h.fetcher, DataSource.DATA_DISK_CACHE, this.e);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher.DataCallback
    public void onLoadFailed(Exception exc) {
        this.c.onDataFetcherFailed(this.e, exc, this.h.fetcher, DataSource.DATA_DISK_CACHE);
    }
}
