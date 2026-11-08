package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.aa;
import com.umeng.commonsdk.proguard.af;
import com.umeng.commonsdk.proguard.ag;
import com.umeng.commonsdk.proguard.ah;
import com.umeng.commonsdk.proguard.ai;
import com.umeng.commonsdk.proguard.ak;
import com.umeng.commonsdk.proguard.am;
import com.umeng.commonsdk.proguard.an;
import com.umeng.commonsdk.proguard.ao;
import com.umeng.commonsdk.proguard.ap;
import com.umeng.commonsdk.proguard.as;
import com.umeng.commonsdk.proguard.at;
import com.umeng.commonsdk.proguard.av;
import com.umeng.commonsdk.proguard.ax;
import com.umeng.commonsdk.proguard.ay;
import com.umeng.commonsdk.proguard.az;
import com.umeng.commonsdk.proguard.ba;
import com.umeng.commonsdk.proguard.bb;
import com.umeng.commonsdk.proguard.bc;
import com.umeng.commonsdk.proguard.bd;
import com.umeng.commonsdk.proguard.be;
import com.umeng.commonsdk.proguard.t;
import com.umeng.commonsdk.proguard.z;
import com.umeng.message.proguard.l;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c implements t<c, e>, Serializable, Cloneable {
    public static final Map<e, af> d;
    private static final long e = -5764118265293965743L;
    private static final ax f = new ax("IdTracking");
    private static final an g = new an("snapshots", az.k, 1);
    private static final an h = new an("journals", az.m, 2);
    private static final an i = new an("checksum", (byte) 11, 3);
    private static final Map<Class<? extends ba>, bb> j = new HashMap();
    public Map<String, com.umeng.commonsdk.statistics.proto.b> a;
    public List<com.umeng.commonsdk.statistics.proto.a> b;
    public String c;
    private e[] k;

    static {
        j.put(bc.class, new b());
        j.put(bd.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.SNAPSHOTS, (e) new af("snapshots", (byte) 1, new ai(az.k, new ag((byte) 11), new ak((byte) 12, com.umeng.commonsdk.statistics.proto.b.class))));
        enumMap.put((EnumMap) e.JOURNALS, (e) new af("journals", (byte) 2, new ah(az.m, new ak((byte) 12, com.umeng.commonsdk.statistics.proto.a.class))));
        enumMap.put((EnumMap) e.CHECKSUM, (e) new af("checksum", (byte) 2, new ag((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        af.a(c.class, d);
    }

    public enum e implements aa {
        SNAPSHOTS(1, "snapshots"),
        JOURNALS(2, "journals"),
        CHECKSUM(3, "checksum");

        private static final Map<String, e> d = new HashMap();
        private final short e;
        private final String f;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return SNAPSHOTS;
                case 2:
                    return JOURNALS;
                case 3:
                    return CHECKSUM;
                default:
                    return null;
            }
        }

        public static e b(int i) {
            e eVarA = a(i);
            if (eVarA != null) {
                return eVarA;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return d.get(str);
        }

        e(short s, String str) {
            this.e = s;
            this.f = str;
        }

        @Override // com.umeng.commonsdk.proguard.aa
        public short a() {
            return this.e;
        }

        @Override // com.umeng.commonsdk.proguard.aa
        public String b() {
            return this.f;
        }
    }

    public c() {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
    }

    public c(Map<String, com.umeng.commonsdk.statistics.proto.b> map) {
        this();
        this.a = map;
    }

    public c(c cVar) {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
        if (cVar.e()) {
            HashMap map = new HashMap();
            for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.b> entry : cVar.a.entrySet()) {
                map.put(entry.getKey(), new com.umeng.commonsdk.statistics.proto.b(entry.getValue()));
            }
            this.a = map;
        }
        if (cVar.j()) {
            ArrayList arrayList = new ArrayList();
            Iterator<com.umeng.commonsdk.statistics.proto.a> it = cVar.b.iterator();
            while (it.hasNext()) {
                arrayList.add(new com.umeng.commonsdk.statistics.proto.a(it.next()));
            }
            this.b = arrayList;
        }
        if (cVar.m()) {
            this.c = cVar.c;
        }
    }

    @Override // com.umeng.commonsdk.proguard.t
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public c deepCopy() {
        return new c(this);
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public int b() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public void a(String str, com.umeng.commonsdk.statistics.proto.b bVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, bVar);
    }

    public Map<String, com.umeng.commonsdk.statistics.proto.b> c() {
        return this.a;
    }

    public c a(Map<String, com.umeng.commonsdk.statistics.proto.b> map) {
        this.a = map;
        return this;
    }

    public void d() {
        this.a = null;
    }

    public boolean e() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.a = null;
    }

    public int f() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }

    public Iterator<com.umeng.commonsdk.statistics.proto.a> g() {
        if (this.b == null) {
            return null;
        }
        return this.b.iterator();
    }

    public void a(com.umeng.commonsdk.statistics.proto.a aVar) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(aVar);
    }

    public List<com.umeng.commonsdk.statistics.proto.a> h() {
        return this.b;
    }

    public c a(List<com.umeng.commonsdk.statistics.proto.a> list) {
        this.b = list;
        return this;
    }

    public void i() {
        this.b = null;
    }

    public boolean j() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (z) {
            return;
        }
        this.b = null;
    }

    public String k() {
        return this.c;
    }

    public c a(String str) {
        this.c = str;
        return this;
    }

    public void l() {
        this.c = null;
    }

    public boolean m() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.c = null;
    }

    @Override // com.umeng.commonsdk.proguard.t
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void read(as asVar) throws z {
        j.get(asVar.D()).b().b(asVar, this);
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void write(as asVar) throws z {
        j.get(asVar.D()).b().a(asVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdTracking(");
        sb.append("snapshots:");
        if (this.a == null) {
            sb.append("null");
        } else {
            sb.append(this.a);
        }
        if (j()) {
            sb.append(", ");
            sb.append("journals:");
            if (this.b == null) {
                sb.append("null");
            } else {
                sb.append(this.b);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("checksum:");
            if (this.c == null) {
                sb.append("null");
            } else {
                sb.append(this.c);
            }
        }
        sb.append(l.t);
        return sb.toString();
    }

    public void n() throws z {
        if (this.a != null) {
            return;
        }
        throw new at("Required field 'snapshots' was not present! Struct: " + toString());
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new am(new be(objectOutputStream)));
        } catch (z e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            read(new am(new be(objectInputStream)));
        } catch (z e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private static class b implements bb {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.bb
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    private static class a extends bc<c> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(as asVar, c cVar) throws z {
            asVar.j();
            while (true) {
                an anVarL = asVar.l();
                if (anVarL.b != 0) {
                    int i = 0;
                    switch (anVarL.c) {
                        case 1:
                            if (anVarL.b == 13) {
                                ap apVarN = asVar.n();
                                cVar.a = new HashMap(apVarN.c * 2);
                                while (i < apVarN.c) {
                                    String strZ = asVar.z();
                                    com.umeng.commonsdk.statistics.proto.b bVar = new com.umeng.commonsdk.statistics.proto.b();
                                    bVar.read(asVar);
                                    cVar.a.put(strZ, bVar);
                                    i++;
                                }
                                asVar.o();
                                cVar.a(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 2:
                            if (anVarL.b == 15) {
                                ao aoVarP = asVar.p();
                                cVar.b = new ArrayList(aoVarP.b);
                                while (i < aoVarP.b) {
                                    com.umeng.commonsdk.statistics.proto.a aVar = new com.umeng.commonsdk.statistics.proto.a();
                                    aVar.read(asVar);
                                    cVar.b.add(aVar);
                                    i++;
                                }
                                asVar.q();
                                cVar.b(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 3:
                            if (anVarL.b == 11) {
                                cVar.c = asVar.z();
                                cVar.c(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        default:
                            av.a(asVar, anVarL.b);
                            break;
                    }
                    asVar.m();
                } else {
                    asVar.k();
                    cVar.n();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(as asVar, c cVar) throws z {
            cVar.n();
            asVar.a(c.f);
            if (cVar.a != null) {
                asVar.a(c.g);
                asVar.a(new ap((byte) 11, (byte) 12, cVar.a.size()));
                for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.b> entry : cVar.a.entrySet()) {
                    asVar.a(entry.getKey());
                    entry.getValue().write(asVar);
                }
                asVar.e();
                asVar.c();
            }
            if (cVar.b != null && cVar.j()) {
                asVar.a(c.h);
                asVar.a(new ao((byte) 12, cVar.b.size()));
                Iterator<com.umeng.commonsdk.statistics.proto.a> it = cVar.b.iterator();
                while (it.hasNext()) {
                    it.next().write(asVar);
                }
                asVar.f();
                asVar.c();
            }
            if (cVar.c != null && cVar.m()) {
                asVar.a(c.i);
                asVar.a(cVar.c);
                asVar.c();
            }
            asVar.d();
            asVar.b();
        }
    }

    private static class d implements bb {
        private d() {
        }

        @Override // com.umeng.commonsdk.proguard.bb
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0147c b() {
            return new C0147c();
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.c$c, reason: collision with other inner class name */
    private static class C0147c extends bd<c> {
        private C0147c() {
        }

        @Override // com.umeng.commonsdk.proguard.ba
        public void a(as asVar, c cVar) throws z {
            ay ayVar = (ay) asVar;
            ayVar.a(cVar.a.size());
            for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.b> entry : cVar.a.entrySet()) {
                ayVar.a(entry.getKey());
                entry.getValue().write(ayVar);
            }
            BitSet bitSet = new BitSet();
            if (cVar.j()) {
                bitSet.set(0);
            }
            if (cVar.m()) {
                bitSet.set(1);
            }
            ayVar.a(bitSet, 2);
            if (cVar.j()) {
                ayVar.a(cVar.b.size());
                Iterator<com.umeng.commonsdk.statistics.proto.a> it = cVar.b.iterator();
                while (it.hasNext()) {
                    it.next().write(ayVar);
                }
            }
            if (cVar.m()) {
                ayVar.a(cVar.c);
            }
        }

        @Override // com.umeng.commonsdk.proguard.ba
        public void b(as asVar, c cVar) throws z {
            ay ayVar = (ay) asVar;
            ap apVar = new ap((byte) 11, (byte) 12, ayVar.w());
            cVar.a = new HashMap(apVar.c * 2);
            for (int i = 0; i < apVar.c; i++) {
                String strZ = ayVar.z();
                com.umeng.commonsdk.statistics.proto.b bVar = new com.umeng.commonsdk.statistics.proto.b();
                bVar.read(ayVar);
                cVar.a.put(strZ, bVar);
            }
            cVar.a(true);
            BitSet bitSetB = ayVar.b(2);
            if (bitSetB.get(0)) {
                ao aoVar = new ao((byte) 12, ayVar.w());
                cVar.b = new ArrayList(aoVar.b);
                for (int i2 = 0; i2 < aoVar.b; i2++) {
                    com.umeng.commonsdk.statistics.proto.a aVar = new com.umeng.commonsdk.statistics.proto.a();
                    aVar.read(ayVar);
                    cVar.b.add(aVar);
                }
                cVar.b(true);
            }
            if (bitSetB.get(1)) {
                cVar.c = ayVar.z();
                cVar.c(true);
            }
        }
    }
}
