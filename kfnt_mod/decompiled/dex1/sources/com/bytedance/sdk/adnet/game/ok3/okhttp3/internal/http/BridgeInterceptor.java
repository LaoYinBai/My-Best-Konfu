package com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http;

import anet.channel.util.HttpConstant;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Cookie;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.CookieJar;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Interceptor;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.MediaType;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Response;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.Util;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.Version;
import com.bytedance.sdk.adnet.game.ok3.okio.GzipSource;
import com.bytedance.sdk.adnet.game.ok3.okio.Okio;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class BridgeInterceptor implements Interceptor {
    private final CookieJar cookieJar;

    public BridgeInterceptor(CookieJar cookieJar) {
        this.cookieJar = cookieJar;
    }

    private String cookieHeader(List<Cookie> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            Cookie cookie = list.get(i);
            sb.append(cookie.name());
            sb.append('=');
            sb.append(cookie.value());
        }
        return sb.toString();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        String str;
        Request request = chain.request();
        Request.Builder builderNewBuilder = request.newBuilder();
        RequestBody requestBodyBody = request.body();
        if (requestBodyBody != null) {
            MediaType mediaTypeContentType = requestBodyBody.contentType();
            if (mediaTypeContentType != null) {
                builderNewBuilder.header("Content-Type", mediaTypeContentType.toString());
            }
            long jContentLength = requestBodyBody.contentLength();
            if (jContentLength != -1) {
                builderNewBuilder.header("Content-Length", Long.toString(jContentLength));
                str = "Transfer-Encoding";
            } else {
                builderNewBuilder.header("Transfer-Encoding", "chunked");
                str = "Content-Length";
            }
            builderNewBuilder.removeHeader(str);
        }
        boolean z = false;
        if (request.header(HttpConstant.HOST) == null) {
            builderNewBuilder.header(HttpConstant.HOST, Util.hostHeader(request.url(), false));
        }
        if (request.header("Connection") == null) {
            builderNewBuilder.header("Connection", "Keep-Alive");
        }
        if (request.header(HttpConstant.ACCEPT_ENCODING) == null && request.header("Range") == null) {
            z = true;
            builderNewBuilder.header(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
        }
        List<Cookie> listLoadForRequest = this.cookieJar.loadForRequest(request.url());
        if (!listLoadForRequest.isEmpty()) {
            builderNewBuilder.header(HttpConstant.COOKIE, cookieHeader(listLoadForRequest));
        }
        if (request.header("User-Agent") == null) {
            builderNewBuilder.header("User-Agent", Version.userAgent());
        }
        Response responseProceed = chain.proceed(builderNewBuilder.build());
        HttpHeaders.receiveHeaders(this.cookieJar, request.url(), responseProceed.headers());
        Response.Builder builderRequest = responseProceed.newBuilder().request(request);
        if (z && HttpConstant.GZIP.equalsIgnoreCase(responseProceed.header("Content-Encoding")) && HttpHeaders.hasBody(responseProceed)) {
            GzipSource gzipSource = new GzipSource(responseProceed.body().source());
            builderRequest.headers(responseProceed.headers().newBuilder().removeAll("Content-Encoding").removeAll("Content-Length").build());
            builderRequest.body(new RealResponseBody(responseProceed.header("Content-Type"), -1L, Okio.buffer(gzipSource)));
        }
        return builderRequest.build();
    }
}
