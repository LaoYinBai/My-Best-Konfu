package cp.bd.c;

import cp.bd.c.t;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class v extends t.b {
    private final w j;

    public v(boolean z, w wVar) {
        this.a = z;
        this.j = wVar;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.b = wVar.c(byteBufferAllocate, 16L);
        this.c = wVar.a(byteBufferAllocate, 32L);
        this.d = wVar.a(byteBufferAllocate, 40L);
        this.e = wVar.c(byteBufferAllocate, 54L);
        this.f = wVar.c(byteBufferAllocate, 56L);
        this.g = wVar.c(byteBufferAllocate, 58L);
        this.h = wVar.c(byteBufferAllocate, 60L);
        this.i = wVar.c(byteBufferAllocate, 62L);
    }

    @Override // cp.bd.c.t.b
    public final t.a a(long j, int i) {
        return new s(this.j, this, j, i);
    }

    @Override // cp.bd.c.t.b
    public final t.c a(long j) {
        return new y(this.j, this, j);
    }

    @Override // cp.bd.c.t.b
    public final t.d a() {
        return new aa(this.j, this);
    }
}
