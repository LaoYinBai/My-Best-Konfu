package com.ss.union.game.sdk.core.glide.request.target;

import android.graphics.drawable.Drawable;
import com.ss.union.game.sdk.core.glide.request.Request;
import com.ss.union.game.sdk.core.glide.util.Util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class CustomTarget<T> implements Target<T> {
    private final int a;
    private final int b;
    private Request c;

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public void onLoadFailed(Drawable drawable) {
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public void onLoadStarted(Drawable drawable) {
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onStart() {
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onStop() {
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public final void removeCallback(SizeReadyCallback sizeReadyCallback) {
    }

    public CustomTarget() {
        this(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }

    public CustomTarget(int i, int i2) {
        if (!Util.isValidDimensions(i, i2)) {
            throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + i + " and height: " + i2);
        }
        this.a = i;
        this.b = i2;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public final void getSize(SizeReadyCallback sizeReadyCallback) {
        sizeReadyCallback.onSizeReady(this.a, this.b);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public final void setRequest(Request request) {
        this.c = request;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public final Request getRequest() {
        return this.c;
    }
}
