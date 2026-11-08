package com.ss.union.game.sdk.common.util;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.ss.android.account.model2.BDAccountPlatformEntity;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.union.game.sdk.feedback.picture.select.d;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RomUtils {
    private static final String A = "ro.build.MiFavor_version";
    private static final String B = "ro.rom.version";
    private static final String C = "ro.build.rom.id";
    private static final String D = "unknown";
    private static final String u = "ro.build.version.emui";
    private static final String v = "ro.vivo.os.build.display.id";
    private static final String w = "ro.build.version.incremental";
    private static final String x = "ro.build.version.opporom";
    private static final String y = "ro.letv.release.version";
    private static final String z = "ro.build.uiversion";
    private static final String[] a = {BDAccountPlatformEntity.PLAT_NAME_HUAWEI};
    private static final String[] b = {"vivo"};
    private static final String[] c = {"xiaomi"};
    private static final String[] d = {"oppo"};
    private static final String[] e = {"leeco", "letv"};
    private static final String[] f = {"360", "qiku"};
    private static final String[] g = {"zte"};
    private static final String[] h = {"oneplus"};
    private static final String[] i = {"nubia"};
    private static final String[] j = {"coolpad", "yulong"};
    private static final String[] k = {"lg", "lge"};
    private static final String[] l = {BDAccountPlatformEntity.PLAT_NAME_GOOGLE};
    private static final String[] m = {"samsung"};
    private static final String[] n = {"meizu"};
    private static final String[] o = {"lenovo"};
    private static final String[] p = {"smartisan"};
    private static final String[] q = {"htc"};
    private static final String[] r = {"sony"};
    private static final String[] s = {"gionee", "amigo"};
    private static final String[] t = {"motorola"};
    private static RomInfo E = null;

    private RomUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isHuawei() {
        return a[0].equals(getRomInfo().a);
    }

    public static boolean isVivo() {
        return b[0].equals(getRomInfo().a);
    }

    public static boolean isXiaomi() {
        return c[0].equals(getRomInfo().a);
    }

    public static boolean isOppo() {
        return d[0].equals(getRomInfo().a);
    }

    public static boolean isLeeco() {
        return e[0].equals(getRomInfo().a);
    }

    public static boolean is360() {
        return f[0].equals(getRomInfo().a);
    }

    public static boolean isZte() {
        return g[0].equals(getRomInfo().a);
    }

    public static boolean isOneplus() {
        return h[0].equals(getRomInfo().a);
    }

    public static boolean isNubia() {
        return i[0].equals(getRomInfo().a);
    }

    public static boolean isCoolpad() {
        return j[0].equals(getRomInfo().a);
    }

    public static boolean isLg() {
        return k[0].equals(getRomInfo().a);
    }

    public static boolean isGoogle() {
        return l[0].equals(getRomInfo().a);
    }

    public static boolean isSamsung() {
        return m[0].equals(getRomInfo().a);
    }

    public static boolean isMeizu() {
        return n[0].equals(getRomInfo().a);
    }

    public static boolean isLenovo() {
        return o[0].equals(getRomInfo().a);
    }

    public static boolean isSmartisan() {
        return p[0].equals(getRomInfo().a);
    }

    public static boolean isHtc() {
        return q[0].equals(getRomInfo().a);
    }

    public static boolean isSony() {
        return r[0].equals(getRomInfo().a);
    }

    public static boolean isGionee() {
        return s[0].equals(getRomInfo().a);
    }

    public static boolean isMotorola() {
        return t[0].equals(getRomInfo().a);
    }

    public static RomInfo getRomInfo() {
        if (E != null) {
            return E;
        }
        E = new RomInfo();
        String strB = b();
        String strA = a();
        if (a(strB, strA, a)) {
            E.a = a[0];
            String strA2 = a(u);
            String[] strArrSplit = strA2.split("_");
            if (strArrSplit.length > 1) {
                E.b = strArrSplit[1];
            } else {
                E.b = strA2;
            }
            return E;
        }
        if (a(strB, strA, b)) {
            E.a = b[0];
            E.b = a(v);
            return E;
        }
        if (a(strB, strA, c)) {
            E.a = c[0];
            E.b = a(w);
            return E;
        }
        if (a(strB, strA, d)) {
            E.a = d[0];
            E.b = a(x);
            return E;
        }
        if (a(strB, strA, e)) {
            E.a = e[0];
            E.b = a(y);
            return E;
        }
        if (a(strB, strA, f)) {
            E.a = f[0];
            E.b = a(z);
            return E;
        }
        if (a(strB, strA, g)) {
            E.a = g[0];
            E.b = a(A);
            return E;
        }
        if (a(strB, strA, h)) {
            E.a = h[0];
            E.b = a(B);
            return E;
        }
        if (a(strB, strA, i)) {
            E.a = i[0];
            E.b = a(C);
            return E;
        }
        if (a(strB, strA, j)) {
            E.a = j[0];
        } else if (a(strB, strA, k)) {
            E.a = k[0];
        } else if (a(strB, strA, l)) {
            E.a = l[0];
        } else if (a(strB, strA, m)) {
            E.a = m[0];
        } else if (a(strB, strA, n)) {
            E.a = n[0];
        } else if (a(strB, strA, o)) {
            E.a = o[0];
        } else if (a(strB, strA, p)) {
            E.a = p[0];
        } else if (a(strB, strA, q)) {
            E.a = q[0];
        } else if (a(strB, strA, r)) {
            E.a = r[0];
        } else if (a(strB, strA, s)) {
            E.a = s[0];
        } else if (a(strB, strA, t)) {
            E.a = t[0];
        } else {
            E.a = strA;
        }
        E.b = a("");
        return E;
    }

    private static boolean a(String str, String str2, String... strArr) {
        for (String str3 : strArr) {
            if (str.contains(str3) || str2.contains(str3)) {
                return true;
            }
        }
        return false;
    }

    private static String a() {
        try {
            String str = Build.MANUFACTURER;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    private static String b() {
        try {
            String str = Build.BRAND;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    private static String a(String str) {
        String strB = TextUtils.isEmpty(str) ? "" : b(str);
        if (TextUtils.isEmpty(strB) || strB.equals("unknown")) {
            try {
                String str2 = Build.DISPLAY;
                if (!TextUtils.isEmpty(str2)) {
                    strB = str2.toLowerCase();
                }
            } catch (Throwable unused) {
            }
        }
        return TextUtils.isEmpty(strB) ? "unknown" : strB;
    }

    private static String b(String str) throws Throwable {
        String strC = c(str);
        if (!TextUtils.isEmpty(strC)) {
            return strC;
        }
        String strD = d(str);
        return (TextUtils.isEmpty(strD) && Build.VERSION.SDK_INT < 28) ? e(str) : strD;
    }

    private static String c(String str) throws Throwable {
        BufferedReader bufferedReader;
        String line;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), d.d);
            } catch (IOException unused) {
                return "";
            }
        } catch (IOException unused2) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            line = bufferedReader.readLine();
        } catch (IOException unused3) {
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 == null) {
                return "";
            }
            bufferedReader2.close();
            return "";
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
        if (line != null) {
            try {
                bufferedReader.close();
            } catch (IOException unused5) {
            }
            return line;
        }
        bufferedReader.close();
        return "";
    }

    private static String d(String str) throws IOException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            return properties.getProperty(str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    private static String e(String str) throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class, String.class).invoke(cls, str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    public static class RomInfo {
        private String a;
        private String b;

        public String getName() {
            return this.a;
        }

        public String getVersion() {
            return this.b;
        }

        public String toString() {
            return "RomInfo{name=" + this.a + ", version=" + this.b + "}";
        }
    }
}
