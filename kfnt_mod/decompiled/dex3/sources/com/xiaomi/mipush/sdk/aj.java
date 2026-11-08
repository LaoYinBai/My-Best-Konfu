package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.l;
import com.xiaomi.push.bh;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class aj {
    public static AbstractPushManager a(Context context, e eVar) {
        return b(context, eVar);
    }

    private static AbstractPushManager b(Context context, e eVar) {
        l.a aVarM85a = l.m85a(eVar);
        if (aVarM85a == null || TextUtils.isEmpty(aVarM85a.a) || TextUtils.isEmpty(aVarM85a.b)) {
            return null;
        }
        return (AbstractPushManager) bh.a(aVarM85a.a, aVarM85a.b, context);
    }
}
