package com.bytedance.pangle.f;

import java.nio.ByteBuffer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class h implements k {

    /* renamed from: a, reason: collision with root package name */
    private final ByteBuffer f4617a;

    h(ByteBuffer byteBuffer) {
        this.f4617a = byteBuffer.slice();
    }

    @Override // com.bytedance.pangle.f.k
    public final long a() {
        return this.f4617a.capacity();
    }

    @Override // com.bytedance.pangle.f.k
    public final void a(j jVar, long j, int i) {
        ByteBuffer byteBufferSlice;
        synchronized (this.f4617a) {
            this.f4617a.position(0);
            int i2 = (int) j;
            this.f4617a.limit(i + i2);
            this.f4617a.position(i2);
            byteBufferSlice = this.f4617a.slice();
        }
        jVar.a(byteBufferSlice);
    }
}
