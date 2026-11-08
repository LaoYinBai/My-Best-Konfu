package com.xiaomi.push;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ir implements iu<ir, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public hy f781a;

    /* renamed from: a, reason: collision with other field name */
    public String f782a;

    /* renamed from: a, reason: collision with other field name */
    public List<String> f783a;

    /* renamed from: b, reason: collision with other field name */
    public String f784b;

    /* renamed from: c, reason: collision with other field name */
    public String f785c;

    /* renamed from: d, reason: collision with other field name */
    public String f786d;

    /* renamed from: e, reason: collision with other field name */
    public String f787e;

    /* renamed from: f, reason: collision with other field name */
    public String f788f;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f780a = new jk("XmPushActionUnSubscription");
    private static final jc a = new jc("", (byte) 11, 1);
    private static final jc b = new jc("", (byte) 12, 2);
    private static final jc c = new jc("", (byte) 11, 3);
    private static final jc d = new jc("", (byte) 11, 4);
    private static final jc e = new jc("", (byte) 11, 5);
    private static final jc f = new jc("", (byte) 11, 6);
    private static final jc g = new jc("", (byte) 11, 7);
    private static final jc h = new jc("", com.umeng.commonsdk.proguard.az.m, 8);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ir irVar) {
        int iA;
        int iA2;
        int iA3;
        int iA4;
        int iA5;
        int iA6;
        int iA7;
        int iA8;
        if (!getClass().equals(irVar.getClass())) {
            return getClass().getName().compareTo(irVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m518a()).compareTo(Boolean.valueOf(irVar.m518a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m518a() && (iA8 = iv.a(this.f782a, irVar.f782a)) != 0) {
            return iA8;
        }
        int iCompareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(irVar.b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (b() && (iA7 = iv.a(this.f781a, irVar.f781a)) != 0) {
            return iA7;
        }
        int iCompareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(irVar.c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (c() && (iA6 = iv.a(this.f784b, irVar.f784b)) != 0) {
            return iA6;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(irVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA5 = iv.a(this.f785c, irVar.f785c)) != 0) {
            return iA5;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(irVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA4 = iv.a(this.f786d, irVar.f786d)) != 0) {
            return iA4;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(irVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA3 = iv.a(this.f787e, irVar.f787e)) != 0) {
            return iA3;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(irVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA2 = iv.a(this.f788f, irVar.f788f)) != 0) {
            return iA2;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(irVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (!h() || (iA = iv.a(this.f783a, irVar.f783a)) == 0) {
            return 0;
        }
        return iA;
    }

    public ir a(String str) {
        this.f784b = str;
        return this;
    }

    public void a() throws jg {
        if (this.f784b == null) {
            throw new jg("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f785c == null) {
            throw new jg("Required field 'appId' was not present! Struct: " + toString());
        }
        if (this.f786d != null) {
            return;
        }
        throw new jg("Required field 'topic' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0019  */
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
            r2 = 11
            switch(r1) {
                case 1: goto L92;
                case 2: goto L7f;
                case 3: goto L74;
                case 4: goto L69;
                case 5: goto L5e;
                case 6: goto L53;
                case 7: goto L48;
                case 8: goto L20;
                default: goto L19;
            }
        L19:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r5, r0)
            goto L9c
        L20:
            byte r1 = r0.a
            r2 = 15
            if (r1 != r2) goto L19
            com.xiaomi.push.jd r0 = r5.mo532a()
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.f813a
            r1.<init>(r2)
            r4.f783a = r1
            r1 = 0
        L34:
            int r2 = r0.f813a
            if (r1 >= r2) goto L44
            java.lang.String r2 = r5.mo536a()
            java.util.List<java.lang.String> r3 = r4.f783a
            r3.add(r2)
            int r1 = r1 + 1
            goto L34
        L44:
            r5.i()
            goto L9c
        L48:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r5.mo536a()
            r4.f788f = r0
            goto L9c
        L53:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r5.mo536a()
            r4.f787e = r0
            goto L9c
        L5e:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r5.mo536a()
            r4.f786d = r0
            goto L9c
        L69:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r5.mo536a()
            r4.f785c = r0
            goto L9c
        L74:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r5.mo536a()
            r4.f784b = r0
            goto L9c
        L7f:
            byte r1 = r0.a
            r2 = 12
            if (r1 != r2) goto L19
            com.xiaomi.push.hy r0 = new com.xiaomi.push.hy
            r0.<init>()
            r4.f781a = r0
            com.xiaomi.push.hy r0 = r4.f781a
            r0.a(r5)
            goto L9c
        L92:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r5.mo536a()
            r4.f782a = r0
        L9c:
            r5.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ir.a(com.xiaomi.push.jf):void");
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m518a() {
        return this.f782a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m519a(ir irVar) {
        if (irVar == null) {
            return false;
        }
        boolean zM518a = m518a();
        boolean zM518a2 = irVar.m518a();
        if ((zM518a || zM518a2) && !(zM518a && zM518a2 && this.f782a.equals(irVar.f782a))) {
            return false;
        }
        boolean zB = b();
        boolean zB2 = irVar.b();
        if ((zB || zB2) && !(zB && zB2 && this.f781a.m430a(irVar.f781a))) {
            return false;
        }
        boolean zC = c();
        boolean zC2 = irVar.c();
        if ((zC || zC2) && !(zC && zC2 && this.f784b.equals(irVar.f784b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = irVar.d();
        if ((zD || zD2) && !(zD && zD2 && this.f785c.equals(irVar.f785c))) {
            return false;
        }
        boolean zE = e();
        boolean zE2 = irVar.e();
        if ((zE || zE2) && !(zE && zE2 && this.f786d.equals(irVar.f786d))) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = irVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f787e.equals(irVar.f787e))) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = irVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f788f.equals(irVar.f788f))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = irVar.h();
        if (zH || zH2) {
            return zH && zH2 && this.f783a.equals(irVar.f783a);
        }
        return true;
    }

    public ir b(String str) {
        this.f785c = str;
        return this;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        a();
        jfVar.a(f780a);
        if (this.f782a != null && m518a()) {
            jfVar.a(a);
            jfVar.a(this.f782a);
            jfVar.b();
        }
        if (this.f781a != null && b()) {
            jfVar.a(b);
            this.f781a.b(jfVar);
            jfVar.b();
        }
        if (this.f784b != null) {
            jfVar.a(c);
            jfVar.a(this.f784b);
            jfVar.b();
        }
        if (this.f785c != null) {
            jfVar.a(d);
            jfVar.a(this.f785c);
            jfVar.b();
        }
        if (this.f786d != null) {
            jfVar.a(e);
            jfVar.a(this.f786d);
            jfVar.b();
        }
        if (this.f787e != null && f()) {
            jfVar.a(f);
            jfVar.a(this.f787e);
            jfVar.b();
        }
        if (this.f788f != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f788f);
            jfVar.b();
        }
        if (this.f783a != null && h()) {
            jfVar.a(h);
            jfVar.a(new jd((byte) 11, this.f783a.size()));
            Iterator<String> it = this.f783a.iterator();
            while (it.hasNext()) {
                jfVar.a(it.next());
            }
            jfVar.e();
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public boolean b() {
        return this.f781a != null;
    }

    public ir c(String str) {
        this.f786d = str;
        return this;
    }

    public boolean c() {
        return this.f784b != null;
    }

    public ir d(String str) {
        this.f787e = str;
        return this;
    }

    public boolean d() {
        return this.f785c != null;
    }

    public ir e(String str) {
        this.f788f = str;
        return this;
    }

    public boolean e() {
        return this.f786d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ir)) {
            return m519a((ir) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f787e != null;
    }

    public boolean g() {
        return this.f788f != null;
    }

    public boolean h() {
        return this.f783a != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnSubscription(");
        if (m518a()) {
            sb.append("debug:");
            sb.append(this.f782a == null ? "null" : this.f782a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f781a == null) {
                sb.append("null");
            } else {
                sb.append(this.f781a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f784b == null ? "null" : this.f784b);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f785c == null ? "null" : this.f785c);
        sb.append(", ");
        sb.append("topic:");
        sb.append(this.f786d == null ? "null" : this.f786d);
        if (f()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f787e == null ? "null" : this.f787e);
        }
        if (g()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.f788f == null ? "null" : this.f788f);
        }
        if (h()) {
            sb.append(", ");
            sb.append("aliases:");
            if (this.f783a == null) {
                sb.append("null");
            } else {
                sb.append(this.f783a);
            }
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
