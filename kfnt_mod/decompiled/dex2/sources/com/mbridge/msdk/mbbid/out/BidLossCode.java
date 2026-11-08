package com.mbridge.msdk.mbbid.out;

import com.kwai.sodler.lib.ext.PluginError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BidLossCode {
    private static int a;

    private BidLossCode(int i) {
        a = i;
    }

    public static BidLossCode bidTimeOut() {
        return new BidLossCode(2);
    }

    public static BidLossCode bidPriceNotHighest() {
        return new BidLossCode(102);
    }

    public static BidLossCode bidWinButNotShow() {
        return new BidLossCode(PluginError.ERROR_INS_NOT_FOUND);
    }

    public int getCurrentCode() {
        return a;
    }
}
