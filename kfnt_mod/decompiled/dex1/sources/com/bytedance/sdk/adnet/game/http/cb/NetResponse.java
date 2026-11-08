package com.bytedance.sdk.adnet.game.http.cb;

import android.text.TextUtils;
import com.bytedance.sdk.adnet.game.http.req.base.NetBuilder;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class NetResponse<T, R extends NetBuilder> {
    public int code;
    public T data;
    public Throwable error;
    public Map<String, String> headers;
    public String httpBody;
    public int httpCode;
    public String httpMessage;
    public String message;
    public R request;

    private NetResponse(T t, int i, String str, String str2, int i2, String str3, Throwable th, Map<String, String> map, R r) {
        this.data = t;
        this.code = i2;
        this.message = str3;
        this.httpCode = i;
        this.httpMessage = str;
        this.httpBody = str2;
        this.error = th;
        this.headers = map;
        this.request = r;
    }

    public static <R extends NetBuilder> NetResponse error(int i, String str, String str2, int i2, String str3, Throwable th, Map<String, String> map, R r) {
        return new NetResponse(null, i, str, str2, i2, str3, th, map, r);
    }

    public static <T, R extends NetBuilder> NetResponse success(int i, String str, String str2, int i2, String str3, T t, Map<String, String> map, R r) {
        return new NetResponse(t, i, str, str2, i2, str3, null, map, r);
    }

    public String header(String str) {
        if (TextUtils.isEmpty(str) || this.headers == null) {
            return null;
        }
        return this.headers.get(str);
    }
}
