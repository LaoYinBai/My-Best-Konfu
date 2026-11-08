package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.aa;
import com.umeng.commonsdk.proguard.ad;
import com.xiaomi.mipush.sdk.Constants;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class ad<T extends ad<?, ?>, F extends aa> implements t<T, F> {
    private static final Map<Class<? extends ba>, bb> c = new HashMap();
    protected Object a;
    protected F b;

    protected abstract F a(short s);

    protected abstract Object a(as asVar, an anVar) throws z;

    protected abstract Object a(as asVar, short s) throws z;

    protected abstract void a(as asVar) throws z;

    protected abstract void b(F f, Object obj) throws ClassCastException;

    protected abstract void b(as asVar) throws z;

    protected abstract an c(F f);

    protected abstract ax d();

    protected ad() {
        this.b = null;
        this.a = null;
    }

    static {
        c.put(bc.class, new b());
        c.put(bd.class, new d());
    }

    protected ad(F f, Object obj) throws ClassCastException {
        a((ad<T, F>) f, obj);
    }

    protected ad(ad<T, F> adVar) {
        if (!adVar.getClass().equals(getClass())) {
            throw new ClassCastException();
        }
        this.b = adVar.b;
        this.a = a(adVar.a);
    }

    private static Object a(Object obj) {
        if (obj instanceof t) {
            return ((t) obj).deepCopy();
        }
        if (obj instanceof ByteBuffer) {
            return u.d((ByteBuffer) obj);
        }
        if (obj instanceof List) {
            return a((List) obj);
        }
        if (obj instanceof Set) {
            return a((Set) obj);
        }
        return obj instanceof Map ? a((Map<Object, Object>) obj) : obj;
    }

    private static Map a(Map<Object, Object> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            map2.put(a(entry.getKey()), a(entry.getValue()));
        }
        return map2;
    }

    private static Set a(Set set) {
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            hashSet.add(a(it.next()));
        }
        return hashSet;
    }

    private static List a(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return arrayList;
    }

    public F a() {
        return this.b;
    }

    public Object b() {
        return this.a;
    }

    public Object a(F f) {
        if (f != this.b) {
            throw new IllegalArgumentException("Cannot get the value of field " + f + " because union's set field is " + this.b);
        }
        return b();
    }

    public Object a(int i) {
        return a((ad<T, F>) a((short) i));
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean b(F f) {
        return this.b == f;
    }

    public boolean b(int i) {
        return b((ad<T, F>) a((short) i));
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void read(as asVar) throws z {
        c.get(asVar.D()).b().b(asVar, this);
    }

    public void a(F f, Object obj) throws ClassCastException {
        b(f, obj);
        this.b = f;
        this.a = obj;
    }

    public void a(int i, Object obj) throws ClassCastException {
        a((ad<T, F>) a((short) i), obj);
    }

    @Override // com.umeng.commonsdk.proguard.t
    public void write(as asVar) throws z {
        c.get(asVar.D()).b().a(asVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getClass().getSimpleName());
        sb.append(" ");
        if (a() != null) {
            Object objB = b();
            sb.append(c(a()).a);
            sb.append(Constants.COLON_SEPARATOR);
            if (objB instanceof ByteBuffer) {
                u.a((ByteBuffer) objB, sb);
            } else {
                sb.append(objB.toString());
            }
        }
        sb.append(">");
        return sb.toString();
    }

    @Override // com.umeng.commonsdk.proguard.t
    public final void clear() {
        this.b = null;
        this.a = null;
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

    private static class a extends bc<ad> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(as asVar, ad adVar) throws z {
            adVar.b = null;
            adVar.a = null;
            asVar.j();
            an anVarL = asVar.l();
            adVar.a = adVar.a(asVar, anVarL);
            if (adVar.a != null) {
                adVar.b = (F) adVar.a(anVarL.c);
            }
            asVar.m();
            asVar.l();
            asVar.k();
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(as asVar, ad adVar) throws z {
            if (adVar.a() == null || adVar.b() == null) {
                throw new at("Cannot write a TUnion with no set value!");
            }
            asVar.a(adVar.d());
            asVar.a(adVar.c(adVar.b));
            adVar.a(asVar);
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

    private static class c extends bd<ad> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(as asVar, ad adVar) throws z {
            adVar.b = null;
            adVar.a = null;
            short sV = asVar.v();
            adVar.a = adVar.a(asVar, sV);
            if (adVar.a != null) {
                adVar.b = (F) adVar.a(sV);
            }
        }

        @Override // com.umeng.commonsdk.proguard.ba
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(as asVar, ad adVar) throws z {
            if (adVar.a() == null || adVar.b() == null) {
                throw new at("Cannot write a TUnion with no set value!");
            }
            asVar.a(adVar.b.a());
            adVar.b(asVar);
        }
    }
}
