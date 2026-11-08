package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bx {

    /* renamed from: a, reason: collision with root package name */
    private static volatile bx f3193a;

    private bx() {
    }

    public static bx a() {
        if (f3193a == null) {
            synchronized (bx.class) {
                if (f3193a == null) {
                    f3193a = new bx();
                }
            }
        }
        return f3193a;
    }

    public Boolean a(Context context) {
        return a(context, 1);
    }

    public Boolean b(Context context) {
        return a(context, 0);
    }

    private Boolean a(Context context, int i) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            boolean z = true;
            if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
                bb.a().c("Utils", "no permission android.permission.ACCESS_NETWORK_STATE");
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || activeNetworkInfo.getType() != i || !activeNetworkInfo.isConnected()) {
                z = false;
            }
            return Boolean.valueOf(z);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean c(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isAvailable();
            }
            return false;
        } catch (Exception e) {
            bb.a().a("isCurrentNetworkAvailable", e);
            return false;
        }
    }
}
