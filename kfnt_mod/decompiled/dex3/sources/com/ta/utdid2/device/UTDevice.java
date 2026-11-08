package com.ta.utdid2.device;

import android.content.Context;
import com.ta.utdid2.a.a.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UTDevice {
    @Deprecated
    public static String getUtdid(Context context) {
        return d(context);
    }

    @Deprecated
    public static String getUtdidForUpdate(Context context) {
        return e(context);
    }

    private static String d(Context context) {
        a aVarB = b.b(context);
        return (aVarB == null || g.m6a(aVarB.f())) ? "ffffffffffffffffffffffff" : aVarB.f();
    }

    private static String e(Context context) {
        String strH = c.a(context).h();
        return (strH == null || g.m6a(strH)) ? "ffffffffffffffffffffffff" : strH;
    }
}
