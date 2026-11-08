package a.a.c.a.a;

import a.a.c.a.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j extends c.d {
    public j(f fVar, c.b bVar, int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(bVar.f342a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        fVar.a(byteBufferAllocate, bVar.f344c + (i * bVar.f) + 44, 4);
        this.f348a = byteBufferAllocate.getInt() & 4294967295L;
    }
}
