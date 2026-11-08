package b;

import com.bytedance.sdk.account.utils.UrlBuilder;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class s extends ad {

    /* renamed from: a, reason: collision with root package name */
    private static final x f1268a = x.a(UrlBuilder.CONTENT_TYPE);

    /* renamed from: b, reason: collision with root package name */
    private final List<String> f1269b;

    /* renamed from: c, reason: collision with root package name */
    private final List<String> f1270c;

    s(List<String> list, List<String> list2) {
        this.f1269b = b.a.c.a(list);
        this.f1270c = b.a.c.a(list2);
    }

    public int a() {
        return this.f1269b.size();
    }

    public String a(int i) {
        return this.f1269b.get(i);
    }

    public String b(int i) {
        return v.a(a(i), true);
    }

    public String c(int i) {
        return this.f1270c.get(i);
    }

    public String d(int i) {
        return v.a(c(i), true);
    }

    @Override // b.ad
    public x b() {
        return f1268a;
    }

    @Override // b.ad
    public long c() {
        return a((c.d) null, true);
    }

    @Override // b.ad
    public void a(c.d dVar) throws IOException {
        a(dVar, false);
    }

    private long a(@Nullable c.d dVar, boolean z) {
        c.c cVarC;
        if (z) {
            cVarC = new c.c();
        } else {
            cVarC = dVar.c();
        }
        int size = this.f1269b.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                cVarC.m(38);
            }
            cVarC.b(this.f1269b.get(i));
            cVarC.m(61);
            cVarC.b(this.f1270c.get(i));
        }
        if (!z) {
            return 0L;
        }
        long jB = cVarC.b();
        cVarC.y();
        return jB;
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private final List<String> f1271a;

        /* renamed from: b, reason: collision with root package name */
        private final List<String> f1272b;

        /* renamed from: c, reason: collision with root package name */
        private final Charset f1273c;

        public a() {
            this(null);
        }

        public a(Charset charset) {
            this.f1271a = new ArrayList();
            this.f1272b = new ArrayList();
            this.f1273c = charset;
        }

        public a a(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (str2 == null) {
                throw new NullPointerException("value == null");
            }
            this.f1271a.add(v.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.f1273c));
            this.f1272b.add(v.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.f1273c));
            return this;
        }

        public a b(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (str2 == null) {
                throw new NullPointerException("value == null");
            }
            this.f1271a.add(v.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.f1273c));
            this.f1272b.add(v.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.f1273c));
            return this;
        }

        public s a() {
            return new s(this.f1271a, this.f1272b);
        }
    }
}
