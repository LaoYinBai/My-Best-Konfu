package com.bytedance.sdk.adnet.game.ok3.okhttp3;

import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.NamedRunnable;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.cache.CacheInterceptor;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.ConnectInterceptor;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.StreamAllocation;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http.BridgeInterceptor;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http.CallServerInterceptor;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http.RealInterceptorChain;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http.RetryAndFollowUpInterceptor;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.platform.Platform;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class RealCall implements Call {
    final OkHttpClient client;
    private EventListener eventListener;
    private boolean executed;
    final boolean forWebSocket;
    final Request originalRequest;
    final RetryAndFollowUpInterceptor retryAndFollowUpInterceptor;

    final class AsyncCall extends NamedRunnable {
        private final Callback responseCallback;

        AsyncCall(Callback callback) {
            super("OkHttp %s", RealCall.this.redactedUrl());
            this.responseCallback = callback;
        }

        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.NamedRunnable
        protected void execute() {
            IOException e;
            Response responseWithInterceptorChain;
            boolean z = true;
            try {
                try {
                    responseWithInterceptorChain = RealCall.this.getResponseWithInterceptorChain();
                } catch (IOException e2) {
                    e = e2;
                    z = false;
                }
                try {
                    if (RealCall.this.retryAndFollowUpInterceptor.isCanceled()) {
                        this.responseCallback.onFailure(RealCall.this, new IOException("Canceled"));
                    } else {
                        this.responseCallback.onResponse(RealCall.this, responseWithInterceptorChain);
                    }
                } catch (IOException e3) {
                    e = e3;
                    if (z) {
                        Platform.get().log(4, "Callback failure for " + RealCall.this.toLoggableString(), e);
                    } else {
                        RealCall.this.eventListener.callFailed(RealCall.this, e);
                        this.responseCallback.onFailure(RealCall.this, e);
                    }
                }
            } finally {
                RealCall.this.client.dispatcher().finished(this);
            }
        }

        RealCall get() {
            return RealCall.this;
        }

        String host() {
            return RealCall.this.originalRequest.url().host();
        }

        Request request() {
            return RealCall.this.originalRequest;
        }
    }

    private RealCall(OkHttpClient okHttpClient, Request request, boolean z) {
        this.client = okHttpClient;
        this.originalRequest = request;
        this.forWebSocket = z;
        this.retryAndFollowUpInterceptor = new RetryAndFollowUpInterceptor(okHttpClient, z);
    }

    private void captureCallStackTrace() {
        this.retryAndFollowUpInterceptor.setCallStackTrace(Platform.get().getStackTraceForCloseable("response.body().close()"));
    }

    static RealCall newRealCall(OkHttpClient okHttpClient, Request request, boolean z) {
        RealCall realCall = new RealCall(okHttpClient, request, z);
        realCall.eventListener = okHttpClient.eventListenerFactory().create(realCall);
        return realCall;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Call
    public void cancel() throws IOException {
        this.retryAndFollowUpInterceptor.cancel();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Call
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public RealCall m252clone() {
        return newRealCall(this.client, this.originalRequest, this.forWebSocket);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Call
    public void enqueue(Callback callback) {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        captureCallStackTrace();
        this.eventListener.callStart(this);
        this.client.dispatcher().enqueue(new AsyncCall(callback));
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Call
    public Response execute() {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        captureCallStackTrace();
        this.eventListener.callStart(this);
        try {
            try {
                this.client.dispatcher().executed(this);
                Response responseWithInterceptorChain = getResponseWithInterceptorChain();
                if (responseWithInterceptorChain != null) {
                    return responseWithInterceptorChain;
                }
                throw new IOException("Canceled");
            } catch (IOException e) {
                this.eventListener.callFailed(this, e);
                throw e;
            }
        } finally {
            this.client.dispatcher().finished(this);
        }
    }

    Response getResponseWithInterceptorChain() {
        ArrayList arrayList = new ArrayList(this.client.interceptors());
        arrayList.add(this.retryAndFollowUpInterceptor);
        arrayList.add(new BridgeInterceptor(this.client.cookieJar()));
        arrayList.add(new CacheInterceptor(this.client.internalCache()));
        arrayList.add(new ConnectInterceptor(this.client));
        if (!this.forWebSocket) {
            arrayList.addAll(this.client.networkInterceptors());
        }
        arrayList.add(new CallServerInterceptor(this.forWebSocket));
        return new RealInterceptorChain(arrayList, null, null, null, 0, this.originalRequest, this, this.eventListener, this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis()).proceed(this.originalRequest);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Call
    public boolean isCanceled() {
        return this.retryAndFollowUpInterceptor.isCanceled();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Call
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    String redactedUrl() {
        return this.originalRequest.url().redact();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Call
    public Request request() {
        return this.originalRequest;
    }

    StreamAllocation streamAllocation() {
        return this.retryAndFollowUpInterceptor.streamAllocation();
    }

    String toLoggableString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isCanceled() ? "canceled " : "");
        sb.append(this.forWebSocket ? "web socket" : "call");
        sb.append(" to ");
        sb.append(redactedUrl());
        return sb.toString();
    }
}
