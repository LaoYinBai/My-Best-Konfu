package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.annotation.RequiresPermission;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressLint({"MissingPermission"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class n {

    /* renamed from: a, reason: collision with root package name */
    private static volatile String f2020a;

    /* renamed from: b, reason: collision with root package name */
    private static volatile String f2021b;

    /* renamed from: c, reason: collision with root package name */
    private static volatile String f2022c;
    private static volatile String d;
    private static volatile String e;
    private static volatile String f;
    private static volatile String g;
    private static volatile String h;
    private static volatile String i;
    private static final AtomicInteger j = new AtomicInteger(0);

    public static String a(Context context) {
        if (TextUtils.isEmpty(f2021b)) {
            synchronized (n.class) {
                if (TextUtils.isEmpty(f2021b)) {
                    n(context);
                }
            }
        }
        return f2021b;
    }

    public static String b(Context context) {
        if (TextUtils.isEmpty(g)) {
            synchronized (n.class) {
                if (TextUtils.isEmpty(g)) {
                    b();
                }
            }
        }
        return g;
    }

    private static Context c(Context context) {
        return context == null ? bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d() : context;
    }

    public static String d(Context context) {
        if (TextUtils.isEmpty(f2020a)) {
            synchronized (n.class) {
                if (TextUtils.isEmpty(f2020a)) {
                    p(context);
                }
            }
        }
        return f2020a;
    }

    public static String e(Context context) {
        if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().q().isCanUsePhoneState()) {
            return null;
        }
        if (TextUtils.isEmpty(f2022c)) {
            synchronized (n.class) {
                if (TextUtils.isEmpty(f2022c)) {
                    s(context);
                }
            }
        }
        return f2022c;
    }

    public static String f(Context context) {
        if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().q().isCanUsePhoneState()) {
            return null;
        }
        if (TextUtils.isEmpty(f)) {
            synchronized (n.class) {
                if (TextUtils.isEmpty(f)) {
                    s(context);
                }
            }
        }
        return f;
    }

    public static String g(Context context) {
        if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().q().isCanUseLocation()) {
            return null;
        }
        if (TextUtils.isEmpty(d)) {
            synchronized (n.class) {
                if (TextUtils.isEmpty(d)) {
                    r(context);
                }
            }
        }
        return d;
    }

    public static String h(Context context) {
        if (TextUtils.isEmpty(h)) {
            synchronized (n.class) {
                if (TextUtils.isEmpty(h)) {
                    q(context);
                }
            }
        }
        return h;
    }

    public static String i(Context context) {
        if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().q().isCanUseLocation()) {
            return null;
        }
        if (TextUtils.isEmpty(e)) {
            synchronized (n.class) {
                if (TextUtils.isEmpty(e)) {
                    r(context);
                }
            }
        }
        return e;
    }

    public static String j(Context context) {
        if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().q().isCanUsePhoneState()) {
            return null;
        }
        if (TextUtils.isEmpty(i)) {
            synchronized (n.class) {
                if (TextUtils.isEmpty(i)) {
                    o(context);
                }
            }
        }
        return i;
    }

    private static boolean k(Context context) {
        return bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.c.a(context, "android.permission.ACCESS_FINE_LOCATION") == 0 || bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.c.a(context, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    private static boolean l(Context context) {
        return bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.c.a(context, "android.permission.READ_PHONE_STATE") == 0;
    }

    public static synchronized void m(Context context) {
        Context contextC = c(context);
        if (contextC == null) {
            return;
        }
        s(contextC);
        r(context);
        o(context);
        n(context);
        p(context);
        c();
        b();
        q(context);
    }

    private static synchronized void n(Context context) {
        if (context == null) {
            return;
        }
        f2021b = a(context, true);
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    private static synchronized void o(Context context) {
        if (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().q().isCanUsePhoneState() && l(context)) {
            try {
                if (Build.VERSION.SDK_INT >= 28) {
                    i = Build.getSerial();
                } else {
                    i = Build.SERIAL;
                }
            } catch (Exception unused) {
            }
        }
    }

    private static synchronized void p(Context context) {
        if (context == null) {
            return;
        }
        f2020a = m.c();
        if (TextUtils.isEmpty(f2020a)) {
            f2020a = u.a("tt_device_info", context).a("did", (String) null);
        } else {
            a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d(), f2020a);
        }
    }

    private static synchronized void q(Context context) {
        if (context == null) {
            return;
        }
        h = u.a("tt_device_info", context).a("uuid", (String) null);
    }

    private static synchronized void r(Context context) {
        WifiInfo connectionInfo;
        if (context == null) {
            return;
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR);
            if (wifiManager != null) {
                if (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().q().isCanUseLocation() && k(context) && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
                    d = connectionInfo.getSSID();
                }
                e = a(wifiManager, context);
            }
        } catch (Exception unused) {
        }
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    private static synchronized void s(Context context) {
        if (j.intValue() > 5) {
            return;
        }
        if (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().q().isCanUsePhoneState() && l(context)) {
            try {
                j.incrementAndGet();
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    f2022c = telephonyManager.getDeviceId();
                    f = telephonyManager.getSubscriberId();
                }
            } catch (Exception unused) {
            }
        }
    }

    public static synchronized void t(Context context) {
        if (TextUtils.isEmpty(f2022c)) {
            Context contextC = c(context);
            if (contextC == null) {
                return;
            }
            try {
                s(contextC);
            } catch (Exception unused) {
            }
        }
    }

    private static synchronized void c() {
        a();
    }

    public static synchronized void a(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(f2020a)) {
            u.a("tt_device_info", context).b("did", str);
            f2020a = str;
        }
    }

    private static synchronized void b() {
        g = String.valueOf(Build.TIME);
    }

    private static String a(WifiManager wifiManager, Context context) {
        String str = "02:00:00:00:00:00";
        if (wifiManager == null) {
            return "02:00:00:00:00:00";
        }
        if (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().q().isCanUseLocation() && k(context)) {
            try {
                List<ScanResult> scanResults = wifiManager.getScanResults();
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (scanResults != null && connectionInfo != null && connectionInfo.getBSSID() != null) {
                    for (int i2 = 0; i2 < scanResults.size(); i2++) {
                        ScanResult scanResult = scanResults.get(i2);
                        if (connectionInfo.getBSSID().equals(scanResult.BSSID)) {
                            str = scanResult.BSSID;
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0024, code lost:
    
        if (r1.length() >= 13) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0084  */
    @android.annotation.SuppressLint({"TrulyRandom", "HardwareIds"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(android.content.Context r5, boolean r6) throws java.lang.Throwable {
        /*
            android.content.Context r5 = c(r5)
            r0 = 0
            if (r5 != 0) goto L8
            return r0
        L8:
            android.content.ContentResolver r1 = r5.getContentResolver()     // Catch: java.lang.Exception -> L13
            java.lang.String r2 = "android_id"
            java.lang.String r1 = android.provider.Settings.Secure.getString(r1, r2)     // Catch: java.lang.Exception -> L13
            goto L14
        L13:
            r1 = r0
        L14:
            r2 = 13
            if (r1 == 0) goto L26
            java.lang.String r3 = "9774d56d682e549c"
            boolean r3 = r1.equals(r3)     // Catch: java.lang.Exception -> L8d
            if (r3 != 0) goto L26
            int r3 = r1.length()     // Catch: java.lang.Exception -> L8d
            if (r3 >= r2) goto L8d
        L26:
            java.lang.String r3 = "tt_device_info"
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.u r5 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.u.a(r3, r5)     // Catch: java.lang.Exception -> L8d
            java.lang.String r3 = "openudid"
            java.lang.String r0 = r5.a(r3, r0)     // Catch: java.lang.Exception -> L8d
            boolean r3 = a(r0)     // Catch: java.lang.Exception -> L8d
            if (r3 != 0) goto L8b
            java.security.SecureRandom r0 = new java.security.SecureRandom     // Catch: java.lang.Exception -> L8d
            r0.<init>()     // Catch: java.lang.Exception -> L8d
            java.math.BigInteger r3 = new java.math.BigInteger     // Catch: java.lang.Exception -> L8d
            r4 = 64
            r3.<init>(r4, r0)     // Catch: java.lang.Exception -> L8d
            r0 = 16
            java.lang.String r0 = r3.toString(r0)     // Catch: java.lang.Exception -> L8d
            r3 = 0
            char r3 = r0.charAt(r3)     // Catch: java.lang.Exception -> L8d
            r4 = 45
            if (r3 != r4) goto L58
            r3 = 1
            java.lang.String r0 = r0.substring(r3)     // Catch: java.lang.Exception -> L8d
        L58:
            int r3 = r0.length()     // Catch: java.lang.Exception -> L8d
            int r2 = r2 - r3
            if (r2 <= 0) goto L75
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L8d
            r3.<init>()     // Catch: java.lang.Exception -> L8d
        L64:
            if (r2 <= 0) goto L6e
            r4 = 70
            r3.append(r4)     // Catch: java.lang.Exception -> L8d
            int r2 = r2 + (-1)
            goto L64
        L6e:
            r3.append(r0)     // Catch: java.lang.Exception -> L8d
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Exception -> L8d
        L75:
            if (r6 == 0) goto L84
            java.lang.String r6 = "openudid.dat"
            java.lang.String r6 = a(r6, r0)     // Catch: java.lang.Exception -> L8d
            boolean r2 = a(r6)     // Catch: java.lang.Exception -> L8d
            if (r2 == 0) goto L84
            goto L85
        L84:
            r6 = r0
        L85:
            java.lang.String r0 = "openudid"
            r5.b(r0, r6)     // Catch: java.lang.Exception -> L8d
            goto L8e
        L8b:
            r6 = r0
            goto L8e
        L8d:
            r6 = r1
        L8e:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.n.a(android.content.Context, boolean):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00b4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00af A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(java.lang.String r6, java.lang.String r7) throws java.lang.Throwable {
        /*
            java.lang.String r0 = android.os.Environment.getExternalStorageState()
            java.lang.String r1 = "mounted"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto Ld
            return r7
        Ld:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r1 = r1.getPath()
            r0.append(r1)
            java.lang.String r1 = "/Android/data/com.snssdk.api/cache"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = "/"
            r1.append(r2)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lb8
            r2.<init>(r0)     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lb8
            boolean r0 = r2.exists()     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lb8
            if (r0 != 0) goto L4f
            boolean r0 = r2.mkdirs()     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> Lb8
            if (r0 != 0) goto L4f
            return r7
        L4d:
            r6 = move-exception
            goto Lab
        L4f:
            java.io.File r0 = new java.io.File     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lb8
            r0.<init>(r6)     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lb8
            java.io.RandomAccessFile r6 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lb8
            java.lang.String r2 = "rwd"
            r6.<init>(r0, r2)     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lb8
            java.nio.channels.FileChannel r2 = r6.getChannel()     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lb9
            java.nio.channels.FileLock r2 = r2.lock()     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lb9
            boolean r0 = r0.isFile()     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La6
            if (r0 == 0) goto L8c
            r0 = 129(0x81, float:1.81E-43)
            byte[] r1 = new byte[r0]     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La6
            r3 = 0
            int r4 = r6.read(r1, r3, r0)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La6
            if (r4 <= 0) goto L8c
            if (r4 >= r0) goto L8c
            java.lang.String r0 = new java.lang.String     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La6
            java.lang.String r5 = "UTF-8"
            r0.<init>(r1, r3, r4, r5)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La6
            boolean r1 = a(r0)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La6
            if (r1 == 0) goto L8c
            if (r2 == 0) goto L88
            r2.release()     // Catch: java.lang.Exception -> L88
        L88:
            r6.close()     // Catch: java.lang.Exception -> L8b
        L8b:
            return r0
        L8c:
            java.lang.String r0 = "UTF-8"
            byte[] r0 = r7.getBytes(r0)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La6
            r3 = 0
            r6.setLength(r3)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La6
            r6.write(r0)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La6
            if (r2 == 0) goto L9f
            r2.release()     // Catch: java.lang.Exception -> L9f
        L9f:
            r6.close()     // Catch: java.lang.Exception -> La2
        La2:
            return r7
        La3:
            r7 = move-exception
            r1 = r2
            goto Lad
        La6:
            r1 = r2
            goto Lb9
        La8:
            r7 = move-exception
            goto Lad
        Laa:
            r6 = move-exception
        Lab:
            r7 = r6
            r6 = r1
        Lad:
            if (r1 == 0) goto Lb2
            r1.release()     // Catch: java.lang.Exception -> Lb2
        Lb2:
            if (r6 == 0) goto Lb7
            r6.close()     // Catch: java.lang.Exception -> Lb7
        Lb7:
            throw r7
        Lb8:
            r6 = r1
        Lb9:
            if (r1 == 0) goto Lbe
            r1.release()     // Catch: java.lang.Exception -> Lbe
        Lbe:
            if (r6 == 0) goto Lc3
            r6.close()     // Catch: java.lang.Exception -> Lc3
        Lc3:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.n.a(java.lang.String, java.lang.String):java.lang.String");
    }

    private static boolean a(String str) {
        int length;
        if (str == null || (length = str.length()) < 13 || length > 128) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if ((cCharAt < '0' || cCharAt > '9') && ((cCharAt < 'a' || cCharAt > 'f') && ((cCharAt < 'A' || cCharAt > 'F') && cCharAt != '-'))) {
                return false;
            }
        }
        return true;
    }

    private static String a() {
        StringBuilder sb = new StringBuilder();
        try {
            if (t.t()) {
                sb.append("MIUI-");
            } else if (t.q()) {
                sb.append("FLYME-");
            } else {
                String strF = t.f();
                if (t.d(strF)) {
                    sb.append("EMUI-");
                }
                if (!TextUtils.isEmpty(strF)) {
                    sb.append(strF);
                    sb.append("-");
                }
            }
            sb.append(Build.VERSION.INCREMENTAL);
        } catch (Exception unused) {
        }
        return sb.toString();
    }
}
