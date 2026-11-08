package com.apm.insight.runtime.a;

import android.content.Context;
import com.apm.insight.CrashType;
import com.apm.insight.entity.Header;
import com.apm.insight.l.s;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class k extends c {
    k(Context context, b bVar, d dVar) {
        super(CrashType.LAUNCH, context, bVar, dVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.apm.insight.runtime.a.c
    public com.apm.insight.entity.a a(int i, com.apm.insight.entity.a aVar) throws JSONException {
        com.apm.insight.entity.a aVarA = super.a(i, aVar);
        if (i != 5) {
            switch (i) {
                case 0:
                    Header headerA = Header.a(this.f3010b);
                    headerA.c();
                    aVarA.a(headerA);
                    s.a(aVarA, headerA, this.f3009a);
                    break;
                case 1:
                    Header headerI = aVarA.i();
                    headerI.d();
                    headerI.e();
                    break;
                case 2:
                    Header.a(aVarA.i());
                    try {
                        aVarA.i().f().put("launch_did", com.apm.insight.i.a.a(this.f3010b));
                        break;
                    } catch (Throwable unused) {
                        break;
                    }
            }
        } else {
            Header.b(aVarA.i());
        }
        return aVarA;
    }
}
