package com.huawei.hms.utils;

import android.content.Context;
import com.huawei.a.b.a;
import com.huawei.a.b.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HMSBIInit {
    public void init(Context context, boolean z, boolean z2, boolean z3, String str) {
        checkNonNull(context, "context must not be null.");
        new b.a(context).d(z).b(z2).c(z3).a(0, str).a();
    }

    public void refresh(Context context, boolean z, boolean z2, boolean z3, String str, boolean z4) {
        checkNonNull(context, "context must not be null.");
        new b.a(context).d(z).b(z2).c(z3).a(0, str).a(z4);
    }

    public boolean isInit() {
        return a.b();
    }

    public static <T> T checkNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(str));
    }
}
