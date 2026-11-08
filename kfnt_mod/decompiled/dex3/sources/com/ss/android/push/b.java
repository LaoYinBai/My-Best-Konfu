package com.ss.android.push;

import java.io.Serializable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class b<L, M, R> implements Serializable, Comparable<b<L, M, R>> {
    private static final long serialVersionUID = 1;

    public abstract R d();

    public abstract M e();

    public abstract L f();

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(b<L, M, R> bVar) {
        int iCompareTo = ((Comparable) f()).compareTo(bVar.f());
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int iCompareTo2 = ((Comparable) e()).compareTo(bVar.e());
        return iCompareTo2 != 0 ? iCompareTo2 : ((Comparable) d()).compareTo(bVar.d());
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return a(f(), bVar.f()) && a(e(), bVar.e()) && a(d(), bVar.d());
    }

    private boolean a(Object obj, Object obj2) {
        return (obj == null || obj2 == null || (obj != obj2 && !obj.equals(obj2))) ? false : true;
    }

    public int hashCode() {
        return ((f() == null ? 0 : f().hashCode()) ^ (e() == null ? 0 : e().hashCode())) ^ (d() != null ? d().hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(f());
        sb.append(',');
        sb.append(e());
        sb.append(',');
        sb.append(d());
        sb.append(')');
        return sb.toString();
    }
}
