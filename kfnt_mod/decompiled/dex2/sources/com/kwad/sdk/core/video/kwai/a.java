package com.kwad.sdk.core.video.kwai;

import android.media.TimedText;
import com.kwad.sdk.core.video.kwai.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a implements c {
    private c.e a;
    private c.b b;
    private c.a c;
    private c.f d;
    private c.h e;
    private c.InterfaceC0046c f;
    private c.d g;
    private c.g h;

    public void a() {
        this.a = null;
        this.c = null;
        this.b = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
    }

    protected final void a(int i) {
        if (this.c != null) {
            this.c.a(this, i);
        }
    }

    protected final void a(int i, int i2) {
        if (this.e != null) {
            this.e.a(this, i, i2);
        }
    }

    protected final void a(TimedText timedText) {
        if (this.h != null) {
            this.h.a(this, timedText);
        }
    }

    @Override // com.kwad.sdk.core.video.kwai.c
    public void a(c.a aVar) {
        this.c = aVar;
    }

    @Override // com.kwad.sdk.core.video.kwai.c
    public void a(c.b bVar) {
        this.b = bVar;
    }

    @Override // com.kwad.sdk.core.video.kwai.c
    public void a(c.InterfaceC0046c interfaceC0046c) {
        this.f = interfaceC0046c;
    }

    @Override // com.kwad.sdk.core.video.kwai.c
    public void a(c.d dVar) {
        this.g = dVar;
    }

    @Override // com.kwad.sdk.core.video.kwai.c
    public void a(c.e eVar) {
        this.a = eVar;
    }

    @Override // com.kwad.sdk.core.video.kwai.c
    public void a(c.f fVar) {
        this.d = fVar;
    }

    @Override // com.kwad.sdk.core.video.kwai.c
    public void a(c.h hVar) {
        this.e = hVar;
    }

    protected final void b() {
        if (this.a != null) {
            this.a.a(this);
        }
    }

    protected final boolean b(int i, int i2) {
        return this.f != null && this.f.a(this, i, i2);
    }

    protected final void c() {
        if (this.b != null) {
            this.b.a(this);
        }
    }

    protected final boolean c(int i, int i2) {
        return this.g != null && this.g.a(this, i, i2);
    }

    protected final void d() {
        if (this.d != null) {
            this.d.a(this);
        }
    }
}
