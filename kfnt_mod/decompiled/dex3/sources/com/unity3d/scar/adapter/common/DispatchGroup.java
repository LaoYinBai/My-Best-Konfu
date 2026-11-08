package com.unity3d.scar.adapter.common;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DispatchGroup {
    private Runnable _runnable;
    private int _threadCount;

    public DispatchGroup() {
        this._threadCount = 0;
        this._threadCount = 0;
    }

    public synchronized void enter() {
        this._threadCount++;
    }

    public synchronized void leave() {
        this._threadCount--;
        notifyGroup();
    }

    public void notify(Runnable runnable) {
        this._runnable = runnable;
        notifyGroup();
    }

    private void notifyGroup() {
        if (this._threadCount > 0 || this._runnable == null) {
            return;
        }
        this._runnable.run();
    }
}
