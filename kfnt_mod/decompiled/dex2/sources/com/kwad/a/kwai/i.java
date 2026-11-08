package com.kwad.a.kwai;

import com.kwad.a.kwai.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class i extends c.d {
    public i(f fVar, c.b bVar, int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.a = fVar.c(byteBufferAllocate, bVar.d + (i * bVar.g) + 28);
    }
}
