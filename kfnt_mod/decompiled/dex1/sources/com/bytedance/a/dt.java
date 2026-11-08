package com.bytedance.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class dt<T> {

    /* renamed from: a, reason: collision with root package name */
    public volatile T f3516a;

    public abstract T a(Object... objArr);

    public final T b(Object... objArr) {
        if (this.f3516a == null) {
            synchronized (this) {
                if (this.f3516a == null) {
                    this.f3516a = a(objArr);
                }
            }
        }
        return this.f3516a;
    }
}
