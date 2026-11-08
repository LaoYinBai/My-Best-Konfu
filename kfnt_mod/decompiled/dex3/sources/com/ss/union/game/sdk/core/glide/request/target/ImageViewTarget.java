package com.ss.union.game.sdk.core.glide.request.target;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.ss.union.game.sdk.core.glide.request.transition.Transition;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class ImageViewTarget<Z> extends ViewTarget<ImageView, Z> implements Transition.ViewAdapter {
    private Animatable a;

    protected abstract void setResource(Z z);

    public ImageViewTarget(ImageView imageView) {
        super(imageView);
    }

    @Deprecated
    public ImageViewTarget(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.transition.Transition.ViewAdapter
    public Drawable getCurrentDrawable() {
        return ((ImageView) this.view).getDrawable();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.transition.Transition.ViewAdapter
    public void setDrawable(Drawable drawable) {
        ((ImageView) this.view).setImageDrawable(drawable);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.ViewTarget, com.ss.union.game.sdk.core.glide.request.target.BaseTarget, com.ss.union.game.sdk.core.glide.request.target.Target
    public void onLoadStarted(Drawable drawable) {
        super.onLoadStarted(drawable);
        a(null);
        setDrawable(drawable);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.BaseTarget, com.ss.union.game.sdk.core.glide.request.target.Target
    public void onLoadFailed(Drawable drawable) {
        super.onLoadFailed(drawable);
        a(null);
        setDrawable(drawable);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.ViewTarget, com.ss.union.game.sdk.core.glide.request.target.BaseTarget, com.ss.union.game.sdk.core.glide.request.target.Target
    public void onLoadCleared(Drawable drawable) {
        super.onLoadCleared(drawable);
        if (this.a != null) {
            this.a.stop();
        }
        a(null);
        setDrawable(drawable);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public void onResourceReady(Z z, Transition<? super Z> transition) {
        if (transition == null || !transition.transition(z, this)) {
            a(z);
        } else {
            b(z);
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.BaseTarget, com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onStart() {
        if (this.a != null) {
            this.a.start();
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.BaseTarget, com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onStop() {
        if (this.a != null) {
            this.a.stop();
        }
    }

    private void a(Z z) {
        setResource(z);
        b(z);
    }

    private void b(Z z) {
        if (z instanceof Animatable) {
            this.a = (Animatable) z;
            this.a.start();
        } else {
            this.a = null;
        }
    }
}
