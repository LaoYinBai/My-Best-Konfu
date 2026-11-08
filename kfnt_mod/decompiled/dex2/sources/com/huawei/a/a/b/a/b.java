package com.huawei.a.a.b.a;

import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    public static final Charset a = Charset.forName(C.UTF8_NAME);
    private static b b;
    private f c;

    public enum a {
        AES(16),
        RSA(128),
        HMCSHA256(16),
        OTHER(0);

        private int e;

        a(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }
    }

    private b() {
    }

    public static b a() {
        if (b == null) {
            b();
        }
        return b;
    }

    private static synchronized void b() {
        if (b == null) {
            b = new b();
        }
    }

    public f a(a aVar) {
        f aVar2;
        switch (aVar) {
            case AES:
                aVar2 = new com.huawei.a.a.b.a.a();
                this.c = aVar2;
                break;
            case HMCSHA256:
                aVar2 = new e();
                this.c = aVar2;
                break;
            case RSA:
                aVar2 = new g();
                this.c = aVar2;
                break;
            default:
                com.huawei.a.a.b.e.a.b("CryptFactory", "crypt type is other");
                break;
        }
        return this.c;
    }

    public String a(String str) {
        return c.a(str);
    }

    public String a(char[] cArr, byte[] bArr) {
        return c.a(cArr, bArr);
    }

    public String b(a aVar) {
        return d.a(aVar.a());
    }

    public byte[] b(String str) {
        return d.a(str);
    }
}
