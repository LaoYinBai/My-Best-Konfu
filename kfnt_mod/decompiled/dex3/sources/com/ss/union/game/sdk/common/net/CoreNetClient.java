package com.ss.union.game.sdk.common.net;

import android.content.Context;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestDownload;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestGet;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestHead;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestTrace;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CoreNetClient {
    public static final ACoreNetClient netClient = new a();

    public static void init(Context context, String str) {
        netClient.init(context, str);
    }

    public static ACoreRequestGet get(String str) {
        return netClient.get(str);
    }

    public static ACoreRequestPost post(String str) {
        return netClient.post(str);
    }

    public static ACoreRequestHead head(String str) {
        return netClient.head(str);
    }

    public static ACoreRequestTrace trace(String str) {
        return netClient.trace(str);
    }

    public static ACoreRequestDownload download(String str) {
        return netClient.download(str);
    }

    public static void cancelAll() {
        netClient.cancelAll();
    }

    public static void cancel(String str) {
        netClient.cancel(str);
    }

    public static void commonHeader(String str, String str2) {
        netClient.commonHeader(str, str2);
    }

    public static Map<String, String> commonHeaders() {
        return netClient.commonHeaders();
    }

    public static void commonParam(String str, String str2) {
        netClient.commonParam(str, str2);
    }

    public static Map<String, String> commonUrlParams() {
        return netClient.commonUrlParams();
    }
}
