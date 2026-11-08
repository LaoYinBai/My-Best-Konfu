package com.kwad.sdk.core.config;

import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static volatile c b;
    private Map<String, String> a = new HashMap();

    private c() {
    }

    public static c a() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    public synchronized void a(String str, String str2) {
        this.a.put(str, str2);
    }
}
