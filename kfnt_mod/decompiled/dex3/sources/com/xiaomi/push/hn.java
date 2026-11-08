package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class hn implements iu<hn, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public long f480a;

    /* renamed from: a, reason: collision with other field name */
    public String f481a;

    /* renamed from: a, reason: collision with other field name */
    private BitSet f482a = new BitSet(3);

    /* renamed from: a, reason: collision with other field name */
    public Map<String, String> f483a;

    /* renamed from: a, reason: collision with other field name */
    public boolean f484a;

    /* renamed from: b, reason: collision with other field name */
    public long f485b;

    /* renamed from: b, reason: collision with other field name */
    public String f486b;

    /* renamed from: c, reason: collision with other field name */
    public String f487c;

    /* renamed from: d, reason: collision with other field name */
    public String f488d;

    /* renamed from: e, reason: collision with other field name */
    public String f489e;

    /* renamed from: f, reason: collision with other field name */
    public String f490f;

    /* renamed from: g, reason: collision with other field name */
    public String f491g;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f479a = new jk("ClientUploadDataItem");
    private static final jc a = new jc("", (byte) 11, 1);
    private static final jc b = new jc("", (byte) 11, 2);
    private static final jc c = new jc("", (byte) 11, 3);
    private static final jc d = new jc("", (byte) 10, 4);
    private static final jc e = new jc("", (byte) 10, 5);
    private static final jc f = new jc("", (byte) 2, 6);
    private static final jc g = new jc("", (byte) 11, 7);
    private static final jc h = new jc("", (byte) 11, 8);
    private static final jc i = new jc("", (byte) 11, 9);
    private static final jc j = new jc("", com.umeng.commonsdk.proguard.az.k, 10);
    private static final jc k = new jc("", (byte) 11, 11);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(hn hnVar) {
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
        int iA11;
        if (!getClass().equals(hnVar.getClass())) {
            return getClass().getName().compareTo(hnVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m390a()).compareTo(Boolean.valueOf(hnVar.m390a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m390a() && (iA11 = iv.a(this.f481a, hnVar.f481a)) != 0) {
            return iA11;
        }
        int iCompareTo2 = Boolean.valueOf(m392b()).compareTo(Boolean.valueOf(hnVar.m392b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (m392b() && (iA10 = iv.a(this.f486b, hnVar.f486b)) != 0) {
            return iA10;
        }
        int iCompareTo3 = Boolean.valueOf(m393c()).compareTo(Boolean.valueOf(hnVar.m393c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (m393c() && (iA9 = iv.a(this.f487c, hnVar.f487c)) != 0) {
            return iA9;
        }
        int iCompareTo4 = Boolean.valueOf(m394d()).compareTo(Boolean.valueOf(hnVar.m394d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (m394d() && (iA8 = iv.a(this.f480a, hnVar.f480a)) != 0) {
            return iA8;
        }
        int iCompareTo5 = Boolean.valueOf(m395e()).compareTo(Boolean.valueOf(hnVar.m395e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (m395e() && (iA7 = iv.a(this.f485b, hnVar.f485b)) != 0) {
            return iA7;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(hnVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA6 = iv.a(this.f484a, hnVar.f484a)) != 0) {
            return iA6;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(hnVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA5 = iv.a(this.f488d, hnVar.f488d)) != 0) {
            return iA5;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(hnVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA4 = iv.a(this.f489e, hnVar.f489e)) != 0) {
            return iA4;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(hnVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (i() && (iA3 = iv.a(this.f490f, hnVar.f490f)) != 0) {
            return iA3;
        }
        int iCompareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(hnVar.j()));
        if (iCompareTo10 != 0) {
            return iCompareTo10;
        }
        if (j() && (iA2 = iv.a(this.f483a, hnVar.f483a)) != 0) {
            return iA2;
        }
        int iCompareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(hnVar.k()));
        if (iCompareTo11 != 0) {
            return iCompareTo11;
        }
        if (!k() || (iA = iv.a(this.f491g, hnVar.f491g)) == 0) {
            return 0;
        }
        return iA;
    }

    public long a() {
        return this.f485b;
    }

    public hn a(long j2) {
        this.f480a = j2;
        m389a(true);
        return this;
    }

    public hn a(String str) {
        this.f481a = str;
        return this;
    }

    public hn a(Map<String, String> map) {
        this.f483a = map;
        return this;
    }

    public hn a(boolean z) {
        this.f484a = z;
        c(true);
        return this;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m386a() {
        return this.f481a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public Map<String, String> m387a() {
        return this.f483a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m388a() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001d  */
    @Override // com.xiaomi.push.iu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.xiaomi.push.jf r7) {
        /*
            r6 = this;
            r7.mo535a()
        L3:
            com.xiaomi.push.jc r0 = r7.mo531a()
            byte r1 = r0.a
            if (r1 != 0) goto L12
            r7.f()
            r6.m388a()
            return
        L12:
            short r1 = r0.f812a
            r2 = 2
            r3 = 10
            r4 = 1
            r5 = 11
            switch(r1) {
                case 1: goto Lc0;
                case 2: goto Lb5;
                case 3: goto Laa;
                case 4: goto L9c;
                case 5: goto L8e;
                case 6: goto L80;
                case 7: goto L75;
                case 8: goto L6a;
                case 9: goto L5f;
                case 10: goto L30;
                case 11: goto L24;
                default: goto L1d;
            }
        L1d:
            byte r0 = r0.a
            com.xiaomi.push.ji.a(r7, r0)
            goto Lca
        L24:
            byte r1 = r0.a
            if (r1 != r5) goto L1d
            java.lang.String r0 = r7.mo536a()
            r6.f491g = r0
            goto Lca
        L30:
            byte r1 = r0.a
            r3 = 13
            if (r1 != r3) goto L1d
            com.xiaomi.push.je r0 = r7.mo533a()
            java.util.HashMap r1 = new java.util.HashMap
            int r3 = r0.f814a
            int r3 = r3 * 2
            r1.<init>(r3)
            r6.f483a = r1
            r1 = 0
        L46:
            int r2 = r0.f814a
            if (r1 >= r2) goto L5a
            java.lang.String r2 = r7.mo536a()
            java.lang.String r3 = r7.mo536a()
            java.util.Map<java.lang.String, java.lang.String> r4 = r6.f483a
            r4.put(r2, r3)
            int r1 = r1 + 1
            goto L46
        L5a:
            r7.h()
            goto Lca
        L5f:
            byte r1 = r0.a
            if (r1 != r5) goto L1d
            java.lang.String r0 = r7.mo536a()
            r6.f490f = r0
            goto Lca
        L6a:
            byte r1 = r0.a
            if (r1 != r5) goto L1d
            java.lang.String r0 = r7.mo536a()
            r6.f489e = r0
            goto Lca
        L75:
            byte r1 = r0.a
            if (r1 != r5) goto L1d
            java.lang.String r0 = r7.mo536a()
            r6.f488d = r0
            goto Lca
        L80:
            byte r1 = r0.a
            if (r1 != r2) goto L1d
            boolean r0 = r7.mo541a()
            r6.f484a = r0
            r6.c(r4)
            goto Lca
        L8e:
            byte r1 = r0.a
            if (r1 != r3) goto L1d
            long r0 = r7.mo530a()
            r6.f485b = r0
            r6.b(r4)
            goto Lca
        L9c:
            byte r1 = r0.a
            if (r1 != r3) goto L1d
            long r0 = r7.mo530a()
            r6.f480a = r0
            r6.m389a(r4)
            goto Lca
        Laa:
            byte r1 = r0.a
            if (r1 != r5) goto L1d
            java.lang.String r0 = r7.mo536a()
            r6.f487c = r0
            goto Lca
        Lb5:
            byte r1 = r0.a
            if (r1 != r5) goto L1d
            java.lang.String r0 = r7.mo536a()
            r6.f486b = r0
            goto Lca
        Lc0:
            byte r1 = r0.a
            if (r1 != r5) goto L1d
            java.lang.String r0 = r7.mo536a()
            r6.f481a = r0
        Lca:
            r7.g()
            goto L3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hn.a(com.xiaomi.push.jf):void");
    }

    public void a(String str, String str2) {
        if (this.f483a == null) {
            this.f483a = new HashMap();
        }
        this.f483a.put(str, str2);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m389a(boolean z) {
        this.f482a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m390a() {
        return this.f481a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m391a(hn hnVar) {
        if (hnVar == null) {
            return false;
        }
        boolean zM390a = m390a();
        boolean zM390a2 = hnVar.m390a();
        if ((zM390a || zM390a2) && !(zM390a && zM390a2 && this.f481a.equals(hnVar.f481a))) {
            return false;
        }
        boolean zM392b = m392b();
        boolean zM392b2 = hnVar.m392b();
        if ((zM392b || zM392b2) && !(zM392b && zM392b2 && this.f486b.equals(hnVar.f486b))) {
            return false;
        }
        boolean zM393c = m393c();
        boolean zM393c2 = hnVar.m393c();
        if ((zM393c || zM393c2) && !(zM393c && zM393c2 && this.f487c.equals(hnVar.f487c))) {
            return false;
        }
        boolean zM394d = m394d();
        boolean zM394d2 = hnVar.m394d();
        if ((zM394d || zM394d2) && !(zM394d && zM394d2 && this.f480a == hnVar.f480a)) {
            return false;
        }
        boolean zM395e = m395e();
        boolean zM395e2 = hnVar.m395e();
        if ((zM395e || zM395e2) && !(zM395e && zM395e2 && this.f485b == hnVar.f485b)) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = hnVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f484a == hnVar.f484a)) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = hnVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f488d.equals(hnVar.f488d))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = hnVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f489e.equals(hnVar.f489e))) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = hnVar.i();
        if ((zI || zI2) && !(zI && zI2 && this.f490f.equals(hnVar.f490f))) {
            return false;
        }
        boolean zJ = j();
        boolean zJ2 = hnVar.j();
        if ((zJ || zJ2) && !(zJ && zJ2 && this.f483a.equals(hnVar.f483a))) {
            return false;
        }
        boolean zK = k();
        boolean zK2 = hnVar.k();
        if (zK || zK2) {
            return zK && zK2 && this.f491g.equals(hnVar.f491g);
        }
        return true;
    }

    public hn b(long j2) {
        this.f485b = j2;
        b(true);
        return this;
    }

    public hn b(String str) {
        this.f486b = str;
        return this;
    }

    public String b() {
        return this.f487c;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) {
        m388a();
        jfVar.a(f479a);
        if (this.f481a != null && m390a()) {
            jfVar.a(a);
            jfVar.a(this.f481a);
            jfVar.b();
        }
        if (this.f486b != null && m392b()) {
            jfVar.a(b);
            jfVar.a(this.f486b);
            jfVar.b();
        }
        if (this.f487c != null && m393c()) {
            jfVar.a(c);
            jfVar.a(this.f487c);
            jfVar.b();
        }
        if (m394d()) {
            jfVar.a(d);
            jfVar.a(this.f480a);
            jfVar.b();
        }
        if (m395e()) {
            jfVar.a(e);
            jfVar.a(this.f485b);
            jfVar.b();
        }
        if (f()) {
            jfVar.a(f);
            jfVar.a(this.f484a);
            jfVar.b();
        }
        if (this.f488d != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f488d);
            jfVar.b();
        }
        if (this.f489e != null && h()) {
            jfVar.a(h);
            jfVar.a(this.f489e);
            jfVar.b();
        }
        if (this.f490f != null && i()) {
            jfVar.a(i);
            jfVar.a(this.f490f);
            jfVar.b();
        }
        if (this.f483a != null && j()) {
            jfVar.a(j);
            jfVar.a(new je((byte) 11, (byte) 11, this.f483a.size()));
            for (Map.Entry<String, String> entry : this.f483a.entrySet()) {
                jfVar.a(entry.getKey());
                jfVar.a(entry.getValue());
            }
            jfVar.d();
            jfVar.b();
        }
        if (this.f491g != null && k()) {
            jfVar.a(k);
            jfVar.a(this.f491g);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public void b(boolean z) {
        this.f482a.set(1, z);
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m392b() {
        return this.f486b != null;
    }

    public hn c(String str) {
        this.f487c = str;
        return this;
    }

    public String c() {
        return this.f489e;
    }

    public void c(boolean z) {
        this.f482a.set(2, z);
    }

    /* renamed from: c, reason: collision with other method in class */
    public boolean m393c() {
        return this.f487c != null;
    }

    public hn d(String str) {
        this.f488d = str;
        return this;
    }

    public String d() {
        return this.f490f;
    }

    /* renamed from: d, reason: collision with other method in class */
    public boolean m394d() {
        return this.f482a.get(0);
    }

    public hn e(String str) {
        this.f489e = str;
        return this;
    }

    public String e() {
        return this.f491g;
    }

    /* renamed from: e, reason: collision with other method in class */
    public boolean m395e() {
        return this.f482a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof hn)) {
            return m391a((hn) obj);
        }
        return false;
    }

    public hn f(String str) {
        this.f490f = str;
        return this;
    }

    public boolean f() {
        return this.f482a.get(2);
    }

    public hn g(String str) {
        this.f491g = str;
        return this;
    }

    public boolean g() {
        return this.f488d != null;
    }

    public boolean h() {
        return this.f489e != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f490f != null;
    }

    public boolean j() {
        return this.f483a != null;
    }

    public boolean k() {
        return this.f491g != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("ClientUploadDataItem(");
        if (m390a()) {
            sb.append("channel:");
            sb.append(this.f481a == null ? "null" : this.f481a);
            z = false;
        } else {
            z = true;
        }
        if (m392b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("data:");
            sb.append(this.f486b == null ? "null" : this.f486b);
            z = false;
        }
        if (m393c()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("name:");
            sb.append(this.f487c == null ? "null" : this.f487c);
            z = false;
        }
        if (m394d()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("counter:");
            sb.append(this.f480a);
            z = false;
        }
        if (m395e()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("timestamp:");
            sb.append(this.f485b);
            z = false;
        }
        if (f()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("fromSdk:");
            sb.append(this.f484a);
            z = false;
        }
        if (g()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("category:");
            sb.append(this.f488d == null ? "null" : this.f488d);
            z = false;
        }
        if (h()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("sourcePackage:");
            sb.append(this.f489e == null ? "null" : this.f489e);
            z = false;
        }
        if (i()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("id:");
            sb.append(this.f490f == null ? "null" : this.f490f);
            z = false;
        }
        if (j()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("extra:");
            if (this.f483a == null) {
                sb.append("null");
            } else {
                sb.append(this.f483a);
            }
            z = false;
        }
        if (k()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("pkgName:");
            sb.append(this.f491g == null ? "null" : this.f491g);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
