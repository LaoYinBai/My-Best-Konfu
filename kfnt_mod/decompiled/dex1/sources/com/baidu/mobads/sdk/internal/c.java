package com.baidu.mobads.sdk.internal;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static volatile c f3199a;

    public interface a {

        /* renamed from: a, reason: collision with root package name */
        public static final String f3200a = "remote_adserv";

        /* renamed from: b, reason: collision with root package name */
        public static final String f3201b = "remote_novel";
    }

    private c() {
    }

    public static c a() {
        if (f3199a == null) {
            synchronized (c.class) {
                if (f3199a == null) {
                    f3199a = new c();
                }
            }
        }
        return f3199a;
    }

    public ag a(String str) {
        return new ag(str);
    }
}
