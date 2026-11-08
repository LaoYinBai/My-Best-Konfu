package com.kwad.sdk.utils;

import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class l {
    public static String a(long j, boolean z) {
        String[] strArr = {" B", " KB", " MB", " GB", " TB", " PB", " EB", " ZB", " YB"};
        if (j <= 1) {
            return j + "B";
        }
        double d = j;
        double d2 = 1024;
        int iLog = (int) (Math.log(d) / Math.log(d2));
        double dPow = j > 1024 ? d / Math.pow(d2, iLog) : d / d2;
        return z ? String.format(Locale.ENGLISH, "%.1f%s", Double.valueOf(dPow), strArr[iLog]) : String.format(Locale.ENGLISH, "%.1f", Double.valueOf(dPow));
    }
}
