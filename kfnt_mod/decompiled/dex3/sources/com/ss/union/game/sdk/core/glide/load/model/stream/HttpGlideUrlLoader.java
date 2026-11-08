package com.ss.union.game.sdk.core.glide.load.model.stream;

import com.ss.union.game.sdk.core.glide.load.Option;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.model.GlideUrl;
import com.ss.union.game.sdk.core.glide.load.model.ModelCache;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory;
import com.ss.union.game.sdk.core.glide.load.model.MultiModelLoaderFactory;
import com.ss.union.game.sdk.core.glide.tnc.TncHttpUrlFetcher;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class HttpGlideUrlLoader implements ModelLoader<GlideUrl, InputStream> {
    public static final Option<Integer> TIMEOUT = Option.memory("model.stream.HttpGlideUrlLoader.Timeout", 2500);
    private final ModelCache<GlideUrl, GlideUrl> a;

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public boolean handles(GlideUrl glideUrl) {
        return true;
    }

    public HttpGlideUrlLoader() {
        this(null);
    }

    public HttpGlideUrlLoader(ModelCache<GlideUrl, GlideUrl> modelCache) {
        this.a = modelCache;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public ModelLoader.LoadData<InputStream> buildLoadData(GlideUrl glideUrl, int i, int i2, Options options) {
        if (this.a != null) {
            GlideUrl glideUrl2 = this.a.get(glideUrl, 0, 0);
            if (glideUrl2 == null) {
                this.a.put(glideUrl, 0, 0, glideUrl);
            } else {
                glideUrl = glideUrl2;
            }
        }
        return new ModelLoader.LoadData<>(glideUrl, new TncHttpUrlFetcher(glideUrl, ((Integer) options.get(TIMEOUT)).intValue()));
    }

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        private final ModelCache<GlideUrl, GlideUrl> a = new ModelCache<>(500);

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public ModelLoader<GlideUrl, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new HttpGlideUrlLoader(this.a);
        }
    }
}
