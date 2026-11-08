package com.cmic.sso.sdk.d;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.util.Log;
import anet.channel.util.HttpConstant;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class t {

    /* renamed from: a, reason: collision with root package name */
    private static t f5471a;

    /* renamed from: b, reason: collision with root package name */
    private ConnectivityManager f5472b;

    /* renamed from: c, reason: collision with root package name */
    private Network f5473c;
    private ConnectivityManager.NetworkCallback d;
    private boolean e;
    private volatile boolean f = false;

    public interface a {
        void a(Network network);
    }

    private t(Context context) {
        this.f5472b = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public boolean a() {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.f5473c != null;
        }
        return this.f;
    }

    public static t a(Context context) {
        if (f5471a == null) {
            synchronized (t.class) {
                if (f5471a == null) {
                    f5471a = new t(context);
                }
            }
        }
        return f5471a;
    }

    @TargetApi(21)
    public void a(final a aVar) {
        NetworkInfo networkInfo;
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (this.f5473c != null && !this.e && (networkInfo = this.f5472b.getNetworkInfo(this.f5473c)) != null && networkInfo.isAvailable()) {
            Log.e("HttpUtils", "reuse network: ");
            aVar.a(this.f5473c);
            return;
        }
        if (this.d != null) {
            try {
                this.f5472b.unregisterNetworkCallback(this.d);
            } catch (Exception e) {
                e.printStackTrace();
                this.d = null;
            }
            Log.e("HttpUtils", "clear: ");
        }
        NetworkRequest networkRequestBuild = new NetworkRequest.Builder().addCapability(12).addTransportType(0).build();
        this.d = new ConnectivityManager.NetworkCallback() { // from class: com.cmic.sso.sdk.d.t.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                t.this.f5473c = network;
                aVar.a(network);
                t.this.e = false;
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                t.this.e = true;
            }
        };
        this.f5472b.requestNetwork(networkRequestBuild, this.d);
    }

    public void b() {
        try {
            if (Build.VERSION.SDK_INT < 21) {
                this.f = false;
                this.f5472b.stopUsingNetworkFeature(0, "enableHIPRI");
            } else {
                if (this.f5472b != null && this.d != null) {
                    this.f5472b.unregisterNetworkCallback(this.d);
                    this.d = null;
                    this.f5473c = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int b(String str) throws UnknownHostException {
        try {
            InetAddress byName = InetAddress.getByName(str);
            if (byName != null) {
                c.b("WifiNetworkUtils", "lookupHost inetAddress " + byName.toString());
            }
            byte[] address = byName.getAddress();
            return (address[0] & 255) | ((address[3] & 255) << 24) | ((address[2] & 255) << 16) | ((address[1] & 255) << 8);
        } catch (UnknownHostException unused) {
            return -1;
        }
    }

    private static String c(String str) {
        int iIndexOf = str.indexOf(HttpConstant.SCHEME_SPLIT);
        if (iIndexOf > 0) {
            str = str.substring(iIndexOf + 3);
        }
        int iIndexOf2 = str.indexOf(58);
        if (iIndexOf2 >= 0) {
            str = str.substring(0, iIndexOf2);
        }
        int iIndexOf3 = str.indexOf(47);
        if (iIndexOf3 >= 0) {
            str = str.substring(0, iIndexOf3);
        }
        int iIndexOf4 = str.indexOf(63);
        return iIndexOf4 >= 0 ? str.substring(0, iIndexOf4) : str;
    }

    public boolean a(String str) throws InterruptedException, UnknownHostException {
        this.f5472b.startUsingNetworkFeature(0, "enableHIPRI");
        for (int i = 0; i < 30; i++) {
            try {
                if (this.f5472b.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                    break;
                }
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                com.cmic.sso.sdk.c.a.f5440a.add(e);
                c.a("WifiNetworkUtils", "check hipri failed");
            }
        }
        this.f = this.f5472b.requestRouteToHost(5, b(c(str)));
        c.a("WifiNetworkUtils", "切换数据网络结果 >>> " + this.f);
        return this.f;
    }
}
