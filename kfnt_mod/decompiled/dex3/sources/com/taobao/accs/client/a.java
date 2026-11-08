package com.taobao.accs.client;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import com.taobao.accs.IProcessName;
import com.taobao.accs.utl.ALog;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static final int SECURITY_OFF = 2;
    public static final int SECURITY_OPEN = 1;
    public static final int SECURITY_TAOBAO = 0;
    public static int a;
    public static String b;
    public static String c;
    public static String d;
    public static String e;
    public static IProcessName f;
    public static AtomicInteger g = new AtomicInteger(-1);
    private static volatile a h;
    private static Context i;
    private ActivityManager j;
    private ConnectivityManager k;

    public static a a(Context context) {
        if (h == null) {
            synchronized (a.class) {
                if (h == null) {
                    h = new a(context);
                }
            }
        }
        return h;
    }

    private a(Context context) {
        if (context == null) {
            throw new RuntimeException("Context is null!!");
        }
        if (i == null) {
            i = context.getApplicationContext();
        }
    }

    public ActivityManager a() {
        if (this.j == null) {
            this.j = (ActivityManager) i.getSystemService("activity");
        }
        return this.j;
    }

    public ConnectivityManager b() {
        if (this.k == null) {
            this.k = (ConnectivityManager) i.getSystemService("connectivity");
        }
        return this.k;
    }

    public static String a(String str) {
        String str2;
        if (TextUtils.isEmpty(b)) {
            str2 = str + ".TaobaoIntentService";
        } else {
            str2 = b;
        }
        ALog.d("AdapterGlobalClientInfo", "getAgooCustomServiceName", "serviceName", str2);
        return str2;
    }

    public static boolean c() {
        return g.intValue() == 0;
    }
}
