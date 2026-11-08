package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ig implements iu<ig, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public List<hu> f641a;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f640a = new jk("XmPushActionCustomConfig");
    private static final jc a = new jc("", com.umeng.commonsdk.proguard.az.m, 1);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ig igVar) {
        int iA;
        if (!getClass().equals(igVar.getClass())) {
            return getClass().getName().compareTo(igVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m471a()).compareTo(Boolean.valueOf(igVar.m471a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (!m471a() || (iA = iv.a(this.f641a, igVar.f641a)) == 0) {
            return 0;
        }
        return iA;
    }

    public List<hu> a() {
        return this.f641a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m470a() throws jg {
        if (this.f641a != null) {
            return;
        }
        throw new jg("Required field 'customConfigs' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.iu
    public void a(jf jfVar) throws jg {
        jfVar.mo535a();
        while (true) {
            jc jcVarMo531a = jfVar.mo531a();
            if (jcVarMo531a.a == 0) {
                jfVar.f();
                m470a();
                return;
            }
            if (jcVarMo531a.f812a == 1 && jcVarMo531a.a == 15) {
                jd jdVarMo532a = jfVar.mo532a();
                this.f641a = new ArrayList(jdVarMo532a.f813a);
                for (int i = 0; i < jdVarMo532a.f813a; i++) {
                    hu huVar = new hu();
                    huVar.a(jfVar);
                    this.f641a.add(huVar);
                }
                jfVar.i();
            } else {
                ji.a(jfVar, jcVarMo531a.a);
            }
            jfVar.g();
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m471a() {
        return this.f641a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m472a(ig igVar) {
        if (igVar == null) {
            return false;
        }
        boolean zM471a = m471a();
        boolean zM471a2 = igVar.m471a();
        if (zM471a || zM471a2) {
            return zM471a && zM471a2 && this.f641a.equals(igVar.f641a);
        }
        return true;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m470a();
        jfVar.a(f640a);
        if (this.f641a != null) {
            jfVar.a(a);
            jfVar.a(new jd((byte) 12, this.f641a.size()));
            Iterator<hu> it = this.f641a.iterator();
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
        if (obj != null && (obj instanceof ig)) {
            return m472a((ig) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionCustomConfig(");
        sb.append("customConfigs:");
        if (this.f641a == null) {
            sb.append("null");
        } else {
            sb.append(this.f641a);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
