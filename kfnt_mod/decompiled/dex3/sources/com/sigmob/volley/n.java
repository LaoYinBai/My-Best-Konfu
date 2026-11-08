package com.sigmob.volley;

import com.sigmob.volley.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class n<T> {
    public final T a;
    public final b.a b;
    public final s c;
    public boolean d;

    public interface a {
        void onErrorResponse(s sVar);
    }

    public interface b<T> {
        void a(T t);
    }

    private n(s sVar) {
        this.d = false;
        this.a = null;
        this.b = null;
        this.c = sVar;
    }

    private n(T t, b.a aVar) {
        this.d = false;
        this.a = t;
        this.b = aVar;
        this.c = null;
    }

    public static <T> n<T> a(s sVar) {
        return new n<>(sVar);
    }

    public static <T> n<T> a(T t, b.a aVar) {
        return new n<>(t, aVar);
    }

    public boolean a() {
        return this.c == null;
    }
}
