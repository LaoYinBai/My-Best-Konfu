package com.kwad.a.kwai;

import com.kwad.a.kwai.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends c.a {
    public a(f fVar, c.b bVar, long j, int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = j + (i * 8);
        this.a = fVar.c(byteBufferAllocate, j2);
        this.b = fVar.c(byteBufferAllocate, j2 + 4);
    }
}
