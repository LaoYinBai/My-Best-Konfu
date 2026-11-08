package b;

import com.bytedance.sdk.account.api.BDAccountNetApi;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    public static final g f1232a = new a().a();

    /* renamed from: b, reason: collision with root package name */
    private final Set<b> f1233b;

    /* renamed from: c, reason: collision with root package name */
    @Nullable
    private final b.a.j.c f1234c;

    g(Set<b> set, @Nullable b.a.j.c cVar) {
        this.f1233b = set;
        this.f1234c = cVar;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            if (b.a.c.a(this.f1234c, gVar.f1234c) && this.f1233b.equals(gVar.f1233b)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((this.f1234c != null ? this.f1234c.hashCode() : 0) * 31) + this.f1233b.hashCode();
    }

    public void a(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        List<b> listA = a(str);
        if (listA.isEmpty()) {
            return;
        }
        if (this.f1234c != null) {
            list = this.f1234c.a(list, str);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            X509Certificate x509Certificate = (X509Certificate) list.get(i);
            int size2 = listA.size();
            c.f fVarB = null;
            c.f fVarA = null;
            for (int i2 = 0; i2 < size2; i2++) {
                b bVar = listA.get(i2);
                if (bVar.f1238c.equals("sha256/")) {
                    if (fVarB == null) {
                        fVarB = b(x509Certificate);
                    }
                    if (bVar.d.equals(fVarB)) {
                        return;
                    }
                } else if (bVar.f1238c.equals("sha1/")) {
                    if (fVarA == null) {
                        fVarA = a(x509Certificate);
                    }
                    if (bVar.d.equals(fVarA)) {
                        return;
                    }
                } else {
                    throw new AssertionError("unsupported hashAlgorithm: " + bVar.f1238c);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Certificate pinning failure!");
        sb.append("\n  Peer certificate chain:");
        int size3 = list.size();
        for (int i3 = 0; i3 < size3; i3++) {
            X509Certificate x509Certificate2 = (X509Certificate) list.get(i3);
            sb.append("\n    ");
            sb.append(a((Certificate) x509Certificate2));
            sb.append(": ");
            sb.append(x509Certificate2.getSubjectDN().getName());
        }
        sb.append("\n  Pinned certificates for ");
        sb.append(str);
        sb.append(":");
        int size4 = listA.size();
        for (int i4 = 0; i4 < size4; i4++) {
            b bVar2 = listA.get(i4);
            sb.append("\n    ");
            sb.append(bVar2);
        }
        throw new SSLPeerUnverifiedException(sb.toString());
    }

    public void a(String str, Certificate... certificateArr) throws SSLPeerUnverifiedException {
        a(str, Arrays.asList(certificateArr));
    }

    List<b> a(String str) {
        List<b> listEmptyList = Collections.emptyList();
        for (b bVar : this.f1233b) {
            if (bVar.a(str)) {
                if (listEmptyList.isEmpty()) {
                    listEmptyList = new ArrayList<>();
                }
                listEmptyList.add(bVar);
            }
        }
        return listEmptyList;
    }

    g a(@Nullable b.a.j.c cVar) {
        return b.a.c.a(this.f1234c, cVar) ? this : new g(this.f1233b, cVar);
    }

    public static String a(Certificate certificate) {
        if (!(certificate instanceof X509Certificate)) {
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
        }
        return "sha256/" + b((X509Certificate) certificate).b();
    }

    static c.f a(X509Certificate x509Certificate) {
        return c.f.a(x509Certificate.getPublicKey().getEncoded()).d();
    }

    static c.f b(X509Certificate x509Certificate) {
        return c.f.a(x509Certificate.getPublicKey().getEncoded()).e();
    }

    static final class b {
        private static final String e = "*.";

        /* renamed from: a, reason: collision with root package name */
        final String f1236a;

        /* renamed from: b, reason: collision with root package name */
        final String f1237b;

        /* renamed from: c, reason: collision with root package name */
        final String f1238c;
        final c.f d;

        b(String str, String str2) {
            String strI;
            this.f1236a = str;
            if (str.startsWith(e)) {
                strI = v.g(BDAccountNetApi.Platform.PROJECT_MODE_SCHEME + str.substring(e.length())).i();
            } else {
                strI = v.g(BDAccountNetApi.Platform.PROJECT_MODE_SCHEME + str).i();
            }
            this.f1237b = strI;
            if (str2.startsWith("sha1/")) {
                this.f1238c = "sha1/";
                this.d = c.f.b(str2.substring("sha1/".length()));
            } else if (str2.startsWith("sha256/")) {
                this.f1238c = "sha256/";
                this.d = c.f.b(str2.substring("sha256/".length()));
            } else {
                throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + str2);
            }
            if (this.d != null) {
                return;
            }
            throw new IllegalArgumentException("pins must be base64: " + str2);
        }

        boolean a(String str) {
            if (this.f1236a.startsWith(e)) {
                int iIndexOf = str.indexOf(46);
                return (str.length() - iIndexOf) - 1 == this.f1237b.length() && str.regionMatches(false, iIndexOf + 1, this.f1237b, 0, this.f1237b.length());
            }
            return str.equals(this.f1237b);
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                if (this.f1236a.equals(bVar.f1236a) && this.f1238c.equals(bVar.f1238c) && this.d.equals(bVar.d)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return ((((527 + this.f1236a.hashCode()) * 31) + this.f1238c.hashCode()) * 31) + this.d.hashCode();
        }

        public String toString() {
            return this.f1238c + this.d.b();
        }
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private final List<b> f1235a = new ArrayList();

        public a a(String str, String... strArr) {
            if (str == null) {
                throw new NullPointerException("pattern == null");
            }
            for (String str2 : strArr) {
                this.f1235a.add(new b(str, str2));
            }
            return this;
        }

        public g a() {
            return new g(new LinkedHashSet(this.f1235a), null);
        }
    }
}
