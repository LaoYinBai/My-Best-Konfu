package com.cmic.sso.sdk.d;

import com.cmic.sso.sdk.auth.TokenListener;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static ConcurrentHashMap<String, TokenListener> f5449a = new ConcurrentHashMap<>(16);

    public static boolean a(String str) {
        return !f5449a.containsKey(str);
    }

    public static void a(String str, TokenListener tokenListener) {
        f5449a.put(str, tokenListener);
    }

    public static void b(String str) {
        f5449a.remove(str);
    }

    public static TokenListener c(String str) {
        return f5449a.get(str);
    }

    public static boolean a() {
        return f5449a.isEmpty();
    }
}
