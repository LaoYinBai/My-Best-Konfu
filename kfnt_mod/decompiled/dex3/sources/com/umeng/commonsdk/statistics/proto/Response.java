package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.aa;
import com.umeng.commonsdk.proguard.af;
import com.umeng.commonsdk.proguard.ag;
import com.umeng.commonsdk.proguard.ak;
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
import com.umeng.commonsdk.proguard.o;
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
public class Response implements t<Response, e>, Serializable, Cloneable {
    private static final int __RESP_CODE_ISSET_ID = 0;
    public static final Map<e, af> metaDataMap;
    private static final long serialVersionUID = -4549277923241195391L;
    private byte __isset_bitfield;
    public com.umeng.commonsdk.statistics.proto.d imprint;
    public String msg;
    private e[] optionals;
    public int resp_code;
    private static final ax STRUCT_DESC = new ax("Response");
    private static final an RESP_CODE_FIELD_DESC = new an("resp_code", (byte) 8, 1);
    private static final an MSG_FIELD_DESC = new an("msg", (byte) 11, 2);
    private static final an IMPRINT_FIELD_DESC = new an(o.U, (byte) 12, 3);
    private static final Map<Class<? extends ba>, bb> schemes = new HashMap();

    static {
        schemes.put(bc.class, new b());
        schemes.put(bd.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.RESP_CODE, (e) new af("resp_code", (byte) 1, new ag((byte) 8)));
        enumMap.put((EnumMap) e.MSG, (e) new af("msg", (byte) 2, new ag((byte) 11)));
        enumMap.put((EnumMap) e.IMPRINT, (e) new af(o.U, (byte) 2, new ak((byte) 12, com.umeng.commonsdk.statistics.proto.d.class)));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        af.a(Response.class, metaDataMap);
    }

    public enum e implements aa {
        RESP_CODE(1, "resp_code"),
        MSG(2, "msg"),
        IMPRINT(3, o.U);

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
                    return RESP_CODE;
                case 2:
                    return MSG;
                case 3:
                    return IMPRINT;
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

    public Response() {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new e[]{e.MSG, e.IMPRINT};
    }

    public Response(int i) {
        this();
        this.resp_code = i;
        setResp_codeIsSet(true);
    }

    public Response(Response response) {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new e[]{e.MSG, e.IMPRINT};
        this.__isset_bitfield = response.__isset_bitfield;
        this.resp_code = response.resp_code;
        if (response.isSetMsg()) {
            this.msg = response.msg;
        }
        if (response.isSetImprint()) {
            this.imprint = new com.umeng.commonsdk.statistics.proto.d(response.imprint);
        }
    }

    @Override // com.umeng.commonsdk.proguard.t
    public Response deepCopy() {
        return new Response(this);
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void clear() {
        setResp_codeIsSet(false);
        this.resp_code = 0;
        this.msg = null;
        this.imprint = null;
    }

    public int getResp_code() {
        return this.resp_code;
    }

    public Response setResp_code(int i) {
        this.resp_code = i;
        setResp_codeIsSet(true);
        return this;
    }

    public void unsetResp_code() {
        this.__isset_bitfield = q.b(this.__isset_bitfield, 0);
    }

    public boolean isSetResp_code() {
        return q.a(this.__isset_bitfield, 0);
    }

    public void setResp_codeIsSet(boolean z) {
        this.__isset_bitfield = q.a(this.__isset_bitfield, 0, z);
    }

    public String getMsg() {
        return this.msg;
    }

    public Response setMsg(String str) {
        this.msg = str;
        return this;
    }

    public void unsetMsg() {
        this.msg = null;
    }

    public boolean isSetMsg() {
        return this.msg != null;
    }

    public void setMsgIsSet(boolean z) {
        if (z) {
            return;
        }
        this.msg = null;
    }

    public com.umeng.commonsdk.statistics.proto.d getImprint() {
        return this.imprint;
    }

    public Response setImprint(com.umeng.commonsdk.statistics.proto.d dVar) {
        this.imprint = dVar;
        return this;
    }

    public void unsetImprint() {
        this.imprint = null;
    }

    public boolean isSetImprint() {
        return this.imprint != null;
    }

    public void setImprintIsSet(boolean z) {
        if (z) {
            return;
        }
        this.imprint = null;
    }

    @Override // com.umeng.commonsdk.proguard.t
    public e fieldForId(int i) {
        return e.a(i);
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void read(as asVar) throws z {
        schemes.get(asVar.D()).b().b(asVar, this);
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void write(as asVar) throws z {
        schemes.get(asVar.D()).b().a(asVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Response(");
        sb.append("resp_code:");
        sb.append(this.resp_code);
        if (isSetMsg()) {
            sb.append(", ");
            sb.append("msg:");
            if (this.msg == null) {
                sb.append("null");
            } else {
                sb.append(this.msg);
            }
        }
        if (isSetImprint()) {
            sb.append(", ");
            sb.append("imprint:");
            if (this.imprint == null) {
                sb.append("null");
            } else {
                sb.append(this.imprint);
            }
        }
        sb.append(l.t);
        return sb.toString();
    }

    public void validate() throws z {
        if (this.imprint != null) {
            this.imprint.l();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new am(new be(objectOutputStream)));
        } catch (z e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.__isset_bitfield = (byte) 0;
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

    private static class a extends bc<Response> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(as asVar, Response response) throws z {
            asVar.j();
            while (true) {
                an anVarL = asVar.l();
                if (anVarL.b != 0) {
                    switch (anVarL.c) {
                        case 1:
                            if (anVarL.b == 8) {
                                response.resp_code = asVar.w();
                                response.setResp_codeIsSet(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 2:
                            if (anVarL.b == 11) {
                                response.msg = asVar.z();
                                response.setMsgIsSet(true);
                                break;
                            } else {
                                av.a(asVar, anVarL.b);
                                break;
                            }
                        case 3:
                            if (anVarL.b == 12) {
                                response.imprint = new com.umeng.commonsdk.statistics.proto.d();
                                response.imprint.read(asVar);
                                response.setImprintIsSet(true);
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
                    if (!response.isSetResp_code()) {
                        throw new at("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
                    }
                    response.validate();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(as asVar, Response response) throws z {
            response.validate();
            asVar.a(Response.STRUCT_DESC);
            asVar.a(Response.RESP_CODE_FIELD_DESC);
            asVar.a(response.resp_code);
            asVar.c();
            if (response.msg != null && response.isSetMsg()) {
                asVar.a(Response.MSG_FIELD_DESC);
                asVar.a(response.msg);
                asVar.c();
            }
            if (response.imprint != null && response.isSetImprint()) {
                asVar.a(Response.IMPRINT_FIELD_DESC);
                response.imprint.write(asVar);
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

    private static class c extends bd<Response> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.ba
        public void a(as asVar, Response response) throws z {
            ay ayVar = (ay) asVar;
            ayVar.a(response.resp_code);
            BitSet bitSet = new BitSet();
            if (response.isSetMsg()) {
                bitSet.set(0);
            }
            if (response.isSetImprint()) {
                bitSet.set(1);
            }
            ayVar.a(bitSet, 2);
            if (response.isSetMsg()) {
                ayVar.a(response.msg);
            }
            if (response.isSetImprint()) {
                response.imprint.write(ayVar);
            }
        }

        @Override // com.umeng.commonsdk.proguard.ba
        public void b(as asVar, Response response) throws z {
            ay ayVar = (ay) asVar;
            response.resp_code = ayVar.w();
            response.setResp_codeIsSet(true);
            BitSet bitSetB = ayVar.b(2);
            if (bitSetB.get(0)) {
                response.msg = ayVar.z();
                response.setMsgIsSet(true);
            }
            if (bitSetB.get(1)) {
                response.imprint = new com.umeng.commonsdk.statistics.proto.d();
                response.imprint.read(ayVar);
                response.setImprintIsSet(true);
            }
        }
    }
}
