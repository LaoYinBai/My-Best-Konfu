package com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http;

import anet.channel.request.Request;
import anet.channel.util.HttpConstant;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Address;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Call;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.CertificatePinner;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.EventListener;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.HttpUrl;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Interceptor;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.OkHttpClient;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Response;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Route;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.Util;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RealConnection;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RouteException;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.StreamAllocation;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.ConnectionShutdownException;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class RetryAndFollowUpInterceptor implements Interceptor {
    private static final int MAX_FOLLOW_UPS = 20;
    private Object callStackTrace;
    private volatile boolean canceled;
    private final OkHttpClient client;
    private final boolean forWebSocket;
    private StreamAllocation streamAllocation;

    public RetryAndFollowUpInterceptor(OkHttpClient okHttpClient, boolean z) {
        this.client = okHttpClient;
        this.forWebSocket = z;
    }

    private Address createAddress(HttpUrl httpUrl) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifier;
        CertificatePinner certificatePinner;
        if (httpUrl.isHttps()) {
            SSLSocketFactory sslSocketFactory = this.client.sslSocketFactory();
            hostnameVerifier = this.client.hostnameVerifier();
            sSLSocketFactory = sslSocketFactory;
            certificatePinner = this.client.certificatePinner();
        } else {
            sSLSocketFactory = null;
            hostnameVerifier = null;
            certificatePinner = null;
        }
        return new Address(httpUrl.host(), httpUrl.port(), this.client.dns(), this.client.socketFactory(), sSLSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
    }

    private Request followUpRequest(Response response) throws ProtocolException {
        String strHeader;
        HttpUrl httpUrlResolve;
        if (response == null) {
            throw new IllegalStateException();
        }
        RealConnection realConnectionConnection = this.streamAllocation.connection();
        RequestBody requestBodyBody = null;
        Route route = realConnectionConnection != null ? realConnectionConnection.route() : null;
        int iCode = response.code();
        String strMethod = response.request().method();
        switch (iCode) {
            case TTAdConstant.DEFAULT_LIVE_SHOW_TIME_MAX /* 300 */:
            case 301:
            case 302:
            case 303:
                break;
            case 307:
            case 308:
                if (!strMethod.equals("GET") && !strMethod.equals(Request.Method.HEAD)) {
                    return null;
                }
                break;
            case 401:
                return this.client.authenticator().authenticate(route, response);
            case 407:
                if ((route != null ? route.proxy() : this.client.proxy()).type() == Proxy.Type.HTTP) {
                    return this.client.proxyAuthenticator().authenticate(route, response);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            case 408:
                if (!this.client.retryOnConnectionFailure() || (response.request().body() instanceof UnrepeatableRequestBody)) {
                    return null;
                }
                if (response.priorResponse() == null || response.priorResponse().code() != 408) {
                    return response.request();
                }
                return null;
            default:
                return null;
        }
        if (!this.client.followRedirects() || (strHeader = response.header("Location")) == null || (httpUrlResolve = response.request().url().resolve(strHeader)) == null) {
            return null;
        }
        if (!httpUrlResolve.scheme().equals(response.request().url().scheme()) && !this.client.followSslRedirects()) {
            return null;
        }
        Request.Builder builderNewBuilder = response.request().newBuilder();
        if (HttpMethod.permitsRequestBody(strMethod)) {
            boolean zRedirectsWithBody = HttpMethod.redirectsWithBody(strMethod);
            if (HttpMethod.redirectsToGet(strMethod)) {
                strMethod = "GET";
            } else if (zRedirectsWithBody) {
                requestBodyBody = response.request().body();
            }
            builderNewBuilder.method(strMethod, requestBodyBody);
            if (!zRedirectsWithBody) {
                builderNewBuilder.removeHeader("Transfer-Encoding");
                builderNewBuilder.removeHeader("Content-Length");
                builderNewBuilder.removeHeader("Content-Type");
            }
        }
        if (!sameConnection(response, httpUrlResolve)) {
            builderNewBuilder.removeHeader(HttpConstant.AUTHORIZATION);
        }
        return builderNewBuilder.url(httpUrlResolve).build();
    }

    private boolean isRecoverable(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? (iOException instanceof SocketTimeoutException) && !z : (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private boolean recover(IOException iOException, boolean z, com.bytedance.sdk.adnet.game.ok3.okhttp3.Request request) throws IOException {
        this.streamAllocation.streamFailed(iOException);
        if (this.client.retryOnConnectionFailure()) {
            return !(z && (request.body() instanceof UnrepeatableRequestBody)) && isRecoverable(iOException, z) && this.streamAllocation.hasMoreRoutes();
        }
        return false;
    }

    private boolean sameConnection(Response response, HttpUrl httpUrl) {
        HttpUrl httpUrlUrl = response.request().url();
        return httpUrlUrl.host().equals(httpUrl.host()) && httpUrlUrl.port() == httpUrl.port() && httpUrlUrl.scheme().equals(httpUrl.scheme());
    }

    public void cancel() throws IOException {
        this.canceled = true;
        StreamAllocation streamAllocation = this.streamAllocation;
        if (streamAllocation != null) {
            streamAllocation.cancel();
        }
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response responseBuild;
        com.bytedance.sdk.adnet.game.ok3.okhttp3.Request requestFollowUpRequest;
        com.bytedance.sdk.adnet.game.ok3.okhttp3.Request request = chain.request();
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Call call = realInterceptorChain.call();
        EventListener eventListener = realInterceptorChain.eventListener();
        this.streamAllocation = new StreamAllocation(this.client.connectionPool(), createAddress(request.url()), call, eventListener, this.callStackTrace);
        Response response = null;
        int i = 0;
        while (!this.canceled) {
            try {
                try {
                    Response responseProceed = realInterceptorChain.proceed(request, this.streamAllocation, null, null);
                    responseBuild = response != null ? responseProceed.newBuilder().priorResponse(response.newBuilder().body(null).build()).build() : responseProceed;
                    requestFollowUpRequest = followUpRequest(responseBuild);
                } catch (RouteException e) {
                    if (!recover(e.getLastConnectException(), false, request)) {
                        throw e.getLastConnectException();
                    }
                } catch (IOException e2) {
                    if (!recover(e2, !(e2 instanceof ConnectionShutdownException), request)) {
                        throw e2;
                    }
                }
                if (requestFollowUpRequest == null) {
                    if (!this.forWebSocket) {
                        this.streamAllocation.release();
                    }
                    return responseBuild;
                }
                Util.closeQuietly(responseBuild.body());
                int i2 = i + 1;
                if (i2 > 20) {
                    this.streamAllocation.release();
                    throw new ProtocolException("Too many follow-up requests: " + i2);
                }
                if (requestFollowUpRequest.body() instanceof UnrepeatableRequestBody) {
                    this.streamAllocation.release();
                    throw new HttpRetryException("Cannot retry streamed HTTP body", responseBuild.code());
                }
                if (!sameConnection(responseBuild, requestFollowUpRequest.url())) {
                    this.streamAllocation.release();
                    this.streamAllocation = new StreamAllocation(this.client.connectionPool(), createAddress(requestFollowUpRequest.url()), call, eventListener, this.callStackTrace);
                } else if (this.streamAllocation.codec() != null) {
                    throw new IllegalStateException("Closing the body of " + responseBuild + " didn't close its backing stream. Bad interceptor?");
                }
                response = responseBuild;
                request = requestFollowUpRequest;
                i = i2;
            } catch (Throwable th) {
                this.streamAllocation.streamFailed(null);
                this.streamAllocation.release();
                throw th;
            }
        }
        this.streamAllocation.release();
        throw new IOException("Canceled");
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public void setCallStackTrace(Object obj) {
        this.callStackTrace = obj;
    }

    public StreamAllocation streamAllocation() {
        return this.streamAllocation;
    }
}
