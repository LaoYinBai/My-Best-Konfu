package com.xiaomi.push;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class fe implements iu<fe, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public String f363a;

    /* renamed from: a, reason: collision with other field name */
    public List<fd> f364a;

    /* renamed from: b, reason: collision with other field name */
    public String f365b;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f362a = new jk("StatsEvents");
    private static final jc a = new jc("", (byte) 11, 1);
    private static final jc b = new jc("", (byte) 11, 2);
    private static final jc c = new jc("", com.umeng.commonsdk.proguard.az.m, 3);

    public fe() {
    }

    public fe(String str, List<fd> list) {
        this();
        this.f363a = str;
        this.f364a = list;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(fe feVar) {
        int iA;
        int iA2;
        int iA3;
        if (!getClass().equals(feVar.getClass())) {
            return getClass().getName().compareTo(feVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m310a()).compareTo(Boolean.valueOf(feVar.m310a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m310a() && (iA3 = iv.a(this.f363a, feVar.f363a)) != 0) {
            return iA3;
        }
        int iCompareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(feVar.b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (b() && (iA2 = iv.a(this.f365b, feVar.f365b)) != 0) {
            return iA2;
        }
        int iCompareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(feVar.c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (!c() || (iA = iv.a(this.f364a, feVar.f364a)) == 0) {
            return 0;
        }
        return iA;
    }

    public fe a(String str) {
        this.f365b = str;
        return this;
    }

    public void a() throws jg {
        if (this.f363a == null) {
            throw new jg("Required field 'uuid' was not present! Struct: " + toString());
        }
        if (this.f364a != null) {
            return;
        }
        throw new jg("Required field 'events' was not present! Struct: " + toString());
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
                case 1: goto L56;
                case 2: goto L4b;
                case 3: goto L1f;
                default: goto L19;
            }
        L19:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r5, r0)
            goto L60
        L1f:
            byte r1 = r0.a
            r2 = 15
            if (r1 != r2) goto L19
            com.xiaomi.push.jd r0 = r5.mo532a()
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.f813a
            r1.<init>(r2)
            r4.f364a = r1
            r1 = 0
        L33:
            int r2 = r0.f813a
            if (r1 >= r2) goto L47
            com.xiaomi.push.fd r2 = new com.xiaomi.push.fd
            r2.<init>()
            r2.a(r5)
            java.util.List<com.xiaomi.push.fd> r3 = r4.f364a
            r3.add(r2)
            int r1 = r1 + 1
            goto L33
        L47:
            r5.i()
            goto L60
        L4b:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r5.mo536a()
            r4.f365b = r0
            goto L60
        L56:
            byte r1 = r0.a
            if (r1 != r2) goto L19
            java.lang.String r0 = r5.mo536a()
            r4.f363a = r0
        L60:
            r5.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.fe.a(com.xiaomi.push.jf):void");
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m310a() {
        return this.f363a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m311a(fe feVar) {
        if (feVar == null) {
            return false;
        }
        boolean zM310a = m310a();
        boolean zM310a2 = feVar.m310a();
        if ((zM310a || zM310a2) && !(zM310a && zM310a2 && this.f363a.equals(feVar.f363a))) {
            return false;
        }
        boolean zB = b();
        boolean zB2 = feVar.b();
        if ((zB || zB2) && !(zB && zB2 && this.f365b.equals(feVar.f365b))) {
            return false;
        }
        boolean zC = c();
        boolean zC2 = feVar.c();
        if (zC || zC2) {
            return zC && zC2 && this.f364a.equals(feVar.f364a);
        }
        return true;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) {
        a();
        jfVar.a(f362a);
        if (this.f363a != null) {
            jfVar.a(a);
            jfVar.a(this.f363a);
            jfVar.b();
        }
        if (this.f365b != null && b()) {
            jfVar.a(b);
            jfVar.a(this.f365b);
            jfVar.b();
        }
        if (this.f364a != null) {
            jfVar.a(c);
            jfVar.a(new jd((byte) 12, this.f364a.size()));
            Iterator<fd> it = this.f364a.iterator();
            while (it.hasNext()) {
                it.next().b(jfVar);
            }
            jfVar.e();
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public boolean b() {
        return this.f365b != null;
    }

    public boolean c() {
        return this.f364a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof fe)) {
            return m311a((fe) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("StatsEvents(");
        sb.append("uuid:");
        sb.append(this.f363a == null ? "null" : this.f363a);
        if (b()) {
            sb.append(", ");
            sb.append("operator:");
            sb.append(this.f365b == null ? "null" : this.f365b);
        }
        sb.append(", ");
        sb.append("events:");
        if (this.f364a == null) {
            sb.append("null");
        } else {
            sb.append(this.f364a);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
