package anet.channel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import anet.channel.entity.ENV;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.dispatch.DispatchConstants;
import anet.channel.util.ALog;
import anet.channel.util.Utils;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GlobalAppRuntimeInfo {

    /* renamed from: a, reason: collision with root package name */
    private static Context f369a;
    private static String e;
    private static String f;
    private static String g;

    /* renamed from: b, reason: collision with root package name */
    private static ENV f370b = ENV.ONLINE;

    /* renamed from: c, reason: collision with root package name */
    private static String f371c = "";
    private static String d = "";
    private static volatile boolean h = true;
    private static SharedPreferences i = null;
    private static volatile CopyOnWriteArrayList<String> j = null;

    public static void setContext(Context context) {
        f369a = context;
        if (context != null) {
            if (TextUtils.isEmpty(d)) {
                d = Utils.getProcessName(context, Process.myPid());
            }
            if (TextUtils.isEmpty(f371c)) {
                f371c = Utils.getMainProcessName(context);
            }
            if (i == null) {
                i = PreferenceManager.getDefaultSharedPreferences(context);
                f = i.getString("UserId", null);
            }
            ALog.e("awcn.GlobalAppRuntimeInfo", "", null, "CurrentProcess", d, "TargetProcess", f371c);
        }
    }

    public static Context getContext() {
        return f369a;
    }

    public static void setTargetProcess(String str) {
        f371c = str;
    }

    public static boolean isTargetProcess() {
        if (TextUtils.isEmpty(f371c) || TextUtils.isEmpty(d)) {
            return true;
        }
        return f371c.equalsIgnoreCase(d);
    }

    public static String getCurrentProcess() {
        return d;
    }

    public static void setCurrentProcess(String str) {
        d = str;
    }

    public static void setEnv(ENV env) {
        f370b = env;
    }

    public static ENV getEnv() {
        return f370b;
    }

    public static void setTtid(String str) {
        e = str;
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            int iIndexOf = str.indexOf("@");
            String strSubstring = null;
            String strSubstring2 = iIndexOf != -1 ? str.substring(0, iIndexOf) : null;
            String strSubstring3 = str.substring(iIndexOf + 1);
            int iLastIndexOf = strSubstring3.lastIndexOf("_");
            if (iLastIndexOf != -1) {
                String strSubstring4 = strSubstring3.substring(0, iLastIndexOf);
                strSubstring = strSubstring3.substring(iLastIndexOf + 1);
                strSubstring3 = strSubstring4;
            }
            anet.channel.strategy.dispatch.a.a(strSubstring3, strSubstring, strSubstring2);
        } catch (Exception unused) {
        }
    }

    public static String getTtid() {
        return e;
    }

    public static void setUserId(String str) {
        if (f == null || !f.equals(str)) {
            f = str;
            StrategyCenter.getInstance().forceRefreshStrategy(DispatchConstants.getAmdcServerDomain());
            if (i != null) {
                i.edit().putString("UserId", str).apply();
            }
        }
    }

    public static String getUserId() {
        return f;
    }

    public static void setUtdid(String str) {
        if (g == null || !g.equals(str)) {
            g = str;
        }
    }

    public static String getUtdid() {
        if (g == null && f369a != null) {
            g = Utils.getDeviceId(f369a);
        }
        return g;
    }

    public static void setBackground(boolean z) {
        h = z;
    }

    public static boolean isAppBackground() {
        if (f369a == null) {
            return true;
        }
        return h;
    }

    public static void addBucketInfo(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.length() > 32 || str2.length() > 32) {
            return;
        }
        synchronized (GlobalAppRuntimeInfo.class) {
            if (j == null) {
                j = new CopyOnWriteArrayList<>();
            }
            j.add(str);
            j.add(str2);
        }
    }

    public static CopyOnWriteArrayList<String> getBucketInfo() {
        return j;
    }
}
