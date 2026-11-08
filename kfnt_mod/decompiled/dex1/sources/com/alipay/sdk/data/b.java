package com.alipay.sdk.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.ConditionVariable;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.alipay.sdk.app.OpenAuthTask;
import com.alipay.sdk.util.c;
import com.alipay.sdk.util.f;
import com.alipay.sdk.util.k;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.bytedance.sdk.account.platform.onekey.OnekeyLoginConfig;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final String f2592a = "virtualImeiAndImsi";

    /* renamed from: b, reason: collision with root package name */
    private static final String f2593b = "virtual_imei";

    /* renamed from: c, reason: collision with root package name */
    private static final String f2594c = "virtual_imsi";
    private static volatile b d;
    private String e;
    private String f = "sdk-and-lite";
    private String g;

    private static String e() {
        return "1";
    }

    private static String f() {
        return "-1;-1";
    }

    public String a() {
        return this.g;
    }

    private b() {
        String strA = com.alipay.sdk.app.a.a();
        if (com.alipay.sdk.app.a.b()) {
            return;
        }
        this.f += '_' + strA;
    }

    public static synchronized b b() {
        if (d == null) {
            d = new b();
        }
        return d;
    }

    public static synchronized void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        PreferenceManager.getDefaultSharedPreferences(com.alipay.sdk.sys.b.a().b()).edit().putString(com.alipay.sdk.cons.b.i, str).apply();
        com.alipay.sdk.cons.a.e = str;
    }

    private static String b(Context context) {
        return Float.toString(new TextView(context).getTextSize());
    }

    public String a(com.alipay.sdk.sys.a aVar, com.alipay.sdk.tid.a aVar2) throws IOException {
        Context contextB = com.alipay.sdk.sys.b.a().b();
        com.alipay.sdk.util.a aVarA = com.alipay.sdk.util.a.a(contextB);
        if (TextUtils.isEmpty(this.e)) {
            this.e = "Msp/15.7.9 (" + k.b() + f.f2657b + k.c() + f.f2657b + k.d(contextB) + f.f2657b + k.f(contextB) + f.f2657b + k.e(contextB) + f.f2657b + b(contextB);
        }
        String strB = com.alipay.sdk.util.a.b(contextB).b();
        String strG = k.g(contextB);
        String strE = e();
        String strA = aVarA.a();
        String strB2 = aVarA.b();
        String strD = d();
        String strC = c();
        if (aVar2 != null) {
            this.g = aVar2.b();
        }
        String strReplace = Build.MANUFACTURER.replace(f.f2657b, " ");
        String strReplace2 = Build.MODEL.replace(f.f2657b, " ");
        boolean zD = com.alipay.sdk.sys.b.d();
        String strD2 = aVarA.d();
        String strC2 = c(contextB);
        String strD3 = d(contextB);
        StringBuilder sb = new StringBuilder();
        sb.append(this.e);
        sb.append(f.f2657b);
        sb.append(strB);
        sb.append(f.f2657b);
        sb.append(strG);
        sb.append(f.f2657b);
        sb.append(strE);
        sb.append(f.f2657b);
        sb.append(strA);
        sb.append(f.f2657b);
        sb.append(strB2);
        sb.append(f.f2657b);
        sb.append(this.g);
        sb.append(f.f2657b);
        sb.append(strReplace);
        sb.append(f.f2657b);
        sb.append(strReplace2);
        sb.append(f.f2657b);
        sb.append(zD);
        sb.append(f.f2657b);
        sb.append(strD2);
        sb.append(f.f2657b);
        sb.append(f());
        sb.append(f.f2657b);
        sb.append(this.f);
        sb.append(f.f2657b);
        sb.append(strD);
        sb.append(f.f2657b);
        sb.append(strC);
        sb.append(f.f2657b);
        sb.append(strC2);
        sb.append(f.f2657b);
        sb.append(strD3);
        if (aVar2 != null) {
            HashMap map = new HashMap();
            map.put("tid", com.alipay.sdk.tid.a.a(contextB).a());
            map.put(com.alipay.sdk.cons.b.g, com.alipay.sdk.sys.b.a().e());
            String strC3 = c(aVar, contextB, map);
            if (!TextUtils.isEmpty(strC3)) {
                sb.append(";;;");
                sb.append(strC3);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public static String c() {
        String strB;
        Context contextB = com.alipay.sdk.sys.b.a().b();
        SharedPreferences sharedPreferences = contextB.getSharedPreferences(f2592a, 0);
        String string = sharedPreferences.getString(f2593b, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(com.alipay.sdk.tid.a.a(contextB).a())) {
                strB = g();
            } else {
                strB = com.alipay.sdk.util.a.a(contextB).b();
            }
            string = strB;
            sharedPreferences.edit().putString(f2593b, string).apply();
        }
        return string;
    }

    public static String d() {
        String strA;
        Context contextB = com.alipay.sdk.sys.b.a().b();
        SharedPreferences sharedPreferences = contextB.getSharedPreferences(f2592a, 0);
        String string = sharedPreferences.getString(f2594c, null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        if (TextUtils.isEmpty(com.alipay.sdk.tid.a.a(contextB).a())) {
            String strE = com.alipay.sdk.sys.b.a().e();
            if (TextUtils.isEmpty(strE) || strE.length() < 18) {
                strA = g();
            } else {
                strA = strE.substring(3, 18);
            }
        } else {
            strA = com.alipay.sdk.util.a.a(contextB).a();
        }
        String str = strA;
        sharedPreferences.edit().putString(f2594c, str).apply();
        return str;
    }

    private static String g() {
        return Long.toHexString(System.currentTimeMillis()) + (new Random().nextInt(OpenAuthTask.OK) + 1000);
    }

    private static String c(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService(OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR)).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getSSID() : "-1";
    }

    private static String d(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService(OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR)).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getBSSID() : "00";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(com.alipay.sdk.sys.a aVar, Context context, HashMap<String, String> map) {
        final String[] strArr = {""};
        try {
            APSecuritySdk aPSecuritySdk = APSecuritySdk.getInstance(context);
            final ConditionVariable conditionVariable = new ConditionVariable();
            aPSecuritySdk.initToken(0, map, new APSecuritySdk.InitResultListener() { // from class: com.alipay.sdk.data.b.1
                @Override // com.alipay.apmobilesecuritysdk.face.APSecuritySdk.InitResultListener
                public void onResult(APSecuritySdk.TokenResult tokenResult) {
                    if (tokenResult != null) {
                        strArr[0] = tokenResult.apdidToken;
                    }
                    conditionVariable.open();
                }
            });
            conditionVariable.block(OnekeyLoginConfig.OVER_TIME);
        } catch (Throwable th) {
            c.a(th);
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.e, com.alipay.sdk.app.statistic.b.i, th);
        }
        if (TextUtils.isEmpty(strArr[0])) {
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.e, com.alipay.sdk.app.statistic.b.j, "missing token");
        }
        c.a(com.alipay.sdk.cons.a.x, "ap:" + strArr[0]);
        return strArr[0];
    }

    private static String c(final com.alipay.sdk.sys.a aVar, final Context context, final HashMap<String, String> map) {
        try {
            return (String) Executors.newFixedThreadPool(2).submit(new Callable<String>() { // from class: com.alipay.sdk.data.b.2
                @Override // java.util.concurrent.Callable
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public String call() throws Exception {
                    return b.b(aVar, context, map);
                }
            }).get(OnekeyLoginConfig.OVER_TIME, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.e, com.alipay.sdk.app.statistic.b.k, th);
            return "";
        }
    }

    public static String a(Context context) throws PackageManager.NameNotFoundException {
        if (context == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            sb.append("(");
            sb.append(packageName);
            sb.append(f.f2657b);
            sb.append(packageInfo.versionCode);
            sb.append(")");
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
