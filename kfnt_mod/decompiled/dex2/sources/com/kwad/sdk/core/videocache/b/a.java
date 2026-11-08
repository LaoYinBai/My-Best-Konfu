package com.kwad.sdk.core.videocache.b;

import android.content.Context;
import com.kwad.sdk.core.videocache.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static f a;

    public static f a(Context context) {
        if (a != null) {
            return a;
        }
        f fVarB = b(context);
        a = fVarB;
        return fVarB;
    }

    private static f b(Context context) {
        return new f.a(context).a(104857600L).a();
    }
}
