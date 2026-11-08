package com.kwad.a.kwai;

import com.kwad.a.kwai.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g extends c.AbstractC0022c {
    public g(f fVar, c.b bVar, long j) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = bVar.c + (j * bVar.e);
        this.a = fVar.c(byteBufferAllocate, j2);
        this.b = fVar.c(byteBufferAllocate, 4 + j2);
        this.c = fVar.c(byteBufferAllocate, 8 + j2);
        this.d = fVar.c(byteBufferAllocate, j2 + 20);
    }
}
