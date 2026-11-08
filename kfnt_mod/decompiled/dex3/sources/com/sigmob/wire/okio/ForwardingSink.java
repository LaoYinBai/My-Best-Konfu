package com.sigmob.wire.okio;

import com.umeng.message.proguard.l;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class ForwardingSink implements Sink {
    private final Sink delegate;

    public ForwardingSink(Sink sink) {
        if (sink == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.delegate = sink;
    }

    @Override // com.sigmob.wire.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    public final Sink delegate() {
        return this.delegate;
    }

    @Override // com.sigmob.wire.okio.Sink, java.io.Flushable
    public void flush() {
        this.delegate.flush();
    }

    @Override // com.sigmob.wire.okio.Sink
    public Timeout timeout() {
        return this.delegate.timeout();
    }

    public String toString() {
        return getClass().getSimpleName() + l.s + this.delegate.toString() + l.t;
    }

    @Override // com.sigmob.wire.okio.Sink
    public void write(Buffer buffer, long j) {
        this.delegate.write(buffer, j);
    }
}
