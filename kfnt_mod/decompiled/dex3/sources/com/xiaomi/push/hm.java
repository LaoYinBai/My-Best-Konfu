package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class hm implements iu<hm, Object>, Serializable, Cloneable {

    /* renamed from: a, reason: collision with other field name */
    public List<hn> f478a;

    /* renamed from: a, reason: collision with other field name */
    private static final jk f477a = new jk("ClientUploadData");
    private static final jc a = new jc("", com.umeng.commonsdk.proguard.az.m, 1);

    public int a() {
        if (this.f478a == null) {
            return 0;
        }
        return this.f478a.size();
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(hm hmVar) {
        int iA;
        if (!getClass().equals(hmVar.getClass())) {
            return getClass().getName().compareTo(hmVar.getClass().getName());
        }
        int iCompareTo = Boolean.valueOf(m384a()).compareTo(Boolean.valueOf(hmVar.m384a()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (!m384a() || (iA = iv.a(this.f478a, hmVar.f478a)) == 0) {
            return 0;
        }
        return iA;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m383a() throws jg {
        if (this.f478a != null) {
            return;
        }
        throw new jg("Required field 'uploadDataItems' was not present! Struct: " + toString());
    }

    public void a(hn hnVar) {
        if (this.f478a == null) {
            this.f478a = new ArrayList();
        }
        this.f478a.add(hnVar);
    }

    @Override // com.xiaomi.push.iu
    public void a(jf jfVar) throws jg {
        jfVar.mo535a();
        while (true) {
            jc jcVarMo531a = jfVar.mo531a();
            if (jcVarMo531a.a == 0) {
                jfVar.f();
                m383a();
                return;
            }
            if (jcVarMo531a.f812a == 1 && jcVarMo531a.a == 15) {
                jd jdVarMo532a = jfVar.mo532a();
                this.f478a = new ArrayList(jdVarMo532a.f813a);
                for (int i = 0; i < jdVarMo532a.f813a; i++) {
                    hn hnVar = new hn();
                    hnVar.a(jfVar);
                    this.f478a.add(hnVar);
                }
                jfVar.i();
            } else {
                ji.a(jfVar, jcVarMo531a.a);
            }
            jfVar.g();
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m384a() {
        return this.f478a != null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m385a(hm hmVar) {
        if (hmVar == null) {
            return false;
        }
        boolean zM384a = m384a();
        boolean zM384a2 = hmVar.m384a();
        if (zM384a || zM384a2) {
            return zM384a && zM384a2 && this.f478a.equals(hmVar.f478a);
        }
        return true;
    }

    @Override // com.xiaomi.push.iu
    public void b(jf jfVar) throws jg {
        m383a();
        jfVar.a(f477a);
        if (this.f478a != null) {
            jfVar.a(a);
            jfVar.a(new jd((byte) 12, this.f478a.size()));
            Iterator<hn> it = this.f478a.iterator();
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
        if (obj != null && (obj instanceof hm)) {
            return m385a((hm) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ClientUploadData(");
        sb.append("uploadDataItems:");
        if (this.f478a == null) {
            sb.append("null");
        } else {
            sb.append(this.f478a);
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }
}
