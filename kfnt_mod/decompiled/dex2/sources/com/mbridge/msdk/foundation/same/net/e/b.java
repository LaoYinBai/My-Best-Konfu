package com.mbridge.msdk.foundation.same.net.e;

import com.mbridge.msdk.thrid.okhttp.ConnectionPool;
import com.mbridge.msdk.thrid.okhttp.Dispatcher;
import com.mbridge.msdk.thrid.okhttp.EventListener;
import com.mbridge.msdk.thrid.okhttp.OkHttpClient;
import java.util.concurrent.TimeUnit;

/* compiled from: OKHTTPClientManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    private OkHttpClient a;

    private b() {
        this.a = null;
    }

    public final synchronized OkHttpClient a() {
        if (this.a == null) {
            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequestsPerHost(10);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(100L, TimeUnit.SECONDS);
            builder.connectTimeout(60L, TimeUnit.SECONDS);
            builder.writeTimeout(60L, TimeUnit.SECONDS);
            builder.connectionPool(new ConnectionPool(32, 5L, TimeUnit.MINUTES));
            builder.dispatcher(dispatcher);
            builder.eventListener(new EventListener() { // from class: com.mbridge.msdk.foundation.same.net.e.b.1
            });
            this.a = builder.build();
        }
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: OKHTTPClientManager.java */
    static final class a {
        private static final b a = new b();
    }
}
