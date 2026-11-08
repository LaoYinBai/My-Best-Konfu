package com.kwad.sdk.reward;

import android.os.Looper;
import com.kwad.sdk.utils.ax;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private final Set<com.kwad.sdk.reward.a.g> a;

    private static class a {
        private static final c a = new c();
    }

    private c() {
        this.a = new HashSet();
    }

    public static c a() {
        return a.a;
    }

    private boolean c() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    private void d() {
        if (this.a.size() == 0) {
            return;
        }
        Iterator<com.kwad.sdk.reward.a.g> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    public void a(com.kwad.sdk.reward.a.g gVar) {
        if (gVar != null) {
            this.a.add(gVar);
        }
    }

    public void b() {
        if (c()) {
            d();
        } else {
            ax.a(new Runnable() { // from class: com.kwad.sdk.reward.c.1
                @Override // java.lang.Runnable
                public void run() {
                    c.this.b();
                }
            });
        }
    }

    public void b(com.kwad.sdk.reward.a.g gVar) {
        this.a.remove(gVar);
    }
}
