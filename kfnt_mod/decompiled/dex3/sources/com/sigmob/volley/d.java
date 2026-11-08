package com.sigmob.volley;

import android.os.Handler;
import com.tachikoma.core.component.input.ReturnKeyType;
import java.util.concurrent.Executor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d implements o {
    private final Executor a;

    private static class a implements Runnable {
        private final l a;
        private final n b;
        private final Runnable c;

        public a(l lVar, n nVar, Runnable runnable) {
            this.a = lVar;
            this.b = nVar;
            this.c = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a.k()) {
                this.a.b("canceled-at-delivery");
                return;
            }
            if (this.b.a()) {
                this.a.a((l) this.b.a);
            } else {
                this.a.a(this.b.c);
            }
            if (this.b.d) {
                this.a.a("intermediate-response");
            } else {
                this.a.b(ReturnKeyType.DONE);
            }
            if (this.c != null) {
                this.c.run();
            }
        }
    }

    public d(final Handler handler) {
        this.a = new Executor() { // from class: com.sigmob.volley.d.1
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                handler.post(runnable);
            }
        };
    }

    @Override // com.sigmob.volley.o
    public void a(l<?> lVar, n<?> nVar) {
        a(lVar, nVar, null);
    }

    public void a(l<?> lVar, n<?> nVar, Runnable runnable) {
        lVar.s();
        lVar.a("post-response");
        this.a.execute(new a(lVar, nVar, runnable));
    }

    @Override // com.sigmob.volley.o
    public void a(l<?> lVar, s sVar) {
        lVar.a("post-error");
        this.a.execute(new a(lVar, n.a(sVar), null));
    }
}
