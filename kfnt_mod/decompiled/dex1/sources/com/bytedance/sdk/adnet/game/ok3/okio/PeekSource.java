package com.bytedance.sdk.adnet.game.ok3.okio;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class PeekSource implements Source {
    private final Buffer buffer;
    private boolean closed;
    private int expectedPos;
    private Segment expectedSegment;
    private long pos;
    private final BufferedSource upstream;

    PeekSource(BufferedSource bufferedSource) {
        this.upstream = bufferedSource;
        this.buffer = bufferedSource.buffer();
        this.expectedSegment = this.buffer.head;
        this.expectedPos = this.expectedSegment != null ? this.expectedSegment.pos : -1;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.closed = true;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Source
    public long read(Buffer buffer, long j) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (this.expectedSegment != null && (this.expectedSegment != this.buffer.head || this.expectedPos != this.buffer.head.pos)) {
            throw new IllegalStateException("Peek source is invalid because upstream source was used");
        }
        this.upstream.request(this.pos + j);
        if (this.expectedSegment == null && this.buffer.head != null) {
            this.expectedSegment = this.buffer.head;
            this.expectedPos = this.buffer.head.pos;
        }
        long jMin = Math.min(j, this.buffer.size - this.pos);
        if (jMin <= 0) {
            return -1L;
        }
        this.buffer.copyTo(buffer, this.pos, jMin);
        this.pos += jMin;
        return jMin;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Source
    public Timeout timeout() {
        return this.upstream.timeout();
    }
}
