package com.kwad.sdk.reward;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private Set<InterfaceC0068b> a;

    private static class a {
        private static b a = new b();
    }

    /* renamed from: com.kwad.sdk.reward.b$b, reason: collision with other inner class name */
    public interface InterfaceC0068b {
        void onPageClose();
    }

    private b() {
        this.a = new HashSet();
    }

    public static b a() {
        return a.a;
    }

    public void a(InterfaceC0068b interfaceC0068b) {
        if (interfaceC0068b != null) {
            this.a.add(interfaceC0068b);
        }
    }

    public void b() {
        if (this.a.size() == 0) {
            return;
        }
        Iterator<InterfaceC0068b> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().onPageClose();
        }
    }

    public void b(InterfaceC0068b interfaceC0068b) {
        this.a.remove(interfaceC0068b);
    }
}
