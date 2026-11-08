package com.ss.union.game.sdk.core.glide.manager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class b implements Lifecycle {
    @Override // com.ss.union.game.sdk.core.glide.manager.Lifecycle
    public void removeListener(LifecycleListener lifecycleListener) {
    }

    b() {
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.Lifecycle
    public void addListener(LifecycleListener lifecycleListener) {
        lifecycleListener.onStart();
    }
}
