package cp.bd.c;

import cp.bd.c.t;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class u extends t.b {
    private final w j;

    public u(boolean z, w wVar) {
        this.a = z;
        this.j = wVar;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.b = wVar.c(byteBufferAllocate, 16L);
        this.c = wVar.b(byteBufferAllocate, 28L);
        this.d = wVar.b(byteBufferAllocate, 32L);
        this.e = wVar.c(byteBufferAllocate, 42L);
        this.f = wVar.c(byteBufferAllocate, 44L);
        this.g = wVar.c(byteBufferAllocate, 46L);
        this.h = wVar.c(byteBufferAllocate, 48L);
        this.i = wVar.c(byteBufferAllocate, 50L);
    }

    @Override // cp.bd.c.t.b
    public final t.a a(long j, int i) {
        return new r(this.j, this, j, i);
    }

    @Override // cp.bd.c.t.b
    public final t.c a(long j) {
        return new x(this.j, this, j);
    }

    @Override // cp.bd.c.t.b
    public final t.d a() {
        return new z(this.j, this);
    }
}
