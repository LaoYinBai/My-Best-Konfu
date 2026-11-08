package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class il implements iu<il, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public long f713a;

    /* renamed from: a, reason: collision with other field name */
    public hy f714a;

    /* renamed from: a, reason: collision with other field name */
    public String f715a;

    /* renamed from: a, reason: collision with other field name */
    private BitSet f716a = new BitSet(1);

    /* renamed from: b, reason: collision with other field name */
    public String f717b;

    /* renamed from: c, reason: collision with other field name */
    public String f718c;

    /* renamed from: d, reason: collision with other field name */
    public String f719d;

    /* renamed from: e, reason: collision with other field name */
    public String f720e;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f712a = new jk("XmPushActionSendFeedbackResult");
    private static final jc a = new jc("", (byte) 11, 1);
    private static final jc b = new jc("", (byte) 12, 2);
    private static final jc c = new jc("", (byte) 11, 3);
    private static final jc d = new jc("", (byte) 11, 4);
    private static final jc e = new jc("", (byte) 10, 6);
    private static final jc f = new jc("", (byte) 11, 7);
    private static final jc g = new jc("", (byte) 11, 8);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(il ilVar) {
        int iA;
        int iA2;
        int iA3;
        int iA4;
        int iA5;
        int iA6;
        int iA7;
        if (!getClass().equals(ilVar.getClass())) {
            return getClass().getName().compareTo(ilVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m495a()).compareTo(Boolean.valueOf(ilVar.m495a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m495a() && (iA7 = iv.a(this.f715a, ilVar.f715a)) != 0) {
            return iA7;
        }
        int iCompareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(ilVar.b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (b() && (iA6 = iv.a(this.f714a, ilVar.f714a)) != 0) {
            return iA6;
        }
        int iCompareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(ilVar.c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (c() && (iA5 = iv.a(this.f717b, ilVar.f717b)) != 0) {
            return iA5;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(ilVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA4 = iv.a(this.f718c, ilVar.f718c)) != 0) {
            return iA4;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(ilVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA3 = iv.a(this.f713a, ilVar.f713a)) != 0) {
            return iA3;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(ilVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA2 = iv.a(this.f719d, ilVar.f719d)) != 0) {
            return iA2;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(ilVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (!g() || (iA = iv.a(this.f720e, ilVar.f720e)) == 0) {
            return 0;
        }
        return iA;
    }

    public void a() throws jg {
        if (this.f717b == null) {
            throw new jg("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f718c != null) {
            return;
        }
        throw new jg("Required field 'appId' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003a  */
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
            if (r1 != 0) goto L33
            r4.f()
            boolean r4 = r3.e()
            if (r4 == 0) goto L18
            r3.a()
            return
        L18:
            com.xiaomi.push.jg r4 = new com.xiaomi.push.jg
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'errorCode' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r3.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4.<init>(r0)
            throw r4
        L33:
            short r1 = r0.f812a
            r2 = 11
            switch(r1) {
                case 1: goto L90;
                case 2: goto L7d;
                case 3: goto L72;
                case 4: goto L67;
                case 5: goto L3a;
                case 6: goto L56;
                case 7: goto L4b;
                case 8: goto L40;
                default: goto L3a;
            }
        L3a:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r4, r0)
            goto L9a
        L40:
            byte r1 = r0.a
            if (r1 != r2) goto L3a
            java.lang.String r0 = r4.mo536a()
            r3.f720e = r0
            goto L9a
        L4b:
            byte r1 = r0.a
            if (r1 != r2) goto L3a
            java.lang.String r0 = r4.mo536a()
            r3.f719d = r0
            goto L9a
        L56:
            byte r1 = r0.a
            r2 = 10
            if (r1 != r2) goto L3a
            long r0 = r4.mo530a()
            r3.f713a = r0
            r0 = 1
            r3.a(r0)
            goto L9a
        L67:
            byte r1 = r0.a
            if (r1 != r2) goto L3a
            java.lang.String r0 = r4.mo536a()
            r3.f718c = r0
            goto L9a
        L72:
            byte r1 = r0.a
            if (r1 != r2) goto L3a
            java.lang.String r0 = r4.mo536a()
            r3.f717b = r0
            goto L9a
        L7d:
            byte r1 = r0.a
            r2 = 12
            if (r1 != r2) goto L3a
            com.xiaomi.push.hy r0 = new com.xiaomi.push.hy
            r0.<init>()
            r3.f714a = r0
            com.xiaomi.push.hy r0 = r3.f714a
            r0.a(r4)
            goto L9a
        L90:
            byte r1 = r0.a
            if (r1 != r2) goto L3a
            java.lang.String r0 = r4.mo536a()
            r3.f715a = r0
        L9a:
            r4.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.il.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f716a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m495a() {
        return this.f715a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m496a(il ilVar) {
        if (ilVar == null) {
            return false;
        }
        boolean zM495a = m495a();
        boolean zM495a2 = ilVar.m495a();
        if ((zM495a || zM495a2) && !(zM495a && zM495a2 && this.f715a.equals(ilVar.f715a))) {
            return false;
        }
        boolean zB = b();
        boolean zB2 = ilVar.b();
        if ((zB || zB2) && !(zB && zB2 && this.f714a.m430a(ilVar.f714a))) {
            return false;
        }
        boolean zC = c();
        boolean zC2 = ilVar.c();
        if ((zC || zC2) && !(zC && zC2 && this.f717b.equals(ilVar.f717b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = ilVar.d();
        if (((zD || zD2) && !(zD && zD2 && this.f718c.equals(ilVar.f718c))) || this.f713a != ilVar.f713a) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = ilVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f719d.equals(ilVar.f719d))) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = ilVar.g();
        if (zG || zG2) {
            return zG && zG2 && this.f720e.equals(ilVar.f720e);
        }
        return true;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        a();
        jfVar.a(f712a);
        if (this.f715a != null && m495a()) {
            jfVar.a(a);
            jfVar.a(this.f715a);
            jfVar.b();
        }
        if (this.f714a != null && b()) {
            jfVar.a(b);
            this.f714a.b(jfVar);
            jfVar.b();
        }
        if (this.f717b != null) {
            jfVar.a(c);
            jfVar.a(this.f717b);
            jfVar.b();
        }
        if (this.f718c != null) {
            jfVar.a(d);
            jfVar.a(this.f718c);
            jfVar.b();
        }
        jfVar.a(e);
        jfVar.a(this.f713a);
        jfVar.b();
        if (this.f719d != null && f()) {
            jfVar.a(f);
            jfVar.a(this.f719d);
            jfVar.b();
        }
        if (this.f720e != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f720e);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public boolean b() {
        return this.f714a != null;
    }

    public boolean c() {
        return this.f717b != null;
    }

    public boolean d() {
        return this.f718c != null;
    }

    public boolean e() {
        return this.f716a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof il)) {
            return m496a((il) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f719d != null;
    }

    public boolean g() {
        return this.f720e != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSendFeedbackResult(");
        if (m495a()) {
            sb.append("debug:");
            sb.append(this.f715a == null ? "null" : this.f715a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f714a == null) {
                sb.append("null");
            } else {
                sb.append(this.f714a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f717b == null ? "null" : this.f717b);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f718c == null ? "null" : this.f718c);
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.f713a);
        if (f()) {
            sb.append(", ");
            sb.append("reason:");
            sb.append(this.f719d == null ? "null" : this.f719d);
        }
        if (g()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.f720e == null ? "null" : this.f720e);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
