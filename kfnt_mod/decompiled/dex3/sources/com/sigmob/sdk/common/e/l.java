package com.sigmob.sdk.common.e;

import android.os.Handler;
import com.sigmob.volley.m;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class l extends com.sigmob.volley.m {
    private final Map<com.sigmob.volley.l<?>, a> a;

    class a {
        final Handler a;
        final Runnable b;

        void a() {
            this.a.removeCallbacks(this.b);
        }
    }

    l(com.sigmob.volley.f fVar, int i) {
        super(fVar, i);
        this.a = new HashMap(10);
    }

    public void a() {
        a(new m.a() { // from class: com.sigmob.sdk.common.e.l.1
            @Override // com.sigmob.volley.m.a
            public boolean a(com.sigmob.volley.l<?> lVar) {
                return true;
            }
        });
    }

    @Override // com.sigmob.volley.m
    public void a(m.a aVar) {
        super.a(aVar);
        Iterator<Map.Entry<com.sigmob.volley.l<?>, a>> it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<com.sigmob.volley.l<?>, a> next = it.next();
            if (aVar.a(next.getKey())) {
                next.getKey().j();
                next.getValue().a();
                it.remove();
            }
        }
    }
}
