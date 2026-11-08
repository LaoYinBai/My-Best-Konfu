package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ie implements iu<ie, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public long f619a;

    /* renamed from: a, reason: collision with other field name */
    public hy f620a;

    /* renamed from: a, reason: collision with other field name */
    public String f621a;

    /* renamed from: a, reason: collision with other field name */
    public List<String> f623a;

    /* renamed from: b, reason: collision with other field name */
    public String f625b;

    /* renamed from: c, reason: collision with other field name */
    public String f626c;

    /* renamed from: d, reason: collision with other field name */
    public String f627d;

    /* renamed from: e, reason: collision with other field name */
    public String f628e;

    /* renamed from: f, reason: collision with other field name */
    public String f629f;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f618a = new jk("XmPushActionCommandResult");
    private static final jc a = new jc("", (byte) 12, 2);
    private static final jc b = new jc("", (byte) 11, 3);
    private static final jc c = new jc("", (byte) 11, 4);
    private static final jc d = new jc("", (byte) 11, 5);
    private static final jc e = new jc("", (byte) 10, 7);
    private static final jc f = new jc("", (byte) 11, 8);
    private static final jc g = new jc("", (byte) 11, 9);
    private static final jc h = new jc("", com.umeng.commonsdk.proguard.az.m, 10);
    private static final jc i = new jc("", (byte) 11, 12);
    private static final jc j = new jc("", (byte) 2, 13);

    /* renamed from: a, reason: collision with other field name */
    private BitSet f622a = new BitSet(2);

    /* renamed from: a, reason: collision with other field name */
    public boolean f624a = true;

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ie ieVar) {
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
        if (!getClass().equals(ieVar.getClass())) {
            return getClass().getName().compareTo(ieVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m457a()).compareTo(Boolean.valueOf(ieVar.m457a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m457a() && (iA10 = iv.a(this.f620a, ieVar.f620a)) != 0) {
            return iA10;
        }
        int iCompareTo2 = Boolean.valueOf(m459b()).compareTo(Boolean.valueOf(ieVar.m459b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (m459b() && (iA9 = iv.a(this.f621a, ieVar.f621a)) != 0) {
            return iA9;
        }
        int iCompareTo3 = Boolean.valueOf(m460c()).compareTo(Boolean.valueOf(ieVar.m460c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (m460c() && (iA8 = iv.a(this.f625b, ieVar.f625b)) != 0) {
            return iA8;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(ieVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA7 = iv.a(this.f626c, ieVar.f626c)) != 0) {
            return iA7;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(ieVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA6 = iv.a(this.f619a, ieVar.f619a)) != 0) {
            return iA6;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(ieVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA5 = iv.a(this.f627d, ieVar.f627d)) != 0) {
            return iA5;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(ieVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA4 = iv.a(this.f628e, ieVar.f628e)) != 0) {
            return iA4;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(ieVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA3 = iv.a(this.f623a, ieVar.f623a)) != 0) {
            return iA3;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(ieVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (i() && (iA2 = iv.a(this.f629f, ieVar.f629f)) != 0) {
            return iA2;
        }
        int iCompareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(ieVar.j()));
        if (iCompareTo10 != 0) {
            return iCompareTo10;
        }
        if (!j() || (iA = iv.a(this.f624a, ieVar.f624a)) == 0) {
            return 0;
        }
        return iA;
    }

    public String a() {
        return this.f621a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public List<String> m455a() {
        return this.f623a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m456a() throws jg {
        if (this.f621a == null) {
            throw new jg("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f625b == null) {
            throw new jg("Required field 'appId' was not present! Struct: " + toString());
        }
        if (this.f626c != null) {
            return;
        }
        throw new jg("Required field 'cmdName' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003b  */
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
            if (r1 != 0) goto L33
            r5.f()
            boolean r5 = r4.e()
            if (r5 == 0) goto L18
            r4.m456a()
            return
        L18:
            com.xiaomi.push.jg r5 = new com.xiaomi.push.jg
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'errorCode' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r4.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L33:
            short r1 = r0.f812a
            r2 = 1
            r3 = 11
            switch(r1) {
                case 2: goto Lcd;
                case 3: goto Lc2;
                case 4: goto Lb7;
                case 5: goto Lac;
                case 6: goto L3b;
                case 7: goto L9c;
                case 8: goto L91;
                case 9: goto L86;
                case 10: goto L5e;
                case 11: goto L3b;
                case 12: goto L52;
                case 13: goto L42;
                default: goto L3b;
            }
        L3b:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r5, r0)
            goto Ldf
        L42:
            byte r1 = r0.a
            r3 = 2
            if (r1 != r3) goto L3b
            boolean r0 = r5.mo541a()
            r4.f624a = r0
            r4.b(r2)
            goto Ldf
        L52:
            byte r1 = r0.a
            if (r1 != r3) goto L3b
            java.lang.String r0 = r5.mo536a()
            r4.f629f = r0
            goto Ldf
        L5e:
            byte r1 = r0.a
            r2 = 15
            if (r1 != r2) goto L3b
            com.xiaomi.push.jd r0 = r5.mo532a()
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.f813a
            r1.<init>(r2)
            r4.f623a = r1
            r1 = 0
        L72:
            int r2 = r0.f813a
            if (r1 >= r2) goto L82
            java.lang.String r2 = r5.mo536a()
            java.util.List<java.lang.String> r3 = r4.f623a
            r3.add(r2)
            int r1 = r1 + 1
            goto L72
        L82:
            r5.i()
            goto Ldf
        L86:
            byte r1 = r0.a
            if (r1 != r3) goto L3b
            java.lang.String r0 = r5.mo536a()
            r4.f628e = r0
            goto Ldf
        L91:
            byte r1 = r0.a
            if (r1 != r3) goto L3b
            java.lang.String r0 = r5.mo536a()
            r4.f627d = r0
            goto Ldf
        L9c:
            byte r1 = r0.a
            r3 = 10
            if (r1 != r3) goto L3b
            long r0 = r5.mo530a()
            r4.f619a = r0
            r4.a(r2)
            goto Ldf
        Lac:
            byte r1 = r0.a
            if (r1 != r3) goto L3b
            java.lang.String r0 = r5.mo536a()
            r4.f626c = r0
            goto Ldf
        Lb7:
            byte r1 = r0.a
            if (r1 != r3) goto L3b
            java.lang.String r0 = r5.mo536a()
            r4.f625b = r0
            goto Ldf
        Lc2:
            byte r1 = r0.a
            if (r1 != r3) goto L3b
            java.lang.String r0 = r5.mo536a()
            r4.f621a = r0
            goto Ldf
        Lcd:
            byte r1 = r0.a
            r2 = 12
            if (r1 != r2) goto L3b
            com.xiaomi.push.hy r0 = new com.xiaomi.push.hy
            r0.<init>()
            r4.f620a = r0
            com.xiaomi.push.hy r0 = r4.f620a
            r0.a(r5)
        Ldf:
            r5.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ie.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f622a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m457a() {
        return this.f620a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m458a(ie ieVar) {
        if (ieVar == null) {
            return false;
        }
        boolean zM457a = m457a();
        boolean zM457a2 = ieVar.m457a();
        if ((zM457a || zM457a2) && !(zM457a && zM457a2 && this.f620a.m430a(ieVar.f620a))) {
            return false;
        }
        boolean zM459b = m459b();
        boolean zM459b2 = ieVar.m459b();
        if ((zM459b || zM459b2) && !(zM459b && zM459b2 && this.f621a.equals(ieVar.f621a))) {
            return false;
        }
        boolean zM460c = m460c();
        boolean zM460c2 = ieVar.m460c();
        if ((zM460c || zM460c2) && !(zM460c && zM460c2 && this.f625b.equals(ieVar.f625b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = ieVar.d();
        if (((zD || zD2) && !(zD && zD2 && this.f626c.equals(ieVar.f626c))) || this.f619a != ieVar.f619a) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = ieVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f627d.equals(ieVar.f627d))) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = ieVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f628e.equals(ieVar.f628e))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = ieVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f623a.equals(ieVar.f623a))) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = ieVar.i();
        if ((zI || zI2) && !(zI && zI2 && this.f629f.equals(ieVar.f629f))) {
            return false;
        }
        boolean zJ = j();
        boolean zJ2 = ieVar.j();
        if (zJ || zJ2) {
            return zJ && zJ2 && this.f624a == ieVar.f624a;
        }
        return true;
    }

    public String b() {
        return this.f626c;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m456a();
        jfVar.a(f618a);
        if (this.f620a != null && m457a()) {
            jfVar.a(a);
            this.f620a.b(jfVar);
            jfVar.b();
        }
        if (this.f621a != null) {
            jfVar.a(b);
            jfVar.a(this.f621a);
            jfVar.b();
        }
        if (this.f625b != null) {
            jfVar.a(c);
            jfVar.a(this.f625b);
            jfVar.b();
        }
        if (this.f626c != null) {
            jfVar.a(d);
            jfVar.a(this.f626c);
            jfVar.b();
        }
        jfVar.a(e);
        jfVar.a(this.f619a);
        jfVar.b();
        if (this.f627d != null && f()) {
            jfVar.a(f);
            jfVar.a(this.f627d);
            jfVar.b();
        }
        if (this.f628e != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f628e);
            jfVar.b();
        }
        if (this.f623a != null && h()) {
            jfVar.a(h);
            jfVar.a(new jd((byte) 11, this.f623a.size()));
            Iterator<String> it = this.f623a.iterator();
            while (it.hasNext()) {
                jfVar.a(it.next());
            }
            jfVar.e();
            jfVar.b();
        }
        if (this.f629f != null && i()) {
            jfVar.a(i);
            jfVar.a(this.f629f);
            jfVar.b();
        }
        if (j()) {
            jfVar.a(j);
            jfVar.a(this.f624a);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public void b(boolean z) {
        this.f622a.set(1, z);
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m459b() {
        return this.f621a != null;
    }

    public String c() {
        return this.f629f;
    }

    /* renamed from: c, reason: collision with other method in class */
    public boolean m460c() {
        return this.f625b != null;
    }

    public boolean d() {
        return this.f626c != null;
    }

    public boolean e() {
        return this.f622a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ie)) {
            return m458a((ie) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f627d != null;
    }

    public boolean g() {
        return this.f628e != null;
    }

    public boolean h() {
        return this.f623a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f629f != null;
    }

    public boolean j() {
        return this.f622a.get(1);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionCommandResult(");
        if (m457a()) {
            sb.append("target:");
            if (this.f620a == null) {
                sb.append("null");
            } else {
                sb.append(this.f620a);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f621a == null ? "null" : this.f621a);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f625b == null ? "null" : this.f625b);
        sb.append(", ");
        sb.append("cmdName:");
        sb.append(this.f626c == null ? "null" : this.f626c);
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.f619a);
        if (f()) {
            sb.append(", ");
            sb.append("reason:");
            sb.append(this.f627d == null ? "null" : this.f627d);
        }
        if (g()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f628e == null ? "null" : this.f628e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("cmdArgs:");
            if (this.f623a == null) {
                sb.append("null");
            } else {
                sb.append(this.f623a);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.f629f == null ? "null" : this.f629f);
        }
        if (j()) {
            sb.append(", ");
            sb.append("response2Client:");
            sb.append(this.f624a);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
