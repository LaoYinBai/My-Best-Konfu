package com.sigmob.wire.okio;

import com.umeng.message.proguard.l;
import java.util.zip.Deflater;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class DeflaterSink implements Sink {
    private boolean closed;
    private final Deflater deflater;
    private final BufferedSink sink;

    DeflaterSink(BufferedSink bufferedSink, Deflater deflater) {
        if (bufferedSink == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (deflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.sink = bufferedSink;
        this.deflater = deflater;
    }

    public DeflaterSink(Sink sink, Deflater deflater) {
        this(Okio.buffer(sink), deflater);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void deflate(boolean r9) {
        /*
            r8 = this;
            com.sigmob.wire.okio.BufferedSink r0 = r8.sink
            com.sigmob.wire.okio.Buffer r0 = r0.buffer()
        L6:
            r1 = 1
            com.sigmob.wire.okio.Segment r1 = r0.writableSegment(r1)
            r2 = 0
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L3c
            r4 = 19
            if (r3 < r4) goto L2d
            if (r9 == 0) goto L24
            java.util.zip.Deflater r3 = r8.deflater     // Catch: java.lang.Throwable -> L3c
            byte[] r4 = r1.data     // Catch: java.lang.Throwable -> L3c
            int r5 = r1.limit     // Catch: java.lang.Throwable -> L3c
            int r6 = r1.limit     // Catch: java.lang.Throwable -> L3c
            int r6 = 8192 - r6
            r7 = 2
            int r3 = r3.deflate(r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L3c
            goto L3b
        L24:
            java.util.zip.Deflater r3 = r8.deflater     // Catch: java.lang.Throwable -> L3c
            byte[] r4 = r1.data     // Catch: java.lang.Throwable -> L3c
            int r5 = r1.limit     // Catch: java.lang.Throwable -> L3c
            int r6 = r1.limit     // Catch: java.lang.Throwable -> L3c
            goto L35
        L2d:
            java.util.zip.Deflater r3 = r8.deflater     // Catch: java.lang.Throwable -> L3c
            byte[] r4 = r1.data     // Catch: java.lang.Throwable -> L3c
            int r5 = r1.limit     // Catch: java.lang.Throwable -> L3c
            int r6 = r1.limit     // Catch: java.lang.Throwable -> L3c
        L35:
            int r6 = 8192 - r6
            int r3 = r3.deflate(r4, r5, r6)     // Catch: java.lang.Throwable -> L3c
        L3b:
            r2 = r3
        L3c:
            if (r2 <= 0) goto L4f
            int r3 = r1.limit
            int r3 = r3 + r2
            r1.limit = r3
            long r3 = r0.size
            long r1 = (long) r2
            long r3 = r3 + r1
            r0.size = r3
            com.sigmob.wire.okio.BufferedSink r1 = r8.sink
            r1.emitCompleteSegments()
            goto L6
        L4f:
            java.util.zip.Deflater r2 = r8.deflater
            boolean r2 = r2.needsInput()
            if (r2 == 0) goto L6
            int r9 = r1.pos
            int r2 = r1.limit
            if (r9 != r2) goto L66
            com.sigmob.wire.okio.Segment r9 = r1.pop()
            r0.head = r9
            com.sigmob.wire.okio.SegmentPool.recycle(r1)
        L66:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.wire.okio.DeflaterSink.deflate(boolean):void");
    }

    @Override // com.sigmob.wire.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.closed) {
            return;
        }
        Throwable th = null;
        try {
            finishDeflate();
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.deflater.end();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        try {
            this.sink.close();
        } catch (Throwable th4) {
            if (th == null) {
                th = th4;
            }
        }
        this.closed = true;
        if (th != null) {
            Util.sneakyRethrow(th);
        }
    }

    void finishDeflate() {
        this.deflater.finish();
        deflate(false);
    }

    @Override // com.sigmob.wire.okio.Sink, java.io.Flushable
    public void flush() {
        deflate(true);
        this.sink.flush();
    }

    @Override // com.sigmob.wire.okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        return "DeflaterSink(" + this.sink + l.t;
    }

    @Override // com.sigmob.wire.okio.Sink
    public void write(Buffer buffer, long j) {
        Util.checkOffsetAndCount(buffer.size, 0L, j);
        while (j > 0) {
            Segment segment = buffer.head;
            int iMin = (int) Math.min(j, segment.limit - segment.pos);
            this.deflater.setInput(segment.data, segment.pos, iMin);
            deflate(false);
            long j2 = iMin;
            buffer.size -= j2;
            segment.pos += iMin;
            if (segment.pos == segment.limit) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            }
            j -= j2;
        }
    }
}
