package b;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class u {

    /* renamed from: a, reason: collision with root package name */
    private final String[] f1277a;

    u(a aVar) {
        this.f1277a = (String[]) aVar.f1278a.toArray(new String[aVar.f1278a.size()]);
    }

    private u(String[] strArr) {
        this.f1277a = strArr;
    }

    @Nullable
    public String a(String str) {
        return a(this.f1277a, str);
    }

    @Nullable
    public Date b(String str) {
        String strA = a(str);
        if (strA != null) {
            return b.a.d.d.a(strA);
        }
        return null;
    }

    public int a() {
        return this.f1277a.length / 2;
    }

    public String a(int i) {
        return this.f1277a[i * 2];
    }

    public String b(int i) {
        return this.f1277a[(i * 2) + 1];
    }

    public Set<String> b() {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        int iA = a();
        for (int i = 0; i < iA; i++) {
            treeSet.add(a(i));
        }
        return Collections.unmodifiableSet(treeSet);
    }

    public List<String> c(String str) {
        int iA = a();
        ArrayList arrayList = null;
        for (int i = 0; i < iA; i++) {
            if (str.equalsIgnoreCase(a(i))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(b(i));
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    public long c() {
        long length = this.f1277a.length * 2;
        for (int i = 0; i < this.f1277a.length; i++) {
            length += this.f1277a[i].length();
        }
        return length;
    }

    public a d() {
        a aVar = new a();
        Collections.addAll(aVar.f1278a, this.f1277a);
        return aVar;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof u) && Arrays.equals(((u) obj).f1277a, this.f1277a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.f1277a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int iA = a();
        for (int i = 0; i < iA; i++) {
            sb.append(a(i));
            sb.append(": ");
            sb.append(b(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    public Map<String, List<String>> e() {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        int iA = a();
        for (int i = 0; i < iA; i++) {
            String lowerCase = a(i).toLowerCase(Locale.US);
            List arrayList = (List) treeMap.get(lowerCase);
            if (arrayList == null) {
                arrayList = new ArrayList(2);
                treeMap.put(lowerCase, arrayList);
            }
            arrayList.add(b(i));
        }
        return treeMap;
    }

    private static String a(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    public static u a(String... strArr) {
        if (strArr == null) {
            throw new NullPointerException("namesAndValues == null");
        }
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        String[] strArr2 = (String[]) strArr.clone();
        for (int i = 0; i < strArr2.length; i++) {
            if (strArr2[i] == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            strArr2[i] = strArr2[i].trim();
        }
        for (int i2 = 0; i2 < strArr2.length; i2 += 2) {
            String str = strArr2[i2];
            String str2 = strArr2[i2 + 1];
            if (str.length() == 0 || str.indexOf(0) != -1 || str2.indexOf(0) != -1) {
                throw new IllegalArgumentException("Unexpected header: " + str + ": " + str2);
            }
        }
        return new u(strArr2);
    }

    public static u a(Map<String, String> map) {
        if (map == null) {
            throw new NullPointerException("headers == null");
        }
        String[] strArr = new String[map.size() * 2];
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            String strTrim = entry.getKey().trim();
            String strTrim2 = entry.getValue().trim();
            if (strTrim.length() == 0 || strTrim.indexOf(0) != -1 || strTrim2.indexOf(0) != -1) {
                throw new IllegalArgumentException("Unexpected header: " + strTrim + ": " + strTrim2);
            }
            strArr[i] = strTrim;
            strArr[i + 1] = strTrim2;
            i += 2;
        }
        return new u(strArr);
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        final List<String> f1278a = new ArrayList(20);

        a a(String str) {
            int iIndexOf = str.indexOf(":", 1);
            if (iIndexOf != -1) {
                return b(str.substring(0, iIndexOf), str.substring(iIndexOf + 1));
            }
            if (str.startsWith(":")) {
                return b("", str.substring(1));
            }
            return b("", str);
        }

        public a b(String str) {
            int iIndexOf = str.indexOf(":");
            if (iIndexOf == -1) {
                throw new IllegalArgumentException("Unexpected header: " + str);
            }
            return a(str.substring(0, iIndexOf).trim(), str.substring(iIndexOf + 1));
        }

        public a a(String str, String str2) {
            d(str, str2);
            return b(str, str2);
        }

        a b(String str, String str2) {
            this.f1278a.add(str);
            this.f1278a.add(str2.trim());
            return this;
        }

        public a c(String str) {
            int i = 0;
            while (i < this.f1278a.size()) {
                if (str.equalsIgnoreCase(this.f1278a.get(i))) {
                    this.f1278a.remove(i);
                    this.f1278a.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        public a c(String str, String str2) {
            d(str, str2);
            c(str);
            b(str, str2);
            return this;
        }

        private void d(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (str.isEmpty()) {
                throw new IllegalArgumentException("name is empty");
            }
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if (cCharAt <= ' ' || cCharAt >= 127) {
                    throw new IllegalArgumentException(b.a.c.a("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i), str));
                }
            }
            if (str2 == null) {
                throw new NullPointerException("value for name " + str + " == null");
            }
            int length2 = str2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                char cCharAt2 = str2.charAt(i2);
                if ((cCharAt2 <= 31 && cCharAt2 != '\t') || cCharAt2 >= 127) {
                    throw new IllegalArgumentException(b.a.c.a("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(cCharAt2), Integer.valueOf(i2), str, str2));
                }
            }
        }

        public String d(String str) {
            for (int size = this.f1278a.size() - 2; size >= 0; size -= 2) {
                if (str.equalsIgnoreCase(this.f1278a.get(size))) {
                    return this.f1278a.get(size + 1);
                }
            }
            return null;
        }

        public u a() {
            return new u(this);
        }
    }
}
