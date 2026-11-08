package com.alipay.sdk.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.data.a;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    static final String f2667a = "com.eg.android.AlipayGphone";

    /* renamed from: b, reason: collision with root package name */
    static final int f2668b = 125;

    /* renamed from: c, reason: collision with root package name */
    private static final String f2669c = "com.alipay.android.app";
    private static final String d = "com.eg.android.AlipayGphoneRC";
    private static final int e = 99;
    private static final String[] f = {"10.1.5.1013151", "10.1.5.1013148"};

    public static String g(Context context) {
        return "-1;-1";
    }

    static String a() {
        if (EnvUtils.isSandBox()) {
            return d;
        }
        try {
            return com.alipay.sdk.app.a.f2557a.get(0).f2589a;
        } catch (Throwable unused) {
            return f2667a;
        }
    }

    static String a(String str) {
        return (EnvUtils.isSandBox() && TextUtils.equals(str, d)) ? "com.eg.android.AlipayGphoneRC.IAlixPay" : "com.eg.android.AlipayGphone.IAlixPay";
    }

    public static Map<String, String> b(String str) {
        HashMap map = new HashMap();
        for (String str2 : str.split("&")) {
            int iIndexOf = str2.indexOf("=", 1);
            if (-1 != iIndexOf) {
                map.put(str2.substring(0, iIndexOf), URLDecoder.decode(str2.substring(iIndexOf + 1)));
            }
        }
        return map;
    }

    public static Map<String, String> a(com.alipay.sdk.sys.a aVar, String str) {
        HashMap map = new HashMap(4);
        int iIndexOf = str.indexOf(63);
        if (iIndexOf != -1 && iIndexOf < str.length() - 1) {
            for (String str2 : str.substring(iIndexOf + 1).split("&")) {
                int iIndexOf2 = str2.indexOf(61, 1);
                if (iIndexOf2 != -1 && iIndexOf2 < str2.length() - 1) {
                    map.put(str2.substring(0, iIndexOf2), b(aVar, str2.substring(iIndexOf2 + 1)));
                }
            }
        }
        return map;
    }

    public static JSONObject c(String str) {
        try {
            return new JSONObject(str);
        } catch (Throwable unused) {
            return new JSONObject();
        }
    }

    public static String b(com.alipay.sdk.sys.a aVar, String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.v, e2);
            return "";
        }
    }

    public static String a(String str, String str2, String str3) {
        try {
            int iIndexOf = str3.indexOf(str) + str.length();
            if (iIndexOf <= str.length()) {
                return "";
            }
            int iIndexOf2 = TextUtils.isEmpty(str2) ? 0 : str3.indexOf(str2, iIndexOf);
            if (iIndexOf2 < 1) {
                return str3.substring(iIndexOf);
            }
            return str3.substring(iIndexOf, iIndexOf2);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String a(com.alipay.sdk.sys.a aVar, byte[] bArr) {
        BigInteger modulus;
        try {
            PublicKey publicKey = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey();
            if (!(publicKey instanceof RSAPublicKey) || (modulus = ((RSAPublicKey) publicKey).getModulus()) == null) {
                return null;
            }
            return modulus.toString(16);
        } catch (Exception e2) {
            com.alipay.sdk.app.statistic.a.a(aVar, "auth", com.alipay.sdk.app.statistic.b.p, e2);
            return null;
        }
    }

    public static a a(com.alipay.sdk.sys.a aVar, Context context, List<a.C0078a> list) {
        a aVarA;
        if (list == null) {
            return null;
        }
        for (a.C0078a c0078a : list) {
            if (c0078a != null && (aVarA = a(aVar, context, c0078a.f2589a, c0078a.f2590b, c0078a.f2591c)) != null && !aVarA.a(aVar) && !aVarA.a()) {
                return aVarA;
            }
        }
        return null;
    }

    private static a a(com.alipay.sdk.sys.a aVar, Context context, String str, int i, String str2) {
        PackageInfo packageInfoB;
        if (EnvUtils.isSandBox() && f2667a.equals(str)) {
            str = d;
        }
        try {
            packageInfoB = b(context, str);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(aVar, "auth", com.alipay.sdk.app.statistic.b.m, th.getMessage());
            packageInfoB = null;
        }
        if (a(aVar, packageInfoB)) {
            return a(packageInfoB, i, str2);
        }
        return null;
    }

    private static boolean a(com.alipay.sdk.sys.a aVar, PackageInfo packageInfo) {
        String str = "";
        boolean z = false;
        if (packageInfo == null) {
            str = "info == null";
        } else if (packageInfo.signatures == null) {
            str = "info.signatures == null";
        } else if (packageInfo.signatures.length <= 0) {
            str = "info.signatures.length <= 0";
        } else {
            z = true;
        }
        if (!z) {
            com.alipay.sdk.app.statistic.a.a(aVar, "auth", com.alipay.sdk.app.statistic.b.n, str);
        }
        return z;
    }

    private static PackageInfo b(Context context, String str) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(str, 192);
    }

    private static a a(PackageInfo packageInfo, int i, String str) {
        if (packageInfo == null) {
            return null;
        }
        return new a(packageInfo, i, str);
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public final PackageInfo f2671a;

        /* renamed from: b, reason: collision with root package name */
        public final int f2672b;

        /* renamed from: c, reason: collision with root package name */
        public final String f2673c;

        public a(PackageInfo packageInfo, int i, String str) {
            this.f2671a = packageInfo;
            this.f2672b = i;
            this.f2673c = str;
        }

        public boolean a(com.alipay.sdk.sys.a aVar) {
            Signature[] signatureArr = this.f2671a.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return false;
            }
            for (Signature signature : signatureArr) {
                String strA = k.a(aVar, signature.toByteArray());
                if (strA != null && !TextUtils.equals(strA, this.f2673c)) {
                    com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.x, String.format("Got %s, expected %s", strA, this.f2673c));
                    return true;
                }
            }
            return false;
        }

        public boolean a() {
            return this.f2671a.versionCode < this.f2672b;
        }
    }

    public static boolean a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(f2669c, 128) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean b(com.alipay.sdk.sys.a aVar, Context context, List<a.C0078a> list) {
        try {
            for (a.C0078a c0078a : list) {
                if (c0078a != null) {
                    String str = c0078a.f2589a;
                    if (EnvUtils.isSandBox() && f2667a.equals(str)) {
                        str = d;
                    }
                    try {
                        if (context.getPackageManager().getPackageInfo(str, 128) != null) {
                            return true;
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                        continue;
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.Y, th);
            return false;
        }
    }

    static boolean a(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        try {
            String str = packageInfo.versionName;
            if (!TextUtils.equals(str, f[0])) {
                if (!TextUtils.equals(str, f[1])) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(a(), 128);
            if (packageInfo == null) {
                return false;
            }
            return packageInfo.versionCode < 99;
        } catch (Throwable th) {
            c.a(th);
            return false;
        }
    }

    public static String c(Context context) throws IOException {
        return " (" + b() + f.f2657b + c() + f.f2657b + d(context) + f.f2657b + f.f2657b + e(context) + ")(sdk android)";
    }

    public static String b() {
        return "Android " + Build.VERSION.RELEASE;
    }

    public static String c() throws IOException {
        String strF = f();
        int iIndexOf = strF.indexOf("-");
        if (iIndexOf != -1) {
            strF = strF.substring(0, iIndexOf);
        }
        int iIndexOf2 = strF.indexOf("\n");
        if (iIndexOf2 != -1) {
            strF = strF.substring(0, iIndexOf2);
        }
        return "Linux " + strF;
    }

    private static String f() throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            try {
                String line = bufferedReader.readLine();
                bufferedReader.close();
                Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(line);
                if (!matcher.matches() || matcher.groupCount() < 4) {
                    return "Unavailable";
                }
                return matcher.group(1) + "\n" + matcher.group(2) + " " + matcher.group(3) + "\n" + matcher.group(4);
            } catch (Throwable th) {
                bufferedReader.close();
                throw th;
            }
        } catch (IOException unused) {
            return "Unavailable";
        }
    }

    public static String d(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    public static String e(Context context) {
        DisplayMetrics displayMetricsI = i(context);
        return displayMetricsI.widthPixels + "*" + displayMetricsI.heightPixels;
    }

    private static DisplayMetrics i(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static String f(Context context) {
        String strA = j.a(context);
        return strA.substring(0, strA.indexOf(HttpConstant.SCHEME_SPLIT));
    }

    public static String a(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            switch (random.nextInt(3)) {
                case 0:
                    sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 65.0d)));
                    break;
                case 1:
                    sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 97.0d)));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    public static boolean d(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }

    static String a(Context context, String str) {
        String strSubstring = "";
        try {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getApplicationContext().getSystemService(TTDownloadField.TT_ACTIVITY)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    strSubstring = strSubstring + "#M";
                } else {
                    if (runningAppProcessInfo.processName.startsWith(str + ":")) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(strSubstring);
                        sb.append("#");
                        sb.append(runningAppProcessInfo.processName.replace(str + ":", ""));
                        strSubstring = sb.toString();
                    }
                }
            }
        } catch (Throwable unused) {
            strSubstring = "";
        }
        if (strSubstring.length() > 0) {
            strSubstring = strSubstring.substring(1);
        }
        return strSubstring.length() == 0 ? "N" : strSubstring;
    }

    public static boolean a(com.alipay.sdk.sys.a aVar, String str, final Activity activity) throws NumberFormatException {
        a aVarA;
        String strSubstring;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (activity == null) {
            return false;
        }
        if (str.toLowerCase().startsWith(com.alipay.sdk.cons.a.l.toLowerCase()) || str.toLowerCase().startsWith(com.alipay.sdk.cons.a.m.toLowerCase())) {
            try {
                aVarA = a(aVar, activity, com.alipay.sdk.app.a.f2557a);
            } catch (Throwable unused) {
            }
            if (aVarA != null && !aVarA.a() && !aVarA.a(aVar)) {
                if (str.startsWith("intent://platformapi/startapp")) {
                    str = str.replaceFirst("intent://platformapi/startapp\\?", com.alipay.sdk.cons.a.l);
                }
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            }
            return true;
        }
        if (TextUtils.equals(str, com.alipay.sdk.cons.a.o) || TextUtils.equals(str, com.alipay.sdk.cons.a.p)) {
            com.alipay.sdk.app.b.a(com.alipay.sdk.app.b.c());
            activity.finish();
            return true;
        }
        if (!str.startsWith(com.alipay.sdk.cons.a.n)) {
            return false;
        }
        try {
            String strSubstring2 = str.substring(str.indexOf(com.alipay.sdk.cons.a.n) + com.alipay.sdk.cons.a.n.length());
            int i = Integer.parseInt(strSubstring2.substring(strSubstring2.lastIndexOf(com.alipay.sdk.cons.a.q) + com.alipay.sdk.cons.a.q.length()));
            if (i == com.alipay.sdk.app.c.SUCCEEDED.a() || i == com.alipay.sdk.app.c.PAY_WAITTING.a()) {
                if (com.alipay.sdk.cons.a.u) {
                    StringBuilder sb = new StringBuilder();
                    String strDecode = URLDecoder.decode(str);
                    String strDecode2 = URLDecoder.decode(strDecode);
                    String str2 = strDecode2.substring(strDecode2.indexOf(com.alipay.sdk.cons.a.n) + com.alipay.sdk.cons.a.n.length(), strDecode2.lastIndexOf(com.alipay.sdk.cons.a.q)).split(com.alipay.sdk.cons.a.s)[0];
                    int iIndexOf = strDecode.indexOf(com.alipay.sdk.cons.a.s) + com.alipay.sdk.cons.a.s.length();
                    sb.append(str2);
                    sb.append(com.alipay.sdk.cons.a.s);
                    sb.append(strDecode.substring(iIndexOf, strDecode.indexOf("&", iIndexOf)));
                    sb.append(strDecode.substring(strDecode.indexOf("&", iIndexOf)));
                    strSubstring = sb.toString();
                } else {
                    String strDecode3 = URLDecoder.decode(str);
                    strSubstring = strDecode3.substring(strDecode3.indexOf(com.alipay.sdk.cons.a.n) + com.alipay.sdk.cons.a.n.length(), strDecode3.lastIndexOf(com.alipay.sdk.cons.a.q));
                }
                com.alipay.sdk.app.c cVarB = com.alipay.sdk.app.c.b(i);
                com.alipay.sdk.app.b.a(com.alipay.sdk.app.b.a(cVarB.a(), cVarB.b(), strSubstring));
            } else {
                com.alipay.sdk.app.c cVarB2 = com.alipay.sdk.app.c.b(com.alipay.sdk.app.c.FAILED.a());
                com.alipay.sdk.app.b.a(com.alipay.sdk.app.b.a(cVarB2.a(), cVarB2.b(), ""));
            }
        } catch (Exception unused2) {
            com.alipay.sdk.app.b.a(com.alipay.sdk.app.b.e());
        }
        activity.runOnUiThread(new Runnable() { // from class: com.alipay.sdk.util.k.1
            @Override // java.lang.Runnable
            public void run() {
                activity.finish();
            }
        });
        return true;
    }

    public static String a(com.alipay.sdk.sys.a aVar, Context context) {
        return a(aVar, context, context.getPackageName());
    }

    private static String a(com.alipay.sdk.sys.a aVar, Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 128).versionName;
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.m, th);
            return "";
        }
    }

    public static String e(String str) {
        try {
            Uri uri = Uri.parse(str);
            return String.format("%s%s", uri.getAuthority(), uri.getPath());
        } catch (Throwable th) {
            c.a(th);
            return "-";
        }
    }

    public static String f(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return a(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            sb.append(Character.forDigit((b2 & 240) >> 4, 16));
            sb.append(Character.forDigit(b2 & 15, 16));
        }
        return sb.toString();
    }

    public static int d() {
        try {
            return Process.myUid();
        } catch (Throwable th) {
            c.a(th);
            return ErrorConstant.ERROR_NO_NETWORK;
        }
    }

    public static ActivityInfo h(Context context) {
        try {
            if (!(context instanceof Activity)) {
                return null;
            }
            Activity activity = (Activity) context;
            for (ActivityInfo activityInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities) {
                if (TextUtils.equals(activityInfo.name, activity.getClass().getName())) {
                    return activityInfo;
                }
            }
            return null;
        } catch (Throwable th) {
            c.a(th);
            return null;
        }
    }

    public static int b(int i) {
        return i / 100000;
    }

    public static boolean e() {
        try {
            String[] strArrSplit = com.alipay.sdk.data.a.p().j().split("\\|");
            String str = Build.MODEL;
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            for (String str2 : strArrSplit) {
                if (TextUtils.equals(str, str2)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            c.a(th);
            return false;
        }
    }

    public static String a(com.alipay.sdk.sys.a aVar) {
        return c(aVar, "ro.build.fingerprint");
    }

    public static String c(com.alipay.sdk.sys.a aVar, String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod(ApiRequest.METHOD_GET, String.class).invoke(null, str);
        } catch (Exception e2) {
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, "rflex", e2.getClass().getSimpleName());
            return null;
        }
    }
}
