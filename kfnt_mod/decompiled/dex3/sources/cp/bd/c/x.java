package cp.bd.c;

import cp.bd.c.t;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class x extends t.c {
    public x(w wVar, t.b bVar, long j) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = bVar.c + (j * bVar.e);
        this.a = wVar.b(byteBufferAllocate, j2);
        this.b = wVar.b(byteBufferAllocate, 4 + j2);
        this.c = wVar.b(byteBufferAllocate, 8 + j2);
        this.d = wVar.b(byteBufferAllocate, j2 + 20);
    }
}
