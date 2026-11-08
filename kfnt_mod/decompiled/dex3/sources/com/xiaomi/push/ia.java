package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ia implements iu<ia, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public hy f590a;

    /* renamed from: a, reason: collision with other field name */
    public String f591a;

    /* renamed from: a, reason: collision with other field name */
    public Map<String, String> f593a;

    /* renamed from: b, reason: collision with other field name */
    public String f594b;

    /* renamed from: c, reason: collision with other field name */
    public String f595c;

    /* renamed from: d, reason: collision with other field name */
    public String f596d;

    /* renamed from: e, reason: collision with other field name */
    public String f597e;

    /* renamed from: f, reason: collision with other field name */
    public String f598f;

    /* renamed from: g, reason: collision with other field name */
    public String f599g;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f588a = new jk("XmPushActionAckNotification");
    private static final jc a = new jc("", (byte) 11, 1);
    private static final jc b = new jc("", (byte) 12, 2);
    private static final jc c = new jc("", (byte) 11, 3);
    private static final jc d = new jc("", (byte) 11, 4);
    private static final jc e = new jc("", (byte) 11, 5);
    private static final jc f = new jc("", (byte) 10, 7);
    private static final jc g = new jc("", (byte) 11, 8);
    private static final jc h = new jc("", com.umeng.commonsdk.proguard.az.k, 9);
    private static final jc i = new jc("", (byte) 11, 10);
    private static final jc j = new jc("", (byte) 11, 11);

    /* renamed from: a, reason: collision with other field name */
    private BitSet f592a = new BitSet(1);

    /* renamed from: a, reason: collision with other field name */
    public long f589a = 0;

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ia iaVar) {
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
        if (!getClass().equals(iaVar.getClass())) {
            return getClass().getName().compareTo(iaVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m444a()).compareTo(Boolean.valueOf(iaVar.m444a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m444a() && (iA10 = iv.a(this.f591a, iaVar.f591a)) != 0) {
            return iA10;
        }
        int iCompareTo2 = Boolean.valueOf(m446b()).compareTo(Boolean.valueOf(iaVar.m446b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (m446b() && (iA9 = iv.a(this.f590a, iaVar.f590a)) != 0) {
            return iA9;
        }
        int iCompareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(iaVar.c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (c() && (iA8 = iv.a(this.f594b, iaVar.f594b)) != 0) {
            return iA8;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(iaVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA7 = iv.a(this.f595c, iaVar.f595c)) != 0) {
            return iA7;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(iaVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA6 = iv.a(this.f596d, iaVar.f596d)) != 0) {
            return iA6;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(iaVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA5 = iv.a(this.f589a, iaVar.f589a)) != 0) {
            return iA5;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(iaVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA4 = iv.a(this.f597e, iaVar.f597e)) != 0) {
            return iA4;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(iaVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA3 = iv.a(this.f593a, iaVar.f593a)) != 0) {
            return iA3;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(iaVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (i() && (iA2 = iv.a(this.f598f, iaVar.f598f)) != 0) {
            return iA2;
        }
        int iCompareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(iaVar.j()));
        if (iCompareTo10 != 0) {
            return iCompareTo10;
        }
        if (!j() || (iA = iv.a(this.f599g, iaVar.f599g)) == 0) {
            return 0;
        }
        return iA;
    }

    public ia a(long j2) {
        this.f589a = j2;
        a(true);
        return this;
    }

    public ia a(hy hyVar) {
        this.f590a = hyVar;
        return this;
    }

    public ia a(String str) {
        this.f594b = str;
        return this;
    }

    public String a() {
        return this.f594b;
    }

    /* renamed from: a, reason: collision with other method in class */
    public Map<String, String> m442a() {
        return this.f593a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m443a() throws jg {
        if (this.f594b != null) {
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
    public void a(com.xiaomi.push.jf r6) throws com.xiaomi.push.jg {
        /*
            r5 = this;
            r6.mo535a()
        L3:
            com.xiaomi.push.jc r0 = r6.mo531a()
            byte r1 = r0.a
            if (r1 != 0) goto L12
            r6.f()
            r5.m443a()
            return
        L12:
            short r1 = r0.f812a
            r2 = 11
            switch(r1) {
                case 1: goto Lb6;
                case 2: goto La3;
                case 3: goto L98;
                case 4: goto L8d;
                case 5: goto L82;
                case 6: goto L19;
                case 7: goto L71;
                case 8: goto L66;
                case 9: goto L38;
                case 10: goto L2c;
                case 11: goto L20;
                default: goto L19;
            }
        L19:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r6, r0)
            goto Lc0
        L20:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r6.mo536a()
            r5.f599g = r0
            goto Lc0
        L2c:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r6.mo536a()
            r5.f598f = r0
            goto Lc0
        L38:
            byte r1 = r0.a
            r2 = 13
            if (r1 != r2) goto L19
            com.xiaomi.push.je r0 = r6.mo533a()
            java.util.HashMap r1 = new java.util.HashMap
            int r2 = r0.f814a
            int r2 = r2 * 2
            r1.<init>(r2)
            r5.f593a = r1
            r1 = 0
        L4e:
            int r2 = r0.f814a
            if (r1 >= r2) goto L62
            java.lang.String r2 = r6.mo536a()
            java.lang.String r3 = r6.mo536a()
            java.util.Map<java.lang.String, java.lang.String> r4 = r5.f593a
            r4.put(r2, r3)
            int r1 = r1 + 1
            goto L4e
        L62:
            r6.h()
            goto Lc0
        L66:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r6.mo536a()
            r5.f597e = r0
            goto Lc0
        L71:
            byte r1 = r0.a
            r2 = 10
            if (r1 != r2) goto L19
            long r0 = r6.mo530a()
            r5.f589a = r0
            r0 = 1
            r5.a(r0)
            goto Lc0
        L82:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r6.mo536a()
            r5.f596d = r0
            goto Lc0
        L8d:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r6.mo536a()
            r5.f595c = r0
            goto Lc0
        L98:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r6.mo536a()
            r5.f594b = r0
            goto Lc0
        La3:
            byte r1 = r0.a
            r2 = 12
            if (r1 != r2) goto L19
            com.xiaomi.push.hy r0 = new com.xiaomi.push.hy
            r0.<init>()
            r5.f590a = r0
            com.xiaomi.push.hy r0 = r5.f590a
            r0.a(r6)
            goto Lc0
        Lb6:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r6.mo536a()
            r5.f591a = r0
        Lc0:
            r6.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ia.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f592a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m444a() {
        return this.f591a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m445a(ia iaVar) {
        if (iaVar == null) {
            return false;
        }
        boolean zM444a = m444a();
        boolean zM444a2 = iaVar.m444a();
        if ((zM444a || zM444a2) && !(zM444a && zM444a2 && this.f591a.equals(iaVar.f591a))) {
            return false;
        }
        boolean zM446b = m446b();
        boolean zM446b2 = iaVar.m446b();
        if ((zM446b || zM446b2) && !(zM446b && zM446b2 && this.f590a.m430a(iaVar.f590a))) {
            return false;
        }
        boolean zC = c();
        boolean zC2 = iaVar.c();
        if ((zC || zC2) && !(zC && zC2 && this.f594b.equals(iaVar.f594b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = iaVar.d();
        if ((zD || zD2) && !(zD && zD2 && this.f595c.equals(iaVar.f595c))) {
            return false;
        }
        boolean zE = e();
        boolean zE2 = iaVar.e();
        if ((zE || zE2) && !(zE && zE2 && this.f596d.equals(iaVar.f596d))) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = iaVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f589a == iaVar.f589a)) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = iaVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f597e.equals(iaVar.f597e))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = iaVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f593a.equals(iaVar.f593a))) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = iaVar.i();
        if ((zI || zI2) && !(zI && zI2 && this.f598f.equals(iaVar.f598f))) {
            return false;
        }
        boolean zJ = j();
        boolean zJ2 = iaVar.j();
        if (zJ || zJ2) {
            return zJ && zJ2 && this.f599g.equals(iaVar.f599g);
        }
        return true;
    }

    public ia b(String str) {
        this.f595c = str;
        return this;
    }

    public String b() {
        return this.f596d;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m443a();
        jfVar.a(f588a);
        if (this.f591a != null && m444a()) {
            jfVar.a(a);
            jfVar.a(this.f591a);
            jfVar.b();
        }
        if (this.f590a != null && m446b()) {
            jfVar.a(b);
            this.f590a.b(jfVar);
            jfVar.b();
        }
        if (this.f594b != null) {
            jfVar.a(c);
            jfVar.a(this.f594b);
            jfVar.b();
        }
        if (this.f595c != null && d()) {
            jfVar.a(d);
            jfVar.a(this.f595c);
            jfVar.b();
        }
        if (this.f596d != null && e()) {
            jfVar.a(e);
            jfVar.a(this.f596d);
            jfVar.b();
        }
        if (f()) {
            jfVar.a(f);
            jfVar.a(this.f589a);
            jfVar.b();
        }
        if (this.f597e != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f597e);
            jfVar.b();
        }
        if (this.f593a != null && h()) {
            jfVar.a(h);
            jfVar.a(new je((byte) 11, (byte) 11, this.f593a.size()));
            for (Map.Entry<String, String> entry : this.f593a.entrySet()) {
                jfVar.a(entry.getKey());
                jfVar.a(entry.getValue());
            }
            jfVar.d();
            jfVar.b();
        }
        if (this.f598f != null && i()) {
            jfVar.a(i);
            jfVar.a(this.f598f);
            jfVar.b();
        }
        if (this.f599g != null && j()) {
            jfVar.a(j);
            jfVar.a(this.f599g);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m446b() {
        return this.f590a != null;
    }

    public ia c(String str) {
        this.f596d = str;
        return this;
    }

    public boolean c() {
        return this.f594b != null;
    }

    public ia d(String str) {
        this.f597e = str;
        return this;
    }

    public boolean d() {
        return this.f595c != null;
    }

    public ia e(String str) {
        this.f598f = str;
        return this;
    }

    public boolean e() {
        return this.f596d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ia)) {
            return m445a((ia) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f592a.get(0);
    }

    public boolean g() {
        return this.f597e != null;
    }

    public boolean h() {
        return this.f593a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f598f != null;
    }

    public boolean j() {
        return this.f599g != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionAckNotification(");
        if (m444a()) {
            sb.append("debug:");
            sb.append(this.f591a == null ? "null" : this.f591a);
            z = false;
        } else {
            z = true;
        }
        if (m446b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f590a == null) {
                sb.append("null");
            } else {
                sb.append(this.f590a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f594b == null ? "null" : this.f594b);
        if (d()) {
            sb.append(", ");
            sb.append("appId:");
            sb.append(this.f595c == null ? "null" : this.f595c);
        }
        if (e()) {
            sb.append(", ");
            sb.append("type:");
            sb.append(this.f596d == null ? "null" : this.f596d);
        }
        if (f()) {
            sb.append(", ");
            sb.append("errorCode:");
            sb.append(this.f589a);
        }
        if (g()) {
            sb.append(", ");
            sb.append("reason:");
            sb.append(this.f597e == null ? "null" : this.f597e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("extra:");
            if (this.f593a == null) {
                sb.append("null");
            } else {
                sb.append(this.f593a);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f598f == null ? "null" : this.f598f);
        }
        if (j()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.f599g == null ? "null" : this.f599g);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
