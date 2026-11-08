package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ih implements iu<ih, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public List<hs> f643a;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f642a = new jk("XmPushActionNormalConfig");
    private static final jc a = new jc("", com.umeng.commonsdk.proguard.az.m, 1);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ih ihVar) {
        int iA;
        if (!getClass().equals(ihVar.getClass())) {
            return getClass().getName().compareTo(ihVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m474a()).compareTo(Boolean.valueOf(ihVar.m474a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (!m474a() || (iA = iv.a(this.f643a, ihVar.f643a)) == 0) {
            return 0;
        }
        return iA;
    }

    public List<hs> a() {
        return this.f643a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m473a() throws jg {
        if (this.f643a != null) {
            return;
        }
        throw new jg("Required field 'normalConfigs' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.iu
    public void a(jf jfVar) throws jg {
        jfVar.mo535a();
        while (true) {
            jc jcVarMo531a = jfVar.mo531a();
            if (jcVarMo531a.a == 0) {
                jfVar.f();
                m473a();
                return;
            }
            if (jcVarMo531a.f812a == 1 && jcVarMo531a.a == 15) {
                jd jdVarMo532a = jfVar.mo532a();
                this.f643a = new ArrayList(jdVarMo532a.f813a);
                for (int i = 0; i < jdVarMo532a.f813a; i++) {
                    hs hsVar = new hs();
                    hsVar.a(jfVar);
                    this.f643a.add(hsVar);
                }
                jfVar.i();
            } else {
                ji.a(jfVar, jcVarMo531a.a);
            }
            jfVar.g();
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m474a() {
        return this.f643a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m475a(ih ihVar) {
        if (ihVar == null) {
            return false;
        }
        boolean zM474a = m474a();
        boolean zM474a2 = ihVar.m474a();
        if (zM474a || zM474a2) {
            return zM474a && zM474a2 && this.f643a.equals(ihVar.f643a);
        }
        return true;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m473a();
        jfVar.a(f642a);
        if (this.f643a != null) {
            jfVar.a(a);
            jfVar.a(new jd((byte) 12, this.f643a.size()));
            Iterator<hs> it = this.f643a.iterator();
            while (it.hasNext()) {
                it.next().b(jfVar);
            }
            jfVar.e();
            jfVar.b();
        }
        jfVar.c();
        jfVar.mo539a();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ih)) {
            return m475a((ih) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionNormalConfig(");
        sb.append("normalConfigs:");
        if (this.f643a == null) {
            sb.append("null");
        } else {
            sb.append(this.f643a);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
