package com.kwad.sdk.core.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.kwad.sdk.api.core.fragment.KsFragment;
import com.kwad.sdk.core.imageloader.core.DisplayImageOptionsCompat;
import com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public enum ImageLoaderProxy implements IImageLoader {
    INSTANCE;

    private IImageLoader mDelegate = null;

    ImageLoaderProxy() {
    }

    @Override // com.kwad.sdk.core.imageloader.IImageLoader
    public void clearMemory(Context context) {
        if (this.mDelegate != null) {
            this.mDelegate.clearMemory(context);
        }
    }

    @Override // com.kwad.sdk.core.imageloader.IImageLoader
    public void init(Context context) {
    }

    public void init(Context context, IImageLoader iImageLoader) {
        iImageLoader.init(context);
        this.mDelegate = iImageLoader;
    }

    @Override // com.kwad.sdk.core.imageloader.IImageLoader
    public boolean isInited() {
        if (this.mDelegate != null) {
            return this.mDelegate.isInited();
        }
        return false;
    }

    @Override // com.kwad.sdk.core.imageloader.IImageLoader
    public void load(@NonNull Context context, ImageView imageView, Object obj, int i, int i2) {
        if (this.mDelegate != null) {
            this.mDelegate.load(context, imageView, obj, i, i2);
        }
    }

    @Override // com.kwad.sdk.core.imageloader.IImageLoader
    public void load(@NonNull Context context, String str, ImageView imageView, DisplayImageOptionsCompat displayImageOptionsCompat, ImageLoadingListener imageLoadingListener) {
        if (this.mDelegate != null) {
            this.mDelegate.load(context, str, imageView, displayImageOptionsCompat, imageLoadingListener);
        }
    }

    @Override // com.kwad.sdk.core.imageloader.IImageLoader
    public void load(Context context, String str, DisplayImageOptionsCompat displayImageOptionsCompat, ImageLoadingListener imageLoadingListener) {
        if (this.mDelegate != null) {
            this.mDelegate.load(context, str, displayImageOptionsCompat, imageLoadingListener);
        }
    }

    @Override // com.kwad.sdk.core.imageloader.IImageLoader
    public void load(ImageView imageView, Object obj) {
        if (this.mDelegate != null) {
            this.mDelegate.load(imageView, obj);
        }
    }

    @Override // com.kwad.sdk.core.imageloader.IImageLoader
    public void load(KsFragment ksFragment, @NonNull Context context, String str, DisplayImageOptionsCompat displayImageOptionsCompat, ImageLoadingListener imageLoadingListener) {
        if (this.mDelegate != null) {
            this.mDelegate.load(ksFragment, context, str, displayImageOptionsCompat, imageLoadingListener);
        }
    }

    @Override // com.kwad.sdk.core.imageloader.IImageLoader
    public void load(@NonNull KsFragment ksFragment, @NonNull String str, @NonNull ImageView imageView, @NonNull Drawable drawable, @NonNull Drawable drawable2) {
        if (this.mDelegate != null) {
            this.mDelegate.load(ksFragment, str, imageView, drawable, drawable2);
        }
    }

    @Override // com.kwad.sdk.core.imageloader.IImageLoader
    public void load(@NonNull KsFragment ksFragment, @NonNull String str, @NonNull ImageView imageView, @NonNull Drawable drawable, @NonNull Drawable drawable2, float f) {
        if (this.mDelegate != null) {
            this.mDelegate.load(ksFragment, str, imageView, drawable, drawable2, f);
        }
    }

    @Override // com.kwad.sdk.core.imageloader.IImageLoader
    public void pause() {
        if (this.mDelegate != null) {
            this.mDelegate.pause();
        }
    }

    @Override // com.kwad.sdk.core.imageloader.IImageLoader
    public void resume() {
        if (this.mDelegate != null) {
            this.mDelegate.resume();
        }
    }
}
