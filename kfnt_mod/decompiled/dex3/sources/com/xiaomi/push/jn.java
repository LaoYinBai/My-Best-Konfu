package com.xiaomi.push;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class jn extends jp {
    private int a;

    /* renamed from: a, reason: collision with other field name */
    private ix f817a;

    public jn(int i) {
        this.f817a = new ix(i);
    }

    @Override // com.xiaomi.push.jp
    public int a(byte[] bArr, int i, int i2) {
        byte[] bArrM527a = this.f817a.m527a();
        if (i2 > this.f817a.a() - this.a) {
            i2 = this.f817a.a() - this.a;
        }
        if (i2 > 0) {
            System.arraycopy(bArrM527a, this.a, bArr, i, i2);
            this.a += i2;
        }
        return i2;
    }

    @Override // com.xiaomi.push.jp
    /* renamed from: a */
    public void mo542a(byte[] bArr, int i, int i2) {
        this.f817a.write(bArr, i, i2);
    }

    public int a_() {
        return this.f817a.size();
    }
}
