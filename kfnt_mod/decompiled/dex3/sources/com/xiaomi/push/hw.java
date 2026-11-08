package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class hw implements iu<hw, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public int f540a;

    /* renamed from: a, reason: collision with other field name */
    public long f541a;

    /* renamed from: a, reason: collision with other field name */
    public String f542a;

    /* renamed from: a, reason: collision with other field name */
    private BitSet f543a;

    /* renamed from: a, reason: collision with other field name */
    public Map<String, String> f544a;

    /* renamed from: a, reason: collision with other field name */
    public boolean f545a;

    /* renamed from: b, reason: collision with other field name */
    public int f546b;

    /* renamed from: b, reason: collision with other field name */
    public String f547b;

    /* renamed from: b, reason: collision with other field name */
    public Map<String, String> f548b;

    /* renamed from: c, reason: collision with other field name */
    public int f549c;

    /* renamed from: c, reason: collision with other field name */
    public String f550c;

    /* renamed from: c, reason: collision with other field name */
    public Map<String, String> f551c;

    /* renamed from: d, reason: collision with other field name */
    public String f552d;

    /* renamed from: e, reason: collision with other field name */
    public String f553e;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f539a = new jk("PushMetaInfo");
    private static final jc a = new jc("", (byte) 11, 1);
    private static final jc b = new jc("", (byte) 10, 2);
    private static final jc c = new jc("", (byte) 11, 3);
    private static final jc d = new jc("", (byte) 11, 4);
    private static final jc e = new jc("", (byte) 11, 5);
    private static final jc f = new jc("", (byte) 8, 6);
    private static final jc g = new jc("", (byte) 11, 7);
    private static final jc h = new jc("", (byte) 8, 8);
    private static final jc i = new jc("", (byte) 8, 9);
    private static final jc j = new jc("", com.umeng.commonsdk.proguard.az.k, 10);
    private static final jc k = new jc("", com.umeng.commonsdk.proguard.az.k, 11);
    private static final jc l = new jc("", (byte) 2, 12);
    private static final jc m = new jc("", com.umeng.commonsdk.proguard.az.k, 13);

    public hw() {
        this.f543a = new BitSet(5);
        this.f545a = false;
    }

    public hw(hw hwVar) {
        this.f543a = new BitSet(5);
        this.f543a.clear();
        this.f543a.or(hwVar.f543a);
        if (hwVar.m421a()) {
            this.f542a = hwVar.f542a;
        }
        this.f541a = hwVar.f541a;
        if (hwVar.m427c()) {
            this.f547b = hwVar.f547b;
        }
        if (hwVar.m428d()) {
            this.f550c = hwVar.f550c;
        }
        if (hwVar.e()) {
            this.f552d = hwVar.f552d;
        }
        this.f540a = hwVar.f540a;
        if (hwVar.g()) {
            this.f553e = hwVar.f553e;
        }
        this.f546b = hwVar.f546b;
        this.f549c = hwVar.f549c;
        if (hwVar.j()) {
            HashMap map = new HashMap();
            for (Map.Entry<String, String> entry : hwVar.f544a.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            this.f544a = map;
        }
        if (hwVar.k()) {
            HashMap map2 = new HashMap();
            for (Map.Entry<String, String> entry2 : hwVar.f548b.entrySet()) {
                map2.put(entry2.getKey(), entry2.getValue());
            }
            this.f548b = map2;
        }
        this.f545a = hwVar.f545a;
        if (hwVar.n()) {
            HashMap map3 = new HashMap();
            for (Map.Entry<String, String> entry3 : hwVar.f551c.entrySet()) {
                map3.put(entry3.getKey(), entry3.getValue());
            }
            this.f551c = map3;
        }
    }

    public int a() {
        return this.f540a;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(hw hwVar) {
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
        if (!getClass().equals(hwVar.getClass())) {
            return getClass().getName().compareTo(hwVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m421a()).compareTo(Boolean.valueOf(hwVar.m421a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (m421a() && (iA13 = iv.a(this.f542a, hwVar.f542a)) != 0) {
            return iA13;
        }
        int iCompareTo2 = Boolean.valueOf(m425b()).compareTo(Boolean.valueOf(hwVar.m425b()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        if (m425b() && (iA12 = iv.a(this.f541a, hwVar.f541a)) != 0) {
            return iA12;
        }
        int iCompareTo3 = Boolean.valueOf(m427c()).compareTo(Boolean.valueOf(hwVar.m427c()));
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        if (m427c() && (iA11 = iv.a(this.f547b, hwVar.f547b)) != 0) {
            return iA11;
        }
        int iCompareTo4 = Boolean.valueOf(m428d()).compareTo(Boolean.valueOf(hwVar.m428d()));
        if (iCompareTo4 != 0) {
            return iCompareTo4;
        }
        if (m428d() && (iA10 = iv.a(this.f550c, hwVar.f550c)) != 0) {
            return iA10;
        }
        int iCompareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(hwVar.e()));
        if (iCompareTo5 != 0) {
            return iCompareTo5;
        }
        if (e() && (iA9 = iv.a(this.f552d, hwVar.f552d)) != 0) {
            return iA9;
        }
        int iCompareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(hwVar.f()));
        if (iCompareTo6 != 0) {
            return iCompareTo6;
        }
        if (f() && (iA8 = iv.a(this.f540a, hwVar.f540a)) != 0) {
            return iA8;
        }
        int iCompareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(hwVar.g()));
        if (iCompareTo7 != 0) {
            return iCompareTo7;
        }
        if (g() && (iA7 = iv.a(this.f553e, hwVar.f553e)) != 0) {
            return iA7;
        }
        int iCompareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(hwVar.h()));
        if (iCompareTo8 != 0) {
            return iCompareTo8;
        }
        if (h() && (iA6 = iv.a(this.f546b, hwVar.f546b)) != 0) {
            return iA6;
        }
        int iCompareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(hwVar.i()));
        if (iCompareTo9 != 0) {
            return iCompareTo9;
        }
        if (i() && (iA5 = iv.a(this.f549c, hwVar.f549c)) != 0) {
            return iA5;
        }
        int iCompareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(hwVar.j()));
        if (iCompareTo10 != 0) {
            return iCompareTo10;
        }
        if (j() && (iA4 = iv.a(this.f544a, hwVar.f544a)) != 0) {
            return iA4;
        }
        int iCompareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(hwVar.k()));
        if (iCompareTo11 != 0) {
            return iCompareTo11;
        }
        if (k() && (iA3 = iv.a(this.f548b, hwVar.f548b)) != 0) {
            return iA3;
        }
        int iCompareTo12 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(hwVar.m()));
        if (iCompareTo12 != 0) {
            return iCompareTo12;
        }
        if (m() && (iA2 = iv.a(this.f545a, hwVar.f545a)) != 0) {
            return iA2;
        }
        int iCompareTo13 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(hwVar.n()));
        if (iCompareTo13 != 0) {
            return iCompareTo13;
        }
        if (!n() || (iA = iv.a(this.f551c, hwVar.f551c)) == 0) {
            return 0;
        }
        return iA;
    }

    /* renamed from: a, reason: collision with other method in class */
    public long m416a() {
        return this.f541a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public hw m417a() {
        return new hw(this);
    }

    public hw a(int i2) {
        this.f540a = i2;
        b(true);
        return this;
    }

    public hw a(String str) {
        this.f542a = str;
        return this;
    }

    public hw a(Map<String, String> map) {
        this.f544a = map;
        return this;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m418a() {
        return this.f542a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public Map<String, String> m419a() {
        return this.f544a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m420a() throws jg {
        if (this.f542a != null) {
            return;
        }
        throw new jg("Required field 'id' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0041  */
    @Override // com.xiaomi.push.iu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.xiaomi.push.jf r9) throws com.xiaomi.push.jg {
        /*
            Method dump skipped, instructions count: 356
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hw.a(com.xiaomi.push.jf):void");
    }

    public void a(String str, String str2) {
        if (this.f544a == null) {
            this.f544a = new HashMap();
        }
        this.f544a.put(str, str2);
    }

    public void a(boolean z) {
        this.f543a.set(0, z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m421a() {
        return this.f542a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m422a(hw hwVar) {
        if (hwVar == null) {
            return false;
        }
        boolean zM421a = m421a();
        boolean zM421a2 = hwVar.m421a();
        if (((zM421a || zM421a2) && !(zM421a && zM421a2 && this.f542a.equals(hwVar.f542a))) || this.f541a != hwVar.f541a) {
            return false;
        }
        boolean zM427c = m427c();
        boolean zM427c2 = hwVar.m427c();
        if ((zM427c || zM427c2) && !(zM427c && zM427c2 && this.f547b.equals(hwVar.f547b))) {
            return false;
        }
        boolean zM428d = m428d();
        boolean zM428d2 = hwVar.m428d();
        if ((zM428d || zM428d2) && !(zM428d && zM428d2 && this.f550c.equals(hwVar.f550c))) {
            return false;
        }
        boolean zE = e();
        boolean zE2 = hwVar.e();
        if ((zE || zE2) && !(zE && zE2 && this.f552d.equals(hwVar.f552d))) {
            return false;
        }
        boolean zF = f();
        boolean zF2 = hwVar.f();
        if ((zF || zF2) && !(zF && zF2 && this.f540a == hwVar.f540a)) {
            return false;
        }
        boolean zG = g();
        boolean zG2 = hwVar.g();
        if ((zG || zG2) && !(zG && zG2 && this.f553e.equals(hwVar.f553e))) {
            return false;
        }
        boolean zH = h();
        boolean zH2 = hwVar.h();
        if ((zH || zH2) && !(zH && zH2 && this.f546b == hwVar.f546b)) {
            return false;
        }
        boolean zI = i();
        boolean zI2 = hwVar.i();
        if ((zI || zI2) && !(zI && zI2 && this.f549c == hwVar.f549c)) {
            return false;
        }
        boolean zJ = j();
        boolean zJ2 = hwVar.j();
        if ((zJ || zJ2) && !(zJ && zJ2 && this.f544a.equals(hwVar.f544a))) {
            return false;
        }
        boolean zK = k();
        boolean zK2 = hwVar.k();
        if ((zK || zK2) && !(zK && zK2 && this.f548b.equals(hwVar.f548b))) {
            return false;
        }
        boolean zM = m();
        boolean zM2 = hwVar.m();
        if ((zM || zM2) && !(zM && zM2 && this.f545a == hwVar.f545a)) {
            return false;
        }
        boolean zN = n();
        boolean zN2 = hwVar.n();
        if (zN || zN2) {
            return zN && zN2 && this.f551c.equals(hwVar.f551c);
        }
        return true;
    }

    public int b() {
        return this.f546b;
    }

    public hw b(int i2) {
        this.f546b = i2;
        c(true);
        return this;
    }

    public hw b(String str) {
        this.f547b = str;
        return this;
    }

    /* renamed from: b, reason: collision with other method in class */
    public String m423b() {
        return this.f547b;
    }

    /* renamed from: b, reason: collision with other method in class */
    public Map<String, String> m424b() {
        return this.f548b;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m420a();
        jfVar.a(f539a);
        if (this.f542a != null) {
            jfVar.a(a);
            jfVar.a(this.f542a);
            jfVar.b();
        }
        jfVar.a(b);
        jfVar.a(this.f541a);
        jfVar.b();
        if (this.f547b != null && m427c()) {
            jfVar.a(c);
            jfVar.a(this.f547b);
            jfVar.b();
        }
        if (this.f550c != null && m428d()) {
            jfVar.a(d);
            jfVar.a(this.f550c);
            jfVar.b();
        }
        if (this.f552d != null && e()) {
            jfVar.a(e);
            jfVar.a(this.f552d);
            jfVar.b();
        }
        if (f()) {
            jfVar.a(f);
            jfVar.mo540a(this.f540a);
            jfVar.b();
        }
        if (this.f553e != null && g()) {
            jfVar.a(g);
            jfVar.a(this.f553e);
            jfVar.b();
        }
        if (h()) {
            jfVar.a(h);
            jfVar.mo540a(this.f546b);
            jfVar.b();
        }
        if (i()) {
            jfVar.a(i);
            jfVar.mo540a(this.f549c);
            jfVar.b();
        }
        if (this.f544a != null && j()) {
            jfVar.a(j);
            jfVar.a(new je((byte) 11, (byte) 11, this.f544a.size()));
            for (Map.Entry<String, String> entry : this.f544a.entrySet()) {
                jfVar.a(entry.getKey());
                jfVar.a(entry.getValue());
            }
            jfVar.d();
            jfVar.b();
        }
        if (this.f548b != null && k()) {
            jfVar.a(k);
            jfVar.a(new je((byte) 11, (byte) 11, this.f548b.size()));
            for (Map.Entry<String, String> entry2 : this.f548b.entrySet()) {
                jfVar.a(entry2.getKey());
                jfVar.a(entry2.getValue());
            }
            jfVar.d();
            jfVar.b();
        }
        if (m()) {
            jfVar.a(l);
            jfVar.a(this.f545a);
            jfVar.b();
        }
        if (this.f551c != null && n()) {
            jfVar.a(m);
            jfVar.a(new je((byte) 11, (byte) 11, this.f551c.size()));
            for (Map.Entry<String, String> entry3 : this.f551c.entrySet()) {
                jfVar.a(entry3.getKey());
                jfVar.a(entry3.getValue());
            }
            jfVar.d();
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public void b(String str, String str2) {
        if (this.f548b == null) {
            this.f548b = new HashMap();
        }
        this.f548b.put(str, str2);
    }

    public void b(boolean z) {
        this.f543a.set(1, z);
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m425b() {
        return this.f543a.get(0);
    }

    public int c() {
        return this.f549c;
    }

    public hw c(int i2) {
        this.f549c = i2;
        d(true);
        return this;
    }

    public hw c(String str) {
        this.f550c = str;
        return this;
    }

    /* renamed from: c, reason: collision with other method in class */
    public String m426c() {
        return this.f550c;
    }

    public void c(boolean z) {
        this.f543a.set(2, z);
    }

    /* renamed from: c, reason: collision with other method in class */
    public boolean m427c() {
        return this.f547b != null;
    }

    public hw d(String str) {
        this.f552d = str;
        return this;
    }

    public String d() {
        return this.f552d;
    }

    public void d(boolean z) {
        this.f543a.set(3, z);
    }

    /* renamed from: d, reason: collision with other method in class */
    public boolean m428d() {
        return this.f550c != null;
    }

    public void e(boolean z) {
        this.f543a.set(4, z);
    }

    public boolean e() {
        return this.f552d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof hw)) {
            return m422a((hw) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f543a.get(1);
    }

    public boolean g() {
        return this.f553e != null;
    }

    public boolean h() {
        return this.f543a.get(2);
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f543a.get(3);
    }

    public boolean j() {
        return this.f544a != null;
    }

    public boolean k() {
        return this.f548b != null;
    }

    public boolean l() {
        return this.f545a;
    }

    public boolean m() {
        return this.f543a.get(4);
    }

    public boolean n() {
        return this.f551c != null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PushMetaInfo(");
        sb.append("id:");
        sb.append(this.f542a == null ? "null" : this.f542a);
        sb.append(", ");
        sb.append("messageTs:");
        sb.append(this.f541a);
        if (m427c()) {
            sb.append(", ");
            sb.append("topic:");
            sb.append(this.f547b == null ? "null" : this.f547b);
        }
        if (m428d()) {
            sb.append(", ");
            sb.append("title:");
            sb.append(this.f550c == null ? "null" : this.f550c);
        }
        if (e()) {
            sb.append(", ");
            sb.append("description:");
            sb.append(this.f552d == null ? "null" : this.f552d);
        }
        if (f()) {
            sb.append(", ");
            sb.append("notifyType:");
            sb.append(this.f540a);
        }
        if (g()) {
            sb.append(", ");
            sb.append("url:");
            sb.append(this.f553e == null ? "null" : this.f553e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("passThrough:");
            sb.append(this.f546b);
        }
        if (i()) {
            sb.append(", ");
            sb.append("notifyId:");
            sb.append(this.f549c);
        }
        if (j()) {
            sb.append(", ");
            sb.append("extra:");
            if (this.f544a == null) {
                sb.append("null");
            } else {
                sb.append(this.f544a);
            }
        }
        if (k()) {
            sb.append(", ");
            sb.append("internal:");
            if (this.f548b == null) {
                sb.append("null");
            } else {
                sb.append(this.f548b);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("ignoreRegInfo:");
            sb.append(this.f545a);
        }
        if (n()) {
            sb.append(", ");
            sb.append("apsProperFields:");
            if (this.f551c == null) {
                sb.append("null");
            } else {
                sb.append(this.f551c);
            }
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
