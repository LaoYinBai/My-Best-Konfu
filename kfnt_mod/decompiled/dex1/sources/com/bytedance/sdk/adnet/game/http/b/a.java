package com.bytedance.sdk.adnet.game.http.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.Version;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static String f5028a = "";

    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static String a(Context context) {
        String defaultUserAgent;
        if (!TextUtils.isEmpty(f5028a)) {
            return f5028a;
        }
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                try {
                    defaultUserAgent = WebSettings.getDefaultUserAgent(context);
                } catch (Exception unused) {
                }
            } else {
                defaultUserAgent = System.getProperty("http.agent");
            }
            StringBuffer stringBuffer = new StringBuffer();
            int length = defaultUserAgent.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = defaultUserAgent.charAt(i);
                if (cCharAt <= 31 || cCharAt >= 127) {
                    stringBuffer.append(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                } else {
                    stringBuffer.append(cCharAt);
                }
            }
            f5028a = stringBuffer.toString();
            return f5028a;
        } catch (Throwable unused2) {
            f5028a = Version.userAgent();
            return f5028a;
        }
    }

    public static boolean a(boolean z) {
        Context contextA = b.a();
        if (contextA == null) {
            return z;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) contextA.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isAvailable()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return z;
        }
    }
}
