package a.a.c.a.a;

import a.a.c.a.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h extends c.AbstractC0001c {
    public h(f fVar, c.b bVar, long j) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(bVar.f342a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = (j * bVar.d) + bVar.f343b;
        fVar.a(byteBufferAllocate, j2, 4);
        this.f345a = byteBufferAllocate.getInt() & 4294967295L;
        fVar.a(byteBufferAllocate, 8 + j2, 8);
        this.f346b = byteBufferAllocate.getLong();
        fVar.a(byteBufferAllocate, 16 + j2, 8);
        this.f347c = byteBufferAllocate.getLong();
        fVar.a(byteBufferAllocate, j2 + 40, 8);
        this.d = byteBufferAllocate.getLong();
    }
}
