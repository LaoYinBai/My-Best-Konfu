package com.kwai.sodler.lib;

import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class k implements com.kwai.sodler.lib.kwai.e {
    private final com.kwai.sodler.lib.kwai.d a;
    private final com.kwai.sodler.lib.kwai.g b;
    private final com.kwai.sodler.lib.kwai.c c;
    private final com.kwai.sodler.lib.kwai.b d;
    private final com.kwai.sodler.lib.ext.c e;
    private final com.kwai.sodler.lib.ext.a f;

    public static abstract class a {
        final com.kwai.sodler.lib.kwai.e a;

        /* renamed from: com.kwai.sodler.lib.k$a$a, reason: collision with other inner class name */
        private static class C0100a extends a {
            public C0100a(com.kwai.sodler.lib.kwai.e eVar) {
                super(eVar);
            }

            @Override // com.kwai.sodler.lib.k.a
            public void a(com.kwai.sodler.lib.kwai.f fVar) {
                this.a.f().a(fVar);
            }
        }

        private static class b extends a {
            b(com.kwai.sodler.lib.kwai.e eVar) {
                super(eVar);
            }

            @Override // com.kwai.sodler.lib.k.a
            public void a(com.kwai.sodler.lib.kwai.f fVar) {
                this.a.c().a(fVar);
            }
        }

        private static class c extends a {
            c(com.kwai.sodler.lib.kwai.e eVar) {
                super(eVar);
            }

            @Override // com.kwai.sodler.lib.k.a
            public void a(com.kwai.sodler.lib.kwai.f fVar) {
                this.a.d().a(fVar);
            }
        }

        public a(com.kwai.sodler.lib.kwai.e eVar) {
            this.a = eVar;
        }

        public static a a(com.kwai.sodler.lib.kwai.e eVar, int i) {
            return i != 1 ? i != 256 ? new b(eVar) : new C0100a(eVar) : new c(eVar);
        }

        public abstract void a(com.kwai.sodler.lib.kwai.f fVar);
    }

    public k(com.kwai.sodler.lib.kwai.d dVar, com.kwai.sodler.lib.kwai.g gVar, com.kwai.sodler.lib.kwai.c cVar, com.kwai.sodler.lib.kwai.b bVar, com.kwai.sodler.lib.ext.c cVar2, com.kwai.sodler.lib.ext.a aVar) {
        this.a = dVar;
        this.b = gVar;
        this.c = cVar;
        this.e = cVar2;
        this.f = aVar;
        this.d = bVar;
    }

    public com.kwai.sodler.lib.kwai.f a(@NonNull com.kwai.sodler.lib.kwai.f fVar, @NonNull a aVar) {
        if (fVar.b() == null) {
            fVar.a(this);
        }
        com.kwai.sodler.lib.a.c("Sodler.manager", "request id = " + fVar.i() + ", state log = " + fVar.d());
        aVar.a(fVar);
        return fVar;
    }

    protected Map a(Map map) {
        return (map == null || map == Collections.EMPTY_MAP) ? new HashMap() : map;
    }

    @Override // com.kwai.sodler.lib.kwai.e
    public com.kwai.sodler.lib.ext.c b() {
        return this.e;
    }

    @Override // com.kwai.sodler.lib.kwai.e
    public com.kwai.sodler.lib.kwai.d c() {
        return this.a;
    }

    @Override // com.kwai.sodler.lib.kwai.e
    public com.kwai.sodler.lib.kwai.g d() {
        return this.b;
    }

    @Override // com.kwai.sodler.lib.kwai.e
    public com.kwai.sodler.lib.kwai.c e() {
        return this.c;
    }

    @Override // com.kwai.sodler.lib.kwai.e
    public com.kwai.sodler.lib.kwai.b f() {
        return this.d;
    }

    @Override // com.kwai.sodler.lib.kwai.e
    public com.kwai.sodler.lib.ext.a g() {
        return this.f;
    }
}
