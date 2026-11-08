package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class io implements iu<io, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public long f745a;

    /* renamed from: a, reason: collision with other field name */
    public hy f746a;

    /* renamed from: a, reason: collision with other field name */
    public String f747a;

    /* renamed from: a, reason: collision with other field name */
    private BitSet f748a = new BitSet(1);

    /* renamed from: b, reason: collision with other field name */
    public String f749b;

    /* renamed from: c, reason: collision with other field name */
    public String f750c;

    /* renamed from: d, reason: collision with other field name */
    public String f751d;

    /* renamed from: e, reason: collision with other field name */
    public String f752e;

    /* renamed from: f, reason: collision with other field name */
    public String f753f;

    /* renamed from: g, reason: collision with other field name */
    public String f754g;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f744a = new jk("XmPushActionSubscriptionResult");
    private static final jc a = new jc("", (byte) 11, 1);
    private static final jc b = new jc("", (byte) 12, 2);
    private static final jc c = new jc("", (byte) 11, 3);
    private static final jc d = new jc("", (byte) 11, 4);
    private static final jc e = new jc("", (byte) 10, 6);
    private static final jc f = new jc("", (byte) 11, 7);
    private static final jc g = new jc("", (byte) 11, 8);
    private static final jc h = new jc("", (byte) 11, 9);
    private static final jc i = new jc("", (byte) 11, 10);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(io ioVar) {
        int iA;
        int iA2;
        int iA3;
        int iA4;
        int iA5;
        int iA6;
        int iA7;
        int iA8;
        int iA9;
        if (!getClass().equals(ioVar.getClass())) {
            return getClass().getName().compareTo(ioVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m509a()).compareTo(Boolean.valueOf(ioVar.m509a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m509a() && (iA9 = iv.a(this.f747a, ioVar.f747a)) != 0) {
            return iA9;
        }
        int iCompareTo2 = Boolean.valueOf(m511b()).compareTo(Boolean.valueOf(ioVar.m511b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (m511b() && (iA8 = iv.a(this.f746a, ioVar.f746a)) != 0) {
            return iA8;
        }
        int iCompareTo3 = Boolean.valueOf(m512c()).compareTo(Boolean.valueOf(ioVar.m512c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (m512c() && (iA7 = iv.a(this.f749b, ioVar.f749b)) != 0) {
            return iA7;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(ioVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA6 = iv.a(this.f750c, ioVar.f750c)) != 0) {
            return iA6;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(ioVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA5 = iv.a(this.f745a, ioVar.f745a)) != 0) {
            return iA5;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(ioVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA4 = iv.a(this.f751d, ioVar.f751d)) != 0) {
            return iA4;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(ioVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA3 = iv.a(this.f752e, ioVar.f752e)) != 0) {
            return iA3;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(ioVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA2 = iv.a(this.f753f, ioVar.f753f)) != 0) {
            return iA2;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(ioVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (!i() || (iA = iv.a(this.f754g, ioVar.f754g)) == 0) {
            return 0;
        }
        return iA;
    }

    public String a() {
        return this.f749b;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m508a() throws jg {
        if (this.f749b != null) {
            return;
        }
        throw new jg("Required field 'id' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0019  */
    @Override // com.xiaomi.push.iu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.xiaomi.push.jf r4) throws com.xiaomi.push.jg {
        /*
            r3 = this;
            r4.mo535a()
        L3:
            com.xiaomi.push.jc r0 = r4.mo531a()
            byte r1 = r0.a
            if (r1 != 0) goto L12
            r4.f()
            r3.m508a()
            return
        L12:
            short r1 = r0.f812a
            r2 = 11
            switch(r1) {
                case 1: goto L86;
                case 2: goto L73;
                case 3: goto L68;
                case 4: goto L5d;
                case 5: goto L19;
                case 6: goto L4c;
                case 7: goto L41;
                case 8: goto L36;
                case 9: goto L2b;
                case 10: goto L20;
                default: goto L19;
            }
        L19:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r4, r0)
            goto L90
        L20:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r4.mo536a()
            r3.f754g = r0
            goto L90
        L2b:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r4.mo536a()
            r3.f753f = r0
            goto L90
        L36:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r4.mo536a()
            r3.f752e = r0
            goto L90
        L41:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r4.mo536a()
            r3.f751d = r0
            goto L90
        L4c:
            byte r1 = r0.a
            r2 = 10
            if (r1 != r2) goto L19
            long r0 = r4.mo530a()
            r3.f745a = r0
            r0 = 1
            r3.a(r0)
            goto L90
        L5d:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r4.mo536a()
            r3.f750c = r0
            goto L90
        L68:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r4.mo536a()
            r3.f749b = r0
            goto L90
        L73:
            byte r1 = r0.a
            r2 = 12
            if (r1 != r2) goto L19
            com.xiaomi.push.hy r0 = new com.xiaomi.push.hy
            r0.<init>()
            r3.f746a = r0
            com.xiaomi.push.hy r0 = r3.f746a
            r0.a(r4)
            goto L90
        L86:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r4.mo536a()
            r3.f747a = r0
        L90:
            r4.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.io.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f748a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m509a() {
        return this.f747a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m510a(io ioVar) {
        if (ioVar == null) {
            return false;
        }
        boolean zM509a = m509a();
        boolean zM509a2 = ioVar.m509a();
        if ((zM509a || zM509a2) && !(zM509a && zM509a2 && this.f747a.equals(ioVar.f747a))) {
            return false;
        }
        boolean zM511b = m511b();
        boolean zM511b2 = ioVar.m511b();
        if ((zM511b || zM511b2) && !(zM511b && zM511b2 && this.f746a.m430a(ioVar.f746a))) {
            return false;
        }
        boolean zM512c = m512c();
        boolean zM512c2 = ioVar.m512c();
        if ((zM512c || zM512c2) && !(zM512c && zM512c2 && this.f749b.equals(ioVar.f749b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = ioVar.d();
        if ((zD || zD2) && !(zD && zD2 && this.f750c.equals(ioVar.f750c))) {
            return false;
        }
        boolean zE = e();
        boolean zE2 = ioVar.e();
        if ((zE || zE2) && !(zE && zE2 && this.f745a == ioVar.f745a)) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = ioVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f751d.equals(ioVar.f751d))) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = ioVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f752e.equals(ioVar.f752e))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = ioVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f753f.equals(ioVar.f753f))) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = ioVar.i();
        if (zI || zI2) {
            return zI && zI2 && this.f754g.equals(ioVar.f754g);
        }
        return true;
    }

    public String b() {
        return this.f752e;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m508a();
        jfVar.a(f744a);
        if (this.f747a != null && m509a()) {
            jfVar.a(a);
            jfVar.a(this.f747a);
            jfVar.b();
        }
        if (this.f746a != null && m511b()) {
            jfVar.a(b);
            this.f746a.b(jfVar);
            jfVar.b();
        }
        if (this.f749b != null) {
            jfVar.a(c);
            jfVar.a(this.f749b);
            jfVar.b();
        }
        if (this.f750c != null && d()) {
            jfVar.a(d);
            jfVar.a(this.f750c);
            jfVar.b();
        }
        if (e()) {
            jfVar.a(e);
            jfVar.a(this.f745a);
            jfVar.b();
        }
        if (this.f751d != null && f()) {
            jfVar.a(f);
            jfVar.a(this.f751d);
            jfVar.b();
        }
        if (this.f752e != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f752e);
            jfVar.b();
        }
        if (this.f753f != null && h()) {
            jfVar.a(h);
            jfVar.a(this.f753f);
            jfVar.b();
        }
        if (this.f754g != null && i()) {
            jfVar.a(i);
            jfVar.a(this.f754g);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m511b() {
        return this.f746a != null;
    }

    public String c() {
        return this.f754g;
    }

    /* renamed from: c, reason: collision with other method in class */
    public boolean m512c() {
        return this.f749b != null;
    }

    public boolean d() {
        return this.f750c != null;
    }

    public boolean e() {
        return this.f748a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof io)) {
            return m510a((io) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f751d != null;
    }

    public boolean g() {
        return this.f752e != null;
    }

    public boolean h() {
        return this.f753f != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f754g != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSubscriptionResult(");
        if (m509a()) {
            sb.append("debug:");
            sb.append(this.f747a == null ? "null" : this.f747a);
            z = false;
        } else {
            z = true;
        }
        if (m511b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f746a == null) {
                sb.append("null");
            } else {
                sb.append(this.f746a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f749b == null ? "null" : this.f749b);
        if (d()) {
            sb.append(", ");
            sb.append("appId:");
            sb.append(this.f750c == null ? "null" : this.f750c);
        }
        if (e()) {
            sb.append(", ");
            sb.append("errorCode:");
            sb.append(this.f745a);
        }
        if (f()) {
            sb.append(", ");
            sb.append("reason:");
            sb.append(this.f751d == null ? "null" : this.f751d);
        }
        if (g()) {
            sb.append(", ");
            sb.append("topic:");
            sb.append(this.f752e == null ? "null" : this.f752e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f753f == null ? "null" : this.f753f);
        }
        if (i()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.f754g == null ? "null" : this.f754g);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
