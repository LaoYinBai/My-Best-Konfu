package a.a.c.a.a;

import a.a.c.a.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends c.a {
    public a(f fVar, c.b bVar, long j, int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(bVar.f342a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = j + (i * 8);
        fVar.a(byteBufferAllocate, j2, 4);
        this.f340a = byteBufferAllocate.getInt() & 4294967295L;
        fVar.a(byteBufferAllocate, j2 + 4, 4);
        this.f341b = byteBufferAllocate.getInt() & 4294967295L;
    }
}
