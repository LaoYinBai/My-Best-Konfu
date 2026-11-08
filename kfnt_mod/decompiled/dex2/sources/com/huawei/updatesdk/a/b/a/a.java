package com.huawei.updatesdk.a.b.a;

import android.content.Context;
import android.net.ConnectivityManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final Object a = new Object();
    private static a b = null;
    private Context c;
    private ConnectivityManager d = null;

    public a(Context context) {
        this.c = context.getApplicationContext();
    }

    public static a a() {
        a aVar;
        synchronized (a) {
            aVar = b;
        }
        return aVar;
    }

    public static void a(Context context) {
        synchronized (a) {
            if (b == null) {
                b = new a(context);
            }
        }
    }

    public Context b() {
        return this.c;
    }

    public String c() {
        return (this.c == null || this.c.getFilesDir() == null) ? "" : this.c.getFilesDir().getAbsolutePath();
    }
}
