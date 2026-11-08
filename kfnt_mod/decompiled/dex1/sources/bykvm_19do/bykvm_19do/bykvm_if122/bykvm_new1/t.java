package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.os.Build;
import android.text.TextUtils;
import com.bytedance.msdk.adapter.util.Logger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class t {

    /* renamed from: a, reason: collision with root package name */
    private static final CharSequence f2027a = "amigo";

    /* renamed from: b, reason: collision with root package name */
    private static final CharSequence f2028b = "funtouch";

    /* renamed from: c, reason: collision with root package name */
    private static ExecutorService f2029c = Executors.newSingleThreadExecutor();
    public static boolean d = false;
    public static boolean e = false;

    public static class a implements Callable<String> {

        /* renamed from: a, reason: collision with root package name */
        private String f2030a;

        public a(String str) {
            this.f2030a = str;
        }

        @Override // java.util.concurrent.Callable
        public String call() throws Exception {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String strB = t.b(this.f2030a);
            Logger.d("RomUtils", "property:" + strB + ",getSystemProperty use time :" + (System.currentTimeMillis() - jCurrentTimeMillis));
            if (!TextUtils.isEmpty(strB)) {
                try {
                    Logger.w("RomUtils", "SP-getPropertyFromSP:" + strB);
                    u.a("rom_info", bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()).b("rom_property_info", strB);
                } catch (Throwable unused) {
                }
            }
            return strB;
        }
    }

    public static String b() {
        return Build.DISPLAY + "_" + c("ro.gn.sv.version");
    }

    public static String c() {
        if (!o()) {
            return "";
        }
        return "coloros_" + c("ro.build.version.opporom") + "_" + Build.DISPLAY;
    }

    public static String d() {
        String strF = f();
        if (strF == null || !strF.toLowerCase().contains("emotionui")) {
            return "";
        }
        return strF + "_" + Build.DISPLAY;
    }

    public static String e() {
        if (!p()) {
            return "";
        }
        return "eui_" + c("ro.letv.release.version") + "_" + Build.DISPLAY;
    }

    public static String f() {
        return c("ro.build.version.emui");
    }

    public static String g() {
        String str = Build.DISPLAY;
        return (str == null || !str.toLowerCase().contains(com.bytedance.b.a.ag)) ? "" : str;
    }

    public static String h() {
        return c("ro.vivo.os.build.display.id") + "_" + c("ro.vivo.product.version");
    }

    public static String i() {
        if (!t()) {
            return "";
        }
        return "miui_" + c("ro.miui.ui.version.name") + "_" + Build.VERSION.INCREMENTAL;
    }

    private static String j() {
        try {
            String strA = u.a("rom_info", bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()).a("rom_property_info", "");
            Logger.i("RomUtils", "get Property From SP...=" + strA);
            return strA;
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String k() {
        if (u()) {
            return l();
        }
        if (t()) {
            return i();
        }
        if (q()) {
            return g();
        }
        if (o()) {
            return c();
        }
        String strD = d();
        if (!TextUtils.isEmpty(strD)) {
            return strD;
        }
        if (r()) {
            return h();
        }
        if (n()) {
            return b();
        }
        if (m()) {
            return a();
        }
        String strE = e();
        return !TextUtils.isEmpty(strE) ? strE : Build.DISPLAY;
    }

    public static String l() {
        if (u()) {
            try {
                return "smartisan_" + c("ro.smartisan.version");
            } catch (Throwable unused) {
            }
        }
        return Build.DISPLAY;
    }

    public static boolean m() {
        String str = Build.MANUFACTURER + Build.BRAND;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.contains("360") || lowerCase.contains("qiku");
    }

    public static boolean n() {
        return !TextUtils.isEmpty(Build.DISPLAY) && Build.DISPLAY.toLowerCase().contains(f2027a);
    }

    public static boolean o() {
        String str = Build.MANUFACTURER;
        return !TextUtils.isEmpty(str) && str.toLowerCase().contains("oppo");
    }

    public static boolean p() {
        return !TextUtils.isEmpty(c("ro.letv.release.version"));
    }

    public static boolean q() {
        return Build.DISPLAY.contains("Flyme") || Build.USER.equals(com.bytedance.b.a.ag);
    }

    public static boolean r() {
        String strC = c("ro.vivo.os.build.display.id");
        return !TextUtils.isEmpty(strC) && strC.toLowerCase().contains(f2028b);
    }

    public static boolean s() {
        try {
            if (TextUtils.isEmpty(Build.BRAND) || !Build.BRAND.toLowerCase().startsWith(com.bytedance.b.a.ah)) {
                if (TextUtils.isEmpty(Build.MANUFACTURER)) {
                    return false;
                }
                if (!Build.MANUFACTURER.toLowerCase().startsWith(com.bytedance.b.a.ah)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean t() {
        if (!e) {
            try {
                if (Class.forName("miui.os.Build") != null) {
                    d = true;
                    e = true;
                    return d;
                }
            } catch (Exception unused) {
            }
            e = true;
        }
        return d;
    }

    public static boolean u() {
        return "smartisan".equalsIgnoreCase(Build.MANUFACTURER) || "smartisan".equalsIgnoreCase(Build.BRAND);
    }

    public static String a() {
        return c("ro.build.uiversion") + "_" + Build.DISPLAY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:41:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String b(java.lang.String r6) throws java.io.IOException {
        /*
            java.lang.String r0 = ""
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L43
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L43
            r2.<init>()     // Catch: java.lang.Throwable -> L43
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch: java.lang.Throwable -> L43
            r2.append(r6)     // Catch: java.lang.Throwable -> L43
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L43
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.lang.Throwable -> L43
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L43
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L43
            java.io.InputStream r4 = r1.getInputStream()     // Catch: java.lang.Throwable -> L43
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L43
            r4 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L43
            java.lang.String r3 = r2.readLine()     // Catch: java.lang.Throwable -> L41
            r1.destroy()     // Catch: java.lang.Throwable -> L3f
            r2.close()     // Catch: java.io.IOException -> L36
            goto L3e
        L36:
            r6 = move-exception
            java.lang.String r0 = "ToolUtils"
            java.lang.String r1 = "Exception while closing InputStream"
            com.bytedance.msdk.adapter.util.Logger.e(r0, r1, r6)
        L3e:
            return r3
        L3f:
            r0 = move-exception
            goto L47
        L41:
            r1 = move-exception
            goto L45
        L43:
            r1 = move-exception
            r2 = 0
        L45:
            r3 = r0
            r0 = r1
        L47:
            java.lang.String r1 = "ToolUtils"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6c
            r4.<init>()     // Catch: java.lang.Throwable -> L6c
            java.lang.String r5 = "Unable to read sysprop "
            r4.append(r5)     // Catch: java.lang.Throwable -> L6c
            r4.append(r6)     // Catch: java.lang.Throwable -> L6c
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L6c
            com.bytedance.msdk.adapter.util.Logger.e(r1, r6, r0)     // Catch: java.lang.Throwable -> L6c
            if (r2 == 0) goto L6b
            r2.close()     // Catch: java.io.IOException -> L63
            goto L6b
        L63:
            r6 = move-exception
            java.lang.String r0 = "ToolUtils"
            java.lang.String r1 = "Exception while closing InputStream"
            com.bytedance.msdk.adapter.util.Logger.e(r0, r1, r6)
        L6b:
            return r3
        L6c:
            r6 = move-exception
            if (r2 == 0) goto L7b
            r2.close()     // Catch: java.io.IOException -> L73
            goto L7b
        L73:
            r0 = move-exception
            java.lang.String r1 = "ToolUtils"
            java.lang.String r2 = "Exception while closing InputStream"
            com.bytedance.msdk.adapter.util.Logger.e(r1, r2, r0)
        L7b:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.t.b(java.lang.String):java.lang.String");
    }

    private static String c(String str) {
        String strJ;
        try {
            strJ = j();
            try {
                if (TextUtils.isEmpty(strJ)) {
                    FutureTask futureTask = new FutureTask(new a(str));
                    f2029c.execute(futureTask);
                    strJ = (String) futureTask.get(1L, TimeUnit.SECONDS);
                }
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            strJ = "";
        }
        return strJ == null ? "" : strJ;
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            str = f();
        }
        return (!TextUtils.isEmpty(str) && str.toLowerCase().startsWith("emotionui")) || s();
    }
}
