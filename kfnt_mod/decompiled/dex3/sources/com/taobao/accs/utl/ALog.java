package com.taobao.accs.utl;

import android.util.Log;
import com.taobao.tlog.adapter.AdapterForTLog;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ALog {
    private static final String REFLECT_TLOG = "com.taobao.tlog.adapter.AdapterForTLog";
    public static volatile boolean isUseTlog = false;
    private static String preTag = "NAccs.";

    public enum Level {
        V,
        D,
        I,
        W,
        E,
        L
    }

    @Deprecated
    public static boolean isPrintLog() {
        return false;
    }

    @Deprecated
    public static void setEnableTLog(boolean z) {
    }

    @Deprecated
    public static void setPrintLog(boolean z) {
    }

    static {
        try {
            Class.forName(REFLECT_TLOG);
            isUseTlog = true;
        } catch (ClassNotFoundException unused) {
            isUseTlog = false;
        }
    }

    @Deprecated
    public static void initALog(String str, int i) {
        preTag = str;
    }

    @Deprecated
    public static void setUseTlog(boolean z) {
        isUseTlog = z;
    }

    public static boolean isPrintLog(Level level) {
        if (!isUseTlog) {
            return true;
        }
        Level levelValueOf = Level.L;
        try {
            levelValueOf = Level.valueOf(AdapterForTLog.getLogLevel());
        } catch (Exception unused) {
        }
        return level.ordinal() >= levelValueOf.ordinal();
    }

    public static void v(String str, String str2, Object... objArr) {
        if (isPrintLog(Level.V)) {
            if (isUseTlog) {
                AdapterForTLog.logv(buildLogTag(str), buildLogMsg(str2, objArr));
            } else {
                Log.v(buildLogTag(str), buildLogMsg(str2, objArr));
            }
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (isPrintLog(Level.D)) {
            if (isUseTlog) {
                AdapterForTLog.logd(buildLogTag(str), buildLogMsg(str2, objArr));
            } else {
                Log.d(buildLogTag(str), buildLogMsg(str2, objArr));
            }
        }
    }

    public static void i(String str, String str2, Object... objArr) {
        if (isPrintLog(Level.I)) {
            if (isUseTlog) {
                AdapterForTLog.logi(buildLogTag(str), buildLogMsg(str2, objArr));
            } else {
                Log.i(buildLogTag(str), buildLogMsg(str2, objArr));
            }
        }
    }

    public static void w(String str, String str2, Object... objArr) {
        if (isPrintLog(Level.W)) {
            if (isUseTlog) {
                AdapterForTLog.logw(buildLogTag(str), buildLogMsg(str2, objArr));
            } else {
                Log.w(buildLogTag(str), buildLogMsg(str2, objArr));
            }
        }
    }

    public static void w(String str, String str2, Throwable th, Object... objArr) {
        if (isPrintLog(Level.W)) {
            if (isUseTlog) {
                AdapterForTLog.logw(buildLogTag(str), buildLogMsg(str2, objArr), th);
            } else {
                Log.w(buildLogTag(str), buildLogMsg(str2, objArr), th);
            }
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (isPrintLog(Level.E)) {
            if (isUseTlog) {
                AdapterForTLog.loge(buildLogTag(str), buildLogMsg(str2, objArr));
            } else {
                Log.e(buildLogTag(str), buildLogMsg(str2, objArr));
            }
        }
    }

    public static void e(String str, String str2, Throwable th, Object... objArr) {
        if (isPrintLog(Level.E)) {
            if (isUseTlog) {
                AdapterForTLog.loge(buildLogTag(str), buildLogMsg(str2, objArr), th);
            } else {
                Log.e(buildLogTag(str), buildLogMsg(str2, objArr), th);
            }
        }
    }

    private static String formatKv(Object obj, Object obj2) {
        StringBuilder sb = new StringBuilder();
        if (obj == null) {
            obj = "";
        }
        sb.append(obj);
        sb.append(Constants.COLON_SEPARATOR);
        if (obj2 == null) {
            obj2 = "";
        }
        sb.append(obj2);
        return sb.toString();
    }

    private static String buildLogTag(String str) {
        return preTag + str;
    }

    private static String buildLogMsg(String str, Object... objArr) {
        if (str == null && objArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(" ");
            sb.append(str);
        }
        if (objArr != null) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if (i2 >= objArr.length) {
                    break;
                }
                sb.append(" ");
                sb.append(formatKv(objArr[i], objArr[i2]));
                i = i2 + 1;
            }
            if (i == objArr.length - 1) {
                sb.append(" ");
                sb.append(objArr[i]);
            }
        }
        return sb.toString();
    }
}
