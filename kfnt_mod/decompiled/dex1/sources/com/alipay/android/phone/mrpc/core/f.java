package com.alipay.android.phone.mrpc.core;

import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class f implements ConnectionKeepAliveStrategy {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ d f2452a;

    f(d dVar) {
        this.f2452a = dVar;
    }

    @Override // org.apache.http.conn.ConnectionKeepAliveStrategy
    public final long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        return 180000L;
    }
}
