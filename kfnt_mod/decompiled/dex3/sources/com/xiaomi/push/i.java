package com.xiaomi.push;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class i {
    private static String a = null;
    private static String b = null;
    private static String c = "";
    private static String d;
    private static String e;
    private static final String f = String.valueOf((char) 2);

    /* renamed from: a, reason: collision with other field name */
    private static final String[] f587a = {"--", "a-", "u-", "v-", "o-", "g-"};
    private static String g = null;

    /* renamed from: a, reason: collision with other field name */
    private static volatile boolean f586a = false;

    private static double a(double d2) {
        int i = 1;
        while (true) {
            double d3 = i;
            if (d3 >= d2) {
                return d3;
            }
            i <<= 1;
        }
    }

    private static float a(int i) {
        float f2 = ((((((i + 102400) / DownloadExpSwitchCode.BUGFIX_DOWNLOAD_RUNNABLE_POOL_ERROR) + 1) * 512) * com.ss.union.game.sdk.feedback.picture.select.d.d) / 1024.0f) / 1024.0f;
        double d2 = f2;
        return d2 > 0.5d ? (float) Math.ceil(d2) : f2;
    }

    @TargetApi(17)
    public static int a() {
        Object objA;
        if (Build.VERSION.SDK_INT >= 17 && (objA = bh.a("android.os.UserHandle", "myUserId", new Object[0])) != null) {
            return ((Integer) Integer.class.cast(objA)).intValue();
        }
        return -1;
    }

    private static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int length = str.length();
        int iCharAt = 0;
        for (int i = 0; i < length; i++) {
            iCharAt = (iCharAt * 31) + str.charAt(i);
        }
        return iCharAt;
    }

    private static long a(File file) {
        long blockCount;
        long blockSize;
        StatFs statFs = new StatFs(file.getPath());
        if (Build.VERSION.SDK_INT >= 18) {
            blockCount = statFs.getBlockCountLong();
            blockSize = statFs.getBlockSizeLong();
        } else {
            blockCount = statFs.getBlockCount();
            blockSize = statFs.getBlockSize();
        }
        return blockSize * blockCount;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static String m433a() {
        return a(b()) + "GB";
    }

    /* renamed from: a, reason: collision with other method in class */
    private static String m434a(int i) {
        return (i <= 0 || i >= f587a.length) ? f587a[0] : f587a[i];
    }

    public static String a(Context context) {
        try {
            return j.a(context).a();
        } catch (Exception e2) {
            com.xiaomi.channel.commonutils.logger.b.m22a("failure to get gaid:" + e2.getMessage());
            return null;
        }
    }

    public static String a(Context context, boolean z) throws Throwable {
        int i;
        if (d == null) {
            String strE = e(context);
            switch (1) {
                case 1:
                    String strG = "";
                    if (!l.d()) {
                        strG = z ? g(context) : o(context);
                    }
                    String strD = d(context);
                    if ((Build.VERSION.SDK_INT < 26) || !b(strG) || !b(strD)) {
                        strE = strG + strE + strD;
                        i = 1;
                        break;
                    }
                    break;
                case 2:
                    String strMo100a = ax.a(context).mo100a();
                    if (!TextUtils.isEmpty(strMo100a)) {
                        strE = strMo100a + strE;
                        i = 2;
                        break;
                    }
                case 3:
                    String strN = n(context);
                    if (!TextUtils.isEmpty(strN)) {
                        strE = strN;
                        i = 3;
                        break;
                    }
                case 4:
                    String strMo102b = ax.a(context).mo102b();
                    if (!TextUtils.isEmpty(strMo102b)) {
                        strE = strMo102b;
                        i = 4;
                        break;
                    }
                case 5:
                    i = 5;
                    break;
                default:
                    strE = "";
                    i = 1;
                    break;
            }
            com.xiaomi.channel.commonutils.logger.b.b("devid rule select:" + i);
            if (i == 3) {
                d = strE;
            } else {
                d = m434a(i) + bm.b(strE);
            }
            b(context, d);
        }
        return d;
    }

    public static void a(Context context, String str) throws Throwable {
        com.xiaomi.channel.commonutils.logger.b.c("update vdevid = " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        g = str;
        u uVar = null;
        try {
            try {
                if (m440c(context)) {
                    File file = new File(Environment.getExternalStorageDirectory(), "/.vdevdir/");
                    if (file.exists() && file.isFile()) {
                        file.delete();
                    }
                    File file2 = new File(file, ".vdevid");
                    u uVarA = u.a(context, file2);
                    try {
                        y.m649a(file2);
                        y.a(file2, g);
                        uVar = uVarA;
                    } catch (IOException unused) {
                        uVar = uVarA;
                        com.xiaomi.channel.commonutils.logger.b.m22a("update vdevid failure.");
                        if (uVar == null) {
                            return;
                        }
                        uVar.a();
                    } catch (Throwable th) {
                        th = th;
                        uVar = uVarA;
                        if (uVar != null) {
                            uVar.a();
                        }
                        throw th;
                    }
                }
                y.a(new File(context.getFilesDir(), ".vdevid"), g);
                if (uVar == null) {
                    return;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException unused2) {
        }
        uVar.a();
    }

    public static void a(Context context, Map<String, String> map) {
        if (map == null || context == null) {
            return;
        }
        String strN = n(context);
        if (TextUtils.isEmpty(strN)) {
            return;
        }
        map.put("local_virt_devid", strN);
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m435a(Context context) {
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver == null) {
            return false;
        }
        int intExtra = intentRegisterReceiver.getIntExtra("status", -1);
        return intExtra == 2 || intExtra == 5;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m436a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < f587a.length; i++) {
            if (str.startsWith(f587a[i])) {
                return true;
            }
        }
        return false;
    }

    public static int b() throws Throwable {
        Throwable th;
        BufferedReader bufferedReader;
        String[] strArrSplit;
        int i = 0;
        if (new File("/proc/meminfo").exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
                try {
                    String line = bufferedReader.readLine();
                    if (!TextUtils.isEmpty(line) && (strArrSplit = line.split("\\s+")) != null && strArrSplit.length >= 2 && TextUtils.isDigitsOnly(strArrSplit[1])) {
                        i = Integer.parseInt(strArrSplit[1]);
                    }
                } catch (Exception unused) {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    return i;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Exception unused3) {
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
            try {
                bufferedReader.close();
            } catch (IOException unused4) {
            }
        }
        return i;
    }

    /* renamed from: b, reason: collision with other method in class */
    public static String m437b() {
        return a(((a(Environment.getDataDirectory()) / 1024.0d) / 1024.0d) / 1024.0d) + "GB";
    }

    public static String b(Context context) throws Throwable {
        File file;
        u uVarA;
        u uVar = null;
        if (!m440c(context)) {
            return null;
        }
        if (!TextUtils.isEmpty(g)) {
            return g;
        }
        g = y.a(new File(context.getFilesDir(), ".vdevid"));
        if (!TextUtils.isEmpty(g)) {
            return g;
        }
        try {
            try {
                file = new File(new File(Environment.getExternalStorageDirectory(), "/.vdevdir/"), ".vdevid");
                uVarA = u.a(context, file);
            } catch (IOException unused) {
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            g = "";
            String strA = y.a(file);
            if (strA != null) {
                g = strA;
            }
            String str = g;
            if (uVarA != null) {
                uVarA.a();
            }
            return str;
        } catch (IOException unused2) {
            uVar = uVarA;
            com.xiaomi.channel.commonutils.logger.b.m22a("getVDevID failure.");
            if (uVar != null) {
                uVar.a();
            }
            return g;
        } catch (Throwable th2) {
            th = th2;
            uVar = uVarA;
            if (uVar != null) {
                uVar.a();
            }
            throw th;
        }
    }

    private static void b(Context context, String str) throws Throwable {
        u uVarA;
        com.xiaomi.channel.commonutils.logger.b.c("write lvdd = " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        u uVar = null;
        try {
            try {
                if (m440c(context)) {
                    File file = new File(Environment.getExternalStorageDirectory(), "/.vdevdir/");
                    if (file.exists() && file.isFile()) {
                        file.delete();
                    }
                    File file2 = new File(file, ".vdevidlocal");
                    if (file2.exists() && file2.isFile()) {
                        com.xiaomi.channel.commonutils.logger.b.b("vdr exists, not rewrite.");
                        return;
                    }
                    uVarA = u.a(context, file2);
                    try {
                        y.m649a(file2);
                        y.a(file2, d + f + a(d));
                        com.xiaomi.channel.commonutils.logger.b.b("lvdd write succ.");
                    } catch (IOException unused) {
                        uVar = uVarA;
                        com.xiaomi.channel.commonutils.logger.b.m22a("write lvdd failure.");
                        if (uVar != null) {
                            uVar.a();
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        uVar = uVarA;
                        th = th;
                        if (uVar != null) {
                            uVar.a();
                        }
                        throw th;
                    }
                } else {
                    com.xiaomi.channel.commonutils.logger.b.m22a("not support write lvdd.");
                    uVarA = null;
                }
                if (uVarA != null) {
                    uVarA.a();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException unused2) {
        }
    }

    /* renamed from: b, reason: collision with other method in class */
    public static boolean m438b(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        return powerManager == null || powerManager.isScreenOn();
    }

    private static boolean b(String str) {
        if (str == null) {
            return true;
        }
        String strTrim = str.trim();
        return strTrim.length() == 0 || strTrim.equalsIgnoreCase("null") || strTrim.equalsIgnoreCase(UtilityImpl.NET_TYPE_UNKNOWN);
    }

    private static int c() {
        return Build.VERSION.SDK_INT < 29 ? 10 : 0;
    }

    /* renamed from: c, reason: collision with other method in class */
    public static String m439c() {
        return b() + "KB";
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String c(android.content.Context r5) throws java.lang.Throwable {
        /*
            boolean r0 = m440c(r5)
            r1 = 0
            if (r0 == 0) goto Lb6
            boolean r0 = com.xiaomi.push.i.f586a
            if (r0 == 0) goto Ld
            goto Lb6
        Ld:
            r0 = 1
            com.xiaomi.push.i.f586a = r0
            java.io.File r0 = new java.io.File
            java.io.File r2 = r5.getFilesDir()
            java.lang.String r3 = ".vdevid"
            r0.<init>(r2, r3)
            java.lang.String r0 = com.xiaomi.push.y.a(r0)
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            java.io.File r3 = android.os.Environment.getExternalStorageDirectory()     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            java.lang.String r4 = "/.vdevdir/"
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            java.lang.String r4 = ".vdevid"
            r3.<init>(r2, r4)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            com.xiaomi.push.u r2 = com.xiaomi.push.u.a(r5, r3)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            java.lang.String r3 = com.xiaomi.push.y.a(r3)     // Catch: java.io.IOException -> L42 java.lang.Throwable -> Lae
            if (r2 == 0) goto L4d
            r2.a()
            goto L4d
        L3f:
            r5 = move-exception
            goto Lb0
        L41:
            r2 = r1
        L42:
            java.lang.String r3 = "check id failure."
            com.xiaomi.channel.commonutils.logger.b.m22a(r3)     // Catch: java.lang.Throwable -> Lae
            if (r2 == 0) goto L4c
            r2.a()
        L4c:
            r3 = r1
        L4d:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto La6
            com.xiaomi.push.i.g = r0
            boolean r2 = android.text.TextUtils.isEmpty(r3)
            if (r2 != 0) goto L70
            int r2 = r3.length()
            r4 = 128(0x80, float:1.8E-43)
            if (r2 <= r4) goto L64
            goto L70
        L64:
            boolean r5 = android.text.TextUtils.equals(r0, r3)
            if (r5 != 0) goto L87
            java.lang.String r5 = "vid changed, need sync"
            com.xiaomi.channel.commonutils.logger.b.m22a(r5)
            return r3
        L70:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "recover vid :"
            r2.append(r4)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.xiaomi.channel.commonutils.logger.b.m22a(r2)
            a(r5, r0)
        L87:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "vdevid = "
            r5.append(r0)
            java.lang.String r0 = com.xiaomi.push.i.g
            r5.append(r0)
            java.lang.String r0 = " "
            r5.append(r0)
            r5.append(r3)
            java.lang.String r5 = r5.toString()
            com.xiaomi.channel.commonutils.logger.b.c(r5)
            return r1
        La6:
            java.lang.String r5 = "empty local vid"
            com.xiaomi.channel.commonutils.logger.b.m22a(r5)
            java.lang.String r5 = "F*"
            return r5
        Lae:
            r5 = move-exception
            r1 = r2
        Lb0:
            if (r1 == 0) goto Lb5
            r1.a()
        Lb5:
            throw r5
        Lb6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.i.c(android.content.Context):java.lang.String");
    }

    /* renamed from: c, reason: collision with other method in class */
    private static boolean m440c(Context context) {
        if ((Build.VERSION.SDK_INT >= 29 && context.getApplicationInfo().targetSdkVersion >= 29) || !m.a(context, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE) || l.m547a()) {
            return false;
        }
        boolean z = Build.VERSION.SDK_INT >= 26;
        return !z ? t.m647a(context) : z;
    }

    private static boolean c(String str) {
        return !TextUtils.isEmpty(str) && str.length() <= 15 && str.length() >= 14 && bm.m135b(str) && !bm.c(str);
    }

    public static String d() {
        return (a(Environment.getDataDirectory()) / DownloadConstants.KB) + "KB";
    }

    public static String d(Context context) {
        if (!m441d(context)) {
            return null;
        }
        if (Build.VERSION.SDK_INT > 8 && Build.VERSION.SDK_INT < 26) {
            return Build.SERIAL;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return (String) bh.a("android.os.Build", "getSerial", (Object[]) null);
        }
        return null;
    }

    /* renamed from: d, reason: collision with other method in class */
    private static boolean m441d(Context context) {
        String packageName = context.getPackageName();
        return context.getPackageManager().checkPermission(MsgConstant.PERMISSION_READ_PHONE_STATE, packageName) == 0 || context.getPackageManager().checkPermission("android.permission.READ_PRIVILEGED_PHONE_STATE", packageName) == 0;
    }

    public static String e(Context context) {
        if (b != null) {
            return b;
        }
        try {
            b = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable th) {
            com.xiaomi.channel.commonutils.logger.b.m22a("failure to get androidId: " + th);
        }
        return b;
    }

    public static String f(Context context) {
        int iC = c();
        String strG = g(context);
        while (strG == null) {
            int i = iC - 1;
            if (iC <= 0) {
                break;
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException unused) {
            }
            strG = g(context);
            iC = i;
        }
        return strG;
    }

    public static String g(Context context) {
        String deviceId;
        Object objA;
        Object objA2;
        Object objA3;
        if (l.d()) {
            return "";
        }
        if (a != null) {
            return a;
        }
        try {
            if (m441d(context)) {
                String str = (!l.m547a() || (objA2 = bh.a("miui.telephony.TelephonyManager", "getDefault", new Object[0])) == null || (objA3 = bh.a(objA2, "getMiuiDeviceId", new Object[0])) == null || !(objA3 instanceof String)) ? null : (String) String.class.cast(objA3);
                if (str == null) {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (Build.VERSION.SDK_INT < 26) {
                        deviceId = telephonyManager.getDeviceId();
                    } else {
                        if (1 == telephonyManager.getPhoneType()) {
                            objA = bh.a((Object) telephonyManager, "getImei", (Object[]) null);
                        } else {
                            if (2 == telephonyManager.getPhoneType()) {
                                objA = bh.a((Object) telephonyManager, "getMeid", (Object[]) null);
                            }
                            deviceId = str;
                        }
                        deviceId = (String) objA;
                    }
                } else {
                    deviceId = str;
                }
            } else {
                deviceId = null;
            }
            if (!c(deviceId)) {
                return "";
            }
            a = deviceId;
            return deviceId;
        } catch (Throwable th) {
            com.xiaomi.channel.commonutils.logger.b.m22a("failure to get id:" + th);
            return null;
        }
    }

    public static String h(Context context) {
        int iC = c();
        String strJ = j(context);
        while (strJ == null) {
            int i = iC - 1;
            if (iC <= 0) {
                break;
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException unused) {
            }
            strJ = j(context);
            iC = i;
        }
        return strJ;
    }

    public static String i(Context context) {
        Object objA;
        if (l.d() || Build.VERSION.SDK_INT < 22) {
            return "";
        }
        if (!TextUtils.isEmpty(c)) {
            return c;
        }
        g(context);
        if (TextUtils.isEmpty(a)) {
            return "";
        }
        try {
            if (!m441d(context)) {
                return "";
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            Integer num = (Integer) bh.a((Object) telephonyManager, "getPhoneCount", new Object[0]);
            if (num == null || num.intValue() <= 1) {
                return "";
            }
            String str = null;
            for (int i = 0; i < num.intValue(); i++) {
                if (Build.VERSION.SDK_INT < 26) {
                    objA = bh.a((Object) telephonyManager, "getDeviceId", Integer.valueOf(i));
                } else if (1 == telephonyManager.getPhoneType()) {
                    objA = bh.a((Object) telephonyManager, "getImei", Integer.valueOf(i));
                } else {
                    if (2 == telephonyManager.getPhoneType()) {
                        objA = bh.a((Object) telephonyManager, "getMeid", Integer.valueOf(i));
                    }
                    if (TextUtils.isEmpty(str) && !TextUtils.equals(a, str) && c(str)) {
                        c += str + Constants.ACCEPT_TIME_SEPARATOR_SP;
                    }
                }
                str = (String) objA;
                if (TextUtils.isEmpty(str)) {
                }
            }
            int length = c.length();
            if (length > 0) {
                c = c.substring(0, length - 1);
            }
            return c;
        } catch (Exception e2) {
            com.xiaomi.channel.commonutils.logger.b.m22a("failure to get ids: " + e2);
            return "";
        }
    }

    public static String j(Context context) {
        i(context);
        if (TextUtils.isEmpty(c)) {
            return "";
        }
        String str = "";
        for (String str2 : c.split(Constants.ACCEPT_TIME_SEPARATOR_SP)) {
            if (c(str2)) {
                str = str + bm.a(str2) + Constants.ACCEPT_TIME_SEPARATOR_SP;
            }
        }
        int length = str.length();
        return length > 0 ? str.substring(0, length - 1) : str;
    }

    public static synchronized String k(Context context) {
        if (e != null) {
            return e;
        }
        e = bm.b(e(context) + d(context));
        return e;
    }

    public static synchronized String l(Context context) {
        return bm.b(e(context) + ((String) null));
    }

    public static String m(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperatorName();
    }

    private static String n(Context context) throws Throwable {
        String str;
        u uVarA;
        u uVar = null;
        str = null;
        str = null;
        str = null;
        str = null;
        str = null;
        String str2 = null;
        u uVar2 = null;
        try {
            if (!m440c(context)) {
                com.xiaomi.channel.commonutils.logger.b.m22a("not support read lvdd.");
                return null;
            }
            try {
                File file = new File(new File(Environment.getExternalStorageDirectory(), "/.vdevdir/"), ".vdevidlocal");
                if (file.exists() && file.isFile()) {
                    uVarA = u.a(context, file);
                    try {
                        String strA = y.a(file);
                        if (!TextUtils.isEmpty(strA)) {
                            String[] strArrSplit = strA.split(f);
                            if (strArrSplit.length == 2) {
                                String str3 = strArrSplit[0];
                                try {
                                    if (a(str3) == Integer.parseInt(strArrSplit[1])) {
                                        str2 = str3;
                                    }
                                } catch (Exception unused) {
                                }
                            }
                        }
                        if (TextUtils.isEmpty(str2)) {
                            y.m649a(file);
                            com.xiaomi.channel.commonutils.logger.b.b("lvdd content invalid, remove it.");
                        }
                    } catch (IOException unused2) {
                        String str4 = str2;
                        uVar2 = uVarA;
                        str = str4;
                        com.xiaomi.channel.commonutils.logger.b.m22a("get lvdd failure.");
                        if (uVar2 == null) {
                            return str;
                        }
                        uVar2.a();
                        return str;
                    } catch (Throwable th) {
                        th = th;
                        uVar = uVarA;
                        if (uVar != null) {
                            uVar.a();
                        }
                        throw th;
                    }
                } else {
                    com.xiaomi.channel.commonutils.logger.b.b("lvdf not exists");
                    uVarA = null;
                }
                if (uVarA != null) {
                    uVarA.a();
                }
                return str2;
            } catch (IOException unused3) {
                str = null;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static String o(Context context) throws InterruptedException {
        int iC = c();
        String strG = g(context);
        while (TextUtils.isEmpty(strG)) {
            int i = iC - 1;
            if (iC <= 0) {
                break;
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException unused) {
            }
            strG = g(context);
            iC = i;
        }
        return strG;
    }
}
