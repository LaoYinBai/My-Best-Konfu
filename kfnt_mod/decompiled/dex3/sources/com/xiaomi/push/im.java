package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class im implements iu<im, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public hv f722a;

    /* renamed from: a, reason: collision with other field name */
    public hy f723a;

    /* renamed from: a, reason: collision with other field name */
    public String f724a;

    /* renamed from: a, reason: collision with other field name */
    public Map<String, String> f726a;

    /* renamed from: b, reason: collision with other field name */
    public String f728b;

    /* renamed from: c, reason: collision with other field name */
    public String f729c;

    /* renamed from: d, reason: collision with other field name */
    public String f730d;

    /* renamed from: e, reason: collision with other field name */
    public String f731e;

    /* renamed from: f, reason: collision with other field name */
    public String f732f;

    /* renamed from: g, reason: collision with other field name */
    public String f733g;

    /* renamed from: h, reason: collision with other field name */
    public String f734h;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f721a = new jk("XmPushActionSendMessage");
    private static final jc a = new jc("", (byte) 11, 1);
    private static final jc b = new jc("", (byte) 12, 2);
    private static final jc c = new jc("", (byte) 11, 3);
    private static final jc d = new jc("", (byte) 11, 4);
    private static final jc e = new jc("", (byte) 11, 5);
    private static final jc f = new jc("", (byte) 11, 6);
    private static final jc g = new jc("", (byte) 11, 7);
    private static final jc h = new jc("", (byte) 12, 8);
    private static final jc i = new jc("", (byte) 2, 9);
    private static final jc j = new jc("", com.umeng.commonsdk.proguard.az.k, 10);
    private static final jc k = new jc("", (byte) 11, 11);
    private static final jc l = new jc("", (byte) 11, 12);

    /* renamed from: a, reason: collision with other field name */
    private BitSet f725a = new BitSet(1);

    /* renamed from: a, reason: collision with other field name */
    public boolean f727a = true;

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(im imVar) {
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
        if (!getClass().equals(imVar.getClass())) {
            return getClass().getName().compareTo(imVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m499a()).compareTo(Boolean.valueOf(imVar.m499a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m499a() && (iA12 = iv.a(this.f724a, imVar.f724a)) != 0) {
            return iA12;
        }
        int iCompareTo2 = Boolean.valueOf(m501b()).compareTo(Boolean.valueOf(imVar.m501b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (m501b() && (iA11 = iv.a(this.f723a, imVar.f723a)) != 0) {
            return iA11;
        }
        int iCompareTo3 = Boolean.valueOf(m502c()).compareTo(Boolean.valueOf(imVar.m502c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (m502c() && (iA10 = iv.a(this.f728b, imVar.f728b)) != 0) {
            return iA10;
        }
        int iCompareTo4 = Boolean.valueOf(m503d()).compareTo(Boolean.valueOf(imVar.m503d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (m503d() && (iA9 = iv.a(this.f729c, imVar.f729c)) != 0) {
            return iA9;
        }
        int iCompareTo5 = Boolean.valueOf(m504e()).compareTo(Boolean.valueOf(imVar.m504e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (m504e() && (iA8 = iv.a(this.f730d, imVar.f730d)) != 0) {
            return iA8;
        }
        int iCompareTo6 = Boolean.valueOf(m505f()).compareTo(Boolean.valueOf(imVar.m505f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (m505f() && (iA7 = iv.a(this.f731e, imVar.f731e)) != 0) {
            return iA7;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(imVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA6 = iv.a(this.f732f, imVar.f732f)) != 0) {
            return iA6;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(imVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA5 = iv.a(this.f722a, imVar.f722a)) != 0) {
            return iA5;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(imVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (i() && (iA4 = iv.a(this.f727a, imVar.f727a)) != 0) {
            return iA4;
        }
        int iCompareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(imVar.j()));
        if (iCompareTo10 != 0) {
            return iCompareTo10;
        }
        if (j() && (iA3 = iv.a(this.f726a, imVar.f726a)) != 0) {
            return iA3;
        }
        int iCompareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(imVar.k()));
        if (iCompareTo11 != 0) {
            return iCompareTo11;
        }
        if (k() && (iA2 = iv.a(this.f733g, imVar.f733g)) != 0) {
            return iA2;
        }
        int iCompareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(imVar.l()));
        if (iCompareTo12 != 0) {
            return iCompareTo12;
        }
        if (!l() || (iA = iv.a(this.f734h, imVar.f734h)) == 0) {
            return 0;
        }
        return iA;
    }

    public hv a() {
        return this.f722a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m497a() {
        return this.f728b;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m498a() throws jg {
        if (this.f728b == null) {
            throw new jg("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f729c != null) {
            return;
        }
        throw new jg("Required field 'appId' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001c  */
    @Override // com.xiaomi.push.iu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.xiaomi.push.jf r6) throws com.xiaomi.push.jg {
        /*
            r5 = this;
            r6.mo535a()
        L3:
            com.xiaomi.push.jc r0 = r6.mo531a()
            byte r1 = r0.a
            if (r1 != 0) goto L12
            r6.f()
            r5.m498a()
            return
        L12:
            short r1 = r0.f812a
            r2 = 2
            r3 = 12
            r4 = 11
            switch(r1) {
                case 1: goto Ld2;
                case 2: goto Lc1;
                case 3: goto Lb6;
                case 4: goto Lab;
                case 5: goto La0;
                case 6: goto L95;
                case 7: goto L8a;
                case 8: goto L79;
                case 9: goto L6a;
                case 10: goto L3b;
                case 11: goto L2f;
                case 12: goto L23;
                default: goto L1c;
            }
        L1c:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r6, r0)
            goto Ldc
        L23:
            byte r1 = r0.a
            if (r1 != r4) goto L1c
            java.lang.String r0 = r6.mo536a()
            r5.f734h = r0
            goto Ldc
        L2f:
            byte r1 = r0.a
            if (r1 != r4) goto L1c
            java.lang.String r0 = r6.mo536a()
            r5.f733g = r0
            goto Ldc
        L3b:
            byte r1 = r0.a
            r3 = 13
            if (r1 != r3) goto L1c
            com.xiaomi.push.je r0 = r6.mo533a()
            java.util.HashMap r1 = new java.util.HashMap
            int r3 = r0.f814a
            int r3 = r3 * 2
            r1.<init>(r3)
            r5.f726a = r1
            r1 = 0
        L51:
            int r2 = r0.f814a
            if (r1 >= r2) goto L65
            java.lang.String r2 = r6.mo536a()
            java.lang.String r3 = r6.mo536a()
            java.util.Map<java.lang.String, java.lang.String> r4 = r5.f726a
            r4.put(r2, r3)
            int r1 = r1 + 1
            goto L51
        L65:
            r6.h()
            goto Ldc
        L6a:
            byte r1 = r0.a
            if (r1 != r2) goto L1c
            boolean r0 = r6.mo541a()
            r5.f727a = r0
            r0 = 1
            r5.a(r0)
            goto Ldc
        L79:
            byte r1 = r0.a
            if (r1 != r3) goto L1c
            com.xiaomi.push.hv r0 = new com.xiaomi.push.hv
            r0.<init>()
            r5.f722a = r0
            com.xiaomi.push.hv r0 = r5.f722a
            r0.a(r6)
            goto Ldc
        L8a:
            byte r1 = r0.a
            if (r1 != r4) goto L1c
            java.lang.String r0 = r6.mo536a()
            r5.f732f = r0
            goto Ldc
        L95:
            byte r1 = r0.a
            if (r1 != r4) goto L1c
            java.lang.String r0 = r6.mo536a()
            r5.f731e = r0
            goto Ldc
        La0:
            byte r1 = r0.a
            if (r1 != r4) goto L1c
            java.lang.String r0 = r6.mo536a()
            r5.f730d = r0
            goto Ldc
        Lab:
            byte r1 = r0.a
            if (r1 != r4) goto L1c
            java.lang.String r0 = r6.mo536a()
            r5.f729c = r0
            goto Ldc
        Lb6:
            byte r1 = r0.a
            if (r1 != r4) goto L1c
            java.lang.String r0 = r6.mo536a()
            r5.f728b = r0
            goto Ldc
        Lc1:
            byte r1 = r0.a
            if (r1 != r3) goto L1c
            com.xiaomi.push.hy r0 = new com.xiaomi.push.hy
            r0.<init>()
            r5.f723a = r0
            com.xiaomi.push.hy r0 = r5.f723a
            r0.a(r6)
            goto Ldc
        Ld2:
            byte r1 = r0.a
            if (r1 != r4) goto L1c
            java.lang.String r0 = r6.mo536a()
            r5.f724a = r0
        Ldc:
            r6.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.im.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f725a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m499a() {
        return this.f724a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m500a(im imVar) {
        if (imVar == null) {
            return false;
        }
        boolean zM499a = m499a();
        boolean zM499a2 = imVar.m499a();
        if ((zM499a || zM499a2) && !(zM499a && zM499a2 && this.f724a.equals(imVar.f724a))) {
            return false;
        }
        boolean zM501b = m501b();
        boolean zM501b2 = imVar.m501b();
        if ((zM501b || zM501b2) && !(zM501b && zM501b2 && this.f723a.m430a(imVar.f723a))) {
            return false;
        }
        boolean zM502c = m502c();
        boolean zM502c2 = imVar.m502c();
        if ((zM502c || zM502c2) && !(zM502c && zM502c2 && this.f728b.equals(imVar.f728b))) {
            return false;
        }
        boolean zM503d = m503d();
        boolean zM503d2 = imVar.m503d();
        if ((zM503d || zM503d2) && !(zM503d && zM503d2 && this.f729c.equals(imVar.f729c))) {
            return false;
        }
        boolean zM504e = m504e();
        boolean zM504e2 = imVar.m504e();
        if ((zM504e || zM504e2) && !(zM504e && zM504e2 && this.f730d.equals(imVar.f730d))) {
            return false;
        }
        boolean zM505f = m505f();
        boolean zM505f2 = imVar.m505f();
        if ((zM505f || zM505f2) && !(zM505f && zM505f2 && this.f731e.equals(imVar.f731e))) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = imVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f732f.equals(imVar.f732f))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = imVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f722a.m413a(imVar.f722a))) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = imVar.i();
        if ((zI || zI2) && !(zI && zI2 && this.f727a == imVar.f727a)) {
            return false;
        }
        boolean zJ = j();
        boolean zJ2 = imVar.j();
        if ((zJ || zJ2) && !(zJ && zJ2 && this.f726a.equals(imVar.f726a))) {
            return false;
        }
        boolean zK = k();
        boolean zK2 = imVar.k();
        if ((zK || zK2) && !(zK && zK2 && this.f733g.equals(imVar.f733g))) {
            return false;
        }
        boolean zL = l();
        boolean zL2 = imVar.l();
        if (zL || zL2) {
            return zL && zL2 && this.f734h.equals(imVar.f734h);
        }
        return true;
    }

    public String b() {
        return this.f729c;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m498a();
        jfVar.a(f721a);
        if (this.f724a != null && m499a()) {
            jfVar.a(a);
            jfVar.a(this.f724a);
            jfVar.b();
        }
        if (this.f723a != null && m501b()) {
            jfVar.a(b);
            this.f723a.b(jfVar);
            jfVar.b();
        }
        if (this.f728b != null) {
            jfVar.a(c);
            jfVar.a(this.f728b);
            jfVar.b();
        }
        if (this.f729c != null) {
            jfVar.a(d);
            jfVar.a(this.f729c);
            jfVar.b();
        }
        if (this.f730d != null && m504e()) {
            jfVar.a(e);
            jfVar.a(this.f730d);
            jfVar.b();
        }
        if (this.f731e != null && m505f()) {
            jfVar.a(f);
            jfVar.a(this.f731e);
            jfVar.b();
        }
        if (this.f732f != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f732f);
            jfVar.b();
        }
        if (this.f722a != null && h()) {
            jfVar.a(h);
            this.f722a.b(jfVar);
            jfVar.b();
        }
        if (i()) {
            jfVar.a(i);
            jfVar.a(this.f727a);
            jfVar.b();
        }
        if (this.f726a != null && j()) {
            jfVar.a(j);
            jfVar.a(new je((byte) 11, (byte) 11, this.f726a.size()));
            for (Map.Entry<String, String> entry : this.f726a.entrySet()) {
                jfVar.a(entry.getKey());
                jfVar.a(entry.getValue());
            }
            jfVar.d();
            jfVar.b();
        }
        if (this.f733g != null && k()) {
            jfVar.a(k);
            jfVar.a(this.f733g);
            jfVar.b();
        }
        if (this.f734h != null && l()) {
            jfVar.a(l);
            jfVar.a(this.f734h);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m501b() {
        return this.f723a != null;
    }

    public String c() {
        return this.f731e;
    }

    /* renamed from: c, reason: collision with other method in class */
    public boolean m502c() {
        return this.f728b != null;
    }

    public String d() {
        return this.f732f;
    }

    /* renamed from: d, reason: collision with other method in class */
    public boolean m503d() {
        return this.f729c != null;
    }

    public String e() {
        return this.f733g;
    }

    /* renamed from: e, reason: collision with other method in class */
    public boolean m504e() {
        return this.f730d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof im)) {
            return m500a((im) obj);
        }
        return false;
    }

    public String f() {
        return this.f734h;
    }

    /* renamed from: f, reason: collision with other method in class */
    public boolean m505f() {
        return this.f731e != null;
    }

    public boolean g() {
        return this.f732f != null;
    }

    public boolean h() {
        return this.f722a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f725a.get(0);
    }

    public boolean j() {
        return this.f726a != null;
    }

    public boolean k() {
        return this.f733g != null;
    }

    public boolean l() {
        return this.f734h != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSendMessage(");
        if (m499a()) {
            sb.append("debug:");
            sb.append(this.f724a == null ? "null" : this.f724a);
            z = false;
        } else {
            z = true;
        }
        if (m501b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f723a == null) {
                sb.append("null");
            } else {
                sb.append(this.f723a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f728b == null ? "null" : this.f728b);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f729c == null ? "null" : this.f729c);
        if (m504e()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f730d == null ? "null" : this.f730d);
        }
        if (m505f()) {
            sb.append(", ");
            sb.append("topic:");
            sb.append(this.f731e == null ? "null" : this.f731e);
        }
        if (g()) {
            sb.append(", ");
            sb.append("aliasName:");
            sb.append(this.f732f == null ? "null" : this.f732f);
        }
        if (h()) {
            sb.append(", ");
            sb.append("message:");
            if (this.f722a == null) {
                sb.append("null");
            } else {
                sb.append(this.f722a);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("needAck:");
            sb.append(this.f727a);
        }
        if (j()) {
            sb.append(", ");
            sb.append("params:");
            if (this.f726a == null) {
                sb.append("null");
            } else {
                sb.append(this.f726a);
            }
        }
        if (k()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.f733g == null ? "null" : this.f733g);
        }
        if (l()) {
            sb.append(", ");
            sb.append("userAccount:");
            sb.append(this.f734h == null ? "null" : this.f734h);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
