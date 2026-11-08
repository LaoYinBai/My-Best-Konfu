package com.ss.union.game.sdk.core.glide.request;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface Request {
    void begin();

    void clear();

    boolean isCleared();

    boolean isComplete();

    boolean isEquivalentTo(Request request);

    boolean isFailed();

    boolean isResourceSet();

    boolean isRunning();

    void recycle();
}
