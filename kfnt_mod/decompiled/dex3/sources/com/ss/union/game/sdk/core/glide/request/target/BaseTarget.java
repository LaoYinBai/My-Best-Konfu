package com.ss.union.game.sdk.core.glide.request.target;

import android.graphics.drawable.Drawable;
import com.ss.union.game.sdk.core.glide.request.Request;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class BaseTarget<Z> implements Target<Z> {
    private Request a;

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public void onLoadCleared(Drawable drawable) {
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
    public void setRequest(Request request) {
        this.a = request;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public Request getRequest() {
        return this.a;
    }
}
