package com.apm.insight.runtime.a;

import android.content.Context;
import com.apm.insight.CrashType;
import com.apm.insight.entity.Header;
import com.apm.insight.l.s;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i extends c {
    i(Context context, b bVar, d dVar) {
        super(CrashType.ENSURE, context, bVar, dVar);
    }

    @Override // com.apm.insight.runtime.a.c
    public com.apm.insight.entity.a a(com.apm.insight.entity.a aVar) throws JSONException {
        com.apm.insight.entity.a aVarA = super.a(aVar);
        g(aVarA);
        s.a(aVarA, (Header) null, this.f3009a);
        return aVarA;
    }

    @Override // com.apm.insight.runtime.a.c
    protected boolean a() {
        return false;
    }

    @Override // com.apm.insight.runtime.a.c
    protected boolean c() {
        return false;
    }

    @Override // com.apm.insight.runtime.a.c
    protected boolean d() {
        return false;
    }
}
