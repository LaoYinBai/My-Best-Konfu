package com.bytedance.pangle.log;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Keep;
import com.bytedance.pangle.g;
import org.json.JSONException;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ZeusLogger {
    public static final String TAG = "Zeus";
    public static final String TAG_ACTIVITY = "Zeus/activity";
    public static final String TAG_DOWNLOAD = "Zeus/download";
    public static final String TAG_INIT = "Zeus/init";
    public static final String TAG_INSTALL = "Zeus/install";
    public static final String TAG_LOAD = "Zeus/load";
    public static final String TAG_PAM = "Zeus/pam";
    public static final String TAG_PPM = "Zeus/ppm";
    public static final String TAG_PROVIDER = "Zeus/provider";
    public static final String TAG_RECEIVER = "Zeus/receiver";
    public static final String TAG_REPORTER = "Zeus/reporter";
    public static final String TAG_RESOURCES = "Zeus/resources";
    public static final String TAG_SERVER = "Zeus/server";
    public static final String TAG_SERVICE = "Zeus/service";
    public static final String TAG_SO = "Zeus/so";
    private static boolean sDebug = true;
    private static boolean sEnableTrace = false;

    public static void setDebug(boolean z) {
        sDebug = z;
    }

    public static boolean isDebug() {
        return sDebug;
    }

    public static void setEnableTrace(boolean z) {
        sEnableTrace = z;
    }

    public static boolean isEnableTrace() {
        return sEnableTrace;
    }

    public static void v(String str) {
        v(null, str);
    }

    public static void v(String str, String str2) {
        String strPrefixTraceInfo = prefixTraceInfo(str2);
        if (sDebug) {
            Log.v(str, strPrefixTraceInfo);
        } else if (g.a().f4632b.getLogger() != null) {
            g.a().f4632b.getLogger().v(str, strPrefixTraceInfo);
        }
    }

    public static void d(String str) {
        d(null, str);
    }

    public static void d(String str, String str2) {
        i(str, str2);
    }

    public static void i(String str) {
        i(null, str);
    }

    public static void i(String str, String str2) {
        String strPrefixTraceInfo = prefixTraceInfo(str2);
        if (sDebug) {
            Log.i(str, strPrefixTraceInfo);
        } else if (g.a().f4632b.getLogger() != null) {
            g.a().f4632b.getLogger().i(str, strPrefixTraceInfo);
        }
    }

    public static void w(String str) {
        w(null, str);
    }

    public static void w(String str, String str2) {
        String strPrefixTraceInfo = prefixTraceInfo(str2);
        if (sDebug) {
            Log.w(str, strPrefixTraceInfo);
        } else if (g.a().f4632b.getLogger() != null) {
            g.a().f4632b.getLogger().w(str, strPrefixTraceInfo);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        String strPrefixTraceInfo = prefixTraceInfo(str2);
        if (sDebug) {
            Log.w(str, strPrefixTraceInfo, th);
        } else if (g.a().f4632b.getLogger() != null) {
            g.a().f4632b.getLogger().w(str, strPrefixTraceInfo, th);
        }
    }

    public static void e(String str, String str2) throws JSONException {
        e(str, str2, null, null);
    }

    public static void e(String str, String str2, String str3) throws JSONException {
        e(str, str2, str3, null);
    }

    public static void e(String str, String str2, Throwable th) {
        e(str, str2, null, th);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0045 A[Catch: JSONException -> 0x0043, TryCatch #0 {JSONException -> 0x0043, blocks: (B:10:0x0031, B:12:0x0039, B:16:0x004c, B:18:0x0052, B:19:0x0057, B:15:0x0045), top: B:24:0x0031 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void e(java.lang.String r3, java.lang.String r4, java.lang.String r5, java.lang.Throwable r6) throws org.json.JSONException {
        /*
            java.lang.String r4 = prefixTraceInfo(r4)
            boolean r0 = com.bytedance.pangle.log.ZeusLogger.sDebug
            if (r0 == 0) goto Lc
            android.util.Log.e(r3, r4, r6)
            goto L25
        Lc:
            com.bytedance.pangle.g r0 = com.bytedance.pangle.g.a()
            com.bytedance.pangle.ZeusParam r0 = r0.f4632b
            com.bytedance.pangle.log.IZeusLogger r0 = r0.getLogger()
            if (r0 == 0) goto L25
            com.bytedance.pangle.g r0 = com.bytedance.pangle.g.a()
            com.bytedance.pangle.ZeusParam r0 = r0.f4632b
            com.bytedance.pangle.log.IZeusLogger r0 = r0.getLogger()
            r0.e(r3, r4, r6)
        L25:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            if (r3 == 0) goto L45
            java.lang.String r2 = "Zeus"
            boolean r2 = r3.startsWith(r2)     // Catch: org.json.JSONException -> L43
            if (r2 == 0) goto L45
            java.lang.String r2 = "log_tag"
            java.lang.String r3 = com.bytedance.pangle.log.d.a(r3)     // Catch: org.json.JSONException -> L43
            r0.putOpt(r2, r3)     // Catch: org.json.JSONException -> L43
            goto L4c
        L43:
            r3 = move-exception
            goto L6a
        L45:
            java.lang.String r3 = "log_tag"
            java.lang.String r2 = "other"
            r0.putOpt(r3, r2)     // Catch: org.json.JSONException -> L43
        L4c:
            boolean r3 = android.text.TextUtils.isEmpty(r5)     // Catch: org.json.JSONException -> L43
            if (r3 != 0) goto L57
            java.lang.String r3 = "plugin_package_name"
            r0.putOpt(r3, r5)     // Catch: org.json.JSONException -> L43
        L57:
            java.lang.String r3 = "message"
            java.lang.String r4 = com.bytedance.pangle.log.d.a(r4)     // Catch: org.json.JSONException -> L43
            r1.putOpt(r3, r4)     // Catch: org.json.JSONException -> L43
            java.lang.String r3 = "throwable"
            java.lang.String r4 = com.bytedance.pangle.log.d.a(r6)     // Catch: org.json.JSONException -> L43
            r1.putOpt(r3, r4)     // Catch: org.json.JSONException -> L43
            goto L6d
        L6a:
            r3.printStackTrace()
        L6d:
            java.lang.String r3 = com.bytedance.pangle.b.b.i
            r4 = 0
            com.bytedance.pangle.log.d.a(r3, r0, r4, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.log.ZeusLogger.e(java.lang.String, java.lang.String, java.lang.String, java.lang.Throwable):void");
    }

    private static String prefixTraceInfo(String str) {
        if (!sEnableTrace) {
            return str;
        }
        return str + getTraceInfo();
    }

    private static String getTraceInfo() {
        try {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            StackTraceElement stackTraceElement = null;
            int i = 1;
            while (true) {
                if (i >= stackTrace.length) {
                    break;
                }
                if (!TextUtils.equals(stackTrace[i].getClassName(), ZeusLogger.class.getName())) {
                    stackTraceElement = stackTrace[i];
                    break;
                }
                i++;
            }
            if (stackTraceElement == null) {
                return "\t\t[No Trace Info]";
            }
            return "\t\t[" + stackTraceElement.toString() + "]";
        } catch (Exception e) {
            e.printStackTrace();
            return "\t\t[No Trace Info]";
        }
    }
}
