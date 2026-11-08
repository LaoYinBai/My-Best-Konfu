package com.kwad.a.kwai;

import com.kwad.a.kwai.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e extends c.b {
    private final f j;

    public e(boolean z, f fVar) {
        this.a = z;
        this.j = fVar;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.b = fVar.d(byteBufferAllocate, 16L);
        this.c = fVar.b(byteBufferAllocate, 32L);
        this.d = fVar.b(byteBufferAllocate, 40L);
        this.e = fVar.d(byteBufferAllocate, 54L);
        this.f = fVar.d(byteBufferAllocate, 56L);
        this.g = fVar.d(byteBufferAllocate, 58L);
        this.h = fVar.d(byteBufferAllocate, 60L);
        this.i = fVar.d(byteBufferAllocate, 62L);
    }

    @Override // com.kwad.a.kwai.c.b
    public c.a a(long j, int i) {
        return new b(this.j, this, j, i);
    }

    @Override // com.kwad.a.kwai.c.b
    public c.AbstractC0022c a(long j) {
        return new h(this.j, this, j);
    }

    @Override // com.kwad.a.kwai.c.b
    public c.d a(int i) {
        return new j(this.j, this, i);
    }
}
