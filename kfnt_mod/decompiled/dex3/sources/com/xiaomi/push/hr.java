package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class hr implements iu<hr, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public long f499a;

    /* renamed from: a, reason: collision with other field name */
    public hl f500a;

    /* renamed from: a, reason: collision with other field name */
    public String f501a;

    /* renamed from: a, reason: collision with other field name */
    private BitSet f502a = new BitSet(1);

    /* renamed from: a, reason: collision with other field name */
    private static final jk f498a = new jk("DataCollectionItem");
    private static final jc a = new jc("", (byte) 10, 1);
    private static final jc b = new jc("", (byte) 8, 2);
    private static final jc c = new jc("", (byte) 11, 3);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(hr hrVar) {
        int iA;
        int iA2;
        int iA3;
        if (!getClass().equals(hrVar.getClass())) {
            return getClass().getName().compareTo(hrVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m397a()).compareTo(Boolean.valueOf(hrVar.m397a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m397a() && (iA3 = iv.a(this.f499a, hrVar.f499a)) != 0) {
            return iA3;
        }
        int iCompareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(hrVar.b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (b() && (iA2 = iv.a(this.f500a, hrVar.f500a)) != 0) {
            return iA2;
        }
        int iCompareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(hrVar.c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (!c() || (iA = iv.a(this.f501a, hrVar.f501a)) == 0) {
            return 0;
        }
        return iA;
    }

    public hr a(long j) {
        this.f499a = j;
        a(true);
        return this;
    }

    public hr a(hl hlVar) {
        this.f500a = hlVar;
        return this;
    }

    public hr a(String str) {
        this.f501a = str;
        return this;
    }

    public String a() {
        return this.f501a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m396a() throws jg {
        if (this.f500a == null) {
            throw new jg("Required field 'collectionType' was not present! Struct: " + toString());
        }
        if (this.f501a != null) {
            return;
        }
        throw new jg("Required field 'content' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0038  */
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
            boolean r4 = r3.m397a()
            if (r4 == 0) goto L18
            r3.m396a()
            return
        L18:
            com.xiaomi.push.jg r4 = new com.xiaomi.push.jg
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'collectedAt' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r3.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4.<init>(r0)
            throw r4
        L33:
            short r1 = r0.f812a
            switch(r1) {
                case 1: goto L5c;
                case 2: goto L4b;
                case 3: goto L3e;
                default: goto L38;
            }
        L38:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r4, r0)
            goto L6c
        L3e:
            byte r1 = r0.a
            r2 = 11
            if (r1 != r2) goto L38
            java.lang.String r0 = r4.mo536a()
            r3.f501a = r0
            goto L6c
        L4b:
            byte r1 = r0.a
            r2 = 8
            if (r1 != r2) goto L38
            int r0 = r4.mo529a()
            com.xiaomi.push.hl r0 = com.xiaomi.push.hl.a(r0)
            r3.f500a = r0
            goto L6c
        L5c:
            byte r1 = r0.a
            r2 = 10
            if (r1 != r2) goto L38
            long r0 = r4.mo530a()
            r3.f499a = r0
            r0 = 1
            r3.a(r0)
        L6c:
            r4.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hr.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f502a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m397a() {
        return this.f502a.get(0);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m398a(hr hrVar) {
        if (hrVar == null || this.f499a != hrVar.f499a) {
            return false;
        }
        boolean zB = b();
        boolean zB2 = hrVar.b();
        if ((zB || zB2) && !(zB && zB2 && this.f500a.equals(hrVar.f500a))) {
            return false;
        }
        boolean zC = c();
        boolean zC2 = hrVar.c();
        if (zC || zC2) {
            return zC && zC2 && this.f501a.equals(hrVar.f501a);
        }
        return true;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m396a();
        jfVar.a(f498a);
        jfVar.a(a);
        jfVar.a(this.f499a);
        jfVar.b();
        if (this.f500a != null) {
            jfVar.a(b);
            jfVar.mo540a(this.f500a.a());
            jfVar.b();
        }
        if (this.f501a != null) {
            jfVar.a(c);
            jfVar.a(this.f501a);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public boolean b() {
        return this.f500a != null;
    }

    public boolean c() {
        return this.f501a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof hr)) {
            return m398a((hr) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataCollectionItem(");
        sb.append("collectedAt:");
        sb.append(this.f499a);
        sb.append(", ");
        sb.append("collectionType:");
        if (this.f500a == null) {
            sb.append("null");
        } else {
            sb.append(this.f500a);
        }
        sb.append(", ");
        sb.append("content:");
        sb.append(this.f501a == null ? "null" : this.f501a);
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
