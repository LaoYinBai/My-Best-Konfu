package com.ss.union.game.sdk.account.c;

import android.text.TextUtils;
import com.sigmob.sdk.common.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public enum a {
    CN("china", "+86", 11),
    TWN("taiwan", "+886", 10),
    HK("hongkong", "+852", 8),
    Macao("macao", "+853", 8);

    public final String e;
    public final String f;
    public final int g;

    a(String str, String str2, int i) {
        this.e = str;
        this.f = str2;
        this.g = i;
    }

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String strReplace = str.replace(" ", "");
        int length = strReplace.length();
        return CN.f.equals(str2) ? length >= CN.g : (HK.f.equals(str2) || Macao.f.equals(str2)) ? length >= HK.g : TWN.f.equals(str2) ? strReplace.startsWith(Constants.FAIL) ? length >= TWN.g : length >= TWN.g - 1 : length >= CN.g;
    }
}
