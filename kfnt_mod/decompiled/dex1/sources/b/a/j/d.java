package b.a.j;

import javax.security.auth.x500.X500Principal;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class d {

    /* renamed from: a, reason: collision with root package name */
    private final String f956a;

    /* renamed from: b, reason: collision with root package name */
    private final int f957b;

    /* renamed from: c, reason: collision with root package name */
    private int f958c;
    private int d;
    private int e;
    private int f;
    private char[] g;

    d(X500Principal x500Principal) {
        this.f956a = x500Principal.getName("RFC2253");
        this.f957b = this.f956a.length();
    }

    private String a() {
        while (this.f958c < this.f957b && this.g[this.f958c] == ' ') {
            this.f958c++;
        }
        if (this.f958c == this.f957b) {
            return null;
        }
        this.d = this.f958c;
        this.f958c++;
        while (this.f958c < this.f957b && this.g[this.f958c] != '=' && this.g[this.f958c] != ' ') {
            this.f958c++;
        }
        if (this.f958c >= this.f957b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f956a);
        }
        this.e = this.f958c;
        if (this.g[this.f958c] == ' ') {
            while (this.f958c < this.f957b && this.g[this.f958c] != '=' && this.g[this.f958c] == ' ') {
                this.f958c++;
            }
            if (this.g[this.f958c] != '=' || this.f958c == this.f957b) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f956a);
            }
        }
        this.f958c++;
        while (this.f958c < this.f957b && this.g[this.f958c] == ' ') {
            this.f958c++;
        }
        if (this.e - this.d > 4 && this.g[this.d + 3] == '.' && ((this.g[this.d] == 'O' || this.g[this.d] == 'o') && ((this.g[this.d + 1] == 'I' || this.g[this.d + 1] == 'i') && (this.g[this.d + 2] == 'D' || this.g[this.d + 2] == 'd')))) {
            this.d += 4;
        }
        return new String(this.g, this.d, this.e - this.d);
    }

    private String b() {
        this.f958c++;
        this.d = this.f958c;
        this.e = this.d;
        while (this.f958c != this.f957b) {
            if (this.g[this.f958c] == '\"') {
                this.f958c++;
                while (this.f958c < this.f957b && this.g[this.f958c] == ' ') {
                    this.f958c++;
                }
                return new String(this.g, this.d, this.e - this.d);
            }
            if (this.g[this.f958c] == '\\') {
                this.g[this.e] = e();
            } else {
                this.g[this.e] = this.g[this.f958c];
            }
            this.f958c++;
            this.e++;
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f956a);
    }

    private String c() {
        int i;
        if (this.f958c + 4 >= this.f957b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f956a);
        }
        this.d = this.f958c;
        this.f958c++;
        while (this.f958c != this.f957b && this.g[this.f958c] != '+' && this.g[this.f958c] != ',' && this.g[this.f958c] != ';') {
            if (this.g[this.f958c] == ' ') {
                this.e = this.f958c;
                this.f958c++;
                while (this.f958c < this.f957b && this.g[this.f958c] == ' ') {
                    this.f958c++;
                }
                i = this.e - this.d;
                if (i >= 5 || (i & 1) == 0) {
                    throw new IllegalStateException("Unexpected end of DN: " + this.f956a);
                }
                byte[] bArr = new byte[i / 2];
                int i2 = this.d + 1;
                for (int i3 = 0; i3 < bArr.length; i3++) {
                    bArr[i3] = (byte) a(i2);
                    i2 += 2;
                }
                return new String(this.g, this.d, i);
            }
            if (this.g[this.f958c] >= 'A' && this.g[this.f958c] <= 'F') {
                char[] cArr = this.g;
                int i4 = this.f958c;
                cArr[i4] = (char) (cArr[i4] + ' ');
            }
            this.f958c++;
        }
        this.e = this.f958c;
        i = this.e - this.d;
        if (i >= 5) {
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f956a);
    }

    private String d() {
        this.d = this.f958c;
        this.e = this.f958c;
        while (this.f958c < this.f957b) {
            char c2 = this.g[this.f958c];
            if (c2 != ' ') {
                if (c2 != ';') {
                    if (c2 == '\\') {
                        char[] cArr = this.g;
                        int i = this.e;
                        this.e = i + 1;
                        cArr[i] = e();
                        this.f958c++;
                    } else {
                        switch (c2) {
                            case '+':
                            case ',':
                                break;
                            default:
                                char[] cArr2 = this.g;
                                int i2 = this.e;
                                this.e = i2 + 1;
                                cArr2[i2] = this.g[this.f958c];
                                this.f958c++;
                                continue;
                        }
                    }
                }
                return new String(this.g, this.d, this.e - this.d);
            }
            this.f = this.e;
            this.f958c++;
            char[] cArr3 = this.g;
            int i3 = this.e;
            this.e = i3 + 1;
            cArr3[i3] = ' ';
            while (this.f958c < this.f957b && this.g[this.f958c] == ' ') {
                char[] cArr4 = this.g;
                int i4 = this.e;
                this.e = i4 + 1;
                cArr4[i4] = ' ';
                this.f958c++;
            }
            if (this.f958c == this.f957b || this.g[this.f958c] == ',' || this.g[this.f958c] == '+' || this.g[this.f958c] == ';') {
                return new String(this.g, this.d, this.f - this.d);
            }
        }
        return new String(this.g, this.d, this.e - this.d);
    }

    private char e() {
        this.f958c++;
        if (this.f958c == this.f957b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f956a);
        }
        char c2 = this.g[this.f958c];
        if (c2 != ' ' && c2 != '%' && c2 != '\\' && c2 != '_') {
            switch (c2) {
                case '\"':
                case '#':
                    break;
                default:
                    switch (c2) {
                        case '*':
                        case '+':
                        case ',':
                            break;
                        default:
                            switch (c2) {
                                case ';':
                                case '<':
                                case '=':
                                case '>':
                                    break;
                                default:
                                    return f();
                            }
                    }
            }
        }
        return this.g[this.f958c];
    }

    private char f() {
        int i;
        int i2;
        int iA = a(this.f958c);
        this.f958c++;
        if (iA < 128) {
            return (char) iA;
        }
        if (iA < 192 || iA > 247) {
            return '?';
        }
        if (iA <= 223) {
            i2 = iA & 31;
            i = 1;
        } else if (iA <= 239) {
            i = 2;
            i2 = iA & 15;
        } else {
            i = 3;
            i2 = iA & 7;
        }
        for (int i3 = 0; i3 < i; i3++) {
            this.f958c++;
            if (this.f958c == this.f957b || this.g[this.f958c] != '\\') {
                return '?';
            }
            this.f958c++;
            int iA2 = a(this.f958c);
            this.f958c++;
            if ((iA2 & 192) != 128) {
                return '?';
            }
            i2 = (i2 << 6) + (iA2 & 63);
        }
        return (char) i2;
    }

    private int a(int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 >= this.f957b) {
            throw new IllegalStateException("Malformed DN: " + this.f956a);
        }
        char c2 = this.g[i];
        if (c2 >= '0' && c2 <= '9') {
            i2 = c2 - '0';
        } else if (c2 >= 'a' && c2 <= 'f') {
            i2 = c2 - 'W';
        } else {
            if (c2 < 'A' || c2 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f956a);
            }
            i2 = c2 - '7';
        }
        char c3 = this.g[i4];
        if (c3 >= '0' && c3 <= '9') {
            i3 = c3 - '0';
        } else if (c3 >= 'a' && c3 <= 'f') {
            i3 = c3 - 'W';
        } else {
            if (c3 < 'A' || c3 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f956a);
            }
            i3 = c3 - '7';
        }
        return (i2 << 4) + i3;
    }

    public String a(String str) {
        this.f958c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = this.f956a.toCharArray();
        String strA = a();
        if (strA == null) {
            return null;
        }
        do {
            String strB = "";
            if (this.f958c == this.f957b) {
                return null;
            }
            switch (this.g[this.f958c]) {
                case '\"':
                    strB = b();
                    break;
                case '#':
                    strB = c();
                    break;
                case '+':
                case ',':
                case ';':
                    break;
                default:
                    strB = d();
                    break;
            }
            if (str.equalsIgnoreCase(strA)) {
                return strB;
            }
            if (this.f958c >= this.f957b) {
                return null;
            }
            if (this.g[this.f958c] != ',' && this.g[this.f958c] != ';' && this.g[this.f958c] != '+') {
                throw new IllegalStateException("Malformed DN: " + this.f956a);
            }
            this.f958c++;
            strA = a();
        } while (strA != null);
        throw new IllegalStateException("Malformed DN: " + this.f956a);
    }
}
