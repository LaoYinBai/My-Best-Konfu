package com.mbridge.msdk.foundation.download.utils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class Utils {
    public static int getDownloadRate(long j, long j2) {
        return (int) (((j2 * 1.0d) / j) * 1.0d * 100.0d);
    }

    private Utils() {
    }
}
