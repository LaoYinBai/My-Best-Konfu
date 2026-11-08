package com.kwai.sodler.lib.a;

import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements Comparable<a> {
    public String a;
    public String b;
    public boolean c;

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(@NonNull a aVar) {
        return aVar.b.compareTo(this.b);
    }
}
