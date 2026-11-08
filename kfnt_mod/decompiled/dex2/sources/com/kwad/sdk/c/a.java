package com.kwad.sdk.c;

import android.content.Context;
import com.kwad.sdk.c.a.b;
import com.kwad.sdk.c.kwai.f;
import com.kwai.filedownloader.i;
import com.kwai.filedownloader.m;
import com.kwai.filedownloader.r;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements com.kwad.sdk.c.a.b {
    private final Context a;

    public a(Context context) {
        this.a = context;
    }

    @Override // com.kwad.sdk.c.a.b
    public void a(final com.kwad.sdk.c.kwai.d dVar, final b.a aVar) {
        com.kwad.sdk.c.b.b.a(dVar, 1);
        String strD = dVar.d();
        String strC = new f(strD).c();
        if (strC == null) {
            return;
        }
        dVar.d(strC.substring(0, strC.indexOf(".zip")));
        String strB = com.kwad.sdk.c.b.a.b(this.a, dVar.f());
        dVar.c(strB);
        r.a().a(strD).a(dVar).a(strB).a((i) new m() { // from class: com.kwad.sdk.c.a.1
            @Override // com.kwai.filedownloader.m, com.kwai.filedownloader.i
            protected void a(com.kwai.filedownloader.a aVar2, Throwable th) {
                super.a(aVar2, th);
                com.kwad.sdk.c.b.b.a(dVar, 1, th.getMessage());
                if (aVar != null) {
                    aVar.b((com.kwad.sdk.c.kwai.d) aVar2.z());
                }
            }

            @Override // com.kwai.filedownloader.m, com.kwai.filedownloader.i
            protected void c(com.kwai.filedownloader.a aVar2) {
                super.c(aVar2);
                if (aVar != null && aVar2.v() == -3) {
                    aVar.a((com.kwad.sdk.c.kwai.d) aVar2.z());
                    return;
                }
                if (aVar != null) {
                    aVar.b((com.kwad.sdk.c.kwai.d) aVar2.z());
                    com.kwad.sdk.c.b.b.a(dVar, 1, "task.getStatus()=" + ((int) aVar2.v()));
                }
            }
        }).e();
    }
}
