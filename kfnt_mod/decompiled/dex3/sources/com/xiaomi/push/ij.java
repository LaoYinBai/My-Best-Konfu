package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ij implements iu<ij, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public int f662a;

    /* renamed from: a, reason: collision with other field name */
    public long f663a;

    /* renamed from: a, reason: collision with other field name */
    public hx f664a;

    /* renamed from: a, reason: collision with other field name */
    public hy f665a;

    /* renamed from: a, reason: collision with other field name */
    public String f666a;

    /* renamed from: a, reason: collision with other field name */
    public Map<String, String> f668a;

    /* renamed from: b, reason: collision with other field name */
    public int f670b;

    /* renamed from: b, reason: collision with other field name */
    public long f671b;

    /* renamed from: b, reason: collision with other field name */
    public String f672b;

    /* renamed from: c, reason: collision with other field name */
    public int f674c;

    /* renamed from: c, reason: collision with other field name */
    public String f675c;

    /* renamed from: d, reason: collision with other field name */
    public String f677d;

    /* renamed from: e, reason: collision with other field name */
    public String f678e;

    /* renamed from: f, reason: collision with other field name */
    public String f679f;

    /* renamed from: g, reason: collision with other field name */
    public String f680g;

    /* renamed from: h, reason: collision with other field name */
    public String f681h;

    /* renamed from: i, reason: collision with other field name */
    public String f682i;

    /* renamed from: j, reason: collision with other field name */
    public String f683j;

    /* renamed from: k, reason: collision with other field name */
    public String f684k;

    /* renamed from: l, reason: collision with other field name */
    public String f685l;

    /* renamed from: m, reason: collision with other field name */
    public String f686m;

    /* renamed from: n, reason: collision with other field name */
    public String f687n;

    /* renamed from: o, reason: collision with other field name */
    public String f688o;

    /* renamed from: p, reason: collision with other field name */
    public String f689p;

    /* renamed from: q, reason: collision with other field name */
    public String f690q;

    /* renamed from: r, reason: collision with other field name */
    public String f691r;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f661a = new jk("XmPushActionRegistration");
    private static final jc a = new jc("", (byte) 11, 1);
    private static final jc b = new jc("", (byte) 12, 2);
    private static final jc c = new jc("", (byte) 11, 3);
    private static final jc d = new jc("", (byte) 11, 4);
    private static final jc e = new jc("", (byte) 11, 5);
    private static final jc f = new jc("", (byte) 11, 6);
    private static final jc g = new jc("", (byte) 11, 7);
    private static final jc h = new jc("", (byte) 11, 8);
    private static final jc i = new jc("", (byte) 11, 9);
    private static final jc j = new jc("", (byte) 11, 10);
    private static final jc k = new jc("", (byte) 11, 11);
    private static final jc l = new jc("", (byte) 11, 12);
    private static final jc m = new jc("", (byte) 8, 13);
    private static final jc n = new jc("", (byte) 8, 14);
    private static final jc o = new jc("", (byte) 11, 15);
    private static final jc p = new jc("", (byte) 11, 16);
    private static final jc q = new jc("", (byte) 11, 17);
    private static final jc r = new jc("", (byte) 11, 18);
    private static final jc s = new jc("", (byte) 8, 19);
    private static final jc t = new jc("", (byte) 8, 20);
    private static final jc u = new jc("", (byte) 2, 21);
    private static final jc v = new jc("", (byte) 10, 22);
    private static final jc w = new jc("", (byte) 10, 23);
    private static final jc x = new jc("", (byte) 11, 24);
    private static final jc y = new jc("", (byte) 11, 25);
    private static final jc z = new jc("", (byte) 2, 26);
    private static final jc A = new jc("", com.umeng.commonsdk.proguard.az.k, 100);
    private static final jc B = new jc("", (byte) 2, 101);
    private static final jc C = new jc("", (byte) 11, 102);

    /* renamed from: a, reason: collision with other field name */
    private BitSet f667a = new BitSet(8);

    /* renamed from: a, reason: collision with other field name */
    public boolean f669a = true;

    /* renamed from: c, reason: collision with other field name */
    public boolean f676c = false;

    /* renamed from: b, reason: collision with other field name */
    public boolean f673b = false;

    public boolean A() {
        return this.f668a != null;
    }

    public boolean B() {
        return this.f667a.get(7);
    }

    public boolean C() {
        return this.f691r != null;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ij ijVar) {
        int iA;
        int iA2;
        int iA3;
        int iA4;
        int iA5;
        int iA6;
        int iA7;
        int iA8;
        int iA9;
        int iA10;
        int iA11;
        int iA12;
        int iA13;
        int iA14;
        int iA15;
        int iA16;
        int iA17;
        int iA18;
        int iA19;
        int iA20;
        int iA21;
        int iA22;
        int iA23;
        int iA24;
        int iA25;
        int iA26;
        int iA27;
        int iA28;
        int iA29;
        if (!getClass().equals(ijVar.getClass())) {
            return getClass().getName().compareTo(ijVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m486a()).compareTo(Boolean.valueOf(ijVar.m486a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m486a() && (iA29 = iv.a(this.f666a, ijVar.f666a)) != 0) {
            return iA29;
        }
        int iCompareTo2 = Boolean.valueOf(m488b()).compareTo(Boolean.valueOf(ijVar.m488b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (m488b() && (iA28 = iv.a(this.f665a, ijVar.f665a)) != 0) {
            return iA28;
        }
        int iCompareTo3 = Boolean.valueOf(m489c()).compareTo(Boolean.valueOf(ijVar.m489c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (m489c() && (iA27 = iv.a(this.f672b, ijVar.f672b)) != 0) {
            return iA27;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(ijVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA26 = iv.a(this.f675c, ijVar.f675c)) != 0) {
            return iA26;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(ijVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA25 = iv.a(this.f677d, ijVar.f677d)) != 0) {
            return iA25;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(ijVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA24 = iv.a(this.f678e, ijVar.f678e)) != 0) {
            return iA24;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(ijVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA23 = iv.a(this.f679f, ijVar.f679f)) != 0) {
            return iA23;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(ijVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA22 = iv.a(this.f680g, ijVar.f680g)) != 0) {
            return iA22;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(ijVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (i() && (iA21 = iv.a(this.f681h, ijVar.f681h)) != 0) {
            return iA21;
        }
        int iCompareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(ijVar.j()));
        if (iCompareTo10 != 0) {
            return iCompareTo10;
        }
        if (j() && (iA20 = iv.a(this.f682i, ijVar.f682i)) != 0) {
            return iA20;
        }
        int iCompareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(ijVar.k()));
        if (iCompareTo11 != 0) {
            return iCompareTo11;
        }
        if (k() && (iA19 = iv.a(this.f683j, ijVar.f683j)) != 0) {
            return iA19;
        }
        int iCompareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(ijVar.l()));
        if (iCompareTo12 != 0) {
            return iCompareTo12;
        }
        if (l() && (iA18 = iv.a(this.f684k, ijVar.f684k)) != 0) {
            return iA18;
        }
        int iCompareTo13 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(ijVar.m()));
        if (iCompareTo13 != 0) {
            return iCompareTo13;
        }
        if (m() && (iA17 = iv.a(this.f662a, ijVar.f662a)) != 0) {
            return iA17;
        }
        int iCompareTo14 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(ijVar.n()));
        if (iCompareTo14 != 0) {
            return iCompareTo14;
        }
        if (n() && (iA16 = iv.a(this.f670b, ijVar.f670b)) != 0) {
            return iA16;
        }
        int iCompareTo15 = Boolean.valueOf(o()).compareTo(Boolean.valueOf(ijVar.o()));
        if (iCompareTo15 != 0) {
            return iCompareTo15;
        }
        if (o() && (iA15 = iv.a(this.f685l, ijVar.f685l)) != 0) {
            return iA15;
        }
        int iCompareTo16 = Boolean.valueOf(p()).compareTo(Boolean.valueOf(ijVar.p()));
        if (iCompareTo16 != 0) {
            return iCompareTo16;
        }
        if (p() && (iA14 = iv.a(this.f686m, ijVar.f686m)) != 0) {
            return iA14;
        }
        int iCompareTo17 = Boolean.valueOf(q()).compareTo(Boolean.valueOf(ijVar.q()));
        if (iCompareTo17 != 0) {
            return iCompareTo17;
        }
        if (q() && (iA13 = iv.a(this.f687n, ijVar.f687n)) != 0) {
            return iA13;
        }
        int iCompareTo18 = Boolean.valueOf(r()).compareTo(Boolean.valueOf(ijVar.r()));
        if (iCompareTo18 != 0) {
            return iCompareTo18;
        }
        if (r() && (iA12 = iv.a(this.f688o, ijVar.f688o)) != 0) {
            return iA12;
        }
        int iCompareTo19 = Boolean.valueOf(s()).compareTo(Boolean.valueOf(ijVar.s()));
        if (iCompareTo19 != 0) {
            return iCompareTo19;
        }
        if (s() && (iA11 = iv.a(this.f674c, ijVar.f674c)) != 0) {
            return iA11;
        }
        int iCompareTo20 = Boolean.valueOf(t()).compareTo(Boolean.valueOf(ijVar.t()));
        if (iCompareTo20 != 0) {
            return iCompareTo20;
        }
        if (t() && (iA10 = iv.a(this.f664a, ijVar.f664a)) != 0) {
            return iA10;
        }
        int iCompareTo21 = Boolean.valueOf(u()).compareTo(Boolean.valueOf(ijVar.u()));
        if (iCompareTo21 != 0) {
            return iCompareTo21;
        }
        if (u() && (iA9 = iv.a(this.f669a, ijVar.f669a)) != 0) {
            return iA9;
        }
        int iCompareTo22 = Boolean.valueOf(v()).compareTo(Boolean.valueOf(ijVar.v()));
        if (iCompareTo22 != 0) {
            return iCompareTo22;
        }
        if (v() && (iA8 = iv.a(this.f663a, ijVar.f663a)) != 0) {
            return iA8;
        }
        int iCompareTo23 = Boolean.valueOf(w()).compareTo(Boolean.valueOf(ijVar.w()));
        if (iCompareTo23 != 0) {
            return iCompareTo23;
        }
        if (w() && (iA7 = iv.a(this.f671b, ijVar.f671b)) != 0) {
            return iA7;
        }
        int iCompareTo24 = Boolean.valueOf(x()).compareTo(Boolean.valueOf(ijVar.x()));
        if (iCompareTo24 != 0) {
            return iCompareTo24;
        }
        if (x() && (iA6 = iv.a(this.f689p, ijVar.f689p)) != 0) {
            return iA6;
        }
        int iCompareTo25 = Boolean.valueOf(y()).compareTo(Boolean.valueOf(ijVar.y()));
        if (iCompareTo25 != 0) {
            return iCompareTo25;
        }
        if (y() && (iA5 = iv.a(this.f690q, ijVar.f690q)) != 0) {
            return iA5;
        }
        int iCompareTo26 = Boolean.valueOf(z()).compareTo(Boolean.valueOf(ijVar.z()));
        if (iCompareTo26 != 0) {
            return iCompareTo26;
        }
        if (z() && (iA4 = iv.a(this.f673b, ijVar.f673b)) != 0) {
            return iA4;
        }
        int iCompareTo27 = Boolean.valueOf(A()).compareTo(Boolean.valueOf(ijVar.A()));
        if (iCompareTo27 != 0) {
            return iCompareTo27;
        }
        if (A() && (iA3 = iv.a(this.f668a, ijVar.f668a)) != 0) {
            return iA3;
        }
        int iCompareTo28 = Boolean.valueOf(B()).compareTo(Boolean.valueOf(ijVar.B()));
        if (iCompareTo28 != 0) {
            return iCompareTo28;
        }
        if (B() && (iA2 = iv.a(this.f676c, ijVar.f676c)) != 0) {
            return iA2;
        }
        int iCompareTo29 = Boolean.valueOf(C()).compareTo(Boolean.valueOf(ijVar.C()));
        if (iCompareTo29 != 0) {
            return iCompareTo29;
        }
        if (!C() || (iA = iv.a(this.f691r, ijVar.f691r)) == 0) {
            return 0;
        }
        return iA;
    }

    public ij a(int i2) {
        this.f662a = i2;
        a(true);
        return this;
    }

    public ij a(hx hxVar) {
        this.f664a = hxVar;
        return this;
    }

    public ij a(String str) {
        this.f672b = str;
        return this;
    }

    public String a() {
        return this.f672b;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m485a() throws jg {
        if (this.f672b == null) {
            throw new jg("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f675c == null) {
            throw new jg("Required field 'appId' was not present! Struct: " + toString());
        }
        if (this.f679f != null) {
            return;
        }
        throw new jg("Required field 'token' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    @Override // com.xiaomi.push.iu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.xiaomi.push.jf r8) throws com.xiaomi.push.jg {
        /*
            Method dump skipped, instructions count: 522
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ij.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z2) {
        this.f667a.set(0, z2);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m486a() {
        return this.f666a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m487a(ij ijVar) {
        if (ijVar == null) {
            return false;
        }
        boolean zM486a = m486a();
        boolean zM486a2 = ijVar.m486a();
        if ((zM486a || zM486a2) && !(zM486a && zM486a2 && this.f666a.equals(ijVar.f666a))) {
            return false;
        }
        boolean zM488b = m488b();
        boolean zM488b2 = ijVar.m488b();
        if ((zM488b || zM488b2) && !(zM488b && zM488b2 && this.f665a.m430a(ijVar.f665a))) {
            return false;
        }
        boolean zM489c = m489c();
        boolean zM489c2 = ijVar.m489c();
        if ((zM489c || zM489c2) && !(zM489c && zM489c2 && this.f672b.equals(ijVar.f672b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = ijVar.d();
        if ((zD || zD2) && !(zD && zD2 && this.f675c.equals(ijVar.f675c))) {
            return false;
        }
        boolean zE = e();
        boolean zE2 = ijVar.e();
        if ((zE || zE2) && !(zE && zE2 && this.f677d.equals(ijVar.f677d))) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = ijVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f678e.equals(ijVar.f678e))) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = ijVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f679f.equals(ijVar.f679f))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = ijVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f680g.equals(ijVar.f680g))) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = ijVar.i();
        if ((zI || zI2) && !(zI && zI2 && this.f681h.equals(ijVar.f681h))) {
            return false;
        }
        boolean zJ = j();
        boolean zJ2 = ijVar.j();
        if ((zJ || zJ2) && !(zJ && zJ2 && this.f682i.equals(ijVar.f682i))) {
            return false;
        }
        boolean zK = k();
        boolean zK2 = ijVar.k();
        if ((zK || zK2) && !(zK && zK2 && this.f683j.equals(ijVar.f683j))) {
            return false;
        }
        boolean zL = l();
        boolean zL2 = ijVar.l();
        if ((zL || zL2) && !(zL && zL2 && this.f684k.equals(ijVar.f684k))) {
            return false;
        }
        boolean zM = m();
        boolean zM2 = ijVar.m();
        if ((zM || zM2) && !(zM && zM2 && this.f662a == ijVar.f662a)) {
            return false;
        }
        boolean zN = n();
        boolean zN2 = ijVar.n();
        if ((zN || zN2) && !(zN && zN2 && this.f670b == ijVar.f670b)) {
            return false;
        }
        boolean zO = o();
        boolean zO2 = ijVar.o();
        if ((zO || zO2) && !(zO && zO2 && this.f685l.equals(ijVar.f685l))) {
            return false;
        }
        boolean zP = p();
        boolean zP2 = ijVar.p();
        if ((zP || zP2) && !(zP && zP2 && this.f686m.equals(ijVar.f686m))) {
            return false;
        }
        boolean zQ = q();
        boolean zQ2 = ijVar.q();
        if ((zQ || zQ2) && !(zQ && zQ2 && this.f687n.equals(ijVar.f687n))) {
            return false;
        }
        boolean zR = r();
        boolean zR2 = ijVar.r();
        if ((zR || zR2) && !(zR && zR2 && this.f688o.equals(ijVar.f688o))) {
            return false;
        }
        boolean zS = s();
        boolean zS2 = ijVar.s();
        if ((zS || zS2) && !(zS && zS2 && this.f674c == ijVar.f674c)) {
            return false;
        }
        boolean zT = t();
        boolean zT2 = ijVar.t();
        if ((zT || zT2) && !(zT && zT2 && this.f664a.equals(ijVar.f664a))) {
            return false;
        }
        boolean zU = u();
        boolean zU2 = ijVar.u();
        if ((zU || zU2) && !(zU && zU2 && this.f669a == ijVar.f669a)) {
            return false;
        }
        boolean zV = v();
        boolean zV2 = ijVar.v();
        if ((zV || zV2) && !(zV && zV2 && this.f663a == ijVar.f663a)) {
            return false;
        }
        boolean zW = w();
        boolean zW2 = ijVar.w();
        if ((zW || zW2) && !(zW && zW2 && this.f671b == ijVar.f671b)) {
            return false;
        }
        boolean zX = x();
        boolean zX2 = ijVar.x();
        if ((zX || zX2) && !(zX && zX2 && this.f689p.equals(ijVar.f689p))) {
            return false;
        }
        boolean zY = y();
        boolean zY2 = ijVar.y();
        if ((zY || zY2) && !(zY && zY2 && this.f690q.equals(ijVar.f690q))) {
            return false;
        }
        boolean z2 = z();
        boolean z3 = ijVar.z();
        if ((z2 || z3) && !(z2 && z3 && this.f673b == ijVar.f673b)) {
            return false;
        }
        boolean zA = A();
        boolean zA2 = ijVar.A();
        if ((zA || zA2) && !(zA && zA2 && this.f668a.equals(ijVar.f668a))) {
            return false;
        }
        boolean zB = B();
        boolean zB2 = ijVar.B();
        if ((zB || zB2) && !(zB && zB2 && this.f676c == ijVar.f676c)) {
            return false;
        }
        boolean zC = C();
        boolean zC2 = ijVar.C();
        if (zC || zC2) {
            return zC && zC2 && this.f691r.equals(ijVar.f691r);
        }
        return true;
    }

    public ij b(int i2) {
        this.f670b = i2;
        b(true);
        return this;
    }

    public ij b(String str) {
        this.f675c = str;
        return this;
    }

    public String b() {
        return this.f675c;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m485a();
        jfVar.a(f661a);
        if (this.f666a != null && m486a()) {
            jfVar.a(a);
            jfVar.a(this.f666a);
            jfVar.b();
        }
        if (this.f665a != null && m488b()) {
            jfVar.a(b);
            this.f665a.b(jfVar);
            jfVar.b();
        }
        if (this.f672b != null) {
            jfVar.a(c);
            jfVar.a(this.f672b);
            jfVar.b();
        }
        if (this.f675c != null) {
            jfVar.a(d);
            jfVar.a(this.f675c);
            jfVar.b();
        }
        if (this.f677d != null && e()) {
            jfVar.a(e);
            jfVar.a(this.f677d);
            jfVar.b();
        }
        if (this.f678e != null && f()) {
            jfVar.a(f);
            jfVar.a(this.f678e);
            jfVar.b();
        }
        if (this.f679f != null) {
            jfVar.a(g);
            jfVar.a(this.f679f);
            jfVar.b();
        }
        if (this.f680g != null && h()) {
            jfVar.a(h);
            jfVar.a(this.f680g);
            jfVar.b();
        }
        if (this.f681h != null && i()) {
            jfVar.a(i);
            jfVar.a(this.f681h);
            jfVar.b();
        }
        if (this.f682i != null && j()) {
            jfVar.a(j);
            jfVar.a(this.f682i);
            jfVar.b();
        }
        if (this.f683j != null && k()) {
            jfVar.a(k);
            jfVar.a(this.f683j);
            jfVar.b();
        }
        if (this.f684k != null && l()) {
            jfVar.a(l);
            jfVar.a(this.f684k);
            jfVar.b();
        }
        if (m()) {
            jfVar.a(m);
            jfVar.mo540a(this.f662a);
            jfVar.b();
        }
        if (n()) {
            jfVar.a(n);
            jfVar.mo540a(this.f670b);
            jfVar.b();
        }
        if (this.f685l != null && o()) {
            jfVar.a(o);
            jfVar.a(this.f685l);
            jfVar.b();
        }
        if (this.f686m != null && p()) {
            jfVar.a(p);
            jfVar.a(this.f686m);
            jfVar.b();
        }
        if (this.f687n != null && q()) {
            jfVar.a(q);
            jfVar.a(this.f687n);
            jfVar.b();
        }
        if (this.f688o != null && r()) {
            jfVar.a(r);
            jfVar.a(this.f688o);
            jfVar.b();
        }
        if (s()) {
            jfVar.a(s);
            jfVar.mo540a(this.f674c);
            jfVar.b();
        }
        if (this.f664a != null && t()) {
            jfVar.a(t);
            jfVar.mo540a(this.f664a.a());
            jfVar.b();
        }
        if (u()) {
            jfVar.a(u);
            jfVar.a(this.f669a);
            jfVar.b();
        }
        if (v()) {
            jfVar.a(v);
            jfVar.a(this.f663a);
            jfVar.b();
        }
        if (w()) {
            jfVar.a(w);
            jfVar.a(this.f671b);
            jfVar.b();
        }
        if (this.f689p != null && x()) {
            jfVar.a(x);
            jfVar.a(this.f689p);
            jfVar.b();
        }
        if (this.f690q != null && y()) {
            jfVar.a(y);
            jfVar.a(this.f690q);
            jfVar.b();
        }
        if (z()) {
            jfVar.a(z);
            jfVar.a(this.f673b);
            jfVar.b();
        }
        if (this.f668a != null && A()) {
            jfVar.a(A);
            jfVar.a(new je((byte) 11, (byte) 11, this.f668a.size()));
            for (Map.Entry<String, String> entry : this.f668a.entrySet()) {
                jfVar.a(entry.getKey());
                jfVar.a(entry.getValue());
            }
            jfVar.d();
            jfVar.b();
        }
        if (B()) {
            jfVar.a(B);
            jfVar.a(this.f676c);
            jfVar.b();
        }
        if (this.f691r != null && C()) {
            jfVar.a(C);
            jfVar.a(this.f691r);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public void b(boolean z2) {
        this.f667a.set(1, z2);
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m488b() {
        return this.f665a != null;
    }

    public ij c(int i2) {
        this.f674c = i2;
        c(true);
        return this;
    }

    public ij c(String str) {
        this.f677d = str;
        return this;
    }

    public String c() {
        return this.f679f;
    }

    public void c(boolean z2) {
        this.f667a.set(2, z2);
    }

    /* renamed from: c, reason: collision with other method in class */
    public boolean m489c() {
        return this.f672b != null;
    }

    public ij d(String str) {
        this.f678e = str;
        return this;
    }

    public void d(boolean z2) {
        this.f667a.set(3, z2);
    }

    public boolean d() {
        return this.f675c != null;
    }

    public ij e(String str) {
        this.f679f = str;
        return this;
    }

    public void e(boolean z2) {
        this.f667a.set(4, z2);
    }

    public boolean e() {
        return this.f677d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ij)) {
            return m487a((ij) obj);
        }
        return false;
    }

    public ij f(String str) {
        this.f680g = str;
        return this;
    }

    public void f(boolean z2) {
        this.f667a.set(5, z2);
    }

    public boolean f() {
        return this.f678e != null;
    }

    public ij g(String str) {
        this.f681h = str;
        return this;
    }

    public void g(boolean z2) {
        this.f667a.set(6, z2);
    }

    public boolean g() {
        return this.f679f != null;
    }

    public ij h(String str) {
        this.f684k = str;
        return this;
    }

    public void h(boolean z2) {
        this.f667a.set(7, z2);
    }

    public boolean h() {
        return this.f680g != null;
    }

    public int hashCode() {
        return 0;
    }

    public ij i(String str) {
        this.f688o = str;
        return this;
    }

    public boolean i() {
        return this.f681h != null;
    }

    public boolean j() {
        return this.f682i != null;
    }

    public boolean k() {
        return this.f683j != null;
    }

    public boolean l() {
        return this.f684k != null;
    }

    public boolean m() {
        return this.f667a.get(0);
    }

    public boolean n() {
        return this.f667a.get(1);
    }

    public boolean o() {
        return this.f685l != null;
    }

    public boolean p() {
        return this.f686m != null;
    }

    public boolean q() {
        return this.f687n != null;
    }

    public boolean r() {
        return this.f688o != null;
    }

    public boolean s() {
        return this.f667a.get(2);
    }

    public boolean t() {
        return this.f664a != null;
    }

    public String toString() {
        boolean z2;
        StringBuilder sb = new StringBuilder("XmPushActionRegistration(");
        if (m486a()) {
            sb.append("debug:");
            sb.append(this.f666a == null ? "null" : this.f666a);
            z2 = false;
        } else {
            z2 = true;
        }
        if (m488b()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f665a == null) {
                sb.append("null");
            } else {
                sb.append(this.f665a);
            }
            z2 = false;
        }
        if (!z2) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f672b == null ? "null" : this.f672b);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f675c == null ? "null" : this.f675c);
        if (e()) {
            sb.append(", ");
            sb.append("appVersion:");
            sb.append(this.f677d == null ? "null" : this.f677d);
        }
        if (f()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f678e == null ? "null" : this.f678e);
        }
        sb.append(", ");
        sb.append("token:");
        sb.append(this.f679f == null ? "null" : this.f679f);
        if (h()) {
            sb.append(", ");
            sb.append("deviceId:");
            sb.append(this.f680g == null ? "null" : this.f680g);
        }
        if (i()) {
            sb.append(", ");
            sb.append("aliasName:");
            sb.append(this.f681h == null ? "null" : this.f681h);
        }
        if (j()) {
            sb.append(", ");
            sb.append("sdkVersion:");
            sb.append(this.f682i == null ? "null" : this.f682i);
        }
        if (k()) {
            sb.append(", ");
            sb.append("regId:");
            sb.append(this.f683j == null ? "null" : this.f683j);
        }
        if (l()) {
            sb.append(", ");
            sb.append("pushSdkVersionName:");
            sb.append(this.f684k == null ? "null" : this.f684k);
        }
        if (m()) {
            sb.append(", ");
            sb.append("pushSdkVersionCode:");
            sb.append(this.f662a);
        }
        if (n()) {
            sb.append(", ");
            sb.append("appVersionCode:");
            sb.append(this.f670b);
        }
        if (o()) {
            sb.append(", ");
            sb.append("androidId:");
            sb.append(this.f685l == null ? "null" : this.f685l);
        }
        if (p()) {
            sb.append(", ");
            sb.append("imei:");
            sb.append(this.f686m == null ? "null" : this.f686m);
        }
        if (q()) {
            sb.append(", ");
            sb.append("serial:");
            sb.append(this.f687n == null ? "null" : this.f687n);
        }
        if (r()) {
            sb.append(", ");
            sb.append("imeiMd5:");
            sb.append(this.f688o == null ? "null" : this.f688o);
        }
        if (s()) {
            sb.append(", ");
            sb.append("spaceId:");
            sb.append(this.f674c);
        }
        if (t()) {
            sb.append(", ");
            sb.append("reason:");
            if (this.f664a == null) {
                sb.append("null");
            } else {
                sb.append(this.f664a);
            }
        }
        if (u()) {
            sb.append(", ");
            sb.append("validateToken:");
            sb.append(this.f669a);
        }
        if (v()) {
            sb.append(", ");
            sb.append("miid:");
            sb.append(this.f663a);
        }
        if (w()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f671b);
        }
        if (x()) {
            sb.append(", ");
            sb.append("subImei:");
            sb.append(this.f689p == null ? "null" : this.f689p);
        }
        if (y()) {
            sb.append(", ");
            sb.append("subImeiMd5:");
            sb.append(this.f690q == null ? "null" : this.f690q);
        }
        if (z()) {
            sb.append(", ");
            sb.append("isHybridFrame:");
            sb.append(this.f673b);
        }
        if (A()) {
            sb.append(", ");
            sb.append("connectionAttrs:");
            if (this.f668a == null) {
                sb.append("null");
            } else {
                sb.append(this.f668a);
            }
        }
        if (B()) {
            sb.append(", ");
            sb.append("cleanOldRegInfo:");
            sb.append(this.f676c);
        }
        if (C()) {
            sb.append(", ");
            sb.append("oldRegId:");
            sb.append(this.f691r == null ? "null" : this.f691r);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }

    public boolean u() {
        return this.f667a.get(3);
    }

    public boolean v() {
        return this.f667a.get(4);
    }

    public boolean w() {
        return this.f667a.get(5);
    }

    public boolean x() {
        return this.f689p != null;
    }

    public boolean y() {
        return this.f690q != null;
    }

    public boolean z() {
        return this.f667a.get(6);
    }
}
