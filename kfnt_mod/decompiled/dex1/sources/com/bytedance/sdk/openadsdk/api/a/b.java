package com.bytedance.sdk.openadsdk.api.a;

import javax.security.auth.x500.X500Principal;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class b {

    /* renamed from: a, reason: collision with root package name */
    private final String f5185a;

    /* renamed from: b, reason: collision with root package name */
    private final int f5186b;

    /* renamed from: c, reason: collision with root package name */
    private int f5187c;
    private int d;
    private int e;
    private int f;
    private char[] g;

    b(X500Principal x500Principal) {
        this.f5185a = x500Principal.getName("RFC2253");
        this.f5186b = this.f5185a.length();
    }

    private String a() {
        while (this.f5187c < this.f5186b && this.g[this.f5187c] == ' ') {
            this.f5187c++;
        }
        if (this.f5187c == this.f5186b) {
            return null;
        }
        this.d = this.f5187c;
        this.f5187c++;
        while (this.f5187c < this.f5186b && this.g[this.f5187c] != '=' && this.g[this.f5187c] != ' ') {
            this.f5187c++;
        }
        if (this.f5187c >= this.f5186b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f5185a);
        }
        this.e = this.f5187c;
        if (this.g[this.f5187c] == ' ') {
            while (this.f5187c < this.f5186b && this.g[this.f5187c] != '=' && this.g[this.f5187c] == ' ') {
                this.f5187c++;
            }
            if (this.g[this.f5187c] != '=' || this.f5187c == this.f5186b) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f5185a);
            }
        }
        this.f5187c++;
        while (this.f5187c < this.f5186b && this.g[this.f5187c] == ' ') {
            this.f5187c++;
        }
        if (this.e - this.d > 4 && this.g[this.d + 3] == '.' && ((this.g[this.d] == 'O' || this.g[this.d] == 'o') && ((this.g[this.d + 1] == 'I' || this.g[this.d + 1] == 'i') && (this.g[this.d + 2] == 'D' || this.g[this.d + 2] == 'd')))) {
            this.d += 4;
        }
        return new String(this.g, this.d, this.e - this.d);
    }

    private String b() {
        this.f5187c++;
        this.d = this.f5187c;
        this.e = this.d;
        while (this.f5187c != this.f5186b) {
            if (this.g[this.f5187c] == '\"') {
                this.f5187c++;
                while (this.f5187c < this.f5186b && this.g[this.f5187c] == ' ') {
                    this.f5187c++;
                }
                return new String(this.g, this.d, this.e - this.d);
            }
            if (this.g[this.f5187c] == '\\') {
                this.g[this.e] = e();
            } else {
                this.g[this.e] = this.g[this.f5187c];
            }
            this.f5187c++;
            this.e++;
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f5185a);
    }

    private String c() {
        int i;
        if (this.f5187c + 4 >= this.f5186b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f5185a);
        }
        this.d = this.f5187c;
        this.f5187c++;
        while (this.f5187c != this.f5186b && this.g[this.f5187c] != '+' && this.g[this.f5187c] != ',' && this.g[this.f5187c] != ';') {
            if (this.g[this.f5187c] == ' ') {
                this.e = this.f5187c;
                this.f5187c++;
                while (this.f5187c < this.f5186b && this.g[this.f5187c] == ' ') {
                    this.f5187c++;
                }
                i = this.e - this.d;
                if (i >= 5 || (i & 1) == 0) {
                    throw new IllegalStateException("Unexpected end of DN: " + this.f5185a);
                }
                byte[] bArr = new byte[i / 2];
                int i2 = this.d + 1;
                for (int i3 = 0; i3 < bArr.length; i3++) {
                    bArr[i3] = (byte) a(i2);
                    i2 += 2;
                }
                return new String(this.g, this.d, i);
            }
            if (this.g[this.f5187c] >= 'A' && this.g[this.f5187c] <= 'F') {
                char[] cArr = this.g;
                int i4 = this.f5187c;
                cArr[i4] = (char) (cArr[i4] + ' ');
            }
            this.f5187c++;
        }
        this.e = this.f5187c;
        i = this.e - this.d;
        if (i >= 5) {
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f5185a);
    }

    private String d() {
        this.d = this.f5187c;
        this.e = this.f5187c;
        while (this.f5187c < this.f5186b) {
            char c2 = this.g[this.f5187c];
            if (c2 != ' ') {
                if (c2 != ';') {
                    if (c2 == '\\') {
                        char[] cArr = this.g;
                        int i = this.e;
                        this.e = i + 1;
                        cArr[i] = e();
                        this.f5187c++;
                    } else {
                        switch (c2) {
                            case '+':
                            case ',':
                                break;
                            default:
                                char[] cArr2 = this.g;
                                int i2 = this.e;
                                this.e = i2 + 1;
                                cArr2[i2] = this.g[this.f5187c];
                                this.f5187c++;
                                continue;
                        }
                    }
                }
                return new String(this.g, this.d, this.e - this.d);
            }
            this.f = this.e;
            this.f5187c++;
            char[] cArr3 = this.g;
            int i3 = this.e;
            this.e = i3 + 1;
            cArr3[i3] = ' ';
            while (this.f5187c < this.f5186b && this.g[this.f5187c] == ' ') {
                char[] cArr4 = this.g;
                int i4 = this.e;
                this.e = i4 + 1;
                cArr4[i4] = ' ';
                this.f5187c++;
            }
            if (this.f5187c == this.f5186b || this.g[this.f5187c] == ',' || this.g[this.f5187c] == '+' || this.g[this.f5187c] == ';') {
                return new String(this.g, this.d, this.f - this.d);
            }
        }
        return new String(this.g, this.d, this.e - this.d);
    }

    private char e() {
        this.f5187c++;
        if (this.f5187c == this.f5186b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f5185a);
        }
        char c2 = this.g[this.f5187c];
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
        return this.g[this.f5187c];
    }

    private char f() {
        int i;
        int i2;
        int iA = a(this.f5187c);
        this.f5187c++;
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
            this.f5187c++;
            if (this.f5187c == this.f5186b || this.g[this.f5187c] != '\\') {
                return '?';
            }
            this.f5187c++;
            int iA2 = a(this.f5187c);
            this.f5187c++;
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
        if (i4 >= this.f5186b) {
            throw new IllegalStateException("Malformed DN: " + this.f5185a);
        }
        char c2 = this.g[i];
        if (c2 >= '0' && c2 <= '9') {
            i2 = c2 - '0';
        } else if (c2 >= 'a' && c2 <= 'f') {
            i2 = c2 - 'W';
        } else {
            if (c2 < 'A' || c2 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f5185a);
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
                throw new IllegalStateException("Malformed DN: " + this.f5185a);
            }
            i3 = c3 - '7';
        }
        return (i2 << 4) + i3;
    }

    public String a(String str) {
        this.f5187c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = this.f5185a.toCharArray();
        String strA = a();
        if (strA == null) {
            return null;
        }
        do {
            String strB = "";
            if (this.f5187c == this.f5186b) {
                return null;
            }
            switch (this.g[this.f5187c]) {
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
            if (this.f5187c >= this.f5186b) {
                return null;
            }
            if (this.g[this.f5187c] != ',' && this.g[this.f5187c] != ';' && this.g[this.f5187c] != '+') {
                throw new IllegalStateException("Malformed DN: " + this.f5185a);
            }
            this.f5187c++;
            strA = a();
        } while (strA != null);
        throw new IllegalStateException("Malformed DN: " + this.f5185a);
    }
}
