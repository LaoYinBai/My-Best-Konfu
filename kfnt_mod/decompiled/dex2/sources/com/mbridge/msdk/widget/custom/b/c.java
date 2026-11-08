package com.mbridge.msdk.widget.custom.b;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import java.io.File;

/* compiled from: InflaterUtil.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    private static c a;
    private com.mbridge.msdk.widget.custom.a.b b;

    public static c a(Context context) {
        if (a == null) {
            a = new c(context);
        }
        return a;
    }

    private c(Context context) {
        this.b = null;
        this.b = com.mbridge.msdk.widget.custom.a.b.a();
        this.b.a("");
    }

    public final View a(String str) {
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return null;
        }
        return this.b.f(str);
    }
}
