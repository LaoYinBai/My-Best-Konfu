package com.kwad.sdk.reward;

import android.os.Looper;
import com.kwad.sdk.utils.ax;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    private final Set<com.kwad.sdk.reward.a.h> a;

    private static class a {
        private static final d a = new d();
    }

    private d() {
        this.a = new HashSet();
    }

    public static d a() {
        return a.a;
    }

    private boolean c() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    private void d() {
        if (this.a.size() == 0) {
            return;
        }
        Iterator<com.kwad.sdk.reward.a.h> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    public void a(com.kwad.sdk.reward.a.h hVar) {
        if (hVar != null) {
            this.a.add(hVar);
        }
    }

    public void b() {
        if (c()) {
            d();
        } else {
            ax.a(new Runnable() { // from class: com.kwad.sdk.reward.d.1
                @Override // java.lang.Runnable
                public void run() {
                    d.this.b();
                }
            });
        }
    }

    public void b(com.kwad.sdk.reward.a.h hVar) {
        this.a.remove(hVar);
    }
}
