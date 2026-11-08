package com.kwad.sdk.core.f.kwai;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class i {
    private Context a;

    public i(Context context) {
        this.a = context;
    }

    public String a() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        String str = "";
        try {
            Class<?> cls = Class.forName("com.android.id.impl.IdProviderImpl");
            String str2 = (String) cls.getMethod("getOAID", Context.class).invoke(cls.newInstance(), this.a);
            try {
                com.kwad.sdk.core.d.a.c("XiaomiDeviceIDHelper", "getOAID oaid:" + str2);
                return str2;
            } catch (Exception e) {
                e = e;
                str = str2;
                com.kwad.sdk.core.d.a.c("XiaomiDeviceIDHelper", "getOAID fail");
                com.kwad.sdk.core.d.a.b(e);
                return str;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }
}
