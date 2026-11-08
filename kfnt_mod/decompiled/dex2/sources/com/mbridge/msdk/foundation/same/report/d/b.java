package com.mbridge.msdk.foundation.same.report.d;

import com.mbridge.msdk.foundation.same.net.f;
import com.mbridge.msdk.foundation.same.net.k;
import com.mbridge.msdk.foundation.tools.s;

/* compiled from: ReportResponseHandler.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b extends f<String> {
    private static final String a = "b";

    public abstract void a(String str);

    public abstract void b(String str);

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(k<String> kVar) {
        if (kVar != null) {
            a(kVar.a);
        }
    }

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(com.mbridge.msdk.foundation.same.net.a.a aVar) {
        s.d(a, "errorCode = " + aVar.a);
        b(com.mbridge.msdk.foundation.same.net.g.a.a(aVar.a));
    }
}
