package com.ss.union.game.sdk.core.glide.load.model.stream;

import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.model.GlideUrl;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory;
import com.ss.union.game.sdk.core.glide.load.model.MultiModelLoaderFactory;
import java.io.InputStream;
import java.net.URL;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UrlLoader implements ModelLoader<URL, InputStream> {
    private final ModelLoader<GlideUrl, InputStream> a;

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public boolean handles(URL url) {
        return true;
    }

    public UrlLoader(ModelLoader<GlideUrl, InputStream> modelLoader) {
        this.a = modelLoader;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public ModelLoader.LoadData<InputStream> buildLoadData(URL url, int i, int i2, Options options) {
        return this.a.buildLoadData(new GlideUrl(url), i, i2, options);
    }

    public static class StreamFactory implements ModelLoaderFactory<URL, InputStream> {
        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public ModelLoader<URL, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new UrlLoader(multiModelLoaderFactory.build(GlideUrl.class, InputStream.class));
        }
    }
}
