package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class is implements iu<is, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public long f790a;

    /* renamed from: a, reason: collision with other field name */
    public hy f791a;

    /* renamed from: a, reason: collision with other field name */
    public String f792a;

    /* renamed from: a, reason: collision with other field name */
    private BitSet f793a = new BitSet(1);

    /* renamed from: b, reason: collision with other field name */
    public String f794b;

    /* renamed from: c, reason: collision with other field name */
    public String f795c;

    /* renamed from: d, reason: collision with other field name */
    public String f796d;

    /* renamed from: e, reason: collision with other field name */
    public String f797e;

    /* renamed from: f, reason: collision with other field name */
    public String f798f;

    /* renamed from: g, reason: collision with other field name */
    public String f799g;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f789a = new jk("XmPushActionUnSubscriptionResult");
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
    public int compareTo(is isVar) {
        int iA;
        int iA2;
        int iA3;
        int iA4;
        int iA5;
        int iA6;
        int iA7;
        int iA8;
        int iA9;
        if (!getClass().equals(isVar.getClass())) {
            return getClass().getName().compareTo(isVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m521a()).compareTo(Boolean.valueOf(isVar.m521a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m521a() && (iA9 = iv.a(this.f792a, isVar.f792a)) != 0) {
            return iA9;
        }
        int iCompareTo2 = Boolean.valueOf(m523b()).compareTo(Boolean.valueOf(isVar.m523b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (m523b() && (iA8 = iv.a(this.f791a, isVar.f791a)) != 0) {
            return iA8;
        }
        int iCompareTo3 = Boolean.valueOf(m524c()).compareTo(Boolean.valueOf(isVar.m524c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (m524c() && (iA7 = iv.a(this.f794b, isVar.f794b)) != 0) {
            return iA7;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(isVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA6 = iv.a(this.f795c, isVar.f795c)) != 0) {
            return iA6;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(isVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA5 = iv.a(this.f790a, isVar.f790a)) != 0) {
            return iA5;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(isVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA4 = iv.a(this.f796d, isVar.f796d)) != 0) {
            return iA4;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(isVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA3 = iv.a(this.f797e, isVar.f797e)) != 0) {
            return iA3;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(isVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA2 = iv.a(this.f798f, isVar.f798f)) != 0) {
            return iA2;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(isVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (!i() || (iA = iv.a(this.f799g, isVar.f799g)) == 0) {
            return 0;
        }
        return iA;
    }

    public String a() {
        return this.f794b;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m520a() throws jg {
        if (this.f794b != null) {
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
            r3.m520a()
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
            r3.f799g = r0
            goto L90
        L2b:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r4.mo536a()
            r3.f798f = r0
            goto L90
        L36:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r4.mo536a()
            r3.f797e = r0
            goto L90
        L41:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r4.mo536a()
            r3.f796d = r0
            goto L90
        L4c:
            byte r1 = r0.a
            r2 = 10
            if (r1 != r2) goto L19
            long r0 = r4.mo530a()
            r3.f790a = r0
            r0 = 1
            r3.a(r0)
            goto L90
        L5d:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r4.mo536a()
            r3.f795c = r0
            goto L90
        L68:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r4.mo536a()
            r3.f794b = r0
            goto L90
        L73:
            byte r1 = r0.a
            r2 = 12
            if (r1 != r2) goto L19
            com.xiaomi.push.hy r0 = new com.xiaomi.push.hy
            r0.<init>()
            r3.f791a = r0
            com.xiaomi.push.hy r0 = r3.f791a
            r0.a(r4)
            goto L90
        L86:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r4.mo536a()
            r3.f792a = r0
        L90:
            r4.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.is.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f793a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m521a() {
        return this.f792a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m522a(is isVar) {
        if (isVar == null) {
            return false;
        }
        boolean zM521a = m521a();
        boolean zM521a2 = isVar.m521a();
        if ((zM521a || zM521a2) && !(zM521a && zM521a2 && this.f792a.equals(isVar.f792a))) {
            return false;
        }
        boolean zM523b = m523b();
        boolean zM523b2 = isVar.m523b();
        if ((zM523b || zM523b2) && !(zM523b && zM523b2 && this.f791a.m430a(isVar.f791a))) {
            return false;
        }
        boolean zM524c = m524c();
        boolean zM524c2 = isVar.m524c();
        if ((zM524c || zM524c2) && !(zM524c && zM524c2 && this.f794b.equals(isVar.f794b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = isVar.d();
        if ((zD || zD2) && !(zD && zD2 && this.f795c.equals(isVar.f795c))) {
            return false;
        }
        boolean zE = e();
        boolean zE2 = isVar.e();
        if ((zE || zE2) && !(zE && zE2 && this.f790a == isVar.f790a)) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = isVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f796d.equals(isVar.f796d))) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = isVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f797e.equals(isVar.f797e))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = isVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f798f.equals(isVar.f798f))) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = isVar.i();
        if (zI || zI2) {
            return zI && zI2 && this.f799g.equals(isVar.f799g);
        }
        return true;
    }

    public String b() {
        return this.f797e;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m520a();
        jfVar.a(f789a);
        if (this.f792a != null && m521a()) {
            jfVar.a(a);
            jfVar.a(this.f792a);
            jfVar.b();
        }
        if (this.f791a != null && m523b()) {
            jfVar.a(b);
            this.f791a.b(jfVar);
            jfVar.b();
        }
        if (this.f794b != null) {
            jfVar.a(c);
            jfVar.a(this.f794b);
            jfVar.b();
        }
        if (this.f795c != null && d()) {
            jfVar.a(d);
            jfVar.a(this.f795c);
            jfVar.b();
        }
        if (e()) {
            jfVar.a(e);
            jfVar.a(this.f790a);
            jfVar.b();
        }
        if (this.f796d != null && f()) {
            jfVar.a(f);
            jfVar.a(this.f796d);
            jfVar.b();
        }
        if (this.f797e != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f797e);
            jfVar.b();
        }
        if (this.f798f != null && h()) {
            jfVar.a(h);
            jfVar.a(this.f798f);
            jfVar.b();
        }
        if (this.f799g != null && i()) {
            jfVar.a(i);
            jfVar.a(this.f799g);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m523b() {
        return this.f791a != null;
    }

    public String c() {
        return this.f799g;
    }

    /* renamed from: c, reason: collision with other method in class */
    public boolean m524c() {
        return this.f794b != null;
    }

    public boolean d() {
        return this.f795c != null;
    }

    public boolean e() {
        return this.f793a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof is)) {
            return m522a((is) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f796d != null;
    }

    public boolean g() {
        return this.f797e != null;
    }

    public boolean h() {
        return this.f798f != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f799g != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnSubscriptionResult(");
        if (m521a()) {
            sb.append("debug:");
            sb.append(this.f792a == null ? "null" : this.f792a);
            z = false;
        } else {
            z = true;
        }
        if (m523b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f791a == null) {
                sb.append("null");
            } else {
                sb.append(this.f791a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f794b == null ? "null" : this.f794b);
        if (d()) {
            sb.append(", ");
            sb.append("appId:");
            sb.append(this.f795c == null ? "null" : this.f795c);
        }
        if (e()) {
            sb.append(", ");
            sb.append("errorCode:");
            sb.append(this.f790a);
        }
        if (f()) {
            sb.append(", ");
            sb.append("reason:");
            sb.append(this.f796d == null ? "null" : this.f796d);
        }
        if (g()) {
            sb.append(", ");
            sb.append("topic:");
            sb.append(this.f797e == null ? "null" : this.f797e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f798f == null ? "null" : this.f798f);
        }
        if (i()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.f799g == null ? "null" : this.f799g);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
