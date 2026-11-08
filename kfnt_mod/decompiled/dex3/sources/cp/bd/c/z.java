package cp.bd.c;

import cp.bd.c.t;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class z extends t.d {
    public z(w wVar, t.b bVar) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.a = wVar.b(byteBufferAllocate, bVar.d + (bVar.g * 0) + 28);
    }
}
