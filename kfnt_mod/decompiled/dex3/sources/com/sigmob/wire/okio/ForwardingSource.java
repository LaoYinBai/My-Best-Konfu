package com.sigmob.wire.okio;

import com.umeng.message.proguard.l;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class ForwardingSource implements Source {
    private final Source delegate;

    public ForwardingSource(Source source) {
        if (source == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.delegate = source;
    }

    @Override // com.sigmob.wire.okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    public final Source delegate() {
        return this.delegate;
    }

    @Override // com.sigmob.wire.okio.Source
    public long read(Buffer buffer, long j) {
        return this.delegate.read(buffer, j);
    }

    @Override // com.sigmob.wire.okio.Source
    public Timeout timeout() {
        return this.delegate.timeout();
    }

    public String toString() {
        return getClass().getSimpleName() + l.s + this.delegate.toString() + l.t;
    }
}
