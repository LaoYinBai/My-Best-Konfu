package com.alipay.sdk.interior;

import android.content.Context;
import android.os.SystemClock;
import com.alipay.sdk.app.statistic.a;
import com.alipay.sdk.sys.b;
import com.alipay.sdk.util.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class Log {

    /* renamed from: a, reason: collision with root package name */
    private static long f2605a;

    public interface ISdkLogCallback {
        void onLogLine(String str);
    }

    public static void setupLogCallback(ISdkLogCallback iSdkLogCallback) {
        c.a(iSdkLogCallback);
    }

    public static boolean forcedLogReport(Context context) {
        try {
            b.a().a(context);
            long jElapsedRealtime = SystemClock.elapsedRealtime() / 1000;
            if (jElapsedRealtime - f2605a < 600) {
                return false;
            }
            f2605a = jElapsedRealtime;
            a.a(context);
            return true;
        } catch (Exception e) {
            c.a(e);
            return false;
        }
    }
}
