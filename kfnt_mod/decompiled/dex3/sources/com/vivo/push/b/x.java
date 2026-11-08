package com.vivo.push.b;

import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class x extends com.vivo.push.g {
    public HashMap<String, String> c;
    public long d;

    public x() {
        super(2012);
    }

    public x(long j) {
        this();
        this.d = j;
    }

    @Override // com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        aVar.a("ReporterCommand.EXTRA_PARAMS", this.c);
        aVar.a("ReporterCommand.EXTRA_REPORTER_TYPE", this.d);
    }

    @Override // com.vivo.push.g
    public final String toString() {
        return "ReporterCommand（" + this.d + com.umeng.message.proguard.l.t;
    }

    @Override // com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        this.c = (HashMap) (aVar.a == null ? null : aVar.a.getSerializable("ReporterCommand.EXTRA_PARAMS"));
        this.d = aVar.b("ReporterCommand.EXTRA_REPORTER_TYPE", this.d);
    }
}
