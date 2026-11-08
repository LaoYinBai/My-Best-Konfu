package com.xiaomi.push;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.if, reason: invalid class name */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Cif implements iu<Cif, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public hj f631a;

    /* renamed from: a, reason: collision with other field name */
    public hw f632a;

    /* renamed from: a, reason: collision with other field name */
    public hy f633a;

    /* renamed from: a, reason: collision with other field name */
    public String f634a;

    /* renamed from: a, reason: collision with other field name */
    public ByteBuffer f635a;

    /* renamed from: b, reason: collision with other field name */
    public String f638b;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f630a = new jk("XmPushActionContainer");
    private static final jc a = new jc("", (byte) 8, 1);
    private static final jc b = new jc("", (byte) 2, 2);
    private static final jc c = new jc("", (byte) 2, 3);
    private static final jc d = new jc("", (byte) 11, 4);
    private static final jc e = new jc("", (byte) 11, 5);
    private static final jc f = new jc("", (byte) 11, 6);
    private static final jc g = new jc("", (byte) 12, 7);
    private static final jc h = new jc("", (byte) 12, 8);

    /* renamed from: a, reason: collision with other field name */
    private BitSet f636a = new BitSet(2);

    /* renamed from: a, reason: collision with other field name */
    public boolean f637a = true;

    /* renamed from: b, reason: collision with other field name */
    public boolean f639b = true;

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(Cif cif) {
        int iA;
        int iA2;
        int iA3;
        int iA4;
        int iA5;
        int iA6;
        int iA7;
        int iA8;
        if (!getClass().equals(cif.getClass())) {
            return getClass().getName().compareTo(cif.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m465a()).compareTo(Boolean.valueOf(cif.m465a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m465a() && (iA8 = iv.a(this.f631a, cif.f631a)) != 0) {
            return iA8;
        }
        int iCompareTo2 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(cif.c()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (c() && (iA7 = iv.a(this.f637a, cif.f637a)) != 0) {
            return iA7;
        }
        int iCompareTo3 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(cif.d()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (d() && (iA6 = iv.a(this.f639b, cif.f639b)) != 0) {
            return iA6;
        }
        int iCompareTo4 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(cif.e()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (e() && (iA5 = iv.a(this.f635a, cif.f635a)) != 0) {
            return iA5;
        }
        int iCompareTo5 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(cif.f()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (f() && (iA4 = iv.a(this.f634a, cif.f634a)) != 0) {
            return iA4;
        }
        int iCompareTo6 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(cif.g()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (g() && (iA3 = iv.a(this.f638b, cif.f638b)) != 0) {
            return iA3;
        }
        int iCompareTo7 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(cif.h()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (h() && (iA2 = iv.a(this.f633a, cif.f633a)) != 0) {
            return iA2;
        }
        int iCompareTo8 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(cif.i()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (!i() || (iA = iv.a(this.f632a, cif.f632a)) == 0) {
            return 0;
        }
        return iA;
    }

    public hj a() {
        return this.f631a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public hw m461a() {
        return this.f632a;
    }

    public Cif a(hj hjVar) {
        this.f631a = hjVar;
        return this;
    }

    public Cif a(hw hwVar) {
        this.f632a = hwVar;
        return this;
    }

    public Cif a(hy hyVar) {
        this.f633a = hyVar;
        return this;
    }

    public Cif a(String str) {
        this.f634a = str;
        return this;
    }

    public Cif a(ByteBuffer byteBuffer) {
        this.f635a = byteBuffer;
        return this;
    }

    public Cif a(boolean z) {
        this.f637a = z;
        m464a(true);
        return this;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m462a() {
        return this.f634a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m463a() throws jg {
        if (this.f631a == null) {
            throw new jg("Required field 'action' was not present! Struct: " + toString());
        }
        if (this.f635a == null) {
            throw new jg("Required field 'pushAction' was not present! Struct: " + toString());
        }
        if (this.f633a != null) {
            return;
        }
        throw new jg("Required field 'target' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005f  */
    @Override // com.xiaomi.push.iu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.xiaomi.push.jf r7) throws com.xiaomi.push.jg {
        /*
            r6 = this;
            r7.mo535a()
        L3:
            com.xiaomi.push.jc r0 = r7.mo531a()
            byte r1 = r0.a
            if (r1 != 0) goto L54
            r7.f()
            boolean r7 = r6.c()
            if (r7 == 0) goto L39
            boolean r7 = r6.d()
            if (r7 == 0) goto L1e
            r6.m463a()
            return
        L1e:
            com.xiaomi.push.jg r7 = new com.xiaomi.push.jg
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'isRequest' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r6.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0)
            throw r7
        L39:
            com.xiaomi.push.jg r7 = new com.xiaomi.push.jg
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'encryptAction' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r6.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0)
            throw r7
        L54:
            short r1 = r0.f812a
            r2 = 1
            r3 = 12
            r4 = 2
            r5 = 11
            switch(r1) {
                case 1: goto Lc5;
                case 2: goto Lb7;
                case 3: goto La9;
                case 4: goto L9e;
                case 5: goto L93;
                case 6: goto L88;
                case 7: goto L77;
                case 8: goto L66;
                default: goto L5f;
            }
        L5f:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r7, r0)
            goto Ld5
        L66:
            byte r1 = r0.a
            if (r1 != r3) goto L5f
            com.xiaomi.push.hw r0 = new com.xiaomi.push.hw
            r0.<init>()
            r6.f632a = r0
            com.xiaomi.push.hw r0 = r6.f632a
            r0.a(r7)
            goto Ld5
        L77:
            byte r1 = r0.a
            if (r1 != r3) goto L5f
            com.xiaomi.push.hy r0 = new com.xiaomi.push.hy
            r0.<init>()
            r6.f633a = r0
            com.xiaomi.push.hy r0 = r6.f633a
            r0.a(r7)
            goto Ld5
        L88:
            byte r1 = r0.a
            if (r1 != r5) goto L5f
            java.lang.String r0 = r7.mo536a()
            r6.f638b = r0
            goto Ld5
        L93:
            byte r1 = r0.a
            if (r1 != r5) goto L5f
            java.lang.String r0 = r7.mo536a()
            r6.f634a = r0
            goto Ld5
        L9e:
            byte r1 = r0.a
            if (r1 != r5) goto L5f
            java.nio.ByteBuffer r0 = r7.mo537a()
            r6.f635a = r0
            goto Ld5
        La9:
            byte r1 = r0.a
            if (r1 != r4) goto L5f
            boolean r0 = r7.mo541a()
            r6.f639b = r0
            r6.m468b(r2)
            goto Ld5
        Lb7:
            byte r1 = r0.a
            if (r1 != r4) goto L5f
            boolean r0 = r7.mo541a()
            r6.f637a = r0
            r6.m464a(r2)
            goto Ld5
        Lc5:
            byte r1 = r0.a
            r2 = 8
            if (r1 != r2) goto L5f
            int r0 = r7.mo529a()
            com.xiaomi.push.hj r0 = com.xiaomi.push.hj.a(r0)
            r6.f631a = r0
        Ld5:
            r7.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.Cif.a(com.xiaomi.push.jf):void");
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m464a(boolean z) {
        this.f636a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m465a() {
        return this.f631a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m466a(Cif cif) {
        if (cif == null) {
            return false;
        }
        boolean zM465a = m465a();
        boolean zM465a2 = cif.m465a();
        if (((zM465a || zM465a2) && (!zM465a || !zM465a2 || !this.f631a.equals(cif.f631a))) || this.f637a != cif.f637a || this.f639b != cif.f639b) {
            return false;
        }
        boolean zE = e();
        boolean zE2 = cif.e();
        if ((zE || zE2) && !(zE && zE2 && this.f635a.equals(cif.f635a))) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = cif.f();
        if ((zF || zF2) && !(zF && zF2 && this.f634a.equals(cif.f634a))) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = cif.g();
        if ((zG || zG2) && !(zG && zG2 && this.f638b.equals(cif.f638b))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = cif.h();
        if ((zH || zH2) && !(zH && zH2 && this.f633a.m430a(cif.f633a))) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = cif.i();
        if (zI || zI2) {
            return zI && zI2 && this.f632a.m422a(cif.f632a);
        }
        return true;
    }

    /* renamed from: a, reason: collision with other method in class */
    public byte[] m467a() {
        a(iv.a(this.f635a));
        return this.f635a.array();
    }

    public Cif b(String str) {
        this.f638b = str;
        return this;
    }

    public Cif b(boolean z) {
        this.f639b = z;
        m468b(true);
        return this;
    }

    public String b() {
        return this.f638b;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m463a();
        jfVar.a(f630a);
        if (this.f631a != null) {
            jfVar.a(a);
            jfVar.mo540a(this.f631a.a());
            jfVar.b();
        }
        jfVar.a(b);
        jfVar.a(this.f637a);
        jfVar.b();
        jfVar.a(c);
        jfVar.a(this.f639b);
        jfVar.b();
        if (this.f635a != null) {
            jfVar.a(d);
            jfVar.a(this.f635a);
            jfVar.b();
        }
        if (this.f634a != null && f()) {
            jfVar.a(e);
            jfVar.a(this.f634a);
            jfVar.b();
        }
        if (this.f638b != null && g()) {
            jfVar.a(f);
            jfVar.a(this.f638b);
            jfVar.b();
        }
        if (this.f633a != null) {
            jfVar.a(g);
            this.f633a.b(jfVar);
            jfVar.b();
        }
        if (this.f632a != null && i()) {
            jfVar.a(h);
            this.f632a.b(jfVar);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    /* renamed from: b, reason: collision with other method in class */
    public void m468b(boolean z) {
        this.f636a.set(1, z);
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m469b() {
        return this.f637a;
    }

    public boolean c() {
        return this.f636a.get(0);
    }

    public boolean d() {
        return this.f636a.get(1);
    }

    public boolean e() {
        return this.f635a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Cif)) {
            return m466a((Cif) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f634a != null;
    }

    public boolean g() {
        return this.f638b != null;
    }

    public boolean h() {
        return this.f633a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f632a != null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionContainer(");
        sb.append("action:");
        if (this.f631a == null) {
            sb.append("null");
        } else {
            sb.append(this.f631a);
        }
        sb.append(", ");
        sb.append("encryptAction:");
        sb.append(this.f637a);
        sb.append(", ");
        sb.append("isRequest:");
        sb.append(this.f639b);
        sb.append(", ");
        sb.append("pushAction:");
        if (this.f635a == null) {
            sb.append("null");
        } else {
            iv.a(this.f635a, sb);
        }
        if (f()) {
            sb.append(", ");
            sb.append("appid:");
            sb.append(this.f634a == null ? "null" : this.f634a);
        }
        if (g()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f638b == null ? "null" : this.f638b);
        }
        sb.append(", ");
        sb.append("target:");
        if (this.f633a == null) {
            sb.append("null");
        } else {
            sb.append(this.f633a);
        }
        if (i()) {
            sb.append(", ");
            sb.append("metaInfo:");
            if (this.f632a == null) {
                sb.append("null");
            } else {
                sb.append(this.f632a);
            }
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
