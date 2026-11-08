package com.kwad.sdk.draw.a.a;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdTemplate;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private AdTemplate a;
    private InterfaceC0059a b;

    @Nullable
    private b c;
    private boolean d = false;

    /* renamed from: com.kwad.sdk.draw.a.a.a$a, reason: collision with other inner class name */
    public interface InterfaceC0059a {
        void a();
    }

    public interface b {
        boolean a();
    }

    public a(AdTemplate adTemplate) {
        this.a = adTemplate;
    }

    public void a() {
        if (this.d) {
            return;
        }
        this.d = true;
        if (d.j(this.a).status == 1 || d.j(this.a).status == 2 || d.j(this.a).status == 3) {
            return;
        }
        if ((this.c == null || !this.c.a()) && this.b != null) {
            this.b.a();
        }
    }

    @MainThread
    public void a(InterfaceC0059a interfaceC0059a) {
        this.b = interfaceC0059a;
    }

    @MainThread
    public void a(b bVar) {
        this.c = bVar;
    }
}
