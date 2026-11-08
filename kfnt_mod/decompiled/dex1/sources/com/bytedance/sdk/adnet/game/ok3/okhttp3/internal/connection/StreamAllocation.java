package com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection;

import com.bytedance.sdk.adnet.game.ok3.okhttp3.Address;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Call;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.ConnectionPool;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.EventListener;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Interceptor;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.OkHttpClient;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Route;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.Internal;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.Util;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RouteSelector;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http.HttpCodec;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class StreamAllocation {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public final Address address;
    public final Call call;
    private final Object callStackTrace;
    private boolean canceled;
    private HttpCodec codec;
    private RealConnection connection;
    private final ConnectionPool connectionPool;
    public final EventListener eventListener;
    private int refusedStreamCount;
    private boolean released;
    private boolean reportedAcquired;
    private Route route;
    private RouteSelector.Selection routeSelection;
    private final RouteSelector routeSelector;

    public static final class StreamAllocationReference extends WeakReference<StreamAllocation> {
        public final Object callStackTrace;

        StreamAllocationReference(StreamAllocation streamAllocation, Object obj) {
            super(streamAllocation);
            this.callStackTrace = obj;
        }
    }

    public StreamAllocation(ConnectionPool connectionPool, Address address, Call call, EventListener eventListener, Object obj) {
        this.connectionPool = connectionPool;
        this.address = address;
        this.call = call;
        this.eventListener = eventListener;
        this.routeSelector = new RouteSelector(address, routeDatabase(), call, eventListener);
        this.callStackTrace = obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.net.Socket deallocate(boolean r2, boolean r3, boolean r4) {
        /*
            r1 = this;
            r0 = 0
            if (r4 == 0) goto L5
            r1.codec = r0
        L5:
            r4 = 1
            if (r3 == 0) goto La
            r1.released = r4
        La:
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RealConnection r3 = r1.connection
            if (r3 == 0) goto L50
            if (r2 == 0) goto L14
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RealConnection r2 = r1.connection
            r2.noNewStreams = r4
        L14:
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http.HttpCodec r2 = r1.codec
            if (r2 != 0) goto L50
            boolean r2 = r1.released
            if (r2 != 0) goto L22
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RealConnection r2 = r1.connection
            boolean r2 = r2.noNewStreams
            if (r2 == 0) goto L50
        L22:
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RealConnection r2 = r1.connection
            r1.release(r2)
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RealConnection r2 = r1.connection
            java.util.List<java.lang.ref.Reference<com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.StreamAllocation>> r2 = r2.allocations
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L4c
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RealConnection r2 = r1.connection
            long r3 = java.lang.System.nanoTime()
            r2.idleAtNanos = r3
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.Internal r2 = com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.Internal.instance
            com.bytedance.sdk.adnet.game.ok3.okhttp3.ConnectionPool r3 = r1.connectionPool
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RealConnection r4 = r1.connection
            boolean r2 = r2.connectionBecameIdle(r3, r4)
            if (r2 == 0) goto L4c
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RealConnection r2 = r1.connection
            java.net.Socket r2 = r2.socket()
            goto L4d
        L4c:
            r2 = r0
        L4d:
            r1.connection = r0
            goto L51
        L50:
            r2 = r0
        L51:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.StreamAllocation.deallocate(boolean, boolean, boolean):java.net.Socket");
    }

    private RealConnection findConnection(int i, int i2, int i3, boolean z) throws Throwable {
        Socket socketReleaseIfNoNewStreams;
        Socket socketDeduplicate;
        RealConnection realConnection;
        RealConnection realConnection2;
        RealConnection realConnection3;
        Route next;
        boolean z2;
        boolean z3;
        synchronized (this.connectionPool) {
            if (this.released) {
                throw new IllegalStateException("released");
            }
            if (this.codec != null) {
                throw new IllegalStateException("codec != null");
            }
            if (this.canceled) {
                throw new IOException("Canceled");
            }
            RealConnection realConnection4 = this.connection;
            socketReleaseIfNoNewStreams = releaseIfNoNewStreams();
            socketDeduplicate = null;
            if (this.connection != null) {
                realConnection2 = this.connection;
                realConnection = null;
            } else {
                realConnection = realConnection4;
                realConnection2 = null;
            }
            if (!this.reportedAcquired) {
                realConnection = null;
            }
            if (realConnection2 == null) {
                Internal.instance.get(this.connectionPool, this.address, this, null);
                if (this.connection != null) {
                    realConnection3 = this.connection;
                    next = null;
                    z2 = true;
                } else {
                    next = this.route;
                    realConnection3 = realConnection2;
                }
            } else {
                realConnection3 = realConnection2;
                next = null;
            }
            z2 = false;
        }
        Util.closeQuietly(socketReleaseIfNoNewStreams);
        if (realConnection != null) {
            this.eventListener.connectionReleased(this.call, realConnection);
        }
        if (z2) {
            this.eventListener.connectionAcquired(this.call, realConnection3);
        }
        if (realConnection3 != null) {
            return realConnection3;
        }
        if (next != null || (this.routeSelection != null && this.routeSelection.hasNext())) {
            z3 = false;
        } else {
            this.routeSelection = this.routeSelector.next();
            z3 = true;
        }
        synchronized (this.connectionPool) {
            if (this.canceled) {
                throw new IOException("Canceled");
            }
            if (z3) {
                List<Route> all = this.routeSelection.getAll();
                int size = all.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size) {
                        break;
                    }
                    Route route = all.get(i4);
                    Internal.instance.get(this.connectionPool, this.address, this, route);
                    if (this.connection != null) {
                        realConnection3 = this.connection;
                        this.route = route;
                        z2 = true;
                        break;
                    }
                    i4++;
                }
            }
            if (!z2) {
                if (next == null) {
                    next = this.routeSelection.next();
                }
                this.route = next;
                this.refusedStreamCount = 0;
                realConnection3 = new RealConnection(this.connectionPool, next);
                acquire(realConnection3, false);
            }
        }
        if (!z2) {
            realConnection3.connect(i, i2, i3, z, this.call, this.eventListener);
            routeDatabase().connected(realConnection3.route());
            synchronized (this.connectionPool) {
                this.reportedAcquired = true;
                Internal.instance.put(this.connectionPool, realConnection3);
                if (realConnection3.isMultiplexed()) {
                    socketDeduplicate = Internal.instance.deduplicate(this.connectionPool, this.address, this);
                    realConnection3 = this.connection;
                }
            }
            Util.closeQuietly(socketDeduplicate);
        }
        this.eventListener.connectionAcquired(this.call, realConnection3);
        return realConnection3;
    }

    private RealConnection findHealthyConnection(int i, int i2, int i3, boolean z, boolean z2) throws Throwable {
        while (true) {
            RealConnection realConnectionFindConnection = findConnection(i, i2, i3, z);
            synchronized (this.connectionPool) {
                if (realConnectionFindConnection.successCount == 0) {
                    return realConnectionFindConnection;
                }
                if (realConnectionFindConnection.isHealthy(z2)) {
                    return realConnectionFindConnection;
                }
                noNewStreams();
            }
        }
    }

    private void release(RealConnection realConnection) {
        int size = realConnection.allocations.size();
        for (int i = 0; i < size; i++) {
            if (realConnection.allocations.get(i).get() == this) {
                realConnection.allocations.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    private Socket releaseIfNoNewStreams() {
        RealConnection realConnection = this.connection;
        if (realConnection == null || !realConnection.noNewStreams) {
            return null;
        }
        return deallocate(false, false, true);
    }

    private RouteDatabase routeDatabase() {
        return Internal.instance.routeDatabase(this.connectionPool);
    }

    public void acquire(RealConnection realConnection, boolean z) {
        if (this.connection != null) {
            throw new IllegalStateException();
        }
        this.connection = realConnection;
        this.reportedAcquired = z;
        realConnection.allocations.add(new StreamAllocationReference(this, this.callStackTrace));
    }

    public void cancel() throws IOException {
        HttpCodec httpCodec;
        RealConnection realConnection;
        synchronized (this.connectionPool) {
            this.canceled = true;
            httpCodec = this.codec;
            realConnection = this.connection;
        }
        if (httpCodec != null) {
            httpCodec.cancel();
        } else if (realConnection != null) {
            realConnection.cancel();
        }
    }

    public HttpCodec codec() {
        HttpCodec httpCodec;
        synchronized (this.connectionPool) {
            httpCodec = this.codec;
        }
        return httpCodec;
    }

    public synchronized RealConnection connection() {
        return this.connection;
    }

    public boolean hasMoreRoutes() {
        return this.route != null || (this.routeSelection != null && this.routeSelection.hasNext()) || this.routeSelector.hasNext();
    }

    public HttpCodec newStream(OkHttpClient okHttpClient, Interceptor.Chain chain, boolean z) {
        try {
            HttpCodec httpCodecNewCodec = findHealthyConnection(chain.connectTimeoutMillis(), chain.readTimeoutMillis(), chain.writeTimeoutMillis(), okHttpClient.retryOnConnectionFailure(), z).newCodec(okHttpClient, chain, this);
            synchronized (this.connectionPool) {
                this.codec = httpCodecNewCodec;
            }
            return httpCodecNewCodec;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    public void noNewStreams() throws IOException {
        RealConnection realConnection;
        Socket socketDeallocate;
        synchronized (this.connectionPool) {
            realConnection = this.connection;
            socketDeallocate = deallocate(true, false, false);
            if (this.connection != null) {
                realConnection = null;
            }
        }
        Util.closeQuietly(socketDeallocate);
        if (realConnection != null) {
            this.eventListener.connectionReleased(this.call, realConnection);
        }
    }

    public void release() throws IOException {
        RealConnection realConnection;
        Socket socketDeallocate;
        synchronized (this.connectionPool) {
            realConnection = this.connection;
            socketDeallocate = deallocate(false, true, false);
            if (this.connection != null) {
                realConnection = null;
            }
        }
        Util.closeQuietly(socketDeallocate);
        if (realConnection != null) {
            this.eventListener.connectionReleased(this.call, realConnection);
        }
    }

    public Socket releaseAndAcquire(RealConnection realConnection) {
        if (this.codec != null || this.connection.allocations.size() != 1) {
            throw new IllegalStateException();
        }
        Reference<StreamAllocation> reference = this.connection.allocations.get(0);
        Socket socketDeallocate = deallocate(true, false, false);
        this.connection = realConnection;
        realConnection.allocations.add(reference);
        return socketDeallocate;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0054 A[Catch: all -> 0x0067, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000a, B:8:0x0012, B:9:0x0017, B:11:0x001d, B:29:0x004a, B:31:0x0054, B:34:0x0059, B:26:0x0045, B:14:0x0022, B:16:0x0026, B:18:0x002e, B:20:0x0032, B:22:0x0038, B:25:0x003e), top: B:42:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void streamFailed(java.io.IOException r7) throws java.io.IOException {
        /*
            r6 = this;
            com.bytedance.sdk.adnet.game.ok3.okhttp3.ConnectionPool r0 = r6.connectionPool
            monitor-enter(r0)
            boolean r1 = r7 instanceof com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.StreamResetException     // Catch: java.lang.Throwable -> L67
            r2 = 0
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L22
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.StreamResetException r7 = (com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.StreamResetException) r7     // Catch: java.lang.Throwable -> L67
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.ErrorCode r1 = r7.errorCode     // Catch: java.lang.Throwable -> L67
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.ErrorCode r5 = com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.ErrorCode.REFUSED_STREAM     // Catch: java.lang.Throwable -> L67
            if (r1 != r5) goto L17
            int r1 = r6.refusedStreamCount     // Catch: java.lang.Throwable -> L67
            int r1 = r1 + r4
            r6.refusedStreamCount = r1     // Catch: java.lang.Throwable -> L67
        L17:
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.ErrorCode r7 = r7.errorCode     // Catch: java.lang.Throwable -> L67
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.ErrorCode r1 = com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.ErrorCode.REFUSED_STREAM     // Catch: java.lang.Throwable -> L67
            if (r7 != r1) goto L45
            int r7 = r6.refusedStreamCount     // Catch: java.lang.Throwable -> L67
            if (r7 <= r4) goto L49
            goto L45
        L22:
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RealConnection r1 = r6.connection     // Catch: java.lang.Throwable -> L67
            if (r1 == 0) goto L49
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RealConnection r1 = r6.connection     // Catch: java.lang.Throwable -> L67
            boolean r1 = r1.isMultiplexed()     // Catch: java.lang.Throwable -> L67
            if (r1 == 0) goto L32
            boolean r1 = r7 instanceof com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.ConnectionShutdownException     // Catch: java.lang.Throwable -> L67
            if (r1 == 0) goto L49
        L32:
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RealConnection r1 = r6.connection     // Catch: java.lang.Throwable -> L67
            int r1 = r1.successCount     // Catch: java.lang.Throwable -> L67
            if (r1 != 0) goto L47
            com.bytedance.sdk.adnet.game.ok3.okhttp3.Route r1 = r6.route     // Catch: java.lang.Throwable -> L67
            if (r1 == 0) goto L45
            if (r7 == 0) goto L45
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RouteSelector r1 = r6.routeSelector     // Catch: java.lang.Throwable -> L67
            com.bytedance.sdk.adnet.game.ok3.okhttp3.Route r5 = r6.route     // Catch: java.lang.Throwable -> L67
            r1.connectFailed(r5, r7)     // Catch: java.lang.Throwable -> L67
        L45:
            r6.route = r3     // Catch: java.lang.Throwable -> L67
        L47:
            r7 = 1
            goto L4a
        L49:
            r7 = 0
        L4a:
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RealConnection r1 = r6.connection     // Catch: java.lang.Throwable -> L67
            java.net.Socket r7 = r6.deallocate(r7, r2, r4)     // Catch: java.lang.Throwable -> L67
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RealConnection r2 = r6.connection     // Catch: java.lang.Throwable -> L67
            if (r2 != 0) goto L58
            boolean r2 = r6.reportedAcquired     // Catch: java.lang.Throwable -> L67
            if (r2 != 0) goto L59
        L58:
            r1 = r3
        L59:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L67
            com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.Util.closeQuietly(r7)
            if (r1 == 0) goto L66
            com.bytedance.sdk.adnet.game.ok3.okhttp3.EventListener r7 = r6.eventListener
            com.bytedance.sdk.adnet.game.ok3.okhttp3.Call r0 = r6.call
            r7.connectionReleased(r0, r1)
        L66:
            return
        L67:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L67
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.StreamAllocation.streamFailed(java.io.IOException):void");
    }

    public void streamFinished(boolean z, HttpCodec httpCodec, long j, IOException iOException) {
        RealConnection realConnection;
        Socket socketDeallocate;
        boolean z2;
        this.eventListener.responseBodyEnd(this.call, j);
        synchronized (this.connectionPool) {
            if (httpCodec != null) {
                if (httpCodec == this.codec) {
                    if (!z) {
                        this.connection.successCount++;
                    }
                    realConnection = this.connection;
                    socketDeallocate = deallocate(z, false, true);
                    if (this.connection != null) {
                        realConnection = null;
                    }
                    z2 = this.released;
                }
            }
            throw new IllegalStateException("expected " + this.codec + " but was " + httpCodec);
        }
        Util.closeQuietly(socketDeallocate);
        if (realConnection != null) {
            this.eventListener.connectionReleased(this.call, realConnection);
        }
        if (iOException != null) {
            this.eventListener.callFailed(this.call, iOException);
        } else if (z2) {
            this.eventListener.callEnd(this.call);
        }
    }

    public String toString() {
        RealConnection realConnectionConnection = connection();
        return realConnectionConnection != null ? realConnectionConnection.toString() : this.address.toString();
    }
}
