package com.huawei.hms.update.b;

import android.content.Context;
import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    public static void a(HttpsURLConnection httpsURLConnection, Context context) {
        SocketFactory socketFactoryA = f.a(context);
        if (socketFactoryA instanceof SSLSocketFactory) {
            httpsURLConnection.setSSLSocketFactory((SSLSocketFactory) socketFactoryA);
        }
    }
}
