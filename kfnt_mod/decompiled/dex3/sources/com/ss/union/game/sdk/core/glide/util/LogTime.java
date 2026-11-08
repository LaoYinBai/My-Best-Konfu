package com.ss.union.game.sdk.core.glide.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.SystemClock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class LogTime {
    private static final double a;

    static {
        a = Build.VERSION.SDK_INT >= 17 ? 1.0d / Math.pow(10.0d, 6.0d) : 1.0d;
    }

    private LogTime() {
    }

    @TargetApi(17)
    public static long getLogTime() {
        if (Build.VERSION.SDK_INT >= 17) {
            return SystemClock.elapsedRealtimeNanos();
        }
        return SystemClock.uptimeMillis();
    }

    public static double getElapsedMillis(long j) {
        return (getLogTime() - j) * a;
    }
}
