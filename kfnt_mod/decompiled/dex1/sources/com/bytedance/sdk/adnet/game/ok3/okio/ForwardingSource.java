package com.bytedance.sdk.adnet.game.ok3.okio;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class ForwardingSource implements Source {
    private final Source delegate;

    public ForwardingSource(Source source) {
        if (source == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.delegate = source;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    public final Source delegate() {
        return this.delegate;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Source
    public long read(Buffer buffer, long j) {
        return this.delegate.read(buffer, j);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Source
    public Timeout timeout() {
        return this.delegate.timeout();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.delegate.toString() + ")";
    }
}
