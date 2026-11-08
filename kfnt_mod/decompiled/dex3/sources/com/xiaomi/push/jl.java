package com.xiaomi.push;

import com.xiaomi.push.jb;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class jl extends jb {
    private static int b = 10000;
    private static int c = 10000;
    private static int d = 10000;
    private static int e = 10485760;
    private static int f = 104857600;

    public static class a extends jb.a {
        public a() {
            super(false, true);
        }

        public a(boolean z, boolean z2, int i) {
            super(z, z2, i);
        }

        @Override // com.xiaomi.push.jb.a, com.xiaomi.push.jh
        public jf a(jp jpVar) {
            jl jlVar = new jl(jpVar, this.f810a, this.b);
            if (this.a != 0) {
                jlVar.b(this.a);
            }
            return jlVar;
        }
    }

    public jl(jp jpVar, boolean z, boolean z2) {
        super(jpVar, z, z2);
    }

    @Override // com.xiaomi.push.jb, com.xiaomi.push.jf
    /* renamed from: a */
    public jd mo532a() throws jg {
        byte bA = mo532a();
        int iA = mo532a();
        if (iA <= c) {
            return new jd(bA, iA);
        }
        throw new jg(3, "Thrift list size " + iA + " out of range!");
    }

    @Override // com.xiaomi.push.jb, com.xiaomi.push.jf
    /* renamed from: a */
    public je mo533a() throws jg {
        byte bA = mo532a();
        byte bA2 = mo532a();
        int iA = mo532a();
        if (iA <= b) {
            return new je(bA, bA2, iA);
        }
        throw new jg(3, "Thrift map size " + iA + " out of range!");
    }

    @Override // com.xiaomi.push.jb, com.xiaomi.push.jf
    /* renamed from: a */
    public jj mo534a() throws jg {
        byte bA = mo532a();
        int iA = mo532a();
        if (iA <= d) {
            return new jj(bA, iA);
        }
        throw new jg(3, "Thrift set size " + iA + " out of range!");
    }

    @Override // com.xiaomi.push.jb, com.xiaomi.push.jf
    /* renamed from: a */
    public String mo536a() throws iz {
        int iA = mo532a();
        if (iA > e) {
            throw new jg(3, "Thrift string size " + iA + " out of range!");
        }
        if (this.a.b() < iA) {
            return a(iA);
        }
        try {
            String str = new String(this.a.mo543a(), this.a.a(), iA, "UTF-8");
            this.a.a(iA);
            return str;
        } catch (UnsupportedEncodingException unused) {
            throw new iz("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.xiaomi.push.jb, com.xiaomi.push.jf
    /* renamed from: a */
    public ByteBuffer mo537a() throws jg, jq {
        int iA = mo532a();
        if (iA > f) {
            throw new jg(3, "Thrift binary size " + iA + " out of range!");
        }
        c(iA);
        if (this.a.b() >= iA) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.a.mo543a(), this.a.a(), iA);
            this.a.a(iA);
            return byteBufferWrap;
        }
        byte[] bArr = new byte[iA];
        this.a.b(bArr, 0, iA);
        return ByteBuffer.wrap(bArr);
    }
}
