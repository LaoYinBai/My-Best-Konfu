package com.sigmob.sdk.b;

import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    public final File a;
    public final com.sigmob.sdk.b.a.c b;
    public final com.sigmob.sdk.b.a.a c;
    public final com.sigmob.sdk.b.c.c d;
    public final com.sigmob.sdk.b.b.b e;

    c(File file, com.sigmob.sdk.b.a.c cVar, com.sigmob.sdk.b.a.a aVar, com.sigmob.sdk.b.c.c cVar2, com.sigmob.sdk.b.b.b bVar) {
        this.a = file;
        this.b = cVar;
        this.c = aVar;
        this.d = cVar2;
        this.e = bVar;
    }

    File a(String str) {
        return new File(this.a, this.b.a(str));
    }
}
