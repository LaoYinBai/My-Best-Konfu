package com.bytedance.sdk.adnet.game.ok3.okio;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ForwardingTimeout extends Timeout {
    private Timeout delegate;

    public ForwardingTimeout(Timeout timeout) {
        if (timeout == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.delegate = timeout;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Timeout
    public Timeout clearDeadline() {
        return this.delegate.clearDeadline();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Timeout
    public Timeout clearTimeout() {
        return this.delegate.clearTimeout();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Timeout
    public long deadlineNanoTime() {
        return this.delegate.deadlineNanoTime();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Timeout
    public Timeout deadlineNanoTime(long j) {
        return this.delegate.deadlineNanoTime(j);
    }

    public final Timeout delegate() {
        return this.delegate;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Timeout
    public boolean hasDeadline() {
        return this.delegate.hasDeadline();
    }

    public final ForwardingTimeout setDelegate(Timeout timeout) {
        if (timeout == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.delegate = timeout;
        return this;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Timeout
    public void throwIfReached() throws InterruptedIOException {
        this.delegate.throwIfReached();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Timeout
    public Timeout timeout(long j, TimeUnit timeUnit) {
        return this.delegate.timeout(j, timeUnit);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Timeout
    public long timeoutNanos() {
        return this.delegate.timeoutNanos();
    }
}
