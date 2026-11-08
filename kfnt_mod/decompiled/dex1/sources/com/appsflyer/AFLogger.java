package com.appsflyer;

import android.util.Log;
import androidx.annotation.NonNull;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AFLogger {

    /* renamed from: ˋ, reason: contains not printable characters */
    private static long f73 = System.currentTimeMillis();

    public enum LogLevel {
        NONE(0),
        ERROR(1),
        WARNING(2),
        INFO(3),
        DEBUG(4),
        VERBOSE(5);


        /* renamed from: ˋ, reason: contains not printable characters */
        private int f75;

        LogLevel(int i) {
            this.f75 = i;
        }

        public final int getLevel() {
            return this.f75;
        }
    }

    public static void afInfoLog(String str, boolean z) {
        if (LogLevel.INFO.getLevel() <= AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel())) {
            Log.i(AppsFlyerLib.LOG_TAG, m49(str, false));
        }
        if (z) {
            r.m212().m227("I", m49(str, true));
        }
    }

    public static void resetDeltaTime() {
        f73 = System.currentTimeMillis();
    }

    @NonNull
    /* renamed from: ˎ, reason: contains not printable characters */
    private static String m49(String str, boolean z) {
        if (!z && LogLevel.VERBOSE.getLevel() > AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel())) {
            return str;
        }
        StringBuilder sb = new StringBuilder("(");
        sb.append(m50(System.currentTimeMillis() - f73));
        sb.append(") [");
        sb.append(Thread.currentThread().getName());
        sb.append("] ");
        sb.append(str);
        return sb.toString();
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    private static void m52(String str, Throwable th, boolean z) {
        if ((LogLevel.ERROR.getLevel() <= AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel())) && z) {
            Log.e(AppsFlyerLib.LOG_TAG, m49(str, false), th);
        }
        r.m212().m221(th);
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    static void m48(String str) {
        if (LogLevel.WARNING.getLevel() <= AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel())) {
            Log.w(AppsFlyerLib.LOG_TAG, m49(str, false));
        }
        r.m212().m227("W", m49(str, true));
    }

    public static void afRDLog(String str) {
        if (LogLevel.VERBOSE.getLevel() <= AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel())) {
            Log.v(AppsFlyerLib.LOG_TAG, m49(str, false));
        }
        r.m212().m227("V", m49(str, true));
    }

    public static void afInfoLog(String str) {
        afInfoLog(str, true);
    }

    public static void afErrorLog(String str, Throwable th) {
        m52(str, th, false);
    }

    public static void afErrorLog(String str, Throwable th, boolean z) {
        m52(str, th, z);
    }

    public static void afWarnLog(String str) {
        m48(str);
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    private static String m50(long j) {
        long hours = TimeUnit.MILLISECONDS.toHours(j);
        long millis = j - TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long millis2 = millis - TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis2);
        return String.format(Locale.getDefault(), "%02d:%02d:%02d:%03d", Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds), Long.valueOf(TimeUnit.MILLISECONDS.toMillis(millis2 - TimeUnit.SECONDS.toMillis(seconds))));
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    static void m51(String str) {
        if (!AppsFlyerProperties.getInstance().isLogsDisabledCompletely()) {
            Log.d(AppsFlyerLib.LOG_TAG, m49(str, false));
        }
        r.m212().m227("F", str);
    }

    public static void afDebugLog(String str) {
        if (LogLevel.DEBUG.getLevel() <= AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel())) {
            Log.d(AppsFlyerLib.LOG_TAG, m49(str, false));
        }
        r.m212().m227("D", m49(str, true));
    }
}
