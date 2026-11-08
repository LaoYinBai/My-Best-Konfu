package com.sigmob.sdk.base.common;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class n {
    private static com.sigmob.volley.toolbox.g a;
    private static com.sigmob.volley.toolbox.g b;

    public static com.sigmob.volley.toolbox.g a() {
        if (a == null) {
            synchronized (n.class) {
                if (a == null && com.sigmob.sdk.common.e.f.d() != null) {
                    a = new com.sigmob.volley.toolbox.g(com.sigmob.sdk.common.e.f.d(), 3);
                }
            }
        }
        return a;
    }

    public static com.sigmob.volley.toolbox.g b() {
        if (b == null) {
            synchronized (n.class) {
                if (b == null && com.sigmob.sdk.common.e.f.e() != null) {
                    b = new com.sigmob.volley.toolbox.g(com.sigmob.sdk.common.e.f.e(), 1);
                }
            }
        }
        return b;
    }

    public static com.sigmob.volley.toolbox.g c() {
        if (a == null) {
            synchronized (n.class) {
                if (a == null && com.sigmob.sdk.common.e.f.e() != null) {
                    a = new com.sigmob.volley.toolbox.g(com.sigmob.sdk.common.e.f.e(), 1);
                }
            }
        }
        return a;
    }
}
