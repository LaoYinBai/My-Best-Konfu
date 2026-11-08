package com.alipay.sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final String f2641a = "00:00:00:00:00:00";

    /* renamed from: c, reason: collision with root package name */
    private static a f2642c;

    /* renamed from: b, reason: collision with root package name */
    private String f2643b;

    public String a() {
        return "000000000000000";
    }

    public String b() {
        return "000000000000000";
    }

    public static a a(Context context) {
        if (f2642c == null) {
            f2642c = new a(context);
        }
        return f2642c;
    }

    private a(Context context) {
        try {
            try {
                this.f2643b = ((WifiManager) context.getApplicationContext().getSystemService(OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR)).getConnectionInfo().getMacAddress();
            } catch (Exception e) {
                c.a(e);
                if (TextUtils.isEmpty(this.f2643b)) {
                }
            }
            if (TextUtils.isEmpty(this.f2643b)) {
                this.f2643b = f2641a;
            }
        } catch (Throwable th) {
            if (TextUtils.isEmpty(this.f2643b)) {
                this.f2643b = f2641a;
            }
            throw th;
        }
    }

    public String c() {
        String str = b() + "|";
        String strA = a();
        if (TextUtils.isEmpty(strA)) {
            return str + "000000000000000";
        }
        return str + strA;
    }

    public String d() {
        return this.f2643b;
    }

    public static d b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                return d.a(activeNetworkInfo.getSubtype());
            }
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
                return d.WIFI;
            }
            return d.NONE;
        } catch (Exception unused) {
            return d.NONE;
        }
    }

    public static String c(Context context) {
        return a(context).c().substring(0, 8);
    }

    public static String d(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getConfiguration().locale.toString();
        } catch (Throwable unused) {
            return "";
        }
    }
}
