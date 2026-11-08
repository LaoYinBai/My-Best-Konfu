package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class hs implements iu<hs, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public int f504a;

    /* renamed from: a, reason: collision with other field name */
    public hp f505a;

    /* renamed from: a, reason: collision with other field name */
    private BitSet f506a = new BitSet(1);

    /* renamed from: a, reason: collision with other field name */
    public List<hu> f507a;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f503a = new jk("NormalConfig");
    private static final jc a = new jc("", (byte) 8, 1);
    private static final jc b = new jc("", com.umeng.commonsdk.proguard.az.m, 2);
    private static final jc c = new jc("", (byte) 8, 3);

    public int a() {
        return this.f504a;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(hs hsVar) {
        int iA;
        int iA2;
        int iA3;
        if (!getClass().equals(hsVar.getClass())) {
            return getClass().getName().compareTo(hsVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m401a()).compareTo(Boolean.valueOf(hsVar.m401a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m401a() && (iA3 = iv.a(this.f504a, hsVar.f504a)) != 0) {
            return iA3;
        }
        int iCompareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(hsVar.b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (b() && (iA2 = iv.a(this.f507a, hsVar.f507a)) != 0) {
            return iA2;
        }
        int iCompareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(hsVar.c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (!c() || (iA = iv.a(this.f505a, hsVar.f505a)) == 0) {
            return 0;
        }
        return iA;
    }

    /* renamed from: a, reason: collision with other method in class */
    public hp m399a() {
        return this.f505a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m400a() throws jg {
        if (this.f507a != null) {
            return;
        }
        throw new jg("Required field 'configItems' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003a  */
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
            boolean r5 = r4.m401a()
            if (r5 == 0) goto L18
            r4.m400a()
            return
        L18:
            com.xiaomi.push.jg r5 = new com.xiaomi.push.jg
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'version' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r4.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L33:
            short r1 = r0.f812a
            r2 = 8
            switch(r1) {
                case 1: goto L7b;
                case 2: goto L4f;
                case 3: goto L40;
                default: goto L3a;
            }
        L3a:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r5, r0)
            goto L89
        L40:
            byte r1 = r0.a
            if (r1 != r2) goto L3a
            int r0 = r5.mo529a()
            com.xiaomi.push.hp r0 = com.xiaomi.push.hp.a(r0)
            r4.f505a = r0
            goto L89
        L4f:
            byte r1 = r0.a
            r2 = 15
            if (r1 != r2) goto L3a
            com.xiaomi.push.jd r0 = r5.mo532a()
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.f813a
            r1.<init>(r2)
            r4.f507a = r1
            r1 = 0
        L63:
            int r2 = r0.f813a
            if (r1 >= r2) goto L77
            com.xiaomi.push.hu r2 = new com.xiaomi.push.hu
            r2.<init>()
            r2.a(r5)
            java.util.List<com.xiaomi.push.hu> r3 = r4.f507a
            r3.add(r2)
            int r1 = r1 + 1
            goto L63
        L77:
            r5.i()
            goto L89
        L7b:
            byte r1 = r0.a
            if (r1 != r2) goto L3a
            int r0 = r5.mo529a()
            r4.f504a = r0
            r0 = 1
            r4.a(r0)
        L89:
            r5.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hs.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f506a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m401a() {
        return this.f506a.get(0);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m402a(hs hsVar) {
        if (hsVar == null || this.f504a != hsVar.f504a) {
            return false;
        }
        boolean zB = b();
        boolean zB2 = hsVar.b();
        if ((zB || zB2) && !(zB && zB2 && this.f507a.equals(hsVar.f507a))) {
            return false;
        }
        boolean zC = c();
        boolean zC2 = hsVar.c();
        if (zC || zC2) {
            return zC && zC2 && this.f505a.equals(hsVar.f505a);
        }
        return true;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m400a();
        jfVar.a(f503a);
        jfVar.a(a);
        jfVar.mo540a(this.f504a);
        jfVar.b();
        if (this.f507a != null) {
            jfVar.a(b);
            jfVar.a(new jd((byte) 12, this.f507a.size()));
            Iterator<hu> it = this.f507a.iterator();
            while (it.hasNext()) {
                it.next().b(jfVar);
            }
            jfVar.e();
            jfVar.b();
        }
        if (this.f505a != null && c()) {
            jfVar.a(c);
            jfVar.mo540a(this.f505a.a());
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public boolean b() {
        return this.f507a != null;
    }

    public boolean c() {
        return this.f505a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof hs)) {
            return m402a((hs) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("NormalConfig(");
        sb.append("version:");
        sb.append(this.f504a);
        sb.append(", ");
        sb.append("configItems:");
        if (this.f507a == null) {
            sb.append("null");
        } else {
            sb.append(this.f507a);
        }
        if (c()) {
            sb.append(", ");
            sb.append("type:");
            if (this.f505a == null) {
                sb.append("null");
            } else {
                sb.append(this.f505a);
            }
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
