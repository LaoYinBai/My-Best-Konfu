package b;

import anet.channel.util.HttpConstant;
import com.appsflyer.share.Constants;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class v {

    /* renamed from: a, reason: collision with root package name */
    static final String f1279a = " \"':;<=>@[]^`{}|/\\?#";

    /* renamed from: b, reason: collision with root package name */
    static final String f1280b = " \"':;<=>@[]^`{}|/\\?#";

    /* renamed from: c, reason: collision with root package name */
    static final String f1281c = " \"<>^`{}|/\\?#";
    static final String d = "[]";
    static final String e = " \"'<>#";
    static final String f = " \"'<>#&=";
    static final String g = " !\"#$&'(),/:;<=>?@[]\\^`{|}~";
    static final String h = "\\^`{|}";
    static final String i = " \"':;<=>@[]^`{}|/\\?#&!$(),~";
    static final String j = "";
    static final String k = " \"#<>\\^`{|}";
    private static final char[] o = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    final String l;
    final String m;
    final int n;
    private final String p;
    private final String q;
    private final List<String> r;

    @Nullable
    private final List<String> s;

    @Nullable
    private final String t;
    private final String u;

    v(a aVar) {
        this.l = aVar.f1283a;
        this.p = a(aVar.f1284b, false);
        this.q = a(aVar.f1285c, false);
        this.m = aVar.d;
        this.n = aVar.a();
        this.r = a(aVar.f, false);
        this.s = aVar.g != null ? a(aVar.g, true) : null;
        this.t = aVar.h != null ? a(aVar.h, false) : null;
        this.u = aVar.toString();
    }

    public URL a() {
        try {
            return new URL(this.u);
        } catch (MalformedURLException e2) {
            throw new RuntimeException(e2);
        }
    }

    public URI b() {
        String string = v().b().toString();
        try {
            return new URI(string);
        } catch (URISyntaxException e2) {
            try {
                return URI.create(string.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception unused) {
                throw new RuntimeException(e2);
            }
        }
    }

    public String c() {
        return this.l;
    }

    public boolean d() {
        return this.l.equals("https");
    }

    public String e() {
        if (this.p.isEmpty()) {
            return "";
        }
        int length = this.l.length() + 3;
        return this.u.substring(length, b.a.c.a(this.u, length, this.u.length(), ":@"));
    }

    public String f() {
        return this.p;
    }

    public String g() {
        if (this.q.isEmpty()) {
            return "";
        }
        return this.u.substring(this.u.indexOf(58, this.l.length() + 3) + 1, this.u.indexOf(64));
    }

    public String h() {
        return this.q;
    }

    public String i() {
        return this.m;
    }

    public int j() {
        return this.n;
    }

    public static int a(String str) {
        if (str.equals(HttpConstant.HTTP)) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    public int k() {
        return this.r.size();
    }

    public String l() {
        int iIndexOf = this.u.indexOf(47, this.l.length() + 3);
        return this.u.substring(iIndexOf, b.a.c.a(this.u, iIndexOf, this.u.length(), "?#"));
    }

    static void a(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append('/');
            sb.append(list.get(i2));
        }
    }

    public List<String> m() {
        int iIndexOf = this.u.indexOf(47, this.l.length() + 3);
        int iA = b.a.c.a(this.u, iIndexOf, this.u.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (iIndexOf < iA) {
            int i2 = iIndexOf + 1;
            int iA2 = b.a.c.a(this.u, i2, iA, '/');
            arrayList.add(this.u.substring(i2, iA2));
            iIndexOf = iA2;
        }
        return arrayList;
    }

    public List<String> n() {
        return this.r;
    }

    @Nullable
    public String o() {
        if (this.s == null) {
            return null;
        }
        int iIndexOf = this.u.indexOf(63) + 1;
        return this.u.substring(iIndexOf, b.a.c.a(this.u, iIndexOf, this.u.length(), '#'));
    }

    static void b(StringBuilder sb, List<String> list) {
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

    static List<String> b(String str) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 <= str.length()) {
            int iIndexOf = str.indexOf(38, i2);
            if (iIndexOf == -1) {
                iIndexOf = str.length();
            }
            int iIndexOf2 = str.indexOf(61, i2);
            if (iIndexOf2 == -1 || iIndexOf2 > iIndexOf) {
                arrayList.add(str.substring(i2, iIndexOf));
                arrayList.add(null);
            } else {
                arrayList.add(str.substring(i2, iIndexOf2));
                arrayList.add(str.substring(iIndexOf2 + 1, iIndexOf));
            }
            i2 = iIndexOf + 1;
        }
        return arrayList;
    }

    @Nullable
    public String p() {
        if (this.s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        b(sb, this.s);
        return sb.toString();
    }

    public int q() {
        if (this.s != null) {
            return this.s.size() / 2;
        }
        return 0;
    }

    @Nullable
    public String c(String str) {
        if (this.s == null) {
            return null;
        }
        int size = this.s.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            if (str.equals(this.s.get(i2))) {
                return this.s.get(i2 + 1);
            }
        }
        return null;
    }

    public Set<String> r() {
        if (this.s == null) {
            return Collections.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int size = this.s.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            linkedHashSet.add(this.s.get(i2));
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public List<String> d(String str) {
        if (this.s == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = this.s.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            if (str.equals(this.s.get(i2))) {
                arrayList.add(this.s.get(i2 + 1));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public String a(int i2) {
        if (this.s == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.s.get(i2 * 2);
    }

    public String b(int i2) {
        if (this.s == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.s.get((i2 * 2) + 1);
    }

    @Nullable
    public String s() {
        if (this.t == null) {
            return null;
        }
        return this.u.substring(this.u.indexOf(35) + 1);
    }

    @Nullable
    public String t() {
        return this.t;
    }

    public String u() {
        return f("/...").b("").d("").c().toString();
    }

    @Nullable
    public v e(String str) {
        a aVarF = f(str);
        if (aVarF != null) {
            return aVarF.c();
        }
        return null;
    }

    public a v() {
        a aVar = new a();
        aVar.f1283a = this.l;
        aVar.f1284b = e();
        aVar.f1285c = g();
        aVar.d = this.m;
        aVar.e = this.n != a(this.l) ? this.n : -1;
        aVar.f.clear();
        aVar.f.addAll(m());
        aVar.m(o());
        aVar.h = s();
        return aVar;
    }

    @Nullable
    public a f(String str) {
        a aVar = new a();
        if (aVar.a(this, str) == a.EnumC0038a.SUCCESS) {
            return aVar;
        }
        return null;
    }

    @Nullable
    public static v g(String str) {
        a aVar = new a();
        if (aVar.a((v) null, str) == a.EnumC0038a.SUCCESS) {
            return aVar.c();
        }
        return null;
    }

    @Nullable
    public static v a(URL url) {
        return g(url.toString());
    }

    static v h(String str) throws MalformedURLException, UnknownHostException {
        a aVar = new a();
        a.EnumC0038a enumC0038aA = aVar.a((v) null, str);
        switch (enumC0038aA) {
            case SUCCESS:
                return aVar.c();
            case INVALID_HOST:
                throw new UnknownHostException("Invalid host: " + str);
            default:
                throw new MalformedURLException("Invalid URL: " + enumC0038aA + " for " + str);
        }
    }

    @Nullable
    public static v a(URI uri) {
        return g(uri.toString());
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof v) && ((v) obj).u.equals(this.u);
    }

    public int hashCode() {
        return this.u.hashCode();
    }

    public String toString() {
        return this.u;
    }

    @Nullable
    public String w() {
        if (b.a.c.c(this.m)) {
            return null;
        }
        return b.a.i.a.a().a(this.m);
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        @Nullable
        String f1283a;

        @Nullable
        String d;

        @Nullable
        List<String> g;

        @Nullable
        String h;

        /* renamed from: b, reason: collision with root package name */
        String f1284b = "";

        /* renamed from: c, reason: collision with root package name */
        String f1285c = "";
        int e = -1;
        final List<String> f = new ArrayList();

        /* renamed from: b.v$a$a, reason: collision with other inner class name */
        enum EnumC0038a {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public a() {
            this.f.add("");
        }

        public a a(String str) {
            if (str == null) {
                throw new NullPointerException("scheme == null");
            }
            if (str.equalsIgnoreCase(HttpConstant.HTTP)) {
                this.f1283a = HttpConstant.HTTP;
            } else if (str.equalsIgnoreCase("https")) {
                this.f1283a = "https";
            } else {
                throw new IllegalArgumentException("unexpected scheme: " + str);
            }
            return this;
        }

        public a b(String str) {
            if (str == null) {
                throw new NullPointerException("username == null");
            }
            this.f1284b = v.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public a c(String str) {
            if (str == null) {
                throw new NullPointerException("encodedUsername == null");
            }
            this.f1284b = v.a(str, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
            return this;
        }

        public a d(String str) {
            if (str == null) {
                throw new NullPointerException("password == null");
            }
            this.f1285c = v.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public a e(String str) {
            if (str == null) {
                throw new NullPointerException("encodedPassword == null");
            }
            this.f1285c = v.a(str, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
            return this;
        }

        public a f(String str) {
            if (str == null) {
                throw new NullPointerException("host == null");
            }
            String strE = e(str, 0, str.length());
            if (strE == null) {
                throw new IllegalArgumentException("unexpected host: " + str);
            }
            this.d = strE;
            return this;
        }

        public a a(int i) {
            if (i <= 0 || i > 65535) {
                throw new IllegalArgumentException("unexpected port: " + i);
            }
            this.e = i;
            return this;
        }

        int a() {
            return this.e != -1 ? this.e : v.a(this.f1283a);
        }

        public a g(String str) {
            if (str == null) {
                throw new NullPointerException("pathSegment == null");
            }
            a(str, 0, str.length(), false, false);
            return this;
        }

        public a h(String str) {
            if (str == null) {
                throw new NullPointerException("pathSegments == null");
            }
            return a(str, false);
        }

        public a i(String str) {
            if (str == null) {
                throw new NullPointerException("encodedPathSegment == null");
            }
            a(str, 0, str.length(), false, true);
            return this;
        }

        public a j(String str) {
            if (str == null) {
                throw new NullPointerException("encodedPathSegments == null");
            }
            return a(str, true);
        }

        private a a(String str, boolean z) {
            int i = 0;
            do {
                int iA = b.a.c.a(str, i, str.length(), "/\\");
                a(str, i, iA, iA < str.length(), z);
                i = iA + 1;
            } while (i <= str.length());
            return this;
        }

        public a a(int i, String str) {
            if (str == null) {
                throw new NullPointerException("pathSegment == null");
            }
            String strA = v.a(str, 0, str.length(), v.f1281c, false, false, false, true, null);
            if (s(strA) || t(strA)) {
                throw new IllegalArgumentException("unexpected path segment: " + str);
            }
            this.f.set(i, strA);
            return this;
        }

        public a b(int i, String str) {
            if (str == null) {
                throw new NullPointerException("encodedPathSegment == null");
            }
            String strA = v.a(str, 0, str.length(), v.f1281c, true, false, false, true, null);
            this.f.set(i, strA);
            if (!s(strA) && !t(strA)) {
                return this;
            }
            throw new IllegalArgumentException("unexpected path segment: " + str);
        }

        public a b(int i) {
            this.f.remove(i);
            if (this.f.isEmpty()) {
                this.f.add("");
            }
            return this;
        }

        public a k(String str) {
            if (str == null) {
                throw new NullPointerException("encodedPath == null");
            }
            if (!str.startsWith(Constants.URL_PATH_DELIMITER)) {
                throw new IllegalArgumentException("unexpected encodedPath: " + str);
            }
            a(str, 0, str.length());
            return this;
        }

        public a l(@Nullable String str) {
            this.g = str != null ? v.b(v.a(str, v.e, false, false, true, true)) : null;
            return this;
        }

        public a m(@Nullable String str) {
            this.g = str != null ? v.b(v.a(str, v.e, true, false, true, true)) : null;
            return this;
        }

        public a a(String str, @Nullable String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(v.a(str, v.g, false, false, true, true));
            this.g.add(str2 != null ? v.a(str2, v.g, false, false, true, true) : null);
            return this;
        }

        public a b(String str, @Nullable String str2) {
            if (str == null) {
                throw new NullPointerException("encodedName == null");
            }
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(v.a(str, v.f, true, false, true, true));
            this.g.add(str2 != null ? v.a(str2, v.f, true, false, true, true) : null);
            return this;
        }

        public a c(String str, @Nullable String str2) {
            n(str);
            a(str, str2);
            return this;
        }

        public a d(String str, @Nullable String str2) {
            o(str);
            b(str, str2);
            return this;
        }

        public a n(String str) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (this.g == null) {
                return this;
            }
            r(v.a(str, v.g, false, false, true, true));
            return this;
        }

        public a o(String str) {
            if (str == null) {
                throw new NullPointerException("encodedName == null");
            }
            if (this.g == null) {
                return this;
            }
            r(v.a(str, v.f, true, false, true, true));
            return this;
        }

        private void r(String str) {
            for (int size = this.g.size() - 2; size >= 0; size -= 2) {
                if (str.equals(this.g.get(size))) {
                    this.g.remove(size + 1);
                    this.g.remove(size);
                    if (this.g.isEmpty()) {
                        this.g = null;
                        return;
                    }
                }
            }
        }

        public a p(@Nullable String str) {
            this.h = str != null ? v.a(str, "", false, false, false, false) : null;
            return this;
        }

        public a q(@Nullable String str) {
            this.h = str != null ? v.a(str, "", true, false, false, false) : null;
            return this;
        }

        a b() {
            int size = this.f.size();
            for (int i = 0; i < size; i++) {
                this.f.set(i, v.a(this.f.get(i), v.d, true, true, false, true));
            }
            if (this.g != null) {
                int size2 = this.g.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    String str = this.g.get(i2);
                    if (str != null) {
                        this.g.set(i2, v.a(str, v.h, true, true, true, true));
                    }
                }
            }
            if (this.h != null) {
                this.h = v.a(this.h, v.k, true, true, false, false);
            }
            return this;
        }

        public v c() {
            if (this.f1283a == null) {
                throw new IllegalStateException("scheme == null");
            }
            if (this.d == null) {
                throw new IllegalStateException("host == null");
            }
            return new v(this);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f1283a);
            sb.append(HttpConstant.SCHEME_SPLIT);
            if (!this.f1284b.isEmpty() || !this.f1285c.isEmpty()) {
                sb.append(this.f1284b);
                if (!this.f1285c.isEmpty()) {
                    sb.append(':');
                    sb.append(this.f1285c);
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
            if (iA != v.a(this.f1283a)) {
                sb.append(':');
                sb.append(iA);
            }
            v.a(sb, this.f);
            if (this.g != null) {
                sb.append('?');
                v.b(sb, this.g);
            }
            if (this.h != null) {
                sb.append('#');
                sb.append(this.h);
            }
            return sb.toString();
        }

        EnumC0038a a(@Nullable v vVar, String str) {
            int iA;
            int i;
            int i2;
            int iA2 = b.a.c.a(str, 0, str.length());
            int iB = b.a.c.b(str, iA2, str.length());
            if (b(str, iA2, iB) != -1) {
                if (str.regionMatches(true, iA2, "https:", 0, 6)) {
                    this.f1283a = "https";
                    iA2 += "https:".length();
                } else if (str.regionMatches(true, iA2, "http:", 0, 5)) {
                    this.f1283a = HttpConstant.HTTP;
                    iA2 += "http:".length();
                } else {
                    return EnumC0038a.UNSUPPORTED_SCHEME;
                }
            } else if (vVar != null) {
                this.f1283a = vVar.l;
            } else {
                return EnumC0038a.MISSING_SCHEME;
            }
            int iC = c(str, iA2, iB);
            char c2 = '#';
            if (iC >= 2 || vVar == null || !vVar.l.equals(this.f1283a)) {
                int i3 = iA2 + iC;
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    iA = b.a.c.a(str, i3, iB, "@/\\?#");
                    char cCharAt = iA != iB ? str.charAt(iA) : (char) 65535;
                    if (cCharAt != 65535 && cCharAt != c2 && cCharAt != '/' && cCharAt != '\\') {
                        switch (cCharAt) {
                            case '?':
                                break;
                            case '@':
                                if (!z) {
                                    int iA3 = b.a.c.a(str, i3, iA, ':');
                                    i2 = iA;
                                    String strA = v.a(str, i3, iA3, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                    if (z2) {
                                        strA = this.f1284b + "%40" + strA;
                                    }
                                    this.f1284b = strA;
                                    if (iA3 != i2) {
                                        this.f1285c = v.a(str, iA3 + 1, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                        z = true;
                                    }
                                    z2 = true;
                                } else {
                                    i2 = iA;
                                    this.f1285c += "%40" + v.a(str, i3, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                }
                                i3 = i2 + 1;
                                continue;
                                c2 = '#';
                            default:
                                c2 = '#';
                        }
                    }
                }
                i = iA;
                int iD = d(str, i3, i);
                int i4 = iD + 1;
                if (i4 < i) {
                    this.d = e(str, i3, iD);
                    this.e = f(str, i4, i);
                    if (this.e == -1) {
                        return EnumC0038a.INVALID_PORT;
                    }
                } else {
                    this.d = e(str, i3, iD);
                    this.e = v.a(this.f1283a);
                }
                if (this.d == null) {
                    return EnumC0038a.INVALID_HOST;
                }
            } else {
                this.f1284b = vVar.e();
                this.f1285c = vVar.g();
                this.d = vVar.m;
                this.e = vVar.n;
                this.f.clear();
                this.f.addAll(vVar.m());
                if (iA2 == iB || str.charAt(iA2) == '#') {
                    m(vVar.o());
                }
                i = iA2;
            }
            int iA4 = b.a.c.a(str, i, iB, "?#");
            a(str, i, iA4);
            if (iA4 < iB && str.charAt(iA4) == '?') {
                int iA5 = b.a.c.a(str, iA4, iB, '#');
                this.g = v.b(v.a(str, iA4 + 1, iA5, v.e, true, false, true, true, null));
                iA4 = iA5;
            }
            if (iA4 < iB && str.charAt(iA4) == '#') {
                this.h = v.a(str, 1 + iA4, iB, "", true, false, false, false, null);
            }
            return EnumC0038a.SUCCESS;
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
                i = b.a.c.a(str, i3, i2, "/\\");
                boolean z = i < i2;
                a(str, i3, i, z, true);
                if (z) {
                    i++;
                }
            }
        }

        private void a(String str, int i, int i2, boolean z, boolean z2) {
            String strA = v.a(str, i, i2, v.f1281c, z2, false, false, true, null);
            if (s(strA)) {
                return;
            }
            if (t(strA)) {
                d();
                return;
            }
            if (this.f.get(this.f.size() - 1).isEmpty()) {
                this.f.set(this.f.size() - 1, strA);
            } else {
                this.f.add(strA);
            }
            if (z) {
                this.f.add("");
            }
        }

        private boolean s(String str) {
            return str.equals(".") || str.equalsIgnoreCase("%2e");
        }

        private boolean t(String str) {
            return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        private void d() {
            if (this.f.remove(this.f.size() - 1).isEmpty() && !this.f.isEmpty()) {
                this.f.set(this.f.size() - 1, "");
            } else {
                this.f.add("");
            }
        }

        private static int b(String str, int i, int i2) {
            if (i2 - i < 2) {
                return -1;
            }
            char cCharAt = str.charAt(i);
            if ((cCharAt < 'a' || cCharAt > 'z') && (cCharAt < 'A' || cCharAt > 'Z')) {
                return -1;
            }
            while (true) {
                i++;
                if (i >= i2) {
                    return -1;
                }
                char cCharAt2 = str.charAt(i);
                if (cCharAt2 < 'a' || cCharAt2 > 'z') {
                    if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                        if (cCharAt2 < '0' || cCharAt2 > '9') {
                            if (cCharAt2 != '+' && cCharAt2 != '-' && cCharAt2 != '.') {
                                if (cCharAt2 == ':') {
                                    return i;
                                }
                                return -1;
                            }
                        }
                    }
                }
            }
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
            return b.a.c.a(v.a(str, i, i2, false));
        }

        private static int f(String str, int i, int i2) throws NumberFormatException {
            try {
                int i3 = Integer.parseInt(v.a(str, i, i2, "", false, false, false, true, null));
                if (i3 <= 0 || i3 > 65535) {
                    return -1;
                }
                return i3;
            } catch (NumberFormatException unused) {
                return -1;
            }
        }
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
                c.c cVar = new c.c();
                cVar.b(str, i2, i4);
                a(cVar, str, i4, i3, z);
                return cVar.t();
            }
        }
        return str.substring(i2, i3);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void a(c.c r5, java.lang.String r6, int r7, int r8, boolean r9) {
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
            int r2 = b.a.c.a(r2)
            char r3 = r6.charAt(r1)
            int r3 = b.a.c.a(r3)
            r4 = -1
            if (r2 == r4) goto L39
            if (r3 == r4) goto L39
            int r7 = r2 << 4
            int r7 = r7 + r3
            r5.m(r7)
            r7 = r1
            goto L3c
        L2d:
            r1 = 43
            if (r0 != r1) goto L39
            if (r9 == 0) goto L39
            r1 = 32
            r5.m(r1)
            goto L3c
        L39:
            r5.n(r0)
        L3c:
            int r0 = java.lang.Character.charCount(r0)
            int r7 = r7 + r0
            goto L0
        L42:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.v.a(c.c, java.lang.String, int, int, boolean):void");
    }

    static boolean a(String str, int i2, int i3) {
        int i4 = i2 + 2;
        return i4 < i3 && str.charAt(i2) == '%' && b.a.c.a(str.charAt(i2 + 1)) != -1 && b.a.c.a(str.charAt(i4)) != -1;
    }

    static String a(String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        int iCharCount = i2;
        while (iCharCount < i3) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt >= 32 && iCodePointAt != 127 && (iCodePointAt < 128 || !z4)) {
                if (str2.indexOf(iCodePointAt) == -1 && ((iCodePointAt != 37 || (z && (!z2 || a(str, iCharCount, i3)))) && (iCodePointAt != 43 || !z3))) {
                    iCharCount += Character.charCount(iCodePointAt);
                } else {
                    c.c cVar = new c.c();
                    cVar.b(str, i2, iCharCount);
                    a(cVar, str, iCharCount, i3, str2, z, z2, z3, z4, charset);
                    return cVar.t();
                }
            } else {
                c.c cVar2 = new c.c();
                cVar2.b(str, i2, iCharCount);
                a(cVar2, str, iCharCount, i3, str2, z, z2, z3, z4, charset);
                return cVar2.t();
            }
        }
        return str.substring(i2, i3);
    }

    static void a(c.c cVar, String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        c.c cVar2 = null;
        while (i2 < i3) {
            int iCodePointAt = str.codePointAt(i2);
            if (!z || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt == 43 && z3) {
                    cVar.b(z ? "+" : "%2B");
                } else if (iCodePointAt < 32 || iCodePointAt == 127 || ((iCodePointAt >= 128 && z4) || str2.indexOf(iCodePointAt) != -1 || (iCodePointAt == 37 && (!z || (z2 && !a(str, i2, i3)))))) {
                    if (cVar2 == null) {
                        cVar2 = new c.c();
                    }
                    if (charset == null || charset.equals(b.a.c.e)) {
                        cVar2.n(iCodePointAt);
                    } else {
                        cVar2.b(str, i2, Character.charCount(iCodePointAt) + i2, charset);
                    }
                    while (!cVar2.g()) {
                        int iJ = cVar2.j() & 255;
                        cVar.m(37);
                        cVar.m((int) o[(iJ >> 4) & 15]);
                        cVar.m((int) o[iJ & 15]);
                    }
                } else {
                    cVar.n(iCodePointAt);
                }
            }
            i2 += Character.charCount(iCodePointAt);
        }
    }

    static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, charset);
    }

    static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, null);
    }
}
