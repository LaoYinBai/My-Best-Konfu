package a.a.c.a.a;

import a.a.c.a.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g extends c.AbstractC0001c {
    public g(f fVar, c.b bVar, long j) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(bVar.f342a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = (j * bVar.d) + bVar.f343b;
        fVar.a(byteBufferAllocate, j2, 4);
        this.f345a = byteBufferAllocate.getInt() & 4294967295L;
        fVar.a(byteBufferAllocate, 4 + j2, 4);
        this.f346b = byteBufferAllocate.getInt() & 4294967295L;
        fVar.a(byteBufferAllocate, 8 + j2, 4);
        this.f347c = byteBufferAllocate.getInt() & 4294967295L;
        fVar.a(byteBufferAllocate, j2 + 20, 4);
        this.d = byteBufferAllocate.getInt() & 4294967295L;
    }
}
