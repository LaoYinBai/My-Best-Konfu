package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122;

import anet.channel.util.HttpConstant;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class s {
    private static final char[] i = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a, reason: collision with root package name */
    final String f1729a;

    /* renamed from: b, reason: collision with root package name */
    private final String f1730b;

    /* renamed from: c, reason: collision with root package name */
    private final String f1731c;
    final String d;
    final int e;
    private final List<String> f;
    private final String g;
    private final String h;

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        String f1732a;
        String d;
        List<String> g;
        String h;

        /* renamed from: b, reason: collision with root package name */
        String f1733b = "";

        /* renamed from: c, reason: collision with root package name */
        String f1734c = "";
        int e = -1;
        final List<String> f = new ArrayList();

        /* renamed from: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.s$a$a, reason: collision with other inner class name */
        enum EnumC0055a {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public a() {
            this.f.add("");
        }

        private boolean g(String str) {
            return str.equals(".") || str.equalsIgnoreCase("%2e");
        }

        private boolean h(String str) {
            return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        public a a(int i) {
            if (i > 0 && i <= 65535) {
                this.e = i;
                return this;
            }
            throw new IllegalArgumentException("unexpected port: " + i);
        }

        int b() {
            int i = this.e;
            return i != -1 ? i : s.c(this.f1732a);
        }

        public a c(String str) {
            if (str == null) {
                throw new NullPointerException("host == null");
            }
            String strA = a(str, 0, str.length());
            if (strA != null) {
                this.d = strA;
                return this;
            }
            throw new IllegalArgumentException("unexpected host: " + str);
        }

        public a d(String str) {
            if (str == null) {
                throw new NullPointerException("password == null");
            }
            this.f1734c = s.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public a e(String str) {
            if (str == null) {
                throw new NullPointerException("scheme == null");
            }
            if (str.equalsIgnoreCase(HttpConstant.HTTP)) {
                this.f1732a = HttpConstant.HTTP;
            } else {
                if (!str.equalsIgnoreCase("https")) {
                    throw new IllegalArgumentException("unexpected scheme: " + str);
                }
                this.f1732a = "https";
            }
            return this;
        }

        public a f(String str) {
            if (str == null) {
                throw new NullPointerException("username == null");
            }
            this.f1733b = s.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f1732a);
            sb.append(HttpConstant.SCHEME_SPLIT);
            if (!this.f1733b.isEmpty() || !this.f1734c.isEmpty()) {
                sb.append(this.f1733b);
                if (!this.f1734c.isEmpty()) {
                    sb.append(':');
                    sb.append(this.f1734c);
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
            int iB = b();
            if (iB != s.c(this.f1732a)) {
                sb.append(':');
                sb.append(iB);
            }
            s.b(sb, this.f);
            if (this.g != null) {
                sb.append('?');
                s.a(sb, this.g);
            }
            if (this.h != null) {
                sb.append('#');
                sb.append(this.h);
            }
            return sb.toString();
        }

        public a b(String str) {
            this.g = str != null ? s.e(s.a(str, " \"'<>#", true, false, true, true)) : null;
            return this;
        }

        private void d(String str, int i, int i2) {
            if (i == i2) {
                return;
            }
            char cCharAt = str.charAt(i);
            if (cCharAt != '/' && cCharAt != '\\') {
                List<String> list = this.f;
                list.set(list.size() - 1, "");
            } else {
                this.f.clear();
                this.f.add("");
                i++;
            }
            while (true) {
                int i3 = i;
                if (i3 >= i2) {
                    return;
                }
                i = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(str, i3, i2, "/\\");
                boolean z = i < i2;
                a(str, i3, i, z, true);
                if (z) {
                    i++;
                }
            }
        }

        private static int f(String str, int i, int i2) {
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

        public a a(String str) {
            if (str != null) {
                return a(str, true);
            }
            throw new NullPointerException("encodedPathSegments == null");
        }

        private static int b(String str, int i, int i2) throws NumberFormatException {
            int i3;
            try {
                i3 = Integer.parseInt(s.a(str, i, i2, "", false, false, false, true, null));
            } catch (NumberFormatException unused) {
            }
            if (i3 <= 0 || i3 > 65535) {
                return -1;
            }
            return i3;
        }

        private a a(String str, boolean z) {
            int i = 0;
            do {
                int iA = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(str, i, str.length(), "/\\");
                a(str, i, iA, iA < str.length(), z);
                i = iA + 1;
            } while (i <= str.length());
            return this;
        }

        a c() {
            int size = this.f.size();
            for (int i = 0; i < size; i++) {
                this.f.set(i, s.a(this.f.get(i), "[]", true, true, false, true));
            }
            List<String> list = this.g;
            if (list != null) {
                int size2 = list.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    String str = this.g.get(i2);
                    if (str != null) {
                        this.g.set(i2, s.a(str, "\\^`{|}", true, true, true, true));
                    }
                }
            }
            String str2 = this.h;
            if (str2 != null) {
                this.h = s.a(str2, " \"#<>\\^`{|}", true, true, false, false);
            }
            return this;
        }

        private static int e(String str, int i, int i2) {
            if (i2 - i < 2) {
                return -1;
            }
            char cCharAt = str.charAt(i);
            if ((cCharAt >= 'a' && cCharAt <= 'z') || (cCharAt >= 'A' && cCharAt <= 'Z')) {
                while (true) {
                    i++;
                    if (i >= i2) {
                        break;
                    }
                    char cCharAt2 = str.charAt(i);
                    if (cCharAt2 < 'a' || cCharAt2 > 'z') {
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            if (cCharAt2 < '0' || cCharAt2 > '9') {
                                if (cCharAt2 != '+' && cCharAt2 != '-' && cCharAt2 != '.') {
                                    if (cCharAt2 == ':') {
                                        return i;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return -1;
        }

        public a a(String str, String str2) {
            if (str != null) {
                if (this.g == null) {
                    this.g = new ArrayList();
                }
                this.g.add(s.a(str, " \"'<>#&=", true, false, true, true));
                this.g.add(str2 != null ? s.a(str2, " \"'<>#&=", true, false, true, true) : null);
                return this;
            }
            throw new NullPointerException("encodedName == null");
        }

        public s a() {
            if (this.f1732a != null) {
                if (this.d != null) {
                    return new s(this);
                }
                throw new IllegalStateException("host == null");
            }
            throw new IllegalStateException("scheme == null");
        }

        EnumC0055a a(s sVar, String str) {
            int iA;
            int i;
            int i2;
            int iB = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.b(str, 0, str.length());
            int iC = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.c(str, iB, str.length());
            if (e(str, iB, iC) != -1) {
                if (str.regionMatches(true, iB, "https:", 0, 6)) {
                    this.f1732a = "https";
                    iB += 6;
                } else if (str.regionMatches(true, iB, "http:", 0, 5)) {
                    this.f1732a = HttpConstant.HTTP;
                    iB += 5;
                } else {
                    return EnumC0055a.UNSUPPORTED_SCHEME;
                }
            } else if (sVar != null) {
                this.f1732a = sVar.f1729a;
            } else {
                return EnumC0055a.MISSING_SCHEME;
            }
            int iF = f(str, iB, iC);
            char c2 = '#';
            if (iF < 2 && sVar != null && sVar.f1729a.equals(this.f1732a)) {
                this.f1733b = sVar.f();
                this.f1734c = sVar.b();
                this.d = sVar.d;
                this.e = sVar.e;
                this.f.clear();
                this.f.addAll(sVar.d());
                if (iB == iC || str.charAt(iB) == '#') {
                    b(sVar.e());
                }
                i = iB;
            } else {
                int i3 = iB + iF;
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    iA = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(str, i3, iC, "@/\\?#");
                    char cCharAt = iA != iC ? str.charAt(iA) : (char) 65535;
                    if (cCharAt == 65535 || cCharAt == c2 || cCharAt == '/' || cCharAt == '\\' || cCharAt == '?') {
                        break;
                    }
                    if (cCharAt == '@') {
                        if (!z) {
                            int iA2 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(str, i3, iA, ':');
                            i2 = iA;
                            String strA = s.a(str, i3, iA2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                            if (z2) {
                                strA = this.f1733b + "%40" + strA;
                            }
                            this.f1733b = strA;
                            if (iA2 != i2) {
                                this.f1734c = s.a(str, iA2 + 1, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                z = true;
                            }
                            z2 = true;
                        } else {
                            i2 = iA;
                            this.f1734c += "%40" + s.a(str, i3, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                        }
                        i3 = i2 + 1;
                        c2 = '#';
                    }
                }
                i = iA;
                int iC2 = c(str, i3, i);
                int i4 = iC2 + 1;
                if (i4 < i) {
                    this.d = a(str, i3, iC2);
                    this.e = b(str, i4, i);
                    if (this.e == -1) {
                        return EnumC0055a.INVALID_PORT;
                    }
                } else {
                    this.d = a(str, i3, iC2);
                    this.e = s.c(this.f1732a);
                }
                if (this.d == null) {
                    return EnumC0055a.INVALID_HOST;
                }
            }
            int iA3 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(str, i, iC, "?#");
            d(str, i, iA3);
            if (iA3 < iC && str.charAt(iA3) == '?') {
                int iA4 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(str, iA3, iC, '#');
                this.g = s.e(s.a(str, iA3 + 1, iA4, " \"'<>#", true, false, true, true, null));
                iA3 = iA4;
            }
            if (iA3 < iC && str.charAt(iA3) == '#') {
                this.h = s.a(str, 1 + iA3, iC, "", true, false, false, false, null);
            }
            return EnumC0055a.SUCCESS;
        }

        private void d() {
            if (this.f.remove(r0.size() - 1).isEmpty() && !this.f.isEmpty()) {
                this.f.set(r0.size() - 1, "");
            } else {
                this.f.add("");
            }
        }

        private static int c(String str, int i, int i2) {
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

        private void a(String str, int i, int i2, boolean z, boolean z2) {
            String strA = s.a(str, i, i2, " \"<>^`{}|/\\?#", z2, false, false, true, null);
            if (g(strA)) {
                return;
            }
            if (h(strA)) {
                d();
                return;
            }
            if (this.f.get(r11.size() - 1).isEmpty()) {
                this.f.set(r11.size() - 1, strA);
            } else {
                this.f.add(strA);
            }
            if (z) {
                this.f.add("");
            }
        }

        private static String a(String str, int i, int i2) {
            return bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(s.a(str, i, i2, false));
        }
    }

    s(a aVar) {
        this.f1729a = aVar.f1732a;
        this.f1730b = a(aVar.f1733b, false);
        this.f1731c = a(aVar.f1734c, false);
        this.d = aVar.d;
        this.e = aVar.b();
        a(aVar.f, false);
        List<String> list = aVar.g;
        this.f = list != null ? a(list, true) : null;
        String str = aVar.h;
        this.g = str != null ? a(str, false) : null;
        this.h = aVar.toString();
    }

    static void a(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            String str = list.get(i2);
            String str2 = list.get(i2 + 1);
            if (i2 > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    public static int c(String str) {
        if (str.equals(HttpConstant.HTTP)) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    public String b() {
        if (this.f1731c.isEmpty()) {
            return "";
        }
        return this.h.substring(this.h.indexOf(58, this.f1729a.length() + 3) + 1, this.h.indexOf(64));
    }

    public List<String> d() {
        int iIndexOf = this.h.indexOf(47, this.f1729a.length() + 3);
        String str = this.h;
        int iA = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(str, iIndexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (iIndexOf < iA) {
            int i2 = iIndexOf + 1;
            int iA2 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(this.h, i2, iA, '/');
            arrayList.add(this.h.substring(i2, iA2));
            iIndexOf = iA2;
        }
        return arrayList;
    }

    public String e() {
        if (this.f == null) {
            return null;
        }
        int iIndexOf = this.h.indexOf(63) + 1;
        String str = this.h;
        return this.h.substring(iIndexOf, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(str, iIndexOf, str.length(), '#'));
    }

    public boolean equals(Object obj) {
        return (obj instanceof s) && ((s) obj).h.equals(this.h);
    }

    public String f() {
        if (this.f1730b.isEmpty()) {
            return "";
        }
        int length = this.f1729a.length() + 3;
        String str = this.h;
        return this.h.substring(length, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(str, length, str.length(), ":@"));
    }

    public String g() {
        return this.d;
    }

    public boolean h() {
        return this.f1729a.equals("https");
    }

    public int hashCode() {
        return this.h.hashCode();
    }

    public a i() {
        a aVar = new a();
        aVar.f1732a = this.f1729a;
        aVar.f1733b = f();
        aVar.f1734c = b();
        aVar.d = this.d;
        aVar.e = this.e != c(this.f1729a) ? this.e : -1;
        aVar.f.clear();
        aVar.f.addAll(d());
        aVar.b(e());
        aVar.h = a();
        return aVar;
    }

    public int j() {
        return this.e;
    }

    public String k() {
        if (this.f == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        a(sb, this.f);
        return sb.toString();
    }

    public String l() {
        return a("/...").f("").d("").a().toString();
    }

    public String m() {
        return this.f1729a;
    }

    public URI n() {
        String string = i().c().toString();
        try {
            return new URI(string);
        } catch (URISyntaxException e) {
            try {
                return URI.create(string.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception unused) {
                throw new RuntimeException(e);
            }
        }
    }

    public URL o() {
        try {
            return new URL(this.h);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return this.h;
    }

    public String c() {
        int iIndexOf = this.h.indexOf(47, this.f1729a.length() + 3);
        String str = this.h;
        return this.h.substring(iIndexOf, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(str, iIndexOf, str.length(), "?#"));
    }

    static void b(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append('/');
            sb.append(list.get(i2));
        }
    }

    static List<String> e(String str) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 <= str.length()) {
            int iIndexOf = str.indexOf(38, i2);
            if (iIndexOf == -1) {
                iIndexOf = str.length();
            }
            int iIndexOf2 = str.indexOf(61, i2);
            if (iIndexOf2 != -1 && iIndexOf2 <= iIndexOf) {
                arrayList.add(str.substring(i2, iIndexOf2));
                arrayList.add(str.substring(iIndexOf2 + 1, iIndexOf));
            } else {
                arrayList.add(str.substring(i2, iIndexOf));
                arrayList.add(null);
            }
            i2 = iIndexOf + 1;
        }
        return arrayList;
    }

    public static s d(String str) {
        a aVar = new a();
        if (aVar.a((s) null, str) == a.EnumC0055a.SUCCESS) {
            return aVar.a();
        }
        return null;
    }

    public s b(String str) {
        a aVarA = a(str);
        if (aVarA != null) {
            return aVarA.a();
        }
        return null;
    }

    public String a() {
        if (this.g == null) {
            return null;
        }
        return this.h.substring(this.h.indexOf(35) + 1);
    }

    public a a(String str) {
        a aVar = new a();
        if (aVar.a(this, str) == a.EnumC0055a.SUCCESS) {
            return aVar;
        }
        return null;
    }

    static String a(String str, boolean z) {
        return a(str, 0, str.length(), z);
    }

    private List<String> a(List<String> list, boolean z) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            String str = list.get(i2);
            arrayList.add(str != null ? a(str, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    static String a(String str, int i2, int i3, boolean z) {
        for (int i4 = i2; i4 < i3; i4++) {
            char cCharAt = str.charAt(i4);
            if (cCharAt == '%' || (cCharAt == '+' && z)) {
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c();
                cVar.a(str, i2, i4);
                a(cVar, str, i4, i3, z);
                return cVar.n();
            }
        }
        return str.substring(i2, i3);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c r5, java.lang.String r6, int r7, int r8, boolean r9) {
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
            int r2 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(r2)
            char r3 = r6.charAt(r1)
            int r3 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(r3)
            r4 = -1
            if (r2 == r4) goto L39
            if (r3 == r4) goto L39
            int r7 = r2 << 4
            int r7 = r7 + r3
            r5.writeByte(r7)
            r7 = r1
            goto L3c
        L2d:
            r1 = 43
            if (r0 != r1) goto L39
            if (r9 == 0) goto L39
            r1 = 32
            r5.writeByte(r1)
            goto L3c
        L39:
            r5.c(r0)
        L3c:
            int r0 = java.lang.Character.charCount(r0)
            int r7 = r7 + r0
            goto L0
        L42:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.s.a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c, java.lang.String, int, int, boolean):void");
    }

    static boolean a(String str, int i2, int i3) {
        int i4 = i2 + 2;
        return i4 < i3 && str.charAt(i2) == '%' && bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(str.charAt(i2 + 1)) != -1 && bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(str.charAt(i4)) != -1;
    }

    static String a(String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        int iCharCount = i2;
        while (iCharCount < i3) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt >= 32 && iCodePointAt != 127 && (iCodePointAt < 128 || !z4)) {
                if (str2.indexOf(iCodePointAt) == -1 && ((iCodePointAt != 37 || (z && (!z2 || a(str, iCharCount, i3)))) && (iCodePointAt != 43 || !z3))) {
                    iCharCount += Character.charCount(iCodePointAt);
                } else {
                    bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c();
                    cVar.a(str, i2, iCharCount);
                    a(cVar, str, iCharCount, i3, str2, z, z2, z3, z4, charset);
                    return cVar.n();
                }
            } else {
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar2 = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c();
                cVar2.a(str, i2, iCharCount);
                a(cVar2, str, iCharCount, i3, str2, z, z2, z3, z4, charset);
                return cVar2.n();
            }
        }
        return str.substring(i2, i3);
    }

    static void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar2 = null;
        while (i2 < i3) {
            int iCodePointAt = str.codePointAt(i2);
            if (!z || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt == 43 && z3) {
                    cVar.a(z ? "+" : "%2B");
                } else if (iCodePointAt >= 32 && iCodePointAt != 127 && ((iCodePointAt < 128 || !z4) && str2.indexOf(iCodePointAt) == -1 && (iCodePointAt != 37 || (z && (!z2 || a(str, i2, i3)))))) {
                    cVar.c(iCodePointAt);
                } else {
                    if (cVar2 == null) {
                        cVar2 = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c();
                    }
                    if (charset != null && !charset.equals(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.i)) {
                        cVar2.a(str, i2, Character.charCount(iCodePointAt) + i2, charset);
                    } else {
                        cVar2.c(iCodePointAt);
                    }
                    while (!cVar2.f()) {
                        int i4 = cVar2.readByte() & 255;
                        cVar.writeByte(37);
                        cVar.writeByte((int) i[(i4 >> 4) & 15]);
                        cVar.writeByte((int) i[i4 & 15]);
                    }
                }
            }
            i2 += Character.charCount(iCodePointAt);
        }
    }

    static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, null);
    }
}
