package com.ss.union.game.sdk.vcenter.c.a;

import android.util.Log;
import com.ss.union.game.sdk.core.vapp.VLog;
import com.ss.union.game.sdk.vcenter.b.a.f;
import com.ss.union.game.sdk.vcenter.n;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    public static n a() {
        f fVar = new f();
        fVar.a(new a());
        fVar.a(new b());
        fVar.a(d.b());
        fVar.a(new com.ss.union.game.sdk.vcenter.b.a.b() { // from class: com.ss.union.game.sdk.vcenter.c.a.c.1
            @Override // com.ss.union.game.sdk.vcenter.b.a.b
            public void a(String str, String str2) {
                VLog.d(str, str2);
            }

            @Override // com.ss.union.game.sdk.vcenter.b.a.b
            public void b(String str, String str2) {
                VLog.e(str, str2);
            }

            @Override // com.ss.union.game.sdk.vcenter.b.a.b
            public void a(String str, String str2, Throwable th) {
                VLog.e(str, str2 + Log.getStackTraceString(th));
            }
        });
        return fVar;
    }
}
