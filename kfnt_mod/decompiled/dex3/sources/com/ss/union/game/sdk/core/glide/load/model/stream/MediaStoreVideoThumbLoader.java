package com.ss.union.game.sdk.core.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.data.mediastore.MediaStoreUtil;
import com.ss.union.game.sdk.core.glide.load.data.mediastore.ThumbFetcher;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory;
import com.ss.union.game.sdk.core.glide.load.model.MultiModelLoaderFactory;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.VideoDecoder;
import com.ss.union.game.sdk.core.glide.signature.ObjectKey;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MediaStoreVideoThumbLoader implements ModelLoader<Uri, InputStream> {
    private final Context a;

    public MediaStoreVideoThumbLoader(Context context) {
        this.a = context.getApplicationContext();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public ModelLoader.LoadData<InputStream> buildLoadData(Uri uri, int i, int i2, Options options) {
        if (MediaStoreUtil.isThumbnailSize(i, i2) && a(options)) {
            return new ModelLoader.LoadData<>(new ObjectKey(uri), ThumbFetcher.buildVideoFetcher(this.a, uri));
        }
        return null;
    }

    private boolean a(Options options) {
        Long l = (Long) options.get(VideoDecoder.TARGET_FRAME);
        return l != null && l.longValue() == -1;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public boolean handles(Uri uri) {
        return MediaStoreUtil.isMediaStoreVideoUri(uri);
    }

    public static class Factory implements ModelLoaderFactory<Uri, InputStream> {
        private final Context a;

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        public Factory(Context context) {
            this.a = context;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new MediaStoreVideoThumbLoader(this.a);
        }
    }
}
