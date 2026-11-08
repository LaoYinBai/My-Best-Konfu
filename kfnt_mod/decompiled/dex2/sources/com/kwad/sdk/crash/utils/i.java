package com.kwad.sdk.crash.utils;

import com.kwad.v8.Platform;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class i {
    private static final Object a = new Object();
    private static final Object b = new Object();
    private static List<String> c = null;
    private static String d = null;
    private static SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static char[] f = new char[29];
    private static char[] g = new char[29];

    public static String a(long j) {
        return j <= 0 ? Platform.UNKNOWN : e.format(new Date(j));
    }
}
