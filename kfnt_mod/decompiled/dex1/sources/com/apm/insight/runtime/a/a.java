package com.apm.insight.runtime.a;

import android.content.Context;
import com.apm.insight.CrashType;
import com.apm.insight.entity.Header;
import com.apm.insight.l.s;
import com.bytedance.pangle.provider.ContentProviderManager;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class a extends c {
    a(Context context, b bVar, d dVar) {
        super(CrashType.ANR, context, bVar, dVar);
    }

    @Override // com.apm.insight.runtime.a.c
    public com.apm.insight.entity.a a(com.apm.insight.entity.a aVar) throws JSONException {
        com.apm.insight.entity.a aVarA = super.a(aVar);
        Header headerA = Header.a(this.f3010b);
        Header.a(headerA);
        Header.b(headerA);
        headerA.c();
        headerA.d();
        headerA.e();
        aVarA.a(headerA);
        aVarA.a(ContentProviderManager.PLUGIN_PROCESS_NAME, (Object) com.apm.insight.l.a.c(this.f3010b));
        s.a(aVarA, headerA, this.f3009a);
        return aVarA;
    }

    @Override // com.apm.insight.runtime.a.c
    protected boolean a() {
        return true;
    }
}
