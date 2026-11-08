package pl.droidsonroids.gif;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
class ConditionVariable {
    private volatile boolean mCondition;

    ConditionVariable() {
    }

    synchronized void set(boolean z) {
        try {
            if (z) {
                open();
            } else {
                close();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    synchronized void open() {
        boolean z = this.mCondition;
        this.mCondition = true;
        if (!z) {
            notify();
        }
    }

    synchronized void close() {
        this.mCondition = false;
    }

    synchronized void block() throws InterruptedException {
        while (!this.mCondition) {
            wait();
        }
    }
}
