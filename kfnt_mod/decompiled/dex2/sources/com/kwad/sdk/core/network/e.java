package com.kwad.sdk.core.network;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.kwad.sdk.api.KsAdSDK;
import com.kwad.sdk.utils.q;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.File;
import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    private static volatile e a;

    private e() {
    }

    public static synchronized e a() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    @WorkerThread
    private void b(String str) throws Throwable {
        try {
            q.a(new File(d()), str, Charset.forName(C.UTF8_NAME), false);
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.b(e);
        }
    }

    @Nullable
    @WorkerThread
    private String c() {
        try {
            return q.a(new File(d()), Charset.forName(C.UTF8_NAME));
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.b(e);
            return null;
        }
    }

    private String d() {
        return com.kwad.sdk.e.a(KsAdSDK.getContext()).getPath() + "/cookie";
    }

    @WorkerThread
    public void a(String str) throws Throwable {
        String strC = c();
        if (strC == null || !strC.equals(str)) {
            com.kwad.sdk.core.d.a.a("CookieStrHelper", "CookieStrHelper saveCookieString newCookieString=" + str);
            b(str);
        }
    }

    @Nullable
    @WorkerThread
    public String b() {
        String strC = c();
        com.kwad.sdk.core.d.a.a("CookieStrHelper", "CookieStrHelper getCookieString cookieString=" + strC);
        return strC;
    }
}
