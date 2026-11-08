package com.sigmob.wire.okio;

import android.os.Build;
import com.sigmob.sdk.common.mta.PointCategory;
import com.umeng.message.proguard.l;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class Okio {
    static final Logger logger = Logger.getLogger(Okio.class.getName());

    private Okio() {
    }

    public static Sink appendingSink(File file) {
        if (file != null) {
            return sink(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static BufferedSink buffer(Sink sink) {
        if (sink != null) {
            return new RealBufferedSink(sink);
        }
        throw new IllegalArgumentException("sink == null");
    }

    public static BufferedSource buffer(Source source) {
        if (source != null) {
            return new RealBufferedSource(source);
        }
        throw new IllegalArgumentException("source == null");
    }

    static boolean isAndroidGetsocknameError(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static Sink sink(File file) {
        if (file != null) {
            return sink(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static Sink sink(OutputStream outputStream) {
        return sink(outputStream, new Timeout());
    }

    private static Sink sink(final OutputStream outputStream, final Timeout timeout) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        if (timeout != null) {
            return new Sink() { // from class: com.sigmob.wire.okio.Okio.1
                @Override // com.sigmob.wire.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    outputStream.close();
                }

                @Override // com.sigmob.wire.okio.Sink, java.io.Flushable
                public void flush() throws IOException {
                    outputStream.flush();
                }

                @Override // com.sigmob.wire.okio.Sink
                public Timeout timeout() {
                    return timeout;
                }

                public String toString() {
                    return "sink(" + outputStream + l.t;
                }

                @Override // com.sigmob.wire.okio.Sink
                public void write(Buffer buffer, long j) throws IOException {
                    Util.checkOffsetAndCount(buffer.size, 0L, j);
                    while (j > 0) {
                        timeout.throwIfReached();
                        Segment segment = buffer.head;
                        int iMin = (int) Math.min(j, segment.limit - segment.pos);
                        outputStream.write(segment.data, segment.pos, iMin);
                        segment.pos += iMin;
                        long j2 = iMin;
                        j -= j2;
                        buffer.size -= j2;
                        if (segment.pos == segment.limit) {
                            buffer.head = segment.pop();
                            SegmentPool.recycle(segment);
                        }
                    }
                }
            };
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static Sink sink(Socket socket) {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        AsyncTimeout asyncTimeoutTimeout = timeout(socket);
        return asyncTimeoutTimeout.sink(sink(socket.getOutputStream(), asyncTimeoutTimeout));
    }

    public static Sink sink(Path path, OpenOption... openOptionArr) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("path == null");
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                return sink(Files.newOutputStream(path, openOptionArr));
            }
            throw new IOException("no support os version");
        } catch (Throwable th) {
            throw new IOException(th.getMessage());
        }
    }

    public static Source source(File file) {
        if (file != null) {
            return source(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static Source source(InputStream inputStream) {
        return source(inputStream, new Timeout());
    }

    private static Source source(final InputStream inputStream, final Timeout timeout) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (timeout != null) {
            return new Source() { // from class: com.sigmob.wire.okio.Okio.2
                @Override // com.sigmob.wire.okio.Source, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    inputStream.close();
                }

                @Override // com.sigmob.wire.okio.Source
                public long read(Buffer buffer, long j) throws IOException {
                    if (j < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j);
                    }
                    if (j == 0) {
                        return 0L;
                    }
                    try {
                        timeout.throwIfReached();
                        Segment segmentWritableSegment = buffer.writableSegment(1);
                        int i = inputStream.read(segmentWritableSegment.data, segmentWritableSegment.limit, (int) Math.min(j, 8192 - segmentWritableSegment.limit));
                        if (i == -1) {
                            return -1L;
                        }
                        segmentWritableSegment.limit += i;
                        long j2 = i;
                        buffer.size += j2;
                        return j2;
                    } catch (AssertionError e) {
                        if (Okio.isAndroidGetsocknameError(e)) {
                            throw new IOException(e);
                        }
                        throw e;
                    }
                }

                @Override // com.sigmob.wire.okio.Source
                public Timeout timeout() {
                    return timeout;
                }

                public String toString() {
                    return "source(" + inputStream + l.t;
                }
            };
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static Source source(Socket socket) {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        AsyncTimeout asyncTimeoutTimeout = timeout(socket);
        return asyncTimeoutTimeout.source(source(socket.getInputStream(), asyncTimeoutTimeout));
    }

    public static Source source(Path path, OpenOption... openOptionArr) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("path == null");
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                return source(Files.newInputStream(path, openOptionArr));
            }
            throw new IOException("no support os version");
        } catch (Throwable th) {
            throw new IOException(th.getMessage());
        }
    }

    private static AsyncTimeout timeout(final Socket socket) {
        return new AsyncTimeout() { // from class: com.sigmob.wire.okio.Okio.3
            @Override // com.sigmob.wire.okio.AsyncTimeout
            protected IOException newTimeoutException(IOException iOException) {
                SocketTimeoutException socketTimeoutException = new SocketTimeoutException(PointCategory.TIMEOUT);
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.sigmob.wire.okio.AsyncTimeout
            protected void timedOut() throws IOException {
                Level level;
                StringBuilder sb;
                Logger logger2;
                Exception exc;
                try {
                    socket.close();
                } catch (AssertionError e) {
                    if (!Okio.isAndroidGetsocknameError(e)) {
                        throw e;
                    }
                    Logger logger3 = Okio.logger;
                    level = Level.WARNING;
                    sb = new StringBuilder();
                    exc = e;
                    logger2 = logger3;
                    sb.append("Failed to close timed out socket ");
                    sb.append(socket);
                    logger2.log(level, sb.toString(), (Throwable) exc);
                } catch (Exception e2) {
                    Logger logger4 = Okio.logger;
                    level = Level.WARNING;
                    sb = new StringBuilder();
                    exc = e2;
                    logger2 = logger4;
                    sb.append("Failed to close timed out socket ");
                    sb.append(socket);
                    logger2.log(level, sb.toString(), (Throwable) exc);
                }
            }
        };
    }
}
