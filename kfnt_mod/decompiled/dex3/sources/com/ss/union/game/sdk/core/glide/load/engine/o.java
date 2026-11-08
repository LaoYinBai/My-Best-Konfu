package com.ss.union.game.sdk.core.glide.load.engine;

import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.data.DataFetcher;
import com.ss.union.game.sdk.core.glide.load.engine.DataFetcherGenerator;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import java.io.File;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class o implements DataFetcher.DataCallback<Object>, DataFetcherGenerator {
    private final DataFetcherGenerator.FetcherReadyCallback a;
    private final f<?> b;
    private int c;
    private int d = -1;
    private Key e;
    private List<ModelLoader<File, ?>> f;
    private int g;
    private volatile ModelLoader.LoadData<?> h;
    private File i;
    private p j;

    o(f<?> fVar, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.b = fVar;
        this.a = fetcherReadyCallback;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.DataFetcherGenerator
    public boolean startNext() {
        List<Key> listO = this.b.o();
        boolean z = false;
        if (listO.isEmpty()) {
            return false;
        }
        List<Class<?>> listL = this.b.l();
        if (listL.isEmpty()) {
            if (File.class.equals(this.b.j())) {
                return false;
            }
            throw new IllegalStateException("Failed to find any load path from " + this.b.k() + " to " + this.b.j());
        }
        while (true) {
            if (this.f == null || !a()) {
                this.d++;
                if (this.d >= listL.size()) {
                    this.c++;
                    if (this.c >= listO.size()) {
                        return false;
                    }
                    this.d = 0;
                }
                Key key = listO.get(this.c);
                Class<?> cls = listL.get(this.d);
                this.j = new p(this.b.i(), key, this.b.f(), this.b.g(), this.b.h(), this.b.c(cls), cls, this.b.e());
                this.i = this.b.b().get(this.j);
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
        this.a.onDataFetcherReady(this.e, obj, this.h.fetcher, DataSource.RESOURCE_DISK_CACHE, this.j);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher.DataCallback
    public void onLoadFailed(Exception exc) {
        this.a.onDataFetcherFailed(this.j, exc, this.h.fetcher, DataSource.RESOURCE_DISK_CACHE);
    }
}
