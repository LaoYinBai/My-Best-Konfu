package b.a.f;

import androidx.appcompat.widget.ActivityChooserView;
import anet.channel.util.HttpConstant;
import c.p;
import c.y;
import com.appsflyer.share.Constants;
import com.baidu.mobads.sdk.internal.bs;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class d {

    /* renamed from: a, reason: collision with root package name */
    static final c[] f853a = {new c(c.f, ""), new c(c.f852c, "GET"), new c(c.f852c, "POST"), new c(c.d, Constants.URL_PATH_DELIMITER), new c(c.d, "/index.html"), new c(c.e, HttpConstant.HTTP), new c(c.e, "https"), new c(c.f851b, "200"), new c(c.f851b, "204"), new c(c.f851b, "206"), new c(c.f851b, "304"), new c(c.f851b, "400"), new c(c.f851b, bs.f3181b), new c(c.f851b, "500"), new c("accept-charset", ""), new c("accept-encoding", "gzip, deflate"), new c("accept-language", ""), new c("accept-ranges", ""), new c("accept", ""), new c("access-control-allow-origin", ""), new c("age", ""), new c("allow", ""), new c("authorization", ""), new c("cache-control", ""), new c("content-disposition", ""), new c("content-encoding", ""), new c("content-language", ""), new c("content-length", ""), new c("content-location", ""), new c("content-range", ""), new c(com.alipay.sdk.packet.e.d, ""), new c("cookie", ""), new c("date", ""), new c("etag", ""), new c("expect", ""), new c("expires", ""), new c(ParamKeyConstants.WebViewConstants.QUERY_FROM, ""), new c(com.alipay.sdk.cons.c.f, ""), new c("if-match", ""), new c("if-modified-since", ""), new c("if-none-match", ""), new c("if-range", ""), new c("if-unmodified-since", ""), new c("last-modified", ""), new c("link", ""), new c("location", ""), new c("max-forwards", ""), new c("proxy-authenticate", ""), new c("proxy-authorization", ""), new c("range", ""), new c("referer", ""), new c(com.alipay.sdk.widget.d.n, ""), new c("retry-after", ""), new c("server", ""), new c("set-cookie", ""), new c("strict-transport-security", ""), new c("transfer-encoding", ""), new c("user-agent", ""), new c("vary", ""), new c("via", ""), new c("www-authenticate", "")};

    /* renamed from: b, reason: collision with root package name */
    static final Map<c.f, Integer> f854b = a();

    /* renamed from: c, reason: collision with root package name */
    private static final int f855c = 15;
    private static final int d = 31;
    private static final int e = 63;
    private static final int f = 127;

    private d() {
    }

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        c[] f856a;

        /* renamed from: b, reason: collision with root package name */
        int f857b;

        /* renamed from: c, reason: collision with root package name */
        int f858c;
        int d;
        private final List<c> e;
        private final c.e f;
        private final int g;
        private int h;

        a(int i, y yVar) {
            this(i, i, yVar);
        }

        a(int i, int i2, y yVar) {
            this.e = new ArrayList();
            this.f856a = new c[8];
            this.f857b = this.f856a.length - 1;
            this.f858c = 0;
            this.d = 0;
            this.g = i;
            this.h = i2;
            this.f = p.a(yVar);
        }

        int a() {
            return this.h;
        }

        private void e() {
            if (this.h < this.d) {
                if (this.h == 0) {
                    f();
                } else {
                    a(this.d - this.h);
                }
            }
        }

        private void f() {
            Arrays.fill(this.f856a, (Object) null);
            this.f857b = this.f856a.length - 1;
            this.f858c = 0;
            this.d = 0;
        }

        private int a(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.f856a.length;
                while (true) {
                    length--;
                    if (length < this.f857b || i <= 0) {
                        break;
                    }
                    i -= this.f856a[length].i;
                    this.d -= this.f856a[length].i;
                    this.f858c--;
                    i2++;
                }
                System.arraycopy(this.f856a, this.f857b + 1, this.f856a, this.f857b + 1 + i2, this.f858c);
                this.f857b += i2;
            }
            return i2;
        }

        void b() throws IOException {
            while (!this.f.g()) {
                int iJ = this.f.j() & 255;
                if (iJ == 128) {
                    throw new IOException("index == 0");
                }
                if ((iJ & 128) == 128) {
                    b(a(iJ, d.f) - 1);
                } else if (iJ == 64) {
                    h();
                } else if ((iJ & 64) == 64) {
                    e(a(iJ, 63) - 1);
                } else if ((iJ & 32) == 32) {
                    this.h = a(iJ, 31);
                    if (this.h < 0 || this.h > this.g) {
                        throw new IOException("Invalid dynamic table size update " + this.h);
                    }
                    e();
                } else if (iJ == 16 || iJ == 0) {
                    g();
                } else {
                    d(a(iJ, 15) - 1);
                }
            }
        }

        public List<c> c() {
            ArrayList arrayList = new ArrayList(this.e);
            this.e.clear();
            return arrayList;
        }

        private void b(int i) throws IOException {
            if (g(i)) {
                this.e.add(d.f853a[i]);
                return;
            }
            int iC = c(i - d.f853a.length);
            if (iC < 0 || iC >= this.f856a.length) {
                throw new IOException("Header index too large " + (i + 1));
            }
            this.e.add(this.f856a[iC]);
        }

        private int c(int i) {
            return this.f857b + 1 + i;
        }

        private void d(int i) throws IOException {
            this.e.add(new c(f(i), d()));
        }

        private void g() throws IOException {
            this.e.add(new c(d.a(d()), d()));
        }

        private void e(int i) throws IOException {
            a(-1, new c(f(i), d()));
        }

        private void h() throws IOException {
            a(-1, new c(d.a(d()), d()));
        }

        private c.f f(int i) throws IOException {
            if (g(i)) {
                return d.f853a[i].g;
            }
            int iC = c(i - d.f853a.length);
            if (iC < 0 || iC >= this.f856a.length) {
                throw new IOException("Header index too large " + (i + 1));
            }
            return this.f856a[iC].g;
        }

        private boolean g(int i) {
            return i >= 0 && i <= d.f853a.length - 1;
        }

        private void a(int i, c cVar) {
            this.e.add(cVar);
            int i2 = cVar.i;
            if (i != -1) {
                i2 -= this.f856a[c(i)].i;
            }
            if (i2 > this.h) {
                f();
                return;
            }
            int iA = a((this.d + i2) - this.h);
            if (i == -1) {
                if (this.f858c + 1 > this.f856a.length) {
                    c[] cVarArr = new c[this.f856a.length * 2];
                    System.arraycopy(this.f856a, 0, cVarArr, this.f856a.length, this.f856a.length);
                    this.f857b = this.f856a.length - 1;
                    this.f856a = cVarArr;
                }
                int i3 = this.f857b;
                this.f857b = i3 - 1;
                this.f856a[i3] = cVar;
                this.f858c++;
            } else {
                this.f856a[i + c(i) + iA] = cVar;
            }
            this.d += i2;
        }

        private int i() throws IOException {
            return this.f.j() & 255;
        }

        int a(int i, int i2) throws IOException {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                int i5 = i();
                if ((i5 & 128) == 0) {
                    return i2 + (i5 << i4);
                }
                i2 += (i5 & d.f) << i4;
                i4 += 7;
            }
        }

        c.f d() throws IOException {
            int i = i();
            boolean z = (i & 128) == 128;
            int iA = a(i, d.f);
            if (z) {
                return c.f.a(k.a().a(this.f.h(iA)));
            }
            return this.f.d(iA);
        }
    }

    private static Map<c.f, Integer> a() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(f853a.length);
        for (int i = 0; i < f853a.length; i++) {
            if (!linkedHashMap.containsKey(f853a[i].g)) {
                linkedHashMap.put(f853a[i].g, Integer.valueOf(i));
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    static final class b {
        private static final int g = 4096;
        private static final int h = 16384;

        /* renamed from: a, reason: collision with root package name */
        int f859a;

        /* renamed from: b, reason: collision with root package name */
        int f860b;

        /* renamed from: c, reason: collision with root package name */
        c[] f861c;
        int d;
        int e;
        int f;
        private final c.c i;
        private final boolean j;
        private int k;
        private boolean l;

        b(c.c cVar) {
            this(4096, true, cVar);
        }

        b(int i, boolean z, c.c cVar) {
            this.k = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            this.f861c = new c[8];
            this.d = this.f861c.length - 1;
            this.e = 0;
            this.f = 0;
            this.f859a = i;
            this.f860b = i;
            this.j = z;
            this.i = cVar;
        }

        private void a() {
            Arrays.fill(this.f861c, (Object) null);
            this.d = this.f861c.length - 1;
            this.e = 0;
            this.f = 0;
        }

        private int b(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.f861c.length;
                while (true) {
                    length--;
                    if (length < this.d || i <= 0) {
                        break;
                    }
                    i -= this.f861c[length].i;
                    this.f -= this.f861c[length].i;
                    this.e--;
                    i2++;
                }
                System.arraycopy(this.f861c, this.d + 1, this.f861c, this.d + 1 + i2, this.e);
                Arrays.fill(this.f861c, this.d + 1, this.d + 1 + i2, (Object) null);
                this.d += i2;
            }
            return i2;
        }

        private void a(c cVar) {
            int i = cVar.i;
            if (i > this.f860b) {
                a();
                return;
            }
            b((this.f + i) - this.f860b);
            if (this.e + 1 > this.f861c.length) {
                c[] cVarArr = new c[this.f861c.length * 2];
                System.arraycopy(this.f861c, 0, cVarArr, this.f861c.length, this.f861c.length);
                this.d = this.f861c.length - 1;
                this.f861c = cVarArr;
            }
            int i2 = this.d;
            this.d = i2 - 1;
            this.f861c[i2] = cVar;
            this.e++;
            this.f += i;
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x006e  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void a(java.util.List<b.a.f.c> r14) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 240
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: b.a.f.d.b.a(java.util.List):void");
        }

        void a(int i, int i2, int i3) {
            if (i < i2) {
                this.i.m(i | i3);
                return;
            }
            this.i.m(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.i.m(128 | (i4 & d.f));
                i4 >>>= 7;
            }
            this.i.m(i4);
        }

        void a(c.f fVar) throws IOException {
            if (this.j && k.a().a(fVar) < fVar.k()) {
                c.c cVar = new c.c();
                k.a().a(fVar, cVar);
                c.f fVarS = cVar.s();
                a(fVarS.k(), d.f, 128);
                this.i.g(fVarS);
                return;
            }
            a(fVar.k(), d.f, 0);
            this.i.g(fVar);
        }

        void a(int i) {
            this.f859a = i;
            int iMin = Math.min(i, 16384);
            if (this.f860b == iMin) {
                return;
            }
            if (iMin < this.f860b) {
                this.k = Math.min(this.k, iMin);
            }
            this.l = true;
            this.f860b = iMin;
            b();
        }

        private void b() {
            if (this.f860b < this.f) {
                if (this.f860b == 0) {
                    a();
                } else {
                    b(this.f - this.f860b);
                }
            }
        }
    }

    static c.f a(c.f fVar) throws IOException {
        int iK = fVar.k();
        for (int i = 0; i < iK; i++) {
            byte b2 = fVar.b(i);
            if (b2 >= 65 && b2 <= 90) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + fVar.a());
            }
        }
        return fVar;
    }
}
