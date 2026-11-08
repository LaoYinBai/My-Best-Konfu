package com.kwad.a.kwai;

import com.kwad.a.kwai.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h extends c.AbstractC0022c {
    public h(f fVar, c.b bVar, long j) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = bVar.c + (j * bVar.e);
        this.a = fVar.c(byteBufferAllocate, j2);
        this.b = fVar.b(byteBufferAllocate, 8 + j2);
        this.c = fVar.b(byteBufferAllocate, 16 + j2);
        this.d = fVar.b(byteBufferAllocate, j2 + 40);
    }
}
