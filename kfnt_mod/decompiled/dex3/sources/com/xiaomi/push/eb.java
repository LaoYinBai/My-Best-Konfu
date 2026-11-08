package com.xiaomi.push;

import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class eb {

    public static final class a extends com.xiaomi.push.e {

        /* renamed from: a, reason: collision with other field name */
        private boolean f258a;

        /* renamed from: b, reason: collision with other field name */
        private boolean f260b;

        /* renamed from: c, reason: collision with other field name */
        private boolean f262c;

        /* renamed from: d, reason: collision with other field name */
        private boolean f264d;

        /* renamed from: e, reason: collision with other field name */
        private boolean f266e;

        /* renamed from: f, reason: collision with other field name */
        private boolean f267f;
        private boolean g;
        private boolean h;
        private boolean i;
        private boolean j;
        private boolean k;
        private int a = 0;

        /* renamed from: a, reason: collision with other field name */
        private long f256a = 0;

        /* renamed from: a, reason: collision with other field name */
        private String f257a = "";

        /* renamed from: b, reason: collision with other field name */
        private String f259b = "";

        /* renamed from: c, reason: collision with other field name */
        private String f261c = "";

        /* renamed from: d, reason: collision with other field name */
        private String f263d = "";

        /* renamed from: e, reason: collision with other field name */
        private String f265e = "";
        private int b = 1;
        private int c = 0;
        private int d = 0;
        private String f = "";
        private int e = -1;

        @Override // com.xiaomi.push.e
        public int a() {
            if (this.e < 0) {
                b();
            }
            return this.e;
        }

        /* renamed from: a, reason: collision with other method in class */
        public long m217a() {
            return this.f256a;
        }

        /* renamed from: a, reason: collision with other method in class */
        public a m218a() {
            this.f267f = false;
            this.f263d = "";
            return this;
        }

        public a a(int i) {
            this.f258a = true;
            this.a = i;
            return this;
        }

        public a a(long j) {
            this.f260b = true;
            this.f256a = j;
            return this;
        }

        @Override // com.xiaomi.push.e
        public a a(com.xiaomi.push.b bVar) throws com.xiaomi.push.d {
            while (true) {
                int iM109a = bVar.m109a();
                switch (iM109a) {
                    case 0:
                        return this;
                    case 8:
                        a(bVar.m118b());
                        break;
                    case 16:
                        a(bVar.m119b());
                        break;
                    case 26:
                        a(bVar.m112a());
                        break;
                    case 34:
                        b(bVar.m112a());
                        break;
                    case 42:
                        c(bVar.m112a());
                        break;
                    case 50:
                        d(bVar.m112a());
                        break;
                    case 58:
                        e(bVar.m112a());
                        break;
                    case 64:
                        b(bVar.m118b());
                        break;
                    case 72:
                        c(bVar.m118b());
                        break;
                    case 80:
                        d(bVar.m118b());
                        break;
                    case 90:
                        f(bVar.m112a());
                        break;
                    default:
                        if (!a(bVar, iM109a)) {
                            return this;
                        }
                        break;
                }
            }
        }

        public a a(String str) {
            this.f262c = true;
            this.f257a = str;
            return this;
        }

        /* renamed from: a, reason: collision with other method in class */
        public String m219a() {
            return this.f257a;
        }

        @Override // com.xiaomi.push.e
        public void a(com.xiaomi.push.c cVar) throws IOException {
            if (m220a()) {
                cVar.m149a(1, c());
            }
            if (m222b()) {
                cVar.m163b(2, m217a());
            }
            if (m224c()) {
                cVar.m153a(3, m219a());
            }
            if (m226d()) {
                cVar.m153a(4, m221b());
            }
            if (m228e()) {
                cVar.m153a(5, m223c());
            }
            if (m230f()) {
                cVar.m153a(6, m225d());
            }
            if (g()) {
                cVar.m153a(7, m227e());
            }
            if (h()) {
                cVar.m149a(8, d());
            }
            if (i()) {
                cVar.m149a(9, e());
            }
            if (j()) {
                cVar.m149a(10, f());
            }
            if (k()) {
                cVar.m153a(11, m229f());
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m220a() {
            return this.f258a;
        }

        @Override // com.xiaomi.push.e
        public int b() {
            int iA = m220a() ? 0 + com.xiaomi.push.c.a(1, c()) : 0;
            if (m222b()) {
                iA += com.xiaomi.push.c.b(2, m217a());
            }
            if (m224c()) {
                iA += com.xiaomi.push.c.a(3, m219a());
            }
            if (m226d()) {
                iA += com.xiaomi.push.c.a(4, m221b());
            }
            if (m228e()) {
                iA += com.xiaomi.push.c.a(5, m223c());
            }
            if (m230f()) {
                iA += com.xiaomi.push.c.a(6, m225d());
            }
            if (g()) {
                iA += com.xiaomi.push.c.a(7, m227e());
            }
            if (h()) {
                iA += com.xiaomi.push.c.a(8, d());
            }
            if (i()) {
                iA += com.xiaomi.push.c.a(9, e());
            }
            if (j()) {
                iA += com.xiaomi.push.c.a(10, f());
            }
            if (k()) {
                iA += com.xiaomi.push.c.a(11, m229f());
            }
            this.e = iA;
            return iA;
        }

        public a b(int i) {
            this.h = true;
            this.b = i;
            return this;
        }

        public a b(String str) {
            this.f264d = true;
            this.f259b = str;
            return this;
        }

        /* renamed from: b, reason: collision with other method in class */
        public String m221b() {
            return this.f259b;
        }

        /* renamed from: b, reason: collision with other method in class */
        public boolean m222b() {
            return this.f260b;
        }

        public int c() {
            return this.a;
        }

        public a c(int i) {
            this.i = true;
            this.c = i;
            return this;
        }

        public a c(String str) {
            this.f266e = true;
            this.f261c = str;
            return this;
        }

        /* renamed from: c, reason: collision with other method in class */
        public String m223c() {
            return this.f261c;
        }

        /* renamed from: c, reason: collision with other method in class */
        public boolean m224c() {
            return this.f262c;
        }

        public int d() {
            return this.b;
        }

        public a d(int i) {
            this.j = true;
            this.d = i;
            return this;
        }

        public a d(String str) {
            this.f267f = true;
            this.f263d = str;
            return this;
        }

        /* renamed from: d, reason: collision with other method in class */
        public String m225d() {
            return this.f263d;
        }

        /* renamed from: d, reason: collision with other method in class */
        public boolean m226d() {
            return this.f264d;
        }

        public int e() {
            return this.c;
        }

        public a e(String str) {
            this.g = true;
            this.f265e = str;
            return this;
        }

        /* renamed from: e, reason: collision with other method in class */
        public String m227e() {
            return this.f265e;
        }

        /* renamed from: e, reason: collision with other method in class */
        public boolean m228e() {
            return this.f266e;
        }

        public int f() {
            return this.d;
        }

        public a f(String str) {
            this.k = true;
            this.f = str;
            return this;
        }

        /* renamed from: f, reason: collision with other method in class */
        public String m229f() {
            return this.f;
        }

        /* renamed from: f, reason: collision with other method in class */
        public boolean m230f() {
            return this.f267f;
        }

        public boolean g() {
            return this.g;
        }

        public boolean h() {
            return this.h;
        }

        public boolean i() {
            return this.i;
        }

        public boolean j() {
            return this.j;
        }

        public boolean k() {
            return this.k;
        }
    }

    public static final class b extends com.xiaomi.push.e {

        /* renamed from: a, reason: collision with other field name */
        private boolean f268a;

        /* renamed from: c, reason: collision with other field name */
        private boolean f270c;

        /* renamed from: d, reason: collision with other field name */
        private boolean f271d;
        private boolean e;

        /* renamed from: b, reason: collision with other field name */
        private boolean f269b = false;
        private int a = 0;
        private int b = 0;
        private int c = 0;
        private int d = -1;

        public static b a(byte[] bArr) {
            return (b) new b().a(bArr);
        }

        @Override // com.xiaomi.push.e
        public int a() {
            if (this.d < 0) {
                b();
            }
            return this.d;
        }

        public b a(int i) {
            this.f270c = true;
            this.a = i;
            return this;
        }

        @Override // com.xiaomi.push.e
        public b a(com.xiaomi.push.b bVar) throws com.xiaomi.push.d {
            while (true) {
                int iM109a = bVar.m109a();
                if (iM109a == 0) {
                    return this;
                }
                if (iM109a == 8) {
                    a(bVar.m115a());
                } else if (iM109a == 24) {
                    a(bVar.m118b());
                } else if (iM109a == 32) {
                    b(bVar.m118b());
                } else if (iM109a == 40) {
                    c(bVar.m118b());
                } else if (!a(bVar, iM109a)) {
                    return this;
                }
            }
        }

        public b a(boolean z) {
            this.f268a = true;
            this.f269b = z;
            return this;
        }

        @Override // com.xiaomi.push.e
        public void a(com.xiaomi.push.c cVar) throws IOException {
            if (m232b()) {
                cVar.m154a(1, m231a());
            }
            if (m233c()) {
                cVar.m149a(3, c());
            }
            if (m234d()) {
                cVar.m149a(4, d());
            }
            if (m235e()) {
                cVar.m149a(5, e());
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m231a() {
            return this.f269b;
        }

        @Override // com.xiaomi.push.e
        public int b() {
            int iA = m232b() ? 0 + com.xiaomi.push.c.a(1, m231a()) : 0;
            if (m233c()) {
                iA += com.xiaomi.push.c.a(3, c());
            }
            if (m234d()) {
                iA += com.xiaomi.push.c.a(4, d());
            }
            if (m235e()) {
                iA += com.xiaomi.push.c.a(5, e());
            }
            this.d = iA;
            return iA;
        }

        public b b(int i) {
            this.f271d = true;
            this.b = i;
            return this;
        }

        /* renamed from: b, reason: collision with other method in class */
        public boolean m232b() {
            return this.f268a;
        }

        public int c() {
            return this.a;
        }

        public b c(int i) {
            this.e = true;
            this.c = i;
            return this;
        }

        /* renamed from: c, reason: collision with other method in class */
        public boolean m233c() {
            return this.f270c;
        }

        public int d() {
            return this.b;
        }

        /* renamed from: d, reason: collision with other method in class */
        public boolean m234d() {
            return this.f271d;
        }

        public int e() {
            return this.c;
        }

        /* renamed from: e, reason: collision with other method in class */
        public boolean m235e() {
            return this.e;
        }
    }

    public static final class c extends com.xiaomi.push.e {

        /* renamed from: a, reason: collision with other field name */
        private boolean f273a;

        /* renamed from: b, reason: collision with other field name */
        private boolean f274b;

        /* renamed from: c, reason: collision with other field name */
        private boolean f275c;

        /* renamed from: d, reason: collision with other field name */
        private boolean f276d;

        /* renamed from: e, reason: collision with other field name */
        private boolean f277e;

        /* renamed from: f, reason: collision with other field name */
        private boolean f278f;

        /* renamed from: a, reason: collision with other field name */
        private String f272a = "";
        private String b = "";
        private String c = "";
        private String d = "";
        private String e = "";
        private String f = "";
        private int a = -1;

        @Override // com.xiaomi.push.e
        public int a() {
            if (this.a < 0) {
                b();
            }
            return this.a;
        }

        @Override // com.xiaomi.push.e
        public c a(com.xiaomi.push.b bVar) throws com.xiaomi.push.d {
            while (true) {
                int iM109a = bVar.m109a();
                if (iM109a == 0) {
                    return this;
                }
                if (iM109a == 10) {
                    a(bVar.m112a());
                } else if (iM109a == 18) {
                    b(bVar.m112a());
                } else if (iM109a == 26) {
                    c(bVar.m112a());
                } else if (iM109a == 34) {
                    d(bVar.m112a());
                } else if (iM109a == 42) {
                    e(bVar.m112a());
                } else if (iM109a == 50) {
                    f(bVar.m112a());
                } else if (!a(bVar, iM109a)) {
                    return this;
                }
            }
        }

        public c a(String str) {
            this.f273a = true;
            this.f272a = str;
            return this;
        }

        /* renamed from: a, reason: collision with other method in class */
        public String m236a() {
            return this.f272a;
        }

        @Override // com.xiaomi.push.e
        public void a(com.xiaomi.push.c cVar) throws IOException {
            if (m237a()) {
                cVar.m153a(1, m236a());
            }
            if (m239b()) {
                cVar.m153a(2, m238b());
            }
            if (m240c()) {
                cVar.m153a(3, c());
            }
            if (m241d()) {
                cVar.m153a(4, d());
            }
            if (m242e()) {
                cVar.m153a(5, e());
            }
            if (m243f()) {
                cVar.m153a(6, f());
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m237a() {
            return this.f273a;
        }

        @Override // com.xiaomi.push.e
        public int b() {
            int iA = m237a() ? 0 + com.xiaomi.push.c.a(1, m236a()) : 0;
            if (m239b()) {
                iA += com.xiaomi.push.c.a(2, m238b());
            }
            if (m240c()) {
                iA += com.xiaomi.push.c.a(3, c());
            }
            if (m241d()) {
                iA += com.xiaomi.push.c.a(4, d());
            }
            if (m242e()) {
                iA += com.xiaomi.push.c.a(5, e());
            }
            if (m243f()) {
                iA += com.xiaomi.push.c.a(6, f());
            }
            this.a = iA;
            return iA;
        }

        public c b(String str) {
            this.f274b = true;
            this.b = str;
            return this;
        }

        /* renamed from: b, reason: collision with other method in class */
        public String m238b() {
            return this.b;
        }

        /* renamed from: b, reason: collision with other method in class */
        public boolean m239b() {
            return this.f274b;
        }

        public c c(String str) {
            this.f275c = true;
            this.c = str;
            return this;
        }

        public String c() {
            return this.c;
        }

        /* renamed from: c, reason: collision with other method in class */
        public boolean m240c() {
            return this.f275c;
        }

        public c d(String str) {
            this.f276d = true;
            this.d = str;
            return this;
        }

        public String d() {
            return this.d;
        }

        /* renamed from: d, reason: collision with other method in class */
        public boolean m241d() {
            return this.f276d;
        }

        public c e(String str) {
            this.f277e = true;
            this.e = str;
            return this;
        }

        public String e() {
            return this.e;
        }

        /* renamed from: e, reason: collision with other method in class */
        public boolean m242e() {
            return this.f277e;
        }

        public c f(String str) {
            this.f278f = true;
            this.f = str;
            return this;
        }

        public String f() {
            return this.f;
        }

        /* renamed from: f, reason: collision with other method in class */
        public boolean m243f() {
            return this.f278f;
        }
    }

    public static final class d extends com.xiaomi.push.e {

        /* renamed from: a, reason: collision with other field name */
        private boolean f280a;

        /* renamed from: c, reason: collision with other field name */
        private boolean f282c;
        private boolean d;
        private boolean e;

        /* renamed from: b, reason: collision with other field name */
        private boolean f281b = false;

        /* renamed from: a, reason: collision with other field name */
        private String f279a = "";
        private String b = "";
        private String c = "";
        private int a = -1;

        public static d a(byte[] bArr) {
            return (d) new d().a(bArr);
        }

        @Override // com.xiaomi.push.e
        public int a() {
            if (this.a < 0) {
                b();
            }
            return this.a;
        }

        @Override // com.xiaomi.push.e
        public d a(com.xiaomi.push.b bVar) throws com.xiaomi.push.d {
            while (true) {
                int iM109a = bVar.m109a();
                if (iM109a == 0) {
                    return this;
                }
                if (iM109a == 8) {
                    a(bVar.m115a());
                } else if (iM109a == 18) {
                    a(bVar.m112a());
                } else if (iM109a == 26) {
                    b(bVar.m112a());
                } else if (iM109a == 34) {
                    c(bVar.m112a());
                } else if (!a(bVar, iM109a)) {
                    return this;
                }
            }
        }

        public d a(String str) {
            this.f282c = true;
            this.f279a = str;
            return this;
        }

        public d a(boolean z) {
            this.f280a = true;
            this.f281b = z;
            return this;
        }

        /* renamed from: a, reason: collision with other method in class */
        public String m244a() {
            return this.f279a;
        }

        @Override // com.xiaomi.push.e
        public void a(com.xiaomi.push.c cVar) throws IOException {
            if (m247b()) {
                cVar.m154a(1, m245a());
            }
            if (m248c()) {
                cVar.m153a(2, m244a());
            }
            if (d()) {
                cVar.m153a(3, m246b());
            }
            if (e()) {
                cVar.m153a(4, c());
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m245a() {
            return this.f281b;
        }

        @Override // com.xiaomi.push.e
        public int b() {
            int iA = m247b() ? 0 + com.xiaomi.push.c.a(1, m245a()) : 0;
            if (m248c()) {
                iA += com.xiaomi.push.c.a(2, m244a());
            }
            if (d()) {
                iA += com.xiaomi.push.c.a(3, m246b());
            }
            if (e()) {
                iA += com.xiaomi.push.c.a(4, c());
            }
            this.a = iA;
            return iA;
        }

        public d b(String str) {
            this.d = true;
            this.b = str;
            return this;
        }

        /* renamed from: b, reason: collision with other method in class */
        public String m246b() {
            return this.b;
        }

        /* renamed from: b, reason: collision with other method in class */
        public boolean m247b() {
            return this.f280a;
        }

        public d c(String str) {
            this.e = true;
            this.c = str;
            return this;
        }

        public String c() {
            return this.c;
        }

        /* renamed from: c, reason: collision with other method in class */
        public boolean m248c() {
            return this.f282c;
        }

        public boolean d() {
            return this.d;
        }

        public boolean e() {
            return this.e;
        }
    }

    public static final class e extends com.xiaomi.push.e {

        /* renamed from: a, reason: collision with other field name */
        private boolean f285a;

        /* renamed from: b, reason: collision with other field name */
        private boolean f287b;

        /* renamed from: c, reason: collision with other field name */
        private boolean f289c;

        /* renamed from: d, reason: collision with other field name */
        private boolean f291d;

        /* renamed from: e, reason: collision with other field name */
        private boolean f292e;

        /* renamed from: f, reason: collision with other field name */
        private boolean f293f;
        private boolean g;
        private boolean h;
        private boolean i;
        private boolean j;
        private int a = 0;

        /* renamed from: a, reason: collision with other field name */
        private String f284a = "";

        /* renamed from: b, reason: collision with other field name */
        private String f286b = "";

        /* renamed from: c, reason: collision with other field name */
        private String f288c = "";
        private int b = 0;

        /* renamed from: d, reason: collision with other field name */
        private String f290d = "";
        private String e = "";
        private String f = "";

        /* renamed from: a, reason: collision with other field name */
        private b f283a = null;
        private int c = 0;
        private int d = -1;

        @Override // com.xiaomi.push.e
        public int a() {
            if (this.d < 0) {
                b();
            }
            return this.d;
        }

        /* renamed from: a, reason: collision with other method in class */
        public b m249a() {
            return this.f283a;
        }

        public e a(int i) {
            this.f285a = true;
            this.a = i;
            return this;
        }

        @Override // com.xiaomi.push.e
        public e a(com.xiaomi.push.b bVar) throws com.xiaomi.push.d {
            while (true) {
                int iM109a = bVar.m109a();
                switch (iM109a) {
                    case 0:
                        return this;
                    case 8:
                        a(bVar.c());
                        break;
                    case 18:
                        a(bVar.m112a());
                        break;
                    case 26:
                        b(bVar.m112a());
                        break;
                    case 34:
                        c(bVar.m112a());
                        break;
                    case 40:
                        b(bVar.m118b());
                        break;
                    case 50:
                        d(bVar.m112a());
                        break;
                    case 58:
                        e(bVar.m112a());
                        break;
                    case 66:
                        f(bVar.m112a());
                        break;
                    case 74:
                        b bVar2 = new b();
                        bVar.a(bVar2);
                        a(bVar2);
                        break;
                    case 80:
                        c(bVar.m118b());
                        break;
                    default:
                        if (!a(bVar, iM109a)) {
                            return this;
                        }
                        break;
                }
            }
        }

        public e a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException();
            }
            this.i = true;
            this.f283a = bVar;
            return this;
        }

        public e a(String str) {
            this.f287b = true;
            this.f284a = str;
            return this;
        }

        /* renamed from: a, reason: collision with other method in class */
        public String m250a() {
            return this.f284a;
        }

        @Override // com.xiaomi.push.e
        public void a(com.xiaomi.push.c cVar) throws IOException {
            if (m251a()) {
                cVar.m162b(1, c());
            }
            if (m253b()) {
                cVar.m153a(2, m250a());
            }
            if (m255c()) {
                cVar.m153a(3, m252b());
            }
            if (m257d()) {
                cVar.m153a(4, m254c());
            }
            if (m259e()) {
                cVar.m149a(5, d());
            }
            if (m260f()) {
                cVar.m153a(6, m256d());
            }
            if (g()) {
                cVar.m153a(7, m258e());
            }
            if (h()) {
                cVar.m153a(8, f());
            }
            if (i()) {
                cVar.m152a(9, (com.xiaomi.push.e) m249a());
            }
            if (j()) {
                cVar.m149a(10, e());
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m251a() {
            return this.f285a;
        }

        @Override // com.xiaomi.push.e
        public int b() {
            int iB = m251a() ? 0 + com.xiaomi.push.c.b(1, c()) : 0;
            if (m253b()) {
                iB += com.xiaomi.push.c.a(2, m250a());
            }
            if (m255c()) {
                iB += com.xiaomi.push.c.a(3, m252b());
            }
            if (m257d()) {
                iB += com.xiaomi.push.c.a(4, m254c());
            }
            if (m259e()) {
                iB += com.xiaomi.push.c.a(5, d());
            }
            if (m260f()) {
                iB += com.xiaomi.push.c.a(6, m256d());
            }
            if (g()) {
                iB += com.xiaomi.push.c.a(7, m258e());
            }
            if (h()) {
                iB += com.xiaomi.push.c.a(8, f());
            }
            if (i()) {
                iB += com.xiaomi.push.c.a(9, (com.xiaomi.push.e) m249a());
            }
            if (j()) {
                iB += com.xiaomi.push.c.a(10, e());
            }
            this.d = iB;
            return iB;
        }

        public e b(int i) {
            this.f292e = true;
            this.b = i;
            return this;
        }

        public e b(String str) {
            this.f289c = true;
            this.f286b = str;
            return this;
        }

        /* renamed from: b, reason: collision with other method in class */
        public String m252b() {
            return this.f286b;
        }

        /* renamed from: b, reason: collision with other method in class */
        public boolean m253b() {
            return this.f287b;
        }

        public int c() {
            return this.a;
        }

        public e c(int i) {
            this.j = true;
            this.c = i;
            return this;
        }

        public e c(String str) {
            this.f291d = true;
            this.f288c = str;
            return this;
        }

        /* renamed from: c, reason: collision with other method in class */
        public String m254c() {
            return this.f288c;
        }

        /* renamed from: c, reason: collision with other method in class */
        public boolean m255c() {
            return this.f289c;
        }

        public int d() {
            return this.b;
        }

        public e d(String str) {
            this.f293f = true;
            this.f290d = str;
            return this;
        }

        /* renamed from: d, reason: collision with other method in class */
        public String m256d() {
            return this.f290d;
        }

        /* renamed from: d, reason: collision with other method in class */
        public boolean m257d() {
            return this.f291d;
        }

        public int e() {
            return this.c;
        }

        public e e(String str) {
            this.g = true;
            this.e = str;
            return this;
        }

        /* renamed from: e, reason: collision with other method in class */
        public String m258e() {
            return this.e;
        }

        /* renamed from: e, reason: collision with other method in class */
        public boolean m259e() {
            return this.f292e;
        }

        public e f(String str) {
            this.h = true;
            this.f = str;
            return this;
        }

        public String f() {
            return this.f;
        }

        /* renamed from: f, reason: collision with other method in class */
        public boolean m260f() {
            return this.f293f;
        }

        public boolean g() {
            return this.g;
        }

        public boolean h() {
            return this.h;
        }

        public boolean i() {
            return this.i;
        }

        public boolean j() {
            return this.j;
        }
    }

    public static final class f extends com.xiaomi.push.e {

        /* renamed from: a, reason: collision with other field name */
        private boolean f296a;

        /* renamed from: b, reason: collision with other field name */
        private boolean f297b;
        private boolean c;

        /* renamed from: a, reason: collision with other field name */
        private String f295a = "";
        private String b = "";

        /* renamed from: a, reason: collision with other field name */
        private b f294a = null;
        private int a = -1;

        public static f a(byte[] bArr) {
            return (f) new f().a(bArr);
        }

        @Override // com.xiaomi.push.e
        public int a() {
            if (this.a < 0) {
                b();
            }
            return this.a;
        }

        /* renamed from: a, reason: collision with other method in class */
        public b m261a() {
            return this.f294a;
        }

        @Override // com.xiaomi.push.e
        public f a(com.xiaomi.push.b bVar) throws com.xiaomi.push.d {
            while (true) {
                int iM109a = bVar.m109a();
                if (iM109a == 0) {
                    return this;
                }
                if (iM109a == 10) {
                    a(bVar.m112a());
                } else if (iM109a == 18) {
                    b(bVar.m112a());
                } else if (iM109a == 26) {
                    b bVar2 = new b();
                    bVar.a(bVar2);
                    a(bVar2);
                } else if (!a(bVar, iM109a)) {
                    return this;
                }
            }
        }

        public f a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException();
            }
            this.c = true;
            this.f294a = bVar;
            return this;
        }

        public f a(String str) {
            this.f296a = true;
            this.f295a = str;
            return this;
        }

        /* renamed from: a, reason: collision with other method in class */
        public String m262a() {
            return this.f295a;
        }

        @Override // com.xiaomi.push.e
        public void a(com.xiaomi.push.c cVar) throws IOException {
            if (m263a()) {
                cVar.m153a(1, m262a());
            }
            if (m265b()) {
                cVar.m153a(2, m264b());
            }
            if (c()) {
                cVar.m152a(3, (com.xiaomi.push.e) m261a());
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m263a() {
            return this.f296a;
        }

        @Override // com.xiaomi.push.e
        public int b() {
            int iA = m263a() ? 0 + com.xiaomi.push.c.a(1, m262a()) : 0;
            if (m265b()) {
                iA += com.xiaomi.push.c.a(2, m264b());
            }
            if (c()) {
                iA += com.xiaomi.push.c.a(3, (com.xiaomi.push.e) m261a());
            }
            this.a = iA;
            return iA;
        }

        public f b(String str) {
            this.f297b = true;
            this.b = str;
            return this;
        }

        /* renamed from: b, reason: collision with other method in class */
        public String m264b() {
            return this.b;
        }

        /* renamed from: b, reason: collision with other method in class */
        public boolean m265b() {
            return this.f297b;
        }

        public boolean c() {
            return this.c;
        }
    }

    public static final class g extends com.xiaomi.push.e {

        /* renamed from: a, reason: collision with other field name */
        private boolean f299a;

        /* renamed from: b, reason: collision with other field name */
        private boolean f300b;

        /* renamed from: c, reason: collision with other field name */
        private boolean f301c;

        /* renamed from: a, reason: collision with other field name */
        private String f298a = "";
        private String b = "";
        private String c = "";
        private int a = -1;

        public static g a(byte[] bArr) {
            return (g) new g().a(bArr);
        }

        @Override // com.xiaomi.push.e
        public int a() {
            if (this.a < 0) {
                b();
            }
            return this.a;
        }

        @Override // com.xiaomi.push.e
        public g a(com.xiaomi.push.b bVar) throws com.xiaomi.push.d {
            while (true) {
                int iM109a = bVar.m109a();
                if (iM109a == 0) {
                    return this;
                }
                if (iM109a == 10) {
                    a(bVar.m112a());
                } else if (iM109a == 18) {
                    b(bVar.m112a());
                } else if (iM109a == 26) {
                    c(bVar.m112a());
                } else if (!a(bVar, iM109a)) {
                    return this;
                }
            }
        }

        public g a(String str) {
            this.f299a = true;
            this.f298a = str;
            return this;
        }

        /* renamed from: a, reason: collision with other method in class */
        public String m266a() {
            return this.f298a;
        }

        @Override // com.xiaomi.push.e
        public void a(com.xiaomi.push.c cVar) throws IOException {
            if (m267a()) {
                cVar.m153a(1, m266a());
            }
            if (m269b()) {
                cVar.m153a(2, m268b());
            }
            if (m270c()) {
                cVar.m153a(3, c());
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m267a() {
            return this.f299a;
        }

        @Override // com.xiaomi.push.e
        public int b() {
            int iA = m267a() ? 0 + com.xiaomi.push.c.a(1, m266a()) : 0;
            if (m269b()) {
                iA += com.xiaomi.push.c.a(2, m268b());
            }
            if (m270c()) {
                iA += com.xiaomi.push.c.a(3, c());
            }
            this.a = iA;
            return iA;
        }

        public g b(String str) {
            this.f300b = true;
            this.b = str;
            return this;
        }

        /* renamed from: b, reason: collision with other method in class */
        public String m268b() {
            return this.b;
        }

        /* renamed from: b, reason: collision with other method in class */
        public boolean m269b() {
            return this.f300b;
        }

        public g c(String str) {
            this.f301c = true;
            this.c = str;
            return this;
        }

        public String c() {
            return this.c;
        }

        /* renamed from: c, reason: collision with other method in class */
        public boolean m270c() {
            return this.f301c;
        }
    }

    public static final class h extends com.xiaomi.push.e {

        /* renamed from: a, reason: collision with other field name */
        private boolean f303a;

        /* renamed from: b, reason: collision with other field name */
        private boolean f304b;
        private int a = 0;

        /* renamed from: a, reason: collision with other field name */
        private String f302a = "";
        private int b = -1;

        public static h a(byte[] bArr) {
            return (h) new h().a(bArr);
        }

        @Override // com.xiaomi.push.e
        public int a() {
            if (this.b < 0) {
                b();
            }
            return this.b;
        }

        public h a(int i) {
            this.f303a = true;
            this.a = i;
            return this;
        }

        @Override // com.xiaomi.push.e
        public h a(com.xiaomi.push.b bVar) throws com.xiaomi.push.d {
            while (true) {
                int iM109a = bVar.m109a();
                if (iM109a == 0) {
                    return this;
                }
                if (iM109a == 8) {
                    a(bVar.m118b());
                } else if (iM109a == 18) {
                    a(bVar.m112a());
                } else if (!a(bVar, iM109a)) {
                    return this;
                }
            }
        }

        public h a(String str) {
            this.f304b = true;
            this.f302a = str;
            return this;
        }

        /* renamed from: a, reason: collision with other method in class */
        public String m271a() {
            return this.f302a;
        }

        @Override // com.xiaomi.push.e
        public void a(com.xiaomi.push.c cVar) throws IOException {
            if (m272a()) {
                cVar.m149a(1, c());
            }
            if (m273b()) {
                cVar.m153a(2, m271a());
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m272a() {
            return this.f303a;
        }

        @Override // com.xiaomi.push.e
        public int b() {
            int iA = m272a() ? 0 + com.xiaomi.push.c.a(1, c()) : 0;
            if (m273b()) {
                iA += com.xiaomi.push.c.a(2, m271a());
            }
            this.b = iA;
            return iA;
        }

        /* renamed from: b, reason: collision with other method in class */
        public boolean m273b() {
            return this.f304b;
        }

        public int c() {
            return this.a;
        }
    }

    public static final class i extends com.xiaomi.push.e {

        /* renamed from: a, reason: collision with other field name */
        private boolean f306a;

        /* renamed from: a, reason: collision with other field name */
        private com.xiaomi.push.a f305a = com.xiaomi.push.a.a;
        private int a = -1;

        public static i a(byte[] bArr) {
            return (i) new i().a(bArr);
        }

        @Override // com.xiaomi.push.e
        public int a() {
            if (this.a < 0) {
                b();
            }
            return this.a;
        }

        /* renamed from: a, reason: collision with other method in class */
        public com.xiaomi.push.a m274a() {
            return this.f305a;
        }

        public i a(com.xiaomi.push.a aVar) {
            this.f306a = true;
            this.f305a = aVar;
            return this;
        }

        @Override // com.xiaomi.push.e
        public i a(com.xiaomi.push.b bVar) throws com.xiaomi.push.d {
            while (true) {
                int iM109a = bVar.m109a();
                if (iM109a == 0) {
                    return this;
                }
                if (iM109a == 10) {
                    a(bVar.m111a());
                } else if (!a(bVar, iM109a)) {
                    return this;
                }
            }
        }

        @Override // com.xiaomi.push.e
        public void a(com.xiaomi.push.c cVar) throws IOException {
            if (m275a()) {
                cVar.m151a(1, m274a());
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m275a() {
            return this.f306a;
        }

        @Override // com.xiaomi.push.e
        public int b() {
            int iA = m275a() ? 0 + com.xiaomi.push.c.a(1, m274a()) : 0;
            this.a = iA;
            return iA;
        }
    }

    public static final class j extends com.xiaomi.push.e {

        /* renamed from: a, reason: collision with other field name */
        private boolean f309a;
        private boolean b;

        /* renamed from: a, reason: collision with other field name */
        private com.xiaomi.push.a f307a = com.xiaomi.push.a.a;

        /* renamed from: a, reason: collision with other field name */
        private b f308a = null;
        private int a = -1;

        public static j a(byte[] bArr) {
            return (j) new j().a(bArr);
        }

        @Override // com.xiaomi.push.e
        public int a() {
            if (this.a < 0) {
                b();
            }
            return this.a;
        }

        /* renamed from: a, reason: collision with other method in class */
        public com.xiaomi.push.a m276a() {
            return this.f307a;
        }

        /* renamed from: a, reason: collision with other method in class */
        public b m277a() {
            return this.f308a;
        }

        public j a(com.xiaomi.push.a aVar) {
            this.f309a = true;
            this.f307a = aVar;
            return this;
        }

        @Override // com.xiaomi.push.e
        public j a(com.xiaomi.push.b bVar) throws com.xiaomi.push.d {
            while (true) {
                int iM109a = bVar.m109a();
                if (iM109a == 0) {
                    return this;
                }
                if (iM109a == 10) {
                    a(bVar.m111a());
                } else if (iM109a == 18) {
                    b bVar2 = new b();
                    bVar.a(bVar2);
                    a(bVar2);
                } else if (!a(bVar, iM109a)) {
                    return this;
                }
            }
        }

        public j a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException();
            }
            this.b = true;
            this.f308a = bVar;
            return this;
        }

        @Override // com.xiaomi.push.e
        public void a(com.xiaomi.push.c cVar) throws IOException {
            if (m278a()) {
                cVar.m151a(1, m276a());
            }
            if (m279b()) {
                cVar.m152a(2, (com.xiaomi.push.e) m277a());
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m278a() {
            return this.f309a;
        }

        @Override // com.xiaomi.push.e
        public int b() {
            int iA = m278a() ? 0 + com.xiaomi.push.c.a(1, m276a()) : 0;
            if (m279b()) {
                iA += com.xiaomi.push.c.a(2, (com.xiaomi.push.e) m277a());
            }
            this.a = iA;
            return iA;
        }

        /* renamed from: b, reason: collision with other method in class */
        public boolean m279b() {
            return this.b;
        }
    }

    public static final class k extends com.xiaomi.push.e {

        /* renamed from: a, reason: collision with other field name */
        private boolean f312a;

        /* renamed from: b, reason: collision with other field name */
        private boolean f315b;
        private boolean c;
        private boolean d;
        private boolean e;
        private boolean g;

        /* renamed from: a, reason: collision with other field name */
        private String f311a = "";

        /* renamed from: b, reason: collision with other field name */
        private String f314b = "";

        /* renamed from: a, reason: collision with other field name */
        private long f310a = 0;

        /* renamed from: b, reason: collision with other field name */
        private long f313b = 0;
        private boolean f = false;
        private int a = 0;
        private int b = -1;

        public static k a(byte[] bArr) {
            return (k) new k().a(bArr);
        }

        @Override // com.xiaomi.push.e
        public int a() {
            if (this.b < 0) {
                b();
            }
            return this.b;
        }

        /* renamed from: a, reason: collision with other method in class */
        public long m280a() {
            return this.f310a;
        }

        public k a(int i) {
            this.g = true;
            this.a = i;
            return this;
        }

        public k a(long j) {
            this.c = true;
            this.f310a = j;
            return this;
        }

        @Override // com.xiaomi.push.e
        public k a(com.xiaomi.push.b bVar) throws com.xiaomi.push.d {
            while (true) {
                int iM109a = bVar.m109a();
                if (iM109a == 0) {
                    return this;
                }
                if (iM109a == 10) {
                    a(bVar.m112a());
                } else if (iM109a == 18) {
                    b(bVar.m112a());
                } else if (iM109a == 24) {
                    a(bVar.m110a());
                } else if (iM109a == 32) {
                    b(bVar.m110a());
                } else if (iM109a == 40) {
                    a(bVar.m115a());
                } else if (iM109a == 48) {
                    a(bVar.m118b());
                } else if (!a(bVar, iM109a)) {
                    return this;
                }
            }
        }

        public k a(String str) {
            this.f312a = true;
            this.f311a = str;
            return this;
        }

        public k a(boolean z) {
            this.e = true;
            this.f = z;
            return this;
        }

        /* renamed from: a, reason: collision with other method in class */
        public String m281a() {
            return this.f311a;
        }

        @Override // com.xiaomi.push.e
        public void a(com.xiaomi.push.c cVar) throws IOException {
            if (m282a()) {
                cVar.m153a(1, m281a());
            }
            if (m285b()) {
                cVar.m153a(2, m284b());
            }
            if (m286c()) {
                cVar.m150a(3, m280a());
            }
            if (d()) {
                cVar.m150a(4, m283b());
            }
            if (f()) {
                cVar.m154a(5, e());
            }
            if (g()) {
                cVar.m149a(6, c());
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m282a() {
            return this.f312a;
        }

        @Override // com.xiaomi.push.e
        public int b() {
            int iA = m282a() ? 0 + com.xiaomi.push.c.a(1, m281a()) : 0;
            if (m285b()) {
                iA += com.xiaomi.push.c.a(2, m284b());
            }
            if (m286c()) {
                iA += com.xiaomi.push.c.a(3, m280a());
            }
            if (d()) {
                iA += com.xiaomi.push.c.a(4, m283b());
            }
            if (f()) {
                iA += com.xiaomi.push.c.a(5, e());
            }
            if (g()) {
                iA += com.xiaomi.push.c.a(6, c());
            }
            this.b = iA;
            return iA;
        }

        /* renamed from: b, reason: collision with other method in class */
        public long m283b() {
            return this.f313b;
        }

        public k b(long j) {
            this.d = true;
            this.f313b = j;
            return this;
        }

        public k b(String str) {
            this.f315b = true;
            this.f314b = str;
            return this;
        }

        /* renamed from: b, reason: collision with other method in class */
        public String m284b() {
            return this.f314b;
        }

        /* renamed from: b, reason: collision with other method in class */
        public boolean m285b() {
            return this.f315b;
        }

        public int c() {
            return this.a;
        }

        /* renamed from: c, reason: collision with other method in class */
        public boolean m286c() {
            return this.c;
        }

        public boolean d() {
            return this.d;
        }

        public boolean e() {
            return this.f;
        }

        public boolean f() {
            return this.e;
        }

        public boolean g() {
            return this.g;
        }
    }
}
