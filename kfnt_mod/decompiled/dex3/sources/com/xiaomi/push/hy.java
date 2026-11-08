package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class hy implements iu<hy, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public String f558a;

    /* renamed from: d, reason: collision with other field name */
    public String f563d;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f556a = new jk("Target");
    private static final jc a = new jc("", (byte) 10, 1);
    private static final jc b = new jc("", (byte) 11, 2);
    private static final jc c = new jc("", (byte) 11, 3);
    private static final jc d = new jc("", (byte) 11, 4);
    private static final jc e = new jc("", (byte) 2, 5);
    private static final jc f = new jc("", (byte) 11, 7);

    /* renamed from: a, reason: collision with other field name */
    private BitSet f559a = new BitSet(2);

    /* renamed from: a, reason: collision with other field name */
    public long f557a = 5;

    /* renamed from: b, reason: collision with other field name */
    public String f561b = "xiaomi.com";

    /* renamed from: c, reason: collision with other field name */
    public String f562c = "";

    /* renamed from: a, reason: collision with other field name */
    public boolean f560a = false;

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(hy hyVar) {
        int iA;
        int iA2;
        int iA3;
        int iA4;
        int iA5;
        int iA6;
        if (!getClass().equals(hyVar.getClass())) {
            return getClass().getName().compareTo(hyVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m429a()).compareTo(Boolean.valueOf(hyVar.m429a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m429a() && (iA6 = iv.a(this.f557a, hyVar.f557a)) != 0) {
            return iA6;
        }
        int iCompareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(hyVar.b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (b() && (iA5 = iv.a(this.f558a, hyVar.f558a)) != 0) {
            return iA5;
        }
        int iCompareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(hyVar.c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (c() && (iA4 = iv.a(this.f561b, hyVar.f561b)) != 0) {
            return iA4;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(hyVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA3 = iv.a(this.f562c, hyVar.f562c)) != 0) {
            return iA3;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(hyVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA2 = iv.a(this.f560a, hyVar.f560a)) != 0) {
            return iA2;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(hyVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (!f() || (iA = iv.a(this.f563d, hyVar.f563d)) == 0) {
            return 0;
        }
        return iA;
    }

    public void a() throws jg {
        if (this.f558a != null) {
            return;
        }
        throw new jg("Required field 'userId' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003e  */
    @Override // com.xiaomi.push.iu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.xiaomi.push.jf r5) {
        /*
            r4 = this;
            r5.mo535a()
        L3:
            com.xiaomi.push.jc r0 = r5.mo531a()
            byte r1 = r0.a
            if (r1 != 0) goto L33
            r5.f()
            boolean r5 = r4.m429a()
            if (r5 == 0) goto L18
            r4.a()
            return
        L18:
            com.xiaomi.push.jg r5 = new com.xiaomi.push.jg
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'channelId' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r4.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L33:
            short r1 = r0.f812a
            r2 = 7
            r3 = 11
            if (r1 == r2) goto L84
            r2 = 1
            switch(r1) {
                case 1: goto L74;
                case 2: goto L69;
                case 3: goto L5e;
                case 4: goto L53;
                case 5: goto L44;
                default: goto L3e;
            }
        L3e:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r5, r0)
            goto L8e
        L44:
            byte r1 = r0.a
            r3 = 2
            if (r1 != r3) goto L3e
            boolean r0 = r5.mo541a()
            r4.f560a = r0
            r4.b(r2)
            goto L8e
        L53:
            byte r1 = r0.a
            if (r1 != r3) goto L3e
            java.lang.String r0 = r5.mo536a()
            r4.f562c = r0
            goto L8e
        L5e:
            byte r1 = r0.a
            if (r1 != r3) goto L3e
            java.lang.String r0 = r5.mo536a()
            r4.f561b = r0
            goto L8e
        L69:
            byte r1 = r0.a
            if (r1 != r3) goto L3e
            java.lang.String r0 = r5.mo536a()
            r4.f558a = r0
            goto L8e
        L74:
            byte r1 = r0.a
            r3 = 10
            if (r1 != r3) goto L3e
            long r0 = r5.mo530a()
            r4.f557a = r0
            r4.a(r2)
            goto L8e
        L84:
            byte r1 = r0.a
            if (r1 != r3) goto L3e
            java.lang.String r0 = r5.mo536a()
            r4.f563d = r0
        L8e:
            r5.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hy.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f559a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m429a() {
        return this.f559a.get(0);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m430a(hy hyVar) {
        if (hyVar == null || this.f557a != hyVar.f557a) {
            return false;
        }
        boolean zB = b();
        boolean zB2 = hyVar.b();
        if ((zB || zB2) && !(zB && zB2 && this.f558a.equals(hyVar.f558a))) {
            return false;
        }
        boolean zC = c();
        boolean zC2 = hyVar.c();
        if ((zC || zC2) && !(zC && zC2 && this.f561b.equals(hyVar.f561b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = hyVar.d();
        if ((zD || zD2) && !(zD && zD2 && this.f562c.equals(hyVar.f562c))) {
            return false;
        }
        boolean zE = e();
        boolean zE2 = hyVar.e();
        if ((zE || zE2) && !(zE && zE2 && this.f560a == hyVar.f560a)) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = hyVar.f();
        if (zF || zF2) {
            return zF && zF2 && this.f563d.equals(hyVar.f563d);
        }
        return true;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) {
        a();
        jfVar.a(f556a);
        jfVar.a(a);
        jfVar.a(this.f557a);
        jfVar.b();
        if (this.f558a != null) {
            jfVar.a(b);
            jfVar.a(this.f558a);
            jfVar.b();
        }
        if (this.f561b != null && c()) {
            jfVar.a(c);
            jfVar.a(this.f561b);
            jfVar.b();
        }
        if (this.f562c != null && d()) {
            jfVar.a(d);
            jfVar.a(this.f562c);
            jfVar.b();
        }
        if (e()) {
            jfVar.a(e);
            jfVar.a(this.f560a);
            jfVar.b();
        }
        if (this.f563d != null && f()) {
            jfVar.a(f);
            jfVar.a(this.f563d);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public void b(boolean z) {
        this.f559a.set(1, z);
    }

    public boolean b() {
        return this.f558a != null;
    }

    public boolean c() {
        return this.f561b != null;
    }

    public boolean d() {
        return this.f562c != null;
    }

    public boolean e() {
        return this.f559a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof hy)) {
            return m430a((hy) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f563d != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Target(");
        sb.append("channelId:");
        sb.append(this.f557a);
        sb.append(", ");
        sb.append("userId:");
        sb.append(this.f558a == null ? "null" : this.f558a);
        if (c()) {
            sb.append(", ");
            sb.append("server:");
            sb.append(this.f561b == null ? "null" : this.f561b);
        }
        if (d()) {
            sb.append(", ");
            sb.append("resource:");
            sb.append(this.f562c == null ? "null" : this.f562c);
        }
        if (e()) {
            sb.append(", ");
            sb.append("isPreview:");
            sb.append(this.f560a);
        }
        if (f()) {
            sb.append(", ");
            sb.append("token:");
            sb.append(this.f563d == null ? "null" : this.f563d);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
