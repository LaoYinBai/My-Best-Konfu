package com.mbridge.msdk.foundation.same.e;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.mbridge.msdk.foundation.same.e.a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: CommonTaskLoader.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    ThreadPoolExecutor a;
    HashMap<Long, a> b;
    WeakReference<Context> c;

    public b(Context context, int i) {
        if (i == 0) {
            this.a = new ThreadPoolExecutor(1, 5, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new ThreadPoolExecutor.DiscardPolicy());
        } else {
            this.a = new ThreadPoolExecutor(i, (i * 2) + 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new ThreadPoolExecutor.DiscardPolicy());
        }
        this.a.allowCoreThreadTimeOut(true);
        this.b = new HashMap<>();
        this.c = new WeakReference<>(context);
    }

    public b(Context context) {
        this.a = new ThreadPoolExecutor(0, Api.BaseClientBuilder.API_PRIORITY_OTHER, 15L, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadPoolExecutor.DiscardPolicy());
        this.a.allowCoreThreadTimeOut(true);
        this.b = new HashMap<>();
        this.c = new WeakReference<>(context);
    }

    public final synchronized void a(a aVar) {
        if (aVar != null) {
            if (this.b.containsKey(Long.valueOf(aVar.getId()))) {
                a aVar2 = this.b.get(Long.valueOf(aVar.getId()));
                if (aVar2 != null) {
                    aVar2.cancel();
                }
                this.b.remove(Long.valueOf(aVar.getId()));
            }
        }
    }

    private synchronized void b(final a aVar, final a.b bVar) {
        this.b.put(Long.valueOf(aVar.getId()), aVar);
        aVar.setonStateChangeListener(new a.b() { // from class: com.mbridge.msdk.foundation.same.e.b.1
            @Override // com.mbridge.msdk.foundation.same.e.a.b
            public final void a(a.EnumC0111a enumC0111a) {
                if (enumC0111a == a.EnumC0111a.CANCEL || enumC0111a == a.EnumC0111a.FINISH) {
                    b.this.b.remove(Long.valueOf(aVar.getId()));
                } else if (enumC0111a == a.EnumC0111a.RUNNING && b.this.c.get() == null) {
                    b.this.a();
                }
                if (bVar != null) {
                    bVar.a(enumC0111a);
                }
            }
        });
    }

    public final synchronized void a() {
        try {
            Iterator<Map.Entry<Long, a>> it = this.b.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().cancel();
            }
            this.b.clear();
        } catch (Exception unused) {
        }
    }

    public final void b(a aVar) {
        b(aVar, null);
        this.a.execute(aVar);
    }

    public final void a(a aVar, a.b bVar) {
        b(aVar, bVar);
        this.a.execute(aVar);
    }
}
