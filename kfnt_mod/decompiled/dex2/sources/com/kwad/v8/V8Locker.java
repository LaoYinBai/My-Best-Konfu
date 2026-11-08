package com.kwad.v8;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class V8Locker {
    private V8 runtime;
    private Thread thread = null;
    private boolean released = false;

    V8Locker(V8 v8) {
        this.runtime = v8;
        acquire();
    }

    public synchronized void acquire() {
        if (this.thread != null && this.thread != Thread.currentThread()) {
            throw new Error("Invalid V8 thread access: current thread is " + Thread.currentThread() + " while the locker has thread " + this.thread);
        }
        if (this.thread == Thread.currentThread()) {
            return;
        }
        this.runtime.acquireLock(this.runtime.getV8RuntimePtr());
        this.thread = Thread.currentThread();
        this.released = false;
    }

    public void checkThread() {
        if (this.released && this.thread == null) {
            throw new Error("Invalid V8 thread access: the locker has been released!");
        }
        if (this.thread == Thread.currentThread()) {
            return;
        }
        throw new Error("Invalid V8 thread access: current thread is " + Thread.currentThread() + " while the locker has thread " + this.thread);
    }

    public Thread getThread() {
        return this.thread;
    }

    public boolean hasLock() {
        return this.thread == Thread.currentThread();
    }

    public synchronized void release() {
        if ((this.released && this.thread == null) || this.runtime.isReleased()) {
            return;
        }
        checkThread();
        this.runtime.releaseLock(this.runtime.getV8RuntimePtr());
        this.thread = null;
        this.released = true;
    }

    public synchronized boolean tryAcquire() {
        if (this.thread != null && this.thread != Thread.currentThread()) {
            return false;
        }
        if (this.thread == Thread.currentThread()) {
            return true;
        }
        this.runtime.acquireLock(this.runtime.getV8RuntimePtr());
        this.thread = Thread.currentThread();
        this.released = false;
        return true;
    }
}
