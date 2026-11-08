package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class iq implements iu<iq, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public long f770a;

    /* renamed from: a, reason: collision with other field name */
    public hy f771a;

    /* renamed from: a, reason: collision with other field name */
    public String f772a;

    /* renamed from: a, reason: collision with other field name */
    private BitSet f773a = new BitSet(3);

    /* renamed from: b, reason: collision with other field name */
    public long f774b;

    /* renamed from: b, reason: collision with other field name */
    public String f775b;

    /* renamed from: c, reason: collision with other field name */
    public long f776c;

    /* renamed from: c, reason: collision with other field name */
    public String f777c;

    /* renamed from: d, reason: collision with other field name */
    public String f778d;

    /* renamed from: e, reason: collision with other field name */
    public String f779e;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f769a = new jk("XmPushActionUnRegistrationResult");
    private static final jc a = new jc("", (byte) 11, 1);
    private static final jc b = new jc("", (byte) 12, 2);
    private static final jc c = new jc("", (byte) 11, 3);
    private static final jc d = new jc("", (byte) 11, 4);
    private static final jc e = new jc("", (byte) 10, 6);
    private static final jc f = new jc("", (byte) 11, 7);
    private static final jc g = new jc("", (byte) 11, 8);
    private static final jc h = new jc("", (byte) 10, 9);
    private static final jc i = new jc("", (byte) 10, 10);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(iq iqVar) {
        int iA;
        int iA2;
        int iA3;
        int iA4;
        int iA5;
        int iA6;
        int iA7;
        int iA8;
        int iA9;
        if (!getClass().equals(iqVar.getClass())) {
            return getClass().getName().compareTo(iqVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m516a()).compareTo(Boolean.valueOf(iqVar.m516a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m516a() && (iA9 = iv.a(this.f772a, iqVar.f772a)) != 0) {
            return iA9;
        }
        int iCompareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(iqVar.b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (b() && (iA8 = iv.a(this.f771a, iqVar.f771a)) != 0) {
            return iA8;
        }
        int iCompareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(iqVar.c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (c() && (iA7 = iv.a(this.f775b, iqVar.f775b)) != 0) {
            return iA7;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(iqVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA6 = iv.a(this.f777c, iqVar.f777c)) != 0) {
            return iA6;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(iqVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA5 = iv.a(this.f770a, iqVar.f770a)) != 0) {
            return iA5;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(iqVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA4 = iv.a(this.f778d, iqVar.f778d)) != 0) {
            return iA4;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(iqVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA3 = iv.a(this.f779e, iqVar.f779e)) != 0) {
            return iA3;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(iqVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA2 = iv.a(this.f774b, iqVar.f774b)) != 0) {
            return iA2;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(iqVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (!i() || (iA = iv.a(this.f776c, iqVar.f776c)) == 0) {
            return 0;
        }
        return iA;
    }

    public String a() {
        return this.f779e;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m515a() throws jg {
        if (this.f775b == null) {
            throw new jg("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f777c != null) {
            return;
        }
        throw new jg("Required field 'appId' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003d  */
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
            if (r1 != 0) goto L33
            r6.f()
            boolean r6 = r5.e()
            if (r6 == 0) goto L18
            r5.m515a()
            return
        L18:
            com.xiaomi.push.jg r6 = new com.xiaomi.push.jg
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'errorCode' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r5.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r6.<init>(r0)
            throw r6
        L33:
            short r1 = r0.f812a
            r2 = 1
            r3 = 10
            r4 = 11
            switch(r1) {
                case 1: goto Lad;
                case 2: goto L9a;
                case 3: goto L8f;
                case 4: goto L84;
                case 5: goto L3d;
                case 6: goto L76;
                case 7: goto L6b;
                case 8: goto L60;
                case 9: goto L52;
                case 10: goto L44;
                default: goto L3d;
            }
        L3d:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r6, r0)
            goto Lb7
        L44:
            byte r1 = r0.a
            if (r1 != r3) goto L3d
            long r0 = r6.mo530a()
            r5.f776c = r0
            r5.c(r2)
            goto Lb7
        L52:
            byte r1 = r0.a
            if (r1 != r3) goto L3d
            long r0 = r6.mo530a()
            r5.f774b = r0
            r5.b(r2)
            goto Lb7
        L60:
            byte r1 = r0.a
            if (r1 != r4) goto L3d
            java.lang.String r0 = r6.mo536a()
            r5.f779e = r0
            goto Lb7
        L6b:
            byte r1 = r0.a
            if (r1 != r4) goto L3d
            java.lang.String r0 = r6.mo536a()
            r5.f778d = r0
            goto Lb7
        L76:
            byte r1 = r0.a
            if (r1 != r3) goto L3d
            long r0 = r6.mo530a()
            r5.f770a = r0
            r5.a(r2)
            goto Lb7
        L84:
            byte r1 = r0.a
            if (r1 != r4) goto L3d
            java.lang.String r0 = r6.mo536a()
            r5.f777c = r0
            goto Lb7
        L8f:
            byte r1 = r0.a
            if (r1 != r4) goto L3d
            java.lang.String r0 = r6.mo536a()
            r5.f775b = r0
            goto Lb7
        L9a:
            byte r1 = r0.a
            r2 = 12
            if (r1 != r2) goto L3d
            com.xiaomi.push.hy r0 = new com.xiaomi.push.hy
            r0.<init>()
            r5.f771a = r0
            com.xiaomi.push.hy r0 = r5.f771a
            r0.a(r6)
            goto Lb7
        Lad:
            byte r1 = r0.a
            if (r1 != r4) goto L3d
            java.lang.String r0 = r6.mo536a()
            r5.f772a = r0
        Lb7:
            r6.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.iq.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f773a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m516a() {
        return this.f772a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m517a(iq iqVar) {
        if (iqVar == null) {
            return false;
        }
        boolean zM516a = m516a();
        boolean zM516a2 = iqVar.m516a();
        if ((zM516a || zM516a2) && !(zM516a && zM516a2 && this.f772a.equals(iqVar.f772a))) {
            return false;
        }
        boolean zB = b();
        boolean zB2 = iqVar.b();
        if ((zB || zB2) && !(zB && zB2 && this.f771a.m430a(iqVar.f771a))) {
            return false;
        }
        boolean zC = c();
        boolean zC2 = iqVar.c();
        if ((zC || zC2) && !(zC && zC2 && this.f775b.equals(iqVar.f775b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = iqVar.d();
        if (((zD || zD2) && !(zD && zD2 && this.f777c.equals(iqVar.f777c))) || this.f770a != iqVar.f770a) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = iqVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f778d.equals(iqVar.f778d))) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = iqVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f779e.equals(iqVar.f779e))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = iqVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f774b == iqVar.f774b)) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = iqVar.i();
        if (zI || zI2) {
            return zI && zI2 && this.f776c == iqVar.f776c;
        }
        return true;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m515a();
        jfVar.a(f769a);
        if (this.f772a != null && m516a()) {
            jfVar.a(a);
            jfVar.a(this.f772a);
            jfVar.b();
        }
        if (this.f771a != null && b()) {
            jfVar.a(b);
            this.f771a.b(jfVar);
            jfVar.b();
        }
        if (this.f775b != null) {
            jfVar.a(c);
            jfVar.a(this.f775b);
            jfVar.b();
        }
        if (this.f777c != null) {
            jfVar.a(d);
            jfVar.a(this.f777c);
            jfVar.b();
        }
        jfVar.a(e);
        jfVar.a(this.f770a);
        jfVar.b();
        if (this.f778d != null && f()) {
            jfVar.a(f);
            jfVar.a(this.f778d);
            jfVar.b();
        }
        if (this.f779e != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f779e);
            jfVar.b();
        }
        if (h()) {
            jfVar.a(h);
            jfVar.a(this.f774b);
            jfVar.b();
        }
        if (i()) {
            jfVar.a(i);
            jfVar.a(this.f776c);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public void b(boolean z) {
        this.f773a.set(1, z);
    }

    public boolean b() {
        return this.f771a != null;
    }

    public void c(boolean z) {
        this.f773a.set(2, z);
    }

    public boolean c() {
        return this.f775b != null;
    }

    public boolean d() {
        return this.f777c != null;
    }

    public boolean e() {
        return this.f773a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof iq)) {
            return m517a((iq) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f778d != null;
    }

    public boolean g() {
        return this.f779e != null;
    }

    public boolean h() {
        return this.f773a.get(1);
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f773a.get(2);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnRegistrationResult(");
        if (m516a()) {
            sb.append("debug:");
            sb.append(this.f772a == null ? "null" : this.f772a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f771a == null) {
                sb.append("null");
            } else {
                sb.append(this.f771a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f775b == null ? "null" : this.f775b);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f777c == null ? "null" : this.f777c);
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.f770a);
        if (f()) {
            sb.append(", ");
            sb.append("reason:");
            sb.append(this.f778d == null ? "null" : this.f778d);
        }
        if (g()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f779e == null ? "null" : this.f779e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("unRegisteredAt:");
            sb.append(this.f774b);
        }
        if (i()) {
            sb.append(", ");
            sb.append("costTime:");
            sb.append(this.f776c);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
