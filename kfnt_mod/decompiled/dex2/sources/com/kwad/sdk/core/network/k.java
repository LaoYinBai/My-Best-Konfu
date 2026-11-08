package com.kwad.sdk.core.network;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.utils.as;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.net.URLEncoder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class k {
    private static String a = "";
    private static String b = "";

    public static String a() {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        a = System.getProperty("http.agent");
        if (TextUtils.isEmpty(a)) {
            return a;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int length = a.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = a.charAt(i);
            if (cCharAt <= 31 || cCharAt >= 127) {
                stringBuffer.append(String.format("\\u%04x", Integer.valueOf(cCharAt)));
            } else {
                stringBuffer.append(cCharAt);
            }
        }
        a = stringBuffer.toString();
        return a;
    }

    public static String b() {
        return a() + "-ksad-android-3.3.17";
    }

    public static String c() {
        return com.kwad.sdk.core.config.b.an();
    }

    public static String d() {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        Context context = KsAdSDKImpl.get().getContext();
        if (context == null) {
            return "";
        }
        try {
            b = as.f(context);
        } catch (Exception unused) {
        }
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        b = Build.VERSION.SDK_INT >= 17 ? WebSettings.getDefaultUserAgent(context) : System.getProperty("http.agent");
        b = URLEncoder.encode(b, C.UTF8_NAME);
        as.j(context, b);
        return b;
    }
}
