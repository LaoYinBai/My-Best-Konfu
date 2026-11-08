package com.ss.union.game.sdk.core.glide.manager;

import com.ss.union.game.sdk.core.glide.request.target.Target;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class TargetTracker implements LifecycleListener {
    private final Set<Target<?>> a = Collections.newSetFromMap(new WeakHashMap());

    public void track(Target<?> target) {
        this.a.add(target);
    }

    public void untrack(Target<?> target) {
        this.a.remove(target);
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onStart() {
        Iterator it = Util.getSnapshot(this.a).iterator();
        while (it.hasNext()) {
            ((Target) it.next()).onStart();
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onStop() {
        Iterator it = Util.getSnapshot(this.a).iterator();
        while (it.hasNext()) {
            ((Target) it.next()).onStop();
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onDestroy() {
        Iterator it = Util.getSnapshot(this.a).iterator();
        while (it.hasNext()) {
            ((Target) it.next()).onDestroy();
        }
    }

    public List<Target<?>> getAll() {
        return Util.getSnapshot(this.a);
    }

    public void clear() {
        this.a.clear();
    }
}
