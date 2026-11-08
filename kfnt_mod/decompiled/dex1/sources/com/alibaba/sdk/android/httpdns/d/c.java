package com.alibaba.sdk.android.httpdns.d;

import android.text.TextUtils;
import com.alibaba.sdk.android.httpdns.h;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import java.net.SocketTimeoutException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {
    public static int a(Throwable th) {
        if (th instanceof h) {
            return ((h) th).getErrorCode();
        }
        if (th instanceof SocketTimeoutException) {
            return 10001;
        }
        return NetCallback.ERROR_CODE_REQUEST_SUCCESS;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static String m22a(Throwable th) {
        return (th == null || TextUtils.isEmpty(th.getMessage())) ? th instanceof SocketTimeoutException ? "time out exception" : "default error" : th.getMessage();
    }

    public static int b() {
        return 0;
    }
}
