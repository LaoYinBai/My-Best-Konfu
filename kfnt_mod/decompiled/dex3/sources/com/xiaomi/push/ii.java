package com.xiaomi.push;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ii implements iu<ii, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public long f645a;

    /* renamed from: a, reason: collision with other field name */
    public hy f646a;

    /* renamed from: a, reason: collision with other field name */
    public String f647a;

    /* renamed from: a, reason: collision with other field name */
    public ByteBuffer f648a;

    /* renamed from: a, reason: collision with other field name */
    private BitSet f649a;

    /* renamed from: a, reason: collision with other field name */
    public Map<String, String> f650a;

    /* renamed from: a, reason: collision with other field name */
    public boolean f651a;

    /* renamed from: b, reason: collision with other field name */
    public String f652b;

    /* renamed from: b, reason: collision with other field name */
    public boolean f653b;

    /* renamed from: c, reason: collision with other field name */
    public String f654c;

    /* renamed from: d, reason: collision with other field name */
    public String f655d;

    /* renamed from: e, reason: collision with other field name */
    public String f656e;

    /* renamed from: f, reason: collision with other field name */
    public String f657f;

    /* renamed from: g, reason: collision with other field name */
    public String f658g;

    /* renamed from: h, reason: collision with other field name */
    public String f659h;

    /* renamed from: i, reason: collision with other field name */
    public String f660i;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f644a = new jk("XmPushActionNotification");
    private static final jc a = new jc("", (byte) 11, 1);
    private static final jc b = new jc("", (byte) 12, 2);
    private static final jc c = new jc("", (byte) 11, 3);
    private static final jc d = new jc("", (byte) 11, 4);
    private static final jc e = new jc("", (byte) 11, 5);
    private static final jc f = new jc("", (byte) 2, 6);
    private static final jc g = new jc("", (byte) 11, 7);
    private static final jc h = new jc("", com.umeng.commonsdk.proguard.az.k, 8);
    private static final jc i = new jc("", (byte) 11, 9);
    private static final jc j = new jc("", (byte) 11, 10);
    private static final jc k = new jc("", (byte) 11, 12);
    private static final jc l = new jc("", (byte) 11, 13);
    private static final jc m = new jc("", (byte) 11, 14);
    private static final jc n = new jc("", (byte) 10, 15);
    private static final jc o = new jc("", (byte) 2, 20);

    public ii() {
        this.f649a = new BitSet(3);
        this.f651a = true;
        this.f653b = false;
    }

    public ii(String str, boolean z) {
        this();
        this.f652b = str;
        this.f651a = z;
        m479a(true);
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ii iiVar) {
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
        int iA12;
        int iA13;
        int iA14;
        int iA15;
        if (!getClass().equals(iiVar.getClass())) {
            return getClass().getName().compareTo(iiVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m480a()).compareTo(Boolean.valueOf(iiVar.m480a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m480a() && (iA15 = iv.a(this.f647a, iiVar.f647a)) != 0) {
            return iA15;
        }
        int iCompareTo2 = Boolean.valueOf(m483b()).compareTo(Boolean.valueOf(iiVar.m483b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (m483b() && (iA14 = iv.a(this.f646a, iiVar.f646a)) != 0) {
            return iA14;
        }
        int iCompareTo3 = Boolean.valueOf(m484c()).compareTo(Boolean.valueOf(iiVar.m484c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (m484c() && (iA13 = iv.a(this.f652b, iiVar.f652b)) != 0) {
            return iA13;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(iiVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA12 = iv.a(this.f654c, iiVar.f654c)) != 0) {
            return iA12;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(iiVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA11 = iv.a(this.f655d, iiVar.f655d)) != 0) {
            return iA11;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(iiVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA10 = iv.a(this.f651a, iiVar.f651a)) != 0) {
            return iA10;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(iiVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA9 = iv.a(this.f656e, iiVar.f656e)) != 0) {
            return iA9;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(iiVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA8 = iv.a(this.f650a, iiVar.f650a)) != 0) {
            return iA8;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(iiVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (i() && (iA7 = iv.a(this.f657f, iiVar.f657f)) != 0) {
            return iA7;
        }
        int iCompareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(iiVar.j()));
        if (iCompareTo10 != 0) {
            return iCompareTo10;
        }
        if (j() && (iA6 = iv.a(this.f658g, iiVar.f658g)) != 0) {
            return iA6;
        }
        int iCompareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(iiVar.k()));
        if (iCompareTo11 != 0) {
            return iCompareTo11;
        }
        if (k() && (iA5 = iv.a(this.f659h, iiVar.f659h)) != 0) {
            return iA5;
        }
        int iCompareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(iiVar.l()));
        if (iCompareTo12 != 0) {
            return iCompareTo12;
        }
        if (l() && (iA4 = iv.a(this.f660i, iiVar.f660i)) != 0) {
            return iA4;
        }
        int iCompareTo13 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(iiVar.m()));
        if (iCompareTo13 != 0) {
            return iCompareTo13;
        }
        if (m() && (iA3 = iv.a(this.f648a, iiVar.f648a)) != 0) {
            return iA3;
        }
        int iCompareTo14 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(iiVar.n()));
        if (iCompareTo14 != 0) {
            return iCompareTo14;
        }
        if (n() && (iA2 = iv.a(this.f645a, iiVar.f645a)) != 0) {
            return iA2;
        }
        int iCompareTo15 = Boolean.valueOf(o()).compareTo(Boolean.valueOf(iiVar.o()));
        if (iCompareTo15 != 0) {
            return iCompareTo15;
        }
        if (!o() || (iA = iv.a(this.f653b, iiVar.f653b)) == 0) {
            return 0;
        }
        return iA;
    }

    public hy a() {
        return this.f646a;
    }

    public ii a(String str) {
        this.f652b = str;
        return this;
    }

    public ii a(ByteBuffer byteBuffer) {
        this.f648a = byteBuffer;
        return this;
    }

    public ii a(Map<String, String> map) {
        this.f650a = map;
        return this;
    }

    public ii a(boolean z) {
        this.f651a = z;
        m479a(true);
        return this;
    }

    public ii a(byte[] bArr) {
        a(ByteBuffer.wrap(bArr));
        return this;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m476a() {
        return this.f652b;
    }

    /* renamed from: a, reason: collision with other method in class */
    public Map<String, String> m477a() {
        return this.f650a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m478a() throws jg {
        if (this.f652b != null) {
            return;
        }
        throw new jg("Required field 'id' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    @Override // com.xiaomi.push.iu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.xiaomi.push.jf r6) throws com.xiaomi.push.jg {
        /*
            Method dump skipped, instructions count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ii.a(com.xiaomi.push.jf):void");
    }

    public void a(String str, String str2) {
        if (this.f650a == null) {
            this.f650a = new HashMap();
        }
        this.f650a.put(str, str2);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m479a(boolean z) {
        this.f649a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m480a() {
        return this.f647a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m481a(ii iiVar) {
        if (iiVar == null) {
            return false;
        }
        boolean zM480a = m480a();
        boolean zM480a2 = iiVar.m480a();
        if ((zM480a || zM480a2) && !(zM480a && zM480a2 && this.f647a.equals(iiVar.f647a))) {
            return false;
        }
        boolean zM483b = m483b();
        boolean zM483b2 = iiVar.m483b();
        if ((zM483b || zM483b2) && !(zM483b && zM483b2 && this.f646a.m430a(iiVar.f646a))) {
            return false;
        }
        boolean zM484c = m484c();
        boolean zM484c2 = iiVar.m484c();
        if ((zM484c || zM484c2) && !(zM484c && zM484c2 && this.f652b.equals(iiVar.f652b))) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = iiVar.d();
        if ((zD || zD2) && !(zD && zD2 && this.f654c.equals(iiVar.f654c))) {
            return false;
        }
        boolean zE = e();
        boolean zE2 = iiVar.e();
        if (((zE || zE2) && !(zE && zE2 && this.f655d.equals(iiVar.f655d))) || this.f651a != iiVar.f651a) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = iiVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f656e.equals(iiVar.f656e))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = iiVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f650a.equals(iiVar.f650a))) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = iiVar.i();
        if ((zI || zI2) && !(zI && zI2 && this.f657f.equals(iiVar.f657f))) {
            return false;
        }
        boolean zJ = j();
        boolean zJ2 = iiVar.j();
        if ((zJ || zJ2) && !(zJ && zJ2 && this.f658g.equals(iiVar.f658g))) {
            return false;
        }
        boolean zK = k();
        boolean zK2 = iiVar.k();
        if ((zK || zK2) && !(zK && zK2 && this.f659h.equals(iiVar.f659h))) {
            return false;
        }
        boolean zL = l();
        boolean zL2 = iiVar.l();
        if ((zL || zL2) && !(zL && zL2 && this.f660i.equals(iiVar.f660i))) {
            return false;
        }
        boolean zM = m();
        boolean zM2 = iiVar.m();
        if ((zM || zM2) && !(zM && zM2 && this.f648a.equals(iiVar.f648a))) {
            return false;
        }
        boolean zN = n();
        boolean zN2 = iiVar.n();
        if ((zN || zN2) && !(zN && zN2 && this.f645a == iiVar.f645a)) {
            return false;
        }
        boolean zO = o();
        boolean zO2 = iiVar.o();
        if (zO || zO2) {
            return zO && zO2 && this.f653b == iiVar.f653b;
        }
        return true;
    }

    /* renamed from: a, reason: collision with other method in class */
    public byte[] m482a() {
        a(iv.a(this.f648a));
        return this.f648a.array();
    }

    public ii b(String str) {
        this.f654c = str;
        return this;
    }

    public String b() {
        return this.f654c;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m478a();
        jfVar.a(f644a);
        if (this.f647a != null && m480a()) {
            jfVar.a(a);
            jfVar.a(this.f647a);
            jfVar.b();
        }
        if (this.f646a != null && m483b()) {
            jfVar.a(b);
            this.f646a.b(jfVar);
            jfVar.b();
        }
        if (this.f652b != null) {
            jfVar.a(c);
            jfVar.a(this.f652b);
            jfVar.b();
        }
        if (this.f654c != null && d()) {
            jfVar.a(d);
            jfVar.a(this.f654c);
            jfVar.b();
        }
        if (this.f655d != null && e()) {
            jfVar.a(e);
            jfVar.a(this.f655d);
            jfVar.b();
        }
        jfVar.a(f);
        jfVar.a(this.f651a);
        jfVar.b();
        if (this.f656e != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f656e);
            jfVar.b();
        }
        if (this.f650a != null && h()) {
            jfVar.a(h);
            jfVar.a(new je((byte) 11, (byte) 11, this.f650a.size()));
            for (Map.Entry<String, String> entry : this.f650a.entrySet()) {
                jfVar.a(entry.getKey());
                jfVar.a(entry.getValue());
            }
            jfVar.d();
            jfVar.b();
        }
        if (this.f657f != null && i()) {
            jfVar.a(i);
            jfVar.a(this.f657f);
            jfVar.b();
        }
        if (this.f658g != null && j()) {
            jfVar.a(j);
            jfVar.a(this.f658g);
            jfVar.b();
        }
        if (this.f659h != null && k()) {
            jfVar.a(k);
            jfVar.a(this.f659h);
            jfVar.b();
        }
        if (this.f660i != null && l()) {
            jfVar.a(l);
            jfVar.a(this.f660i);
            jfVar.b();
        }
        if (this.f648a != null && m()) {
            jfVar.a(m);
            jfVar.a(this.f648a);
            jfVar.b();
        }
        if (n()) {
            jfVar.a(n);
            jfVar.a(this.f645a);
            jfVar.b();
        }
        if (o()) {
            jfVar.a(o);
            jfVar.a(this.f653b);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public void b(boolean z) {
        this.f649a.set(1, z);
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m483b() {
        return this.f646a != null;
    }

    public ii c(String str) {
        this.f655d = str;
        return this;
    }

    public String c() {
        return this.f657f;
    }

    public void c(boolean z) {
        this.f649a.set(2, z);
    }

    /* renamed from: c, reason: collision with other method in class */
    public boolean m484c() {
        return this.f652b != null;
    }

    public ii d(String str) {
        this.f657f = str;
        return this;
    }

    public boolean d() {
        return this.f654c != null;
    }

    public boolean e() {
        return this.f655d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ii)) {
            return m481a((ii) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f649a.get(0);
    }

    public boolean g() {
        return this.f656e != null;
    }

    public boolean h() {
        return this.f650a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f657f != null;
    }

    public boolean j() {
        return this.f658g != null;
    }

    public boolean k() {
        return this.f659h != null;
    }

    public boolean l() {
        return this.f660i != null;
    }

    public boolean m() {
        return this.f648a != null;
    }

    public boolean n() {
        return this.f649a.get(1);
    }

    public boolean o() {
        return this.f649a.get(2);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionNotification(");
        if (m480a()) {
            sb.append("debug:");
            sb.append(this.f647a == null ? "null" : this.f647a);
            z = false;
        } else {
            z = true;
        }
        if (m483b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f646a == null) {
                sb.append("null");
            } else {
                sb.append(this.f646a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f652b == null ? "null" : this.f652b);
        if (d()) {
            sb.append(", ");
            sb.append("appId:");
            sb.append(this.f654c == null ? "null" : this.f654c);
        }
        if (e()) {
            sb.append(", ");
            sb.append("type:");
            sb.append(this.f655d == null ? "null" : this.f655d);
        }
        sb.append(", ");
        sb.append("requireAck:");
        sb.append(this.f651a);
        if (g()) {
            sb.append(", ");
            sb.append("payload:");
            sb.append(this.f656e == null ? "null" : this.f656e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("extra:");
            if (this.f650a == null) {
                sb.append("null");
            } else {
                sb.append(this.f650a);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f657f == null ? "null" : this.f657f);
        }
        if (j()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.f658g == null ? "null" : this.f658g);
        }
        if (k()) {
            sb.append(", ");
            sb.append("regId:");
            sb.append(this.f659h == null ? "null" : this.f659h);
        }
        if (l()) {
            sb.append(", ");
            sb.append("aliasName:");
            sb.append(this.f660i == null ? "null" : this.f660i);
        }
        if (m()) {
            sb.append(", ");
            sb.append("binaryExtra:");
            if (this.f648a == null) {
                sb.append("null");
            } else {
                iv.a(this.f648a, sb);
            }
        }
        if (n()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f645a);
        }
        if (o()) {
            sb.append(", ");
            sb.append("alreadyLogClickInXmq:");
            sb.append(this.f653b);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
