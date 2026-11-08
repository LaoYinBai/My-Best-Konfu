package com.kwad.sdk.utils;

import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class at {
    private static final SimpleDateFormat a = new SimpleDateFormat("MM/dd", Locale.US);
    private static final SimpleDateFormat b = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
    private static final SimpleDateFormat c = new SimpleDateFormat("MM月dd日", Locale.US);
    private static final SimpleDateFormat d = new SimpleDateFormat("yyyy年MM月dd日", Locale.US);
    private static final SimpleDateFormat e = new SimpleDateFormat("HH:mm", Locale.US);
    private static final SimpleDateFormat f = new SimpleDateFormat("MM-dd", Locale.US);
    private static final SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    @Deprecated
    public static boolean a(String str) {
        return TextUtils.isEmpty(str) || "null".equalsIgnoreCase(str);
    }

    public static boolean a(String str, String str2) {
        return !TextUtils.isEmpty(str) && str.equals(str2);
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches(".*\\.kpg.*");
    }
}
