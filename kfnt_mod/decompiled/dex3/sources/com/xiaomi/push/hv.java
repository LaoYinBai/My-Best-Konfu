package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class hv implements iu<hv, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public long f520a;

    /* renamed from: a, reason: collision with other field name */
    public hw f521a;

    /* renamed from: a, reason: collision with other field name */
    public hy f522a;

    /* renamed from: a, reason: collision with other field name */
    public String f523a;

    /* renamed from: a, reason: collision with other field name */
    private BitSet f524a = new BitSet(4);

    /* renamed from: a, reason: collision with other field name */
    public boolean f525a = false;

    /* renamed from: b, reason: collision with other field name */
    public long f526b;

    /* renamed from: b, reason: collision with other field name */
    public String f527b;

    /* renamed from: c, reason: collision with other field name */
    public long f528c;

    /* renamed from: c, reason: collision with other field name */
    public String f529c;

    /* renamed from: d, reason: collision with other field name */
    public String f530d;

    /* renamed from: e, reason: collision with other field name */
    public String f531e;

    /* renamed from: f, reason: collision with other field name */
    public String f532f;

    /* renamed from: g, reason: collision with other field name */
    public String f533g;

    /* renamed from: h, reason: collision with other field name */
    public String f534h;

    /* renamed from: i, reason: collision with other field name */
    public String f535i;

    /* renamed from: j, reason: collision with other field name */
    public String f536j;

    /* renamed from: k, reason: collision with other field name */
    public String f537k;

    /* renamed from: l, reason: collision with other field name */
    public String f538l;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f519a = new jk("PushMessage");
    private static final jc a = new jc("", (byte) 12, 1);
    private static final jc b = new jc("", (byte) 11, 2);
    private static final jc c = new jc("", (byte) 11, 3);
    private static final jc d = new jc("", (byte) 11, 4);
    private static final jc e = new jc("", (byte) 10, 5);
    private static final jc f = new jc("", (byte) 10, 6);
    private static final jc g = new jc("", (byte) 11, 7);
    private static final jc h = new jc("", (byte) 11, 8);
    private static final jc i = new jc("", (byte) 11, 9);
    private static final jc j = new jc("", (byte) 11, 10);
    private static final jc k = new jc("", (byte) 11, 11);
    private static final jc l = new jc("", (byte) 12, 12);
    private static final jc m = new jc("", (byte) 11, 13);
    private static final jc n = new jc("", (byte) 2, 14);
    private static final jc o = new jc("", (byte) 11, 15);
    private static final jc p = new jc("", (byte) 10, 16);
    private static final jc q = new jc("", (byte) 11, 20);
    private static final jc r = new jc("", (byte) 11, 21);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(hv hvVar) {
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
        if (!getClass().equals(hvVar.getClass())) {
            return getClass().getName().compareTo(hvVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m412a()).compareTo(Boolean.valueOf(hvVar.m412a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m412a() && (iA18 = iv.a(this.f522a, hvVar.f522a)) != 0) {
            return iA18;
        }
        int iCompareTo2 = Boolean.valueOf(m414b()).compareTo(Boolean.valueOf(hvVar.m414b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (m414b() && (iA17 = iv.a(this.f523a, hvVar.f523a)) != 0) {
            return iA17;
        }
        int iCompareTo3 = Boolean.valueOf(m415c()).compareTo(Boolean.valueOf(hvVar.m415c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (m415c() && (iA16 = iv.a(this.f527b, hvVar.f527b)) != 0) {
            return iA16;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(hvVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA15 = iv.a(this.f529c, hvVar.f529c)) != 0) {
            return iA15;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(hvVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA14 = iv.a(this.f520a, hvVar.f520a)) != 0) {
            return iA14;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(hvVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA13 = iv.a(this.f526b, hvVar.f526b)) != 0) {
            return iA13;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(hvVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA12 = iv.a(this.f530d, hvVar.f530d)) != 0) {
            return iA12;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(hvVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA11 = iv.a(this.f531e, hvVar.f531e)) != 0) {
            return iA11;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(hvVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (i() && (iA10 = iv.a(this.f532f, hvVar.f532f)) != 0) {
            return iA10;
        }
        int iCompareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(hvVar.j()));
        if (iCompareTo10 != 0) {
            return iCompareTo10;
        }
        if (j() && (iA9 = iv.a(this.f533g, hvVar.f533g)) != 0) {
            return iA9;
        }
        int iCompareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(hvVar.k()));
        if (iCompareTo11 != 0) {
            return iCompareTo11;
        }
        if (k() && (iA8 = iv.a(this.f534h, hvVar.f534h)) != 0) {
            return iA8;
        }
        int iCompareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(hvVar.l()));
        if (iCompareTo12 != 0) {
            return iCompareTo12;
        }
        if (l() && (iA7 = iv.a(this.f521a, hvVar.f521a)) != 0) {
            return iA7;
        }
        int iCompareTo13 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(hvVar.m()));
        if (iCompareTo13 != 0) {
            return iCompareTo13;
        }
        if (m() && (iA6 = iv.a(this.f535i, hvVar.f535i)) != 0) {
            return iA6;
        }
        int iCompareTo14 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(hvVar.n()));
        if (iCompareTo14 != 0) {
            return iCompareTo14;
        }
        if (n() && (iA5 = iv.a(this.f525a, hvVar.f525a)) != 0) {
            return iA5;
        }
        int iCompareTo15 = Boolean.valueOf(o()).compareTo(Boolean.valueOf(hvVar.o()));
        if (iCompareTo15 != 0) {
            return iCompareTo15;
        }
        if (o() && (iA4 = iv.a(this.f536j, hvVar.f536j)) != 0) {
            return iA4;
        }
        int iCompareTo16 = Boolean.valueOf(p()).compareTo(Boolean.valueOf(hvVar.p()));
        if (iCompareTo16 != 0) {
            return iCompareTo16;
        }
        if (p() && (iA3 = iv.a(this.f528c, hvVar.f528c)) != 0) {
            return iA3;
        }
        int iCompareTo17 = Boolean.valueOf(q()).compareTo(Boolean.valueOf(hvVar.q()));
        if (iCompareTo17 != 0) {
            return iCompareTo17;
        }
        if (q() && (iA2 = iv.a(this.f537k, hvVar.f537k)) != 0) {
            return iA2;
        }
        int iCompareTo18 = Boolean.valueOf(r()).compareTo(Boolean.valueOf(hvVar.r()));
        if (iCompareTo18 != 0) {
            return iCompareTo18;
        }
        if (!r() || (iA = iv.a(this.f538l, hvVar.f538l)) == 0) {
            return 0;
        }
        return iA;
    }

    public long a() {
        return this.f520a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m410a() {
        return this.f523a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m411a() throws jg {
        if (this.f523a == null) {
            throw new jg("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f527b == null) {
            throw new jg("Required field 'appId' was not present! Struct: " + toString());
        }
        if (this.f529c != null) {
            return;
        }
        throw new jg("Required field 'payload' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001e  */
    @Override // com.xiaomi.push.iu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.xiaomi.push.jf r7) throws com.xiaomi.push.jg {
        /*
            Method dump skipped, instructions count: 320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hv.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f524a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m412a() {
        return this.f522a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m413a(hv hvVar) {
        if (hvVar == null) {
            return false;
        }
        boolean zM412a = m412a();
        boolean zM412a2 = hvVar.m412a();
        if ((zM412a || zM412a2) && !(zM412a && zM412a2 && this.f522a.m430a(hvVar.f522a))) {
            return false;
        }
        boolean zM414b = m414b();
        boolean zM414b2 = hvVar.m414b();
        if ((zM414b || zM414b2) && !(zM414b && zM414b2 && this.f523a.equals(hvVar.f523a))) {
            return false;
        }
        boolean zM415c = m415c();
        boolean zM415c2 = hvVar.m415c();
        if ((zM415c || zM415c2) && !(zM415c && zM415c2 && this.f527b.equals(hvVar.f527b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = hvVar.d();
        if ((zD || zD2) && !(zD && zD2 && this.f529c.equals(hvVar.f529c))) {
            return false;
        }
        boolean zE = e();
        boolean zE2 = hvVar.e();
        if ((zE || zE2) && !(zE && zE2 && this.f520a == hvVar.f520a)) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = hvVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f526b == hvVar.f526b)) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = hvVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f530d.equals(hvVar.f530d))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = hvVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f531e.equals(hvVar.f531e))) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = hvVar.i();
        if ((zI || zI2) && !(zI && zI2 && this.f532f.equals(hvVar.f532f))) {
            return false;
        }
        boolean zJ = j();
        boolean zJ2 = hvVar.j();
        if ((zJ || zJ2) && !(zJ && zJ2 && this.f533g.equals(hvVar.f533g))) {
            return false;
        }
        boolean zK = k();
        boolean zK2 = hvVar.k();
        if ((zK || zK2) && !(zK && zK2 && this.f534h.equals(hvVar.f534h))) {
            return false;
        }
        boolean zL = l();
        boolean zL2 = hvVar.l();
        if ((zL || zL2) && !(zL && zL2 && this.f521a.m422a(hvVar.f521a))) {
            return false;
        }
        boolean zM = m();
        boolean zM2 = hvVar.m();
        if ((zM || zM2) && !(zM && zM2 && this.f535i.equals(hvVar.f535i))) {
            return false;
        }
        boolean zN = n();
        boolean zN2 = hvVar.n();
        if ((zN || zN2) && !(zN && zN2 && this.f525a == hvVar.f525a)) {
            return false;
        }
        boolean zO = o();
        boolean zO2 = hvVar.o();
        if ((zO || zO2) && !(zO && zO2 && this.f536j.equals(hvVar.f536j))) {
            return false;
        }
        boolean zP = p();
        boolean zP2 = hvVar.p();
        if ((zP || zP2) && !(zP && zP2 && this.f528c == hvVar.f528c)) {
            return false;
        }
        boolean zQ = q();
        boolean zQ2 = hvVar.q();
        if ((zQ || zQ2) && !(zQ && zQ2 && this.f537k.equals(hvVar.f537k))) {
            return false;
        }
        boolean zR = r();
        boolean zR2 = hvVar.r();
        if (zR || zR2) {
            return zR && zR2 && this.f538l.equals(hvVar.f538l);
        }
        return true;
    }

    public String b() {
        return this.f527b;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m411a();
        jfVar.a(f519a);
        if (this.f522a != null && m412a()) {
            jfVar.a(a);
            this.f522a.b(jfVar);
            jfVar.b();
        }
        if (this.f523a != null) {
            jfVar.a(b);
            jfVar.a(this.f523a);
            jfVar.b();
        }
        if (this.f527b != null) {
            jfVar.a(c);
            jfVar.a(this.f527b);
            jfVar.b();
        }
        if (this.f529c != null) {
            jfVar.a(d);
            jfVar.a(this.f529c);
            jfVar.b();
        }
        if (e()) {
            jfVar.a(e);
            jfVar.a(this.f520a);
            jfVar.b();
        }
        if (f()) {
            jfVar.a(f);
            jfVar.a(this.f526b);
            jfVar.b();
        }
        if (this.f530d != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f530d);
            jfVar.b();
        }
        if (this.f531e != null && h()) {
            jfVar.a(h);
            jfVar.a(this.f531e);
            jfVar.b();
        }
        if (this.f532f != null && i()) {
            jfVar.a(i);
            jfVar.a(this.f532f);
            jfVar.b();
        }
        if (this.f533g != null && j()) {
            jfVar.a(j);
            jfVar.a(this.f533g);
            jfVar.b();
        }
        if (this.f534h != null && k()) {
            jfVar.a(k);
            jfVar.a(this.f534h);
            jfVar.b();
        }
        if (this.f521a != null && l()) {
            jfVar.a(l);
            this.f521a.b(jfVar);
            jfVar.b();
        }
        if (this.f535i != null && m()) {
            jfVar.a(m);
            jfVar.a(this.f535i);
            jfVar.b();
        }
        if (n()) {
            jfVar.a(n);
            jfVar.a(this.f525a);
            jfVar.b();
        }
        if (this.f536j != null && o()) {
            jfVar.a(o);
            jfVar.a(this.f536j);
            jfVar.b();
        }
        if (p()) {
            jfVar.a(p);
            jfVar.a(this.f528c);
            jfVar.b();
        }
        if (this.f537k != null && q()) {
            jfVar.a(q);
            jfVar.a(this.f537k);
            jfVar.b();
        }
        if (this.f538l != null && r()) {
            jfVar.a(r);
            jfVar.a(this.f538l);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public void b(boolean z) {
        this.f524a.set(1, z);
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m414b() {
        return this.f523a != null;
    }

    public String c() {
        return this.f529c;
    }

    public void c(boolean z) {
        this.f524a.set(2, z);
    }

    /* renamed from: c, reason: collision with other method in class */
    public boolean m415c() {
        return this.f527b != null;
    }

    public void d(boolean z) {
        this.f524a.set(3, z);
    }

    public boolean d() {
        return this.f529c != null;
    }

    public boolean e() {
        return this.f524a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof hv)) {
            return m413a((hv) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f524a.get(1);
    }

    public boolean g() {
        return this.f530d != null;
    }

    public boolean h() {
        return this.f531e != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f532f != null;
    }

    public boolean j() {
        return this.f533g != null;
    }

    public boolean k() {
        return this.f534h != null;
    }

    public boolean l() {
        return this.f521a != null;
    }

    public boolean m() {
        return this.f535i != null;
    }

    public boolean n() {
        return this.f524a.get(2);
    }

    public boolean o() {
        return this.f536j != null;
    }

    public boolean p() {
        return this.f524a.get(3);
    }

    public boolean q() {
        return this.f537k != null;
    }

    public boolean r() {
        return this.f538l != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("PushMessage(");
        if (m412a()) {
            sb.append("to:");
            if (this.f522a == null) {
                sb.append("null");
            } else {
                sb.append(this.f522a);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f523a == null ? "null" : this.f523a);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f527b == null ? "null" : this.f527b);
        sb.append(", ");
        sb.append("payload:");
        sb.append(this.f529c == null ? "null" : this.f529c);
        if (e()) {
            sb.append(", ");
            sb.append("createAt:");
            sb.append(this.f520a);
        }
        if (f()) {
            sb.append(", ");
            sb.append("ttl:");
            sb.append(this.f526b);
        }
        if (g()) {
            sb.append(", ");
            sb.append("collapseKey:");
            sb.append(this.f530d == null ? "null" : this.f530d);
        }
        if (h()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f531e == null ? "null" : this.f531e);
        }
        if (i()) {
            sb.append(", ");
            sb.append("regId:");
            sb.append(this.f532f == null ? "null" : this.f532f);
        }
        if (j()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.f533g == null ? "null" : this.f533g);
        }
        if (k()) {
            sb.append(", ");
            sb.append("topic:");
            sb.append(this.f534h == null ? "null" : this.f534h);
        }
        if (l()) {
            sb.append(", ");
            sb.append("metaInfo:");
            if (this.f521a == null) {
                sb.append("null");
            } else {
                sb.append(this.f521a);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("aliasName:");
            sb.append(this.f535i == null ? "null" : this.f535i);
        }
        if (n()) {
            sb.append(", ");
            sb.append("isOnline:");
            sb.append(this.f525a);
        }
        if (o()) {
            sb.append(", ");
            sb.append("userAccount:");
            sb.append(this.f536j == null ? "null" : this.f536j);
        }
        if (p()) {
            sb.append(", ");
            sb.append("miid:");
            sb.append(this.f528c);
        }
        if (q()) {
            sb.append(", ");
            sb.append("imeiMd5:");
            sb.append(this.f537k == null ? "null" : this.f537k);
        }
        if (r()) {
            sb.append(", ");
            sb.append("deviceId:");
            sb.append(this.f538l == null ? "null" : this.f538l);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
