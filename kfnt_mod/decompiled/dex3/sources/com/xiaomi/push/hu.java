package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class hu implements iu<hu, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public int f511a;

    /* renamed from: a, reason: collision with other field name */
    public long f512a;

    /* renamed from: a, reason: collision with other field name */
    public String f513a;

    /* renamed from: a, reason: collision with other field name */
    private BitSet f514a = new BitSet(6);

    /* renamed from: a, reason: collision with other field name */
    public boolean f515a;

    /* renamed from: b, reason: collision with other field name */
    public int f516b;

    /* renamed from: b, reason: collision with other field name */
    public boolean f517b;

    /* renamed from: c, reason: collision with other field name */
    public int f518c;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f510a = new jk("OnlineConfigItem");
    private static final jc a = new jc("", (byte) 8, 1);
    private static final jc b = new jc("", (byte) 8, 2);
    private static final jc c = new jc("", (byte) 2, 3);
    private static final jc d = new jc("", (byte) 8, 4);
    private static final jc e = new jc("", (byte) 10, 5);
    private static final jc f = new jc("", (byte) 11, 6);
    private static final jc g = new jc("", (byte) 2, 7);

    public int a() {
        return this.f511a;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(hu huVar) {
        int iA;
        int iA2;
        int iA3;
        int iA4;
        int iA5;
        int iA6;
        int iA7;
        if (!getClass().equals(huVar.getClass())) {
            return getClass().getName().compareTo(huVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m406a()).compareTo(Boolean.valueOf(huVar.m406a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m406a() && (iA7 = iv.a(this.f511a, huVar.f511a)) != 0) {
            return iA7;
        }
        int iCompareTo2 = Boolean.valueOf(m408b()).compareTo(Boolean.valueOf(huVar.m408b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (m408b() && (iA6 = iv.a(this.f516b, huVar.f516b)) != 0) {
            return iA6;
        }
        int iCompareTo3 = Boolean.valueOf(m409c()).compareTo(Boolean.valueOf(huVar.m409c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (m409c() && (iA5 = iv.a(this.f515a, huVar.f515a)) != 0) {
            return iA5;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(huVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA4 = iv.a(this.f518c, huVar.f518c)) != 0) {
            return iA4;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(huVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA3 = iv.a(this.f512a, huVar.f512a)) != 0) {
            return iA3;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(huVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA2 = iv.a(this.f513a, huVar.f513a)) != 0) {
            return iA2;
        }
        int iCompareTo7 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(huVar.h()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (!h() || (iA = iv.a(this.f517b, huVar.f517b)) == 0) {
            return 0;
        }
        return iA;
    }

    /* renamed from: a, reason: collision with other method in class */
    public long m403a() {
        return this.f512a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m404a() {
        return this.f513a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m405a() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001b  */
    @Override // com.xiaomi.push.iu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.xiaomi.push.jf r6) {
        /*
            r5 = this;
            r6.mo535a()
        L3:
            com.xiaomi.push.jc r0 = r6.mo531a()
            byte r1 = r0.a
            if (r1 != 0) goto L12
            r6.f()
            r5.m405a()
            return
        L12:
            short r1 = r0.f812a
            r2 = 2
            r3 = 8
            r4 = 1
            switch(r1) {
                case 1: goto L76;
                case 2: goto L68;
                case 3: goto L5a;
                case 4: goto L4c;
                case 5: goto L3c;
                case 6: goto L2f;
                case 7: goto L21;
                default: goto L1b;
            }
        L1b:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r6, r0)
            goto L83
        L21:
            byte r1 = r0.a
            if (r1 != r2) goto L1b
            boolean r0 = r6.mo541a()
            r5.f517b = r0
            r5.f(r4)
            goto L83
        L2f:
            byte r1 = r0.a
            r2 = 11
            if (r1 != r2) goto L1b
            java.lang.String r0 = r6.mo536a()
            r5.f513a = r0
            goto L83
        L3c:
            byte r1 = r0.a
            r2 = 10
            if (r1 != r2) goto L1b
            long r0 = r6.mo530a()
            r5.f512a = r0
            r5.e(r4)
            goto L83
        L4c:
            byte r1 = r0.a
            if (r1 != r3) goto L1b
            int r0 = r6.mo529a()
            r5.f518c = r0
            r5.d(r4)
            goto L83
        L5a:
            byte r1 = r0.a
            if (r1 != r2) goto L1b
            boolean r0 = r6.mo541a()
            r5.f515a = r0
            r5.c(r4)
            goto L83
        L68:
            byte r1 = r0.a
            if (r1 != r3) goto L1b
            int r0 = r6.mo529a()
            r5.f516b = r0
            r5.b(r4)
            goto L83
        L76:
            byte r1 = r0.a
            if (r1 != r3) goto L1b
            int r0 = r6.mo529a()
            r5.f511a = r0
            r5.a(r4)
        L83:
            r6.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hu.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f514a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m406a() {
        return this.f514a.get(0);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m407a(hu huVar) {
        if (huVar == null) {
            return false;
        }
        boolean zM406a = m406a();
        boolean zM406a2 = huVar.m406a();
        if ((zM406a || zM406a2) && !(zM406a && zM406a2 && this.f511a == huVar.f511a)) {
            return false;
        }
        boolean zM408b = m408b();
        boolean zM408b2 = huVar.m408b();
        if ((zM408b || zM408b2) && !(zM408b && zM408b2 && this.f516b == huVar.f516b)) {
            return false;
        }
        boolean zM409c = m409c();
        boolean zM409c2 = huVar.m409c();
        if ((zM409c || zM409c2) && !(zM409c && zM409c2 && this.f515a == huVar.f515a)) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = huVar.d();
        if ((zD || zD2) && !(zD && zD2 && this.f518c == huVar.f518c)) {
            return false;
        }
        boolean zE = e();
        boolean zE2 = huVar.e();
        if ((zE || zE2) && !(zE && zE2 && this.f512a == huVar.f512a)) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = huVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f513a.equals(huVar.f513a))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = huVar.h();
        if (zH || zH2) {
            return zH && zH2 && this.f517b == huVar.f517b;
        }
        return true;
    }

    public int b() {
        return this.f516b;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) {
        m405a();
        jfVar.a(f510a);
        if (m406a()) {
            jfVar.a(a);
            jfVar.mo540a(this.f511a);
            jfVar.b();
        }
        if (m408b()) {
            jfVar.a(b);
            jfVar.mo540a(this.f516b);
            jfVar.b();
        }
        if (m409c()) {
            jfVar.a(c);
            jfVar.a(this.f515a);
            jfVar.b();
        }
        if (d()) {
            jfVar.a(d);
            jfVar.mo540a(this.f518c);
            jfVar.b();
        }
        if (e()) {
            jfVar.a(e);
            jfVar.a(this.f512a);
            jfVar.b();
        }
        if (this.f513a != null && f()) {
            jfVar.a(f);
            jfVar.a(this.f513a);
            jfVar.b();
        }
        if (h()) {
            jfVar.a(g);
            jfVar.a(this.f517b);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public void b(boolean z) {
        this.f514a.set(1, z);
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m408b() {
        return this.f514a.get(1);
    }

    public int c() {
        return this.f518c;
    }

    public void c(boolean z) {
        this.f514a.set(2, z);
    }

    /* renamed from: c, reason: collision with other method in class */
    public boolean m409c() {
        return this.f514a.get(2);
    }

    public void d(boolean z) {
        this.f514a.set(3, z);
    }

    public boolean d() {
        return this.f514a.get(3);
    }

    public void e(boolean z) {
        this.f514a.set(4, z);
    }

    public boolean e() {
        return this.f514a.get(4);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof hu)) {
            return m407a((hu) obj);
        }
        return false;
    }

    public void f(boolean z) {
        this.f514a.set(5, z);
    }

    public boolean f() {
        return this.f513a != null;
    }

    public boolean g() {
        return this.f517b;
    }

    public boolean h() {
        return this.f514a.get(5);
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("OnlineConfigItem(");
        if (m406a()) {
            sb.append("key:");
            sb.append(this.f511a);
            z = false;
        } else {
            z = true;
        }
        if (m408b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("type:");
            sb.append(this.f516b);
            z = false;
        }
        if (m409c()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("clear:");
            sb.append(this.f515a);
            z = false;
        }
        if (d()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("intValue:");
            sb.append(this.f518c);
            z = false;
        }
        if (e()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("longValue:");
            sb.append(this.f512a);
            z = false;
        }
        if (f()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("stringValue:");
            sb.append(this.f513a == null ? "null" : this.f513a);
            z = false;
        }
        if (h()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("boolValue:");
            sb.append(this.f517b);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
