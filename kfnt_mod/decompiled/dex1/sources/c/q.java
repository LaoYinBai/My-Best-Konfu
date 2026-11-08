package c;

import java.util.AbstractList;
import java.util.RandomAccess;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class q extends AbstractList<f> implements RandomAccess {

    /* renamed from: a, reason: collision with root package name */
    final f[] f2166a;

    private q(f[] fVarArr) {
        this.f2166a = fVarArr;
    }

    public static q a(f... fVarArr) {
        return new q((f[]) fVarArr.clone());
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public f get(int i) {
        return this.f2166a[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.f2166a.length;
    }
}
