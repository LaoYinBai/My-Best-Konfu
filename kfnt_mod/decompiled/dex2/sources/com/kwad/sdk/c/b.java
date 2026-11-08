package com.kwad.sdk.c;

import android.content.Context;
import com.kwad.sdk.utils.q;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private final Context a;

    public b(Context context) {
        this.a = context;
    }

    public String a(com.kwad.sdk.c.kwai.d dVar, boolean z) {
        com.kwad.sdk.c.b.b.a(dVar, 2);
        String strB = com.kwad.sdk.c.b.a.b(this.a, dVar.f());
        try {
            com.kwad.sdk.c.b.a.a(strB, com.kwad.sdk.c.b.a.a(this.a, dVar.f()));
        } catch (Exception e) {
            e.printStackTrace();
            com.kwad.sdk.c.b.b.a(dVar, 2, e.getMessage());
        }
        if (strB != null) {
            q.c(new File(strB));
        }
        return dVar.f();
    }
}
