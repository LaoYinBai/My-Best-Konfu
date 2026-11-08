package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.am;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class w {
    private final as a;
    private final bf b;

    public w() {
        this(new am.a());
    }

    public w(au auVar) {
        this.b = new bf();
        this.a = auVar.a(this.b);
    }

    public void a(t tVar, byte[] bArr) throws z {
        try {
            this.b.a(bArr);
            tVar.read(this.a);
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    public void a(t tVar, String str, String str2) throws z {
        try {
            try {
                a(tVar, str.getBytes(str2));
            } catch (UnsupportedEncodingException unused) {
                throw new z("JVM DOES NOT SUPPORT ENCODING: " + str2);
            }
        } finally {
            this.a.B();
        }
    }

    public void a(t tVar, byte[] bArr, aa aaVar, aa... aaVarArr) throws z {
        try {
            try {
                if (j(bArr, aaVar, aaVarArr) != null) {
                    tVar.read(this.a);
                }
            } catch (Exception e) {
                throw new z(e);
            }
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    public Boolean a(byte[] bArr, aa aaVar, aa... aaVarArr) throws z {
        return (Boolean) a((byte) 2, bArr, aaVar, aaVarArr);
    }

    public Byte b(byte[] bArr, aa aaVar, aa... aaVarArr) throws z {
        return (Byte) a((byte) 3, bArr, aaVar, aaVarArr);
    }

    public Double c(byte[] bArr, aa aaVar, aa... aaVarArr) throws z {
        return (Double) a((byte) 4, bArr, aaVar, aaVarArr);
    }

    public Short d(byte[] bArr, aa aaVar, aa... aaVarArr) throws z {
        return (Short) a((byte) 6, bArr, aaVar, aaVarArr);
    }

    public Integer e(byte[] bArr, aa aaVar, aa... aaVarArr) throws z {
        return (Integer) a((byte) 8, bArr, aaVar, aaVarArr);
    }

    public Long f(byte[] bArr, aa aaVar, aa... aaVarArr) throws z {
        return (Long) a((byte) 10, bArr, aaVar, aaVarArr);
    }

    public String g(byte[] bArr, aa aaVar, aa... aaVarArr) throws z {
        return (String) a((byte) 11, bArr, aaVar, aaVarArr);
    }

    public ByteBuffer h(byte[] bArr, aa aaVar, aa... aaVarArr) throws z {
        return (ByteBuffer) a((byte) 100, bArr, aaVar, aaVarArr);
    }

    public Short i(byte[] bArr, aa aaVar, aa... aaVarArr) throws z {
        Short shValueOf;
        try {
            try {
                if (j(bArr, aaVar, aaVarArr) != null) {
                    this.a.j();
                    shValueOf = Short.valueOf(this.a.l().c);
                } else {
                    shValueOf = null;
                }
                return shValueOf;
            } catch (Exception e) {
                throw new z(e);
            }
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x009a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.Object a(byte r1, byte[] r2, com.umeng.commonsdk.proguard.aa r3, com.umeng.commonsdk.proguard.aa... r4) throws com.umeng.commonsdk.proguard.z {
        /*
            r0 = this;
            com.umeng.commonsdk.proguard.an r2 = r0.j(r2, r3, r4)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            if (r2 == 0) goto L9a
            r3 = 6
            if (r1 == r3) goto L8b
            r3 = 8
            if (r1 == r3) goto L7c
            r3 = 100
            r4 = 11
            if (r1 == r3) goto L71
            switch(r1) {
                case 2: goto L61;
                case 3: goto L51;
                case 4: goto L41;
                default: goto L16;
            }     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
        L16:
            switch(r1) {
                case 10: goto L30;
                case 11: goto L1b;
                default: goto L19;
            }     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
        L19:
            goto L9a
        L1b:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            if (r1 != r4) goto L9a
            com.umeng.commonsdk.proguard.as r1 = r0.a     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            java.lang.String r1 = r1.z()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
        L25:
            com.umeng.commonsdk.proguard.bf r2 = r0.b
            r2.e()
            com.umeng.commonsdk.proguard.as r2 = r0.a
            r2.B()
            return r1
        L30:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            r2 = 10
            if (r1 != r2) goto L9a
            com.umeng.commonsdk.proguard.as r1 = r0.a     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            long r1 = r1.x()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            goto L25
        L41:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            r2 = 4
            if (r1 != r2) goto L9a
            com.umeng.commonsdk.proguard.as r1 = r0.a     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            double r1 = r1.y()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            java.lang.Double r1 = java.lang.Double.valueOf(r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            goto L25
        L51:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            r2 = 3
            if (r1 != r2) goto L9a
            com.umeng.commonsdk.proguard.as r1 = r0.a     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            byte r1 = r1.u()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            java.lang.Byte r1 = java.lang.Byte.valueOf(r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            goto L25
        L61:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            r2 = 2
            if (r1 != r2) goto L9a
            com.umeng.commonsdk.proguard.as r1 = r0.a     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            boolean r1 = r1.t()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            goto L25
        L71:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            if (r1 != r4) goto L9a
            com.umeng.commonsdk.proguard.as r1 = r0.a     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            java.nio.ByteBuffer r1 = r1.A()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            goto L25
        L7c:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            if (r1 != r3) goto L9a
            com.umeng.commonsdk.proguard.as r1 = r0.a     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            int r1 = r1.w()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            goto L25
        L8b:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            if (r1 != r3) goto L9a
            com.umeng.commonsdk.proguard.as r1 = r0.a     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            short r1 = r1.v()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            java.lang.Short r1 = java.lang.Short.valueOf(r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            goto L25
        L9a:
            r1 = 0
            goto L25
        L9c:
            r1 = move-exception
            goto La5
        L9e:
            r1 = move-exception
            com.umeng.commonsdk.proguard.z r2 = new com.umeng.commonsdk.proguard.z     // Catch: java.lang.Throwable -> L9c
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L9c
            throw r2     // Catch: java.lang.Throwable -> L9c
        La5:
            com.umeng.commonsdk.proguard.bf r2 = r0.b
            r2.e()
            com.umeng.commonsdk.proguard.as r2 = r0.a
            r2.B()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.proguard.w.a(byte, byte[], com.umeng.commonsdk.proguard.aa, com.umeng.commonsdk.proguard.aa[]):java.lang.Object");
    }

    private an j(byte[] bArr, aa aaVar, aa... aaVarArr) throws z {
        this.b.a(bArr);
        aa[] aaVarArr2 = new aa[aaVarArr.length + 1];
        int i = 0;
        aaVarArr2[0] = aaVar;
        int i2 = 0;
        while (i2 < aaVarArr.length) {
            int i3 = i2 + 1;
            aaVarArr2[i3] = aaVarArr[i2];
            i2 = i3;
        }
        this.a.j();
        an anVarL = null;
        while (i < aaVarArr2.length) {
            anVarL = this.a.l();
            if (anVarL.b == 0 || anVarL.c > aaVarArr2[i].a()) {
                return null;
            }
            if (anVarL.c != aaVarArr2[i].a()) {
                av.a(this.a, anVarL.b);
                this.a.m();
            } else {
                i++;
                if (i < aaVarArr2.length) {
                    this.a.j();
                }
            }
        }
        return anVarL;
    }

    public void a(t tVar, String str) throws z {
        a(tVar, str.getBytes());
    }
}
