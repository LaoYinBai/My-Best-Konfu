package com.bumptech.glide.request;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface Request {
    void begin();

    void clear();

    boolean isCancelled();

    boolean isComplete();

    boolean isFailed();

    boolean isPaused();

    boolean isResourceSet();

    boolean isRunning();

    void pause();

    void recycle();
}
