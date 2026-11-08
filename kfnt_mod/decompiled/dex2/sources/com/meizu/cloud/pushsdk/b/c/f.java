package com.meizu.cloud.pushsdk.b.c;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final int f;
    private final List<String> g;
    private final List<String> h;
    private final String i;
    private final String j;

    /* renamed from: com.meizu.cloud.pushsdk.b.c.f$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[a.EnumC0154a.values().length];

        static {
            try {
                a[a.EnumC0154a.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[a.EnumC0154a.INVALID_HOST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[a.EnumC0154a.UNSUPPORTED_SCHEME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[a.EnumC0154a.MISSING_SCHEME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[a.EnumC0154a.INVALID_PORT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static final class a {
        String a;
        String d;
        List<String> g;
        String h;
        String b = "";
        String c = "";
        int e = -1;
        final List<String> f = new ArrayList();

        /* renamed from: com.meizu.cloud.pushsdk.b.c.f$a$a, reason: collision with other inner class name */
        enum EnumC0154a {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public a() {
            this.f.add("");
        }

        private static String a(byte[] bArr) {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = -1;
            while (i2 < bArr.length) {
                int i5 = i2;
                while (i5 < 16 && bArr[i5] == 0 && bArr[i5 + 1] == 0) {
                    i5 += 2;
                }
                int i6 = i5 - i2;
                if (i6 > i3) {
                    i4 = i2;
                    i3 = i6;
                }
                i2 = i5 + 2;
            }
            com.meizu.cloud.pushsdk.b.g.b bVar = new com.meizu.cloud.pushsdk.b.g.b();
            while (i < bArr.length) {
                if (i == i4) {
                    bVar.b(58);
                    i += i3;
                    if (i == 16) {
                        bVar.b(58);
                    }
                } else {
                    if (i > 0) {
                        bVar.b(58);
                    }
                    bVar.d(((bArr[i] & FileDownloadStatus.error) << 8) | (bArr[i + 1] & FileDownloadStatus.error));
                    i += 2;
                }
            }
            return bVar.h();
        }

        private void a(String str, int i, int i2) {
            if (i == i2) {
                return;
            }
            char cCharAt = str.charAt(i);
            if (cCharAt == '/' || cCharAt == '\\') {
                this.f.clear();
                this.f.add("");
                i++;
            } else {
                this.f.set(this.f.size() - 1, "");
            }
            while (true) {
                int i3 = i;
                if (i3 >= i2) {
                    return;
                }
                i = m.a(str, i3, i2, "/\\");
                boolean z = i < i2;
                a(str, i3, i, z, true);
                if (z) {
                    i++;
                }
            }
        }

        private void a(String str, int i, int i2, boolean z, boolean z2) {
            String strB = f.b(str, i, i2, " \"<>^`{}|/\\?#", z2, false, false, true);
            if (b(strB)) {
                return;
            }
            if (c(strB)) {
                c();
                return;
            }
            if (this.f.get(this.f.size() - 1).isEmpty()) {
                this.f.set(this.f.size() - 1, strB);
            } else {
                this.f.add(strB);
            }
            if (z) {
                this.f.add("");
            }
        }

        private static boolean a(String str, int i, int i2, byte[] bArr, int i3) {
            int i4 = i3;
            while (i < i2) {
                if (i4 == bArr.length) {
                    return false;
                }
                if (i4 != i3) {
                    if (str.charAt(i) != '.') {
                        return false;
                    }
                    i++;
                }
                int i5 = i;
                int i6 = 0;
                while (i5 < i2) {
                    char cCharAt = str.charAt(i5);
                    if (cCharAt < '0' || cCharAt > '9') {
                        break;
                    }
                    if ((i6 == 0 && i != i5) || (i6 = ((i6 * 10) + cCharAt) - 48) > 255) {
                        return false;
                    }
                    i5++;
                }
                if (i5 - i == 0) {
                    return false;
                }
                bArr[i4] = (byte) i6;
                i4++;
                i = i5;
            }
            return i4 == i3 + 4;
        }

        private static int b(String str, int i, int i2) {
            if (i2 - i < 2) {
                return -1;
            }
            char cCharAt = str.charAt(i);
            boolean z = cCharAt < 'a' || cCharAt > 'z';
            boolean z2 = cCharAt < 'A' || cCharAt > 'Z';
            if (z && z2) {
                return -1;
            }
            for (int i3 = i + 1; i3 < i2; i3++) {
                char cCharAt2 = str.charAt(i3);
                if ((cCharAt2 < 'a' || cCharAt2 > 'z') && ((cCharAt2 < 'A' || cCharAt2 > 'Z') && !((cCharAt2 >= '0' && cCharAt2 <= '9') || cCharAt2 == '+' || cCharAt2 == '-' || cCharAt2 == '.'))) {
                    if (cCharAt2 == ':') {
                        return i3;
                    }
                    return -1;
                }
            }
            return -1;
        }

        private boolean b(String str) {
            return ".".equals(str) || "%2e".equalsIgnoreCase(str);
        }

        private static int c(String str, int i, int i2) {
            int i3 = 0;
            while (i < i2) {
                char cCharAt = str.charAt(i);
                if (cCharAt != '\\' && cCharAt != '/') {
                    break;
                }
                i3++;
                i++;
            }
            return i3;
        }

        private void c() {
            if (!this.f.remove(this.f.size() - 1).isEmpty() || this.f.isEmpty()) {
                this.f.add("");
            } else {
                this.f.set(this.f.size() - 1, "");
            }
        }

        private boolean c(String str) {
            return "..".equals(str) || "%2e.".equalsIgnoreCase(str) || ".%2e".equalsIgnoreCase(str) || "%2e%2e".equalsIgnoreCase(str);
        }

        private static int d(String str, int i, int i2) {
            while (i < i2) {
                char cCharAt = str.charAt(i);
                if (cCharAt == ':') {
                    return i;
                }
                if (cCharAt == '[') {
                    do {
                        i++;
                        if (i < i2) {
                        }
                    } while (str.charAt(i) != ']');
                }
                i++;
            }
            return i2;
        }

        private static String e(String str, int i, int i2) {
            String strB = f.b(str, i, i2, false);
            if (!strB.contains(":")) {
                return m.a(strB);
            }
            InetAddress inetAddressF = (strB.startsWith("[") && strB.endsWith("]")) ? f(strB, 1, strB.length() - 1) : f(strB, 0, strB.length());
            if (inetAddressF == null) {
                return null;
            }
            byte[] address = inetAddressF.getAddress();
            if (address.length == 16) {
                return a(address);
            }
            throw new AssertionError();
        }

        /* JADX WARN: Code restructure failed: missing block: B:44:0x007d, code lost:
        
            if (r3 == r0.length) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x007f, code lost:
        
            if (r4 != (-1)) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0081, code lost:
        
            return null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0082, code lost:
        
            r11 = r3 - r4;
            java.lang.System.arraycopy(r0, r4, r0, r0.length - r11, r11);
            java.util.Arrays.fill(r0, r4, (r0.length - r3) + r4, (byte) 0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0093, code lost:
        
            return java.net.InetAddress.getByAddress(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x0099, code lost:
        
            throw new java.lang.AssertionError();
         */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0051  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static java.net.InetAddress f(java.lang.String r10, int r11, int r12) {
            /*
                r0 = 16
                byte[] r0 = new byte[r0]
                r1 = -1
                r2 = 0
                r3 = 0
                r4 = -1
                r5 = -1
            L9:
                r6 = 0
                if (r11 >= r12) goto L7c
                int r7 = r0.length
                if (r3 != r7) goto L10
                return r6
            L10:
                int r7 = r11 + 2
                if (r7 > r12) goto L29
                java.lang.String r8 = "::"
                r9 = 2
                boolean r8 = r10.regionMatches(r11, r8, r2, r9)
                if (r8 == 0) goto L29
                if (r4 == r1) goto L20
                return r6
            L20:
                int r3 = r3 + 2
                if (r7 != r12) goto L26
                r4 = r3
                goto L7c
            L26:
                r4 = r3
                r5 = r7
                goto L4d
            L29:
                if (r3 == 0) goto L4c
                java.lang.String r7 = ":"
                r8 = 1
                boolean r7 = r10.regionMatches(r11, r7, r2, r8)
                if (r7 == 0) goto L37
                int r11 = r11 + 1
                goto L4c
            L37:
                java.lang.String r7 = "."
                boolean r11 = r10.regionMatches(r11, r7, r2, r8)
                if (r11 == 0) goto L4b
                int r11 = r3 + (-2)
                boolean r10 = a(r10, r5, r12, r0, r11)
                if (r10 != 0) goto L48
                return r6
            L48:
                int r3 = r3 + 2
                goto L7c
            L4b:
                return r6
            L4c:
                r5 = r11
            L4d:
                r11 = r5
                r7 = 0
            L4f:
                if (r11 >= r12) goto L62
                char r8 = r10.charAt(r11)
                int r8 = com.meizu.cloud.pushsdk.b.c.f.a(r8)
                if (r8 != r1) goto L5c
                goto L62
            L5c:
                int r7 = r7 << 4
                int r7 = r7 + r8
                int r11 = r11 + 1
                goto L4f
            L62:
                int r8 = r11 - r5
                if (r8 == 0) goto L7b
                r9 = 4
                if (r8 <= r9) goto L6a
                goto L7b
            L6a:
                int r6 = r3 + 1
                int r8 = r7 >>> 8
                r8 = r8 & 255(0xff, float:3.57E-43)
                byte r8 = (byte) r8
                r0[r3] = r8
                int r3 = r6 + 1
                r7 = r7 & 255(0xff, float:3.57E-43)
                byte r7 = (byte) r7
                r0[r6] = r7
                goto L9
            L7b:
                return r6
            L7c:
                int r10 = r0.length
                if (r3 == r10) goto L8f
                if (r4 != r1) goto L82
                return r6
            L82:
                int r10 = r0.length
                int r11 = r3 - r4
                int r10 = r10 - r11
                java.lang.System.arraycopy(r0, r4, r0, r10, r11)
                int r10 = r0.length
                int r10 = r10 - r3
                int r10 = r10 + r4
                java.util.Arrays.fill(r0, r4, r10, r2)
            L8f:
                java.net.InetAddress r10 = java.net.InetAddress.getByAddress(r0)     // Catch: java.net.UnknownHostException -> L94
                return r10
            L94:
                java.lang.AssertionError r10 = new java.lang.AssertionError
                r10.<init>()
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.b.c.f.a.f(java.lang.String, int, int):java.net.InetAddress");
        }

        private static int g(String str, int i, int i2) throws NumberFormatException {
            int i3;
            try {
                i3 = Integer.parseInt(f.b(str, i, i2, "", false, false, false, true));
            } catch (NumberFormatException unused) {
            }
            if (i3 <= 0 || i3 > 65535) {
                return -1;
            }
            return i3;
        }

        int a() {
            return this.e != -1 ? this.e : f.a(this.a);
        }

        EnumC0154a a(f fVar, String str) {
            int iA;
            int i;
            int i2;
            String str2;
            int iA2 = m.a(str, 0, str.length());
            int iB = m.b(str, iA2, str.length());
            if (b(str, iA2, iB) != -1) {
                if (str.regionMatches(true, iA2, "https:", 0, 6)) {
                    this.a = "https";
                    str2 = "https:";
                } else {
                    if (!str.regionMatches(true, iA2, "http:", 0, 5)) {
                        return EnumC0154a.UNSUPPORTED_SCHEME;
                    }
                    this.a = "http";
                    str2 = "http:";
                }
                iA2 += str2.length();
            } else {
                if (fVar == null) {
                    return EnumC0154a.MISSING_SCHEME;
                }
                this.a = fVar.b;
            }
            int iC = c(str, iA2, iB);
            char c = '#';
            if (iC >= 2 || fVar == null || !fVar.b.equals(this.a)) {
                int i3 = iA2 + iC;
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    iA = m.a(str, i3, iB, "@/\\?#");
                    char cCharAt = iA != iB ? str.charAt(iA) : (char) 65535;
                    if (cCharAt != 65535 && cCharAt != c && cCharAt != '/' && cCharAt != '\\') {
                        switch (cCharAt) {
                            case '?':
                                break;
                            case '@':
                                if (z) {
                                    i2 = iA;
                                    this.c += "%40" + f.b(str, i3, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                } else {
                                    int iA3 = m.a(str, i3, iA, ':');
                                    i2 = iA;
                                    String strB = f.b(str, i3, iA3, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                    if (z2) {
                                        strB = this.b + "%40" + strB;
                                    }
                                    this.b = strB;
                                    if (iA3 != i2) {
                                        this.c = f.b(str, iA3 + 1, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                        z = true;
                                    }
                                    z2 = true;
                                }
                                i3 = i2 + 1;
                                continue;
                                c = '#';
                            default:
                                c = '#';
                        }
                    }
                }
                i = iA;
                int iD = d(str, i3, i);
                int i4 = iD + 1;
                if (i4 < i) {
                    this.d = e(str, i3, iD);
                    this.e = g(str, i4, i);
                    if (this.e == -1) {
                        return EnumC0154a.INVALID_PORT;
                    }
                } else {
                    this.d = e(str, i3, iD);
                    this.e = f.a(this.a);
                }
                if (this.d == null) {
                    return EnumC0154a.INVALID_HOST;
                }
            } else {
                this.b = fVar.b();
                this.c = fVar.c();
                this.d = fVar.e;
                this.e = fVar.f;
                this.f.clear();
                this.f.addAll(fVar.d());
                if (iA2 == iB || str.charAt(iA2) == '#') {
                    a(fVar.e());
                }
                i = iA2;
            }
            int iA4 = m.a(str, i, iB, "?#");
            a(str, i, iA4);
            if (iA4 < iB && str.charAt(iA4) == '?') {
                int iA5 = m.a(str, iA4, iB, '#');
                this.g = f.b(f.b(str, iA4 + 1, iA5, " \"'<>#", true, false, true, true));
                iA4 = iA5;
            }
            if (iA4 < iB && str.charAt(iA4) == '#') {
                this.h = f.b(str, 1 + iA4, iB, "", true, false, false, false);
            }
            return EnumC0154a.SUCCESS;
        }

        public a a(String str) {
            this.g = str != null ? f.b(f.a(str, " \"'<>#", true, false, true, true)) : null;
            return this;
        }

        public a a(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("name == null");
            }
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(f.a(str, " \"'<>#&=", false, false, true, true));
            this.g.add(str2 != null ? f.a(str2, " \"'<>#&=", false, false, true, true) : null);
            return this;
        }

        public f b() {
            if (this.a == null) {
                throw new IllegalStateException("scheme == null");
            }
            if (this.d != null) {
                return new f(this, null);
            }
            throw new IllegalStateException("host == null");
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.a);
            sb.append("://");
            if (!this.b.isEmpty() || !this.c.isEmpty()) {
                sb.append(this.b);
                if (!this.c.isEmpty()) {
                    sb.append(':');
                    sb.append(this.c);
                }
                sb.append('@');
            }
            if (this.d.indexOf(58) != -1) {
                sb.append('[');
                sb.append(this.d);
                sb.append(']');
            } else {
                sb.append(this.d);
            }
            int iA = a();
            if (iA != f.a(this.a)) {
                sb.append(':');
                sb.append(iA);
            }
            f.a(sb, this.f);
            if (this.g != null) {
                sb.append('?');
                f.b(sb, this.g);
            }
            if (this.h != null) {
                sb.append('#');
                sb.append(this.h);
            }
            return sb.toString();
        }
    }

    private f(a aVar) {
        this.b = aVar.a;
        this.c = a(aVar.b, false);
        this.d = a(aVar.c, false);
        this.e = aVar.d;
        this.f = aVar.a();
        this.g = a(aVar.f, false);
        this.h = aVar.g != null ? a(aVar.g, true) : null;
        this.i = aVar.h != null ? a(aVar.h, false) : null;
        this.j = aVar.toString();
    }

    /* synthetic */ f(a aVar, AnonymousClass1 anonymousClass1) {
        this(aVar);
    }

    public static int a(String str) {
        if ("http".equals(str)) {
            return 80;
        }
        return "https".equals(str) ? 443 : -1;
    }

    static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return b(str, 0, str.length(), str2, z, z2, z3, z4);
    }

    static String a(String str, boolean z) {
        return b(str, 0, str.length(), z);
    }

    private List<String> a(List<String> list, boolean z) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            arrayList.add(next != null ? a(next, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static void a(com.meizu.cloud.pushsdk.b.g.b bVar, String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        int iCharCount = i;
        com.meizu.cloud.pushsdk.b.g.b bVar2 = null;
        while (iCharCount < i2) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt == 43 && z3) {
                bVar.b(z ? "+" : "%2B");
            } else if (a(iCodePointAt, iCharCount, str, i2, str2, z, z2, z3, z4)) {
                if (bVar2 == null) {
                    bVar2 = new com.meizu.cloud.pushsdk.b.g.b();
                }
                bVar2.a(iCodePointAt);
                while (!bVar2.c()) {
                    int iF = bVar2.f() & FileDownloadStatus.error;
                    bVar.b(37);
                    bVar.b((int) a[(iF >> 4) & 15]);
                    bVar.b((int) a[iF & 15]);
                }
            } else {
                bVar.a(iCodePointAt);
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(com.meizu.cloud.pushsdk.b.g.b r5, java.lang.String r6, int r7, int r8, boolean r9) {
        /*
        L0:
            if (r7 >= r8) goto L42
            int r0 = r6.codePointAt(r7)
            r1 = 37
            if (r0 != r1) goto L2d
            int r1 = r7 + 2
            if (r1 >= r8) goto L2d
            int r2 = r7 + 1
            char r2 = r6.charAt(r2)
            int r2 = b(r2)
            char r3 = r6.charAt(r1)
            int r3 = b(r3)
            r4 = -1
            if (r2 == r4) goto L39
            if (r3 == r4) goto L39
            int r7 = r2 << 4
            int r7 = r7 + r3
            r5.b(r7)
            r7 = r1
            goto L3c
        L2d:
            r1 = 43
            if (r0 != r1) goto L39
            if (r9 == 0) goto L39
            r1 = 32
            r5.b(r1)
            goto L3c
        L39:
            r5.a(r0)
        L3c:
            int r0 = java.lang.Character.charCount(r0)
            int r7 = r7 + r0
            goto L0
        L42:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.b.c.f.a(com.meizu.cloud.pushsdk.b.g.b, java.lang.String, int, int, boolean):void");
    }

    static void a(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append('/');
            sb.append(list.get(i));
        }
    }

    private static boolean a(int i, int i2, String str, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        if (i < 32 || i == 127) {
            return true;
        }
        if ((i >= 128 && z4) || str2.indexOf(i) != -1) {
            return true;
        }
        boolean z5 = !z || (z2 && !a(str, i2, i3));
        if (i == 37 && z5) {
            return true;
        }
        return i == 43 && z3;
    }

    private static boolean a(String str, int i, int i2) {
        int i3 = i + 2;
        return i3 < i2 && str.charAt(i) == '%' && b(str.charAt(i + 1)) != -1 && b(str.charAt(i3)) != -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int b(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'a';
        if (c < 'a' || c > 'f') {
            c2 = 'A';
            if (c < 'A' || c > 'F') {
                return -1;
            }
        }
        return (c - c2) + 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        int iCharCount = i;
        while (iCharCount < i2) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (a(iCodePointAt, iCharCount, str, i2, str2, z, z2, z3, z4)) {
                com.meizu.cloud.pushsdk.b.g.b bVar = new com.meizu.cloud.pushsdk.b.g.b();
                bVar.a(str, i, iCharCount);
                a(bVar, str, iCharCount, i2, str2, z, z2, z3, z4);
                return bVar.h();
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str.substring(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str, int i, int i2, boolean z) {
        for (int i3 = i; i3 < i2; i3++) {
            char cCharAt = str.charAt(i3);
            boolean z2 = false;
            boolean z3 = cCharAt == '%';
            if (cCharAt == '+' && z) {
                z2 = true;
            }
            if (z3 || z2) {
                com.meizu.cloud.pushsdk.b.g.b bVar = new com.meizu.cloud.pushsdk.b.g.b();
                bVar.a(str, i, i3);
                a(bVar, str, i3, i2, z);
                return bVar.h();
            }
        }
        return str.substring(i, i2);
    }

    static List<String> b(String str) {
        String strSubstring;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i <= str.length()) {
            int iIndexOf = str.indexOf(38, i);
            if (iIndexOf == -1) {
                iIndexOf = str.length();
            }
            int iIndexOf2 = str.indexOf(61, i);
            if (iIndexOf2 == -1 || iIndexOf2 > iIndexOf) {
                arrayList.add(str.substring(i, iIndexOf));
                strSubstring = null;
            } else {
                arrayList.add(str.substring(i, iIndexOf2));
                strSubstring = str.substring(iIndexOf2 + 1, iIndexOf);
            }
            arrayList.add(strSubstring);
            i = iIndexOf + 1;
        }
        return arrayList;
    }

    static void b(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            String str = list.get(i);
            String str2 = list.get(i + 1);
            if (i > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    public static f c(String str) {
        a aVar = new a();
        if (aVar.a((f) null, str) == a.EnumC0154a.SUCCESS) {
            return aVar.b();
        }
        return null;
    }

    public boolean a() {
        return "https".equals(this.b);
    }

    public String b() {
        if (this.c.isEmpty()) {
            return "";
        }
        int length = this.b.length() + 3;
        return this.j.substring(length, m.a(this.j, length, this.j.length(), ":@"));
    }

    public String c() {
        if (this.d.isEmpty()) {
            return "";
        }
        return this.j.substring(this.j.indexOf(58, this.b.length() + 3) + 1, this.j.indexOf(64));
    }

    public List<String> d() {
        int iIndexOf = this.j.indexOf(47, this.b.length() + 3);
        int iA = m.a(this.j, iIndexOf, this.j.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (iIndexOf < iA) {
            int i = iIndexOf + 1;
            int iA2 = m.a(this.j, i, iA, '/');
            arrayList.add(this.j.substring(i, iA2));
            iIndexOf = iA2;
        }
        return arrayList;
    }

    public String e() {
        if (this.h == null) {
            return null;
        }
        int iIndexOf = this.j.indexOf(63) + 1;
        return this.j.substring(iIndexOf, m.a(this.j, iIndexOf + 1, this.j.length(), '#'));
    }

    public boolean equals(Object obj) {
        return (obj instanceof f) && ((f) obj).j.equals(this.j);
    }

    public String f() {
        if (this.i == null) {
            return null;
        }
        return this.j.substring(this.j.indexOf(35) + 1);
    }

    public a g() {
        a aVar = new a();
        aVar.a = this.b;
        aVar.b = b();
        aVar.c = c();
        aVar.d = this.e;
        aVar.e = this.f != a(this.b) ? this.f : -1;
        aVar.f.clear();
        aVar.f.addAll(d());
        aVar.a(e());
        aVar.h = f();
        return aVar;
    }

    public int hashCode() {
        return this.j.hashCode();
    }

    public String toString() {
        return this.j;
    }
}
