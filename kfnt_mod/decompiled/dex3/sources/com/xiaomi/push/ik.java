package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ik implements iu<ik, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public int f693a;

    /* renamed from: a, reason: collision with other field name */
    public long f694a;

    /* renamed from: a, reason: collision with other field name */
    public hy f695a;

    /* renamed from: a, reason: collision with other field name */
    public String f696a;

    /* renamed from: a, reason: collision with other field name */
    private BitSet f697a = new BitSet(5);

    /* renamed from: b, reason: collision with other field name */
    public int f698b;

    /* renamed from: b, reason: collision with other field name */
    public long f699b;

    /* renamed from: b, reason: collision with other field name */
    public String f700b;

    /* renamed from: c, reason: collision with other field name */
    public long f701c;

    /* renamed from: c, reason: collision with other field name */
    public String f702c;

    /* renamed from: d, reason: collision with other field name */
    public String f703d;

    /* renamed from: e, reason: collision with other field name */
    public String f704e;

    /* renamed from: f, reason: collision with other field name */
    public String f705f;

    /* renamed from: g, reason: collision with other field name */
    public String f706g;

    /* renamed from: h, reason: collision with other field name */
    public String f707h;

    /* renamed from: i, reason: collision with other field name */
    public String f708i;

    /* renamed from: j, reason: collision with other field name */
    public String f709j;

    /* renamed from: k, reason: collision with other field name */
    public String f710k;

    /* renamed from: l, reason: collision with other field name */
    public String f711l;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f692a = new jk("XmPushActionRegistrationResult");
    private static final jc a = new jc("", (byte) 11, 1);
    private static final jc b = new jc("", (byte) 12, 2);
    private static final jc c = new jc("", (byte) 11, 3);
    private static final jc d = new jc("", (byte) 11, 4);
    private static final jc e = new jc("", (byte) 10, 6);
    private static final jc f = new jc("", (byte) 11, 7);
    private static final jc g = new jc("", (byte) 11, 8);
    private static final jc h = new jc("", (byte) 11, 9);
    private static final jc i = new jc("", (byte) 11, 10);
    private static final jc j = new jc("", (byte) 10, 11);
    private static final jc k = new jc("", (byte) 11, 12);
    private static final jc l = new jc("", (byte) 11, 13);
    private static final jc m = new jc("", (byte) 10, 14);
    private static final jc n = new jc("", (byte) 11, 15);
    private static final jc o = new jc("", (byte) 8, 16);
    private static final jc p = new jc("", (byte) 11, 17);
    private static final jc q = new jc("", (byte) 8, 18);
    private static final jc r = new jc("", (byte) 11, 19);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ik ikVar) {
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
        if (!getClass().equals(ikVar.getClass())) {
            return getClass().getName().compareTo(ikVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m492a()).compareTo(Boolean.valueOf(ikVar.m492a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m492a() && (iA18 = iv.a(this.f696a, ikVar.f696a)) != 0) {
            return iA18;
        }
        int iCompareTo2 = Boolean.valueOf(m494b()).compareTo(Boolean.valueOf(ikVar.m494b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (m494b() && (iA17 = iv.a(this.f695a, ikVar.f695a)) != 0) {
            return iA17;
        }
        int iCompareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(ikVar.c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (c() && (iA16 = iv.a(this.f700b, ikVar.f700b)) != 0) {
            return iA16;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(ikVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA15 = iv.a(this.f702c, ikVar.f702c)) != 0) {
            return iA15;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(ikVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA14 = iv.a(this.f694a, ikVar.f694a)) != 0) {
            return iA14;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(ikVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA13 = iv.a(this.f703d, ikVar.f703d)) != 0) {
            return iA13;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(ikVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA12 = iv.a(this.f704e, ikVar.f704e)) != 0) {
            return iA12;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(ikVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA11 = iv.a(this.f705f, ikVar.f705f)) != 0) {
            return iA11;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(ikVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (i() && (iA10 = iv.a(this.f706g, ikVar.f706g)) != 0) {
            return iA10;
        }
        int iCompareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(ikVar.j()));
        if (iCompareTo10 != 0) {
            return iCompareTo10;
        }
        if (j() && (iA9 = iv.a(this.f699b, ikVar.f699b)) != 0) {
            return iA9;
        }
        int iCompareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(ikVar.k()));
        if (iCompareTo11 != 0) {
            return iCompareTo11;
        }
        if (k() && (iA8 = iv.a(this.f707h, ikVar.f707h)) != 0) {
            return iA8;
        }
        int iCompareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(ikVar.l()));
        if (iCompareTo12 != 0) {
            return iCompareTo12;
        }
        if (l() && (iA7 = iv.a(this.f708i, ikVar.f708i)) != 0) {
            return iA7;
        }
        int iCompareTo13 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(ikVar.m()));
        if (iCompareTo13 != 0) {
            return iCompareTo13;
        }
        if (m() && (iA6 = iv.a(this.f701c, ikVar.f701c)) != 0) {
            return iA6;
        }
        int iCompareTo14 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(ikVar.n()));
        if (iCompareTo14 != 0) {
            return iCompareTo14;
        }
        if (n() && (iA5 = iv.a(this.f709j, ikVar.f709j)) != 0) {
            return iA5;
        }
        int iCompareTo15 = Boolean.valueOf(o()).compareTo(Boolean.valueOf(ikVar.o()));
        if (iCompareTo15 != 0) {
            return iCompareTo15;
        }
        if (o() && (iA4 = iv.a(this.f693a, ikVar.f693a)) != 0) {
            return iA4;
        }
        int iCompareTo16 = Boolean.valueOf(p()).compareTo(Boolean.valueOf(ikVar.p()));
        if (iCompareTo16 != 0) {
            return iCompareTo16;
        }
        if (p() && (iA3 = iv.a(this.f710k, ikVar.f710k)) != 0) {
            return iA3;
        }
        int iCompareTo17 = Boolean.valueOf(q()).compareTo(Boolean.valueOf(ikVar.q()));
        if (iCompareTo17 != 0) {
            return iCompareTo17;
        }
        if (q() && (iA2 = iv.a(this.f698b, ikVar.f698b)) != 0) {
            return iA2;
        }
        int iCompareTo18 = Boolean.valueOf(r()).compareTo(Boolean.valueOf(ikVar.r()));
        if (iCompareTo18 != 0) {
            return iCompareTo18;
        }
        if (!r() || (iA = iv.a(this.f711l, ikVar.f711l)) == 0) {
            return 0;
        }
        return iA;
    }

    public long a() {
        return this.f694a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m490a() {
        return this.f700b;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m491a() throws jg {
        if (this.f700b == null) {
            throw new jg("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f702c != null) {
            return;
        }
        throw new jg("Required field 'appId' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003f  */
    @Override // com.xiaomi.push.iu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.xiaomi.push.jf r7) throws com.xiaomi.push.jg {
        /*
            Method dump skipped, instructions count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ik.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f697a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m492a() {
        return this.f696a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m493a(ik ikVar) {
        if (ikVar == null) {
            return false;
        }
        boolean zM492a = m492a();
        boolean zM492a2 = ikVar.m492a();
        if ((zM492a || zM492a2) && !(zM492a && zM492a2 && this.f696a.equals(ikVar.f696a))) {
            return false;
        }
        boolean zM494b = m494b();
        boolean zM494b2 = ikVar.m494b();
        if ((zM494b || zM494b2) && !(zM494b && zM494b2 && this.f695a.m430a(ikVar.f695a))) {
            return false;
        }
        boolean zC = c();
        boolean zC2 = ikVar.c();
        if ((zC || zC2) && !(zC && zC2 && this.f700b.equals(ikVar.f700b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = ikVar.d();
        if (((zD || zD2) && !(zD && zD2 && this.f702c.equals(ikVar.f702c))) || this.f694a != ikVar.f694a) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = ikVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f703d.equals(ikVar.f703d))) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = ikVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f704e.equals(ikVar.f704e))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = ikVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f705f.equals(ikVar.f705f))) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = ikVar.i();
        if ((zI || zI2) && !(zI && zI2 && this.f706g.equals(ikVar.f706g))) {
            return false;
        }
        boolean zJ = j();
        boolean zJ2 = ikVar.j();
        if ((zJ || zJ2) && !(zJ && zJ2 && this.f699b == ikVar.f699b)) {
            return false;
        }
        boolean zK = k();
        boolean zK2 = ikVar.k();
        if ((zK || zK2) && !(zK && zK2 && this.f707h.equals(ikVar.f707h))) {
            return false;
        }
        boolean zL = l();
        boolean zL2 = ikVar.l();
        if ((zL || zL2) && !(zL && zL2 && this.f708i.equals(ikVar.f708i))) {
            return false;
        }
        boolean zM = m();
        boolean zM2 = ikVar.m();
        if ((zM || zM2) && !(zM && zM2 && this.f701c == ikVar.f701c)) {
            return false;
        }
        boolean zN = n();
        boolean zN2 = ikVar.n();
        if ((zN || zN2) && !(zN && zN2 && this.f709j.equals(ikVar.f709j))) {
            return false;
        }
        boolean zO = o();
        boolean zO2 = ikVar.o();
        if ((zO || zO2) && !(zO && zO2 && this.f693a == ikVar.f693a)) {
            return false;
        }
        boolean zP = p();
        boolean zP2 = ikVar.p();
        if ((zP || zP2) && !(zP && zP2 && this.f710k.equals(ikVar.f710k))) {
            return false;
        }
        boolean zQ = q();
        boolean zQ2 = ikVar.q();
        if ((zQ || zQ2) && !(zQ && zQ2 && this.f698b == ikVar.f698b)) {
            return false;
        }
        boolean zR = r();
        boolean zR2 = ikVar.r();
        if (zR || zR2) {
            return zR && zR2 && this.f711l.equals(ikVar.f711l);
        }
        return true;
    }

    public String b() {
        return this.f706g;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m491a();
        jfVar.a(f692a);
        if (this.f696a != null && m492a()) {
            jfVar.a(a);
            jfVar.a(this.f696a);
            jfVar.b();
        }
        if (this.f695a != null && m494b()) {
            jfVar.a(b);
            this.f695a.b(jfVar);
            jfVar.b();
        }
        if (this.f700b != null) {
            jfVar.a(c);
            jfVar.a(this.f700b);
            jfVar.b();
        }
        if (this.f702c != null) {
            jfVar.a(d);
            jfVar.a(this.f702c);
            jfVar.b();
        }
        jfVar.a(e);
        jfVar.a(this.f694a);
        jfVar.b();
        if (this.f703d != null && f()) {
            jfVar.a(f);
            jfVar.a(this.f703d);
            jfVar.b();
        }
        if (this.f704e != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f704e);
            jfVar.b();
        }
        if (this.f705f != null && h()) {
            jfVar.a(h);
            jfVar.a(this.f705f);
            jfVar.b();
        }
        if (this.f706g != null && i()) {
            jfVar.a(i);
            jfVar.a(this.f706g);
            jfVar.b();
        }
        if (j()) {
            jfVar.a(j);
            jfVar.a(this.f699b);
            jfVar.b();
        }
        if (this.f707h != null && k()) {
            jfVar.a(k);
            jfVar.a(this.f707h);
            jfVar.b();
        }
        if (this.f708i != null && l()) {
            jfVar.a(l);
            jfVar.a(this.f708i);
            jfVar.b();
        }
        if (m()) {
            jfVar.a(m);
            jfVar.a(this.f701c);
            jfVar.b();
        }
        if (this.f709j != null && n()) {
            jfVar.a(n);
            jfVar.a(this.f709j);
            jfVar.b();
        }
        if (o()) {
            jfVar.a(o);
            jfVar.mo540a(this.f693a);
            jfVar.b();
        }
        if (this.f710k != null && p()) {
            jfVar.a(p);
            jfVar.a(this.f710k);
            jfVar.b();
        }
        if (q()) {
            jfVar.a(q);
            jfVar.mo540a(this.f698b);
            jfVar.b();
        }
        if (this.f711l != null && r()) {
            jfVar.a(r);
            jfVar.a(this.f711l);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public void b(boolean z) {
        this.f697a.set(1, z);
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m494b() {
        return this.f695a != null;
    }

    public void c(boolean z) {
        this.f697a.set(2, z);
    }

    public boolean c() {
        return this.f700b != null;
    }

    public void d(boolean z) {
        this.f697a.set(3, z);
    }

    public boolean d() {
        return this.f702c != null;
    }

    public void e(boolean z) {
        this.f697a.set(4, z);
    }

    public boolean e() {
        return this.f697a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ik)) {
            return m493a((ik) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f703d != null;
    }

    public boolean g() {
        return this.f704e != null;
    }

    public boolean h() {
        return this.f705f != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f706g != null;
    }

    public boolean j() {
        return this.f697a.get(1);
    }

    public boolean k() {
        return this.f707h != null;
    }

    public boolean l() {
        return this.f708i != null;
    }

    public boolean m() {
        return this.f697a.get(2);
    }

    public boolean n() {
        return this.f709j != null;
    }

    public boolean o() {
        return this.f697a.get(3);
    }

    public boolean p() {
        return this.f710k != null;
    }

    public boolean q() {
        return this.f697a.get(4);
    }

    public boolean r() {
        return this.f711l != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionRegistrationResult(");
        if (m492a()) {
            sb.append("debug:");
            sb.append(this.f696a == null ? "null" : this.f696a);
            z = false;
        } else {
            z = true;
        }
        if (m494b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f695a == null) {
                sb.append("null");
            } else {
                sb.append(this.f695a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f700b == null ? "null" : this.f700b);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f702c == null ? "null" : this.f702c);
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.f694a);
        if (f()) {
            sb.append(", ");
            sb.append("reason:");
            sb.append(this.f703d == null ? "null" : this.f703d);
        }
        if (g()) {
            sb.append(", ");
            sb.append("regId:");
            sb.append(this.f704e == null ? "null" : this.f704e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("regSecret:");
            sb.append(this.f705f == null ? "null" : this.f705f);
        }
        if (i()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f706g == null ? "null" : this.f706g);
        }
        if (j()) {
            sb.append(", ");
            sb.append("registeredAt:");
            sb.append(this.f699b);
        }
        if (k()) {
            sb.append(", ");
            sb.append("aliasName:");
            sb.append(this.f707h == null ? "null" : this.f707h);
        }
        if (l()) {
            sb.append(", ");
            sb.append("clientId:");
            sb.append(this.f708i == null ? "null" : this.f708i);
        }
        if (m()) {
            sb.append(", ");
            sb.append("costTime:");
            sb.append(this.f701c);
        }
        if (n()) {
            sb.append(", ");
            sb.append("appVersion:");
            sb.append(this.f709j == null ? "null" : this.f709j);
        }
        if (o()) {
            sb.append(", ");
            sb.append("pushSdkVersionCode:");
            sb.append(this.f693a);
        }
        if (p()) {
            sb.append(", ");
            sb.append("hybridPushEndpoint:");
            sb.append(this.f710k == null ? "null" : this.f710k);
        }
        if (q()) {
            sb.append(", ");
            sb.append("appVersionCode:");
            sb.append(this.f698b);
        }
        if (r()) {
            sb.append(", ");
            sb.append("region:");
            sb.append(this.f711l == null ? "null" : this.f711l);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
