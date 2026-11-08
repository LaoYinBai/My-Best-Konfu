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
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b implements t<b, e>, Serializable, Cloneable {
    public static final Map<e, af> d;
    private static final long e = -6496538196005191531L;
    private static final ax f = new ax("IdSnapshot");
    private static final an g = new an("identity", (byte) 11, 1);
    private static final an h = new an("ts", (byte) 10, 2);
    private static final an i = new an("version", (byte) 8, 3);
    private static final Map<Class<? extends ba>, bb> j = new HashMap();
    private static final int k = 0;
    private static final int l = 1;
    public String a;
    public long b;
    public int c;
    private byte m;

    static {
        j.put(bc.class, new C0146b());
        j.put(bd.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.IDENTITY, (e) new af("identity", (byte) 1, new ag((byte) 11)));
        enumMap.put((EnumMap) e.TS, (e) new af("ts", (byte) 1, new ag((byte) 10)));
        enumMap.put((EnumMap) e.VERSION, (e) new af("version", (byte) 1, new ag((byte) 8)));
        d = Collections.unmodifiableMap(enumMap);
        af.a(b.class, d);
    }

    public enum e implements aa {
        IDENTITY(1, "identity"),
        TS(2, "ts"),
        VERSION(3, "version");

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
                    return IDENTITY;
                case 2:
                    return TS;
                case 3:
                    return VERSION;
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

    public b() {
        this.m = (byte) 0;
    }

    public b(String str, long j2, int i2) {
        this();
        this.a = str;
        this.b = j2;
        b(true);
        this.c = i2;
        c(true);
    }

    public b(b bVar) {
        this.m = (byte) 0;
        this.m = bVar.m;
        if (bVar.d()) {
            this.a = bVar.a;
        }
        this.b = bVar.b;
        this.c = bVar.c;
    }

    @Override // com.umeng.commonsdk.proguard.t
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public b deepCopy() {
        return new b(this);
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void clear() {
        this.a = null;
        b(false);
        this.b = 0L;
        c(false);
        this.c = 0;
    }

    public String b() {
        return this.a;
    }

    public b a(String str) {
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

    public long e() {
        return this.b;
    }

    public b a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public void f() {
        this.m = q.b(this.m, 0);
    }

    public boolean g() {
        return q.a(this.m, 0);
    }

    public void b(boolean z) {
        this.m = q.a(this.m, 0, z);
    }

    public int h() {
        return this.c;
    }

    public b a(int i2) {
        this.c = i2;
        c(true);
        return this;
    }

    public void i() {
        this.m = q.b(this.m, 1);
    }

    public boolean j() {
        return q.a(this.m, 1);
    }

    public void c(boolean z) {
        this.m = q.a(this.m, 1, z);
    }

    @Override // com.umeng.commonsdk.proguard.t
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
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
        StringBuilder sb = new StringBuilder("IdSnapshot(");
        sb.append("identity:");
        if (this.a == null) {
            sb.append("null");
        } else {
            sb.append(this.a);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("version:");
        sb.append(this.c);
        sb.append(l.t);
        return sb.toString();
    }

    public void k() throws z {
        if (this.a != null) {
            return;
        }
        throw new at("Required field 'identity' was not present! Struct: " + toString());
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
            this.m = (byte) 0;
            read(new am(new be(objectInputStream)));
        } catch (z e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.b$b, reason: collision with other inner class name */
    private static class C0146b implements bb {
        private C0146b() {
        }

        @Override // com.umeng.commonsdk.proguard.bb
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    private static class a extends bc<b> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(as asVar, b bVar) throws z {
            asVar.j();
            while (true) {
                an anVarL = asVar.l();
                if (anVarL.b != 0) {
                    switch (anVarL.c) {
                        case 1:
                            if (anVarL.b == 11) {
                                bVar.a = asVar.z();
                                bVar.a(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 2:
                            if (anVarL.b == 10) {
                                bVar.b = asVar.x();
                                bVar.b(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 3:
                            if (anVarL.b == 8) {
                                bVar.c = asVar.w();
                                bVar.c(true);
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
                    if (!bVar.g()) {
                        throw new at("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    }
                    if (!bVar.j()) {
                        throw new at("Required field 'version' was not found in serialized data! Struct: " + toString());
                    }
                    bVar.k();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(as asVar, b bVar) throws z {
            bVar.k();
            asVar.a(b.f);
            if (bVar.a != null) {
                asVar.a(b.g);
                asVar.a(bVar.a);
                asVar.c();
            }
            asVar.a(b.h);
            asVar.a(bVar.b);
            asVar.c();
            asVar.a(b.i);
            asVar.a(bVar.c);
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

    private static class c extends bd<b> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.ba
        public void a(as asVar, b bVar) throws z {
            ay ayVar = (ay) asVar;
            ayVar.a(bVar.a);
            ayVar.a(bVar.b);
            ayVar.a(bVar.c);
        }

        @Override // com.umeng.commonsdk.proguard.ba
        public void b(as asVar, b bVar) throws z {
            ay ayVar = (ay) asVar;
            bVar.a = ayVar.z();
            bVar.a(true);
            bVar.b = ayVar.x();
            bVar.b(true);
            bVar.c = ayVar.w();
            bVar.c(true);
        }
    }
}
