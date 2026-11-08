package b.a.k;

import c.c;
import c.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    static final String f976a = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

    /* renamed from: b, reason: collision with root package name */
    static final int f977b = 128;

    /* renamed from: c, reason: collision with root package name */
    static final int f978c = 64;
    static final int d = 32;
    static final int e = 16;
    static final int f = 15;
    static final int g = 8;
    static final int h = 128;
    static final int i = 127;
    static final int j = 0;
    static final int k = 1;
    static final int l = 2;
    static final int m = 8;
    static final int n = 9;
    static final int o = 10;
    static final long p = 125;
    static final long q = 123;
    static final int r = 126;
    static final long s = 65535;
    static final int t = 127;
    static final int u = 1001;
    static final int v = 1005;

    static void a(c.a aVar, byte[] bArr) {
        int length = bArr.length;
        int i2 = 0;
        do {
            byte[] bArr2 = aVar.d;
            int i3 = aVar.e;
            int i4 = aVar.f;
            while (i3 < i4) {
                int i5 = i2 % length;
                bArr2[i3] = (byte) (bArr2[i3] ^ bArr[i5]);
                i3++;
                i2 = i5 + 1;
            }
        } while (aVar.a() != -1);
    }

    static String a(int i2) {
        if (i2 < 1000 || i2 >= 5000) {
            return "Code must be in range [1000,5000): " + i2;
        }
        if ((i2 < 1004 || i2 > 1006) && (i2 < 1012 || i2 > 2999)) {
            return null;
        }
        return "Code " + i2 + " is reserved and may not be used.";
    }

    static void b(int i2) {
        String strA = a(i2);
        if (strA != null) {
            throw new IllegalArgumentException(strA);
        }
    }

    public static String a(String str) {
        return f.a(str + f976a).d().b();
    }

    private b() {
        throw new AssertionError("No instances.");
    }
}
