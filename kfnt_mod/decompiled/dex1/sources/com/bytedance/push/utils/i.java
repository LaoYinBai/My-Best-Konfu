package com.bytedance.push.utils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class i<T> {

    /* renamed from: a, reason: collision with root package name */
    private volatile T f4999a;

    protected abstract T b(Object... objArr);

    public final T c(Object... objArr) {
        if (this.f4999a == null) {
            synchronized (this) {
                if (this.f4999a == null) {
                    this.f4999a = b(objArr);
                }
            }
        }
        return this.f4999a;
    }
}
