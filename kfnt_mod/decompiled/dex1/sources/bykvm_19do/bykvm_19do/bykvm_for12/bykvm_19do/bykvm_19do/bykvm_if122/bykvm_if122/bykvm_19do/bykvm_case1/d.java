package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_case1;

import javax.security.auth.x500.X500Principal;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class d {

    /* renamed from: a, reason: collision with root package name */
    private final String f1525a;

    /* renamed from: b, reason: collision with root package name */
    private final int f1526b;

    /* renamed from: c, reason: collision with root package name */
    private int f1527c;
    private int d;
    private int e;
    private int f;
    private char[] g;

    d(X500Principal x500Principal) {
        this.f1525a = x500Principal.getName("RFC2253");
        this.f1526b = this.f1525a.length();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0053, code lost:
    
        r1 = r8.g;
        r2 = r8.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005f, code lost:
    
        return new java.lang.String(r1, r2, r8.e - r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a() {
        /*
            r8 = this;
            int r0 = r8.f1527c
            r8.d = r0
            r8.e = r0
        L6:
            int r0 = r8.f1527c
            int r1 = r8.f1526b
            if (r0 < r1) goto L19
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.g
            int r2 = r8.d
            int r3 = r8.e
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L19:
            char[] r1 = r8.g
            char r2 = r1[r0]
            r3 = 44
            r4 = 43
            r5 = 59
            r6 = 32
            if (r2 == r6) goto L60
            if (r2 == r5) goto L53
            r5 = 92
            if (r2 == r5) goto L40
            if (r2 == r4) goto L53
            if (r2 == r3) goto L53
            int r2 = r8.e
            int r3 = r2 + 1
            r8.e = r3
            char r3 = r1[r0]
            r1[r2] = r3
            int r0 = r0 + 1
            r8.f1527c = r0
            goto L6
        L40:
            int r0 = r8.e
            int r2 = r0 + 1
            r8.e = r2
            char r2 = r8.b()
            r1[r0] = r2
            int r0 = r8.f1527c
            int r0 = r0 + 1
            r8.f1527c = r0
            goto L6
        L53:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.g
            int r2 = r8.d
            int r3 = r8.e
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L60:
            int r2 = r8.e
            r8.f = r2
            int r0 = r0 + 1
            r8.f1527c = r0
            int r0 = r2 + 1
            r8.e = r0
            r1[r2] = r6
        L6e:
            int r0 = r8.f1527c
            int r1 = r8.f1526b
            if (r0 >= r1) goto L87
            char[] r1 = r8.g
            char r2 = r1[r0]
            if (r2 != r6) goto L87
            int r2 = r8.e
            int r7 = r2 + 1
            r8.e = r7
            r1[r2] = r6
            int r0 = r0 + 1
            r8.f1527c = r0
            goto L6e
        L87:
            int r0 = r8.f1527c
            int r1 = r8.f1526b
            if (r0 == r1) goto L9b
            char[] r1 = r8.g
            char r2 = r1[r0]
            if (r2 == r3) goto L9b
            char r2 = r1[r0]
            if (r2 == r4) goto L9b
            char r0 = r1[r0]
            if (r0 != r5) goto L6
        L9b:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.g
            int r2 = r8.d
            int r3 = r8.f
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_case1.d.a():java.lang.String");
    }

    private char b() {
        this.f1527c++;
        int i = this.f1527c;
        if (i == this.f1526b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f1525a);
        }
        char[] cArr = this.g;
        char c2 = cArr[i];
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
                                    return c();
                            }
                    }
            }
        }
        return cArr[i];
    }

    private char c() {
        int i;
        int i2;
        int iA = a(this.f1527c);
        this.f1527c++;
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
            this.f1527c++;
            int i4 = this.f1527c;
            if (i4 == this.f1526b || this.g[i4] != '\\') {
                return '?';
            }
            this.f1527c = i4 + 1;
            int iA2 = a(this.f1527c);
            this.f1527c++;
            if ((iA2 & 192) != 128) {
                return '?';
            }
            i2 = (i2 << 6) + (iA2 & 63);
        }
        return (char) i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x005f, code lost:
    
        r5.e = r5.f1527c;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String d() {
        /*
            r5 = this;
            int r0 = r5.f1527c
            int r1 = r0 + 4
            int r2 = r5.f1526b
            if (r1 >= r2) goto La8
            r5.d = r0
            int r0 = r0 + 1
            r5.f1527c = r0
        Le:
            int r0 = r5.f1527c
            int r1 = r5.f1526b
            if (r0 == r1) goto L5f
            char[] r1 = r5.g
            char r2 = r1[r0]
            r3 = 43
            if (r2 == r3) goto L5f
            char r2 = r1[r0]
            r3 = 44
            if (r2 == r3) goto L5f
            char r2 = r1[r0]
            r3 = 59
            if (r2 != r3) goto L29
            goto L5f
        L29:
            char r2 = r1[r0]
            r3 = 32
            if (r2 != r3) goto L46
            r5.e = r0
            int r0 = r0 + 1
            r5.f1527c = r0
        L35:
            int r0 = r5.f1527c
            int r1 = r5.f1526b
            if (r0 >= r1) goto L63
            char[] r1 = r5.g
            char r1 = r1[r0]
            if (r1 != r3) goto L63
            int r0 = r0 + 1
            r5.f1527c = r0
            goto L35
        L46:
            char r2 = r1[r0]
            r4 = 65
            if (r2 < r4) goto L58
            char r2 = r1[r0]
            r4 = 70
            if (r2 > r4) goto L58
            char r2 = r1[r0]
            int r2 = r2 + r3
            char r2 = (char) r2
            r1[r0] = r2
        L58:
            int r0 = r5.f1527c
            int r0 = r0 + 1
            r5.f1527c = r0
            goto Le
        L5f:
            int r0 = r5.f1527c
            r5.e = r0
        L63:
            int r0 = r5.e
            int r1 = r5.d
            int r0 = r0 - r1
            r2 = 5
            if (r0 < r2) goto L8f
            r2 = r0 & 1
            if (r2 == 0) goto L8f
            int r2 = r0 / 2
            byte[] r2 = new byte[r2]
            r3 = 0
            int r1 = r1 + 1
        L76:
            int r4 = r2.length
            if (r3 >= r4) goto L85
            int r4 = r5.a(r1)
            byte r4 = (byte) r4
            r2[r3] = r4
            int r1 = r1 + 2
            int r3 = r3 + 1
            goto L76
        L85:
            java.lang.String r1 = new java.lang.String
            char[] r2 = r5.g
            int r3 = r5.d
            r1.<init>(r2, r3, r0)
            return r1
        L8f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unexpected end of DN: "
            r1.append(r2)
            java.lang.String r2 = r5.f1525a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        La8:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unexpected end of DN: "
            r1.append(r2)
            java.lang.String r2 = r5.f1525a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_case1.d.d():java.lang.String");
    }

    private String e() {
        while (true) {
            int i = this.f1527c;
            if (i >= this.f1526b || this.g[i] != ' ') {
                break;
            }
            this.f1527c = i + 1;
        }
        int i2 = this.f1527c;
        if (i2 == this.f1526b) {
            return null;
        }
        this.d = i2;
        this.f1527c = i2 + 1;
        while (true) {
            int i3 = this.f1527c;
            if (i3 >= this.f1526b) {
                break;
            }
            char[] cArr = this.g;
            if (cArr[i3] == '=' || cArr[i3] == ' ') {
                break;
            }
            this.f1527c = i3 + 1;
        }
        int i4 = this.f1527c;
        if (i4 >= this.f1526b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f1525a);
        }
        this.e = i4;
        if (this.g[i4] == ' ') {
            while (true) {
                int i5 = this.f1527c;
                if (i5 >= this.f1526b) {
                    break;
                }
                char[] cArr2 = this.g;
                if (cArr2[i5] == '=' || cArr2[i5] != ' ') {
                    break;
                }
                this.f1527c = i5 + 1;
            }
            char[] cArr3 = this.g;
            int i6 = this.f1527c;
            if (cArr3[i6] != '=' || i6 == this.f1526b) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f1525a);
            }
        }
        this.f1527c++;
        while (true) {
            int i7 = this.f1527c;
            if (i7 >= this.f1526b || this.g[i7] != ' ') {
                break;
            }
            this.f1527c = i7 + 1;
        }
        int i8 = this.e;
        int i9 = this.d;
        if (i8 - i9 > 4) {
            char[] cArr4 = this.g;
            if (cArr4[i9 + 3] == '.' && (cArr4[i9] == 'O' || cArr4[i9] == 'o')) {
                char[] cArr5 = this.g;
                int i10 = this.d + 1;
                if (cArr5[i10] == 'I' || cArr5[i10] == 'i') {
                    char[] cArr6 = this.g;
                    int i11 = this.d + 2;
                    if (cArr6[i11] == 'D' || cArr6[i11] == 'd') {
                        this.d += 4;
                    }
                }
            }
        }
        char[] cArr7 = this.g;
        int i12 = this.d;
        return new String(cArr7, i12, this.e - i12);
    }

    private String f() {
        this.f1527c++;
        this.d = this.f1527c;
        this.e = this.d;
        while (true) {
            int i = this.f1527c;
            if (i == this.f1526b) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f1525a);
            }
            char[] cArr = this.g;
            if (cArr[i] == '\"') {
                this.f1527c = i + 1;
                while (true) {
                    int i2 = this.f1527c;
                    if (i2 >= this.f1526b || this.g[i2] != ' ') {
                        break;
                    }
                    this.f1527c = i2 + 1;
                }
                char[] cArr2 = this.g;
                int i3 = this.d;
                return new String(cArr2, i3, this.e - i3);
            }
            if (cArr[i] == '\\') {
                cArr[this.e] = b();
            } else {
                cArr[this.e] = cArr[i];
            }
            this.f1527c++;
            this.e++;
        }
    }

    private int a(int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 < this.f1526b) {
            char c2 = this.g[i];
            if (c2 >= '0' && c2 <= '9') {
                i2 = c2 - '0';
            } else if (c2 >= 'a' && c2 <= 'f') {
                i2 = c2 - 'W';
            } else {
                if (c2 < 'A' || c2 > 'F') {
                    throw new IllegalStateException("Malformed DN: " + this.f1525a);
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
                    throw new IllegalStateException("Malformed DN: " + this.f1525a);
                }
                i3 = c3 - '7';
            }
            return (i2 << 4) + i3;
        }
        throw new IllegalStateException("Malformed DN: " + this.f1525a);
    }

    public String a(String str) {
        this.f1527c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = this.f1525a.toCharArray();
        String strE = e();
        if (strE == null) {
            return null;
        }
        do {
            String strF = "";
            int i = this.f1527c;
            if (i == this.f1526b) {
                return null;
            }
            char c2 = this.g[i];
            if (c2 == '\"') {
                strF = f();
            } else if (c2 == '#') {
                strF = d();
            } else if (c2 != '+' && c2 != ',' && c2 != ';') {
                strF = a();
            }
            if (str.equalsIgnoreCase(strE)) {
                return strF;
            }
            int i2 = this.f1527c;
            if (i2 >= this.f1526b) {
                return null;
            }
            char[] cArr = this.g;
            if (cArr[i2] != ',' && cArr[i2] != ';' && cArr[i2] != '+') {
                throw new IllegalStateException("Malformed DN: " + this.f1525a);
            }
            this.f1527c++;
            strE = e();
        } while (strE != null);
        throw new IllegalStateException("Malformed DN: " + this.f1525a);
    }
}
