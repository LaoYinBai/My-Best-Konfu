package com.ss.union.game.sdk.common.util;

import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RegexUtils {
    private static final String a = "^[1]\\d{10}$";
    private static final String b = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static final String c = "[a-zA-z]+://[^\\s]*";

    private RegexUtils() {
    }

    public static boolean isMobile(CharSequence charSequence) {
        return isMatch(a, charSequence);
    }

    public static boolean isEmail(CharSequence charSequence) {
        return isMatch(b, charSequence);
    }

    public static boolean isURL(CharSequence charSequence) {
        return isMatch(c, charSequence);
    }

    public static boolean isMatch(String str, CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        try {
            if (charSequence.length() > 0) {
                return Pattern.matches(str, charSequence);
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }
}
