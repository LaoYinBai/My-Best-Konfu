package com.bytedance.sdk.adnet.game.ok3.okhttp3.internal;

import com.bytedance.sdk.adnet.game.ok3.okhttp3.Address;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Call;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.ConnectionPool;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.ConnectionSpec;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Headers;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.HttpUrl;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.OkHttpClient;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Response;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Route;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.cache.InternalCache;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RealConnection;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.RouteDatabase;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.connection.StreamAllocation;
import java.net.Socket;
import javax.net.ssl.SSLSocket;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class Internal {
    public static Internal instance;

    public static void initializeInstanceForTests() {
        new OkHttpClient();
    }

    public abstract void addLenient(Headers.Builder builder, String str);

    public abstract void addLenient(Headers.Builder builder, String str, String str2);

    public abstract void apply(ConnectionSpec connectionSpec, SSLSocket sSLSocket, boolean z);

    public abstract int code(Response.Builder builder);

    public abstract boolean connectionBecameIdle(ConnectionPool connectionPool, RealConnection realConnection);

    public abstract Socket deduplicate(ConnectionPool connectionPool, Address address, StreamAllocation streamAllocation);

    public abstract boolean equalsNonHost(Address address, Address address2);

    public abstract RealConnection get(ConnectionPool connectionPool, Address address, StreamAllocation streamAllocation, Route route);

    public abstract HttpUrl getHttpUrlChecked(String str);

    public abstract Call newWebSocketCall(OkHttpClient okHttpClient, Request request);

    public abstract void put(ConnectionPool connectionPool, RealConnection realConnection);

    public abstract RouteDatabase routeDatabase(ConnectionPool connectionPool);

    public abstract void setCache(OkHttpClient.Builder builder, InternalCache internalCache);

    public abstract StreamAllocation streamAllocation(Call call);
}
