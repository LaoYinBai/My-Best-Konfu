package com.alibaba.sdk.android.httpdns.e;

import android.util.Log;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.util.Random;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {
    private String q;

    /* renamed from: com.alibaba.sdk.android.httpdns.e.a$a, reason: collision with other inner class name */
    private static final class C0073a {

        /* renamed from: a, reason: collision with root package name */
        private static final a f2368a = new a();
    }

    private a() {
        try {
            Random random = new Random();
            char[] cArr = new char[12];
            for (int i = 0; i < 12; i++) {
                cArr[i] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".length()));
            }
            this.q = new String(cArr);
        } catch (Exception e) {
            Log.d("SessionTrackMgr", e.getMessage(), e);
        }
    }

    public static a a() {
        return C0073a.f2368a;
    }

    public String getSessionId() {
        return this.q;
    }

    public String h() {
        return com.alibaba.sdk.android.httpdns.c.a.a().h();
    }

    public String k() {
        int networkType = com.alibaba.sdk.android.httpdns.c.a.a().getNetworkType();
        if (networkType == 255) {
            return "unknown";
        }
        switch (networkType) {
            case 0:
                return "unknown";
            case 1:
                return OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR;
            case 2:
                return "2g";
            case 3:
                return "3g";
            case 4:
                return "4g";
            default:
                return "unknown";
        }
    }
}
