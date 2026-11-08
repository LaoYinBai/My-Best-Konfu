package com.kwad.a.kwai;

import com.kwad.a.kwai.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends c.b {
    private final f j;

    public d(boolean z, f fVar) {
        this.a = z;
        this.j = fVar;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.b = fVar.d(byteBufferAllocate, 16L);
        this.c = fVar.c(byteBufferAllocate, 28L);
        this.d = fVar.c(byteBufferAllocate, 32L);
        this.e = fVar.d(byteBufferAllocate, 42L);
        this.f = fVar.d(byteBufferAllocate, 44L);
        this.g = fVar.d(byteBufferAllocate, 46L);
        this.h = fVar.d(byteBufferAllocate, 48L);
        this.i = fVar.d(byteBufferAllocate, 50L);
    }

    @Override // com.kwad.a.kwai.c.b
    public c.a a(long j, int i) {
        return new a(this.j, this, j, i);
    }

    @Override // com.kwad.a.kwai.c.b
    public c.AbstractC0022c a(long j) {
        return new g(this.j, this, j);
    }

    @Override // com.kwad.a.kwai.c.b
    public c.d a(int i) {
        return new i(this.j, this, i);
    }
}
