package b.a.f;

import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class e {

    /* renamed from: b, reason: collision with root package name */
    static final int f863b = 16384;

    /* renamed from: c, reason: collision with root package name */
    static final byte f864c = 0;
    static final byte d = 1;
    static final byte e = 2;
    static final byte f = 3;
    static final byte g = 4;
    static final byte h = 5;
    static final byte i = 6;
    static final byte j = 7;
    static final byte k = 8;
    static final byte l = 9;
    static final byte m = 0;
    static final byte n = 1;
    static final byte o = 1;
    static final byte p = 4;
    static final byte q = 4;
    static final byte r = 8;
    static final byte s = 32;
    static final byte t = 32;

    /* renamed from: a, reason: collision with root package name */
    static final c.f f862a = c.f.a("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    private static final String[] w = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
    static final String[] u = new String[64];
    static final String[] v = new String[256];

    static {
        for (int i2 = 0; i2 < v.length; i2++) {
            v[i2] = b.a.c.a("%8s", Integer.toBinaryString(i2)).replace(' ', '0');
        }
        u[0] = "";
        u[1] = "END_STREAM";
        int[] iArr = {1};
        u[8] = "PADDED";
        for (int i3 : iArr) {
            u[i3 | 8] = u[i3] + "|PADDED";
        }
        u[4] = "END_HEADERS";
        u[32] = "PRIORITY";
        u[36] = "END_HEADERS|PRIORITY";
        for (int i4 : new int[]{4, 32, 36}) {
            for (int i5 : iArr) {
                int i6 = i5 | i4;
                u[i6] = u[i5] + '|' + u[i4];
                u[i6 | 8] = u[i5] + '|' + u[i4] + "|PADDED";
            }
        }
        for (int i7 = 0; i7 < u.length; i7++) {
            if (u[i7] == null) {
                u[i7] = v[i7];
            }
        }
    }

    private e() {
    }

    static IllegalArgumentException a(String str, Object... objArr) {
        throw new IllegalArgumentException(b.a.c.a(str, objArr));
    }

    static IOException b(String str, Object... objArr) throws IOException {
        throw new IOException(b.a.c.a(str, objArr));
    }

    static String a(boolean z, int i2, int i3, byte b2, byte b3) {
        String strA = b2 < w.length ? w[b2] : b.a.c.a("0x%02x", Byte.valueOf(b2));
        String strA2 = a(b2, b3);
        Object[] objArr = new Object[5];
        objArr[0] = z ? "<<" : ">>";
        objArr[1] = Integer.valueOf(i2);
        objArr[2] = Integer.valueOf(i3);
        objArr[3] = strA;
        objArr[4] = strA2;
        return b.a.c.a("%s 0x%08x %5d %-13s %s", objArr);
    }

    static String a(byte b2, byte b3) {
        if (b3 == 0) {
            return "";
        }
        switch (b2) {
            case 2:
            case 3:
            case 7:
            case 8:
                return v[b3];
            case 4:
            case 6:
                return b3 == 1 ? "ACK" : v[b3];
            case 5:
            default:
                String str = b3 < u.length ? u[b3] : v[b3];
                if (b2 != 5 || (b3 & 4) == 0) {
                    return (b2 != 0 || (b3 & 32) == 0) ? str : str.replace("PRIORITY", "COMPRESSED");
                }
                return str.replace("HEADERS", "PUSH_PROMISE");
        }
    }
}
