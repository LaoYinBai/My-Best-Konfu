package com.ss.union.game.sdk.account.f;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final String a = "12341234";
    private static final String b = "1122";

    public static boolean a(String str) {
        if (!TextUtils.isEmpty(str) && str.length() >= 8) {
            return a.equals(str.substring(0, 8));
        }
        return false;
    }

    public static boolean b(String str) {
        return !TextUtils.isEmpty(str) && b.equals(str);
    }
}
