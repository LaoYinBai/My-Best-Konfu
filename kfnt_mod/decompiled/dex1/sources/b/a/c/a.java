package b.a.c;

import b.ac;
import b.ae;
import b.w;
import b.z;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a implements w {

    /* renamed from: a, reason: collision with root package name */
    public final z f793a;

    public a(z zVar) {
        this.f793a = zVar;
    }

    @Override // b.w
    public ae a(w.a aVar) throws IOException {
        b.a.d.g gVar = (b.a.d.g) aVar;
        ac acVarA = gVar.a();
        g gVarG = gVar.g();
        return gVar.a(acVarA, gVarG, gVarG.a(this.f793a, aVar, !acVarA.b().equals("GET")), gVarG.c());
    }
}
