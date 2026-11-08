package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ib implements iu<ib, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public int f601a;

    /* renamed from: a, reason: collision with other field name */
    private BitSet f602a = new BitSet(2);

    /* renamed from: b, reason: collision with other field name */
    public int f603b;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f600a = new jk("XmPushActionCheckClientInfo");
    private static final jc a = new jc("", (byte) 8, 1);
    private static final jc b = new jc("", (byte) 8, 2);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ib ibVar) {
        int iA;
        int iA2;
        if (!getClass().equals(ibVar.getClass())) {
            return getClass().getName().compareTo(ibVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m447a()).compareTo(Boolean.valueOf(ibVar.m447a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m447a() && (iA2 = iv.a(this.f601a, ibVar.f601a)) != 0) {
            return iA2;
        }
        int iCompareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(ibVar.b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (!b() || (iA = iv.a(this.f603b, ibVar.f603b)) == 0) {
            return 0;
        }
        return iA;
    }

    public ib a(int i) {
        this.f601a = i;
        a(true);
        return this;
    }

    public void a() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005c  */
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
            if (r1 != 0) goto L54
            r5.f()
            boolean r5 = r4.m447a()
            if (r5 == 0) goto L39
            boolean r5 = r4.b()
            if (r5 == 0) goto L1e
            r4.a()
            return
        L1e:
            com.xiaomi.push.jg r5 = new com.xiaomi.push.jg
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'pluginConfigVersion' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r4.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L39:
            com.xiaomi.push.jg r5 = new com.xiaomi.push.jg
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'miscConfigVersion' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r4.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L54:
            short r1 = r0.f812a
            r2 = 1
            r3 = 8
            switch(r1) {
                case 1: goto L70;
                case 2: goto L62;
                default: goto L5c;
            }
        L5c:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r5, r0)
            goto L7d
        L62:
            byte r1 = r0.a
            if (r1 != r3) goto L5c
            int r0 = r5.mo529a()
            r4.f603b = r0
            r4.b(r2)
            goto L7d
        L70:
            byte r1 = r0.a
            if (r1 != r3) goto L5c
            int r0 = r5.mo529a()
            r4.f601a = r0
            r4.a(r2)
        L7d:
            r5.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ib.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f602a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m447a() {
        return this.f602a.get(0);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m448a(ib ibVar) {
        return ibVar != null && this.f601a == ibVar.f601a && this.f603b == ibVar.f603b;
    }

    public ib b(int i) {
        this.f603b = i;
        b(true);
        return this;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) {
        a();
        jfVar.a(f600a);
        jfVar.a(a);
        jfVar.mo540a(this.f601a);
        jfVar.b();
        jfVar.a(b);
        jfVar.mo540a(this.f603b);
        jfVar.b();
        jfVar.c();
        jfVar.mo539a();
    }

    public void b(boolean z) {
        this.f602a.set(1, z);
    }

    public boolean b() {
        return this.f602a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ib)) {
            return m448a((ib) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "XmPushActionCheckClientInfo(miscConfigVersion:" + this.f601a + ", pluginConfigVersion:" + this.f603b + com.umeng.message.proguard.l.t;
    }
}
