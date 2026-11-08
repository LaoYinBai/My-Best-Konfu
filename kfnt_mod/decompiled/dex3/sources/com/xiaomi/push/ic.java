package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ic implements iu<ic, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public List<hr> f605a;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f604a = new jk("XmPushActionCollectData");
    private static final jc a = new jc("", com.umeng.commonsdk.proguard.az.m, 1);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ic icVar) {
        int iA;
        if (!getClass().equals(icVar.getClass())) {
            return getClass().getName().compareTo(icVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m449a()).compareTo(Boolean.valueOf(icVar.m449a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (!m449a() || (iA = iv.a(this.f605a, icVar.f605a)) == 0) {
            return 0;
        }
        return iA;
    }

    public ic a(List<hr> list) {
        this.f605a = list;
        return this;
    }

    public void a() throws jg {
        if (this.f605a != null) {
            return;
        }
        throw new jg("Required field 'dataCollectionItems' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.iu
    public void a(jf jfVar) throws jg {
        jfVar.mo535a();
        while (true) {
            jc jcVarMo531a = jfVar.mo531a();
            if (jcVarMo531a.a == 0) {
                jfVar.f();
                a();
                return;
            }
            if (jcVarMo531a.f812a == 1 && jcVarMo531a.a == 15) {
                jd jdVarMo532a = jfVar.mo532a();
                this.f605a = new ArrayList(jdVarMo532a.f813a);
                for (int i = 0; i < jdVarMo532a.f813a; i++) {
                    hr hrVar = new hr();
                    hrVar.a(jfVar);
                    this.f605a.add(hrVar);
                }
                jfVar.i();
            } else {
                ji.a(jfVar, jcVarMo531a.a);
            }
            jfVar.g();
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m449a() {
        return this.f605a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m450a(ic icVar) {
        if (icVar == null) {
            return false;
        }
        boolean zM449a = m449a();
        boolean zM449a2 = icVar.m449a();
        if (zM449a || zM449a2) {
            return zM449a && zM449a2 && this.f605a.equals(icVar.f605a);
        }
        return true;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        a();
        jfVar.a(f604a);
        if (this.f605a != null) {
            jfVar.a(a);
            jfVar.a(new jd((byte) 12, this.f605a.size()));
            Iterator<hr> it = this.f605a.iterator();
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
        if (obj != null && (obj instanceof ic)) {
            return m450a((ic) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionCollectData(");
        sb.append("dataCollectionItems:");
        if (this.f605a == null) {
            sb.append("null");
        } else {
            sb.append(this.f605a);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
