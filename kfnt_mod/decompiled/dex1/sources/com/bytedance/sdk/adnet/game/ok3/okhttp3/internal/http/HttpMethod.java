package com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http;

import anet.channel.request.Request;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class HttpMethod {
    private HttpMethod() {
    }

    public static boolean invalidatesCache(String str) {
        return str.equals("POST") || str.equals("PATCH") || str.equals(Request.Method.PUT) || str.equals(Request.Method.DELETE) || str.equals("MOVE");
    }

    public static boolean permitsRequestBody(String str) {
        return requiresRequestBody(str) || str.equals(Request.Method.OPTION) || str.equals(Request.Method.DELETE) || str.equals("PROPFIND") || str.equals("MKCOL") || str.equals("LOCK");
    }

    public static boolean redirectsToGet(String str) {
        return !str.equals("PROPFIND");
    }

    public static boolean redirectsWithBody(String str) {
        return str.equals("PROPFIND");
    }

    public static boolean requiresRequestBody(String str) {
        return str.equals("POST") || str.equals(Request.Method.PUT) || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT");
    }
}
