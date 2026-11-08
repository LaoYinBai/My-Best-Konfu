package com.xiaomi.push;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class in implements iu<in, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public hy f736a;

    /* renamed from: a, reason: collision with other field name */
    public String f737a;

    /* renamed from: a, reason: collision with other field name */
    public List<String> f738a;

    /* renamed from: b, reason: collision with other field name */
    public String f739b;

    /* renamed from: c, reason: collision with other field name */
    public String f740c;

    /* renamed from: d, reason: collision with other field name */
    public String f741d;

    /* renamed from: e, reason: collision with other field name */
    public String f742e;

    /* renamed from: f, reason: collision with other field name */
    public String f743f;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f735a = new jk("XmPushActionSubscription");
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
    public int compareTo(in inVar) {
        int iA;
        int iA2;
        int iA3;
        int iA4;
        int iA5;
        int iA6;
        int iA7;
        int iA8;
        if (!getClass().equals(inVar.getClass())) {
            return getClass().getName().compareTo(inVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m506a()).compareTo(Boolean.valueOf(inVar.m506a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m506a() && (iA8 = iv.a(this.f737a, inVar.f737a)) != 0) {
            return iA8;
        }
        int iCompareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(inVar.b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (b() && (iA7 = iv.a(this.f736a, inVar.f736a)) != 0) {
            return iA7;
        }
        int iCompareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(inVar.c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (c() && (iA6 = iv.a(this.f739b, inVar.f739b)) != 0) {
            return iA6;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(inVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA5 = iv.a(this.f740c, inVar.f740c)) != 0) {
            return iA5;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(inVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA4 = iv.a(this.f741d, inVar.f741d)) != 0) {
            return iA4;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(inVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA3 = iv.a(this.f742e, inVar.f742e)) != 0) {
            return iA3;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(inVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA2 = iv.a(this.f743f, inVar.f743f)) != 0) {
            return iA2;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(inVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (!h() || (iA = iv.a(this.f738a, inVar.f738a)) == 0) {
            return 0;
        }
        return iA;
    }

    public in a(String str) {
        this.f739b = str;
        return this;
    }

    public void a() throws jg {
        if (this.f739b == null) {
            throw new jg("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f740c == null) {
            throw new jg("Required field 'appId' was not present! Struct: " + toString());
        }
        if (this.f741d != null) {
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
            r4.f738a = r1
            r1 = 0
        L34:
            int r2 = r0.f813a
            if (r1 >= r2) goto L44
            java.lang.String r2 = r5.mo536a()
            java.util.List<java.lang.String> r3 = r4.f738a
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
            r4.f743f = r0
            goto L9c
        L53:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r5.mo536a()
            r4.f742e = r0
            goto L9c
        L5e:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r5.mo536a()
            r4.f741d = r0
            goto L9c
        L69:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r5.mo536a()
            r4.f740c = r0
            goto L9c
        L74:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r5.mo536a()
            r4.f739b = r0
            goto L9c
        L7f:
            byte r1 = r0.a
            r2 = 12
            if (r1 != r2) goto L19
            com.xiaomi.push.hy r0 = new com.xiaomi.push.hy
            r0.<init>()
            r4.f736a = r0
            com.xiaomi.push.hy r0 = r4.f736a
            r0.a(r5)
            goto L9c
        L92:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r5.mo536a()
            r4.f737a = r0
        L9c:
            r5.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.in.a(com.xiaomi.push.jf):void");
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m506a() {
        return this.f737a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m507a(in inVar) {
        if (inVar == null) {
            return false;
        }
        boolean zM506a = m506a();
        boolean zM506a2 = inVar.m506a();
        if ((zM506a || zM506a2) && !(zM506a && zM506a2 && this.f737a.equals(inVar.f737a))) {
            return false;
        }
        boolean zB = b();
        boolean zB2 = inVar.b();
        if ((zB || zB2) && !(zB && zB2 && this.f736a.m430a(inVar.f736a))) {
            return false;
        }
        boolean zC = c();
        boolean zC2 = inVar.c();
        if ((zC || zC2) && !(zC && zC2 && this.f739b.equals(inVar.f739b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = inVar.d();
        if ((zD || zD2) && !(zD && zD2 && this.f740c.equals(inVar.f740c))) {
            return false;
        }
        boolean zE = e();
        boolean zE2 = inVar.e();
        if ((zE || zE2) && !(zE && zE2 && this.f741d.equals(inVar.f741d))) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = inVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f742e.equals(inVar.f742e))) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = inVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f743f.equals(inVar.f743f))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = inVar.h();
        if (zH || zH2) {
            return zH && zH2 && this.f738a.equals(inVar.f738a);
        }
        return true;
    }

    public in b(String str) {
        this.f740c = str;
        return this;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        a();
        jfVar.a(f735a);
        if (this.f737a != null && m506a()) {
            jfVar.a(a);
            jfVar.a(this.f737a);
            jfVar.b();
        }
        if (this.f736a != null && b()) {
            jfVar.a(b);
            this.f736a.b(jfVar);
            jfVar.b();
        }
        if (this.f739b != null) {
            jfVar.a(c);
            jfVar.a(this.f739b);
            jfVar.b();
        }
        if (this.f740c != null) {
            jfVar.a(d);
            jfVar.a(this.f740c);
            jfVar.b();
        }
        if (this.f741d != null) {
            jfVar.a(e);
            jfVar.a(this.f741d);
            jfVar.b();
        }
        if (this.f742e != null && f()) {
            jfVar.a(f);
            jfVar.a(this.f742e);
            jfVar.b();
        }
        if (this.f743f != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f743f);
            jfVar.b();
        }
        if (this.f738a != null && h()) {
            jfVar.a(h);
            jfVar.a(new jd((byte) 11, this.f738a.size()));
            Iterator<String> it = this.f738a.iterator();
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
        return this.f736a != null;
    }

    public in c(String str) {
        this.f741d = str;
        return this;
    }

    public boolean c() {
        return this.f739b != null;
    }

    public in d(String str) {
        this.f742e = str;
        return this;
    }

    public boolean d() {
        return this.f740c != null;
    }

    public in e(String str) {
        this.f743f = str;
        return this;
    }

    public boolean e() {
        return this.f741d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof in)) {
            return m507a((in) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f742e != null;
    }

    public boolean g() {
        return this.f743f != null;
    }

    public boolean h() {
        return this.f738a != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSubscription(");
        if (m506a()) {
            sb.append("debug:");
            sb.append(this.f737a == null ? "null" : this.f737a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f736a == null) {
                sb.append("null");
            } else {
                sb.append(this.f736a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f739b == null ? "null" : this.f739b);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f740c == null ? "null" : this.f740c);
        sb.append(", ");
        sb.append("topic:");
        sb.append(this.f741d == null ? "null" : this.f741d);
        if (f()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f742e == null ? "null" : this.f742e);
        }
        if (g()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.f743f == null ? "null" : this.f743f);
        }
        if (h()) {
            sb.append(", ");
            sb.append("aliases:");
            if (this.f738a == null) {
                sb.append("null");
            } else {
                sb.append(this.f738a);
            }
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
