package b.a.d;

import b.aa;
import b.ae;
import java.io.IOException;
import java.net.ProtocolException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    public static final int f833a = 307;

    /* renamed from: b, reason: collision with root package name */
    public static final int f834b = 308;

    /* renamed from: c, reason: collision with root package name */
    public static final int f835c = 100;
    public final aa d;
    public final int e;
    public final String f;

    public k(aa aaVar, int i, String str) {
        this.d = aaVar;
        this.e = i;
        this.f = str;
    }

    public static k a(ae aeVar) {
        return new k(aeVar.b(), aeVar.c(), aeVar.e());
    }

    public static k a(String str) throws NumberFormatException, IOException {
        aa aaVar;
        int i = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException("Unexpected status line: " + str);
            }
            int iCharAt = str.charAt(7) - '0';
            if (iCharAt == 0) {
                aaVar = aa.HTTP_1_0;
            } else if (iCharAt == 1) {
                aaVar = aa.HTTP_1_1;
            } else {
                throw new ProtocolException("Unexpected status line: " + str);
            }
        } else if (str.startsWith("ICY ")) {
            aaVar = aa.HTTP_1_0;
            i = 4;
        } else {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        int i2 = i + 3;
        if (str.length() < i2) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        try {
            int i3 = Integer.parseInt(str.substring(i, i2));
            String strSubstring = "";
            if (str.length() > i2) {
                if (str.charAt(i2) != ' ') {
                    throw new ProtocolException("Unexpected status line: " + str);
                }
                strSubstring = str.substring(i + 4);
            }
            return new k(aaVar, i3, strSubstring);
        } catch (NumberFormatException unused) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.d == aa.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
        sb.append(' ');
        sb.append(this.e);
        if (this.f != null) {
            sb.append(' ');
            sb.append(this.f);
        }
        return sb.toString();
    }
}
