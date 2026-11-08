package com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws;

import com.bytedance.sdk.adnet.game.ok3.okhttp3.Call;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Callback;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.EventListener;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.OkHttpClient;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Protocol;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Response;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.WebSocket;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.WebSocketListener;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.Internal;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.Util;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.StreamAllocation;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.WebSocketReader;
import com.bytedance.sdk.adnet.game.ok3.okio.BufferedSink;
import com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource;
import com.bytedance.sdk.adnet.game.ok3.okio.ByteString;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    private static final long MAX_QUEUE_SIZE = 16777216;
    private static final List<Protocol> ONLY_HTTP1 = Collections.singletonList(Protocol.HTTP_1_1);
    private Call call;
    private ScheduledFuture<?> cancelFuture;
    private boolean enqueuedClose;
    private ScheduledExecutorService executor;
    private boolean failed;
    private final String key;
    final WebSocketListener listener;
    private final Request originalRequest;
    int pingCount;
    int pongCount;
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private String receivedCloseReason;
    private Streams streams;
    private WebSocketWriter writer;
    private final Runnable writerRunnable;
    private final ArrayDeque<ByteString> pongQueue = new ArrayDeque<>();
    private final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque<>();
    private int receivedCloseCode = -1;

    final class CancelRunnable implements Runnable {
        CancelRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RealWebSocket.this.cancel();
        }
    }

    static final class Close {
        final long cancelAfterCloseMillis;
        final int code;
        final ByteString reason;

        Close(int i, ByteString byteString, long j) {
            this.code = i;
            this.reason = byteString;
            this.cancelAfterCloseMillis = j;
        }
    }

    static final class Message {
        final ByteString data;
        final int formatOpcode;

        Message(int i, ByteString byteString) {
            this.formatOpcode = i;
            this.data = byteString;
        }
    }

    private final class PingRunnable implements Runnable {
        PingRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RealWebSocket.this.writePingFrame();
        }
    }

    public static abstract class Streams implements Closeable {
        public final boolean client;
        public final BufferedSink sink;
        public final BufferedSource source;

        public Streams(boolean z, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.client = z;
            this.source = bufferedSource;
            this.sink = bufferedSink;
        }
    }

    public RealWebSocket(Request request, WebSocketListener webSocketListener, Random random) {
        if (!"GET".equals(request.method())) {
            throw new IllegalArgumentException("Request must be GET: " + request.method());
        }
        this.originalRequest = request;
        this.listener = webSocketListener;
        this.random = random;
        byte[] bArr = new byte[16];
        random.nextBytes(bArr);
        this.key = ByteString.of(bArr).base64();
        this.writerRunnable = new Runnable() { // from class: com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket.1
            @Override // java.lang.Runnable
            public void run() {
                do {
                    try {
                    } catch (IOException e) {
                        RealWebSocket.this.failWebSocket(e, null);
                        return;
                    }
                } while (RealWebSocket.this.writeOneFrame());
            }
        };
    }

    private void runWriter() {
        if (this.executor != null) {
            this.executor.execute(this.writerRunnable);
        }
    }

    private synchronized boolean send(ByteString byteString, int i) {
        if (!this.failed && !this.enqueuedClose) {
            if (this.queueSize + byteString.size() > MAX_QUEUE_SIZE) {
                close(1001, null);
                return false;
            }
            this.queueSize += byteString.size();
            this.messageAndCloseQueue.add(new Message(i, byteString));
            runWriter();
            return true;
        }
        return false;
    }

    void awaitTermination(int i, TimeUnit timeUnit) {
        this.executor.awaitTermination(i, timeUnit);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.WebSocket
    public void cancel() {
        this.call.cancel();
    }

    void checkResponse(Response response) throws ProtocolException {
        if (response.code() != 101) {
            throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + " " + response.message() + "'");
        }
        String strHeader = response.header("Connection");
        if (!"Upgrade".equalsIgnoreCase(strHeader)) {
            throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + strHeader + "'");
        }
        String strHeader2 = response.header("Upgrade");
        if (!"websocket".equalsIgnoreCase(strHeader2)) {
            throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + strHeader2 + "'");
        }
        String strHeader3 = response.header("Sec-WebSocket-Accept");
        String strBase64 = ByteString.encodeUtf8(this.key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64();
        if (strBase64.equals(strHeader3)) {
            return;
        }
        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + strBase64 + "' but was '" + strHeader3 + "'");
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.WebSocket
    public boolean close(int i, String str) {
        return close(i, str, 60000L);
    }

    synchronized boolean close(int i, String str, long j) {
        WebSocketProtocol.validateCloseCode(i);
        ByteString byteStringEncodeUtf8 = null;
        if (str != null) {
            byteStringEncodeUtf8 = ByteString.encodeUtf8(str);
            if (byteStringEncodeUtf8.size() > 123) {
                throw new IllegalArgumentException("reason.size() > 123: " + str);
            }
        }
        if (!this.failed && !this.enqueuedClose) {
            this.enqueuedClose = true;
            this.messageAndCloseQueue.add(new Close(i, byteStringEncodeUtf8, j));
            runWriter();
            return true;
        }
        return false;
    }

    public void connect(OkHttpClient okHttpClient) {
        OkHttpClient okHttpClientBuild = okHttpClient.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        final int iPingIntervalMillis = okHttpClientBuild.pingIntervalMillis();
        final Request requestBuild = this.originalRequest.newBuilder().header("Upgrade", "websocket").header("Connection", "Upgrade").header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").build();
        this.call = Internal.instance.newWebSocketCall(okHttpClientBuild, requestBuild);
        this.call.enqueue(new Callback() { // from class: com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket.2
            @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                RealWebSocket.this.failWebSocket(iOException, null);
            }

            @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    RealWebSocket.this.checkResponse(response);
                    StreamAllocation streamAllocation = Internal.instance.streamAllocation(call);
                    streamAllocation.noNewStreams();
                    Streams streamsNewWebSocketStreams = streamAllocation.connection().newWebSocketStreams(streamAllocation);
                    try {
                        RealWebSocket.this.listener.onOpen(RealWebSocket.this, response);
                        RealWebSocket.this.initReaderAndWriter("OkHttp WebSocket " + requestBuild.url().redact(), iPingIntervalMillis, streamsNewWebSocketStreams);
                        streamAllocation.connection().socket().setSoTimeout(0);
                        RealWebSocket.this.loopReader();
                    } catch (Exception e) {
                        RealWebSocket.this.failWebSocket(e, null);
                    }
                } catch (ProtocolException e2) {
                    RealWebSocket.this.failWebSocket(e2, response);
                    Util.closeQuietly(response);
                }
            }
        });
    }

    public void failWebSocket(Exception exc, Response response) {
        synchronized (this) {
            if (this.failed) {
                return;
            }
            this.failed = true;
            Streams streams = this.streams;
            this.streams = null;
            if (this.cancelFuture != null) {
                this.cancelFuture.cancel(false);
            }
            if (this.executor != null) {
                this.executor.shutdown();
            }
            try {
                this.listener.onFailure(this, exc, response);
            } finally {
                Util.closeQuietly(streams);
            }
        }
    }

    public void initReaderAndWriter(String str, long j, Streams streams) {
        synchronized (this) {
            this.streams = streams;
            this.writer = new WebSocketWriter(streams.client, streams.sink, this.random);
            this.executor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(str, false));
            if (j != 0) {
                this.executor.scheduleAtFixedRate(new PingRunnable(), j, j, TimeUnit.MILLISECONDS);
            }
            if (!this.messageAndCloseQueue.isEmpty()) {
                runWriter();
            }
        }
        this.reader = new WebSocketReader(streams.client, streams.source, this);
    }

    public void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            this.reader.processNextFrame();
        }
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadClose(int i, String str) {
        Streams streams;
        if (i == -1) {
            throw new IllegalArgumentException();
        }
        synchronized (this) {
            if (this.receivedCloseCode != -1) {
                throw new IllegalStateException("already closed");
            }
            this.receivedCloseCode = i;
            this.receivedCloseReason = str;
            if (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
                streams = this.streams;
                this.streams = null;
                if (this.cancelFuture != null) {
                    this.cancelFuture.cancel(false);
                }
                this.executor.shutdown();
            } else {
                streams = null;
            }
        }
        try {
            this.listener.onClosing(this, i, str);
            if (streams != null) {
                this.listener.onClosed(this, i, str);
            }
        } finally {
            Util.closeQuietly(streams);
        }
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(ByteString byteString) {
        this.listener.onMessage(this, byteString);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(String str) {
        this.listener.onMessage(this, str);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.WebSocketReader.FrameCallback
    public synchronized void onReadPing(ByteString byteString) {
        if (!this.failed && (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty())) {
            this.pongQueue.add(byteString);
            runWriter();
            this.pingCount++;
        }
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.WebSocketReader.FrameCallback
    public synchronized void onReadPong(ByteString byteString) {
        this.pongCount++;
    }

    synchronized int pingCount() {
        return this.pingCount;
    }

    synchronized boolean pong(ByteString byteString) {
        boolean z;
        if (this.failed || (this.enqueuedClose && this.messageAndCloseQueue.isEmpty())) {
            z = false;
        } else {
            this.pongQueue.add(byteString);
            runWriter();
            z = true;
        }
        return z;
    }

    synchronized int pongCount() {
        return this.pongCount;
    }

    boolean processNextFrame() {
        try {
            this.reader.processNextFrame();
            return this.receivedCloseCode == -1;
        } catch (Exception e) {
            failWebSocket(e, null);
            return false;
        }
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.WebSocket
    public synchronized long queueSize() {
        return this.queueSize;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.WebSocket
    public Request request() {
        return this.originalRequest;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.WebSocket
    public boolean send(ByteString byteString) {
        if (byteString != null) {
            return send(byteString, 2);
        }
        throw new NullPointerException("bytes == null");
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.WebSocket
    public boolean send(String str) {
        if (str != null) {
            return send(ByteString.encodeUtf8(str), 1);
        }
        throw new NullPointerException("text == null");
    }

    void tearDown() {
        if (this.cancelFuture != null) {
            this.cancelFuture.cancel(false);
        }
        this.executor.shutdown();
        this.executor.awaitTermination(10L, TimeUnit.SECONDS);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0052 A[Catch: all -> 0x0056, TRY_ENTER, TryCatch #1 {all -> 0x0056, blocks: (B:23:0x0052, B:26:0x0058, B:28:0x005c, B:29:0x0078, B:35:0x0087, B:36:0x0088, B:38:0x008c, B:40:0x0097, B:43:0x00a1, B:44:0x00a6, B:30:0x0079, B:31:0x0083), top: B:52:0x0050, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0058 A[Catch: all -> 0x0056, TryCatch #1 {all -> 0x0056, blocks: (B:23:0x0052, B:26:0x0058, B:28:0x005c, B:29:0x0078, B:35:0x0087, B:36:0x0088, B:38:0x008c, B:40:0x0097, B:43:0x00a1, B:44:0x00a6, B:30:0x0079, B:31:0x0083), top: B:52:0x0050, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean writeOneFrame() {
        /*
            r11 = this;
            monitor-enter(r11)
            boolean r0 = r11.failed     // Catch: java.lang.Throwable -> Lab
            r1 = 0
            if (r0 == 0) goto L8
            monitor-exit(r11)     // Catch: java.lang.Throwable -> Lab
            return r1
        L8:
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.WebSocketWriter r0 = r11.writer     // Catch: java.lang.Throwable -> Lab
            java.util.ArrayDeque<com.bytedance.sdk.adnet.game.ok3.okio.ByteString> r2 = r11.pongQueue     // Catch: java.lang.Throwable -> Lab
            java.lang.Object r2 = r2.poll()     // Catch: java.lang.Throwable -> Lab
            com.bytedance.sdk.adnet.game.ok3.okio.ByteString r2 = (com.bytedance.sdk.adnet.game.ok3.okio.ByteString) r2     // Catch: java.lang.Throwable -> Lab
            r3 = -1
            r4 = 0
            if (r2 != 0) goto L4c
            java.util.ArrayDeque<java.lang.Object> r5 = r11.messageAndCloseQueue     // Catch: java.lang.Throwable -> Lab
            java.lang.Object r5 = r5.poll()     // Catch: java.lang.Throwable -> Lab
            boolean r6 = r5 instanceof com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket.Close     // Catch: java.lang.Throwable -> Lab
            if (r6 == 0) goto L46
            int r1 = r11.receivedCloseCode     // Catch: java.lang.Throwable -> Lab
            java.lang.String r6 = r11.receivedCloseReason     // Catch: java.lang.Throwable -> Lab
            if (r1 == r3) goto L31
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket$Streams r3 = r11.streams     // Catch: java.lang.Throwable -> Lab
            r11.streams = r4     // Catch: java.lang.Throwable -> Lab
            java.util.concurrent.ScheduledExecutorService r4 = r11.executor     // Catch: java.lang.Throwable -> Lab
            r4.shutdown()     // Catch: java.lang.Throwable -> Lab
            r4 = r3
            goto L4f
        L31:
            java.util.concurrent.ScheduledExecutorService r3 = r11.executor     // Catch: java.lang.Throwable -> Lab
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket$CancelRunnable r7 = new com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket$CancelRunnable     // Catch: java.lang.Throwable -> Lab
            r7.<init>()     // Catch: java.lang.Throwable -> Lab
            r8 = r5
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket$Close r8 = (com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket.Close) r8     // Catch: java.lang.Throwable -> Lab
            long r8 = r8.cancelAfterCloseMillis     // Catch: java.lang.Throwable -> Lab
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> Lab
            java.util.concurrent.ScheduledFuture r3 = r3.schedule(r7, r8, r10)     // Catch: java.lang.Throwable -> Lab
            r11.cancelFuture = r3     // Catch: java.lang.Throwable -> Lab
            goto L4f
        L46:
            if (r5 != 0) goto L4a
            monitor-exit(r11)     // Catch: java.lang.Throwable -> Lab
            return r1
        L4a:
            r6 = r4
            goto L4e
        L4c:
            r5 = r4
            r6 = r5
        L4e:
            r1 = -1
        L4f:
            monitor-exit(r11)     // Catch: java.lang.Throwable -> Lab
            if (r2 == 0) goto L58
            r0.writePong(r2)     // Catch: java.lang.Throwable -> L56
            goto L9c
        L56:
            r0 = move-exception
            goto La7
        L58:
            boolean r2 = r5 instanceof com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket.Message     // Catch: java.lang.Throwable -> L56
            if (r2 == 0) goto L88
            r1 = r5
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket$Message r1 = (com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket.Message) r1     // Catch: java.lang.Throwable -> L56
            com.bytedance.sdk.adnet.game.ok3.okio.ByteString r1 = r1.data     // Catch: java.lang.Throwable -> L56
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket$Message r5 = (com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket.Message) r5     // Catch: java.lang.Throwable -> L56
            int r2 = r5.formatOpcode     // Catch: java.lang.Throwable -> L56
            int r3 = r1.size()     // Catch: java.lang.Throwable -> L56
            long r5 = (long) r3     // Catch: java.lang.Throwable -> L56
            com.bytedance.sdk.adnet.game.ok3.okio.Sink r0 = r0.newMessageSink(r2, r5)     // Catch: java.lang.Throwable -> L56
            com.bytedance.sdk.adnet.game.ok3.okio.BufferedSink r0 = com.bytedance.sdk.adnet.game.ok3.okio.Okio.buffer(r0)     // Catch: java.lang.Throwable -> L56
            r0.write(r1)     // Catch: java.lang.Throwable -> L56
            r0.close()     // Catch: java.lang.Throwable -> L56
            monitor-enter(r11)     // Catch: java.lang.Throwable -> L56
            long r2 = r11.queueSize     // Catch: java.lang.Throwable -> L85
            int r0 = r1.size()     // Catch: java.lang.Throwable -> L85
            long r0 = (long) r0     // Catch: java.lang.Throwable -> L85
            long r2 = r2 - r0
            r11.queueSize = r2     // Catch: java.lang.Throwable -> L85
            monitor-exit(r11)     // Catch: java.lang.Throwable -> L85
            goto L9c
        L85:
            r0 = move-exception
            monitor-exit(r11)     // Catch: java.lang.Throwable -> L85
            throw r0     // Catch: java.lang.Throwable -> L56
        L88:
            boolean r2 = r5 instanceof com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket.Close     // Catch: java.lang.Throwable -> L56
            if (r2 == 0) goto La1
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket$Close r5 = (com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket.Close) r5     // Catch: java.lang.Throwable -> L56
            int r2 = r5.code     // Catch: java.lang.Throwable -> L56
            com.bytedance.sdk.adnet.game.ok3.okio.ByteString r3 = r5.reason     // Catch: java.lang.Throwable -> L56
            r0.writeClose(r2, r3)     // Catch: java.lang.Throwable -> L56
            if (r4 == 0) goto L9c
            com.bytedance.sdk.adnet.game.ok3.okhttp3.WebSocketListener r0 = r11.listener     // Catch: java.lang.Throwable -> L56
            r0.onClosed(r11, r1, r6)     // Catch: java.lang.Throwable -> L56
        L9c:
            r0 = 1
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.Util.closeQuietly(r4)
            return r0
        La1:
            java.lang.AssertionError r0 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L56
            r0.<init>()     // Catch: java.lang.Throwable -> L56
            throw r0     // Catch: java.lang.Throwable -> L56
        La7:
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.Util.closeQuietly(r4)
            throw r0
        Lab:
            r0 = move-exception
            monitor-exit(r11)     // Catch: java.lang.Throwable -> Lab
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.ws.RealWebSocket.writeOneFrame():boolean");
    }

    void writePingFrame() {
        synchronized (this) {
            if (this.failed) {
                return;
            }
            WebSocketWriter webSocketWriter = this.writer;
            try {
                webSocketWriter.writePing(ByteString.EMPTY);
            } catch (IOException e) {
                failWebSocket(e, null);
            }
        }
    }
}
