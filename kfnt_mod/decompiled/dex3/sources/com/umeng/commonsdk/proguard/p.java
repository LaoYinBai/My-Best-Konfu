package com.umeng.commonsdk.proguard;

import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class p implements t<p, e>, Serializable, Cloneable {
    private static final int A = 2;
    private static final int B = 3;
    public static final Map<e, af> k;
    private static final long l = 420342210744516016L;
    private static final ax m = new ax("UMEnvelope");
    private static final an n = new an("version", (byte) 11, 1);
    private static final an o = new an("address", (byte) 11, 2);
    private static final an p = new an(InAppPurchaseMetaData.KEY_SIGNATURE, (byte) 11, 3);
    private static final an q = new an("serial_num", (byte) 8, 4);
    private static final an r = new an("ts_secs", (byte) 8, 5);
    private static final an s = new an("length", (byte) 8, 6);
    private static final an t = new an("entity", (byte) 11, 7);
    private static final an u = new an("guid", (byte) 11, 8);
    private static final an v = new an("checksum", (byte) 11, 9);
    private static final an w = new an("codex", (byte) 8, 10);
    private static final Map<Class<? extends ba>, bb> x = new HashMap();
    private static final int y = 0;
    private static final int z = 1;
    private byte C;
    private e[] D;
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;

    static {
        x.put(bc.class, new b());
        x.put(bd.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.VERSION, (e) new af("version", (byte) 1, new ag((byte) 11)));
        enumMap.put((EnumMap) e.ADDRESS, (e) new af("address", (byte) 1, new ag((byte) 11)));
        enumMap.put((EnumMap) e.SIGNATURE, (e) new af(InAppPurchaseMetaData.KEY_SIGNATURE, (byte) 1, new ag((byte) 11)));
        enumMap.put((EnumMap) e.SERIAL_NUM, (e) new af("serial_num", (byte) 1, new ag((byte) 8)));
        enumMap.put((EnumMap) e.TS_SECS, (e) new af("ts_secs", (byte) 1, new ag((byte) 8)));
        enumMap.put((EnumMap) e.LENGTH, (e) new af("length", (byte) 1, new ag((byte) 8)));
        enumMap.put((EnumMap) e.ENTITY, (e) new af("entity", (byte) 1, new ag((byte) 11, true)));
        enumMap.put((EnumMap) e.GUID, (e) new af("guid", (byte) 1, new ag((byte) 11)));
        enumMap.put((EnumMap) e.CHECKSUM, (e) new af("checksum", (byte) 1, new ag((byte) 11)));
        enumMap.put((EnumMap) e.CODEX, (e) new af("codex", (byte) 2, new ag((byte) 8)));
        k = Collections.unmodifiableMap(enumMap);
        af.a(p.class, k);
    }

    public enum e implements aa {
        VERSION(1, "version"),
        ADDRESS(2, "address"),
        SIGNATURE(3, InAppPurchaseMetaData.KEY_SIGNATURE),
        SERIAL_NUM(4, "serial_num"),
        TS_SECS(5, "ts_secs"),
        LENGTH(6, "length"),
        ENTITY(7, "entity"),
        GUID(8, "guid"),
        CHECKSUM(9, "checksum"),
        CODEX(10, "codex");

        private static final Map<String, e> k = new HashMap();
        private final short l;
        private final String m;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                k.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case 7:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
                    return CHECKSUM;
                case 10:
                    return CODEX;
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
            return k.get(str);
        }

        e(short s, String str) {
            this.l = s;
            this.m = str;
        }

        @Override // com.umeng.commonsdk.proguard.aa
        public short a() {
            return this.l;
        }

        @Override // com.umeng.commonsdk.proguard.aa
        public String b() {
            return this.m;
        }
    }

    public p() {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
    }

    public p(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = i;
        d(true);
        this.e = i2;
        e(true);
        this.f = i3;
        f(true);
        this.g = byteBuffer;
        this.h = str4;
        this.i = str5;
    }

    public p(p pVar) {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
        this.C = pVar.C;
        if (pVar.d()) {
            this.a = pVar.a;
        }
        if (pVar.g()) {
            this.b = pVar.b;
        }
        if (pVar.j()) {
            this.c = pVar.c;
        }
        this.d = pVar.d;
        this.e = pVar.e;
        this.f = pVar.f;
        if (pVar.w()) {
            this.g = u.d(pVar.g);
        }
        if (pVar.z()) {
            this.h = pVar.h;
        }
        if (pVar.C()) {
            this.i = pVar.i;
        }
        this.j = pVar.j;
    }

    @Override // com.umeng.commonsdk.proguard.t
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public p deepCopy() {
        return new p(this);
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0;
        e(false);
        this.e = 0;
        f(false);
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        j(false);
        this.j = 0;
    }

    public String b() {
        return this.a;
    }

    public p a(String str) {
        this.a = str;
        return this;
    }

    public void c() {
        this.a = null;
    }

    public boolean d() {
        return this.a != null;
    }

    public void a(boolean z2) {
        if (z2) {
            return;
        }
        this.a = null;
    }

    public String e() {
        return this.b;
    }

    public p b(String str) {
        this.b = str;
        return this;
    }

    public void f() {
        this.b = null;
    }

    public boolean g() {
        return this.b != null;
    }

    public void b(boolean z2) {
        if (z2) {
            return;
        }
        this.b = null;
    }

    public String h() {
        return this.c;
    }

    public p c(String str) {
        this.c = str;
        return this;
    }

    public void i() {
        this.c = null;
    }

    public boolean j() {
        return this.c != null;
    }

    public void c(boolean z2) {
        if (z2) {
            return;
        }
        this.c = null;
    }

    public int k() {
        return this.d;
    }

    public p a(int i) {
        this.d = i;
        d(true);
        return this;
    }

    public void l() {
        this.C = q.b(this.C, 0);
    }

    public boolean m() {
        return q.a(this.C, 0);
    }

    public void d(boolean z2) {
        this.C = q.a(this.C, 0, z2);
    }

    public int n() {
        return this.e;
    }

    public p b(int i) {
        this.e = i;
        e(true);
        return this;
    }

    public void o() {
        this.C = q.b(this.C, 1);
    }

    public boolean p() {
        return q.a(this.C, 1);
    }

    public void e(boolean z2) {
        this.C = q.a(this.C, 1, z2);
    }

    public int q() {
        return this.f;
    }

    public p c(int i) {
        this.f = i;
        f(true);
        return this;
    }

    public void r() {
        this.C = q.b(this.C, 2);
    }

    public boolean s() {
        return q.a(this.C, 2);
    }

    public void f(boolean z2) {
        this.C = q.a(this.C, 2, z2);
    }

    public byte[] t() {
        a(u.c(this.g));
        if (this.g == null) {
            return null;
        }
        return this.g.array();
    }

    public ByteBuffer u() {
        return this.g;
    }

    public p a(byte[] bArr) {
        a(bArr == null ? (ByteBuffer) null : ByteBuffer.wrap(bArr));
        return this;
    }

    public p a(ByteBuffer byteBuffer) {
        this.g = byteBuffer;
        return this;
    }

    public void v() {
        this.g = null;
    }

    public boolean w() {
        return this.g != null;
    }

    public void g(boolean z2) {
        if (z2) {
            return;
        }
        this.g = null;
    }

    public String x() {
        return this.h;
    }

    public p d(String str) {
        this.h = str;
        return this;
    }

    public void y() {
        this.h = null;
    }

    public boolean z() {
        return this.h != null;
    }

    public void h(boolean z2) {
        if (z2) {
            return;
        }
        this.h = null;
    }

    public String A() {
        return this.i;
    }

    public p e(String str) {
        this.i = str;
        return this;
    }

    public void B() {
        this.i = null;
    }

    public boolean C() {
        return this.i != null;
    }

    public void i(boolean z2) {
        if (z2) {
            return;
        }
        this.i = null;
    }

    public int D() {
        return this.j;
    }

    public p d(int i) {
        this.j = i;
        j(true);
        return this;
    }

    public void E() {
        this.C = q.b(this.C, 3);
    }

    public boolean F() {
        return q.a(this.C, 3);
    }

    public void j(boolean z2) {
        this.C = q.a(this.C, 3, z2);
    }

    @Override // com.umeng.commonsdk.proguard.t
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i) {
        return e.a(i);
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void read(as asVar) throws z {
        x.get(asVar.D()).b().b(asVar, this);
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void write(as asVar) throws z {
        x.get(asVar.D()).b().a(asVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UMEnvelope(");
        sb.append("version:");
        if (this.a == null) {
            sb.append("null");
        } else {
            sb.append(this.a);
        }
        sb.append(", ");
        sb.append("address:");
        if (this.b == null) {
            sb.append("null");
        } else {
            sb.append(this.b);
        }
        sb.append(", ");
        sb.append("signature:");
        if (this.c == null) {
            sb.append("null");
        } else {
            sb.append(this.c);
        }
        sb.append(", ");
        sb.append("serial_num:");
        sb.append(this.d);
        sb.append(", ");
        sb.append("ts_secs:");
        sb.append(this.e);
        sb.append(", ");
        sb.append("length:");
        sb.append(this.f);
        sb.append(", ");
        sb.append("entity:");
        if (this.g == null) {
            sb.append("null");
        } else {
            u.a(this.g, sb);
        }
        sb.append(", ");
        sb.append("guid:");
        if (this.h == null) {
            sb.append("null");
        } else {
            sb.append(this.h);
        }
        sb.append(", ");
        sb.append("checksum:");
        if (this.i == null) {
            sb.append("null");
        } else {
            sb.append(this.i);
        }
        if (F()) {
            sb.append(", ");
            sb.append("codex:");
            sb.append(this.j);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }

    public void G() throws z {
        if (this.a == null) {
            throw new at("Required field 'version' was not present! Struct: " + toString());
        }
        if (this.b == null) {
            throw new at("Required field 'address' was not present! Struct: " + toString());
        }
        if (this.c == null) {
            throw new at("Required field 'signature' was not present! Struct: " + toString());
        }
        if (this.g == null) {
            throw new at("Required field 'entity' was not present! Struct: " + toString());
        }
        if (this.h == null) {
            throw new at("Required field 'guid' was not present! Struct: " + toString());
        }
        if (this.i != null) {
            return;
        }
        throw new at("Required field 'checksum' was not present! Struct: " + toString());
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
            this.C = (byte) 0;
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

    private static class a extends bc<p> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(as asVar, p pVar) throws z {
            asVar.j();
            while (true) {
                an anVarL = asVar.l();
                if (anVarL.b != 0) {
                    switch (anVarL.c) {
                        case 1:
                            if (anVarL.b == 11) {
                                pVar.a = asVar.z();
                                pVar.a(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 2:
                            if (anVarL.b == 11) {
                                pVar.b = asVar.z();
                                pVar.b(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 3:
                            if (anVarL.b == 11) {
                                pVar.c = asVar.z();
                                pVar.c(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 4:
                            if (anVarL.b == 8) {
                                pVar.d = asVar.w();
                                pVar.d(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 5:
                            if (anVarL.b == 8) {
                                pVar.e = asVar.w();
                                pVar.e(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 6:
                            if (anVarL.b == 8) {
                                pVar.f = asVar.w();
                                pVar.f(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 7:
                            if (anVarL.b == 11) {
                                pVar.g = asVar.A();
                                pVar.g(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 8:
                            if (anVarL.b == 11) {
                                pVar.h = asVar.z();
                                pVar.h(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 9:
                            if (anVarL.b == 11) {
                                pVar.i = asVar.z();
                                pVar.i(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 10:
                            if (anVarL.b == 8) {
                                pVar.j = asVar.w();
                                pVar.j(true);
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
                    if (!pVar.m()) {
                        throw new at("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    }
                    if (!pVar.p()) {
                        throw new at("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    }
                    if (!pVar.s()) {
                        throw new at("Required field 'length' was not found in serialized data! Struct: " + toString());
                    }
                    pVar.G();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(as asVar, p pVar) throws z {
            pVar.G();
            asVar.a(p.m);
            if (pVar.a != null) {
                asVar.a(p.n);
                asVar.a(pVar.a);
                asVar.c();
            }
            if (pVar.b != null) {
                asVar.a(p.o);
                asVar.a(pVar.b);
                asVar.c();
            }
            if (pVar.c != null) {
                asVar.a(p.p);
                asVar.a(pVar.c);
                asVar.c();
            }
            asVar.a(p.q);
            asVar.a(pVar.d);
            asVar.c();
            asVar.a(p.r);
            asVar.a(pVar.e);
            asVar.c();
            asVar.a(p.s);
            asVar.a(pVar.f);
            asVar.c();
            if (pVar.g != null) {
                asVar.a(p.t);
                asVar.a(pVar.g);
                asVar.c();
            }
            if (pVar.h != null) {
                asVar.a(p.u);
                asVar.a(pVar.h);
                asVar.c();
            }
            if (pVar.i != null) {
                asVar.a(p.v);
                asVar.a(pVar.i);
                asVar.c();
            }
            if (pVar.F()) {
                asVar.a(p.w);
                asVar.a(pVar.j);
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
        public c b() {
            return new c();
        }
    }

    private static class c extends bd<p> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.ba
        public void a(as asVar, p pVar) throws z {
            ay ayVar = (ay) asVar;
            ayVar.a(pVar.a);
            ayVar.a(pVar.b);
            ayVar.a(pVar.c);
            ayVar.a(pVar.d);
            ayVar.a(pVar.e);
            ayVar.a(pVar.f);
            ayVar.a(pVar.g);
            ayVar.a(pVar.h);
            ayVar.a(pVar.i);
            BitSet bitSet = new BitSet();
            if (pVar.F()) {
                bitSet.set(0);
            }
            ayVar.a(bitSet, 1);
            if (pVar.F()) {
                ayVar.a(pVar.j);
            }
        }

        @Override // com.umeng.commonsdk.proguard.ba
        public void b(as asVar, p pVar) throws z {
            ay ayVar = (ay) asVar;
            pVar.a = ayVar.z();
            pVar.a(true);
            pVar.b = ayVar.z();
            pVar.b(true);
            pVar.c = ayVar.z();
            pVar.c(true);
            pVar.d = ayVar.w();
            pVar.d(true);
            pVar.e = ayVar.w();
            pVar.e(true);
            pVar.f = ayVar.w();
            pVar.f(true);
            pVar.g = ayVar.A();
            pVar.g(true);
            pVar.h = ayVar.z();
            pVar.h(true);
            pVar.i = ayVar.z();
            pVar.i(true);
            if (ayVar.b(1).get(0)) {
                pVar.j = ayVar.w();
                pVar.j(true);
            }
        }
    }
}
