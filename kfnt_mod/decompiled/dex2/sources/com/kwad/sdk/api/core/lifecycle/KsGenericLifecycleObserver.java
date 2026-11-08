package com.kwad.sdk.api.core.lifecycle;

import androidx.annotation.Keep;
import com.kwad.sdk.api.core.lifecycle.KsLifecycle;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class KsGenericLifecycleObserver extends KsLifecycleObserver {
    @Keep
    public abstract void onStateChanged(KsLifecycle.KsLifeEvent ksLifeEvent);
}
