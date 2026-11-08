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
public class e implements t<e, EnumC0149e>, Serializable, Cloneable {
    public static final Map<EnumC0149e, af> d;
    private static final long e = 7501688097813630241L;
    private static final ax f = new ax("ImprintValue");
    private static final an g = new an("value", (byte) 11, 1);
    private static final an h = new an("ts", (byte) 10, 2);
    private static final an i = new an("guid", (byte) 11, 3);
    private static final Map<Class<? extends ba>, bb> j = new HashMap();
    private static final int k = 0;
    public String a;
    public long b;
    public String c;
    private byte l;
    private EnumC0149e[] m;

    static {
        j.put(bc.class, new b());
        j.put(bd.class, new d());
        EnumMap enumMap = new EnumMap(EnumC0149e.class);
        enumMap.put((EnumMap) EnumC0149e.VALUE, (EnumC0149e) new af("value", (byte) 2, new ag((byte) 11)));
        enumMap.put((EnumMap) EnumC0149e.TS, (EnumC0149e) new af("ts", (byte) 1, new ag((byte) 10)));
        enumMap.put((EnumMap) EnumC0149e.GUID, (EnumC0149e) new af("guid", (byte) 1, new ag((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        af.a(e.class, d);
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.e$e, reason: collision with other inner class name */
    public enum EnumC0149e implements aa {
        VALUE(1, "value"),
        TS(2, "ts"),
        GUID(3, "guid");

        private static final Map<String, EnumC0149e> d = new HashMap();
        private final short e;
        private final String f;

        static {
            Iterator it = EnumSet.allOf(EnumC0149e.class).iterator();
            while (it.hasNext()) {
                EnumC0149e enumC0149e = (EnumC0149e) it.next();
                d.put(enumC0149e.b(), enumC0149e);
            }
        }

        public static EnumC0149e a(int i) {
            switch (i) {
                case 1:
                    return VALUE;
                case 2:
                    return TS;
                case 3:
                    return GUID;
                default:
                    return null;
            }
        }

        public static EnumC0149e b(int i) {
            EnumC0149e enumC0149eA = a(i);
            if (enumC0149eA != null) {
                return enumC0149eA;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static EnumC0149e a(String str) {
            return d.get(str);
        }

        EnumC0149e(short s, String str) {
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

    public e() {
        this.l = (byte) 0;
        this.m = new EnumC0149e[]{EnumC0149e.VALUE};
    }

    public e(long j2, String str) {
        this();
        this.b = j2;
        b(true);
        this.c = str;
    }

    public e(e eVar) {
        this.l = (byte) 0;
        this.m = new EnumC0149e[]{EnumC0149e.VALUE};
        this.l = eVar.l;
        if (eVar.d()) {
            this.a = eVar.a;
        }
        this.b = eVar.b;
        if (eVar.j()) {
            this.c = eVar.c;
        }
    }

    @Override // com.umeng.commonsdk.proguard.t
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public e deepCopy() {
        return new e(this);
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void clear() {
        this.a = null;
        b(false);
        this.b = 0L;
        this.c = null;
    }

    public String b() {
        return this.a;
    }

    public e a(String str) {
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

    public e a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public void f() {
        this.l = q.b(this.l, 0);
    }

    public boolean g() {
        return q.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = q.a(this.l, 0, z);
    }

    public String h() {
        return this.c;
    }

    public e b(String str) {
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

    @Override // com.umeng.commonsdk.proguard.t
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public EnumC0149e fieldForId(int i2) {
        return EnumC0149e.a(i2);
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
        boolean z;
        StringBuilder sb = new StringBuilder("ImprintValue(");
        if (d()) {
            sb.append("value:");
            if (this.a == null) {
                sb.append("null");
            } else {
                sb.append(this.a);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("ts:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("guid:");
        if (this.c == null) {
            sb.append("null");
        } else {
            sb.append(this.c);
        }
        sb.append(l.t);
        return sb.toString();
    }

    public void k() throws z {
        if (this.c != null) {
            return;
        }
        throw new at("Required field 'guid' was not present! Struct: " + toString());
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
            this.l = (byte) 0;
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

    private static class a extends bc<e> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(as asVar, e eVar) throws z {
            asVar.j();
            while (true) {
                an anVarL = asVar.l();
                if (anVarL.b != 0) {
                    switch (anVarL.c) {
                        case 1:
                            if (anVarL.b == 11) {
                                eVar.a = asVar.z();
                                eVar.a(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 2:
                            if (anVarL.b == 10) {
                                eVar.b = asVar.x();
                                eVar.b(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 3:
                            if (anVarL.b == 11) {
                                eVar.c = asVar.z();
                                eVar.c(true);
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
                    if (!eVar.g()) {
                        throw new at("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    }
                    eVar.k();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(as asVar, e eVar) throws z {
            eVar.k();
            asVar.a(e.f);
            if (eVar.a != null && eVar.d()) {
                asVar.a(e.g);
                asVar.a(eVar.a);
                asVar.c();
            }
            asVar.a(e.h);
            asVar.a(eVar.b);
            asVar.c();
            if (eVar.c != null) {
                asVar.a(e.i);
                asVar.a(eVar.c);
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

    private static class c extends bd<e> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.ba
        public void a(as asVar, e eVar) throws z {
            ay ayVar = (ay) asVar;
            ayVar.a(eVar.b);
            ayVar.a(eVar.c);
            BitSet bitSet = new BitSet();
            if (eVar.d()) {
                bitSet.set(0);
            }
            ayVar.a(bitSet, 1);
            if (eVar.d()) {
                ayVar.a(eVar.a);
            }
        }

        @Override // com.umeng.commonsdk.proguard.ba
        public void b(as asVar, e eVar) throws z {
            ay ayVar = (ay) asVar;
            eVar.b = ayVar.x();
            eVar.b(true);
            eVar.c = ayVar.z();
            eVar.c(true);
            if (ayVar.b(1).get(0)) {
                eVar.a = ayVar.z();
                eVar.a(true);
            }
        }
    }
}
