package com.ss.union.game.sdk.common.util;

import android.os.Handler;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NoLeakHandler<T> extends Handler {
    protected WeakReference<T> mOuterClass;

    public NoLeakHandler(T t) {
        this.mOuterClass = new WeakReference<>(t);
    }
}
