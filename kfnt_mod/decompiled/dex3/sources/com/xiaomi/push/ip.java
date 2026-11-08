package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ip implements iu<ip, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public long f756a;

    /* renamed from: a, reason: collision with other field name */
    public hy f757a;

    /* renamed from: a, reason: collision with other field name */
    public String f758a;

    /* renamed from: a, reason: collision with other field name */
    private BitSet f759a = new BitSet(2);

    /* renamed from: a, reason: collision with other field name */
    public boolean f760a = true;

    /* renamed from: b, reason: collision with other field name */
    public String f761b;

    /* renamed from: c, reason: collision with other field name */
    public String f762c;

    /* renamed from: d, reason: collision with other field name */
    public String f763d;

    /* renamed from: e, reason: collision with other field name */
    public String f764e;

    /* renamed from: f, reason: collision with other field name */
    public String f765f;

    /* renamed from: g, reason: collision with other field name */
    public String f766g;

    /* renamed from: h, reason: collision with other field name */
    public String f767h;

    /* renamed from: i, reason: collision with other field name */
    public String f768i;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f755a = new jk("XmPushActionUnRegistration");
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
    private static final jc k = new jc("", (byte) 2, 11);
    private static final jc l = new jc("", (byte) 10, 12);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ip ipVar) {
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
        if (!getClass().equals(ipVar.getClass())) {
            return getClass().getName().compareTo(ipVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m513a()).compareTo(Boolean.valueOf(ipVar.m513a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m513a() && (iA12 = iv.a(this.f758a, ipVar.f758a)) != 0) {
            return iA12;
        }
        int iCompareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(ipVar.b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (b() && (iA11 = iv.a(this.f757a, ipVar.f757a)) != 0) {
            return iA11;
        }
        int iCompareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(ipVar.c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (c() && (iA10 = iv.a(this.f761b, ipVar.f761b)) != 0) {
            return iA10;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(ipVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA9 = iv.a(this.f762c, ipVar.f762c)) != 0) {
            return iA9;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(ipVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA8 = iv.a(this.f763d, ipVar.f763d)) != 0) {
            return iA8;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(ipVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA7 = iv.a(this.f764e, ipVar.f764e)) != 0) {
            return iA7;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(ipVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA6 = iv.a(this.f765f, ipVar.f765f)) != 0) {
            return iA6;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(ipVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA5 = iv.a(this.f766g, ipVar.f766g)) != 0) {
            return iA5;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(ipVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (i() && (iA4 = iv.a(this.f767h, ipVar.f767h)) != 0) {
            return iA4;
        }
        int iCompareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(ipVar.j()));
        if (iCompareTo10 != 0) {
            return iCompareTo10;
        }
        if (j() && (iA3 = iv.a(this.f768i, ipVar.f768i)) != 0) {
            return iA3;
        }
        int iCompareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(ipVar.k()));
        if (iCompareTo11 != 0) {
            return iCompareTo11;
        }
        if (k() && (iA2 = iv.a(this.f760a, ipVar.f760a)) != 0) {
            return iA2;
        }
        int iCompareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(ipVar.l()));
        if (iCompareTo12 != 0) {
            return iCompareTo12;
        }
        if (!l() || (iA = iv.a(this.f756a, ipVar.f756a)) == 0) {
            return 0;
        }
        return iA;
    }

    public ip a(String str) {
        this.f761b = str;
        return this;
    }

    public void a() throws jg {
        if (this.f761b == null) {
            throw new jg("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f762c != null) {
            return;
        }
        throw new jg("Required field 'appId' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001a  */
    @Override // com.xiaomi.push.iu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.xiaomi.push.jf r5) throws com.xiaomi.push.jg {
        /*
            r4 = this;
            r5.mo535a()
        L3:
            com.xiaomi.push.jc r0 = r5.mo531a()
            byte r1 = r0.a
            if (r1 != 0) goto L12
            r5.f()
            r4.a()
            return
        L12:
            short r1 = r0.f812a
            r2 = 1
            r3 = 11
            switch(r1) {
                case 1: goto Lae;
                case 2: goto L9b;
                case 3: goto L90;
                case 4: goto L85;
                case 5: goto L7a;
                case 6: goto L6f;
                case 7: goto L64;
                case 8: goto L59;
                case 9: goto L4e;
                case 10: goto L42;
                case 11: goto L32;
                case 12: goto L21;
                default: goto L1a;
            }
        L1a:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r5, r0)
            goto Lb8
        L21:
            byte r1 = r0.a
            r3 = 10
            if (r1 != r3) goto L1a
            long r0 = r5.mo530a()
            r4.f756a = r0
            r4.b(r2)
            goto Lb8
        L32:
            byte r1 = r0.a
            r3 = 2
            if (r1 != r3) goto L1a
            boolean r0 = r5.mo541a()
            r4.f760a = r0
            r4.a(r2)
            goto Lb8
        L42:
            byte r1 = r0.a
            if (r1 != r3) goto L1a
            java.lang.String r0 = r5.mo536a()
            r4.f768i = r0
            goto Lb8
        L4e:
            byte r1 = r0.a
            if (r1 != r3) goto L1a
            java.lang.String r0 = r5.mo536a()
            r4.f767h = r0
            goto Lb8
        L59:
            byte r1 = r0.a
            if (r1 != r3) goto L1a
            java.lang.String r0 = r5.mo536a()
            r4.f766g = r0
            goto Lb8
        L64:
            byte r1 = r0.a
            if (r1 != r3) goto L1a
            java.lang.String r0 = r5.mo536a()
            r4.f765f = r0
            goto Lb8
        L6f:
            byte r1 = r0.a
            if (r1 != r3) goto L1a
            java.lang.String r0 = r5.mo536a()
            r4.f764e = r0
            goto Lb8
        L7a:
            byte r1 = r0.a
            if (r1 != r3) goto L1a
            java.lang.String r0 = r5.mo536a()
            r4.f763d = r0
            goto Lb8
        L85:
            byte r1 = r0.a
            if (r1 != r3) goto L1a
            java.lang.String r0 = r5.mo536a()
            r4.f762c = r0
            goto Lb8
        L90:
            byte r1 = r0.a
            if (r1 != r3) goto L1a
            java.lang.String r0 = r5.mo536a()
            r4.f761b = r0
            goto Lb8
        L9b:
            byte r1 = r0.a
            r2 = 12
            if (r1 != r2) goto L1a
            com.xiaomi.push.hy r0 = new com.xiaomi.push.hy
            r0.<init>()
            r4.f757a = r0
            com.xiaomi.push.hy r0 = r4.f757a
            r0.a(r5)
            goto Lb8
        Lae:
            byte r1 = r0.a
            if (r1 != r3) goto L1a
            java.lang.String r0 = r5.mo536a()
            r4.f758a = r0
        Lb8:
            r5.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ip.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f759a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m513a() {
        return this.f758a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m514a(ip ipVar) {
        if (ipVar == null) {
            return false;
        }
        boolean zM513a = m513a();
        boolean zM513a2 = ipVar.m513a();
        if ((zM513a || zM513a2) && !(zM513a && zM513a2 && this.f758a.equals(ipVar.f758a))) {
            return false;
        }
        boolean zB = b();
        boolean zB2 = ipVar.b();
        if ((zB || zB2) && !(zB && zB2 && this.f757a.m430a(ipVar.f757a))) {
            return false;
        }
        boolean zC = c();
        boolean zC2 = ipVar.c();
        if ((zC || zC2) && !(zC && zC2 && this.f761b.equals(ipVar.f761b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = ipVar.d();
        if ((zD || zD2) && !(zD && zD2 && this.f762c.equals(ipVar.f762c))) {
            return false;
        }
        boolean zE = e();
        boolean zE2 = ipVar.e();
        if ((zE || zE2) && !(zE && zE2 && this.f763d.equals(ipVar.f763d))) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = ipVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f764e.equals(ipVar.f764e))) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = ipVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f765f.equals(ipVar.f765f))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = ipVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f766g.equals(ipVar.f766g))) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = ipVar.i();
        if ((zI || zI2) && !(zI && zI2 && this.f767h.equals(ipVar.f767h))) {
            return false;
        }
        boolean zJ = j();
        boolean zJ2 = ipVar.j();
        if ((zJ || zJ2) && !(zJ && zJ2 && this.f768i.equals(ipVar.f768i))) {
            return false;
        }
        boolean zK = k();
        boolean zK2 = ipVar.k();
        if ((zK || zK2) && !(zK && zK2 && this.f760a == ipVar.f760a)) {
            return false;
        }
        boolean zL = l();
        boolean zL2 = ipVar.l();
        if (zL || zL2) {
            return zL && zL2 && this.f756a == ipVar.f756a;
        }
        return true;
    }

    public ip b(String str) {
        this.f762c = str;
        return this;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        a();
        jfVar.a(f755a);
        if (this.f758a != null && m513a()) {
            jfVar.a(a);
            jfVar.a(this.f758a);
            jfVar.b();
        }
        if (this.f757a != null && b()) {
            jfVar.a(b);
            this.f757a.b(jfVar);
            jfVar.b();
        }
        if (this.f761b != null) {
            jfVar.a(c);
            jfVar.a(this.f761b);
            jfVar.b();
        }
        if (this.f762c != null) {
            jfVar.a(d);
            jfVar.a(this.f762c);
            jfVar.b();
        }
        if (this.f763d != null && e()) {
            jfVar.a(e);
            jfVar.a(this.f763d);
            jfVar.b();
        }
        if (this.f764e != null && f()) {
            jfVar.a(f);
            jfVar.a(this.f764e);
            jfVar.b();
        }
        if (this.f765f != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f765f);
            jfVar.b();
        }
        if (this.f766g != null && h()) {
            jfVar.a(h);
            jfVar.a(this.f766g);
            jfVar.b();
        }
        if (this.f767h != null && i()) {
            jfVar.a(i);
            jfVar.a(this.f767h);
            jfVar.b();
        }
        if (this.f768i != null && j()) {
            jfVar.a(j);
            jfVar.a(this.f768i);
            jfVar.b();
        }
        if (k()) {
            jfVar.a(k);
            jfVar.a(this.f760a);
            jfVar.b();
        }
        if (l()) {
            jfVar.a(l);
            jfVar.a(this.f756a);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public void b(boolean z) {
        this.f759a.set(1, z);
    }

    public boolean b() {
        return this.f757a != null;
    }

    public ip c(String str) {
        this.f763d = str;
        return this;
    }

    public boolean c() {
        return this.f761b != null;
    }

    public ip d(String str) {
        this.f765f = str;
        return this;
    }

    public boolean d() {
        return this.f762c != null;
    }

    public ip e(String str) {
        this.f766g = str;
        return this;
    }

    public boolean e() {
        return this.f763d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ip)) {
            return m514a((ip) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f764e != null;
    }

    public boolean g() {
        return this.f765f != null;
    }

    public boolean h() {
        return this.f766g != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f767h != null;
    }

    public boolean j() {
        return this.f768i != null;
    }

    public boolean k() {
        return this.f759a.get(0);
    }

    public boolean l() {
        return this.f759a.get(1);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnRegistration(");
        if (m513a()) {
            sb.append("debug:");
            sb.append(this.f758a == null ? "null" : this.f758a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f757a == null) {
                sb.append("null");
            } else {
                sb.append(this.f757a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f761b == null ? "null" : this.f761b);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f762c == null ? "null" : this.f762c);
        if (e()) {
            sb.append(", ");
            sb.append("regId:");
            sb.append(this.f763d == null ? "null" : this.f763d);
        }
        if (f()) {
            sb.append(", ");
            sb.append("appVersion:");
            sb.append(this.f764e == null ? "null" : this.f764e);
        }
        if (g()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f765f == null ? "null" : this.f765f);
        }
        if (h()) {
            sb.append(", ");
            sb.append("token:");
            sb.append(this.f766g == null ? "null" : this.f766g);
        }
        if (i()) {
            sb.append(", ");
            sb.append("deviceId:");
            sb.append(this.f767h == null ? "null" : this.f767h);
        }
        if (j()) {
            sb.append(", ");
            sb.append("aliasName:");
            sb.append(this.f768i == null ? "null" : this.f768i);
        }
        if (k()) {
            sb.append(", ");
            sb.append("needAck:");
            sb.append(this.f760a);
        }
        if (l()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f756a);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
