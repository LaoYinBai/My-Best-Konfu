package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class fd implements iu<fd, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public byte f351a;

    /* renamed from: a, reason: collision with other field name */
    public int f352a;

    /* renamed from: a, reason: collision with other field name */
    public String f353a;

    /* renamed from: a, reason: collision with other field name */
    private BitSet f354a = new BitSet(6);

    /* renamed from: b, reason: collision with other field name */
    public int f355b;

    /* renamed from: b, reason: collision with other field name */
    public String f356b;

    /* renamed from: c, reason: collision with other field name */
    public int f357c;

    /* renamed from: c, reason: collision with other field name */
    public String f358c;

    /* renamed from: d, reason: collision with other field name */
    public int f359d;

    /* renamed from: d, reason: collision with other field name */
    public String f360d;

    /* renamed from: e, reason: collision with other field name */
    public int f361e;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f350a = new jk("StatsEvent");
    private static final jc a = new jc("", (byte) 3, 1);
    private static final jc b = new jc("", (byte) 8, 2);
    private static final jc c = new jc("", (byte) 8, 3);
    private static final jc d = new jc("", (byte) 11, 4);
    private static final jc e = new jc("", (byte) 11, 5);
    private static final jc f = new jc("", (byte) 8, 6);
    private static final jc g = new jc("", (byte) 11, 7);
    private static final jc h = new jc("", (byte) 11, 8);
    private static final jc i = new jc("", (byte) 8, 9);
    private static final jc j = new jc("", (byte) 8, 10);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(fd fdVar) {
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
        if (!getClass().equals(fdVar.getClass())) {
            return getClass().getName().compareTo(fdVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m308a()).compareTo(Boolean.valueOf(fdVar.m308a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m308a() && (iA10 = iv.a(this.f351a, fdVar.f351a)) != 0) {
            return iA10;
        }
        int iCompareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(fdVar.b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (b() && (iA9 = iv.a(this.f352a, fdVar.f352a)) != 0) {
            return iA9;
        }
        int iCompareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(fdVar.c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (c() && (iA8 = iv.a(this.f355b, fdVar.f355b)) != 0) {
            return iA8;
        }
        int iCompareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(fdVar.d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (d() && (iA7 = iv.a(this.f353a, fdVar.f353a)) != 0) {
            return iA7;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(fdVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA6 = iv.a(this.f356b, fdVar.f356b)) != 0) {
            return iA6;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(fdVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA5 = iv.a(this.f357c, fdVar.f357c)) != 0) {
            return iA5;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(fdVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA4 = iv.a(this.f358c, fdVar.f358c)) != 0) {
            return iA4;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(fdVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA3 = iv.a(this.f360d, fdVar.f360d)) != 0) {
            return iA3;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(fdVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (i() && (iA2 = iv.a(this.f359d, fdVar.f359d)) != 0) {
            return iA2;
        }
        int iCompareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(fdVar.j()));
        if (iCompareTo10 != 0) {
            return iCompareTo10;
        }
        if (!j() || (iA = iv.a(this.f361e, fdVar.f361e)) == 0) {
            return 0;
        }
        return iA;
    }

    public fd a(byte b2) {
        this.f351a = b2;
        a(true);
        return this;
    }

    public fd a(int i2) {
        this.f352a = i2;
        b(true);
        return this;
    }

    public fd a(String str) {
        this.f353a = str;
        return this;
    }

    public void a() throws jg {
        if (this.f353a != null) {
            return;
        }
        throw new jg("Required field 'connpt' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007f  */
    @Override // com.xiaomi.push.iu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.xiaomi.push.jf r6) throws com.xiaomi.push.jg {
        /*
            Method dump skipped, instructions count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.fd.a(com.xiaomi.push.jf):void");
    }

    public void a(boolean z) {
        this.f354a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m308a() {
        return this.f354a.get(0);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m309a(fd fdVar) {
        if (fdVar == null || this.f351a != fdVar.f351a || this.f352a != fdVar.f352a || this.f355b != fdVar.f355b) {
            return false;
        }
        boolean zD = d();
        boolean zD2 = fdVar.d();
        if ((zD || zD2) && !(zD && zD2 && this.f353a.equals(fdVar.f353a))) {
            return false;
        }
        boolean zE = e();
        boolean zE2 = fdVar.e();
        if ((zE || zE2) && !(zE && zE2 && this.f356b.equals(fdVar.f356b))) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = fdVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f357c == fdVar.f357c)) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = fdVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f358c.equals(fdVar.f358c))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = fdVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f360d.equals(fdVar.f360d))) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = fdVar.i();
        if ((zI || zI2) && !(zI && zI2 && this.f359d == fdVar.f359d)) {
            return false;
        }
        boolean zJ = j();
        boolean zJ2 = fdVar.j();
        if (zJ || zJ2) {
            return zJ && zJ2 && this.f361e == fdVar.f361e;
        }
        return true;
    }

    public fd b(int i2) {
        this.f355b = i2;
        c(true);
        return this;
    }

    public fd b(String str) {
        this.f356b = str;
        return this;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) {
        a();
        jfVar.a(f350a);
        jfVar.a(a);
        jfVar.a(this.f351a);
        jfVar.b();
        jfVar.a(b);
        jfVar.mo540a(this.f352a);
        jfVar.b();
        jfVar.a(c);
        jfVar.mo540a(this.f355b);
        jfVar.b();
        if (this.f353a != null) {
            jfVar.a(d);
            jfVar.a(this.f353a);
            jfVar.b();
        }
        if (this.f356b != null && e()) {
            jfVar.a(e);
            jfVar.a(this.f356b);
            jfVar.b();
        }
        if (f()) {
            jfVar.a(f);
            jfVar.mo540a(this.f357c);
            jfVar.b();
        }
        if (this.f358c != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f358c);
            jfVar.b();
        }
        if (this.f360d != null && h()) {
            jfVar.a(h);
            jfVar.a(this.f360d);
            jfVar.b();
        }
        if (i()) {
            jfVar.a(i);
            jfVar.mo540a(this.f359d);
            jfVar.b();
        }
        if (j()) {
            jfVar.a(j);
            jfVar.mo540a(this.f361e);
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public void b(boolean z) {
        this.f354a.set(1, z);
    }

    public boolean b() {
        return this.f354a.get(1);
    }

    public fd c(int i2) {
        this.f357c = i2;
        d(true);
        return this;
    }

    public fd c(String str) {
        this.f358c = str;
        return this;
    }

    public void c(boolean z) {
        this.f354a.set(2, z);
    }

    public boolean c() {
        return this.f354a.get(2);
    }

    public fd d(int i2) {
        this.f359d = i2;
        e(true);
        return this;
    }

    public fd d(String str) {
        this.f360d = str;
        return this;
    }

    public void d(boolean z) {
        this.f354a.set(3, z);
    }

    public boolean d() {
        return this.f353a != null;
    }

    public void e(boolean z) {
        this.f354a.set(4, z);
    }

    public boolean e() {
        return this.f356b != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof fd)) {
            return m309a((fd) obj);
        }
        return false;
    }

    public void f(boolean z) {
        this.f354a.set(5, z);
    }

    public boolean f() {
        return this.f354a.get(3);
    }

    public boolean g() {
        return this.f358c != null;
    }

    public boolean h() {
        return this.f360d != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f354a.get(4);
    }

    public boolean j() {
        return this.f354a.get(5);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("StatsEvent(");
        sb.append("chid:");
        sb.append((int) this.f351a);
        sb.append(", ");
        sb.append("type:");
        sb.append(this.f352a);
        sb.append(", ");
        sb.append("value:");
        sb.append(this.f355b);
        sb.append(", ");
        sb.append("connpt:");
        sb.append(this.f353a == null ? "null" : this.f353a);
        if (e()) {
            sb.append(", ");
            sb.append("host:");
            sb.append(this.f356b == null ? "null" : this.f356b);
        }
        if (f()) {
            sb.append(", ");
            sb.append("subvalue:");
            sb.append(this.f357c);
        }
        if (g()) {
            sb.append(", ");
            sb.append("annotation:");
            sb.append(this.f358c == null ? "null" : this.f358c);
        }
        if (h()) {
            sb.append(", ");
            sb.append("user:");
            sb.append(this.f360d == null ? "null" : this.f360d);
        }
        if (i()) {
            sb.append(", ");
            sb.append("time:");
            sb.append(this.f359d);
        }
        if (j()) {
            sb.append(", ");
            sb.append("clientIp:");
            sb.append(this.f361e);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
