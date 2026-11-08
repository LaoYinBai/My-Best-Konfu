package com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.cache;

import com.bytedance.sdk.adnet.game.ok3.okio.Buffer;
import com.bytedance.sdk.adnet.game.ok3.okio.ForwardingSink;
import com.bytedance.sdk.adnet.game.ok3.okio.Sink;
import java.io.EOFException;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class FaultHidingSink extends ForwardingSink {
    private boolean hasErrors;

    FaultHidingSink(Sink sink) {
        super(sink);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.ForwardingSink, com.bytedance.sdk.adnet.game.ok3.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.hasErrors) {
            return;
        }
        try {
            super.close();
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.ForwardingSink, com.bytedance.sdk.adnet.game.ok3.okio.Sink, java.io.Flushable
    public void flush() {
        if (this.hasErrors) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }

    protected void onException(IOException iOException) {
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.ForwardingSink, com.bytedance.sdk.adnet.game.ok3.okio.Sink
    public void write(Buffer buffer, long j) throws EOFException {
        if (this.hasErrors) {
            buffer.skip(j);
            return;
        }
        try {
            super.write(buffer, j);
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }
}
