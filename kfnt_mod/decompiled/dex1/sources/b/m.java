package b;

import anet.channel.util.HttpConstant;
import com.appsflyer.share.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class m {

    /* renamed from: a, reason: collision with root package name */
    private static final Pattern f1255a = Pattern.compile("(\\d{2,4})[^\\d]*");

    /* renamed from: b, reason: collision with root package name */
    private static final Pattern f1256b = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");

    /* renamed from: c, reason: collision with root package name */
    private static final Pattern f1257c = Pattern.compile("(\\d{1,2})[^\\d]*");
    private static final Pattern d = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
    private final String e;
    private final String f;
    private final long g;
    private final String h;
    private final String i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final boolean m;

    private m(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.e = str;
        this.f = str2;
        this.g = j;
        this.h = str3;
        this.i = str4;
        this.j = z;
        this.k = z2;
        this.m = z3;
        this.l = z4;
    }

    m(a aVar) {
        if (aVar.f1258a == null) {
            throw new NullPointerException("builder.name == null");
        }
        if (aVar.f1259b == null) {
            throw new NullPointerException("builder.value == null");
        }
        if (aVar.d == null) {
            throw new NullPointerException("builder.domain == null");
        }
        this.e = aVar.f1258a;
        this.f = aVar.f1259b;
        this.g = aVar.f1260c;
        this.h = aVar.d;
        this.i = aVar.e;
        this.j = aVar.f;
        this.k = aVar.g;
        this.l = aVar.h;
        this.m = aVar.i;
    }

    public String a() {
        return this.e;
    }

    public String b() {
        return this.f;
    }

    public boolean c() {
        return this.l;
    }

    public long d() {
        return this.g;
    }

    public boolean e() {
        return this.m;
    }

    public String f() {
        return this.h;
    }

    public String g() {
        return this.i;
    }

    public boolean h() {
        return this.k;
    }

    public boolean i() {
        return this.j;
    }

    public boolean a(v vVar) {
        boolean zA;
        if (this.m) {
            zA = vVar.i().equals(this.h);
        } else {
            zA = a(vVar.i(), this.h);
        }
        if (zA && b(vVar, this.i)) {
            return !this.j || vVar.d();
        }
        return false;
    }

    private static boolean a(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        return str.endsWith(str2) && str.charAt((str.length() - str2.length()) - 1) == '.' && !b.a.c.c(str);
    }

    private static boolean b(v vVar, String str) {
        String strL = vVar.l();
        if (strL.equals(str)) {
            return true;
        }
        if (strL.startsWith(str)) {
            return str.endsWith(Constants.URL_PATH_DELIMITER) || strL.charAt(str.length()) == '/';
        }
        return false;
    }

    @Nullable
    public static m a(v vVar, String str) {
        return a(System.currentTimeMillis(), vVar, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00c9 A[PHI: r2
  0x00c9: PHI (r2v9 long) = (r2v1 long), (r2v6 long) binds: [B:42:0x00c7, B:53:0x00eb] A[DONT_GENERATE, DONT_INLINE]] */
    @javax.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static b.m a(long r25, b.v r27, java.lang.String r28) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b.m.a(long, b.v, java.lang.String):b.m");
    }

    private static long a(String str, int i, int i2) throws NumberFormatException {
        int iA = a(str, i, i2, false);
        Matcher matcher = d.matcher(str);
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int iIndexOf = -1;
        int i6 = -1;
        int i7 = -1;
        while (iA < i2) {
            int iA2 = a(str, iA + 1, i2, true);
            matcher.region(iA, iA2);
            if (i3 == -1 && matcher.usePattern(d).matches()) {
                int i8 = Integer.parseInt(matcher.group(1));
                int i9 = Integer.parseInt(matcher.group(2));
                i7 = Integer.parseInt(matcher.group(3));
                i6 = i9;
                i3 = i8;
            } else if (i5 == -1 && matcher.usePattern(f1257c).matches()) {
                i5 = Integer.parseInt(matcher.group(1));
            } else if (iIndexOf == -1 && matcher.usePattern(f1256b).matches()) {
                iIndexOf = f1256b.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
            } else if (i4 == -1 && matcher.usePattern(f1255a).matches()) {
                i4 = Integer.parseInt(matcher.group(1));
            }
            iA = a(str, iA2 + 1, i2, false);
        }
        if (i4 >= 70 && i4 <= 99) {
            i4 += 1900;
        }
        if (i4 >= 0 && i4 <= 69) {
            i4 += 2000;
        }
        if (i4 < 1601) {
            throw new IllegalArgumentException();
        }
        if (iIndexOf == -1) {
            throw new IllegalArgumentException();
        }
        if (i5 < 1 || i5 > 31) {
            throw new IllegalArgumentException();
        }
        if (i3 < 0 || i3 > 23) {
            throw new IllegalArgumentException();
        }
        if (i6 < 0 || i6 > 59) {
            throw new IllegalArgumentException();
        }
        if (i7 < 0 || i7 > 59) {
            throw new IllegalArgumentException();
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(b.a.c.g);
        gregorianCalendar.setLenient(false);
        gregorianCalendar.set(1, i4);
        gregorianCalendar.set(2, iIndexOf - 1);
        gregorianCalendar.set(5, i5);
        gregorianCalendar.set(11, i3);
        gregorianCalendar.set(12, i6);
        gregorianCalendar.set(13, i7);
        gregorianCalendar.set(14, 0);
        return gregorianCalendar.getTimeInMillis();
    }

    private static int a(String str, int i, int i2, boolean z) {
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (((cCharAt < ' ' && cCharAt != '\t') || cCharAt >= 127 || (cCharAt >= '0' && cCharAt <= '9') || ((cCharAt >= 'a' && cCharAt <= 'z') || ((cCharAt >= 'A' && cCharAt <= 'Z') || cCharAt == ':'))) == (!z)) {
                return i;
            }
            i++;
        }
        return i2;
    }

    private static long a(String str) throws NumberFormatException {
        try {
            long j = Long.parseLong(str);
            if (j <= 0) {
                return Long.MIN_VALUE;
            }
            return j;
        } catch (NumberFormatException e) {
            if (str.matches("-?\\d+")) {
                return str.startsWith("-") ? Long.MIN_VALUE : Long.MAX_VALUE;
            }
            throw e;
        }
    }

    private static String b(String str) {
        if (str.endsWith(".")) {
            throw new IllegalArgumentException();
        }
        if (str.startsWith(".")) {
            str = str.substring(1);
        }
        String strA = b.a.c.a(str);
        if (strA != null) {
            return strA;
        }
        throw new IllegalArgumentException();
    }

    public static List<m> a(v vVar, u uVar) {
        List<String> listC = uVar.c(HttpConstant.SET_COOKIE);
        int size = listC.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            m mVarA = a(vVar, listC.get(i));
            if (mVarA != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(mVarA);
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        String f1258a;

        /* renamed from: b, reason: collision with root package name */
        String f1259b;
        String d;
        boolean f;
        boolean g;
        boolean h;
        boolean i;

        /* renamed from: c, reason: collision with root package name */
        long f1260c = 253402300799999L;
        String e = Constants.URL_PATH_DELIMITER;

        public a a(String str) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (!str.trim().equals(str)) {
                throw new IllegalArgumentException("name is not trimmed");
            }
            this.f1258a = str;
            return this;
        }

        public a b(String str) {
            if (str == null) {
                throw new NullPointerException("value == null");
            }
            if (!str.trim().equals(str)) {
                throw new IllegalArgumentException("value is not trimmed");
            }
            this.f1259b = str;
            return this;
        }

        public a a(long j) {
            if (j <= 0) {
                j = Long.MIN_VALUE;
            }
            if (j > 253402300799999L) {
                j = 253402300799999L;
            }
            this.f1260c = j;
            this.h = true;
            return this;
        }

        public a c(String str) {
            return a(str, false);
        }

        public a d(String str) {
            return a(str, true);
        }

        private a a(String str, boolean z) {
            if (str == null) {
                throw new NullPointerException("domain == null");
            }
            String strA = b.a.c.a(str);
            if (strA == null) {
                throw new IllegalArgumentException("unexpected domain: " + str);
            }
            this.d = strA;
            this.i = z;
            return this;
        }

        public a e(String str) {
            if (!str.startsWith(Constants.URL_PATH_DELIMITER)) {
                throw new IllegalArgumentException("path must start with '/'");
            }
            this.e = str;
            return this;
        }

        public a a() {
            this.f = true;
            return this;
        }

        public a b() {
            this.g = true;
            return this;
        }

        public m c() {
            return new m(this);
        }
    }

    public String toString() {
        return a(false);
    }

    String a(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.e);
        sb.append('=');
        sb.append(this.f);
        if (this.l) {
            if (this.g == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(b.a.d.d.a(new Date(this.g)));
            }
        }
        if (!this.m) {
            sb.append("; domain=");
            if (z) {
                sb.append(".");
            }
            sb.append(this.h);
        }
        sb.append("; path=");
        sb.append(this.i);
        if (this.j) {
            sb.append("; secure");
        }
        if (this.k) {
            sb.append("; httponly");
        }
        return sb.toString();
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return mVar.e.equals(this.e) && mVar.f.equals(this.f) && mVar.h.equals(this.h) && mVar.i.equals(this.i) && mVar.g == this.g && mVar.j == this.j && mVar.k == this.k && mVar.l == this.l && mVar.m == this.m;
    }

    public int hashCode() {
        return ((((((((((((((((527 + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.h.hashCode()) * 31) + this.i.hashCode()) * 31) + ((int) (this.g ^ (this.g >>> 32)))) * 31) + (!this.j ? 1 : 0)) * 31) + (!this.k ? 1 : 0)) * 31) + (!this.l ? 1 : 0)) * 31) + (!this.m ? 1 : 0);
    }
}
