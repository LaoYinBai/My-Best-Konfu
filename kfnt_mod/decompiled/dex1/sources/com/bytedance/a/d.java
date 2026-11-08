package com.bytedance.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class d<T> {

    /* renamed from: a, reason: collision with root package name */
    public volatile T f3484a;

    public abstract T a(Object... objArr);

    public final T b(Object... objArr) {
        if (this.f3484a == null) {
            synchronized (this) {
                if (this.f3484a == null) {
                    this.f3484a = a(objArr);
                }
            }
        }
        return this.f3484a;
    }
}
