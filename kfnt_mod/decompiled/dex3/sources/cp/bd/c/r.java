package cp.bd.c;

import cp.bd.c.t;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class r extends t.a {
    public r(w wVar, t.b bVar, long j, int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = j + (i * 8);
        this.a = wVar.b(byteBufferAllocate, j2);
        this.b = wVar.b(byteBufferAllocate, j2 + 4);
    }
}
