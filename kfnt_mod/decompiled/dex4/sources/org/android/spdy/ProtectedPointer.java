package org.android.spdy;

import java.util.concurrent.atomic.AtomicLong;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
class ProtectedPointer {
    private static final long CLOSED = 3;
    private static final long INIT = 1;
    private static final long WAIT_CLOSE = 2;
    private Object data2protected;
    protected ProtectedPointerOnClose how2close;
    private AtomicLong referAndstatus = new AtomicLong(INIT);

    interface ProtectedPointerOnClose {
        void close(Object obj);
    }

    ProtectedPointer(Object obj) {
        this.data2protected = obj;
    }

    void setHow2close(ProtectedPointerOnClose protectedPointerOnClose) {
        this.how2close = protectedPointerOnClose;
    }

    boolean enter() {
        long j;
        do {
            j = this.referAndstatus.get();
            if (j == CLOSED) {
                return false;
            }
        } while (!this.referAndstatus.compareAndSet(j, 16 + j));
        return true;
    }

    void exit() {
        this.referAndstatus.addAndGet(-16L);
        if (this.referAndstatus.compareAndSet(WAIT_CLOSE, CLOSED)) {
            if (this.how2close != null) {
                this.how2close.close(this.data2protected);
            }
            this.data2protected = null;
        }
    }

    void release() {
        this.referAndstatus.incrementAndGet();
        if (this.referAndstatus.compareAndSet(WAIT_CLOSE, CLOSED)) {
            if (this.how2close != null) {
                this.how2close.close(this.data2protected);
            }
            this.data2protected = null;
        }
    }

    Object getData() {
        return this.data2protected;
    }
}
