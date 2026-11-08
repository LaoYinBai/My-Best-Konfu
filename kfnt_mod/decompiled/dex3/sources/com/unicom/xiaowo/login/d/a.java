package com.unicom.xiaowo.login.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.text.TextUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static volatile a a;
    private ConnectivityManager.NetworkCallback b = null;
    private ConnectivityManager c = null;
    private volatile d d = null;
    private ExecutorService e = null;

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    static /* synthetic */ d a(a aVar, d dVar) {
        aVar.d = null;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Context context, String str) throws InterruptedException {
        try {
            this.c = (ConnectivityManager) context.getSystemService("connectivity");
            if (this.c == null) {
                return false;
            }
            NetworkInfo.State state = this.c.getNetworkInfo(5).getState();
            if (state.compareTo(NetworkInfo.State.CONNECTED) != 0 && state.compareTo(NetworkInfo.State.CONNECTING) != 0) {
                int iStartUsingNetworkFeature = this.c.startUsingNetworkFeature(0, "enableHIPRI");
                if (-1 == iStartUsingNetworkFeature) {
                    return false;
                }
                if (iStartUsingNetworkFeature == 0) {
                    return true;
                }
                String strB = h.b(str);
                if (TextUtils.isEmpty(strB)) {
                    strB = str;
                }
                int iC = h.c(strB);
                if (-1 == iC) {
                    return false;
                }
                for (int i = 0; i < 3; i++) {
                    try {
                        if (this.c.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                            break;
                        }
                        Thread.sleep(1000L);
                    } catch (InterruptedException unused) {
                    }
                }
                return this.c.requestRouteToHost(5, iC);
            }
            return true;
        } catch (Exception unused2) {
            return false;
        }
    }

    public final void a(Context context, String str, e eVar) {
        if (Build.VERSION.SDK_INT < 21) {
            if (this.e != null) {
                this.e.shutdownNow();
            }
            this.e = Executors.newSingleThreadExecutor();
            this.e.submit(new b(this, context, str, eVar));
            return;
        }
        try {
            this.c = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            builder.addTransportType(0);
            builder.addCapability(12);
            NetworkRequest networkRequestBuild = builder.build();
            this.b = new c(this, eVar);
            if (this.d != null) {
                this.d.cancel();
                this.d = null;
            }
            this.d = new d(this, eVar);
            this.d.start();
            this.c.requestNetwork(networkRequestBuild, this.b);
        } catch (Exception e) {
            e.printStackTrace();
            if (eVar != null) {
                eVar.a(false, null);
            }
        }
    }

    public final void b() {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.c != null) {
                    if (this.b != null) {
                        this.c.unregisterNetworkCallback(this.b);
                        this.b = null;
                    }
                    this.c = null;
                }
            } else if (this.c != null) {
                this.c = null;
            }
            if (this.d != null) {
                this.d.cancel();
                this.d = null;
            }
            if (this.e != null) {
                this.e.shutdownNow();
                this.e = null;
            }
        } catch (Exception unused) {
        }
    }
}
