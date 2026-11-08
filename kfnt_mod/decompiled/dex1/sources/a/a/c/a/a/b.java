package a.a.c.a.a;

import a.a.c.a.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b extends c.a {
    public b(f fVar, c.b bVar, long j, int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(bVar.f342a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = j + (i * 16);
        fVar.a(byteBufferAllocate, j2, 8);
        this.f340a = byteBufferAllocate.getLong();
        fVar.a(byteBufferAllocate, j2 + 8, 8);
        this.f341b = byteBufferAllocate.getLong();
    }
}
