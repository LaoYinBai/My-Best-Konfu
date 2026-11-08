package com.kwad.sdk.pngencrypt;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class k {
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final int i;
    public final int j;
    public final int k;
    public final int l;
    public final int m;
    private long n = -1;
    private long o = -1;

    /* JADX WARN: Removed duplicated region for block: B:39:0x00b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public k(int r3, int r4, int r5, boolean r6, boolean r7, boolean r8) {
        /*
            Method dump skipped, instructions count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.pngencrypt.k.<init>(int, int, int, boolean, boolean, boolean):void");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        k kVar = (k) obj;
        return this.e == kVar.e && this.c == kVar.c && this.a == kVar.a && this.f == kVar.f && this.g == kVar.g && this.b == kVar.b;
    }

    public int hashCode() {
        return (((((((((((this.e ? 1231 : 1237) + 31) * 31) + this.c) * 31) + this.a) * 31) + (this.f ? 1231 : 1237)) * 31) + (this.g ? 1231 : 1237)) * 31) + this.b;
    }

    public String toString() {
        return "ImageInfo [cols=" + this.a + ", rows=" + this.b + ", bitDepth=" + this.c + ", channels=" + this.d + ", alpha=" + this.e + ", greyscale=" + this.f + ", indexed=" + this.g + "]";
    }
}
