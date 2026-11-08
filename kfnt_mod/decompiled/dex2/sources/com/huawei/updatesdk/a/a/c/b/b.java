package com.huawei.updatesdk.a.a.c.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.net.InetSocketAddress;
import java.net.Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static String a = "NetworkUtil";
    private static int b = -1;
    private static int c = -1;
    private static Proxy d;

    private static int a(NetworkInfo networkInfo) {
        if (networkInfo != null && networkInfo.isConnected()) {
            int type = networkInfo.getType();
            if (1 == type || 13 == type) {
                return 1;
            }
            if (type == 0) {
                switch (networkInfo.getSubtype()) {
                }
                return 1;
            }
        }
        return 0;
    }

    public static Proxy a() {
        return d;
    }

    private static void a(int i) {
        c = i;
    }

    private static void a(Proxy proxy) {
        d = proxy;
    }

    public static boolean a(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        return (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) ? false : true;
    }

    private static boolean a(NetworkInfo networkInfo, Context context) throws Throwable {
        int type = networkInfo.getType();
        boolean zB = a.b(context);
        if (type != 0 || !zB) {
            return false;
        }
        String host = android.net.Proxy.getHost(context);
        int port = android.net.Proxy.getPort(context);
        Proxy proxy = null;
        if (host != null && host.length() > 0 && port != -1) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
        }
        a(proxy);
        return true;
    }

    public static boolean b(Context context) {
        if (-1 == c) {
            c(context);
        }
        return c == -3;
    }

    public static int c(Context context) {
        if (-1 == b) {
            com.huawei.updatesdk.a.a.b.a.a.a.a(a, "getPsType() need init");
            if (context != null) {
                e(context);
            }
        }
        return b;
    }

    public static Proxy d(Context context) {
        if (b(context)) {
            return a();
        }
        return null;
    }

    private static void e(Context context) {
        a(0);
        NetworkInfo networkInfoF = f(context);
        if (networkInfoF == null) {
            com.huawei.updatesdk.a.a.b.a.a.a.a(a, "setPsType() info = null");
            return;
        }
        b = a(networkInfoF);
        if (1 != b) {
            a(a(networkInfoF, context) ? -3 : -2);
        }
    }

    private static NetworkInfo f(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }
}
