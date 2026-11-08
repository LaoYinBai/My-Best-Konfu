package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class id implements iu<id, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public long f607a;

    /* renamed from: a, reason: collision with other field name */
    public hy f608a;

    /* renamed from: a, reason: collision with other field name */
    public String f609a;

    /* renamed from: a, reason: collision with other field name */
    public List<String> f611a;

    /* renamed from: b, reason: collision with other field name */
    public String f613b;

    /* renamed from: c, reason: collision with other field name */
    public String f615c;

    /* renamed from: d, reason: collision with other field name */
    public String f616d;

    /* renamed from: e, reason: collision with other field name */
    public String f617e;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f606a = new jk("XmPushActionCommand");
    private static final jc a = new jc("", (byte) 12, 2);
    private static final jc b = new jc("", (byte) 11, 3);
    private static final jc c = new jc("", (byte) 11, 4);
    private static final jc d = new jc("", (byte) 11, 5);
    private static final jc e = new jc("", com.umeng.commonsdk.proguard.az.m, 6);
    private static final jc f = new jc("", (byte) 11, 7);
    private static final jc g = new jc("", (byte) 11, 9);
    private static final jc h = new jc("", (byte) 2, 10);
    private static final jc i = new jc("", (byte) 2, 11);
    private static final jc j = new jc("", (byte) 10, 12);

    /* renamed from: a, reason: collision with other field name */
    private BitSet f610a = new BitSet(3);

    /* renamed from: a, reason: collision with other field name */
    public boolean f612a = false;

    /* renamed from: b, reason: collision with other field name */
    public boolean f614b = true;

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(id idVar) {
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
        if (!getClass().equals(idVar.getClass())) {
            return getClass().getName().compareTo(idVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m453a()).compareTo(Boolean.valueOf(idVar.m453a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m453a() && (iA10 = iv.a(this.f608a, idVar.f608a)) != 0) {
            return iA10;
        }
        int iCompareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(idVar.b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (b() && (iA9 = iv.a(this.f609a, idVar.f609a)) != 0) {
            return iA9;
        }
        int iCompareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(idVar.c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (c() && (iA8 = iv.a(this.f613b, idVar.f613b)) != 0) {
            return iA8;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(idVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA7 = iv.a(this.f615c, idVar.f615c)) != 0) {
            return iA7;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(idVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA6 = iv.a(this.f611a, idVar.f611a)) != 0) {
            return iA6;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(idVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA5 = iv.a(this.f616d, idVar.f616d)) != 0) {
            return iA5;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(idVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA4 = iv.a(this.f617e, idVar.f617e)) != 0) {
            return iA4;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(idVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA3 = iv.a(this.f612a, idVar.f612a)) != 0) {
            return iA3;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(idVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (i() && (iA2 = iv.a(this.f614b, idVar.f614b)) != 0) {
            return iA2;
        }
        int iCompareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(idVar.j()));
        if (iCompareTo10 != 0) {
            return iCompareTo10;
        }
        if (!j() || (iA = iv.a(this.f607a, idVar.f607a)) == 0) {
            return 0;
        }
        return iA;
    }

    public id a(String str) {
        this.f609a = str;
        return this;
    }

    public id a(List<String> list) {
        this.f611a = list;
        return this;
    }

    public String a() {
        return this.f615c;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m451a() throws jg {
        if (this.f609a == null) {
            throw new jg("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f613b == null) {
            throw new jg("Required field 'appId' was not present! Struct: " + toString());
        }
        if (this.f615c != null) {
            return;
        }
        throw new jg("Required field 'cmdName' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001b  */
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
            r5.m451a()
            return
        L12:
            short r1 = r0.f812a
            r2 = 2
            r3 = 1
            r4 = 11
            switch(r1) {
                case 2: goto Lb0;
                case 3: goto La5;
                case 4: goto L9a;
                case 5: goto L8f;
                case 6: goto L67;
                case 7: goto L5c;
                case 8: goto L1b;
                case 9: goto L51;
                case 10: goto L42;
                case 11: goto L33;
                case 12: goto L22;
                default: goto L1b;
            }
        L1b:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r6, r0)
            goto Lc2
        L22:
            byte r1 = r0.a
            r2 = 10
            if (r1 != r2) goto L1b
            long r0 = r6.mo530a()
            r5.f607a = r0
            r5.c(r3)
            goto Lc2
        L33:
            byte r1 = r0.a
            if (r1 != r2) goto L1b
            boolean r0 = r6.mo541a()
            r5.f614b = r0
            r5.b(r3)
            goto Lc2
        L42:
            byte r1 = r0.a
            if (r1 != r2) goto L1b
            boolean r0 = r6.mo541a()
            r5.f612a = r0
            r5.a(r3)
            goto Lc2
        L51:
            byte r1 = r0.a
            if (r1 != r4) goto L1b
            java.lang.String r0 = r6.mo536a()
            r5.f617e = r0
            goto Lc2
        L5c:
            byte r1 = r0.a
            if (r1 != r4) goto L1b
            java.lang.String r0 = r6.mo536a()
            r5.f616d = r0
            goto Lc2
        L67:
            byte r1 = r0.a
            r2 = 15
            if (r1 != r2) goto L1b
            com.xiaomi.push.jd r0 = r6.mo532a()
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.f813a
            r1.<init>(r2)
            r5.f611a = r1
            r1 = 0
        L7b:
            int r2 = r0.f813a
            if (r1 >= r2) goto L8b
            java.lang.String r2 = r6.mo536a()
            java.util.List<java.lang.String> r3 = r5.f611a
            r3.add(r2)
            int r1 = r1 + 1
            goto L7b
        L8b:
            r6.i()
            goto Lc2
        L8f:
            byte r1 = r0.a
            if (r1 != r4) goto L1b
            java.lang.String r0 = r6.mo536a()
            r5.f615c = r0
            goto Lc2
        L9a:
            byte r1 = r0.a
            if (r1 != r4) goto L1b
            java.lang.String r0 = r6.mo536a()
            r5.f613b = r0
            goto Lc2
        La5:
            byte r1 = r0.a
            if (r1 != r4) goto L1b
            java.lang.String r0 = r6.mo536a()
            r5.f609a = r0
            goto Lc2
        Lb0:
            byte r1 = r0.a
            r2 = 12
            if (r1 != r2) goto L1b
            com.xiaomi.push.hy r0 = new com.xiaomi.push.hy
            r0.<init>()
            r5.f608a = r0
            com.xiaomi.push.hy r0 = r5.f608a
            r0.a(r6)
        Lc2:
            r6.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.id.a(com.xiaomi.push.jf):void");
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m452a(String str) {
        if (this.f611a == null) {
            this.f611a = new ArrayList();
        }
        this.f611a.add(str);
    }

    public void a(boolean z) {
        this.f610a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m453a() {
        return this.f608a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m454a(id idVar) {
        if (idVar == null) {
            return false;
        }
        boolean zM453a = m453a();
        boolean zM453a2 = idVar.m453a();
        if ((zM453a || zM453a2) && !(zM453a && zM453a2 && this.f608a.m430a(idVar.f608a))) {
            return false;
        }
        boolean zB = b();
        boolean zB2 = idVar.b();
        if ((zB || zB2) && !(zB && zB2 && this.f609a.equals(idVar.f609a))) {
            return false;
        }
        boolean zC = c();
        boolean zC2 = idVar.c();
        if ((zC || zC2) && !(zC && zC2 && this.f613b.equals(idVar.f613b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = idVar.d();
        if ((zD || zD2) && !(zD && zD2 && this.f615c.equals(idVar.f615c))) {
            return false;
        }
        boolean zE = e();
        boolean zE2 = idVar.e();
        if ((zE || zE2) && !(zE && zE2 && this.f611a.equals(idVar.f611a))) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = idVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f616d.equals(idVar.f616d))) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = idVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f617e.equals(idVar.f617e))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = idVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f612a == idVar.f612a)) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = idVar.i();
        if ((zI || zI2) && !(zI && zI2 && this.f614b == idVar.f614b)) {
            return false;
        }
        boolean zJ = j();
        boolean zJ2 = idVar.j();
        if (zJ || zJ2) {
            return zJ && zJ2 && this.f607a == idVar.f607a;
        }
        return true;
    }

    public id b(String str) {
        this.f613b = str;
        return this;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m451a();
        jfVar.a(f606a);
        if (this.f608a != null && m453a()) {
            jfVar.a(a);
            this.f608a.b(jfVar);
            jfVar.b();
        }
        if (this.f609a != null) {
            jfVar.a(b);
            jfVar.a(this.f609a);
            jfVar.b();
        }
        if (this.f613b != null) {
            jfVar.a(c);
            jfVar.a(this.f613b);
            jfVar.b();
        }
        if (this.f615c != null) {
            jfVar.a(d);
            jfVar.a(this.f615c);
            jfVar.b();
        }
        if (this.f611a != null && e()) {
            jfVar.a(e);
            jfVar.a(new jd((byte) 11, this.f611a.size()));
            Iterator<String> it = this.f611a.iterator();
            while (it.hasNext()) {
                jfVar.a(it.next());
            }
            jfVar.e();
            jfVar.b();
        }
        if (this.f616d != null && f()) {
            jfVar.a(f);
            jfVar.a(this.f616d);
            jfVar.b();
        }
        if (this.f617e != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f617e);
            jfVar.b();
        }
        if (h()) {
            jfVar.a(h);
            jfVar.a(this.f612a);
            jfVar.b();
        }
        if (i()) {
            jfVar.a(i);
            jfVar.a(this.f614b);
            jfVar.b();
        }
        if (j()) {
            jfVar.a(j);
            jfVar.a(this.f607a);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public void b(boolean z) {
        this.f610a.set(1, z);
    }

    public boolean b() {
        return this.f609a != null;
    }

    public id c(String str) {
        this.f615c = str;
        return this;
    }

    public void c(boolean z) {
        this.f610a.set(2, z);
    }

    public boolean c() {
        return this.f613b != null;
    }

    public id d(String str) {
        this.f616d = str;
        return this;
    }

    public boolean d() {
        return this.f615c != null;
    }

    public id e(String str) {
        this.f617e = str;
        return this;
    }

    public boolean e() {
        return this.f611a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof id)) {
            return m454a((id) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f616d != null;
    }

    public boolean g() {
        return this.f617e != null;
    }

    public boolean h() {
        return this.f610a.get(0);
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f610a.get(1);
    }

    public boolean j() {
        return this.f610a.get(2);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionCommand(");
        if (m453a()) {
            sb.append("target:");
            if (this.f608a == null) {
                sb.append("null");
            } else {
                sb.append(this.f608a);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f609a == null ? "null" : this.f609a);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f613b == null ? "null" : this.f613b);
        sb.append(", ");
        sb.append("cmdName:");
        sb.append(this.f615c == null ? "null" : this.f615c);
        if (e()) {
            sb.append(", ");
            sb.append("cmdArgs:");
            if (this.f611a == null) {
                sb.append("null");
            } else {
                sb.append(this.f611a);
            }
        }
        if (f()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f616d == null ? "null" : this.f616d);
        }
        if (g()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.f617e == null ? "null" : this.f617e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("updateCache:");
            sb.append(this.f612a);
        }
        if (i()) {
            sb.append(", ");
            sb.append("response2Client:");
            sb.append(this.f614b);
        }
        if (j()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f607a);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
