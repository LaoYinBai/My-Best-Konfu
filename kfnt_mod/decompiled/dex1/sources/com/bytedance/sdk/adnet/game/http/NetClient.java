package com.bytedance.sdk.adnet.game.http;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.adnet.game.a.a;
import com.bytedance.sdk.adnet.game.http.a.b;
import com.bytedance.sdk.adnet.game.http.a.c;
import com.bytedance.sdk.adnet.game.http.a.d;
import com.bytedance.sdk.adnet.game.http.cb.ICommonParam;
import com.bytedance.sdk.adnet.game.http.cb.LogCallback;
import com.bytedance.sdk.adnet.game.http.cookie.ICookieStore;
import com.bytedance.sdk.adnet.game.http.req.OkDownloadBuilder;
import com.bytedance.sdk.adnet.game.http.req.OkGetBuilder;
import com.bytedance.sdk.adnet.game.http.req.OkHeadBuilder;
import com.bytedance.sdk.adnet.game.http.req.OkPostBuilder;
import com.bytedance.sdk.adnet.game.http.req.OkTraceBuilder;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Authenticator;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Cache;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Call;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.CertificatePinner;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.ConnectionPool;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.ConnectionSpec;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Cookie;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.CookieJar;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Dispatcher;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Dns;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.EventListener;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.HttpUrl;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Interceptor;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.OkHttpClient;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Protocol;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class NetClient {

    /* renamed from: a, reason: collision with root package name */
    private OkHttpClient f5012a;

    /* renamed from: b, reason: collision with root package name */
    private int f5013b;

    /* renamed from: c, reason: collision with root package name */
    private ICommonParam f5014c;
    private ICommonParam d;

    public static final class NetClientBuilder {

        /* renamed from: a, reason: collision with root package name */
        private OkHttpClient.Builder f5015a;

        /* renamed from: b, reason: collision with root package name */
        private Context f5016b;

        /* renamed from: c, reason: collision with root package name */
        private int f5017c;
        private ICommonParam d;
        private ICommonParam e;
        private d f;
        private b g;

        public NetClientBuilder() {
            this(null);
        }

        public NetClientBuilder(Context context) {
            this.f5017c = 0;
            if (context != null) {
                this.f5016b = context.getApplicationContext();
                com.bytedance.sdk.adnet.game.http.b.b.a(this.f5016b);
            }
            this.f5015a = new OkHttpClient.Builder();
            List<Interceptor> listInterceptors = this.f5015a.interceptors();
            this.f = new d();
            listInterceptors.add(this.f);
            this.g = new b(null);
            listInterceptors.add(this.g);
            listInterceptors.add(new c());
            listInterceptors.add(new com.bytedance.sdk.adnet.game.http.a.a());
        }

        public NetClientBuilder addInterceptor(Interceptor interceptor) {
            if (interceptor != null) {
                this.f5015a.addInterceptor(interceptor);
            }
            return this;
        }

        public NetClientBuilder addNetworkInterceptor(Interceptor interceptor) {
            if (interceptor != null) {
                this.f5015a.addNetworkInterceptor(interceptor);
            }
            return this;
        }

        public NetClientBuilder authenticator(Authenticator authenticator) {
            if (authenticator != null) {
                this.f5015a.authenticator(authenticator);
            }
            return this;
        }

        public void build() {
            NetClient.getInstance().a(this);
        }

        public NetClientBuilder cache(Cache cache) {
            this.f5015a.cache(cache);
            return this;
        }

        public NetClientBuilder certificatePinner(CertificatePinner certificatePinner) {
            if (certificatePinner != null) {
                this.f5015a.certificatePinner(certificatePinner);
            }
            return this;
        }

        public NetClientBuilder commonHeader(ICommonParam iCommonParam) {
            this.d = iCommonParam;
            return this;
        }

        public NetClientBuilder commonParam(ICommonParam iCommonParam) {
            this.e = iCommonParam;
            return this;
        }

        public NetClientBuilder connectTimeout(long j, TimeUnit timeUnit) {
            this.f5015a.connectTimeout(j, timeUnit);
            return this;
        }

        public NetClientBuilder connectionPool(ConnectionPool connectionPool) {
            if (connectionPool != null) {
                this.f5015a.connectionPool(connectionPool);
            }
            return this;
        }

        public NetClientBuilder connectionSpecs(List<ConnectionSpec> list) {
            this.f5015a.connectionSpecs(list);
            return this;
        }

        public NetClientBuilder cookieJar(CookieJar cookieJar) {
            if (cookieJar != null) {
                this.f5015a.cookieJar(cookieJar);
            }
            return this;
        }

        public NetClientBuilder cookieOpen() {
            return this.f5016b != null ? cookieJar(new com.bytedance.sdk.adnet.game.http.cookie.b(new com.bytedance.sdk.adnet.game.http.cookie.a(this.f5016b.getApplicationContext()))) : this;
        }

        public NetClientBuilder dispatcher(Dispatcher dispatcher) {
            if (dispatcher != null) {
                this.f5015a.dispatcher(dispatcher);
            }
            return this;
        }

        public NetClientBuilder dns(Dns dns) {
            if (dns != null) {
                this.f5015a.dns(dns);
            }
            return this;
        }

        public NetClientBuilder eventListener(EventListener eventListener) {
            if (eventListener != null) {
                this.f5015a.eventListener(eventListener);
            }
            return this;
        }

        public NetClientBuilder eventListenerFactory(EventListener.Factory factory) {
            if (factory != null) {
                this.f5015a.eventListenerFactory(factory);
            }
            return this;
        }

        public NetClientBuilder followRedirects(boolean z) {
            this.f5015a.followRedirects(z);
            return this;
        }

        public NetClientBuilder followSslRedirects(boolean z) {
            this.f5015a.followSslRedirects(z);
            return this;
        }

        public NetClientBuilder hostnameVerifier(HostnameVerifier hostnameVerifier) {
            if (hostnameVerifier != null) {
                this.f5015a.hostnameVerifier(hostnameVerifier);
            }
            return this;
        }

        public List<Interceptor> interceptors() {
            return this.f5015a.interceptors();
        }

        public NetClientBuilder log(LogCallback logCallback) {
            if (this.g != null) {
                this.g.a(logCallback);
            }
            return this;
        }

        public List<Interceptor> networkInterceptors() {
            return this.f5015a.networkInterceptors();
        }

        public NetClient newClient() {
            return new NetClient(this);
        }

        public NetClientBuilder pingInterval(long j, TimeUnit timeUnit) {
            this.f5015a.pingInterval(j, timeUnit);
            return this;
        }

        public NetClientBuilder protocols(List<Protocol> list) {
            this.f5015a.protocols(list);
            return this;
        }

        public NetClientBuilder proxy(Proxy proxy) {
            this.f5015a.proxy(proxy);
            return this;
        }

        public NetClientBuilder proxyAuthenticator(Authenticator authenticator) {
            if (authenticator != null) {
                this.f5015a.proxyAuthenticator(authenticator);
            }
            return this;
        }

        public NetClientBuilder proxySelector(ProxySelector proxySelector) {
            this.f5015a.proxySelector(proxySelector);
            return this;
        }

        public NetClientBuilder readTimeout(long j, TimeUnit timeUnit) {
            this.f5015a.readTimeout(j, timeUnit);
            return this;
        }

        public NetClientBuilder retryCount(int i) {
            this.f5017c = i;
            return this;
        }

        public NetClientBuilder retryOnConnectionFailure(boolean z) {
            this.f5015a.retryOnConnectionFailure(z);
            return this;
        }

        public NetClientBuilder socketFactory(SocketFactory socketFactory) {
            if (socketFactory != null) {
                this.f5015a.socketFactory(socketFactory);
            }
            return this;
        }

        public NetClientBuilder sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
            if (sSLSocketFactory != null) {
                this.f5015a.sslSocketFactory(sSLSocketFactory);
            }
            return this;
        }

        public NetClientBuilder sslSocketFactory(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
            if (sSLSocketFactory != null && x509TrustManager != null) {
                this.f5015a.sslSocketFactory(sSLSocketFactory, x509TrustManager);
            }
            return this;
        }

        public NetClientBuilder sslUnsafe() {
            a.C0151a c0151aA = com.bytedance.sdk.adnet.game.a.a.a();
            sslSocketFactory(c0151aA.f5006a, c0151aA.f5007b);
            hostnameVerifier(com.bytedance.sdk.adnet.game.a.c.f5011a);
            return this;
        }

        public NetClientBuilder tnc(Interceptor interceptor) {
            if (this.f != null) {
                this.f.a(interceptor);
            }
            return this;
        }

        public NetClientBuilder writeTimeout(long j, TimeUnit timeUnit) {
            this.f5015a.writeTimeout(j, timeUnit);
            return this;
        }
    }

    private static final class a {

        /* renamed from: a, reason: collision with root package name */
        static final NetClient f5018a = new NetClient();
    }

    private NetClient() {
        this.f5013b = 0;
        a(new NetClientBuilder());
    }

    public NetClient(NetClientBuilder netClientBuilder) {
        this.f5013b = 0;
        a(netClientBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(NetClientBuilder netClientBuilder) {
        this.f5012a = netClientBuilder.f5015a.build();
        this.f5013b = netClientBuilder.f5017c;
        this.f5014c = netClientBuilder.d;
        this.d = netClientBuilder.e;
    }

    public static void cancel(OkHttpClient okHttpClient, String str) {
        Dispatcher dispatcher;
        if (okHttpClient == null || TextUtils.isEmpty(str) || (dispatcher = okHttpClient.dispatcher()) == null) {
            return;
        }
        for (Call call : dispatcher.queuedCalls()) {
            if (str.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call2 : dispatcher.runningCalls()) {
            if (str.equals(call2.request().tag())) {
                call2.cancel();
            }
        }
    }

    public static void cancel(String str) {
        cancel(getInstance().f5012a, str);
    }

    public static void cancelAll() {
        cancelAll(getInstance().f5012a);
    }

    public static void cancelAll(OkHttpClient okHttpClient) {
        if (okHttpClient != null) {
            okHttpClient.dispatcher().cancelAll();
        }
    }

    public static OkDownloadBuilder download() {
        return new OkDownloadBuilder();
    }

    public static OkGetBuilder get() {
        return new OkGetBuilder();
    }

    public static NetClient getInstance() {
        return a.f5018a;
    }

    public static OkHeadBuilder head() {
        return new OkHeadBuilder();
    }

    public static NetClientBuilder init(Context context) {
        return new NetClientBuilder(context);
    }

    public static OkPostBuilder post() {
        return new OkPostBuilder();
    }

    public static OkTraceBuilder trace() {
        return new OkTraceBuilder();
    }

    public Map<String, String> commonHeaders() {
        return this.f5014c == null ? new HashMap() : this.f5014c.commonParam();
    }

    public Map<String, String> commonParams() {
        return this.d == null ? new HashMap() : this.d.commonParam();
    }

    public ICookieStore cookieStore() {
        CookieJar cookieJar = this.f5012a.cookieJar();
        if (cookieJar == null || !(cookieJar instanceof com.bytedance.sdk.adnet.game.http.cookie.b)) {
            return null;
        }
        return ((com.bytedance.sdk.adnet.game.http.cookie.b) cookieJar).a();
    }

    public List<Cookie> cookies(String str) {
        List<Cookie> cookie;
        ArrayList arrayList = new ArrayList();
        ICookieStore iCookieStoreCookieStore = cookieStore();
        if (iCookieStoreCookieStore != null && (cookie = iCookieStoreCookieStore.getCookie(HttpUrl.parse(str))) != null && cookie.size() > 0) {
            arrayList.addAll(cookie);
        }
        return arrayList;
    }

    public OkHttpClient getOkHttpClient() {
        return this.f5012a;
    }

    public int retryCount() {
        return this.f5013b;
    }

    public NetClient setOkHttpClient(OkHttpClient okHttpClient) {
        com.bytedance.sdk.adnet.game.http.b.a.a(okHttpClient, "okHttpClient cannot be null");
        this.f5012a = okHttpClient.newBuilder().addInterceptor(new c()).addInterceptor(new com.bytedance.sdk.adnet.game.http.a.a()).build();
        return this;
    }
}
