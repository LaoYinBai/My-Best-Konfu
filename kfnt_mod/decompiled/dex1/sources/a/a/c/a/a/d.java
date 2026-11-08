package a.a.c.a.a;

import a.a.c.a.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d extends c.b {
    public final f g;

    public d(boolean z, f fVar) {
        this.f342a = z;
        this.g = fVar;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        fVar.a(byteBufferAllocate, 16L, 2);
        byteBufferAllocate.getShort();
        fVar.a(byteBufferAllocate, 28L, 4);
        this.f343b = byteBufferAllocate.getInt() & 4294967295L;
        fVar.a(byteBufferAllocate, 32L, 4);
        this.f344c = byteBufferAllocate.getInt() & 4294967295L;
        fVar.a(byteBufferAllocate, 42L, 2);
        this.d = byteBufferAllocate.getShort() & 65535;
        fVar.a(byteBufferAllocate, 44L, 2);
        this.e = byteBufferAllocate.getShort() & 65535;
        fVar.a(byteBufferAllocate, 46L, 2);
        this.f = byteBufferAllocate.getShort() & 65535;
        fVar.a(byteBufferAllocate, 48L, 2);
        byteBufferAllocate.getShort();
        fVar.a(byteBufferAllocate, 50L, 2);
        byteBufferAllocate.getShort();
    }

    @Override // a.a.c.a.a.c.b
    public c.a a(long j, int i) {
        return new a(this.g, this, j, i);
    }

    @Override // a.a.c.a.a.c.b
    public c.AbstractC0001c a(long j) {
        return new g(this.g, this, j);
    }

    @Override // a.a.c.a.a.c.b
    public c.d a(int i) {
        return new i(this.g, this, i);
    }
}
