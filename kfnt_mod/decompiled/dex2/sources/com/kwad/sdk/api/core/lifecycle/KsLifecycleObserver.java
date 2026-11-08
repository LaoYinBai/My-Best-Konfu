package com.kwad.sdk.api.core.lifecycle;

import androidx.annotation.Keep;
import androidx.lifecycle.LifecycleObserver;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KsLifecycleObserver {
    LifecycleObserver mBase;

    public LifecycleObserver getBase() {
        return this.mBase;
    }

    public void setBase(LifecycleObserver lifecycleObserver) {
        this.mBase = lifecycleObserver;
    }
}
