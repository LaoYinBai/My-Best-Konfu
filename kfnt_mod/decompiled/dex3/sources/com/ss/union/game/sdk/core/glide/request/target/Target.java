package com.ss.union.game.sdk.core.glide.request.target;

import android.graphics.drawable.Drawable;
import com.ss.union.game.sdk.core.glide.manager.LifecycleListener;
import com.ss.union.game.sdk.core.glide.request.Request;
import com.ss.union.game.sdk.core.glide.request.transition.Transition;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface Target<R> extends LifecycleListener {
    public static final int SIZE_ORIGINAL = Integer.MIN_VALUE;

    Request getRequest();

    void getSize(SizeReadyCallback sizeReadyCallback);

    void onLoadCleared(Drawable drawable);

    void onLoadFailed(Drawable drawable);

    void onLoadStarted(Drawable drawable);

    void onResourceReady(R r, Transition<? super R> transition);

    void removeCallback(SizeReadyCallback sizeReadyCallback);

    void setRequest(Request request);
}
