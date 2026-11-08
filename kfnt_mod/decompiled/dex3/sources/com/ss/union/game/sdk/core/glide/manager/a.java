package com.ss.union.game.sdk.core.glide.manager;

import com.ss.union.game.sdk.core.glide.util.Util;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a implements Lifecycle {
    private final Set<LifecycleListener> a = Collections.newSetFromMap(new WeakHashMap());
    private boolean b;
    private boolean c;

    a() {
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.Lifecycle
    public void addListener(LifecycleListener lifecycleListener) {
        this.a.add(lifecycleListener);
        if (this.c) {
            lifecycleListener.onDestroy();
        } else if (this.b) {
            lifecycleListener.onStart();
        } else {
            lifecycleListener.onStop();
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.Lifecycle
    public void removeListener(LifecycleListener lifecycleListener) {
        this.a.remove(lifecycleListener);
    }

    void a() {
        this.b = true;
        Iterator it = Util.getSnapshot(this.a).iterator();
        while (it.hasNext()) {
            ((LifecycleListener) it.next()).onStart();
        }
    }

    void b() {
        this.b = false;
        Iterator it = Util.getSnapshot(this.a).iterator();
        while (it.hasNext()) {
            ((LifecycleListener) it.next()).onStop();
        }
    }

    void c() {
        this.c = true;
        Iterator it = Util.getSnapshot(this.a).iterator();
        while (it.hasNext()) {
            ((LifecycleListener) it.next()).onDestroy();
        }
    }
}
