package a.a.c.a.a;

import a.a.c.a.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e extends c.b {
    public final f g;

    public e(boolean z, f fVar) {
        this.f342a = z;
        this.g = fVar;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        fVar.a(byteBufferAllocate, 16L, 2);
        byteBufferAllocate.getShort();
        fVar.a(byteBufferAllocate, 32L, 8);
        this.f343b = byteBufferAllocate.getLong();
        fVar.a(byteBufferAllocate, 40L, 8);
        this.f344c = byteBufferAllocate.getLong();
        fVar.a(byteBufferAllocate, 54L, 2);
        this.d = byteBufferAllocate.getShort() & 65535;
        fVar.a(byteBufferAllocate, 56L, 2);
        this.e = byteBufferAllocate.getShort() & 65535;
        fVar.a(byteBufferAllocate, 58L, 2);
        this.f = byteBufferAllocate.getShort() & 65535;
        fVar.a(byteBufferAllocate, 60L, 2);
        byteBufferAllocate.getShort();
        fVar.a(byteBufferAllocate, 62L, 2);
        byteBufferAllocate.getShort();
    }

    @Override // a.a.c.a.a.c.b
    public c.a a(long j, int i) {
        return new b(this.g, this, j, i);
    }

    @Override // a.a.c.a.a.c.b
    public c.AbstractC0001c a(long j) {
        return new h(this.g, this, j);
    }

    @Override // a.a.c.a.a.c.b
    public c.d a(int i) {
        return new j(this.g, this, i);
    }
}
