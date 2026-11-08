package com.alipay.security.mobile.module.http.v2;

import android.content.Context;
import com.alipay.security.mobile.module.http.d;
import com.alipay.security.mobile.module.http.model.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements a {

    /* renamed from: a, reason: collision with root package name */
    private static a f2736a;

    /* renamed from: b, reason: collision with root package name */
    private static com.alipay.security.mobile.module.http.a f2737b;

    public static a a(Context context, String str) {
        if (context == null) {
            return null;
        }
        if (f2736a == null) {
            f2737b = d.a(context, str);
            f2736a = new b();
        }
        return f2736a;
    }

    @Override // com.alipay.security.mobile.module.http.v2.a
    public c a(com.alipay.security.mobile.module.http.model.d dVar) {
        return com.alipay.security.mobile.module.http.model.b.a(f2737b.a(com.alipay.security.mobile.module.http.model.b.a(dVar)));
    }

    @Override // com.alipay.security.mobile.module.http.v2.a
    public boolean a(String str) {
        return f2737b.a(str);
    }
}
