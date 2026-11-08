package com.bumptech.glide.request;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface RequestCoordinator {
    boolean canNotifyStatusChanged(Request request);

    boolean canSetImage(Request request);

    boolean isAnyResourceSet();

    void onRequestSuccess(Request request);
}
