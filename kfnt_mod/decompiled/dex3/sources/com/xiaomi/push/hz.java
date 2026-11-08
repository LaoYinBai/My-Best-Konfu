package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class hz implements iu<hz, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public int f565a;

    /* renamed from: a, reason: collision with other field name */
    public long f566a;

    /* renamed from: a, reason: collision with other field name */
    public hy f567a;

    /* renamed from: a, reason: collision with other field name */
    public im f568a;

    /* renamed from: a, reason: collision with other field name */
    public String f569a;

    /* renamed from: a, reason: collision with other field name */
    public Map<String, String> f571a;

    /* renamed from: a, reason: collision with other field name */
    public short f572a;

    /* renamed from: b, reason: collision with other field name */
    public String f574b;

    /* renamed from: b, reason: collision with other field name */
    public short f575b;

    /* renamed from: c, reason: collision with other field name */
    public String f576c;

    /* renamed from: d, reason: collision with other field name */
    public String f577d;

    /* renamed from: e, reason: collision with other field name */
    public String f578e;

    /* renamed from: f, reason: collision with other field name */
    public String f579f;

    /* renamed from: g, reason: collision with other field name */
    public String f580g;

    /* renamed from: h, reason: collision with other field name */
    public String f581h;

    /* renamed from: i, reason: collision with other field name */
    public String f582i;

    /* renamed from: j, reason: collision with other field name */
    public String f583j;

    /* renamed from: k, reason: collision with other field name */
    public String f584k;

    /* renamed from: l, reason: collision with other field name */
    public String f585l;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f564a = new jk("XmPushActionAckMessage");
    private static final jc a = new jc("", (byte) 11, 1);
    private static final jc b = new jc("", (byte) 12, 2);
    private static final jc c = new jc("", (byte) 11, 3);
    private static final jc d = new jc("", (byte) 11, 4);
    private static final jc e = new jc("", (byte) 10, 5);
    private static final jc f = new jc("", (byte) 11, 6);
    private static final jc g = new jc("", (byte) 11, 7);
    private static final jc h = new jc("", (byte) 12, 8);
    private static final jc i = new jc("", (byte) 11, 9);
    private static final jc j = new jc("", (byte) 11, 10);
    private static final jc k = new jc("", (byte) 2, 11);
    private static final jc l = new jc("", (byte) 11, 12);
    private static final jc m = new jc("", (byte) 11, 13);
    private static final jc n = new jc("", (byte) 11, 14);
    private static final jc o = new jc("", (byte) 6, 15);
    private static final jc p = new jc("", (byte) 6, 16);
    private static final jc q = new jc("", (byte) 11, 20);
    private static final jc r = new jc("", (byte) 11, 21);
    private static final jc s = new jc("", (byte) 8, 22);
    private static final jc t = new jc("", com.umeng.commonsdk.proguard.az.k, 23);

    /* renamed from: a, reason: collision with other field name */
    private BitSet f570a = new BitSet(5);

    /* renamed from: a, reason: collision with other field name */
    public boolean f573a = false;

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(hz hzVar) {
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
        if (!getClass().equals(hzVar.getClass())) {
            return getClass().getName().compareTo(hzVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m431a()).compareTo(Boolean.valueOf(hzVar.m431a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m431a() && (iA20 = iv.a(this.f569a, hzVar.f569a)) != 0) {
            return iA20;
        }
        int iCompareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(hzVar.b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (b() && (iA19 = iv.a(this.f567a, hzVar.f567a)) != 0) {
            return iA19;
        }
        int iCompareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(hzVar.c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (c() && (iA18 = iv.a(this.f574b, hzVar.f574b)) != 0) {
            return iA18;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(hzVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA17 = iv.a(this.f576c, hzVar.f576c)) != 0) {
            return iA17;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(hzVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA16 = iv.a(this.f566a, hzVar.f566a)) != 0) {
            return iA16;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(hzVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA15 = iv.a(this.f577d, hzVar.f577d)) != 0) {
            return iA15;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(hzVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA14 = iv.a(this.f578e, hzVar.f578e)) != 0) {
            return iA14;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(hzVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA13 = iv.a(this.f568a, hzVar.f568a)) != 0) {
            return iA13;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(hzVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (i() && (iA12 = iv.a(this.f579f, hzVar.f579f)) != 0) {
            return iA12;
        }
        int iCompareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(hzVar.j()));
        if (iCompareTo10 != 0) {
            return iCompareTo10;
        }
        if (j() && (iA11 = iv.a(this.f580g, hzVar.f580g)) != 0) {
            return iA11;
        }
        int iCompareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(hzVar.k()));
        if (iCompareTo11 != 0) {
            return iCompareTo11;
        }
        if (k() && (iA10 = iv.a(this.f573a, hzVar.f573a)) != 0) {
            return iA10;
        }
        int iCompareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(hzVar.l()));
        if (iCompareTo12 != 0) {
            return iCompareTo12;
        }
        if (l() && (iA9 = iv.a(this.f581h, hzVar.f581h)) != 0) {
            return iA9;
        }
        int iCompareTo13 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(hzVar.m()));
        if (iCompareTo13 != 0) {
            return iCompareTo13;
        }
        if (m() && (iA8 = iv.a(this.f582i, hzVar.f582i)) != 0) {
            return iA8;
        }
        int iCompareTo14 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(hzVar.n()));
        if (iCompareTo14 != 0) {
            return iCompareTo14;
        }
        if (n() && (iA7 = iv.a(this.f583j, hzVar.f583j)) != 0) {
            return iA7;
        }
        int iCompareTo15 = Boolean.valueOf(o()).compareTo(Boolean.valueOf(hzVar.o()));
        if (iCompareTo15 != 0) {
            return iCompareTo15;
        }
        if (o() && (iA6 = iv.a(this.f572a, hzVar.f572a)) != 0) {
            return iA6;
        }
        int iCompareTo16 = Boolean.valueOf(p()).compareTo(Boolean.valueOf(hzVar.p()));
        if (iCompareTo16 != 0) {
            return iCompareTo16;
        }
        if (p() && (iA5 = iv.a(this.f575b, hzVar.f575b)) != 0) {
            return iA5;
        }
        int iCompareTo17 = Boolean.valueOf(q()).compareTo(Boolean.valueOf(hzVar.q()));
        if (iCompareTo17 != 0) {
            return iCompareTo17;
        }
        if (q() && (iA4 = iv.a(this.f584k, hzVar.f584k)) != 0) {
            return iA4;
        }
        int iCompareTo18 = Boolean.valueOf(r()).compareTo(Boolean.valueOf(hzVar.r()));
        if (iCompareTo18 != 0) {
            return iCompareTo18;
        }
        if (r() && (iA3 = iv.a(this.f585l, hzVar.f585l)) != 0) {
            return iA3;
        }
        int iCompareTo19 = Boolean.valueOf(s()).compareTo(Boolean.valueOf(hzVar.s()));
        if (iCompareTo19 != 0) {
            return iCompareTo19;
        }
        if (s() && (iA2 = iv.a(this.f565a, hzVar.f565a)) != 0) {
            return iA2;
        }
        int iCompareTo20 = Boolean.valueOf(t()).compareTo(Boolean.valueOf(hzVar.t()));
        if (iCompareTo20 != 0) {
            return iCompareTo20;
        }
        if (!t() || (iA = iv.a(this.f571a, hzVar.f571a)) == 0) {
            return 0;
        }
        return iA;
    }

    public hz a(long j2) {
        this.f566a = j2;
        a(true);
        return this;
    }

    public hz a(String str) {
        this.f574b = str;
        return this;
    }

    public hz a(short s2) {
        this.f572a = s2;
        c(true);
        return this;
    }

    public void a() throws jg {
        if (this.f574b == null) {
            throw new jg("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f576c != null) {
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
    public void a(com.xiaomi.push.jf r8) throws com.xiaomi.push.jg {
        /*
            Method dump skipped, instructions count: 422
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hz.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f570a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m431a() {
        return this.f569a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m432a(hz hzVar) {
        if (hzVar == null) {
            return false;
        }
        boolean zM431a = m431a();
        boolean zM431a2 = hzVar.m431a();
        if ((zM431a || zM431a2) && !(zM431a && zM431a2 && this.f569a.equals(hzVar.f569a))) {
            return false;
        }
        boolean zB = b();
        boolean zB2 = hzVar.b();
        if ((zB || zB2) && !(zB && zB2 && this.f567a.m430a(hzVar.f567a))) {
            return false;
        }
        boolean zC = c();
        boolean zC2 = hzVar.c();
        if ((zC || zC2) && !(zC && zC2 && this.f574b.equals(hzVar.f574b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = hzVar.d();
        if (((zD || zD2) && !(zD && zD2 && this.f576c.equals(hzVar.f576c))) || this.f566a != hzVar.f566a) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = hzVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f577d.equals(hzVar.f577d))) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = hzVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f578e.equals(hzVar.f578e))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = hzVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f568a.m500a(hzVar.f568a))) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = hzVar.i();
        if ((zI || zI2) && !(zI && zI2 && this.f579f.equals(hzVar.f579f))) {
            return false;
        }
        boolean zJ = j();
        boolean zJ2 = hzVar.j();
        if ((zJ || zJ2) && !(zJ && zJ2 && this.f580g.equals(hzVar.f580g))) {
            return false;
        }
        boolean zK = k();
        boolean zK2 = hzVar.k();
        if ((zK || zK2) && !(zK && zK2 && this.f573a == hzVar.f573a)) {
            return false;
        }
        boolean zL = l();
        boolean zL2 = hzVar.l();
        if ((zL || zL2) && !(zL && zL2 && this.f581h.equals(hzVar.f581h))) {
            return false;
        }
        boolean zM = m();
        boolean zM2 = hzVar.m();
        if ((zM || zM2) && !(zM && zM2 && this.f582i.equals(hzVar.f582i))) {
            return false;
        }
        boolean zN = n();
        boolean zN2 = hzVar.n();
        if ((zN || zN2) && !(zN && zN2 && this.f583j.equals(hzVar.f583j))) {
            return false;
        }
        boolean zO = o();
        boolean zO2 = hzVar.o();
        if ((zO || zO2) && !(zO && zO2 && this.f572a == hzVar.f572a)) {
            return false;
        }
        boolean zP = p();
        boolean zP2 = hzVar.p();
        if ((zP || zP2) && !(zP && zP2 && this.f575b == hzVar.f575b)) {
            return false;
        }
        boolean zQ = q();
        boolean zQ2 = hzVar.q();
        if ((zQ || zQ2) && !(zQ && zQ2 && this.f584k.equals(hzVar.f584k))) {
            return false;
        }
        boolean zR = r();
        boolean zR2 = hzVar.r();
        if ((zR || zR2) && !(zR && zR2 && this.f585l.equals(hzVar.f585l))) {
            return false;
        }
        boolean zS = s();
        boolean zS2 = hzVar.s();
        if ((zS || zS2) && !(zS && zS2 && this.f565a == hzVar.f565a)) {
            return false;
        }
        boolean zT = t();
        boolean zT2 = hzVar.t();
        if (zT || zT2) {
            return zT && zT2 && this.f571a.equals(hzVar.f571a);
        }
        return true;
    }

    public hz b(String str) {
        this.f576c = str;
        return this;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        a();
        jfVar.a(f564a);
        if (this.f569a != null && m431a()) {
            jfVar.a(a);
            jfVar.a(this.f569a);
            jfVar.b();
        }
        if (this.f567a != null && b()) {
            jfVar.a(b);
            this.f567a.b(jfVar);
            jfVar.b();
        }
        if (this.f574b != null) {
            jfVar.a(c);
            jfVar.a(this.f574b);
            jfVar.b();
        }
        if (this.f576c != null) {
            jfVar.a(d);
            jfVar.a(this.f576c);
            jfVar.b();
        }
        jfVar.a(e);
        jfVar.a(this.f566a);
        jfVar.b();
        if (this.f577d != null && f()) {
            jfVar.a(f);
            jfVar.a(this.f577d);
            jfVar.b();
        }
        if (this.f578e != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f578e);
            jfVar.b();
        }
        if (this.f568a != null && h()) {
            jfVar.a(h);
            this.f568a.b(jfVar);
            jfVar.b();
        }
        if (this.f579f != null && i()) {
            jfVar.a(i);
            jfVar.a(this.f579f);
            jfVar.b();
        }
        if (this.f580g != null && j()) {
            jfVar.a(j);
            jfVar.a(this.f580g);
            jfVar.b();
        }
        if (k()) {
            jfVar.a(k);
            jfVar.a(this.f573a);
            jfVar.b();
        }
        if (this.f581h != null && l()) {
            jfVar.a(l);
            jfVar.a(this.f581h);
            jfVar.b();
        }
        if (this.f582i != null && m()) {
            jfVar.a(m);
            jfVar.a(this.f582i);
            jfVar.b();
        }
        if (this.f583j != null && n()) {
            jfVar.a(n);
            jfVar.a(this.f583j);
            jfVar.b();
        }
        if (o()) {
            jfVar.a(o);
            jfVar.a(this.f572a);
            jfVar.b();
        }
        if (p()) {
            jfVar.a(p);
            jfVar.a(this.f575b);
            jfVar.b();
        }
        if (this.f584k != null && q()) {
            jfVar.a(q);
            jfVar.a(this.f584k);
            jfVar.b();
        }
        if (this.f585l != null && r()) {
            jfVar.a(r);
            jfVar.a(this.f585l);
            jfVar.b();
        }
        if (s()) {
            jfVar.a(s);
            jfVar.mo540a(this.f565a);
            jfVar.b();
        }
        if (this.f571a != null && t()) {
            jfVar.a(t);
            jfVar.a(new je((byte) 11, (byte) 11, this.f571a.size()));
            for (Map.Entry<String, String> entry : this.f571a.entrySet()) {
                jfVar.a(entry.getKey());
                jfVar.a(entry.getValue());
            }
            jfVar.d();
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public void b(boolean z) {
        this.f570a.set(1, z);
    }

    public boolean b() {
        return this.f567a != null;
    }

    public hz c(String str) {
        this.f577d = str;
        return this;
    }

    public void c(boolean z) {
        this.f570a.set(2, z);
    }

    public boolean c() {
        return this.f574b != null;
    }

    public hz d(String str) {
        this.f578e = str;
        return this;
    }

    public void d(boolean z) {
        this.f570a.set(3, z);
    }

    public boolean d() {
        return this.f576c != null;
    }

    public void e(boolean z) {
        this.f570a.set(4, z);
    }

    public boolean e() {
        return this.f570a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof hz)) {
            return m432a((hz) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f577d != null;
    }

    public boolean g() {
        return this.f578e != null;
    }

    public boolean h() {
        return this.f568a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f579f != null;
    }

    public boolean j() {
        return this.f580g != null;
    }

    public boolean k() {
        return this.f570a.get(1);
    }

    public boolean l() {
        return this.f581h != null;
    }

    public boolean m() {
        return this.f582i != null;
    }

    public boolean n() {
        return this.f583j != null;
    }

    public boolean o() {
        return this.f570a.get(2);
    }

    public boolean p() {
        return this.f570a.get(3);
    }

    public boolean q() {
        return this.f584k != null;
    }

    public boolean r() {
        return this.f585l != null;
    }

    public boolean s() {
        return this.f570a.get(4);
    }

    public boolean t() {
        return this.f571a != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionAckMessage(");
        if (m431a()) {
            sb.append("debug:");
            sb.append(this.f569a == null ? "null" : this.f569a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f567a == null) {
                sb.append("null");
            } else {
                sb.append(this.f567a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f574b == null ? "null" : this.f574b);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f576c == null ? "null" : this.f576c);
        sb.append(", ");
        sb.append("messageTs:");
        sb.append(this.f566a);
        if (f()) {
            sb.append(", ");
            sb.append("topic:");
            sb.append(this.f577d == null ? "null" : this.f577d);
        }
        if (g()) {
            sb.append(", ");
            sb.append("aliasName:");
            sb.append(this.f578e == null ? "null" : this.f578e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("request:");
            if (this.f568a == null) {
                sb.append("null");
            } else {
                sb.append(this.f568a);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f579f == null ? "null" : this.f579f);
        }
        if (j()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.f580g == null ? "null" : this.f580g);
        }
        if (k()) {
            sb.append(", ");
            sb.append("isOnline:");
            sb.append(this.f573a);
        }
        if (l()) {
            sb.append(", ");
            sb.append("regId:");
            sb.append(this.f581h == null ? "null" : this.f581h);
        }
        if (m()) {
            sb.append(", ");
            sb.append("callbackUrl:");
            sb.append(this.f582i == null ? "null" : this.f582i);
        }
        if (n()) {
            sb.append(", ");
            sb.append("userAccount:");
            sb.append(this.f583j == null ? "null" : this.f583j);
        }
        if (o()) {
            sb.append(", ");
            sb.append("deviceStatus:");
            sb.append((int) this.f572a);
        }
        if (p()) {
            sb.append(", ");
            sb.append("geoMsgStatus:");
            sb.append((int) this.f575b);
        }
        if (q()) {
            sb.append(", ");
            sb.append("imeiMd5:");
            sb.append(this.f584k == null ? "null" : this.f584k);
        }
        if (r()) {
            sb.append(", ");
            sb.append("deviceId:");
            sb.append(this.f585l == null ? "null" : this.f585l);
        }
        if (s()) {
            sb.append(", ");
            sb.append("passThrough:");
            sb.append(this.f565a);
        }
        if (t()) {
            sb.append(", ");
            sb.append("extra:");
            if (this.f571a == null) {
                sb.append("null");
            } else {
                sb.append(this.f571a);
            }
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
