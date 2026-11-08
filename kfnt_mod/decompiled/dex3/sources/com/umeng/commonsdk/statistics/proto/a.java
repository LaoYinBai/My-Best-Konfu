package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.aa;
import com.umeng.commonsdk.proguard.af;
import com.umeng.commonsdk.proguard.ag;
import com.umeng.commonsdk.proguard.am;
import com.umeng.commonsdk.proguard.an;
import com.umeng.commonsdk.proguard.as;
import com.umeng.commonsdk.proguard.at;
import com.umeng.commonsdk.proguard.av;
import com.umeng.commonsdk.proguard.ax;
import com.umeng.commonsdk.proguard.ay;
import com.umeng.commonsdk.proguard.ba;
import com.umeng.commonsdk.proguard.bb;
import com.umeng.commonsdk.proguard.bc;
import com.umeng.commonsdk.proguard.bd;
import com.umeng.commonsdk.proguard.be;
import com.umeng.commonsdk.proguard.q;
import com.umeng.commonsdk.proguard.t;
import com.umeng.commonsdk.proguard.z;
import com.umeng.message.proguard.l;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements t<a, e>, Serializable, Cloneable {
    public static final Map<e, af> e;
    private static final long f = 9132678615281394583L;
    private static final ax g = new ax("IdJournal");
    private static final an h = new an("domain", (byte) 11, 1);
    private static final an i = new an("old_id", (byte) 11, 2);
    private static final an j = new an("new_id", (byte) 11, 3);
    private static final an k = new an("ts", (byte) 10, 4);
    private static final Map<Class<? extends ba>, bb> l = new HashMap();
    private static final int m = 0;
    public String a;
    public String b;
    public String c;
    public long d;
    private byte n;
    private e[] o;

    static {
        l.put(bc.class, new b());
        l.put(bd.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.DOMAIN, (e) new af("domain", (byte) 1, new ag((byte) 11)));
        enumMap.put((EnumMap) e.OLD_ID, (e) new af("old_id", (byte) 2, new ag((byte) 11)));
        enumMap.put((EnumMap) e.NEW_ID, (e) new af("new_id", (byte) 1, new ag((byte) 11)));
        enumMap.put((EnumMap) e.TS, (e) new af("ts", (byte) 1, new ag((byte) 10)));
        e = Collections.unmodifiableMap(enumMap);
        af.a(a.class, e);
    }

    public enum e implements aa {
        DOMAIN(1, "domain"),
        OLD_ID(2, "old_id"),
        NEW_ID(3, "new_id"),
        TS(4, "ts");

        private static final Map<String, e> e = new HashMap();
        private final short f;
        private final String g;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                e.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return DOMAIN;
                case 2:
                    return OLD_ID;
                case 3:
                    return NEW_ID;
                case 4:
                    return TS;
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
            return e.get(str);
        }

        e(short s, String str) {
            this.f = s;
            this.g = str;
        }

        @Override // com.umeng.commonsdk.proguard.aa
        public short a() {
            return this.f;
        }

        @Override // com.umeng.commonsdk.proguard.aa
        public String b() {
            return this.g;
        }
    }

    public a() {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
    }

    public a(String str, String str2, long j2) {
        this();
        this.a = str;
        this.c = str2;
        this.d = j2;
        d(true);
    }

    public a(a aVar) {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
        this.n = aVar.n;
        if (aVar.d()) {
            this.a = aVar.a;
        }
        if (aVar.g()) {
            this.b = aVar.b;
        }
        if (aVar.j()) {
            this.c = aVar.c;
        }
        this.d = aVar.d;
    }

    @Override // com.umeng.commonsdk.proguard.t
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public a deepCopy() {
        return new a(this);
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0L;
    }

    public String b() {
        return this.a;
    }

    public a a(String str) {
        this.a = str;
        return this;
    }

    public void c() {
        this.a = null;
    }

    public boolean d() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.a = null;
    }

    public String e() {
        return this.b;
    }

    public a b(String str) {
        this.b = str;
        return this;
    }

    public void f() {
        this.b = null;
    }

    public boolean g() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (z) {
            return;
        }
        this.b = null;
    }

    public String h() {
        return this.c;
    }

    public a c(String str) {
        this.c = str;
        return this;
    }

    public void i() {
        this.c = null;
    }

    public boolean j() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.c = null;
    }

    public long k() {
        return this.d;
    }

    public a a(long j2) {
        this.d = j2;
        d(true);
        return this;
    }

    public void l() {
        this.n = q.b(this.n, 0);
    }

    public boolean m() {
        return q.a(this.n, 0);
    }

    public void d(boolean z) {
        this.n = q.a(this.n, 0, z);
    }

    @Override // com.umeng.commonsdk.proguard.t
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void read(as asVar) throws z {
        l.get(asVar.D()).b().b(asVar, this);
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void write(as asVar) throws z {
        l.get(asVar.D()).b().a(asVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdJournal(");
        sb.append("domain:");
        if (this.a == null) {
            sb.append("null");
        } else {
            sb.append(this.a);
        }
        if (g()) {
            sb.append(", ");
            sb.append("old_id:");
            if (this.b == null) {
                sb.append("null");
            } else {
                sb.append(this.b);
            }
        }
        sb.append(", ");
        sb.append("new_id:");
        if (this.c == null) {
            sb.append("null");
        } else {
            sb.append(this.c);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.d);
        sb.append(l.t);
        return sb.toString();
    }

    public void n() throws z {
        if (this.a == null) {
            throw new at("Required field 'domain' was not present! Struct: " + toString());
        }
        if (this.c != null) {
            return;
        }
        throw new at("Required field 'new_id' was not present! Struct: " + toString());
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
            this.n = (byte) 0;
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
        public C0145a b() {
            return new C0145a();
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.a$a, reason: collision with other inner class name */
    private static class C0145a extends bc<a> {
        private C0145a() {
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(as asVar, a aVar) throws z {
            asVar.j();
            while (true) {
                an anVarL = asVar.l();
                if (anVarL.b != 0) {
                    switch (anVarL.c) {
                        case 1:
                            if (anVarL.b == 11) {
                                aVar.a = asVar.z();
                                aVar.a(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 2:
                            if (anVarL.b == 11) {
                                aVar.b = asVar.z();
                                aVar.b(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 3:
                            if (anVarL.b == 11) {
                                aVar.c = asVar.z();
                                aVar.c(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 4:
                            if (anVarL.b == 10) {
                                aVar.d = asVar.x();
                                aVar.d(true);
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
                    if (!aVar.m()) {
                        throw new at("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    }
                    aVar.n();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(as asVar, a aVar) throws z {
            aVar.n();
            asVar.a(a.g);
            if (aVar.a != null) {
                asVar.a(a.h);
                asVar.a(aVar.a);
                asVar.c();
            }
            if (aVar.b != null && aVar.g()) {
                asVar.a(a.i);
                asVar.a(aVar.b);
                asVar.c();
            }
            if (aVar.c != null) {
                asVar.a(a.j);
                asVar.a(aVar.c);
                asVar.c();
            }
            asVar.a(a.k);
            asVar.a(aVar.d);
            asVar.c();
            asVar.d();
            asVar.b();
        }
    }

    private static class d implements bb {
        private d() {
        }

        @Override // com.umeng.commonsdk.proguard.bb
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c b() {
            return new c();
        }
    }

    private static class c extends bd<a> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.ba
        public void a(as asVar, a aVar) throws z {
            ay ayVar = (ay) asVar;
            ayVar.a(aVar.a);
            ayVar.a(aVar.c);
            ayVar.a(aVar.d);
            BitSet bitSet = new BitSet();
            if (aVar.g()) {
                bitSet.set(0);
            }
            ayVar.a(bitSet, 1);
            if (aVar.g()) {
                ayVar.a(aVar.b);
            }
        }

        @Override // com.umeng.commonsdk.proguard.ba
        public void b(as asVar, a aVar) throws z {
            ay ayVar = (ay) asVar;
            aVar.a = ayVar.z();
            aVar.a(true);
            aVar.c = ayVar.z();
            aVar.c(true);
            aVar.d = ayVar.x();
            aVar.d(true);
            if (ayVar.b(1).get(0)) {
                aVar.b = ayVar.z();
                aVar.b(true);
            }
        }
    }
}
