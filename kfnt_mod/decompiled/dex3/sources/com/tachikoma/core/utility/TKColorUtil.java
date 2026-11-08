package com.tachikoma.core.utility;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKColorUtil {
    public static int getOpacityFromColor(int i) {
        int i2 = i >>> 24;
        if (i2 == 255) {
            return -1;
        }
        return i2 == 0 ? -2 : -3;
    }

    public static int multiplyColorAlpha(int i, int i2) {
        if (i2 == 255) {
            return i;
        }
        if (i2 == 0) {
            return i & 16777215;
        }
        return (i & 16777215) | ((((i >>> 24) * (i2 + (i2 >> 7))) >> 8) << 24);
    }

    public static String rgba2argb(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.charAt(0) != '#' && (str.length() == 6 || str.length() == 8)) {
            str = "#" + str;
        }
        if (str.length() != 9) {
            return str;
        }
        String strSubstring = str.substring(1, 7);
        return "#" + str.substring(7, 9) + strSubstring;
    }
}
