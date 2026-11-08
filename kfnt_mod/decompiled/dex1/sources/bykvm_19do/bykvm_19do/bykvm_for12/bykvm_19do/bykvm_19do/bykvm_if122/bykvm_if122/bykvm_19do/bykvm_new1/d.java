package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1;

import androidx.appcompat.widget.ActivityChooserView;
import anet.channel.util.HttpConstant;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s;
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
    static final c[] f1583a = {new c(c.i, ""), new c(c.f, "GET"), new c(c.f, "POST"), new c(c.g, Constants.URL_PATH_DELIMITER), new c(c.g, "/index.html"), new c(c.h, HttpConstant.HTTP), new c(c.h, "https"), new c(c.e, "200"), new c(c.e, "204"), new c(c.e, "206"), new c(c.e, "304"), new c(c.e, "400"), new c(c.e, bs.f3181b), new c(c.e, "500"), new c("accept-charset", ""), new c("accept-encoding", "gzip, deflate"), new c("accept-language", ""), new c("accept-ranges", ""), new c("accept", ""), new c("access-control-allow-origin", ""), new c("age", ""), new c("allow", ""), new c("authorization", ""), new c("cache-control", ""), new c("content-disposition", ""), new c("content-encoding", ""), new c("content-language", ""), new c("content-length", ""), new c("content-location", ""), new c("content-range", ""), new c(com.alipay.sdk.packet.e.d, ""), new c("cookie", ""), new c("date", ""), new c("etag", ""), new c("expect", ""), new c("expires", ""), new c(ParamKeyConstants.WebViewConstants.QUERY_FROM, ""), new c(com.alipay.sdk.cons.c.f, ""), new c("if-match", ""), new c("if-modified-since", ""), new c("if-none-match", ""), new c("if-range", ""), new c("if-unmodified-since", ""), new c("last-modified", ""), new c("link", ""), new c("location", ""), new c("max-forwards", ""), new c("proxy-authenticate", ""), new c("proxy-authorization", ""), new c("range", ""), new c("referer", ""), new c(com.alipay.sdk.widget.d.n, ""), new c("retry-after", ""), new c("server", ""), new c("set-cookie", ""), new c("strict-transport-security", ""), new c("transfer-encoding", ""), new c("user-agent", ""), new c("vary", ""), new c("via", ""), new c("www-authenticate", "")};

    /* renamed from: b, reason: collision with root package name */
    static final Map<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.f, Integer> f1584b = a();

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        private final List<c> f1585a;

        /* renamed from: b, reason: collision with root package name */
        private final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e f1586b;

        /* renamed from: c, reason: collision with root package name */
        private final int f1587c;
        private int d;
        c[] e;
        int f;
        int g;
        int h;

        a(int i, s sVar) {
            this(i, i, sVar);
        }

        private int b(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.e.length;
                while (true) {
                    length--;
                    if (length < this.f || i <= 0) {
                        break;
                    }
                    c[] cVarArr = this.e;
                    i -= cVarArr[length].f1582c;
                    this.h -= cVarArr[length].f1582c;
                    this.g--;
                    i2++;
                }
                c[] cVarArr2 = this.e;
                int i3 = this.f + 1;
                System.arraycopy(cVarArr2, i3, cVarArr2, i3 + i2, this.g);
                this.f += i2;
            }
            return i2;
        }

        private void d() {
            int i = this.d;
            int i2 = this.h;
            if (i < i2) {
                if (i == 0) {
                    e();
                } else {
                    b(i2 - i);
                }
            }
        }

        private void e() {
            Arrays.fill(this.e, (Object) null);
            this.f = this.e.length - 1;
            this.g = 0;
            this.h = 0;
        }

        private void f(int i) throws IOException {
            a(-1, new c(c(i), b()));
        }

        private void g(int i) throws IOException {
            this.f1585a.add(new c(c(i), b()));
        }

        private void h() throws IOException {
            this.f1585a.add(new c(d.a(b()), b()));
        }

        public List<c> a() {
            ArrayList arrayList = new ArrayList(this.f1585a);
            this.f1585a.clear();
            return arrayList;
        }

        void c() throws IOException {
            while (!this.f1586b.f()) {
                int i = this.f1586b.readByte() & 255;
                if (i == 128) {
                    throw new IOException("index == 0");
                }
                if ((i & 128) == 128) {
                    e(a(i, 127) - 1);
                } else if (i == 64) {
                    g();
                } else if ((i & 64) == 64) {
                    f(a(i, 63) - 1);
                } else if ((i & 32) == 32) {
                    this.d = a(i, 31);
                    int i2 = this.d;
                    if (i2 < 0 || i2 > this.f1587c) {
                        throw new IOException("Invalid dynamic table size update " + this.d);
                    }
                    d();
                } else if (i == 16 || i == 0) {
                    h();
                } else {
                    g(a(i, 15) - 1);
                }
            }
        }

        a(int i, int i2, s sVar) {
            this.f1585a = new ArrayList();
            this.e = new c[8];
            this.f = this.e.length - 1;
            this.g = 0;
            this.h = 0;
            this.f1587c = i;
            this.d = i2;
            this.f1586b = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.l.a(sVar);
        }

        private int a(int i) {
            return this.f + 1 + i;
        }

        private void a(int i, c cVar) {
            this.f1585a.add(cVar);
            int i2 = cVar.f1582c;
            if (i != -1) {
                i2 -= this.e[a(i)].f1582c;
            }
            int i3 = this.d;
            if (i2 > i3) {
                e();
                return;
            }
            int iB = b((this.h + i2) - i3);
            if (i == -1) {
                int i4 = this.g + 1;
                c[] cVarArr = this.e;
                if (i4 > cVarArr.length) {
                    c[] cVarArr2 = new c[cVarArr.length * 2];
                    System.arraycopy(cVarArr, 0, cVarArr2, cVarArr.length, cVarArr.length);
                    this.f = this.e.length - 1;
                    this.e = cVarArr2;
                }
                int i5 = this.f;
                this.f = i5 - 1;
                this.e[i5] = cVar;
                this.g++;
            } else {
                this.e[i + a(i) + iB] = cVar;
            }
            this.h += i2;
        }

        private int f() throws IOException {
            return this.f1586b.readByte() & 255;
        }

        private void g() throws IOException {
            a(-1, new c(d.a(b()), b()));
        }

        private void e(int i) throws IOException {
            if (d(i)) {
                this.f1585a.add(d.f1583a[i]);
                return;
            }
            int iA = a(i - d.f1583a.length);
            if (iA >= 0) {
                c[] cVarArr = this.e;
                if (iA <= cVarArr.length - 1) {
                    this.f1585a.add(cVarArr[iA]);
                    return;
                }
            }
            throw new IOException("Header index too large " + (i + 1));
        }

        private boolean d(int i) {
            return i >= 0 && i <= d.f1583a.length - 1;
        }

        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.f b() throws IOException {
            int iF = f();
            boolean z = (iF & 128) == 128;
            int iA = a(iF, 127);
            if (z) {
                return bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.f.a(k.b().a(this.f1586b.d(iA)));
            }
            return this.f1586b.b(iA);
        }

        private bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.f c(int i) {
            if (d(i)) {
                return d.f1583a[i].f1580a;
            }
            return this.e[a(i - d.f1583a.length)].f1580a;
        }

        int a(int i, int i2) throws IOException {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                int iF = f();
                if ((iF & 128) == 0) {
                    return i2 + (iF << i4);
                }
                i2 += (iF & 127) << i4;
                i4 += 7;
            }
        }
    }

    static final class b {

        /* renamed from: a, reason: collision with root package name */
        private final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c f1588a;

        /* renamed from: b, reason: collision with root package name */
        private final boolean f1589b;

        /* renamed from: c, reason: collision with root package name */
        private int f1590c;
        private boolean d;
        int e;
        c[] f;
        int g;
        int h;
        int i;

        b(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar) {
            this(4096, true, cVar);
        }

        private void a(c cVar) {
            int i = cVar.f1582c;
            int i2 = this.e;
            if (i > i2) {
                b();
                return;
            }
            b((this.i + i) - i2);
            int i3 = this.h + 1;
            c[] cVarArr = this.f;
            if (i3 > cVarArr.length) {
                c[] cVarArr2 = new c[cVarArr.length * 2];
                System.arraycopy(cVarArr, 0, cVarArr2, cVarArr.length, cVarArr.length);
                this.g = this.f.length - 1;
                this.f = cVarArr2;
            }
            int i4 = this.g;
            this.g = i4 - 1;
            this.f[i4] = cVar;
            this.h++;
            this.i += i;
        }

        private void b() {
            Arrays.fill(this.f, (Object) null);
            this.g = this.f.length - 1;
            this.h = 0;
            this.i = 0;
        }

        b(int i, boolean z, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar) {
            this.f1590c = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            this.f = new c[8];
            this.g = this.f.length - 1;
            this.h = 0;
            this.i = 0;
            this.e = i;
            this.f1589b = z;
            this.f1588a = cVar;
        }

        private int b(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.f.length;
                while (true) {
                    length--;
                    if (length < this.g || i <= 0) {
                        break;
                    }
                    c[] cVarArr = this.f;
                    i -= cVarArr[length].f1582c;
                    this.i -= cVarArr[length].f1582c;
                    this.h--;
                    i2++;
                }
                c[] cVarArr2 = this.f;
                int i3 = this.g + 1;
                System.arraycopy(cVarArr2, i3, cVarArr2, i3 + i2, this.h);
                c[] cVarArr3 = this.f;
                int i4 = this.g + 1;
                Arrays.fill(cVarArr3, i4, i4 + i2, (Object) null);
                this.g += i2;
            }
            return i2;
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x006c  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void a(java.util.List<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.c> r14) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 238
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.d.b.a(java.util.List):void");
        }

        void a(int i, int i2, int i3) {
            if (i < i2) {
                this.f1588a.writeByte(i | i3);
                return;
            }
            this.f1588a.writeByte(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.f1588a.writeByte(128 | (i4 & 127));
                i4 >>>= 7;
            }
            this.f1588a.writeByte(i4);
        }

        void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.f fVar) throws IOException {
            if (this.f1589b && k.b().a(fVar) < fVar.e()) {
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c();
                k.b().a(fVar, cVar);
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.f fVarM = cVar.m();
                a(fVarM.e(), 127, 128);
                this.f1588a.a(fVarM);
                return;
            }
            a(fVar.e(), 127, 0);
            this.f1588a.a(fVar);
        }

        void a(int i) {
            int iMin = Math.min(i, 16384);
            int i2 = this.e;
            if (i2 == iMin) {
                return;
            }
            if (iMin < i2) {
                this.f1590c = Math.min(this.f1590c, iMin);
            }
            this.d = true;
            this.e = iMin;
            a();
        }

        private void a() {
            int i = this.e;
            int i2 = this.i;
            if (i < i2) {
                if (i == 0) {
                    b();
                } else {
                    b(i2 - i);
                }
            }
        }
    }

    private static Map<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.f, Integer> a() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(f1583a.length);
        int i = 0;
        while (true) {
            c[] cVarArr = f1583a;
            if (i >= cVarArr.length) {
                return Collections.unmodifiableMap(linkedHashMap);
            }
            if (!linkedHashMap.containsKey(cVarArr[i].f1580a)) {
                linkedHashMap.put(f1583a[i].f1580a, Integer.valueOf(i));
            }
            i++;
        }
    }

    static bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.f a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.f fVar) throws IOException {
        int iE = fVar.e();
        for (int i = 0; i < iE; i++) {
            byte bA = fVar.a(i);
            if (bA >= 65 && bA <= 90) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + fVar.h());
            }
        }
        return fVar;
    }
}
