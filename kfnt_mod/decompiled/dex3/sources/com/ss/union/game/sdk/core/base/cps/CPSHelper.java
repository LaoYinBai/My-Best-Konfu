package com.ss.union.game.sdk.core.base.cps;

import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CPSHelper {
    public static boolean isEnable() {
        return Cps.getInstance().isEnable();
    }

    public static boolean isPushEnable() {
        return Cps.getInstance().isPushEnable();
    }

    public static boolean isSimpleCpsChannel() {
        return Cps.getInstance().isSimpleCpsChannel();
    }

    public static String replace(String str) {
        return Cps.getInstance().cpReplace(str);
    }

    public static String getAdCpsCodeId(String str) {
        return Cps.getInstance().getAdCpsCodeId(str);
    }
}
