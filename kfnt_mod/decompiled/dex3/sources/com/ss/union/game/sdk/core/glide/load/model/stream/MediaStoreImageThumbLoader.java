package com.ss.union.game.sdk.core.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.data.mediastore.MediaStoreUtil;
import com.ss.union.game.sdk.core.glide.load.data.mediastore.ThumbFetcher;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory;
import com.ss.union.game.sdk.core.glide.load.model.MultiModelLoaderFactory;
import com.ss.union.game.sdk.core.glide.signature.ObjectKey;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MediaStoreImageThumbLoader implements ModelLoader<Uri, InputStream> {
    private final Context a;

    public MediaStoreImageThumbLoader(Context context) {
        this.a = context.getApplicationContext();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public ModelLoader.LoadData<InputStream> buildLoadData(Uri uri, int i, int i2, Options options) {
        if (MediaStoreUtil.isThumbnailSize(i, i2)) {
            return new ModelLoader.LoadData<>(new ObjectKey(uri), ThumbFetcher.buildImageFetcher(this.a, uri));
        }
        return null;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public boolean handles(Uri uri) {
        return MediaStoreUtil.isMediaStoreImageUri(uri);
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
            return new MediaStoreImageThumbLoader(this.a);
        }
    }
}
